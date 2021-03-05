package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


public class DungeonEnd extends Feature<NoFeatureConfig> {
    public DungeonEnd(Codec<NoFeatureConfig> configFactory) {
        super(configFactory);
    }

    private static final Logger LOGGER = LogManager.getLogger();
    private static final BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();
    private static final ResourceLocation CHEST_LOOT = new ResourceLocation(RepurposedStructures.MODID, "chests/dungeon/end");
    private static final ResourceLocation SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, "dungeon_end");


    //only the mob spawner chance and what blocks the wall cannot replace was changed. Everything else is just the normal dungeon code.

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
        int randXRange = random.nextInt(2) + 2;
        int xMin = -randXRange - 1;
        int xMax = randXRange + 1;
        int randZRange = random.nextInt(2) + 2;
        int zMin = -randZRange - 1;
        int zMax = randZRange + 1;
        int validOpenings = 0;
        int ceilingOpenings = 0;
        BlockPos newPosition = new BlockPos(position.getX(), random.nextInt(world.getHeight(Heightmap.Type.WORLD_SURFACE, position.getX(), position.getZ()) + 5), position.getZ());
        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable().setPos(newPosition);

        for (int x = xMin; x <= xMax; ++x) {
            for (int y = -1; y <= 4; ++y) {
                for (int z = zMin; z <= zMax; ++z) {
                    blockpos$Mutable.setPos(newPosition).move(x, y, z);
                    Material material = world.getBlockState(blockpos$Mutable).getMaterial();
                    boolean flag = material.isSolid();

                    if (y == -1 && !flag) {
                        return false;
                    }

                    if (y == 4 && !flag) {
                        ceilingOpenings++;
                    }

                    if ((x == xMin || x == xMax || z == zMin || z == zMax) && y == 0 && world.isAirBlock(blockpos$Mutable) && world.isAirBlock(blockpos$Mutable.up())) {
                        ++validOpenings;
                    }
                }
            }
        }

        if (validOpenings >= 1 && validOpenings <= 14 && ceilingOpenings < 14) {
            for (int x = xMin; x <= xMax; ++x) {
                for (int y = 3; y >= -1; --y) {
                    for (int z = zMin; z <= zMax; ++z) {
                        blockpos$Mutable.setPos(newPosition).move(x, y, z);
                        BlockState state = world.getBlockState(blockpos$Mutable);

                        if (x != xMin && y != -1 && z != zMin && x != xMax && y != 4 && z != zMax) {
                            if (isNotSpecialBlock(state)) {
                                world.setBlockState(blockpos$Mutable, CAVE_AIR, 2);
                            }
                        } else if (blockpos$Mutable.getY() >= 0 && !world.getBlockState(blockpos$Mutable.down()).getMaterial().isSolid()) {
                            world.setBlockState(blockpos$Mutable, CAVE_AIR, 2);
                        }

                        //made sure the dungeon wall cannot replace other dungeon's mob spawner now.
                        else if (world.getBlockState(blockpos$Mutable).getMaterial().isSolid() && isNotSpecialBlock(state)) {
                            world.setBlockState(blockpos$Mutable, Blocks.END_STONE_BRICKS.getDefaultState(), 2);
                        }
                    }
                }
            }

            for (int boxInstance = 0; boxInstance < 2; ++boxInstance) {
                for (int boxAttempts = 0; boxAttempts < random.nextInt(4); ++boxAttempts) {
                    int x = newPosition.getX() + random.nextInt(randXRange * 2 + 1) - randXRange;
                    int y = newPosition.getY();
                    int z = newPosition.getZ() + random.nextInt(randZRange * 2 + 1) - randZRange;
                    blockpos$Mutable.setPos(x, y, z);

                    if (world.isAirBlock(blockpos$Mutable)) {
                        int wallCount = 0;

                        for (Direction Direction : Direction.Plane.HORIZONTAL) {
                            if (world.getBlockState(blockpos$Mutable.offset(Direction)).getMaterial().isSolid()) {
                                ++wallCount;
                            }
                        }

                        if (wallCount != 0 && wallCount != 4) {
                            world.setBlockState(blockpos$Mutable, Blocks.SHULKER_BOX.getDefaultState(), 2);
                            LockableLootTileEntity.setLootTable(world, random, blockpos$Mutable, CHEST_LOOT);
                            setSpawner(world, random, blockpos$Mutable.down());
                            break;
                        }
                    }
                }
            }

            world.setBlockState(newPosition, Blocks.END_PORTAL_FRAME.getDefaultState().with(BlockStateProperties.EYE, true), 2);
            setSpawner(world, random, newPosition.down());

            return true;
        } else {
            return false;
        }
    }

    private void setSpawner(ISeedReader world, Random random, BlockPos newPosition) {
        world.setBlockState(newPosition, Blocks.AIR.getDefaultState(), 2);
        world.setBlockState(newPosition, Blocks.SPAWNER.getDefaultState(), 2);
        TileEntity tileentity = world.getTileEntity(newPosition);

        if (tileentity instanceof MobSpawnerTileEntity) {
            ((MobSpawnerTileEntity) tileentity).getSpawnerBaseLogic()
                    .setEntityType(RepurposedStructures.mobSpawnerManager.getSpawnerMob(SPAWNER_ID, random));
        } else {
            LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})", new Object[]{newPosition.getX(), newPosition.getY(), newPosition.getZ()});
        }
    }

    private boolean isNotSpecialBlock(BlockState state){
        return state.getBlock() != Blocks.CHEST && !(state.getBlock() instanceof ShulkerBoxBlock) && state.getBlock() != Blocks.SPAWNER;
    }
}
