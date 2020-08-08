package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

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


public class TempleNetherPools{
    public static void initPools(){
    }

    static {
        //Basalt Temple
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


        //Crimson Temple
        ImmutableList<StructureProcessor> randomizationList1 = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235379_ms_, 0.02F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.NETHER_WART_BLOCK.getDefaultState()),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235379_ms_, 0.3F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235379_ms_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235379_ms_, 0.4F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235379_ms_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235379_ms_, 0.5F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235379_ms_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235381_mu_, 0.15F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.NETHER_WART_BLOCK.getDefaultState()),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235377_mq_, 0.22F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235379_ms_.getDefaultState()))));

        JigsawManager.REGISTRY.register(
                new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_crimson"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":temples/temple_nether_crimson", randomizationList1), 1)),
                        JigsawPattern.PlacementBehaviour.RIGID));


        //Soul Temple
        ImmutableList<StructureProcessor> randomizationList2 = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235336_cN_, 0.1F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_SAND.getDefaultState()),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.SOUL_SAND, 0.05F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235336_cN_.getDefaultState()))));

        JigsawManager.REGISTRY.register(
                new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_soul"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":temples/temple_nether_soul", randomizationList2), 1)),
                        JigsawPattern.PlacementBehaviour.RIGID));


        //Warped Temple
        ImmutableList<StructureProcessor> randomizationList3 = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
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
                        new SingleJigsawPiece(RepurposedStructures.MODID+":temples/temple_nether_warped", randomizationList3), 1)),
                        JigsawPattern.PlacementBehaviour.RIGID));


        //Wasteland Temple
        ImmutableList<StructureProcessor> randomizationList4 = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.015F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.MAGMA_BLOCK.getDefaultState()),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.12F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.BLACK_TERRACOTTA.getDefaultState()),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.22F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.RED_NETHER_BRICKS.getDefaultState()),
                new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.2F),
                        AlwaysTrueRuleTest.INSTANCE, Blocks.field_235394_nH_.getDefaultState()))));

        JigsawManager.REGISTRY.register(
                new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_wasteland"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                        new SingleJigsawPiece(RepurposedStructures.MODID+":temples/temple_nether_wasteland", randomizationList4), 1)),
                        JigsawPattern.PlacementBehaviour.RIGID));
    }
}