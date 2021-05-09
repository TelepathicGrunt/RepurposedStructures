package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.CheckerboardBiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.world.gen.feature.structure.Structure.IStartFactory;

public class GenericJigsawStructure extends AbstractBaseStructure<NoFeatureConfig> {
    protected final ResourceLocation startPool;
    protected final int structureSize;
    protected final int centerOffset;
    protected final int biomeRange;
    protected final int structureBlacklistRange;
    protected final Set<RSStructureTagMap.STRUCTURE_TAGS> structureTagsSet;
    protected final List<MobSpawnInfo.Spawners> monsterSpawns;
    protected final List<MobSpawnInfo.Spawners> creatureSpawns;
    protected final int allowTerrainHeightRange;
    protected final int terrainHeightRadius;
    protected final int minHeightLimit;

    public GenericJigsawStructure(ResourceLocation poolRL, int structureSize, int centerOffset, int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet) {
        this(poolRL, structureSize, centerOffset, biomeRange, structureBlacklistRange, avoidStructuresSet, -1, 0);
    }

    public GenericJigsawStructure(ResourceLocation poolRL, int structureSize, int centerOffset, int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet, int allowTerrainHeightRange, int terrainHeightRadius) {
        this(poolRL, structureSize, centerOffset, biomeRange, structureBlacklistRange, avoidStructuresSet, allowTerrainHeightRange, terrainHeightRadius, new ArrayList<>(), new ArrayList<>());
    }

    public GenericJigsawStructure(ResourceLocation poolID, int structureSize, int centerOffset, int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet, List<MobSpawnInfo.Spawners> monsterSpawns, List<MobSpawnInfo.Spawners> creatureSpawns) {
        this(poolID, structureSize, centerOffset, biomeRange, structureBlacklistRange, avoidStructuresSet, -1, 0, monsterSpawns, creatureSpawns);
    }

    public GenericJigsawStructure(ResourceLocation poolID, int structureSize, int centerOffset,
                                  int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet,
                                  int allowTerrainHeightRange, int terrainHeightRadius,
                                  List<MobSpawnInfo.Spawners> monsterSpawns, List<MobSpawnInfo.Spawners> creatureSpawns)
    {
        this(poolID, structureSize, centerOffset, biomeRange, structureBlacklistRange, avoidStructuresSet, allowTerrainHeightRange, terrainHeightRadius, monsterSpawns, creatureSpawns, Integer.MIN_VALUE);
    }
    public GenericJigsawStructure(ResourceLocation poolID, int structureSize, int centerOffset,
                                  int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet,
                                  int allowTerrainHeightRange, int terrainHeightRadius,
                                  List<MobSpawnInfo.Spawners> monsterSpawns, List<MobSpawnInfo.Spawners> creatureSpawns,
                                  int minHeightLimit)
    {
        super(NoFeatureConfig.CODEC);

        this.startPool = poolID;
        this.structureSize = structureSize;
        this.centerOffset = centerOffset;
        this.biomeRange = biomeRange;
        this.structureBlacklistRange = structureBlacklistRange;
        this.structureTagsSet = avoidStructuresSet;
        this.monsterSpawns = monsterSpawns;
        this.creatureSpawns = creatureSpawns;
        this.allowTerrainHeightRange = allowTerrainHeightRange;
        this.terrainHeightRadius = terrainHeightRadius;
        this.minHeightLimit = minHeightLimit;

        RSStructures.RS_STRUCTURE_START_PIECES.add(startPool);
    }

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return monsterSpawns;
    }

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList() {
        return creatureSpawns;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig noFeatureConfig) {
        if(!(biomeSource instanceof CheckerboardBiomeProvider)) {
            for (int curChunkX = chunkX - biomeRange; curChunkX <= chunkX + biomeRange; curChunkX++) {
                for (int curChunkZ = chunkZ - biomeRange; curChunkZ <= chunkZ + biomeRange; curChunkZ++) {
                    if (!biomeSource.getNoiseBiome(curChunkX << 2, 60, curChunkZ << 2).getGenerationSettings().isValidStart(this)) {
                        return false;
                    }
                }
            }
        }

        //cannot be near other specified structure
        for (int curChunkX = chunkX - structureBlacklistRange; curChunkX <= chunkX + structureBlacklistRange; curChunkX++) {
            for (int curChunkZ = chunkZ - structureBlacklistRange; curChunkZ <= chunkZ + structureBlacklistRange; curChunkZ++) {
                if(curChunkX == chunkX && curChunkZ == chunkZ) continue; // Prevent detecting the structure itself and thus, never spawning if structure is in its own blacklist

                for(RSStructureTagMap.STRUCTURE_TAGS tag : structureTagsSet){
                    for(Structure<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag)){
                        StructureSeparationSettings structureConfig = chunkGenerator.getSettings().getConfig(structureFeature);
                        if(structureConfig != null && structureConfig.spacing() > 8){
                            ChunkPos chunkPos2 = structureFeature.getPotentialFeatureChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
                            if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        if(allowTerrainHeightRange != -1){
            int maxTerrainHeight = Integer.MIN_VALUE;
            int minTerrainHeight = Integer.MAX_VALUE;

            for (int curChunkX = chunkX - terrainHeightRadius; curChunkX <= chunkX + terrainHeightRadius; curChunkX++) {
                for (int curChunkZ = chunkZ - terrainHeightRadius; curChunkZ <= chunkZ + terrainHeightRadius; curChunkZ++) {
                    int height = chunkGenerator.getFirstOccupiedHeight((curChunkX << 4) + 7, (curChunkZ << 4) + 7, Heightmap.Type.WORLD_SURFACE_WG);
                    maxTerrainHeight = Math.max(maxTerrainHeight, height);
                    minTerrainHeight = Math.min(minTerrainHeight, height);

                    if(minTerrainHeight < this.minHeightLimit){
                        return false;
                    }
                }
            }

            return maxTerrainHeight - minTerrainHeight <= allowTerrainHeightRange;
        }

        return true;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return GenericJigsawStructure.MainStart::new;
    }

    public class MainStart extends MarginedStructureStart<NoFeatureConfig> {
        public MainStart(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig NoFeatureConfig) {

            BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            JigsawManager.addPieces(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)
                            .get(startPool),
                            structureSize),
                    AbstractVillagePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.pieces,
                    this.random,
                    true,
                    true);
            this.calculateBoundingBox();
            this.pieces.get(0).move(0, centerOffset, 0);
        }
    }
}