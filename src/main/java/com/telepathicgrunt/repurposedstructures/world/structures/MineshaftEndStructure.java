package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3f;
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
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.common.util.Lazy;


public class MineshaftEndStructure extends MineshaftStructure {

    public MineshaftEndStructure(ResourceLocation poolID, Lazy<Integer> structureSize, int biomeRange,
                                 Lazy<Integer> maxY, Lazy<Integer> minY, boolean clipOutOfBoundsPieces,
                                 Lazy<Integer> verticalRange, Lazy<Double> probability)
    {
        super(poolID, structureSize, biomeRange, maxY, minY, clipOutOfBoundsPieces, verticalRange, probability);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        boolean superCheck = super.isFeatureChunk(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, featureConfig);
        if(!superCheck)
            return false;

        //cannot be near end strongholds
        int structureCheckRadius = 6;
        for (int curChunkX = chunkX - structureCheckRadius; curChunkX <= chunkZ + structureCheckRadius; curChunkX++) {
            for (int curChunkZ = chunkX - structureCheckRadius; curChunkZ <= chunkZ + structureCheckRadius; curChunkZ++) {
                for(Structure<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.END_MINESHAFT_AVOID_STRUCTURE)){
                    if(structureFeature == this) continue;

                    StructureSeparationSettings structureConfig = chunkGenerator.getSettings().getConfig(structureFeature);
                    if(structureConfig != null && structureConfig.spacing() > 10){
                        ChunkPos chunkPos2 = structureFeature.getPotentialFeatureChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                }
            }
        }

        int minThickness = RepurposedStructures.RSMineshaftsConfig.endMineshaftMinIslandThickness.get();
        if(minThickness == 0)
            return true;

        BlockPos.Mutable islandTopBottomThickness = new BlockPos.Mutable(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        int xPos = chunkX * 16;
        int zPos = chunkZ * 16;

        // Surrounding far terrain is more likely to fail the check and exit early.
        for(int i = 2; i >= 1; i--) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                Vector3f offsetPos = new Vector3f(direction.getStepX(), 0, direction.getStepZ());
                offsetPos = new Vector3f(offsetPos.x() * 30f * i, 0, offsetPos.z() * 30f * i);
                analyzeLand(chunkGenerator, xPos + (int) offsetPos.x(), zPos + (int) offsetPos.z(), islandTopBottomThickness);
                if (islandTopBottomThickness.getZ() < minThickness) return false;
            }
        }

        analyzeLand(chunkGenerator, xPos, zPos, islandTopBottomThickness);
        return islandTopBottomThickness.getZ() >= minThickness;
    }

    private void analyzeLand(ChunkGenerator chunkGenerator, int xPos, int zPos, BlockPos.Mutable islandTopBottomThickness) {
        IBlockReader columnOfBlocks = chunkGenerator.getBaseColumn(xPos, zPos);
        BlockPos.Mutable currentPos = new BlockPos.Mutable(xPos, chunkGenerator.getGenDepth(), zPos);
        boolean isInIsland = false;

        while(currentPos.getY() >= -1){
            BlockState state = columnOfBlocks.getBlockState(currentPos);

            // Detects top of island
            if(!state.isAir() && !isInIsland){
                isInIsland = true;
                int topIslandY = Math.min(currentPos.getY(), islandTopBottomThickness.getX());
                islandTopBottomThickness.set(topIslandY, islandTopBottomThickness.getY(), islandTopBottomThickness.getZ());
            }

            // Detects bottom of island
            else if(state.isAir() && isInIsland){
                int bottomIslandY = Math.max(currentPos.getY(), islandTopBottomThickness.getY());
                islandTopBottomThickness.set(islandTopBottomThickness.getX(), bottomIslandY, islandTopBottomThickness.getZ());
                break;
            }

            currentPos.move(Direction.DOWN);
        }

        // Never hit land since isInIsland was never set to true for terrain top.
        if(!isInIsland){
            islandTopBottomThickness.set(0, 0, 0);
        }

        int thickness = islandTopBottomThickness.getX() - islandTopBottomThickness.getY();
        islandTopBottomThickness.set(islandTopBottomThickness.getX(), islandTopBottomThickness.getY(), thickness);
    }


    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return MineshaftEndStructure.MainStart::new;
    }

    public class MainStart extends MarginedStructureStart<NoFeatureConfig> {

        private final ResourceLocation structureID;

        public MainStart(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox box, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, box, referenceIn, seedIn);
            structureID = Registry.STRUCTURE_FEATURE.getKey(structureIn);
        }

        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager structureManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig defaultFeatureConfig) {
            BlockPos.Mutable blockpos = new BlockPos.Mutable(chunkX * 16, 0, chunkZ * 16);
            BlockPos.Mutable islandTopBottomThickness = new BlockPos.Mutable(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            analyzeLand(chunkGenerator, blockpos.getX(), blockpos.getZ(), islandTopBottomThickness);

            int minThickness = RepurposedStructures.RSMineshaftsConfig.endMineshaftMinIslandThickness.get();
            int maxY = 53;
            int minY = 15;
            if(minThickness == 0){
                blockpos.move(Direction.UP, 35);
            }
            else{
                int structureStartHeight = random.nextInt((islandTopBottomThickness.getZ() - minThickness) + 1) + islandTopBottomThickness.getY() + (minThickness / 2);
                blockpos.move(Direction.UP, structureStartHeight);
                maxY = islandTopBottomThickness.getX() - 5;
                minY = islandTopBottomThickness.getY();
                if(maxY - minY <= 5){
                    minY = maxY - 5;
                }
            }

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
                    maxY,
                    minY);

            this.calculateBoundingBox();
        }
    }


    public static class Builder<T extends Builder<T>> extends MineshaftStructure.Builder<T> {

        public Builder(ResourceLocation startPool) {
            super(startPool);
        }

        public MineshaftEndStructure build() {
            return new MineshaftEndStructure(
                    startPool,
                    structureSize,
                    biomeRange,
                    maxY,
                    minY,
                    clipOutOfBoundsPieces,
                    verticalRange,
                    probability);
        }
    }
}
