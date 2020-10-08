package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RSStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.GeneralJigsawGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import java.util.List;
import java.util.Objects;


public class ShipwreckNetherStructure extends Structure<NoFeatureConfig> {
    // Special thanks to cannon_foddr and miguelforge for allowing me to use their nether shipwreck design!

    private final ResourceLocation START_POOL;
    private final boolean spawnAtSeaLevel;
    private static List<Structure<?>> AVOID_STRUCTURE_LIST = null;


    public ShipwreckNetherStructure(ResourceLocation start_pool, boolean spawnAtSeaLevel) {
        super(NoFeatureConfig.CODEC);
        this.spawnAtSeaLevel = spawnAtSeaLevel;
        START_POOL = start_pool;
        RSStructures.RS_STRUCTURE_START_PIECES.add(start_pool);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig NoFeatureConfig) {
        if(AVOID_STRUCTURE_LIST == null){
            AVOID_STRUCTURE_LIST = Lists.newArrayList(Iterators.concat(
                    RSStructures.NETHER_OUTPOSTS_LIST.iterator(),
                    RSStructures.NETHER_TEMPLE_LIST.iterator(),
                    RSStructures.NETHER_VILLAGE_LIST.iterator(),
                    RSStructures.LARGE_VANILLA_NETHER_STRUCTURE_LIST.iterator()));
        }


        // Quick shitty check to see if there some air where the structure wants to spawn.
        // Doesn't account for rotation of structure.
        BlockPos blockPos;
        if(spawnAtSeaLevel){
            blockPos = new BlockPos(chunkX << 4, chunkGenerator.getSeaLevel() + 1, chunkZ << 4);
        }
        else{
            SharedSeedRandom random = new SharedSeedRandom(seed + (chunkX * (chunkZ * 17)));
            int height = chunkGenerator.getSeaLevel() + random.nextInt(Math.max(chunkGenerator.getMaxY() - (chunkGenerator.getSeaLevel() + 30), 1));
            blockPos = new BlockPos(chunkX << 4, height, chunkZ << 4);
        }

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos blockPos2 = blockPos.offset(direction, 8);
            IBlockReader blockView = chunkGenerator.getColumnSample(blockPos2.getX(), blockPos2.getZ());

            if (!blockView.getBlockState(blockPos2).isAir() ||
                    !blockView.getBlockState(blockPos2.up(9)).isAir() ||
                    !blockView.getBlockState(blockPos2.up(18)).isAir()) {
                return false;
            }
        }

        //cannot be near any other structure
        for (int curChunkX = chunkX - 3; curChunkX <= chunkX + 3; curChunkX++) {
            for (int curChunkZ = chunkZ - 3; curChunkZ <= chunkZ + 3; curChunkZ++) {
                for(Structure<?> structure : AVOID_STRUCTURE_LIST){
                    StructureSeparationSettings structureConfig = chunkGenerator.getStructuresConfig().getForType(structure);
                    if(structureConfig != null) {
                        ChunkPos chunkPos2 = structure.getStartChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                }
            }
        }

        return super.shouldStartAt(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, NoFeatureConfig);
    }

    private static final List<MobSpawnInfo.Spawners> MONSTER_SPAWNS =
            Lists.newArrayList(new MobSpawnInfo.Spawners(EntityType.WITHER_SKELETON, 25, 1, 1));

    @Override
    public List<MobSpawnInfo.Spawners> getSpawnList() {
        return MONSTER_SPAWNS;
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    public class Start extends AbstractNetherStructure.AbstractStart {
        private final long seed;

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
            seed = seedIn;
        }

        @Override
        public void init(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {
            int placementHeight = chunkGenerator.getSeaLevel();

            if(spawnAtSeaLevel){
                placementHeight = placementHeight - 4;
            }
            else{
                SharedSeedRandom random = new SharedSeedRandom(seed + (chunkX * (chunkZ * 17)));
                placementHeight = placementHeight + random.nextInt(Math.max(chunkGenerator.getMaxY() - (placementHeight + 30), 1));
            }

            BlockPos blockpos = new BlockPos(chunkX * 16, placementHeight, chunkZ * 16);
            GeneralJigsawGenerator.addPieces(dynamicRegistryManager, chunkGenerator, structureManager, blockpos, this.components, this.rand, dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN).getOrDefault(START_POOL), 1);
            this.recalculateStructureSize();
        }
    }
}