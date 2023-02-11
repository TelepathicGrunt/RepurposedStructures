package com.telepathicgrunt.repurposedstructures.world.features;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;


public class StructurePostProcessConnectiveBlocks extends Feature<NoneFeatureConfiguration> {

    public StructurePostProcessConnectiveBlocks() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {

        BlockPos.MutableBlockPos currentBlockMutable = new BlockPos.MutableBlockPos();
        ChunkPos currentChunkPos = new ChunkPos(context.origin());
        ChunkAccess currentChunk = context.level().getChunk(currentChunkPos.x, currentChunkPos.z);
        for(int x = -1; x <= 1; x++) {
            for(int z = -1; z <= 1; z++) {
                // only run the connection code in adjacent spots
                if(Math.abs(x) + Math.abs(z) != 1) continue;

                for(int y = 0; y >= -2; y--) {
                    currentBlockMutable.set(context.origin()).move(x, y, z);
                    if (currentChunkPos.x != currentBlockMutable.getX() >> 4 || currentChunkPos.z != currentBlockMutable.getZ() >> 4) {
                        currentChunk = context.level().getChunk(currentBlockMutable);
                        currentChunkPos = new ChunkPos(currentBlockMutable);
                    }

                    BlockState currentBlock = currentChunk.getBlockState(currentBlockMutable);
                    if(currentBlock.getBlock() instanceof FenceBlock || currentBlock.getBlock() instanceof WallBlock) {
                        placeConnectBlock(context, currentBlockMutable, currentChunkPos, currentChunk, currentBlock);
                    }
                }
            }
        }

        return true;
    }

    protected static void placeConnectBlock(FeaturePlaceContext<?> context, BlockPos.MutableBlockPos currentBlockMutable, ChunkPos currentChunkPos, ChunkAccess currentChunk, BlockState incomingBlockState) {
        BlockPos.MutableBlockPos offsetMutable = new BlockPos.MutableBlockPos();
        if(incomingBlockState.getBlock() instanceof WallBlock) {
            BlockState currentState = incomingBlockState.getBlock().defaultBlockState();
            for(Direction direction : Direction.values()) {
                offsetMutable.set(currentBlockMutable).move(direction);
                if (currentChunkPos.x != offsetMutable.getX() >> 4 || currentChunkPos.z != offsetMutable.getZ() >> 4) {
                    continue;
                }
                BlockState sideBlock = currentChunk.getBlockState(offsetMutable);
                currentState  = currentState.updateShape(
                        direction,
                        sideBlock,
                        context.level(),
                        currentBlockMutable,
                        offsetMutable
                );
            }
            if(currentState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                currentState = currentState.setValue(BlockStateProperties.WATERLOGGED, incomingBlockState.getValue(BlockStateProperties.WATERLOGGED));
            }
            context.level().setBlock(currentBlockMutable, currentState, 3);
        }
        else if(incomingBlockState.getBlock() instanceof FenceBlock) {
            BlockState currentState = incomingBlockState.getBlock().defaultBlockState();
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                offsetMutable.set(currentBlockMutable).move(direction);
                if (currentChunkPos.x != offsetMutable.getX() >> 4 || currentChunkPos.z != offsetMutable.getZ() >> 4) {
                    continue;
                }
                BlockState sideBlock = currentChunk.getBlockState(offsetMutable);
                currentState  = currentState.updateShape(
                        direction,
                        sideBlock,
                        context.level(),
                        currentBlockMutable,
                        offsetMutable
                );
            }
            if(currentState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                currentState = currentState.setValue(BlockStateProperties.WATERLOGGED, incomingBlockState.getValue(BlockStateProperties.WATERLOGGED));
            }
            context.level().setBlock(currentBlockMutable, currentState, 3);
        }
        else {
            context.level().setBlock(currentBlockMutable, incomingBlockState, 3);
        }
    }
}