package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;


public class BuriableStructure extends AbstractBaseStructure<NoneFeatureConfiguration> {

    private final ResourceLocation startPool;
    private final int offsetAmount;
    private final boolean onLand;
    private final boolean cannotSpawnInWater;

    public BuriableStructure(ResourceLocation startPool, int offsetAmount, boolean onLand, boolean cannotSpawnInWater) {
        super(NoneFeatureConfiguration.CODEC);
        this.startPool = startPool;
        RSStructures.RS_STRUCTURE_START_PIECES.add(this.startPool);
        this.offsetAmount = offsetAmount;
        this.onLand = onLand;
        this.cannotSpawnInWater = cannotSpawnInWater;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, WorldgenRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration defaultFeatureConfig, LevelHeightAccessor heightLimitView) {

        if(cannotSpawnInWater){
            BlockPos centerOfChunk = chunkPos.getMiddleBlockPosition(0);
            int landHeight = chunkGenerator.getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
            NoiseColumn columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ(), heightLimitView);
            BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));
            return topBlock.getFluidState().isEmpty();
        }

        return true;
    }

    @Override
    public StructureFeature.StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return BuriableStructure.Start::new;
    }

    public class Start extends StructureStart<NoneFeatureConfiguration> {

        public Start(StructureFeature<NoneFeatureConfiguration> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        @Override
        public void init(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, NoneFeatureConfiguration defaultFeatureConfig, LevelHeightAccessor heightLimitView) {
            BlockPos blockpos = new BlockPos(chunkPos1.getMinBlockX(), chunkGenerator.getSeaLevel(), chunkPos1.getMinBlockZ());
            JigsawPlacement.addPieces(
                    dynamicRegistryManager,
                    new JigsawConfiguration(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(startPool), 11),
                    PoolElementStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this,
                    random,
                    false,
                    false,
                    heightLimitView);
            this.getBoundingBox();

            Rotation rotation = this.pieces.get(0).getRotation();
            BlockPos maxCorner = new BlockPos(this.pieces.get(0).getBoundingBox().getXSpan(), 0, this.pieces.get(0).getBoundingBox().getZSpan()).rotate(rotation);

            Heightmap.Types heightMapToUse = onLand ? Heightmap.Types.WORLD_SURFACE_WG : Heightmap.Types.OCEAN_FLOOR_WG;

            int highestLandPos = chunkGenerator.getBaseHeight(blockpos.getX() + maxCorner.getX(), blockpos.getZ() + maxCorner.getZ(), heightMapToUse, heightLimitView);
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getBaseHeight(blockpos.getX(), blockpos.getZ() + maxCorner.getZ(), heightMapToUse, heightLimitView));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getBaseHeight(blockpos.getX() + maxCorner.getX(), blockpos.getZ(), heightMapToUse, heightLimitView));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getBaseHeight(blockpos.getX(), blockpos.getZ(), heightMapToUse, heightLimitView));

            this.moveInsideHeights(this.random, highestLandPos-(offsetAmount+1), highestLandPos-offsetAmount);
        }
    }


    public static class Builder<T extends BuriableStructure.Builder<T>> {
        private final ResourceLocation startPool;
        private int offsetAmount = 14;
        private boolean onLand = true;
        private boolean cannotSpawnInWater = true;

        public Builder(ResourceLocation startPool) {
            this.startPool = startPool;
        }

        @SuppressWarnings("unchecked")
        protected T getThis() {
            return (T) this;
        }

        public T setOffsetAmount(int offsetAmount){
            this.offsetAmount = offsetAmount;
            return getThis();
        }

        public T useOceanHeightmap(){
            this.onLand = false;
            return getThis();
        }

        public T cannotSpawnInWater(){
            this.cannotSpawnInWater = false;
            return getThis();
        }

        public BuriableStructure build() {
            return new BuriableStructure(
                    startPool,
                    offsetAmount,
                    onLand,
                    cannotSpawnInWater
            );
        }
    }
}