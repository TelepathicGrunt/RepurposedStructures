package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureStart;

import java.util.Map;


public class MineshaftStructure extends AdvancedJigsawStructure {

    protected final double probability;

    public MineshaftStructure(ResourceLocation poolID, int structureSize, int biomeRange,
                              int maxY, int minY, boolean clipOutOfBoundsPieces, Integer verticalRange,
                              double probability)
    {
        super(poolID, structureSize, biomeRange, maxY, minY, clipOutOfBoundsPieces, verticalRange);
        this.probability = probability;
    }

    @Override
    public BlockPos getNearestGeneratedFeature(LevelReader world, StructureFeatureManager structureAccessor, BlockPos searchStartPos, int searchRadius, boolean skipExistingChunks, long worldSeed, StructureFeatureConfiguration config) {
        return MineshaftStructure.locateStructureFast(world, structureAccessor, searchStartPos, searchRadius, skipExistingChunks, worldSeed, config, this, this.probability);
    }

    public static <C extends FeatureConfiguration> BlockPos locateStructureFast(LevelReader worldView, StructureFeatureManager structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureFeatureConfiguration structureConfig, StructureFeature<C> structure, double probability) {
        int spacing = structureConfig.spacing();
        int chunkPosX = blockPos.getX() >> 4;
        int chunkPosZ = blockPos.getZ() >> 4;
        int currentRadius = 0;
        WorldgenRandom msRandom = new WorldgenRandom();

        for(WorldgenRandom chunkRandom = new WorldgenRandom(); currentRadius <= 100000; ++currentRadius) {
            for(int xRadius = -currentRadius; xRadius <= currentRadius; ++xRadius) {
                boolean xEdge = xRadius == -currentRadius || xRadius == currentRadius;

                for(int zRadius = -currentRadius; zRadius <= currentRadius; ++zRadius) {
                    boolean zEdge = zRadius == -currentRadius || zRadius == currentRadius;
                    if (xEdge || zEdge) {
                        int trueChunkX = chunkPosX + spacing * xRadius;
                        int trueChunkZ = chunkPosZ + spacing * zRadius;
                        ChunkPos chunkPos = structure.getPotentialFeatureChunk(structureConfig, seed, chunkRandom, trueChunkX, trueChunkZ);

                        // Speedup for mineshafts by checking probability chance before getChunk or grabbing biome.
                        msRandom.setLargeFeatureSeed(seed + structureConfig.salt(), chunkPos.x, chunkPos.z);
                        double d = (probability / 10000D);
                        if(msRandom.nextDouble() < d && worldView.getNoiseBiome((chunkPos.x << 2) + 2, 60, (chunkPos.z << 2) + 2).getGenerationSettings().isValidStart(structure)) {

                            ChunkAccess chunk = worldView.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_STARTS);
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

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, WorldgenRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration featureConfig, LevelHeightAccessor heightLimitView) {
        StructureFeatureConfiguration structureConfig = chunkGenerator.getSettings().getConfig(this);
        if(structureConfig != null) {
            chunkRandom.setLargeFeatureSeed(seed + structureConfig.salt(), chunkPos1.x, chunkPos1.z);
            double d = (this.probability / 10000D);
            return chunkRandom.nextDouble() < d;
        }
        return false;
    }

    public static class Builder<T extends MineshaftStructure.Builder<T>> extends AdvancedJigsawStructure.Builder<T> {

        protected double probability = 0.01;

        public Builder(ResourceLocation startPool) {
            super(startPool);
        }

        public T setProbability(double probability){
            this.probability = probability;
            return getThis();
        }

        public MineshaftStructure build() {
            return new MineshaftStructure(
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