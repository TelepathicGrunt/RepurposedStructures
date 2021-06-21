package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.BlockView;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Map;


public class RSMineshaftStructure extends AdvancedJigsawStructure {

    protected final double probability;
    protected final ENVIRONMENT_CHECK environmentCheck;
    public enum ENVIRONMENT_CHECK {
        NONE,
        LIQUID,
        AIR
    }

    public RSMineshaftStructure(Identifier poolID, int structureSize, Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, int maxY, int minY, float probability, ENVIRONMENT_CHECK environmentCheck) {
        super(poolID, structureSize, requiredPieces, maxY, minY);
        this.probability = probability;
        this.environmentCheck = environmentCheck;
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

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return RSMineshaftStructure.Start::new;
    }


    public class Start extends AdvancedJigsawStructure.MainStart {

        public Start(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }
    }
}