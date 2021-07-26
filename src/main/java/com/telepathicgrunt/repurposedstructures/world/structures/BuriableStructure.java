package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class BuriableStructure extends AbstractBaseStructure<NoFeatureConfig> {

    private final ResourceLocation startPool;
    private final int offsetAmount;
    private final boolean onLand;
    private final boolean cannotSpawnInWater;

    public BuriableStructure(ResourceLocation startPool, int offsetAmount, boolean onLand, boolean cannotSpawnInWater) {
        super(NoFeatureConfig.CODEC);
        this.startPool = startPool;
        RSStructures.RS_STRUCTURE_START_PIECES.add(this.startPool);
        this.offsetAmount = offsetAmount;
        this.onLand = onLand;
        this.cannotSpawnInWater = cannotSpawnInWater;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig defaultFeatureConfig) {

        if(cannotSpawnInWater){
            BlockPos centerOfChunk = chunkPos.getWorldPosition();
            int landHeight = chunkGenerator.getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
            IBlockReader columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ());
            BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));
            return topBlock.getFluidState().isEmpty();
        }

        return true;
    }

    @Override
    public Structure.IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    public class Start extends StructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox box, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, box, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig defaultFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, chunkGenerator.getSeaLevel(), chunkZ * 16);
            JigsawManager.addPieces(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(startPool), 11),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.pieces,
                    random,
                    false,
                    false);
            GeneralUtils.centerAllPieces(blockpos, this.pieces);
            this.calculateBoundingBox();

            Rotation rotation = this.pieces.get(0).getRotation();
            BlockPos maxCorner = new BlockPos(this.pieces.get(0).getBoundingBox().getXSpan(), 0, this.pieces.get(0).getBoundingBox().getZSpan()).rotate(rotation);

            Heightmap.Type heightMapToUse = onLand ? Heightmap.Type.WORLD_SURFACE_WG : Heightmap.Type.OCEAN_FLOOR_WG;

            int highestLandPos = chunkGenerator.getBaseHeight(blockpos.getX() + maxCorner.getX(), blockpos.getZ() + maxCorner.getZ(), heightMapToUse);
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getBaseHeight(blockpos.getX(), blockpos.getZ() + maxCorner.getZ(), heightMapToUse));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getBaseHeight(blockpos.getX() + maxCorner.getX(), blockpos.getZ(), heightMapToUse));
            highestLandPos = Math.min(highestLandPos, chunkGenerator.getBaseHeight(blockpos.getX(), blockpos.getZ(), heightMapToUse));

            if(!onLand){
                int maxHeightForSubmerging = chunkGenerator.getSeaLevel() - this.pieces.get(0).getBoundingBox().getYSpan();
                highestLandPos = Math.min(highestLandPos, maxHeightForSubmerging);
            }

            this.moveInsideHeights(this.random, highestLandPos-(offsetAmount+1), highestLandPos-offsetAmount);
        }
    }


    public static class Builder<T extends Builder<T>> {
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