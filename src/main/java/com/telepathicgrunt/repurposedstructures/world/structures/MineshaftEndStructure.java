package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.util.Lazy;

import java.util.Map;


public class MineshaftEndStructure extends MineshaftStructure {

    public MineshaftEndStructure(ResourceLocation poolID, int structureSize, int biomeRange,
                                 Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces,
                                 Lazy<Integer> maxY, Lazy<Integer> minY, boolean clipOutOfBoundsPieces, Integer verticalRange,
                                 Lazy<Double> probability)
    {
        super(poolID, structureSize, biomeRange, requiredPieces, maxY, minY, clipOutOfBoundsPieces, verticalRange, probability);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        boolean superCheck = super.isFeatureChunk(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, featureConfig);
        if(!superCheck)
            return false;

        if(RepurposedStructures.RSMineshaftsConfig.barrensIslandsEndMineshafts.get())
            return true;

        int minLandHeight = Math.min(chunkGenerator.getGenDepth(), 45);
        int xPos = chunkX * 16;
        int zPos = chunkZ * 16;
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


    public static class Builder<T extends Builder<T>> extends AdvancedJigsawStructure.Builder<T> {

        protected Lazy<Double> probability = Lazy.of(() -> 0.01D);

        public Builder(ResourceLocation startPool) {
            super(startPool);
        }

        public T setProbability(Lazy<Double> probability){
            this.probability = probability;
            return getThis();
        }

        public MineshaftEndStructure build() {
            return new MineshaftEndStructure(
                    startPool,
                    structureSize,
                    biomeRange,
                    requiredPieces,
                    maxY,
                    minY,
                    clipOutOfBoundsPieces,
                    verticalRange,
                    probability);
        }
    }
}
