package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.CheckerboardBiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Set;

public class GenericJigsawStructure extends AbstractBaseStructure<DefaultFeatureConfig> {

    protected final Identifier startPool;
    protected final int structureSize;
    protected final int centerOffset;
    protected final int biomeRange;
    protected final int structureBlacklistRange;
    protected final Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet;
    protected final int allowTerrainHeightRange;
    protected final int terrainHeightRadius;
    protected final int minHeightLimit;
    protected int fixedYSpawn = 0;
    protected boolean useHeightmap = true;

    public GenericJigsawStructure(Identifier poolID, int structureSize, int centerOffset, int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet) {
        this(poolID, structureSize, centerOffset, biomeRange, structureBlacklistRange, avoidStructuresSet, -1, 0);
    }

    public GenericJigsawStructure(Identifier poolID, int structureSize, int centerOffset, int biomeRange, int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet, int allowTerrainHeightRange, int terrainHeightRadius) {
        this(poolID, structureSize, centerOffset, biomeRange, structureBlacklistRange, avoidStructuresSet, allowTerrainHeightRange, terrainHeightRadius, -1);
    }

    public GenericJigsawStructure(Identifier poolID, int biomeRange, int structureSize, int structureBlacklistRange,
                                  Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet, int fixedHeight)
    {
        this(poolID, structureSize, 0, biomeRange, structureBlacklistRange, avoidStructuresSet, -1, 0, Integer.MIN_VALUE);
        this.fixedYSpawn = fixedHeight;
        this.useHeightmap = false;
    }

    public GenericJigsawStructure(Identifier poolID, int structureSize, int centerOffset, int biomeRange,
                                  int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet,
                                  int allowTerrainHeightRange, int terrainHeightRadius,
                                  int minHeightLimit)
    {
        super(DefaultFeatureConfig.CODEC);

        this.startPool = poolID;
        this.structureSize = structureSize;
        this.centerOffset = centerOffset;
        this.biomeRange = biomeRange;
        this.structureBlacklistRange = structureBlacklistRange;
        this.avoidStructuresSet = avoidStructuresSet;
        this.allowTerrainHeightRange = allowTerrainHeightRange;
        this.terrainHeightRadius = terrainHeightRadius;
        this.minHeightLimit = minHeightLimit;

        RSStructures.RS_STRUCTURE_START_PIECES.add(startPool);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
        if(!(biomeSource instanceof CheckerboardBiomeSource)) {
            for (int curChunkX = chunkPos1.x - biomeRange; curChunkX <= chunkPos1.x + biomeRange; curChunkX++) {
                for (int curChunkZ = chunkPos1.z - biomeRange; curChunkZ <= chunkPos1.z + biomeRange; curChunkZ++) {
                    if (!biomeSource.getBiomeForNoiseGen(curChunkX << 2, 64, curChunkZ << 2).getGenerationSettings().hasStructureFeature(this)) {
                        return false;
                    }
                }
            }
        }

        //cannot be near other specified structure
        for (int curChunkX = chunkPos1.x - structureBlacklistRange; curChunkX <= chunkPos1.x + structureBlacklistRange; curChunkX++) {
            for (int curChunkZ = chunkPos1.z - structureBlacklistRange; curChunkZ <= chunkPos1.z + structureBlacklistRange; curChunkZ++) {
                if(curChunkX == chunkPos1.x && curChunkZ == chunkPos1.z) continue; // Prevent detecting the structure itself and thus, never spawning if structure is in its own blacklist

                for(RSStructureTagMap.STRUCTURE_TAGS tag : avoidStructuresSet){
                    for(StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag)){
                        StructureConfig structureConfig = chunkGenerator.getStructuresConfig().getForType(structureFeature);
                        if(structureConfig != null && structureConfig.getSpacing() > 8){
                            ChunkPos chunkPos2 = structureFeature.getStartChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
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

            for (int curChunkX = chunkPos1.x - terrainHeightRadius; curChunkX <= chunkPos1.x + terrainHeightRadius; curChunkX++) {
                for (int curChunkZ = chunkPos1.z - terrainHeightRadius; curChunkZ <= chunkPos1.z + terrainHeightRadius; curChunkZ++) {
                    int height = chunkGenerator.getHeight((curChunkX << 4) + 7, (curChunkZ << 4) + 7, Heightmap.Type.WORLD_SURFACE_WG, heightLimitView);
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
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return GenericJigsawStructure.MainStart::new;
    }

    public class MainStart extends MarginedStructureStart<DefaultFeatureConfig> {
        public MainStart(StructureFeature<DefaultFeatureConfig> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView) {
            BlockPos blockpos = new BlockPos(chunkPos1.getStartX(), fixedYSpawn, chunkPos1.getStartZ());
            StructurePoolBasedGenerator.method_30419(
                    dynamicRegistryManager,
                    new StructurePoolFeatureConfig(() -> dynamicRegistryManager.get(Registry.STRUCTURE_POOL_KEY).get(startPool), structureSize),
                    PoolStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this,
                    this.random,
                    useHeightmap,
                    useHeightmap,
                    heightLimitView);
            this.setBoundingBoxFromChildren();
            this.children.get(0).translate(0, centerOffset, 0);
        }
    }
}