package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Tuple;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MansionPieces{
    public static void createMansionLayout(StructureManager manager, BlockPos pos, Rotation rotation, List<MansionPieces.Piece> pieces, Random random, Piece.MANSIONTYPE type) {
        MansionPieces.MansionParameters mansionParameters = new MansionPieces.MansionParameters(random);
        MansionPieces.LayoutGenerator layoutGenerator = new MansionPieces.LayoutGenerator(manager, random);
        layoutGenerator.generate(pos, rotation, pieces, mansionParameters, type);
    }

    static class ThirdFloorRoomPool extends MansionPieces.SecondFloorRoomPool {
        private ThirdFloorRoomPool() {
            super();
        }
    }

    static class SecondFloorRoomPool extends MansionPieces.RoomPool {
        private SecondFloorRoomPool() {
            super();
        }

        public String getSmallRoom(Random random) {
            return "1x1_b" + (random.nextInt(4) + 1);
        }

        public String getSmallSecretRoom(Random random) {
            return "1x1_as" + (random.nextInt(4) + 1);
        }

        public String getMediumFunctionalRoom(Random random, boolean staircase) {
            return staircase ? "1x2_c_stairs" : "1x2_c" + (random.nextInt(4) + 1);
        }

        public String getMediumGenericRoom(Random random, boolean staircase) {
            return staircase ? "1x2_d_stairs" : "1x2_d" + (random.nextInt(5) + 1);
        }

        public String getMediumSecretRoom(Random random) {
            return "1x2_se" + (random.nextInt(1) + 1);
        }

        public String getBigRoom(Random random) {
            return "2x2_b" + (random.nextInt(5) + 1);
        }

        public String getBigSecretRoom(Random random) {
            return "2x2_s1";
        }
    }

    static class FirstFloorRoomPool extends MansionPieces.RoomPool {
        private FirstFloorRoomPool() {
            super();
        }

        public String getSmallRoom(Random random) {
            return "1x1_a" + (random.nextInt(5) + 1);
        }

        public String getSmallSecretRoom(Random random) {
            return "1x1_as" + (random.nextInt(4) + 1);
        }

        public String getMediumFunctionalRoom(Random random, boolean staircase) {
            return "1x2_a" + (random.nextInt(9) + 1);
        }

        public String getMediumGenericRoom(Random random, boolean staircase) {
            return "1x2_b" + (random.nextInt(5) + 1);
        }

        public String getMediumSecretRoom(Random random) {
            return "1x2_s" + (random.nextInt(2) + 1);
        }

        public String getBigRoom(Random random) {
            return "2x2_a" + (random.nextInt(4) + 1);
        }

        public String getBigSecretRoom(Random random) {
            return "2x2_s1";
        }
    }

    abstract static class RoomPool {
        private RoomPool() {
        }

        public abstract String getSmallRoom(Random random);

        public abstract String getSmallSecretRoom(Random random);

        public abstract String getMediumFunctionalRoom(Random random, boolean staircase);

        public abstract String getMediumGenericRoom(Random random, boolean staircase);

        public abstract String getMediumSecretRoom(Random random);

        public abstract String getBigRoom(Random random);

        public abstract String getBigSecretRoom(Random random);
    }

    static class FlagMatrix {
        private final int[][] array;
        private final int n;
        private final int m;
        private final int fallback;

        public FlagMatrix(int n, int m, int fallback) {
            this.n = n;
            this.m = m;
            this.fallback = fallback;
            this.array = new int[n][m];
        }

        public void set(int i, int j, int value) {
            if (i >= 0 && i < this.n && j >= 0 && j < this.m) {
                this.array[i][j] = value;
            }

        }

        public void fill(int i0, int j0, int i1, int j1, int value) {
            for(int i = j0; i <= j1; ++i) {
                for(int j = i0; j <= i1; ++j) {
                    this.set(j, i, value);
                }
            }

        }

        public int get(int i, int j) {
            return i >= 0 && i < this.n && j >= 0 && j < this.m ? this.array[i][j] : this.fallback;
        }

        public void update(int i, int j, int expected, int newValue) {
            if (this.get(i, j) == expected) {
                this.set(i, j, newValue);
            }

        }

        public boolean anyMatchAround(int i, int j, int value) {
            return this.get(i - 1, j) == value || this.get(i + 1, j) == value || this.get(i, j + 1) == value || this.get(i, j - 1) == value;
        }
    }

    static class MansionParameters {
        private final Random random;
        private final MansionPieces.FlagMatrix field_15440;
        private final MansionPieces.FlagMatrix field_15439;
        private final MansionPieces.FlagMatrix[] field_15443;
        private final int field_15442;
        private final int field_15441;

        public MansionParameters(Random random) {
            this.random = random;
            this.field_15442 = 7;
            this.field_15441 = 4;
            this.field_15440 = new MansionPieces.FlagMatrix(11, 11, 5);
            this.field_15440.fill(this.field_15442, this.field_15441, this.field_15442 + 1, this.field_15441 + 1, 3);
            this.field_15440.fill(this.field_15442 - 1, this.field_15441, this.field_15442 - 1, this.field_15441 + 1, 2);
            this.field_15440.fill(this.field_15442 + 2, this.field_15441 - 2, this.field_15442 + 3, this.field_15441 + 3, 5);
            this.field_15440.fill(this.field_15442 + 1, this.field_15441 - 2, this.field_15442 + 1, this.field_15441 - 1, 1);
            this.field_15440.fill(this.field_15442 + 1, this.field_15441 + 2, this.field_15442 + 1, this.field_15441 + 3, 1);
            this.field_15440.set(this.field_15442 - 1, this.field_15441 - 1, 1);
            this.field_15440.set(this.field_15442 - 1, this.field_15441 + 2, 1);
            this.field_15440.fill(0, 0, 11, 1, 5);
            this.field_15440.fill(0, 9, 11, 11, 5);
            this.method_15045(this.field_15440, this.field_15442, this.field_15441 - 2, Direction.WEST, 6);
            this.method_15045(this.field_15440, this.field_15442, this.field_15441 + 3, Direction.WEST, 6);
            this.method_15045(this.field_15440, this.field_15442 - 2, this.field_15441 - 1, Direction.WEST, 3);
            this.method_15045(this.field_15440, this.field_15442 - 2, this.field_15441 + 2, Direction.WEST, 3);

            while(this.method_15046(this.field_15440)) {
            }

            this.field_15443 = new MansionPieces.FlagMatrix[3];
            this.field_15443[0] = new MansionPieces.FlagMatrix(11, 11, 5);
            this.field_15443[1] = new MansionPieces.FlagMatrix(11, 11, 5);
            this.field_15443[2] = new MansionPieces.FlagMatrix(11, 11, 5);
            this.method_15042(this.field_15440, this.field_15443[0]);
            this.method_15042(this.field_15440, this.field_15443[1]);
            this.field_15443[0].fill(this.field_15442 + 1, this.field_15441, this.field_15442 + 1, this.field_15441 + 1, 8388608);
            this.field_15443[1].fill(this.field_15442 + 1, this.field_15441, this.field_15442 + 1, this.field_15441 + 1, 8388608);
            this.field_15439 = new MansionPieces.FlagMatrix(this.field_15440.n, this.field_15440.m, 5);
            this.method_15048();
            this.method_15042(this.field_15439, this.field_15443[2]);
        }

        public static boolean method_15047(MansionPieces.FlagMatrix flagMatrix, int i, int j) {
            int k = flagMatrix.get(i, j);
            return k == 1 || k == 2 || k == 3 || k == 4;
        }

        public boolean method_15039(MansionPieces.FlagMatrix flagMatrix, int i, int j, int k, int l) {
            return (this.field_15443[k].get(i, j) & '\uffff') == l;
        }

        
        public Direction method_15040(MansionPieces.FlagMatrix flagMatrix, int i, int j, int k, int l) {
            Iterator<Direction> var6 = Direction.Plane.HORIZONTAL.iterator();

            Direction direction;
            do {
                if (!var6.hasNext()) {
                    return null;
                }

                direction = var6.next();
            } while(!this.method_15039(flagMatrix, i + direction.getStepX(), j + direction.getStepZ(), k, l));

            return direction;
        }

        private void method_15045(MansionPieces.FlagMatrix flagMatrix, int i, int j, Direction direction, int k) {
            if (k > 0) {
                flagMatrix.set(i, j, 1);
                flagMatrix.update(i + direction.getStepX(), j + direction.getStepZ(), 0, 1);

                Direction direction2;
                for(int l = 0; l < 8; ++l) {
                    direction2 = Direction.from2DDataValue(this.random.nextInt(4));
                    if (direction2 != direction.getOpposite() && (direction2 != Direction.EAST || !this.random.nextBoolean())) {
                        int m = i + direction.getStepX();
                        int n = j + direction.getStepZ();
                        if (flagMatrix.get(m + direction2.getStepX(), n + direction2.getStepZ()) == 0 && flagMatrix.get(m + direction2.getStepX() * 2, n + direction2.getStepZ() * 2) == 0) {
                            this.method_15045(flagMatrix, i + direction.getStepX() + direction2.getStepX(), j + direction.getStepZ() + direction2.getStepZ(), direction2, k - 1);
                            break;
                        }
                    }
                }

                Direction direction3 = direction.getClockWise();
                direction2 = direction.getCounterClockWise();
                flagMatrix.update(i + direction3.getStepX(), j + direction3.getStepZ(), 0, 2);
                flagMatrix.update(i + direction2.getStepX(), j + direction2.getStepZ(), 0, 2);
                flagMatrix.update(i + direction.getStepX() + direction3.getStepX(), j + direction.getStepZ() + direction3.getStepZ(), 0, 2);
                flagMatrix.update(i + direction.getStepX() + direction2.getStepX(), j + direction.getStepZ() + direction2.getStepZ(), 0, 2);
                flagMatrix.update(i + direction.getStepX() * 2, j + direction.getStepZ() * 2, 0, 2);
                flagMatrix.update(i + direction3.getStepX() * 2, j + direction3.getStepZ() * 2, 0, 2);
                flagMatrix.update(i + direction2.getStepX() * 2, j + direction2.getStepZ() * 2, 0, 2);
            }
        }

        private boolean method_15046(MansionPieces.FlagMatrix flagMatrix) {
            boolean bl = false;

            for(int i = 0; i < flagMatrix.m; ++i) {
                for(int j = 0; j < flagMatrix.n; ++j) {
                    if (flagMatrix.get(j, i) == 0) {
                        int k = method_15047(flagMatrix, j + 1, i) ? 1 : 0;
                        k += method_15047(flagMatrix, j - 1, i) ? 1 : 0;
                        k += method_15047(flagMatrix, j, i + 1) ? 1 : 0;
                        k += method_15047(flagMatrix, j, i - 1) ? 1 : 0;
                        if (k >= 3) {
                            flagMatrix.set(j, i, 2);
                            bl = true;
                        } else if (k == 2) {
                            int l = method_15047(flagMatrix, j + 1, i + 1) ? 1 : 0;
                            l += method_15047(flagMatrix, j - 1, i + 1) ? 1 : 0;
                            l += method_15047(flagMatrix, j + 1, i - 1) ? 1 : 0;
                            l += method_15047(flagMatrix, j - 1, i - 1) ? 1 : 0;
                            if (l <= 1) {
                                flagMatrix.set(j, i, 2);
                                bl = true;
                            }
                        }
                    }
                }
            }

            return bl;
        }

        private void method_15048() {
            List<Tuple<Integer, Integer>> list = Lists.newArrayList();
            MansionPieces.FlagMatrix flagMatrix = this.field_15443[1];

            int m;
            int n;
            for(int i = 0; i < this.field_15439.m; ++i) {
                for(m = 0; m < this.field_15439.n; ++m) {
                    int k = flagMatrix.get(m, i);
                    n = k & 983040;
                    if (n == 131072 && (k & 2097152) == 2097152) {
                        list.add(new Tuple<>(m, i));
                    }
                }
            }

            if (list.isEmpty()) {
                this.field_15439.fill(0, 0, this.field_15439.n, this.field_15439.m, 5);
            } else {
                Tuple<Integer, Integer> pair = list.get(this.random.nextInt(list.size()));
                m = flagMatrix.get(pair.getA(), pair.getB());
                flagMatrix.set(pair.getA(), pair.getB(), m | 4194304);
                Direction direction = this.method_15040(this.field_15440, pair.getA(), pair.getB(), 1, m & '\uffff');
                n = pair.getA() + direction.getStepX();
                int o = pair.getB() + direction.getStepZ();

                for(int p = 0; p < this.field_15439.m; ++p) {
                    for(int q = 0; q < this.field_15439.n; ++q) {
                        if (!method_15047(this.field_15440, q, p)) {
                            this.field_15439.set(q, p, 5);
                        } else if (q == pair.getA() && p == pair.getB()) {
                            this.field_15439.set(q, p, 3);
                        } else if (q == n && p == o) {
                            this.field_15439.set(q, p, 3);
                            this.field_15443[2].set(q, p, 8388608);
                        }
                    }
                }

                List<Direction> list2 = Lists.newArrayList();

                for (Direction direction2 : Direction.Plane.HORIZONTAL) {
                    if (this.field_15439.get(n + direction2.getStepX(), o + direction2.getStepZ()) == 0) {
                        list2.add(direction2);
                    }
                }

                if (list2.isEmpty()) {
                    this.field_15439.fill(0, 0, this.field_15439.n, this.field_15439.m, 5);
                    flagMatrix.set(pair.getA(), pair.getB(), m);
                } else {
                    Direction direction3 = list2.get(this.random.nextInt(list2.size()));
                    this.method_15045(this.field_15439, n + direction3.getStepX(), o + direction3.getStepZ(), direction3, 4);

                    while(this.method_15046(this.field_15439)) {
                    }

                }
            }
        }

        private void method_15042(MansionPieces.FlagMatrix flagMatrix, MansionPieces.FlagMatrix flagMatrix2) {
            List<Tuple<Integer, Integer>> list = Lists.newArrayList();

            int k;
            for(k = 0; k < flagMatrix.m; ++k) {
                for(int j = 0; j < flagMatrix.n; ++j) {
                    if (flagMatrix.get(j, k) == 2) {
                        list.add(new Tuple<>(j, k));
                    }
                }
            }

            Collections.shuffle(list, this.random);
            k = 10;
            Iterator<Tuple<Integer, Integer>> var19 = list.iterator();

            while(true) {
                int l;
                int m;
                do {
                    if (!var19.hasNext()) {
                        return;
                    }

                    Tuple<Integer, Integer> pair = var19.next();
                    l = pair.getA();
                    m = pair.getB();
                } while(flagMatrix2.get(l, m) != 0);

                int n = l;
                int o = l;
                int p = m;
                int q = m;
                int r = 65536;
                if (flagMatrix2.get(l + 1, m) == 0 && flagMatrix2.get(l, m + 1) == 0 && flagMatrix2.get(l + 1, m + 1) == 0 && flagMatrix.get(l + 1, m) == 2 && flagMatrix.get(l, m + 1) == 2 && flagMatrix.get(l + 1, m + 1) == 2) {
                    o = l + 1;
                    q = m + 1;
                    r = 262144;
                } else if (flagMatrix2.get(l - 1, m) == 0 && flagMatrix2.get(l, m + 1) == 0 && flagMatrix2.get(l - 1, m + 1) == 0 && flagMatrix.get(l - 1, m) == 2 && flagMatrix.get(l, m + 1) == 2 && flagMatrix.get(l - 1, m + 1) == 2) {
                    n = l - 1;
                    q = m + 1;
                    r = 262144;
                } else if (flagMatrix2.get(l - 1, m) == 0 && flagMatrix2.get(l, m - 1) == 0 && flagMatrix2.get(l - 1, m - 1) == 0 && flagMatrix.get(l - 1, m) == 2 && flagMatrix.get(l, m - 1) == 2 && flagMatrix.get(l - 1, m - 1) == 2) {
                    n = l - 1;
                    p = m - 1;
                    r = 262144;
                } else if (flagMatrix2.get(l + 1, m) == 0 && flagMatrix.get(l + 1, m) == 2) {
                    o = l + 1;
                    r = 131072;
                } else if (flagMatrix2.get(l, m + 1) == 0 && flagMatrix.get(l, m + 1) == 2) {
                    q = m + 1;
                    r = 131072;
                } else if (flagMatrix2.get(l - 1, m) == 0 && flagMatrix.get(l - 1, m) == 2) {
                    n = l - 1;
                    r = 131072;
                } else if (flagMatrix2.get(l, m - 1) == 0 && flagMatrix.get(l, m - 1) == 2) {
                    p = m - 1;
                    r = 131072;
                }

                int s = this.random.nextBoolean() ? n : o;
                int t = this.random.nextBoolean() ? p : q;
                int u = 2097152;
                if (!flagMatrix.anyMatchAround(s, t, 1)) {
                    s = s == n ? o : n;
                    t = t == p ? q : p;
                    if (!flagMatrix.anyMatchAround(s, t, 1)) {
                        t = t == p ? q : p;
                        if (!flagMatrix.anyMatchAround(s, t, 1)) {
                            s = s == n ? o : n;
                            t = t == p ? q : p;
                            if (!flagMatrix.anyMatchAround(s, t, 1)) {
                                u = 0;
                                s = n;
                                t = p;
                            }
                        }
                    }
                }

                for(int v = p; v <= q; ++v) {
                    for(int w = n; w <= o; ++w) {
                        if (w == s && v == t) {
                            flagMatrix2.set(w, v, 1048576 | u | r | k);
                        } else {
                            flagMatrix2.set(w, v, r | k);
                        }
                    }
                }

                ++k;
            }
        }
    }

    static class LayoutGenerator {
        private final StructureManager manager;
        private final Random random;
        private int field_15446;
        private int field_15445;

        public LayoutGenerator(StructureManager manager, Random random) {
            this.manager = manager;
            this.random = random;
        }

        public void generate(BlockPos pos, Rotation rotation, List<MansionPieces.Piece> pieces, MansionPieces.MansionParameters mansionParameters, Piece.MANSIONTYPE type) {
            MansionPieces.GenerationPiece generationPiece = new MansionPieces.GenerationPiece();
            generationPiece.position = pos;
            generationPiece.rotation = rotation;
            generationPiece.template = "wall_flat";
            MansionPieces.GenerationPiece generationPiece2 = new MansionPieces.GenerationPiece();
            this.addEntrance(pieces, generationPiece, type);
            generationPiece2.position = generationPiece.position.above(8);
            generationPiece2.rotation = generationPiece.rotation;
            generationPiece2.template = "wall_window";

            MansionPieces.FlagMatrix flagMatrix = mansionParameters.field_15440;
            MansionPieces.FlagMatrix flagMatrix2 = mansionParameters.field_15439;
            this.field_15446 = mansionParameters.field_15442 + 1;
            this.field_15445 = mansionParameters.field_15441 + 1;
            int i = mansionParameters.field_15442 + 1;
            int j = mansionParameters.field_15441;
            this.addRoof(pieces, generationPiece, flagMatrix, Direction.SOUTH, this.field_15446, this.field_15445, i, j, type);
            this.addRoof(pieces, generationPiece2, flagMatrix, Direction.SOUTH, this.field_15446, this.field_15445, i, j, type);
            MansionPieces.GenerationPiece generationPiece3 = new MansionPieces.GenerationPiece();
            generationPiece3.position = generationPiece.position.above(19);
            generationPiece3.rotation = generationPiece.rotation;
            generationPiece3.template = "wall_window";
            boolean bl = false;

            int m;
            for(int k = 0; k < flagMatrix2.m && !bl; ++k) {
                for(m = flagMatrix2.n - 1; m >= 0 && !bl; --m) {
                    if (MansionPieces.MansionParameters.method_15047(flagMatrix2, m, k)) {
                        generationPiece3.position = generationPiece3.position.relative(rotation.rotate(Direction.SOUTH), 8 + (k - this.field_15445) * 8);
                        generationPiece3.position = generationPiece3.position.relative(rotation.rotate(Direction.EAST), (m - this.field_15446) * 8);
                        this.method_15052(pieces, generationPiece3, type);
                        this.addRoof(pieces, generationPiece3, flagMatrix2, Direction.SOUTH, m, k, m, k, type);
                        bl = true;
                    }
                }
            }

            this.method_15055(pieces, pos.above(16), rotation, flagMatrix, flagMatrix2, type);
            this.method_15055(pieces, pos.above(27), rotation, flagMatrix2, null, type);

            MansionPieces.RoomPool[] roomPools = new MansionPieces.RoomPool[]{new MansionPieces.FirstFloorRoomPool(), new MansionPieces.SecondFloorRoomPool(), new MansionPieces.ThirdFloorRoomPool()};

            for(m = 0; m < 3; ++m) {
                BlockPos blockPos = pos.above(8 * m + (m == 2 ? 3 : 0));
                MansionPieces.FlagMatrix flagMatrix3 = mansionParameters.field_15443[m];
                MansionPieces.FlagMatrix flagMatrix4 = m == 2 ? flagMatrix2 : flagMatrix;
                String string = m == 0 ? "carpet_south_1" : "carpet_south_2";
                String string2 = m == 0 ? "carpet_west_1" : "carpet_west_2";

                for(int n = 0; n < flagMatrix4.m; ++n) {
                    for(int o = 0; o < flagMatrix4.n; ++o) {
                        if (flagMatrix4.get(o, n) == 1) {
                            BlockPos blockPos2 = blockPos.relative(rotation.rotate(Direction.SOUTH), 8 + (n - this.field_15445) * 8);
                            blockPos2 = blockPos2.relative(rotation.rotate(Direction.EAST), (o - this.field_15446) * 8);
                            pieces.add(new MansionPieces.Piece(this.manager, "corridor_floor", blockPos2, rotation, type));
                            if (flagMatrix4.get(o, n - 1) == 1 || (flagMatrix3.get(o, n - 1) & 8388608) == 8388608) {
                                pieces.add(new MansionPieces.Piece(this.manager, "carpet_north", blockPos2.relative(rotation.rotate(Direction.EAST), 1).above(), rotation, type));
                            }

                            if (flagMatrix4.get(o + 1, n) == 1 || (flagMatrix3.get(o + 1, n) & 8388608) == 8388608) {
                                pieces.add(new MansionPieces.Piece(this.manager, "carpet_east", blockPos2.relative(rotation.rotate(Direction.SOUTH), 1).relative(rotation.rotate(Direction.EAST), 5).above(), rotation, type));
                            }

                            if (flagMatrix4.get(o, n + 1) == 1 || (flagMatrix3.get(o, n + 1) & 8388608) == 8388608) {
                                pieces.add(new MansionPieces.Piece(this.manager, string, blockPos2.relative(rotation.rotate(Direction.SOUTH), 5).relative(rotation.rotate(Direction.WEST), 1), rotation, type));
                            }

                            if (flagMatrix4.get(o - 1, n) == 1 || (flagMatrix3.get(o - 1, n) & 8388608) == 8388608) {
                                pieces.add(new MansionPieces.Piece(this.manager, string2, blockPos2.relative(rotation.rotate(Direction.WEST), 1).relative(rotation.rotate(Direction.NORTH), 1), rotation, type));
                            }
                        }
                    }
                }

                String string3 = m == 0 ? "indoors_wall_1" : "indoors_wall_2";
                String string4 = m == 0 ? "indoors_door_1" : "indoors_door_2";
                List<Direction> list = Lists.newArrayList();

                for(int p = 0; p < flagMatrix4.m; ++p) {
                    for(int q = 0; q < flagMatrix4.n; ++q) {
                        boolean bl2 = m == 2 && flagMatrix4.get(q, p) == 3;
                        if (flagMatrix4.get(q, p) == 2 || bl2) {
                            int r = flagMatrix3.get(q, p);
                            int s = r & 983040;
                            int t = r & '\uffff';
                            bl2 = bl2 && (r & 8388608) == 8388608;
                            list.clear();
                            if ((r & 2097152) == 2097152) {
                                for (Direction direction : Direction.Plane.HORIZONTAL) {
                                    if (flagMatrix4.get(q + direction.getStepX(), p + direction.getStepZ()) == 1) {
                                        list.add(direction);
                                    }
                                }
                            }

                            Direction direction2 = null;
                            if (!list.isEmpty()) {
                                direction2 = list.get(this.random.nextInt(list.size()));
                            } else if ((r & 1048576) == 1048576) {
                                direction2 = Direction.UP;
                            }

                            BlockPos blockPos3 = blockPos.relative(rotation.rotate(Direction.SOUTH), 8 + (p - this.field_15445) * 8);
                            blockPos3 = blockPos3.relative(rotation.rotate(Direction.EAST), -1 + (q - this.field_15446) * 8);
                            if (MansionPieces.MansionParameters.method_15047(flagMatrix4, q - 1, p) && !mansionParameters.method_15039(flagMatrix4, q - 1, p, m, t)) {
                                pieces.add(new MansionPieces.Piece(this.manager, direction2 == Direction.WEST ? string4 : string3, blockPos3, rotation, type));
                            }

                            BlockPos blockPos6;
                            if (flagMatrix4.get(q + 1, p) == 1 && !bl2) {
                                blockPos6 = blockPos3.relative(rotation.rotate(Direction.EAST), 8);
                                pieces.add(new MansionPieces.Piece(this.manager, direction2 == Direction.EAST ? string4 : string3, blockPos6, rotation, type));
                            }

                            if (MansionPieces.MansionParameters.method_15047(flagMatrix4, q, p + 1) && !mansionParameters.method_15039(flagMatrix4, q, p + 1, m, t)) {
                                blockPos6 = blockPos3.relative(rotation.rotate(Direction.SOUTH), 7);
                                blockPos6 = blockPos6.relative(rotation.rotate(Direction.EAST), 7);
                                pieces.add(new MansionPieces.Piece(this.manager, direction2 == Direction.SOUTH ? string4 : string3, blockPos6, rotation.getRotated(Rotation.CLOCKWISE_90), type));
                            }

                            if (flagMatrix4.get(q, p - 1) == 1 && !bl2) {
                                blockPos6 = blockPos3.relative(rotation.rotate(Direction.NORTH), 1);
                                blockPos6 = blockPos6.relative(rotation.rotate(Direction.EAST), 7);
                                pieces.add(new MansionPieces.Piece(this.manager, direction2 == Direction.NORTH ? string4 : string3, blockPos6, rotation.getRotated(Rotation.CLOCKWISE_90), type));
                            }

                            if (s == 65536) {
                                this.addSmallRoom(pieces, blockPos3, rotation, direction2, roomPools[m], type);
                            } else {
                                Direction direction4;
                                if (s == 131072 && direction2 != null) {
                                    direction4 = mansionParameters.method_15040(flagMatrix4, q, p, m, t);
                                    boolean bl3 = (r & 4194304) == 4194304;
                                    this.addMediumRoom(pieces, blockPos3, rotation, direction4, direction2, roomPools[m], bl3, type);
                                } else if (s == 262144 && direction2 != null && direction2 != Direction.UP) {
                                    direction4 = direction2.getClockWise();
                                    if (!mansionParameters.method_15039(flagMatrix4, q + direction4.getStepX(), p + direction4.getStepZ(), m, t)) {
                                        direction4 = direction4.getOpposite();
                                    }

                                    this.addBigRoom(pieces, blockPos3, rotation, direction4, direction2, roomPools[m], type);
                                } else if (s == 262144 && direction2 == Direction.UP) {
                                    this.addBigSecretRoom(pieces, blockPos3, rotation, roomPools[m], type);
                                }
                            }
                        }
                    }
                }
            }

        }

        private void addRoof(List<MansionPieces.Piece> list, MansionPieces.GenerationPiece generationPiece, MansionPieces.FlagMatrix flagMatrix, Direction direction, int i, int j, int k, int l, Piece.MANSIONTYPE type) {
            int m = i;
            int n = j;
            Direction direction2 = direction;

            do {
                if (!MansionPieces.MansionParameters.method_15047(flagMatrix, m + direction.getStepX(), n + direction.getStepZ())) {
                    this.method_15058(list, generationPiece, type);
                    direction = direction.getClockWise();
                    if (m != k || n != l || direction2 != direction) {
                        this.method_15052(list, generationPiece, type);
                    }
                } else if (MansionPieces.MansionParameters.method_15047(flagMatrix, m + direction.getStepX(), n + direction.getStepZ()) && MansionPieces.MansionParameters.method_15047(flagMatrix, m + direction.getStepX() + direction.getCounterClockWise().getStepX(), n + direction.getStepZ() + direction.getCounterClockWise().getStepZ())) {
                    this.method_15060(list, generationPiece);
                    m += direction.getStepX();
                    n += direction.getStepZ();
                    direction = direction.getCounterClockWise();
                } else {
                    m += direction.getStepX();
                    n += direction.getStepZ();
                    if (m != k || n != l || direction2 != direction) {
                        this.method_15052(list, generationPiece, type);
                    }
                }
            } while(m != k || n != l || direction2 != direction);

        }

        private void method_15055(List<MansionPieces.Piece> list, BlockPos blockPos, Rotation blockRotation, MansionPieces.FlagMatrix flagMatrix,  MansionPieces.FlagMatrix flagMatrix2, Piece.MANSIONTYPE type) {
            int k;
            int l;
            BlockPos blockPos7;
            boolean bl3;
            BlockPos blockPos15;
            for(k = 0; k < flagMatrix.m; ++k) {
                for(l = 0; l < flagMatrix.n; ++l) {
                    blockPos7 = blockPos.relative(blockRotation.rotate(Direction.SOUTH), 8 + (k - this.field_15445) * 8);
                    blockPos7 = blockPos7.relative(blockRotation.rotate(Direction.EAST), (l - this.field_15446) * 8);
                    bl3 = flagMatrix2 != null && MansionPieces.MansionParameters.method_15047(flagMatrix2, l, k);
                    if (MansionPieces.MansionParameters.method_15047(flagMatrix, l, k) && !bl3) {
                        list.add(new MansionPieces.Piece(this.manager, "roof", blockPos7.above(3), blockRotation, type));
                        if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l + 1, k)) {
                            blockPos15 = blockPos7.relative(blockRotation.rotate(Direction.EAST), 6);
                            list.add(new MansionPieces.Piece(this.manager, "roof_front", blockPos15, blockRotation, type));
                        }

                        if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l - 1, k)) {
                            blockPos15 = blockPos7.relative(blockRotation.rotate(Direction.EAST), 0);
                            blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 7);
                            list.add(new MansionPieces.Piece(this.manager, "roof_front", blockPos15, blockRotation.getRotated(Rotation.CLOCKWISE_180), type));
                        }

                        if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l, k - 1)) {
                            blockPos15 = blockPos7.relative(blockRotation.rotate(Direction.WEST), 1);
                            list.add(new MansionPieces.Piece(this.manager, "roof_front", blockPos15, blockRotation.getRotated(Rotation.COUNTERCLOCKWISE_90), type));
                        }

                        if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l, k + 1)) {
                            blockPos15 = blockPos7.relative(blockRotation.rotate(Direction.EAST), 6);
                            blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 6);
                            list.add(new MansionPieces.Piece(this.manager, "roof_front", blockPos15, blockRotation.getRotated(Rotation.CLOCKWISE_90), type));
                        }
                    }
                }
            }

            if (flagMatrix2 != null) {
                for(k = 0; k < flagMatrix.m; ++k) {
                    for(l = 0; l < flagMatrix.n; ++l) {
                        blockPos7 = blockPos.relative(blockRotation.rotate(Direction.SOUTH), 8 + (k - this.field_15445) * 8);
                        blockPos7 = blockPos7.relative(blockRotation.rotate(Direction.EAST), (l - this.field_15446) * 8);
                        bl3 = MansionPieces.MansionParameters.method_15047(flagMatrix2, l, k);
                        if (MansionPieces.MansionParameters.method_15047(flagMatrix, l, k) && bl3) {
                            if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l + 1, k)) {
                                blockPos15 = blockPos7.relative(blockRotation.rotate(Direction.EAST), 7);
                                list.add(new MansionPieces.Piece(this.manager, "small_wall", blockPos15, blockRotation, type));
                            }

                            if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l - 1, k)) {
                                blockPos15 = blockPos7.relative(blockRotation.rotate(Direction.WEST), 1);
                                blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 6);
                                list.add(new MansionPieces.Piece(this.manager, "small_wall", blockPos15, blockRotation.getRotated(Rotation.CLOCKWISE_180), type));
                            }

                            if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l, k - 1)) {
                                blockPos15 = blockPos7.relative(blockRotation.rotate(Direction.WEST), 0);
                                blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.NORTH), 1);
                                list.add(new MansionPieces.Piece(this.manager, "small_wall", blockPos15, blockRotation.getRotated(Rotation.COUNTERCLOCKWISE_90), type));
                            }

                            if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l, k + 1)) {
                                blockPos15 = blockPos7.relative(blockRotation.rotate(Direction.EAST), 6);
                                blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 7);
                                list.add(new MansionPieces.Piece(this.manager, "small_wall", blockPos15, blockRotation.getRotated(Rotation.CLOCKWISE_90), type));
                            }

                            if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l + 1, k)) {
                                if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l, k - 1)) {
                                    blockPos15 = blockPos7.relative(blockRotation.rotate(Direction.EAST), 7);
                                    blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.NORTH), 2);
                                    list.add(new MansionPieces.Piece(this.manager, "small_wall_corner", blockPos15, blockRotation, type));
                                }

                                if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l, k + 1)) {
                                    blockPos15 = blockPos7.relative(blockRotation.rotate(Direction.EAST), 8);
                                    blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 7);
                                    list.add(new MansionPieces.Piece(this.manager, "small_wall_corner", blockPos15, blockRotation.getRotated(Rotation.CLOCKWISE_90), type));
                                }
                            }

                            if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l - 1, k)) {
                                if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l, k - 1)) {
                                    blockPos15 = blockPos7.relative(blockRotation.rotate(Direction.WEST), 2);
                                    blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.NORTH), 1);
                                    list.add(new MansionPieces.Piece(this.manager, "small_wall_corner", blockPos15, blockRotation.getRotated(Rotation.COUNTERCLOCKWISE_90), type));
                                }

                                if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l, k + 1)) {
                                    blockPos15 = blockPos7.relative(blockRotation.rotate(Direction.WEST), 1);
                                    blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 8);
                                    list.add(new MansionPieces.Piece(this.manager, "small_wall_corner", blockPos15, blockRotation.getRotated(Rotation.CLOCKWISE_180), type));
                                }
                            }
                        }
                    }
                }
            }

            for(k = 0; k < flagMatrix.m; ++k) {
                for(l = 0; l < flagMatrix.n; ++l) {
                    blockPos7 = blockPos.relative(blockRotation.rotate(Direction.SOUTH), 8 + (k - this.field_15445) * 8);
                    blockPos7 = blockPos7.relative(blockRotation.rotate(Direction.EAST), (l - this.field_15446) * 8);
                    bl3 = flagMatrix2 != null && MansionPieces.MansionParameters.method_15047(flagMatrix2, l, k);
                    if (MansionPieces.MansionParameters.method_15047(flagMatrix, l, k) && !bl3) {
                        BlockPos blockPos24;
                        if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l + 1, k)) {
                            blockPos15 = blockPos7.relative(blockRotation.rotate(Direction.EAST), 6);
                            if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l, k + 1)) {
                                blockPos24 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 6);
                                list.add(new MansionPieces.Piece(this.manager, "roof_corner", blockPos24, blockRotation, type));
                            } else if (MansionPieces.MansionParameters.method_15047(flagMatrix, l + 1, k + 1)) {
                                blockPos24 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 5);
                                list.add(new MansionPieces.Piece(this.manager, "roof_inner_corner", blockPos24, blockRotation, type));
                            }

                            if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l, k - 1)) {
                                list.add(new MansionPieces.Piece(this.manager, "roof_corner", blockPos15, blockRotation.getRotated(Rotation.COUNTERCLOCKWISE_90), type));
                            } else if (MansionPieces.MansionParameters.method_15047(flagMatrix, l + 1, k - 1)) {
                                blockPos24 = blockPos7.relative(blockRotation.rotate(Direction.EAST), 9);
                                blockPos24 = blockPos24.relative(blockRotation.rotate(Direction.NORTH), 2);
                                list.add(new MansionPieces.Piece(this.manager, "roof_inner_corner", blockPos24, blockRotation.getRotated(Rotation.CLOCKWISE_90), type));
                            }
                        }

                        if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l - 1, k)) {
                            blockPos15 = blockPos7.relative(blockRotation.rotate(Direction.EAST), 0);
                            blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 0);
                            if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l, k + 1)) {
                                blockPos24 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 6);
                                list.add(new MansionPieces.Piece(this.manager, "roof_corner", blockPos24, blockRotation.getRotated(Rotation.CLOCKWISE_90), type));
                            } else if (MansionPieces.MansionParameters.method_15047(flagMatrix, l - 1, k + 1)) {
                                blockPos24 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 8);
                                blockPos24 = blockPos24.relative(blockRotation.rotate(Direction.WEST), 3);
                                list.add(new MansionPieces.Piece(this.manager, "roof_inner_corner", blockPos24, blockRotation.getRotated(Rotation.COUNTERCLOCKWISE_90), type));
                            }

                            if (!MansionPieces.MansionParameters.method_15047(flagMatrix, l, k - 1)) {
                                list.add(new MansionPieces.Piece(this.manager, "roof_corner", blockPos15, blockRotation.getRotated(Rotation.CLOCKWISE_180), type));
                            } else if (MansionPieces.MansionParameters.method_15047(flagMatrix, l - 1, k - 1)) {
                                blockPos24 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 1);
                                list.add(new MansionPieces.Piece(this.manager, "roof_inner_corner", blockPos24, blockRotation.getRotated(Rotation.CLOCKWISE_180), type));
                            }
                        }
                    }
                }
            }

        }

        private void addEntrance(List<MansionPieces.Piece> list, MansionPieces.GenerationPiece generationPiece, Piece.MANSIONTYPE type) {
            Direction direction = generationPiece.rotation.rotate(Direction.WEST);
            list.add(new MansionPieces.Piece(this.manager, "entrance", generationPiece.position.relative(direction, 9), generationPiece.rotation, type));
            generationPiece.position = generationPiece.position.relative(generationPiece.rotation.rotate(Direction.SOUTH), 16);
        }

        private void method_15052(List<MansionPieces.Piece> list, MansionPieces.GenerationPiece generationPiece, Piece.MANSIONTYPE type) {
            list.add(new MansionPieces.Piece(this.manager, generationPiece.template, generationPiece.position.relative(generationPiece.rotation.rotate(Direction.EAST), 7), generationPiece.rotation, type));
            generationPiece.position = generationPiece.position.relative(generationPiece.rotation.rotate(Direction.SOUTH), 8);
        }

        private void method_15058(List<MansionPieces.Piece> list, MansionPieces.GenerationPiece generationPiece, Piece.MANSIONTYPE type) {
            generationPiece.position = generationPiece.position.relative(generationPiece.rotation.rotate(Direction.SOUTH), -1);
            list.add(new MansionPieces.Piece(this.manager, "wall_corner", generationPiece.position, generationPiece.rotation, type));
            generationPiece.position = generationPiece.position.relative(generationPiece.rotation.rotate(Direction.SOUTH), -7);
            generationPiece.position = generationPiece.position.relative(generationPiece.rotation.rotate(Direction.WEST), -6);
            generationPiece.rotation = generationPiece.rotation.getRotated(Rotation.CLOCKWISE_90);
        }

        private void method_15060(List<MansionPieces.Piece> list, MansionPieces.GenerationPiece generationPiece) {
            generationPiece.position = generationPiece.position.relative(generationPiece.rotation.rotate(Direction.SOUTH), 6);
            generationPiece.position = generationPiece.position.relative(generationPiece.rotation.rotate(Direction.EAST), 8);
            generationPiece.rotation = generationPiece.rotation.getRotated(Rotation.COUNTERCLOCKWISE_90);
        }

        private void addSmallRoom(List<MansionPieces.Piece> list, BlockPos blockPos, Rotation blockRotation, Direction direction, MansionPieces.RoomPool roomPool, Piece.MANSIONTYPE type) {
            Rotation blockRotation2 = Rotation.NONE;
            String string = roomPool.getSmallRoom(this.random);
            if (direction != Direction.EAST) {
                if (direction == Direction.NORTH) {
                    blockRotation2 = blockRotation2.getRotated(Rotation.COUNTERCLOCKWISE_90);
                } else if (direction == Direction.WEST) {
                    blockRotation2 = blockRotation2.getRotated(Rotation.CLOCKWISE_180);
                } else if (direction == Direction.SOUTH) {
                    blockRotation2 = blockRotation2.getRotated(Rotation.CLOCKWISE_90);
                } else {
                    string = roomPool.getSmallSecretRoom(this.random);
                }
            }

            BlockPos blockPos2 = StructureTemplate.getZeroPositionWithTransform(new BlockPos(1, 0, 0), Mirror.NONE, blockRotation2, 7, 7);
            blockRotation2 = blockRotation2.getRotated(blockRotation);
            blockPos2 = blockPos2.rotate(blockRotation);
            BlockPos blockPos3 = blockPos.offset(blockPos2.getX(), 0, blockPos2.getZ());
            list.add(new MansionPieces.Piece(this.manager, string, blockPos3, blockRotation2, type));
        }

        private void addMediumRoom(List<MansionPieces.Piece> list, BlockPos blockPos, Rotation blockRotation, Direction direction, Direction direction2, MansionPieces.RoomPool roomPool, boolean staircase, Piece.MANSIONTYPE type) {
            BlockPos blockPos15;
            if (direction2 == Direction.EAST && direction == Direction.SOUTH) {
                blockPos15 = blockPos.relative(blockRotation.rotate(Direction.EAST), 1);
                list.add(new MansionPieces.Piece(this.manager, roomPool.getMediumFunctionalRoom(this.random, staircase), blockPos15, blockRotation, type));
            } else if (direction2 == Direction.EAST && direction == Direction.NORTH) {
                blockPos15 = blockPos.relative(blockRotation.rotate(Direction.EAST), 1);
                blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 6);
                list.add(new MansionPieces.Piece(this.manager, roomPool.getMediumFunctionalRoom(this.random, staircase), blockPos15, blockRotation, Mirror.LEFT_RIGHT, type));
            } else if (direction2 == Direction.WEST && direction == Direction.NORTH) {
                blockPos15 = blockPos.relative(blockRotation.rotate(Direction.EAST), 7);
                blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 6);
                list.add(new MansionPieces.Piece(this.manager, roomPool.getMediumFunctionalRoom(this.random, staircase), blockPos15, blockRotation.getRotated(Rotation.CLOCKWISE_180), type));
            } else if (direction2 == Direction.WEST && direction == Direction.SOUTH) {
                blockPos15 = blockPos.relative(blockRotation.rotate(Direction.EAST), 7);
                list.add(new MansionPieces.Piece(this.manager, roomPool.getMediumFunctionalRoom(this.random, staircase), blockPos15, blockRotation, Mirror.FRONT_BACK, type));
            } else if (direction2 == Direction.SOUTH && direction == Direction.EAST) {
                blockPos15 = blockPos.relative(blockRotation.rotate(Direction.EAST), 1);
                list.add(new MansionPieces.Piece(this.manager, roomPool.getMediumFunctionalRoom(this.random, staircase), blockPos15, blockRotation.getRotated(Rotation.CLOCKWISE_90), Mirror.LEFT_RIGHT, type));
            } else if (direction2 == Direction.SOUTH && direction == Direction.WEST) {
                blockPos15 = blockPos.relative(blockRotation.rotate(Direction.EAST), 7);
                list.add(new MansionPieces.Piece(this.manager, roomPool.getMediumFunctionalRoom(this.random, staircase), blockPos15, blockRotation.getRotated(Rotation.CLOCKWISE_90), type));
            } else if (direction2 == Direction.NORTH && direction == Direction.WEST) {
                blockPos15 = blockPos.relative(blockRotation.rotate(Direction.EAST), 7);
                blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 6);
                list.add(new MansionPieces.Piece(this.manager, roomPool.getMediumFunctionalRoom(this.random, staircase), blockPos15, blockRotation.getRotated(Rotation.CLOCKWISE_90), Mirror.FRONT_BACK, type));
            } else if (direction2 == Direction.NORTH && direction == Direction.EAST) {
                blockPos15 = blockPos.relative(blockRotation.rotate(Direction.EAST), 1);
                blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 6);
                list.add(new MansionPieces.Piece(this.manager, roomPool.getMediumFunctionalRoom(this.random, staircase), blockPos15, blockRotation.getRotated(Rotation.COUNTERCLOCKWISE_90), type));
            } else if (direction2 == Direction.SOUTH && direction == Direction.NORTH) {
                blockPos15 = blockPos.relative(blockRotation.rotate(Direction.EAST), 1);
                blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.NORTH), 8);
                list.add(new MansionPieces.Piece(this.manager, roomPool.getMediumGenericRoom(this.random, staircase), blockPos15, blockRotation, type));
            } else if (direction2 == Direction.NORTH && direction == Direction.SOUTH) {
                blockPos15 = blockPos.relative(blockRotation.rotate(Direction.EAST), 7);
                blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 14);
                list.add(new MansionPieces.Piece(this.manager, roomPool.getMediumGenericRoom(this.random, staircase), blockPos15, blockRotation.getRotated(Rotation.CLOCKWISE_180), type));
            } else if (direction2 == Direction.WEST && direction == Direction.EAST) {
                blockPos15 = blockPos.relative(blockRotation.rotate(Direction.EAST), 15);
                list.add(new MansionPieces.Piece(this.manager, roomPool.getMediumGenericRoom(this.random, staircase), blockPos15, blockRotation.getRotated(Rotation.CLOCKWISE_90), type));
            } else if (direction2 == Direction.EAST && direction == Direction.WEST) {
                blockPos15 = blockPos.relative(blockRotation.rotate(Direction.WEST), 7);
                blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.SOUTH), 6);
                list.add(new MansionPieces.Piece(this.manager, roomPool.getMediumGenericRoom(this.random, staircase), blockPos15, blockRotation.getRotated(Rotation.COUNTERCLOCKWISE_90), type));
            } else if (direction2 == Direction.UP && direction == Direction.EAST) {
                blockPos15 = blockPos.relative(blockRotation.rotate(Direction.EAST), 15);
                list.add(new MansionPieces.Piece(this.manager, roomPool.getMediumSecretRoom(this.random), blockPos15, blockRotation.getRotated(Rotation.CLOCKWISE_90), type));
            } else if (direction2 == Direction.UP && direction == Direction.SOUTH) {
                blockPos15 = blockPos.relative(blockRotation.rotate(Direction.EAST), 1);
                blockPos15 = blockPos15.relative(blockRotation.rotate(Direction.NORTH), 0);
                list.add(new MansionPieces.Piece(this.manager, roomPool.getMediumSecretRoom(this.random), blockPos15, blockRotation, type));
            }

        }

        private void addBigRoom(List<MansionPieces.Piece> list, BlockPos blockPos, Rotation blockRotation, Direction direction, Direction direction2, MansionPieces.RoomPool roomPool, Piece.MANSIONTYPE type) {
            int i = 0;
            int j = 0;
            Rotation blockRotation2 = blockRotation;
            Mirror blockMirror = Mirror.NONE;
            if (direction2 == Direction.EAST && direction == Direction.SOUTH) {
                i = -7;
            } else if (direction2 == Direction.EAST && direction == Direction.NORTH) {
                i = -7;
                j = 6;
                blockMirror = Mirror.LEFT_RIGHT;
            } else if (direction2 == Direction.NORTH && direction == Direction.EAST) {
                i = 1;
                j = 14;
                blockRotation2 = blockRotation.getRotated(Rotation.COUNTERCLOCKWISE_90);
            } else if (direction2 == Direction.NORTH && direction == Direction.WEST) {
                i = 7;
                j = 14;
                blockRotation2 = blockRotation.getRotated(Rotation.COUNTERCLOCKWISE_90);
                blockMirror = Mirror.LEFT_RIGHT;
            } else if (direction2 == Direction.SOUTH && direction == Direction.WEST) {
                i = 7;
                j = -8;
                blockRotation2 = blockRotation.getRotated(Rotation.CLOCKWISE_90);
            } else if (direction2 == Direction.SOUTH && direction == Direction.EAST) {
                i = 1;
                j = -8;
                blockRotation2 = blockRotation.getRotated(Rotation.CLOCKWISE_90);
                blockMirror = Mirror.LEFT_RIGHT;
            } else if (direction2 == Direction.WEST && direction == Direction.NORTH) {
                i = 15;
                j = 6;
                blockRotation2 = blockRotation.getRotated(Rotation.CLOCKWISE_180);
            } else if (direction2 == Direction.WEST && direction == Direction.SOUTH) {
                i = 15;
                blockMirror = Mirror.FRONT_BACK;
            }

            BlockPos blockPos2 = blockPos.relative(blockRotation.rotate(Direction.EAST), i);
            blockPos2 = blockPos2.relative(blockRotation.rotate(Direction.SOUTH), j);
            list.add(new MansionPieces.Piece(this.manager, roomPool.getBigRoom(this.random), blockPos2, blockRotation2, blockMirror, type));
        }

        private void addBigSecretRoom(List<MansionPieces.Piece> list, BlockPos blockPos, Rotation blockRotation, MansionPieces.RoomPool roomPool, Piece.MANSIONTYPE type) {
            BlockPos blockPos2 = blockPos.relative(blockRotation.rotate(Direction.EAST), 1);
            list.add(new MansionPieces.Piece(this.manager, roomPool.getBigSecretRoom(this.random), blockPos2, blockRotation, Mirror.NONE, type));
        }
    }

    static class GenerationPiece {
        public Rotation rotation;
        public BlockPos position;
        public String template;

        private GenerationPiece() {
        }
    }

    public static class Piece extends TemplateStructurePiece {
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

        public Piece(StructureManager structureManager, String template, BlockPos pos, Rotation rotation, Mirror mirror, MANSIONTYPE type) {
            super(RSStructurePieces.MANSION_PIECE, 0, structureManager, getId(template, type), getId(template, type).toString(), createPlacementData(mirror, rotation), pos);
        }

        public Piece(ServerLevel world, CompoundTag nbt) {
            super(RSStructurePieces.MANSION_PIECE, nbt, world, (identifier) ->
                    createPlacementData(Mirror.valueOf(nbt.getString("Mi")), Rotation.valueOf(nbt.getString("Rot"))));
        }

        public Piece(StructureManager structureManager, String template, BlockPos pos, Rotation rotation, MANSIONTYPE type) {
            this(structureManager, template, pos, rotation, Mirror.NONE, type);
        }

        private static ResourceLocation getId(String identifier, MANSIONTYPE type) {
            return new ResourceLocation(RepurposedStructures.MODID, "mansions/" + type.name().toLowerCase(Locale.ROOT) + "/" + identifier);
        }

        private static StructurePlaceSettings createPlacementData(Mirror mirror, Rotation rotation) {
            return (new StructurePlaceSettings()).setIgnoreEntities(false).setRotation(rotation).setMirror(mirror);
        }

        protected void addAdditionalSaveData(ServerLevel world, CompoundTag nbt) {
            super.addAdditionalSaveData(world, nbt);
            nbt.putString("Rot", this.placeSettings.getRotation().name());
            nbt.putString("Mi", this.placeSettings.getMirror().name());
        }

        protected void handleDataMarker(String metadata, BlockPos pos, ServerLevelAccessor serverWorldAccess, Random random, BoundingBox boundingBox) { }
    }
}
