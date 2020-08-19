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


public class VillageSwampPools
{
    public static void init() {
		StructureProcessorList zombiefy = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/swamp/zombiefy",
			ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.8F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()),
		       new RuleEntry(new TagMatchRuleTest(BlockTags.DOORS), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new BlockMatchRuleTest(Blocks.TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new BlockMatchRuleTest(Blocks.WALL_TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.MOSSY_COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHITE_TERRACOTTA, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.OAK_LOG, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.OAK_PLANKS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.OAK_STAIRS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COARSE_DIRT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CLAY, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.STRIPPED_OAK_LOG, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true)),
		       new RuleEntry(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.6F), AlwaysTrueRuleTest.INSTANCE, Blocks.PUMPKIN_STEM.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_MUSHROOM.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.RED_MUSHROOM.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.4F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 1.0F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState())))));

		StructureProcessorList mossify = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/swamp/mossify",
			ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BROWN_TERRACOTTA, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRAY_TERRACOTTA.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRAY_TERRACOTTA, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_TERRACOTTA.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRAY_TERRACOTTA, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_CONCRETE.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BROWN_TERRACOTTA, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_CONCRETE.getDefaultState())))));

		StructureProcessorList path_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/swamp/path_randomizer",
			ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_TERRACOTTA.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COARSE_DIRT.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRAY_TERRACOTTA.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.55F), new BlockMatchRuleTest(Blocks.WATER), Blocks.GRAY_TERRACOTTA.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.85F), new BlockMatchRuleTest(Blocks.WATER), Blocks.BROWN_TERRACOTTA.getDefaultState()),
		       new RuleEntry(new BlockMatchRuleTest(Blocks.GRASS_PATH), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_BLOCK, 0.5F), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_BLOCK, 0.1F), new BlockMatchRuleTest(Blocks.WATER), Blocks.BROWN_TERRACOTTA.getDefaultState()),
		       new RuleEntry(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), new BlockMatchRuleTest(Blocks.WATER), Blocks.GRAY_TERRACOTTA.getDefaultState()),
		       new RuleEntry(new BlockMatchRuleTest(Blocks.DIRT), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.95F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState()),
		       new RuleEntry(new BlockMatchRuleTest(Blocks.DIRT), AlwaysTrueRuleTest.INSTANCE, Blocks.COARSE_DIRT.getDefaultState())))));

		StructureProcessorList crop_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/swamp/crop_randomizer", 
			ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.PUMPKIN_STEM.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_MUSHROOM.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.RED_MUSHROOM.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.8F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 1.0F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState())))));

       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/swamp/town_centers"), new ResourceLocation("empty"),
	       ImmutableList.of(new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/town_centers/meeting_point_1", path_randomizer), 50),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/town_centers/meeting_point_2", path_randomizer), 50),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/town_centers/meeting_point_3", path_randomizer), 50),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/town_centers/meeting_point_1", path_randomizer), 1),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/town_centers/meeting_point_2", path_randomizer), 1),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/town_centers/meeting_point_3", path_randomizer), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));

       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/swamp/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/swamp/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/corner_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/corner_02", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/corner_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/straight_01", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/straight_02", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/straight_03", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/straight_04", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/straight_06", path_randomizer), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/straight_08", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/square_01", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/turn_01", path_randomizer), 3)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/swamp/zombie/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/swamp/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/corner_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/corner_02", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/corner_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/straight_01", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/straight_02", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/straight_03", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/straight_04", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/straight_06", path_randomizer), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/straight_08", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/streets/square_01", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/streets/turn_01", path_randomizer), 3)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/swamp/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/swamp/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/swamp/houses/animal_pen_1"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/swamp/houses/animal_pen_2"), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/armorer_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/armorer_house_2", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/butcher_shop_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/butcher_shop_2", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/cartographer_house_1", mossify), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/farm_1", crop_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/farm_2", crop_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/fisher_cottage", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/fletcher_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/library_1", mossify), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/masons_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/masons_house_2", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/medium_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/medium_house_2", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/medium_house_3", mossify), 2),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/swamp/houses/shepherds_house_1"), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/small_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/small_house_2", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/small_house_3", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/small_house_4", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/small_house_5", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/small_house_6", mossify), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/small_house_7", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/small_house_8", mossify), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/tannery_1", mossify), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/temple_1", mossify), 5),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/tool_smith_1", mossify), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/weapon_smith_1", mossify), 1),
		       Pair.of(JigsawPiece.method_30438(), 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/swamp/zombie/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/swamp/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/houses/small_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/houses/small_house_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/houses/small_house_3", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/houses/small_house_4", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/houses/small_house_5", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/houses/small_house_6", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/houses/small_house_7", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/houses/small_house_8", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/houses/medium_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/houses/medium_house_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/zombie/houses/medium_house_3", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/butchers_shop_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/butchers_shop_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/tool_smith_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/fletcher_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/shepherds_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/armorer_house_1", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/armorer_house_2", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/fisher_cottage", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/tannery_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/cartographer_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/library_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/masons_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/masons_house_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/weapon_smith_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/temple_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/farm_1", zombiefy), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/farm_2", zombiefy), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/animal_pen_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/houses/animal_pen_2", zombiefy), 2),
		       Pair.of(JigsawPiece.method_30438(), 6)), JigsawPattern.PlacementBehaviour.RIGID));
       
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/swamp/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/plains/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/plains/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/plains/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/plains/terminators/terminator_04", path_randomizer), 1)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));

       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/swamp/zombie/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/plains/zombie/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/plains/zombie/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/plains/zombie/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/plains/zombie/terminators/terminator_04", path_randomizer), 1)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/swamp/trees"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30421(Features.SWAMP_TREE), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/swamp/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/swamp/decor/lamp_post_01"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/swamp/decor/lamp_post_02"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/swamp/decor/lamp_post_03"), 1),
				   new Pair<>(JigsawPiece.method_30421(Features.SWAMP_TREE), 1),
				   new Pair<>(JigsawPiece.method_30421(Features.FLOWER_SWAMP), 1),
				   new Pair<>(JigsawPiece.method_30421(Features.PILE_PUMPKIN), 1),
		       Pair.of(JigsawPiece.method_30438(), 2)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/swamp/zombie/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/swamp/decor/lamp_post_01", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/swamp/decor/lamp_post_02"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/swamp/decor/lamp_post_03"), 1),
		       new Pair<>(JigsawPiece.method_30421(Features.SWAMP_TREE), 2),
		       new Pair<>(JigsawPiece.method_30421(Features.FLOWER_SWAMP), 2),
		       new Pair<>(JigsawPiece.method_30421(Features.PILE_PUMPKIN), 2),
		       Pair.of(JigsawPiece.method_30438(), 2)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/swamp/villagers"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/swamp/villagers/nitwit"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/swamp/villagers/baby"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/swamp/villagers/unemployed"), 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/swamp/zombie/villagers"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/swamp/zombie/villagers/nitwit"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/swamp/zombie/villagers/unemployed"), 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
    }
}