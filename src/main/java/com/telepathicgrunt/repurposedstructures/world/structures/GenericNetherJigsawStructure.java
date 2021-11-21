package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Set;

public class GenericNetherJigsawStructure extends GenericJigsawStructureMain {

    protected final boolean highestLandSearch;
    protected final boolean canPlaceOnLiquid;
    protected final int ledgeSpotOffset;
    protected final int liquidSpotOffset;

    public GenericNetherJigsawStructure(ResourceLocation poolID, int structureSize, int centerOffset, int biomeRange,
                                        int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet,
                                        int allowTerrainHeightRange, int terrainHeightRadius,
                                        int minHeightLimit, int fixedYSpawn, boolean useHeightmap,
                                        boolean cannotSpawnInWater, boolean highestLandSearch,
                                        boolean canPlaceOnLiquid, int ledgeSpotOffset, int liquidSpotOffset)
    {
        super(
                poolID,
                structureSize,
                centerOffset,
                biomeRange,
                structureBlacklistRange,
                avoidStructuresSet,
                allowTerrainHeightRange,
                terrainHeightRadius,
                minHeightLimit,
                fixedYSpawn,
                useHeightmap,
                cannotSpawnInWater
        );
        this.highestLandSearch = highestLandSearch;
        this.canPlaceOnLiquid = canPlaceOnLiquid;
        this.ledgeSpotOffset = ledgeSpotOffset;
        this.liquidSpotOffset = liquidSpotOffset;
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return GenericNetherJigsawStructure.Start::new;
    }

    public class Start extends MainStart {
        public Start(StructureFeature<NoneFeatureConfiguration> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        public void generatePieces(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, NoneFeatureConfiguration defaultFeatureConfig, LevelHeightAccessor heightLimitView) {
            super.generatePieces(dynamicRegistryManager, chunkGenerator, structureManager, chunkPos1, biome, defaultFeatureConfig, heightLimitView);

            BlockPos placementPos;
            if(highestLandSearch){
                placementPos = GeneralUtils.getHighestLand(chunkGenerator, this.createBoundingBox(), heightLimitView, canPlaceOnLiquid);
            }
            else{
                placementPos = GeneralUtils.getLowestLand(chunkGenerator, this.createBoundingBox(), heightLimitView, canPlaceOnLiquid);
            }

            if (placementPos.getY() >= chunkGenerator.getGenDepth() || placementPos.getY() <= chunkGenerator.getSeaLevel() + 1) {
                this.moveInsideHeights(this.random, chunkGenerator.getSeaLevel() + ledgeSpotOffset, chunkGenerator.getSeaLevel() + (ledgeSpotOffset + 1));
            }
            else {
                this.moveInsideHeights(this.random, placementPos.getY() + liquidSpotOffset, placementPos.getY() + (liquidSpotOffset + 1));
            }
        }
    }

    public static class Builder<T extends GenericNetherJigsawStructure.Builder<T>> extends GenericJigsawStructureMain.Builder<T> {
        protected boolean highestLandSearch = false;
        protected boolean canPlaceOnLiquid = false;
        protected int ledgeSpotOffset;
        protected int liquidSpotOffset;

        public Builder(ResourceLocation startPool) {
            super(startPool);
        }

        public T searchForHighestLand(){
            this.highestLandSearch = true;
            return getThis();
        }

        public T canSpawnOnLiquid(){
            this.canPlaceOnLiquid = true;
            return getThis();
        }

        public T setLedgeSpotOffset(int ledgeSpotOffset){
            this.ledgeSpotOffset = ledgeSpotOffset;
            return getThis();
        }

        public T setLiquidSpotOffset(int liquidSpotOffset){
            this.liquidSpotOffset = liquidSpotOffset;
            return getThis();
        }

        public GenericNetherJigsawStructure build() {
            return new GenericNetherJigsawStructure(
                    startPool,
                    structureSize,
                    centerOffset,
                    biomeRange,
                    structureBlacklistRange,
                    avoidStructuresSet,
                    allowTerrainHeightRange,
                    terrainHeightRadius,
                    minHeightLimit,
                    fixedYSpawn,
                    useHeightmap,
                    cannotSpawnInWater,
                    highestLandSearch,
                    canPlaceOnLiquid,
                    ledgeSpotOffset,
                    liquidSpotOffset);
        }
    }
}