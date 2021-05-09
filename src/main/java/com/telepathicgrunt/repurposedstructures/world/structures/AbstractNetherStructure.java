package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;


public abstract class AbstractNetherStructure extends AbstractBaseStructure<NoFeatureConfig> {
    public AbstractNetherStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig defaultFeatureConfig) {

        // No one can be within 6 chunks of outpost
        boolean isNetherOutpost = RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST).contains(this);
        int radius = isNetherOutpost ? 6 : 3;
        for (int curChunkX = chunkX - radius; curChunkX <= chunkX + radius; curChunkX++) {
            for (int curChunkZ = chunkZ - radius; curChunkZ <= chunkZ + radius; curChunkZ++) {
                if(curChunkX == chunkX && curChunkZ == chunkZ) continue; // Prevent detecting the structure itself and thus, never spawning if structure is in its own blacklist

                for(Structure<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE)) {
                    StructureSeparationSettings structureConfig = chunkGenerator.getSettings().getConfig(structureFeature);
                    if(structureConfig != null && structureConfig.spacing() > 8){
                        ChunkPos chunkPos2 = structureFeature.getPotentialFeatureChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                }
            }
        }

        return super.isFeatureChunk(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, defaultFeatureConfig);
    }

    public static abstract class AbstractStart extends MarginedStructureStart<NoFeatureConfig> {
        public AbstractStart(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }


        public BlockPos getHighestLand(ChunkGenerator chunkGenerator){
            BlockPos.Mutable mutable = new BlockPos.Mutable().set(this.boundingBox.getCenter().getX(), chunkGenerator.getGenDepth() - 20, this.boundingBox.getCenter().getZ());
            IBlockReader blockView = chunkGenerator.getBaseColumn(mutable.getX(), mutable.getZ());
            BlockState currentBlockstate;
            while(mutable.getY() > chunkGenerator.getSeaLevel() - 2){
                currentBlockstate = blockView.getBlockState(mutable);
                if(!currentBlockstate.isRedstoneConductor(blockView, mutable)){
                    mutable.move(Direction.DOWN);
                    continue;
                }
                else if(blockView.getBlockState(mutable.offset(0,3,0)).getMaterial() == Material.AIR && !currentBlockstate.isAir())
                {
                    break;
                }
                mutable.move(Direction.DOWN);
            }

            return mutable;
        }

        public BlockPos getLowestLand(ChunkGenerator chunkGenerator){
            BlockPos.Mutable mutable = new BlockPos.Mutable().set(this.boundingBox.getCenter().getX(), chunkGenerator.getSeaLevel() + 3, this.boundingBox.getCenter().getZ());
            IBlockReader blockView = chunkGenerator.getBaseColumn(mutable.getX(), mutable.getZ());
            BlockState currentBlockstate = blockView.getBlockState(mutable);
            while (mutable.getY() <= chunkGenerator.getGenDepth() - 20) {

                if(blockView.getBlockState(mutable).getMaterial() != Material.AIR &&
                        blockView.getBlockState(mutable.above()).getMaterial() == Material.AIR &&
                        blockView.getBlockState(mutable.above(5)).getMaterial() == Material.AIR &&
                        !currentBlockstate.isAir())
                {
                    mutable.move(Direction.UP);
                    break;
                }

                mutable.move(Direction.UP);
                currentBlockstate = blockView.getBlockState(mutable);
            }

            return mutable;
        }
    }
}