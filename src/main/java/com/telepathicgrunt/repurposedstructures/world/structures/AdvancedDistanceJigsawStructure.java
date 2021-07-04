package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.misc.PieceLimitedJigsawManager;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Map;


public class AdvancedDistanceJigsawStructure extends AdvancedJigsawStructure {

    protected final int distanceFromWorldOrigin;

    public AdvancedDistanceJigsawStructure(Identifier poolID, int structureSize, int biomeRange,
                                           Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces,
                                           int maxY, int minY, boolean clipOutOfBoundsPieces, Integer verticalRange,
                                           int distanceFromWorldOrigin)
    {
        super(poolID, structureSize, biomeRange, requiredPieces, maxY, minY, clipOutOfBoundsPieces, verticalRange);
        this.distanceFromWorldOrigin = distanceFromWorldOrigin;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig featureConfig, HeightLimitView heightLimitView) {
        int radius = distanceFromWorldOrigin;
        int xBlockPos = chunkPos1.getStartX();
        int zBlockPos = chunkPos1.getStartZ();
        return (xBlockPos * xBlockPos) + (zBlockPos * zBlockPos) > radius * radius;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return AdvancedDistanceJigsawStructure.Start::new;
    }

    public class Start extends MainStart {

        private final Identifier structureID;

        public Start(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
            structureID = Registry.STRUCTURE_FEATURE.getId(structureIn);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            BlockPos.Mutable blockpos = new BlockPos.Mutable(chunkPos1.getStartX(), 0, chunkPos1.getStartZ());

            // -5 so that the start piece's bottom 2 jigsaw blocks can spawn extra pieces and the rest of the stronghold wont go as high as start stairway
            blockpos.move(Direction.UP, maxY - 5);

            PieceLimitedJigsawManager.assembleJigsawStructure(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.STRUCTURE_POOL_KEY).get(startPool), structureSize),
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.children,
                    this.random,
                    false,
                    false,
                    heightLimitView,
                    structureID,
                    maxY,
                    minY);

            this.setBoundingBoxFromChildren();
        }
    }


    public static class Builder<T extends AdvancedDistanceJigsawStructure.Builder<T>> extends AdvancedJigsawStructure.Builder<T> {

        protected int distanceFromWorldOrigin = 2817;

        public Builder(Identifier startPool) {
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
                    requiredPieces,
                    maxY,
                    minY,
                    clipOutOfBoundsPieces,
                    verticalRange,
                    distanceFromWorldOrigin);
        }
    }
}