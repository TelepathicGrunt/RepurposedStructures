package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.math.Vector3f;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.PieceLimitedJigsawManager;
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
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.NoiseAffectingStructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;


public class MineshaftEndStructure extends MineshaftStructure {

    public MineshaftEndStructure(ResourceLocation poolID, int structureSize, int biomeRange,
                                 int maxY, int minY, boolean clipOutOfBoundsPieces,
                                 Integer verticalRange, double probability)
    {
        super(poolID, structureSize, biomeRange, maxY, minY, clipOutOfBoundsPieces, verticalRange, probability);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, WorldgenRandom chunkRandom, ChunkPos chunkPos1, Biome biome, ChunkPos chunkPos, NoneFeatureConfiguration featureConfig, LevelHeightAccessor heightLimitView) {
        boolean superCheck = super.isFeatureChunk(chunkGenerator, biomeSource, seed, chunkRandom, chunkPos1, biome, chunkPos, featureConfig, heightLimitView);
        if(!superCheck)
            return false;

        //cannot be near end strongholds
        int structureCheckRadius = 6;
        for (int curChunkX = chunkPos1.x - structureCheckRadius; curChunkX <= chunkPos1.x + structureCheckRadius; curChunkX++) {
            for (int curChunkZ = chunkPos1.z - structureCheckRadius; curChunkZ <= chunkPos1.z + structureCheckRadius; curChunkZ++) {
                for(StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.END_MINESHAFT_AVOID_STRUCTURE)){
                    if(structureFeature == this) continue;

                    StructureFeatureConfiguration structureConfig = chunkGenerator.getSettings().getConfig(structureFeature);
                    if(structureConfig != null && structureConfig.spacing() > 10){
                        ChunkPos chunkPos2 = structureFeature.getPotentialFeatureChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                }
            }
        }

        int minThickness = RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.endMineshaftMinIslandThickness;
        if(minThickness == 0)
            return true;

        BlockPos.MutableBlockPos islandTopBottomThickness = new BlockPos.MutableBlockPos(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        int xPos = chunkPos1.getMinBlockX();
        int zPos = chunkPos1.getMinBlockZ();

        // Surrounding far terrain is more likely to fail the check and exit early.
        for(int i = 2; i >= 1; i--) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                Vector3f offsetPos = new Vector3f(direction.getStepX(), 0, direction.getStepZ());
                offsetPos = new Vector3f(offsetPos.x() * 30f * i, 0, offsetPos.z() * 30f * i);
                analyzeLand(chunkGenerator, xPos + (int) offsetPos.x(), zPos + (int) offsetPos.z(), islandTopBottomThickness, heightLimitView);
                if (islandTopBottomThickness.getZ() < minThickness) return false;
            }
        }

        analyzeLand(chunkGenerator, xPos, zPos, islandTopBottomThickness, heightLimitView);
        return islandTopBottomThickness.getZ() >= minThickness;
    }

    private void analyzeLand(ChunkGenerator chunkGenerator, int xPos, int zPos, BlockPos.MutableBlockPos islandTopBottomThickness, LevelHeightAccessor heightLimitView) {
        NoiseColumn columnOfBlocks = chunkGenerator.getBaseColumn(xPos, zPos, heightLimitView);
        int minY = chunkGenerator.getMinY();
        int rangeHeight = chunkGenerator.getGenDepth();
        int maxY = minY + rangeHeight;
        BlockPos.MutableBlockPos currentPos = new BlockPos.MutableBlockPos(xPos, maxY, zPos);
        boolean isInIsland = false;

        while(currentPos.getY() >= minY){
            BlockState state = columnOfBlocks.getBlockState(currentPos);

            // Detects top of island
            if(!state.isAir() && !isInIsland){
                isInIsland = true;
                int topIslandY = Math.min(currentPos.getY(), islandTopBottomThickness.getX());
                islandTopBottomThickness.set(topIslandY, islandTopBottomThickness.getY(), islandTopBottomThickness.getZ());
            }

            // Detects bottom of island or land
            else if((state.isAir() && isInIsland) || currentPos.getY() == minY){
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
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return MineshaftEndStructure.MainStart::new;
    }

    public class MainStart extends NoiseAffectingStructureStart<NoneFeatureConfiguration> {

        private final ResourceLocation structureID;

        public MainStart(StructureFeature<NoneFeatureConfiguration> structureIn, ChunkPos chunkPos1, int referenceIn, long seedIn) {
            super(structureIn, chunkPos1, referenceIn, seedIn);
            structureID = Registry.STRUCTURE_FEATURE.getKey(structureIn);
        }

        public void generatePieces(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos1, Biome biome, NoneFeatureConfiguration defaultFeatureConfig, LevelHeightAccessor heightLimitView) {
            BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos(chunkPos1.getMinBlockX(), 0, chunkPos1.getMinBlockZ());
            BlockPos.MutableBlockPos islandTopBottomThickness = new BlockPos.MutableBlockPos(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            analyzeLand(chunkGenerator, blockpos.getX(), blockpos.getZ(), islandTopBottomThickness, heightLimitView);

            int minThickness = RepurposedStructures.RSAllConfig.RSMineshaftsConfig.misc.endMineshaftMinIslandThickness;
            int maxY = 53;
            int minY = 15;
            if(minThickness == 0){
                blockpos.move(Direction.UP, 35);
            }
            else{
                int structureStartHeight = random.nextInt(Math.max(islandTopBottomThickness.getZ() - minThickness + 1, 1)) + islandTopBottomThickness.getY() + (minThickness / 2);
                blockpos.move(Direction.UP, structureStartHeight);
                maxY = islandTopBottomThickness.getX() - 5;
                minY = islandTopBottomThickness.getY();
                if(maxY - minY <= 5){
                    minY = maxY - 5;
                }
            }

            PieceLimitedJigsawManager.assembleJigsawStructure(
                    dynamicRegistryManager,
                    new JigsawConfiguration(() -> dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).get(startPool), structureSize),
                    chunkGenerator,
                    structureManager,
                    blockpos,
                    this.pieces,
                    this.random,
                    false,
                    false,
                    heightLimitView,
                    structureID,
                    maxY,
                    minY);

            this.getBoundingBox();
        }
    }


    public static class Builder<T extends MineshaftEndStructure.Builder<T>> extends AdvancedJigsawStructure.Builder<T> {

        protected double probability = 0.01;

        public Builder(ResourceLocation startPool) {
            super(startPool);
        }

        public T setProbability(double probability){
            this.probability = probability;
            return getThis();
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
