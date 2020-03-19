package com.telepathicgrunt.repurposedstructures.world.features.structures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RSConfig;
import com.telepathicgrunt.repurposedstructures.world.features.RSFeatures;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FourWayBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTables;


public class JungleFortressPieces
{
	private static final Map<BlockState, BlockState> INFESTED_STONE_LOOKUP;
	static {
		INFESTED_STONE_LOOKUP = new HashMap<BlockState, BlockState>();
		INFESTED_STONE_LOOKUP.put(Blocks.STONE_BRICKS.getDefaultState(), Blocks.INFESTED_STONE_BRICKS.getDefaultState());
		INFESTED_STONE_LOOKUP.put(Blocks.STONE.getDefaultState(), Blocks.INFESTED_STONE.getDefaultState());
		INFESTED_STONE_LOOKUP.put(Blocks.MOSSY_COBBLESTONE.getDefaultState(), Blocks.INFESTED_MOSSY_STONE_BRICKS.getDefaultState());
		INFESTED_STONE_LOOKUP.put(Blocks.CRACKED_STONE_BRICKS.getDefaultState(), Blocks.INFESTED_CRACKED_STONE_BRICKS.getDefaultState());
		INFESTED_STONE_LOOKUP.put(Blocks.COBBLESTONE.getDefaultState(), Blocks.INFESTED_COBBLESTONE.getDefaultState());
		INFESTED_STONE_LOOKUP.put(Blocks.CHISELED_STONE_BRICKS.getDefaultState(), Blocks.INFESTED_CHISELED_STONE_BRICKS.getDefaultState());
		INFESTED_STONE_LOOKUP.put(Blocks.MOSSY_STONE_BRICKS.getDefaultState(), Blocks.MOSSY_STONE_BRICKS.getDefaultState());
	}
	
	private static final JungleFortressPieces.PieceWeight[] PRIMARY_COMPONENTS = new JungleFortressPieces.PieceWeight[] { new JungleFortressPieces.PieceWeight(JungleFortressPieces.Straight.class, 30, 0, true), new JungleFortressPieces.PieceWeight(JungleFortressPieces.Crossing3.class, 10, 4), new JungleFortressPieces.PieceWeight(JungleFortressPieces.Crossing.class, 10, 4), new JungleFortressPieces.PieceWeight(JungleFortressPieces.Stairs.class, 10, 3), new JungleFortressPieces.PieceWeight(JungleFortressPieces.Throne.class, 5, 2),
			new JungleFortressPieces.PieceWeight(JungleFortressPieces.Entrance.class, 5, 1) };
	private static final JungleFortressPieces.PieceWeight[] SECONDARY_COMPONENTS = new JungleFortressPieces.PieceWeight[] { new JungleFortressPieces.PieceWeight(JungleFortressPieces.Corridor5.class, 25, 0, true), new JungleFortressPieces.PieceWeight(JungleFortressPieces.Crossing2.class, 15, 5), new JungleFortressPieces.PieceWeight(JungleFortressPieces.Corridor2.class, 5, 10), new JungleFortressPieces.PieceWeight(JungleFortressPieces.Corridor.class, 5, 10),
			new JungleFortressPieces.PieceWeight(JungleFortressPieces.Corridor3.class, 10, 3, true), new JungleFortressPieces.PieceWeight(JungleFortressPieces.Corridor4.class, 7, 2), new JungleFortressPieces.PieceWeight(JungleFortressPieces.NetherStalkRoom.class, 5, 2) };


