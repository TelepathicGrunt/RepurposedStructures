package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.Tags;


public class WellMossyStone extends Feature<NoFeatureConfig>
{
	private static final BlockState	MOSSY_STONE_BRICK_SLAB	= Blocks.MOSSY_STONE_BRICK_SLAB.getDefaultState();
	private static final BlockState	STONE_BRICK_SLAB		= Blocks.STONE_BRICK_SLAB.getDefaultState();
	private static final BlockState	MOSSY_STONE_BRICKS		= Blocks.MOSSY_STONE_BRICKS.getDefaultState();
	private static final BlockState	STONE_BRICKS			= Blocks.STONE_BRICKS.getDefaultState();
	private static final BlockState	MOSSY_STONE_BRICK_WALL	= Blocks.MOSSY_STONE_BRICK_WALL.getDefaultState();
	private static final BlockState	STONE_BRICK_WALL		= Blocks.STONE_BRICK_WALL.getDefaultState();
	private static final BlockState	MOSSY_COBBLESTONE		= Blocks.MOSSY_COBBLESTONE.getDefaultState();
	private static final BlockState	COBBLESTONE				= Blocks.COBBLESTONE.getDefaultState();
	private static final BlockState	AIR						= Blocks.AIR.getDefaultState();
	private static final BlockState	WATER					= Blocks.WATER.getDefaultState();
	private static final BlockState	BELL					= Blocks.BELL.getDefaultState();
	private static final BlockState	EMERALD_ORE				= Blocks.EMERALD_ORE.getDefaultState();
	private static final float		EMERALD_CHANCE			= 0.12f;


