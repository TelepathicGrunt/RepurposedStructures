package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RSStructures;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.GeneralJigsawGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;
import java.util.Objects;
import java.util.Random;


public class ShipwreckNetherStructure extends StructureFeature<DefaultFeatureConfig> {
    // Special thanks to /r/l-ll-ll-l_IsDisLoss and miguelforge for allowing me to use their nether shipwreck design!

    private final Identifier START_POOL;
    private final boolean spawnAtSeaLevel;

    public ShipwreckNetherStructure(Identifier start_pool, boolean spawnAtSeaLevel) {
        super(DefaultFeatureConfig.CODEC);
        this.spawnAtSeaLevel = spawnAtSeaLevel;
        START_POOL = start_pool;
        RSStructures.RS_STRUCTURE_START_PIECES.add(start_pool);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {

        //quick shitty check to see if there some air where the sealevel boat wants to spawn
        BlockPos blockPos;
        if(spawnAtSeaLevel){
            blockPos = new BlockPos(chunkX << 4, chunkGenerator.getSeaLevel() + 1, chunkZ << 4);
        }
        else{
            ChunkRandom random = new ChunkRandom(seed + (chunkX * (chunkZ * 17)));
            int height = chunkGenerator.getSeaLevel() + random.nextInt(Math.max(chunkGenerator.getMaxY() - (chunkGenerator.getSeaLevel() + 30), 1));
            blockPos = new BlockPos(chunkX << 4, height, chunkZ << 4);
        }

        for(Direction direction : Direction.Type.HORIZONTAL) {
            BlockPos blockPos2 = blockPos.offset(direction, 6);
            BlockView blockView = chunkGenerator.getColumnSample(blockPos2.getX(), blockPos2.getZ());

            if (!blockView.getBlockState(blockPos2).isAir() ||
                    !blockView.getBlockState(blockPos2.up(8)).isAir() ||
                    !blockView.getBlockState(blockPos2.up(16)).isAir()) {
                return false;
            }
        }

        //cannot be near any other structure
        if(this != RSStructures.WARPED_OUTPOST && this != RSStructures.CRIMSON_OUTPOST && this != RSStructures.NETHER_BRICK_OUTPOST){
            for (int curChunkX = chunkX - 3; curChunkX <= chunkX + 3; curChunkX++) {
                for (int curChunkZ = chunkZ - 3; curChunkZ <= chunkZ + 3; curChunkZ++) {
                    for(StructureFeature<DefaultFeatureConfig> outpost : RSStructures.NETHER_OUTPOSTS_LIST){
                        ChunkPos chunkPos2 = outpost.getStartChunk(Objects.requireNonNull(chunkGenerator.getStructuresConfig().getForType(outpost)), seed, chunkRandom, curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                    for(StructureFeature<DefaultFeatureConfig> outpost : RSStructures.NETHER_TEMPLE_LIST){
                        ChunkPos chunkPos2 = outpost.getStartChunk(Objects.requireNonNull(chunkGenerator.getStructuresConfig().getForType(outpost)), seed, chunkRandom, curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                    for(StructureFeature<DefaultFeatureConfig> outpost : RSStructures.NETHER_VILLAGE_LIST){
                        ChunkPos chunkPos2 = outpost.getStartChunk(Objects.requireNonNull(chunkGenerator.getStructuresConfig().getForType(outpost)), seed, chunkRandom, curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                }
            }
        }
        return super.shouldStartAt(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, defaultFeatureConfig);
    }

    private static final List<SpawnSettings.SpawnEntry> MONSTER_SPAWNS =
            Lists.newArrayList(new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 25, 1, 1));

    @Override
    public List<SpawnSettings.SpawnEntry> getMonsterSpawns() {
        return MONSTER_SPAWNS;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return ShipwreckNetherStructure.Start::new;
    }

    public class Start extends AbstractNetherStructure.AbstractStart {
        private final long seed;

        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
            seed = seedIn;
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            int placementHeight = chunkGenerator.getSeaLevel();

            if(spawnAtSeaLevel){
                placementHeight = placementHeight - 4;
            }
            else{
                ChunkRandom random = new ChunkRandom(seed + (chunkX * (chunkZ * 17)));
                placementHeight = placementHeight + random.nextInt(Math.max(chunkGenerator.getMaxY() - (placementHeight + 30), 1));
            }

            BlockPos blockpos = new BlockPos(chunkX * 16, placementHeight, chunkZ * 16);
            GeneralJigsawGenerator.addPieces(dynamicRegistryManager, chunkGenerator, structureManager, blockpos, this.children, this.random, dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN).get(START_POOL), 1);
            this.setBoundingBoxFromChildren();
        }
    }
}