package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.argument.BlockArgumentParser;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import org.apache.commons.lang3.math.NumberUtils;

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
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfoLocal, Structure.StructureBlockInfo structureBlockInfoWorld, StructurePlacementData structurePlacementData) {
        BlockState blockState = structureBlockInfoWorld.state;
        if (blockState.isOf(Blocks.STRUCTURE_BLOCK)) {
            String metadata = structureBlockInfoWorld.tag.getString("metadata");
            BlockPos worldPos = structureBlockInfoWorld.pos;

            try {
                // Pillar mode activated
                if(metadata.contains(DATA_PROCESSOR_MODE.PILLARS.symbol)){
                    String[] splitString = metadata.split(DATA_PROCESSOR_MODE.PILLARS.symbol);

                    // Parses the data block's field to get direction, blockstate, and depth
                    Direction direction = Direction.valueOf(splitString[0].toUpperCase());
                    BlockArgumentParser blockArgumentParser = new BlockArgumentParser(new StringReader(splitString[1]), false);
                    blockArgumentParser.parse(true);
                    BlockState replacementState = blockArgumentParser.getBlockState();
                    BlockState currentBlock = worldView.getBlockState(worldPos);
                    BlockPos.Mutable currentPos = new BlockPos.Mutable().set(worldPos);

                    int depth = 256;
                    if(splitString.length > 2){
                        String thirdArgument = splitString[2];
                        if(NumberUtils.isParsable(thirdArgument)){
                            depth = parseInt(thirdArgument) + 1;
                        }
                    }

                    // Creates the pillars in the world that replaces air and liquids
                    while((currentBlock.isAir() || currentBlock.getMaterial().isLiquid()) &&
                            currentPos.getY() <= worldView.getDimension().getLogicalHeight() &&
                            currentPos.getY() >= 0 &&
                            currentPos.isWithinDistance(worldPos, depth)
                    ){
                        Structure.StructureBlockInfo newPillarState1 = new Structure.StructureBlockInfo(structureBlockInfoLocal.pos.subtract(currentPos), structureBlockInfoLocal.state, structureBlockInfoLocal.tag);
                        Structure.StructureBlockInfo newPillarState2 = new Structure.StructureBlockInfo(currentPos.toImmutable(), structureBlockInfoLocal.state, structureBlockInfoLocal.tag);

                        for(StructureProcessor processor : structurePlacementData.getProcessors()){
                            if(newPillarState2 == null){
                                break;
                            }
                            newPillarState2 = processor.process(worldView, pos, blockPos, newPillarState1, newPillarState2, structurePlacementData);
                        }

                        if(newPillarState2 != null){
                            worldView.getChunk(currentPos).setBlockState(currentPos, newPillarState2.state, false);
                        }

                        currentPos.move(direction);
                        currentBlock = worldView.getBlockState(currentPos);
                    }

                    // Replaces the data block itself
                    return replacementState == null || replacementState.isOf(Blocks.STRUCTURE_VOID) ? null : new Structure.StructureBlockInfo(worldPos, replacementState, null);
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
