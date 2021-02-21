package com.telepathicgrunt.repurposedstructures.world.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
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


public abstract class AbstractNetherStructure extends AbstractBaseStructure<DefaultFeatureConfig> {

    public AbstractNetherStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {

        // No one can be within 6 chunks of outpost
        boolean isNetherOutpost = RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NETHER_OUTPOST).contains(this);
        int radius = isNetherOutpost ? 6 : 3;
        for (int curChunkX = chunkX - radius; curChunkX <= chunkX + radius; curChunkX++) {
            for (int curChunkZ = chunkZ - radius; curChunkZ <= chunkZ + radius; curChunkZ++) {
                if(curChunkX == chunkX && curChunkZ == chunkZ) continue; // Prevent detecting the structure itself and thus, never spawning if structure is in its own blacklist

                for(StructureFeature<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.GENERIC_AVOID_NETHER_STRUCTURE)) {
                    StructureConfig structureConfig = chunkGenerator.getStructuresConfig().getForType(structureFeature);
                    if(structureConfig != null && structureConfig.getSpacing() > 8) {
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
            BlockPos.Mutable mutable = new BlockPos.Mutable().set(this.boundingBox.getCenter().getX(), chunkGenerator.getWorldHeight() - 20, this.boundingBox.getCenter().getZ());
            BlockView blockView = chunkGenerator.getColumnSample(mutable.getX(), mutable.getZ());
            BlockState currentBlockstate;
            while(mutable.getY() > chunkGenerator.getSeaLevel() - 2){
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
            BlockPos.Mutable mutable = new BlockPos.Mutable().set(this.boundingBox.getCenter().getX(), chunkGenerator.getSeaLevel() + 3, this.boundingBox.getCenter().getZ());
            BlockView blockView = chunkGenerator.getColumnSample(mutable.getX(), mutable.getZ());
            BlockState currentBlockstate = blockView.getBlockState(mutable);
            while (mutable.getY() <= chunkGenerator.getWorldHeight()) {

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