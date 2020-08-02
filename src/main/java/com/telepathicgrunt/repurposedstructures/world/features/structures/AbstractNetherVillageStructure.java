package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
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
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public abstract class AbstractNetherVillageStructure extends AbstractVillageStructure {
    public AbstractNetherVillageStructure(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkRandom chunkRandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
        for (int curChunkX = chunkX - 10; curChunkX <= chunkX + 10; curChunkX++) {
            for (int curChunkZ = chunkZ - 10; curChunkZ <= chunkZ + 10; curChunkZ++) {
                ChunkPos chunkPos2 = RSFeatures.WARPED_OUTPOST.method_27218(chunkGenerator.getConfig().method_28600(RSFeatures.WARPED_OUTPOST), seed, chunkRandom, curChunkX, curChunkZ);
                if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                    return false;
                }
                chunkPos2 = RSFeatures.CRIMSON_OUTPOST.method_27218(chunkGenerator.getConfig().method_28600(RSFeatures.CRIMSON_OUTPOST), seed, chunkRandom, curChunkX, curChunkZ);
                if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                    return false;
                }
                chunkPos2 = RSFeatures.NETHER_BRICK_OUTPOST.method_27218(chunkGenerator.getConfig().method_28600(RSFeatures.NETHER_BRICK_OUTPOST), seed, chunkRandom, curChunkX, curChunkZ);
                if (curChunkX == chunkPos2.x && curChunkZ == chunkPos2.z) {
                    return false;
                }
            }
        }
        return super.shouldStartAt(chunkGenerator, biomeSource, seed, chunkRandom, chunkX, chunkZ, biome, chunkPos, defaultFeatureConfig);
    }

    public static abstract class NetherAbstractStart extends AbstractStart {
        public NetherAbstractStart(StructureFeature<DefaultFeatureConfig> structureIn, int chunkX, int chunkZ, BlockBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        public BlockPos getHighestLand(ChunkGenerator chunkGenerator) {
            BlockPos.Mutable mutable = new BlockPos.Mutable().set(this.boundingBox.getCenter().getX(), 108, this.boundingBox.getCenter().getZ());
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

        private boolean isValidBlock(BlockState currentBlockstate) {
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
}