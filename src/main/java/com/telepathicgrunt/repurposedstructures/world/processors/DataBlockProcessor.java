package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.commands.arguments.blocks.BlockStateParser;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Locale;

import static java.lang.Integer.parseInt;

/**
 * POOL ENTRY MUST BE USING legacy_single_pool_element OR ELSE THE STRUCTURE BLOCK IS REMOVED BEFORE THIS PROCESSOR RUNS.
 */
public class DataBlockProcessor extends StructureProcessor {

    private enum DATA_PROCESSOR_MODE {
        PILLARS("-");

        private final String symbol;
        DATA_PROCESSOR_MODE(String symbol) {
            this.symbol = symbol;
        }
    }

    public static final Codec<DataBlockProcessor> CODEC = Codec.unit(DataBlockProcessor::new);
    private DataBlockProcessor() { }

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader worldView, BlockPos pos, BlockPos blockPos, StructureTemplate.StructureBlockInfo structureBlockInfoLocal, StructureTemplate.StructureBlockInfo structureBlockInfoWorld, StructurePlaceSettings structurePlacementData) {
        BlockState blockState = structureBlockInfoWorld.state;
        if (blockState.is(Blocks.STRUCTURE_BLOCK)) {
            String metadata = structureBlockInfoWorld.nbt.getString("metadata");
            BlockPos worldPos = structureBlockInfoWorld.pos;

            try {
                // Pillar mode activated
                if(metadata.contains(DATA_PROCESSOR_MODE.PILLARS.symbol)){
                    String[] splitString = metadata.split(DATA_PROCESSOR_MODE.PILLARS.symbol);

                    // Parses the data block's field to get direction, blockstate, and depth
                    Direction direction = Direction.valueOf(splitString[0].toUpperCase(Locale.ROOT));
                    BlockStateParser blockArgumentParser = new BlockStateParser(new StringReader(splitString[1]), false);
                    blockArgumentParser.parse(true);
                    BlockState replacementState = blockArgumentParser.getState();
                    BlockState currentBlock = worldView.getBlockState(worldPos);
                    BlockPos.MutableBlockPos currentPos = new BlockPos.MutableBlockPos().set(worldPos);

                    int depth = 256;
                    if(splitString.length > 2){
                        String thirdArgument = splitString[2];
                        if(NumberUtils.isParsable(thirdArgument)){
                            depth = parseInt(thirdArgument) + 1;
                        }
                    }

                    // Creates the pillars in the world that replaces air and liquids
                    while(!currentBlock.canOcclude() &&
                            currentPos.getY() <= worldView.dimensionType().logicalHeight() &&
                            currentPos.getY() >= 0 &&
                            currentPos.closerThan(worldPos, depth)
                    ){
                        StructureTemplate.StructureBlockInfo newPillarState1 = new StructureTemplate.StructureBlockInfo(structureBlockInfoLocal.pos, replacementState, null);
                        StructureTemplate.StructureBlockInfo newPillarState2 = new StructureTemplate.StructureBlockInfo(currentPos.immutable(), replacementState, null);

                        for(StructureProcessor processor : structurePlacementData.getProcessors()){
                            if(newPillarState2 == null){
                                break;
                            }
                            newPillarState2 = processor.processBlock(worldView, pos, blockPos, newPillarState1, newPillarState2, structurePlacementData);
                        }

                        if(newPillarState2 != null){
                            worldView.getChunk(currentPos).setBlockState(currentPos, newPillarState2.state, false);
                        }

                        currentPos.move(direction);
                        currentBlock = worldView.getBlockState(currentPos);
                    }

                    // Replaces the data block itself
                    return replacementState == null || replacementState.is(Blocks.STRUCTURE_VOID) ? null : new StructureTemplate.StructureBlockInfo(worldPos, replacementState, null);
                }
            }
            catch (CommandSyntaxException var11) {
                throw new RuntimeException(var11);
            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return RSProcessors.DATA_BLOCK_PROCESSOR;
    }
}
