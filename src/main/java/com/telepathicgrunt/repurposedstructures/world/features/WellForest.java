package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.Tags;


public class WellForest extends Feature<NoFeatureConfig>
{
	private static final BlockState	OAK_SLAB			= Blocks.OAK_SLAB.getDefaultState();
	private static final BlockState	STRIPPED_OAK_WOOD	= Blocks.STRIPPED_OAK_WOOD.getDefaultState();
	private static final BlockState	OAK_FENCE			= Blocks.OAK_FENCE.getDefaultState();
	private static final BlockState	DIRT				= Blocks.DIRT.getDefaultState();
	private static final BlockState	GRASS_BLOCK			= Blocks.GRASS_BLOCK.getDefaultState();
	private static final BlockState	STONE				= Blocks.STONE.getDefaultState();
	private static final BlockState	WATER				= Blocks.WATER.getDefaultState();
	private static final BlockState	AIR					= Blocks.AIR.getDefaultState();
	private static final BlockState	BELL				= Blocks.BELL.getDefaultState();
	private static final BlockState	IRON_ORE			= Blocks.IRON_ORE.getDefaultState();
	private static final float		IRON_CHANCE			= 0.3f;


	public WellForest(Function<Dynamic<?>, ? extends NoFeatureConfig> config)
	{
		super(config);
	}


	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random random, BlockPos position, NoFeatureConfig config)
	{
		//move to top land block below position
		for (position = position.up(); world.isAirBlock(position) && position.getY() > 2; position = position.down()){
			;
		}
		BlockPos.Mutable mutable = new BlockPos.Mutable(position);

		Block block = world.getBlockState(mutable).getBlock();
		if (Tags.Blocks.DIRT.contains(block))
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

			for (int y = 0; y >= -4; --y)
			{
				for (int x = -3; x <= 3; ++x)
				{
					for (int z = -3; z <= 3; ++z)
					{
						if(y == 0)
						{
							world.setBlockState(mutable.add(x, y, z), GRASS_BLOCK, 2);
						}
						else if(x*x+z*z <= 14 - y*3)
						{
							world.setBlockState(mutable.add(x, y, z), DIRT, 2);
						}
					}
				}
			}
			

			world.setBlockState(mutable.up(), AIR, 2);
			world.setBlockState(mutable, WATER, 2);
			if (random.nextFloat() < IRON_CHANCE)
			{
				world.setBlockState(mutable.down(), IRON_ORE, 2);
			}
			else
			{
				world.setBlockState(mutable.down(), STONE, 2);
			}

			for (Direction direction : Direction.Plane.HORIZONTAL)
			{
				mutable.setPos(position).move(direction);
				world.setBlockState(mutable.up(), AIR, 2);
				world.setBlockState(mutable, WATER, 2);

				mutable.move(Direction.DOWN);
				if (random.nextFloat() < IRON_CHANCE)
				{
					world.setBlockState(mutable, IRON_ORE, 2);
				}
				else
				{
					world.setBlockState(mutable, STONE, 2);
				}
			}
			mutable.setPos(position);

			for (int x = -2; x <= 2; ++x)
			{
				for (int z = -2; z <= 2; ++z)
				{
					if (x == -2 || x == 2 || z == -2 || z == 2)
					{
						world.setBlockState(mutable.add(x, 1, z), STRIPPED_OAK_WOOD, 2);
					}
				}
			}

			world.setBlockState(mutable.add(2, 1, 0), OAK_SLAB, 2);
			world.setBlockState(mutable.add(-2, 1, 0), OAK_SLAB, 2);
			world.setBlockState(mutable.add(0, 1, 2), OAK_SLAB, 2);
			world.setBlockState(mutable.add(0, 1, -2), OAK_SLAB, 2);

			for (int x = -1; x <= 1; ++x)
			{
				for (int z = -1; z <= 1; ++z)
				{
					if (x == 0 && z == 0)
					{
						world.setBlockState(mutable.add(x, 4, z), STRIPPED_OAK_WOOD, 2);
						
						if(random.nextInt(100) == 0) 
							world.setBlockState(mutable.add(x, 3, z), BELL, 2);
					}
					else
					{
						world.setBlockState(mutable.add(x, 4, z), OAK_SLAB, 2);
					}
				}
			}

			for (int y = 1; y <= 3; ++y)
			{
				world.setBlockState(mutable.add(-1, y, -1), OAK_FENCE, 2);
				world.setBlockState(mutable.add(-1, y, 1), OAK_FENCE, 2);
				world.setBlockState(mutable.add(1, y, -1), OAK_FENCE, 2);
				world.setBlockState(mutable.add(1, y, 1), OAK_FENCE, 2);
			}

			return true;
		}

		return false;
	}
}