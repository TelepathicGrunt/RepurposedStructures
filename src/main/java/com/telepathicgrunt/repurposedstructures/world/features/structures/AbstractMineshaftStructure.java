package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;


public abstract class AbstractMineshaftStructure extends StructureFeature<DefaultFeatureConfig> {

    public AbstractMineshaftStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long l, ChunkRandom chunkRandom, int i, int j, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig featureConfig) {
        chunkRandom.setCarverSeed(l, i, j);
        double chance = mineshaftProbability()/10000D;
        double roll = chunkRandom.nextDouble();
        return roll < chance;
    }

    public abstract double mineshaftProbability();
}