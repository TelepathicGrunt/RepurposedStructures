package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.structure.pool.*;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;

@SuppressWarnings("deprecation")
public class VillageBirchPools
{
    public static void init() {
    }

    static {
	
       ImmutableList<StructureProcessor> zombiefy = ImmutableList.of(new RuleStructureProcessor(
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
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState()))));
       
       ImmutableList<StructureProcessor> mossify = ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()))));

       ImmutableList<StructureProcessor> path_randomizer = ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.GRASS_PATH), new BlockMatchRuleTest(Blocks.WATER), Blocks.BIRCH_PLANKS.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GRASS_PATH, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState()),
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.DIRT), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.getDefaultState()),
		new StructureProcessorRule(new BlockMatchRuleTest(Blocks.DIRT), AlwaysTrueRuleTest.INSTANCE, Blocks.GRASS_BLOCK.getDefaultState()))));

       ImmutableList<StructureProcessor> crop_randomizer = ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState()))));

		StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID+":village/birch/town_centers"), new ResourceLocation("empty"),
	       ImmutableList.of(new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/town_centers/fountain_01", ImmutableList.of(new RuleStructureProcessor(
        	       ImmutableList.of(
        		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()))))), 50),
        		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/town_centers/meeting_point_1", ImmutableList.of(new RuleStructureProcessor(
        	       ImmutableList.of(
        		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()))))), 50),
        		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/town_centers/meeting_point_2"), 50),
        		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/town_centers/meeting_point_3", ImmutableList.of(new RuleStructureProcessor(
        	       ImmutableList.of(
        		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.7F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()))))), 50),
        		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/town_centers/fountain_01", zombiefy), 1),
        		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/town_centers/meeting_point_1", zombiefy), 1),
        		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/town_centers/meeting_point_2", zombiefy), 1),
        		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/town_centers/meeting_point_3", zombiefy), 1)),
	       StructurePool.Projection.RIGID));

		StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID+":village/birch/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/birch/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/corner_01", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/corner_02", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/corner_03", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/straight_01", path_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/straight_02", path_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/straight_03", path_randomizer), 7),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/straight_04", path_randomizer), 7),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/straight_05", path_randomizer), 3),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/straight_06", path_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/streets/turn_01", path_randomizer), 3)),
	       StructurePool.Projection.TERRAIN_MATCHING));
       
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID+":village/birch/zombie/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/birch/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/corner_01", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/corner_02", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/corner_03", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_01", path_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_02", path_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_03", path_randomizer), 7),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_04", path_randomizer), 7),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_05", path_randomizer), 3),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/straight_06", path_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/streets/turn_01", path_randomizer), 3)),
	       StructurePool.Projection.TERRAIN_MATCHING));
       
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID+":village/birch/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/birch/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/small_house_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/small_house_2", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/small_house_3", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/small_house_4", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/small_house_5", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/small_house_6", mossify), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/small_house_7", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/small_house_8", mossify), 3),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/medium_house_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/medium_house_2", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/big_house_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/butcher_shop_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/butcher_shop_2", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/tool_smith_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/fletcher_house_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/shepherds_house_1"), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/armorer_house_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/fisher_cottage_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/tannery_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/cartographer_1", mossify), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/library_1", mossify), 5),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/library_2", mossify), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/masons_house_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/weaponsmith_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/temple_3", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/temple_4", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/stable_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/stable_2"), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/large_farm_1", crop_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/small_farm_1", crop_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/animal_pen_1"), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/animal_pen_2"), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/animal_pen_3"), 5),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/accessory_1"), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/meeting_point_4", ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
			       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.7F), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_COBBLESTONE.getDefaultState()))))), 3),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/meeting_point_5"), 1),
		       Pair.of(EmptyPoolElement.INSTANCE, 10)),
	       StructurePool.Projection.RIGID));
       
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID+":village/birch/zombie/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/birch/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_2", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_3", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_4", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_5", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_6", zombiefy), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_7", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/small_house_8", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/medium_house_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/medium_house_2", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/big_house_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/butcher_shop_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/butcher_shop_2", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/tool_smith_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/fletcher_house_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/shepherds_house_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/armorer_house_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/fisher_cottage_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/tannery_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/cartographer_1", zombiefy), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/library_1", zombiefy), 3),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/library_2", zombiefy), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/masons_house_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/weaponsmith_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/temple_3", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/temple_4", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/stable_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/stable_2", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/large_farm_1", zombiefy), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/small_farm_1", zombiefy), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/animal_pen_1", zombiefy), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/houses/animal_pen_2", zombiefy), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/animal_pen_3", zombiefy), 5),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/meeting_point_4", zombiefy), 3),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/houses/meeting_point_5", zombiefy), 1),
		       Pair.of(EmptyPoolElement.INSTANCE, 10)),
	       StructurePool.Projection.RIGID));
      
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID+":village/birch/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/terminators/terminator_04", path_randomizer), 1)),
	       StructurePool.Projection.TERRAIN_MATCHING));

       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID+":village/birch/zombie/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/terminators/terminator_04", path_randomizer), 1)),
	       StructurePool.Projection.TERRAIN_MATCHING));
       
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID+":village/birch/trees"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(new FeaturePoolElement(Feature.TREE.configure(DefaultBiomeFeatures.BIRCH_TREE_CONFIG)), 1)),
	       StructurePool.Projection.RIGID));
       
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID+":village/birch/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/decor/lamp_1"), 2),
		       new Pair<>(new FeaturePoolElement(Feature.TREE.configure(DefaultBiomeFeatures.BIRCH_TREE_CONFIG)), 1),
		       new Pair<>(new FeaturePoolElement(Feature.FLOWER.configure(DefaultBiomeFeatures.LILY_OF_THE_VALLEY_CONFIG)), 1),
		       new Pair<>(new FeaturePoolElement(Feature.BLOCK_PILE.configure(DefaultBiomeFeatures.HAY_PILE_CONFIG)), 1),
		       Pair.of(EmptyPoolElement.INSTANCE, 2)),
	       StructurePool.Projection.RIGID));
      
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID+":village/birch/zombie/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/decor/lamp_1", zombiefy), 1),
		       new Pair<>(new FeaturePoolElement(Feature.TREE.configure(DefaultBiomeFeatures.BIRCH_TREE_CONFIG)), 1),
		       new Pair<>(new FeaturePoolElement(Feature.FLOWER.configure(DefaultBiomeFeatures.LILY_OF_THE_VALLEY_CONFIG)), 1),
		       new Pair<>(new FeaturePoolElement(Feature.BLOCK_PILE.configure(DefaultBiomeFeatures.HAY_PILE_CONFIG)), 1),
		       Pair.of(EmptyPoolElement.INSTANCE, 2)),
	       StructurePool.Projection.RIGID));
      
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID+":village/birch/villagers"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/villagers/nitwit"), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/villagers/baby"), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/villagers/unemployed"), 10)),
	       StructurePool.Projection.RIGID));
      
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new ResourceLocation(RepurposedStructures.MODID+":village/birch/zombie/villagers"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/villagers/nitwit"), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/birch/zombie/villagers/unemployed"), 10)),
	       StructurePool.Projection.RIGID));
    }
}