package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.StructureProcessorListAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockStateProvidingFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.template.*;


public class VillageMountainsPools
{
    public static void init() {
		StructureProcessorList zombiefy = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/mountains/zombiefy",
			ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.8F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()),
			new RuleEntry(new TagMatchRuleTest(BlockTags.DOORS), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			new RuleEntry(new BlockMatchRuleTest(Blocks.TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			new RuleEntry(new BlockMatchRuleTest(Blocks.WALL_TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.MOSSY_COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.STONE_BRICKS, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.MOSSY_STONE_BRICKS, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRACKED_STONE_BRICKS, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHITE_TERRACOTTA, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.SPRUCE_LOG, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.SPRUCE_PLANKS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.SPRUCE_STAIRS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.STRIPPED_SPRUCE_LOG, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
			new RuleEntry(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true)),
			new RuleEntry(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState())))));

		StructureProcessorList mossify = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/mountains/mossify",
			ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.STONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBBLESTONE.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.STONE_BRICKS, 0.4F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_STONE_BRICKS.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.STONE_BRICKS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_STONE_BRICKS.getDefaultState()),
			new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE_WALL, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE_WALL.getDefaultState())))));

		StructureProcessorList path_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/mountains/path_randomizer", 
				ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.1F), new BlockMatchRuleTest(Blocks.STONE), Blocks.STONE.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.02F), new BlockMatchRuleTest(Blocks.STONE), Blocks.MOSSY_COBBLESTONE.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.1F), new BlockMatchRuleTest(Blocks.STONE), Blocks.MOSSY_STONE_BRICKS.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.3F), new BlockMatchRuleTest(Blocks.STONE), Blocks.CRACKED_STONE_BRICKS.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.55F), new BlockMatchRuleTest(Blocks.STONE), Blocks.STONE_BRICKS.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.15F), new BlockMatchRuleTest(Blocks.STONE), Blocks.COBBLESTONE.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.1F), new BlockMatchRuleTest(Blocks.WATER), Blocks.MOSSY_STONE_BRICKS.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.05F), new BlockMatchRuleTest(Blocks.WATER), Blocks.MOSSY_STONE_BRICKS.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.1F), new BlockMatchRuleTest(Blocks.WATER), Blocks.CRACKED_STONE_BRICKS.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.4F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_STONE_BRICKS.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_STONE_BRICKS.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.STONE_BRICKS.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBBLESTONE.getDefaultState()),
            		new RuleEntry(new BlockMatchRuleTest(Blocks.GRASS_PATH), new BlockMatchRuleTest(Blocks.WATER), Blocks.STONE_BRICKS.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState()),
            		new RuleEntry(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBBLESTONE.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()),
            		new RuleEntry(new BlockMatchRuleTest(Blocks.DIRT), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
				new RuleEntry(new BlockMatchRuleTest(Blocks.DIRT), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState())))));

		StructureProcessorList path_randomizer_and_mossify = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/mountains/path_randomizer_and_mossify", 
				ImmutableList.copyOf(Iterables.concat(mossify.getList(), path_randomizer.getList())));

		StructureProcessorList crop_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/mountains/crop_randomizer",
				ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
            		new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.SWEET_BERRY_BUSH.getDefaultState())))));

       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/mountains/town_centers"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/town_centers/meeting_point_1", path_randomizer_and_mossify), 50),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/town_centers/meeting_point_2", path_randomizer_and_mossify), 50),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/town_centers/meeting_point_1", path_randomizer_and_mossify), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/town_centers/meeting_point_2", path_randomizer_and_mossify), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/mountains/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/mountains/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/corner_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/corner_02", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/corner_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/straight_01", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/straight_02", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/straight_03", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/straight_04", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/straight_05", path_randomizer), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/straight_06", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/streets/turn_01", path_randomizer), 3)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/mountains/zombie/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/mountains/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/corner_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/corner_02", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/corner_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/straight_01", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/straight_02", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/straight_03", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/straight_04", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/straight_05", path_randomizer), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/straight_06", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/streets/turn_01", path_randomizer), 3)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/mountains/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/mountains/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/mountains/houses/animal_pen_1"), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/armorer_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/armorer_house_2", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/butcher_shop_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/cartographer_house_1", mossify), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/fisher_cottage", mossify), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/fletcher_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/large_farm_1", crop_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/large_farm_2", crop_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/library_1", mossify), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/masons_house_1", mossify), 5),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/medium_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/medium_house_2", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/medium_house_3", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/medium_house_4", mossify), 2),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/mountains/houses/shepherds_house_1"), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/small_farm_1", crop_randomizer), 5),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/small_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/small_house_2", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/small_house_3", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/small_house_4", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/small_house_5", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/tannery_1", mossify), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/temple_1", mossify), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/tool_smith_1", mossify), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/weapon_smith_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/weapon_smith_2", mossify), 2),
		       Pair.of(JigsawPiece.method_30438(), 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/mountains/zombie/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/mountains/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/small_house_1", zombiefy), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/small_house_2", zombiefy), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/small_house_3", zombiefy), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/small_house_4", zombiefy), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/small_house_5", zombiefy), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/medium_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/medium_house_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/medium_house_3", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/medium_house_4", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/butcher_shop_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/tool_smith_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/fletcher_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/shepherds_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/armorer_house_1", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/fisher_cottage_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/tannery_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/cartographer_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/library_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/masons_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/weaponsmith_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/weaponsmith_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/temple_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/large_farm_1", zombiefy), 6),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/zombie/houses/large_farm_2", zombiefy), 6),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/small_farm_1", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/houses/animal_pen_1", zombiefy), 2),
		       Pair.of(JigsawPiece.method_30438(), 6)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/mountains/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/plains/terminators/terminator_05", path_randomizer), 1)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));

       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/mountains/zombie/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/plains/zombie/terminators/terminator_05", path_randomizer), 1)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/mountains/trees"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30421(Features.SPRUCE), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));

		BlockStateProvidingFeatureConfig COBBLESTONE_PILE_CONFIG = new BlockStateProvidingFeatureConfig(new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState()));

		JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/mountains/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/mountains/decor/lamp_post_1"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/mountains/decor/decoration_1"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/mountains/decor/decoration_2"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/mountains/decor/decoration_3"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/mountains/decor/decoration_4"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/mountains/decor/decoration_5"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/mountains/decor/decoration_6"), 1),
		       new Pair<>(JigsawPiece.method_30421(Features.SPRUCE), 1),
		       new Pair<>(JigsawPiece.method_30421(Features.FLOWER_DEFAULT), 1),
		       new Pair<>(JigsawPiece.method_30421(Feature.BLOCK_PILE.configure(COBBLESTONE_PILE_CONFIG)), 1),
		       Pair.of(JigsawPiece.method_30438(), 2)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/mountains/zombie/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/mountains/decor/lamp_post_1", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/mountains/decor/decoration_1"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/mountains/decor/decoration_2"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/mountains/decor/decoration_3"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/mountains/decor/decoration_4"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/mountains/decor/decoration_5"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/mountains/decor/decoration_6"), 1),
		       new Pair<>(JigsawPiece.method_30421(Features.SPRUCE), 2),
		       new Pair<>(JigsawPiece.method_30421(Features.FLOWER_DEFAULT), 2),
		       new Pair<>(JigsawPiece.method_30421(Feature.BLOCK_PILE.configure(COBBLESTONE_PILE_CONFIG)), 2),
		       Pair.of(JigsawPiece.method_30438(), 2)),
	       JigsawPattern.PlacementBehaviour.RIGID));

		JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/mountains/zombie/villagers"), new ResourceLocation("empty"),
				ImmutableList.of(
						new Pair<>(JigsawPiece.method_30425("village/taiga/zombie/villagers/nitwit"), 1),
						new Pair<>(JigsawPiece.method_30425("village/taiga/zombie/villagers/unemployed"), 10)),
				JigsawPattern.PlacementBehaviour.RIGID));
    }
}