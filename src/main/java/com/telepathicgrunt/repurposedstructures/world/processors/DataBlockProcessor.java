package com.telepathicgrunt.repurposedstructures.world.processors;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSProcessors;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.arguments.BlockStateParser;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

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
    public Template.BlockInfo process(IWorldReader worldView, BlockPos pos, BlockPos blockPos, Template.BlockInfo structureBlockInfoLocal, Template.BlockInfo structureBlockInfoWorld, PlacementSettings structurePlacementData) {
        BlockState blockState = structureBlockInfoWorld.state;
        if (blockState.isIn(Blocks.STRUCTURE_BLOCK)) {
            String string = structureBlockInfoWorld.nbt.getString("metadata");

            try {
                // Pillar mode activated
                if(string.contains(DATA_PROCESSOR_MODE.PILLARS.symbol)){
                    String[] splitString = string.split(DATA_PROCESSOR_MODE.PILLARS.symbol);

                    // Parses the data block's name field to get direction, blockstate, and depth
                    Direction direction = Direction.valueOf(splitString[0].toUpperCase());
                    BlockStateParser blockArgumentParser = new BlockStateParser(new StringReader(splitString[1]), false);
                    blockArgumentParser.parse(true);
                    BlockState replacementState = blockArgumentParser.getState();
                    BlockState currentBlock = worldView.getBlockState(structureBlockInfoWorld.pos);
                    BlockPos.Mutable currentPos = new BlockPos.Mutable().setPos(structureBlockInfoWorld.pos);
                    int depth = splitString.length > 2 ? parseInt(splitString[2]) + 1 : 256;

                    // Creates the pillars in the world that replaces air and liquids
                    while((currentBlock.isAir() || currentBlock.getMaterial().isLiquid()) &&
                            currentPos.getY() <= worldView.getDimension().getLogicalHeight() &&
                            currentPos.getY() >= 0 &&
                            currentPos.withinDistance(structureBlockInfoWorld.pos, depth)
                    ){
                        worldView.getChunk(currentPos).setBlockState(currentPos, replacementState, false);
                        currentPos.move(direction);
                        currentBlock = worldView.getBlockState(currentPos);
                    }

                    // Replaces the data block itself
                    return replacementState.isIn(Blocks.STRUCTURE_VOID) ? null : new Template.BlockInfo(structureBlockInfoWorld.pos, replacementState, null);
                }
            }
            catch (CommandSyntaxException var11) {
                throw new RuntimeException(var11);
            }
        }
        return structureBlockInfoWorld;
    }

    @Override
    protected IStructureProcessorType<?> getType() {
        return RSProcessors.DATA_BLOCK_PROCESSORS;
    }
}