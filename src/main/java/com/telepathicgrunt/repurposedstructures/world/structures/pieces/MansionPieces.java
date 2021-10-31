package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MansionPieces  {
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
    
    public static void generateMansion(DynamicRegistries dynamicRegistryManager, TemplateManager templateManager, BlockPos blockPos, Rotation rotation, List<StructurePiece> mansionTemplates, Random p_191152_4_, MANSIONTYPE type) {
        MansionPieces.Grid MansionPieces$grid = new MansionPieces.Grid(p_191152_4_);
        MansionPieces.Placer MansionPieces$placer = new MansionPieces.Placer(templateManager, p_191152_4_);
        MansionPieces$placer.createMansion(dynamicRegistryManager, blockPos, rotation, mansionTemplates, MansionPieces$grid, type);
    }

    abstract static class RoomCollection {
        protected final String floor;
        protected final MANSIONTYPE type;

        private RoomCollection(String floor, MANSIONTYPE type) {
            this.floor = floor;
            this.type = type;
        }

        public abstract String get1x1(Random p_191104_1_);

        public abstract String get1x1Secret(Random p_191099_1_);

        public abstract String get1x2SideEntrance(Random p_191100_1_, boolean p_191100_2_);

        public abstract String get1x2FrontEntrance(Random p_191098_1_, boolean p_191098_2_);

        public abstract String get1x2Secret(Random p_191102_1_);

        public abstract String get2x2(Random p_191101_1_);

        public abstract String get2x2Secret(Random p_191103_1_);
    }

    static class FirstFloor extends MansionPieces.RoomCollection {
        private FirstFloor(MANSIONTYPE type) {
            super("first_floor", type);
        }

        public String get1x1(Random random) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_1x1_rooms";
        }

        public String get1x1Secret(Random random) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_1x1_secret_rooms";
        }

        public String get1x2SideEntrance(Random random, boolean isStairs) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_1x2_rooms";
        }

        public String get1x2FrontEntrance(Random random, boolean isStairs) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_1x2_alternative_rooms";
        }

        public String get1x2Secret(Random random) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_1x2_secret_rooms";
        }

        public String get2x2(Random random) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_2x2_rooms";
        }

        public String get2x2Secret(Random random) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_2x2_secret_rooms";
        }
    }


    static class SecondFloor extends MansionPieces.RoomCollection {
        private SecondFloor(MANSIONTYPE type) {
            super("second_floor", type);
        }

        public String get1x1(Random random) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_1x1_rooms";
        }

        public String get1x1Secret(Random random) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_1x1_secret_rooms";
        }

        public String get1x2SideEntrance(Random random, boolean isStairs) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor +
                    (isStairs ? "_1x2_c_stairs" : "_1x2_rooms");
        }

        public String get1x2FrontEntrance(Random random, boolean isStairs) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor +
                    (isStairs ? "_1x2_d_stairs" : "_1x2_alternative_rooms");
        }

        public String get1x2Secret(Random random) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_1x2_secret_rooms";
        }

        public String get2x2(Random random) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_2x2_rooms";
        }

        public String get2x2Secret(Random random) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_2x2_secret_rooms";
        }
    }

    static class ThirdFloor extends MansionPieces.RoomCollection {
        private ThirdFloor(MANSIONTYPE type) {
            super("third_floor", type);
        }

        public String get1x1(Random random) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_1x1_rooms";
        }

        public String get1x1Secret(Random random) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_1x1_secret_rooms";
        }

        public String get1x2SideEntrance(Random random, boolean isStairs) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor +
                    (isStairs ? "_1x2_c_stairs" : "_1x2_rooms");
        }

        public String get1x2FrontEntrance(Random random, boolean isStairs) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor +
                    (isStairs ? "_1x2_d_stairs" : "_1x2_alternative_rooms");
        }

        public String get1x2Secret(Random random) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_1x2_secret_rooms";
        }

        public String get2x2(Random random) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_2x2_rooms";
        }

        public String get2x2Secret(Random random) {
            return RepurposedStructures.MODID + ":mansions/" + this.type + "/" + this.floor + "_2x2_secret_rooms";
        }
    }

    static class Grid {
        private final Random random;
        private final MansionPieces.SimpleGrid baseGrid;
        private final MansionPieces.SimpleGrid thirdFloorGrid;
        private final MansionPieces.SimpleGrid[] floorRooms;
        private final int entranceX;
        private final int entranceY;

        public Grid(Random random) {
            this.random = random;
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

            boolean cleaning = true;
            while(cleaning) {
                cleaning = this.cleanEdges(this.baseGrid);
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

        public boolean isRoomId(int p_191114_2_, int p_191114_3_, int p_191114_4_, int p_191114_5_) {
            return (this.floorRooms[p_191114_4_].get(p_191114_2_, p_191114_3_) & '\uffff') == p_191114_5_;
        }

        @Nullable
        public Direction get1x2RoomDirection(int p_191113_2_, int p_191113_3_, int p_191113_4_, int p_191113_5_) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                if (this.isRoomId(p_191113_2_ + direction.getStepX(), p_191113_3_ + direction.getStepZ(), p_191113_4_, p_191113_5_)) {
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
                Direction direction1 = this.get1x2RoomDirection(tuple.getA(), tuple.getB(), 1, l1 & '\uffff');
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

                    boolean cleaning = true;
                    while(cleaning) {
                        cleaning = this.cleanEdges(this.thirdFloorGrid);
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

        public void createMansion(DynamicRegistries dynamicRegistryManager, BlockPos blockPos, Rotation rotation, List<StructurePiece> structurePieces, Grid grid, MANSIONTYPE type) {
            MutableRegistry<JigsawPattern> poolRegistry = dynamicRegistryManager.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);
            MansionPieces.PlacementData MansionPieces$placementdata = new MansionPieces.PlacementData();
            MansionPieces$placementdata.position = blockPos;
            MansionPieces$placementdata.rotation = rotation;
            MansionPieces$placementdata.wallType = "wall_flat";
            MansionPieces.PlacementData MansionPieces$placementdata1 = new MansionPieces.PlacementData();
            this.entrance(poolRegistry, structurePieces, MansionPieces$placementdata, type);
            MansionPieces$placementdata1.position = MansionPieces$placementdata.position.above(8);
            MansionPieces$placementdata1.rotation = MansionPieces$placementdata.rotation;
            MansionPieces$placementdata1.wallType = "wall_window";

            MansionPieces.SimpleGrid MansionPieces$simplegrid = grid.baseGrid;
            MansionPieces.SimpleGrid MansionPieces$simplegrid1 = grid.thirdFloorGrid;
            this.startX = grid.entranceX + 1;
            this.startY = grid.entranceY + 1;
            int i = grid.entranceX + 1;
            int j = grid.entranceY;
            this.traverseOuterWalls(poolRegistry, structurePieces, MansionPieces$placementdata, MansionPieces$simplegrid, Direction.SOUTH, this.startX, this.startY, i, j, type);
            this.traverseOuterWalls(poolRegistry, structurePieces, MansionPieces$placementdata1, MansionPieces$simplegrid, Direction.SOUTH, this.startX, this.startY, i, j, type);
            MansionPieces.PlacementData MansionPieces$placementdata2 = new MansionPieces.PlacementData();
            MansionPieces$placementdata2.position = MansionPieces$placementdata.position.above(19);
            MansionPieces$placementdata2.rotation = MansionPieces$placementdata.rotation;
            MansionPieces$placementdata2.wallType = "wall_window";
            boolean flag = false;

            for(int k = 0; k < MansionPieces$simplegrid1.height && !flag; ++k) {
                for(int l = MansionPieces$simplegrid1.width - 1; l >= 0 && !flag; --l) {
                    if (MansionPieces.Grid.isHouse(MansionPieces$simplegrid1, l, k)) {
                        MansionPieces$placementdata2.position = MansionPieces$placementdata2.position.relative(rotation.rotate(Direction.SOUTH), 8 + (k - this.startY) * 8);
                        MansionPieces$placementdata2.position = MansionPieces$placementdata2.position.relative(rotation.rotate(Direction.EAST), (l - this.startX) * 8);
                        this.traverseWallPiece(poolRegistry, structurePieces, MansionPieces$placementdata2, type);
                        this.traverseOuterWalls(poolRegistry, structurePieces, MansionPieces$placementdata2, MansionPieces$simplegrid1, Direction.SOUTH, l, k, l, k, type);
                        flag = true;
                    }
                }
            }

            this.createRoof(poolRegistry, structurePieces, blockPos.above(16), rotation, MansionPieces$simplegrid, MansionPieces$simplegrid1, type);
            this.createRoof(poolRegistry, structurePieces, blockPos.above(27), rotation, MansionPieces$simplegrid1, null, type);

            MansionPieces.RoomCollection[] aMansionPieces$roomcollection = new MansionPieces.RoomCollection[]{
                    new MansionPieces.FirstFloor(type),
                    new MansionPieces.SecondFloor(type),
                    new MansionPieces.ThirdFloor(type)
            };

            for(int floor = 0; floor < 3; ++floor) {
                BlockPos blockpos = blockPos.above(8 * floor + (floor == 2 ? 3 : 0));
                MansionPieces.SimpleGrid MansionPieces$simplegrid2 = grid.floorRooms[floor];
                MansionPieces.SimpleGrid MansionPieces$simplegrid3 = floor == 2 ? MansionPieces$simplegrid1 : MansionPieces$simplegrid;
                String s = floor == 0 ? "carpet_south_1" : "carpet_south_2";
                String s1 = floor == 0 ? "carpet_west_1" : "carpet_west_2";

                for(int i1 = 0; i1 < MansionPieces$simplegrid3.height; ++i1) {
                    for(int j1 = 0; j1 < MansionPieces$simplegrid3.width; ++j1) {
                        if (MansionPieces$simplegrid3.get(j1, i1) == 1) {
                            BlockPos blockpos1 = blockpos.relative(rotation.rotate(Direction.SOUTH), 8 + (i1 - this.startY) * 8);
                            blockpos1 = blockpos1.relative(rotation.rotate(Direction.EAST), (j1 - this.startX) * 8);
                            structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + (floor == 0 ? "/corridor_floor" : "/corridor_floor_high"), blockpos1, rotation, Mirror.NONE, type));
                            if (MansionPieces$simplegrid3.get(j1, i1 - 1) == 1 || (MansionPieces$simplegrid2.get(j1, i1 - 1) & 8388608) == 8388608) {
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/carpet_north", blockpos1.relative(rotation.rotate(Direction.EAST), 1).above(), rotation, Mirror.NONE, type));
                            }

                            if (MansionPieces$simplegrid3.get(j1 + 1, i1) == 1 || (MansionPieces$simplegrid2.get(j1 + 1, i1) & 8388608) == 8388608) {
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/carpet_east", blockpos1.relative(rotation.rotate(Direction.SOUTH), 1).relative(rotation.rotate(Direction.EAST), 5).above(), rotation, Mirror.NONE, type));
                            }

                            if (MansionPieces$simplegrid3.get(j1, i1 + 1) == 1 || (MansionPieces$simplegrid2.get(j1, i1 + 1) & 8388608) == 8388608) {
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/" + s, blockpos1.relative(rotation.rotate(Direction.SOUTH), 5).relative(rotation.rotate(Direction.WEST), 1), rotation, Mirror.NONE, type));
                            }

                            if (MansionPieces$simplegrid3.get(j1 - 1, i1) == 1 || (MansionPieces$simplegrid2.get(j1 - 1, i1) & 8388608) == 8388608) {
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/" + s1, blockpos1.relative(rotation.rotate(Direction.WEST), 1).relative(rotation.rotate(Direction.NORTH), 1), rotation, Mirror.NONE, type));
                            }
                        }
                    }
                }

                String s2 = floor == 0 ? "indoors_wall_1" : "indoors_wall_2";
                String s3 = floor == 0 ? "indoors_door_1" : "indoors_door_2";
                List<Direction> list = Lists.newArrayList();

                for(int k1 = 0; k1 < MansionPieces$simplegrid3.height; ++k1) {
                    for(int l1 = 0; l1 < MansionPieces$simplegrid3.width; ++l1) {
                        boolean flag1 = floor == 2 && MansionPieces$simplegrid3.get(l1, k1) == 3;
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

                            BlockPos blockpos3 = blockpos.relative(rotation.rotate(Direction.SOUTH), 8 + (k1 - this.startY) * 8);
                            blockpos3 = blockpos3.relative(rotation.rotate(Direction.EAST), -1 + (l1 - this.startX) * 8);
                            if (MansionPieces.Grid.isHouse(MansionPieces$simplegrid3, l1 - 1, k1) && !grid.isRoomId(l1 - 1, k1, floor, k2)) {
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/" + (direction1 == Direction.WEST ? s3 : s2), blockpos3, rotation, Mirror.NONE, type));
                            }

                            if (MansionPieces$simplegrid3.get(l1 + 1, k1) == 1 && !flag1) {
                                BlockPos blockpos2 = blockpos3.relative(rotation.rotate(Direction.EAST), 8);
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/" + (direction1 == Direction.EAST ? s3 : s2), blockpos2, rotation, Mirror.NONE, type));
                            }

                            if (MansionPieces.Grid.isHouse(MansionPieces$simplegrid3, l1, k1 + 1) && !grid.isRoomId(l1, k1 + 1, floor, k2)) {
                                BlockPos blockpos4 = blockpos3.relative(rotation.rotate(Direction.SOUTH), 7);
                                blockpos4 = blockpos4.relative(rotation.rotate(Direction.EAST), 7);
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/" + (direction1 == Direction.SOUTH ? s3 : s2), blockpos4, rotation.getRotated(Rotation.CLOCKWISE_90), Mirror.NONE, type));
                            }

                            if (MansionPieces$simplegrid3.get(l1, k1 - 1) == 1 && !flag1) {
                                BlockPos blockpos5 = blockpos3.relative(rotation.rotate(Direction.NORTH), 1);
                                blockpos5 = blockpos5.relative(rotation.rotate(Direction.EAST), 7);
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/" + (direction1 == Direction.NORTH ? s3 : s2), blockpos5, rotation.getRotated(Rotation.CLOCKWISE_90), Mirror.NONE, type));
                            }

                            if (j2 == 65536) {
                                this.addRoom1x1(poolRegistry, structurePieces, blockpos3, rotation, direction1, aMansionPieces$roomcollection[floor], type);
                            } else if (j2 == 131072 && direction1 != null) {
                                Direction direction3 = grid.get1x2RoomDirection(l1, k1, floor, k2);
                                boolean flag2 = (i2 & 4194304) == 4194304;
                                this.addRoom1x2(poolRegistry, structurePieces, blockpos3, rotation, direction3, direction1, aMansionPieces$roomcollection[floor], flag2, type);
                            } else if (j2 == 262144 && direction1 != null && direction1 != Direction.UP) {
                                Direction direction2 = direction1.getClockWise();
                                if (!grid.isRoomId(l1 + direction2.getStepX(), k1 + direction2.getStepZ(), floor, k2)) {
                                    direction2 = direction2.getOpposite();
                                }

                                this.addRoom2x2(poolRegistry, structurePieces, blockpos3, rotation, direction2, direction1, aMansionPieces$roomcollection[floor], type);
                            } else if (j2 == 262144 && direction1 == Direction.UP) {
                                this.addRoom2x2Secret(poolRegistry, structurePieces, blockpos3, rotation, aMansionPieces$roomcollection[floor], type);
                            }
                        }
                    }
                }
            }

        }

        private void traverseOuterWalls(MutableRegistry<JigsawPattern> poolRegistry, List<StructurePiece> structurePieces, MansionPieces.PlacementData placementData, MansionPieces.SimpleGrid simpleGrid, Direction direction1, int x, int z, int x2, int z2, MANSIONTYPE type) {
            int xOffset = x;
            int zOffset = z;
            Direction direction = direction1;

            do {
                if (!MansionPieces.Grid.isHouse(simpleGrid, xOffset + direction1.getStepX(), zOffset + direction1.getStepZ())) {
                    this.traverseTurn(poolRegistry, structurePieces, placementData, type);
                    direction1 = direction1.getClockWise();
                    if (xOffset != x2 || zOffset != z2 || direction != direction1) {
                        this.traverseWallPiece(poolRegistry, structurePieces, placementData, type);
                    }
                } else if (MansionPieces.Grid.isHouse(simpleGrid, xOffset + direction1.getStepX(), zOffset + direction1.getStepZ()) && MansionPieces.Grid.isHouse(simpleGrid, xOffset + direction1.getStepX() + direction1.getCounterClockWise().getStepX(), zOffset + direction1.getStepZ() + direction1.getCounterClockWise().getStepZ())) {
                    this.traverseInnerTurn(placementData);
                    xOffset += direction1.getStepX();
                    zOffset += direction1.getStepZ();
                    direction1 = direction1.getCounterClockWise();
                } else {
                    xOffset += direction1.getStepX();
                    zOffset += direction1.getStepZ();
                    if (xOffset != x2 || zOffset != z2 || direction != direction1) {
                        this.traverseWallPiece(poolRegistry, structurePieces, placementData, type);
                    }
                }
            } while(xOffset != x2 || zOffset != z2 || direction != direction1);

        }

        private void createRoof(MutableRegistry<JigsawPattern> poolRegistry, List<StructurePiece> structurePieces, BlockPos blockPos1, Rotation rotation, MansionPieces.SimpleGrid p_191123_4_, @Nullable MansionPieces.SimpleGrid simpleGrid, MANSIONTYPE type) {
            for(int i = 0; i < p_191123_4_.height; ++i) {
                for(int j = 0; j < p_191123_4_.width; ++j) {
                    BlockPos blockPos = blockPos1.relative(rotation.rotate(Direction.SOUTH), 8 + (i - this.startY) * 8);
                    blockPos = blockPos.relative(rotation.rotate(Direction.EAST), (j - this.startX) * 8);
                    boolean flag = simpleGrid != null && MansionPieces.Grid.isHouse(simpleGrid, j, i);
                    if (MansionPieces.Grid.isHouse(p_191123_4_, j, i) && !flag) {
                        structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/roof", blockPos.above(3), rotation, Mirror.NONE, type));
                        if (!MansionPieces.Grid.isHouse(p_191123_4_, j + 1, i)) {
                            BlockPos blockpos1 = blockPos.relative(rotation.rotate(Direction.EAST), 6);
                            structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/roof_front", blockpos1, rotation, Mirror.NONE, type));
                        }

                        if (!MansionPieces.Grid.isHouse(p_191123_4_, j - 1, i)) {
                            BlockPos blockpos5 = blockPos.relative(rotation.rotate(Direction.EAST), 0);
                            blockpos5 = blockpos5.relative(rotation.rotate(Direction.SOUTH), 7);
                            structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/roof_front", blockpos5, rotation.getRotated(Rotation.CLOCKWISE_180), Mirror.NONE, type));
                        }

                        if (!MansionPieces.Grid.isHouse(p_191123_4_, j, i - 1)) {
                            BlockPos blockpos6 = blockPos.relative(rotation.rotate(Direction.WEST), 1);
                            structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/roof_front", blockpos6, rotation.getRotated(Rotation.COUNTERCLOCKWISE_90), Mirror.NONE, type));
                        }

                        if (!MansionPieces.Grid.isHouse(p_191123_4_, j, i + 1)) {
                            BlockPos blockpos7 = blockPos.relative(rotation.rotate(Direction.EAST), 6);
                            blockpos7 = blockpos7.relative(rotation.rotate(Direction.SOUTH), 6);
                            structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/roof_front", blockpos7, rotation.getRotated(Rotation.CLOCKWISE_90), Mirror.NONE, type));
                        }
                    }
                }
            }

            if (simpleGrid != null) {
                for(int k = 0; k < p_191123_4_.height; ++k) {
                    for(int i1 = 0; i1 < p_191123_4_.width; ++i1) {
                        BlockPos blockpos3 = blockPos1.relative(rotation.rotate(Direction.SOUTH), 8 + (k - this.startY) * 8);
                        blockpos3 = blockpos3.relative(rotation.rotate(Direction.EAST), (i1 - this.startX) * 8);
                        boolean flag1 = MansionPieces.Grid.isHouse(simpleGrid, i1, k);
                        if (MansionPieces.Grid.isHouse(p_191123_4_, i1, k) && flag1) {
                            if (!MansionPieces.Grid.isHouse(p_191123_4_, i1 + 1, k)) {
                                BlockPos blockpos8 = blockpos3.relative(rotation.rotate(Direction.EAST), 7);
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/small_wall", blockpos8, rotation, Mirror.NONE, type));
                            }

                            if (!MansionPieces.Grid.isHouse(p_191123_4_, i1 - 1, k)) {
                                BlockPos blockpos9 = blockpos3.relative(rotation.rotate(Direction.WEST), 1);
                                blockpos9 = blockpos9.relative(rotation.rotate(Direction.SOUTH), 6);
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/small_wall", blockpos9, rotation.getRotated(Rotation.CLOCKWISE_180), Mirror.NONE, type));
                            }

                            if (!MansionPieces.Grid.isHouse(p_191123_4_, i1, k - 1)) {
                                BlockPos blockpos10 = blockpos3.relative(rotation.rotate(Direction.WEST), 0);
                                blockpos10 = blockpos10.relative(rotation.rotate(Direction.NORTH), 1);
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/small_wall", blockpos10, rotation.getRotated(Rotation.COUNTERCLOCKWISE_90), Mirror.NONE, type));
                            }

                            if (!MansionPieces.Grid.isHouse(p_191123_4_, i1, k + 1)) {
                                BlockPos blockpos11 = blockpos3.relative(rotation.rotate(Direction.EAST), 6);
                                blockpos11 = blockpos11.relative(rotation.rotate(Direction.SOUTH), 7);
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/small_wall", blockpos11, rotation.getRotated(Rotation.CLOCKWISE_90), Mirror.NONE, type));
                            }

                            if (!MansionPieces.Grid.isHouse(p_191123_4_, i1 + 1, k)) {
                                if (!MansionPieces.Grid.isHouse(p_191123_4_, i1, k - 1)) {
                                    BlockPos blockpos12 = blockpos3.relative(rotation.rotate(Direction.EAST), 7);
                                    blockpos12 = blockpos12.relative(rotation.rotate(Direction.NORTH), 2);
                                    structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/small_wall_corner", blockpos12, rotation, Mirror.NONE, type));
                                }

                                if (!MansionPieces.Grid.isHouse(p_191123_4_, i1, k + 1)) {
                                    BlockPos blockpos13 = blockpos3.relative(rotation.rotate(Direction.EAST), 8);
                                    blockpos13 = blockpos13.relative(rotation.rotate(Direction.SOUTH), 7);
                                    structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/small_wall_corner", blockpos13, rotation.getRotated(Rotation.CLOCKWISE_90), Mirror.NONE, type));
                                }
                            }

                            if (!MansionPieces.Grid.isHouse(p_191123_4_, i1 - 1, k)) {
                                if (!MansionPieces.Grid.isHouse(p_191123_4_, i1, k - 1)) {
                                    BlockPos blockpos14 = blockpos3.relative(rotation.rotate(Direction.WEST), 2);
                                    blockpos14 = blockpos14.relative(rotation.rotate(Direction.NORTH), 1);
                                    structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/small_wall_corner", blockpos14, rotation.getRotated(Rotation.COUNTERCLOCKWISE_90), Mirror.NONE, type));
                                }

                                if (!MansionPieces.Grid.isHouse(p_191123_4_, i1, k + 1)) {
                                    BlockPos blockpos15 = blockpos3.relative(rotation.rotate(Direction.WEST), 1);
                                    blockpos15 = blockpos15.relative(rotation.rotate(Direction.SOUTH), 8);
                                    structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/small_wall_corner", blockpos15, rotation.getRotated(Rotation.CLOCKWISE_180), Mirror.NONE, type));
                                }
                            }
                        }
                    }
                }
            }

            for(int l = 0; l < p_191123_4_.height; ++l) {
                for(int j1 = 0; j1 < p_191123_4_.width; ++j1) {
                    BlockPos blockpos4 = blockPos1.relative(rotation.rotate(Direction.SOUTH), 8 + (l - this.startY) * 8);
                    blockpos4 = blockpos4.relative(rotation.rotate(Direction.EAST), (j1 - this.startX) * 8);
                    boolean flag2 = simpleGrid != null && MansionPieces.Grid.isHouse(simpleGrid, j1, l);
                    if (MansionPieces.Grid.isHouse(p_191123_4_, j1, l) && !flag2) {
                        if (!MansionPieces.Grid.isHouse(p_191123_4_, j1 + 1, l)) {
                            BlockPos blockpos16 = blockpos4.relative(rotation.rotate(Direction.EAST), 6);
                            if (!MansionPieces.Grid.isHouse(p_191123_4_, j1, l + 1)) {
                                BlockPos blockpos2 = blockpos16.relative(rotation.rotate(Direction.SOUTH), 6);
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/roof_corner", blockpos2, rotation, Mirror.NONE, type));
                            } else if (MansionPieces.Grid.isHouse(p_191123_4_, j1 + 1, l + 1)) {
                                BlockPos blockpos18 = blockpos16.relative(rotation.rotate(Direction.SOUTH), 5);
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/roof_inner_corner", blockpos18, rotation, Mirror.NONE, type));
                            }

                            if (!MansionPieces.Grid.isHouse(p_191123_4_, j1, l - 1)) {
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/roof_corner", blockpos16, rotation.getRotated(Rotation.COUNTERCLOCKWISE_90), Mirror.NONE, type));
                            } else if (MansionPieces.Grid.isHouse(p_191123_4_, j1 + 1, l - 1)) {
                                BlockPos blockpos19 = blockpos4.relative(rotation.rotate(Direction.EAST), 9);
                                blockpos19 = blockpos19.relative(rotation.rotate(Direction.NORTH), 2);
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/roof_inner_corner", blockpos19, rotation.getRotated(Rotation.CLOCKWISE_90), Mirror.NONE, type));
                            }
                        }

                        if (!MansionPieces.Grid.isHouse(p_191123_4_, j1 - 1, l)) {
                            BlockPos blockpos17 = blockpos4.relative(rotation.rotate(Direction.EAST), 0);
                            blockpos17 = blockpos17.relative(rotation.rotate(Direction.SOUTH), 0);
                            if (!MansionPieces.Grid.isHouse(p_191123_4_, j1, l + 1)) {
                                BlockPos blockpos20 = blockpos17.relative(rotation.rotate(Direction.SOUTH), 6);
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/roof_corner", blockpos20, rotation.getRotated(Rotation.CLOCKWISE_90), Mirror.NONE, type));
                            } else if (MansionPieces.Grid.isHouse(p_191123_4_, j1 - 1, l + 1)) {
                                BlockPos blockpos21 = blockpos17.relative(rotation.rotate(Direction.SOUTH), 8);
                                blockpos21 = blockpos21.relative(rotation.rotate(Direction.WEST), 3);
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/roof_inner_corner", blockpos21, rotation.getRotated(Rotation.COUNTERCLOCKWISE_90), Mirror.NONE, type));
                            }

                            if (!MansionPieces.Grid.isHouse(p_191123_4_, j1, l - 1)) {
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/roof_corner", blockpos17, rotation.getRotated(Rotation.CLOCKWISE_180), Mirror.NONE, type));
                            } else if (MansionPieces.Grid.isHouse(p_191123_4_, j1 - 1, l - 1)) {
                                BlockPos blockpos22 = blockpos17.relative(rotation.rotate(Direction.SOUTH), 1);
                                structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/roof_inner_corner", blockpos22, rotation.getRotated(Rotation.CLOCKWISE_180), Mirror.NONE, type));
                            }
                        }
                    }
                }
            }

        }

        private void entrance(MutableRegistry<JigsawPattern> poolRegistry, List<StructurePiece> structurePieces, MansionPieces.PlacementData placementData, MANSIONTYPE type) {
            Direction direction = placementData.rotation.rotate(Direction.WEST);
            structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/entrance", placementData.position.relative(direction, 9), placementData.rotation, Mirror.NONE, type));
            placementData.position = placementData.position.relative(placementData.rotation.rotate(Direction.SOUTH), 16);
        }

        private void traverseWallPiece(MutableRegistry<JigsawPattern> poolRegistry, List<StructurePiece> structurePieces, MansionPieces.PlacementData mansionTemplate, MANSIONTYPE type) {
            structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/" + mansionTemplate.wallType, mansionTemplate.position.relative(mansionTemplate.rotation.rotate(Direction.EAST), 7), mansionTemplate.rotation, Mirror.NONE, type));
            mansionTemplate.position = mansionTemplate.position.relative(mansionTemplate.rotation.rotate(Direction.SOUTH), 8);
        }

        private void traverseTurn(MutableRegistry<JigsawPattern> poolRegistry, List<StructurePiece> structurePieces, MansionPieces.PlacementData placementData, MANSIONTYPE type) {
            placementData.position = placementData.position.relative(placementData.rotation.rotate(Direction.SOUTH), -1);
            structurePieces.add(getJigsawPiece(poolRegistry, RepurposedStructures.MODID + ":mansions/" + type + "/wall_corner", placementData.position, placementData.rotation, Mirror.NONE, type));
            placementData.position = placementData.position.relative(placementData.rotation.rotate(Direction.SOUTH), -7);
            placementData.position = placementData.position.relative(placementData.rotation.rotate(Direction.WEST), -6);
            placementData.rotation = placementData.rotation.getRotated(Rotation.CLOCKWISE_90);
        }

        private void traverseInnerTurn(MansionPieces.PlacementData p_191126_2_) {
            p_191126_2_.position = p_191126_2_.position.relative(p_191126_2_.rotation.rotate(Direction.SOUTH), 6);
            p_191126_2_.position = p_191126_2_.position.relative(p_191126_2_.rotation.rotate(Direction.EAST), 8);
            p_191126_2_.rotation = p_191126_2_.rotation.getRotated(Rotation.COUNTERCLOCKWISE_90);
        }

        private void addRoom1x1(MutableRegistry<JigsawPattern> poolRegistry, List<StructurePiece> structurePieces, BlockPos blockPos, Rotation rotation, Direction direction, RoomCollection roomCollection, MANSIONTYPE type) {
            Rotation finalRotation = Rotation.NONE;
            String poolPath = roomCollection.get1x1(this.random);
            if (direction != Direction.EAST) {
                if (direction == Direction.NORTH) {
                    finalRotation = finalRotation.getRotated(Rotation.COUNTERCLOCKWISE_90);
                } else if (direction == Direction.WEST) {
                    finalRotation = finalRotation.getRotated(Rotation.CLOCKWISE_180);
                } else if (direction == Direction.SOUTH) {
                    finalRotation = finalRotation.getRotated(Rotation.CLOCKWISE_90);
                } else {
                    poolPath = roomCollection.get1x1Secret(this.random);
                }
            }


            BlockPos blockpos = Template.getZeroPositionWithTransform(new BlockPos(1, 0, 0), Mirror.NONE, finalRotation, 7, 7);
            finalRotation = finalRotation.getRotated(rotation);
            blockpos = blockpos.rotate(rotation);
            BlockPos blockpos1 = blockPos.offset(blockpos.getX(), 0, blockpos.getZ());

            structurePieces.add(getJigsawPiece(poolRegistry, poolPath, blockpos1, finalRotation, Mirror.NONE, type));
        }

        private void addRoom1x2(MutableRegistry<JigsawPattern> poolRegistry, List<StructurePiece> structurePieces, BlockPos blockPos, Rotation rotation, Direction direction, Direction direction1, MansionPieces.RoomCollection roomCollection, boolean isStairs, MANSIONTYPE type) {
            if (direction1 == Direction.EAST && direction == Direction.SOUTH) {
                BlockPos blockpos13 = blockPos.relative(rotation.rotate(Direction.EAST), 1);
                structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get1x2SideEntrance(this.random, isStairs), blockpos13, rotation, Mirror.NONE, type));
            }
            else if (direction1 == Direction.EAST && direction == Direction.NORTH) {
                BlockPos blockpos12 = blockPos.relative(rotation.rotate(Direction.EAST), 1);
                blockpos12 = blockpos12.relative(rotation.rotate(Direction.SOUTH), 6);
                structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get1x2SideEntrance(this.random, isStairs), blockpos12, rotation, Mirror.LEFT_RIGHT, type));
            }
            else if (direction1 == Direction.WEST && direction == Direction.NORTH) {
                BlockPos blockpos11 = blockPos.relative(rotation.rotate(Direction.EAST), 7);
                blockpos11 = blockpos11.relative(rotation.rotate(Direction.SOUTH), 6);
                structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get1x2SideEntrance(this.random, isStairs), blockpos11, rotation.getRotated(Rotation.CLOCKWISE_180), Mirror.NONE, type));
            }
            else if (direction1 == Direction.WEST && direction == Direction.SOUTH) {
                BlockPos blockpos10 = blockPos.relative(rotation.rotate(Direction.EAST), 7);
                structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get1x2SideEntrance(this.random, isStairs), blockpos10, rotation, Mirror.FRONT_BACK, type));
            }
            else if (direction1 == Direction.SOUTH && direction == Direction.EAST) {
                BlockPos blockpos9 = blockPos.relative(rotation.rotate(Direction.EAST), 1);
                structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get1x2SideEntrance(this.random, isStairs), blockpos9, rotation.getRotated(Rotation.CLOCKWISE_90), Mirror.LEFT_RIGHT, type));
            }
            else if (direction1 == Direction.SOUTH && direction == Direction.WEST) {
                BlockPos blockpos8 = blockPos.relative(rotation.rotate(Direction.EAST), 7);
                structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get1x2SideEntrance(this.random, isStairs), blockpos8, rotation.getRotated(Rotation.CLOCKWISE_90), Mirror.NONE, type));
            }
            else if (direction1 == Direction.NORTH && direction == Direction.WEST) {
                BlockPos blockpos7 = blockPos.relative(rotation.rotate(Direction.EAST), 7);
                blockpos7 = blockpos7.relative(rotation.rotate(Direction.SOUTH), 6);
                structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get1x2SideEntrance(this.random, isStairs), blockpos7, rotation.getRotated(Rotation.CLOCKWISE_90), Mirror.FRONT_BACK, type));
            }
            else if (direction1 == Direction.NORTH && direction == Direction.EAST) {
                BlockPos blockpos6 = blockPos.relative(rotation.rotate(Direction.EAST), 1);
                blockpos6 = blockpos6.relative(rotation.rotate(Direction.SOUTH), 6);
                structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get1x2SideEntrance(this.random, isStairs), blockpos6, rotation.getRotated(Rotation.COUNTERCLOCKWISE_90), Mirror.NONE, type));
            }
            else if (direction1 == Direction.SOUTH && direction == Direction.NORTH) {
                BlockPos blockpos5 = blockPos.relative(rotation.rotate(Direction.EAST), 1);
                blockpos5 = blockpos5.relative(rotation.rotate(Direction.NORTH), 8);
                structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get1x2FrontEntrance(this.random, isStairs), blockpos5, rotation, Mirror.NONE, type));
            }
            else if (direction1 == Direction.NORTH && direction == Direction.SOUTH) {
                BlockPos blockpos4 = blockPos.relative(rotation.rotate(Direction.EAST), 7);
                blockpos4 = blockpos4.relative(rotation.rotate(Direction.SOUTH), 14);
                structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get1x2FrontEntrance(this.random, isStairs), blockpos4, rotation.getRotated(Rotation.CLOCKWISE_180), Mirror.NONE, type));
            }
            else if (direction1 == Direction.WEST && direction == Direction.EAST) {
                BlockPos blockpos3 = blockPos.relative(rotation.rotate(Direction.EAST), 15);
                structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get1x2FrontEntrance(this.random, isStairs), blockpos3, rotation.getRotated(Rotation.CLOCKWISE_90), Mirror.NONE, type));
            }
            else if (direction1 == Direction.EAST && direction == Direction.WEST) {
                BlockPos blockpos2 = blockPos.relative(rotation.rotate(Direction.WEST), 7);
                blockpos2 = blockpos2.relative(rotation.rotate(Direction.SOUTH), 6);
                structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get1x2FrontEntrance(this.random, isStairs), blockpos2, rotation.getRotated(Rotation.COUNTERCLOCKWISE_90), Mirror.NONE, type));
            }
            else if (direction1 == Direction.UP && direction == Direction.EAST) {
                BlockPos blockpos1 = blockPos.relative(rotation.rotate(Direction.EAST), 15);
                structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get1x2Secret(this.random), blockpos1, rotation.getRotated(Rotation.CLOCKWISE_90), Mirror.NONE, type));
            }
            else if (direction1 == Direction.UP && direction == Direction.SOUTH) {
                BlockPos blockpos = blockPos.relative(rotation.rotate(Direction.EAST), 1);
                blockpos = blockpos.relative(rotation.rotate(Direction.NORTH), 0);
                structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get1x2Secret(this.random), blockpos, rotation, Mirror.NONE, type));
            }
        }

        private void addRoom2x2(MutableRegistry<JigsawPattern> poolRegistry, List<StructurePiece> structurePieces, BlockPos blockPos, Rotation rotation, Direction direction, Direction direction1, MansionPieces.RoomCollection roomCollection, MANSIONTYPE type) {
            int i = 0;
            int j = 0;
            Rotation finalRotation = rotation;
            Mirror mirror = Mirror.NONE;
            if (direction1 == Direction.EAST && direction == Direction.SOUTH) {
                i = -7;
            } else if (direction1 == Direction.EAST && direction == Direction.NORTH) {
                i = -7;
                j = 6;
                mirror = Mirror.LEFT_RIGHT;
            } else if (direction1 == Direction.NORTH && direction == Direction.EAST) {
                i = 1;
                j = 14;
                finalRotation = rotation.getRotated(Rotation.COUNTERCLOCKWISE_90);
            } else if (direction1 == Direction.NORTH && direction == Direction.WEST) {
                i = 7;
                j = 14;
                finalRotation = rotation.getRotated(Rotation.COUNTERCLOCKWISE_90);
                mirror = Mirror.LEFT_RIGHT;
            } else if (direction1 == Direction.SOUTH && direction == Direction.WEST) {
                i = 7;
                j = -8;
                finalRotation = rotation.getRotated(Rotation.CLOCKWISE_90);
            } else if (direction1 == Direction.SOUTH && direction == Direction.EAST) {
                i = 1;
                j = -8;
                finalRotation = rotation.getRotated(Rotation.CLOCKWISE_90);
                mirror = Mirror.LEFT_RIGHT;
            } else if (direction1 == Direction.WEST && direction == Direction.NORTH) {
                i = 15;
                j = 6;
                finalRotation = rotation.getRotated(Rotation.CLOCKWISE_180);
            } else if (direction1 == Direction.WEST && direction == Direction.SOUTH) {
                i = 15;
                mirror = Mirror.FRONT_BACK;
            }

            BlockPos blockpos = blockPos.relative(rotation.rotate(Direction.EAST), i);
            blockpos = blockpos.relative(rotation.rotate(Direction.SOUTH), j);
            structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get2x2(this.random), blockpos, finalRotation, mirror, type));
        }

        private void addRoom2x2Secret(MutableRegistry<JigsawPattern> poolRegistry, List<StructurePiece> structurePieces, BlockPos blockPos, Rotation rotation, MansionPieces.RoomCollection roomCollection, MANSIONTYPE type) {
            BlockPos blockpos = blockPos.relative(rotation.rotate(Direction.EAST), 1);
            structurePieces.add(getJigsawPiece(poolRegistry, roomCollection.get2x2Secret(this.random), blockpos, rotation, Mirror.NONE, type));
        }

        private StructurePiece getJigsawPiece(MutableRegistry<JigsawPattern> poolRegistry, String poolPath, BlockPos blockPos, Rotation rotation, Mirror mirror, MANSIONTYPE type) {
            ResourceLocation resourceLocation = new ResourceLocation(poolPath.toLowerCase(Locale.ROOT));
            JigsawPattern pool = poolRegistry.get(resourceLocation);
            JigsawPiece poolEntry;

            if(pool == null || pool.size() == 0){
                RepurposedStructures.LOGGER.warn("Repurposed Structures: Empty or nonexistent pool: {}  Will not generate mansion piece at spot.", resourceLocation + " - Mansion type: " + type);
                poolEntry = JigsawPiece.empty().apply(JigsawPattern.PlacementBehaviour.RIGID);
            }
            else {
                poolEntry = pool.getRandomTemplate(this.random);
                if(poolEntry instanceof SingleJigsawPiece) {
                    poolEntry = new MirroringSingleJigsawPiece((SingleJigsawPiece) poolEntry, mirror);
                }
            }

            return new AbstractVillagePiece(
                    this.templateManager,
                    poolEntry,
                    blockPos,
                    poolEntry.getGroundLevelDelta(),
                    rotation,
                    poolEntry.getBoundingBox(this.templateManager, blockPos, rotation)
            );
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
}
