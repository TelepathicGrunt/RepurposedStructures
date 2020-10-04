package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

public class VillageBaseStructure extends Structure<NoFeatureConfig> {
    private static boolean INITIALIZED_POOLS = false;
    private final ResourceLocation START_POOL;
    private final int STRUCTURE_SIZE;

    public VillageBaseStructure(Codec<NoFeatureConfig> config, ResourceLocation poolRL, int structureSize) {
        super(config);
        START_POOL = poolRL;
        STRUCTURE_SIZE = structureSize;
        RSStructures.RS_STRUCTURE_START_PIECES.add(START_POOL);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig NoFeatureConfig) {
         for (int curChunkX = chunkX - 1; curChunkX <= chunkX + 1; curChunkX++) {
            for (int curChunkZ = chunkZ - 1; curChunkZ <= chunkZ + 1; curChunkZ++) {
                if (!biomeSource.getBiomeForNoiseGen(curChunkX << 2, 60, curChunkZ << 2).getGenerationSettings().hasStructureFeature(this)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return VillageBaseStructure.MainStart::new;
    }

    @Override
    public BlockPos locateStructure(IWorldReader worldView, StructureManager structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureSeparationSettings structureConfig) {
        return AbstractBaseStructure.locateStructureFast(worldView, structureAccessor, blockPos, radius, skipExistingChunks, seed, structureConfig, this);
    }

    public class MainStart extends MarginedStructureStart<NoFeatureConfig> {
        public MainStart(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void init(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {

            BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            JigsawManager.method_30419(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN)
                            .getOrDefault(START_POOL),
                            STRUCTURE_SIZE),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.components,
                    this.rand,
                    true,
                    true);
            this.recalculateStructureSize();
        }
    }
}