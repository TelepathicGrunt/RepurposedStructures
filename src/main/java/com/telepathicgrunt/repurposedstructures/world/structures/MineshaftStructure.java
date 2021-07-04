package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Map;


public class MineshaftStructure extends AdvancedJigsawStructure {

    protected final double probability;

    public MineshaftStructure(Identifier poolID, int structureSize, int biomeRange,
                              Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces,
                              int maxY, int minY, boolean clipOutOfBoundsPieces, Integer verticalRange,
                              double probability)
    {
        super(poolID, structureSize, biomeRange, requiredPieces, maxY, minY, clipOutOfBoundsPieces, verticalRange);
        this.probability = probability;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig featureConfig, HeightLimitView heightLimitView) {
        StructureConfig structureConfig = chunkGenerator.getStructuresConfig().getForType(this);
        if(structureConfig != null) {
            chunkRandom.setCarverSeed(seed + structureConfig.getSalt(), chunkPos1.x, chunkPos1.z);
            double d = (probability / 10000D);
            return chunkRandom.nextDouble() < d;
        }
        return false;
    }

    public static class Builder<T extends MineshaftStructure.Builder<?>> extends AdvancedJigsawStructure.Builder<T> {

        protected double probability = 0.01;

        public Builder(Identifier startPool) {
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