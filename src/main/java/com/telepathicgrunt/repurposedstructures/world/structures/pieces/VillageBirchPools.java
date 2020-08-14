package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Lifecycle;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.StructureProcessorListAccessor;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.structure.pool.*;
import net.minecraft.structure.processor.*;
import net.minecraft.structure.rule.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

@SuppressWarnings("deprecation")
public class VillageBirchPools
{
    public static void init(BuiltinRegistries poolRegistry) {
		StructureProcessorList zombiefy = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/birch/zombify",
		ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.8F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()),
		       new StructureProcessorRule(new TagMatchRuleTest(BlockTags.DOORS), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.WALL_TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MOSSY_COBBLESTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHITE_TERRACOTTA, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BIRCH_LOG, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BIRCH_PLANKS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BIRCH_STAIRS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STRIPPED_BIRCH_LOG, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.COBWEB.getDefaultState()),
		       new StructureProcessorRule(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true)),
		       new StructureProcessorRule(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState())))));

		StructureProcessorList mossify = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/birch/mossify",
				ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState())))));

		StructureProcessorList path_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/birch/path_randomizer",
			ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.GRASS_PATH), new BlockMatchRuleTest(Blocks.WATER), Blocks.BIRCH_PLANKS.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState()),
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.DIRT), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
		new StructureProcessorRule(new BlockMatchRuleTest(Blocks.DIRT), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState())))));

		StructureProcessorList crop_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/birch/crop_randomizer",
			ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState())))));

		poolRegistry.add(RegistryKey.of(poolRegistry.getKey(), new Identifier(RepurposedStructures.MODID+":village/birch/town_centers")), StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/birch/town_centers"), new Identifier("empty"),
	       ImmutableList.of(new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/town_centers/fountain_01", StructureProcessorLists.MOSSIFY_20_PERCENT), 50),
        		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/town_centers/meeting_point_1", StructureProcessorLists.MOSSIFY_20_PERCENT), 50),
        		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/birch/town_centers/meeting_point_2"), 50),
        		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/town_centers/meeting_point_3", StructureProcessorLists.MOSSIFY_70_PERCENT), 50),
        		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/town_centers/fountain_01", zombiefy), 1),
        		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/town_centers/meeting_point_1", zombiefy), 1),
        		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/town_centers/meeting_point_2", zombiefy), 1),
        		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/town_centers/meeting_point_3", zombiefy), 1)),
	       StructurePool.Projection.RIGID)), Lifecycle.experimental());;

		poolRegistry.add(RegistryKey.of(poolRegistry.getKey(), new Identifier(RepurposedStructures.MODID+":village/birch/streets")), StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/birch/streets"), new Identifier(RepurposedStructures.MODID+":village/birch/terminators"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/corner_01", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/corner_02", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/corner_03", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/straight_01", path_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/straight_02", path_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/straight_03", path_randomizer), 7),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/straight_04", path_randomizer), 7),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/straight_05", path_randomizer), 3),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/straight_06", path_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/streets/turn_01", path_randomizer), 3)),
	       StructurePool.Projection.TERRAIN_MATCHING)), Lifecycle.experimental());;
       
       poolRegistry.add(RegistryKey.of(poolRegistry.getKey(), new Identifier(RepurposedStructures.MODID+":village/birch/zombie/streets")), StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/birch/zombie/streets"), new Identifier(RepurposedStructures.MODID+":village/birch/terminators"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/corner_01", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/corner_02", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/corner_03", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_01", path_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_02", path_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_03", path_randomizer), 7),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_04", path_randomizer), 7),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_05", path_randomizer), 3),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_06", path_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/streets/turn_01", path_randomizer), 3)),
	       StructurePool.Projection.TERRAIN_MATCHING)), Lifecycle.experimental());;
       
       poolRegistry.add(RegistryKey.of(poolRegistry.getKey(), new Identifier(RepurposedStructures.MODID+":village/birch/houses")), StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/birch/houses"), new Identifier(RepurposedStructures.MODID+":village/birch/terminators"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_2", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_3", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_4", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_5", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_6", mossify), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_7", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_house_8", mossify), 3),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/medium_house_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/medium_house_2", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/big_house_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/butcher_shop_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/butcher_shop_2", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/tool_smith_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/fletcher_house_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/birch/houses/shepherds_house_1"), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/armorer_house_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/fisher_cottage_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/tannery_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/cartographer_1", mossify), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/library_1", mossify), 5),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/library_2", mossify), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/masons_house_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/weaponsmith_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/temple_3", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/temple_4", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/stable_1", mossify), 2),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/birch/houses/stable_2"), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/large_farm_1", crop_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_farm_1", crop_randomizer), 4),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/birch/houses/animal_pen_1"), 1),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/birch/houses/animal_pen_2"), 1),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/birch/houses/animal_pen_3"), 5),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/birch/houses/accessory_1"), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/meeting_point_4", StructureProcessorLists.MOSSIFY_70_PERCENT), 3),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/birch/houses/meeting_point_5"), 1),
		       Pair.of(StructurePoolElement.method_30438(), 10)),
	       StructurePool.Projection.RIGID)), Lifecycle.experimental());;
       
       poolRegistry.add(RegistryKey.of(poolRegistry.getKey(), new Identifier(RepurposedStructures.MODID+":village/birch/zombie/houses")), StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/birch/zombie/houses"), new Identifier(RepurposedStructures.MODID+":village/birch/terminators"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_2", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_3", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_4", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_5", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_6", zombiefy), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_7", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_8", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/medium_house_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/medium_house_2", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/big_house_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/butcher_shop_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/butcher_shop_2", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/tool_smith_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/fletcher_house_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/shepherds_house_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/armorer_house_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/fisher_cottage_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/tannery_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/cartographer_1", zombiefy), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/library_1", zombiefy), 3),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/library_2", zombiefy), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/masons_house_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/weaponsmith_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/temple_3", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/temple_4", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/stable_1", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/stable_2", zombiefy), 2),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/large_farm_1", zombiefy), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/small_farm_1", zombiefy), 4),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/animal_pen_1", zombiefy), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/houses/animal_pen_2", zombiefy), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/animal_pen_3", zombiefy), 5),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/meeting_point_4", zombiefy), 3),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/houses/meeting_point_5", zombiefy), 1),
		       Pair.of(StructurePoolElement.method_30438(), 10)),
	       StructurePool.Projection.RIGID)), Lifecycle.experimental());;
      
       poolRegistry.add(RegistryKey.of(poolRegistry.getKey(), new Identifier(RepurposedStructures.MODID+":village/birch/terminators")), StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/birch/terminators"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/terminators/terminator_04", path_randomizer), 1)),
	       StructurePool.Projection.TERRAIN_MATCHING)), Lifecycle.experimental());;

       poolRegistry.add(RegistryKey.of(poolRegistry.getKey(), new Identifier(RepurposedStructures.MODID+":village/birch/zombie/terminators")), StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/birch/zombie/terminators"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/zombie/terminators/terminator_04", path_randomizer), 1)),
	       StructurePool.Projection.TERRAIN_MATCHING)), Lifecycle.experimental());;
       
       poolRegistry.add(RegistryKey.of(poolRegistry.getKey(), new Identifier(RepurposedStructures.MODID+":village/birch/trees")), StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/birch/trees"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.TREES_BIRCH), 1)),
	       StructurePool.Projection.RIGID)), Lifecycle.experimental());;

       ConfiguredFeature lily_of_the_valley_feature = Feature.field_26361.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_OF_THE_VALLEY.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(64).build());
       poolRegistry.add(RegistryKey.of(poolRegistry.getKey(), new Identifier(RepurposedStructures.MODID+":village/birch/decor")), StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/birch/decor"), new Identifier("empty"),
	       ImmutableList.of(new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/birch/decor/lamp_1"), 2),
		       new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.TREES_BIRCH), 1),
		       new Pair<>(StructurePoolElement.method_30421(lily_of_the_valley_feature), 1),
		       new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.PILE_HAY), 1),
		       Pair.of(StructurePoolElement.method_30438(), 2)),
	       StructurePool.Projection.RIGID)), Lifecycle.experimental());;
      
       poolRegistry.add(RegistryKey.of(poolRegistry.getKey(), new Identifier(RepurposedStructures.MODID+":village/birch/zombie/decor")), StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/birch/zombie/decor"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30426(RepurposedStructures.MODID+":village/birch/decor/lamp_1", zombiefy), 1),
		       new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.TREES_BIRCH), 1),
		       new Pair<>(StructurePoolElement.method_30421(lily_of_the_valley_feature), 1),
		       new Pair<>(StructurePoolElement.method_30421(ConfiguredFeatures.PILE_HAY), 1),
		       Pair.of(StructurePoolElement.method_30438(), 2)),
	       StructurePool.Projection.RIGID)), Lifecycle.experimental());
      
       poolRegistry.add(RegistryKey.of(poolRegistry.getKey(), new Identifier(RepurposedStructures.MODID+":village/birch/villagers")), StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/birch/villagers"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/birch/villagers/nitwit"), 1),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/birch/villagers/baby"), 1),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/birch/villagers/unemployed"), 10)),
	       StructurePool.Projection.RIGID)), Lifecycle.experimental());;
      
       poolRegistry.add(RegistryKey.of(poolRegistry.getKey(), new Identifier(RepurposedStructures.MODID+":village/birch/zombie/villagers")), StructurePools.register(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/birch/zombie/villagers"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/birch/zombie/villagers/nitwit"), 1),
		       new Pair<>(StructurePoolElement.method_30425(RepurposedStructures.MODID+":village/birch/zombie/villagers/unemployed"), 10)),
	       StructurePool.Projection.RIGID)), Lifecycle.experimental());;
    }
}