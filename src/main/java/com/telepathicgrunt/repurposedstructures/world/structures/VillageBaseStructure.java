package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.*;
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

public class VillageBaseStructure extends StructureFeature<DefaultFeatureConfig> {

    private static boolean INITIALIZED_POOLS = false;
    private final Identifier POOL_ID;
    private final int STRUCTURE_SIZE;

    public VillageBaseStructure(Codec<DefaultFeatureConfig> config, Identifier poolID, int structureSize) {
        super(config);
        POOL_ID = poolID;
        STRUCTURE_SIZE = structureSize;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
         for (int curChunkX = chunkX - 1; curChunkX <= chunkX + 1; curChunkX++) {
            for (int curChunkZ = chunkZ - 1; curChunkZ <= chunkZ + 1; curChunkZ++) {
                if (!biomeSource.getBiomeForNoiseGen(curChunkX << 2, 60, curChunkZ << 2).hasStructureFeature(this)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return MainStart::new;
    }

    @Override
    public BlockPos locateStructure(WorldView worldView, StructureAccessor structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureConfig structureConfig) {
        return AbstractBaseStructure.locateStructureFast(worldView, structureAccessor, blockPos, radius, skipExistingChunks, seed, structureConfig, this);
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

    public class MainStart extends VillageStructureStart<DefaultFeatureConfig> {
        public MainStart(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            if(!INITIALIZED_POOLS){
                registerVillagePools();
                INITIALIZED_POOLS = true;
            }

            BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            VillageGenerator.addPieces(chunkGenerator, structureManager, blockpos, this.children, this.random, new StructurePoolFeatureConfig(POOL_ID, STRUCTURE_SIZE));
            this.setBoundingBoxFromChildren();
        }
    }
}