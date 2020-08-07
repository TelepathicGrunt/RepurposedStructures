package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.*;


public class TempleNetherWarpedStructure extends Structure<NoFeatureConfig> {
    static {
        ImmutableList<StructureProcessor> randomizationList = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235370_mj_, 0.015F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235374_mn_.getDefaultState()),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235370_mj_, 0.3F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235370_mj_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235370_mj_, 0.4F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235370_mj_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235370_mj_, 0.5F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235370_mj_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235372_ml_, 0.15F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235374_mn_.getDefaultState()),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235368_mh_, 0.22F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235370_mj_.getDefaultState()))));

        JigsawManager.REGISTRY.register(
                new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_warped"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":temples/temple_nether_warped", randomizationList), 1)),
                        JigsawPattern.PlacementBehaviour.RIGID));
    }

    public TempleNetherWarpedStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return TempleNetherWarpedStructure.Start::new;
    }

    public static class Start extends AbstractNetherStructure.AbstractStart{
        ResourceLocation NETHER_TEMPLE_POOL = new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_warped");

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
                this.func_214626_a(this.rand, lowestLandPos.getY() - 16, lowestLandPos.getY() - 15);
            }
        }
    }
}