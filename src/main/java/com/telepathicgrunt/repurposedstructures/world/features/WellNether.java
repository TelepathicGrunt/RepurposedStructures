package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Collection;
import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.Tags;


public class WellNether extends Feature<NoFeatureConfig>
{
	private static final BlockState	NETHER_BRICK_SLAB	= Blocks.NETHER_BRICK_SLAB.getDefaultState();
	private static final BlockState	NETHER_BRICKS		= Blocks.NETHER_BRICKS.getDefaultState();
	private static final BlockState	NETHER_BRICK_FENCE	= Blocks.NETHER_BRICK_FENCE.getDefaultState();
	private static final BlockState	GLOWSTONE		= Blocks.GLOWSTONE.getDefaultState();
	private static final BlockState	AIR			= Blocks.AIR.getDefaultState();
	private static final BlockState	LAVA			= Blocks.LAVA.getDefaultState();
	private static final BlockState	BELL			= Blocks.BELL.getDefaultState();
	private static final float COMMON_ORE_CHANCE		= 0.5f;
	private static final float RARE_ORE_CHANCE		= 0.08f;
	private static final ResourceLocation NETHER_WELL_ORE_RL = new ResourceLocation("repurposed_structures:nether_well_ores");


	public WellNether(Function<Dynamic<?>, ? extends NoFeatureConfig> config)
	{
		super(config);
	}


	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random random, BlockPos position, NoFeatureConfig config)
	{

		//if buried, skip generation
		if (!world.isAirBlock(position.up())){
			return false;
		}
		for (int x = -1; x < 2; x++){
			for (int z = -1; z < 2; z++){
				for (int up = 2; up < 4; up++){
					if (!world.isAirBlock(position.add(x, up, z))){
						return false;
					}
				}
			}
		}

		//move to top land block below position
		for (position = position.up(); world.isAirBlock(position) && position.getY() > 2; position = position.down()){
			;
		}
		BlockPos.Mutable mutable = new BlockPos.Mutable(position);

		Block block = world.getBlockState(mutable).getBlock();
		if (Tags.Blocks.NETHERRACK.contains(block) || 
			block == Blocks.SOUL_SAND || 
			block == Blocks.GRAVEL || 
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
						world.setBlockState(mutable.add(x, y, z), NETHER_BRICKS, 2);
					}
				}
			}
			Tag<Block> ORE_TAG = BlockTags.getCollection().getOrCreate(NETHER_WELL_ORE_RL);
			Collection<Block> allOreBlocks = ORE_TAG.getAllElements();

			world.setBlockState(mutable.up(), AIR, 2);
			world.setBlockState(mutable, LAVA, 2);
			float chance = random.nextFloat();
			if (!allOreBlocks.isEmpty() && chance < RARE_ORE_CHANCE)
			{
				world.setBlockState(mutable.down(), ((Block)allOreBlocks.toArray()[0]).getDefaultState(), 2);
			}
			else if (allOreBlocks.size() > 1 && chance - RARE_ORE_CHANCE < COMMON_ORE_CHANCE)
			{
				world.setBlockState(mutable.down(), ((Block)allOreBlocks.toArray()[random.nextInt(allOreBlocks.size()-1)+1]).getDefaultState(), 2);
			}

			for (Direction direction : Direction.Plane.HORIZONTAL)
			{
				chance = random.nextFloat();
				mutable.setPos(position).move(direction);
				world.setBlockState(mutable.up(), AIR, 2);
				world.setBlockState(mutable, LAVA, 2);

				mutable.move(Direction.DOWN);
				if (!allOreBlocks.isEmpty() && chance < RARE_ORE_CHANCE)
				{
					world.setBlockState(mutable, ((Block)allOreBlocks.toArray()[0]).getDefaultState(), 2);
				}
				else if (allOreBlocks.size() > 1  && chance - RARE_ORE_CHANCE < COMMON_ORE_CHANCE)
				{
					world.setBlockState(mutable, ((Block)allOreBlocks.toArray()[random.nextInt(allOreBlocks.size()-1)+1]).getDefaultState(), 2);
				}
			}
			mutable.setPos(position);

			for (int x = -2; x <= 2; ++x)
			{
				for (int z = -2; z <= 2; ++z)
				{
					if (x == -2 || x == 2 || z == -2 || z == 2)
					{
						world.setBlockState(mutable.add(x, 1, z), NETHER_BRICKS, 2);
					}
				}
			}

			world.setBlockState(mutable.add(2, 1, 0), NETHER_BRICK_SLAB, 2);
			world.setBlockState(mutable.add(-2, 1, 0), NETHER_BRICK_SLAB, 2);
			world.setBlockState(mutable.add(0, 1, 2), NETHER_BRICK_SLAB, 2);
			world.setBlockState(mutable.add(0, 1, -2), NETHER_BRICK_SLAB, 2);

			for (int x = -1; x <= 1; ++x)
			{
				for (int z = -1; z <= 1; ++z)
				{
					if (x == 0 && z == 0)
					{
						world.setBlockState(mutable.add(x, 4, z), GLOWSTONE, 2);
						world.setBlockState(mutable.add(x, 5, z), NETHER_BRICK_SLAB, 2);
						
						if(RepurposedStructures.RSWellsConfig.canHaveBells.get() && random.nextInt(100) == 0) 
							world.setBlockState(mutable.add(x, 3, z), BELL, 2);
					}
					else if (x == 0 || z == 0)
					{
						world.setBlockState(mutable.add(x, 4, z), NETHER_BRICKS, 2);
					}
					else
					{
						world.setBlockState(mutable.add(x, 4, z), NETHER_BRICK_SLAB, 2);
					}
				}
			}

			for (int y = 1; y <= 3; ++y)
			{
				world.setBlockState(mutable.add(-1, y, -1), NETHER_BRICK_FENCE, 2);
				world.setBlockState(mutable.add(-1, y, 1), NETHER_BRICK_FENCE, 2);
				world.setBlockState(mutable.add(1, y, -1), NETHER_BRICK_FENCE, 2);
				world.setBlockState(mutable.add(1, y, 1), NETHER_BRICK_FENCE, 2);
			}

			return true;
		}

		return false;
	}
}