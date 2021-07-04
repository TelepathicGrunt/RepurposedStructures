package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.block.BlockState;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.CheckerboardBiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO: Turn GenericJigsawStructure and other classes of it into a builder system.
 * I bet that would be cleaner and more readable.
 */
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
    protected int fixedYSpawn;
    protected boolean useHeightmap;
    protected boolean cannotSpawnInWater;

    public GenericJigsawStructure(Identifier poolID, int structureSize, int centerOffset, int biomeRange,
                                  int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet,
                                  int allowTerrainHeightRange, int terrainHeightRadius, int minHeightLimit,
                                  int fixedYSpawn, boolean useHeightmap, boolean cannotSpawnInWater)
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
        this.fixedYSpawn = fixedYSpawn;
        this.useHeightmap = useHeightmap;
        this.cannotSpawnInWater = cannotSpawnInWater;

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

        if(cannotSpawnInWater){
            BlockPos centerOfChunk = chunkPos.getCenterAtY(0);
            int landHeight = chunkGenerator.getHeightInGround(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG, heightLimitView);
            VerticalBlockSample columnOfBlocks = chunkGenerator.getColumnSample(centerOfChunk.getX(), centerOfChunk.getZ(), heightLimitView);
            BlockState topBlock = columnOfBlocks.getState(centerOfChunk.up(landHeight));
            return topBlock.getFluidState().isEmpty();
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

    public static class Builder<T extends Builder<T>> {
        protected final Identifier startPool;
        protected int structureSize = 1;
        protected int centerOffset = 0;
        protected int biomeRange = 0;
        protected int structureBlacklistRange = 0;
        protected Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet = new HashSet<>();
        protected int allowTerrainHeightRange = -1;
        protected int terrainHeightRadius = 0;
        protected int minHeightLimit = Integer.MIN_VALUE;
        protected int fixedYSpawn = 0;
        protected boolean useHeightmap = true;
        protected boolean cannotSpawnInWater = false;

        public Builder(Identifier startPool) {
            this.startPool = startPool;
        }

        @SuppressWarnings("unchecked")
        protected T getThis() {
            return (T) this;
        }

        public T setStructureSize(int structureSize){
            this.structureSize = structureSize;
            return getThis();
        }

        public T setCenterOffset(int centerOffset){
            this.centerOffset = centerOffset;
            return getThis();
        }

        public T setBiomeRange(int biomeRange){
            this.biomeRange = biomeRange;
            return getThis();
        }

        public T setStructureBlacklistRange(int structureBlacklistRange){
            this.structureBlacklistRange = structureBlacklistRange;
            return getThis();
        }

        public T setAvoidStructuresSet(Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet){
            this.avoidStructuresSet = avoidStructuresSet;
            return getThis();
        }

        public T setAllowTerrainHeightRange(int allowTerrainHeightRange){
            this.allowTerrainHeightRange = allowTerrainHeightRange;
            return getThis();
        }

        public T setTerrainHeightRadius(int terrainHeightRadius){
            this.terrainHeightRadius = terrainHeightRadius;
            return getThis();
        }

        public T setMinHeightLimit(int minHeightLimit){
            this.minHeightLimit = minHeightLimit;
            return getThis();
        }

        public T setFixedYSpawn(int fixedYSpawn){
            this.fixedYSpawn = fixedYSpawn;
            return getThis();
        }

        public T doNotUseHeightmap(){
            this.useHeightmap = false;
            return getThis();
        }

        public T cannotSpawnInWater(){
            this.cannotSpawnInWater = true;
            return getThis();
        }

        public GenericJigsawStructure build() {
            return new GenericJigsawStructure(
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
                    cannotSpawnInWater
            );
        }
    }
}