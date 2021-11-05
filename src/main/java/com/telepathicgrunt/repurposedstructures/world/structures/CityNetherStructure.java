package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
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
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.common.util.Lazy;

import java.util.Set;

public class CityNetherStructure extends GenericJigsawStructure {

    public CityNetherStructure(ResourceLocation poolID, Lazy<Integer> structureSize, int centerOffset, int biomeRange,
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
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig defaultFeatureConfig) {

        // do cheaper checks first
        if(super.isFeatureChunk(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, defaultFeatureConfig)){

            // make sure land is open enough for city
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for (int curChunkX = chunkX - 1; curChunkX <= chunkX + 1; curChunkX++) {
                for (int curChunkZ = chunkZ - 1; curChunkZ <= chunkZ + 1; curChunkZ++) {
                    mutable.set(curChunkX * 16, chunkGenerator.getSeaLevel() + 10, curChunkZ * 16);
                    IBlockReader blockView = chunkGenerator.getBaseColumn(mutable.getX(), mutable.getZ());
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
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return MainStart::new;
    }

    public class MainStart extends MarginedStructureStart<NoFeatureConfig> {
        private final ResourceLocation structureID;

        public MainStart(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox box, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, box, referenceIn, seedIn);
            structureID = Registry.STRUCTURE_FEATURE.getKey(structureIn);
        }

        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig defaultFeatureConfig) {
            BlockPos blockpos = new BlockPos(chunkX * 16, chunkGenerator.getSeaLevel(), chunkZ * 16);
            PieceLimitedJigsawManager.assembleJigsawStructure(
                    dynamicRegistryManager,
                    new VillageConfig(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(startPool), structureSize.get()),
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.pieces,
                    this.random,
                    false,
                    false,
                    structureID,
                    Integer.MAX_VALUE,
                    Integer.MIN_VALUE);

            this.calculateBoundingBox();
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