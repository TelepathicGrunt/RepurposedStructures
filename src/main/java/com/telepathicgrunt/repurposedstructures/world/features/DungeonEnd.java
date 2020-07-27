package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


public class DungeonEnd extends Feature<NoFeatureConfig> {
    public DungeonEnd(Codec<NoFeatureConfig> configFactory) {
        super(configFactory);
    }

    private static final Logger LOGGER = LogManager.getLogger();
    private static final BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();
    private static final ResourceLocation CHEST_LOOT = new ResourceLocation(RepurposedStructures.MODID + ":chests/dungeon_end");
    private static final ResourceLocation SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID + ":dungeon_end");


    //only the mob spawner chance and what blocks the wall cannot replace was changed. Everything else is just the normal dungeon code.

    @Override
    public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
        int randXRange = random.nextInt(2) + 2;
        int xMin = -randXRange - 1;
        int xMax = randXRange + 1;
        int randZRange = random.nextInt(2) + 2;
        int zMin = -randZRange - 1;
        int zMax = randZRange + 1;
        int validOpeneings = 0;
        int ceilingOpenings = 0;
        BlockPos newPosition = new BlockPos(position.getX(), random.nextInt(world.getTopY(Heightmap.Type.WORLD_SURFACE, position.getX(), position.getZ()) + 5), position.getZ());
        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable().set(newPosition);

        for (int x = xMin; x <= xMax; ++x) {
            for (int y = -1; y <= 4; ++y) {
                for (int z = zMin; z <= zMax; ++z) {
                    blockpos$Mutable.set(newPosition).move(x, y, z);
                    Material material = world.getBlockState(blockpos$Mutable).getMaterial();
                    boolean flag = material.isSolid();

                    if (y == -1 && !flag) {
                        return false;
                    }

                    if (y == 4 && !flag) {
                        ceilingOpenings++;
                    }

                    if ((x == xMin || x == xMax || z == zMin || z == zMax) && y == 0 && world.isAir(blockpos$Mutable) && world.isAir(blockpos$Mutable.up())) {
                        ++validOpeneings;
                    }
                }
            }
        }

        if (validOpeneings >= 1 && validOpeneings <= 14 && ceilingOpenings < 14) {
            for (int x = xMin; x <= xMax; ++x) {
                for (int y = 3; y >= -1; --y) {
                    for (int z = zMin; z <= zMax; ++z) {
                        blockpos$Mutable.set(newPosition).move(x, y, z);

                        if (x != xMin && y != -1 && z != zMin && x != xMax && y != 4 && z != zMax) {
                            if (world.getBlockState(blockpos$Mutable).getBlock() != Blocks.CHEST && world.getBlockState(blockpos$Mutable).getBlock() != Blocks.SPAWNER) {
                                world.setBlockState(blockpos$Mutable, CAVE_AIR, 2);
                            }
                        } else if (blockpos$Mutable.getY() >= 0 && !world.getBlockState(blockpos$Mutable.down()).getMaterial().isSolid()) {
                            world.setBlockState(blockpos$Mutable, CAVE_AIR, 2);
                        }

                        //made sure the dungeon wall cannot replace other dungeon's mob spawner now.
                        else if (world.getBlockState(blockpos$Mutable).getMaterial().isSolid() && world.getBlockState(blockpos$Mutable).getBlock() != Blocks.CHEST && world.getBlockState(blockpos$Mutable).getBlock() != Blocks.SPAWNER) {
                            world.setBlockState(blockpos$Mutable, Blocks.END_STONE_BRICKS.getDefaultState(), 2);
                        }
                    }
                }
            }

            for (int j4 = 0; j4 < random.nextInt(4); ++j4) {
                int x = newPosition.getX() + random.nextInt(randXRange * 2 + 1) - randXRange;
                int y = newPosition.getY();
                int z = newPosition.getZ() + random.nextInt(randZRange * 2 + 1) - randZRange;
                blockpos$Mutable.set(x, y, z);

                if (world.isAir(blockpos$Mutable)) {
                    int j3 = 0;

                    for (Direction Direction : Direction.Type.HORIZONTAL) {
                        if (world.getBlockState(blockpos$Mutable.offset(Direction)).getMaterial().isSolid()) {
                            ++j3;
                        }
                    }

                    if (j3 == 1) {
                        world.setBlockState(blockpos$Mutable, Blocks.SHULKER_BOX.getDefaultState(), 2);
                        LootableContainerBlockEntity.setLootTable(world, random, blockpos$Mutable, CHEST_LOOT);

                        break;
                    }
                }
            }

            world.setBlockState(newPosition, Blocks.END_PORTAL_FRAME.getDefaultState().with(Properties.EYE, true), 2);
            world.setBlockState(position.down(), Blocks.AIR.getDefaultState(), 2);
            world.setBlockState(newPosition.down(), Blocks.SPAWNER.getDefaultState(), 2);
            BlockEntity tileentity = world.getBlockEntity(newPosition.down());

            if (tileentity instanceof MobSpawnerBlockEntity) {
                ((MobSpawnerBlockEntity) tileentity).getLogic()
                        .setEntityId(RepurposedStructures.mobSpawnerManager.getSpawnerMob(SPAWNER_ID, random));
            } else {
                LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})", new Object[]{Integer.valueOf(newPosition.getX()), Integer.valueOf(newPosition.down().getY()), Integer.valueOf(newPosition.getZ())});
            }

            return true;
        } else {
            return false;
        }
    }
}
