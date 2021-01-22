package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;


public abstract class AbstractNetherStructure extends AbstractBaseStructure {
    public AbstractNetherStructure(Codec<NoFeatureConfig> config) {
        super(config);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeProvider biomeSource, long seed, SharedSeedRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig defaultFeatureConfig) {

        // No one can be within 6 chunks of outpost
        int radius = RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST).contains(this) ? 6 : 3;
        if(this != RSStructures.WARPED_OUTPOST.get() && this != RSStructures.CRIMSON_OUTPOST.get() && this != RSStructures.NETHER_BRICK_OUTPOST.get()){
            for (int curChunkX = chunkX - radius; curChunkX <= chunkX + radius; curChunkX++) {
                for (int curChunkZ = chunkZ - radius; curChunkZ <= chunkZ + radius; curChunkZ++) {
                    for(Structure<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE)) {
                        StructureSeparationSettings structureConfig = chunkGenerator.getStructuresConfig().getForType(structureFeature);
                        if(structureConfig != null){
                            ChunkPos chunkPos2 = structureFeature.getStartChunk(structureConfig, seed, chunkRandom, curChunkX, curChunkZ);
                            if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return super.shouldStartAt(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, defaultFeatureConfig);
    }

    public static abstract class AbstractStart extends MarginedStructureStart<NoFeatureConfig> {
        public AbstractStart(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }


        public BlockPos getHighestLand(ChunkGenerator chunkGenerator){
            BlockPos.Mutable mutable = new BlockPos.Mutable().setPos(this.bounds.func_215126_f().getX(), 108, this.bounds.func_215126_f().getZ());
            IBlockReader blockView = chunkGenerator.getColumnSample(mutable.getX(), mutable.getZ());
            BlockState currentBlockstate;
            while(mutable.getY() > 30){
                currentBlockstate = blockView.getBlockState(mutable);
                if(!currentBlockstate.isNormalCube(blockView, mutable)){
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
            BlockPos.Mutable mutable = new BlockPos.Mutable().setPos(this.bounds.func_215126_f().getX(), 35, this.bounds.func_215126_f().getZ());
            IBlockReader blockView = chunkGenerator.getColumnSample(mutable.getX(), mutable.getZ());
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
            return BlockTags.INFINIBURN_NETHER.contains(currentBlockstate.getBlock()) ||
                    BlockTags.VALID_SPAWN.contains(currentBlockstate.getBlock()) ||
                    BlockTags.SAND.contains(currentBlockstate.getBlock()) ||
                    BlockTags.NYLIUM.contains(currentBlockstate.getBlock()) ||
                    BlockTags.ICE.contains(currentBlockstate.getBlock()) ||
                    BlockTags.PLANKS.contains(currentBlockstate.getBlock()) ||
                    BlockTags.STONE_BRICKS.contains(currentBlockstate.getBlock()) ||
                    BlockTags.WITHER_IMMUNE.contains(currentBlockstate.getBlock()) ||
                    BlockTags.WOOL.contains(currentBlockstate.getBlock()) ||
                    currentBlockstate.getMaterial() == Material.SAND ||
                    currentBlockstate.getMaterial() == Material.ROCK ||
                    currentBlockstate.getMaterial() == Material.EARTH;
        }
    }
}