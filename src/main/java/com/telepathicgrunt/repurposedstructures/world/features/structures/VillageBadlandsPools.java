package com.telepathicgrunt.repurposedstructures.world.features.structures;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.RSFeatures;

import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.jigsaw.EmptyJigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.FeatureJigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.AlwaysTrueRuleTest;
import net.minecraft.world.gen.feature.template.RandomBlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleEntry;
import net.minecraft.world.gen.feature.template.RuleStructureProcessor;
import net.minecraft.world.gen.feature.template.StructureProcessor;

@SuppressWarnings("deprecation")
public class VillageBadlandsPools
{
    public static void init() {
	List<Structure<?>> tempList = new ArrayList<Structure<?>>(Feature.ILLAGER_STRUCTURES);
	tempList.add(RSFeatures.BADLANDS_VILLAGE);
	Feature.ILLAGER_STRUCTURES = ImmutableList.copyOf(tempList);
    }

    static {
	ImmutableList<StructureProcessor> crop_replacement = ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState()),
		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MELON_STEM.getDefaultState()),
		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.PODZOL, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.COARSE_DIRT.getDefaultState()),
		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COARSE_DIRT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.PODZOL.getDefaultState()))));

	ImmutableList<StructureProcessor> decaying = ImmutableList.of(new RuleStructureProcessor(
		ImmutableList.of(
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.ORANGE_TERRACOTTA, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.TERRACOTTA.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.TERRACOTTA, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.ORANGE_TERRACOTTA.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CHISELED_RED_SANDSTONE, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.RED_SANDSTONE.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.SMOOTH_RED_SANDSTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.RED_SANDSTONE.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.STONE_BRICKS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_STONE_BRICKS.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE_WALL, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE_WALL.getDefaultState()))));

	ImmutableList<StructureProcessor> path_randomizer = ImmutableList.of(new RuleStructureProcessor(
		ImmutableList.of(
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.ORANGE_TERRACOTTA, 0.12F), AlwaysTrueRuleTest.INSTANCE, Blocks.TERRACOTTA.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.TERRACOTTA, 0.12F), AlwaysTrueRuleTest.INSTANCE, Blocks.ORANGE_TERRACOTTA.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.ORANGE_TERRACOTTA, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.RED_SAND.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.TERRACOTTA, 0.15F), AlwaysTrueRuleTest.INSTANCE, Blocks.RED_SAND.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.RED_SANDSTONE, 0.10F), AlwaysTrueRuleTest.INSTANCE, Blocks.RED_SAND.getDefaultState()))));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID + ":village/badlands/town_centers"), new ResourceLocation("empty"), 
	       ImmutableList.of(
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/town_centers/center_1", path_randomizer), 98)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID + ":village/badlands/streets"), 
	       						 new ResourceLocation(RepurposedStructures.MODID + ":village/badlands/terminators"), 
	       	ImmutableList.of(
	       		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/corner_01", path_randomizer), 3), 
	       		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/corner_02", path_randomizer), 3), 
	       		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/corner_03", path_randomizer), 3), 
	       		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/straight_01", path_randomizer), 6), 
	       		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/straight_02", path_randomizer), 6),
	       		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/straight_03", path_randomizer), 2), 
	       		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/crossroad_01", path_randomizer), 3), 
	       		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/crossroad_02", path_randomizer), 3), 
	       		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/crossroad_03", path_randomizer), 3), 
	       		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/square_01", path_randomizer), 3), 
	       		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/streets/square_02", path_randomizer), 3)), 
	       	JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
       
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID + ":village/badlands/houses"), new ResourceLocation(RepurposedStructures.MODID + ":village/badlands/terminators"), 
        	ImmutableList.of(
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/small_house_1", decaying), 2), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/small_house_2", decaying), 2), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/small_house_3", decaying), 2), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/small_house_4", decaying), 2),
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/small_house_5", decaying), 2), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/medium_house_1", decaying), 2), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/medium_house_2", decaying), 2), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/medium_house_3", decaying), 2), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/medium_house_4", decaying), 2), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/butcher", decaying), 1), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/tool_smith", decaying), 4),
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/fletcher", decaying), 2),
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/shepherd", decaying), 1),
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/saloon", decaying), 6),
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/armorer", decaying), 3), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/fisher", decaying), 1), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/tannery", decaying), 2),
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/cartographer", decaying), 2),
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/library", decaying), 2), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/mason", decaying), 4), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/weaponsmith", decaying), 4),
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/temple_1", decaying), 4), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/temple_2", decaying), 4), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/farm_1", crop_replacement), 11),
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/farm_2", crop_replacement), 4),
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/pen_1", decaying), 2),
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/houses/meeting_point", path_randomizer), 3),
        		Pair.of(EmptyJigsawPiece.INSTANCE, 5)), 
        	JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID + ":village/badlands/terminators"), new ResourceLocation("empty"), 
        	ImmutableList.of(
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/terminators/terminator_01", path_randomizer), 1), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/terminators/terminator_02", path_randomizer), 1), 
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/terminators/terminator_03", path_randomizer), 1)),
        	JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
       
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID + ":village/badlands/decor"), new ResourceLocation("empty"), 
        	ImmutableList.of(
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/decor/lamp_1", decaying), 10),
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/decor/lamp_2", decaying), 10),
        		new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID + ":village/badlands/decor/lamp_3", decaying), 10),
        		new Pair<>(new FeatureJigsawPiece(Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.CACTUS_CONFIG)), 12),
        		new Pair<>(new FeatureJigsawPiece(Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.DEAD_BUSH_CONFIG)), 3),
        		new Pair<>(new FeatureJigsawPiece(Feature.BLOCK_PILE.withConfiguration(DefaultBiomeFeatures.HAY_PILE_CONFIG)), 1),
        		Pair.of(EmptyJigsawPiece.INSTANCE, 10)), 
        	JigsawPattern.PlacementBehaviour.RIGID));
    }
    
}