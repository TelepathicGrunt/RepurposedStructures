package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSMineshaftPieces;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import java.util.List;


public class RSMineshaftEndStructure extends RSMineshaftStructure {
    public RSMineshaftEndStructure(RSMineshaftPieces.Type mineshaftType, double probability, int maxHeight, int minHeight) {
        super(mineshaftType, probability, maxHeight, minHeight);
    }

    private static final List<MobSpawnInfo.Spawners> MONSTER_SPAWNS = Lists.newArrayList(
                new MobSpawnInfo.Spawners(EntityType.ENDERMITE, 10, 2, 5),
                new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 5, 1, 3)
            );

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return MONSTER_SPAWNS;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        StructureSeparationSettings structureConfig = chunkGenerator.getStructuresConfig().getForType(this);
        if(structureConfig != null){
            chunkRandom.setLargeFeatureSeed(seed + structureConfig.getSalt(), chunkX, chunkZ);
            double d = (probability / 10000D);
            if(chunkRandom.nextDouble() < d) {
                int xPos = chunkX << 4;
                int zPos = chunkZ << 4;
                int landHeight = chunkGenerator.func_222531_c(xPos, zPos, Heightmap.Type.WORLD_SURFACE_WG);
                landHeight = Math.min(landHeight, chunkGenerator.func_222531_c(xPos + 50, zPos, Heightmap.Type.WORLD_SURFACE_WG));
                landHeight = Math.min(landHeight, chunkGenerator.func_222531_c(xPos, zPos + 50, Heightmap.Type.WORLD_SURFACE_WG));
                landHeight = Math.min(landHeight, chunkGenerator.func_222531_c(xPos - 50, zPos, Heightmap.Type.WORLD_SURFACE_WG));
                landHeight = Math.min(landHeight, chunkGenerator.func_222531_c(xPos, zPos - 50, Heightmap.Type.WORLD_SURFACE_WG));
                return RepurposedStructures.RSMineshaftsConfig.barrensIslandsEndMineshafts.get() || landHeight > Math.min(chunkGenerator.getMaxY(), 45);
            }
        }

        return false;
    }
}