	public WellMossyStone(Function<Dynamic<?>, ? extends NoFeatureConfig> config)
	{
		super(config);
	}


	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random random, BlockPos position, NoFeatureConfig config)
	{
		//move to top land block below position
		for (position = position.up(); (world.isAirBlock(position) || world.getBlockState(position).getMaterial() == Material.WATER) && position.getY() > 2; position = position.down()){
			;
		}
		BlockPos.Mutable mutable = new BlockPos.Mutable(position);
		boolean isWithinWaterArea = false;
		boolean isBelowSealevel = false;
		BlockState state1 = null;
		BlockState state2 = null;
		
		Block block = world.getBlockState(mutable).getBlock();
		if (Tags.Blocks.SAND.contains(block) || block == Blocks.CLAY || Tags.Blocks.DIRT.contains(block))
		{
			for (int x = -2; x <= 2; ++x)
			{
				for (int z = -2; z <= 2; ++z)
				{
					if (world.isAirBlock(mutable.down()) && world.isAirBlock(mutable.down(2)))
					{
						return false;
					}
				}
			}

			for (int y = -1; y <= 0; ++y)
			{
				for (int x = -2; x <= 2; ++x)
				{
					for (int z = -2; z <= 2; ++z)
					{
						world.setBlockState(mutable.add(x, y, z), pickRandomBlock(MOSSY_STONE_BRICKS, STONE_BRICKS, random, 0.6f), 2);
					}
				}
			}

			isWithinWaterArea = world.getBlockState(mutable.up()).getMaterial() == Material.WATER;
			isBelowSealevel = isWithinWaterArea && mutable.up().getY() < world.getSeaLevel();
			world.setBlockState(mutable.up(), isBelowSealevel ? WATER : AIR, 2);
			world.setBlockState(mutable, WATER, 2);
			if (random.nextFloat() < EMERALD_CHANCE)
			{
				world.setBlockState(mutable.down(), EMERALD_ORE, 2);
			}
			else
			{
				world.setBlockState(mutable.down(), pickRandomBlock(MOSSY_COBBLESTONE, COBBLESTONE, random, 0.5f), 2);
			}

			for (Direction direction : Direction.Plane.HORIZONTAL)
			{
				mutable.setPos(position).move(direction);
				world.setBlockState(mutable.up(), isBelowSealevel ? WATER : AIR, 2);
				world.setBlockState(mutable, WATER, 2);

				mutable.move(Direction.DOWN);
				if (random.nextFloat() < EMERALD_CHANCE)
				{
					world.setBlockState(mutable, EMERALD_ORE, 2);
				}
				else
				{
					world.setBlockState(mutable, pickRandomBlock(MOSSY_COBBLESTONE, COBBLESTONE, random, 0.5f), 2);
				}
			}
			mutable.setPos(position);

			for (int x = -2; x <= 2; ++x)
			{
				for (int z = -2; z <= 2; ++z)
				{
					if ((x == -2 || x == 2 || z == -2 || z == 2) && (x != 0 && z != 0))
					{
						world.setBlockState(mutable.add(x, 1, z), pickRandomBlock(MOSSY_STONE_BRICKS, STONE_BRICKS, random, 0.6f), 2);
					}
				}
			}


			state1 = isBelowSealevel ?  MOSSY_STONE_BRICK_SLAB.with(SlabBlock.WATERLOGGED, true) : MOSSY_STONE_BRICK_SLAB;
			state2 = isBelowSealevel ?  STONE_BRICK_SLAB.with(SlabBlock.WATERLOGGED, true) : STONE_BRICK_SLAB;
			world.setBlockState(mutable.add(2, 1, 0), pickRandomBlock(state1, state2, random, 0.6f), 2);

			state1 = isBelowSealevel ?  MOSSY_STONE_BRICK_SLAB.with(SlabBlock.WATERLOGGED, true) : MOSSY_STONE_BRICK_SLAB;
			state2 = isBelowSealevel ?  STONE_BRICK_SLAB.with(SlabBlock.WATERLOGGED, true) : STONE_BRICK_SLAB;
			world.setBlockState(mutable.add(-2, 1, 0), pickRandomBlock(state1, state2, random, 0.6f), 2);

			state1 = isBelowSealevel ?  MOSSY_STONE_BRICK_SLAB.with(SlabBlock.WATERLOGGED, true) : MOSSY_STONE_BRICK_SLAB;
			state2 = isBelowSealevel ?  STONE_BRICK_SLAB.with(SlabBlock.WATERLOGGED, true) : STONE_BRICK_SLAB;
			world.setBlockState(mutable.add(0, 1, 2), pickRandomBlock(state1, state2, random, 0.6f), 2);

			state1 = isBelowSealevel ?  MOSSY_STONE_BRICK_SLAB.with(SlabBlock.WATERLOGGED, true) : MOSSY_STONE_BRICK_SLAB;
			state2 = isBelowSealevel ?  STONE_BRICK_SLAB.with(SlabBlock.WATERLOGGED, true) : STONE_BRICK_SLAB;
			world.setBlockState(mutable.add(0, 1, -2), pickRandomBlock(state1, state2, random, 0.6f), 2);

			for (int x = -1; x <= 1; ++x)
			{
				for (int z = -1; z <= 1; ++z)
				{
					if (x == 0 && z == 0)
					{
						world.setBlockState(mutable.add(x, 4, z), pickRandomBlock(MOSSY_STONE_BRICKS, STONE_BRICKS, random, 0.6f), 2);
						
						if(random.nextInt(100) == 0) 
							world.setBlockState(mutable.add(x, 3, z), BELL, 2);
					}
					else
					{
						isBelowSealevel = isWithinWaterArea && mutable.up(4).getY() < world.getSeaLevel();
						state1 = isBelowSealevel ?  MOSSY_STONE_BRICK_SLAB.with(SlabBlock.WATERLOGGED, true) : MOSSY_STONE_BRICK_SLAB;
						state2 = isBelowSealevel ?  STONE_BRICK_SLAB.with(SlabBlock.WATERLOGGED, true) : STONE_BRICK_SLAB;
						world.setBlockState(mutable.add(x, 4, z), pickRandomBlock(state1, state2, random, 0.6f), 2);
					}
				}
			}

			for (int y = 1; y <= 3; ++y)
			{
				isBelowSealevel = isWithinWaterArea && mutable.up(y).getY() < world.getSeaLevel();
				state1 = isBelowSealevel ?  MOSSY_STONE_BRICK_WALL.with(SlabBlock.WATERLOGGED, true) : MOSSY_STONE_BRICK_WALL;
				state2 = isBelowSealevel ?  STONE_BRICK_WALL.with(SlabBlock.WATERLOGGED, true) : STONE_BRICK_WALL;
				world.setBlockState(mutable.add(-1, y, -1), pickRandomBlock(state1, state2, random, 0.6f), 2);

				state1 = isBelowSealevel ?  MOSSY_STONE_BRICK_WALL.with(SlabBlock.WATERLOGGED, true) : MOSSY_STONE_BRICK_WALL;
				state2 = isBelowSealevel ?  STONE_BRICK_WALL.with(SlabBlock.WATERLOGGED, true) : STONE_BRICK_WALL;
				world.setBlockState(mutable.add(-1, y, 1), pickRandomBlock(state1, state2, random, 0.6f), 2);

				state1 = isBelowSealevel ?  MOSSY_STONE_BRICK_WALL.with(SlabBlock.WATERLOGGED, true) : MOSSY_STONE_BRICK_WALL;
				state2 = isBelowSealevel ?  STONE_BRICK_WALL.with(SlabBlock.WATERLOGGED, true) : STONE_BRICK_WALL;
				world.setBlockState(mutable.add(1, y, -1), pickRandomBlock(state1, state2, random, 0.6f), 2);

				state1 = isBelowSealevel ?  MOSSY_STONE_BRICK_WALL.with(SlabBlock.WATERLOGGED, true) : MOSSY_STONE_BRICK_WALL;
				state2 = isBelowSealevel ?  STONE_BRICK_WALL.with(SlabBlock.WATERLOGGED, true) : STONE_BRICK_WALL;
				world.setBlockState(mutable.add(1, y, 1), pickRandomBlock(state1, state2, random, 0.6f), 2);
			}

			return true;
		}

		return false;
	}
	
	private static BlockState pickRandomBlock(BlockState block1, BlockState block2, Random random, float firstBlockChance)
	{
		return random.nextFloat() < firstBlockChance ? block1 : block2;
	}
}