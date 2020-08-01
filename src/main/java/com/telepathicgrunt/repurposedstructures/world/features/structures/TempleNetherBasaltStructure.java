package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.*;


public class TempleNetherBasaltStructure extends Structure<NoFeatureConfig> {
    static {
        ImmutableList<StructureProcessor> randomizationList = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.013F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.MAGMA_BLOCK.getDefaultState()),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.014F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235412_nv_.getDefaultState()),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.015F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.OBSIDIAN.getDefaultState()),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.02F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235337_cO_.getDefaultState()),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.01F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235387_nA_.getDefaultState()))));

        JigsawManager.REGISTRY.register(
                new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_basalt"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":temples/temple_nether_basalt", randomizationList), 1)),
                        JigsawPattern.PlacementBehaviour.RIGID));
    }

    public TempleNetherBasaltStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return TempleNetherBasaltStructure.Start::new;
    }

    public static class Start extends AbstractNetherStructure.AbstractStart{
        ResourceLocation NETHER_TEMPLE_POOL = new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_basalt");

        public Start(Structure<NoFeatureConfig> structureFeature, int x, int z, MutableBoundingBox blockBox, int referenceIn, long seed) {
            super(structureFeature, x, z, blockBox, referenceIn, seed);
        }

        public void init(ChunkGenerator chunkGenerator, TemplateManager structureManager, int i, int j, Biome biome, NoFeatureConfig NoFeatureConfig) {
            BlockPos blockPos = new BlockPos(i * 16, 35, j * 16);
            GeneralJigsawGenerator.addPieces(chunkGenerator, structureManager, blockPos, this.components, this.rand, NETHER_TEMPLE_POOL, 1);
            this.recalculateStructureSize();

            BlockPos lowestLandPos = getLowestLand(chunkGenerator);
            if (lowestLandPos.getY() >= 108 || lowestLandPos.getY() <= 33) {
                this.func_214626_a(this.rand, 16, 17);
            }
            else {
                this.func_214626_a(this.rand, lowestLandPos.getY() - 17, lowestLandPos.getY() - 16);
            }
        }
    }
}