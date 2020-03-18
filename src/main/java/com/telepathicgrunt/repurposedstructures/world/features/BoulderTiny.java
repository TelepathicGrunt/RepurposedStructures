package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;


public class BoulderTiny extends Feature<NoFeatureConfig>
{
	public BoulderTiny(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory)
	{
		super(configFactory);
	}

	private final static BlockState MOSSY_COBBLESTONE = Blocks.MOSSY_COBBLESTONE.getDefaultState();
	private final static BlockState COBBLESTONE = Blocks.COBBLESTONE.getDefaultState();
	private final static BlockState ANDESITE = Blocks.ANDESITE.getDefaultState();
	private final static BlockState COAL_ORE = Blocks.COAL_ORE.getDefaultState();
	private final static BlockState IRON_ORE = Blocks.IRON_ORE.getDefaultState();
	private final static int START_RADIUS = 0;


	@Override
	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random random, BlockPos position, NoFeatureConfig config)
	{

		BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable(position.down());
		Block block = world.getBlockState(blockpos$Mutable).getBlock();

		//boulder can only generate on grass/dirt
		if (block != Blocks.GRASS_BLOCK && block != Blocks.PODZOL && !isDirt(block))
		{
			return false;
		}

		for (int currentCount = 0; START_RADIUS >= 0 && currentCount < 3; ++currentCount)
		{
			int x = START_RADIUS + random.nextInt(2);
			int y = START_RADIUS + random.nextInt(2);
			int z = START_RADIUS + random.nextInt(2);
			float calculatedDistance = (x + y + z) * 0.333F + 0.5F;

			for (BlockPos blockpos : BlockPos.getAllInBoxMutable(blockpos$Mutable.add(-x, -y, -z), blockpos$Mutable.add(x, y, z)))
			{
				if (blockpos.distanceSq(blockpos$Mutable) <= calculatedDistance * calculatedDistance)
				{
					//adds the blocks for generation in this boulder
					//note, if user turns off an ore, that ore's chance is dumped into the below ore for generation
					int randomChance = random.nextInt(1400);

					// 40/1400th chance for iron ore
					if (randomChance <= 40)
					{
						world.setBlockState(blockpos.up(), IRON_ORE, 4);
					}

					// 60/1400th chance for coal ore
					else if (randomChance <= 100)
					{
						world.setBlockState(blockpos.up(), COAL_ORE, 4);
					}

					// 300/1400th chance for andesite
					else if (randomChance <= 400)
					{
						world.setBlockState(blockpos.up(), ANDESITE, 4);
					}

					// 300/1400th chance for cobblestone
					else if (randomChance <= 700)
					{
						world.setBlockState(blockpos.up(), COBBLESTONE, 4);
					}

					// 700/1400th chance for mossyCobblestone
					else
					{
						world.setBlockState(blockpos.up(), MOSSY_COBBLESTONE, 4);
					}
				}
			}
			blockpos$Mutable.move(-(START_RADIUS + 1) + random.nextInt(2 + START_RADIUS * 2), -random.nextInt(2), -(START_RADIUS + 1) + random.nextInt(2 + START_RADIUS * 2));
		}

		//finished generating the boulder
		return true;
	}
}
