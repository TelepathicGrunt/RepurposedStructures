package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

public abstract class AbstractVillageStructure extends Structure<NoFeatureConfig> {
    public AbstractVillageStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    public abstract Structure<NoFeatureConfig> getVillageInstance();

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig NoFeatureConfig) {
         for (int curChunkX = chunkX - 1; curChunkX <= chunkX + 1; curChunkX++) {
            for (int curChunkZ = chunkZ - 1; curChunkZ <= chunkZ + 1; curChunkZ++) {
                if (!biomeSource.getBiomeForNoiseGen(curChunkX << 2, 60, curChunkZ << 2).hasStructure(getVillageInstance())) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public BlockPos locateStructure(IWorldReader worldView, StructureManager structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureSeparationSettings structureConfig) {
        return AbstractBaseStructure.locateStructureFast(worldView, structureAccessor, blockPos, radius, skipExistingChunks, seed, structureConfig, this);
    }

    public static abstract class AbstractStart extends MarginedStructureStart<NoFeatureConfig> {
        public AbstractStart(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public abstract ResourceLocation getResourceLocation();
        public abstract int getSize();

        private static boolean initalizedPools = false;

        public void init(ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            if(!initalizedPools){
                RSFeatures.registerVillagePools();
                initalizedPools = true;
            }

            BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            VillagePieces.func_214838_a(chunkGenerator, structureManager, blockpos, this.components, this.rand, new VillageConfig(getResourceLocation(), getSize()));
            this.recalculateStructureSize();
        }
    }
}