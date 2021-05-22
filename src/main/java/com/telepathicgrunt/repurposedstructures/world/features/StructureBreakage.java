package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetChanceConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.VineBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.BitSet;
import java.util.Random;
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
                    blockState.getMaterial() == Material.SOIL ||
                    blockState.isOf(Blocks.INFESTED_CHISELED_STONE_BRICKS) ||
                    blockState.isOf(Blocks.INFESTED_CRACKED_STONE_BRICKS) ||
                    blockState.isOf(Blocks.INFESTED_STONE_BRICKS) ||
                    blockState.isOf(Blocks.INFESTED_MOSSY_STONE_BRICKS) ||
                    blockState.isOf(Blocks.IRON_BARS);
        }
    };


    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetChanceConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        if(random.nextFloat() < config.chance){
            mutable.set(position).move(
                    random.nextInt(7) - 3,
                    0,
                    random.nextInt(7) - 3
            );

            boolean foundSurface = findSurface(world, mutable, Direction.UP);
            if(!foundSurface){
                mutable.move(Direction.DOWN, 5);
                foundSurface = findSurface(world, mutable, Direction.DOWN);
            }

            if(!foundSurface && !world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                return false;
            }
            mutable.move(Direction.UP);

            float f = random.nextFloat() * 3.1415927F;
            float g = 3;
            int i = 2;
            double d = (float) mutable.getX() + MathHelper.sin(f) * g;
            double e = (float) mutable.getX() - MathHelper.sin(f) * g;
            double h = (float) mutable.getZ() + MathHelper.cos(f) * g;
            double j = (float) mutable.getZ() - MathHelper.cos(f) * g;
            double l = mutable.getY() + random.nextInt(3) - 2;
            double m = mutable.getY() + random.nextInt(3) - 2;
            int n = mutable.getX() - MathHelper.ceil(g) - i;
            int o = mutable.getY() - 4;
            int p = mutable.getZ() - MathHelper.ceil(g) - i;
            int q = 2 * (MathHelper.ceil(g) + i);
            int r = 8;

            for (int s = n; s <= n + q; ++s) {
                for (int t = p; t <= p + q; ++t) {
                    return this.generateVeinPart(world, random, d, e, h, j, l, m, n, o, p, q, r, chunkGenerator);
                    //this.generateVeinPart(world, random, d, e, h, j, l, m, n, o, p, q, r);
                }
            }
        }

        return true;
    }

    private boolean findSurface(StructureWorldAccess world, BlockPos.Mutable mutable, Direction direction) {
        for (int i = 0; i <= 5; i++) {
            if (FORTRESS_BLOCKS.test(world.getBlockState(mutable))) {
                return true;
            }
            mutable.move(direction);
        }
        return false;
    }


    protected boolean generateVeinPart(WorldAccess world, Random random, double startX, double endX, double startZ, double endZ, double startY, double endY, int x, int y, int z, int size, int i, ChunkGenerator chunkGenerator) {
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
                                            BlockState state = world.getBlockState(mutable);
                                            if (FORTRESS_BLOCKS.test(state)) {
                                                ChunkPos currentChunkPos = new ChunkPos(mutable);
                                                Chunk currentChunk = world.getChunk(currentChunkPos.x, currentChunkPos.z);
                                                boolean isBelowSealevel = mutable.getY() < chunkGenerator.getSeaLevel();
                                                currentChunk.setBlockState(mutable, isBelowSealevel ? Blocks.WATER.getDefaultState() : Blocks.CAVE_AIR.getDefaultState(), false);
                                                ++j;

                                                // no floating vines
                                                state = currentChunk.getBlockState(mutable.move(Direction.DOWN));
                                                while(state.getMaterial() == Material.REPLACEABLE_PLANT){
                                                    currentChunk.setBlockState(mutable, isBelowSealevel ? Blocks.WATER.getDefaultState() : Blocks.CAVE_AIR.getDefaultState(), false);
                                                    state = currentChunk.getBlockState(mutable.move(Direction.DOWN));
                                                }

                                                state = currentChunk.getBlockState(mutable.move(Direction.UP));
                                                while(state.getMaterial() == Material.REPLACEABLE_PLANT){
                                                    isBelowSealevel = mutable.getY() < chunkGenerator.getSeaLevel();
                                                    currentChunk.setBlockState(mutable, isBelowSealevel ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState(), false);
                                                    state = currentChunk.getBlockState(mutable.move(Direction.UP));
                                                }

                                                BlockPos.Mutable mutable2 = new BlockPos.Mutable();
                                                for (Direction direction : Direction.values()) {
                                                    if(direction == Direction.UP) continue;

                                                    mutable2.set(mutable).move(direction);
                                                    if (currentChunkPos.x != mutable2.getX() >> 4 || currentChunkPos.z != mutable2.getZ() >> 4) {
                                                        currentChunk = world.getChunk(mutable2);
                                                        currentChunkPos = new ChunkPos(mutable2);
                                                    }

                                                    BlockState neighboringBlock = currentChunk.getBlockState(mutable2);
                                                    if (neighboringBlock.isOf(Blocks.VINE) && neighboringBlock.get(VineBlock.getFacingProperty(direction.getOpposite()))) {
                                                        while(neighboringBlock.getMaterial() == Material.REPLACEABLE_PLANT){
                                                            currentChunk.setBlockState(mutable2, isBelowSealevel ? Blocks.WATER.getDefaultState() : Blocks.CAVE_AIR.getDefaultState(), false);
                                                            neighboringBlock = currentChunk.getBlockState(mutable2.move(Direction.DOWN));
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
}