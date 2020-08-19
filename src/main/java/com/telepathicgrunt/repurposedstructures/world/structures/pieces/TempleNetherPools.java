package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.StructureProcessorListAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.template.*;


public class TempleNetherPools {
    public static void initPools(){

        //Basalt temple
        StructureProcessorList randomizationList = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":temples/basalt_temple_randomizer",
                ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.013F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.MAGMA_BLOCK.getDefaultState()),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.014F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.015F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.OBSIDIAN.getDefaultState()),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.02F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.BASALT.getDefaultState()),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.01F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.GILDED_BLACKSTONE.getDefaultState())))));

        JigsawPatternRegistry.register(
        new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_basalt"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                JigsawPiece.method_30435(RepurposedStructures.MODID+":temples/temple_nether_basalt", randomizationList), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));


        //Crimson Temple
        StructureProcessorList randomizationList1 = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":temples/crimson_temple_randomizer",
                ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.02F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.NETHER_WART_BLOCK.getDefaultState()),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.3F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.4F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.5F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_NYLIUM, 0.15F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.NETHER_WART_BLOCK.getDefaultState()),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_STEM, 0.22F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState())))));

        JigsawPatternRegistry.register(
        new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_crimson"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                JigsawPiece.method_30435(RepurposedStructures.MODID+":temples/temple_nether_crimson", randomizationList1), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));


        //Soul Temple
        StructureProcessorList randomizationList2 = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":temples/soul_temple_randomizer",
                ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.SOUL_SOIL, 0.1F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_SAND.getDefaultState()),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.SOUL_SAND, 0.05F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_SOIL.getDefaultState())))));

        JigsawPatternRegistry.register(
        new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_soul"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                JigsawPiece.method_30435(RepurposedStructures.MODID+":temples/temple_nether_soul", randomizationList2), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));


        //Warped Temple
        StructureProcessorList randomizationList3 = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":temples/warped_temple_randomizer",
                ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WARPED_HYPHAE, 0.015F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.WARPED_WART_BLOCK.getDefaultState()),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WARPED_HYPHAE, 0.3F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.WARPED_HYPHAE.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WARPED_HYPHAE, 0.4F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.WARPED_HYPHAE.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WARPED_HYPHAE, 0.5F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.WARPED_HYPHAE.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WARPED_NYLIUM, 0.15F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.WARPED_WART_BLOCK.getDefaultState()),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WARPED_STEM, 0.22F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.WARPED_HYPHAE.getDefaultState())))));

        JigsawPatternRegistry.register(
        new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_warped"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                JigsawPiece.method_30435(RepurposedStructures.MODID+":temples/temple_nether_warped", randomizationList3), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));


        //Wasteland Temple
        StructureProcessorList randomizationList4 = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":temples/wasteland_temple_randomizer",
                ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.015F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.MAGMA_BLOCK.getDefaultState()),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.12F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.BLACK_TERRACOTTA.getDefaultState()),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.22F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.RED_NETHER_BRICKS.getDefaultState()),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.2F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_NETHER_BRICKS.getDefaultState())))));

        JigsawPatternRegistry.register(
        new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/temple_nether_wasteland"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                JigsawPiece.method_30435(RepurposedStructures.MODID+":temples/temple_nether_wasteland", randomizationList4), 1)),
                JigsawPattern.PlacementBehaviour.RIGID));


    }
}