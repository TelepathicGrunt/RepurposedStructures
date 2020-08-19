package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.StructureProcessorListAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.template.*;


public class PyramidPools {
    public static void initPools(){
        //Badlands Pyramid
        JigsawPatternRegistry.register(
                new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/pyramid_badlands"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                        JigsawPiece.method_30434(RepurposedStructures.MODID+":temples/pyramid_badlands_body"), 1)),
                        JigsawPattern.PlacementBehaviour.RIGID));


        JigsawPatternRegistry.register(
                new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/pyramid_badlands_pit"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                        JigsawPiece.method_30434(RepurposedStructures.MODID+":temples/pyramid_badlands_pit"), 1)),
                        JigsawPattern.PlacementBehaviour.RIGID));


        //Nether Pyramid
        StructureProcessorList randomizationList = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":temples/pyramid_nether_randomizer",
                ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
                        new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.04F),
                                AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()),
                        new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WEEPING_VINES, 0.3F),
                                AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState())))));

        JigsawPatternRegistry.register(
                new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID,"temples/pyramid_nether"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(
                        JigsawPiece.method_30435(RepurposedStructures.MODID+":temples/pyramid_nether", randomizationList), 1)),
                        JigsawPattern.PlacementBehaviour.RIGID));
    }
}