package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.VillageGenerator;
import net.minecraft.structure.VillageStructureStart;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public abstract class AbstractVillageStructure extends StructureFeature<DefaultFeatureConfig> {
    public AbstractVillageStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    public abstract StructureFeature<DefaultFeatureConfig> getVillageInstance();

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
         for (int curChunkX = chunkX - 1; curChunkX <= chunkX + 1; curChunkX++) {
            for (int curChunkZ = chunkZ - 1; curChunkZ <= chunkZ + 1; curChunkZ++) {
                if (!biomeSource.getBiomeForNoiseGen(curChunkX << 2, 60, curChunkZ << 2).hasStructureFeature(getVillageInstance())) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public BlockPos locateStructure(WorldView worldView, StructureAccessor structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureConfig structureConfig) {
        return AbstractBaseStructure.locateStructureFast(worldView, structureAccessor, blockPos, radius, skipExistingChunks, seed, structureConfig, this);
    }

    public static abstract class AbstractStart extends VillageStructureStart<DefaultFeatureConfig> {
        public AbstractStart(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public abstract Identifier getIdentifier();
        public abstract int getSize();

        private static boolean initalizedPools = false;

        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            if(!initalizedPools){
                registerVillagePools();
                initalizedPools = true;
            }

            BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            VillageGenerator.addPieces(chunkGenerator, structureManager, blockpos, this.children, this.random, new StructurePoolFeatureConfig(getIdentifier(), getSize()));
            this.setBoundingBoxFromChildren();
        }

        public static void registerVillagePools() {
            VillageBadlandsPools.init();
            VillageBirchPools.init();
            VillageDarkForestPools.init();
            VillageJunglePools.init();
            VillageSwampPools.init();
            VillageMountainsPools.init();
            VillageGiantTaigaPools.init();
            VillageCrimsonPools.init();
            VillageWarpedPools.init();
        }
    }
}