package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
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
import net.minecraftforge.common.util.Lazy;


public class AdvancedDistanceJigsawStructure extends AdvancedJigsawStructure {

    protected final int distanceFromWorldOrigin;

    public AdvancedDistanceJigsawStructure(ResourceLocation poolID, Lazy<Integer> structureSize, int biomeRange,
                                           Lazy<Integer> maxY, Lazy<Integer> minY, boolean clipOutOfBoundsPieces,
                                           Lazy<Integer> verticalRange, int distanceFromWorldOrigin)
    {
        super(poolID, structureSize, biomeRange, maxY, minY, clipOutOfBoundsPieces, verticalRange);
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
            if(maxY.get() - minY.get() <= 0){
                RepurposedStructures.LOGGER.error("MinY should always be less than MaxY or else a crash will occur or no pieces will spawn. Problematic structure is:" + Registry.STRUCTURE_FEATURE.getKey(this.getFeature()));
            }
            int structureStartHeight = random.nextInt(maxY.get() - minY.get()) + minY.get();
            blockpos.move(Direction.UP, structureStartHeight);

            int topClipOff;
            int bottomClipOff;
            if(verticalRange == null){
                // Help make sure the Jigsaw Blocks have room to spawn new pieces if structure is right on edge of maxY or minY
                topClipOff = clipOutOfBoundsPieces ? maxY.get() + 5 : Integer.MAX_VALUE;
                bottomClipOff = clipOutOfBoundsPieces ? minY.get() - 5 : Integer.MIN_VALUE;
            }
            else{
                topClipOff = structureStartHeight + verticalRange.get();
                bottomClipOff = structureStartHeight - verticalRange.get();
            }

            PieceLimitedJigsawManager.assembleJigsawStructure(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(startPool), structureSize.get()),
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.pieces,
                    this.random,
                    false,
                    false,
                    structureID,
                    topClipOff,
                    bottomClipOff);

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
                    maxY,
                    minY,
                    clipOutOfBoundsPieces,
                    verticalRange,
                    distanceFromWorldOrigin);
        }
    }
}