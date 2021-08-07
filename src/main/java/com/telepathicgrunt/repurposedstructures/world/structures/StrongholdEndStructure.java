package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Map;


public class StrongholdEndStructure extends AdvancedJigsawStructure {

    public StrongholdEndStructure(ResourceLocation poolID, int structureSize, int biomeRange,
                                  Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces,
                                  int maxY, int minY, boolean clipOutOfBoundsPieces, Integer verticalRange)
    {
        super(poolID, structureSize, biomeRange, requiredPieces, maxY, minY, clipOutOfBoundsPieces, verticalRange);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, WorldgenRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration featureConfig, LevelHeightAccessor heightLimitView) {
        boolean superCheck = super.isFeatureChunk(chunkGenerator, biomeSource, seed, chunkRandom, chunkPos1, biome, chunkPos, featureConfig, heightLimitView);
        if(!superCheck)
            return false;

        int minLandHeight = Math.min(chunkGenerator.getGenDepth(), chunkGenerator.getMinY() + 45);
        int xPos = chunkPos1.getMinBlockX();
        int zPos = chunkPos1.getMinBlockZ();
        int landHeight = chunkGenerator.getFirstOccupiedHeight(xPos, zPos, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
        if(landHeight < minLandHeight) return false;

        landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos + 70, zPos, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView));
        if(landHeight < minLandHeight) return false;

        landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos, zPos + 70, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView));
        if(landHeight < minLandHeight) return false;

        landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos - 70, zPos, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView));
        if(landHeight < minLandHeight) return false;

        landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos, zPos - 70, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView));
        return landHeight >= minLandHeight;
    }


    public static class Builder<T extends StrongholdEndStructure.Builder<T>> extends AdvancedJigsawStructure.Builder<T> {

        public Builder(ResourceLocation startPool) {
            super(startPool);
        }

        public StrongholdEndStructure build() {
            return new StrongholdEndStructure(
                    startPool,
                    structureSize,
                    biomeRange,
                    requiredPieces,
                    maxY,
                    minY,
                    clipOutOfBoundsPieces,
                    verticalRange);
        }
    }
}
