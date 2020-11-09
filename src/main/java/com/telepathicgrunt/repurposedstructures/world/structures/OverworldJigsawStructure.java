package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class OverworldJigsawStructure extends AbstractBaseStructure {

    private final Identifier START_POOL;
    private final int STRUCTURE_SIZE;
    private final int CENTER_OFFSET;

    public OverworldJigsawStructure(Identifier poolID, int structureSize, int centerOffset) {
        super(DefaultFeatureConfig.CODEC);
        START_POOL = poolID;
        STRUCTURE_SIZE = structureSize;
        CENTER_OFFSET = centerOffset;
        RSStructures.RS_STRUCTURE_START_PIECES.add(START_POOL);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
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
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return OverworldJigsawStructure.MainStart::new;
    }

    public class MainStart extends MarginedStructureStart<DefaultFeatureConfig> {
        public MainStart(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            StructurePoolBasedGenerator.method_30419(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN).get(START_POOL), STRUCTURE_SIZE),
                    PoolStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.children,
                    this.random,
                    true,
                    true);
            this.setBoundingBoxFromChildren();
            this.children.get(0).translate(0, CENTER_OFFSET, 0);
        }
    }
}