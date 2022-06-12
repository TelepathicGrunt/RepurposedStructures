package com.telepathicgrunt.repurposedstructures.world.structures.pieces;

import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MonumentPieces {
    private MonumentPieces() {
    }

    public static List<StructurePiece> createMonumentBuilding(RegistryAccess registryAccess, StructureTemplateManager StructureTemplateManager, RandomSource random, int x, int y, int z, String monumentType) {
        Registry<StructureTemplatePool> poolRegistry = registryAccess.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);
        List<StructurePiece> pieces = new ArrayList<>();
        MonumentBuilding mainBuilding = new MonumentPieces.MonumentBuilding(poolRegistry, StructureTemplateManager, random, monumentType);
        mainBuilding.addMainBody(pieces, poolRegistry, StructureTemplateManager, random, Rotation.NONE, monumentType);
        pieces.addAll(mainBuilding.childPieces);
        pieces.forEach(piece -> piece.move(x, y, z));
        return pieces;
    }

    private static StructurePiece getJigsawPiece(Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager structureTemplateManager, String poolPath, BlockPos blockPos, Rotation rotation, RandomSource random, String type) {
        ResourceLocation resourceLocation = new ResourceLocation(poolPath.toLowerCase(Locale.ROOT));
        StructureTemplatePool pool = poolRegistry.get(resourceLocation);
        StructurePoolElement poolEntry;

        if(pool == null || pool.size() == 0) {
            RepurposedStructures.LOGGER.warn("Repurposed Structures: Empty or nonexistent pool: {}  Will not generate monument piece at spot.", resourceLocation + " - Monument type: " + type);
            poolEntry = StructurePoolElement.empty().apply(StructureTemplatePool.Projection.RIGID);
        }
        else {
            poolEntry = pool.getRandomTemplate(random);
        }

        return new PoolElementStructurePiece(
                structureTemplateManager,
                poolEntry,
                blockPos,
                poolEntry.getGroundLevelDelta(),
                rotation,
                poolEntry.getBoundingBox(structureTemplateManager, blockPos, rotation)
        );
    }

    private static BlockPos getRoomPosition(RoomGraph roomGraph, int specialOffset) {
        int index = roomGraph.index;
        int x = index % 5;
        int z = index / 5 % 5;
        int y = index / 25;
        return new BlockPos(x * 8, y * 4, -(z + specialOffset) * 8 + 1)
                .offset(9, 0, 35);
    }

    private static Rotation getOpeningRotationFull(Direction direction) {
        return switch (direction) {
            case UP, DOWN, NORTH -> Rotation.NONE;
            case EAST -> Rotation.CLOCKWISE_90;
            case SOUTH -> Rotation.CLOCKWISE_180;
            case WEST -> Rotation.COUNTERCLOCKWISE_90;
        };
    }

    public static class MonumentBuilding extends MonumentPieces.MonumentPiece {
        private RoomGraph sourceRoom;
        private RoomGraph coreRoom;
        protected final List<StructurePiece> childPieces = Lists.newArrayList();

        public MonumentBuilding(Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, RandomSource random, String type) {
            super(null);

            List<RoomGraph> graphList = this.generateRoomGraph(random);
            this.sourceRoom.claimed = true;
            this.childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/"+type+"/rooms/core", getRoomPosition(coreRoom, 2), Rotation.NONE, random, type));
            List<MonumentPieces.MonumentRoomFitter> fitterList = Lists.newArrayList();
            fitterList.add(new MonumentPieces.FitDoubleXYRoom());
            fitterList.add(new MonumentPieces.FitDoubleYZRoom());
            fitterList.add(new MonumentPieces.FitDoubleZRoom());
            fitterList.add(new MonumentPieces.FitDoubleXRoom());
            fitterList.add(new MonumentPieces.FitDoubleYRoom());
            fitterList.add(new MonumentPieces.FitSimpleTopRoom());
            fitterList.add(new MonumentPieces.FitSimplePillarRoom(random));
            fitterList.add(new MonumentPieces.FitSimpleRoom());

            for(RoomGraph chosenRoomGraph : graphList) {
                if (!chosenRoomGraph.claimed && !chosenRoomGraph.isSpecial()) {
                    for(MonumentPieces.MonumentRoomFitter fitter : fitterList) {
                        if (fitter.fits(chosenRoomGraph)) {
                            this.childPieces.add(fitter.create(poolRegistry, StructureTemplateManager, Rotation.NONE, chosenRoomGraph, random, type));
                            fitter.createOpenings(this.childPieces, poolRegistry, StructureTemplateManager, Rotation.NONE, chosenRoomGraph, random, type);
                            break;
                        }
                    }
                }
            }

            if (this.sourceRoom.hasOpening[Direction.WEST.get3DDataValue()]) {
                BlockPos pos = getRoomPosition(this.sourceRoom, 1).offset(0, 1, 3);
                childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/entrance_wall", pos, Rotation.NONE, random, type));
            }
            if (this.sourceRoom.hasOpening[Direction.EAST.get3DDataValue()]) {
                BlockPos pos = getRoomPosition(this.sourceRoom, 1).offset(6, 1, 3);
                childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/entrance_wall", pos, Rotation.NONE, random, type));
            }
            if (this.sourceRoom.hasOpening[Direction.NORTH.get3DDataValue()]) {
                BlockPos pos = getRoomPosition(this.sourceRoom, 1).offset(3, 1, 0);
                childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/wall_1", pos, Rotation.NONE, random, type));
            }
            if (this.sourceRoom.hasOpening[Direction.UP.get3DDataValue()]) {
                BlockPos pos = getRoomPosition(this.sourceRoom, 1).offset(2, 4, 2);
                childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/floor", pos, Rotation.NONE, random, type));
            }
        }

        public void addMainBody(List<StructurePiece> pieces, Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, RandomSource random, Rotation rotation, String type) {
            pieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/"+type+"/body/ne_corner", new BlockPos(29, 0, 0), rotation, random, type));
            pieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/"+type+"/body/nw_corner", new BlockPos(0, 0, 0), rotation, random, type));
            pieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/"+type+"/body/se_corner", new BlockPos(29, 0, 29), rotation, random, type));
            pieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/"+type+"/body/sw_corner", new BlockPos(0, 0, 29), rotation, random, type));
        }

        private List<RoomGraph> generateRoomGraph(RandomSource random) {
            RoomGraph[] roomGraph = new RoomGraph[75];

            for(int i = 0; i < 5; ++i) {
                for(int j = 0; j < 4; ++j) {
                    int l = getRoomIndex(i, 0, j);
                    roomGraph[l] = new RoomGraph(l);
                }
            }

            for(int i2 = 0; i2 < 5; ++i2) {
                for(int l2 = 0; l2 < 4; ++l2) {
                    int j4 = getRoomIndex(i2, 1, l2);
                    roomGraph[j4] = new RoomGraph(j4);
                }
            }

            for(int j2 = 1; j2 < 4; ++j2) {
                for(int i3 = 0; i3 < 2; ++i3) {
                    int k4 = getRoomIndex(j2, 2, i3);
                    roomGraph[k4] = new RoomGraph(k4);
                }
            }

            this.sourceRoom = roomGraph[GRIDROOM_SOURCE_INDEX];

            for(int k2 = 0; k2 < 5; ++k2) {
                for(int j3 = 0; j3 < 5; ++j3) {
                    for(int i4 = 0; i4 < 3; ++i4) {
                        int l4 = getRoomIndex(k2, i4, j3);
                        if (roomGraph[l4] != null) {
                            for(Direction direction : Direction.values()) {
                                int i1 = k2 + direction.getStepX();
                                int j1 = i4 + direction.getStepY();
                                int k1 = j3 + direction.getStepZ();
                                if (i1 >= 0 && i1 < 5 && k1 >= 0 && k1 < 5 && j1 >= 0 && j1 < 3) {
                                    int l1 = getRoomIndex(i1, j1, k1);
                                    if (roomGraph[l1] != null) {
                                        if (k1 == j3) {
                                            roomGraph[l4].setConnection(direction, roomGraph[l1]);
                                        } else {
                                            roomGraph[l4].setConnection(direction.getOpposite(), roomGraph[l1]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            RoomGraph roomGraph1 = new RoomGraph(1003);
            RoomGraph roomGraph2 = new RoomGraph(1001);
            RoomGraph roomGraph3 = new RoomGraph(1002);
            roomGraph[GRIDROOM_TOP_CONNECT_INDEX].setConnection(Direction.UP, roomGraph1);
            roomGraph[GRIDROOM_LEFTWING_CONNECT_INDEX].setConnection(Direction.SOUTH, roomGraph2);
            roomGraph[GRIDROOM_RIGHTWING_CONNECT_INDEX].setConnection(Direction.SOUTH, roomGraph3);
            roomGraph1.claimed = true;
            roomGraph2.claimed = true;
            roomGraph3.claimed = true;
            this.sourceRoom.isSource = true;
            this.coreRoom = roomGraph[getRoomIndex(random.nextInt(4), 0, 2)];
            coreRoom.claimed = true;
            coreRoom.connections[Direction.EAST.get3DDataValue()].claimed = true;
            coreRoom.connections[Direction.NORTH.get3DDataValue()].claimed = true;
            coreRoom.connections[Direction.EAST.get3DDataValue()].connections[Direction.NORTH.get3DDataValue()].claimed = true;
            coreRoom.connections[Direction.UP.get3DDataValue()].claimed = true;
            coreRoom.connections[Direction.EAST.get3DDataValue()].connections[Direction.UP.get3DDataValue()].claimed = true;
            coreRoom.connections[Direction.NORTH.get3DDataValue()].connections[Direction.UP.get3DDataValue()].claimed = true;
            coreRoom.connections[Direction.EAST.get3DDataValue()].connections[Direction.NORTH.get3DDataValue()].connections[Direction.UP.get3DDataValue()].claimed = true;
            ObjectArrayList<RoomGraph> list = new ObjectArrayList<>();

            for(RoomGraph monumentPieces$roomdefinition4 : roomGraph) {
                if (monumentPieces$roomdefinition4 != null) {
                    monumentPieces$roomdefinition4.updateOpenings();
                    list.add(monumentPieces$roomdefinition4);
                }
            }

            roomGraph1.updateOpenings();
            Util.shuffle(list, random);
            int i5 = 1;

            for(RoomGraph selectedRoomGraph : list) {
                int j5 = 0;
                int k5 = 0;

                while(j5 < 2 && k5 < 5) {
                    ++k5;
                    int l5 = random.nextInt(6);
                    if (selectedRoomGraph.hasOpening[l5]) {
                        int i6 = Direction.from3DDataValue(l5).getOpposite().get3DDataValue();
                        selectedRoomGraph.hasOpening[l5] = false;
                        selectedRoomGraph.connections[l5].hasOpening[i6] = false;
                        if (selectedRoomGraph.findSource(i5++) && selectedRoomGraph.connections[l5].findSource(i5++)) {
                            ++j5;
                        }
                        else {
                            selectedRoomGraph.hasOpening[l5] = true;
                            selectedRoomGraph.connections[l5].hasOpening[i6] = true;
                        }
                    }
                }
            }

            list.add(roomGraph1);
            list.add(roomGraph2);
            list.add(roomGraph3);
            return list;
        }
    }

    interface MonumentRoomFitter {
        boolean fits(RoomGraph roomGraph);

        StructurePiece create(Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation rotation, RoomGraph roomGraph, RandomSource random, String type);

        void createOpenings(List<StructurePiece> childPieces, Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation none, RoomGraph roomGraph, RandomSource random, String type);
    }

    static class FitDoubleXRoom implements MonumentPieces.MonumentRoomFitter {
        @Override
        public boolean fits(RoomGraph roomGraph) {
            return roomGraph.hasOpening[Direction.EAST.get3DDataValue()] && !roomGraph.connections[Direction.EAST.get3DDataValue()].claimed;
        }

        @Override
        public StructurePiece create(Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation rotation, RoomGraph roomGraph, RandomSource random, String type) {
            roomGraph.claimed = true;
            roomGraph.connections[Direction.EAST.get3DDataValue()].claimed = true;
            return getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/"+type+"/rooms/double_x", getRoomPosition(roomGraph, 1), rotation, random, type);
        }

        @Override
        public void createOpenings(List<StructurePiece> childPieces, Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation none, RoomGraph roomGraph, RandomSource random, String type) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                if (direction != Direction.EAST) {
                    if (roomGraph.hasOpening[direction.get3DDataValue()]) {
                        Rotation rotation = getOpeningRotationFull(direction);
                        BlockPos pos = getRoomPosition(roomGraph, 2)
                                .offset(3, 1, 11)
                                .relative(direction, 4);
                        if (rotation == Rotation.CLOCKWISE_180) {
                            pos = pos.offset(1, 0, 0);
                        }
                        else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                            pos = pos.offset(1, 0, 1);
                        }
                        else if (rotation == Rotation.NONE) {
                            pos = pos.offset(0, 0, 1);
                        }
                        childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/wall_3", pos, rotation, random, type));
                    }
                }
                if (direction != Direction.WEST) {
                    if (roomGraph.connections[Direction.EAST.get3DDataValue()].hasOpening[direction.get3DDataValue()]) {
                        Rotation rotation = getOpeningRotationFull(direction);
                        BlockPos pos = getRoomPosition(roomGraph, 2)
                                .offset(11, 1, 11)
                                .relative(direction, 4);
                        if (rotation == Rotation.CLOCKWISE_90) {
                            pos = pos.offset(0, 0, 0);
                        }
                        else if (rotation == Rotation.CLOCKWISE_180) {
                            pos = pos.offset(1, 0, 0);
                        }
                        else if (rotation == Rotation.NONE) {
                            pos = pos.offset(0, 0, 1);
                        }
                        childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/wall_3", pos, rotation, random, type));
                    }
                }
            }

            if (roomGraph.hasOpening[Direction.UP.get3DDataValue()]) {
                BlockPos pos = getRoomPosition(roomGraph, 2).offset(2, 4, 10);
                childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/floor", pos, Rotation.NONE, random, type));
            }
            if (roomGraph.connections[Direction.EAST.get3DDataValue()].hasOpening[Direction.UP.get3DDataValue()]) {
                BlockPos pos = getRoomPosition(roomGraph, 2).offset(10, 4, 10);
                childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/floor", pos, Rotation.NONE, random, type));
            }
        }
    }

    static class FitDoubleXYRoom implements MonumentPieces.MonumentRoomFitter {
        @Override
        public boolean fits(RoomGraph roomGraph) {
            if (roomGraph.hasOpening[Direction.EAST.get3DDataValue()] && !roomGraph.connections[Direction.EAST.get3DDataValue()].claimed && roomGraph.hasOpening[Direction.UP.get3DDataValue()] && !roomGraph.connections[Direction.UP.get3DDataValue()].claimed) {
                RoomGraph monumentPieces$roomdefinition = roomGraph.connections[Direction.EAST.get3DDataValue()];
                return monumentPieces$roomdefinition.hasOpening[Direction.UP.get3DDataValue()] && !monumentPieces$roomdefinition.connections[Direction.UP.get3DDataValue()].claimed;
            }
            else {
                return false;
            }
        }

        @Override
        public StructurePiece create(Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation rotation, RoomGraph roomGraph, RandomSource random, String type) {
            roomGraph.claimed = true;
            roomGraph.connections[Direction.EAST.get3DDataValue()].claimed = true;
            roomGraph.connections[Direction.UP.get3DDataValue()].claimed = true;
            roomGraph.connections[Direction.EAST.get3DDataValue()].connections[Direction.UP.get3DDataValue()].claimed = true;
            return getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/"+type+"/rooms/double_xy", getRoomPosition(roomGraph, 1), rotation, random, type);
        }

        @Override
        public void createOpenings(List<StructurePiece> childPieces, Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation none, RoomGraph roomGraph, RandomSource random, String type) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                if (direction != Direction.EAST) {
                    if (roomGraph.hasOpening[direction.get3DDataValue()]) {
                        Rotation rotation = getOpeningRotationFull(direction);
                        BlockPos pos = getRoomPosition(roomGraph, 2)
                                .offset(3, 1, 11)
                                .relative(direction, 4);
                        if (rotation == Rotation.CLOCKWISE_180) {
                            pos = pos.offset(1, 0, 0);
                        }
                        else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                            pos = pos.offset(1, 0, 1);
                        }
                        else if (rotation == Rotation.NONE) {
                            pos = pos.offset(0, 0, 1);
                        }
                        childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/wall_3", pos, rotation, random, type));
                    }

                    if (roomGraph.connections[Direction.UP.get3DDataValue()].hasOpening[direction.get3DDataValue()]) {
                        Rotation rotation = getOpeningRotationFull(direction);
                        BlockPos pos = getRoomPosition(roomGraph, 2)
                                .offset(3, 5, 11)
                                .relative(direction, 4);
                        if (rotation == Rotation.CLOCKWISE_180) {
                            pos = pos.offset(1, 0, 0);
                        }
                        else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                            pos = pos.offset(1, 0, 1);
                        }
                        else if (rotation == Rotation.NONE) {
                            pos = pos.offset(0, 0, 1);
                        }
                        childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/wall_3", pos, rotation, random, type));
                    }
                }
                if (direction != Direction.WEST) {
                    if (roomGraph.connections[Direction.EAST.get3DDataValue()].hasOpening[direction.get3DDataValue()]) {
                        Rotation rotation = getOpeningRotationFull(direction);
                        BlockPos pos = getRoomPosition(roomGraph, 2)
                                .offset(11, 1, 11)
                                .relative(direction, 4);
                        if (rotation == Rotation.CLOCKWISE_90) {
                            pos = pos.offset(0, 0, 0);
                        }
                        else if (rotation == Rotation.CLOCKWISE_180) {
                            pos = pos.offset(1, 0, 0);
                        }
                        else if (rotation == Rotation.NONE) {
                            pos = pos.offset(0, 0, 1);
                        }
                        childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/wall_3", pos, rotation, random, type));
                    }

                    if (roomGraph.connections[Direction.UP.get3DDataValue()].connections[Direction.EAST.get3DDataValue()].hasOpening[direction.get3DDataValue()]) {
                        Rotation rotation = getOpeningRotationFull(direction);
                        BlockPos pos = getRoomPosition(roomGraph, 2)
                                .offset(11, 5, 11)
                                .relative(direction, 4);
                        if (rotation == Rotation.CLOCKWISE_90) {
                            pos = pos.offset(0, 0, 0);
                        }
                        else if (rotation == Rotation.CLOCKWISE_180) {
                            pos = pos.offset(1, 0, 0);
                        }
                        else if (rotation == Rotation.NONE) {
                            pos = pos.offset(0, 0, 1);
                        }
                        childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/wall_3", pos, rotation, random, type));
                    }
                }
            }

            if (roomGraph.connections[Direction.UP.get3DDataValue()].hasOpening[Direction.UP.get3DDataValue()]) {
                BlockPos pos = getRoomPosition(roomGraph, 2).offset(2, 8, 10);
                childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/floor", pos, Rotation.NONE, random, type));
            }
            if (roomGraph.connections[Direction.UP.get3DDataValue()].connections[Direction.EAST.get3DDataValue()].hasOpening[Direction.UP.get3DDataValue()]) {
                BlockPos pos = getRoomPosition(roomGraph, 2).offset(10, 8, 10);
                childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/floor", pos, Rotation.NONE, random, type));
            }
        }
    }

    static class FitDoubleYRoom implements MonumentPieces.MonumentRoomFitter {
        @Override
        public boolean fits(RoomGraph roomGraph) {
            return roomGraph.hasOpening[Direction.UP.get3DDataValue()] && !roomGraph.connections[Direction.UP.get3DDataValue()].claimed;
        }

        @Override
        public StructurePiece create(Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation rotation, RoomGraph roomGraph, RandomSource random, String type) {
            roomGraph.claimed = true;
            roomGraph.connections[Direction.UP.get3DDataValue()].claimed = true;
            return getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/"+type+"/rooms/double_y", getRoomPosition(roomGraph, 1), rotation, random, type);
        }

        @Override
        public void createOpenings(List<StructurePiece> childPieces, Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation none, RoomGraph roomGraph, RandomSource random, String type) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                if (roomGraph.hasOpening[direction.get3DDataValue()]) {
                    Rotation rotation = getOpeningRotationFull(direction);
                    BlockPos pos = getRoomPosition(roomGraph, 1)
                            .offset(3, 1, 3)
                            .relative(direction, 4);
                    if (rotation == Rotation.CLOCKWISE_90) {
                        pos = pos.offset(0, 0, -2);
                    }
                    else if (rotation == Rotation.CLOCKWISE_180) {
                        pos = pos.offset(3, 0, 0);
                    }
                    else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                        pos = pos.offset(1, 0, 3);
                    }
                    else {
                        pos = pos.offset(-2, 0, 1);
                    }
                    childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/arch", pos, rotation, random, type));
                }

                if (roomGraph.connections[Direction.UP.get3DDataValue()].hasOpening[direction.get3DDataValue()]) {
                    Rotation rotation = getOpeningRotationFull(direction);
                    BlockPos pos = getRoomPosition(roomGraph, 1)
                            .offset(3, 5, 3)
                            .relative(direction, 4);
                    if (rotation == Rotation.CLOCKWISE_90) {
                        pos = pos.offset(0, 0, -2);
                    }
                    else if (rotation == Rotation.CLOCKWISE_180) {
                        pos = pos.offset(3, 0, 0);
                    }
                    else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                        pos = pos.offset(1, 0, 3);
                    }
                    else {
                        pos = pos.offset(-2, 0, 1);
                    }
                    childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/arch", pos, rotation, random, type));
                }
            }

            if (roomGraph.connections[Direction.UP.ordinal()].hasOpening[Direction.UP.get3DDataValue()]) {
                BlockPos pos = getRoomPosition(roomGraph, 1).offset(2, 8, 2);
                childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/floor", pos, Rotation.NONE, random, type));
            }
        }
    }

    static class FitDoubleYZRoom implements MonumentPieces.MonumentRoomFitter {
        @Override
        public boolean fits(RoomGraph roomGraph) {
            if (roomGraph.hasOpening[Direction.NORTH.get3DDataValue()] && !roomGraph.connections[Direction.NORTH.get3DDataValue()].claimed && roomGraph.hasOpening[Direction.UP.get3DDataValue()] && !roomGraph.connections[Direction.UP.get3DDataValue()].claimed) {
                RoomGraph monumentPieces$roomdefinition = roomGraph.connections[Direction.NORTH.get3DDataValue()];
                return monumentPieces$roomdefinition.hasOpening[Direction.UP.get3DDataValue()] && !monumentPieces$roomdefinition.connections[Direction.UP.get3DDataValue()].claimed;
            }
            else {
                return false;
            }
        }

        @Override
        public StructurePiece create(Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation rotation, RoomGraph roomGraph, RandomSource random, String type) {
            roomGraph.claimed = true;
            roomGraph.connections[Direction.NORTH.get3DDataValue()].claimed = true;
            roomGraph.connections[Direction.UP.get3DDataValue()].claimed = true;
            roomGraph.connections[Direction.NORTH.get3DDataValue()].connections[Direction.UP.get3DDataValue()].claimed = true;
            return getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/"+type+"/rooms/double_yz", getRoomPosition(roomGraph, 2), rotation, random, type);
        }

        @Override
        public void createOpenings(List<StructurePiece> childPieces, Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation none, RoomGraph roomGraph, RandomSource random, String type) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                if (direction != Direction.NORTH) {
                    if (roomGraph.hasOpening[direction.get3DDataValue()]) {
                        Rotation rotation = getOpeningRotationFull(direction);
                        BlockPos pos = getRoomPosition(roomGraph, 2)
                                .offset(4, 1, 12)
                                .relative(direction, 4);
                        if (rotation == Rotation.CLOCKWISE_90) {
                            pos = pos.offset(-1, 0, -1);
                        }
                        else if (rotation == Rotation.CLOCKWISE_180) {
                            pos = pos.offset(0, 0, -1);
                        }
                        childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/wall_3", pos, rotation, random, type));
                    }

                    if (roomGraph.connections[Direction.UP.get3DDataValue()].hasOpening[direction.get3DDataValue()]) {
                        Rotation rotation = getOpeningRotationFull(direction);
                        BlockPos pos = getRoomPosition(roomGraph, 2)
                                .offset(4, 1, 11)
                                .relative(direction, 5);
                        if (rotation == Rotation.CLOCKWISE_90) {
                            pos = pos.offset(-2, 0, -1);
                        }
                        else if (rotation == Rotation.CLOCKWISE_180) {
                            pos = pos.offset(1, 0, -1);
                        }
                        else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                            pos = pos.offset(1, 0, 2);
                        }
                        childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/wall_shelf", pos, rotation, random, type));
                    }
                }
                if (direction != Direction.SOUTH) {
                    if (roomGraph.connections[Direction.NORTH.get3DDataValue()].hasOpening[direction.get3DDataValue()]) {
                        Rotation rotation = getOpeningRotationFull(direction);
                        BlockPos pos = getRoomPosition(roomGraph, 2)
                                .offset(3, 1, 4)
                                .relative(direction, 4);
                        if (rotation == Rotation.CLOCKWISE_90) {
                            pos = pos.offset(0, 0, -1);
                        }
                        else if (rotation == Rotation.CLOCKWISE_180) {
                            pos = pos.offset(1, 0, -1);
                        }
                        else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                            pos = pos.offset(1, 0, 0);
                        }
                        childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/wall_3", pos, rotation, random, type));
                    }

                    if (roomGraph.connections[Direction.UP.get3DDataValue()].connections[Direction.NORTH.get3DDataValue()].hasOpening[direction.get3DDataValue()]) {
                        Rotation rotation = getOpeningRotationFull(direction);
                        BlockPos pos = getRoomPosition(roomGraph, 2)
                                .offset(2, 1, 5)
                                .relative(direction, 5);
                        if (rotation == Rotation.CLOCKWISE_90) {
                            pos = pos.offset(0, 0, -3);
                        }
                        else if (rotation == Rotation.CLOCKWISE_180) {
                            pos = pos.offset(2, 0, -2);
                        }
                        else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                            pos = pos.offset(3, 0, 0);
                        }
                        childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/wall_shelf", pos, rotation, random, type));
                    }
                }
            }

            if (roomGraph.connections[Direction.UP.get3DDataValue()].hasOpening[Direction.UP.get3DDataValue()]) {
                BlockPos pos = getRoomPosition(roomGraph, 2).offset(2, 8, 10);
                childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/floor", pos, Rotation.NONE, random, type));
            }
            if (roomGraph.connections[Direction.UP.get3DDataValue()].connections[Direction.NORTH.get3DDataValue()].hasOpening[Direction.UP.get3DDataValue()]) {
                BlockPos pos = getRoomPosition(roomGraph, 2).offset(2, 8, 2);
                childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/floor", pos, Rotation.NONE, random, type));
            }
        }
    }

    static class FitDoubleZRoom implements MonumentPieces.MonumentRoomFitter {
        @Override
        public boolean fits(RoomGraph roomGraph) {
            return roomGraph.hasOpening[Direction.NORTH.get3DDataValue()] && !roomGraph.connections[Direction.NORTH.get3DDataValue()].claimed;
        }

        @Override
        public StructurePiece create(Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation rotation, RoomGraph roomGraph, RandomSource random, String type) {
            RoomGraph roomGraph1 = roomGraph;
            if (!roomGraph.hasOpening[Direction.NORTH.get3DDataValue()] || roomGraph.connections[Direction.NORTH.get3DDataValue()].claimed) {
                roomGraph1 = roomGraph.connections[Direction.SOUTH.get3DDataValue()];
            }

            roomGraph1.claimed = true;
            roomGraph1.connections[Direction.NORTH.get3DDataValue()].claimed = true;
            return getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/"+type+"/rooms/double_z", getRoomPosition(roomGraph, 2), rotation, random, type);
        }

        @Override
        public void createOpenings(List<StructurePiece> childPieces, Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation none, RoomGraph roomGraph, RandomSource random, String type) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                if (direction != Direction.NORTH) {
                    if (roomGraph.hasOpening[direction.get3DDataValue()]) {
                        Rotation rotation = getOpeningRotationFull(direction);
                        BlockPos pos = getRoomPosition(roomGraph, 2)
                                .offset(4, 1, 12)
                                .relative(direction, 4);
                        if (rotation == Rotation.CLOCKWISE_90) {
                            pos = pos.offset(-1, 0, -1);
                        }
                        else if (rotation == Rotation.CLOCKWISE_180) {
                            pos = pos.offset(0, 0, -1);
                        }
                        childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/wall_3", pos, rotation, random, type));
                    }
                }
                if (direction != Direction.SOUTH) {
                    if (roomGraph.connections[Direction.NORTH.get3DDataValue()].hasOpening[direction.get3DDataValue()]) {
                        Rotation rotation = getOpeningRotationFull(direction);
                        BlockPos pos = getRoomPosition(roomGraph, 2)
                                .offset(3, 1, 4)
                                .relative(direction, 4);
                        if (rotation == Rotation.CLOCKWISE_90) {
                            pos = pos.offset(0, 0, -1);
                        }
                        else if (rotation == Rotation.CLOCKWISE_180) {
                            pos = pos.offset(1, 0, -1);
                        }
                        else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                            pos = pos.offset(1, 0, 0);
                        }
                        childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/wall_3", pos, rotation, random, type));
                    }
                }
            }

            if (roomGraph.hasOpening[Direction.UP.get3DDataValue()]) {
                BlockPos pos = getRoomPosition(roomGraph, 2).offset(2, 4, 2);
                childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/floor", pos, Rotation.NONE, random, type));
            }
            if (roomGraph.connections[Direction.NORTH.get3DDataValue()].hasOpening[Direction.UP.get3DDataValue()]) {
                BlockPos pos = getRoomPosition(roomGraph, 2).offset(2, 4, 10);
                childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/floor", pos, Rotation.NONE, random, type));
            }
        }
    }

    static class FitSimpleRoom implements MonumentPieces.MonumentRoomFitter {
        @Override
        public boolean fits(RoomGraph roomGraph) {
            return true;
        }

        @Override
        public StructurePiece create(Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation rotation, RoomGraph roomGraph, RandomSource random, String type) {
            roomGraph.claimed = true;
            return getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/"+type+"/rooms/simple", getRoomPosition(roomGraph, 1), rotation, random, type);
        }

        @Override
        public void createOpenings(List<StructurePiece> childPieces, Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation none, RoomGraph roomGraph, RandomSource random, String type) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                if (roomGraph.hasOpening[direction.get3DDataValue()]) {
                    Rotation rotation = getOpeningRotationFull(direction);
                    BlockPos pos = getRoomPosition(roomGraph, 1)
                            .offset(3, 1, 3)
                            .relative(direction, 3);
                    if (rotation == Rotation.CLOCKWISE_90) {
                        pos = pos.offset(1, 0, 0);
                    }
                    else if (rotation == Rotation.CLOCKWISE_180) {
                        pos = pos.offset(1, 0, 1);
                    }
                    else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                        pos = pos.offset(0, 0, 1);
                    }
                    childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/wall_1", pos, rotation, random, type));
                }
            }

            if (roomGraph.hasOpening[Direction.UP.get3DDataValue()]) {
                BlockPos pos = getRoomPosition(roomGraph, 1).offset(2, 4, 2);
                childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/floor", pos, Rotation.NONE, random, type));
            }
        }
    }

    static class FitSimplePillarRoom implements MonumentPieces.MonumentRoomFitter {
        private final RandomSource random;

        FitSimplePillarRoom(RandomSource random) {
            this.random = random;
        }

        @Override
        public boolean fits(RoomGraph roomGraph) {
            return random.nextBoolean() && !roomGraph.hasOpening[Direction.DOWN.get3DDataValue()] && !roomGraph.hasOpening[Direction.UP.get3DDataValue()];
        }

        @Override
        public StructurePiece create(Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation rotation, RoomGraph roomGraph, RandomSource random, String type) {
            roomGraph.claimed = true;
            return getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/"+type+"/rooms/simple_pillar", getRoomPosition(roomGraph, 1), rotation, random, type);
        }

        @Override
        public void createOpenings(List<StructurePiece> childPieces, Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation none, RoomGraph roomGraph, RandomSource random, String type) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                if (roomGraph.hasOpening[direction.get3DDataValue()]) {
                    Rotation rotation = getOpeningRotationFull(direction);
                    BlockPos pos = getRoomPosition(roomGraph, 1)
                            .offset(3, 1, 3)
                            .relative(direction, 3);
                    if (rotation == Rotation.CLOCKWISE_90) {
                        pos = pos.offset(1, 0, 0);
                    }
                    else if (rotation == Rotation.CLOCKWISE_180) {
                        pos = pos.offset(1, 0, 1);
                    }
                    else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                        pos = pos.offset(0, 0, 1);
                    }
                    childPieces.add(getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/" + type + "/openings/wall_1", pos, rotation, random, type));
                }
            }
        }
    }

    static class FitSimpleTopRoom implements MonumentPieces.MonumentRoomFitter {
        @Override
        public boolean fits(RoomGraph roomGraph) {
            return !roomGraph.hasOpening[Direction.WEST.get3DDataValue()] && !roomGraph.hasOpening[Direction.EAST.get3DDataValue()] && !roomGraph.hasOpening[Direction.NORTH.get3DDataValue()] && !roomGraph.hasOpening[Direction.SOUTH.get3DDataValue()] && !roomGraph.hasOpening[Direction.UP.get3DDataValue()];
        }

        @Override
        public StructurePiece create(Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation rotation, RoomGraph roomGraph, RandomSource random, String type) {
            roomGraph.claimed = true;
            return getJigsawPiece(poolRegistry, StructureTemplateManager, RepurposedStructures.MODID + ":monuments/"+type+"/rooms/simple_top", getRoomPosition(roomGraph, 1), rotation, random, type);
        }

        @Override
        public void createOpenings(List<StructurePiece> childPieces, Registry<StructureTemplatePool> poolRegistry, StructureTemplateManager StructureTemplateManager, Rotation none, RoomGraph roomGraph, RandomSource random, String type) {}
    }

    protected abstract static class MonumentPiece {
        protected static final int GRIDROOM_SOURCE_INDEX = getRoomIndex(2, 0, 0);
        protected static final int GRIDROOM_TOP_CONNECT_INDEX = getRoomIndex(2, 2, 0);
        protected static final int GRIDROOM_LEFTWING_CONNECT_INDEX = getRoomIndex(0, 1, 0);
        protected static final int GRIDROOM_RIGHTWING_CONNECT_INDEX = getRoomIndex(4, 1, 0);
        protected RoomGraph roomGraph;

        protected static int getRoomIndex(int x, int y, int z) {
            return y * 25 + z * 5 + x;
        }

        protected MonumentPiece(RoomGraph roomGraph) {
            this.roomGraph = roomGraph;
        }
    }

    static class RoomGraph {
        final int index;
        final RoomGraph[] connections = new RoomGraph[6];
        final boolean[] hasOpening = new boolean[6];
        boolean claimed;
        boolean isSource;
        private int scanIndex;

        public RoomGraph(int index) {
            this.index = index;
        }

        public void setConnection(Direction direction, RoomGraph roomGraph) {
            this.connections[direction.get3DDataValue()] = roomGraph;
            roomGraph.connections[direction.getOpposite().get3DDataValue()] = this;
        }

        public void updateOpenings() {
            for(int i = 0; i < 6; ++i) {
                this.hasOpening[i] = this.connections[i] != null;
            }
        }

        public boolean findSource(int index) {
            if (this.isSource) {
                return true;
            }
            else {
                this.scanIndex = index;

                for(int i = 0; i < 6; ++i) {
                    if (this.connections[i] != null && this.hasOpening[i] && this.connections[i].scanIndex != index && this.connections[i].findSource(index)) {
                        return true;
                    }
                }

                return false;
            }
        }

        public boolean isSpecial() {
            return this.index >= 75;
        }
    }
}