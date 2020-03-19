package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Random;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.storage.loot.LootTables;


public class DungeonEnd extends Feature<NoFeatureConfig>
{
	public DungeonEnd(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory)
	{
		super(configFactory);
	}

	private static final Logger LOGGER = LogManager.getLogger();
	private static final BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();


	//only the mob spawner chance and what blocks the wall cannot replace was changed. Everything else is just the normal dungeon code.

	@Override
	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> changedBlock, Random rand, BlockPos position, NoFeatureConfig p_212245_5_)
	{
		int randXRange = rand.nextInt(2) + 2;
		int xMin = -randXRange - 1;
		int xMax = randXRange + 1;
		int randZRange = rand.nextInt(2) + 2;
		int zMin = -randZRange - 1;
		int zMax = randZRange + 1;
		int validOpeneings = 0;
		int ceilingOpenings = 0;
		BlockPos newPosition = new BlockPos(position.getX(), rand.nextInt(world.getHeight(Heightmap.Type.WORLD_SURFACE, position.getX(), position.getZ())+5), position.getZ());
		BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable(newPosition);

		for (int x = xMin; x <= xMax; ++x)
		{
			for (int y = -1; y <= 4; ++y)
			{
				for (int z = zMin; z <= zMax; ++z)
				{
					blockpos$Mutable.setPos(newPosition).move(x, y, z);
					Material material = world.getBlockState(blockpos$Mutable).getMaterial();
					boolean flag = material.isSolid();

					if (y == -1 && !flag)
					{
						return false;
					}

					if (y == 4 && !flag)
					{
						ceilingOpenings++;
					}

					if ((x == xMin || x == xMax || z == zMin || z == zMax) && y == 0 && world.isAirBlock(blockpos$Mutable) && world.isAirBlock(blockpos$Mutable.up()))
					{
						++validOpeneings;
					}
				}
			}
		}

		if (validOpeneings >= 1 && validOpeneings <= 14 && ceilingOpenings < 14)
		{
			for (int x = xMin; x <= xMax; ++x)
			{
				for (int y = 3; y >= -1; --y)
				{
					for (int z = zMin; z <= zMax; ++z)
					{
						blockpos$Mutable.setPos(newPosition).move(x, y, z);

						if (x != xMin && y != -1 && z != zMin && x != xMax && y != 4 && z != zMax)
						{
							if (world.getBlockState(blockpos$Mutable).getBlock() != Blocks.CHEST && world.getBlockState(blockpos$Mutable).getBlock() != Blocks.SPAWNER)
							{
								world.setBlockState(blockpos$Mutable, CAVE_AIR, 2);
							}
						}
						else if (blockpos$Mutable.getY() >= 0 && !world.getBlockState(blockpos$Mutable.down()).getMaterial().isSolid())
						{
							world.setBlockState(blockpos$Mutable, CAVE_AIR, 2);
						}

						//made sure the dungeon wall cannot replace other dungeon's mob spawner now.
						else if (world.getBlockState(blockpos$Mutable).getMaterial().isSolid() && world.getBlockState(blockpos$Mutable).getBlock() != Blocks.CHEST && world.getBlockState(blockpos$Mutable).getBlock() != Blocks.SPAWNER)
						{
							world.setBlockState(blockpos$Mutable, Blocks.END_STONE_BRICKS.getDefaultState(), 2);
						}
					}
				}
			}

			for (int j4 = 0; j4 < rand.nextInt(4); ++j4)
			{
				int x = newPosition.getX() + rand.nextInt(randXRange * 2 + 1) - randXRange;
				int y = newPosition.getY();
				int z = newPosition.getZ() + rand.nextInt(randZRange * 2 + 1) - randZRange;
				blockpos$Mutable.setPos(x, y, z);

				if (world.isAirBlock(blockpos$Mutable))
				{
					int j3 = 0;

					for (Direction Direction : Direction.Plane.HORIZONTAL)
					{
						if (world.getBlockState(blockpos$Mutable.offset(Direction)).getMaterial().isSolid())
						{
							++j3;
						}
					}

					if (j3 == 1)
					{
						world.setBlockState(blockpos$Mutable, Blocks.SHULKER_BOX.getDefaultState(), 2);
						LockableLootTileEntity.setLootTable(world, rand, blockpos$Mutable, LootTables.CHESTS_SIMPLE_DUNGEON);

						break;
					}
				}
			}

			world.setBlockState(newPosition, Blocks.END_PORTAL_FRAME.getDefaultState().with(BlockStateProperties.EYE, true), 2);
			world.setBlockState(newPosition.down(), Blocks.SPAWNER.getDefaultState(), 2);
			TileEntity tileentity = world.getTileEntity(newPosition.down());

			if (tileentity instanceof MobSpawnerTileEntity)
			{
				((MobSpawnerTileEntity) tileentity).getSpawnerBaseLogic().setEntityType(this.pickMobSpawner(world, rand));
			}
			else
			{
				LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})", new Object[] { Integer.valueOf(newPosition.getX()), Integer.valueOf(newPosition.down().getY()), Integer.valueOf(newPosition.getZ()) });
			}

			return true;
		}
		else
		{
			return false;
		}
	}


	/**
	 * Randomly decides which spawner to use in a dungeon
	 */
	private EntityType<?> pickMobSpawner(IWorld world, Random rand)
	{
		int roll = rand.nextInt(100);

		if (roll < 86)
		{
			//86% chance
			return EntityType.ENDERMITE;
		}
		else if (roll < 98)
		{
			//12% chance
			return EntityType.ENDERMAN;
		}
		else
		{
			//2% chance
			return EntityType.PHANTOM;
		}
	}
}
