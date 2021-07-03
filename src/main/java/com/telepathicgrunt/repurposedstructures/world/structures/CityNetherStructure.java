package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.block.BlockState;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Set;

public class CityNetherStructure extends GenericJigsawStructure {

    public CityNetherStructure(Identifier poolID, int structureSize, int centerOffset, int biomeRange,
                                  int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet,
                                  int allowTerrainHeightRange, int terrainHeightRadius,
                                  int minHeightLimit)
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
                minHeightLimit
        );
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {

        // do cheaper checks first
        if(super.shouldStartAt(chunkGenerator, biomeSource, seed, chunkRandom, chunkPos1, biome, chunkPos, defaultFeatureConfig, heightLimitView)){

            // make sure land is open enough for city
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for (int curChunkX = chunkPos1.x - 1; curChunkX <= chunkPos1.x + 1; curChunkX++) {
                for (int curChunkZ = chunkPos1.z - 1; curChunkZ <= chunkPos1.z + 1; curChunkZ++) {
                    mutable.set(curChunkX << 4, chunkGenerator.getSeaLevel() + 10, curChunkZ << 4);
                    VerticalBlockSample blockView = chunkGenerator.getColumnSample(mutable.getX(), mutable.getZ(), heightLimitView);
                    int minValidSpace = 65;
                    int maxHeight = Math.min(chunkGenerator.getWorldHeight(), chunkGenerator.getSeaLevel() + minValidSpace);

                    while(mutable.getY() < maxHeight){
                        BlockState state = blockView.getState(mutable);
                        if(!state.isAir()){
                            return false;
                        }
                        mutable.move(Direction.UP);
                    }
                }
            }
        }
        else {
            return false;
        }

        return true;
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return CityNetherStructure.MainStart::new;
    }

    public class MainStart extends MarginedStructureStart<DefaultFeatureConfig> {
        public MainStart(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            BlockPos blockpos = new BlockPos(chunkPos1.getStartX(), chunkGenerator.getSeaLevel(), chunkPos1.getStartZ());
            StructurePoolBasedGenerator.method_30419(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.STRUCTURE_POOL_KEY).get(startPool), structureSize),
                    PoolStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this,
                    this.random,
                    false,
                    false,
                    heightLimitView);
            this.setBoundingBoxFromChildren();
            this.children.get(0).translate(0, centerOffset, 0);
        }
    }


    public static class Builder<T extends GenericJigsawStructure.Builder<?>> extends GenericJigsawStructure.Builder<T> {

        public Builder(Identifier startPool) {
            super(startPool);
        }

        public CityNetherStructure build() {
            return new CityNetherStructure(
                    startPool,
                    structureSize,
                    centerOffset,
                    biomeRange,
                    structureBlacklistRange,
                    avoidStructuresSet,
                    allowTerrainHeightRange,
                    terrainHeightRadius,
                    minHeightLimit
            );
        }
    }
}