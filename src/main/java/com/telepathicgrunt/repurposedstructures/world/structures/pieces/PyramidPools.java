package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.StructureProcessorListAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.Identifier;


public class PyramidPools {
    public static void initPools(){
        //Badlands Pyramid
        StructurePools.register(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"temples/pyramid_badlands"), new Identifier("empty"), ImmutableList.of(Pair.of(
                        StructurePoolElement.method_30434(RepurposedStructures.MODID+":temples/pyramid_badlands_body"), 1)),
                        StructurePool.Projection.RIGID));


        StructurePools.register(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"temples/pyramid_badlands_pit"), new Identifier("empty"), ImmutableList.of(Pair.of(
                        StructurePoolElement.method_30434(RepurposedStructures.MODID+":temples/pyramid_badlands_pit"), 1)),
                        StructurePool.Projection.RIGID));


        //Nether Pyramid
        StructureProcessorList randomizationList = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":temples/pyramid_nether_randomizer",
                ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.04F),
                                AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()),
                        new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WEEPING_VINES, 0.3F),
                                AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState())))));

        StructurePools.register(
                new StructurePool(new Identifier(RepurposedStructures.MODID,"temples/pyramid_nether"), new Identifier("empty"), ImmutableList.of(Pair.of(
                        StructurePoolElement.method_30435(RepurposedStructures.MODID+":temples/pyramid_nether", randomizationList), 1)),
                        StructurePool.Projection.RIGID));
    }
}