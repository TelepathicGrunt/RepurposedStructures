package com.telepathicgrunt.repurposedstructures.world.structures;

import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.util.Lazy;


public class StrongholdEndStructure extends AdvancedDistanceJigsawStructure {

    public StrongholdEndStructure(ResourceLocation poolID, int structureSize, int biomeRange,
                                  Lazy<Integer> maxY, Lazy<Integer> minY, boolean clipOutOfBoundsPieces,
                                  Lazy<Integer> verticalRange, int distanceFromWorldOrigin)
    {
        super(poolID, structureSize, biomeRange, maxY, minY, clipOutOfBoundsPieces, verticalRange, distanceFromWorldOrigin);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        boolean superCheck = super.isFeatureChunk(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, featureConfig);
        if(!superCheck)
            return false;

        int minLandHeight = Math.min(chunkGenerator.getGenDepth(), 45);
        int xPos = chunkX * 16;
        int zPos = chunkZ * 16;
        int landHeight = getHeightAt(chunkGenerator, xPos, zPos, Integer.MAX_VALUE);
        if(landHeight < minLandHeight) return false;

        for(int i = 1; i <= 2; i++){
            for(Direction direction : Direction.Plane.HORIZONTAL){
                Vector3f offsetPos = new Vector3f(direction.getStepX(), direction.getStepY(), direction.getStepZ());
                offsetPos.mul(35f * i);
                landHeight = getHeightAt(chunkGenerator, xPos + (int)offsetPos.x(), zPos + (int)offsetPos.z(), landHeight);
                if(landHeight < minLandHeight) return false;
            }
        }

        return true;
    }

    private int getHeightAt(ChunkGenerator chunkGenerator, int xPos, int zPos, int landHeight) {
        landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos, zPos, Heightmap.Type.WORLD_SURFACE_WG));
        return landHeight;
    }

    public static class Builder<T extends StrongholdEndStructure.Builder<T>> extends AdvancedDistanceJigsawStructure.Builder<T> {

        public Builder(ResourceLocation startPool) {
            super(startPool);
        }

        public StrongholdEndStructure build() {
            return new StrongholdEndStructure(
                    startPool,
                    structureSize,
                    biomeRange,
                    maxY,
                    minY,
                    clipOutOfBoundsPieces,
                    verticalRange,
                    distanceFromWorldOrigin);
        }
    }
}
