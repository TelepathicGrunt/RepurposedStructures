package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.common.util.Lazy;

import java.util.List;
import java.util.Map;


public class RSMineshaftEndStructure extends RSMineshaftStructure {
    public RSMineshaftEndStructure(ResourceLocation poolID, Lazy<Integer> structureSize, Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces, Lazy<Integer> maxY, Lazy<Integer> minY, Lazy<Float> probability, ENVIRONMENT_CHECK environmentCheck) {
        super(poolID, structureSize, requiredPieces, maxY, minY, probability, environmentCheck);
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
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int x, int z, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        StructureSeparationSettings structureConfig = chunkGenerator.getSettings().getConfig(this);
        if(structureConfig != null) {
            chunkRandom.setFeatureSeed(seed + structureConfig.salt(), x, z);
            double d = (probability.get() / 10000D);
            if (chunkRandom.nextDouble() < d) {
                if(RepurposedStructures.RSMineshaftsConfig.barrensIslandsEndMineshafts.get())
                    return true;

                int minLandHeight = Math.min(chunkGenerator.getGenDepth(), 45);
                int xPos = x << 4;
                int zPos = z << 4;
                int landHeight = chunkGenerator.getFirstOccupiedHeight(xPos, zPos, Heightmap.Type.WORLD_SURFACE_WG);

                landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos + 70, zPos, Heightmap.Type.WORLD_SURFACE_WG));
                if(landHeight < minLandHeight) return false;

                landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos, zPos + 70, Heightmap.Type.WORLD_SURFACE_WG));
                if(landHeight < minLandHeight) return false;

                landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos - 70, zPos, Heightmap.Type.WORLD_SURFACE_WG));
                if(landHeight < minLandHeight) return false;

                landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos, zPos - 70, Heightmap.Type.WORLD_SURFACE_WG));
                return landHeight >= minLandHeight;
            }
        }
        return false;
    }
}
