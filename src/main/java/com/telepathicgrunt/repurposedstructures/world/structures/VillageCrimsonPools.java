package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusConfig;
import net.minecraft.world.gen.feature.jigsaw.*;
import net.minecraft.world.gen.feature.template.*;

@SuppressWarnings("deprecation")
public class VillageCrimsonPools
{
    public static void init() {
    }

    static {
	
       ImmutableList<StructureProcessor> zombiefy = ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.8F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235412_nv_.getDefaultState()),
		       new RuleEntry(new TagMatchRuleTest(BlockTags.DOORS), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235412_nv_, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.PURPLE_TERRACOTTA, 0.07F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235377_mq_, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235344_mC_, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235356_mO_, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235379_ms_, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.ORANGE_STAINED_GLASS_PANE, 0.5F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new BlockStateMatchRuleTest(Blocks.ORANGE_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, Boolean.TRUE).with(PaneBlock.SOUTH, Boolean.TRUE)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.NORTH, Boolean.TRUE).with(PaneBlock.SOUTH, Boolean.TRUE)),
		       new RuleEntry(new BlockStateMatchRuleTest(Blocks.ORANGE_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.EAST, Boolean.TRUE).with(PaneBlock.WEST, Boolean.TRUE)), AlwaysTrueRuleTest.INSTANCE, Blocks.BROWN_STAINED_GLASS_PANE.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true)),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_WART, 0.35F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_WART, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235335_bO_.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.REDSTONE_TORCH, 0.25F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new RuleEntry(new BlockMatchRuleTest(Blocks.REDSTONE_TORCH), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235339_cQ_.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.REDSTONE_WALL_TORCH, 0.25F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new RuleEntry(new BlockStateMatchRuleTest(Blocks.REDSTONE_WALL_TORCH.getDefaultState().with(RedstoneWallTorchBlock.FACING, Direction.NORTH).with(RedstoneWallTorchBlock.LIT, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235340_cR_.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.NORTH)),
			   new RuleEntry(new BlockStateMatchRuleTest(Blocks.REDSTONE_WALL_TORCH.getDefaultState().with(RedstoneWallTorchBlock.FACING, Direction.SOUTH).with(RedstoneWallTorchBlock.LIT, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235340_cR_.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.SOUTH)),
			   new RuleEntry(new BlockStateMatchRuleTest(Blocks.REDSTONE_WALL_TORCH.getDefaultState().with(RedstoneWallTorchBlock.FACING, Direction.EAST).with(RedstoneWallTorchBlock.LIT, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235340_cR_.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.EAST)),
			   new RuleEntry(new BlockStateMatchRuleTest(Blocks.REDSTONE_WALL_TORCH.getDefaultState().with(RedstoneWallTorchBlock.FACING, Direction.WEST).with(RedstoneWallTorchBlock.LIT, true)), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235340_cR_.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.WEST)),
			   new RuleEntry(new BlockMatchRuleTest(Blocks.field_235383_mw_), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHER_WART_BLOCK.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.04F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235387_nA_.getDefaultState()))));
       
       ImmutableList<StructureProcessor> randomizer = ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235412_nv_.getDefaultState()),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235387_nA_.getDefaultState()),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235377_mq_, 0.15F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235379_ms_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235377_mq_, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235379_ms_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235377_mq_, 0.25F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235379_ms_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235377_mq_, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHER_WART_BLOCK.getDefaultState()),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235379_ms_, 0.15F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235379_ms_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235379_ms_, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235379_ms_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235379_ms_, 0.25F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235379_ms_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235344_mC_, 0.10F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235380_mt_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235344_mC_, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.NETHER_WART_BLOCK.getDefaultState()))));

       ImmutableList<StructureProcessor> path_randomizer = ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
				new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.35F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235381_mu_.getDefaultState()),
			   	new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.15F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235406_np_.getDefaultState()),
			   	new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.015F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235334_I_.getDefaultState()),
			   	new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.15F), new BlockMatchRuleTest(Blocks.LAVA), Blocks.field_235381_mu_.getDefaultState()),
		       	new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.35F), new BlockMatchRuleTest(Blocks.LAVA), Blocks.LAVA.getDefaultState()))));

       ImmutableList<StructureProcessor> crop_randomizer = ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_WART, 0.35F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()))));

		JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/town_centers"), new ResourceLocation("empty"),
	       ImmutableList.of(new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/town_centers/fountain_01", ImmutableList.of(new RuleStructureProcessor(
        	       ImmutableList.of(
        		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235412_nv_.getDefaultState()))))), 50),
        		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/town_centers/meeting_point_1", ImmutableList.of(new RuleStructureProcessor(
        	       ImmutableList.of(
        		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235412_nv_.getDefaultState()))))), 50),
        		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/town_centers/meeting_point_2"), 50),
        		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/town_centers/meeting_point_3", ImmutableList.of(new RuleStructureProcessor(
        	       ImmutableList.of(
        		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.7F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235412_nv_.getDefaultState()))))), 50),
        		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/town_centers/fountain_01", zombiefy), 1),
        		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/town_centers/meeting_point_1", zombiefy), 1),
        		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/town_centers/meeting_point_2", zombiefy), 1),
        		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/town_centers/meeting_point_3", zombiefy), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));

		JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/crimson/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/corner_01", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/corner_02", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/corner_03", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/straight_01", path_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/straight_02", path_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/straight_03", path_randomizer), 7),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/straight_04", path_randomizer), 7),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/straight_05", path_randomizer), 3),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/straight_06", path_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/streets/turn_01", path_randomizer), 3)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/zombie/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/crimson/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/corner_01", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/corner_02", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/corner_03", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_01", path_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_02", path_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_03", path_randomizer), 7),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_04", path_randomizer), 7),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_05", path_randomizer), 3),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/straight_06", path_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/streets/turn_01", path_randomizer), 3)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/crimson/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/small_house_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/small_house_2", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/small_house_3", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/small_house_4", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/small_house_5", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/small_house_6", randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/small_house_7", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/small_house_8", randomizer), 3),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/medium_house_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/medium_house_2", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/big_house_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/butcher_shop_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/butcher_shop_2", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/tool_smith_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/fletcher_house_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/shepherds_house_1"), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/armorer_house_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/fisher_cottage_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/tannery_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/cartographer_1", randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/library_1", randomizer), 5),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/library_2", randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/masons_house_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/weaponsmith_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/temple_3", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/temple_4", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/stable_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/stable_2"), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/large_farm_1", crop_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/small_farm_1", crop_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/animal_pen_1"), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/animal_pen_2"), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/animal_pen_3"), 5),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/accessory_1"), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/meeting_point_4", ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.7F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235412_nv_.getDefaultState()))))), 3),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/meeting_point_5"), 1),
		       Pair.of(EmptyJigsawPiece.INSTANCE, 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/zombie/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/crimson/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_2", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_3", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_4", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_5", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_6", zombiefy), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_7", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/small_house_8", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/medium_house_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/big_house_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/butcher_shop_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/butcher_shop_2", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/tool_smith_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/fletcher_house_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/shepherds_house_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/armorer_house_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/fisher_cottage_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/tannery_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/cartographer_1", zombiefy), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/library_1", zombiefy), 3),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/library_2", zombiefy), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/masons_house_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/weaponsmith_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/temple_3", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/temple_4", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/stable_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/stable_2", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/large_farm_1", zombiefy), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/small_farm_1", zombiefy), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/animal_pen_1", zombiefy), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/houses/animal_pen_2", zombiefy), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/animal_pen_3", zombiefy), 5),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/meeting_point_4", zombiefy), 3),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/houses/meeting_point_5", zombiefy), 1),
		       Pair.of(EmptyJigsawPiece.INSTANCE, 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/terminators/terminator_04", path_randomizer), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));

       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/zombie/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/terminators/terminator_04", path_randomizer), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/trees"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(new FeatureJigsawPiece(Feature.field_236281_L_.configure(HugeFungusConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG)), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/lamp_1"), 8),
		       new Pair<>(new FeatureJigsawPiece(Feature.field_236281_L_.configure(HugeFungusConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG)), 4),
		       new Pair<>(new FeatureJigsawPiece(Feature.field_227244_A_.configure(DefaultBiomeFeatures.CRIMSON_ROOTS_CONFIG)), 4),
		       Pair.of(EmptyJigsawPiece.INSTANCE, 2)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/zombie/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/lamp_1", zombiefy), 3),
		       new Pair<>(new FeatureJigsawPiece(Feature.field_236281_L_.configure(HugeFungusConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG)), 4),
		       new Pair<>(new FeatureJigsawPiece(Feature.field_227244_A_.configure(DefaultBiomeFeatures.CRIMSON_ROOTS_CONFIG)), 4),
		       Pair.of(EmptyJigsawPiece.INSTANCE, 2)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/piglins"), new ResourceLocation("empty"),
	       ImmutableList.of(
			   new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/piglins/adult_crossbow"), 7),
			   new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/piglins/adult_sword"), 7),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/piglins/baby"), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/zombie/piglins"), new ResourceLocation("empty"),
	       ImmutableList.of(
			   new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/piglins/adult_crossbow"), 7),
			   new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/piglins/adult_sword"), 7),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/zombie/piglins/baby_sword"), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));

		JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/mobs/hoglins"), new ResourceLocation("empty"),
			ImmutableList.of(
					new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/mobs/hoglin_adult"), 10),
					new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/mobs/hoglin_baby"), 1)),
			JigsawPattern.PlacementBehaviour.RIGID));


		JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/mobs/zoglins"), new ResourceLocation("empty"),
			ImmutableList.of(
					new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/mobs/zoglin_adult"), 10),
					new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/mobs/zoglin_baby"), 1)),
			JigsawPattern.PlacementBehaviour.RIGID));


		JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/mobs/striders"), new ResourceLocation("empty"),
			ImmutableList.of(
					new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/mobs/strider_adult"), 10),
					new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/mobs/strider_baby"), 1)),
			JigsawPattern.PlacementBehaviour.RIGID));

		JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/crimson/mobs/piglin_brutes"), new ResourceLocation("empty"),
			ImmutableList.of(
					new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/crimson/mobs/adult_sword"), 1)),
			JigsawPattern.PlacementBehaviour.RIGID));
	}
}