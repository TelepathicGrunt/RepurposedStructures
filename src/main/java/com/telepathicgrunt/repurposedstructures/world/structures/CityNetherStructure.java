package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.NoiseAffectingStructureStart;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Set;

public class CityNetherStructure extends GenericJigsawStructure {

    public CityNetherStructure(ResourceLocation poolID, int structureSize, int centerOffset, int biomeRange,
                               int structureBlacklistRange, Set<RSStructureTagMap.STRUCTURE_TAGS> avoidStructuresSet,
                               int allowTerrainHeightRange, int terrainHeightRadius, int minHeightLimit,
                               int fixedYSpawn, boolean useHeightmap, boolean cannotSpawnInWater)
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
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, WorldgenRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration defaultFeatureConfig, LevelHeightAccessor heightLimitView) {

        // do cheaper checks first
        if(super.isFeatureChunk(chunkGenerator, biomeSource, seed, chunkRandom, chunkPos1, biome, chunkPos, defaultFeatureConfig, heightLimitView)){

            // make sure land is open enough for city
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            for (int curChunkX = chunkPos1.x - 1; curChunkX <= chunkPos1.x + 1; curChunkX++) {
                for (int curChunkZ = chunkPos1.z - 1; curChunkZ <= chunkPos1.z + 1; curChunkZ++) {
                    mutable.set(curChunkX << 4, chunkGenerator.getSeaLevel() + 10, curChunkZ << 4);
                    NoiseColumn blockView = chunkGenerator.getBaseColumn(mutable.getX(), mutable.getZ(), heightLimitView);
                    int minValidSpace = 65;
                    int maxHeight = Math.min(chunkGenerator.getGenDepth(), chunkGenerator.getSeaLevel() + minValidSpace);

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
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return CityNetherStructure.MainStart::new;
    }

    public class MainStart extends NoiseAffectingStructureStart<NoneFeatureConfiguration> {
        public MainStart(StructureFeature<NoneFeatureConfiguration> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
        }

        public void generatePieces(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, NoneFeatureConfiguration defaultFeatureConfig, LevelHeightAccessor heightLimitView) {
            BlockPos blockpos = new BlockPos(chunkPos1.getMinBlockX(), chunkGenerator.getSeaLevel(), chunkPos1.getMinBlockZ());
            JigsawPlacement.addPieces(
                    dynamicRegistryManager,
                    new JigsawConfiguration(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(startPool), structureSize),
                    PoolElementStructurePiece::new,
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this,
                    this.random,
                    false,
                    false,
                    heightLimitView);
            this.getBoundingBox();
            this.pieces.get(0).move(0, centerOffset, 0);
        }
    }


    public static class Builder<T extends GenericJigsawStructure.Builder<T>> extends GenericJigsawStructure.Builder<T> {

        public Builder(ResourceLocation startPool) {
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
                    minHeightLimit,
                    fixedYSpawn,
                    useHeightmap,
                    cannotSpawnInWater
            );
        }
    }
}