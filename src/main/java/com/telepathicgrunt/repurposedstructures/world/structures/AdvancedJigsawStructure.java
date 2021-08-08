package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
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
import net.minecraft.world.biome.provider.CheckerboardBiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.common.util.Lazy;

public class AdvancedJigsawStructure extends AbstractBaseStructure<NoFeatureConfig> {

    protected final ResourceLocation startPool;
    protected final int structureSize;
    protected final int biomeRange;
    protected final Lazy<Integer> maxY;
    protected final Lazy<Integer> minY;
    protected final boolean clipOutOfBoundsPieces;
    protected final Lazy<Integer> verticalRange;

    public AdvancedJigsawStructure(ResourceLocation poolID, int structureSize, int biomeRange,
                                   Lazy<Integer> maxY, Lazy<Integer> minY, boolean clipOutOfBoundsPieces, 
                                   Lazy<Integer> verticalRange)
    {
        super(NoFeatureConfig.CODEC);

        this.startPool = poolID;
        this.structureSize = structureSize;
        this.biomeRange = biomeRange;
        this.maxY = maxY;
        this.minY = minY;
        this.clipOutOfBoundsPieces = clipOutOfBoundsPieces;
        this.verticalRange = verticalRange;

        RSStructures.RS_STRUCTURE_START_PIECES.add(startPool);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig defaultFeatureConfig) {
        if(!(biomeSource instanceof CheckerboardBiomeProvider)) {
            for (int curChunkX = chunkX - biomeRange; curChunkX <= chunkX + biomeRange; curChunkX++) {
                for (int curChunkZ = chunkZ - biomeRange; curChunkZ <= chunkZ + biomeRange; curChunkZ++) {
                    if (!biomeSource.getNoiseBiome(curChunkX << 2, 64, curChunkZ << 2).getGenerationSettings().isValidStart(this)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return MainStart::new;
    }

    public class MainStart extends MarginedStructureStart<NoFeatureConfig> {

        private final ResourceLocation structureID;

        public MainStart(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox box, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, box, referenceIn, seedIn);
            structureID = Registry.STRUCTURE_FEATURE.getKey(structureIn);
        }

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

//            long startTime = System.currentTimeMillis();
//            for(int i = 0; i < 50; i++){
//                this.pieces.clear();


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
                        topClipOff,
                        bottomClipOff);

//            }
//            long endTime = System.currentTimeMillis();
//            long duration = (endTime - startTime);
//            RepurposedStructures.LOGGER.warn("Time taken {} milliseconds at {}, {} for {}", duration, chunkX, chunkZ, startPool);

            this.calculateBoundingBox();
        }
    }

    public static class Builder<T extends Builder<T>> {
        protected final ResourceLocation startPool;
        protected int structureSize = 1;
        protected int biomeRange = 0;
        protected Lazy<Integer> maxY = Lazy.of(() -> 255);
        protected Lazy<Integer> minY = Lazy.of(() -> 0);
        protected boolean clipOutOfBoundsPieces = true;
        protected Lazy<Integer> verticalRange = null;

        public Builder(ResourceLocation startPool) {
            this.startPool = startPool;
        }

        @SuppressWarnings("unchecked")
        protected T getThis() {
            return (T) this;
        }

        public T setStructureSize(int structureSize){
            this.structureSize = structureSize;
            return getThis();
        }

        public T setBiomeRange(int biomeRange){
            this.biomeRange = biomeRange;
            return getThis();
        }

        public T setMaxY(Lazy<Integer> maxY){
            this.maxY = maxY;
            return getThis();
        }

        public T setMinY(Lazy<Integer> minY){
            this.minY = minY;
            return getThis();
        }

        public T setVerticalRange(Lazy<Integer> verticalRange){
            this.verticalRange = verticalRange;
            return getThis();
        }

        public T doNotClipOutOfBoundsPieces(){
            this.clipOutOfBoundsPieces = false;
            return getThis();
        }

        public AdvancedJigsawStructure build() {
            return new AdvancedJigsawStructure(
                    startPool,
                    structureSize,
                    biomeRange,
                    maxY,
                    minY,
                    clipOutOfBoundsPieces,
                    verticalRange
            );
        }
    }
}