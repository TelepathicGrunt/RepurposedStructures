package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;


public class VinesShort extends Feature<NoFeatureConfig>
{

	public VinesShort(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory)
	{
		super(configFactory);
	}


	@Override
	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> changedBlocks, Random rand, BlockPos position, NoFeatureConfig config)
	{
		if (!world.isAirBlock(position))
		{
			return false;
		}
		
		//generates vines from given position down 4-6 blocks if path is clear and the given position is valid
		//Also won't generate vines below Y = 15.
		int length = 0;

		BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable(position);
		BlockPos.Mutable blockpos$Mutable2 = new BlockPos.Mutable(position);
		for (; blockpos$Mutable.getY() > 15 && length < rand.nextInt(3)+4; blockpos$Mutable.move(Direction.DOWN))
		{
			if (world.isAirBlock(blockpos$Mutable))
			{
				for (Direction Direction : Direction.Plane.HORIZONTAL)
				{
					BlockState iblockstate = Blocks.VINE.getDefaultState().with(VineBlock.getPropertyFor(Direction), Boolean.valueOf(true));
					blockpos$Mutable2.setPos(blockpos$Mutable).move(net.minecraft.util.Direction.UP);
					if (iblockstate.isValidPosition(world, blockpos$Mutable))
					{
						world.setBlockState(blockpos$Mutable, iblockstate, 2);
						length++;
						break;
					}
					else if (world.getBlockState(blockpos$Mutable2).getBlock() == Blocks.VINE)
					{
						world.setBlockState(blockpos$Mutable, world.getBlockState(blockpos$Mutable2), 2);
						length++;
						break;
					}
				}
			}
			else
			{
				break;
			}
		}

		return true;
	}
}