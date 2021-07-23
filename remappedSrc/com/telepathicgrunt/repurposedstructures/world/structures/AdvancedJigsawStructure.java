package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.PieceLimitedJigsawManager;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.StructurePiecesBehavior;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.CheckerboardColumnBiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.NoiseAffectingStructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

public class AdvancedJigsawStructure extends AbstractBaseStructure<NoneFeatureConfiguration> {

    protected final ResourceLocation startPool;
    protected final int structureSize;
    protected final int biomeRange;
    protected final Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces;
    protected final int maxY;
    protected final int minY;
    protected final boolean clipOutOfBoundsPieces;
    protected final Integer verticalRange;

    public AdvancedJigsawStructure(ResourceLocation poolID, int structureSize, int biomeRange,
                                   Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces,
                                   int maxY, int minY, boolean clipOutOfBoundsPieces, Integer verticalRange)
    {
        super(NoneFeatureConfiguration.CODEC);

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
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, WorldgenRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration defaultFeatureConfig, LevelHeightAccessor heightLimitView) {
        if(!(biomeSource instanceof CheckerboardColumnBiomeSource)) {
            for (int curChunkX = chunkPos1.x - biomeRange; curChunkX <= chunkPos1.x + biomeRange; curChunkX++) {
                for (int curChunkZ = chunkPos1.z - biomeRange; curChunkZ <= chunkPos1.z + biomeRange; curChunkZ++) {
                    if (!biomeSource.getNoiseBiome(curChunkX << 2, 64, curChunkZ << 2).getGenerationSettings().isValidStart(this)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return AdvancedJigsawStructure.MainStart::new;
    }

    public class MainStart extends NoiseAffectingStructureStart<NoneFeatureConfiguration> {

        private final ResourceLocation structureID;

        public MainStart(StructureFeature<NoneFeatureConfiguration> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
            structureID = Registry.STRUCTURE_FEATURE.getKey(structureIn);
        }

        public void init(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, NoneFeatureConfiguration defaultFeatureConfig, LevelHeightAccessor heightLimitView) {
            BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos(chunkPos1.getMinBlockX(), 0, chunkPos1.getMinBlockZ());
            if(maxY - minY <= 0){
                RepurposedStructures.LOGGER.error("MinY should always be less than MaxY or else a crash will occur or no pieces will spawn. Problematic structure is:" + Registry.STRUCTURE_FEATURE.getKey(this.getFeature()));
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

            this.getBoundingBox();
        }
    }

    public static class Builder<T extends AdvancedJigsawStructure.Builder<T>> {
        protected final ResourceLocation startPool;
        protected int structureSize = 1;
        protected int biomeRange = 0;
        protected Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces = new HashMap<>();
        protected int maxY = 255;
        protected int minY = 0;
        protected boolean clipOutOfBoundsPieces = true;
        protected Integer verticalRange = null;

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

        public T setRequiredPieces(Map<ResourceLocation, StructurePiecesBehavior.RequiredPieceNeeds> requiredPieces){
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