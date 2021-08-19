package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.math.Vector3f;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;


public class StrongholdEndStructure extends AdvancedDistanceJigsawStructure {

    public StrongholdEndStructure(ResourceLocation poolID, int structureSize, int biomeRange,
                                  int maxY, int minY, boolean clipOutOfBoundsPieces,
                                  Integer verticalRange, int distanceFromWorldOrigin)
    {
        super(poolID, structureSize, biomeRange, maxY, minY, clipOutOfBoundsPieces, verticalRange, distanceFromWorldOrigin);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, WorldgenRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration featureConfig, LevelHeightAccessor heightLimitView) {
        boolean superCheck = super.isFeatureChunk(chunkGenerator, biomeSource, seed, chunkRandom, chunkPos1, biome, chunkPos, featureConfig, heightLimitView);
        if(!superCheck)
            return false;

        int minLandHeight = Math.min(chunkGenerator.getGenDepth(), chunkGenerator.getMinY() + 45);
        int xPos = chunkPos1.getMinBlockX();
        int zPos = chunkPos1.getMinBlockZ();
        int landHeight = Integer.MAX_VALUE;

        for(int i = 2; i >= 1; i--){
            for(Direction direction : Direction.Plane.HORIZONTAL){
                Vector3f offsetPos = direction.step();
                offsetPos.mul(35f * i);
                landHeight = getHeightAt(chunkGenerator, heightLimitView, xPos + (int)offsetPos.x(), zPos + (int)offsetPos.z(), landHeight);
                if(landHeight < minLandHeight) return false;
            }
        }

        landHeight = getHeightAt(chunkGenerator, heightLimitView, xPos, zPos, landHeight);
        return landHeight >= minLandHeight;
    }

    private int getHeightAt(ChunkGenerator chunkGenerator, LevelHeightAccessor heightLimitView, int xPos, int zPos, int landHeight) {
        landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos, zPos, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView));
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
