package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.StructureProcessorListAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.structure.pool.*;
import net.minecraft.structure.processor.*;
import net.minecraft.structure.rule.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;

@SuppressWarnings("deprecation")
public class VillageDarkForestPools
{
    public static void init() {
    }

    static {

		StructureProcessorList zombiefy = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/dark_forest/zombify",
			ImmutableList.of(new RuleStructureProcessor( ImmutableList.of(
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.8F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()),
		       new StructureProcessorRule(new TagMatchRuleTest(BlockTags.DOORS), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.WALL_TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MOSSY_COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHITE_TERRACOTTA, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DARK_OAK_LOG, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DARK_OAK_PLANKS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DARK_OAK_STAIRS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STRIPPED_DARK_OAK_LOG, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true)),
		       new StructureProcessorRule(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState())))));

		StructureProcessorList mossify = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/dark_forest/mossify",
				ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState())))));

		StructureProcessorList path_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/dark_forest/path_randomizer",
       		ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.GRASS_PATH), new BlockMatchRuleTest(Blocks.WATER), Blocks.DARK_OAK_PLANKS.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState()),
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.DIRT), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
				   new StructureProcessorRule(new BlockMatchRuleTest(Blocks.DIRT), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState())))));

		StructureProcessorList crop_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/dark_forest/crop_randomizer", 
			ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState())))));
      
       StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/dark_forest/town_centers"), new Identifier("empty"),
	       ImmutableList.of(new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/town_centers/fountain_01", StructureProcessorLists.MOSSIFY_20_PERCENT), 50),
        		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/town_centers/meeting_point_1", StructureProcessorLists.MOSSIFY_20_PERCENT), 50),
        		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/dark_forest/town_centers/meeting_point_2"), 50),
        		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/town_centers/meeting_point_3", StructureProcessorLists.MOSSIFY_70_PERCENT), 50),
        		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/town_centers/fountain_01", zombiefy), 1),
        		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/town_centers/meeting_point_1", zombiefy), 1),
        		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/town_centers/meeting_point_2", zombiefy), 1),
        		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/town_centers/meeting_point_3", zombiefy), 1)),
	       StructurePool.Projection.RIGID));
       
       StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/dark_forest/streets"), new Identifier(RepurposedStructures.MODID+":village/dark_forest/terminators"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/corner_01", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/corner_02", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/corner_03", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/straight_01", path_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/straight_02", path_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/straight_03", path_randomizer), 7),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/straight_04", path_randomizer), 7),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/straight_05", path_randomizer), 3),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/straight_06", path_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/streets/turn_01", path_randomizer), 3)),
	       StructurePool.Projection.TERRAIN_MATCHING));
       
       StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/dark_forest/zombie/streets"), new Identifier(RepurposedStructures.MODID+":village/dark_forest/terminators"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/corner_01", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/corner_02", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/corner_03", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/straight_01", path_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/straight_02", path_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/straight_03", path_randomizer), 7),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/straight_04", path_randomizer), 7),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/straight_05", path_randomizer), 3),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/straight_06", path_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/streets/turn_01", path_randomizer), 3)),
	       StructurePool.Projection.TERRAIN_MATCHING));
       
       StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/dark_forest/houses"), new Identifier(RepurposedStructures.MODID+":village/dark_forest/terminators"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/small_house_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/small_house_2", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/small_house_3", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/small_house_4", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/small_house_5", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/small_house_6", mossify), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/small_house_7", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/small_house_8", mossify), 3),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/medium_house_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/medium_house_2", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/big_house_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/butcher_shop_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/butcher_shop_2", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/tool_smith_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/fletcher_house_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/dark_forest/houses/shepherds_house_1"), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/armorer_house_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/fisher_cottage_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/tannery_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/cartographer_1", mossify), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/library_1", mossify), 5),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/library_2", mossify), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/masons_house_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/weaponsmith_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/temple_3", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/temple_4", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/stable_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/dark_forest/houses/stable_2"), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/large_farm_1", crop_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/small_farm_1", crop_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/dark_forest/houses/animal_pen_1"), 1),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/dark_forest/houses/animal_pen_2"), 1),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/dark_forest/houses/animal_pen_3"), 5),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/dark_forest/houses/accessory_1"), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/meeting_point_4", StructureProcessorLists.MOSSIFY_70_PERCENT), 3),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/dark_forest/houses/meeting_point_5"), 1),
		       Pair.of(StructurePoolElement.method_30438(), 10)),
	       StructurePool.Projection.RIGID));
       
       StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/dark_forest/zombie/houses"), new Identifier(RepurposedStructures.MODID+":village/dark_forest/terminators"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/small_house_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/small_house_2", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/small_house_3", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/small_house_4", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/small_house_5", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/small_house_6", zombiefy), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/small_house_7", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/small_house_8", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/medium_house_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/medium_house_2", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/big_house_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/butcher_shop_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/butcher_shop_2", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/tool_smith_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/fletcher_house_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/shepherds_house_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/armorer_house_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/fisher_cottage_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/tannery_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/cartographer_1", zombiefy), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/library_1", zombiefy), 3),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/library_2", zombiefy), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/masons_house_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/weaponsmith_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/temple_3", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/temple_4", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/stable_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/stable_2", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/large_farm_1", zombiefy), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/small_farm_1", zombiefy), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/animal_pen_1", zombiefy), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/houses/animal_pen_2", zombiefy), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/animal_pen_3", zombiefy), 5),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/meeting_point_4", zombiefy), 3),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/houses/meeting_point_5", zombiefy), 1),
		       Pair.of(StructurePoolElement.method_30438(), 10)),
	       StructurePool.Projection.RIGID));
      
       StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/dark_forest/terminators"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/terminators/terminator_04", path_randomizer), 1)),
	       StructurePool.Projection.TERRAIN_MATCHING));

       StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/dark_forest/zombie/terminators"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/zombie/terminators/terminator_04", path_randomizer), 1)),
	       StructurePool.Projection.TERRAIN_MATCHING));
       
       StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/dark_forest/trees"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.DARK_OAK), 1)),
	       StructurePool.Projection.RIGID));
       
       StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/dark_forest/decor"), new Identifier("empty"),
	       ImmutableList.of(new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/dark_forest/decor/lamp_1"), 5),
		       new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.DARK_OAK), 1),
		       new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.PATCH_RED_MUSHROOM), 1),
		       new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.PATCH_BROWN_MUSHROOM), 1),
		       new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.PILE_PUMPKIN), 1),
		       Pair.of(StructurePoolElement.method_30438(), 2)),
	       StructurePool.Projection.RIGID));
      
       StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/dark_forest/zombie/decor"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/dark_forest/decor/lamp_1", zombiefy), 1),
				   new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.DARK_OAK), 1),
				   new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.PATCH_RED_MUSHROOM), 1),
				   new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.PATCH_BROWN_MUSHROOM), 1),
		       new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.PILE_PUMPKIN), 1),
		       Pair.of(StructurePoolElement.method_30438(), 2)),
	       StructurePool.Projection.RIGID));
      
       StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/dark_forest/villagers"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/dark_forest/villagers/nitwit"), 1),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/dark_forest/villagers/baby"), 1),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/dark_forest/villagers/unemployed"), 10)),
	       StructurePool.Projection.RIGID));
      
       StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/dark_forest/zombie/villagers"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/dark_forest/zombie/villagers/nitwit"), 1),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/dark_forest/zombie/villagers/unemployed"), 10)),
	       StructurePool.Projection.RIGID));
    }
}