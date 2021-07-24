package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.misc.MobSpawningOverTime;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureStart;

import java.util.ArrayList;
import java.util.function.Supplier;

public abstract class AbstractBaseStructure<C extends FeatureConfiguration> extends StructureFeature<C> {

    public AbstractBaseStructure(Codec<C> codec) {
        super(codec);
    }

    @Override
    public BlockPos getNearestGeneratedFeature(LevelReader world, StructureFeatureManager structureAccessor, BlockPos searchStartPos, int searchRadius, boolean skipExistingChunks, long worldSeed, StructureFeatureConfiguration config) {
        return AbstractBaseStructure.locateStructureFast(world, structureAccessor, searchStartPos, searchRadius, skipExistingChunks, worldSeed, config, this);
    }

    // We do this so that if another mod needs monster or creature spawns for whatever reason, they can get the right pool.
    private final Supplier<WeightedRandomList<MobSpawnSettings.SpawnerData>> monsterSpawns = Suppliers.memoize(() -> WeightedRandomList.create(MobSpawningOverTime.REPLACE_MOB_SPAWNING.get(MobCategory.MONSTER).getOrDefault(this, new ArrayList<>())));
    private final Supplier<WeightedRandomList<MobSpawnSettings.SpawnerData>> creatureSpawns = Suppliers.memoize(() -> WeightedRandomList.create(MobSpawningOverTime.REPLACE_MOB_SPAWNING.get(MobCategory.CREATURE).getOrDefault(this, new ArrayList<>())));

    @Override
    public WeightedRandomList<MobSpawnSettings.SpawnerData> getSpecialEnemies() {
        return monsterSpawns.get();
    }

    @Override
    public WeightedRandomList<MobSpawnSettings.SpawnerData> getSpecialAnimals() {
        return creatureSpawns.get();
    }

    public static <C extends FeatureConfiguration> BlockPos locateStructureFast(LevelReader worldView, StructureFeatureManager structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureFeatureConfiguration structureConfig, StructureFeature<C> structure) {
        int spacing = structureConfig.spacing();
        int chunkPosX = blockPos.getX() >> 4;
        int chunkPosZ = blockPos.getZ() >> 4;
        int currentRadius = 0;

        for(WorldgenRandom chunkRandom = new WorldgenRandom(); currentRadius <= 100000; ++currentRadius) {
            for(int xRadius = -currentRadius; xRadius <= currentRadius; ++xRadius) {
                boolean xEdge = xRadius == -currentRadius || xRadius == currentRadius;

                for(int zRadius = -currentRadius; zRadius <= currentRadius; ++zRadius) {
                    boolean zEdge = zRadius == -currentRadius || zRadius == currentRadius;
                    if (xEdge || zEdge) {
                        int trueChunkX = chunkPosX + spacing * xRadius;
                        int trueChunkZ = chunkPosZ + spacing * zRadius;
                        ChunkPos chunkPos = structure.getPotentialFeatureChunk(structureConfig, seed, chunkRandom, trueChunkX, trueChunkZ);
                        if(worldView.getNoiseBiome((chunkPos.x << 2) + 2, 60, (chunkPos.z << 2) + 2).getGenerationSettings().isValidStart(structure)) {
                            ChunkAccess chunk = worldView.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_STARTS);
                            StructureStart<?> structureStart = structureAccessor.getStartForFeature(SectionPos.of(chunk.getPos(), 0), structure, chunk);
                            if (structureStart != null && structureStart.isValid()) {
                                if (skipExistingChunks && structureStart.canBeReferenced()) {
                                    structureStart.addReference();
                                    return structureStart.getLocatePos();
                                }

                                if (!skipExistingChunks) {
                                    return structureStart.getLocatePos();
                                }
                            }
                        }
                    }
                    else{
                        zRadius = currentRadius - 1;
                    }
                }
            }
        }

        return null;
    }
}
