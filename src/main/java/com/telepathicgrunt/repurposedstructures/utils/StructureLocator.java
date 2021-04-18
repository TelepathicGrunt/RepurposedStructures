package com.telepathicgrunt.repurposedstructures.utils;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructureFeature;

/**
 * For misc locating of structures like eyes of ender
 */
public class StructureLocator {

    public static BlockPos returnClosestStronghold(BlockPos blockPos, ServerWorld world, BlockPos playerPos){
        ChunkGenerator chunkGenerator = world.getChunkManager().getChunkGenerator();
        BlockPos closestPos = blockPos;
        for(StructureFeature<?> strongholdVariant : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.STRONGHOLD)){
            closestPos = returnCloserPos(closestPos, chunkGenerator.locateStructure(world, strongholdVariant, playerPos, 100, false), playerPos);
        }
        return closestPos;
    }

    private static BlockPos returnCloserPos(BlockPos blockPos1, BlockPos blockPos2, BlockPos centerPos){
        if(blockPos1 == null && blockPos2 == null){
            return null;
        }
        else if(blockPos1 == null){
            return blockPos2;
        }
        else if(blockPos2 == null){
            return blockPos1;
        }

        double distance1 = Math.pow(blockPos1.getX() - centerPos.getX(), 2) + Math.pow(blockPos1.getZ() - centerPos.getZ(), 2);
        double distance2 = Math.pow(blockPos2.getX() - centerPos.getX(), 2) + Math.pow(blockPos2.getZ() - centerPos.getZ(), 2);
        if(distance1 < distance2){
            return blockPos1;
        }
        else{
            return blockPos2;
        }
    }
}
