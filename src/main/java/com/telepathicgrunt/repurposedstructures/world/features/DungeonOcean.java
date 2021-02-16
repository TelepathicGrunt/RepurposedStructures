package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


public class DungeonOcean extends Feature<NoFeatureConfig> {
    public DungeonOcean(Codec<NoFeatureConfig> configFactory) {
        super(configFactory);
    }

    private static final Logger LOGGER = LogManager.getLogger();
    private static final BlockState WATER = Blocks.WATER.getDefaultState();
    private static final BlockState PRISMARINE = Blocks.PRISMARINE.getDefaultState();
    private static final BlockState PRISMARINE_BRICKS = Blocks.PRISMARINE_BRICKS.getDefaultState();
    private static final BlockState PRISMARINE_WALL = Blocks.PRISMARINE_WALL.getDefaultState();
    private static final BlockState DARK_PRISMARINE_STAIRS = Blocks.DARK_PRISMARINE_STAIRS.getDefaultState();
    private static final BlockState DARK_PRISMARINE = Blocks.DARK_PRISMARINE.getDefaultState();
    private static final BlockState MAGMA_BLOCK = Blocks.MAGMA_BLOCK.getDefaultState();
    private static final ResourceLocation CHEST_LOOT = new ResourceLocation(RepurposedStructures.MODID, ":chests/dungeon/ocean");
    private static final ResourceLocation FROZEN_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, ":dungeon_ocean_frozen");
    private static final ResourceLocation COLD_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, ":dungeon_ocean_cold");
    private static final ResourceLocation NEUTRAL_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, ":dungeon_ocean_neutral");
    private static final ResourceLocation LUKEWARM_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, ":dungeon_ocean_lukewarm");
    private static final ResourceLocation WARM_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, ":dungeon_ocean_warm");
    private static final ResourceLocation MISC_SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID, ":dungeon_ocean_misc");


    // only the mob spawner chance and what blocks the wall cannot replace was changed. Everything else is just the normal dungeon code.

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
        boolean validSpot;
        boolean oceanFloor = false;
        BlockPos.Mutable mutable = new BlockPos.Mutable().setPos(position);
        int terrainHeight = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, mutable.getX(), mutable.getZ());

        // ocean floor
        if (terrainHeight - mutable.getY() > 1 && terrainHeight - mutable.getY() < 5) {
            oceanFloor = true;
        }

        // regular dungeon spacing check
        for (int x = xMin; x <= xMax; ++x) {
            for (int y = -1; y <= 4; ++y) {
                for (int z = zMin; z <= zMax; ++z) {

                    mutable.setPos(position).move(x, y, z);
                    Material material = world.getBlockState(mutable).getMaterial();
                    boolean flag = material.isSolid() || material == Material.WATER;

                    if (y == -1 && !flag) {
                        return false;
                    }

                    if (y == 4 && !flag) {
                        ceilingOpenings++;
                    }

                    if ((x == xMin || x == xMax || z == zMin || z == zMax) &&
                            y == 0 &&
                            (world.getBlockState(mutable).getMaterial() == Material.WATER &&
                                    world.getBlockState(mutable.up()).getMaterial() == Material.WATER)) {
                        ++validOpenings;
                    }
                }
            }
        }

        validSpot = oceanFloor ? random.nextFloat() < 0.1f : validOpenings >= 2 && validOpenings <= 6 && ceilingOpenings < 2;


        if (validSpot) {
            BlockState currentBlock;

            for (int x = xMin; x <= xMax; ++x) {
                for (int y = 4; y >= -1; --y) {
                    for (int z = zMin; z <= zMax; ++z) {

                        mutable.setPos(position).move(x, y, z);
                        currentBlock = world.getBlockState(mutable);

                        if (x != xMin && y != -1 && z != zMin && x != xMax && y != 5 && z != zMax) {
                            if (y == 4) {
                                if (currentBlock.isSolid()) {
                                    // ceiling
                                    if (random.nextInt(3) < 2) {
                                        world.setBlockState(mutable, PRISMARINE, 2);
                                    } else {
                                        world.setBlockState(mutable, PRISMARINE_BRICKS, 2);
                                    }
                                }
                            } else {
                                carveSpace(world, chunkGenerator, mutable, currentBlock);

                                mutable.move(Direction.UP);
                                currentBlock = world.getBlockState(mutable);

                                while ((currentBlock.getMaterial() == Material.OCEAN_PLANT || currentBlock.getMaterial() == Material.SEA_GRASS)
                                        && mutable.getY() < 255)
                                {
                                    if (mutable.getY() < chunkGenerator.getSeaLevel()) {
                                        world.setBlockState(mutable, WATER, 2);
                                    } else {
                                        world.setBlockState(mutable, Blocks.AIR.getDefaultState(), 2);
                                    }

                                    mutable.move(Direction.UP);
                                    currentBlock = world.getBlockState(mutable);
                                }
                            }
                        } else if (mutable.getY() >= 0 &&
                                !world.getBlockState(mutable.down()).getMaterial().isSolid()) {
                            carveSpace(world, chunkGenerator, mutable, currentBlock);
                        }

                        // made sure the dungeon wall cannot replace other dungeon's mob spawner now.
                        else if (currentBlock.getMaterial().isSolid() &&
                                currentBlock.getBlock() != Blocks.CHEST &&
                                currentBlock.getBlock() != Blocks.SPAWNER) {
                            // floor
                            if (y == -1) {
                                if (random.nextInt(10) == 0) {
                                    world.setBlockState(mutable, MAGMA_BLOCK, 2);
                                } else if (random.nextInt(2) == 0) {
                                    world.setBlockState(mutable, PRISMARINE, 2);
                                } else {
                                    world.setBlockState(mutable, PRISMARINE_BRICKS, 2);
                                }
                            }

                            // wall
                            else {
                                if (random.nextInt(3) <= 1) {
                                    world.setBlockState(mutable, PRISMARINE, 2);
                                } else {
                                    world.setBlockState(mutable, PRISMARINE_WALL.with(BlockStateProperties.WATERLOGGED, mutable.getY() < chunkGenerator.getSeaLevel()), 3);
                                }
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
                    mutable.setPos(x, y, z);
                    currentBlock = world.getBlockState(mutable);

                    if (currentBlock.getMaterial() == Material.WATER || currentBlock.getMaterial() == Material.AIR) {
                        int j3 = 0;

                        for (Direction direction : Direction.Plane.HORIZONTAL) {
                            Material neighboringMaterial = world.getBlockState(mutable.offset(direction)).getMaterial();
                            if (neighboringMaterial.isSolid()) {
                                ++j3;
                            }
                        }

                        if (j3 == 1) {
                            world.setBlockState(mutable,
                                    StructurePiece.func_197528_a(world, mutable,
                                            Blocks.CHEST.getDefaultState()
                                                    .with(BlockStateProperties.WATERLOGGED,
                                                            mutable.getY() < chunkGenerator.getSeaLevel())), 3);

                            LockableLootTileEntity.setLootTable(world, random, mutable, CHEST_LOOT);

                            // Place block below chest if floating. Check if block below is attached
                            // to a solid block. If not, make a column to solid block below.
                            if(!world.getBlockState(mutable.down()).isSolid()){

                                BlockPos.Mutable mutableColumn = new BlockPos.Mutable().setPos(mutable.down());

                                boolean solidSide = false;
                                for(Direction direction : Direction.Plane.HORIZONTAL){
                                    if(world.getBlockState(mutableColumn.offset(direction)).isSolid()){
                                        solidSide = true;
                                    }
                                }
                                if(solidSide){
                                    // Set block only below chest
                                    world.setBlockState(mutableColumn, Blocks.PRISMARINE.getDefaultState(), 3);
                                    break;
                                }

                                // Set a column of blocks instead.
                                while(mutableColumn.getY() > 0 && !world.getBlockState(mutableColumn).isSolid()){
                                    world.setBlockState(mutableColumn, Blocks.PRISMARINE.getDefaultState(), 3);
                                    mutableColumn.move(Direction.DOWN);
                                }
                            }

                            break;
                        }
                    }
                }
            }

            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    mutable.setPos(position).move(x, -1, z);
                    currentBlock = world.getBlockState(mutable);

                    if (currentBlock.getBlock() != Blocks.CHEST && currentBlock.getBlock() != Blocks.SPAWNER) {
                        world.setBlockState(mutable, DARK_PRISMARINE, 2);
                    }

                    mutable.move(Direction.DOWN);
                    currentBlock = world.getBlockState(mutable);
                    if (currentBlock.getBlock() != Blocks.CHEST && currentBlock.getBlock() != Blocks.SPAWNER) {
                        world.setBlockState(mutable, DARK_PRISMARINE, 2);
                    }
                }
            }

            mutable.setPos(position).move(Direction.DOWN);

            if (mutable.getY() < chunkGenerator.getSeaLevel()) world.setBlockState(mutable, WATER, 2);

            world.setBlockState(mutable, Blocks.AIR.getDefaultState(), 2);
            world.setBlockState(mutable, Blocks.SPAWNER.getDefaultState(), 2);
            TileEntity tileentity = world.getTileEntity(mutable);

            if (tileentity instanceof MobSpawnerTileEntity) {
                ((MobSpawnerTileEntity) tileentity).getSpawnerBaseLogic().setEntityType(pickMobSpawner(world, random, mutable));
            } else {
                LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})", new Object[]{mutable.getX(), mutable.getY(), mutable.getZ()});
            }


            currentBlock = world.getBlockState(mutable.move(Direction.UP));
            if (currentBlock.getBlock() != Blocks.CHEST && currentBlock.getBlock() != Blocks.SPAWNER) {
                world.setBlockState(mutable, PRISMARINE_WALL
                        .with(BlockStateProperties.WATERLOGGED, mutable.getY() < chunkGenerator.getSeaLevel()), 2);
            }

            currentBlock = world.getBlockState(mutable.move(Direction.UP));
            if (currentBlock.getBlock() != Blocks.CHEST && currentBlock.getBlock() != Blocks.SPAWNER) {
                world.setBlockState(mutable, PRISMARINE_WALL
                        .with(BlockStateProperties.WATERLOGGED, mutable.getY() < chunkGenerator.getSeaLevel()), 2);
            }

            for (Direction direction : Direction.Plane.HORIZONTAL) {
                mutable.setPos(position).move(direction);
                currentBlock = world.getBlockState(mutable);
                if (currentBlock.getBlock() != Blocks.CHEST && currentBlock.getBlock() != Blocks.SPAWNER) {
                    world.setBlockState(mutable, DARK_PRISMARINE_STAIRS
                            .with(BlockStateProperties.WATERLOGGED, mutable.getY() < chunkGenerator.getSeaLevel())
                            .with(StairsBlock.FACING, direction.getOpposite()), 2);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private void carveSpace(ISeedReader world, ChunkGenerator chunkGenerator, BlockPos.Mutable mutable, BlockState currentBlock) {
        if (currentBlock.getBlock() != Blocks.CHEST &&
                currentBlock.getBlock() != Blocks.SPAWNER &&
                currentBlock.getBlock() != Blocks.DARK_PRISMARINE &&
                currentBlock.getBlock() != Blocks.DARK_PRISMARINE_STAIRS) {
            if (mutable.getY() < chunkGenerator.getSeaLevel()) {
                world.setBlockState(mutable, WATER, 2);
            } else {
                world.setBlockState(mutable, Blocks.AIR.getDefaultState(), 2);
            }
        }
    }


    /**
     * Randomly decides which spawner to use in a dungeon
     */
    private static EntityType<?> pickMobSpawner(ISeedReader world, Random random, BlockPos position) {
        Biome biome = world.getBiome(position);
        ResourceLocation biomeRL = world.getRegistryManager().get(Registry.BIOME_KEY).getKey(biome);
        if(biomeRL == null){
            biomeRL = ForgeRegistries.BIOMES.getKey(biome);
            if(biomeRL == null){
                biomeRL = biome.getRegistryName();
                if(biomeRL == null) {
                    LOGGER.error("What in the world? The Ocean Dungeon attempted to spawn in a biome not " +
                            "registered in DynamicRegistries, WorldGenRegistries, or the Forge registry!" +
                            "\nPlease tell TelepathicGrunt (RepurposedStructures dev) this and tell him your mod list you have on." +
                            "\nFor now, Ocean Dungeons will use repurposed_structrues:dungeon_ocean_misc mobs as fallback.");

                    return RepurposedStructures.mobSpawnerManager.getSpawnerMob(MISC_SPAWNER_ID, random);
                }
            }
        }

        // spot must be an ocean so we don't return wrong mob when a hot land biome borders a frozen ocean
        if (biome.getCategory() == Biome.Category.OCEAN) {
            String biomeName = biomeRL.getPath();
            float biomeTemp = biome.getTemperature();

            if (biomeTemp < 0.0 || biomeName.contains("frozen") || biomeName.contains("snow") || biomeName.contains("ice")) {
                return RepurposedStructures.mobSpawnerManager.getSpawnerMob(FROZEN_SPAWNER_ID, random);
            }
            // deliberately skip 0.5 temp as all vanilla oceans are 0.5 and we are checking for vanilla oceans temp by name
            else if (biomeTemp < 0.5 || biomeName.contains("cold")) {
                return RepurposedStructures.mobSpawnerManager.getSpawnerMob(COLD_SPAWNER_ID, random);
            }
            else if (biomeTemp > 0.5 || biomeName.equals("ocean") || biomeName.equals("deep_ocean")) {
                return RepurposedStructures.mobSpawnerManager.getSpawnerMob(NEUTRAL_SPAWNER_ID, random);
            }
            else if (biomeTemp >= 0.9 || biomeName.contains("lukewarm")) {
                return RepurposedStructures.mobSpawnerManager.getSpawnerMob(LUKEWARM_SPAWNER_ID, random);
            }
            else if (biomeTemp >= 1.5 || biomeName.contains("warm") || biomeName.contains("hot") || biomeName.contains("tropic")) {
                return RepurposedStructures.mobSpawnerManager.getSpawnerMob(WARM_SPAWNER_ID, random);
            }
        }

        return RepurposedStructures.mobSpawnerManager.getSpawnerMob(MISC_SPAWNER_ID, random);
    }
}
