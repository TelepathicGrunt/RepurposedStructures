package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSMineshaftPieces;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Map;


public class RSNewMineshaftStructure extends AdvancedJigsawStructure {

    protected final double probability;

    public RSNewMineshaftStructure(Identifier poolID, int structureSize, Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, int maxY, int minY, float probability) {
        super(poolID, structureSize, requiredPieces, maxY, minY);
        this.probability = probability;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig featureConfig) {
        StructureConfig structureConfig = chunkGenerator.getStructuresConfig().getForType(this);
        if(structureConfig != null) {
            chunkRandom.setCarverSeed(seed + structureConfig.getSalt(), x, z);
            double d = (probability / 10000D);
            return chunkRandom.nextDouble() < d;
        }
        return false;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return AdvancedJigsawStructure.MainStart::new;
    }
}