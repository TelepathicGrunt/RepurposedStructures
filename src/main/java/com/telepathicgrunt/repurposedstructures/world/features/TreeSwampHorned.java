package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;


public class TreeSwampHorned extends AbstractTreeFeature<TreeFeatureConfig>
{
	private static final BlockState TRUNK = Blocks.OAK_LOG.getDefaultState();
	private static final BlockState LEAF = Blocks.OAK_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, Integer.valueOf(1));


	public TreeSwampHorned(Function<Dynamic<?>, ? extends TreeFeatureConfig> config)
	{
		super(config);
	}


	//generate the spooky horned swamp m trees
	@Override
	public boolean place(IWorldGenerationReader worldReader, Random rand, BlockPos position, Set<BlockPos> leaveSet, Set<BlockPos> trunkSet, MutableBoundingBox boundingBox, TreeFeatureConfig config)
	{
		int height = rand.nextInt(4) + 6;
		IWorld world = (IWorld) worldReader;

		//checks to see if there is room to generate tree
		if (!this.isSpaceAt(world, position, height))
		{
			return false;
		}

		//sets tree in water if there is water below
		for (; world.getBlockState(position.down()).getMaterial() == Material.WATER; position = position.down())
		{
			;
		}

		boolean flag = true;

		if (position.getY() >= 1 && position.getY() + height + 1 <= 256)
		{
			for (int y = position.getY(); y <= position.getY() + 1 + height; ++y)
			{
				int radius = 1;

				if (y == position.getY())
				{
					radius = 0;
				}

				if (y >= position.getY() + 1 + height - 2)
				{
					radius = 3;
				}

				BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable();

				for (int x = position.getX() - radius; x <= position.getX() + radius && flag; ++x)
				{
					for (int z = position.getZ() - radius; z <= position.getZ() + radius && flag; ++z)
					{
						if (y >= 0 && y < 256)
						{
							blockpos$Mutable.setPos(x, y, z);
							if (!isAirOrLeaves(world, blockpos$Mutable))
							{
								if (isWater(world, blockpos$Mutable))
								{
									flag = false;
								}
								else if (y > position.getY())
								{
									flag = false;
								}
							}
						}
						else
						{
							flag = false;
						}
					}
				}
			}

			if (!flag)
			{
				return false;
			}
			else if (isSoil(world, position.down(), config.getSapling()) && position.getY() < world.getMaxHeight() - height - 1)
			{
				this.setDirtAt(world, position.down(), position);

				for (int currentHeight = position.getY() - 4 + height; currentHeight <= position.getY() + height; ++currentHeight)
				{
					int heightDiff = currentHeight - (position.getY() + height);
					int l2 = 2 - heightDiff / 2;

					for (int x = position.getX() - l2 - 1; x <= position.getX() + l2; ++x)
					{
						int xPos = x - position.getX();

						for (int z = position.getZ() - l2 - 1; z <= position.getZ() + l2; ++z)
						{
							int zPos = z - position.getZ();
							int isCornerIfThisIsTwo = 0;

							if (xPos == l2)
							{
								isCornerIfThisIsTwo++;
							}
							if (zPos == l2)
							{
								isCornerIfThisIsTwo++;
							}
							if (xPos == -l2 - 1)
							{
								isCornerIfThisIsTwo++;
							}
							if (zPos == -l2 - 1)
							{
								isCornerIfThisIsTwo++;
							}

							//generate leaves if is in corners or if 2/3rd rng is true
							if (isCornerIfThisIsTwo == 2 || rand.nextInt(3) < 2 && heightDiff != 0)
							{
								BlockPos blockpos = new BlockPos(x, currentHeight, z);

								if (isAirOrLeaves(world, blockpos) || isTallPlants(world, blockpos))
								{
									this.setBlockState(world, blockpos, LEAF);
								}
							}
						}
					}
				}

				//the following four for statements generates the trunk of the tree
				genTrunk(world, position, height);
				genTrunk(world, position.west(), height);
				genTrunk(world, position.north(), height);
				genTrunk(world, position.west().north(), height);

				//vine generation
				for (int currentHeight = position.getY() - 3 + height; currentHeight <= position.getY() + height; ++currentHeight)
				{
					int heightDiff = currentHeight - (position.getY() + height);
					int i3 = 2 - heightDiff / 2;
					BlockPos.Mutable blockpos$Mutable1 = new BlockPos.Mutable();

					for (int x = position.getX() - i3 - 1; x <= position.getX() + i3; ++x)
					{
						for (int z = position.getZ() - i3 - 1; z <= position.getZ() + i3; ++z)
						{
							blockpos$Mutable1.setPos(x, currentHeight, z);

							if (world.getBlockState(blockpos$Mutable1).getMaterial() == Material.LEAVES)
							{
								BlockPos blockpos3 = blockpos$Mutable1.west();
								BlockPos blockpos4 = blockpos$Mutable1.east();
								BlockPos blockpos1 = blockpos$Mutable1.north();
								BlockPos blockpos2 = blockpos$Mutable1.south();

								if (rand.nextInt(4) == 0 && world.isAirBlock(blockpos3))
								{
									this.addVine(world, blockpos3, VineBlock.EAST);
								}

								if (rand.nextInt(4) == 0 && world.isAirBlock(blockpos4))
								{
									this.addVine(world, blockpos4, VineBlock.WEST);
								}

								if (rand.nextInt(4) == 0 && world.isAirBlock(blockpos1))
								{
									this.addVine(world, blockpos1, VineBlock.SOUTH);
								}

								if (rand.nextInt(4) == 0 && world.isAirBlock(blockpos2))
								{
									this.addVine(world, blockpos2, VineBlock.NORTH);
								}
							}
						}
					}
				}

				return true;
			}
			else
			{
				return false;
			}

		}
		else
		{
			return false;
		}
	}


	private void addVine(IWorld world, BlockPos pos, BooleanProperty prop)
	{
		BlockState iblockstate = Blocks.VINE.getDefaultState().with(prop, Boolean.valueOf(true));
		this.setBlockState(world, pos, iblockstate);
		int i = 4;

		for (BlockPos blockpos = pos.down(); world.isAirBlock(blockpos) && i > 0; --i)
		{
			this.setBlockState(world, blockpos, iblockstate);
			blockpos = blockpos.down();
		}
	}


	private void genTrunk(IWorld world, BlockPos position, int height)
	{
		this.setBlockState(world, position.down(), Blocks.DIRT.getDefaultState());
		
		for (int currentHeight = 0; currentHeight < height; ++currentHeight)
		{
			BlockPos upN = position.up(currentHeight);
			BlockState iblockstate1 = world.getBlockState(upN);
			Block block2 = iblockstate1.getBlock();

			if (currentHeight != height - 1 && (block2.isAir(iblockstate1, world, upN) || iblockstate1.isIn(BlockTags.LEAVES) || block2 == Blocks.WATER || block2 == Blocks.LILY_PAD))
			{
				this.setBlockState(world, upN, TRUNK);
			}
			else
			{
				this.setBlockState(world, upN, LEAF);
			}
		}
	}


	private boolean isSpaceAt(IWorldGenerationBaseReader world, BlockPos leavesPos, int height)
	{
		boolean spaceFound = true;
		if (leavesPos.getY() >= 1 && leavesPos.getY() + height + 1 <= world.getMaxHeight())
		{
			for (int y = 0; y <= 1 + height; ++y)
			{
				int radius = 2;
				if (y == 0)
				{
					radius = 1;
				}
				else if (y >= 1 + height - 2)
				{
					radius = 2;
				}

				for (int x = -radius; x <= radius && spaceFound; ++x)
				{
					for (int z = -radius; z <= radius && spaceFound; ++z)
					{
						if (leavesPos.getY() + y < 0 || leavesPos.getY() + y >= world.getMaxHeight() || !canBeReplacedByLogs(world, leavesPos.add(x, y, z)))
						{
							spaceFound = false;
						}
					}
				}
			}

			return spaceFound;
		}
		else
		{
			return false;
		}
	}
}