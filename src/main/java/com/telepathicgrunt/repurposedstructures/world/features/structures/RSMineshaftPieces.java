package com.telepathicgrunt.repurposedstructures.world.features.structures;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RSConfig;
import com.telepathicgrunt.repurposedstructures.world.features.structures.RSMineshaftStructure.Type;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.RailBlock;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.minecart.ChestMinecartEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.state.properties.RailShape;
import net.minecraft.state.properties.RedstoneSide;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTables;


public class RSMineshaftPieces
{

	private static RSMineshaftPieces.Piece createRandomShaftPiece(List<StructurePiece> p_189940_0_, Random p_189940_1_, int p_189940_2_, int p_189940_3_, int p_189940_4_, @Nullable Direction p_189940_5_, int p_189940_6_, RSMineshaftStructure.Type p_189940_7_)
	{
		int i = p_189940_1_.nextInt(100);

		if (i >= 80)
		{
			MutableBoundingBox MutableBoundingBox = RSMineshaftPieces.Cross.findCrossing(p_189940_0_, p_189940_1_, p_189940_2_, p_189940_3_, p_189940_4_, p_189940_5_);

			if (MutableBoundingBox != null)
			{
				return new RSMineshaftPieces.Cross(p_189940_6_, MutableBoundingBox, p_189940_5_, p_189940_7_);
			}
		}
		else if (i >= 70)
		{
			MutableBoundingBox MutableBoundingBox1 = RSMineshaftPieces.Stairs.findStairs(p_189940_0_, p_189940_1_, p_189940_2_, p_189940_3_, p_189940_4_, p_189940_5_);

			if (MutableBoundingBox1 != null)
			{
				return new RSMineshaftPieces.Stairs(p_189940_6_, MutableBoundingBox1, p_189940_5_, p_189940_7_);
			}
		}
		else
		{
			MutableBoundingBox MutableBoundingBox2 = RSMineshaftPieces.Corridor.findCorridorSize(p_189940_0_, p_189940_1_, p_189940_2_, p_189940_3_, p_189940_4_, p_189940_5_);

			if (MutableBoundingBox2 != null)
			{
				return new RSMineshaftPieces.Corridor(p_189940_6_, p_189940_1_, MutableBoundingBox2, p_189940_5_, p_189940_7_);
			}
		}

		return null;
	}


	private static RSMineshaftPieces.Piece generateAndAddPiece(StructurePiece p_189938_0_, List<StructurePiece> p_189938_1_, Random p_189938_2_, int p_189938_3_, int p_189938_4_, int p_189938_5_, Direction p_189938_6_, int p_189938_7_)
	{
		if (p_189938_7_ > 8)
		{
			return null;
		}
		else if (Math.abs(p_189938_3_ - p_189938_0_.getBoundingBox().minX) <= 80 && Math.abs(p_189938_5_ - p_189938_0_.getBoundingBox().minZ) <= 80)
		{
			RSMineshaftStructure.Type mapgenmineshaft$type = ((RSMineshaftPieces.Piece) p_189938_0_).mineShaftType;
			RSMineshaftPieces.Piece structuremineshaftpieces$peice = createRandomShaftPiece(p_189938_1_, p_189938_2_, p_189938_3_, p_189938_4_, p_189938_5_, p_189938_6_, p_189938_7_ + 1, mapgenmineshaft$type);

			if (structuremineshaftpieces$peice != null)
			{
				p_189938_1_.add(structuremineshaftpieces$peice);
				structuremineshaftpieces$peice.buildComponent(p_189938_0_, p_189938_1_, p_189938_2_);
			}

			return structuremineshaftpieces$peice;
		}
		else
		{
			return null;
		}
	}

	public static class Corridor extends RSMineshaftPieces.Piece
	{
		private boolean hasRails;
		private boolean hasSpiders;
		private boolean spawnerPlaced;
		private int sectionCount;


		public Corridor(TemplateManager p_i50456_1_, CompoundNBT p_i50456_2_)
		{
			super(StructurePieces.MSCORRIDORRS, p_i50456_2_);
			this.hasRails = p_i50456_2_.getBoolean("hr");
			this.hasSpiders = p_i50456_2_.getBoolean("sc");
			this.spawnerPlaced = p_i50456_2_.getBoolean("hps");
			this.sectionCount = p_i50456_2_.getInt("Num");
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
			tagCompound.putBoolean("hr", this.hasRails);
			tagCompound.putBoolean("sc", this.hasSpiders);
			tagCompound.putBoolean("hps", this.spawnerPlaced);
			tagCompound.putInt("Num", this.sectionCount);
		}


		public Corridor(int p_i47140_1_, Random p_i47140_2_, MutableBoundingBox p_i47140_3_, Direction p_i47140_4_, RSMineshaftStructure.Type p_i47140_5_)
		{
			super(StructurePieces.MSCORRIDORRS, p_i47140_1_, p_i47140_5_);
			this.setCoordBaseMode(p_i47140_4_);
			this.boundingBox = p_i47140_3_;
			this.hasRails = p_i47140_2_.nextInt(3) == 0;
			this.hasSpiders = !this.hasRails && p_i47140_2_.nextInt(23) == 0;
			if (this.getCoordBaseMode().getAxis() == Direction.Axis.Z)
			{
				this.sectionCount = p_i47140_3_.getZSize() / 5;
			}
			else
			{
				this.sectionCount = p_i47140_3_.getXSize() / 5;
			}

		}


