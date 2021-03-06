package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructurePieces;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MansionPieces  {
    public static void generateMansion(TemplateManager p_191152_0_, BlockPos p_191152_1_, Rotation p_191152_2_, List<MansionPieces.MansionTemplate> p_191152_3_, Random p_191152_4_, MansionTemplate.MANSIONTYPE type) {
        MansionPieces.Grid MansionPieces$grid = new MansionPieces.Grid(p_191152_4_);
        MansionPieces.Placer MansionPieces$placer = new MansionPieces.Placer(p_191152_0_, p_191152_4_);
        MansionPieces$placer.createMansion(p_191152_1_, p_191152_2_, p_191152_3_, MansionPieces$grid, type);
    }

    static class FirstFloor extends MansionPieces.RoomCollection {
        private FirstFloor() {
        }

        public String get1x1(Random p_191104_1_) {
            return "1x1_a" + (p_191104_1_.nextInt(5) + 1);
        }

        public String get1x1Secret(Random p_191099_1_) {
            return "1x1_as" + (p_191099_1_.nextInt(4) + 1);
        }

        public String get1x2SideEntrance(Random p_191100_1_, boolean p_191100_2_) {
            return "1x2_a" + (p_191100_1_.nextInt(9) + 1);
        }

        public String get1x2FrontEntrance(Random p_191098_1_, boolean p_191098_2_) {
            return "1x2_b" + (p_191098_1_.nextInt(5) + 1);
        }

        public String get1x2Secret(Random p_191102_1_) {
            return "1x2_s" + (p_191102_1_.nextInt(2) + 1);
        }

        public String get2x2(Random p_191101_1_) {
            return "2x2_a" + (p_191101_1_.nextInt(4) + 1);
        }

        public String get2x2Secret(Random p_191103_1_) {
            return "2x2_s1";
        }
    }

    static class Grid {
        private final Random random;
        private final MansionPieces.SimpleGrid baseGrid;
        private final MansionPieces.SimpleGrid thirdFloorGrid;
        private final MansionPieces.SimpleGrid[] floorRooms;
        private final int entranceX;
        private final int entranceY;

        public Grid(Random p_i47362_1_) {
            this.random = p_i47362_1_;
            this.entranceX = 7;
            this.entranceY = 4;
            this.baseGrid = new MansionPieces.SimpleGrid(11, 11, 5);
            this.baseGrid.set(this.entranceX, this.entranceY, this.entranceX + 1, this.entranceY + 1, 3);
            this.baseGrid.set(this.entranceX - 1, this.entranceY, this.entranceX - 1, this.entranceY + 1, 2);
            this.baseGrid.set(this.entranceX + 2, this.entranceY - 2, this.entranceX + 3, this.entranceY + 3, 5);
            this.baseGrid.set(this.entranceX + 1, this.entranceY - 2, this.entranceX + 1, this.entranceY - 1, 1);
            this.baseGrid.set(this.entranceX + 1, this.entranceY + 2, this.entranceX + 1, this.entranceY + 3, 1);
            this.baseGrid.set(this.entranceX - 1, this.entranceY - 1, 1);
            this.baseGrid.set(this.entranceX - 1, this.entranceY + 2, 1);
            this.baseGrid.set(0, 0, 11, 1, 5);
            this.baseGrid.set(0, 9, 11, 11, 5);
            this.recursiveCorridor(this.baseGrid, this.entranceX, this.entranceY - 2, Direction.WEST, 6);
            this.recursiveCorridor(this.baseGrid, this.entranceX, this.entranceY + 3, Direction.WEST, 6);
            this.recursiveCorridor(this.baseGrid, this.entranceX - 2, this.entranceY - 1, Direction.WEST, 3);
            this.recursiveCorridor(this.baseGrid, this.entranceX - 2, this.entranceY + 2, Direction.WEST, 3);

            while(this.cleanEdges(this.baseGrid)) {
            }

            this.floorRooms = new MansionPieces.SimpleGrid[3];
            this.floorRooms[0] = new MansionPieces.SimpleGrid(11, 11, 5);
            this.floorRooms[1] = new MansionPieces.SimpleGrid(11, 11, 5);
            this.floorRooms[2] = new MansionPieces.SimpleGrid(11, 11, 5);
            this.identifyRooms(this.baseGrid, this.floorRooms[0]);
            this.identifyRooms(this.baseGrid, this.floorRooms[1]);
            this.floorRooms[0].set(this.entranceX + 1, this.entranceY, this.entranceX + 1, this.entranceY + 1, 8388608);
            this.floorRooms[1].set(this.entranceX + 1, this.entranceY, this.entranceX + 1, this.entranceY + 1, 8388608);
            this.thirdFloorGrid = new MansionPieces.SimpleGrid(this.baseGrid.width, this.baseGrid.height, 5);
            this.setupThirdFloor();
            this.identifyRooms(this.thirdFloorGrid, this.floorRooms[2]);
        }

        public static boolean isHouse(MansionPieces.SimpleGrid p_191109_0_, int p_191109_1_, int p_191109_2_) {
            int i = p_191109_0_.get(p_191109_1_, p_191109_2_);
            return i == 1 || i == 2 || i == 3 || i == 4;
        }

        public boolean isRoomId(MansionPieces.SimpleGrid p_191114_1_, int p_191114_2_, int p_191114_3_, int p_191114_4_, int p_191114_5_) {
            return (this.floorRooms[p_191114_4_].get(p_191114_2_, p_191114_3_) & '\uffff') == p_191114_5_;
        }

        @Nullable
        public Direction get1x2RoomDirection(MansionPieces.SimpleGrid p_191113_1_, int p_191113_2_, int p_191113_3_, int p_191113_4_, int p_191113_5_) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                if (this.isRoomId(p_191113_1_, p_191113_2_ + direction.getStepX(), p_191113_3_ + direction.getStepZ(), p_191113_4_, p_191113_5_)) {
                    return direction;
                }
            }

            return null;
        }

        private void recursiveCorridor(MansionPieces.SimpleGrid p_191110_1_, int p_191110_2_, int p_191110_3_, Direction p_191110_4_, int p_191110_5_) {
            if (p_191110_5_ > 0) {
                p_191110_1_.set(p_191110_2_, p_191110_3_, 1);
                p_191110_1_.setIf(p_191110_2_ + p_191110_4_.getStepX(), p_191110_3_ + p_191110_4_.getStepZ(), 0, 1);

                for(int i = 0; i < 8; ++i) {
                    Direction direction = Direction.from2DDataValue(this.random.nextInt(4));
                    if (direction != p_191110_4_.getOpposite() && (direction != Direction.EAST || !this.random.nextBoolean())) {
                        int j = p_191110_2_ + p_191110_4_.getStepX();
                        int k = p_191110_3_ + p_191110_4_.getStepZ();
                        if (p_191110_1_.get(j + direction.getStepX(), k + direction.getStepZ()) == 0 && p_191110_1_.get(j + direction.getStepX() * 2, k + direction.getStepZ() * 2) == 0) {
                            this.recursiveCorridor(p_191110_1_, p_191110_2_ + p_191110_4_.getStepX() + direction.getStepX(), p_191110_3_ + p_191110_4_.getStepZ() + direction.getStepZ(), direction, p_191110_5_ - 1);
                            break;
                        }
                    }
                }

                Direction direction1 = p_191110_4_.getClockWise();
                Direction direction2 = p_191110_4_.getCounterClockWise();
                p_191110_1_.setIf(p_191110_2_ + direction1.getStepX(), p_191110_3_ + direction1.getStepZ(), 0, 2);
                p_191110_1_.setIf(p_191110_2_ + direction2.getStepX(), p_191110_3_ + direction2.getStepZ(), 0, 2);
                p_191110_1_.setIf(p_191110_2_ + p_191110_4_.getStepX() + direction1.getStepX(), p_191110_3_ + p_191110_4_.getStepZ() + direction1.getStepZ(), 0, 2);
                p_191110_1_.setIf(p_191110_2_ + p_191110_4_.getStepX() + direction2.getStepX(), p_191110_3_ + p_191110_4_.getStepZ() + direction2.getStepZ(), 0, 2);
                p_191110_1_.setIf(p_191110_2_ + p_191110_4_.getStepX() * 2, p_191110_3_ + p_191110_4_.getStepZ() * 2, 0, 2);
                p_191110_1_.setIf(p_191110_2_ + direction1.getStepX() * 2, p_191110_3_ + direction1.getStepZ() * 2, 0, 2);
                p_191110_1_.setIf(p_191110_2_ + direction2.getStepX() * 2, p_191110_3_ + direction2.getStepZ() * 2, 0, 2);
            }
        }

        private boolean cleanEdges(MansionPieces.SimpleGrid p_191111_1_) {
            boolean flag = false;

            for(int i = 0; i < p_191111_1_.height; ++i) {
                for(int j = 0; j < p_191111_1_.width; ++j) {
                    if (p_191111_1_.get(j, i) == 0) {
                        int k = 0;
                        k = k + (isHouse(p_191111_1_, j + 1, i) ? 1 : 0);
                        k = k + (isHouse(p_191111_1_, j - 1, i) ? 1 : 0);
                        k = k + (isHouse(p_191111_1_, j, i + 1) ? 1 : 0);
                        k = k + (isHouse(p_191111_1_, j, i - 1) ? 1 : 0);
                        if (k >= 3) {
                            p_191111_1_.set(j, i, 2);
                            flag = true;
                        } else if (k == 2) {
                            int l = 0;
                            l = l + (isHouse(p_191111_1_, j + 1, i + 1) ? 1 : 0);
                            l = l + (isHouse(p_191111_1_, j - 1, i + 1) ? 1 : 0);
                            l = l + (isHouse(p_191111_1_, j + 1, i - 1) ? 1 : 0);
                            l = l + (isHouse(p_191111_1_, j - 1, i - 1) ? 1 : 0);
                            if (l <= 1) {
                                p_191111_1_.set(j, i, 2);
                                flag = true;
                            }
                        }
                    }
                }
            }

            return flag;
        }

        private void setupThirdFloor() {
            List<Tuple<Integer, Integer>> list = Lists.newArrayList();
            MansionPieces.SimpleGrid MansionPieces$simplegrid = this.floorRooms[1];

            for(int i = 0; i < this.thirdFloorGrid.height; ++i) {
                for(int j = 0; j < this.thirdFloorGrid.width; ++j) {
                    int k = MansionPieces$simplegrid.get(j, i);
                    int l = k & 983040;
                    if (l == 131072 && (k & 2097152) == 2097152) {
                        list.add(new Tuple<>(j, i));
                    }
                }
            }

            if (list.isEmpty()) {
                this.thirdFloorGrid.set(0, 0, this.thirdFloorGrid.width, this.thirdFloorGrid.height, 5);
            } else {
                Tuple<Integer, Integer> tuple = list.get(this.random.nextInt(list.size()));
                int l1 = MansionPieces$simplegrid.get(tuple.getA(), tuple.getB());
                MansionPieces$simplegrid.set(tuple.getA(), tuple.getB(), l1 | 4194304);
                Direction direction1 = this.get1x2RoomDirection(this.baseGrid, tuple.getA(), tuple.getB(), 1, l1 & '\uffff');
                int i2 = tuple.getA() + direction1.getStepX();
                int i1 = tuple.getB() + direction1.getStepZ();

                for(int j1 = 0; j1 < this.thirdFloorGrid.height; ++j1) {
                    for(int k1 = 0; k1 < this.thirdFloorGrid.width; ++k1) {
                        if (!isHouse(this.baseGrid, k1, j1)) {
                            this.thirdFloorGrid.set(k1, j1, 5);
                        } else if (k1 == tuple.getA() && j1 == tuple.getB()) {
                            this.thirdFloorGrid.set(k1, j1, 3);
                        } else if (k1 == i2 && j1 == i1) {
                            this.thirdFloorGrid.set(k1, j1, 3);
                            this.floorRooms[2].set(k1, j1, 8388608);
                        }
                    }
                }

                List<Direction> list1 = Lists.newArrayList();

                for(Direction direction : Direction.Plane.HORIZONTAL) {
                    if (this.thirdFloorGrid.get(i2 + direction.getStepX(), i1 + direction.getStepZ()) == 0) {
                        list1.add(direction);
                    }
                }

                if (list1.isEmpty()) {
                    this.thirdFloorGrid.set(0, 0, this.thirdFloorGrid.width, this.thirdFloorGrid.height, 5);
                    MansionPieces$simplegrid.set(tuple.getA(), tuple.getB(), l1);
                } else {
                    Direction direction2 = list1.get(this.random.nextInt(list1.size()));
                    this.recursiveCorridor(this.thirdFloorGrid, i2 + direction2.getStepX(), i1 + direction2.getStepZ(), direction2, 4);

                    while(this.cleanEdges(this.thirdFloorGrid)) {
                    }

                }
            }
        }

        private void identifyRooms(MansionPieces.SimpleGrid p_191116_1_, MansionPieces.SimpleGrid p_191116_2_) {
            List<Tuple<Integer, Integer>> list = Lists.newArrayList();

            for(int i = 0; i < p_191116_1_.height; ++i) {
                for(int j = 0; j < p_191116_1_.width; ++j) {
                    if (p_191116_1_.get(j, i) == 2) {
                        list.add(new Tuple<>(j, i));
                    }
                }
            }

            Collections.shuffle(list, this.random);
            int k3 = 10;

            for(Tuple<Integer, Integer> tuple : list) {
                int k = tuple.getA();
                int l = tuple.getB();
                if (p_191116_2_.get(k, l) == 0) {
                    int i1 = k;
                    int j1 = k;
                    int k1 = l;
                    int l1 = l;
                    int i2 = 65536;
                    if (p_191116_2_.get(k + 1, l) == 0 && p_191116_2_.get(k, l + 1) == 0 && p_191116_2_.get(k + 1, l + 1) == 0 && p_191116_1_.get(k + 1, l) == 2 && p_191116_1_.get(k, l + 1) == 2 && p_191116_1_.get(k + 1, l + 1) == 2) {
                        j1 = k + 1;
                        l1 = l + 1;
                        i2 = 262144;
                    } else if (p_191116_2_.get(k - 1, l) == 0 && p_191116_2_.get(k, l + 1) == 0 && p_191116_2_.get(k - 1, l + 1) == 0 && p_191116_1_.get(k - 1, l) == 2 && p_191116_1_.get(k, l + 1) == 2 && p_191116_1_.get(k - 1, l + 1) == 2) {
                        i1 = k - 1;
                        l1 = l + 1;
                        i2 = 262144;
                    } else if (p_191116_2_.get(k - 1, l) == 0 && p_191116_2_.get(k, l - 1) == 0 && p_191116_2_.get(k - 1, l - 1) == 0 && p_191116_1_.get(k - 1, l) == 2 && p_191116_1_.get(k, l - 1) == 2 && p_191116_1_.get(k - 1, l - 1) == 2) {
                        i1 = k - 1;
                        k1 = l - 1;
                        i2 = 262144;
                    } else if (p_191116_2_.get(k + 1, l) == 0 && p_191116_1_.get(k + 1, l) == 2) {
                        j1 = k + 1;
                        i2 = 131072;
                    } else if (p_191116_2_.get(k, l + 1) == 0 && p_191116_1_.get(k, l + 1) == 2) {
                        l1 = l + 1;
                        i2 = 131072;
                    } else if (p_191116_2_.get(k - 1, l) == 0 && p_191116_1_.get(k - 1, l) == 2) {
                        i1 = k - 1;
                        i2 = 131072;
                    } else if (p_191116_2_.get(k, l - 1) == 0 && p_191116_1_.get(k, l - 1) == 2) {
                        k1 = l - 1;
                        i2 = 131072;
                    }

                    int j2 = this.random.nextBoolean() ? i1 : j1;
                    int k2 = this.random.nextBoolean() ? k1 : l1;
                    int l2 = 2097152;
                    if (!p_191116_1_.edgesTo(j2, k2, 1)) {
                        j2 = j2 == i1 ? j1 : i1;
                        k2 = k2 == k1 ? l1 : k1;
                        if (!p_191116_1_.edgesTo(j2, k2, 1)) {
                            k2 = k2 == k1 ? l1 : k1;
                            if (!p_191116_1_.edgesTo(j2, k2, 1)) {
                                j2 = j2 == i1 ? j1 : i1;
                                k2 = k2 == k1 ? l1 : k1;
                                if (!p_191116_1_.edgesTo(j2, k2, 1)) {
                                    l2 = 0;
                                    j2 = i1;
                                    k2 = k1;
                                }
                            }
                        }
                    }

                    for(int i3 = k1; i3 <= l1; ++i3) {
                        for(int j3 = i1; j3 <= j1; ++j3) {
                            if (j3 == j2 && i3 == k2) {
                                p_191116_2_.set(j3, i3, 1048576 | l2 | i2 | k3);
                            } else {
                                p_191116_2_.set(j3, i3, i2 | k3);
                            }
                        }
                    }

                    ++k3;
                }
            }

        }
    }

    static class PlacementData {
        public Rotation rotation;
        public BlockPos position;
        public String wallType;

        private PlacementData() {
        }
    }

    static class Placer {
        private final TemplateManager templateManager;
        private final Random random;
        private int startX;
        private int startY;

        public Placer(TemplateManager p_i47361_1_, Random p_i47361_2_) {
            this.templateManager = p_i47361_1_;
            this.random = p_i47361_2_;
        }

        public void createMansion(BlockPos p_191125_1_, Rotation p_191125_2_, List<MansionPieces.MansionTemplate> p_191125_3_, MansionPieces.Grid p_191125_4_, MansionTemplate.MANSIONTYPE type) {
            MansionPieces.PlacementData MansionPieces$placementdata = new MansionPieces.PlacementData();
            MansionPieces$placementdata.position = p_191125_1_;
            MansionPieces$placementdata.rotation = p_191125_2_;
            MansionPieces$placementdata.wallType = "wall_flat";
            MansionPieces.PlacementData MansionPieces$placementdata1 = new MansionPieces.PlacementData();
            this.entrance(p_191125_3_, MansionPieces$placementdata, type);
            MansionPieces$placementdata1.position = MansionPieces$placementdata.position.above(8);
            MansionPieces$placementdata1.rotation = MansionPieces$placementdata.rotation;
            MansionPieces$placementdata1.wallType = "wall_window";
            if (!p_191125_3_.isEmpty()) {
            }

            MansionPieces.SimpleGrid MansionPieces$simplegrid = p_191125_4_.baseGrid;
            MansionPieces.SimpleGrid MansionPieces$simplegrid1 = p_191125_4_.thirdFloorGrid;
            this.startX = p_191125_4_.entranceX + 1;
            this.startY = p_191125_4_.entranceY + 1;
            int i = p_191125_4_.entranceX + 1;
            int j = p_191125_4_.entranceY;
            this.traverseOuterWalls(p_191125_3_, MansionPieces$placementdata, MansionPieces$simplegrid, Direction.SOUTH, this.startX, this.startY, i, j, type);
            this.traverseOuterWalls(p_191125_3_, MansionPieces$placementdata1, MansionPieces$simplegrid, Direction.SOUTH, this.startX, this.startY, i, j, type);
            MansionPieces.PlacementData MansionPieces$placementdata2 = new MansionPieces.PlacementData();
            MansionPieces$placementdata2.position = MansionPieces$placementdata.position.above(19);
            MansionPieces$placementdata2.rotation = MansionPieces$placementdata.rotation;
            MansionPieces$placementdata2.wallType = "wall_window";
            boolean flag = false;

            for(int k = 0; k < MansionPieces$simplegrid1.height && !flag; ++k) {
                for(int l = MansionPieces$simplegrid1.width - 1; l >= 0 && !flag; --l) {
                    if (MansionPieces.Grid.isHouse(MansionPieces$simplegrid1, l, k)) {
                        MansionPieces$placementdata2.position = MansionPieces$placementdata2.position.relative(p_191125_2_.rotate(Direction.SOUTH), 8 + (k - this.startY) * 8);
                        MansionPieces$placementdata2.position = MansionPieces$placementdata2.position.relative(p_191125_2_.rotate(Direction.EAST), (l - this.startX) * 8);
                        this.traverseWallPiece(p_191125_3_, MansionPieces$placementdata2, type);
                        this.traverseOuterWalls(p_191125_3_, MansionPieces$placementdata2, MansionPieces$simplegrid1, Direction.SOUTH, l, k, l, k, type);
                        flag = true;
                    }
                }
            }

            this.createRoof(p_191125_3_, p_191125_1_.above(16), p_191125_2_, MansionPieces$simplegrid, MansionPieces$simplegrid1, type);
            this.createRoof(p_191125_3_, p_191125_1_.above(27), p_191125_2_, MansionPieces$simplegrid1, null, type);
            if (!p_191125_3_.isEmpty()) {
            }

            MansionPieces.RoomCollection[] aMansionPieces$roomcollection = new MansionPieces.RoomCollection[]{new MansionPieces.FirstFloor(), new MansionPieces.SecondFloor(), new MansionPieces.ThirdFloor()};

            for(int l2 = 0; l2 < 3; ++l2) {
                BlockPos blockpos = p_191125_1_.above(8 * l2 + (l2 == 2 ? 3 : 0));
                MansionPieces.SimpleGrid MansionPieces$simplegrid2 = p_191125_4_.floorRooms[l2];
                MansionPieces.SimpleGrid MansionPieces$simplegrid3 = l2 == 2 ? MansionPieces$simplegrid1 : MansionPieces$simplegrid;
                String s = l2 == 0 ? "carpet_south_1" : "carpet_south_2";
                String s1 = l2 == 0 ? "carpet_west_1" : "carpet_west_2";

                for(int i1 = 0; i1 < MansionPieces$simplegrid3.height; ++i1) {
                    for(int j1 = 0; j1 < MansionPieces$simplegrid3.width; ++j1) {
                        if (MansionPieces$simplegrid3.get(j1, i1) == 1) {
                            BlockPos blockpos1 = blockpos.relative(p_191125_2_.rotate(Direction.SOUTH), 8 + (i1 - this.startY) * 8);
                            blockpos1 = blockpos1.relative(p_191125_2_.rotate(Direction.EAST), (j1 - this.startX) * 8);
                            p_191125_3_.add(new MansionPieces.MansionTemplate(this.templateManager, "corridor_floor", blockpos1, p_191125_2_, type));
                            if (MansionPieces$simplegrid3.get(j1, i1 - 1) == 1 || (MansionPieces$simplegrid2.get(j1, i1 - 1) & 8388608) == 8388608) {
                                p_191125_3_.add(new MansionPieces.MansionTemplate(this.templateManager, "carpet_north", blockpos1.relative(p_191125_2_.rotate(Direction.EAST), 1).above(), p_191125_2_, type));
                            }

                            if (MansionPieces$simplegrid3.get(j1 + 1, i1) == 1 || (MansionPieces$simplegrid2.get(j1 + 1, i1) & 8388608) == 8388608) {
                                p_191125_3_.add(new MansionPieces.MansionTemplate(this.templateManager, "carpet_east", blockpos1.relative(p_191125_2_.rotate(Direction.SOUTH), 1).relative(p_191125_2_.rotate(Direction.EAST), 5).above(), p_191125_2_, type));
                            }

                            if (MansionPieces$simplegrid3.get(j1, i1 + 1) == 1 || (MansionPieces$simplegrid2.get(j1, i1 + 1) & 8388608) == 8388608) {
                                p_191125_3_.add(new MansionPieces.MansionTemplate(this.templateManager, s, blockpos1.relative(p_191125_2_.rotate(Direction.SOUTH), 5).relative(p_191125_2_.rotate(Direction.WEST), 1), p_191125_2_, type));
                            }

                            if (MansionPieces$simplegrid3.get(j1 - 1, i1) == 1 || (MansionPieces$simplegrid2.get(j1 - 1, i1) & 8388608) == 8388608) {
                                p_191125_3_.add(new MansionPieces.MansionTemplate(this.templateManager, s1, blockpos1.relative(p_191125_2_.rotate(Direction.WEST), 1).relative(p_191125_2_.rotate(Direction.NORTH), 1), p_191125_2_, type));
                            }
                        }
                    }
                }

                String s2 = l2 == 0 ? "indoors_wall_1" : "indoors_wall_2";
                String s3 = l2 == 0 ? "indoors_door_1" : "indoors_door_2";
                List<Direction> list = Lists.newArrayList();

                for(int k1 = 0; k1 < MansionPieces$simplegrid3.height; ++k1) {
                    for(int l1 = 0; l1 < MansionPieces$simplegrid3.width; ++l1) {
                        boolean flag1 = l2 == 2 && MansionPieces$simplegrid3.get(l1, k1) == 3;
                        if (MansionPieces$simplegrid3.get(l1, k1) == 2 || flag1) {
                            int i2 = MansionPieces$simplegrid2.get(l1, k1);
                            int j2 = i2 & 983040;
                            int k2 = i2 & '\uffff';
                            flag1 = flag1 && (i2 & 8388608) == 8388608;
                            list.clear();
                            if ((i2 & 2097152) == 2097152) {
                                for(Direction direction : Direction.Plane.HORIZONTAL) {
                                    if (MansionPieces$simplegrid3.get(l1 + direction.getStepX(), k1 + direction.getStepZ()) == 1) {
                                        list.add(direction);
                                    }
                                }
                            }

                            Direction direction1 = null;
                            if (!list.isEmpty()) {
                                direction1 = list.get(this.random.nextInt(list.size()));
                            } else if ((i2 & 1048576) == 1048576) {
                                direction1 = Direction.UP;
                            }

                            BlockPos blockpos3 = blockpos.relative(p_191125_2_.rotate(Direction.SOUTH), 8 + (k1 - this.startY) * 8);
                            blockpos3 = blockpos3.relative(p_191125_2_.rotate(Direction.EAST), -1 + (l1 - this.startX) * 8);
                            if (MansionPieces.Grid.isHouse(MansionPieces$simplegrid3, l1 - 1, k1) && !p_191125_4_.isRoomId(MansionPieces$simplegrid3, l1 - 1, k1, l2, k2)) {
                                p_191125_3_.add(new MansionPieces.MansionTemplate(this.templateManager, direction1 == Direction.WEST ? s3 : s2, blockpos3, p_191125_2_, type));
                            }

                            if (MansionPieces$simplegrid3.get(l1 + 1, k1) == 1 && !flag1) {
                                BlockPos blockpos2 = blockpos3.relative(p_191125_2_.rotate(Direction.EAST), 8);
                                p_191125_3_.add(new MansionPieces.MansionTemplate(this.templateManager, direction1 == Direction.EAST ? s3 : s2, blockpos2, p_191125_2_, type));
                            }

                            if (MansionPieces.Grid.isHouse(MansionPieces$simplegrid3, l1, k1 + 1) && !p_191125_4_.isRoomId(MansionPieces$simplegrid3, l1, k1 + 1, l2, k2)) {
                                BlockPos blockpos4 = blockpos3.relative(p_191125_2_.rotate(Direction.SOUTH), 7);
                                blockpos4 = blockpos4.relative(p_191125_2_.rotate(Direction.EAST), 7);
                                p_191125_3_.add(new MansionPieces.MansionTemplate(this.templateManager, direction1 == Direction.SOUTH ? s3 : s2, blockpos4, p_191125_2_.getRotated(Rotation.CLOCKWISE_90), type));
                            }

                            if (MansionPieces$simplegrid3.get(l1, k1 - 1) == 1 && !flag1) {
                                BlockPos blockpos5 = blockpos3.relative(p_191125_2_.rotate(Direction.NORTH), 1);
                                blockpos5 = blockpos5.relative(p_191125_2_.rotate(Direction.EAST), 7);
                                p_191125_3_.add(new MansionPieces.MansionTemplate(this.templateManager, direction1 == Direction.NORTH ? s3 : s2, blockpos5, p_191125_2_.getRotated(Rotation.CLOCKWISE_90), type));
                            }

                            if (j2 == 65536) {
                                this.addRoom1x1(p_191125_3_, blockpos3, p_191125_2_, direction1, aMansionPieces$roomcollection[l2], type);
                            } else if (j2 == 131072 && direction1 != null) {
                                Direction direction3 = p_191125_4_.get1x2RoomDirection(MansionPieces$simplegrid3, l1, k1, l2, k2);
                                boolean flag2 = (i2 & 4194304) == 4194304;
                                this.addRoom1x2(p_191125_3_, blockpos3, p_191125_2_, direction3, direction1, aMansionPieces$roomcollection[l2], flag2, type);
                            } else if (j2 == 262144 && direction1 != null && direction1 != Direction.UP) {
                                Direction direction2 = direction1.getClockWise();
                                if (!p_191125_4_.isRoomId(MansionPieces$simplegrid3, l1 + direction2.getStepX(), k1 + direction2.getStepZ(), l2, k2)) {
                                    direction2 = direction2.getOpposite();
                                }

                                this.addRoom2x2(p_191125_3_, blockpos3, p_191125_2_, direction2, direction1, aMansionPieces$roomcollection[l2], type);
                            } else if (j2 == 262144 && direction1 == Direction.UP) {
                                this.addRoom2x2Secret(p_191125_3_, blockpos3, p_191125_2_, aMansionPieces$roomcollection[l2], type);
                            }
                        }
                    }
                }
            }

        }

        private void traverseOuterWalls(List<MansionPieces.MansionTemplate> p_191130_1_, MansionPieces.PlacementData p_191130_2_, MansionPieces.SimpleGrid p_191130_3_, Direction p_191130_4_, int p_191130_5_, int p_191130_6_, int p_191130_7_, int p_191130_8_, MansionTemplate.MANSIONTYPE type) {
            int i = p_191130_5_;
            int j = p_191130_6_;
            Direction direction = p_191130_4_;

            do {
                if (!MansionPieces.Grid.isHouse(p_191130_3_, i + p_191130_4_.getStepX(), j + p_191130_4_.getStepZ())) {
                    this.traverseTurn(p_191130_1_, p_191130_2_, type);
                    p_191130_4_ = p_191130_4_.getClockWise();
                    if (i != p_191130_7_ || j != p_191130_8_ || direction != p_191130_4_) {
                        this.traverseWallPiece(p_191130_1_, p_191130_2_, type);
                    }
                } else if (MansionPieces.Grid.isHouse(p_191130_3_, i + p_191130_4_.getStepX(), j + p_191130_4_.getStepZ()) && MansionPieces.Grid.isHouse(p_191130_3_, i + p_191130_4_.getStepX() + p_191130_4_.getCounterClockWise().getStepX(), j + p_191130_4_.getStepZ() + p_191130_4_.getCounterClockWise().getStepZ())) {
                    this.traverseInnerTurn(p_191130_1_, p_191130_2_);
                    i += p_191130_4_.getStepX();
                    j += p_191130_4_.getStepZ();
                    p_191130_4_ = p_191130_4_.getCounterClockWise();
                } else {
                    i += p_191130_4_.getStepX();
                    j += p_191130_4_.getStepZ();
                    if (i != p_191130_7_ || j != p_191130_8_ || direction != p_191130_4_) {
                        this.traverseWallPiece(p_191130_1_, p_191130_2_, type);
                    }
                }
            } while(i != p_191130_7_ || j != p_191130_8_ || direction != p_191130_4_);

        }

        private void createRoof(List<MansionPieces.MansionTemplate> p_191123_1_, BlockPos p_191123_2_, Rotation p_191123_3_, MansionPieces.SimpleGrid p_191123_4_, @Nullable MansionPieces.SimpleGrid p_191123_5_, MansionTemplate.MANSIONTYPE type) {
            for(int i = 0; i < p_191123_4_.height; ++i) {
                for(int j = 0; j < p_191123_4_.width; ++j) {
                    BlockPos lvt_8_3_ = p_191123_2_.relative(p_191123_3_.rotate(Direction.SOUTH), 8 + (i - this.startY) * 8);
                    lvt_8_3_ = lvt_8_3_.relative(p_191123_3_.rotate(Direction.EAST), (j - this.startX) * 8);
                    boolean flag = p_191123_5_ != null && MansionPieces.Grid.isHouse(p_191123_5_, j, i);
                    if (MansionPieces.Grid.isHouse(p_191123_4_, j, i) && !flag) {
                        p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "roof", lvt_8_3_.above(3), p_191123_3_, type));
                        if (!MansionPieces.Grid.isHouse(p_191123_4_, j + 1, i)) {
                            BlockPos blockpos1 = lvt_8_3_.relative(p_191123_3_.rotate(Direction.EAST), 6);
                            p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "roof_front", blockpos1, p_191123_3_, type));
                        }

                        if (!MansionPieces.Grid.isHouse(p_191123_4_, j - 1, i)) {
                            BlockPos blockpos5 = lvt_8_3_.relative(p_191123_3_.rotate(Direction.EAST), 0);
                            blockpos5 = blockpos5.relative(p_191123_3_.rotate(Direction.SOUTH), 7);
                            p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "roof_front", blockpos5, p_191123_3_.getRotated(Rotation.CLOCKWISE_180), type));
                        }

                        if (!MansionPieces.Grid.isHouse(p_191123_4_, j, i - 1)) {
                            BlockPos blockpos6 = lvt_8_3_.relative(p_191123_3_.rotate(Direction.WEST), 1);
                            p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "roof_front", blockpos6, p_191123_3_.getRotated(Rotation.COUNTERCLOCKWISE_90), type));
                        }

                        if (!MansionPieces.Grid.isHouse(p_191123_4_, j, i + 1)) {
                            BlockPos blockpos7 = lvt_8_3_.relative(p_191123_3_.rotate(Direction.EAST), 6);
                            blockpos7 = blockpos7.relative(p_191123_3_.rotate(Direction.SOUTH), 6);
                            p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "roof_front", blockpos7, p_191123_3_.getRotated(Rotation.CLOCKWISE_90), type));
                        }
                    }
                }
            }

            if (p_191123_5_ != null) {
                for(int k = 0; k < p_191123_4_.height; ++k) {
                    for(int i1 = 0; i1 < p_191123_4_.width; ++i1) {
                        BlockPos blockpos3 = p_191123_2_.relative(p_191123_3_.rotate(Direction.SOUTH), 8 + (k - this.startY) * 8);
                        blockpos3 = blockpos3.relative(p_191123_3_.rotate(Direction.EAST), (i1 - this.startX) * 8);
                        boolean flag1 = MansionPieces.Grid.isHouse(p_191123_5_, i1, k);
                        if (MansionPieces.Grid.isHouse(p_191123_4_, i1, k) && flag1) {
                            if (!MansionPieces.Grid.isHouse(p_191123_4_, i1 + 1, k)) {
                                BlockPos blockpos8 = blockpos3.relative(p_191123_3_.rotate(Direction.EAST), 7);
                                p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "small_wall", blockpos8, p_191123_3_, type));
                            }

                            if (!MansionPieces.Grid.isHouse(p_191123_4_, i1 - 1, k)) {
                                BlockPos blockpos9 = blockpos3.relative(p_191123_3_.rotate(Direction.WEST), 1);
                                blockpos9 = blockpos9.relative(p_191123_3_.rotate(Direction.SOUTH), 6);
                                p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "small_wall", blockpos9, p_191123_3_.getRotated(Rotation.CLOCKWISE_180), type));
                            }

                            if (!MansionPieces.Grid.isHouse(p_191123_4_, i1, k - 1)) {
                                BlockPos blockpos10 = blockpos3.relative(p_191123_3_.rotate(Direction.WEST), 0);
                                blockpos10 = blockpos10.relative(p_191123_3_.rotate(Direction.NORTH), 1);
                                p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "small_wall", blockpos10, p_191123_3_.getRotated(Rotation.COUNTERCLOCKWISE_90), type));
                            }

                            if (!MansionPieces.Grid.isHouse(p_191123_4_, i1, k + 1)) {
                                BlockPos blockpos11 = blockpos3.relative(p_191123_3_.rotate(Direction.EAST), 6);
                                blockpos11 = blockpos11.relative(p_191123_3_.rotate(Direction.SOUTH), 7);
                                p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "small_wall", blockpos11, p_191123_3_.getRotated(Rotation.CLOCKWISE_90), type));
                            }

                            if (!MansionPieces.Grid.isHouse(p_191123_4_, i1 + 1, k)) {
                                if (!MansionPieces.Grid.isHouse(p_191123_4_, i1, k - 1)) {
                                    BlockPos blockpos12 = blockpos3.relative(p_191123_3_.rotate(Direction.EAST), 7);
                                    blockpos12 = blockpos12.relative(p_191123_3_.rotate(Direction.NORTH), 2);
                                    p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "small_wall_corner", blockpos12, p_191123_3_, type));
                                }

                                if (!MansionPieces.Grid.isHouse(p_191123_4_, i1, k + 1)) {
                                    BlockPos blockpos13 = blockpos3.relative(p_191123_3_.rotate(Direction.EAST), 8);
                                    blockpos13 = blockpos13.relative(p_191123_3_.rotate(Direction.SOUTH), 7);
                                    p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "small_wall_corner", blockpos13, p_191123_3_.getRotated(Rotation.CLOCKWISE_90), type));
                                }
                            }

                            if (!MansionPieces.Grid.isHouse(p_191123_4_, i1 - 1, k)) {
                                if (!MansionPieces.Grid.isHouse(p_191123_4_, i1, k - 1)) {
                                    BlockPos blockpos14 = blockpos3.relative(p_191123_3_.rotate(Direction.WEST), 2);
                                    blockpos14 = blockpos14.relative(p_191123_3_.rotate(Direction.NORTH), 1);
                                    p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "small_wall_corner", blockpos14, p_191123_3_.getRotated(Rotation.COUNTERCLOCKWISE_90), type));
                                }

                                if (!MansionPieces.Grid.isHouse(p_191123_4_, i1, k + 1)) {
                                    BlockPos blockpos15 = blockpos3.relative(p_191123_3_.rotate(Direction.WEST), 1);
                                    blockpos15 = blockpos15.relative(p_191123_3_.rotate(Direction.SOUTH), 8);
                                    p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "small_wall_corner", blockpos15, p_191123_3_.getRotated(Rotation.CLOCKWISE_180), type));
                                }
                            }
                        }
                    }
                }
            }

            for(int l = 0; l < p_191123_4_.height; ++l) {
                for(int j1 = 0; j1 < p_191123_4_.width; ++j1) {
                    BlockPos blockpos4 = p_191123_2_.relative(p_191123_3_.rotate(Direction.SOUTH), 8 + (l - this.startY) * 8);
                    blockpos4 = blockpos4.relative(p_191123_3_.rotate(Direction.EAST), (j1 - this.startX) * 8);
                    boolean flag2 = p_191123_5_ != null && MansionPieces.Grid.isHouse(p_191123_5_, j1, l);
                    if (MansionPieces.Grid.isHouse(p_191123_4_, j1, l) && !flag2) {
                        if (!MansionPieces.Grid.isHouse(p_191123_4_, j1 + 1, l)) {
                            BlockPos blockpos16 = blockpos4.relative(p_191123_3_.rotate(Direction.EAST), 6);
                            if (!MansionPieces.Grid.isHouse(p_191123_4_, j1, l + 1)) {
                                BlockPos blockpos2 = blockpos16.relative(p_191123_3_.rotate(Direction.SOUTH), 6);
                                p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "roof_corner", blockpos2, p_191123_3_, type));
                            } else if (MansionPieces.Grid.isHouse(p_191123_4_, j1 + 1, l + 1)) {
                                BlockPos blockpos18 = blockpos16.relative(p_191123_3_.rotate(Direction.SOUTH), 5);
                                p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "roof_inner_corner", blockpos18, p_191123_3_, type));
                            }

                            if (!MansionPieces.Grid.isHouse(p_191123_4_, j1, l - 1)) {
                                p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "roof_corner", blockpos16, p_191123_3_.getRotated(Rotation.COUNTERCLOCKWISE_90), type));
                            } else if (MansionPieces.Grid.isHouse(p_191123_4_, j1 + 1, l - 1)) {
                                BlockPos blockpos19 = blockpos4.relative(p_191123_3_.rotate(Direction.EAST), 9);
                                blockpos19 = blockpos19.relative(p_191123_3_.rotate(Direction.NORTH), 2);
                                p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "roof_inner_corner", blockpos19, p_191123_3_.getRotated(Rotation.CLOCKWISE_90), type));
                            }
                        }

                        if (!MansionPieces.Grid.isHouse(p_191123_4_, j1 - 1, l)) {
                            BlockPos blockpos17 = blockpos4.relative(p_191123_3_.rotate(Direction.EAST), 0);
                            blockpos17 = blockpos17.relative(p_191123_3_.rotate(Direction.SOUTH), 0);
                            if (!MansionPieces.Grid.isHouse(p_191123_4_, j1, l + 1)) {
                                BlockPos blockpos20 = blockpos17.relative(p_191123_3_.rotate(Direction.SOUTH), 6);
                                p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "roof_corner", blockpos20, p_191123_3_.getRotated(Rotation.CLOCKWISE_90), type));
                            } else if (MansionPieces.Grid.isHouse(p_191123_4_, j1 - 1, l + 1)) {
                                BlockPos blockpos21 = blockpos17.relative(p_191123_3_.rotate(Direction.SOUTH), 8);
                                blockpos21 = blockpos21.relative(p_191123_3_.rotate(Direction.WEST), 3);
                                p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "roof_inner_corner", blockpos21, p_191123_3_.getRotated(Rotation.COUNTERCLOCKWISE_90), type));
                            }

                            if (!MansionPieces.Grid.isHouse(p_191123_4_, j1, l - 1)) {
                                p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "roof_corner", blockpos17, p_191123_3_.getRotated(Rotation.CLOCKWISE_180), type));
                            } else if (MansionPieces.Grid.isHouse(p_191123_4_, j1 - 1, l - 1)) {
                                BlockPos blockpos22 = blockpos17.relative(p_191123_3_.rotate(Direction.SOUTH), 1);
                                p_191123_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "roof_inner_corner", blockpos22, p_191123_3_.getRotated(Rotation.CLOCKWISE_180), type));
                            }
                        }
                    }
                }
            }

        }

        private void entrance(List<MansionPieces.MansionTemplate> p_191133_1_, MansionPieces.PlacementData p_191133_2_, MansionTemplate.MANSIONTYPE type) {
            Direction direction = p_191133_2_.rotation.rotate(Direction.WEST);
            p_191133_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "entrance", p_191133_2_.position.relative(direction, 9), p_191133_2_.rotation, type));
            p_191133_2_.position = p_191133_2_.position.relative(p_191133_2_.rotation.rotate(Direction.SOUTH), 16);
        }

        private void traverseWallPiece(List<MansionPieces.MansionTemplate> p_191131_1_, MansionPieces.PlacementData p_191131_2_, MansionTemplate.MANSIONTYPE type) {
            p_191131_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191131_2_.wallType, p_191131_2_.position.relative(p_191131_2_.rotation.rotate(Direction.EAST), 7), p_191131_2_.rotation, type));
            p_191131_2_.position = p_191131_2_.position.relative(p_191131_2_.rotation.rotate(Direction.SOUTH), 8);
        }

        private void traverseTurn(List<MansionPieces.MansionTemplate> p_191124_1_, MansionPieces.PlacementData p_191124_2_, MansionTemplate.MANSIONTYPE type) {
            p_191124_2_.position = p_191124_2_.position.relative(p_191124_2_.rotation.rotate(Direction.SOUTH), -1);
            p_191124_1_.add(new MansionPieces.MansionTemplate(this.templateManager, "wall_corner", p_191124_2_.position, p_191124_2_.rotation, type));
            p_191124_2_.position = p_191124_2_.position.relative(p_191124_2_.rotation.rotate(Direction.SOUTH), -7);
            p_191124_2_.position = p_191124_2_.position.relative(p_191124_2_.rotation.rotate(Direction.WEST), -6);
            p_191124_2_.rotation = p_191124_2_.rotation.getRotated(Rotation.CLOCKWISE_90);
        }

        private void traverseInnerTurn(List<MansionPieces.MansionTemplate> p_191126_1_, MansionPieces.PlacementData p_191126_2_) {
            p_191126_2_.position = p_191126_2_.position.relative(p_191126_2_.rotation.rotate(Direction.SOUTH), 6);
            p_191126_2_.position = p_191126_2_.position.relative(p_191126_2_.rotation.rotate(Direction.EAST), 8);
            p_191126_2_.rotation = p_191126_2_.rotation.getRotated(Rotation.COUNTERCLOCKWISE_90);
        }

        private void addRoom1x1(List<MansionPieces.MansionTemplate> p_191129_1_, BlockPos p_191129_2_, Rotation p_191129_3_, Direction p_191129_4_, MansionPieces.RoomCollection p_191129_5_, MansionTemplate.MANSIONTYPE type) {
            Rotation rotation = Rotation.NONE;
            String s = p_191129_5_.get1x1(this.random);
            if (p_191129_4_ != Direction.EAST) {
                if (p_191129_4_ == Direction.NORTH) {
                    rotation = rotation.getRotated(Rotation.COUNTERCLOCKWISE_90);
                } else if (p_191129_4_ == Direction.WEST) {
                    rotation = rotation.getRotated(Rotation.CLOCKWISE_180);
                } else if (p_191129_4_ == Direction.SOUTH) {
                    rotation = rotation.getRotated(Rotation.CLOCKWISE_90);
                } else {
                    s = p_191129_5_.get1x1Secret(this.random);
                }
            }

            BlockPos blockpos = Template.getZeroPositionWithTransform(new BlockPos(1, 0, 0), Mirror.NONE, rotation, 7, 7);
            rotation = rotation.getRotated(p_191129_3_);
            blockpos = blockpos.rotate(p_191129_3_);
            BlockPos blockpos1 = p_191129_2_.offset(blockpos.getX(), 0, blockpos.getZ());
            p_191129_1_.add(new MansionPieces.MansionTemplate(this.templateManager, s, blockpos1, rotation, type));
        }

        private void addRoom1x2(List<MansionPieces.MansionTemplate> p_191132_1_, BlockPos p_191132_2_, Rotation p_191132_3_, Direction p_191132_4_, Direction p_191132_5_, MansionPieces.RoomCollection p_191132_6_, boolean p_191132_7_, MansionTemplate.MANSIONTYPE type) {
            if (p_191132_5_ == Direction.EAST && p_191132_4_ == Direction.SOUTH) {
                BlockPos blockpos13 = p_191132_2_.relative(p_191132_3_.rotate(Direction.EAST), 1);
                p_191132_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos13, p_191132_3_, type));
            } else if (p_191132_5_ == Direction.EAST && p_191132_4_ == Direction.NORTH) {
                BlockPos blockpos12 = p_191132_2_.relative(p_191132_3_.rotate(Direction.EAST), 1);
                blockpos12 = blockpos12.relative(p_191132_3_.rotate(Direction.SOUTH), 6);
                p_191132_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos12, p_191132_3_, Mirror.LEFT_RIGHT, type));
            } else if (p_191132_5_ == Direction.WEST && p_191132_4_ == Direction.NORTH) {
                BlockPos blockpos11 = p_191132_2_.relative(p_191132_3_.rotate(Direction.EAST), 7);
                blockpos11 = blockpos11.relative(p_191132_3_.rotate(Direction.SOUTH), 6);
                p_191132_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos11, p_191132_3_.getRotated(Rotation.CLOCKWISE_180), type));
            } else if (p_191132_5_ == Direction.WEST && p_191132_4_ == Direction.SOUTH) {
                BlockPos blockpos10 = p_191132_2_.relative(p_191132_3_.rotate(Direction.EAST), 7);
                p_191132_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos10, p_191132_3_, Mirror.FRONT_BACK, type));
            } else if (p_191132_5_ == Direction.SOUTH && p_191132_4_ == Direction.EAST) {
                BlockPos blockpos9 = p_191132_2_.relative(p_191132_3_.rotate(Direction.EAST), 1);
                p_191132_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos9, p_191132_3_.getRotated(Rotation.CLOCKWISE_90), Mirror.LEFT_RIGHT, type));
            } else if (p_191132_5_ == Direction.SOUTH && p_191132_4_ == Direction.WEST) {
                BlockPos blockpos8 = p_191132_2_.relative(p_191132_3_.rotate(Direction.EAST), 7);
                p_191132_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos8, p_191132_3_.getRotated(Rotation.CLOCKWISE_90), type));
            } else if (p_191132_5_ == Direction.NORTH && p_191132_4_ == Direction.WEST) {
                BlockPos blockpos7 = p_191132_2_.relative(p_191132_3_.rotate(Direction.EAST), 7);
                blockpos7 = blockpos7.relative(p_191132_3_.rotate(Direction.SOUTH), 6);
                p_191132_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos7, p_191132_3_.getRotated(Rotation.CLOCKWISE_90), Mirror.FRONT_BACK, type));
            } else if (p_191132_5_ == Direction.NORTH && p_191132_4_ == Direction.EAST) {
                BlockPos blockpos6 = p_191132_2_.relative(p_191132_3_.rotate(Direction.EAST), 1);
                blockpos6 = blockpos6.relative(p_191132_3_.rotate(Direction.SOUTH), 6);
                p_191132_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191132_6_.get1x2SideEntrance(this.random, p_191132_7_), blockpos6, p_191132_3_.getRotated(Rotation.COUNTERCLOCKWISE_90), type));
            } else if (p_191132_5_ == Direction.SOUTH && p_191132_4_ == Direction.NORTH) {
                BlockPos blockpos5 = p_191132_2_.relative(p_191132_3_.rotate(Direction.EAST), 1);
                blockpos5 = blockpos5.relative(p_191132_3_.rotate(Direction.NORTH), 8);
                p_191132_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191132_6_.get1x2FrontEntrance(this.random, p_191132_7_), blockpos5, p_191132_3_, type));
            } else if (p_191132_5_ == Direction.NORTH && p_191132_4_ == Direction.SOUTH) {
                BlockPos blockpos4 = p_191132_2_.relative(p_191132_3_.rotate(Direction.EAST), 7);
                blockpos4 = blockpos4.relative(p_191132_3_.rotate(Direction.SOUTH), 14);
                p_191132_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191132_6_.get1x2FrontEntrance(this.random, p_191132_7_), blockpos4, p_191132_3_.getRotated(Rotation.CLOCKWISE_180), type));
            } else if (p_191132_5_ == Direction.WEST && p_191132_4_ == Direction.EAST) {
                BlockPos blockpos3 = p_191132_2_.relative(p_191132_3_.rotate(Direction.EAST), 15);
                p_191132_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191132_6_.get1x2FrontEntrance(this.random, p_191132_7_), blockpos3, p_191132_3_.getRotated(Rotation.CLOCKWISE_90), type));
            } else if (p_191132_5_ == Direction.EAST && p_191132_4_ == Direction.WEST) {
                BlockPos blockpos2 = p_191132_2_.relative(p_191132_3_.rotate(Direction.WEST), 7);
                blockpos2 = blockpos2.relative(p_191132_3_.rotate(Direction.SOUTH), 6);
                p_191132_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191132_6_.get1x2FrontEntrance(this.random, p_191132_7_), blockpos2, p_191132_3_.getRotated(Rotation.COUNTERCLOCKWISE_90), type));
            } else if (p_191132_5_ == Direction.UP && p_191132_4_ == Direction.EAST) {
                BlockPos blockpos1 = p_191132_2_.relative(p_191132_3_.rotate(Direction.EAST), 15);
                p_191132_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191132_6_.get1x2Secret(this.random), blockpos1, p_191132_3_.getRotated(Rotation.CLOCKWISE_90), type));
            } else if (p_191132_5_ == Direction.UP && p_191132_4_ == Direction.SOUTH) {
                BlockPos blockpos = p_191132_2_.relative(p_191132_3_.rotate(Direction.EAST), 1);
                blockpos = blockpos.relative(p_191132_3_.rotate(Direction.NORTH), 0);
                p_191132_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191132_6_.get1x2Secret(this.random), blockpos, p_191132_3_, type));
            }

        }

        private void addRoom2x2(List<MansionPieces.MansionTemplate> p_191127_1_, BlockPos p_191127_2_, Rotation p_191127_3_, Direction p_191127_4_, Direction p_191127_5_, MansionPieces.RoomCollection p_191127_6_, MansionTemplate.MANSIONTYPE type) {
            int i = 0;
            int j = 0;
            Rotation rotation = p_191127_3_;
            Mirror mirror = Mirror.NONE;
            if (p_191127_5_ == Direction.EAST && p_191127_4_ == Direction.SOUTH) {
                i = -7;
            } else if (p_191127_5_ == Direction.EAST && p_191127_4_ == Direction.NORTH) {
                i = -7;
                j = 6;
                mirror = Mirror.LEFT_RIGHT;
            } else if (p_191127_5_ == Direction.NORTH && p_191127_4_ == Direction.EAST) {
                i = 1;
                j = 14;
                rotation = p_191127_3_.getRotated(Rotation.COUNTERCLOCKWISE_90);
            } else if (p_191127_5_ == Direction.NORTH && p_191127_4_ == Direction.WEST) {
                i = 7;
                j = 14;
                rotation = p_191127_3_.getRotated(Rotation.COUNTERCLOCKWISE_90);
                mirror = Mirror.LEFT_RIGHT;
            } else if (p_191127_5_ == Direction.SOUTH && p_191127_4_ == Direction.WEST) {
                i = 7;
                j = -8;
                rotation = p_191127_3_.getRotated(Rotation.CLOCKWISE_90);
            } else if (p_191127_5_ == Direction.SOUTH && p_191127_4_ == Direction.EAST) {
                i = 1;
                j = -8;
                rotation = p_191127_3_.getRotated(Rotation.CLOCKWISE_90);
                mirror = Mirror.LEFT_RIGHT;
            } else if (p_191127_5_ == Direction.WEST && p_191127_4_ == Direction.NORTH) {
                i = 15;
                j = 6;
                rotation = p_191127_3_.getRotated(Rotation.CLOCKWISE_180);
            } else if (p_191127_5_ == Direction.WEST && p_191127_4_ == Direction.SOUTH) {
                i = 15;
                mirror = Mirror.FRONT_BACK;
            }

            BlockPos blockpos = p_191127_2_.relative(p_191127_3_.rotate(Direction.EAST), i);
            blockpos = blockpos.relative(p_191127_3_.rotate(Direction.SOUTH), j);
            p_191127_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191127_6_.get2x2(this.random), blockpos, rotation, mirror, type));
        }

        private void addRoom2x2Secret(List<MansionPieces.MansionTemplate> p_191128_1_, BlockPos p_191128_2_, Rotation p_191128_3_, MansionPieces.RoomCollection p_191128_4_, MansionTemplate.MANSIONTYPE type) {
            BlockPos blockpos = p_191128_2_.relative(p_191128_3_.rotate(Direction.EAST), 1);
            p_191128_1_.add(new MansionPieces.MansionTemplate(this.templateManager, p_191128_4_.get2x2Secret(this.random), blockpos, p_191128_3_, Mirror.NONE, type));
        }
    }

    abstract static class RoomCollection {
        private RoomCollection() {
        }

        public abstract String get1x1(Random p_191104_1_);

        public abstract String get1x1Secret(Random p_191099_1_);

        public abstract String get1x2SideEntrance(Random p_191100_1_, boolean p_191100_2_);

        public abstract String get1x2FrontEntrance(Random p_191098_1_, boolean p_191098_2_);

        public abstract String get1x2Secret(Random p_191102_1_);

        public abstract String get2x2(Random p_191101_1_);

        public abstract String get2x2Secret(Random p_191103_1_);
    }

    static class SecondFloor extends MansionPieces.RoomCollection {
        private SecondFloor() {
        }

        public String get1x1(Random p_191104_1_) {
            return "1x1_b" + (p_191104_1_.nextInt(4) + 1);
        }

        public String get1x1Secret(Random p_191099_1_) {
            return "1x1_as" + (p_191099_1_.nextInt(4) + 1);
        }

        public String get1x2SideEntrance(Random p_191100_1_, boolean p_191100_2_) {
            return p_191100_2_ ? "1x2_c_stairs" : "1x2_c" + (p_191100_1_.nextInt(4) + 1);
        }

        public String get1x2FrontEntrance(Random p_191098_1_, boolean p_191098_2_) {
            return p_191098_2_ ? "1x2_d_stairs" : "1x2_d" + (p_191098_1_.nextInt(5) + 1);
        }

        public String get1x2Secret(Random p_191102_1_) {
            return "1x2_se" + (p_191102_1_.nextInt(1) + 1);
        }

        public String get2x2(Random p_191101_1_) {
            return "2x2_b" + (p_191101_1_.nextInt(5) + 1);
        }

        public String get2x2Secret(Random p_191103_1_) {
            return "2x2_s1";
        }
    }

    static class SimpleGrid {
        private final int[][] grid;
        private final int width;
        private final int height;
        private final int valueIfOutside;

        public SimpleGrid(int p_i47358_1_, int p_i47358_2_, int p_i47358_3_) {
            this.width = p_i47358_1_;
            this.height = p_i47358_2_;
            this.valueIfOutside = p_i47358_3_;
            this.grid = new int[p_i47358_1_][p_i47358_2_];
        }

        public void set(int p_191144_1_, int p_191144_2_, int p_191144_3_) {
            if (p_191144_1_ >= 0 && p_191144_1_ < this.width && p_191144_2_ >= 0 && p_191144_2_ < this.height) {
                this.grid[p_191144_1_][p_191144_2_] = p_191144_3_;
            }

        }

        public void set(int p_191142_1_, int p_191142_2_, int p_191142_3_, int p_191142_4_, int p_191142_5_) {
            for(int i = p_191142_2_; i <= p_191142_4_; ++i) {
                for(int j = p_191142_1_; j <= p_191142_3_; ++j) {
                    this.set(j, i, p_191142_5_);
                }
            }

        }

        public int get(int p_191145_1_, int p_191145_2_) {
            return p_191145_1_ >= 0 && p_191145_1_ < this.width && p_191145_2_ >= 0 && p_191145_2_ < this.height ? this.grid[p_191145_1_][p_191145_2_] : this.valueIfOutside;
        }

        public void setIf(int p_197588_1_, int p_197588_2_, int p_197588_3_, int p_197588_4_) {
            if (this.get(p_197588_1_, p_197588_2_) == p_197588_3_) {
                this.set(p_197588_1_, p_197588_2_, p_197588_4_);
            }

        }

        public boolean edgesTo(int p_191147_1_, int p_191147_2_, int p_191147_3_) {
            return this.get(p_191147_1_ - 1, p_191147_2_) == p_191147_3_ || this.get(p_191147_1_ + 1, p_191147_2_) == p_191147_3_ || this.get(p_191147_1_, p_191147_2_ + 1) == p_191147_3_ || this.get(p_191147_1_, p_191147_2_ - 1) == p_191147_3_;
        }
    }

    static class ThirdFloor extends MansionPieces.SecondFloor {
        private ThirdFloor() {
        }
    }

    public static class MansionTemplate extends TemplateStructurePiece {
        private final String templateName;
        private final Rotation rotation;
        private final Mirror mirror;
        private final MANSIONTYPE type;

        public enum MANSIONTYPE {
            BIRCH(Blocks.DARK_OAK_WOOD.defaultBlockState()),
            OAK(Blocks.DARK_OAK_WOOD.defaultBlockState()),
            TAIGA(Blocks.COBBLESTONE.defaultBlockState()),
            JUNGLE(Blocks.MOSSY_COBBLESTONE.defaultBlockState()),
            SAVANNA(Blocks.DARK_OAK_WOOD.defaultBlockState()),
            SNOWY(Blocks.SNOW_BLOCK.defaultBlockState()),
            DESERT(Blocks.SANDSTONE.defaultBlockState());

            private final BlockState foundationBlock;

            MANSIONTYPE(BlockState foundationBlock) {
                this.foundationBlock = foundationBlock;
            }

            public BlockState getFoundationBlock(){
                return foundationBlock;
            }
        }

        public MansionTemplate(TemplateManager templateManager, String templateName, BlockPos blockPos, Rotation rotation, MANSIONTYPE type) {
            this(templateManager, templateName, blockPos, rotation, Mirror.NONE, type);
        }

        public MansionTemplate(TemplateManager templateManager, String templateName, BlockPos blockPos, Rotation rotation, Mirror mirror, MANSIONTYPE type) {
            super(RSStructurePieces.MANSION_PIECE, 0);
            this.templateName = templateName;
            this.templatePosition = blockPos;
            this.rotation = rotation;
            this.mirror = mirror;
            this.type = type;
            this.loadTemplate(templateManager);
        }

        public MansionTemplate(TemplateManager templateManager, CompoundNBT compoundNBT) {
            super(RSStructurePieces.MANSION_PIECE, compoundNBT);
            this.templateName = compoundNBT.getString("Template");
            this.rotation = Rotation.valueOf(compoundNBT.getString("Rot"));
            this.mirror = Mirror.valueOf(compoundNBT.getString("Mi"));
            this.type = MANSIONTYPE.valueOf(compoundNBT.getString("Type"));
            this.loadTemplate(templateManager);
        }

        private void loadTemplate(TemplateManager templateManager) {
            Template template = templateManager.getOrCreate(new ResourceLocation(RepurposedStructures.MODID, "mansions/" + type.name().toLowerCase() + "/" + this.templateName));
            PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(false).setRotation(this.rotation).setMirror(this.mirror).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            this.setup(template, this.templatePosition, placementsettings);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void addAdditionalSaveData(CompoundNBT compoundNBT) {
            super.addAdditionalSaveData(compoundNBT);
            compoundNBT.putString("Template", this.templateName);
            compoundNBT.putString("Rot", this.placeSettings.getRotation().name());
            compoundNBT.putString("Mi", this.placeSettings.getMirror().name());
            compoundNBT.putString("Type", this.type.name());
        }

        protected void handleDataMarker(String p_186175_1_, BlockPos p_186175_2_, IServerWorld p_186175_3_, Random p_186175_4_, MutableBoundingBox p_186175_5_) {
        }
    }

}
