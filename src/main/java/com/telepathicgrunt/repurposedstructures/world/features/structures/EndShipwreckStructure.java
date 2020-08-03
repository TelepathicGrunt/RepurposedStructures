package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.EndCityStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.ArrayList;
import java.util.Random;


public class EndShipwreckStructure extends AbstractBaseStructure {
    // Special thanks to cannon_foddr for allowing me to use his End Shipwreck design!

    public EndShipwreckStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }
    static {
        JigsawManager.REGISTRY.register(
                new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"shipwrecks/end"), new ResourceLocation("empty"),
                        ImmutableList.of(
                            Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_backhalf", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_backhalf_degraded", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_fronthalf", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_fronthalf_degraded", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_full", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/rightsideup_full_degraded", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/sideways_backhalf", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/sideways_backhalf_degraded", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/sideways_fronthalf", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/sideways_fronthalf_degraded", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/sideways_full", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/sideways_full_degraded", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_backhalf", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_backhalf_degraded", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_fronthalf_degraded", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_fronthalf", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_full", new ArrayList<>()), 1),
                                Pair.of(new SingleJigsawPiece(RepurposedStructures.MODID+":shipwrecks/end/upsidedown_full_degraded", new ArrayList<>()), 1)),
                        JigsawPattern.PlacementBehaviour.RIGID));
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return EndShipwreckStructure.Start::new;
    }

    protected boolean shouldStartAt(ChunkGenerator generator, BiomeProvider biomeProvider, long seed, SharedSeedRandom random, int x, int z, Biome biome, ChunkPos chunkPos, NoFeatureConfig config) {
        return getYPosForStructure(x, z, generator) >= 20;
    }

    private static int getYPosForStructure(int x, int z, ChunkGenerator generator) {
        Random random = new Random((long)(x + z * 10387313));
        Rotation rotation = Rotation.randomRotation(random);
        int i = 5;
        int j = 5;
        if (rotation == Rotation.CLOCKWISE_90) {
            i = -5;
        } else if (rotation == Rotation.CLOCKWISE_180) {
            i = -5;
            j = -5;
        } else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
            j = -5;
        }

        int k = (x << 4) + 7;
        int l = (z << 4) + 7;
        int i1 = generator.func_222531_c(k, l, Heightmap.Type.WORLD_SURFACE_WG);
        int j1 = generator.func_222531_c(k, l + j, Heightmap.Type.WORLD_SURFACE_WG);
        int k1 = generator.func_222531_c(k + i, l, Heightmap.Type.WORLD_SURFACE_WG);
        int l1 = generator.func_222531_c(k + i, l + j, Heightmap.Type.WORLD_SURFACE_WG);
        return Math.min(Math.min(i1, j1), Math.min(k1, l1));
    }

    public static class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        private static ResourceLocation SHIPWRECK_ResourceLocation = new ResourceLocation(RepurposedStructures.MODID + ":shipwrecks/end");

        @Override
        public void init(ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 62, chunkZ * 16);
            GeneralJigsawGenerator.addPieces(chunkGenerator, structureManager, blockpos, this.components, this.rand, SHIPWRECK_ResourceLocation, 1);
            this.recalculateStructureSize();

            BlockPos blockPos = new BlockPos(this.components.get(0).getBoundingBox().func_215126_f());
            int highestLandPos = chunkGenerator.func_222529_a(blockPos.getX(), blockPos.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
            highestLandPos = Math.max(30, highestLandPos);
            this.func_214626_a(this.rand, highestLandPos-5, highestLandPos-3);
        }
    }
}