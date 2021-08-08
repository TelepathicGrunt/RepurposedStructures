package com.telepathicgrunt.repurposedstructures.world.structures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.util.Lazy;


public class StrongholdEndStructure extends AdvancedDistanceJigsawStructure {

    public StrongholdEndStructure(ResourceLocation poolID, int structureSize, int biomeRange,
                                  Lazy<Integer> maxY, Lazy<Integer> minY, boolean clipOutOfBoundsPieces,
                                  Integer verticalRange, int distanceFromWorldOrigin)
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
        int landHeight = chunkGenerator.getFirstOccupiedHeight(xPos, zPos, Heightmap.Type.WORLD_SURFACE_WG);

        landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos + 70, zPos, Heightmap.Type.WORLD_SURFACE_WG));
        if(landHeight < minLandHeight) return false;

        landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos, zPos + 70, Heightmap.Type.WORLD_SURFACE_WG));
        if(landHeight < minLandHeight) return false;

        landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos - 70, zPos, Heightmap.Type.WORLD_SURFACE_WG));
        if(landHeight < minLandHeight) return false;

        landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos, zPos - 70, Heightmap.Type.WORLD_SURFACE_WG));
        return landHeight >= minLandHeight;
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
