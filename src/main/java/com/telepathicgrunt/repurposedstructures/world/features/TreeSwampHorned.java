package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.block.*;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.Structure;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.BitSetVoxelSet;
import net.minecraft.util.shape.VoxelSet;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.*;


public class TreeSwampHorned extends Feature<TreeFeatureConfig> {
    private static final BlockState TRUNK = Blocks.OAK_LOG.getDefaultState();
    private static final BlockState LEAF = Blocks.OAK_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, Integer.valueOf(1));


    public TreeSwampHorned(Codec<TreeFeatureConfig> config) {
        super(config);
    }


    public boolean generate(ServerWorldAccess serverWorldAccess, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, TreeFeatureConfig treeFeatureConfig) {
        Set<BlockPos> set = Sets.newHashSet();
        Set<BlockPos> set2 = Sets.newHashSet();
        Set<BlockPos> set3 = Sets.newHashSet();
        BlockBox blockBox = BlockBox.empty();
        boolean bl = this.generate(serverWorldAccess, random, blockPos, set, set2, blockBox, treeFeatureConfig);
        if (blockBox.minX <= blockBox.maxX && bl && !set.isEmpty()) {
            if (!treeFeatureConfig.decorators.isEmpty()) {
                List<BlockPos> list = Lists.newArrayList(set);
                List<BlockPos> list2 = Lists.newArrayList(set2);
                list.sort(Comparator.comparingInt(Vec3i::getY));
                list2.sort(Comparator.comparingInt(Vec3i::getY));
                treeFeatureConfig.decorators.forEach((decorator) -> {
                    decorator.generate(serverWorldAccess, random, list, list2, set3, blockBox);
                });
            }

            VoxelSet voxelSet = this.placeLogsAndLeaves(serverWorldAccess, blockBox, set, set3);
            Structure.updateCorner(serverWorldAccess, 3, voxelSet, blockBox.minX, blockBox.minY, blockBox.minZ);
            return true;
        } else {
            return false;
        }
    }


    // generate the spooky horned swamp m trees
    private boolean generate(ServerWorldAccess world, Random random, BlockPos position, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox box, TreeFeatureConfig config) {
        int height = random.nextInt(4) + 6;

        // checks to see if there is room to generate tree
        if (!this.isSpaceAt(world, position, height)) {
            return false;
        }

        // sets tree in water if there is water below
        for (; world.getBlockState(position.down()).getMaterial() == Material.WATER; position = position.down()) {
            ;
        }

        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + height + 1 <= 256) {
            for (int y = position.getY(); y <= position.getY() + 1 + height; ++y) {
                int radius = 1;

                if (y == position.getY()) {
                    radius = 0;
                }

                if (y >= position.getY() + 1 + height - 2) {
                    radius = 3;
                }

                BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable();

                for (int x = position.getX() - radius; x <= position.getX() + radius && flag; ++x) {
                    for (int z = position.getZ() - radius; z <= position.getZ() + radius && flag; ++z) {
                        if (y >= 0 && y < 256) {
                            blockpos$Mutable.set(x, y, z);
                            if (!isAirOrLeaves(world, blockpos$Mutable)) {
                                if (isWater(world, blockpos$Mutable)) {
                                    flag = false;
                                } else if (y > position.getY()) {
                                    flag = false;
                                }
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else if (isDirtOrGrass(world, position.down()) && position.getY() < world.getHeight() - height - 1) {

                for (int currentHeight = position.getY() - 4 + height; currentHeight <= position.getY() + height; ++currentHeight) {
                    int heightDiff = currentHeight - (position.getY() + height);
                    int l2 = 2 - heightDiff / 2;

                    for (int x = position.getX() - l2 - 1; x <= position.getX() + l2; ++x) {
                        int xPos = x - position.getX();

                        for (int z = position.getZ() - l2 - 1; z <= position.getZ() + l2; ++z) {
                            int zPos = z - position.getZ();
                            int isCornerIfThisIsTwo = 0;

                            if (xPos == l2) {
                                isCornerIfThisIsTwo++;
                            }
                            if (zPos == l2) {
                                isCornerIfThisIsTwo++;
                            }
                            if (xPos == -l2 - 1) {
                                isCornerIfThisIsTwo++;
                            }
                            if (zPos == -l2 - 1) {
                                isCornerIfThisIsTwo++;
                            }

                            // generate leaves if is in corners or if 2/3rd rng is true
                            if (isCornerIfThisIsTwo == 2 || random.nextInt(3) < 2 && heightDiff != 0) {
                                BlockPos blockpos = new BlockPos(x, currentHeight, z);

                                if (isAirOrLeaves(world, blockpos) || isReplaceablePlant(world, blockpos)) {
                                    this.setBlockState(world, blockpos, LEAF);
                                }
                            }
                        }
                    }
                }

                // the following four for statements generates the trunk of the tree
                genTrunk(world, position, height);
                genTrunk(world, position.west(), height);
                genTrunk(world, position.north(), height);
                genTrunk(world, position.west().north(), height);

                // vine generation
                for (int currentHeight = position.getY() - 3 + height; currentHeight <= position.getY() + height; ++currentHeight) {
                    int heightDiff = currentHeight - (position.getY() + height);
                    int i3 = 2 - heightDiff / 2;
                    BlockPos.Mutable blockpos$Mutable1 = new BlockPos.Mutable();

                    for (int x = position.getX() - i3 - 1; x <= position.getX() + i3; ++x) {
                        for (int z = position.getZ() - i3 - 1; z <= position.getZ() + i3; ++z) {
                            blockpos$Mutable1.set(x, currentHeight, z);

                            if (world.getBlockState(blockpos$Mutable1).getMaterial() == Material.LEAVES) {
                                BlockPos blockpos3 = blockpos$Mutable1.west();
                                BlockPos blockpos4 = blockpos$Mutable1.east();
                                BlockPos blockpos1 = blockpos$Mutable1.north();
                                BlockPos blockpos2 = blockpos$Mutable1.south();

                                if (random.nextInt(4) == 0 && world.isAir(blockpos3)) {
                                    this.addVine(world, blockpos3, VineBlock.EAST);
                                }

                                if (random.nextInt(4) == 0 && world.isAir(blockpos4)) {
                                    this.addVine(world, blockpos4, VineBlock.WEST);
                                }

                                if (random.nextInt(4) == 0 && world.isAir(blockpos1)) {
                                    this.addVine(world, blockpos1, VineBlock.SOUTH);
                                }

                                if (random.nextInt(4) == 0 && world.isAir(blockpos2)) {
                                    this.addVine(world, blockpos2, VineBlock.NORTH);
                                }
                            }
                        }
                    }
                }

                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }


    private void addVine(ServerWorldAccess world, BlockPos pos, BooleanProperty prop) {
        BlockState iblockstate = Blocks.VINE.getDefaultState().with(prop, Boolean.valueOf(true));
        this.setBlockState(world, pos, iblockstate);
        int i = 4;

        for (BlockPos blockpos = pos.down(); world.isAir(blockpos) && i > 0; --i) {
            this.setBlockState(world, blockpos, iblockstate);
            blockpos = blockpos.down();
        }
    }


    private void genTrunk(ServerWorldAccess world, BlockPos position, int height) {
        this.setBlockState(world, position.down(), Blocks.DIRT.getDefaultState());

        for (int currentHeight = 0; currentHeight < height; ++currentHeight) {
            BlockPos upN = position.up(currentHeight);
            BlockState iblockstate1 = world.getBlockState(upN);
            Block block2 = iblockstate1.getBlock();

            if (currentHeight != height - 1 && world.isAir(upN) || iblockstate1.isIn(BlockTags.LEAVES) || block2 == Blocks.WATER || block2 == Blocks.LILY_PAD) {
                this.setBlockState(world, upN, TRUNK);
            } else {
                this.setBlockState(world, upN, LEAF);
            }
        }
    }


    private boolean isSpaceAt(ServerWorldAccess world, BlockPos leavesPos, int height) {
        boolean spaceFound = true;
        if (leavesPos.getY() >= 1 && leavesPos.getY() + height + 1 <= world.getHeight()) {
            for (int y = 0; y <= 1 + height; ++y) {
                int radius = 2;
                if (y == 0) {
                    radius = 1;
                } else if (y >= 1 + height - 2) {
                    radius = 2;
                }

                for (int x = -radius; x <= radius && spaceFound; ++x) {
                    for (int z = -radius; z <= radius && spaceFound; ++z) {
                        if (leavesPos.getY() + y < 0 || leavesPos.getY() + y >= world.getHeight() || !canTreeReplace(world, leavesPos.add(x, y, z))) {
                            spaceFound = false;
                        }
                    }
                }
            }

            return spaceFound;
        } else {
            return false;
        }
    }


    public static boolean canTreeReplace(TestableWorld world, BlockPos pos) {
        return canReplace(world, pos) || world.testBlockState(pos, (state) -> {
            return state.isIn(BlockTags.LOGS);
        });
    }

    private static boolean isWater(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> {
            return state.isOf(Blocks.WATER);
        });
    }


    public static boolean isAirOrLeaves(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> {
            return state.isAir() || state.isIn(BlockTags.LEAVES);
        });
    }


    private static boolean isDirtOrGrass(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> {
            Block block = state.getBlock();
            return isDirt(block) || block == Blocks.FARMLAND;
        });
    }


    private static boolean isReplaceablePlant(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> {
            Material material = state.getMaterial();
            return material == Material.REPLACEABLE_PLANT;
        });
    }


    public static void setBlockStateWithoutUpdatingNeighbors(ModifiableWorld world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state, 19);
    }


    public static boolean canReplace(TestableWorld testableWorld, BlockPos pos) {
        return isAirOrLeaves(testableWorld, pos) || isReplaceablePlant(testableWorld, pos) || isWater(testableWorld, pos);
    }


    private VoxelSet placeLogsAndLeaves(WorldAccess world, BlockBox box, Set<BlockPos> logs, Set<BlockPos> leaves) {
        List<Set<BlockPos>> list = Lists.newArrayList();
        VoxelSet voxelSet = new BitSetVoxelSet(box.getBlockCountX(), box.getBlockCountY(), box.getBlockCountZ());

        for (int j = 0; j < 6; ++j) {
            list.add(Sets.newHashSet());
        }

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        Iterator<BlockPos> var9 = Lists.newArrayList(leaves).iterator();

        BlockPos blockPos2;
        while (var9.hasNext()) {
            blockPos2 = var9.next();
            if (box.contains(blockPos2)) {
                voxelSet.set(blockPos2.getX() - box.minX, blockPos2.getY() - box.minY, blockPos2.getZ() - box.minZ, true, true);
            }
        }

        var9 = Lists.newArrayList(logs).iterator();

        while (var9.hasNext()) {
            blockPos2 = var9.next();
            if (box.contains(blockPos2)) {
                voxelSet.set(blockPos2.getX() - box.minX, blockPos2.getY() - box.minY, blockPos2.getZ() - box.minZ, true, true);
            }

            Direction[] var11 = Direction.values();
            int var12 = var11.length;

            for (int var13 = 0; var13 < var12; ++var13) {
                Direction direction = var11[var13];
                mutable.set(blockPos2, direction);
                if (!logs.contains(mutable)) {
                    BlockState blockState = world.getBlockState(mutable);
                    if (blockState.contains(Properties.DISTANCE_1_7)) {
                        list.get(0).add(mutable.toImmutable());
                        setBlockStateWithoutUpdatingNeighbors(world, mutable, (BlockState) blockState.with(Properties.DISTANCE_1_7, 1));
                        if (box.contains(mutable)) {
                            voxelSet.set(mutable.getX() - box.minX, mutable.getY() - box.minY, mutable.getZ() - box.minZ, true, true);
                        }
                    }
                }
            }
        }

        for (int k = 1; k < 6; ++k) {
            Set<BlockPos> set = list.get(k - 1);
            Set<BlockPos> set2 = list.get(k);
            Iterator<BlockPos> var25 = set.iterator();

            while (var25.hasNext()) {
                BlockPos blockPos3 = var25.next();
                if (box.contains(blockPos3)) {
                    voxelSet.set(blockPos3.getX() - box.minX, blockPos3.getY() - box.minY, blockPos3.getZ() - box.minZ, true, true);
                }

                Direction[] var27 = Direction.values();
                int var28 = var27.length;

                for (int var16 = 0; var16 < var28; ++var16) {
                    Direction direction2 = var27[var16];
                    mutable.set(blockPos3, direction2);
                    if (!set.contains(mutable) && !set2.contains(mutable)) {
                        BlockState blockState2 = world.getBlockState(mutable);
                        if (blockState2.contains(Properties.DISTANCE_1_7)) {
                            int l = (Integer) blockState2.get(Properties.DISTANCE_1_7);
                            if (l > k + 1) {
                                BlockState blockState3 = (BlockState) blockState2.with(Properties.DISTANCE_1_7, k + 1);
                                setBlockStateWithoutUpdatingNeighbors(world, mutable, blockState3);
                                if (box.contains(mutable)) {
                                    voxelSet.set(mutable.getX() - box.minX, mutable.getY() - box.minY, mutable.getZ() - box.minZ, true, true);
                                }

                                set2.add(mutable.toImmutable());
                            }
                        }
                    }
                }
            }
        }

        return voxelSet;
    }
}