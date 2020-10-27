package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Predicate;


public class FortressBreakage extends Feature<NoFeatureConfig> {

    public FortressBreakage(Codec<NoFeatureConfig> configFactory) {
        super(configFactory);
    }

    private static final Predicate<BlockState> FORTRESS_BLOCKS = (blockState) -> {
        if (blockState == null) {
            return false;
        } else {
            return blockState.getMaterial() == Material.ROCK ||
                    blockState.getMaterial() == Material.EARTH ||
                    blockState.isIn(Blocks.INFESTED_CHISELED_STONE_BRICKS) ||
                    blockState.isIn(Blocks.INFESTED_CRACKED_STONE_BRICKS) ||
                    blockState.isIn(Blocks.INFESTED_STONE_BRICKS) ||
                    blockState.isIn(Blocks.INFESTED_MOSSY_STONE_BRICKS) ||
                    blockState.isIn(Blocks.IRON_BARS);
        }
    };

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
        if (FORTRESS_BLOCKS.test(world.getBlockState(position.down())) &&
                world.getStructures(SectionPos.from(position), RSStructures.JUNGLE_FORTRESS.get()).findAny().isPresent())
        {
            if(random.nextBoolean())
                position = position.down();

            float f = random.nextFloat() * 3.1415927F;
            float g = 3;
            int i = 2;
            double d = (float) position.getX() + MathHelper.sin(f) * g;
            double e = (float) position.getX() - MathHelper.sin(f) * g;
            double h = (float) position.getZ() + MathHelper.cos(f) * g;
            double j = (float) position.getZ() - MathHelper.cos(f) * g;
            double l = position.getY() + random.nextInt(3) - 2;
            double m = position.getY() + random.nextInt(3) - 2;
            int n = position.getX() - MathHelper.ceil(g) - i;
            int o = position.getY() - 4;
            int p = position.getZ() - MathHelper.ceil(g) - i;
            int q = 2 * (MathHelper.ceil(g) + i);
            int r = 8;

            for (int s = n; s <= n + q; ++s) {
                for (int t = p; t <= p + q; ++t) {
                    return this.generateVeinPart(world, random, d, e, h, j, l, m, n, o, p, q, r);
                }
            }
            return true;
        }

        return false;
    }


    protected boolean generateVeinPart(IWorld world, Random random, double startX, double endX, double startZ, double endZ, double startY, double endY, int x, int y, int z, int size, int i) {
        int j = 0;
        BitSet bitSet = new BitSet(size * i * size);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        double[] ds = new double[24 * 4];

        int m;
        double o;
        double p;
        double q;
        double r;
        for(m = 0; m < 24; ++m) {
            float f = (float)m / (float)24;
            o = MathHelper.lerp(f, startX, endX);
            p = MathHelper.lerp(f, startY, endY);
            q = MathHelper.lerp(f, startZ, endZ);
            r = random.nextDouble() * (double)24 / 16.0D;
            double l = ((double)(MathHelper.sin(3.1415927F * f) + 1.0F) * r + 1.0D) / 2.0D;
            ds[m * 4 + 0] = o;
            ds[m * 4 + 1] = p;
            ds[m * 4 + 2] = q;
            ds[m * 4 + 3] = l;
        }

        for(m = 0; m < 24 - 1; ++m) {
            if (ds[m * 4 + 3] > 0.0D) {
                for(int n = m + 1; n < 24; ++n) {
                    if (ds[n * 4 + 3] > 0.0D) {
                        o = ds[m * 4 + 0] - ds[n * 4 + 0];
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
                double u = ds[m * 4 + 0];
                double v = ds[m * 4 + 1];
                double w = ds[m * 4 + 2];
                int aa = Math.max(MathHelper.floor(u - t), x);
                int ab = Math.max(MathHelper.floor(v - t), y);
                int ac = Math.max(MathHelper.floor(w - t), z);
                int ad = Math.max(MathHelper.floor(u + t), aa);
                int ae = Math.max(MathHelper.floor(v + t), ab);
                int af = Math.max(MathHelper.floor(w + t), ac);

                for(int xx = aa; xx <= ad; ++xx) {
                    double ah = ((double)xx + 0.5D - u) / t;
                    if (ah * ah < 1.0D) {
                        for(int yy = ab; yy <= ae; ++yy) {
                            double aj = ((double)yy + 0.5D - v) / t;
                            if (ah * ah + aj * aj < 1.0D) {
                                for(int zz = ac; zz <= af; ++zz) {
                                    double al = ((double)zz + 0.5D - w) / t;
                                    if (ah * ah + aj * aj + al * al < 1.0D) {
                                        int am = xx - x + (yy - y) * size + (zz - z) * size * i;
                                        if (!bitSet.get(am)) {
                                            bitSet.set(am);
                                            mutable.setPos(xx, yy, zz);
                                            if (FORTRESS_BLOCKS.test(world.getBlockState(mutable))) {
                                                world.setBlockState(mutable, Blocks.AIR.getDefaultState(), 2);
                                                ++j;
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
}