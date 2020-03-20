package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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

		Block block = world.getBlockState(mutable).getBlock();
		Boolean inShallowWater = block == Blocks.WATER && world.getBlockState(mutable.down()).getMaterial() == Material.WATER;
		if (Tags.Blocks.SAND.contains(block) || block == Blocks.CLAY || Tags.Blocks.DIRT.contains(block) || inShallowWater)
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

			world.setBlockState(mutable.up(), inShallowWater ? WATER : AIR, 2);
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
				world.setBlockState(mutable.up(), inShallowWater ? WATER : AIR, 2);
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
					if (x == -2 || x == 2 || z == -2 || z == 2)
					{
						world.setBlockState(mutable.add(x, 1, z), pickRandomBlock(MOSSY_STONE_BRICKS, STONE_BRICKS, random, 0.6f), 2);
					}
				}
			}

			world.setBlockState(mutable.add(2, 1, 0), inShallowWater ? pickRandomBlock(MOSSY_STONE_BRICKS, STONE_BRICKS, random, 0.6f) : pickRandomBlock(MOSSY_STONE_BRICK_SLAB, STONE_BRICK_SLAB, random, 0.6f), 2);
			world.setBlockState(mutable.add(-2, 1, 0), inShallowWater ? pickRandomBlock(MOSSY_STONE_BRICKS, STONE_BRICKS, random, 0.6f) : pickRandomBlock(MOSSY_STONE_BRICK_SLAB, STONE_BRICK_SLAB, random, 0.6f), 2);
			world.setBlockState(mutable.add(0, 1, 2), inShallowWater ? pickRandomBlock(MOSSY_STONE_BRICKS, STONE_BRICKS, random, 0.6f) : pickRandomBlock(MOSSY_STONE_BRICK_SLAB, STONE_BRICK_SLAB, random, 0.6f), 2);
			world.setBlockState(mutable.add(0, 1, -2), inShallowWater ? pickRandomBlock(MOSSY_STONE_BRICKS, STONE_BRICKS, random, 0.6f) : pickRandomBlock(MOSSY_STONE_BRICK_SLAB, STONE_BRICK_SLAB, random, 0.6f), 2);

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
						world.setBlockState(mutable.add(x, 4, z), pickRandomBlock(MOSSY_STONE_BRICK_SLAB, STONE_BRICK_SLAB, random, 0.6f), 2);
					}
				}
			}

			for (int y = 1; y <= 3; ++y)
			{
				world.setBlockState(mutable.add(-1, y, -1), pickRandomBlock(MOSSY_STONE_BRICK_WALL, STONE_BRICK_WALL, random, 0.6f), 2);
				world.setBlockState(mutable.add(-1, y, 1), pickRandomBlock(MOSSY_STONE_BRICK_WALL, STONE_BRICK_WALL, random, 0.6f), 2);
				world.setBlockState(mutable.add(1, y, -1), pickRandomBlock(MOSSY_STONE_BRICK_WALL, STONE_BRICK_WALL, random, 0.6f), 2);
				world.setBlockState(mutable.add(1, y, 1), pickRandomBlock(MOSSY_STONE_BRICK_WALL, STONE_BRICK_WALL, random, 0.6f), 2);
			}
			
			if(inShallowWater)
			{
				world.setBlockState(mutable.add(-1, 1, -1), pickRandomBlock(MOSSY_STONE_BRICKS, STONE_BRICKS, random, 0.6f), 2);
				world.setBlockState(mutable.add(-1, 1, 1), pickRandomBlock(MOSSY_STONE_BRICKS, STONE_BRICKS, random, 0.6f), 2);
				world.setBlockState(mutable.add(1, 1, -1), pickRandomBlock(MOSSY_STONE_BRICKS, STONE_BRICKS, random, 0.6f), 2);
				world.setBlockState(mutable.add(1, 1, 1), pickRandomBlock(MOSSY_STONE_BRICKS, STONE_BRICKS, random, 0.6f), 2);
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