package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import java.util.Map;


public class MineshaftStructure extends AdvancedJigsawStructure {

    protected final double probability;

    public MineshaftStructure(ResourceLocation poolID, int structureSize, int biomeRange,
                              Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces,
                              int maxY, int minY, boolean clipOutOfBoundsPieces, Integer verticalRange,
                              double probability)
    {
        super(poolID, structureSize, biomeRange, requiredPieces, maxY, minY, clipOutOfBoundsPieces, verticalRange);
        this.probability = probability;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        StructureSeparationSettings structureConfig = chunkGenerator.getSettings().getConfig(this);
        if(structureConfig != null) {
            chunkRandom.setLargeFeatureSeed(seed + structureConfig.salt(), chunkX, chunkZ);
            double d = (this.probability / 10000D);
            return chunkRandom.nextDouble() < d;
        }
        return false;
    }

    public static class Builder<T extends Builder<T>> extends AdvancedJigsawStructure.Builder<T> {

        protected double probability = 0.01;

        public Builder(ResourceLocation startPool) {
            super(startPool);
        }

        public T setProbability(double probability){
            this.probability = probability;
            return getThis();
        }

        public MineshaftStructure build() {
            return new MineshaftStructure(
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