package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.StructureProcessorListAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.template.*;


public class VillageBirchPools
{
    public static void init() {
		StructureProcessorList zombiefy = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/birch/zombify",
		ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.8F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()),
		       new RuleEntry(new TagMatchRuleTest(BlockTags.DOORS), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new BlockMatchRuleTest(Blocks.TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new BlockMatchRuleTest(Blocks.WALL_TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.MOSSY_COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHITE_TERRACOTTA, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BIRCH_LOG, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BIRCH_PLANKS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BIRCH_STAIRS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.STRIPPED_BIRCH_LOG, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new RuleEntry(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true)),
		       new RuleEntry(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState())))));

		StructureProcessorList mossify = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/birch/mossify",
				ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState())))));

		StructureProcessorList path_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/birch/path_randomizer",
			ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new RuleEntry(new BlockMatchRuleTest(Blocks.GRASS_PATH), new BlockMatchRuleTest(Blocks.WATER), Blocks.BIRCH_PLANKS.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState()),
		       new RuleEntry(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
		       new RuleEntry(new BlockMatchRuleTest(Blocks.DIRT), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
		new RuleEntry(new BlockMatchRuleTest(Blocks.DIRT), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState())))));

		StructureProcessorList crop_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/birch/crop_randomizer",
			ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState())))));

		JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/birch/town_centers"), new ResourceLocation("empty"),
	       ImmutableList.of(new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/town_centers/fountain_01", ProcessorLists.MOSSIFY_20_PERCENT), 50),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/town_centers/meeting_point_1", ProcessorLists.MOSSIFY_20_PERCENT), 50),
        		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/birch/town_centers/meeting_point_2"), 50),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/town_centers/meeting_point_3", ProcessorLists.MOSSIFY_70_PERCENT), 50),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/town_centers/fountain_01", zombiefy), 1),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/town_centers/meeting_point_1", zombiefy), 1),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/town_centers/meeting_point_2", zombiefy), 1),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/town_centers/meeting_point_3", zombiefy), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));

		JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/birch/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/birch/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/corner_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/corner_02", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/corner_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/straight_01", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/straight_02", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/straight_03", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/straight_04", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/straight_05", path_randomizer), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/straight_06", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/streets/turn_01", path_randomizer), 3)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/birch/zombie/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/birch/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/corner_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/corner_02", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/corner_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_01", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_02", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_03", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_04", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_05", path_randomizer), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_06", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/turn_01", path_randomizer), 3)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/birch/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/birch/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_2", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_3", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_4", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_5", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_6", mossify), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_7", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_8", mossify), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/medium_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/medium_house_2", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/big_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/butcher_shop_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/butcher_shop_2", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/tool_smith_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/fletcher_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/birch/houses/shepherds_house_1"), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/armorer_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/fisher_cottage_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/tannery_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/cartographer_1", mossify), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/library_1", mossify), 5),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/library_2", mossify), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/masons_house_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/weaponsmith_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/temple_3", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/temple_4", mossify), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/stable_1", mossify), 2),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/birch/houses/stable_2"), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/large_farm_1", crop_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_farm_1", crop_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/birch/houses/animal_pen_1"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/birch/houses/animal_pen_2"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/birch/houses/animal_pen_3"), 5),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/birch/houses/accessory_1"), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/meeting_point_4", ProcessorLists.MOSSIFY_70_PERCENT), 3),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/birch/houses/meeting_point_5"), 1),
		       Pair.of(JigsawPiece.method_30438(), 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/birch/zombie/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/birch/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_3", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_4", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_5", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_6", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_7", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_8", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/medium_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/medium_house_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/big_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/butcher_shop_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/butcher_shop_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/tool_smith_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/fletcher_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/shepherds_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/armorer_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/fisher_cottage_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/tannery_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/cartographer_1", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/library_1", zombiefy), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/library_2", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/masons_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/weaponsmith_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/temple_3", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/temple_4", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/stable_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/stable_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/large_farm_1", zombiefy), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_farm_1", zombiefy), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/animal_pen_1", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/houses/animal_pen_2", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/animal_pen_3", zombiefy), 5),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/meeting_point_4", zombiefy), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/meeting_point_5", zombiefy), 1),
		       Pair.of(JigsawPiece.method_30438(), 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/birch/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/terminators/terminator_04", path_randomizer), 1)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));

       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/birch/zombie/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/zombie/terminators/terminator_04", path_randomizer), 1)),
	       JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/birch/trees"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30421(Features.TREES_BIRCH), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));

       ConfiguredFeature<?,?> lily_of_the_valley_feature = Feature.field_26361.configure((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_OF_THE_VALLEY.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(64).build());
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/birch/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/birch/decor/lamp_1"), 2),
		       new Pair<>(JigsawPiece.method_30421(Features.TREES_BIRCH), 1),
		       new Pair<>(JigsawPiece.method_30421(lily_of_the_valley_feature), 1),
		       new Pair<>(JigsawPiece.method_30421(Features.PILE_HAY), 1),
		       Pair.of(JigsawPiece.method_30438(), 2)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/birch/zombie/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/birch/decor/lamp_1", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30421(Features.TREES_BIRCH), 1),
		       new Pair<>(JigsawPiece.method_30421(lily_of_the_valley_feature), 1),
		       new Pair<>(JigsawPiece.method_30421(Features.PILE_HAY), 1),
		       Pair.of(JigsawPiece.method_30438(), 2)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/birch/villagers"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/birch/villagers/nitwit"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/birch/villagers/baby"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/birch/villagers/unemployed"), 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/birch/zombie/villagers"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/birch/zombie/villagers/nitwit"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/birch/zombie/villagers/unemployed"), 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
    }
}