		public static MutableBoundingBox findCorridorSize(List<StructurePiece> p_175814_0_, Random rand, int x, int y, int z, Direction facing)
		{
			MutableBoundingBox MutableBoundingBox = new MutableBoundingBox(x, y, z, x, y + 2, z);
			int i;

			for (i = rand.nextInt(3) + 2; i > 0; --i)
			{
				int j = i * 5;

				switch (facing)
				{
					case NORTH:
					default:
						MutableBoundingBox.maxX = x + 2;
						MutableBoundingBox.minZ = z - (j - 1);
						break;

					case SOUTH:
						MutableBoundingBox.maxX = x + 2;
						MutableBoundingBox.maxZ = z + (j - 1);
						break;

					case WEST:
						MutableBoundingBox.minX = x - (j - 1);
						MutableBoundingBox.maxZ = z + 2;
						break;

					case EAST:
						MutableBoundingBox.maxX = x + (j - 1);
						MutableBoundingBox.maxZ = z + 2;
				}

				if (StructurePiece.findIntersecting(p_175814_0_, MutableBoundingBox) == null)
				{
					break;
				}
			}

			return i > 0 ? MutableBoundingBox : null;
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			int i = this.getComponentType();
			int j = rand.nextInt(4);
			Direction enumfacing = this.getCoordBaseMode();

			if (enumfacing != null)
			{
				switch (enumfacing)
				{
					case NORTH:
					default:
						if (j <= 1)
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, enumfacing, i);
						}
						else if (j == 2)
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, Direction.WEST, i);
						}
						else
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, Direction.EAST, i);
						}

						break;

					case SOUTH:
						if (j <= 1)
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, enumfacing, i);
						}
						else if (j == 2)
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ - 3, Direction.WEST, i);
						}
						else
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ - 3, Direction.EAST, i);
						}

						break;

					case WEST:
						if (j <= 1)
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, enumfacing, i);
						}
						else if (j == 2)
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, Direction.NORTH, i);
						}
						else
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, Direction.SOUTH, i);
						}

						break;

					case EAST:
						if (j <= 1)
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, enumfacing, i);
						}
						else if (j == 2)
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, Direction.NORTH, i);
						}
						else
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, Direction.SOUTH, i);
						}
				}
			}

			if (i < 8)
			{
				if (enumfacing != Direction.NORTH && enumfacing != Direction.SOUTH)
				{
					for (int i1 = this.boundingBox.minX + 3; i1 + 3 <= this.boundingBox.maxX; i1 += 5)
					{
						int j1 = rand.nextInt(5);

						if (j1 == 0)
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, i1, this.boundingBox.minY, this.boundingBox.minZ - 1, Direction.NORTH, i + 1);
						}
						else if (j1 == 1)
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, i1, this.boundingBox.minY, this.boundingBox.maxZ + 1, Direction.SOUTH, i + 1);
						}
					}
				}
				else
				{
					for (int k = this.boundingBox.minZ + 3; k + 3 <= this.boundingBox.maxZ; k += 5)
					{
						int l = rand.nextInt(5);

						if (l == 0)
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, k, Direction.WEST, i + 1);
						}
						else if (l == 1)
						{
							RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, k, Direction.EAST, i + 1);
						}
					}
				}
			}
		}


		@Override
		protected boolean generateChest(IWorld world, MutableBoundingBox structurebb, Random random, int x, int y, int z, ResourceLocation loot)
		{
			BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));

			if (structurebb.isVecInside(blockpos) && world.getBlockState(blockpos).getMaterial() == Material.AIR)
			{
				BlockState iblockstate = Blocks.RAIL.getDefaultState().with(RailBlock.SHAPE, random.nextBoolean() ? RailShape.NORTH_SOUTH : RailShape.EAST_WEST);
				this.setBlockState(world, iblockstate, x, y, z, structurebb);
				ChestMinecartEntity entityminecartchest = new ChestMinecartEntity(world.getWorld(), blockpos.getX() + 0.5F, blockpos.getY() + 0.5F, blockpos.getZ() + 0.5F);
				entityminecartchest.setLootTable(loot, random.nextLong());
				world.addEntity(entityminecartchest);
				return true;
			}
			else
			{
				return false;
			}
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox MutableBoundingBoxIn, ChunkPos p_74875_4_)
		{
			if (this.isLiquidInStructureBoundingBox(world, MutableBoundingBoxIn))
			{
				return false;
			}
			else
			{
				int i1 = this.sectionCount * 5 - 1;
				BlockState iblockstate = this.getPlanksBlock();
				this.fillWithBlocks(world, MutableBoundingBoxIn, 0, 0, 0, 2, 1, i1, CAVE_AIR, CAVE_AIR, false);
				this.generateMaybeBox(world, MutableBoundingBoxIn, random, 0.8F, 0, 2, 0, 2, 2, i1, CAVE_AIR, CAVE_AIR, false, false);

				if (this.hasSpiders)
				{
					this.generateMaybeBox(world, MutableBoundingBoxIn, random, 0.6F, 0, 0, 0, 2, 1, i1, Blocks.COBWEB.getDefaultState(), CAVE_AIR, false, true);
				}

				for (int j1 = 0; j1 < this.sectionCount; ++j1)
				{
					int k1 = 2 + j1 * 5;
					this.placeSupport(world, MutableBoundingBoxIn, 0, 0, k1, 2, 2, random);
					this.placeCobWeb(world, MutableBoundingBoxIn, random, 0.1F, 0, 2, k1 - 1);
					this.placeCobWeb(world, MutableBoundingBoxIn, random, 0.1F, 2, 2, k1 - 1);
					this.placeCobWeb(world, MutableBoundingBoxIn, random, 0.1F, 0, 2, k1 + 1);
					this.placeCobWeb(world, MutableBoundingBoxIn, random, 0.1F, 2, 2, k1 + 1);
					this.placeCobWeb(world, MutableBoundingBoxIn, random, 0.05F, 0, 2, k1 - 2);
					this.placeCobWeb(world, MutableBoundingBoxIn, random, 0.05F, 2, 2, k1 - 2);
					this.placeCobWeb(world, MutableBoundingBoxIn, random, 0.05F, 0, 2, k1 + 2);
					this.placeCobWeb(world, MutableBoundingBoxIn, random, 0.05F, 2, 2, k1 + 2);

					if (RSConfig.lootChestsMS)
					{
						if (random.nextInt(50) == 0)
						{
							this.generateChest(world, MutableBoundingBoxIn, random, 2, 0, k1 - 1, LootTables.CHESTS_ABANDONED_MINESHAFT);
						}

						if (random.nextInt(50) == 0)
						{
							this.generateChest(world, MutableBoundingBoxIn, random, 0, 0, k1 + 1, LootTables.CHESTS_ABANDONED_MINESHAFT);
						}
					}

					if (this.hasSpiders && !this.spawnerPlaced)
					{
						int l1 = this.getYWithOffset(0);
						int i2 = k1 - 1 + random.nextInt(3);
						int j2 = this.getXWithOffset(1, i2);
						int k2 = this.getZWithOffset(1, i2);
						BlockPos blockpos = new BlockPos(j2, l1, k2);

						if (MutableBoundingBoxIn.isVecInside(blockpos) && this.getSkyBrightness(world, 1, 0, i2, MutableBoundingBoxIn))
						{
							this.spawnerPlaced = true;
							world.setBlockState(blockpos, Blocks.SPAWNER.getDefaultState(), 2);
							TileEntity tileentity = world.getTileEntity(blockpos);

							if (tileentity instanceof MobSpawnerTileEntity)
							{
								((MobSpawnerTileEntity) tileentity).getSpawnerBaseLogic().setEntityType(EntityType.CAVE_SPIDER);
							}
						}
					}
				}

				for (int l2 = 0; l2 <= 2; ++l2)
				{
					for (int i3 = 0; i3 <= i1; ++i3)
					{
						BlockState iblockstate3 = this.getBlockStateFromPos(world, l2, -1, i3, MutableBoundingBoxIn);

						if (iblockstate3.getMaterial() == Material.AIR)
						{
							this.setBlockState(world, iblockstate, l2, -1, i3, MutableBoundingBoxIn);
						}
					}
				}

				if (this.hasRails)
				{
					BlockState iblockstate1 = Blocks.RAIL.getDefaultState().with(RailBlock.SHAPE, RailShape.NORTH_SOUTH);

					for (int j3 = 0; j3 <= i1; ++j3)
					{
						BlockState iblockstate2 = this.getBlockStateFromPos(world, 1, -1, j3, MutableBoundingBoxIn);

						if (iblockstate2.getMaterial() != Material.AIR)
						{
							float f = this.getSkyBrightness(world, 1, 0, j3, MutableBoundingBoxIn) ? 0.7F : 0.9F;
							this.randomlyPlaceBlock(world, MutableBoundingBoxIn, random, f, 1, 0, j3, iblockstate1);
						}
					}
				}

				return true;
			}
		}


		private void placeSupport(IWorld world, MutableBoundingBox boundingBox, int x, int y2, int z, int y, int x2, Random random)
		{

			BlockState iblockstate = this.getArchBlock();
			BlockState iblockstate1 = this.getFenceBlock();
			BlockState iblockstate2 = CAVE_AIR;
			this.fillWithBlocks(world, boundingBox, x, y2, z, x, y - 1, z, iblockstate1, iblockstate2, false);
			this.fillWithBlocks(world, boundingBox, x2, y2, z, x2, y - 1, z, iblockstate1, iblockstate2, false);
			this.fillWithBlocks(world, boundingBox, x, y, z, x2, y, z, iblockstate, iblockstate2, false);

			if (this.mineShaftType == Type.END)
			{
				if (random.nextFloat() < 0.08F)
				{
					this.randomlyPlaceBlock(world, boundingBox, random, 1F, x, y, z - 1, Blocks.END_ROD.getDefaultState().with(DirectionalBlock.FACING, Direction.SOUTH));
					this.randomlyPlaceBlock(world, boundingBox, random, 1F, x, y, z + 1, Blocks.END_ROD.getDefaultState().with(DirectionalBlock.FACING, Direction.NORTH));
				}

				if (random.nextFloat() < 0.08F)
				{
					this.randomlyPlaceBlock(world, boundingBox, random, 1F, x + 2, y, z - 1, Blocks.END_ROD.getDefaultState().with(DirectionalBlock.FACING, Direction.SOUTH));
					this.randomlyPlaceBlock(world, boundingBox, random, 1F, x + 2, y, z + 1, Blocks.END_ROD.getDefaultState().with(DirectionalBlock.FACING, Direction.NORTH));
				}
			}
			else if (this.mineShaftType == Type.HELL)
			{
				if (random.nextFloat() < 0.1f)
				{
					this.setBlockState(world, Blocks.REDSTONE_LAMP.getDefaultState().with(RedstoneLampBlock.LIT, Boolean.valueOf(true)), x + 1, y, z, boundingBox);
					this.setBlockState(world, Blocks.REDSTONE_TORCH.getDefaultState(), x, y + 1, z, boundingBox);
					this.setBlockState(world, Blocks.REDSTONE_TORCH.getDefaultState(), x + 2, y + 1, z, boundingBox);
					this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.POWER, Integer.valueOf(14)).with(RedstoneWireBlock.EAST, RedstoneSide.SIDE).with(RedstoneWireBlock.WEST, RedstoneSide.SIDE), x + 1, y + 1, z, boundingBox);
				}
				else
				{
					this.randomlyPlaceBlock(world, boundingBox, random, 0.1F, x + 1, y, z - 1, Blocks.REDSTONE_WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.SOUTH));
					this.randomlyPlaceBlock(world, boundingBox, random, 0.1F, x + 1, y, z + 1, Blocks.REDSTONE_WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.NORTH));
				}
			}
			else if (this.mineShaftType == Type.OCEAN)
			{
				this.randomlyPlaceBlock(world, boundingBox, random, 0.2F, x + 1, y, z, Blocks.SEA_LANTERN.getDefaultState());
			}
			else if (this.mineShaftType == Type.ICEY)
			{
				this.randomlyPlaceBlock(world, boundingBox, random, 0.08F, x + 1, y, z - 1, Blocks.REDSTONE_WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.SOUTH));
				this.randomlyPlaceBlock(world, boundingBox, random, 0.08F, x + 1, y, z + 1, Blocks.REDSTONE_WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.NORTH));
			}
			else
			{
				this.randomlyPlaceBlock(world, boundingBox, random, 0.08F, x + 1, y, z - 1, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.SOUTH));
				this.randomlyPlaceBlock(world, boundingBox, random, 0.08F, x + 1, y, z + 1, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, Direction.NORTH));
			}

		}


		private void placeCobWeb(IWorld p_189922_1_, MutableBoundingBox p_189922_2_, Random p_189922_3_, float p_189922_4_, int p_189922_5_, int p_189922_6_, int p_189922_7_)
		{
			if (this.getSkyBrightness(p_189922_1_, p_189922_5_, p_189922_6_, p_189922_7_, p_189922_2_))
			{
				this.randomlyPlaceBlock(p_189922_1_, p_189922_2_, p_189922_3_, p_189922_4_, p_189922_5_, p_189922_6_, p_189922_7_, Blocks.COBWEB.getDefaultState());
			}
		}

	}

	public static class Cross extends RSMineshaftPieces.Piece
	{
		private Direction corridorDirection;
		private boolean isMultipleFloors;


		public Cross(TemplateManager p_i50454_1_, CompoundNBT p_i50454_2_)
		{
			super(StructurePieces.MSCROSSINGRS, p_i50454_2_);
			this.isMultipleFloors = p_i50454_2_.getBoolean("tf");
			this.corridorDirection = Direction.byHorizontalIndex(p_i50454_2_.getInt("D"));
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
			tagCompound.putBoolean("tf", this.isMultipleFloors);
			tagCompound.putInt("D", this.corridorDirection.getHorizontalIndex());
		}


		public Cross(int p_i50455_1_, MutableBoundingBox p_i50455_2_, @Nullable Direction p_i50455_3_, RSMineshaftStructure.Type p_i50455_4_)
		{
			super(StructurePieces.MSCROSSINGRS, p_i50455_1_, p_i50455_4_);
			this.corridorDirection = p_i50455_3_;
			this.boundingBox = p_i50455_2_;
			this.isMultipleFloors = p_i50455_2_.getYSize() > 3;
		}


		public static MutableBoundingBox findCrossing(List<StructurePiece> listIn, Random rand, int x, int y, int z, Direction facing)
		{
			MutableBoundingBox MutableBoundingBox = new MutableBoundingBox(x, y, z, x, y + 2, z);

			if (rand.nextInt(4) == 0)
			{
				MutableBoundingBox.maxY += 4;
			}

			switch (facing)
			{
				case NORTH:
				default:
					MutableBoundingBox.minX = x - 1;
					MutableBoundingBox.maxX = x + 3;
					MutableBoundingBox.minZ = z - 4;
					break;

				case SOUTH:
					MutableBoundingBox.minX = x - 1;
					MutableBoundingBox.maxX = x + 3;
					MutableBoundingBox.maxZ = z + 3 + 1;
					break;

				case WEST:
					MutableBoundingBox.minX = x - 4;
					MutableBoundingBox.minZ = z - 1;
					MutableBoundingBox.maxZ = z + 3;
					break;

				case EAST:
					MutableBoundingBox.maxX = x + 3 + 1;
					MutableBoundingBox.minZ = z - 1;
					MutableBoundingBox.maxZ = z + 3;
			}

			return StructurePiece.findIntersecting(listIn, MutableBoundingBox) != null ? null : MutableBoundingBox;
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			int i = this.getComponentType();

			switch (this.corridorDirection)
			{
				case NORTH:
				default:
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, Direction.NORTH, i);
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, Direction.WEST, i);
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, Direction.EAST, i);
					break;

				case SOUTH:
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, Direction.SOUTH, i);
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, Direction.WEST, i);
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, Direction.EAST, i);
					break;

				case WEST:
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, Direction.NORTH, i);
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, Direction.SOUTH, i);
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, Direction.WEST, i);
					break;

				case EAST:
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, Direction.NORTH, i);
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, Direction.SOUTH, i);
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, Direction.EAST, i);
			}

			if (this.isMultipleFloors)
			{
				if (rand.nextBoolean())
				{
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ - 1, Direction.NORTH, i);
				}

				if (rand.nextBoolean())
				{
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, Direction.WEST, i);
				}

				if (rand.nextBoolean())
				{
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, Direction.EAST, i);
				}

				if (rand.nextBoolean())
				{
					RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.maxZ + 1, Direction.SOUTH, i);
				}
			}
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox MutableBoundingBoxIn, ChunkPos p_74875_4_)
		{
			if (this.isLiquidInStructureBoundingBox(world, MutableBoundingBoxIn))
			{
				return false;
			}
			else
			{
				BlockState iblockstate = this.getPlanksBlock();

				if (this.isMultipleFloors)
				{
					this.fillWithBlocks(world, MutableBoundingBoxIn, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.minY + 3 - 1, this.boundingBox.maxZ, CAVE_AIR, CAVE_AIR, false);
					this.fillWithBlocks(world, MutableBoundingBoxIn, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.minY + 3 - 1, this.boundingBox.maxZ - 1, CAVE_AIR, CAVE_AIR, false);
					this.fillWithBlocks(world, MutableBoundingBoxIn, this.boundingBox.minX + 1, this.boundingBox.maxY - 2, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ, CAVE_AIR, CAVE_AIR, false);
					this.fillWithBlocks(world, MutableBoundingBoxIn, this.boundingBox.minX, this.boundingBox.maxY - 2, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ - 1, CAVE_AIR, CAVE_AIR, false);
					this.fillWithBlocks(world, MutableBoundingBoxIn, this.boundingBox.minX + 1, this.boundingBox.minY + 3, this.boundingBox.minZ + 1, this.boundingBox.maxX - 1, this.boundingBox.minY + 3, this.boundingBox.maxZ - 1, CAVE_AIR, CAVE_AIR, false);
				}
				else
				{
					this.fillWithBlocks(world, MutableBoundingBoxIn, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ, CAVE_AIR, CAVE_AIR, false);
					this.fillWithBlocks(world, MutableBoundingBoxIn, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ - 1, CAVE_AIR, CAVE_AIR, false);
				}

				this.placeSupportPillar(world, MutableBoundingBoxIn, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxY);
				this.placeSupportPillar(world, MutableBoundingBoxIn, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.maxY);
				this.placeSupportPillar(world, MutableBoundingBoxIn, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxY);
				this.placeSupportPillar(world, MutableBoundingBoxIn, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.maxY);

				for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; ++i)
				{
					for (int j = this.boundingBox.minZ; j <= this.boundingBox.maxZ; ++j)
					{
						if (this.getBlockStateFromPos(world, i, this.boundingBox.minY - 1, j, MutableBoundingBoxIn).getMaterial() == Material.AIR && this.getSkyBrightness(world, i, this.boundingBox.minY - 1, j, MutableBoundingBoxIn))
						{
							this.setBlockState(world, iblockstate, i, this.boundingBox.minY - 1, j, MutableBoundingBoxIn);
						}
					}
				}

				return true;
			}
		}


		private void placeSupportPillar(IWorld p_189923_1_, MutableBoundingBox p_189923_2_, int p_189923_3_, int p_189923_4_, int p_189923_5_, int p_189923_6_)
		{
			if (this.getBlockStateFromPos(p_189923_1_, p_189923_3_, p_189923_6_ + 1, p_189923_5_, p_189923_2_).getMaterial() != Material.AIR)
			{
				this.fillWithBlocks(p_189923_1_, p_189923_2_, p_189923_3_, p_189923_4_, p_189923_5_, p_189923_3_, p_189923_6_, p_189923_5_, this.getPlanksBlock(), CAVE_AIR, false);
			}
		}
	}

	abstract static class Piece extends StructurePiece
	{
		protected RSMineshaftStructure.Type mineShaftType;


		public Piece(IStructurePieceType p_i50452_1_, int p_i50452_2_, RSMineshaftStructure.Type p_i50452_3_)
		{
			super(p_i50452_1_, p_i50452_2_);
			this.mineShaftType = p_i50452_3_;
		}


		public Piece(IStructurePieceType p_i50453_1_, CompoundNBT p_i50453_2_)
		{
			super(p_i50453_1_, p_i50453_2_);
			this.mineShaftType = RSMineshaftStructure.Type.byId(p_i50453_2_.getInt("MST"));
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			tagCompound.putInt("MST", this.mineShaftType.ordinal());
		}


		protected BlockState getArchBlock()
		{
			switch (this.mineShaftType)
			{
				case ICEY:
					return Blocks.PACKED_ICE.getDefaultState();

				case JUNGLE:
					return Blocks.JUNGLE_LOG.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X);

				case TAIGA:
					return Blocks.STRIPPED_SPRUCE_LOG.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X);

				case DESERT:
					return Blocks.CHISELED_SANDSTONE.getDefaultState();

				case END:
					return Blocks.PURPUR_PILLAR.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z);

				case HELL:
					return Blocks.NETHER_BRICKS.getDefaultState();

				case OCEAN:
					return Blocks.DARK_PRISMARINE.getDefaultState();

				case STONE:
					return Blocks.STONE.getDefaultState();

				case SAVANNA:
					return Blocks.ACACIA_LOG.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X);

				case SWAMPORDARKFOREST:
					return Blocks.DARK_OAK_PLANKS.getDefaultState();

				case BIRCH:
				default:
					return Blocks.STRIPPED_BIRCH_LOG.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.X);
			}
		}


		// cannot be a rotatable block
		// The crossing part has a null rotation and will try to force it on the
		// rotatable block which will cause a crash
		protected BlockState getPlanksBlock()
		{
			switch (this.mineShaftType)
			{
				case ICEY:
					return Blocks.ICE.getDefaultState();


				case JUNGLE:
					return Blocks.JUNGLE_PLANKS.getDefaultState();

				case TAIGA:
					return Blocks.SPRUCE_PLANKS.getDefaultState();

				case DESERT:
					return Blocks.SMOOTH_SANDSTONE.getDefaultState();

				case END:
					return Blocks.PURPUR_BLOCK.getDefaultState();

				case HELL:
					return Blocks.NETHER_BRICKS.getDefaultState();

				case OCEAN:
					return Blocks.PRISMARINE_BRICKS.getDefaultState();

				case STONE:
					return Blocks.ANDESITE.getDefaultState();

				case SAVANNA:
					return Blocks.ACACIA_PLANKS.getDefaultState();

				case SWAMPORDARKFOREST:
					return Blocks.GRASS_BLOCK.getDefaultState();

				case BIRCH:
				default:
					return Blocks.BIRCH_PLANKS.getDefaultState();
			}
		}


		protected BlockState getFenceBlock()
		{
			switch (this.mineShaftType)
			{
				case ICEY:
					return Blocks.ICE.getDefaultState();

				case JUNGLE:
					return Blocks.JUNGLE_FENCE.getDefaultState();

				case TAIGA:
					return Blocks.SPRUCE_FENCE.getDefaultState();

				case DESERT:
					return Blocks.SANDSTONE_WALL.getDefaultState();

				case END:
					return Blocks.PURPUR_PILLAR.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y);

				case HELL:
					return Blocks.NETHER_BRICK_WALL.getDefaultState();

				case OCEAN:
					return Blocks.PRISMARINE.getDefaultState();

				case STONE:
					return Blocks.COBBLESTONE_WALL.getDefaultState();

				case SAVANNA:
					return Blocks.ACACIA_FENCE.getDefaultState();

				case SWAMPORDARKFOREST:
					return Blocks.DARK_OAK_LOG.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y);

				case BIRCH:
				default:
					return Blocks.BIRCH_FENCE.getDefaultState();
			}
		}

	}

	public static class Room extends RSMineshaftPieces.Piece
	{
		private final List<MutableBoundingBox> roomsLinkedToTheRoom = Lists.<MutableBoundingBox>newLinkedList();

		public Room(int p_i47137_1_, Random p_i47137_2_, int p_i47137_3_, int p_i47137_4_, RSMineshaftStructure.Type p_i47137_5_)
		{
			super(StructurePieces.MSROOMRS, p_i47137_1_, p_i47137_5_);
			this.mineShaftType = p_i47137_5_;
			this.boundingBox = new MutableBoundingBox(p_i47137_3_, 50, p_i47137_4_, p_i47137_3_ + 7 + p_i47137_2_.nextInt(6), 54 + p_i47137_2_.nextInt(6), p_i47137_4_ + 7 + p_i47137_2_.nextInt(6));
		}


		public Room(TemplateManager p_i50451_1_, CompoundNBT p_i50451_2_)
		{
			super(StructurePieces.MSROOMRS, p_i50451_2_);
			ListNBT listnbt = p_i50451_2_.getList("Entrances", 11);

			for (int i = 0; i < listnbt.size(); ++i)
			{
				this.roomsLinkedToTheRoom.add(new MutableBoundingBox(listnbt.getIntArray(i)));
			}
		}


		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
			super.readAdditional(tagCompound);
			ListNBT listnbt = new ListNBT();

			for (MutableBoundingBox mutableboundingbox : this.roomsLinkedToTheRoom)
			{
				listnbt.add(mutableboundingbox.toNBTTagIntArray());
			}

			tagCompound.put("Entrances", listnbt);
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			int i = this.getComponentType();
			int k = this.boundingBox.getYSize() - 3 - 1;
			if (k <= 0)
			{
				k = 1;
			}

			int l;
			for (int j = 0; j < this.boundingBox.getXSize(); j = l + 4)
			{
				l = j + rand.nextInt(this.boundingBox.getXSize());
				if (l + 3 > this.boundingBox.getXSize())
				{
					break;
				}

				RSMineshaftPieces.Piece structuremineshaftpieces$peice = RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + l, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.minZ - 1, Direction.NORTH, i);

				if (structuremineshaftpieces$peice != null)
				{
					MutableBoundingBox MutableBoundingBox = structuremineshaftpieces$peice.getBoundingBox();
					this.roomsLinkedToTheRoom.add(new MutableBoundingBox(MutableBoundingBox.minX, MutableBoundingBox.minY, this.boundingBox.minZ, MutableBoundingBox.maxX, MutableBoundingBox.maxY, this.boundingBox.minZ + 1));
				}
			}

			for (int i1 = 0; i1 < this.boundingBox.getXSize(); i1 = l + 4)
			{
				l = i1 + rand.nextInt(this.boundingBox.getXSize());

				if (l + 3 > this.boundingBox.getXSize())
				{
					break;
				}

				RSMineshaftPieces.Piece structuremineshaftpieces$peice1 = RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX + l, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.maxZ + 1, Direction.SOUTH, i);

				if (structuremineshaftpieces$peice1 != null)
				{
					MutableBoundingBox MutableBoundingBox1 = structuremineshaftpieces$peice1.getBoundingBox();
					this.roomsLinkedToTheRoom.add(new MutableBoundingBox(MutableBoundingBox1.minX, MutableBoundingBox1.minY, this.boundingBox.maxZ - 1, MutableBoundingBox1.maxX, MutableBoundingBox1.maxY, this.boundingBox.maxZ));
				}
			}

			for (int j1 = 0; j1 < this.boundingBox.getZSize(); j1 = l + 4)
			{
				l = j1 + rand.nextInt(this.boundingBox.getZSize());

				if (l + 3 > this.boundingBox.getZSize())
				{
					break;
				}

				RSMineshaftPieces.Piece structuremineshaftpieces$peice2 = RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.minZ + l, Direction.WEST, i);

				if (structuremineshaftpieces$peice2 != null)
				{
					MutableBoundingBox MutableBoundingBox2 = structuremineshaftpieces$peice2.getBoundingBox();
					this.roomsLinkedToTheRoom.add(new MutableBoundingBox(this.boundingBox.minX, MutableBoundingBox2.minY, MutableBoundingBox2.minZ, this.boundingBox.minX + 1, MutableBoundingBox2.maxY, MutableBoundingBox2.maxZ));
				}
			}

			for (int k1 = 0; k1 < this.boundingBox.getZSize(); k1 = l + 4)
			{
				l = k1 + rand.nextInt(this.boundingBox.getZSize());

				if (l + 3 > this.boundingBox.getZSize())
				{
					break;
				}

				StructurePiece StructurePiece = RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + rand.nextInt(k) + 1, this.boundingBox.minZ + l, Direction.EAST, i);

				if (StructurePiece != null)
				{
					MutableBoundingBox MutableBoundingBox3 = StructurePiece.getBoundingBox();
					this.roomsLinkedToTheRoom.add(new MutableBoundingBox(this.boundingBox.maxX - 1, MutableBoundingBox3.minY, MutableBoundingBox3.minZ, this.boundingBox.maxX, MutableBoundingBox3.maxY, MutableBoundingBox3.maxZ));
				}
			}
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox MutableBoundingBoxIn, ChunkPos p_74875_4_)
		{
			BlockState flooring;

			if (this.mineShaftType == RSMineshaftStructure.Type.HELL)
			{
				flooring = Blocks.SOUL_SAND.getDefaultState();
			}
			else
			{
				flooring = Blocks.COARSE_DIRT.getDefaultState();
			}

			if (this.boundingBox.getYSize() > 100)
			{
				// floor
				this.fillWithBlocks(world, MutableBoundingBoxIn, this.boundingBox.minX - 10, this.boundingBox.minY, this.boundingBox.minZ - 10, this.boundingBox.maxX + 8, this.boundingBox.minY, this.boundingBox.maxZ + 10, flooring, CAVE_AIR, false);
				this.fillWithBlocks(world, MutableBoundingBoxIn, this.boundingBox.minX - 3, this.boundingBox.minY + 1, this.boundingBox.minZ - 3, this.boundingBox.maxX + 1, Math.min(this.boundingBox.minY + 3, this.boundingBox.maxY), this.boundingBox.maxZ + 3, CAVE_AIR, CAVE_AIR, false);

				for (MutableBoundingBox MutableBoundingBox : this.roomsLinkedToTheRoom)
				{
					this.fillWithBlocks(world, MutableBoundingBoxIn, MutableBoundingBox.minX, MutableBoundingBox.maxY - 2, MutableBoundingBox.minZ, MutableBoundingBox.maxX, MutableBoundingBox.maxY, MutableBoundingBox.maxZ, CAVE_AIR, CAVE_AIR, false);
				}

				// wall
				this.randomlyRareFillWithBlocks(world, MutableBoundingBoxIn, this.boundingBox.minX - 1, this.boundingBox.minY + 4, this.boundingBox.minZ - 1, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ + 1, CAVE_AIR, false);
				this.updateLiquidBlocks(world, MutableBoundingBoxIn, this.boundingBox.minX - 5, this.boundingBox.minY + 4, this.boundingBox.minZ - 5, this.boundingBox.maxX + 5, this.boundingBox.maxY + 4, this.boundingBox.maxZ + 7);
				return true;
			}
			else
			{
				this.fillWithBlocks(world, MutableBoundingBoxIn, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.minY, this.boundingBox.maxZ, flooring, CAVE_AIR, false);
				this.fillWithBlocks(world, MutableBoundingBoxIn, this.boundingBox.minX, this.boundingBox.minY + 1, this.boundingBox.minZ, this.boundingBox.maxX, Math.min(this.boundingBox.minY + 3, this.boundingBox.maxY), this.boundingBox.maxZ, CAVE_AIR, CAVE_AIR, false);

				for (MutableBoundingBox MutableBoundingBox : this.roomsLinkedToTheRoom)
				{
					this.fillWithBlocks(world, MutableBoundingBoxIn, MutableBoundingBox.minX, MutableBoundingBox.maxY - 2, MutableBoundingBox.minZ, MutableBoundingBox.maxX, MutableBoundingBox.maxY, MutableBoundingBox.maxZ, CAVE_AIR, CAVE_AIR, false);
				}

				this.randomlyRareFillWithBlocks(world, MutableBoundingBoxIn, this.boundingBox.minX, this.boundingBox.minY + 4, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ, CAVE_AIR, false);
				return true;
			}
		}


		@Override
		public void offset(int x, int y, int z)
		{
			super.offset(x, y, z);

			for (MutableBoundingBox MutableBoundingBox : this.roomsLinkedToTheRoom)
			{
				MutableBoundingBox.offset(x, y, z);
			}
		}


		protected void updateLiquidBlocks(IWorld world, MutableBoundingBox boundingboxIn, int minX, int minY, int minZ, int maxX, int maxY, int maxZ)
		{
			float f = maxX - minX + 1;
			float f1 = maxY - minY + 1;
			float f2 = maxZ - minZ + 1;
			float f3 = minX + f / 2.0F;
			float f4 = minZ + f2 / 2.0F;

			for (int y = minY; y <= maxY; ++y)
			{
				float f5 = (y - minY) / f1;

				for (int x = minX; x <= maxX; ++x)
				{
					float f6 = (x - f3) / (f * 0.5F);

					for (int z = minZ; z <= maxZ; ++z)
					{
						float f7 = (z - f4) / (f2 * 0.5F);
						if (!this.getBlockStateFromPos(world, x, y, z, boundingboxIn).getFluidState().isEmpty())
						{
							float f8 = f6 * f6 + f5 * f5 + f7 * f7;
							if (f8 <= 1.05F)
							{
								BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));

								IFluidState ifluidstate = world.getFluidState(blockpos);
								if (!ifluidstate.isEmpty())
								{
									world.getPendingFluidTicks().scheduleTick(blockpos, ifluidstate.getFluid(), 0);

								}
							}
						}
					}
				}
			}

		}
	}

	public static class Stairs extends RSMineshaftPieces.Piece
	{
		public Stairs(int p_i50449_1_, MutableBoundingBox p_i50449_2_, Direction p_i50449_3_, RSMineshaftStructure.Type p_i50449_4_)
		{
			super(StructurePieces.MSSTAIRSRS, p_i50449_1_, p_i50449_4_);
			this.setCoordBaseMode(p_i50449_3_);
			this.boundingBox = p_i50449_2_;
		}


		public Stairs(TemplateManager p_i50450_1_, CompoundNBT p_i50450_2_)
		{
			super(StructurePieces.MSSTAIRSRS, p_i50450_2_);
		}


		public static MutableBoundingBox findStairs(List<StructurePiece> listIn, Random rand, int x, int y, int z, Direction facing)
		{
			MutableBoundingBox MutableBoundingBox = new MutableBoundingBox(x, y - 5, z, x, y + 2, z);

			switch (facing)
			{
				case NORTH:
				default:
					MutableBoundingBox.maxX = x + 2;
					MutableBoundingBox.minZ = z - 8;
					break;

				case SOUTH:
					MutableBoundingBox.maxX = x + 2;
					MutableBoundingBox.maxZ = z + 8;
					break;

				case WEST:
					MutableBoundingBox.minX = x - 8;
					MutableBoundingBox.maxZ = z + 2;
					break;

				case EAST:
					MutableBoundingBox.maxX = x + 8;
					MutableBoundingBox.maxZ = z + 2;
			}

			return StructurePiece.findIntersecting(listIn, MutableBoundingBox) != null ? null : MutableBoundingBox;
		}


		@Override
		public void buildComponent(StructurePiece componentIn, List<StructurePiece> listIn, Random rand)
		{
			int i = this.getComponentType();
			Direction enumfacing = this.getCoordBaseMode();

			if (enumfacing != null)
			{
				switch (enumfacing)
				{
					case NORTH:
					default:
						RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, Direction.NORTH, i);
						break;

					case SOUTH:
						RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, Direction.SOUTH, i);
						break;

					case WEST:
						RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, Direction.WEST, i);
						break;

					case EAST:
						RSMineshaftPieces.generateAndAddPiece(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, Direction.EAST, i);
				}
			}
		}


		@Override
		public boolean func_225577_a_(IWorld world, ChunkGenerator<?> p_225577_2_, Random random, MutableBoundingBox MutableBoundingBoxIn, ChunkPos p_74875_4_)
		{
			if (this.isLiquidInStructureBoundingBox(world, MutableBoundingBoxIn))
			{
				return false;
			}
			else
			{
				this.fillWithBlocks(world, MutableBoundingBoxIn, 0, 5, 0, 2, 7, 1, CAVE_AIR, CAVE_AIR, false);
				this.fillWithBlocks(world, MutableBoundingBoxIn, 0, 0, 7, 2, 2, 8, CAVE_AIR, CAVE_AIR, false);

				for (int i = 0; i < 5; ++i)
				{
					this.fillWithBlocks(world, MutableBoundingBoxIn, 0, 5 - i - (i < 4 ? 1 : 0), 2 + i, 2, 7 - i, 2 + i, CAVE_AIR, CAVE_AIR, false);
				}

				return true;
			}
		}
	}
}
