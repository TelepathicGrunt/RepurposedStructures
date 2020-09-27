package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


public class DungeonSnow extends Feature<DefaultFeatureConfig> {
    public DungeonSnow() {
        super(DefaultFeatureConfig.CODEC);
    }

    private static final Logger LOGGER = LogManager.getLogger();
    private static final BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();
    private static final Identifier CHEST_LOOT = new Identifier(RepurposedStructures.MODID, "chests/dungeon/snow");
    private static final Identifier SPAWNER_ID = new Identifier(RepurposedStructures.MODID, "dungeon_snow");


    //only the mob spawner chance and what blocks the wall cannot replace was changed. Everything else is just the normal dungeon code.

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config) {
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
                for (int y = 3; y >= -1; --y) {
                    for (int z = zMin; z <= zMax; ++z) {
                        blockpos$Mutable.set(position).move(x, y, z);

                        if (x != xMin && y != -1 && z != zMin && x != xMax && y != 4 && z != zMax) {
                            if (world.getBlockState(blockpos$Mutable).getBlock() != Blocks.CHEST && world.getBlockState(blockpos$Mutable).getBlock() != Blocks.SPAWNER) {
                                //icicles
                                if ((y >= 2 && z % 3 == 0 && x % 2 == 0) || (y == 3 && z % 2 == 0 && x % 2 == 0)) {
                                    world.setBlockState(blockpos$Mutable, Blocks.ICE.getDefaultState(), 2);
                                } else {
                                    world.setBlockState(blockpos$Mutable, CAVE_AIR, 2);
                                }
                            }
                        } else if (blockpos$Mutable.getY() >= 0 && !world.getBlockState(blockpos$Mutable.down()).getMaterial().isSolid()) {
                            world.setBlockState(blockpos$Mutable, CAVE_AIR, 2);
                        }

                        //made sure the dungeon wall cannot replace other dungeon's mob spawner now.
                        else if (world.getBlockState(blockpos$Mutable).getMaterial().isSolid() && world.getBlockState(blockpos$Mutable).getBlock() != Blocks.CHEST && world.getBlockState(blockpos$Mutable).getBlock() != Blocks.SPAWNER) {
                            if (y == -1 && random.nextInt(6) == 0) {
                                world.setBlockState(blockpos$Mutable, Blocks.BLUE_ICE.getDefaultState(), 2);
                            } else if (random.nextInt(4) == 0) {
                                world.setBlockState(blockpos$Mutable, Blocks.SNOW_BLOCK.getDefaultState(), 2);
                            } else {
                                world.setBlockState(blockpos$Mutable, Blocks.ICE.getDefaultState(), 2);
                            }

                        }
                    }
                }
            }

            for (int l3 = random.nextInt(3); l3 < 2; ++l3) {
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
                ((MobSpawnerBlockEntity) tileentity).getLogic()
                        .setEntityId(RepurposedStructures.mobSpawnerManager.getSpawnerMob(SPAWNER_ID, random));
            } else {
                LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})", new Object[]{position.getX(), position.getY(), position.getZ()});
            }

            return true;
        } else {
            return false;
        }
    }
}
