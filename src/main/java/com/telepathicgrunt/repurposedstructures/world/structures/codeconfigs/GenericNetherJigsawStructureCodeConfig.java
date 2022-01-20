package com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.Lazy;

import java.util.Set;

public class GenericNetherJigsawStructureCodeConfig extends GenericJigsawStructureCodeConfig {

    public final boolean highestLandSearch;
    public final boolean canPlaceOnLiquid;
    public final int ledgeSpotOffset;
    public final int liquidSpotOffset;

    public GenericNetherJigsawStructureCodeConfig(ResourceLocation startPool, Lazy<Integer> structureSize, int centerOffset, int biomeRange,
                                                  int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet,
                                                  int allowTerrainHeightRange, int terrainHeightRadius, int minHeightLimit, int fixedYSpawn,
                                                  boolean useHeightmap, boolean cannotSpawnInWater, boolean highestLandSearch,
                                                  boolean canPlaceOnLiquid, int ledgeSpotOffset, int liquidSpotOffset,
                                                  Set<ResourceLocation> poolsThatIgnoreBounds)
    {
        super(startPool, structureSize, centerOffset, biomeRange, structureBlacklistRange, avoidStructuresSet, allowTerrainHeightRange, terrainHeightRadius, minHeightLimit, fixedYSpawn, useHeightmap, cannotSpawnInWater, poolsThatIgnoreBounds);
        this.highestLandSearch = highestLandSearch;
        this.canPlaceOnLiquid = canPlaceOnLiquid;
        this.ledgeSpotOffset = ledgeSpotOffset;
        this.liquidSpotOffset = liquidSpotOffset;
    }

    public static class Builder<T extends Builder<T>> extends GenericJigsawStructureCodeConfig.Builder<T> {

        protected  boolean highestLandSearch = false;
        protected boolean canPlaceOnLiquid = false;
        protected int ledgeSpotOffset;
        protected int liquidSpotOffset;

        public Builder(ResourceLocation startPool) {
            super(startPool);
        }

        public T searchForHighestLand() {
            this.highestLandSearch = true;
            return getThis();
        }

        public T canSpawnOnLiquid() {
            this.canPlaceOnLiquid = true;
            return getThis();
        }

        public T setLedgeSpotOffset(int ledgeSpotOffset) {
            this.ledgeSpotOffset = ledgeSpotOffset;
            return getThis();
        }

        public T setLiquidSpotOffset(int liquidSpotOffset) {
            this.liquidSpotOffset = liquidSpotOffset;
            return getThis();
        }

        public GenericNetherJigsawStructureCodeConfig build() {
            return new GenericNetherJigsawStructureCodeConfig(
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
                    liquidSpotOffset,
                    poolsThatIgnoreBounds);
        }
    }
}