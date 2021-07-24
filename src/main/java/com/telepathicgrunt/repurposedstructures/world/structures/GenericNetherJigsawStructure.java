package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Set;

public class GenericNetherJigsawStructure extends GenericJigsawStructure {

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
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    public class Start extends MainStart {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox box, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, box, referenceIn, seedIn);
        }

        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig defaultFeatureConfig) {
            super.generatePieces(dynamicRegistryManager, chunkGenerator, structureManager, chunkX, chunkZ, biome, defaultFeatureConfig);

            BlockPos placementPos;
            if(highestLandSearch){
                placementPos = GeneralUtils.getHighestLand(chunkGenerator, this.getBoundingBox(), canPlaceOnLiquid);
            }
            else{
                placementPos = GeneralUtils.getLowestLand(chunkGenerator, this.getBoundingBox(), canPlaceOnLiquid);
            }

            if (placementPos.getY() >= chunkGenerator.getGenDepth() || placementPos.getY() <= chunkGenerator.getSeaLevel() + 1) {
                this.moveInsideHeights(this.random, chunkGenerator.getSeaLevel() + ledgeSpotOffset, chunkGenerator.getSeaLevel() + (ledgeSpotOffset + 1));
            }
            else {
                this.moveInsideHeights(this.random, placementPos.getY() + liquidSpotOffset, placementPos.getY() + (liquidSpotOffset + 1));
            }
        }
    }

    public static class Builder<T extends Builder<T>> extends GenericJigsawStructure.Builder<T> {
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