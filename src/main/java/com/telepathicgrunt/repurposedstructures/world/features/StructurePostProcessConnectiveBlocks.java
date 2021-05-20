package com.telepathicgrunt.repurposedstructures.world.features;

import net.minecraft.block.*;
import net.minecraft.util.math.*;
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

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockPos.Mutable mutable2 = new BlockPos.Mutable();
        ChunkPos currentChunkPos = new ChunkPos(position);
        Chunk currentChunk = world.getChunk(currentChunkPos.x, currentChunkPos.z);
        for(int x = -1; x <= 1; x++){
            for(int z = -1; z <= 1; z++){
                for(int y = -2; y <= 0; y++){
                    mutable.set(position).move(x, y, z);

                    if (currentChunkPos.x != mutable.getX() >> 4 || currentChunkPos.z != mutable.getZ() >> 4) {
                        currentChunk = world.getChunk(mutable);
                        currentChunkPos = new ChunkPos(mutable);
                    }

                    BlockState currentState = currentChunk.getBlockState(mutable);
                    if(currentState.getBlock() instanceof WallBlock){
                        for(Direction direction : Direction.values()){
                            mutable2.set(mutable).move(direction);
                            if (currentChunkPos.x != mutable2.getX() >> 4 || currentChunkPos.z != mutable2.getZ() >> 4) {
                                continue;
                            }
                            BlockState sideBlock = currentChunk.getBlockState(mutable2);
                            currentState  = currentState.getStateForNeighborUpdate(
                                    direction,
                                    sideBlock,
                                    world,
                                    mutable,
                                    mutable2
                            );
                        }
                        world.setBlockState(mutable, currentState, 3);
                    }
                    else if(currentState.getBlock() instanceof FenceBlock){
                        for(Direction direction : Direction.Type.HORIZONTAL){
                            mutable2.set(mutable).move(direction);
                            if (currentChunkPos.x != mutable2.getX() >> 4 || currentChunkPos.z != mutable2.getZ() >> 4) {
                                continue;
                            }
                            BlockState sideBlock = currentChunk.getBlockState(mutable2);
                            currentState  = currentState.getStateForNeighborUpdate(
                                    direction,
                                    sideBlock,
                                    world,
                                    mutable,
                                    mutable2
                            );
                        }
                        world.setBlockState(mutable, currentState, 3);
                    }
                }
            }
        }

        return true;
    }
}