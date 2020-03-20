package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.Tags;


public class WellSnow extends Feature<NoFeatureConfig>
{
	private static final BlockState	SNOW				= Blocks.SNOW.getDefaultState().with(SnowBlock.LAYERS, 5);
	private static final BlockState	SNOW_BLOCK			= Blocks.SNOW_BLOCK.getDefaultState();
	private static final BlockState	STONE				= Blocks.STONE.getDefaultState();
	private static final BlockState	ICE					= Blocks.ICE.getDefaultState();
	private static final BlockState	AIR					= Blocks.AIR.getDefaultState();
	private static final BlockState	BELL				= Blocks.BELL.getDefaultState();
	private static final BlockState	LAPIS_ORE			= Blocks.LAPIS_ORE.getDefaultState();
	private static final float		LAPIS_CHANCE		= 0.3f;


	public WellSnow(Function<Dynamic<?>, ? extends NoFeatureConfig> config)
	{
		super(config);
	}


	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random random, BlockPos position, NoFeatureConfig config)
	{
		//move to top land block below position
		for (position = position.up(); (world.isAirBlock(position) || world.getBlockState(position).getBlock() == Blocks.SNOW) && position.getY() > 2; position = position.down()){
			;
		}
		BlockPos.Mutable mutable = new BlockPos.Mutable(position);

		Block block = world.getBlockState(mutable).getBlock();
		if (block == Blocks.SNOW_BLOCK || 
			Tags.Blocks.DIRT.contains(block) || 
			block == world.getBiome(mutable).getSurfaceBuilderConfig().getTop().getBlock())
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
						world.setBlockState(mutable.add(x, y, z), SNOW_BLOCK, 2);
					}
				}
			}

			world.setBlockState(mutable.up(), AIR, 2);
			world.setBlockState(mutable, ICE, 2);
			if (random.nextFloat() < LAPIS_CHANCE)
			{
				world.setBlockState(mutable.down(), LAPIS_ORE, 2);
			}
			else
			{
				world.setBlockState(mutable.down(), STONE, 2);
			}

			for (Direction direction : Direction.Plane.HORIZONTAL)
			{
				mutable.setPos(position).move(direction);
				world.setBlockState(mutable.up(), AIR, 2);
				world.setBlockState(mutable, ICE, 2);

				mutable.move(Direction.DOWN);
				if (random.nextFloat() < LAPIS_CHANCE)
				{
					world.setBlockState(mutable, LAPIS_ORE, 2);
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
						world.setBlockState(mutable.add(x, 1, z), SNOW_BLOCK, 2);
					}
				}
			}

			world.setBlockState(mutable.add(2, 1, 0), SNOW, 2);
			world.setBlockState(mutable.add(-2, 1, 0), SNOW, 2);
			world.setBlockState(mutable.add(0, 1, 2), SNOW, 2);
			world.setBlockState(mutable.add(0, 1, -2), SNOW, 2);

			for (int x = -1; x <= 1; ++x)
			{
				for (int z = -1; z <= 1; ++z)
				{
					if (x == 0 && z == 0)
					{
						world.setBlockState(mutable.add(x, 4, z), SNOW_BLOCK, 2);
						world.setBlockState(mutable.add(x, 5, z), SNOW, 2);
						
						if(random.nextInt(100) == 0) 
							world.setBlockState(mutable.add(x, 3, z), BELL, 2);
					}
					else if (x == 0 || z == 0)
					{
						world.setBlockState(mutable.add(x, 4, z), SNOW_BLOCK, 2);
					}
					else
					{
						world.setBlockState(mutable.add(x, 4, z), SNOW, 2);
					}
				}
			}

			for (int y = 1; y <= 3; ++y)
			{
				world.setBlockState(mutable.add(-1, y, -1), SNOW_BLOCK, 2);
				world.setBlockState(mutable.add(-1, y, 1), SNOW_BLOCK, 2);
				world.setBlockState(mutable.add(1, y, -1), SNOW_BLOCK, 2);
				world.setBlockState(mutable.add(1, y, 1), SNOW_BLOCK, 2);
			}

			return true;
		}

		return false;
	}
}