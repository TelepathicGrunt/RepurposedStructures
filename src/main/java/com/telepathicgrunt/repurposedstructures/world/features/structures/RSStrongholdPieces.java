package com.telepathicgrunt.repurposedstructures.world.features.structures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RSConfig;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.block.FourWayBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.properties.SlabType;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTables;


public class RSStrongholdPieces
{
	private static final RSStrongholdPieces.PieceWeight[] PIECE_WEIGHTS = new RSStrongholdPieces.PieceWeight[] { new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.Straight.class, 40, 0), new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.Prison.class, 5, 8), new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.LeftTurn.class, 20, 0), new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.RightTurn.class, 20, 0),
			new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.RoomCrossing.class, 10, 9), new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.StairsStraight.class, 5, 7), new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.Stairs.class, 5, 7), new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.Crossing.class, 5, 7), new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.ChestCorridor.class, 5, 16), new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.Library.class, 10, 6)
			{
				@Override
				public boolean canSpawnMoreStructuresOfType(int type)
				{
					return super.canSpawnMoreStructuresOfType(type) && type > 4;
				}
			}, new RSStrongholdPieces.PieceWeight(RSStrongholdPieces.PortalRoom.class, 20, 1)
			{
				@Override
				public boolean canSpawnMoreStructuresOfType(int type)
				{
					return super.canSpawnMoreStructuresOfType(type) && type > 5;
				}
			} };
	private static List<RSStrongholdPieces.PieceWeight> structurePieceList;
	private static Class<? extends RSStrongholdPieces.Stronghold> strongComponentType;
	static int totalWeight;
	private static final RSStrongholdPieces.Stones STRONGHOLD_STONES = new RSStrongholdPieces.Stones();


	/**
	 * sets up Arrays with the Structure pieces and their weights
	 */
	public static void prepareStructurePieces()
	{
		structurePieceList = Lists.<RSStrongholdPieces.PieceWeight>newArrayList();

		for (RSStrongholdPieces.PieceWeight structurestrongholdpieces$pieceweight : PIECE_WEIGHTS)
		{
			structurestrongholdpieces$pieceweight.instancesSpawned = 0;
			structurePieceList.add(structurestrongholdpieces$pieceweight);
		}

		strongComponentType = null;
	}


	private static boolean canAddStructurePieces()
	{
		boolean flag = false;
		totalWeight = 0;

		for (RSStrongholdPieces.PieceWeight structurestrongholdpieces$pieceweight : structurePieceList)
		{
			if (structurestrongholdpieces$pieceweight.instancesLimit > 0 && structurestrongholdpieces$pieceweight.instancesSpawned < structurestrongholdpieces$pieceweight.instancesLimit)
			{
				flag = true;
			}

			totalWeight += structurestrongholdpieces$pieceweight.pieceWeight;
		}

		return flag;
	}


	private static RSStrongholdPieces.Stronghold findAndCreatePieceFactory(Class<? extends RSStrongholdPieces.Stronghold> clazz, List<StructurePiece> p_175954_1_, Random p_175954_2_, int p_175954_3_, int p_175954_4_, int p_175954_5_, @Nullable Direction p_175954_6_, int p_175954_7_)
	{
		RSStrongholdPieces.Stronghold structurestrongholdpieces$stronghold = null;

		if (clazz == RSStrongholdPieces.Straight.class)
		{
			structurestrongholdpieces$stronghold = RSStrongholdPieces.Straight.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
		}
		else if (clazz == RSStrongholdPieces.Prison.class)
		{
			structurestrongholdpieces$stronghold = RSStrongholdPieces.Prison.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
		}
		else if (clazz == RSStrongholdPieces.LeftTurn.class)
		{
			structurestrongholdpieces$stronghold = RSStrongholdPieces.LeftTurn.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
		}
		else if (clazz == RSStrongholdPieces.RightTurn.class)
		{
			structurestrongholdpieces$stronghold = RSStrongholdPieces.RightTurn.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
		}
		else if (clazz == RSStrongholdPieces.RoomCrossing.class)
		{
			structurestrongholdpieces$stronghold = RSStrongholdPieces.RoomCrossing.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
		}
		else if (clazz == RSStrongholdPieces.StairsStraight.class)
		{
			structurestrongholdpieces$stronghold = RSStrongholdPieces.StairsStraight.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
		}
		else if (clazz == RSStrongholdPieces.Stairs.class)
		{
			structurestrongholdpieces$stronghold = RSStrongholdPieces.Stairs.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
		}
		else if (clazz == RSStrongholdPieces.Crossing.class)
		{
			structurestrongholdpieces$stronghold = RSStrongholdPieces.Crossing.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
		}
		else if (clazz == RSStrongholdPieces.ChestCorridor.class)
		{
			structurestrongholdpieces$stronghold = RSStrongholdPieces.ChestCorridor.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
		}
		else if (clazz == RSStrongholdPieces.Library.class)
		{
			structurestrongholdpieces$stronghold = RSStrongholdPieces.Library.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
		}
		else if (clazz == RSStrongholdPieces.PortalRoom.class)
		{
			structurestrongholdpieces$stronghold = RSStrongholdPieces.PortalRoom.createPiece(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
		}

		return structurestrongholdpieces$stronghold;
	}


	private static RSStrongholdPieces.Stronghold generatePieceFromSmallDoor(RSStrongholdPieces.Stairs2 p_175955_0_, List<StructurePiece> p_175955_1_, Random p_175955_2_, int p_175955_3_, int p_175955_4_, int p_175955_5_, Direction p_175955_6_, int p_175955_7_)
	{
		if (!canAddStructurePieces())
		{
			return null;
		}
		else
		{
			if (strongComponentType != null)
			{
				RSStrongholdPieces.Stronghold structurestrongholdpieces$stronghold = findAndCreatePieceFactory(strongComponentType, p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_, p_175955_7_);
				strongComponentType = null;

				if (structurestrongholdpieces$stronghold != null)
				{
					return structurestrongholdpieces$stronghold;
				}
			}

			int j = 0;

			while (j < 5)
			{
				++j;
				int i = p_175955_2_.nextInt(totalWeight);

				for (RSStrongholdPieces.PieceWeight structurestrongholdpieces$pieceweight : structurePieceList)
				{
					i -= structurestrongholdpieces$pieceweight.pieceWeight;

					if (i < 0)
					{
						if (!structurestrongholdpieces$pieceweight.canSpawnMoreStructuresOfType(p_175955_7_) || structurestrongholdpieces$pieceweight == p_175955_0_.strongholdPieceWeight)
						{
							break;
						}

						RSStrongholdPieces.Stronghold structurestrongholdpieces$stronghold1 = findAndCreatePieceFactory(structurestrongholdpieces$pieceweight.pieceClass, p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_, p_175955_7_);

						if (structurestrongholdpieces$stronghold1 != null)
						{
							++structurestrongholdpieces$pieceweight.instancesSpawned;
							p_175955_0_.strongholdPieceWeight = structurestrongholdpieces$pieceweight;

							if (!structurestrongholdpieces$pieceweight.canSpawnMoreStructures())
							{
								structurePieceList.remove(structurestrongholdpieces$pieceweight);
							}

							return structurestrongholdpieces$stronghold1;
						}
					}
				}
			}

			MutableBoundingBox mutableboundingbox = RSStrongholdPieces.Corridor.findPieceBox(p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_);

			if (mutableboundingbox != null && mutableboundingbox.minY > 1)
			{
				return new RSStrongholdPieces.Corridor(p_175955_7_, mutableboundingbox, p_175955_6_);
			}
			else
			{
				return null;
			}
		}
	}


	private static StructurePiece generateAndAddPiece(RSStrongholdPieces.Stairs2 p_175953_0_, List<StructurePiece> p_175953_1_, Random p_175953_2_, int p_175953_3_, int p_175953_4_, int p_175953_5_, @Nullable Direction p_175953_6_, int p_175953_7_)
	{
		if (p_175953_7_ > 50)
		{
			return null;
		}
		else if (Math.abs(p_175953_3_ - p_175953_0_.getBoundingBox().minX) <= 112 && Math.abs(p_175953_5_ - p_175953_0_.getBoundingBox().minZ) <= 112)
		{
			StructurePiece StructurePiece = generatePieceFromSmallDoor(p_175953_0_, p_175953_1_, p_175953_2_, p_175953_3_, p_175953_4_, p_175953_5_, p_175953_6_, p_175953_7_ + 1);

			if (StructurePiece != null)
			{
				p_175953_1_.add(StructurePiece);
				p_175953_0_.pendingChildren.add(StructurePiece);
			}

			return StructurePiece;
		}
		else
		{
			return null;
		}
	}

	public static class ChestCorridor extends RSStrongholdPieces.Stronghold
	{
		private boolean hasMadeChest;


		public ChestCorridor(int p_i45582_1_, Random p_i45582_2_, MutableBoundingBox p_i45582_3_, Direction p_i45582_4_)
		{
			super(StructurePieces.SHCCRS, p_i45582_1_);
			this.setCoordBaseMode(p_i45582_4_);
			this.entryDoor = this.getRandomDoor(p_i45582_2_);
			this.boundingBox = p_i45582_3_;
		}


		public ChestCorridor(TemplateManager p_i50140_1_, CompoundNBT p_i50140_2_)
		{
			super(StructurePieces.SHCCRS, p_i50140_2_);
			this.hasMadeChest = p_i50140_2_.getBoolean("Chest");
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
			tagCompound.putBoolean("Chest", this.hasMadeChest);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentNormal((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, 1, 1);
		}


		public static RSStrongholdPieces.ChestCorridor createPiece(List<StructurePiece> p_175868_0_, Random p_175868_1_, int p_175868_2_, int p_175868_3_, int p_175868_4_, Direction p_175868_5_, int p_175868_6_)
		{
			MutableBoundingBox mutableboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p_175868_2_, p_175868_3_, p_175868_4_, -1, -1, 0, 5, 5, 7, p_175868_5_);
			return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.findIntersecting(p_175868_0_, mutableboundingbox) == null ? new RSStrongholdPieces.ChestCorridor(p_175868_6_, p_175868_1_, mutableboundingbox, p_175868_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 0, 0, 0, 4, 4, 6, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 1, 1, 0);
			this.placeDoor(world, random, structureBoundingBoxIn, RSStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 6);
			this.fillWithBlocks(world, structureBoundingBoxIn, 3, 1, 2, 3, 1, 4, Blocks.STONE_BRICKS.getDefaultState(), Blocks.STONE_BRICKS.getDefaultState(), false);
			this.setBlockState(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 3, 1, 1, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 3, 1, 5, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 3, 2, 2, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 3, 2, 4, structureBoundingBoxIn);

			for (int i = 2; i <= 4; ++i)
			{
				this.setBlockState(world, Blocks.STONE_BRICK_SLAB.getDefaultState(), 2, 1, i, structureBoundingBoxIn);
			}

			if (!this.hasMadeChest && structureBoundingBoxIn.isVecInside(new BlockPos(this.getXWithOffset(3, 3), this.getYWithOffset(2), this.getZWithOffset(3, 3))))
			{
				this.hasMadeChest = true;
				if (RSConfig.lootChestsSH)
				{
					this.generateChest(world, structureBoundingBoxIn, random, 3, 2, 3, LootTables.CHESTS_STRONGHOLD_CORRIDOR);
				}
			}

			return true;
		}
	}

	public static class Corridor extends RSStrongholdPieces.Stronghold
	{
		private int steps;


		public Corridor(int p_i50137_1_, MutableBoundingBox p_i50137_2_, Direction p_i50137_3_)
		{
			super(StructurePieces.SHFCRS, p_i50137_1_);
			this.setCoordBaseMode(p_i50137_3_);
			this.boundingBox = p_i50137_2_;
			this.steps = p_i50137_3_ != Direction.NORTH && p_i50137_3_ != Direction.SOUTH ? p_i50137_2_.getXSize() : p_i50137_2_.getZSize();
		}


		public Corridor(TemplateManager p_i50138_1_, CompoundNBT p_i50138_2_)
		{
			super(StructurePieces.SHFCRS, p_i50138_2_);
			this.steps = p_i50138_2_.getInt("Steps");
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
			tagCompound.putInt("Steps", this.steps);
		}


		public static MutableBoundingBox findPieceBox(List<StructurePiece> p_175869_0_, Random p_175869_1_, int p_175869_2_, int p_175869_3_, int p_175869_4_, Direction p_175869_5_)
		{
			MutableBoundingBox mutableboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, 4, p_175869_5_);
			StructurePiece structurePiece = StructurePiece.findIntersecting(p_175869_0_, mutableboundingbox);

			if (structurePiece == null)
			{
				return null;
			}
			else
			{
				if (structurePiece.getBoundingBox().minY == mutableboundingbox.minY)
				{
					for (int j = 3; j >= 1; --j)
					{
						mutableboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, j - 1, p_175869_5_);

						if (!structurePiece.getBoundingBox().intersectsWith(mutableboundingbox))
						{
							return MutableBoundingBox.getComponentToAddBoundingBox(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, j, p_175869_5_);
						}
					}
				}

				return null;
			}
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			for (int i = 0; i < this.steps; ++i)
			{
				this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 0, 0, i, structureBoundingBoxIn);
				this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 0, i, structureBoundingBoxIn);
				this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 2, 0, i, structureBoundingBoxIn);
				this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 3, 0, i, structureBoundingBoxIn);
				this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 4, 0, i, structureBoundingBoxIn);

				for (int j = 1; j <= 3; ++j)
				{
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 0, j, i, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.AIR.getDefaultState(), 1, j, i, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.AIR.getDefaultState(), 2, j, i, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.AIR.getDefaultState(), 3, j, i, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 4, j, i, structureBoundingBoxIn);
				}

				this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 0, 4, i, structureBoundingBoxIn);
				this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 4, i, structureBoundingBoxIn);
				this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 2, 4, i, structureBoundingBoxIn);
				this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 3, 4, i, structureBoundingBoxIn);
				this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 4, 4, i, structureBoundingBoxIn);
			}

			return true;
		}

	}

	public static class Crossing extends RSStrongholdPieces.Stronghold
	{
		private boolean leftLow;
		private boolean leftHigh;
		private boolean rightLow;
		private boolean rightHigh;


		public Crossing(int p_i45580_1_, Random p_i45580_2_, MutableBoundingBox p_i45580_3_, Direction p_i45580_4_)
		{
			super(StructurePieces.SH5CRS, p_i45580_1_);
			this.setCoordBaseMode(p_i45580_4_);
			this.entryDoor = this.getRandomDoor(p_i45580_2_);
			this.boundingBox = p_i45580_3_;
			this.leftLow = p_i45580_2_.nextBoolean();
			this.leftHigh = p_i45580_2_.nextBoolean();
			this.rightLow = p_i45580_2_.nextBoolean();
			this.rightHigh = p_i45580_2_.nextInt(3) > 0;
		}


		public Crossing(TemplateManager p_i50136_1_, CompoundNBT p_i50136_2_)
		{
			super(StructurePieces.SH5CRS, p_i50136_2_);
			this.leftLow = p_i50136_2_.getBoolean("leftLow");
			this.leftHigh = p_i50136_2_.getBoolean("leftHigh");
			this.rightLow = p_i50136_2_.getBoolean("rightLow");
			this.rightHigh = p_i50136_2_.getBoolean("rightHigh");
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
			tagCompound.putBoolean("leftLow", this.leftLow);
			tagCompound.putBoolean("leftHigh", this.leftHigh);
			tagCompound.putBoolean("rightLow", this.rightLow);
			tagCompound.putBoolean("rightHigh", this.rightHigh);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			int i = 3;
			int j = 5;
			Direction enumfacing = this.getCoordBaseMode();

			if (enumfacing == Direction.WEST || enumfacing == Direction.NORTH)
			{
				i = 8 - i;
				j = 8 - j;
			}

			this.getNextComponentNormal((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, 5, 1);

			if (this.leftLow)
			{
				this.getNextComponentX((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, i, 1);
			}

			if (this.leftHigh)
			{
				this.getNextComponentX((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, j, 7);
			}

			if (this.rightLow)
			{
				this.getNextComponentZ((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, i, 1);
			}

			if (this.rightHigh)
			{
				this.getNextComponentZ((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, j, 7);
			}
		}


		public static RSStrongholdPieces.Crossing createPiece(List<StructurePiece> p_175866_0_, Random p_175866_1_, int p_175866_2_, int p_175866_3_, int p_175866_4_, Direction p_175866_5_, int p_175866_6_)
		{
			MutableBoundingBox mutableboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p_175866_2_, p_175866_3_, p_175866_4_, -4, -3, 0, 10, 9, 11, p_175866_5_);
			return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.findIntersecting(p_175866_0_, mutableboundingbox) == null ? new RSStrongholdPieces.Crossing(p_175866_6_, p_175866_1_, mutableboundingbox, p_175866_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 0, 0, 0, 9, 8, 10, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 4, 3, 0);

			if (this.leftLow)
			{
				this.fillWithBlocks(world, structureBoundingBoxIn, 0, 3, 1, 0, 5, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
			}

			if (this.rightLow)
			{
				this.fillWithBlocks(world, structureBoundingBoxIn, 9, 3, 1, 9, 5, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
			}

			if (this.leftHigh)
			{
				this.fillWithBlocks(world, structureBoundingBoxIn, 0, 5, 7, 0, 7, 9, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
			}

			if (this.rightHigh)
			{
				this.fillWithBlocks(world, structureBoundingBoxIn, 9, 5, 7, 9, 7, 9, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
			}

			this.fillWithBlocks(world, structureBoundingBoxIn, 5, 1, 10, 7, 3, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 1, 2, 1, 8, 2, 6, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 4, 1, 5, 4, 4, 9, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 8, 1, 5, 8, 4, 9, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 1, 4, 7, 3, 4, 9, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 1, 3, 5, 3, 3, 6, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithBlocks(world, structureBoundingBoxIn, 1, 3, 4, 3, 3, 4, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
			this.fillWithBlocks(world, structureBoundingBoxIn, 1, 4, 6, 3, 4, 6, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 5, 1, 7, 7, 1, 8, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithBlocks(world, structureBoundingBoxIn, 5, 1, 9, 7, 1, 9, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
			this.fillWithBlocks(world, structureBoundingBoxIn, 5, 2, 7, 7, 2, 7, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
			this.fillWithBlocks(world, structureBoundingBoxIn, 4, 5, 7, 4, 5, 9, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
			this.fillWithBlocks(world, structureBoundingBoxIn, 8, 5, 7, 8, 5, 9, Blocks.STONE_SLAB.getDefaultState(), Blocks.STONE_SLAB.getDefaultState(), false);
			this.fillWithBlocks(world, structureBoundingBoxIn, 5, 5, 7, 7, 5, 9, Blocks.STONE_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.DOUBLE), Blocks.STONE_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.DOUBLE), false);
			this.setBlockState(world, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.SOUTH), 6, 5, 6, structureBoundingBoxIn);
			return true;
		}
	}

	public static class LeftTurn extends RSStrongholdPieces.Turn
	{
		public LeftTurn(int p_i45579_1_, Random p_i45579_2_, MutableBoundingBox p_i45579_3_, Direction p_i45579_4_)
		{
			super(StructurePieces.SHLTRS, p_i45579_1_);
			this.setCoordBaseMode(p_i45579_4_);
			this.entryDoor = this.getRandomDoor(p_i45579_2_);
			this.boundingBox = p_i45579_3_;
		}


		public LeftTurn(TemplateManager p_i50134_1_, CompoundNBT p_i50134_2_)
		{
			super(StructurePieces.SHLTRS, p_i50134_2_);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			Direction enumfacing = this.getCoordBaseMode();

			if (enumfacing != Direction.NORTH && enumfacing != Direction.EAST)
			{
				this.getNextComponentZ((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, 1, 1);
			}
			else
			{
				this.getNextComponentX((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, 1, 1);
			}
		}


		public static RSStrongholdPieces.LeftTurn createPiece(List<StructurePiece> p_175867_0_, Random p_175867_1_, int p_175867_2_, int p_175867_3_, int p_175867_4_, Direction p_175867_5_, int p_175867_6_)
		{
			MutableBoundingBox mutableboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p_175867_2_, p_175867_3_, p_175867_4_, -1, -1, 0, 5, 5, 5, p_175867_5_);
			return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.findIntersecting(p_175867_0_, mutableboundingbox) == null ? new RSStrongholdPieces.LeftTurn(p_175867_6_, p_175867_1_, mutableboundingbox, p_175867_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 0, 0, 0, 4, 4, 4, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 1, 1, 0);
			Direction enumfacing = this.getCoordBaseMode();

			if (enumfacing != Direction.NORTH && enumfacing != Direction.EAST)
			{
				this.fillWithBlocks(world, structureBoundingBoxIn, 4, 1, 1, 4, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
			}
			else
			{
				this.fillWithBlocks(world, structureBoundingBoxIn, 0, 1, 1, 0, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
			}

			return true;
		}
	}

	public static class Library extends RSStrongholdPieces.Stronghold
	{
		private boolean isLargeRoom;


		public Library(int p_i45578_1_, Random p_i45578_2_, MutableBoundingBox p_i45578_3_, Direction p_i45578_4_)
		{
			super(StructurePieces.SHLIRS, p_i45578_1_);
			this.setCoordBaseMode(p_i45578_4_);
			this.entryDoor = this.getRandomDoor(p_i45578_2_);
			this.boundingBox = p_i45578_3_;
			this.isLargeRoom = p_i45578_3_.getYSize() > 6;
		}


		public Library(TemplateManager p_i50133_1_, CompoundNBT p_i50133_2_)
		{
			super(StructurePieces.SHLIRS, p_i50133_2_);
			this.isLargeRoom = p_i50133_2_.getBoolean("Tall");
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
			tagCompound.putBoolean("Tall", this.isLargeRoom);
		}


		public static RSStrongholdPieces.Library createPiece(List<StructurePiece> p_175864_0_, Random p_175864_1_, int p_175864_2_, int p_175864_3_, int p_175864_4_, Direction p_175864_5_, int p_175864_6_)
		{
			MutableBoundingBox mutableboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p_175864_2_, p_175864_3_, p_175864_4_, -4, -1, 0, 14, 11, 15, p_175864_5_);

			if (!canStrongholdGoDeeper(mutableboundingbox) || StructurePiece.findIntersecting(p_175864_0_, mutableboundingbox) != null)
			{
				mutableboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p_175864_2_, p_175864_3_, p_175864_4_, -4, -1, 0, 14, 6, 15, p_175864_5_);

				if (!canStrongholdGoDeeper(mutableboundingbox) || StructurePiece.findIntersecting(p_175864_0_, mutableboundingbox) != null)
				{
					return null;
				}
			}

			return new RSStrongholdPieces.Library(p_175864_6_, p_175864_1_, mutableboundingbox, p_175864_5_);
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			int i = 11;

			if (!this.isLargeRoom)
			{
				i = 6;
			}

			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 0, 0, 0, 13, i - 1, 14, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 4, 1, 0);
			this.generateMaybeBox(world, structureBoundingBoxIn, random, 0.07F, 2, 1, 1, 11, 4, 13, Blocks.COBWEB.getDefaultState(), Blocks.COBWEB.getDefaultState(), false, false);

			for (int l = 1; l <= 13; ++l)
			{
				if ((l - 1) % 4 == 0)
				{
					this.fillWithBlocks(world, structureBoundingBoxIn, 1, 1, l, 1, 4, l, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
					this.fillWithBlocks(world, structureBoundingBoxIn, 12, 1, l, 12, 4, l, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
					this.setBlockState(world, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.EAST), 2, 3, l, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.WEST), 11, 3, l, structureBoundingBoxIn);
					if (this.isLargeRoom)
					{
						this.fillWithBlocks(world, structureBoundingBoxIn, 1, 6, l, 1, 9, l, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
						this.fillWithBlocks(world, structureBoundingBoxIn, 12, 6, l, 12, 9, l, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
					}
				}
				else
				{
					this.fillWithBlocks(world, structureBoundingBoxIn, 1, 1, l, 1, 4, l, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
					this.fillWithBlocks(world, structureBoundingBoxIn, 12, 1, l, 12, 4, l, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
					if (this.isLargeRoom)
					{
						this.fillWithBlocks(world, structureBoundingBoxIn, 1, 6, l, 1, 9, l, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
						this.fillWithBlocks(world, structureBoundingBoxIn, 12, 6, l, 12, 9, l, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
					}
				}
			}

			for (int l1 = 3; l1 < 12; l1 += 2)
			{
				this.fillWithBlocks(world, structureBoundingBoxIn, 3, 1, l1, 4, 3, l1, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
				this.fillWithBlocks(world, structureBoundingBoxIn, 6, 1, l1, 7, 3, l1, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
				this.fillWithBlocks(world, structureBoundingBoxIn, 9, 1, l1, 10, 3, l1, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
			}

			if (this.isLargeRoom)
			{
				this.fillWithBlocks(world, structureBoundingBoxIn, 1, 5, 1, 3, 5, 13, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
				this.fillWithBlocks(world, structureBoundingBoxIn, 10, 5, 1, 12, 5, 13, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
				this.fillWithBlocks(world, structureBoundingBoxIn, 4, 5, 1, 9, 5, 2, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
				this.fillWithBlocks(world, structureBoundingBoxIn, 4, 5, 12, 9, 5, 13, Blocks.OAK_PLANKS.getDefaultState(), Blocks.OAK_PLANKS.getDefaultState(), false);
				this.setBlockState(world, Blocks.OAK_PLANKS.getDefaultState(), 9, 5, 11, structureBoundingBoxIn);
				this.setBlockState(world, Blocks.OAK_PLANKS.getDefaultState(), 8, 5, 11, structureBoundingBoxIn);
				this.setBlockState(world, Blocks.OAK_PLANKS.getDefaultState(), 9, 5, 10, structureBoundingBoxIn);
				BlockState iblockstate5 = Blocks.OAK_FENCE.getDefaultState().with(FourWayBlock.WEST, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true));
				BlockState iblockstate = Blocks.OAK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.SOUTH, Boolean.valueOf(true));
				this.fillWithBlocks(world, structureBoundingBoxIn, 3, 6, 3, 3, 6, 11, iblockstate, iblockstate, false);
				this.fillWithBlocks(world, structureBoundingBoxIn, 10, 6, 3, 10, 6, 9, iblockstate, iblockstate, false);
				this.fillWithBlocks(world, structureBoundingBoxIn, 4, 6, 2, 9, 6, 2, iblockstate5, iblockstate5, false);
				this.fillWithBlocks(world, structureBoundingBoxIn, 4, 6, 12, 7, 6, 12, iblockstate5, iblockstate5, false);
				this.setBlockState(world, Blocks.OAK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true)), 3, 6, 2, structureBoundingBoxIn);
				this.setBlockState(world, Blocks.OAK_FENCE.getDefaultState().with(FourWayBlock.SOUTH, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true)), 3, 6, 12, structureBoundingBoxIn);
				this.setBlockState(world, Blocks.OAK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.WEST, Boolean.valueOf(true)), 10, 6, 2, structureBoundingBoxIn);

				for (int i1 = 0; i1 <= 2; ++i1)
				{
					this.setBlockState(world, Blocks.OAK_FENCE.getDefaultState().with(FourWayBlock.SOUTH, Boolean.valueOf(true)).with(FourWayBlock.WEST, Boolean.valueOf(true)), 8 + i1, 6, 12 - i1, structureBoundingBoxIn);
					if (i1 != 2)
					{
						this.setBlockState(world, Blocks.OAK_FENCE.getDefaultState().with(FourWayBlock.NORTH, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true)), 8 + i1, 6, 11 - i1, structureBoundingBoxIn);
					}
				}

				BlockState iblockstate6 = Blocks.LADDER.getDefaultState().with(LadderBlock.FACING, Direction.SOUTH);
				this.setBlockState(world, iblockstate6, 10, 1, 13, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate6, 10, 2, 13, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate6, 10, 3, 13, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate6, 10, 4, 13, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate6, 10, 5, 13, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate6, 10, 6, 13, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate6, 10, 7, 13, structureBoundingBoxIn);
				BlockState iblockstate1 = Blocks.OAK_FENCE.getDefaultState().with(FourWayBlock.EAST, Boolean.valueOf(true));
				this.setBlockState(world, iblockstate1, 6, 9, 7, structureBoundingBoxIn);
				BlockState iblockstate2 = Blocks.OAK_FENCE.getDefaultState().with(FourWayBlock.WEST, Boolean.valueOf(true));
				this.setBlockState(world, iblockstate2, 7, 9, 7, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate1, 6, 8, 7, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate2, 7, 8, 7, structureBoundingBoxIn);
				BlockState iblockstate3 = iblockstate.with(FourWayBlock.WEST, Boolean.valueOf(true)).with(FourWayBlock.EAST, Boolean.valueOf(true));
				this.setBlockState(world, iblockstate3, 6, 7, 7, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate3, 7, 7, 7, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate1, 5, 7, 7, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate2, 8, 7, 7, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate1.with(FourWayBlock.NORTH, Boolean.valueOf(true)), 6, 7, 6, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate1.with(FourWayBlock.SOUTH, Boolean.valueOf(true)), 6, 7, 8, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate2.with(FourWayBlock.NORTH, Boolean.valueOf(true)), 7, 7, 6, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate2.with(FourWayBlock.SOUTH, Boolean.valueOf(true)), 7, 7, 8, structureBoundingBoxIn);
				BlockState iblockstate4 = Blocks.TORCH.getDefaultState();
				this.setBlockState(world, iblockstate4, 5, 8, 7, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate4, 8, 8, 7, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate4, 6, 8, 6, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate4, 6, 8, 8, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate4, 7, 8, 6, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate4, 7, 8, 8, structureBoundingBoxIn);
			}

			if (RSConfig.lootChestsSH)
			{
				this.generateChest(world, structureBoundingBoxIn, random, 3, 3, 5, LootTables.CHESTS_STRONGHOLD_LIBRARY);
			}

			if (this.isLargeRoom)
			{
				this.setBlockState(world, Blocks.AIR.getDefaultState(), 12, 9, 1, structureBoundingBoxIn);

				if (RSConfig.lootChestsSH)
				{
					this.generateChest(world, structureBoundingBoxIn, random, 12, 8, 1, LootTables.CHESTS_STRONGHOLD_LIBRARY);
				}
			}

			return true;
		}
	}

	static class PieceWeight
	{
		public Class<? extends RSStrongholdPieces.Stronghold> pieceClass;
		public final int pieceWeight;
		public int instancesSpawned;
		public int instancesLimit;


		public PieceWeight(Class<? extends RSStrongholdPieces.Stronghold> p_i2076_1_, int p_i2076_2_, int p_i2076_3_)
		{
			this.pieceClass = p_i2076_1_;
			this.pieceWeight = p_i2076_2_;
			this.instancesLimit = p_i2076_3_;
		}


		public boolean canSpawnMoreStructuresOfType(int p_75189_1_)
		{
			return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
		}


		public boolean canSpawnMoreStructures()
		{
			return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
		}
	}

	public static class PortalRoom extends RSStrongholdPieces.Stronghold
	{
		public PortalRoom(int p_i50131_1_, MutableBoundingBox p_i50131_2_, Direction p_i50131_3_)
		{
			super(StructurePieces.SHPRRS, p_i50131_1_);
			this.setCoordBaseMode(p_i50131_3_);
			this.boundingBox = p_i50131_2_;
		}


		public PortalRoom(TemplateManager p_i50132_1_, CompoundNBT p_i50132_2_)
		{
			super(StructurePieces.SHPRRS, p_i50132_2_);
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
			if (componentIn != null)
			{
				((RSStrongholdPieces.Stairs2) componentIn).strongholdPortalRoom = this;
			}
		}


		public static RSStrongholdPieces.PortalRoom createPiece(List<StructurePiece> p_175865_0_, Random p_175865_1_, int p_175865_2_, int p_175865_3_, int p_175865_4_, Direction p_175865_5_, int p_175865_6_)
		{
			MutableBoundingBox mutableboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p_175865_2_, p_175865_3_, p_175865_4_, -4, -1, 0, 11, 8, 16, p_175865_5_);
			return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.findIntersecting(p_175865_0_, mutableboundingbox) == null ? new RSStrongholdPieces.PortalRoom(p_175865_6_, mutableboundingbox, p_175865_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 0, 0, 0, 10, 7, 15, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.placeDoor(world, random, structureBoundingBoxIn, RSStrongholdPieces.Stronghold.Door.GRATES, 4, 1, 0);
			int i = 6;
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 1, i, 1, 1, i, 14, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 9, i, 1, 9, i, 14, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 2, i, 1, 8, i, 2, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 2, i, 14, 8, i, 14, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 1, 1, 1, 2, 1, 4, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 8, 1, 1, 9, 1, 4, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithBlocks(world, structureBoundingBoxIn, 1, 1, 1, 1, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
			this.fillWithBlocks(world, structureBoundingBoxIn, 9, 1, 1, 9, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 3, 1, 8, 7, 1, 12, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithBlocks(world, structureBoundingBoxIn, 4, 1, 9, 6, 1, 11, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);

			for (int j = 3; j < 14; j += 2)
			{
				this.fillWithBlocks(world, structureBoundingBoxIn, 0, 3, j, 0, 4, j, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
				this.fillWithBlocks(world, structureBoundingBoxIn, 10, 3, j, 10, 4, j, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
			}

			for (int i1 = 2; i1 < 9; i1 += 2)
			{
				this.fillWithBlocks(world, structureBoundingBoxIn, i1, 3, 15, i1, 4, 15, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
			}

			BlockState iblockstate3 = Blocks.STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 4, 1, 5, 6, 1, 7, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 4, 2, 6, 6, 2, 7, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 4, 3, 7, 6, 3, 7, false, random, RSStrongholdPieces.STRONGHOLD_STONES);

			for (int k = 4; k <= 6; ++k)
			{
				this.setBlockState(world, iblockstate3, k, 1, 4, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate3, k, 2, 5, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate3, k, 3, 6, structureBoundingBoxIn);
			}

			BlockState iblockstate4 = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.NORTH);
			BlockState iblockstate = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.SOUTH);
			BlockState iblockstate1 = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.EAST);
			BlockState iblockstate2 = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.WEST);
			boolean flag = true;
			boolean[] aboolean = new boolean[12];

			for (int l = 0; l < aboolean.length; ++l)
			{
				aboolean[l] = random.nextFloat() > 0.9F;
				flag &= aboolean[l];
			}

			this.setBlockState(world, iblockstate4.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[0])), 4, 3, 8, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate4.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[1])), 5, 3, 8, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate4.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[2])), 6, 3, 8, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[3])), 4, 3, 12, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[4])), 5, 3, 12, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[5])), 6, 3, 12, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate1.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[6])), 3, 3, 9, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate1.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[7])), 3, 3, 10, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate1.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[8])), 3, 3, 11, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate2.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[9])), 7, 3, 9, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate2.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[10])), 7, 3, 10, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate2.with(EndPortalFrameBlock.EYE, Boolean.valueOf(aboolean[11])), 7, 3, 11, structureBoundingBoxIn);

			if (flag)
			{
				BlockState iblockstate5 = Blocks.END_PORTAL.getDefaultState();
				this.setBlockState(world, iblockstate5, 4, 3, 9, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate5, 5, 3, 9, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate5, 6, 3, 9, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate5, 4, 3, 10, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate5, 5, 3, 10, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate5, 6, 3, 10, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate5, 4, 3, 11, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate5, 5, 3, 11, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate5, 6, 3, 11, structureBoundingBoxIn);
			}

			i = this.getYWithOffset(3);
			BlockPos blockpos = new BlockPos(this.getXWithOffset(5, 6), i, this.getZWithOffset(5, 6));

			if (structureBoundingBoxIn.isVecInside(blockpos))
			{
				world.setBlockState(blockpos, Blocks.SPAWNER.getDefaultState(), 2);
				TileEntity tileentity = world.getTileEntity(blockpos);

				if (tileentity instanceof MobSpawnerTileEntity)
				{
					((MobSpawnerTileEntity) tileentity).getSpawnerBaseLogic().setEntityType(EntityType.SILVERFISH);
				}
			}

			return true;
		}
	}

	public static class Prison extends RSStrongholdPieces.Stronghold
	{
		public Prison(int p_i45576_1_, Random p_i45576_2_, MutableBoundingBox p_i45576_3_, Direction p_i45576_4_)
		{
			super(StructurePieces.SHPHRS, p_i45576_1_);
			this.setCoordBaseMode(p_i45576_4_);
			this.entryDoor = this.getRandomDoor(p_i45576_2_);
			this.boundingBox = p_i45576_3_;
		}


		public Prison(TemplateManager p_i50130_1_, CompoundNBT p_i50130_2_)
		{
			super(StructurePieces.SHPHRS, p_i50130_2_);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentNormal((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, 1, 1);
		}


		public static RSStrongholdPieces.Prison createPiece(List<StructurePiece> p_175860_0_, Random p_175860_1_, int p_175860_2_, int p_175860_3_, int p_175860_4_, Direction p_175860_5_, int p_175860_6_)
		{
			MutableBoundingBox mutableboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p_175860_2_, p_175860_3_, p_175860_4_, -1, -1, 0, 9, 5, 11, p_175860_5_);
			return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.findIntersecting(p_175860_0_, mutableboundingbox) == null ? new RSStrongholdPieces.Prison(p_175860_6_, p_175860_1_, mutableboundingbox, p_175860_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 0, 0, 0, 8, 4, 10, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 1, 1, 0);
			this.fillWithBlocks(world, structureBoundingBoxIn, 1, 1, 10, 3, 3, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 4, 1, 1, 4, 3, 1, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 4, 1, 3, 4, 3, 3, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 4, 1, 7, 4, 3, 7, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 4, 1, 9, 4, 3, 9, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.fillWithBlocks(world, structureBoundingBoxIn, 4, 1, 4, 4, 3, 6, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
			this.fillWithBlocks(world, structureBoundingBoxIn, 5, 1, 5, 7, 3, 5, Blocks.IRON_BARS.getDefaultState(), Blocks.IRON_BARS.getDefaultState(), false);
			this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), 4, 3, 2, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), 4, 3, 8, structureBoundingBoxIn);
			BlockState iblockstate = Blocks.IRON_DOOR.getDefaultState().with(DoorBlock.FACING, Direction.WEST);
			BlockState iblockstate1 = Blocks.IRON_DOOR.getDefaultState().with(DoorBlock.FACING, Direction.WEST).with(DoorBlock.HALF, DoubleBlockHalf.UPPER);
			this.setBlockState(world, iblockstate, 4, 1, 2, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate1, 4, 2, 2, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate, 4, 1, 8, structureBoundingBoxIn);
			this.setBlockState(world, iblockstate1, 4, 2, 8, structureBoundingBoxIn);
			return true;
		}
	}

	public static class RightTurn extends RSStrongholdPieces.Turn
	{
		public RightTurn(int p_i50127_1_, Random p_i50127_2_, MutableBoundingBox p_i50127_3_, Direction p_i50127_4_)
		{
			super(StructurePieces.SHRTRS, p_i50127_1_);
			this.setCoordBaseMode(p_i50127_4_);
			this.entryDoor = this.getRandomDoor(p_i50127_2_);
			this.boundingBox = p_i50127_3_;
		}


		public RightTurn(TemplateManager p_i50128_1_, CompoundNBT p_i50128_2_)
		{
			super(StructurePieces.SHRTRS, p_i50128_2_);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			Direction enumfacing = this.getCoordBaseMode();

			if (enumfacing != Direction.NORTH && enumfacing != Direction.EAST)
			{
				this.getNextComponentX((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, 1, 1);
			}
			else
			{
				this.getNextComponentZ((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, 1, 1);
			}

		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 0, 0, 0, 4, 4, 4, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 1, 1, 0);
			Direction enumfacing = this.getCoordBaseMode();

			if (enumfacing != Direction.NORTH && enumfacing != Direction.EAST)
			{
				this.fillWithBlocks(world, structureBoundingBoxIn, 0, 1, 1, 0, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
			}
			else
			{
				this.fillWithBlocks(world, structureBoundingBoxIn, 4, 1, 1, 4, 3, 3, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
			}

			return true;
		}


		public static RSStrongholdPieces.RightTurn createPiece(List<StructurePiece> p_214824_0_, Random p_214824_1_, int p_214824_2_, int p_214824_3_, int p_214824_4_, Direction p_214824_5_, int p_214824_6_)
		{
			MutableBoundingBox mutableboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p_214824_2_, p_214824_3_, p_214824_4_, -1, -1, 0, 5, 5, 5, p_214824_5_);
			return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.findIntersecting(p_214824_0_, mutableboundingbox) == null ? new RSStrongholdPieces.RightTurn(p_214824_6_, p_214824_1_, mutableboundingbox, p_214824_5_) : null;
		}
	}

	public static class RoomCrossing extends RSStrongholdPieces.Stronghold
	{
		protected int roomType;


		public RoomCrossing(int p_i45575_1_, Random p_i45575_2_, MutableBoundingBox p_i45575_3_, Direction p_i45575_4_)
		{
			super(StructurePieces.SHRCRS, p_i45575_1_);
			this.setCoordBaseMode(p_i45575_4_);
			this.entryDoor = this.getRandomDoor(p_i45575_2_);
			this.boundingBox = p_i45575_3_;
			this.roomType = p_i45575_2_.nextInt(5);
		}


		public RoomCrossing(TemplateManager p_i50125_1_, CompoundNBT p_i50125_2_)
		{
			super(StructurePieces.SHRCRS, p_i50125_2_);
			this.roomType = p_i50125_2_.getInt("Type");
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
			tagCompound.putInt("Type", this.roomType);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentNormal((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, 4, 1);
			this.getNextComponentX((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, 1, 4);
			this.getNextComponentZ((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, 1, 4);
		}


		public static RSStrongholdPieces.RoomCrossing createPiece(List<StructurePiece> p_175859_0_, Random p_175859_1_, int p_175859_2_, int p_175859_3_, int p_175859_4_, Direction p_175859_5_, int p_175859_6_)
		{
			MutableBoundingBox mutableboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p_175859_2_, p_175859_3_, p_175859_4_, -4, -1, 0, 11, 7, 11, p_175859_5_);
			return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.findIntersecting(p_175859_0_, mutableboundingbox) == null ? new RSStrongholdPieces.RoomCrossing(p_175859_6_, p_175859_1_, mutableboundingbox, p_175859_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 0, 0, 0, 10, 6, 10, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 4, 1, 0);
			this.fillWithBlocks(world, structureBoundingBoxIn, 4, 1, 10, 6, 3, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
			this.fillWithBlocks(world, structureBoundingBoxIn, 0, 1, 4, 0, 3, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
			this.fillWithBlocks(world, structureBoundingBoxIn, 10, 1, 4, 10, 3, 6, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
			BlockPos blockpos;
			
			switch (this.roomType)
			{
				case 0:

					blockpos = new BlockPos(this.getXWithOffset(5, 5), this.getYWithOffset(1), this.getZWithOffset(5, 5));

					if (structureBoundingBoxIn.isVecInside(blockpos))
					{

						if (RSConfig.allowExtraSilverfishSpawnerSH)
						{
							world.setBlockState(blockpos, Blocks.SPAWNER.getDefaultState(), 2);
							TileEntity tileentity = world.getTileEntity(blockpos);

							if (tileentity instanceof MobSpawnerTileEntity)
							{
								((MobSpawnerTileEntity) tileentity).getSpawnerBaseLogic().setEntityType(EntityType.SILVERFISH);
							}
						}
						else
						{
							world.setBlockState(blockpos, Blocks.STONE_BRICKS.getDefaultState(), 2);
						}
					}

					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 5, 2, 5, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 5, 3, 5, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.WEST), 4, 3, 5, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.EAST), 6, 3, 5, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.SOUTH), 5, 3, 4, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.NORTH), 5, 3, 6, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.STONE_SLAB.getDefaultState(), 4, 1, 4, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.STONE_SLAB.getDefaultState(), 4, 1, 5, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.STONE_SLAB.getDefaultState(), 4, 1, 6, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.STONE_SLAB.getDefaultState(), 6, 1, 4, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.STONE_SLAB.getDefaultState(), 6, 1, 5, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.STONE_SLAB.getDefaultState(), 6, 1, 6, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.STONE_SLAB.getDefaultState(), 5, 1, 4, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.STONE_SLAB.getDefaultState(), 5, 1, 6, structureBoundingBoxIn);
					break;

				case 1:
					for (int i1 = 0; i1 < 5; ++i1)
					{
						this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 3, 1, 3 + i1, structureBoundingBoxIn);
						this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 7, 1, 3 + i1, structureBoundingBoxIn);
						this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 3 + i1, 1, 3, structureBoundingBoxIn);
						this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 3 + i1, 1, 7, structureBoundingBoxIn);
					}

					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 5, 2, 5, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 5, 3, 5, structureBoundingBoxIn);

					blockpos = new BlockPos(this.getXWithOffset(5, 5), this.getYWithOffset(1), this.getZWithOffset(5, 5));

					if (structureBoundingBoxIn.isVecInside(blockpos))
					{
						if (RSConfig.allowExtraSilverfishSpawnerSH)
						{
							world.setBlockState(blockpos, Blocks.SPAWNER.getDefaultState(), 2);
							TileEntity tileentity = world.getTileEntity(blockpos);

							if (tileentity instanceof MobSpawnerTileEntity)
							{
								((MobSpawnerTileEntity) tileentity).getSpawnerBaseLogic().setEntityType(EntityType.SILVERFISH);
							}
						}
						else
						{
							world.setBlockState(blockpos, Blocks.STONE_BRICKS.getDefaultState(), 2);
						}
					}

					this.setBlockState(world, Blocks.WATER.getDefaultState(), 5, 4, 5, structureBoundingBoxIn);
					break;

				case 2:
				case 3:

					for (int i = 1; i <= 9; ++i)
					{
						this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), 1, 3, i, structureBoundingBoxIn);
						this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), 9, 3, i, structureBoundingBoxIn);
					}

					for (int j = 1; j <= 9; ++j)
					{
						this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), j, 3, 1, structureBoundingBoxIn);
						this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), j, 3, 9, structureBoundingBoxIn);
					}

					this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), 5, 1, 4, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), 5, 1, 6, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), 5, 3, 4, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), 5, 3, 6, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), 4, 1, 5, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), 6, 1, 5, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), 4, 3, 5, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), 6, 3, 5, structureBoundingBoxIn);

					for (int k = 1; k <= 3; ++k)
					{
						this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), 4, k, 4, structureBoundingBoxIn);
						this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), 6, k, 4, structureBoundingBoxIn);
						this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), 4, k, 6, structureBoundingBoxIn);
						this.setBlockState(world, Blocks.COBBLESTONE.getDefaultState(), 6, k, 6, structureBoundingBoxIn);
					}

					blockpos = new BlockPos(this.getXWithOffset(5, 5), this.getYWithOffset(1), this.getZWithOffset(5, 5));

					if (structureBoundingBoxIn.isVecInside(blockpos))
					{

						if (RSConfig.allowExtraSilverfishSpawnerSH)
						{
							world.setBlockState(blockpos, Blocks.SPAWNER.getDefaultState(), 2);
							TileEntity tileentity = world.getTileEntity(blockpos);

							if (tileentity instanceof MobSpawnerTileEntity)
							{
								((MobSpawnerTileEntity) tileentity).getSpawnerBaseLogic().setEntityType(EntityType.SILVERFISH);
							}
						}
						else
						{
							world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
						}
					}

					this.setBlockState(world, Blocks.TORCH.getDefaultState(), 5, 3, 5, structureBoundingBoxIn);

					for (int l = 2; l <= 8; ++l)
					{
						this.setBlockState(world, Blocks.OAK_PLANKS.getDefaultState(), 2, 3, l, structureBoundingBoxIn);
						this.setBlockState(world, Blocks.OAK_PLANKS.getDefaultState(), 3, 3, l, structureBoundingBoxIn);
						if (l <= 3 || l >= 7)
						{
							this.setBlockState(world, Blocks.OAK_PLANKS.getDefaultState(), 4, 3, l, structureBoundingBoxIn);
							this.setBlockState(world, Blocks.OAK_PLANKS.getDefaultState(), 5, 3, l, structureBoundingBoxIn);
							this.setBlockState(world, Blocks.OAK_PLANKS.getDefaultState(), 6, 3, l, structureBoundingBoxIn);
						}

						this.setBlockState(world, Blocks.OAK_PLANKS.getDefaultState(), 7, 3, l, structureBoundingBoxIn);
						this.setBlockState(world, Blocks.OAK_PLANKS.getDefaultState(), 8, 3, l, structureBoundingBoxIn);
					}

					BlockState iblockstate = Blocks.LADDER.getDefaultState().with(LadderBlock.FACING, Direction.WEST);
					this.setBlockState(world, iblockstate, 9, 1, 3, structureBoundingBoxIn);
					this.setBlockState(world, iblockstate, 9, 2, 3, structureBoundingBoxIn);
					this.setBlockState(world, iblockstate, 9, 3, 3, structureBoundingBoxIn);

					if (RSConfig.lootChestsSH)
					{
						this.generateChest(world, structureBoundingBoxIn, random, 3, 4, 8, LootTables.CHESTS_STRONGHOLD_CROSSING);
						this.generateChest(world, structureBoundingBoxIn, random, 5, 4, 2, LootTables.CHESTS_STRONGHOLD_CROSSING);
						this.generateChest(world, structureBoundingBoxIn, random, 6, 4, 8, LootTables.CHESTS_STRONGHOLD_CROSSING);
						this.generateChest(world, structureBoundingBoxIn, random, 8, 4, 4, LootTables.CHESTS_STRONGHOLD_CROSSING);
					}
			}

			return true;
		}
	}

	public static class Stairs extends RSStrongholdPieces.Stronghold
	{
		private boolean source;


		public Stairs(IStructurePieceType p_i50120_1_, int p_i50120_2_, Random p_i50120_3_, int p_i50120_4_, int p_i50120_5_)
		{
			super(p_i50120_1_, p_i50120_2_);
			this.source = true;
			this.setCoordBaseMode(Direction.Plane.HORIZONTAL.random(p_i50120_3_));
			this.entryDoor = RSStrongholdPieces.Stronghold.Door.OPENING;
			if (this.getCoordBaseMode().getAxis() == Direction.Axis.Z)
			{
				this.boundingBox = new MutableBoundingBox(p_i50120_4_, 64, p_i50120_5_, p_i50120_4_ + 5 - 1, 74, p_i50120_5_ + 5 - 1);
			}
			else
			{
				this.boundingBox = new MutableBoundingBox(p_i50120_4_, 64, p_i50120_5_, p_i50120_4_ + 5 - 1, 74, p_i50120_5_ + 5 - 1);
			}

		}


		public Stairs(int p_i45574_1_, Random p_i45574_2_, MutableBoundingBox p_i45574_3_, Direction p_i45574_4_)
		{
			super(StructurePieces.SHSDRS, p_i45574_1_);
			this.source = false;
			this.setCoordBaseMode(p_i45574_4_);
			this.entryDoor = this.getRandomDoor(p_i45574_2_);
			this.boundingBox = p_i45574_3_;
		}


		public Stairs(IStructurePieceType p_i50121_1_, CompoundNBT p_i50121_2_)
		{
			super(p_i50121_1_, p_i50121_2_);
			this.source = p_i50121_2_.getBoolean("Source");
		}


		public Stairs(TemplateManager p_i50122_1_, CompoundNBT p_i50122_2_)
		{
			this(StructurePieces.SHSDRS, p_i50122_2_);
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
			tagCompound.putBoolean("Source", this.source);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			if (this.source)
			{
				RSStrongholdPieces.strongComponentType = RSStrongholdPieces.Crossing.class;
			}

			this.getNextComponentNormal((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, 1, 1);
		}


		public static RSStrongholdPieces.Stairs createPiece(List<StructurePiece> p_175863_0_, Random p_175863_1_, int p_175863_2_, int p_175863_3_, int p_175863_4_, Direction p_175863_5_, int p_175863_6_)
		{
			MutableBoundingBox mutableboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p_175863_2_, p_175863_3_, p_175863_4_, -1, -7, 0, 5, 11, 5, p_175863_5_);
			return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.findIntersecting(p_175863_0_, mutableboundingbox) == null ? new RSStrongholdPieces.Stairs(p_175863_6_, p_175863_1_, mutableboundingbox, p_175863_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 0, 0, 0, 4, 10, 4, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 1, 7, 0);
			this.placeDoor(world, random, structureBoundingBoxIn, RSStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 4);
			this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 2, 6, 1, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 5, 1, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_SLAB.getDefaultState(), 1, 6, 1, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 5, 2, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 4, 3, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_SLAB.getDefaultState(), 1, 5, 3, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 2, 4, 3, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 3, 3, 3, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_SLAB.getDefaultState(), 3, 4, 3, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 3, 3, 2, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 3, 2, 1, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_SLAB.getDefaultState(), 3, 3, 1, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 2, 2, 1, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 1, 1, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_SLAB.getDefaultState(), 1, 2, 1, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 1, 2, structureBoundingBoxIn);
			this.setBlockState(world, Blocks.STONE_SLAB.getDefaultState(), 1, 1, 3, structureBoundingBoxIn);
			return true;
		}
	}

	public static class Stairs2 extends RSStrongholdPieces.Stairs
	{
		public RSStrongholdPieces.PieceWeight strongholdPieceWeight;
		@Nullable
		public RSStrongholdPieces.PortalRoom strongholdPortalRoom;
		public List<StructurePiece> pendingChildren = Lists.<StructurePiece>newArrayList();


		public Stairs2(Random p_i50117_1_, int p_i50117_2_, int p_i50117_3_)
		{
			super(StructurePieces.SHSTARTRS, 0, p_i50117_1_, p_i50117_2_, p_i50117_3_);
		}


		public Stairs2(TemplateManager p_i50118_1_, CompoundNBT p_i50118_2_)
		{
			super(StructurePieces.SHSTARTRS, p_i50118_2_);
		}
	}

	public static class StairsStraight extends RSStrongholdPieces.Stronghold
	{
		public StairsStraight(int p_i45572_1_, Random p_i45572_2_, MutableBoundingBox p_i45572_3_, Direction p_i45572_4_)
		{
			super(StructurePieces.SHSSDRS, p_i45572_1_);
			this.setCoordBaseMode(p_i45572_4_);
			this.entryDoor = this.getRandomDoor(p_i45572_2_);
			this.boundingBox = p_i45572_3_;
		}


		public StairsStraight(TemplateManager p_i50113_1_, CompoundNBT p_i50113_2_)
		{
			super(StructurePieces.SHSSDRS, p_i50113_2_);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentNormal((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, 1, 1);
		}


		public static RSStrongholdPieces.StairsStraight createPiece(List<StructurePiece> p_175861_0_, Random p_175861_1_, int p_175861_2_, int p_175861_3_, int p_175861_4_, Direction p_175861_5_, int p_175861_6_)
		{
			MutableBoundingBox mutableboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p_175861_2_, p_175861_3_, p_175861_4_, -1, -7, 0, 5, 11, 8, p_175861_5_);
			return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.findIntersecting(p_175861_0_, mutableboundingbox) == null ? new RSStrongholdPieces.StairsStraight(p_175861_6_, p_175861_1_, mutableboundingbox, p_175861_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 0, 0, 0, 4, 10, 7, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 1, 7, 0);
			this.placeDoor(world, random, structureBoundingBoxIn, RSStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 7);
			BlockState iblockstate = Blocks.STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.SOUTH);

			for (int i = 0; i < 6; ++i)
			{
				this.setBlockState(world, iblockstate, 1, 6 - i, 1 + i, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate, 2, 6 - i, 1 + i, structureBoundingBoxIn);
				this.setBlockState(world, iblockstate, 3, 6 - i, 1 + i, structureBoundingBoxIn);

				if (i < 5)
				{
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 1, 5 - i, 1 + i, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 2, 5 - i, 1 + i, structureBoundingBoxIn);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), 3, 5 - i, 1 + i, structureBoundingBoxIn);
				}
			}

			return true;
		}
	}

	static class Stones extends StructurePiece.BlockSelector
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
		
		private Stones()
		{
		}


		@Override
		public void selectBlocks(Random rand, int x, int y, int z, boolean notAir)
		{
			if (notAir)
			{
				float chance = rand.nextFloat();
				if (chance < 0.2F)
				{
					this.blockstate = Blocks.MOSSY_STONE_BRICKS.getDefaultState();
				}
				else if (chance < 0.5F)
				{
					this.blockstate = Blocks.CRACKED_STONE_BRICKS.getDefaultState();
				}
				else
				{
					this.blockstate = Blocks.STONE_BRICKS.getDefaultState();
				}

				
				chance = rand.nextFloat();
				float silverfishThreshold = (float) (RSConfig.silverfishSpawnrateSH / 100);
				if(chance < silverfishThreshold)
				{
					this.blockstate = INFESTED_STONE_LOOKUP.get(this.blockstate);
				}
			}
			else
			{
				this.blockstate = Blocks.CAVE_AIR.getDefaultState();
			}
		}
	}

	public static class Straight extends RSStrongholdPieces.Stronghold
	{
		private boolean expandsX;
		private boolean expandsZ;


		public Straight(int p_i45573_1_, Random p_i45573_2_, MutableBoundingBox p_i45573_3_, Direction p_i45573_4_)
		{
			super(StructurePieces.SHSRS, p_i45573_1_);
			this.setCoordBaseMode(p_i45573_4_);
			this.entryDoor = this.getRandomDoor(p_i45573_2_);
			this.boundingBox = p_i45573_3_;
			this.expandsX = p_i45573_2_.nextInt(2) == 0;
			this.expandsZ = p_i45573_2_.nextInt(2) == 0;
		}


		public Straight(TemplateManager p_i50115_1_, CompoundNBT p_i50115_2_)
		{
			super(StructurePieces.SHSRS, p_i50115_2_);
			this.expandsX = p_i50115_2_.getBoolean("Left");
			this.expandsZ = p_i50115_2_.getBoolean("Right");
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
			tagCompound.putBoolean("Left", this.expandsX);
			tagCompound.putBoolean("Right", this.expandsZ);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			this.getNextComponentNormal((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, 1, 1);

			if (this.expandsX)
			{
				this.getNextComponentX((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, 1, 2);
			}

			if (this.expandsZ)
			{
				this.getNextComponentZ((RSStrongholdPieces.Stairs2) componentIn, listIn, rand, 1, 2);
			}
		}


		public static RSStrongholdPieces.Straight createPiece(List<StructurePiece> p_175862_0_, Random p_175862_1_, int p_175862_2_, int p_175862_3_, int p_175862_4_, Direction p_175862_5_, int p_175862_6_)
		{
			MutableBoundingBox mutableboundingbox = MutableBoundingBox.getComponentToAddBoundingBox(p_175862_2_, p_175862_3_, p_175862_4_, -1, -1, 0, 5, 5, 7, p_175862_5_);
			return canStrongholdGoDeeper(mutableboundingbox) && StructurePiece.findIntersecting(p_175862_0_, mutableboundingbox) == null ? new RSStrongholdPieces.Straight(p_175862_6_, p_175862_1_, mutableboundingbox, p_175862_5_) : null;
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox structureBoundingBoxIn, ChunkPos p_74875_4_)
		{
			this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 0, 0, 0, 4, 4, 6, false, random, RSStrongholdPieces.STRONGHOLD_STONES);
			this.placeDoor(world, random, structureBoundingBoxIn, this.entryDoor, 1, 1, 0);
			this.placeDoor(world, random, structureBoundingBoxIn, RSStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 6);
			BlockState iblockstate = Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.EAST);
			BlockState iblockstate1 = Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.WEST);
			this.randomlyPlaceBlock(world, structureBoundingBoxIn, random, 0.1F, 1, 2, 1, iblockstate);
			this.randomlyPlaceBlock(world, structureBoundingBoxIn, random, 0.1F, 3, 2, 1, iblockstate1);
			this.randomlyPlaceBlock(world, structureBoundingBoxIn, random, 0.1F, 1, 2, 5, iblockstate);
			this.randomlyPlaceBlock(world, structureBoundingBoxIn, random, 0.1F, 3, 2, 5, iblockstate1);

			if (this.expandsX)
			{
				this.fillWithBlocks(world, structureBoundingBoxIn, 0, 1, 2, 0, 3, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
			}

			if (this.expandsZ)
			{
				this.fillWithBlocks(world, structureBoundingBoxIn, 4, 1, 2, 4, 3, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
			}

			return true;
		}
	}

	abstract static class Stronghold extends StructurePiece
	{
		protected RSStrongholdPieces.Stronghold.Door entryDoor = RSStrongholdPieces.Stronghold.Door.OPENING;


		protected Stronghold(IStructurePieceType p_i50110_1_, int p_i50110_2_)
		{
			super(p_i50110_1_, p_i50110_2_);
		}


		public Stronghold(IStructurePieceType p_i50111_1_, CompoundNBT p_i50111_2_)
		{
			super(p_i50111_1_, p_i50111_2_);
			this.entryDoor = RSStrongholdPieces.Stronghold.Door.valueOf(p_i50111_2_.getString("EntryDoor"));
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			tagCompound.putString("EntryDoor", this.entryDoor.name());
		}


		protected void placeDoor(IWorld world, Random p_74990_2_, MutableBoundingBox p_74990_3_, RSStrongholdPieces.Stronghold.Door p_74990_4_, int p_74990_5_, int p_74990_6_, int p_74990_7_)
		{
			switch (p_74990_4_)
			{
				case OPENING:
					this.fillWithBlocks(world, p_74990_3_, p_74990_5_, p_74990_6_, p_74990_7_, p_74990_5_ + 3 - 1, p_74990_6_ + 3 - 1, p_74990_7_, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
					break;

				case WOOD_DOOR:
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.OAK_DOOR.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.OAK_DOOR.getDefaultState().with(DoorBlock.HALF, DoubleBlockHalf.UPPER), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
					break;

				case GRATES:
					this.setBlockState(world, Blocks.AIR.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.AIR.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.IRON_BARS.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
					break;

				case IRON_DOOR:
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.STONE_BRICKS.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.IRON_DOOR.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.IRON_DOOR.getDefaultState().with(DoorBlock.HALF, DoubleBlockHalf.UPPER), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
					this.setBlockState(world, Blocks.STONE_BUTTON.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.NORTH), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_ + 1, p_74990_3_);
					this.setBlockState(world, Blocks.STONE_BUTTON.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.SOUTH), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_ - 1, p_74990_3_);
			}
		}


		protected RSStrongholdPieces.Stronghold.Door getRandomDoor(Random p_74988_1_)
		{
			int i = p_74988_1_.nextInt(5);

			switch (i)
			{
				case 0:
				case 1:
				default:
					return RSStrongholdPieces.Stronghold.Door.OPENING;

				case 2:
					return RSStrongholdPieces.Stronghold.Door.WOOD_DOOR;

				case 3:
					return RSStrongholdPieces.Stronghold.Door.GRATES;

				case 4:
					return RSStrongholdPieces.Stronghold.Door.IRON_DOOR;
			}
		}


		@Nullable
		protected StructurePiece getNextComponentNormal(RSStrongholdPieces.Stairs2 p_74986_1_, List<StructurePiece> p_74986_2_, Random p_74986_3_, int p_74986_4_, int p_74986_5_)
		{
			Direction enumfacing = this.getCoordBaseMode();

			if (enumfacing != null)
			{
				switch (enumfacing)
				{
					case NORTH:
						return RSStrongholdPieces.generateAndAddPiece(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX + p_74986_4_, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ - 1, enumfacing, this.getComponentType());

					case SOUTH:
						return RSStrongholdPieces.generateAndAddPiece(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX + p_74986_4_, this.boundingBox.minY + p_74986_5_, this.boundingBox.maxZ + 1, enumfacing, this.getComponentType());

					case WEST:
						return RSStrongholdPieces.generateAndAddPiece(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ + p_74986_4_, enumfacing, this.getComponentType());

					case EAST:
						return RSStrongholdPieces.generateAndAddPiece(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ + p_74986_4_, enumfacing, this.getComponentType());

					default:
						break;
				}
			}

			return null;
		}


		@Nullable
		protected StructurePiece getNextComponentX(RSStrongholdPieces.Stairs2 p_74989_1_, List<StructurePiece> p_74989_2_, Random p_74989_3_, int p_74989_4_, int p_74989_5_)
		{
			Direction enumfacing = this.getCoordBaseMode();

			if (enumfacing != null)
			{
				switch (enumfacing)
				{
					case NORTH:
						return RSStrongholdPieces.generateAndAddPiece(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ + p_74989_5_, Direction.WEST, this.getComponentType());

					case SOUTH:
						return RSStrongholdPieces.generateAndAddPiece(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ + p_74989_5_, Direction.WEST, this.getComponentType());

					case WEST:
						return RSStrongholdPieces.generateAndAddPiece(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX + p_74989_5_, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ - 1, Direction.NORTH, this.getComponentType());

					case EAST:
						return RSStrongholdPieces.generateAndAddPiece(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX + p_74989_5_, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ - 1, Direction.NORTH, this.getComponentType());

					default:
						break;
				}
			}

			return null;
		}


		@Nullable
		protected StructurePiece getNextComponentZ(RSStrongholdPieces.Stairs2 p_74987_1_, List<StructurePiece> p_74987_2_, Random p_74987_3_, int p_74987_4_, int p_74987_5_)
		{
			Direction enumfacing = this.getCoordBaseMode();

			if (enumfacing != null)
			{
				switch (enumfacing)
				{
					case NORTH:
						return RSStrongholdPieces.generateAndAddPiece(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74987_4_, this.boundingBox.minZ + p_74987_5_, Direction.EAST, this.getComponentType());

					case SOUTH:
						return RSStrongholdPieces.generateAndAddPiece(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74987_4_, this.boundingBox.minZ + p_74987_5_, Direction.EAST, this.getComponentType());

					case WEST:
						return RSStrongholdPieces.generateAndAddPiece(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.minX + p_74987_5_, this.boundingBox.minY + p_74987_4_, this.boundingBox.maxZ + 1, Direction.SOUTH, this.getComponentType());

					case EAST:
						return RSStrongholdPieces.generateAndAddPiece(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.minX + p_74987_5_, this.boundingBox.minY + p_74987_4_, this.boundingBox.maxZ + 1, Direction.SOUTH, this.getComponentType());

					default:
						break;
				}
			}

			return null;
		}


		protected static boolean canStrongholdGoDeeper(MutableBoundingBox p_74991_0_)
		{
			return p_74991_0_ != null && p_74991_0_.minY > 10;
		}

		public static enum Door
		{
			OPENING, WOOD_DOOR, GRATES, IRON_DOOR;
		}
	}

	public abstract static class Turn extends RSStrongholdPieces.Stronghold
	{
		protected Turn(IStructurePieceType p_i50108_1_, int p_i50108_2_)
		{
			super(p_i50108_1_, p_i50108_2_);
		}


		public Turn(IStructurePieceType p_i50109_1_, CompoundNBT p_i50109_2_)
		{
			super(p_i50109_1_, p_i50109_2_);
		}
	}
}
