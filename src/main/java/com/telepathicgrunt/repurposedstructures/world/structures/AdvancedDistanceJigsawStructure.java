package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Comparator;


public class AdvancedDistanceJigsawStructure extends AdvancedJigsawStructure {

    protected final int distanceFromWorldOrigin;

    public AdvancedDistanceJigsawStructure(ResourceLocation poolID, int structureSize, int biomeRange,
                                           int maxY, int minY, boolean clipOutOfBoundsPieces,
                                           Integer verticalRange, int distanceFromWorldOrigin)
    {
        super(poolID, structureSize, biomeRange, maxY, minY, clipOutOfBoundsPieces, verticalRange);
        this.distanceFromWorldOrigin = distanceFromWorldOrigin;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, WorldgenRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration featureConfig, LevelHeightAccessor heightLimitView) {
        int radius = distanceFromWorldOrigin;
        int xBlockPos = chunkPos1.getMinBlockX();
        int zBlockPos = chunkPos1.getMinBlockZ();
        return (xBlockPos * xBlockPos) + (zBlockPos * zBlockPos) > radius * radius;
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return AdvancedDistanceJigsawStructure.Start::new;
    }

    public class Start extends MainStart {
        private final ResourceLocation structureID;

        public Start(StructureFeature<NoneFeatureConfiguration> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
            structureID = Registry.STRUCTURE_FEATURE.getKey(structureIn);
        }

        @Override
        public void generatePieces(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, NoneFeatureConfiguration defaultFeatureConfig, LevelHeightAccessor heightLimitView) {
            BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos(chunkPos1.getMinBlockX(), 0, chunkPos1.getMinBlockZ());
            if(maxY - minY <= 0){
                RepurposedStructures.LOGGER.error("MinY should always be less than MaxY or else a crash will occur or no pieces will spawn. Problematic structure is:" + Registry.STRUCTURE_FEATURE.getKey(this.getFeature()));
            }
            int structureStartHeight = random.nextInt(maxY - minY) + minY;
            blockpos.move(Direction.UP, structureStartHeight);

            int topClipOff;
            int bottomClipOff;
            if(verticalRange == null){
                // Help make sure the Jigsaw Blocks have room to spawn new pieces if structure is right on edge of maxY or topYLimit
                topClipOff = clipOutOfBoundsPieces ? maxY + 5 : Integer.MAX_VALUE;
                bottomClipOff = clipOutOfBoundsPieces ? minY - 5 : Integer.MIN_VALUE;
            }
            else{
                topClipOff = structureStartHeight + verticalRange;
                bottomClipOff = structureStartHeight - verticalRange;
            }

            PieceLimitedJigsawManager.assembleJigsawStructure(
                    dynamicRegistryManager,
                    new JigsawConfiguration(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(startPool), structureSize),
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.pieces,
                    this.random,
                    false,
                    false,
                    heightLimitView,
                    structureID,
                    topClipOff,
                    bottomClipOff);

            int minY = this.getPieces().stream().min(Comparator.comparingInt(p -> p.getBoundingBox().minY())).get().getBoundingBox().minY();
            if(minY < chunkGenerator.getMinY()) {
                int newOffset = chunkGenerator.getMinY() - minY;
                for (StructurePiece piece : this.pieces) {
                    piece.move(0, newOffset, 0);
                }
            }

            this.getBoundingBox();
        }
    }


    public static class Builder<T extends AdvancedDistanceJigsawStructure.Builder<T>> extends AdvancedJigsawStructure.Builder<T> {

        protected int distanceFromWorldOrigin = 2817;

        public Builder(ResourceLocation startPool) {
            super(startPool);
        }

        public T setDistanceFromWorldOrigin(int distanceFromWorldOrigin){
            this.distanceFromWorldOrigin = distanceFromWorldOrigin;
            return getThis();
        }

        public AdvancedDistanceJigsawStructure build() {
            return new AdvancedDistanceJigsawStructure(
                    startPool,
                    structureSize,
                    biomeRange,
                    maxY,
                    minY,
                    clipOutOfBoundsPieces,
                    verticalRange,
                    distanceFromWorldOrigin);
        }
    }
}