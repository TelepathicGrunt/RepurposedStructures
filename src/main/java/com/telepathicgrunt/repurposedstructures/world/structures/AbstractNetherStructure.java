package com.telepathicgrunt.repurposedstructures.world.structures;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.List;


public abstract class AbstractNetherStructure extends AbstractBaseStructure {

    private static List<StructureFeature<?>> AVOID_STRUCTURE_LIST = null;
    public AbstractNetherStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
        if(AVOID_STRUCTURE_LIST == null){
            AVOID_STRUCTURE_LIST = Lists.newArrayList(Iterators.concat(
                    RSStructures.NETHER_SHIPWRECKS_LIST.iterator(),
                    RSStructures.NETHER_OUTPOSTS_LIST.iterator(),
                    RSStructures.LARGE_VANILLA_NETHER_STRUCTURE_LIST.iterator()));
            AVOID_STRUCTURE_LIST.add(RSStructures.NETHER_PYRAMID);
        }

        // No one can be within 6 chunks of outpost
        int radius = RSStructures.NETHER_OUTPOSTS_LIST.contains(this) ? 6 : 3;
        for (int curChunkX = chunkX - radius; curChunkX <= chunkX + radius; curChunkX++) {
            for (int curChunkZ = chunkZ - radius; curChunkZ <= chunkZ + radius; curChunkZ++) {
                for(StructureFeature<?> structureFeature : AVOID_STRUCTURE_LIST) {
                    StructureConfig structureConfig = chunkGenerator.getStructuresConfig().getForType(structureFeature);
                    if(structureConfig != null) {
                        ChunkPos chunkPos2 = structureFeature.getStartChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
                        if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                            return false;
                        }
                    }
                }
            }
        }
        return super.shouldStartAt(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, defaultFeatureConfig);
    }

    public static abstract class AbstractStart extends MarginedStructureStart<DefaultFeatureConfig> {
        public AbstractStart(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }


        public BlockPos getHighestLand(ChunkGenerator chunkGenerator){
            BlockPos.Mutable mutable = new BlockPos.Mutable().set(this.boundingBox.getCenter().getX(), 108, this.boundingBox.getCenter().getZ());
            BlockView blockView = chunkGenerator.getColumnSample(mutable.getX(), mutable.getZ());
            BlockState currentBlockstate;
            while(mutable.getY() > 33){
                currentBlockstate = blockView.getBlockState(mutable);
                if(!currentBlockstate.isSolidBlock(blockView, mutable)){
                    mutable.move(Direction.DOWN);
                    continue;
                }
                else if(blockView.getBlockState(mutable.add(0,3,0)).getMaterial() == Material.AIR &&
                        isValidBlock(currentBlockstate))
                {
                    break;
                }
                mutable.move(Direction.DOWN);
            }

            return mutable;
        }

        public BlockPos getLowestLand(ChunkGenerator chunkGenerator){
            BlockPos.Mutable mutable = new BlockPos.Mutable().set(this.boundingBox.getCenter().getX(), 35, this.boundingBox.getCenter().getZ());
            BlockView blockView = chunkGenerator.getColumnSample(mutable.getX(), mutable.getZ());
            BlockState currentBlockstate = blockView.getBlockState(mutable);
            while (mutable.getY() <= 108) {

                if(blockView.getBlockState(mutable).getMaterial() != Material.AIR &&
                        blockView.getBlockState(mutable.up()).getMaterial() == Material.AIR &&
                        blockView.getBlockState(mutable.up(5)).getMaterial() == Material.AIR &&
                        isValidBlock(currentBlockstate))
                {
                    mutable.move(Direction.UP);
                    break;
                }

                mutable.move(Direction.UP);
                currentBlockstate = blockView.getBlockState(mutable);
            }

            return mutable;
        }

        private boolean isValidBlock(BlockState currentBlockstate){
            if(BlockTags.INFINIBURN_NETHER.contains(currentBlockstate.getBlock()) ||
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
}