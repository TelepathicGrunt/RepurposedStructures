package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;


public class MineshaftEndStructure extends MineshaftStructure {

    public MineshaftEndStructure(ResourceLocation poolID, int structureSize, int biomeRange,
                                 Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces,
                                 int maxY, int minY, boolean clipOutOfBoundsPieces, Integer verticalRange,
                                 double probability)
    {
        super(poolID, structureSize, biomeRange, requiredPieces, maxY, minY, clipOutOfBoundsPieces, verticalRange, probability);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, WorldgenRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration featureConfig, LevelHeightAccessor heightLimitView) {
        StructureFeatureConfiguration structureConfig = chunkGenerator.getSettings().getConfig(this);
        if(structureConfig != null) {
            chunkRandom.setLargeFeatureSeed(seed + structureConfig.salt(), chunkPos1.x, chunkPos1.z);
            double d = (probability / 10000D);
            if (chunkRandom.nextDouble() < d) {
                if(RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.barrensIslandsEndMineshafts)
                    return true;

                int minLandHeight = Math.min(chunkGenerator.getGenDepth(), 45);
                int xPos = chunkPos1.getMinBlockX();
                int zPos = chunkPos1.getMinBlockZ();
                int landHeight = chunkGenerator.getFirstOccupiedHeight(xPos, zPos, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);

                landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos + 70, zPos, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView));
                if(landHeight < minLandHeight) return false;

                landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos, zPos + 70, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView));
                if(landHeight < minLandHeight) return false;

                landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos - 70, zPos, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView));
                if(landHeight < minLandHeight) return false;

                landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos, zPos - 70, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView));
                return landHeight >= minLandHeight;
            }
        }
        return false;
    }


    public static class Builder<T extends MineshaftEndStructure.Builder<T>> extends AdvancedJigsawStructure.Builder<T> {

        protected double probability = 0.01;

        public Builder(ResourceLocation startPool) {
            super(startPool);
        }

        public T setProbability(double probability){
            this.probability = probability;
            return getThis();
        }

        public MineshaftEndStructure build() {
            return new MineshaftEndStructure(
                    startPool,
                    structureSize,
                    biomeRange,
                    requiredPieces,
                    maxY,
                    minY,
                    clipOutOfBoundsPieces,
                    verticalRange,
                    probability);
        }
    }
}