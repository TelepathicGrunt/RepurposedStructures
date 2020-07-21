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
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

@SuppressWarnings("deprecation")
public class VillageNetherPools
{
    public static void init() {
    }

    static {
	
       ImmutableList<StructureProcessor> zombiefy = ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.8F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()),
		       new StructureProcessorRule(new TagMatchRuleTest(BlockTags.DOORS), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.WALL_TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHITE_TERRACOTTA, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_STEM, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_PLANKS, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_STAIRS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new StructureProcessorRule(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, Boolean.TRUE).with(PaneBlock.SOUTH, Boolean.TRUE)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, Boolean.TRUE).with(PaneBlock.SOUTH, Boolean.TRUE)),
		       new StructureProcessorRule(new BlockStateMatchRuleTest(Blocks.GLASS_PANE.getDefaultState().with(PaneBlock.EAST, Boolean.TRUE).with(PaneBlock.WEST, Boolean.TRUE)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CARROTS.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.POTATOES.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.BEETROOTS.getDefaultState()))));
       
       ImmutableList<StructureProcessor> mossify = ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()))));

       ImmutableList<StructureProcessor> path_randomizer = ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.CRIMSON_NYLIUM), new BlockMatchRuleTest(Blocks.LAVA), Blocks.CRIMSON_PLANKS.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CRIMSON_NYLIUM, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHERRACK.getDefaultState()),
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.CRIMSON_NYLIUM), new BlockMatchRuleTest(Blocks.LAVA), Blocks.LAVA.getDefaultState()),
		       new StructureProcessorRule(new BlockMatchRuleTest(Blocks.NETHERRACK), new BlockMatchRuleTest(Blocks.LAVA), Blocks.LAVA.getDefaultState()),
		new StructureProcessorRule(new BlockMatchRuleTest(Blocks.NETHERRACK), AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_NYLIUM.getDefaultState()))));

       ImmutableList<StructureProcessor> crop_randomizer = ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_WART, 0.3F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_FUNGUS.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_WART, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_ROOTS.getDefaultState()),
		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.NETHER_WART, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.FIRE.getDefaultState()))));

		StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/crimson/town_centers"), new Identifier("empty"),
	       ImmutableList.of(new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/town_centers/fountain_01", ImmutableList.of(new RuleStructureProcessor(
        	       ImmutableList.of(
        		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()))))), 50),
        		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/town_centers/meeting_point_1", ImmutableList.of(new RuleStructureProcessor(
        	       ImmutableList.of(
        		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()))))), 50),
        		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/town_centers/meeting_point_2"), 50),
        		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/town_centers/meeting_point_3", ImmutableList.of(new RuleStructureProcessor(
        	       ImmutableList.of(
        		       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.7F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()))))), 50),
        		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/town_centers/fountain_01", zombiefy), 1),
        		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/town_centers/meeting_point_1", zombiefy), 1),
        		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/town_centers/meeting_point_2", zombiefy), 1),
        		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/town_centers/meeting_point_3", zombiefy), 1)),
	       StructurePool.Projection.RIGID));

		StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/crimson/streets"), new Identifier(RepurposedStructures.MODID+":village/crimson/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/corner_01", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/corner_02", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/corner_03", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/straight_01", path_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/straight_02", path_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/straight_03", path_randomizer), 7),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/straight_04", path_randomizer), 7),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/straight_05", path_randomizer), 3),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/straight_06", path_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/streets/turn_01", path_randomizer), 3)),
	       StructurePool.Projection.RIGID));
       
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/crimson/zombie/streets"), new Identifier(RepurposedStructures.MODID+":village/crimson/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/corner_01", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/corner_02", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/corner_03", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_01", path_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_02", path_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_03", path_randomizer), 7),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_04", path_randomizer), 7),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_05", path_randomizer), 3),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_06", path_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/streets/turn_01", path_randomizer), 3)),
	       StructurePool.Projection.RIGID));
       
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/crimson/houses"), new Identifier(RepurposedStructures.MODID+":village/crimson/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/small_house_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/small_house_2", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/small_house_3", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/small_house_4", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/small_house_5", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/small_house_6", mossify), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/small_house_7", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/small_house_8", mossify), 3),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/medium_house_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/medium_house_2", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/big_house_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/butcher_shop_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/butcher_shop_2", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/tool_smith_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/fletcher_house_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/shepherds_house_1"), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/armorer_house_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/fisher_cottage_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/tannery_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/cartographer_1", mossify), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/library_1", mossify), 5),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/library_2", mossify), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/masons_house_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/weaponsmith_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/temple_3", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/temple_4", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/stable_1", mossify), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/stable_2"), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/large_farm_1", crop_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/small_farm_1", crop_randomizer), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/animal_pen_1"), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/animal_pen_2"), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/animal_pen_3"), 5),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/accessory_1"), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/meeting_point_4", ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
			       new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.7F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()))))), 3),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/meeting_point_5"), 1),
		       Pair.of(EmptyPoolElement.INSTANCE, 10)),
	       StructurePool.Projection.RIGID));
       
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/crimson/zombie/houses"), new Identifier(RepurposedStructures.MODID+":village/crimson/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_2", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_3", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_4", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_5", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_6", zombiefy), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_7", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_8", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/medium_house_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/medium_house_2", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/big_house_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/butcher_shop_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/butcher_shop_2", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/tool_smith_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/fletcher_house_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/shepherds_house_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/armorer_house_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/fisher_cottage_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/tannery_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/cartographer_1", zombiefy), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/library_1", zombiefy), 3),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/library_2", zombiefy), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/masons_house_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/weaponsmith_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/temple_3", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/temple_4", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/stable_1", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/stable_2", zombiefy), 2),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/large_farm_1", zombiefy), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/small_farm_1", zombiefy), 4),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/animal_pen_1", zombiefy), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/houses/animal_pen_2", zombiefy), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/animal_pen_3", zombiefy), 5),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/meeting_point_4", zombiefy), 3),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/houses/meeting_point_5", zombiefy), 1),
		       Pair.of(EmptyPoolElement.INSTANCE, 10)),
	       StructurePool.Projection.RIGID));
      
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/crimson/terminators"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/terminators/terminator_04", path_randomizer), 1)),
	       StructurePool.Projection.RIGID));

       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/crimson/zombie/terminators"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/terminators/terminator_04", path_randomizer), 1)),
	       StructurePool.Projection.RIGID));
       
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/crimson/trees"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(new FeaturePoolElement(Feature.HUGE_FUNGUS.configure(HugeFungusFeatureConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG)), 1)),
	       StructurePool.Projection.RIGID));
       
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/crimson/decor"), new Identifier("empty"),
	       ImmutableList.of(new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/decor/lamp_1"), 2),
		       new Pair<>(new FeaturePoolElement(Feature.HUGE_FUNGUS.configure(HugeFungusFeatureConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG)), 1),
		       new Pair<>(new FeaturePoolElement(Feature.FLOWER.configure(DefaultBiomeFeatures.LILY_OF_THE_VALLEY_CONFIG)), 1),
		       new Pair<>(new FeaturePoolElement(Feature.BLOCK_PILE.configure(DefaultBiomeFeatures.HAY_PILE_CONFIG)), 1),
		       Pair.of(EmptyPoolElement.INSTANCE, 2)),
	       StructurePool.Projection.RIGID));
      
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/crimson/zombie/decor"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/decor/lamp_1", zombiefy), 1),
		       new Pair<>(new FeaturePoolElement(Feature.HUGE_FUNGUS.configure(HugeFungusFeatureConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG)), 1),
		       new Pair<>(new FeaturePoolElement(Feature.FLOWER.configure(DefaultBiomeFeatures.LILY_OF_THE_VALLEY_CONFIG)), 1),
		       new Pair<>(new FeaturePoolElement(Feature.BLOCK_PILE.configure(DefaultBiomeFeatures.HAY_PILE_CONFIG)), 1),
		       Pair.of(EmptyPoolElement.INSTANCE, 2)),
	       StructurePool.Projection.RIGID));
      
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/crimson/villagers"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/piglins/adult"), 10),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/piglins/baby"), 1)),
	       StructurePool.Projection.RIGID));
      
       StructurePoolBasedGenerator.REGISTRY.add(new StructurePool(new Identifier(RepurposedStructures.MODID+":village/crimson/zombie/villagers"), new Identifier("empty"),
	       ImmutableList.of(
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/piglins/adult"), 10),
		       new Pair<>(new SinglePoolElement(RepurposedStructures.MODID+":village/crimson/zombie/piglins/baby"), 1)),
	       StructurePool.Projection.RIGID));
    }
}