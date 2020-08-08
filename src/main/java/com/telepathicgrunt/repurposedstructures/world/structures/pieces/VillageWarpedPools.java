package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

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
public class VillageWarpedPools
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
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235368_mh_, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235345_mD_, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235357_mP_, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235370_mj_, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
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
			   new RuleEntry(new BlockMatchRuleTest(Blocks.field_235383_mw_), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235374_mn_.getDefaultState()),
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.04F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235387_nA_.getDefaultState()))));
       
       ImmutableList<StructureProcessor> randomizer = ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235412_nv_.getDefaultState()),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.02F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235387_nA_.getDefaultState()),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235368_mh_, 0.15F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235370_mj_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235368_mh_, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235370_mj_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235368_mh_, 0.25F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235370_mj_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235368_mh_, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235374_mn_ .getDefaultState()),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235370_mj_, 0.15F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235370_mj_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235370_mj_, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235370_mj_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235370_mj_, 0.25F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235370_mj_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235345_mD_, 0.10F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235371_mk_.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)),
				   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235345_mD_, 0.05F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235374_mn_ .getDefaultState()))));

       ImmutableList<StructureProcessor> path_randomizer = ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
				new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.35F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235372_ml_.getDefaultState()),
			   	new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.15F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235406_np_.getDefaultState()),
			   	new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.015F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235334_I_.getDefaultState()),
			   	new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.15F), new BlockMatchRuleTest(Blocks.LAVA), Blocks.field_235372_ml_.getDefaultState()),
		       	new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHERRACK, 0.35F), new BlockMatchRuleTest(Blocks.LAVA), Blocks.LAVA.getDefaultState()))));

       ImmutableList<StructureProcessor> crop_randomizer = ImmutableList.of(new RuleStructureProcessor(
	       ImmutableList.of(
		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_WART, 0.35F), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()))));

		JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/town_centers"), new ResourceLocation("empty"),
	       ImmutableList.of(new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/town_centers/fountain_01", ImmutableList.of(new RuleStructureProcessor(
        	       ImmutableList.of(
        		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235412_nv_.getDefaultState()))))), 50),
        		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/town_centers/meeting_point_1", ImmutableList.of(new RuleStructureProcessor(
        	       ImmutableList.of(
        		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.2F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235412_nv_.getDefaultState()))))), 50),
        		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/town_centers/meeting_point_2"), 50),
        		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/town_centers/meeting_point_3", ImmutableList.of(new RuleStructureProcessor(
        	       ImmutableList.of(
        		       new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.7F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235412_nv_.getDefaultState()))))), 50),
        		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/town_centers/fountain_01", zombiefy), 1),
        		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/town_centers/meeting_point_1", zombiefy), 1),
        		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/town_centers/meeting_point_2", zombiefy), 1),
        		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/town_centers/meeting_point_3", zombiefy), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));

		JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/warped/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/corner_01", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/corner_02", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/corner_03", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/straight_01", path_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/straight_02", path_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/straight_03", path_randomizer), 7),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/straight_04", path_randomizer), 7),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/straight_05", path_randomizer), 3),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/straight_06", path_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/streets/turn_01", path_randomizer), 3)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/zombie/streets"), new ResourceLocation(RepurposedStructures.MODID+":village/warped/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/corner_01", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/corner_02", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/corner_03", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/straight_01", path_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/straight_02", path_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/straight_03", path_randomizer), 7),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/straight_04", path_randomizer), 7),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/straight_05", path_randomizer), 3),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/straight_06", path_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/crossroad_01", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/crossroad_02", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/crossroad_03", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/crossroad_04", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/crossroad_05", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/crossroad_06", path_randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/streets/turn_01", path_randomizer), 3)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/warped/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/small_house_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/small_house_2", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/small_house_3", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/small_house_4", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/small_house_5", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/small_house_6", randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/small_house_7", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/small_house_8", randomizer), 3),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/medium_house_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/medium_house_2", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/big_house_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/butcher_shop_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/butcher_shop_2", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/tool_smith_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/fletcher_house_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/shepherds_house_1"), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/armorer_house_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/fisher_cottage_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/tannery_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/cartographer_1", randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/library_1", randomizer), 5),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/library_2", randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/masons_house_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/weaponsmith_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/temple_3", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/temple_4", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/stable_1", randomizer), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/stable_2"), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/large_farm_1", crop_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/small_farm_1", crop_randomizer), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/animal_pen_1"), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/animal_pen_2"), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/animal_pen_3"), 5),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/accessory_1"), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/meeting_point_4", ImmutableList.of(new RuleStructureProcessor(ImmutableList.of(
			   new RuleEntry(new RandomBlockMatchRuleTest(Blocks.field_235406_np_, 0.7F), AlwaysTrueRuleTest.INSTANCE, Blocks.field_235412_nv_.getDefaultState()))))), 3),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/meeting_point_5"), 1),
		       Pair.of(EmptyJigsawPiece.INSTANCE, 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/zombie/houses"), new ResourceLocation(RepurposedStructures.MODID+":village/warped/terminators"),
	       ImmutableList.of(
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/small_house_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/small_house_2", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/small_house_3", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/small_house_4", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/small_house_5", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/small_house_6", zombiefy), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/small_house_7", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/small_house_8", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/medium_house_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/big_house_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/butcher_shop_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/butcher_shop_2", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/tool_smith_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/fletcher_house_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/shepherds_house_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/armorer_house_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/fisher_cottage_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/tannery_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/cartographer_1", zombiefy), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/library_1", zombiefy), 3),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/library_2", zombiefy), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/masons_house_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/weaponsmith_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/temple_3", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/temple_4", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/stable_1", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/stable_2", zombiefy), 2),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/large_farm_1", zombiefy), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/small_farm_1", zombiefy), 4),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/animal_pen_1", zombiefy), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/houses/animal_pen_2", zombiefy), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/animal_pen_3", zombiefy), 5),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/meeting_point_4", zombiefy), 3),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/houses/meeting_point_5", zombiefy), 1),
		       Pair.of(EmptyJigsawPiece.INSTANCE, 10)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/terminators/terminator_04", path_randomizer), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));

       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/zombie/terminators"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/terminators/terminator_01", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/terminators/terminator_02", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/terminators/terminator_03", path_randomizer), 1),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/terminators/terminator_04", path_randomizer), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/trees"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(new FeatureJigsawPiece(Feature.field_236281_L_.configure(HugeFungusConfig.WARPED_FUNGUS_NOT_PLANTED_CONFIG)), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));
       
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/lamp_1"), 8),
		       new Pair<>(new FeatureJigsawPiece(Feature.field_236281_L_.configure(HugeFungusConfig.WARPED_FUNGUS_NOT_PLANTED_CONFIG)), 4),
		       new Pair<>(new FeatureJigsawPiece(Feature.field_227244_A_.configure(DefaultBiomeFeatures.WARPED_ROOTS_CONFIG)), 4),
		       Pair.of(EmptyJigsawPiece.INSTANCE, 2)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/zombie/decor"), new ResourceLocation("empty"),
	       ImmutableList.of(
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/lamp_1", zombiefy), 3),
		       new Pair<>(new FeatureJigsawPiece(Feature.field_236281_L_.configure(HugeFungusConfig.WARPED_FUNGUS_NOT_PLANTED_CONFIG)), 4),
		       new Pair<>(new FeatureJigsawPiece(Feature.field_227244_A_.configure(DefaultBiomeFeatures.WARPED_ROOTS_CONFIG)), 4),
		       Pair.of(EmptyJigsawPiece.INSTANCE, 2)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/piglins"), new ResourceLocation("empty"),
	       ImmutableList.of(
			   new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/piglins/adult_crossbow"), 7),
			   new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/piglins/adult_sword"), 7),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/piglins/baby"), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));
      
       JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/zombie/piglins"), new ResourceLocation("empty"),
	       ImmutableList.of(
			   new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/piglins/adult_crossbow"), 7),
			   new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/piglins/adult_sword"), 7),
		       new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/zombie/piglins/baby_sword"), 1)),
	       JigsawPattern.PlacementBehaviour.RIGID));

		JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/mobs/hoglins"), new ResourceLocation("empty"),
			ImmutableList.of(
					new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/mobs/hoglin_adult"), 10),
					new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/mobs/hoglin_baby"), 1)),
			JigsawPattern.PlacementBehaviour.RIGID));


		JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/mobs/zoglins"), new ResourceLocation("empty"),
			ImmutableList.of(
					new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/mobs/zoglin_adult"), 10),
					new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/mobs/zoglin_baby"), 1)),
			JigsawPattern.PlacementBehaviour.RIGID));


		JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/mobs/striders"), new ResourceLocation("empty"),
			ImmutableList.of(
					new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/mobs/strider_adult"), 10),
					new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/mobs/strider_baby"), 1)),
			JigsawPattern.PlacementBehaviour.RIGID));

		JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(RepurposedStructures.MODID+":village/warped/mobs/piglin_brutes"), new ResourceLocation("empty"),
			ImmutableList.of(
					new Pair<>(new SingleJigsawPiece(RepurposedStructures.MODID+":village/warped/mobs/adult_sword"), 1)),
			JigsawPattern.PlacementBehaviour.RIGID));
	}
}