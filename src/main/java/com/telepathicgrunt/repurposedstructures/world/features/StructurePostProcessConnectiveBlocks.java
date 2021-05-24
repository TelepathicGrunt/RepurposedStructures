package com.telepathicgrunt.repurposedstructures.world.features;

import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;


public class StructurePostProcessConnectiveBlocks extends Feature<NoFeatureConfig> {

    public StructurePostProcessConnectiveBlocks() {
        super(NoFeatureConfig.CODEC);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {

        BlockPos.Mutable currentBlockMutable = new BlockPos.Mutable();
        BlockPos.Mutable offsetMutable = new BlockPos.Mutable();
        ChunkPos currentChunkPos = new ChunkPos(position);
        IChunk currentChunk = world.getChunk(currentChunkPos.x, currentChunkPos.z);
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
                            currentState  = currentState.updateShape(
                                    direction,
                                    sideBlock,
                                    world,
                                    currentBlockMutable,
                                    offsetMutable
                            );
                        }
                        world.setBlock(currentBlockMutable, currentState, 3);
                    }
                    else if(currentState.getBlock() instanceof FenceBlock){
                        for(Direction direction : Direction.Plane.HORIZONTAL){
                            offsetMutable.set(currentBlockMutable).move(direction);
                            if (currentChunkPos.x != offsetMutable.getX() >> 4 || currentChunkPos.z != offsetMutable.getZ() >> 4) {
                                continue;
                            }
                            BlockState sideBlock = currentChunk.getBlockState(offsetMutable);
                            currentState  = currentState.updateShape(
                                    direction,
                                    sideBlock,
                                    world,
                                    currentBlockMutable,
                                    offsetMutable
                            );
                        }
                        world.setBlock(currentBlockMutable, currentState, 3);
                    }
                }
            }
        }

        return true;
    }
}