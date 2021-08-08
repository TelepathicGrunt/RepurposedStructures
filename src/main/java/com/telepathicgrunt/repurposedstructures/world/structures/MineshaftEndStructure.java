package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.common.util.Lazy;


public class MineshaftEndStructure extends MineshaftStructure {

    public MineshaftEndStructure(ResourceLocation poolID, int structureSize, int biomeRange,
                                 Lazy<Integer> maxY, Lazy<Integer> minY, boolean clipOutOfBoundsPieces,
                                 Lazy<Integer> verticalRange, Lazy<Double> probability)
    {
        super(poolID, structureSize, biomeRange, maxY, minY, clipOutOfBoundsPieces, verticalRange, probability);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        boolean superCheck = super.isFeatureChunk(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, featureConfig);
        if(!superCheck)
            return false;

        if(RepurposedStructures.RSMineshaftsConfig.barrensIslandsEndMineshafts.get())
            return true;


        //cannot be near end strongholds
        int structureCheckRadius = 6;
        for (int curChunkX = chunkX - structureCheckRadius; curChunkX <= chunkZ + structureCheckRadius; curChunkX++) {
            for (int curChunkZ = chunkX - structureCheckRadius; curChunkZ <= chunkZ + structureCheckRadius; curChunkZ++) {
                for(Structure<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.END_MINESHAFT_AVOID_STRUCTURE)){
                    if(structureFeature == this) continue;

                    StructureSeparationSettings structureConfig = chunkGenerator.getSettings().getConfig(structureFeature);
                    if(structureConfig != null && structureConfig.spacing() > 10){
                        ChunkPos chunkPos2 = structureFeature.getPotentialFeatureChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                }
            }
        }

        int minLandHeight = Math.min(chunkGenerator.getGenDepth(), 45);
        int xPos = chunkX * 16;
        int zPos = chunkZ * 16;
        int landHeight = getHeightAt(chunkGenerator, xPos, zPos, Integer.MAX_VALUE);
        if(landHeight < minLandHeight) return false;

        for(Direction direction : Direction.Plane.HORIZONTAL){
            Vector3f offsetPos = direction.step();
            offsetPos.mul(70f);
            landHeight = getHeightAt(chunkGenerator, xPos + (int)offsetPos.x(), zPos + (int)offsetPos.z(), landHeight);
            if(landHeight < minLandHeight) return false;
        }

        return true;
    }

    private int getHeightAt(ChunkGenerator chunkGenerator, int xPos, int zPos, int landHeight) {
        landHeight = Math.min(landHeight, chunkGenerator.getFirstOccupiedHeight(xPos, zPos, Heightmap.Type.WORLD_SURFACE_WG));
        return landHeight;
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
                    maxY,
                    minY,
                    clipOutOfBoundsPieces,
                    verticalRange,
                    probability);
        }
    }
}
