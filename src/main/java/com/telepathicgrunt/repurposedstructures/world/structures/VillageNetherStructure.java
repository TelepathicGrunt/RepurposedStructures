package com.telepathicgrunt.repurposedstructures.world.structures;

import com.telepathicgrunt.repurposedstructures.RSStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.structure.StructureManager;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.BlockView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class VillageNetherStructure extends VillageBaseStructure {

    public VillageNetherStructure(Identifier poolID, int structureSize) {
        super(poolID, structureSize);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
        StructureConfig warpedOutpostConfig = chunkGenerator.getStructuresConfig().getForType(RSStructures.WARPED_OUTPOST);
        StructureConfig crimsonOutpostConfig = chunkGenerator.getStructuresConfig().getForType(RSStructures.CRIMSON_OUTPOST);
        StructureConfig netherBrickOutpostConfig = chunkGenerator.getStructuresConfig().getForType(RSStructures.NETHER_BRICK_OUTPOST);

        for (int curChunkX = chunkX - 10; curChunkX <= chunkX + 10; curChunkX++) {
            for (int curChunkZ = chunkZ - 10; curChunkZ <= chunkZ + 10; curChunkZ++) {
                ChunkPos chunkPos2;
                if(warpedOutpostConfig != null){
                    chunkPos2 = RSStructures.WARPED_OUTPOST.getStartChunk(warpedOutpostConfig, seed, chunkRandom, curChunkX, curChunkZ);
                    if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                        return false;
                    }
                }
                if(warpedOutpostConfig != null){
                    chunkPos2 = RSStructures.CRIMSON_OUTPOST.getStartChunk(crimsonOutpostConfig, seed, chunkRandom, curChunkX, curChunkZ);
                    if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                        return false;
                    }
                }
                if(warpedOutpostConfig != null){
                    chunkPos2 = RSStructures.NETHER_BRICK_OUTPOST.getStartChunk(netherBrickOutpostConfig, seed, chunkRandom, curChunkX, curChunkZ);
                    if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                        return false;
                    }
                }
            }
        }
        return super.shouldStartAt(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, defaultFeatureConfig);
    }

    @Override
    public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return VillageNetherStructure.Start::new;
    }

    public class Start extends MainStart {
        public Start(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
            super.init(dynamicRegistryManager, chunkGenerator, structureManager, chunkX, chunkZ, biome, defaultFeatureConfig);

            BlockPos lowestLandPos = getHighestLand(chunkGenerator, this.boundingBox);
            if (lowestLandPos.getY() >= 108 || lowestLandPos.getY() <= 33) {
                this.method_14976(this.random, 20, 21);
            }
            else {
                this.method_14976(this.random, lowestLandPos.getY() - 13, lowestLandPos.getY() - 12);
            }
        }
    }

    //Helper methods//

    public static BlockPos getHighestLand(ChunkGenerator chunkGenerator, BlockBox boundingBox) {
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(boundingBox.getCenter().getX(), 108, boundingBox.getCenter().getZ());
        BlockView blockView = chunkGenerator.getColumnSample(mutable.getX(), mutable.getZ());
        BlockState currentBlockstate;
        while (mutable.getY() > 33) {
            currentBlockstate = blockView.getBlockState(mutable);
            if (!currentBlockstate.isSolidBlock(blockView, mutable)) {
                mutable.move(Direction.DOWN);
                continue;
            }
            else if (blockView.getBlockState(mutable.add(0, 3, 0)).getMaterial() == Material.AIR &&
                    isValidBlock(currentBlockstate)) {
                break;
            }
            mutable.move(Direction.DOWN);
        }

        return mutable;
    }

    private static boolean isValidBlock(BlockState currentBlockstate) {
        if (BlockTags.INFINIBURN_NETHER.contains(currentBlockstate.getBlock()) ||
                BlockTags.VALID_SPAWN.contains(currentBlockstate.getBlock()) ||
                BlockTags.SAND.contains(currentBlockstate.getBlock()) ||
                BlockTags.NYLIUM.contains(currentBlockstate.getBlock()) ||
                BlockTags.ICE.contains(currentBlockstate.getBlock()) ||
                BlockTags.PLANKS.contains(currentBlockstate.getBlock()) ||
                BlockTags.STONE_BRICKS.contains(currentBlockstate.getBlock()) ||
                BlockTags.WITHER_IMMUNE.contains(currentBlockstate.getBlock()) ||
                BlockTags.WOOL.contains(currentBlockstate.getBlock()) ||
                currentBlockstate.getMaterial() == Material.AGGREGATE ||
                currentBlockstate.getMaterial() == Material.STONE ||
                currentBlockstate.getMaterial() == Material.SOIL)
            return true;
        return false;
    }
}