	private static JungleFortressPieces.Piece findAndCreateBridgePieceFactory(JungleFortressPieces.PieceWeight p_175887_0_, List<StructurePiece> p_175887_1_, Random p_175887_2_, int p_175887_3_, int p_175887_4_, int p_175887_5_, Direction p_175887_6_, int p_175887_7_)
	{
		Class<? extends JungleFortressPieces.Piece> oclass = p_175887_0_.weightClass;
		JungleFortressPieces.Piece structurenetherbridgepieces$piece = null;

		if (oclass == JungleFortressPieces.Straight.class)
		{
			structurenetherbridgepieces$piece = JungleFortressPieces.Straight.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
		}
		else if (oclass == JungleFortressPieces.Crossing3.class)
		{
			structurenetherbridgepieces$piece = JungleFortressPieces.Crossing3.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
		}
		else if (oclass == JungleFortressPieces.Crossing.class)
		{
			structurenetherbridgepieces$piece = JungleFortressPieces.Crossing.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
		}
		else if (oclass == JungleFortressPieces.Stairs.class)
		{
			structurenetherbridgepieces$piece = JungleFortressPieces.Stairs.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_7_, p_175887_6_);
		}
		else if (oclass == JungleFortressPieces.Throne.class)
		{
			structurenetherbridgepieces$piece = JungleFortressPieces.Throne.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_7_, p_175887_6_);
		}
		else if (oclass == JungleFortressPieces.Entrance.class)
		{
			structurenetherbridgepieces$piece = JungleFortressPieces.Entrance.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
		}
		else if (oclass == JungleFortressPieces.Corridor5.class)
		{
			structurenetherbridgepieces$piece = JungleFortressPieces.Corridor5.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
		}
		else if (oclass == JungleFortressPieces.Corridor2.class)
		{
			structurenetherbridgepieces$piece = JungleFortressPieces.Corridor2.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
		}
		else if (oclass == JungleFortressPieces.Corridor.class)
		{
			structurenetherbridgepieces$piece = JungleFortressPieces.Corridor.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
		}
		else if (oclass == JungleFortressPieces.Corridor3.class)
		{
			structurenetherbridgepieces$piece = JungleFortressPieces.Corridor3.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
		}
		else if (oclass == JungleFortressPieces.Corridor4.class)
		{
			structurenetherbridgepieces$piece = JungleFortressPieces.Corridor4.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
		}
		else if (oclass == JungleFortressPieces.Crossing2.class)
		{
			structurenetherbridgepieces$piece = JungleFortressPieces.Crossing2.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
		}
		else if (oclass == JungleFortressPieces.NetherStalkRoom.class)
		{
			structurenetherbridgepieces$piece = JungleFortressPieces.NetherStalkRoom.createPiece(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
		}

		return structurenetherbridgepieces$piece;
	}

	public static class Corridor extends JungleFortressPieces.Piece
	{

		public Corridor(int p_i45615_1_, Random rand, MutableBoundingBox p_i45615_3_, Direction p_i45615_4_)
		{
			super(StructurePieces.JFSCLT, p_i45615_1_);
			this.setCoordBaseMode(p_i45615_4_);
			this.boundingBox = p_i45615_3_;
		}


		public Corridor(TemplateManager p_i50272_1_, CompoundNBT p_i50272_2_)
		{
			super(StructurePieces.JFSCLT, p_i50272_2_);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentX((JungleFortressPieces.Start) componentIn, listIn, rand, 0, 1, true);
		}


		public static JungleFortressPieces.Corridor createPiece(List<StructurePiece> p_175879_0_, Random p_175879_1_, int p_175879_2_, int p_175879_3_, int p_175879_4_, Direction p_175879_5_, int p_175879_6_)
		{
			MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(p_175879_2_, p_175879_3_, p_175879_4_, -1, 0, 0, 5, 7, 5, p_175879_5_);
			return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(p_175879_0_, mutableBoundingBox) == null ? new JungleFortressPieces.Corridor(p_175879_6_, p_175879_1_, mutableBoundingBox, p_175879_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 4, 5, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true)), random);
			BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true)), random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 3, 1, 4, 4, 1, iblockstate1, iblockstate1, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 3, 3, 4, 4, 3, iblockstate1, iblockstate1, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 4, 3, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 3, 4, 1, 4, 4, iblockstate, iblockstate, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 3, 3, 4, 3, 4, 4, iblockstate, iblockstate, false, random);

			if (RSConfig.lootChestsJF && random.nextInt(9) == 0 && structureBoundingBoxIn.isVecInside(new BlockPos(this.getXWithOffset(3, 3), this.getYWithOffset(2), this.getZWithOffset(3, 3))))
			{
				this.generateChest(world, structureBoundingBoxIn, random, 3, 2, 3, pickRandomLoot(random));
			}

			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

			for (int i = 0; i <= 4; ++i)
			{
				for (int j = 0; j <= 4; ++j)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, structureBoundingBoxIn, random);
				}
			}

			attemptToAddVines(world, p_225577_2_, random, structureBoundingBoxIn);
			return true;
		}
	}

	public static class Corridor2 extends JungleFortressPieces.Piece
	{

		public Corridor2(int p_i45613_1_, Random rand, MutableBoundingBox p_i45613_3_, Direction p_i45613_4_)
		{
			super(StructurePieces.JFSCRT, p_i45613_1_);
			this.setCoordBaseMode(p_i45613_4_);
			this.boundingBox = p_i45613_3_;
		}


		public Corridor2(TemplateManager p_i50266_1_, CompoundNBT p_i50266_2_)
		{
			super(StructurePieces.JFSCRT, p_i50266_2_);
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentZ((JungleFortressPieces.Start) componentIn, listIn, rand, 0, 1, true);
		}


		public static JungleFortressPieces.Corridor2 createPiece(List<StructurePiece> p_175876_0_, Random p_175876_1_, int p_175876_2_, int p_175876_3_, int p_175876_4_, Direction p_175876_5_, int p_175876_6_)
		{
			MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(p_175876_2_, p_175876_3_, p_175876_4_, -1, 0, 0, 5, 7, 5, p_175876_5_);
			return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(p_175876_0_, mutableBoundingBox) == null ? new JungleFortressPieces.Corridor2(p_175876_6_, p_175876_1_, mutableBoundingBox, p_175876_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 4, 5, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState(), random).with(FourWayBlock.WEST, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true));
			BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState(), random).with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true));
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 3, 1, 0, 4, 1, iblockstate1, iblockstate1, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 3, 3, 0, 4, 3, iblockstate1, iblockstate1, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 2, 0, 4, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 2, 4, 4, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 3, 4, 1, 4, 4, iblockstate, iblockstate, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 3, 3, 4, 3, 4, 4, iblockstate, iblockstate, false, random);

			if (RSConfig.lootChestsJF && random.nextInt(9) == 0 && structureBoundingBoxIn.isVecInside(new BlockPos(this.getXWithOffset(1, 3), this.getYWithOffset(2), this.getZWithOffset(1, 3))))
			{
				this.generateChest(world, structureBoundingBoxIn, random, 1, 2, 3, pickRandomLoot(random));
			}

			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

			for (int i = 0; i <= 4; ++i)
			{
				for (int j = 0; j <= 4; ++j)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, structureBoundingBoxIn, random);
				}
			}
			
			attemptToAddVines(world, p_225577_2_, random, structureBoundingBoxIn);
			return true;
		}
	}

	public static class Corridor3 extends JungleFortressPieces.Piece
	{
		public Corridor3(int p_i50280_1_, MutableBoundingBox p_i50280_2_, Direction p_i50280_3_)
		{
			super(StructurePieces.JFCCS, p_i50280_1_);
			this.setCoordBaseMode(p_i50280_3_);
			this.boundingBox = p_i50280_2_;
		}


		public Corridor3(TemplateManager p_i50281_1_, CompoundNBT p_i50281_2_)
		{
			super(StructurePieces.JFCCS, p_i50281_2_);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentNormal((JungleFortressPieces.Start) componentIn, listIn, rand, 1, 0, true);
		}


		public static JungleFortressPieces.Corridor3 createPiece(List<StructurePiece> p_175883_0_, Random p_175883_1_, int p_175883_2_, int p_175883_3_, int p_175883_4_, Direction p_175883_5_, int p_175883_6_)
		{
			MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(p_175883_2_, p_175883_3_, p_175883_4_, -1, -7, 0, 5, 14, 10, p_175883_5_);
			return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(p_175883_0_, mutableBoundingBox) == null ? new JungleFortressPieces.Corridor3(p_175883_6_, mutableBoundingBox, p_175883_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.SOUTH), random);
			BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true)), random);

			for (int i = 0; i <= 9; ++i)
			{
				int j = Math.max(1, 7 - i);
				int k = Math.min(Math.max(j + 5, 14 - i), 13);
				int l = i;
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 0, i, 4, j, i, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, j + 1, i, 3, k - 1, i, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);

				if (i <= 6)
				{
					this.setBlockState(world, iblockstate, 1, j + 1, i, structureBoundingBoxIn);
					this.setBlockState(world, iblockstate, 2, j + 1, i, structureBoundingBoxIn);
					this.setBlockState(world, iblockstate, 3, j + 1, i, structureBoundingBoxIn);
				}

				this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, k, i, 4, k, i, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, j + 1, i, 0, k - 1, i, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, j + 1, i, 4, k - 1, i, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

				if ((i & 1) == 0)
				{
					this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, j + 2, i, 0, j + 3, i, iblockstate1, iblockstate1, false, random);
					this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, j + 2, i, 4, j + 3, i, iblockstate1, iblockstate1, false, random);
				}

				for (int i1 = 0; i1 <= 4; ++i1)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i1, -1, l, structureBoundingBoxIn, random);
				}
			}

			attemptToAddVines(world, p_225577_2_, random, structureBoundingBoxIn);
			return true;
		}
	}

	public static class Corridor4 extends JungleFortressPieces.Piece
	{
		public Corridor4(int p_i50277_1_, MutableBoundingBox p_i50277_2_, Direction p_i50277_3_)
		{
			super(StructurePieces.JFCTB, p_i50277_1_);
			this.setCoordBaseMode(p_i50277_3_);
			this.boundingBox = p_i50277_2_;
		}


		public Corridor4(TemplateManager p_i50278_1_, CompoundNBT p_i50278_2_)
		{
			super(StructurePieces.JFCTB, p_i50278_2_);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			int i = 1;
			Direction enumfacing = this.getCoordBaseMode();

			if (enumfacing == Direction.WEST || enumfacing == Direction.NORTH)
			{
				i = 5;
			}

			this.getNextComponentX((JungleFortressPieces.Start) componentIn, listIn, rand, 0, i, rand.nextInt(8) > 0);
			this.getNextComponentZ((JungleFortressPieces.Start) componentIn, listIn, rand, 0, i, rand.nextInt(8) > 0);
		}


		public static JungleFortressPieces.Corridor4 createPiece(List<StructurePiece> p_175880_0_, Random p_175880_1_, int p_175880_2_, int p_175880_3_, int p_175880_4_, Direction p_175880_5_, int p_175880_6_)
		{
			MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(p_175880_2_, p_175880_3_, p_175880_4_, -3, 0, 0, 9, 7, 9, p_175880_5_);
			return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(p_175880_0_, mutableBoundingBox) == null ? new JungleFortressPieces.Corridor4(p_175880_6_, mutableBoundingBox, p_175880_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true)), random);
			BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true)), random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 0, 0, 8, 1, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 8, 5, 8, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 6, 0, 8, 6, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 2, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 6, 2, 0, 8, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 3, 0, 1, 4, 0, iblockstate1, iblockstate1, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 7, 3, 0, 7, 4, 0, iblockstate1, iblockstate1, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 4, 8, 2, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 1, 4, 2, 2, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 6, 1, 4, 7, 2, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 3, 8, 7, 3, 8, iblockstate1, iblockstate1, false, random);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.EAST, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true)), random), 0, 3, 8, structureBoundingBoxIn);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true)), random), 8, 3, 8, structureBoundingBoxIn);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 3, 6, 0, 3, 7, iblockstate, iblockstate, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 8, 3, 6, 8, 3, 7, iblockstate, iblockstate, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 3, 4, 0, 5, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 8, 3, 4, 8, 5, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 3, 5, 2, 5, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 6, 3, 5, 7, 5, 5, Blocks.NETHER_BRICKS.getDefaultState(), getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 4, 5, 1, 5, 5, iblockstate1, iblockstate1, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 7, 4, 5, 7, 5, 5, iblockstate1, iblockstate1, false, random);

			for (int i = 0; i <= 5; ++i)
			{
				for (int j = 0; j <= 8; ++j)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), j, -1, i, structureBoundingBoxIn, random);
				}
			}

			attemptToAddVines(world, p_225577_2_, random, structureBoundingBoxIn);
			return true;
		}
	}

	public static class Corridor5 extends JungleFortressPieces.Piece
	{
		public Corridor5(int p_i50268_1_, MutableBoundingBox p_i50268_2_, Direction p_i50268_3_)
		{
			super(StructurePieces.JFSC, p_i50268_1_);
			this.setCoordBaseMode(p_i50268_3_);
			this.boundingBox = p_i50268_2_;
		}


		public Corridor5(TemplateManager p_i50269_1_, CompoundNBT p_i50269_2_)
		{
			super(StructurePieces.JFSC, p_i50269_2_);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentNormal((JungleFortressPieces.Start) componentIn, listIn, rand, 1, 0, true);
		}


		public static JungleFortressPieces.Corridor5 createPiece(List<StructurePiece> p_175877_0_, Random p_175877_1_, int p_175877_2_, int p_175877_3_, int p_175877_4_, Direction p_175877_5_, int p_175877_6_)
		{
			MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(p_175877_2_, p_175877_3_, p_175877_4_, -1, 0, 0, 5, 7, 5, p_175877_5_);
			return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(p_175877_0_, mutableBoundingBox) == null ? new JungleFortressPieces.Corridor5(p_175877_6_, mutableBoundingBox, p_175877_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 4, 5, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true)), random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 3, 1, 0, 4, 1, iblockstate, iblockstate, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 3, 3, 0, 4, 3, iblockstate, iblockstate, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 3, 1, 4, 4, 1, iblockstate, iblockstate, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 3, 3, 4, 4, 3, iblockstate, iblockstate, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

			for (int i = 0; i <= 4; ++i)
			{
				for (int j = 0; j <= 4; ++j)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, structureBoundingBoxIn, random);
				}
			}

			attemptToAddVines(world, p_225577_2_, random, structureBoundingBoxIn);
			return true;
		}
	}

	public static class Crossing extends JungleFortressPieces.Piece
	{
		public Crossing(int p_i50258_1_, MutableBoundingBox p_i50258_2_, Direction p_i50258_3_)
		{
			super(StructurePieces.JFRC, p_i50258_1_);
			this.setCoordBaseMode(p_i50258_3_);
			this.boundingBox = p_i50258_2_;
		}


		public Crossing(TemplateManager p_i50259_1_, CompoundNBT p_i50259_2_)
		{
			super(StructurePieces.JFRC, p_i50259_2_);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentNormal((JungleFortressPieces.Start) componentIn, listIn, rand, 2, 0, false);
			this.getNextComponentX((JungleFortressPieces.Start) componentIn, listIn, rand, 0, 2, false);
			this.getNextComponentZ((JungleFortressPieces.Start) componentIn, listIn, rand, 0, 2, false);
		}


		public static JungleFortressPieces.Crossing createPiece(List<StructurePiece> p_175873_0_, Random p_175873_1_, int p_175873_2_, int p_175873_3_, int p_175873_4_, Direction p_175873_5_, int p_175873_6_)
		{
			MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(p_175873_2_, p_175873_3_, p_175873_4_, -2, 0, 0, 7, 9, 7, p_175873_5_);
			return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(p_175873_0_, mutableBoundingBox) == null ? new JungleFortressPieces.Crossing(p_175873_6_, mutableBoundingBox, p_175873_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 6, 7, 6, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 1, 6, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 6, 1, 6, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 2, 0, 6, 6, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 2, 6, 6, 6, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 0, 6, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 5, 0, 6, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 6, 2, 0, 6, 6, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 6, 2, 5, 6, 6, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true)), random);
			BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true)), random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 6, 0, 4, 6, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 5, 0, 4, 5, 0, iblockstate, iblockstate, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 6, 6, 4, 6, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 5, 6, 4, 5, 6, iblockstate, iblockstate, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 6, 2, 0, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 5, 2, 0, 5, 4, iblockstate1, iblockstate1, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 6, 6, 2, 6, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 6, 5, 2, 6, 5, 4, iblockstate1, iblockstate1, false, random);

			for (int i = 0; i <= 6; ++i)
			{
				for (int j = 0; j <= 6; ++j)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, structureBoundingBoxIn, random);
				}
			}

			attemptToAddVines(world, p_225577_2_, random, structureBoundingBoxIn);
			return true;
		}
	}

	public static class Crossing2 extends JungleFortressPieces.Piece
	{
		public Crossing2(int p_i50273_1_, MutableBoundingBox p_i50273_2_, Direction p_i50273_3_)
		{
			super(StructurePieces.JFSCSC, p_i50273_1_);
			this.setCoordBaseMode(p_i50273_3_);
			this.boundingBox = p_i50273_2_;
		}


		public Crossing2(TemplateManager p_i50274_1_, CompoundNBT p_i50274_2_)
		{
			super(StructurePieces.JFSCSC, p_i50274_2_);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentNormal((JungleFortressPieces.Start) componentIn, listIn, rand, 1, 0, true);
			this.getNextComponentX((JungleFortressPieces.Start) componentIn, listIn, rand, 0, 1, true);
			this.getNextComponentZ((JungleFortressPieces.Start) componentIn, listIn, rand, 0, 1, true);
		}


		public static JungleFortressPieces.Crossing2 createPiece(List<StructurePiece> p_175878_0_, Random p_175878_1_, int p_175878_2_, int p_175878_3_, int p_175878_4_, Direction p_175878_5_, int p_175878_6_)
		{
			MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(p_175878_2_, p_175878_3_, p_175878_4_, -1, 0, 0, 5, 7, 5, p_175878_5_);
			return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(p_175878_0_, mutableBoundingBox) == null ? new JungleFortressPieces.Crossing2(p_175878_6_, mutableBoundingBox, p_175878_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 4, 5, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 2, 0, 4, 5, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 4, 0, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 2, 4, 4, 5, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

			for (int i = 0; i <= 4; ++i)
			{
				for (int j = 0; j <= 4; ++j)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, structureBoundingBoxIn, random);
				}
			}

			attemptToAddVines(world, p_225577_2_, random, structureBoundingBoxIn);
			return true;
		}
	}

	public static class Crossing3 extends JungleFortressPieces.Piece
	{

		protected Crossing3(IStructurePieceType p_i50287_1_, CompoundNBT p_i50287_2_)
		{
			super(p_i50287_1_, p_i50287_2_);
		}


		public Crossing3(TemplateManager p_i50288_1_, CompoundNBT p_i50288_2_)
		{
			this(StructurePieces.JFCR, p_i50288_2_);
		}


		public Crossing3(int p_i50286_1_, MutableBoundingBox p_i50286_2_, Direction p_i50286_3_)
		{
			super(StructurePieces.JFCR, p_i50286_1_);
			this.setCoordBaseMode(p_i50286_3_);
			this.boundingBox = p_i50286_2_;
		}


		protected Crossing3(Random p_i2042_1_, int p_i2042_2_, int p_i2042_3_)
		{
			super(StructurePieces.JFCR, 0);
			this.setCoordBaseMode(Direction.Plane.HORIZONTAL.random(p_i2042_1_));
			if (this.getCoordBaseMode().getAxis() == Direction.Axis.Z)
			{
				this.boundingBox = new MutableBoundingBox(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1);
			}
			else
			{
				this.boundingBox = new MutableBoundingBox(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1);
			}

		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentNormal((JungleFortressPieces.Start) componentIn, listIn, rand, 8, 3, false);
			this.getNextComponentX((JungleFortressPieces.Start) componentIn, listIn, rand, 3, 8, false);
			this.getNextComponentZ((JungleFortressPieces.Start) componentIn, listIn, rand, 3, 8, false);
		}


		public static JungleFortressPieces.Crossing3 createPiece(List<StructurePiece> p_175885_0_, Random p_175885_1_, int p_175885_2_, int p_175885_3_, int p_175885_4_, Direction p_175885_5_, int p_175885_6_)
		{
			MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(p_175885_2_, p_175885_3_, p_175885_4_, -8, -3, 0, 19, 10, 19, p_175885_5_);
			return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(p_175885_0_, mutableBoundingBox) == null ? new JungleFortressPieces.Crossing3(p_175885_6_, mutableBoundingBox, p_175885_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos chunkPos)
		{
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 7, 3, 0, 11, 4, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 3, 7, 18, 4, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 8, 5, 0, 10, 7, 18, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 5, 8, 18, 7, 10, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 7, 5, 0, 7, 5, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 7, 5, 11, 7, 5, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 11, 5, 0, 11, 5, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 11, 5, 11, 11, 5, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 5, 7, 7, 5, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 11, 5, 7, 18, 5, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 5, 11, 7, 5, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 11, 5, 11, 18, 5, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 7, 2, 0, 11, 2, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 7, 2, 13, 11, 2, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 7, 0, 0, 11, 1, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 7, 0, 15, 11, 1, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

			for (int i = 7; i <= 11; ++i)
			{
				for (int j = 0; j <= 2; ++j)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, structureBoundingBoxIn, random);
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, 18 - j, structureBoundingBoxIn, random);
				}
			}

			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 7, 5, 2, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 13, 2, 7, 18, 2, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 0, 7, 3, 1, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 15, 0, 7, 18, 1, 11, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

			for (int k = 0; k <= 2; ++k)
			{
				for (int l = 7; l <= 11; ++l)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), k, -1, l, structureBoundingBoxIn, random);
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), 18 - k, -1, l, structureBoundingBoxIn, random);
				}
			}

			attemptToAddVines(world, p_225577_2_, random, structureBoundingBoxIn);
			return true;
		}
	}

	public static class End extends JungleFortressPieces.Piece
	{
		private int fillSeed;


		public End(int p_i45621_1_, Random p_i45621_2_, MutableBoundingBox p_i45621_3_, Direction p_i45621_4_)
		{
			super(StructurePieces.JFEF, p_i45621_1_);
			this.setCoordBaseMode(p_i45621_4_);
			this.boundingBox = p_i45621_3_;
			this.fillSeed = p_i45621_2_.nextInt();
		}


		public End(TemplateManager p_i50285_1_, CompoundNBT p_i50285_2_)
		{
			super(StructurePieces.JFEF, p_i50285_2_);
			this.fillSeed = p_i50285_2_.getInt("Seed");
		}


		public static JungleFortressPieces.End createPiece(List<StructurePiece> p_175884_0_, Random p_175884_1_, int p_175884_2_, int p_175884_3_, int p_175884_4_, Direction p_175884_5_, int p_175884_6_)
		{
			MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(p_175884_2_, p_175884_3_, p_175884_4_, -1, -3, 0, 5, 10, 8, p_175884_5_);
			return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(p_175884_0_, mutableBoundingBox) == null ? new JungleFortressPieces.End(p_175884_6_, p_175884_1_, mutableBoundingBox, p_175884_5_) : null;
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
			tagCompound.putInt("Seed", this.fillSeed);
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos chunkPos)
		{
			Random seededRandom = new Random(this.fillSeed);

			for (int i = 0; i <= 4; ++i)
			{
				for (int j = 3; j <= 4; ++j)
				{
					int k = seededRandom.nextInt(8);
					this.fillWithRandomBlocks(world, structureBoundingBoxIn, i, j, 0, i, j, k, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
				}
			}

			int l = seededRandom.nextInt(8);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 5, 0, 0, 5, l, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			l = seededRandom.nextInt(8);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 5, 0, 4, 5, l, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

			for (l = 0; l <= 4; ++l)
			{
				int i1 = seededRandom.nextInt(5);
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, l, 2, 0, l, 2, i1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			}

			for (l = 0; l <= 4; ++l)
			{
				for (int j1 = 0; j1 <= 1; ++j1)
				{
					int k1 = seededRandom.nextInt(3);
					this.fillWithRandomBlocks(world, structureBoundingBoxIn, l, j1, 0, l, j1, k1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
				}
			}

			return true;
		}
	}

	public static class Entrance extends JungleFortressPieces.Piece
	{
		public Entrance(int p_i45617_1_, Random rand, MutableBoundingBox p_i45617_3_, Direction p_i45617_4_)
		{
			super(StructurePieces.JFCE, p_i45617_1_);
			this.setCoordBaseMode(p_i45617_4_);
			this.boundingBox = p_i45617_3_;
		}


		public Entrance(TemplateManager p_i50276_1_, CompoundNBT p_i50276_2_)
		{
			super(StructurePieces.JFCE, p_i50276_2_);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentNormal((JungleFortressPieces.Start) componentIn, listIn, rand, 5, 3, true);
		}


		public static JungleFortressPieces.Entrance createPiece(List<StructurePiece> p_175881_0_, Random p_175881_1_, int p_175881_2_, int p_175881_3_, int p_175881_4_, Direction p_175881_5_, int p_175881_6_)
		{
			MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(p_175881_2_, p_175881_3_, p_175881_4_, -5, -3, 0, 13, 14, 13, p_175881_5_);
			return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(p_175881_0_, mutableBoundingBox) == null ? new JungleFortressPieces.Entrance(p_175881_6_, p_175881_1_, mutableBoundingBox, p_175881_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos chunkPos)
		{
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 3, 0, 12, 4, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 5, 0, 12, 13, 12, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 5, 0, 1, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 11, 5, 0, 12, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 5, 11, 4, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 8, 5, 11, 10, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 9, 11, 7, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 5, 0, 4, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 8, 5, 0, 10, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 9, 0, 7, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 11, 2, 10, 12, 10, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 8, 0, 7, 8, 0, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState(), random), getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState(), random), false, random);
			BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true)), random);
			BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true)), random);

			for (int i = 1; i <= 11; i += 2)
			{
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, i, 10, 0, i, 11, 0, iblockstate, iblockstate, false, random);
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, i, 10, 12, i, 11, 12, iblockstate, iblockstate, false, random);
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 10, i, 0, 11, i, iblockstate1, iblockstate1, false, random);
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, 12, 10, i, 12, 11, i, iblockstate1, iblockstate1, false, random);
				this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), i, 13, 0, structureBoundingBoxIn);
				this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), i, 13, 12, structureBoundingBoxIn);
				this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 0, 13, i, structureBoundingBoxIn);
				this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 12, 13, i, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate, i + 1, 13, 0, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate, i + 1, 13, 12, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate1, 0, 13, i + 1, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate1, 12, 13, i + 1, structureBoundingBoxIn);
			}

			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true)), random), 0, 13, 0, structureBoundingBoxIn);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.SOUTH, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true)), random), 0, 13, 12, structureBoundingBoxIn);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.SOUTH, Boolean.valueOf(true)).with(FourWayBlock.WEST, Boolean.valueOf(true)), random), 12, 13, 12, structureBoundingBoxIn);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.WEST, Boolean.valueOf(true)), random), 12, 13, 0, structureBoundingBoxIn);

			for (int k = 3; k <= 9; k += 2)
			{
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 7, k, 1, 8, k, iblockstate.with(FourWayBlock.WEST, Boolean.valueOf(true)), iblockstate.with(FourWayBlock.WEST, Boolean.valueOf(true)), false, random);
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, 11, 7, k, 11, 8, k, iblockstate.with(FourWayBlock.EAST, Boolean.valueOf(true)), iblockstate.with(FourWayBlock.EAST, Boolean.valueOf(true)), false, random);
			}

			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 2, 0, 8, 2, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 4, 12, 2, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 0, 0, 8, 1, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 0, 9, 8, 1, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 0, 4, 3, 1, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 9, 0, 4, 12, 1, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

			for (int l = 4; l <= 8; ++l)
			{
				for (int j = 0; j <= 2; ++j)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), l, -1, j, structureBoundingBoxIn, random);
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), l, -1, 12 - j, structureBoundingBoxIn, random);
				}
			}

			for (int i1 = 0; i1 <= 2; ++i1)
			{
				for (int j1 = 4; j1 <= 8; ++j1)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i1, -1, j1, structureBoundingBoxIn, random);
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), 12 - i1, -1, j1, structureBoundingBoxIn, random);
				}
			}

			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 5, 5, 7, 5, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 6, 1, 6, 6, 4, 6, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 6, 0, 6, structureBoundingBoxIn);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.LAVA.getDefaultState(), random), 6, 5, 6, structureBoundingBoxIn);
			BlockPos blockpos = new BlockPos(this.getXWithOffset(6, 6), this.getYWithOffset(5), this.getZWithOffset(6, 6));

			if (RSConfig.lootChestsJF)
			{
				this.generateChest(world, structureBoundingBoxIn, random, 6, 5, 8, pickRandomLoot(random));
			}

			if (structureBoundingBoxIn.isVecInside(blockpos))
			{
				world.getPendingFluidTicks().scheduleTick(blockpos, Fluids.LAVA, 0);
			}

			attemptToAddVines(world, p_225577_2_, random, structureBoundingBoxIn);
			return true;
		}
	}

	public static class NetherStalkRoom extends JungleFortressPieces.Piece
	{
		public NetherStalkRoom(int p_i50264_1_, MutableBoundingBox p_i50264_2_, Direction p_i50264_3_)
		{
			super(StructurePieces.JFCSR, p_i50264_1_);
			this.setCoordBaseMode(p_i50264_3_);
			this.boundingBox = p_i50264_2_;
		}


		public NetherStalkRoom(TemplateManager p_i50265_1_, CompoundNBT p_i50265_2_)
		{
			super(StructurePieces.JFCSR, p_i50265_2_);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentNormal((JungleFortressPieces.Start) componentIn, listIn, rand, 5, 3, true);
			this.getNextComponentNormal((JungleFortressPieces.Start) componentIn, listIn, rand, 5, 11, true);
		}


		public static JungleFortressPieces.NetherStalkRoom createPiece(List<StructurePiece> p_175875_0_, Random p_175875_1_, int p_175875_2_, int p_175875_3_, int p_175875_4_, Direction p_175875_5_, int p_175875_6_)
		{
			MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(p_175875_2_, p_175875_3_, p_175875_4_, -5, -3, 0, 13, 14, 13, p_175875_5_);
			return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(p_175875_0_, mutableBoundingBox) == null ? new JungleFortressPieces.NetherStalkRoom(p_175875_6_, mutableBoundingBox, p_175875_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos chunkPos)
		{
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 3, 0, 12, 4, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 5, 0, 12, 13, 12, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 5, 0, 1, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 11, 5, 0, 12, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 5, 11, 4, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 8, 5, 11, 10, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 9, 11, 7, 12, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 5, 0, 4, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 8, 5, 0, 10, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 9, 0, 7, 12, 1, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 11, 2, 10, 12, 10, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true)), random);
			BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true)), random);
			BlockState iblockstate2 = iblockstate1.with(FourWayBlock.WEST, Boolean.valueOf(true));
			BlockState iblockstate3 = iblockstate1.with(FourWayBlock.EAST, Boolean.valueOf(true));

			for (int i = 1; i <= 11; i += 2)
			{
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, i, 10, 0, i, 11, 0, iblockstate, iblockstate, false, random);
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, i, 10, 12, i, 11, 12, iblockstate, iblockstate, false, random);
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 10, i, 0, 11, i, iblockstate1, iblockstate1, false, random);
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, 12, 10, i, 12, 11, i, iblockstate1, iblockstate1, false, random);
				this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), i, 13, 0, structureBoundingBoxIn);
				this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), i, 13, 12, structureBoundingBoxIn);
				this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 0, 13, i, structureBoundingBoxIn);
				this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 12, 13, i, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate, i + 1, 13, 0, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate, i + 1, 13, 12, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate1, 0, 13, i + 1, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate1, 12, 13, i + 1, structureBoundingBoxIn);
			}

			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true)), random), 0, 13, 0, structureBoundingBoxIn);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.SOUTH, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true)), random), 0, 13, 12, structureBoundingBoxIn);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.SOUTH, Boolean.valueOf(true)).with(FourWayBlock.WEST, Boolean.valueOf(true)), random), 12, 13, 12, structureBoundingBoxIn);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.WEST, Boolean.valueOf(true)), random), 12, 13, 0, structureBoundingBoxIn);

			for (int j1 = 3; j1 <= 9; j1 += 2)
			{
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 7, j1, 1, 8, j1, iblockstate2, iblockstate2, false, random);
				this.fillWithRandomBlocks(world, structureBoundingBoxIn, 11, 7, j1, 11, 8, j1, iblockstate3, iblockstate3, false, random);
			}

			BlockState iblockstate4 = getStoneVariantBlockState(Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH), random);

			for (int j = 0; j <= 6; ++j)
			{
				int k = j + 4;

				for (int l = 5; l <= 7; ++l)
				{
					this.setBlockState(world, iblockstate4, l, 5 + j, k, structureBoundingBoxIn);
				}

				if (k >= 5 && k <= 8)
				{
					this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 5, k, 7, j + 4, k, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
				}
				else if (k >= 9 && k <= 10)
				{
					this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 8, k, 7, j + 4, k, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
				}

				if (j >= 1)
				{
					this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 6 + j, k, 7, 9 + j, k, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
				}
			}

			for (int k1 = 5; k1 <= 7; ++k1)
			{
				this.setBlockState(world, iblockstate4, k1, 12, 11, structureBoundingBoxIn);
			}

			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 6, 7, 5, 7, 7, iblockstate, iblockstate, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 7, 6, 7, 7, 7, 7, iblockstate, iblockstate, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 13, 12, 7, 13, 12, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 5, 2, 3, 5, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 5, 9, 3, 5, 10, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 5, 4, 2, 5, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 9, 5, 2, 10, 5, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 9, 5, 9, 10, 5, 10, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 10, 5, 4, 10, 5, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			BlockState iblockstate5 = iblockstate4.with(StairsBlock.FACING, Direction.EAST);
			BlockState iblockstate6 = iblockstate4.with(StairsBlock.FACING, Direction.WEST);
			this.setBlockState(world, iblockstate6, 4, 5, 2, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate6, 4, 5, 3, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate6, 4, 5, 9, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate6, 4, 5, 10, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate5, 8, 5, 2, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate5, 8, 5, 3, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate5, 8, 5, 9, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate5, 8, 5, 10, structureBoundingBoxIn);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 3, 4, 4, 4, 4, 8, Blocks.SOUL_SAND.getDefaultState(), Blocks.SOUL_SAND.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 8, 4, 4, 9, 4, 8, Blocks.SOUL_SAND.getDefaultState(), Blocks.SOUL_SAND.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 3, 5, 4, 4, 5, 8, Blocks.NETHER_WART.getDefaultState(), Blocks.NETHER_WART.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 8, 5, 4, 9, 5, 8, Blocks.NETHER_WART.getDefaultState(), Blocks.NETHER_WART.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 2, 0, 8, 2, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 4, 12, 2, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 0, 0, 8, 1, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 0, 9, 8, 1, 12, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 0, 4, 3, 1, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 9, 0, 4, 12, 1, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

			for (int l1 = 4; l1 <= 8; ++l1)
			{
				for (int i1 = 0; i1 <= 2; ++i1)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), l1, -1, i1, structureBoundingBoxIn, random);
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), l1, -1, 12 - i1, structureBoundingBoxIn, random);
				}
			}

			for (int i2 = 0; i2 <= 2; ++i2)
			{
				for (int j2 = 4; j2 <= 8; ++j2)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i2, -1, j2, structureBoundingBoxIn, random);
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), 12 - i2, -1, j2, structureBoundingBoxIn, random);
				}
			}

			attemptToAddVines(world, p_225577_2_, random, structureBoundingBoxIn);
			return true;
		}
	}

	abstract static class Piece extends StructurePiece
	{
		protected Piece(IStructurePieceType p_i50260_1_, int p_i50260_2_)
		{
			super(p_i50260_1_, p_i50260_2_);
		}


		public Piece(IStructurePieceType p_i50261_1_, CompoundNBT p_i50261_2_)
		{
			super(p_i50261_1_, p_i50261_2_);
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
		}


		private int getTotalWeight(List<JungleFortressPieces.PieceWeight> p_74960_1_)
		{
			boolean flag = false;
			int i = 0;

			for (JungleFortressPieces.PieceWeight structurenetherbridgepieces$pieceweight : p_74960_1_)
			{
				if (structurenetherbridgepieces$pieceweight.maxPlaceCount > 0 && structurenetherbridgepieces$pieceweight.placeCount < structurenetherbridgepieces$pieceweight.maxPlaceCount)
				{
					flag = true;
				}

				i += structurenetherbridgepieces$pieceweight.weight;
			}

			return flag ? i : -1;
		}


		private JungleFortressPieces.Piece generatePiece(JungleFortressPieces.Start startPiece, List<JungleFortressPieces.PieceWeight> p_175871_2_, List<StructurePiece> p_175871_3_, Random p_175871_4_, int p_175871_5_, int p_175871_6_, int p_175871_7_, Direction p_175871_8_, int p_175871_9_)
		{
			int i = this.getTotalWeight(p_175871_2_);
			boolean flag = i > 0 && p_175871_9_ <= 30;
			int j = 0;

			while (j < 5 && flag)
			{
				++j;
				int k = p_175871_4_.nextInt(i);

				for (JungleFortressPieces.PieceWeight structurenetherbridgepieces$pieceweight : p_175871_2_)
				{
					k -= structurenetherbridgepieces$pieceweight.weight;

					if (k < 0)
					{
						if (!structurenetherbridgepieces$pieceweight.doPlace(p_175871_9_) || structurenetherbridgepieces$pieceweight == startPiece.theNetherBridgePieceWeight && !structurenetherbridgepieces$pieceweight.allowInRow)
						{
							break;
						}

						JungleFortressPieces.Piece structurenetherbridgepieces$piece = JungleFortressPieces.findAndCreateBridgePieceFactory(structurenetherbridgepieces$pieceweight, p_175871_3_, p_175871_4_, p_175871_5_, p_175871_6_, p_175871_7_, p_175871_8_, p_175871_9_);

						if (structurenetherbridgepieces$piece != null)
						{
							++structurenetherbridgepieces$pieceweight.placeCount;
							startPiece.theNetherBridgePieceWeight = structurenetherbridgepieces$pieceweight;

							if (!structurenetherbridgepieces$pieceweight.isValid())
							{
								p_175871_2_.remove(structurenetherbridgepieces$pieceweight);
							}

							return structurenetherbridgepieces$piece;
						}
					}
				}
			}

			return JungleFortressPieces.End.createPiece(p_175871_3_, p_175871_4_, p_175871_5_, p_175871_6_, p_175871_7_, p_175871_8_, p_175871_9_);
		}


		private StructurePiece generateAndAddPiece(JungleFortressPieces.Start startPiece, List<StructurePiece> p_175870_2_, Random p_175870_3_, int p_175870_4_, int p_175870_5_, int p_175870_6_, @Nullable Direction p_175870_7_, int p_175870_8_, boolean p_175870_9_)
		{
			if (Math.abs(p_175870_4_ - startPiece.getBoundingBox().minX) <= 112 && Math.abs(p_175870_6_ - startPiece.getBoundingBox().minZ) <= 112)
			{
				List<JungleFortressPieces.PieceWeight> list = startPiece.primaryWeights;

				if (p_175870_9_)
				{
					list = startPiece.secondaryWeights;
				}

				StructurePiece StructurePiece = this.generatePiece(startPiece, list, p_175870_2_, p_175870_3_, p_175870_4_, p_175870_5_, p_175870_6_, p_175870_7_, p_175870_8_ + 1);

				if (StructurePiece != null)
				{
					p_175870_2_.add(StructurePiece);
					startPiece.pendingChildren.add(StructurePiece);
				}

				return StructurePiece;
			}
			else
			{
				return JungleFortressPieces.End.createPiece(p_175870_2_, p_175870_3_, p_175870_4_, p_175870_5_, p_175870_6_, p_175870_7_, p_175870_8_);
			}
		}


		@Nullable
		protected StructurePiece getNextComponentNormal(JungleFortressPieces.Start p_74963_1_, List<StructurePiece> p_74963_2_, Random p_74963_3_, int p_74963_4_, int p_74963_5_, boolean p_74963_6_)
		{
			Direction enumfacing = this.getCoordBaseMode();

			if (enumfacing != null)
			{
				switch (enumfacing)
				{
					case NORTH:
						return this.generateAndAddPiece(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.minX + p_74963_4_, this.boundingBox.minY + p_74963_5_, this.boundingBox.minZ - 1, enumfacing, this.getComponentType(), p_74963_6_);

					case SOUTH:
						return this.generateAndAddPiece(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.minX + p_74963_4_, this.boundingBox.minY + p_74963_5_, this.boundingBox.maxZ + 1, enumfacing, this.getComponentType(), p_74963_6_);

					case WEST:
						return this.generateAndAddPiece(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74963_5_, this.boundingBox.minZ + p_74963_4_, enumfacing, this.getComponentType(), p_74963_6_);

					case EAST:
						return this.generateAndAddPiece(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74963_5_, this.boundingBox.minZ + p_74963_4_, enumfacing, this.getComponentType(), p_74963_6_);

					default:
						break;
				}
			}

			return null;
		}


		@Nullable
		protected StructurePiece getNextComponentX(JungleFortressPieces.Start p_74961_1_, List<StructurePiece> p_74961_2_, Random p_74961_3_, int p_74961_4_, int p_74961_5_, boolean p_74961_6_)
		{
			Direction enumfacing = this.getCoordBaseMode();

			if (enumfacing != null)
			{
				switch (enumfacing)
				{
					case NORTH:
						return this.generateAndAddPiece(p_74961_1_, p_74961_2_, p_74961_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74961_4_, this.boundingBox.minZ + p_74961_5_, Direction.WEST, this.getComponentType(), p_74961_6_);

					case SOUTH:
						return this.generateAndAddPiece(p_74961_1_, p_74961_2_, p_74961_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74961_4_, this.boundingBox.minZ + p_74961_5_, Direction.WEST, this.getComponentType(), p_74961_6_);

					case WEST:
						return this.generateAndAddPiece(p_74961_1_, p_74961_2_, p_74961_3_, this.boundingBox.minX + p_74961_5_, this.boundingBox.minY + p_74961_4_, this.boundingBox.minZ - 1, Direction.NORTH, this.getComponentType(), p_74961_6_);

					case EAST:
						return this.generateAndAddPiece(p_74961_1_, p_74961_2_, p_74961_3_, this.boundingBox.minX + p_74961_5_, this.boundingBox.minY + p_74961_4_, this.boundingBox.minZ - 1, Direction.NORTH, this.getComponentType(), p_74961_6_);

					default:
						break;
				}
			}

			return null;
		}


		@Nullable
		protected StructurePiece getNextComponentZ(JungleFortressPieces.Start p_74965_1_, List<StructurePiece> p_74965_2_, Random p_74965_3_, int p_74965_4_, int p_74965_5_, boolean p_74965_6_)
		{
			Direction enumfacing = this.getCoordBaseMode();

			if (enumfacing != null)
			{
				switch (enumfacing)
				{
					case NORTH:
						return this.generateAndAddPiece(p_74965_1_, p_74965_2_, p_74965_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74965_4_, this.boundingBox.minZ + p_74965_5_, Direction.EAST, this.getComponentType(), p_74965_6_);

					case SOUTH:
						return this.generateAndAddPiece(p_74965_1_, p_74965_2_, p_74965_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74965_4_, this.boundingBox.minZ + p_74965_5_, Direction.EAST, this.getComponentType(), p_74965_6_);

					case WEST:
						return this.generateAndAddPiece(p_74965_1_, p_74965_2_, p_74965_3_, this.boundingBox.minX + p_74965_5_, this.boundingBox.minY + p_74965_4_, this.boundingBox.maxZ + 1, Direction.SOUTH, this.getComponentType(), p_74965_6_);

					case EAST:
						return this.generateAndAddPiece(p_74965_1_, p_74965_2_, p_74965_3_, this.boundingBox.minX + p_74965_5_, this.boundingBox.minY + p_74965_4_, this.boundingBox.maxZ + 1, Direction.SOUTH, this.getComponentType(), p_74965_6_);

					default:
						break;
				}
			}

			return null;
		}


		protected static boolean isAboveGround(MutableBoundingBox p_74964_0_)
		{
			return p_74964_0_ != null && p_74964_0_.minY > 10;
		}


		protected BlockState getStoneVariantBlockState(BlockState blockstateIn, Random rand)
		{
			Block block = blockstateIn.getBlock();

			if (block == Blocks.NETHER_BRICKS)
			{
				BlockState newBlockState;
				float chance = rand.nextFloat();
				if (chance < 0.50f)
				{
					// 50%
					newBlockState = Blocks.MOSSY_STONE_BRICKS.getDefaultState();
				}
				else if (chance < 0.75f)
				{
					// 25%
					newBlockState = Blocks.CRACKED_STONE_BRICKS.getDefaultState();
				}
				else if (chance < 0.95f)
				{
					// 20%
					newBlockState = Blocks.STONE_BRICKS.getDefaultState();
				}
				else
				{
					// 5%
					newBlockState = Blocks.CHISELED_STONE_BRICKS.getDefaultState();
				}
				

				chance = rand.nextFloat();
				float silverfishThreshold = (float) (RSConfig.silverfishSpawnrateJF / 100);
				if(chance < silverfishThreshold)
				{
					newBlockState = INFESTED_STONE_LOOKUP.get(newBlockState);
				}
				
				
				return newBlockState;
			}
			else if (block == Blocks.NETHER_BRICK_FENCE)
			{
				return Blocks.IRON_BARS.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(blockstateIn.get(FourWayBlock.NORTH))).with(FourWayBlock.EAST, Boolean.valueOf(blockstateIn.get(FourWayBlock.EAST))).with(FourWayBlock.SOUTH, Boolean.valueOf(blockstateIn.get(FourWayBlock.SOUTH))).with(FourWayBlock.WEST, Boolean.valueOf(blockstateIn.get(FourWayBlock.WEST)));
			}
			else if (block == Blocks.NETHER_BRICK_STAIRS)
			{

				float chance = rand.nextFloat();
				if (chance < 0.8f)
				{
					// 80%
					return Blocks.STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, blockstateIn.get(StairsBlock.FACING));
				}
				else
				{
					// 20%
					return Blocks.MOSSY_STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, blockstateIn.get(StairsBlock.FACING));
				}
			}
			else if (block == Blocks.SOUL_SAND)
			{
				return Blocks.COARSE_DIRT.getDefaultState();
			}
			else if (block == Blocks.NETHER_WART)
			{

				float chance = rand.nextFloat();

				if (chance < 0.25f)
				{
					// 25%
					return Blocks.RED_MUSHROOM.getDefaultState();
				}
				else if (chance < 0.4f)
				{
					// 15%
					return Blocks.BROWN_MUSHROOM.getDefaultState();
				}
				else
				{
					return Blocks.CAVE_AIR.getDefaultState();
				}
			}
			else if (block == Blocks.LAVA)
			{
				return Blocks.WATER.getDefaultState();
			}

			return block.getDefaultState();
		}

		/**
		 * Fill the given area with the selected random blocks
		 */
		protected void fillWithRandomBlocks(IWorld world, MutableBoundingBox boundingboxIn, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, BlockState boundaryBlockState, BlockState insideBlockState, boolean existingOnly, Random rand)
		{
			for (int y = yMin; y <= yMax; ++y)
			{
				for (int j = xMin; j <= xMax; ++j)
				{
					for (int k = zMin; k <= zMax; ++k)
					{
						if (!existingOnly || this.getBlockStateFromPos(world, j, y, k, boundingboxIn).getMaterial() != Material.AIR)
						{
							if (y != yMin && y != yMax && j != xMin && j != xMax && k != zMin && k != zMax)
							{
								this.setBlockState(world, insideBlockState, j, y, k, boundingboxIn);
							}
							else
							{
								this.setBlockState(world, getStoneVariantBlockState(boundaryBlockState.getBlock().getDefaultState(), rand), j, y, k, boundingboxIn);
							}
						}
					}
				}
			}

		}


		/**
		 * Replaces air and liquid from given position downwards. Stops when hitting anything else than air or liquid
		 */
		protected void replaceAirAndLiquidDownwardsRandomBlocks(IWorld world, BlockState blockstateIn, int x, int y, int z, MutableBoundingBox boundingboxIn, Random rand)
		{
			int i = this.getXWithOffset(x, z);
			int j = this.getYWithOffset(y);
			int k = this.getZWithOffset(x, z);
			if (boundingboxIn.isVecInside(new BlockPos(i, j, k)))
			{
				while ((world.isAirBlock(new BlockPos(i, j, k)) || world.getBlockState(new BlockPos(i, j, k)).getMaterial().isLiquid()) && j > 1)
				{
					world.setBlockState(new BlockPos(i, j, k), getStoneVariantBlockState(blockstateIn.getBlock().getDefaultState(), rand), 2);
					--j;
				}

			}
		}
	}

	static class PieceWeight
	{
		public Class<? extends JungleFortressPieces.Piece> weightClass;
		public final int weight;
		public int placeCount;
		public int maxPlaceCount;
		public boolean allowInRow;


		public PieceWeight(Class<? extends JungleFortressPieces.Piece> weightClass, int weight, int maximumCount, boolean allowMultipleInRow)
		{
			this.weightClass = weightClass;
			this.weight = weight;
			this.maxPlaceCount = maximumCount;
			this.allowInRow = allowMultipleInRow;
		}


		public PieceWeight(Class<? extends JungleFortressPieces.Piece> weightClass, int weight, int maximumCount)
		{
			this(weightClass, weight, maximumCount, false);
		}


		public boolean doPlace(int p_78822_1_)
		{
			return this.maxPlaceCount == 0 || this.placeCount < this.maxPlaceCount;
		}


		public boolean isValid()
		{
			return this.maxPlaceCount == 0 || this.placeCount < this.maxPlaceCount;
		}
	}

	public static class Stairs extends JungleFortressPieces.Piece
	{
		public Stairs(int p_i50255_1_, MutableBoundingBox p_i50255_2_, Direction p_i50255_3_)
		{
			super(StructurePieces.JFSR, p_i50255_1_);
			this.setCoordBaseMode(p_i50255_3_);
			this.boundingBox = p_i50255_2_;
		}


		public Stairs(TemplateManager p_i50256_1_, CompoundNBT p_i50256_2_)
		{
			super(StructurePieces.JFSR, p_i50256_2_);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentZ((JungleFortressPieces.Start) componentIn, listIn, rand, 6, 2, false);
		}


		public static JungleFortressPieces.Stairs createPiece(List<StructurePiece> p_175872_0_, Random p_175872_1_, int p_175872_2_, int p_175872_3_, int p_175872_4_, int p_175872_5_, Direction p_175872_6_)
		{
			MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(p_175872_2_, p_175872_3_, p_175872_4_, -2, 0, 0, 7, 11, 7, p_175872_6_);
			return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(p_175872_0_, mutableBoundingBox) == null ? new JungleFortressPieces.Stairs(p_175872_5_, mutableBoundingBox, p_175872_6_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos chunkPos)
		{
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 6, 10, 6, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 1, 8, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 2, 0, 6, 8, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 1, 0, 8, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 6, 2, 1, 6, 8, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 2, 6, 5, 8, 6, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true)), random);
			BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true)), random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 3, 2, 0, 5, 4, iblockstate1, iblockstate1, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 6, 3, 2, 6, 5, 2, iblockstate1, iblockstate1, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 6, 3, 4, 6, 5, 4, iblockstate1, iblockstate1, false, random);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICKS.getDefaultState(), random), 5, 2, 5, structureBoundingBoxIn);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 2, 5, 4, 3, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 3, 2, 5, 3, 4, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 2, 5, 2, 5, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 2, 5, 1, 6, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 7, 1, 5, 7, 4, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 6, 8, 2, 6, 8, 4, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 6, 0, 4, 8, 0, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 5, 0, 4, 5, 0, iblockstate, iblockstate, false, random);

			for (int i = 0; i <= 6; ++i)
			{
				for (int j = 0; j <= 6; ++j)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, structureBoundingBoxIn, random);
				}
			}

			attemptToAddVines(world, p_225577_2_, random, structureBoundingBoxIn);
			return true;
		}
	}

	public static class Start extends JungleFortressPieces.Crossing3
	{
		public JungleFortressPieces.PieceWeight theNetherBridgePieceWeight;
		public List<JungleFortressPieces.PieceWeight> primaryWeights;
		public List<JungleFortressPieces.PieceWeight> secondaryWeights;
		public List<StructurePiece> pendingChildren = Lists.<StructurePiece>newArrayList();


		public Start(Random p_i2059_1_, int p_i2059_2_, int p_i2059_3_)
		{
			super(p_i2059_1_, p_i2059_2_, p_i2059_3_);
			this.primaryWeights = Lists.<JungleFortressPieces.PieceWeight>newArrayList();

			for (JungleFortressPieces.PieceWeight structurenetherbridgepieces$pieceweight : JungleFortressPieces.PRIMARY_COMPONENTS)
			{
				structurenetherbridgepieces$pieceweight.placeCount = 0;
				this.primaryWeights.add(structurenetherbridgepieces$pieceweight);
			}

			this.secondaryWeights = Lists.<JungleFortressPieces.PieceWeight>newArrayList();

			for (JungleFortressPieces.PieceWeight structurenetherbridgepieces$pieceweight1 : JungleFortressPieces.SECONDARY_COMPONENTS)
			{
				structurenetherbridgepieces$pieceweight1.placeCount = 0;
				this.secondaryWeights.add(structurenetherbridgepieces$pieceweight1);
			}
		}


		public Start(TemplateManager p_i50253_1_, CompoundNBT p_i50253_2_)
		{
			super(StructurePieces.JFSTART, p_i50253_2_);
		}
	}

	public static class Straight extends JungleFortressPieces.Piece
	{
		public Straight(int p_i45620_1_, Random p_i45620_2_, MutableBoundingBox p_i45620_3_, Direction p_i45620_4_)
		{
			super(StructurePieces.JFS, p_i45620_1_);
			this.setCoordBaseMode(p_i45620_4_);
			this.boundingBox = p_i45620_3_;
		}


		public Straight(TemplateManager p_i50283_1_, CompoundNBT p_i50283_2_)
		{
			super(StructurePieces.JFS, p_i50283_2_);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentNormal((JungleFortressPieces.Start) componentIn, listIn, rand, 1, 3, false);
		}


		public static JungleFortressPieces.Straight createPiece(List<StructurePiece> p_175882_0_, Random p_175882_1_, int p_175882_2_, int p_175882_3_, int p_175882_4_, Direction p_175882_5_, int p_175882_6_)
		{
			MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(p_175882_2_, p_175882_3_, p_175882_4_, -1, -3, 0, 5, 10, 19, p_175882_5_);
			return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(p_175882_0_, mutableBoundingBox) == null ? new JungleFortressPieces.Straight(p_175882_6_, p_175882_1_, mutableBoundingBox, p_175882_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos chunkPos)
		{
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 3, 0, 4, 4, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 5, 0, 3, 7, 18, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 5, 0, 0, 5, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 5, 0, 4, 5, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 4, 2, 5, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 13, 4, 2, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 0, 0, 4, 1, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 0, 15, 4, 1, 18, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);

			for (int i = 0; i <= 4; ++i)
			{
				for (int j = 0; j <= 2; ++j)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, structureBoundingBoxIn, random);
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, 18 - j, structureBoundingBoxIn, random);
				}
			}
			BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true)), random);
			BlockState iblockstate2 = iblockstate1.with(FourWayBlock.EAST, Boolean.valueOf(true));
			BlockState iblockstate3 = iblockstate1.with(FourWayBlock.WEST, Boolean.valueOf(true));

			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 1, 1, 0, 4, 1, iblockstate2, iblockstate2, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 3, 4, 0, 4, 4, iblockstate2, iblockstate2, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 3, 14, 0, 4, 14, iblockstate2, iblockstate2, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 1, 17, 0, 4, 17, iblockstate2, iblockstate2, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 1, 1, 4, 4, 1, iblockstate3, iblockstate3, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 3, 4, 4, 4, 4, iblockstate3, iblockstate3, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 3, 14, 4, 4, 14, iblockstate3, iblockstate3, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 4, 1, 17, 4, 4, 17, iblockstate3, iblockstate3, false, random);

			attemptToAddVines(world, p_225577_2_, random, structureBoundingBoxIn);
			return true;
		}
	}

	public static class Throne extends JungleFortressPieces.Piece
	{
		private boolean hasSpawner;


		public Throne(int p_i50262_1_, Random rand, MutableBoundingBox p_i50262_2_, Direction p_i50262_3_)
		{
			super(StructurePieces.JFMT, p_i50262_1_);
			this.setCoordBaseMode(p_i50262_3_);
			this.boundingBox = p_i50262_2_;
		}


		public Throne(TemplateManager p_i50263_1_, CompoundNBT p_i50263_2_)
		{
			super(StructurePieces.JFMT, p_i50263_2_);
			this.hasSpawner = p_i50263_2_.getBoolean("Mob");
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
			tagCompound.putBoolean("Mob", this.hasSpawner);
		}


		public static JungleFortressPieces.Throne createPiece(List<StructurePiece> p_175874_0_, Random p_175874_1_, int p_175874_2_, int p_175874_3_, int p_175874_4_, int p_175874_5_, Direction p_175874_6_)
		{
			MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getComponentToAddBoundingBox(p_175874_2_, p_175874_3_, p_175874_4_, -2, 0, 0, 7, 8, 9, p_175874_6_);
			return isAboveGround(mutableBoundingBox) && StructurePiece.findIntersecting(p_175874_0_, mutableBoundingBox) == null ? new JungleFortressPieces.Throne(p_175874_5_, p_175874_1_, mutableBoundingBox, p_175874_6_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos chunkPos)
		{
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 2, 0, 6, 7, 7, Blocks.CAVE_AIR.getDefaultState(), Blocks.CAVE_AIR.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 0, 0, 5, 1, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 2, 1, 5, 2, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 3, 2, 5, 3, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 4, 3, 5, 4, 7, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 2, 0, 1, 4, 2, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 2, 0, 5, 4, 2, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 5, 2, 1, 5, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 5, 5, 2, 5, 5, 3, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 5, 3, 0, 5, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 6, 5, 3, 6, 5, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 5, 8, 5, 5, 8, Blocks.NETHER_BRICKS.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState(), false, random);
			BlockState iblockstate = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true)), random);
			BlockState iblockstate1 = getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true)), random);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, Boolean.valueOf(true)), random), 1, 6, 3, structureBoundingBoxIn);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.EAST, Boolean.valueOf(true)), random), 5, 6, 3, structureBoundingBoxIn);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.EAST, Boolean.valueOf(true)).with(FourWayBlock.NORTH, Boolean.valueOf(true)), random), 0, 6, 3, structureBoundingBoxIn);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, Boolean.valueOf(true)).with(FourWayBlock.NORTH, Boolean.valueOf(true)), random), 6, 6, 3, structureBoundingBoxIn);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 0, 6, 4, 0, 6, 7, iblockstate1, iblockstate1, false, random);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 6, 6, 4, 6, 6, 7, iblockstate1, iblockstate1, false, random);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.EAST, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true)), random), 0, 6, 8, structureBoundingBoxIn);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true)), random), 6, 6, 8, structureBoundingBoxIn);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 1, 6, 8, 5, 6, 8, iblockstate, iblockstate, false, random);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.EAST, Boolean.valueOf(true)), random), 1, 7, 8, structureBoundingBoxIn);
			this.fillWithRandomBlocks(world, structureBoundingBoxIn, 2, 7, 8, 4, 7, 8, iblockstate, iblockstate, false, random);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, Boolean.valueOf(true)), random), 5, 7, 8, structureBoundingBoxIn);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.EAST, Boolean.valueOf(true)), random), 2, 8, 8, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate, 3, 8, 8, structureBoundingBoxIn);
			this.setBlockState(world, getStoneVariantBlockState(Blocks.NETHER_BRICK_FENCE.getDefaultState().with(FourWayBlock.WEST, Boolean.valueOf(true)), random), 4, 8, 8, structureBoundingBoxIn);

			if (!this.hasSpawner)
			{
				BlockPos blockpos = new BlockPos(this.getXWithOffset(3, 5), this.getYWithOffset(5), this.getZWithOffset(3, 5));

				if (structureBoundingBoxIn.isVecInside(blockpos))
				{
					this.hasSpawner = true;

					//mob spawner
					world.setBlockState(blockpos.down(2), Blocks.SPAWNER.getDefaultState(), 2);
					TileEntity tileentity2 = world.getTileEntity(blockpos.down(2));

					if (tileentity2 instanceof MobSpawnerTileEntity)
					{
						//silverfish mob spawner
						if (RSConfig.allowSilverfishSpawnerJF)
						{
							((MobSpawnerTileEntity) tileentity2).getSpawnerBaseLogic().setEntityType(EntityType.SILVERFISH);
						}
						else
						{
							((MobSpawnerTileEntity) tileentity2).getSpawnerBaseLogic().setEntityType(EntityType.SKELETON);
						}
					}
				}
			}

			for (int i = 0; i <= 6; ++i)
			{
				for (int j = 0; j <= 6; ++j)
				{
					this.replaceAirAndLiquidDownwardsRandomBlocks(world, Blocks.NETHER_BRICKS.getDefaultState(), i, -1, j, structureBoundingBoxIn, random);
				}
			}

			if (RSConfig.lootChestsJF)
			{
				this.generateChest(world, structureBoundingBoxIn, random, 3, 5, 7, LootTables.CHESTS_END_CITY_TREASURE);
			}

			attemptToAddVines(world, p_225577_2_, random, structureBoundingBoxIn);
			return true;
		}
	}

	private static ResourceLocation pickRandomLoot(Random random)
	{
		ResourceLocation lootTable = null;
		
		float chance = random.nextFloat();
		if(chance < 0.38f)
		{
			lootTable = LootTables.CHESTS_VILLAGE_VILLAGE_WEAPONSMITH;
		}
		else if(chance < 0.76f)
		{
			lootTable = LootTables.CHESTS_VILLAGE_VILLAGE_ARMORER;
		}
		else
		{
			lootTable = LootTables.CHESTS_JUNGLE_TEMPLE;
		}
		
		
		return lootTable;
	}
	
	
	private static void attemptToAddVines(IWorld world, ChunkGenerator<?> chunkGenerator, Random random, MutableBoundingBox structureBoundingBoxIn)
	{
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		for(int x = structureBoundingBoxIn.minX; x <= structureBoundingBoxIn.maxX; x++)
		{
			for(int z = structureBoundingBoxIn.minZ; z <= structureBoundingBoxIn.maxZ; z++)
			{
				for(int y = 62; y <= 90; y++)
				{
					if(random.nextInt(150) == 0)
					{
						RSFeatures.SHORT_VINES.place(world, chunkGenerator, random, mutablePos.setPos(x, y, z), NoFeatureConfig.NO_FEATURE_CONFIG);
					}
				}
			}
		}
	}
}
