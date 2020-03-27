package com.telepathicgrunt.repurposedstructures.world.features.structures;

import java.util.Random;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.LeverBlock;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.block.RepeaterBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TripWireBlock;
import net.minecraft.block.TripWireHookBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.AttachFace;
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
import net.minecraft.world.gen.feature.structure.ScatteredStructurePiece;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class NetherTemplePiece extends ScatteredStructurePiece
{
	private static final NetherTemplePiece.Selector	NETHER_BLOCK_SELECTOR	= new NetherTemplePiece.Selector();
	public static final ResourceLocation CHESTS_NETHER_TEMPLE 				= new ResourceLocation("repurposed_structures:chests/nether_temple_chest");
	public static final ResourceLocation DISPENSER_NETHER_TEMPLE			= new ResourceLocation("repurposed_structures:chests/nether_temple_dispenser");


	public NetherTemplePiece(Random random, int x, int z)
	{
		super(StructurePieces.NETHER_TEMPLE_PIECE, random, x, 64, z, 12, 10, 15);
	}


	public NetherTemplePiece(TemplateManager templateManager, CompoundNBT data)
	{
		super(StructurePieces.NETHER_TEMPLE_PIECE, data);
	}


	/**
	 * (abstract) Helper method to read subclass data from NBT
	 */
	protected void readAdditional(CompoundNBT tagCompound)
	{
		super.readAdditional(tagCompound);
	}


	public boolean func_225577_a_(IWorld world, ChunkGenerator<?> chunKGenerator, Random random, MutableBoundingBox box, ChunkPos chunkPos)
	{
		BlockPos.Mutable mutable = new BlockPos.Mutable((box.maxX-box.minX)/2+box.minX, 30, (box.maxZ-box.minZ)/2+box.minZ);
		while(!world.isAirBlock(mutable))
		{
			mutable.move(Direction.UP);
		}
		
		if(mutable.getY() >= world.getMaxHeight() || mutable.getY() <= 32)
		{
            this.boundingBox.offset(0, this.hPos - this.boundingBox.minY + 32, 0);
		}
		else
		{
            this.boundingBox.offset(0, this.hPos - this.boundingBox.minY + mutable.getY(), 0);
		}
		
		this.fillWithRandomizedBlocks(world, box, 0, -4, 0, this.width - 1, 0, this.depth - 1, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 2, 1, 2, 9, 2, 2, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 2, 1, 12, 9, 2, 12, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 2, 1, 3, 2, 2, 11, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 9, 1, 3, 9, 2, 11, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 1, 3, 1, 10, 6, 1, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 1, 3, 13, 10, 6, 13, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 1, 3, 2, 1, 6, 12, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 10, 3, 2, 10, 6, 12, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 2, 3, 2, 9, 3, 12, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 2, 6, 2, 9, 6, 12, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 3, 7, 3, 8, 7, 11, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 4, 8, 4, 7, 8, 10, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithAir(world, box, 3, 1, 3, 8, 2, 11);
		this.fillWithAir(world, box, 4, 3, 6, 7, 3, 9);
		this.fillWithAir(world, box, 2, 4, 2, 9, 5, 12);
		this.fillWithAir(world, box, 4, 6, 5, 7, 6, 9);
		this.fillWithAir(world, box, 5, 7, 6, 6, 7, 8);
		this.fillWithAir(world, box, 5, 1, 2, 6, 2, 2);
		this.fillWithAir(world, box, 5, 2, 12, 6, 2, 12);
		this.fillWithAir(world, box, 5, 5, 1, 6, 5, 1);
		this.fillWithAir(world, box, 5, 5, 13, 6, 5, 13);
		this.setBlockState(world, Blocks.AIR.getDefaultState(), 1, 5, 5, box);
		this.setBlockState(world, Blocks.AIR.getDefaultState(), 10, 5, 5, box);
		this.setBlockState(world, Blocks.AIR.getDefaultState(), 1, 5, 9, box);
		this.setBlockState(world, Blocks.AIR.getDefaultState(), 10, 5, 9, box);

		for (int i = 0; i <= 14; i += 14)
		{
			this.fillWithRandomizedBlocks(world, box, 2, 4, i, 2, 5, i, false, random, NETHER_BLOCK_SELECTOR);
			this.fillWithRandomizedBlocks(world, box, 4, 4, i, 4, 5, i, false, random, NETHER_BLOCK_SELECTOR);
			this.fillWithRandomizedBlocks(world, box, 7, 4, i, 7, 5, i, false, random, NETHER_BLOCK_SELECTOR);
			this.fillWithRandomizedBlocks(world, box, 9, 4, i, 9, 5, i, false, random, NETHER_BLOCK_SELECTOR);
		}

		this.fillWithRandomizedBlocks(world, box, 5, 6, 0, 6, 6, 0, false, random, NETHER_BLOCK_SELECTOR);

		for (int l = 0; l <= 11; l += 11)
		{
			for (int j = 2; j <= 12; j += 2)
			{
				this.fillWithRandomizedBlocks(world, box, l, 4, j, l, 5, j, false, random, NETHER_BLOCK_SELECTOR);
			}

			this.fillWithRandomizedBlocks(world, box, l, 6, 5, l, 6, 5, false, random, NETHER_BLOCK_SELECTOR);
			this.fillWithRandomizedBlocks(world, box, l, 6, 9, l, 6, 9, false, random, NETHER_BLOCK_SELECTOR);
		}

		this.fillWithRandomizedBlocks(world, box, 2, 7, 2, 2, 9, 2, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 9, 7, 2, 9, 9, 2, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 2, 7, 12, 2, 9, 12, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 9, 7, 12, 9, 9, 12, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 4, 9, 4, 4, 9, 4, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 7, 9, 4, 7, 9, 4, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 4, 9, 10, 4, 9, 10, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 7, 9, 10, 7, 9, 10, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 5, 9, 7, 6, 9, 7, false, random, NETHER_BLOCK_SELECTOR);
		BlockState blockstate3 = Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.EAST);
		BlockState blockstate4 = Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.WEST);
		BlockState blockstate = Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.SOUTH);
		BlockState blockstate1 = Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
		this.setBlockState(world, blockstate1, 5, 9, 6, box);
		this.setBlockState(world, blockstate1, 6, 9, 6, box);
		this.setBlockState(world, blockstate, 5, 9, 8, box);
		this.setBlockState(world, blockstate, 6, 9, 8, box);
		this.setBlockState(world, blockstate1, 4, 0, 0, box);
		this.setBlockState(world, blockstate1, 5, 0, 0, box);
		this.setBlockState(world, blockstate1, 6, 0, 0, box);
		this.setBlockState(world, blockstate1, 7, 0, 0, box);
		this.setBlockState(world, blockstate1, 4, 1, 8, box);
		this.setBlockState(world, blockstate1, 4, 2, 9, box);
		this.setBlockState(world, blockstate1, 4, 3, 10, box);
		this.setBlockState(world, blockstate1, 7, 1, 8, box);
		this.setBlockState(world, blockstate1, 7, 2, 9, box);
		this.setBlockState(world, blockstate1, 7, 3, 10, box);
		this.fillWithRandomizedBlocks(world, box, 4, 1, 9, 4, 1, 9, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 7, 1, 9, 7, 1, 9, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 4, 1, 10, 7, 2, 10, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 5, 4, 5, 6, 4, 5, false, random, NETHER_BLOCK_SELECTOR);
		this.setBlockState(world, blockstate3, 4, 4, 5, box);
		this.setBlockState(world, blockstate4, 7, 4, 5, box);

		for (int k = 0; k < 4; ++k)
		{
			this.setBlockState(world, blockstate, 5, 0 - k, 6 + k, box);
			this.setBlockState(world, blockstate, 6, 0 - k, 6 + k, box);
			this.fillWithAir(world, box, 5, 0 - k, 7 + k, 6, 0 - k, 9 + k);
		}

		this.fillWithAir(world, box, 1, -3, 12, 10, -1, 13);
		this.fillWithAir(world, box, 1, -3, 1, 3, -1, 13);
		this.fillWithAir(world, box, 1, -3, 1, 9, -1, 5);

		for (int i1 = 1; i1 <= 13; i1 += 2)
		{
			this.fillWithRandomizedBlocks(world, box, 1, -3, i1, 1, -2, i1, false, random, NETHER_BLOCK_SELECTOR);
		}

		for (int j1 = 2; j1 <= 12; j1 += 2)
		{
			this.fillWithRandomizedBlocks(world, box, 1, -1, j1, 3, -1, j1, false, random, NETHER_BLOCK_SELECTOR);
		}

		this.fillWithRandomizedBlocks(world, box, 2, -2, 1, 5, -2, 1, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 7, -2, 1, 9, -2, 1, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 6, -3, 1, 6, -3, 1, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 6, -1, 1, 6, -1, 1, false, random, NETHER_BLOCK_SELECTOR);
		this.setBlockState(world, Blocks.TRIPWIRE_HOOK.getDefaultState().with(TripWireHookBlock.FACING, Direction.EAST).with(TripWireHookBlock.ATTACHED, Boolean.valueOf(true)), 1, -3, 8, box);
		this.setBlockState(world, Blocks.TRIPWIRE_HOOK.getDefaultState().with(TripWireHookBlock.FACING, Direction.WEST).with(TripWireHookBlock.ATTACHED, Boolean.valueOf(true)), 4, -3, 8, box);
		this.setBlockState(world, Blocks.TRIPWIRE.getDefaultState().with(TripWireBlock.EAST, Boolean.valueOf(true)).with(TripWireBlock.WEST, Boolean.valueOf(true)).with(TripWireBlock.ATTACHED, Boolean.valueOf(true)), 2, -3, 8, box);
		this.setBlockState(world, Blocks.TRIPWIRE.getDefaultState().with(TripWireBlock.EAST, Boolean.valueOf(true)).with(TripWireBlock.WEST, Boolean.valueOf(true)).with(TripWireBlock.ATTACHED, Boolean.valueOf(true)), 3, -3, 8, box);
		BlockState blockstate5 = Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.NORTH, RedstoneSide.SIDE).with(RedstoneWireBlock.SOUTH, RedstoneSide.SIDE);
		this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.SOUTH, RedstoneSide.SIDE), 5, -3, 7, box);
		this.setBlockState(world, blockstate5, 5, -3, 6, box);
		this.setBlockState(world, blockstate5, 5, -3, 5, box);
		this.setBlockState(world, blockstate5, 5, -3, 4, box);
		this.setBlockState(world, blockstate5, 5, -3, 3, box);
		this.setBlockState(world, blockstate5, 5, -3, 2, box);
		this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.NORTH, RedstoneSide.SIDE).with(RedstoneWireBlock.WEST, RedstoneSide.SIDE), 5, -3, 1, box);
		this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.EAST, RedstoneSide.SIDE), 4, -3, 1, box);
		this.setBlockState(world, Blocks.MAGMA_BLOCK.getDefaultState(), 3, -3, 1, box);
		this.createDispenser(world, box, random, 3, -2, 1, Direction.NORTH, DISPENSER_NETHER_TEMPLE);
		

		this.setBlockState(world, Blocks.LADDER.getDefaultState().with(LadderBlock.FACING, Direction.NORTH), 3, -2, 2, box);
		this.setBlockState(world, Blocks.TRIPWIRE_HOOK.getDefaultState().with(TripWireHookBlock.FACING, Direction.NORTH).with(TripWireHookBlock.ATTACHED, Boolean.valueOf(true)), 7, -3, 1, box);
		this.setBlockState(world, Blocks.TRIPWIRE_HOOK.getDefaultState().with(TripWireHookBlock.FACING, Direction.SOUTH).with(TripWireHookBlock.ATTACHED, Boolean.valueOf(true)), 7, -3, 5, box);
		this.setBlockState(world, Blocks.TRIPWIRE.getDefaultState().with(TripWireBlock.NORTH, Boolean.valueOf(true)).with(TripWireBlock.SOUTH, Boolean.valueOf(true)).with(TripWireBlock.ATTACHED, Boolean.valueOf(true)), 7, -3, 2, box);
		this.setBlockState(world, Blocks.TRIPWIRE.getDefaultState().with(TripWireBlock.NORTH, Boolean.valueOf(true)).with(TripWireBlock.SOUTH, Boolean.valueOf(true)).with(TripWireBlock.ATTACHED, Boolean.valueOf(true)), 7, -3, 3, box);
		this.setBlockState(world, Blocks.TRIPWIRE.getDefaultState().with(TripWireBlock.NORTH, Boolean.valueOf(true)).with(TripWireBlock.SOUTH, Boolean.valueOf(true)).with(TripWireBlock.ATTACHED, Boolean.valueOf(true)), 7, -3, 4, box);
		this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.EAST, RedstoneSide.SIDE), 8, -3, 6, box);
		this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.WEST, RedstoneSide.SIDE).with(RedstoneWireBlock.SOUTH, RedstoneSide.SIDE), 9, -3, 6, box);
		this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.NORTH, RedstoneSide.SIDE).with(RedstoneWireBlock.SOUTH, RedstoneSide.UP), 9, -3, 5, box);
		this.setBlockState(world, Blocks.MAGMA_BLOCK.getDefaultState(), 9, -3, 4, box);
		this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.NORTH, RedstoneSide.SIDE), 9, -2, 4, box);
		this.createDispenser(world, box, random, 9, -2, 3, Direction.WEST, DISPENSER_NETHER_TEMPLE);
		

		this.setBlockState(world, Blocks.LADDER.getDefaultState().with(LadderBlock.FACING, Direction.WEST), 8, -2, 3, box);
		this.generateChest(world, box, random, 8, -3, 3, CHESTS_NETHER_TEMPLE);
		

		this.setBlockState(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 9, -3, 2, box);
		this.setBlockState(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 8, -3, 1, box);
		this.setBlockState(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 4, -3, 5, box);
		this.setBlockState(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 5, -2, 5, box);
		this.setBlockState(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 5, -1, 5, box);
		this.setBlockState(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 6, -3, 5, box);
		this.setBlockState(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 7, -2, 5, box);
		this.setBlockState(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 7, -1, 5, box);
		this.setBlockState(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 8, -3, 5, box);
		this.fillWithRandomizedBlocks(world, box, 9, -1, 1, 9, -1, 5, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithAir(world, box, 8, -3, 8, 10, -1, 10);
		this.setBlockState(world, Blocks.RED_NETHER_BRICKS.getDefaultState(), 8, -2, 11, box);
		this.setBlockState(world, Blocks.RED_NETHER_BRICKS.getDefaultState(), 9, -2, 11, box);
		this.setBlockState(world, Blocks.RED_NETHER_BRICKS.getDefaultState(), 10, -2, 11, box);
		BlockState blockstate2 = Blocks.LEVER.getDefaultState().with(LeverBlock.HORIZONTAL_FACING, Direction.NORTH).with(LeverBlock.FACE, AttachFace.WALL);
		this.setBlockState(world, blockstate2, 8, -2, 12, box);
		this.setBlockState(world, blockstate2, 9, -2, 12, box);
		this.setBlockState(world, blockstate2, 10, -2, 12, box);
		this.fillWithRandomizedBlocks(world, box, 8, -3, 8, 8, -3, 10, false, random, NETHER_BLOCK_SELECTOR);
		this.fillWithRandomizedBlocks(world, box, 10, -3, 8, 10, -3, 10, false, random, NETHER_BLOCK_SELECTOR);
		this.setBlockState(world, Blocks.NETHER_BRICKS.getDefaultState(), 10, -2, 9, box);
		this.setBlockState(world, Blocks.NETHER_BRICKS.getDefaultState(), 9, -1, 10, box);
		this.setBlockState(world, Blocks.NETHER_BRICKS.getDefaultState(), 9, -1, 9, box);
		this.setBlockState(world, Blocks.NETHER_BRICKS.getDefaultState(), 8, -1, 10, box);
		this.setBlockState(world, Blocks.NETHER_BRICKS.getDefaultState(), 8, -1, 9, box);
		this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.NORTH, RedstoneSide.SIDE), 8, -2, 9, box);
		this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState().with(RedstoneWireBlock.SOUTH, RedstoneSide.SIDE), 8, -2, 10, box);
		this.setBlockState(world, Blocks.REDSTONE_WIRE.getDefaultState(), 10, -1, 9, box);
		this.setBlockState(world, Blocks.STICKY_PISTON.getDefaultState().with(PistonBlock.FACING, Direction.UP), 9, -2, 8, box);
		this.setBlockState(world, Blocks.STICKY_PISTON.getDefaultState().with(PistonBlock.FACING, Direction.WEST), 10, -2, 8, box);
		this.setBlockState(world, Blocks.STICKY_PISTON.getDefaultState().with(PistonBlock.FACING, Direction.WEST), 10, -1, 8, box);
		this.setBlockState(world, Blocks.REPEATER.getDefaultState().with(RepeaterBlock.HORIZONTAL_FACING, Direction.NORTH), 10, -2, 10, box);
		
		if(RepurposedStructures.RSConfig.lootChestsNT.get())
		{
			this.generateChest(world, box, random, 9, -3, 10, CHESTS_NETHER_TEMPLE);
			this.generateChest(world, box, random, 9, -3, 8, CHESTS_NETHER_TEMPLE);
		}
		else 
		{
			this.setBlockState(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 9, -3, 10, box);
			this.setBlockState(world, Blocks.BLACK_TERRACOTTA.getDefaultState(), 9, -3, 8, box);
		}

		BlockPos blockpos = new BlockPos(this.getXWithOffset(9, 9), this.getYWithOffset(-3), this.getZWithOffset(9, 9));
		world.setBlockState(blockpos, Blocks.SPAWNER.getDefaultState(), 2);
		TileEntity tileentity = world.getTileEntity(blockpos);

		if (tileentity instanceof MobSpawnerTileEntity)
		{
			((MobSpawnerTileEntity) tileentity).getSpawnerBaseLogic().setEntityType(EntityType.ZOMBIE_PIGMAN);
		}
	
		return true;
	}

	static class Selector extends StructurePiece.BlockSelector
	{
		private Selector()
		{
		}

		public void selectBlocks(Random rand, int x, int y, int z, boolean wall)
		{
			float chance = rand.nextFloat();
			if (chance < 0.015F)
			{
				this.blockstate = Blocks.MAGMA_BLOCK.getDefaultState();
			}
			else if(chance < 0.12f)
			{
				this.blockstate = Blocks.BLACK_TERRACOTTA.getDefaultState();
			}
			else if(chance < 0.175f)
			{
				this.blockstate = Blocks.RED_NETHER_BRICKS.getDefaultState();
			}
			else
			{
				this.blockstate = Blocks.NETHER_BRICKS.getDefaultState();
			}
		}
	}
}