package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.StructureProcessorListAccessor;
import net.minecraft.block.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.HugeFungusConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.template.*;


public class VillageCrimsonPools
{
    public static void init() {
		StructureProcessorList zombiefy = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/crimson/zombify",
       		ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.8F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()),
		       new RuleEntry(new TagMatchRuleTest(BlockTags.DOORS), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.PURPLE_TERRACOTTA, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_STEM, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_PLANKS, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_STAIRS, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.ORANGE_STAINED_GLASS_PANE, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new BlockStateMatchRuleTest(Blocks.ORANGE_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, Boolean.TRUE).with(PaneBlock.SOUTH, Boolean.TRUE)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, Boolean.TRUE).with(PaneBlock.SOUTH, Boolean.TRUE)),
		       new RuleEntry(new BlockStateMatchRuleTest(Blocks.ORANGE_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.EAST, Boolean.TRUE).with(PaneBlock.WEST, Boolean.TRUE)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_WART, 0.35F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_WART, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_FIRE.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.REDSTONE_TORCH, 0.25F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new RuleEntry(new BlockMatchRuleTest(Blocks.REDSTONE_TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_TORCH.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.REDSTONE_WALL_TORCH, 0.25F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new RuleEntry(new BlockStateMatchRuleTest(Blocks.REDSTONE_WALL_TORCH.getDefaultState().with(RedstoneWallTorchBlock.FACING, Direction.NORTH).with(RedstoneWallTorchBlock.LIT, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.NORTH)),
			   new RuleEntry(new BlockStateMatchRuleTest(Blocks.REDSTONE_WALL_TORCH.getDefaultState().with(RedstoneWallTorchBlock.FACING, Direction.SOUTH).with(RedstoneWallTorchBlock.LIT, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.SOUTH)),
			   new RuleEntry(new BlockStateMatchRuleTest(Blocks.REDSTONE_WALL_TORCH.getDefaultState().with(RedstoneWallTorchBlock.FACING, Direction.EAST).with(RedstoneWallTorchBlock.LIT, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.EAST)),
			   new RuleEntry(new BlockStateMatchRuleTest(Blocks.REDSTONE_WALL_TORCH.getDefaultState().with(RedstoneWallTorchBlock.FACING, Direction.WEST).with(RedstoneWallTorchBlock.LIT, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.WEST)),
			   new RuleEntry(new BlockMatchRuleTest(Blocks.SHROOMLIGHT), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHER_WART_BLOCK.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.04F), AlwaysTrueRuleTest.INSTANCE, Blocks.GILDED_BLACKSTONE.getDefaultState())))));

		StructureProcessorList randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/crimson/randomizer",
		  	ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.GILDED_BLACKSTONE.getDefaultState()),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_STEM, 0.15F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_STEM, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_STEM, 0.25F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_STEM, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHER_WART_BLOCK.getDefaultState()),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.15F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_HYPHAE, 0.25F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_HYPHAE.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_PLANKS, 0.10F), AlwaysTrueRuleTest.INSTANCE, Blocks.STRIPPED_CRIMSON_HYPHAE.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.CRIMSON_PLANKS, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHER_WART_BLOCK.getDefaultState())))));

		StructureProcessorList path_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/crimson/path_randomizer",
		   ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
				new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.35F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRIMSON_NYLIUM.getDefaultState()),
			   	new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.15F), AlwaysTrueRuleTest.INSTANCE, Blocks.BLACKSTONE.getDefaultState()),
			   	new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.015F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHER_GOLD_ORE.getDefaultState()),
			   	new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.15F), new BlockMatchRuleTest(Blocks.LAVA), Blocks.CRIMSON_NYLIUM.getDefaultState()),
		       	new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.35F), new BlockMatchRuleTest(Blocks.LAVA), Blocks.LAVA.getDefaultState())))));

		StructureProcessorList crop_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/crimson/crop_randomizer",
			ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_WART, 0.35F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState())))));

		StructureProcessorList cracked_blackstone_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/crimson/cracked_blackstone_randomizer",
				ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState())))));

		StructureProcessorList very_cracked_blackstone_randomizer = StructureProcessorListAccessor.invokeRegister(RepurposedStructures.MODID+":village/crimson/very_cracked_blackstone_randomizer",
				ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BLACKSTONE, 0.7F), AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState())))));

		JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/town_centers"), new ResourceLocation("empty"),
	       ImmutableList.of(new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/town_centers/fountain_01", cracked_blackstone_randomizer), 50),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/town_centers/meeting_point_1", cracked_blackstone_randomizer), 50),
        		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/town_centers/meeting_point_2"), 50),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/town_centers/meeting_point_3", very_cracked_blackstone_randomizer), 50),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/town_centers/fountain_01", zombiefy), 1),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/town_centers/meeting_point_1", zombiefy), 1),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/town_centers/meeting_point_2", zombiefy), 1),
        		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/town_centers/meeting_point_3", zombiefy), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));

		JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/crimson/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/corner_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/corner_02", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/corner_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/straight_01", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/straight_02", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/straight_03", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/straight_04", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/straight_05", path_randomizer), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/straight_06", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/streets/turn_01", path_randomizer), 3)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/zombie/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/crimson/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/corner_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/corner_02", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/corner_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_01", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_02", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_03", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_04", path_randomizer), 7),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_05", path_randomizer), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_06", path_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/streets/turn_01", path_randomizer), 3)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/crimson/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/small_house_1", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/small_house_2", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/small_house_3", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/small_house_4", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/small_house_5", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/small_house_6", randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/small_house_7", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/small_house_8", randomizer), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/medium_house_1", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/medium_house_2", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/big_house_1", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/butcher_shop_1", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/butcher_shop_2", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/tool_smith_1", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/fletcher_house_1", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/houses/shepherds_house_1"), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/armorer_house_1", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/fisher_cottage_1", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/tannery_1", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/cartographer_1", randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/library_1", randomizer), 5),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/library_2", randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/masons_house_1", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/weaponsmith_1", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/temple_3", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/temple_4", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/stable_1", randomizer), 2),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/houses/stable_2"), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/large_farm_1", crop_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/small_farm_1", crop_randomizer), 4),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/houses/animal_pen_1"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/houses/animal_pen_2"), 1),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/houses/animal_pen_3"), 5),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/houses/accessory_1"), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/meeting_point_4", very_cracked_blackstone_randomizer), 3),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/houses/meeting_point_5"), 1),
		       Pair.of(JigsawPiece.method_30438(), 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/zombie/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/crimson/terminators"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_3", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_4", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_5", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_6", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_7", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_8", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/medium_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/big_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/butcher_shop_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/butcher_shop_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/tool_smith_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/fletcher_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/shepherds_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/armorer_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/fisher_cottage_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/tannery_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/cartographer_1", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/library_1", zombiefy), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/library_2", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/masons_house_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/weaponsmith_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/temple_3", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/temple_4", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/stable_1", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/stable_2", zombiefy), 2),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/large_farm_1", zombiefy), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/small_farm_1", zombiefy), 4),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/animal_pen_1", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/houses/animal_pen_2", zombiefy), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/animal_pen_3", zombiefy), 5),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/meeting_point_4", zombiefy), 3),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/houses/meeting_point_5", zombiefy), 1),
		       Pair.of(JigsawPiece.method_30438(), 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/terminators/terminator_04", path_randomizer), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));

       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/zombie/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/zombie/terminators/terminator_04", path_randomizer), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/trees"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30421(Feature.HUGE_FUNGUS.configure(HugeFungusConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG)), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/lamp_1"), 8),
		       new Pair<>(JigsawPiece.method_30421(Feature.HUGE_FUNGUS.configure(HugeFungusConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG)), 4),
		       new Pair<>(JigsawPiece.method_30421(Features.PATCH_CRIMSON_ROOTS), 4),
		       Pair.of(JigsawPiece.method_30438(), 2)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/zombie/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(JigsawPiece.method_30426(RepurposedStructures.MODID+":village/crimson/lamp_1", zombiefy), 3),
		       new Pair<>(JigsawPiece.method_30421(Feature.HUGE_FUNGUS.configure(HugeFungusConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG)), 4),
		       new Pair<>(JigsawPiece.method_30421(Features.PATCH_CRIMSON_ROOTS), 4),
		       Pair.of(JigsawPiece.method_30438(), 2)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/piglins"), new ResourceLocation("empty"),
	       ImmutableList.of(
			   new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/piglins/adult_crossbow"), 7),
			   new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/piglins/adult_sword"), 7),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/piglins/baby"), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/zombie/piglins"), new ResourceLocation("empty"),
	       ImmutableList.of(
			   new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/zombie/piglins/adult_crossbow"), 7),
			   new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/zombie/piglins/adult_sword"), 7),
		       new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/zombie/piglins/baby_sword"), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));

		JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/mobs/hoglins"), new ResourceLocation("empty"),
			ImmutableList.of(
					new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/mobs/hoglin_adult"), 10),
					new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/mobs/hoglin_baby"), 1)),
			JigsawPattern.PlacementBehaviour.RIGID));


		JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/mobs/zoglins"), new ResourceLocation("empty"),
			ImmutableList.of(
					new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/mobs/zoglin_adult"), 10),
					new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/mobs/zoglin_baby"), 1)),
			JigsawPattern.PlacementBehaviour.RIGID));


		JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/mobs/striders"), new ResourceLocation("empty"),
			ImmutableList.of(
					new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/mobs/strider_adult"), 10),
					new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/mobs/strider_baby"), 1)),
			JigsawPattern.PlacementBehaviour.RIGID));

		JigsawPatternRegistry.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/mobs/piglin_brutes"), new ResourceLocation("empty"),
			ImmutableList.of(
					new Pair<>(JigsawPiece.method_30425(RepurposedStructures.MODID+":village/crimson/mobs/adult_sword"), 1)),
			JigsawPattern.PlacementBehaviour.RIGID));
	}
}