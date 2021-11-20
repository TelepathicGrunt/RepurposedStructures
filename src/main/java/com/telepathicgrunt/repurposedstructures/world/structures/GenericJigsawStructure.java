package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.CheckerboardColumnBiomeSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.NoiseAffectingStructureStart;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class GenericJigsawStructure extends AbstractBaseStructure<NoneFeatureConfiguration> {

    protected final ResourceLocation startPool;
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

    public GenericJigsawStructure(ResourceLocation poolID, int structureSize, int centerOffset, int biomeRange,
                                  int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet,
                                  int allowTerrainHeightRange, int terrainHeightRadius, int minHeightLimit,
                                  int fixedYSpawn, boolean useHeightmap, boolean cannotSpawnInWater)
    {
        super(NoneFeatureConfiguration.CODEC,
                (context) -> this.isFeatureChunk(context.chunkGenerator(), context.biomeSource(), context.seed(), context.chunkPos(), context.validBiome(), context.heightAccessor()),
                (context) -> this.generatePieces(context.registryAccess(), context.chunkGenerator(), context.biomeSource(), context.structureManager(), context.seed(), context.chunkPos(), context.validBiome(), context.heightAccessor())
        );

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

    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkPos chunkPos, Biome biome, LevelHeightAccessor heightLimitView) {

        WorldgenRandom chunkRandom = new WorldgenRandom(new LegacyRandomSource(0L));

        if(!(biomeSource instanceof CheckerboardColumnBiomeSource)) {
            for (int curChunkX = chunkPos.x - biomeRange; curChunkX <= chunkPos.x + biomeRange; curChunkX++) {
                for (int curChunkZ = chunkPos.z - biomeRange; curChunkZ <= chunkPos.z + biomeRange; curChunkZ++) {
                    if (!biomeSource.getNoiseBiome(curChunkX << 2, 64, curChunkZ << 2).getGenerationSettings().isValidStart(this)) {
                        return false;
                    }
                }
            }
        }

        //cannot be near other specified structure
        for (int curChunkX = chunkPos.x - structureBlacklistRange; curChunkX <= chunkPos.x + structureBlacklistRange; curChunkX++) {
            for (int curChunkZ = chunkPos.z - structureBlacklistRange; curChunkZ <= chunkPos.z + structureBlacklistRange; curChunkZ++) {
                for(RSStructureTagMap.STRUCTURE_TAGS tag : avoidStructuresSet){
                    for(StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(tag)){
                        if(structureFeature == this) continue;

                        StructureFeatureConfiguration structureConfig = chunkGenerator.getSettings().getConfig(structureFeature);
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

            for (int curChunkX = chunkPos.x - terrainHeightRadius; curChunkX <= chunkPos.x + terrainHeightRadius; curChunkX++) {
                for (int curChunkZ = chunkPos.z - terrainHeightRadius; curChunkZ <= chunkPos.z + terrainHeightRadius; curChunkZ++) {
                    int height = chunkGenerator.getBaseHeight((curChunkX << 4) + 7, (curChunkZ << 4) + 7, Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
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
            BlockPos centerOfChunk = chunkPos.getMiddleBlockPosition(0);
            int landHeight = chunkGenerator.getFirstOccupiedHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightLimitView);
            NoiseColumn columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ(), heightLimitView);
            BlockState topBlock = columnOfBlocks.getBlock(centerOfChunk.getY() + landHeight);
            return topBlock.getFluidState().isEmpty();
        }

        return true;
    }

    public Optional<PieceGenerator<?>> generatePieces(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos, Predicate<Biome> biomePredicate, LevelHeightAccessor heightLimitView) {
        BlockPos blockpos = new BlockPos(chunkPos.getMinBlockX(), fixedYSpawn, chunkPos.getMinBlockZ());

        if (!biomePredicate.test(chunkGenerator.getNoiseBiome(QuartPos.fromBlock($$18), QuartPos.fromBlock($$21), QuartPos.fromBlock($$19)))) {
            return Optional.empty();
        }
        else {
            ResourceLocation structureID = Registry.STRUCTURE_FEATURE.getKey(this);
            PieceLimitedJigsawManager.assembleJigsawStructure(
                    dynamicRegistryManager,
                    new JigsawConfiguration(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(startPool), structureSize),
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.pieces,
                    useHeightmap,
                    useHeightmap,
                    heightLimitView,
                    structureID,
                    Integer.MAX_VALUE,
                    Integer.MIN_VALUE);
        }
    }

    public static class Builder<T extends Builder<T>> {
        protected final ResourceLocation startPool;
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

        public Builder(ResourceLocation startPool) {
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