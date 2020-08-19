package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
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

import java.util.List;


public class RSMineshaftEndStructure extends RSMineshaftStructure {
    public RSMineshaftEndStructure(Codec<NoFeatureConfig> config, RSMineshaftPieces.Type mineshaftType, double probability, int maxHeight, int minHeight) {
        super(config, mineshaftType, probability, maxHeight, minHeight);
    }

    private static final List<MobSpawnInfo.Spawners> MONSTER_SPAWNS = Lists.newArrayList(
                new MobSpawnInfo.Spawners(EntityType.ENDERMITE, 10, 2, 5),
                new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 5, 1, 3)
            );

    public List<MobSpawnInfo.Spawners> getSpawnList() {
        return MONSTER_SPAWNS;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        chunkRandom.setLargeFeatureSeed(seed, x, z);
        double d = (probability / 10000D);
        int landHeight = chunkGenerator.func_222531_c(x, z, Heightmap.Type.WORLD_SURFACE_WG);
        return chunkRandom.nextDouble() < d && (RepurposedStructures.RSMineshaftsConfig.barrensIslandsEndMineshafts.get() || landHeight > 20);
    }
}
