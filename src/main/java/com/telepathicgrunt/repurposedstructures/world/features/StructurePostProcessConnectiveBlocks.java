package com.telepathicgrunt.repurposedstructures.world.features;

import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructurePostProcessConnectiveBlocks extends Feature<DefaultFeatureConfig> {

    public StructurePostProcessConnectiveBlocks() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config) {

        BlockPos.Mutable currentBlockMutable = new BlockPos.Mutable();
        BlockPos.Mutable offsetMutable = new BlockPos.Mutable();
        ChunkPos currentChunkPos = new ChunkPos(position);
        Chunk currentChunk = world.getChunk(currentChunkPos.x, currentChunkPos.z);
        for(int x = -1; x <= 1; x++){
            for(int z = -1; z <= 1; z++){
                for(int y = -2; y <= 0; y++){
                    currentBlockMutable.set(position).move(x, y, z);

                    if (currentChunkPos.x != currentBlockMutable.getX() >> 4 || currentChunkPos.z != currentBlockMutable.getZ() >> 4) {
                        currentChunk = world.getChunk(currentBlockMutable);
                        currentChunkPos = new ChunkPos(currentBlockMutable);
                    }

                    BlockState currentState = currentChunk.getBlockState(currentBlockMutable);
                    if(currentState.getBlock() instanceof WallBlock){
                        for(Direction direction : Direction.values()){
                            offsetMutable.set(currentBlockMutable).move(direction);
                            if (currentChunkPos.x != offsetMutable.getX() >> 4 || currentChunkPos.z != offsetMutable.getZ() >> 4) {
                                continue;
                            }
                            BlockState sideBlock = currentChunk.getBlockState(offsetMutable);
                            currentState  = currentState.getStateForNeighborUpdate(
                                    direction,
                                    sideBlock,
                                    world,
                                    currentBlockMutable,
                                    offsetMutable
                            );
                        }
                        world.setBlockState(currentBlockMutable, currentState, 3);
                    }
                    else if(currentState.getBlock() instanceof FenceBlock){
                        for(Direction direction : Direction.Type.HORIZONTAL){
                            offsetMutable.set(currentBlockMutable).move(direction);
                            if (currentChunkPos.x != offsetMutable.getX() >> 4 || currentChunkPos.z != offsetMutable.getZ() >> 4) {
                                continue;
                            }
                            BlockState sideBlock = currentChunk.getBlockState(offsetMutable);
                            currentState  = currentState.getStateForNeighborUpdate(
                                    direction,
                                    sideBlock,
                                    world,
                                    currentBlockMutable,
                                    offsetMutable
                            );
                        }
                        world.setBlockState(currentBlockMutable, currentState, 3);
                    }
                }
            }
        }

        return true;
    }
}