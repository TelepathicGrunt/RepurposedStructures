package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetChanceConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Material;

import java.util.BitSet;
import java.util.function.Predicate;


public class StructureBreakage extends Feature<StructureTargetChanceConfig> {

    public StructureBreakage(Codec<StructureTargetChanceConfig> config) {
        super(config);
    }

    private static final Predicate<BlockState> FORTRESS_BLOCKS = (blockState) -> {
        if (blockState == null) {
            return false;
        } else {
            return blockState.getMaterial() == Material.STONE ||
                    blockState.getMaterial() == Material.DIRT ||
                    blockState.is(Blocks.INFESTED_CHISELED_STONE_BRICKS) ||
                    blockState.is(Blocks.INFESTED_CRACKED_STONE_BRICKS) ||
                    blockState.is(Blocks.INFESTED_STONE_BRICKS) ||
                    blockState.is(Blocks.INFESTED_MOSSY_STONE_BRICKS) ||
                    blockState.is(Blocks.IRON_BARS) ||
                    blockState.is(BlockTags.FLOWER_POTS);
        }
    };


    @Override
    public boolean place(FeaturePlaceContext<StructureTargetChanceConfig> context) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        if(context.random().nextFloat() < context.config().chance) {
            mutable.set(context.origin()).move(
                    context.random().nextInt(7) - 3,
                    0,
                    context.random().nextInt(7) - 3
            );

            boolean foundSurface = findSurface(context.level(), mutable, Direction.UP);
            if(!foundSurface) {
                mutable.move(Direction.DOWN, 5);
                foundSurface = findSurface(context.level(), mutable, Direction.DOWN);
            }

            if(!foundSurface) {
                return false;
            }
            mutable.move(Direction.UP, 2);

            float f = context.random().nextFloat() * 3.1415927F;
            float g = 3;
            int i = 2;
            double d = (float) mutable.getX() + Mth.sin(f) * g;
            double e = (float) mutable.getX() - Mth.sin(f) * g;
            double h = (float) mutable.getZ() + Mth.cos(f) * g;
            double j = (float) mutable.getZ() - Mth.cos(f) * g;
            double l = mutable.getY() + context.random().nextInt(3) - 2;
            double m = mutable.getY() + context.random().nextInt(3) - 2;
            int n = mutable.getX() - Mth.ceil(g) - i;
            int o = mutable.getY() - 4;
            int p = mutable.getZ() - Mth.ceil(g) - i;
            int q = 2 * (Mth.ceil(g) + i);
            int r = 8;

            for (int s = n; s <= n + q; ++s) {
                for (int t = p; t <= p + q; ++t) {
                    return this.generateVeinPart(context, d, e, h, j, l, m, n, o, p, q, r, context.chunkGenerator());
                }
            }
        }

