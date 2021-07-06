package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Random;


public class ShipwreckEndStructure extends AbstractBaseStructure<DefaultFeatureConfig> {
    // Special thanks to cannon_foddr for allowing me to use his End Shipwreck design!

    private final Identifier START_POOL;

    public ShipwreckEndStructure() {
        super(DefaultFeatureConfig.CODEC);
        START_POOL = new Identifier(RepurposedStructures.MODID, "shipwrecks/end");
        RSStructures.RS_STRUCTURE_START_PIECES.add(START_POOL);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return ShipwreckEndStructure.Start::new;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
        return getGenerationHeight(chunkPos1, chunkGenerator, heightLimitView) >= Math.min(chunkGenerator.getWorldHeight(), 20);
    }

    private static int getGenerationHeight(ChunkPos chunkPos1, ChunkGenerator chunkGenerator, HeightLimitView heightLimitView) {
        Random random = new Random(chunkPos1.x + chunkPos1.z * 10387313L);
        BlockRotation blockRotation = BlockRotation.random(random);
        int i = 5;
        int j = 5;
        if (blockRotation == BlockRotation.CLOCKWISE_90) {
            i = -5;
        } else if (blockRotation == BlockRotation.CLOCKWISE_180) {
            i = -5;
            j = -5;
        } else if (blockRotation == BlockRotation.COUNTERCLOCKWISE_90) {
            j = -5;
        }

        int k = (chunkPos1.x << 4) + 7;
        int l = (chunkPos1.z << 4) + 7;
        int m = chunkGenerator.getHeightInGround(k, l, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView);
        int n = chunkGenerator.getHeightInGround(k, l + j, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView);
        int o = chunkGenerator.getHeightInGround(k + i, l, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView);
        int p = chunkGenerator.getHeightInGround(k + i, l + j, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView);
        return Math.min(Math.min(m, n), Math.min(o, p));
    }

    public class Start extends StructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            BlockPos blockpos = new BlockPos(chunkPos1.getStartX(), 64, chunkPos1.getStartZ());
            StructurePoolBasedGenerator.generate(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.STRUCTURE_POOL_KEY).get(START_POOL), 1),
                    PoolStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this,
                    random,
                    true,
                    false,
                    heightLimitView);
            this.setBoundingBoxFromChildren();

            BlockPos blockPos = new BlockPos(this.children.get(0).getBoundingBox().getCenter());
            int highestLandPos = chunkGenerator.getHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Type.WORLD_SURFACE_WG, heightLimitView);
            highestLandPos = Math.max(30, highestLandPos);
            this.randomUpwardTranslation(this.random, highestLandPos-5, highestLandPos-3);
        }
    }
}