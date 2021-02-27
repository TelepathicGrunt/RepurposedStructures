package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.block.BlockState;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.List;
import java.util.Set;

public class CityNetherStructure extends GenericJigsawStructure {
    public CityNetherStructure(Identifier poolID, int structureSize, int centerOffset, int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet, List<SpawnSettings.SpawnEntry> monsterSpawns, List<SpawnSettings.SpawnEntry> creatureSpawns) {
        this(poolID, structureSize, centerOffset, biomeRange, structureBlacklistRange, avoidStructuresSet, -1, 0, monsterSpawns, creatureSpawns);
    }

    public CityNetherStructure(Identifier poolID, int structureSize, int centerOffset, int biomeRange,
                               int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet,
                               int allowTerrainHeightRange, int terrainHeightRadius,
                               List<SpawnSettings.SpawnEntry> monsterSpawns, List<SpawnSettings.SpawnEntry> creatureSpawns)
    {
        super(poolID, structureSize, centerOffset, biomeRange,structureBlacklistRange, avoidStructuresSet, allowTerrainHeightRange, terrainHeightRadius, monsterSpawns, creatureSpawns);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {

        // do cheaper checks first
        if(super.shouldStartAt(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, defaultFeatureConfig)){

            // make sure land is open enough for city
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for (int curChunkX = chunkX - 1; curChunkX <= chunkX + 1; curChunkX++) {
                for (int curChunkZ = chunkZ - 1; curChunkZ <= chunkZ + 1; curChunkZ++) {
                    mutable.set(curChunkX << 4, chunkGenerator.getSeaLevel() + 10, curChunkZ << 4);
                    BlockView blockView = chunkGenerator.getColumnSample(mutable.getX(), mutable.getZ());
                    int minValidSpace = 65;
                    int maxHeight = Math.min(chunkGenerator.getWorldHeight(), chunkGenerator.getSeaLevel() + minValidSpace);

                    while(mutable.getY() < maxHeight){
                        BlockState state = blockView.getBlockState(mutable);
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
        public MainStart(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, chunkGenerator.getSeaLevel(), chunkZ * 16);
            StructurePoolBasedGenerator.method_30419(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN).get(startPool), structureSize),
                    PoolStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.children,
                    this.random,
                    false,
                    false);
            this.setBoundingBoxFromChildren();
            this.children.get(0).translate(0, centerOffset, 0);
        }
    }
}