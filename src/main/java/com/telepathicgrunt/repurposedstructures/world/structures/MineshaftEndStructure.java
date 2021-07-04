package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Map;


public class MineshaftEndStructure extends MineshaftStructure {

    public MineshaftEndStructure(Identifier poolID, int structureSize, int biomeRange,
                                 Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces,
                                 int maxY, int minY, boolean clipOutOfBoundsPieces, Integer verticalRange,
                                 double probability)
    {
        super(poolID, structureSize, biomeRange, requiredPieces, maxY, minY, clipOutOfBoundsPieces, verticalRange, probability);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig featureConfig, HeightLimitView heightLimitView) {
        StructureConfig structureConfig = chunkGenerator.getStructuresConfig().getForType(this);
        if(structureConfig != null) {
            chunkRandom.setCarverSeed(seed + structureConfig.getSalt(), chunkPos1.x, chunkPos1.z);
            double d = (probability / 10000D);
            if (chunkRandom.nextDouble() < d) {
                if(RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.barrensIslandsEndMineshafts)
                    return true;

                int minLandHeight = Math.min(chunkGenerator.getWorldHeight(), 45);
                int xPos = chunkPos1.getStartX();
                int zPos = chunkPos1.getStartZ();
                int landHeight = chunkGenerator.getHeightInGround(xPos, zPos, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView);

                landHeight = Math.min(landHeight, chunkGenerator.getHeightInGround(xPos + 70, zPos, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView));
                if(landHeight < minLandHeight) return false;

                landHeight = Math.min(landHeight, chunkGenerator.getHeightInGround(xPos, zPos + 70, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView));
                if(landHeight < minLandHeight) return false;

                landHeight = Math.min(landHeight, chunkGenerator.getHeightInGround(xPos - 70, zPos, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView));
                if(landHeight < minLandHeight) return false;

                landHeight = Math.min(landHeight, chunkGenerator.getHeightInGround(xPos, zPos - 70, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView));
                return landHeight >= minLandHeight;
            }
        }
        return false;
    }


    public static class Builder<T extends MineshaftEndStructure.Builder<?>> extends AdvancedJigsawStructure.Builder<T> {

        protected double probability = 0.01;

        public Builder(Identifier startPool) {
            super(startPool);
        }

        public T setProbability(double probability){
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
