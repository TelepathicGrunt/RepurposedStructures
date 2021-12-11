package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.misc.MobSpawningOverTime;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractBaseStructure<C extends IFeatureConfig> extends Structure<C> {

    public AbstractBaseStructure(Codec<C> codec) {
        super(codec);
    }

    // Forge will spawn monster/creature structure mobs for us that replaces biome spawns
    private final Supplier<List<MobSpawnInfo.Spawners>> monsterSpawns = Suppliers.memoize(() -> new ArrayList<>(MobSpawningOverTime.REPLACE_MOB_SPAWNING.get(EntityClassification.MONSTER).getOrDefault(this, new ArrayList<>())));
    private final Supplier<List<MobSpawnInfo.Spawners>> creatureSpawns = Suppliers.memoize(() -> new ArrayList<>(MobSpawningOverTime.REPLACE_MOB_SPAWNING.get(EntityClassification.CREATURE).getOrDefault(this, new ArrayList<>())));

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return monsterSpawns.get();
    }

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList() {
        return creatureSpawns.get();
    }

    @Override
    public BlockPos getNearestGeneratedFeature(IWorldReader world, StructureManager structureAccessor, BlockPos searchStartPos, int searchRadius, boolean skipExistingChunks, long worldSeed, StructureSeparationSettings config) {
        return AbstractBaseStructure.locateStructureFast(world, structureAccessor, searchStartPos, searchRadius, skipExistingChunks, worldSeed, config, this);
    }

    public static <C extends IFeatureConfig> BlockPos locateStructureFast(IWorldReader worldView, StructureManager structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureSeparationSettings structureConfig, Structure<C> structure) {
        int spacing = structureConfig.spacing();
        int chunkX = blockPos.getX() >> 4;
        int chunkZ = blockPos.getZ() >> 4;
        int currentRadius = 0;
        int maxRadius = radius != 100 ? radius : 50000/structureConfig.spacing();

        for(SharedSeedRandom chunkRandom = new SharedSeedRandom(); currentRadius <= maxRadius; ++currentRadius) {
            for(int xRadius = -currentRadius; xRadius <= currentRadius; ++xRadius) {
                boolean xEdge = xRadius == -currentRadius || xRadius == currentRadius;

                for(int zRadius = -currentRadius; zRadius <= currentRadius; ++zRadius) {
                    boolean zEdge = zRadius == -currentRadius || zRadius == currentRadius;
                    if (xEdge || zEdge) {
                        int trueChunkX = chunkX + spacing * xRadius;
                        int trueChunkZ = chunkZ + spacing * zRadius;
                        ChunkPos chunkPos = structure.getPotentialFeatureChunk(structureConfig, seed, chunkRandom, trueChunkX, trueChunkZ);
                        if(worldView.getNoiseBiome((chunkPos.x << 2) + 2, 60, (chunkPos.z << 2) + 2).getGenerationSettings().isValidStart(structure)) {
                            IChunk chunk = worldView.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_STARTS);
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