        return true;
    }

    private boolean findSurface(WorldGenLevel world, BlockPos.MutableBlockPos mutable, Direction direction) {
        for (int i = 0; i <= 5; i++) {
            if (FORTRESS_BLOCKS.test(world.getBlockState(mutable))) {
                return true;
            }
            mutable.move(direction);
        }
        return false;
    }


    protected boolean generateVeinPart(FeaturePlaceContext<StructureTargetChanceConfig> context, double startX, double endX, double startZ, double endZ, double startY, double endY, int x, int y, int z, int size, int i, ChunkGenerator chunkGenerator) {
        int j = 0;
        BitSet bitSet = new BitSet(size * i * size);
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        double[] ds = new double[24 * 4];

        int m;
        double o;
        double p;
        double q;
        double r;
        for(m = 0; m < 24; ++m) {
            float f = (float)m / (float)24;
            o = Mth.lerp(f, startX, endX);
            p = Mth.lerp(f, startY, endY);
            q = Mth.lerp(f, startZ, endZ);
            r = context.random().nextDouble() * (double)24 / 16.0D;
            double l = ((double)(Mth.sin(3.1415927F * f) + 1.0F) * r + 1.0D) / 2.0D;
            ds[m * 4] = o;
            ds[m * 4 + 1] = p;
            ds[m * 4 + 2] = q;
            ds[m * 4 + 3] = l;
        }

        for(m = 0; m < 24 - 1; ++m) {
            if (ds[m * 4 + 3] > 0.0D) {
                for(int n = m + 1; n < 24; ++n) {
                    if (ds[n * 4 + 3] > 0.0D) {
                        o = ds[m * 4] - ds[n * 4];
                        p = ds[m * 4 + 1] - ds[n * 4 + 1];
                        q = ds[m * 4 + 2] - ds[n * 4 + 2];
                        r = ds[m * 4 + 3] - ds[n * 4 + 3];
                        if (r * r > o * o + p * p + q * q) {
                            if (r > 0.0D) {
                                ds[n * 4 + 3] = -1.0D;
                            } else {
                                ds[m * 4 + 3] = -1.0D;
                            }
                        }
                    }
                }
            }
        }

        for(m = 0; m < 24; ++m) {
            double t = ds[m * 4 + 3];
            if (t >= 0.0D) {
                double u = ds[m * 4];
                double v = ds[m * 4 + 1];
                double w = ds[m * 4 + 2];
                int aa = Math.max(Mth.floor(u - t), x);
                int ab = Math.max(Mth.floor(v - t), y);
                int ac = Math.max(Mth.floor(w - t), z);
                int ad = Math.max(Mth.floor(u + t), aa);
                int ae = Math.max(Mth.floor(v + t), ab);
                int af = Math.max(Mth.floor(w + t), ac);

                for(int ag = aa; ag <= ad; ++ag) {
                    double ah = ((double)ag + 0.5D - u) / t;
                    if (ah * ah < 1.0D) {
                        for(int ai = ab; ai <= ae; ++ai) {
                            double aj = ((double)ai + 0.5D - v) / t;
                            if (ah * ah + aj * aj < 1.0D) {
                                for(int ak = ac; ak <= af; ++ak) {
                                    double al = ((double)ak + 0.5D - w) / t;
                                    if (ah * ah + aj * aj + al * al < 1.0D) {
                                        int am = ag - x + (ai - y) * size + (ak - z) * size * i;
                                        if (!bitSet.get(am)) {
                                            bitSet.set(am);
                                            mutable.set(ag, ai, ak);
                                            BlockState state = context.level().getBlockState(mutable);
                                            if (FORTRESS_BLOCKS.test(state)) {
                                                ChunkPos currentChunkPos = new ChunkPos(mutable);
                                                ChunkAccess currentChunk = context.level().getChunk(currentChunkPos.x, currentChunkPos.z);
                                                boolean isBelowSealevel = mutable.getY() < chunkGenerator.getSeaLevel();

                                                // Do not carve if exposed to cave space
                                                if(isBelowSealevel && isBorderingAir(context.level(), mutable)) {
                                                    continue;
                                                }

                                                currentChunk.setBlockState(mutable, isBelowSealevel ? Blocks.WATER.defaultBlockState() : Blocks.CAVE_AIR.defaultBlockState(), false);
                                                ++j;

                                                // no floating vines
                                                state = currentChunk.getBlockState(mutable.move(Direction.DOWN));
                                                while (state.getMaterial() == Material.REPLACEABLE_PLANT) {
                                                    currentChunk.setBlockState(mutable, isBelowSealevel ? Blocks.WATER.defaultBlockState() : Blocks.CAVE_AIR.defaultBlockState(), false);
                                                    state = currentChunk.getBlockState(mutable.move(Direction.DOWN));
                                                }

                                                state = currentChunk.getBlockState(mutable.move(Direction.UP));
                                                isBelowSealevel = mutable.getY() < chunkGenerator.getSeaLevel();

                                                if (state.is(BlockTags.FLOWER_POTS)) {
                                                    currentChunk.setBlockState(mutable, isBelowSealevel ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState(), false);
                                                }
                                                else {
                                                    while (state.getMaterial() == Material.REPLACEABLE_PLANT) {
                                                         currentChunk.setBlockState(mutable, isBelowSealevel ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState(), false);
                                                        state = currentChunk.getBlockState(mutable.move(Direction.UP));
                                                    }
                                                }

                                                BlockPos.MutableBlockPos mutableVineCheck = new BlockPos.MutableBlockPos();
                                                for (Direction direction : Direction.values()) {
                                                    if (direction == Direction.UP) continue;

                                                    mutableVineCheck.set(mutable).move(direction);
                                                    if (currentChunkPos.x != mutableVineCheck.getX() >> 4 || currentChunkPos.z != mutableVineCheck.getZ() >> 4) {
                                                        currentChunk = context.level().getChunk(mutableVineCheck);
                                                        currentChunkPos = new ChunkPos(mutableVineCheck);
                                                    }

                                                    BlockState neighboringBlock = currentChunk.getBlockState(mutableVineCheck);
                                                    if (neighboringBlock.is(Blocks.VINE) && neighboringBlock.getValue(VineBlock.getPropertyForFace(direction.getOpposite()))) {
                                                        while(neighboringBlock.getMaterial() == Material.REPLACEABLE_PLANT) {
                                                            currentChunk.setBlockState(mutableVineCheck, isBelowSealevel ? Blocks.WATER.defaultBlockState() : Blocks.CAVE_AIR.defaultBlockState(), false);
                                                            neighboringBlock = currentChunk.getBlockState(mutableVineCheck.move(Direction.DOWN));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return j > 0;
    }

    private boolean isBorderingAir(ServerLevelAccessor world, BlockPos.MutableBlockPos mutable) {
        BlockPos.MutableBlockPos mutableWaterCheck = new BlockPos.MutableBlockPos();
        ChunkPos currentChunkPos2 = new ChunkPos(mutable);
        ChunkAccess currentChunk2 = world.getChunk(currentChunkPos2.x, currentChunkPos2.z);
        for (Direction direction : Direction.values()) {
            // Do not check above or else we never carve at sealevel
            if (direction == Direction.UP) continue;

            mutableWaterCheck.set(mutable).move(direction);
            if (currentChunkPos2.x != mutableWaterCheck.getX() >> 4 || currentChunkPos2.z != mutableWaterCheck.getZ() >> 4) {
                currentChunk2 = world.getChunk(mutableWaterCheck);
                currentChunkPos2 = new ChunkPos(mutableWaterCheck);
            }

            if(currentChunk2.getBlockState(mutableWaterCheck).isAir()) {
                return true;
            }
        }
        return false;
    }
}