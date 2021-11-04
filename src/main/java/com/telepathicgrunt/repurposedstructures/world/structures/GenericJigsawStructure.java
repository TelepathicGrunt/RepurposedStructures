package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.misc.BiomeSourceChecks;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.util.Lazy;

import java.util.HashSet;
import java.util.Set;

public class GenericJigsawStructure extends AbstractBaseStructure<NoFeatureConfig> {

    protected final ResourceLocation startPool;
    protected final Lazy<Integer> structureSize;
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

    public GenericJigsawStructure(ResourceLocation poolID, Lazy<Integer> structureSize, int centerOffset, int biomeRange,
                                  int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet,
                                  int allowTerrainHeightRange, int terrainHeightRadius, int minHeightLimit,
                                  int fixedYSpawn, boolean useHeightmap, boolean cannotSpawnInWater)
    {
        super(NoFeatureConfig.CODEC);

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
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig defaultFeatureConfig) {
        if(!BiomeSourceChecks.isCheckeredBiomeSource(biomeSource) && !BiomeSourceChecks.isHexlandBiomeSource(biomeSource)) {
            for (int curChunkX = chunkX - biomeRange; curChunkX <= chunkX + biomeRange; curChunkX++) {
                for (int curChunkZ = chunkZ - biomeRange; curChunkZ <= chunkZ + biomeRange; curChunkZ++) {
                    if (!biomeSource.getNoiseBiome(curChunkX << 2, 64, curChunkZ << 2).getGenerationSettings().isValidStart(this)) {
                        return false;
                    }
                }
            }
        }

        //cannot be near other specified structure
        for (int curChunkX = chunkX - structureBlacklistRange; curChunkX <= chunkX + structureBlacklistRange; curChunkX++) {
            for (int curChunkZ = chunkZ - structureBlacklistRange; curChunkZ <= chunkZ + structureBlacklistRange; curChunkZ++) {
                for(RSStructureTagMap.STRUCTURE_TAGS tag : avoidStructuresSet){
                    for(Structure<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag)){
                        if(structureFeature == this) continue;

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
                    int height = chunkGenerator.getBaseHeight(curChunkX * 16, curChunkZ * 16, Heightmap.Type.WORLD_SURFACE_WG);
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
            BlockPos centerOfChunk = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            int landHeight = chunkGenerator.getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
            IBlockReader columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ());
            BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.above(landHeight));
            return topBlock.getFluidState().isEmpty();
        }

        return true;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return MainStart::new;
    }

    public class MainStart extends MarginedStructureStart<NoFeatureConfig> {
        public MainStart(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox box, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, box, referenceIn, seedIn);
        }

        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig defaultFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, fixedYSpawn, chunkZ * 16);

            PieceLimitedJigsawManager.assembleJigsawStructure(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(startPool), structureSize.get()),
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.pieces,
                    this.random,
                    useHeightmap,
                    useHeightmap,
                    null,
                    Integer.MAX_VALUE,
                    Integer.MIN_VALUE);

            GeneralUtils.centerAllPieces(blockpos, this.pieces);
            this.calculateBoundingBox();
            this.pieces.get(0).move(0, centerOffset, 0);
        }
    }

    public static class Builder<T extends Builder<T>> {
        protected final ResourceLocation startPool;
        protected Lazy<Integer> structureSize = Lazy.of(() -> 1);
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

        public Builder(ResourceLocation startPool) {
            this.startPool = startPool;
        }

        @SuppressWarnings("unchecked")
        protected T getThis() {
            return (T) this;
        }

        public T setStructureSize(ForgeConfigSpec.IntValue structureSize){
            this.structureSize = Lazy.of(structureSize::get);
            return getThis();
        }

        public T setStructureSize(int structureSize){
            this.structureSize = Lazy.of(() -> structureSize);
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