package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.common.util.Lazy;

import java.util.Map;


public class MineshaftStructure extends AdvancedJigsawStructure {

    protected final Lazy<Double> probability;

    public MineshaftStructure(ResourceLocation poolID, int structureSize, int biomeRange,
                              Lazy<Integer> maxY, Lazy<Integer> minY, boolean clipOutOfBoundsPieces, Integer verticalRange,
                              Lazy<Double> probability)
    {
        super(poolID, structureSize, biomeRange, maxY, minY, clipOutOfBoundsPieces, verticalRange);
        this.probability = probability;
    }

    @Override
    public BlockPos getNearestGeneratedFeature(IWorldReader world, StructureManager structureAccessor, BlockPos searchStartPos, int searchRadius, boolean skipExistingChunks, long worldSeed, StructureSeparationSettings config) {
        return MineshaftStructure.locateStructureFast(world, structureAccessor, searchStartPos, searchRadius, skipExistingChunks, worldSeed, config, this, this.probability.get());
    }

    public static <C extends IFeatureConfig> BlockPos locateStructureFast(IWorldReader worldView, StructureManager structureAccessor, BlockPos blockPos, int radius, boolean skipExistingChunks, long seed, StructureSeparationSettings structureConfig, Structure<C> structure, double probability) {
        int spacing = structureConfig.spacing();
        int chunkX = blockPos.getX() >> 4;
        int chunkZ = blockPos.getZ() >> 4;
        int currentRadius = 0;
        SharedSeedRandom msRandom = new SharedSeedRandom();

        for(SharedSeedRandom chunkRandom = new SharedSeedRandom(); currentRadius <= 100000; ++currentRadius) {
            for(int xRadius = -currentRadius; xRadius <= currentRadius; ++xRadius) {
                boolean xEdge = xRadius == -currentRadius || xRadius == currentRadius;

                for(int zRadius = -currentRadius; zRadius <= currentRadius; ++zRadius) {
                    boolean zEdge = zRadius == -currentRadius || zRadius == currentRadius;
                    if (xEdge || zEdge) {
                        int trueChunkX = chunkX + spacing * xRadius;
                        int trueChunkZ = chunkZ + spacing * zRadius;
                        ChunkPos chunkPos = structure.getPotentialFeatureChunk(structureConfig, seed, chunkRandom, trueChunkX, trueChunkZ);

                        // Speedup for mineshafts by checking probability chance before getChunk or grabbing biome.
                        msRandom.setLargeFeatureSeed(seed + structureConfig.salt(), chunkPos.x, chunkPos.z);
                        double d = (probability / 10000D);
                        if(msRandom.nextDouble() < d && worldView.getNoiseBiome((chunkPos.x << 2) + 2, 60, (chunkPos.z << 2) + 2).getGenerationSettings().isValidStart(structure)) {

                            IChunk chunk = worldView.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.STRUCTURE_STARTS);
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
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        StructureSeparationSettings structureConfig = chunkGenerator.getSettings().getConfig(this);
        if(structureConfig != null) {
            chunkRandom.setLargeFeatureSeed(seed + structureConfig.salt(), chunkX, chunkZ);
            double d = (this.probability.get() / 10000D);
            return chunkRandom.nextDouble() < d;
        }
        return false;
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