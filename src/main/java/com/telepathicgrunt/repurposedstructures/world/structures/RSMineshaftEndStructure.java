package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSMineshaftPieces;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.List;


public class RSMineshaftEndStructure extends RSMineshaftStructure {
    public RSMineshaftEndStructure(Codec<DefaultFeatureConfig> config, RSMineshaftPieces.Type mineshaftType, double probability, int maxHeight, int minHeight) {
        super(config, mineshaftType, probability, maxHeight, minHeight);
    }

    private static final List<SpawnSettings.SpawnEntry> MONSTER_SPAWNS = Lists.newArrayList(
                new SpawnSettings.SpawnEntry(EntityType.ENDERMITE, 10, 2, 5),
                new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 5, 1, 3)
            );

    public List<SpawnSettings.SpawnEntry> getMonsterSpawns() {
        return MONSTER_SPAWNS;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig featureConfig) {
        chunkRandom.setCarverSeed(seed, x, z);
        double d = (probability / 10000D);
        int landHeight = chunkGenerator.getHeightInGround(x << 4 + 7, z << 4 + 7, Heightmap.Type.WORLD_SURFACE_WG);
        return chunkRandom.nextDouble() < d && (RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.barrensIslandsEndMineshafts || landHeight >= 20);
    }
}
