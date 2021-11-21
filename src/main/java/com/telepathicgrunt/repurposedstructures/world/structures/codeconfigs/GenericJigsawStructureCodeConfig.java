package com.telepathicgrunt.repurposedstructures.world.structures.codeconfigs;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.AbstractBaseStructure;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class GenericJigsawStructureCodeConfig {

    public final ResourceLocation startPool;
    public final int structureSize;
    public final int centerOffset;
    public final int biomeRange;
    public final int structureBlacklistRange;
    public final Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet;
    public final int allowTerrainHeightRange;
    public final int terrainHeightRadius;
    public final int minHeightLimit;
    public final int fixedYSpawn;
    public final boolean useHeightmap;
    public final boolean cannotSpawnInWater;

    public GenericJigsawStructureCodeConfig(ResourceLocation poolID, int structureSize, int centerOffset, int biomeRange,
                                            int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet,
                                            int allowTerrainHeightRange, int terrainHeightRadius, int minHeightLimit,
                                            int fixedYSpawn, boolean useHeightmap, boolean cannotSpawnInWater)
    {
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

        public GenericJigsawStructureCodeConfig build() {
            return new GenericJigsawStructureCodeConfig(
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