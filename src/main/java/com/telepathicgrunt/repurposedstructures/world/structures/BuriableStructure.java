package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.block.BlockState;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;


public class BuriableStructure extends AbstractBaseStructure<DefaultFeatureConfig> {

    private final Identifier startPool;
    private final int offsetAmount;
    private final boolean onLand;
    private final boolean cannotSpawnInWater;

    public BuriableStructure(Identifier startPool, int offsetAmount, boolean onLand, boolean cannotSpawnInWater) {
        super(DefaultFeatureConfig.CODEC);
        this.startPool = startPool;
        RSStructures.RS_STRUCTURE_START_PIECES.add(this.startPool);
        this.offsetAmount = offsetAmount;
        this.onLand = onLand;
        this.cannotSpawnInWater = cannotSpawnInWater;
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {

        if(cannotSpawnInWater){
            BlockPos centerOfChunk = chunkPos.getCenterAtY(0);
            int landHeight = chunkGenerator.getHeightInGround(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG, heightLimitView);
            VerticalBlockSample columnOfBlocks = chunkGenerator.getColumnSample(centerOfChunk.getX(), centerOfChunk.getZ(), heightLimitView);
            BlockState topBlock = columnOfBlocks.getState(centerOfChunk.up(landHeight));
            return topBlock.getFluidState().isEmpty();
        }

        return true;
    }

    @Override
    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return BuriableStructure.Start::new;
    }

    public class Start extends StructureStart<DefaultFeatureConfig> {

        public Start(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        @Override
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            BlockPos blockpos = new BlockPos(chunkPos1.getStartX(), chunkGenerator.getSeaLevel(), chunkPos1.getStartZ());
            StructurePoolBasedGenerator.method_30419(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.STRUCTURE_POOL_KEY).get(startPool), 11),
                    PoolStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this,
                    random,
                    false,
                    false,
                    heightLimitView);
            this.setBoundingBoxFromChildren();

            BlockRotation rotation = this.children.get(0).getRotation();
            BlockPos maxCorner = new BlockPos(this.children.get(0).getBoundingBox().getBlockCountX(), 0, this.children.get(0).getBoundingBox().getBlockCountZ()).rotate(rotation);

            Heightmap.Type heightMapToUse = onLand ? Heightmap.Type.WORLD_SURFACE_WG : Heightmap.Type.OCEAN_FLOOR_WG;

            int highestLandPos = chunkGenerator.getHeight(blockpos.getX() + maxCorner.getX(), blockpos.getZ() + maxCorner.getZ(), heightMapToUse, heightLimitView);
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getHeight(blockpos.getX(), blockpos.getZ() + maxCorner.getZ(), heightMapToUse, heightLimitView));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getHeight(blockpos.getX() + maxCorner.getX(), blockpos.getZ(), heightMapToUse, heightLimitView));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getHeight(blockpos.getX(), blockpos.getZ(), heightMapToUse, heightLimitView));

            this.randomUpwardTranslation(this.random, highestLandPos-(offsetAmount+1), highestLandPos-offsetAmount);
        }
    }


    public static class Builder<T extends BuriableStructure.Builder<T>> {
        private final Identifier startPool;
        private int offsetAmount = 14;
        private boolean onLand = true;
        private boolean cannotSpawnInWater = true;

        public Builder(Identifier startPool) {
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