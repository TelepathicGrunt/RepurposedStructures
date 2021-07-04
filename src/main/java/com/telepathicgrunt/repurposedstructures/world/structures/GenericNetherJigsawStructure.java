package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Set;

public class GenericNetherJigsawStructure extends GenericJigsawStructure {

    protected final boolean highestLandSearch;
    protected final boolean canPlaceOnLiquid;
    protected final int ledgeSpotOffset;
    protected final int liquidSpotOffset;

    public GenericNetherJigsawStructure(Identifier poolID, int structureSize, int centerOffset, int biomeRange,
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
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return GenericNetherJigsawStructure.Start::new;
    }

    public class Start extends MainStart {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            super.init(dynamicRegistryManager, chunkGenerator, structureManager, chunkPos1, biome, defaultFeatureConfig, heightLimitView);

            BlockPos placementPos;
            if(highestLandSearch){
                placementPos = GeneralUtils.getHighestLand(chunkGenerator, this.calculateBoundingBox(), heightLimitView, canPlaceOnLiquid);
            }
            else{
                placementPos = GeneralUtils.getLowestLand(chunkGenerator, this.calculateBoundingBox(), heightLimitView, canPlaceOnLiquid);
            }

            if (placementPos.getY() >= chunkGenerator.getWorldHeight() || placementPos.getY() <= chunkGenerator.getSeaLevel() + 1) {
                this.randomUpwardTranslation(this.random, chunkGenerator.getSeaLevel() + ledgeSpotOffset, chunkGenerator.getSeaLevel() + (ledgeSpotOffset + 1));
            }
            else {
                this.randomUpwardTranslation(this.random, placementPos.getY() + liquidSpotOffset, placementPos.getY() + (liquidSpotOffset + 1));
            }
        }
    }

    public static class Builder<T extends GenericNetherJigsawStructure.Builder<?>> extends GenericJigsawStructure.Builder<T> {
        protected boolean highestLandSearch = false;
        protected boolean canPlaceOnLiquid = false;
        protected int ledgeSpotOffset;
        protected int liquidSpotOffset;

        public Builder(Identifier startPool) {
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