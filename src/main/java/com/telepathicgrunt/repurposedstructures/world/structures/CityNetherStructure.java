package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Set;

public class CityNetherStructure extends GenericJigsawStructure {
    public CityNetherStructure(ResourceLocation poolID, int structureSize, int centerOffset, int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet, List<MobSpawnInfo.Spawners> monsterSpawns, List<MobSpawnInfo.Spawners> creatureSpawns) {
        this(poolID, structureSize, centerOffset, biomeRange, structureBlacklistRange, avoidStructuresSet, -1, 0, monsterSpawns, creatureSpawns);
    }

    public CityNetherStructure(ResourceLocation poolID, int structureSize, int centerOffset, int biomeRange,
                               int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet,
                               int allowTerrainHeightRange, int terrainHeightRadius,
                               List<MobSpawnInfo.Spawners> monsterSpawns, List<MobSpawnInfo.Spawners> creatureSpawns)
    {
        super(poolID, structureSize, centerOffset, biomeRange,structureBlacklistRange, avoidStructuresSet, allowTerrainHeightRange, terrainHeightRadius, monsterSpawns, creatureSpawns);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig noFeatureConfig) {

        // do cheaper checks first
        if(super.shouldStartAt(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, noFeatureConfig)){

            // make sure land is open enough for city
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for (int curChunkX = chunkX - 1; curChunkX <= chunkX + 1; curChunkX++) {
                for (int curChunkZ = chunkZ - 1; curChunkZ <= chunkZ + 1; curChunkZ++) {
                    mutable.setPos(curChunkX << 4, chunkGenerator.getSeaLevel() + 10, curChunkZ << 4);
                    IBlockReader blockView = chunkGenerator.getColumnSample(mutable.getX(), mutable.getZ());
                    int minValidSpace = 65;
                    int maxHeight = Math.min(chunkGenerator.getMaxY(), chunkGenerator.getSeaLevel() + minValidSpace);

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
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return MainStart::new;
    }

    public class MainStart extends MarginedStructureStart<NoFeatureConfig> {
        public MainStart(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void init(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig defaultFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 32, chunkZ * 16);
            JigsawManager.method_30419(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.get(Registry.TEMPLATE_POOL_WORLDGEN)
                            .getOrDefault(startPool),
                            structureSize),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.components,
                    this.rand,
                    false,
                    false);
            this.recalculateStructureSize();
            this.components.get(0).offset(0, centerOffset, 0);
        }
    }
}