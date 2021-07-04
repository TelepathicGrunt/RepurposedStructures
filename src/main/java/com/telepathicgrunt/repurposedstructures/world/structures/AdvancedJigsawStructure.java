package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.PieceLimitedJigsawManager;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import net.minecraft.structure.MarginedStructureStart;
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
import net.minecraft.world.biome.source.CheckerboardBiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.HashMap;
import java.util.Map;

public class AdvancedJigsawStructure extends AbstractBaseStructure<DefaultFeatureConfig> {

    protected final Identifier startPool;
    protected final int structureSize;
    protected final int biomeRange;
    protected final Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces;
    protected final int maxY;
    protected final int minY;
    protected final boolean clipOutOfBoundsPieces;
    protected final Integer verticalRange;

    public AdvancedJigsawStructure(Identifier poolID, int structureSize, int biomeRange,
                                   Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces,
                                   int maxY, int minY, boolean clipOutOfBoundsPieces, Integer verticalRange)
    {
        super(DefaultFeatureConfig.CODEC);

        this.startPool = poolID;
        this.structureSize = structureSize;
        this.biomeRange = biomeRange;
        this.requiredPieces = requiredPieces;
        this.maxY = maxY;
        this.minY = minY;
        this.clipOutOfBoundsPieces = clipOutOfBoundsPieces;
        this.verticalRange = verticalRange;

        RSStructures.RS_STRUCTURE_START_PIECES.add(startPool);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
        if(!(biomeSource instanceof CheckerboardBiomeSource)) {
            for (int curChunkX = chunkPos1.x - biomeRange; curChunkX <= chunkPos1.x + biomeRange; curChunkX++) {
                for (int curChunkZ = chunkPos1.z - biomeRange; curChunkZ <= chunkPos1.z + biomeRange; curChunkZ++) {
                    if (!biomeSource.getBiomeForNoiseGen(curChunkX << 2, 64, curChunkZ << 2).getGenerationSettings().hasStructureFeature(this)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return AdvancedJigsawStructure.MainStart::new;
    }

    public class MainStart extends MarginedStructureStart<DefaultFeatureConfig> {

        public MainStart(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            BlockPos.Mutable blockpos = new BlockPos.Mutable(chunkPos1.getStartX(), 0, chunkPos1.getStartZ());
            if(maxY - minY <= 0){
                RepurposedStructures.LOGGER.error("MinY should always be less than MaxY or else a crash will occur or no pieces will spawn. Problematic structure is:" + Registry.STRUCTURE_FEATURE.getId(this.getFeature()));
            }
            int structureStartHeight = random.nextInt(maxY - minY) + minY;
            blockpos.move(Direction.UP, structureStartHeight);

            int topClipOff;
            int bottomClipOff;
            if(verticalRange == null){
                // Help make sure the Jigsaw Blocks have room to spawn new pieces if structure is right on edge of maxY or minY
                topClipOff = clipOutOfBoundsPieces ? maxY + 5 : Integer.MAX_VALUE;
                bottomClipOff = clipOutOfBoundsPieces ? minY - 5 : Integer.MIN_VALUE;
            }
            else{
                topClipOff = structureStartHeight + verticalRange;
                bottomClipOff = structureStartHeight - verticalRange;
            }

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
                    requiredPieces,
                    topClipOff,
                    bottomClipOff);

            this.setBoundingBoxFromChildren();
        }
    }

    public static class Builder<T extends AdvancedJigsawStructure.Builder<T>> {
        protected final Identifier startPool;
        protected int structureSize = 1;
        protected int biomeRange = 0;
        protected Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces = new HashMap<>();
        protected int maxY = 255;
        protected int minY = 0;
        protected boolean clipOutOfBoundsPieces = true;
        protected Integer verticalRange = null;

        public Builder(Identifier startPool) {
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

        public T setRequiredPieces(Map<Identifier, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces){
            this.requiredPieces = requiredPieces;
            return getThis();
        }

        public T setMaxY(int maxY){
            this.maxY = maxY;
            return getThis();
        }

        public T setMinY(int minY){
            this.minY = minY;
            return getThis();
        }

        public T setVerticalRange(int verticalRange){
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
                    requiredPieces,
                    maxY,
                    minY,
                    clipOutOfBoundsPieces,
                    verticalRange
            );
        }
    }
}