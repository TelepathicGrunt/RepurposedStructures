package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;


public record StructurePostProcessConnectiveBlocks() implements Feature{

    public static final MapCodec<StructurePostProcessConnectiveBlocks> CODEC = MapCodec.unit(StructurePostProcessConnectiveBlocks::new);

    @Override
    public MapCodec<StructurePostProcessConnectiveBlocks> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        BlockPos.MutableBlockPos currentBlockMutable = new BlockPos.MutableBlockPos();
        ChunkPos currentChunkPos = ChunkPos.containing(origin);
        ChunkAccess currentChunk = level.getChunk(currentChunkPos.x(), currentChunkPos.z());
        for(int x = -1; x <= 1; x++) {
            for(int z = -1; z <= 1; z++) {
                // only run the connection code in adjacent spots
                if(Math.abs(x) + Math.abs(z) != 1) continue;

                for(int y = 0; y >= -2; y--) {
                    currentBlockMutable.set(origin).move(x, y, z);
                    if (currentChunkPos.x() != currentBlockMutable.getX() >> 4 || currentChunkPos.z() != currentBlockMutable.getZ() >> 4) {
                        currentChunk = level.getChunk(currentBlockMutable);
                        currentChunkPos = ChunkPos.containing(currentBlockMutable);
                    }

                    BlockState currentBlock = currentChunk.getBlockState(currentBlockMutable);
                    if(currentBlock.getBlock() instanceof FenceBlock || currentBlock.getBlock() instanceof WallBlock) {
                        placeConnectBlock(level, random, currentBlockMutable, currentChunkPos, currentChunk, currentBlock);
                    }
                }
            }
        }

        return true;
    }

    static void placeConnectBlock(WorldGenLevel level, RandomSource random, BlockPos.MutableBlockPos currentBlockMutable, ChunkPos currentChunkPos, ChunkAccess currentChunk, BlockState incomingBlockState) {
        BlockPos.MutableBlockPos offsetMutable = new BlockPos.MutableBlockPos();
        if(incomingBlockState.getBlock() instanceof WallBlock) {
            BlockState currentState = incomingBlockState.getBlock().defaultBlockState();
            for(Direction direction : Direction.values()) {
                offsetMutable.set(currentBlockMutable).move(direction);
                BlockState sideBlock = currentChunk.getBlockState(offsetMutable);
                currentState  = currentState.updateShape(
                        level,
                        level,
                        currentBlockMutable,
                        direction,
                        offsetMutable,
                        sideBlock,
                        random
                );

                if (currentChunkPos.x() != offsetMutable.getX() >> 4 || currentChunkPos.z() != offsetMutable.getZ() >> 4) {
                    currentState = currentState.setValue(WallBlock.PROPERTY_BY_DIRECTION.get(direction), WallSide.NONE);
                }
            }
            if(currentState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                currentState = currentState.setValue(BlockStateProperties.WATERLOGGED, incomingBlockState.getValue(BlockStateProperties.WATERLOGGED));
            }
            level.setBlock(currentBlockMutable, currentState, 3);
        }
        else if(incomingBlockState.getBlock() instanceof FenceBlock) {
            BlockState currentState = incomingBlockState.getBlock().defaultBlockState();
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                offsetMutable.set(currentBlockMutable).move(direction);
                BlockState sideBlock = currentChunk.getBlockState(offsetMutable);
                currentState  = currentState.updateShape(
                        level,
                        level,
                        currentBlockMutable,
                        direction,
                        offsetMutable,
                        sideBlock,
                        random
                );

                if (currentChunkPos.x() != offsetMutable.getX() >> 4 || currentChunkPos.z() != offsetMutable.getZ() >> 4) {
                    currentState = currentState.setValue(FenceBlock.PROPERTY_BY_DIRECTION.get(direction), false);
                }
            }
            if(currentState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                currentState = currentState.setValue(BlockStateProperties.WATERLOGGED, incomingBlockState.getValue(BlockStateProperties.WATERLOGGED));
            }
            level.setBlock(currentBlockMutable, currentState, 3);
        }
        else {
            level.setBlock(currentBlockMutable, incomingBlockState, 3);
        }
    }
}