package com.telepathicgrunt.repurposedstructures.world.features;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.block.VineBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;


public class DungeonJungle extends Feature<DefaultFeatureConfig>
{
    public DungeonJungle(Codec<DefaultFeatureConfig>configFactory) {
	super(configFactory);
    }

    private static final Logger LOGGER = LogManager.getLogger();
    private static final BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();
    private static final BlockState LEAVES = Blocks.JUNGLE_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, Integer.valueOf(1));
    private static final BlockState LOGS = Blocks.JUNGLE_LOG.getDefaultState();
    private static final BlockState SIDEWAYS_LOGS = Blocks.JUNGLE_LOG.getDefaultState().with(Properties.AXIS, Direction.Axis.X);
    private static final BlockState PLANKS = Blocks.JUNGLE_PLANKS.getDefaultState();
    private static final Identifier CHEST_LOOT = new Identifier(RepurposedStructures.MODID + ":chests/dungeon_jungle");


    // only the mob spawner chance and what blocks the wall cannot replace was changed. Everything else is just the normal dungeon code.

    @Override
    public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config) 
    {
	int randXRange = random.nextInt(2) + 2;
	int xMin = -randXRange - 1;
	int xMax = randXRange + 1;
	int randZRange = random.nextInt(2) + 2;
	int zMin = -randZRange - 1;
	int zMax = randZRange + 1;
	int validOpenings = 0;
	int ceilingOpenings = 0;
	BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable().set(position);

	for (int x = xMin; x <= xMax; ++x) {
	    for (int y = -1; y <= 4; ++y) {
		for (int z = zMin; z <= zMax; ++z) {
		    blockpos$Mutable.set(position).move(x, y, z);
		    Material material = world.getBlockState(blockpos$Mutable).getMaterial();
		    boolean flag = material.isSolid();

		    if (y == -1 && !flag) {
			return false;
		    }

		    if (y == 4 && !flag) {
			ceilingOpenings++;
		    }

		    if ((x == xMin || x == xMax || z == zMin || z == zMax) && y == 0 && world.isAir(blockpos$Mutable) && world.isAir(blockpos$Mutable.up())) {
			++validOpenings;
		    }
		}
	    }
	}

	if (validOpenings >= 1 && validOpenings <= 14 && ceilingOpenings < 14) {
	    for (int x = xMin; x <= xMax; ++x) {
		for (int y = 4; y >= -1; --y) {
		    for (int z = zMin; z <= zMax; ++z) {
			blockpos$Mutable.set(position).move(x, y, z);

			if (y == 4) {
			    if (random.nextInt(3) == 0) {
				world.setBlockState(blockpos$Mutable, SIDEWAYS_LOGS, 2);
			    }
			    else {
				world.setBlockState(blockpos$Mutable, LEAVES, 2);
			    }
			}
			else if (x != xMin && y != -1 && z != zMin && x != xMax && y != 4 && z != zMax) {
			    if (world.getBlockState(blockpos$Mutable).getBlock() != Blocks.CHEST && world.getBlockState(blockpos$Mutable).getBlock() != Blocks.SPAWNER) {
				world.setBlockState(blockpos$Mutable, CAVE_AIR, 2);
			    }
			}

			else if (blockpos$Mutable.getY() >= 0 && !world.getBlockState(blockpos$Mutable.down()).getMaterial().isSolid()) {
			    world.setBlockState(blockpos$Mutable, CAVE_AIR, 2);
			}

			// made sure the dungeon wall cannot replace other dungeon's mob spawner now.
			else if (world.getBlockState(blockpos$Mutable).getMaterial().isSolid() && world.getBlockState(blockpos$Mutable).getBlock() != Blocks.CHEST && world.getBlockState(blockpos$Mutable).getBlock() != Blocks.SPAWNER) {
			    if (y == -1 && random.nextInt(5) != 0) {
				world.setBlockState(blockpos$Mutable, PLANKS, 2);
			    }
			    else {
				if (random.nextInt(3) == 0) {
				    world.setBlockState(blockpos$Mutable, LOGS, 2);
				}
				else {
				    world.setBlockState(blockpos$Mutable, LEAVES, 2);
				}
			    }
			}
		    }
		}
	    }


	    for (int x = xMin + 1; x <= xMax - 1; ++x) {
		for (int z = zMin + 1; z <= zMax - 1; ++z) {
		    Direction facing = Direction.Type.HORIZONTAL.random(random);
		    if (random.nextInt(3) == 0) {
			for (int y = 3; y >= 0; --y) {
			    blockpos$Mutable.set(position).move(x, y, z);

			    if (y == 3) {
				world.setBlockState(blockpos$Mutable.up(), SIDEWAYS_LOGS, 2);
				world.setBlockState(blockpos$Mutable.offset(facing), LEAVES, 2);
			    }
			    world.setBlockState(blockpos$Mutable, Blocks.VINE.getDefaultState().with(VineBlock.FACING_PROPERTIES.get(facing), true).with(VineBlock.UP, y == 3), 2);

			    if (random.nextInt(3) == 0) {
				break;
			    }
			}
		    }
		}
	    }


	    for (int l3 = 0; l3 < 2; ++l3) {
		for (int j4 = 0; j4 < 3; ++j4) {
		    int x = position.getX() + random.nextInt(randXRange * 2 + 1) - randXRange;
		    int y = position.getY();
		    int z = position.getZ() + random.nextInt(randZRange * 2 + 1) - randZRange;
		    blockpos$Mutable.set(x, y, z);

		    if (world.isAir(blockpos$Mutable)) {
			int j3 = 0;

			for (Direction Direction : Direction.Type.HORIZONTAL) {
			    if (world.getBlockState(blockpos$Mutable.offset(Direction)).getMaterial().isSolid()) {
				++j3;
			    }
			}

			if (j3 == 1) {
			    world.setBlockState(blockpos$Mutable, StructurePiece.method_14916(world, blockpos$Mutable, Blocks.CHEST.getDefaultState()), 2);
			    LootableContainerBlockEntity.setLootTable(world, random, blockpos$Mutable, CHEST_LOOT);

			    break;
			}
		    }
		}
	    }

	    world.setBlockState(position, Blocks.AIR.getDefaultState(), 2);
	    world.setBlockState(position, Blocks.SPAWNER.getDefaultState(), 2);
	    BlockEntity tileentity = world.getBlockEntity(position);

	    if (tileentity instanceof MobSpawnerBlockEntity) {
		((MobSpawnerBlockEntity) tileentity).getLogic().setEntityId(pickMobSpawner(world, random));
	    }
	    else {
		LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})", new Object[] { Integer.valueOf(position.getX()), Integer.valueOf(position.getY()), Integer.valueOf(position.getZ()) });
	    }

	    return true;
	}
	else {
	    return false;
	}
    }


    /**
     * Randomly decides which spawner to use in a dungeon
     */
    private static EntityType<?> pickMobSpawner(ServerWorldAccess world, Random random) {
	int roll = random.nextInt(100);

	if (roll < 73) {
	    // 73% chance
	    return RSFeatures.pickRandomVillageDungeonMob(random);
	}
	else if (roll < 98) {
	    // 25% chance
	    return EntityType.PARROT;

	}
	else if (roll < 99) {
	    // 1% chance
	    return EntityType.CHICKEN;
	}
	else {
	    // 1% chance
	    return EntityType.CREEPER;
	}
    }
}
