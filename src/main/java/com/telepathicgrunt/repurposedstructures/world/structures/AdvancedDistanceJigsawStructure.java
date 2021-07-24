package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Map;


public class AdvancedDistanceJigsawStructure extends AdvancedJigsawStructure {

    protected final int distanceFromWorldOrigin;

    public AdvancedDistanceJigsawStructure(ResourceLocation poolID, int structureSize, int biomeRange,
                                           Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces,
                                           int maxY, int minY, boolean clipOutOfBoundsPieces, Integer verticalRange,
                                           int distanceFromWorldOrigin)
    {
        super(poolID, structureSize, biomeRange, requiredPieces, maxY, minY, clipOutOfBoundsPieces, verticalRange);
        this.distanceFromWorldOrigin = distanceFromWorldOrigin;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        int radius = distanceFromWorldOrigin;
        int xBlockPos = chunkX * 16;
        int zBlockPos = chunkZ * 16;
        return (xBlockPos * xBlockPos) + (zBlockPos * zBlockPos) > radius * radius;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    public class Start extends MainStart {

        private final ResourceLocation structureID;

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox box, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, box, referenceIn, seedIn);
            structureID = Registry.STRUCTURE_FEATURE.getKey(structureIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig defaultFeatureConfig) {
            BlockPos.Mutable blockpos = new BlockPos.Mutable(chunkX * 16, 0, chunkZ * 16);

            // -5 so that the start piece's bottom 2 jigsaw blocks can spawn extra pieces and the rest of the stronghold wont go as high as start stairway
            blockpos.move(Direction.UP, maxY - 5);

            PieceLimitedJigsawManager.assembleJigsawStructure(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(startPool), structureSize),
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.pieces,
                    this.random,
                    false,
                    false,
                    structureID,
                    maxY,
                    minY);

            this.calculateBoundingBox();
        }
    }


    public static class Builder<T extends Builder<T>> extends AdvancedJigsawStructure.Builder<T> {

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
                    requiredPieces,
                    maxY,
                    minY,
                    clipOutOfBoundsPieces,
                    verticalRange,
                    distanceFromWorldOrigin);
        }
    }
}