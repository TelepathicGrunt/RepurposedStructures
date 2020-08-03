package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.List;


public class RSMineshaftEndStructure extends AbstractMineshaftStructure {
    public RSMineshaftEndStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    public double getProbability() {
        return RepurposedStructures.RSMineshaftsConfig.endMineshaftSpawnrate.get();
    }

    private static final List<Biome.SpawnListEntry> MONSTER_SPAWNS = Lists.newArrayList(
                new Biome.SpawnListEntry(EntityType.ENDERMITE, 10, 2, 5),
                new Biome.SpawnListEntry(EntityType.ENDERMAN, 5, 1, 3)
            );

    public List<Biome.SpawnListEntry> getSpawnList() {
        return MONSTER_SPAWNS;
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return RSMineshaftEndStructure.Start::new;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        chunkRandom.setLargeFeatureSeed(seed, x, z);
        double d = (getProbability() / 10000D);
        int landHeight = chunkGenerator.func_222531_c(x, z, Heightmap.Type.WORLD_SURFACE_WG);
        return chunkRandom.nextDouble() < d && (RepurposedStructures.RSMineshaftsConfig.barrensIslandsEndMineshafts.get() || landHeight > 20);
    }

    public static class Start extends AbstractStart {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public RSMineshaftPieces.Type getMineshaftType() {
            return RSMineshaftPieces.Type.END;
        }
        @Override
        public int getMaxHeight() {
            return RepurposedStructures.RSMineshaftsConfig.endMineshaftMaxHeight.get();
        }
        @Override
        public int getMinHeight() {
            return RepurposedStructures.RSMineshaftsConfig.endMineshaftMinHeight.get();
        }
    }

}
