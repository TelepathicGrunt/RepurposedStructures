package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.ArrayList;
import java.util.Random;


public class EndShipwreckStructure extends AbstractBaseStructure {
    // Special thanks to cannon_foddr for allowing me to use his End Shipwreck design!

    public EndShipwreckStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }
    static {
        StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"shipwrecks/end"), new Identifier("empty"),
                        ImmutableList.of(
                            Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_backhalf", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_backhalf_degraded", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_fronthalf", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_fronthalf_degraded", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_full", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_full_degraded", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/sideways_backhalf", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/sideways_backhalf_degraded", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/sideways_fronthalf", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/sideways_fronthalf_degraded", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/sideways_full", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/sideways_full_degraded", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_backhalf", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_backhalf_degraded", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_fronthalf_degraded", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_fronthalf", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_full", new ArrayList<>()), 1),
                                Pair.of(new SinglePoolElement(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_full_degraded", new ArrayList<>()), 1)),
                        StructurePool.Projection.RIGID));
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return EndShipwreckStructure.Start::new;
    }


    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
        return getGenerationHeight(x, z, chunkGenerator) >= 20;
    }

    private static int getGenerationHeight(int chunkX, int chunkZ, ChunkGenerator chunkGenerator) {
        Random random = new Random(chunkX + chunkZ * 10387313);
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

        int k = (chunkX << 4) + 7;
        int l = (chunkZ << 4) + 7;
        int m = chunkGenerator.getHeightInGround(k, l, Heightmap.Type.WORLD_SURFACE_WG);
        int n = chunkGenerator.getHeightInGround(k, l + j, Heightmap.Type.WORLD_SURFACE_WG);
        int o = chunkGenerator.getHeightInGround(k + i, l, Heightmap.Type.WORLD_SURFACE_WG);
        int p = chunkGenerator.getHeightInGround(k + i, l + j, Heightmap.Type.WORLD_SURFACE_WG);
        return Math.min(Math.min(m, n), Math.min(o, p));
    }

    public static class Start extends StructureStart<DefaultFeatureConfig> {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        private static Identifier SHIPWRECK_IDENTIFIER = new Identifier(RepurposedStructures.MODID + ":shipwrecks/end");

        @Override
        public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 62, chunkZ * 16);
            GeneralJigsawGenerator.addPieces(chunkGenerator, structureManager, blockpos, this.children, this.random, SHIPWRECK_IDENTIFIER, 1);
            this.setBoundingBoxFromChildren();

            BlockPos blockPos = new BlockPos(this.children.get(0).getBoundingBox().getCenter());
            int highestLandPos = chunkGenerator.getHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
            highestLandPos = Math.max(30, highestLandPos);
            this.method_14976(this.random, highestLandPos-5, highestLandPos-3);
        }
    }
}