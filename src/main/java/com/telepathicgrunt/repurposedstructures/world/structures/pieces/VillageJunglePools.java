package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.StructureProcessorListAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.template.*;


public class VillageJunglePools
{
    public static void init() {
		StructureProcessorList zombiefy = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/jungle/zombiefy",
			ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.8F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()),
		       new RuleEntry(new TagMatchRuleTest(BlockTags.DOORS), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new BlockMatchRuleTest(Blocks.TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new BlockMatchRuleTest(Blocks.WALL_TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.MOSSY_COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHITE_TERRACOTTA, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.JUNGLE_LOG, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.JUNGLE_PLANKS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.JUNGLE_STAIRS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.STRIPPED_JUNGLE_LOG, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true)),
		       new RuleEntry(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState())))));

		StructureProcessorList mossify = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/jungle/mossify",
       		ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.STONE_BRICKS, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_STONE_BRICKS.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.STONE_BRICKS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_STONE_BRICKS.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.STONE_BRICK_STAIRS, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE_STAIRS.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.STONE_BRICK_SLAB, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_STONE_BRICK_SLAB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.STONE_BRICK_WALL, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_STONE_BRICK_WALL.getDefaultState())))));

		StructureProcessorList path_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/jungle/path_randomizer",
       		ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new RuleEntry(new BlockMatchRuleTest(Blocks.GRASS_PATH), new BlockMatchRuleTest(Blocks.WATER), Blocks.JUNGLE_PLANKS.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState()),
		       new RuleEntry(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
		       new RuleEntry(new BlockMatchRuleTest(Blocks.DIRT), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
				   new RuleEntry(new BlockMatchRuleTest(Blocks.DIRT), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState())))));

		StructureProcessorList crop_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/jungle/crop_randomizer",
			ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.MELON_STEM.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.getDefaultState())))));

       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/jungle/town_centers"), new ResourceLocation("empty"),
	       ImmutableList.of(new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/town_centers/meeting_point_1", mossify), 50),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/town_centers/meeting_point_2", mossify), 50),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/town_centers/meeting_point_3", mossify), 50),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/town_centers/meeting_point_4", mossify), 50),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/town_centers/meeting_point_1", zombiefy), 1),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/town_centers/meeting_point_2", zombiefy), 1),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/town_centers/meeting_point_3", zombiefy), 1),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/town_centers/meeting_point_4", zombiefy), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));

       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/jungle/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/jungle/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/corner_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/corner_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/straight_02", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/straight_04", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/straight_05", path_randomizer), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/straight_06", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/straight_08", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/straight_09", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/straight_10", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/straight_11", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/split_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/split_02", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/crossroad_07", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/streets/turn_01", path_randomizer), 3)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/jungle/zombie/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/jungle/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/corner_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/corner_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/straight_02", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/straight_04", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/straight_05", path_randomizer), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/straight_06", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/straight_08", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/straight_09", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/straight_10", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/straight_11", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/split_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/split_02", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/crossroad_07", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/streets/turn_01", path_randomizer), 3)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/jungle/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/jungle/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/animal_pen_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/animal_pen_2", mossify), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/animal_pen_3", mossify), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/armorer_1", mossify), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/butcher_shop_1", mossify), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/butcher_shop_2", mossify), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/cartographer_1", mossify), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/fisher_cottage_1", mossify), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/fletcher_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/large_farm_1", crop_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/large_farm_2", crop_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/library_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/masons_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/medium_house_1", mossify), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/medium_house_2", mossify), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/shepherds_1", mossify), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/small_farm", crop_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/small_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/small_house_2", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/small_house_3", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/small_house_4", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/small_house_5", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/small_house_6", mossify), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/small_house_7", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/small_house_8", mossify), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/tannery_1", mossify), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/temple_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/temple_2", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/tool_smith_1", mossify), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/weaponsmith_1", mossify), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/houses/weaponsmith_2", mossify), 3),
		       Pair.of(JigsawPiece.method_30438(), 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/jungle/zombie/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/jungle/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426("village/jungle/zombie/houses/small_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/zombie/houses/small_house_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/zombie/houses/small_house_3", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/zombie/houses/small_house_4", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/zombie/houses/small_house_5", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/zombie/houses/small_house_6", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/zombie/houses/small_house_7", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/zombie/houses/small_house_8", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/zombie/houses/medium_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/zombie/houses/medium_house_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/butchers_shop_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/butchers_shop_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/tool_smith_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/fletcher_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/shepherd_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/armorer_1", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/fisher_cottage_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/tannery_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/cartographer_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/library_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/mason_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/weaponsmith_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/weaponsmith_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/temple_1", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/temple_2", zombiefy), 3),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/large_farm_1", zombiefy), 4),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/zombie/houses/large_farm_2", zombiefy), 4),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/small_farm", zombiefy), 4),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/houses/animal_pen_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/zombie/houses/animal_pen_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426("village/jungle/zombie/houses/animal_pen_3", zombiefy), 2),
		       Pair.of(JigsawPiece.method_30438(), 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/jungle/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/terminators/terminator_05", path_randomizer), 1)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));

       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/jungle/zombie/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/zombie/terminators/terminator_05", path_randomizer), 1)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/jungle/trees"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30421(Features.MEGA_JUNGLE_TREE), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/jungle/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/jungle/decor/lamp_1"), 5),
		       new Pair<>(JigsawPiece.method_30421(Features.JUNGLE_TREE), 1),
		       new Pair<>(JigsawPiece.method_30421(Features.FLOWER_FOREST), 1),
		       new Pair<>(JigsawPiece.method_30421(Features.PILE_MELON), 1),
		       Pair.of(JigsawPiece.method_30438(), 2)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/jungle/zombie/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/jungle/decor/lamp_1", zombiefy), 1),
			   new Pair<>(JigsawPiece.method_30421(Features.JUNGLE_TREE), 1),
			   new Pair<>(JigsawPiece.method_30421(Features.FLOWER_FOREST), 1),
			   new Pair<>(JigsawPiece.method_30421(Features.PILE_MELON), 1),
		       Pair.of(JigsawPiece.method_30438(), 2)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/jungle/villagers"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/jungle/villagers/nitwit"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/jungle/villagers/baby"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/jungle/villagers/unemployed"), 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/jungle/zombie/villagers"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/jungle/zombie/villagers/nitwit"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/jungle/zombie/villagers/unemployed"), 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
    }
}