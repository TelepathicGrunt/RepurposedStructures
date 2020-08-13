package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.StructureProcessorListAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;


public class TempleNetherPools {
    public static void initPools(){
    }

    static {
        //Basalt temple
        StructureProcessorList randomizationList = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":temples/basalt_temple_randomizer",
                ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.013F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.MAGMA_BLOCK.getDefaultState()),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.014F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.015F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.OBSIDIAN.getDefaultState()),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.02F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.BASALT.getDefaultState()),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.01F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.GILDED_BLACKSTONE.getDefaultState())))));

        StructurePools.register(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"temples/temple_nether_basalt"), new Identifier("empty"), ImmutableList.of(Pair.of(
                        StructurePoolElement.method_30426(RepurposedStructures.MODID+":temples/temple_nether_basalt", randomizationList), 1)),
                        StructurePool.Projection.RIGID));


        //Crimson Temple
        StructureProcessorList randomizationList1 = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":temples/crimson_temple_randomizer",
                ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.02F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.NETHER_WART_BLOCK.getDefaultState()),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.3F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X)),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.4F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.Y)),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.5F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.Z)),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_NYLIUM, 0.15F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.NETHER_WART_BLOCK.getDefaultState()),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_STEM, 0.22F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState())))));

        StructurePools.register(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"temples/temple_nether_crimson"), new Identifier("empty"), ImmutableList.of(Pair.of(
                        StructurePoolElement.method_30426(RepurposedStructures.MODID+":temples/temple_nether_crimson", randomizationList1), 1)),
                        StructurePool.Projection.RIGID));


        //Soul Temple
        StructureProcessorList randomizationList2 = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":temples/soul_temple_randomizer",
                ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SOUL_SOIL, 0.1F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_SAND.getDefaultState()),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SOUL_SAND, 0.05F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_SOIL.getDefaultState())))));

        StructurePools.register(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"temples/temple_nether_soul"), new Identifier("empty"), ImmutableList.of(Pair.of(
                        StructurePoolElement.method_30426(RepurposedStructures.MODID+":temples/temple_nether_soul", randomizationList2), 1)),
                        StructurePool.Projection.RIGID));

        //Warped Temple
        StructureProcessorList randomizationList3 = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":temples/warped_temple_randomizer",
                ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WARPED_HYPHAE, 0.015F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.WARPED_WART_BLOCK.getDefaultState()),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WARPED_HYPHAE, 0.3F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.WARPED_HYPHAE.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.X)),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WARPED_HYPHAE, 0.4F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.WARPED_HYPHAE.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.Y)),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WARPED_HYPHAE, 0.5F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.WARPED_HYPHAE.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.Z)),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WARPED_NYLIUM, 0.15F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.WARPED_WART_BLOCK.getDefaultState()),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WARPED_STEM, 0.22F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.WARPED_HYPHAE.getDefaultState())))));

        StructurePools.register(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"temples/temple_nether_warped"), new Identifier("empty"), ImmutableList.of(Pair.of(
                        StructurePoolElement.method_30426(RepurposedStructures.MODID+":temples/temple_nether_warped", randomizationList3), 1)),
                        StructurePool.Projection.RIGID));


        //Wasteland Temple
        StructureProcessorList randomizationList4 = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":temples/wasteland_temple_randomizer",
                ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.015F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.MAGMA_BLOCK.getDefaultState()),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.12F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.BLACK_TERRACOTTA.getDefaultState()),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.22F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.RED_NETHER_BRICKS.getDefaultState()),
                    new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.2F),
                            AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_NETHER_BRICKS.getDefaultState())))));

        StructurePools.register(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"temples/temple_nether_wasteland"), new Identifier("empty"), ImmutableList.of(Pair.of(
                        StructurePoolElement.method_30426(RepurposedStructures.MODID+":temples/temple_nether_wasteland", randomizationList4), 1)),
                        StructurePool.Projection.RIGID));


    }
}