package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.ArrayList;


public class EndShipwreckStructure extends AbstractBaseStructure {
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