package com.telepathicgrunt.repurposedstructures.world.features.structures;

import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;


public class StructurePieces {
    public static StructurePieceType MINESHAFT_ROOM_RS = RSMineshaftPieces.Room::new;
    public static StructurePieceType MINESHAFT_CORRIDOR_RS = RSMineshaftPieces.Corridor::new;
    public static StructurePieceType MINESHAFT_CROSSING_RS = RSMineshaftPieces.Cross::new;
    public static StructurePieceType MINESHAFT_STAIRS_RS = RSMineshaftPieces.Stairs::new;
    public static StructurePieceType JUNGLE_FORTRESS_START = JungleFortressPieces.Start::new;
    public static StructurePieceType JUNGLE_FORTRESS_END = JungleFortressPieces.End::new;
    public static StructurePieceType JUNGLE_FORTRESS_ENTRANCE = JungleFortressPieces.Entrance::new;
    public static StructurePieceType JUNGLE_FORTRESS_STRAIGHT = JungleFortressPieces.Straight::new;
    public static StructurePieceType JUNGLE_FORTRESS_CORRIDOR_5 = JungleFortressPieces.Corridor5::new;
    public static StructurePieceType JUNGLE_FORTRESS_CORRIDOR_4 = JungleFortressPieces.Corridor4::new;
    public static StructurePieceType JUNGLE_FORTRESS_CORRIDOR_3 = JungleFortressPieces.Corridor3::new;
    public static StructurePieceType JUNGLE_FORTRESS_CORRIDOR_2 = JungleFortressPieces.Corridor2::new;
    public static StructurePieceType JUNGLE_FORTRESS_CORRIDOR_1 = JungleFortressPieces.Corridor::new;
    public static StructurePieceType JUNGLE_FORTRESS_CROSSING_3 = JungleFortressPieces.Crossing3::new;
    public static StructurePieceType JUNGLE_FORTRESS_CROSSING_2 = JungleFortressPieces.Crossing2::new;
    public static StructurePieceType JUNGLE_FORTRESS_CROSSING_1 = JungleFortressPieces.Crossing::new;
    public static StructurePieceType JUNGLE_FORTRESS_MUSHROOM_ROOM = JungleFortressPieces.MushroomRoom::new;
    public static StructurePieceType JUNGLE_FORTRESS_THRONE = JungleFortressPieces.Throne::new;
    public static StructurePieceType JUNGLE_FORTRESS_STAIRS = JungleFortressPieces.Stairs::new;
    public static StructurePieceType STRONGHOLD_CHEST_CORRIDOR = RSStrongholdPieces.ChestCorridor::new;
    public static StructurePieceType STRONGHOLD_CORRIDOR = RSStrongholdPieces.Corridor::new;
    public static StructurePieceType STRONGHOLD_PORTAL_ROOM = RSStrongholdPieces.PortalRoom::new;
    public static StructurePieceType STRONGHOLD_LIBRARY = RSStrongholdPieces.Library::new;
    public static StructurePieceType STRONGHOLD_PRISON = RSStrongholdPieces.Prison::new;
    public static StructurePieceType STRONGHOLD_ROOM_CROSSING = RSStrongholdPieces.RoomCrossing::new;
    public static StructurePieceType STRONGHOLD_CROSSING = RSStrongholdPieces.Crossing::new;
    public static StructurePieceType STRONGHOLD_LEFT_TURN = RSStrongholdPieces.LeftTurn::new;
    public static StructurePieceType STRONGHOLD_RIGHT_TURN = RSStrongholdPieces.RightTurn::new;
    public static StructurePieceType STRONGHOLD_STRAIGHT = RSStrongholdPieces.Straight::new;
    public static StructurePieceType STRONGHOLD_STAIRS = RSStrongholdPieces.Stairs::new;
    public static StructurePieceType STRONGHOLD_STAIRS_STRAIGHT = RSStrongholdPieces.StairsStraight::new;
    public static StructurePieceType STRONGHOLD_ENTRANCE_STAIRS = RSStrongholdPieces.EntranceStairs::new;
    public static StructurePieceType NETHER_TEMPLE_PIECE = NetherTemplePiece::new;
    public static StructurePieceType BADLANDS_TEMPLE_PIECE = BadlandsTemplePiece::new;
    public static StructurePieceType RS_IGLOO_PIECE = RSIglooPieces.Piece::new;

    public static void registerStructurePieces() {
        Registry.register(Registry.STRUCTURE_PIECE, "mineshaft_room_rs", MINESHAFT_ROOM_RS);
        Registry.register(Registry.STRUCTURE_PIECE, "mineshaft_corridor_rs", MINESHAFT_CORRIDOR_RS);
        Registry.register(Registry.STRUCTURE_PIECE, "mineshaft_crossing_rs", MINESHAFT_CROSSING_RS);
        Registry.register(Registry.STRUCTURE_PIECE, "mineshaft_stairs_rs", MINESHAFT_STAIRS_RS);
        Registry.register(Registry.STRUCTURE_PIECE, "jungle_fortress_start", JUNGLE_FORTRESS_START);
        Registry.register(Registry.STRUCTURE_PIECE, "jungle_fortress_entrance", JUNGLE_FORTRESS_ENTRANCE);
        Registry.register(Registry.STRUCTURE_PIECE, "jungle_fortress_end", JUNGLE_FORTRESS_END);
        Registry.register(Registry.STRUCTURE_PIECE, "jungle_fortress_straight", JUNGLE_FORTRESS_STRAIGHT);
        Registry.register(Registry.STRUCTURE_PIECE, "jungle_fortress_corridor_5", JUNGLE_FORTRESS_CORRIDOR_5);
        Registry.register(Registry.STRUCTURE_PIECE, "jungle_fortress_corridor_4", JUNGLE_FORTRESS_CORRIDOR_4);
        Registry.register(Registry.STRUCTURE_PIECE, "jungle_fortress_corridor_3", JUNGLE_FORTRESS_CORRIDOR_3);
        Registry.register(Registry.STRUCTURE_PIECE, "jungle_fortress_corridor_2", JUNGLE_FORTRESS_CORRIDOR_2);
        Registry.register(Registry.STRUCTURE_PIECE, "jungle_fortress_corridor_1", JUNGLE_FORTRESS_CORRIDOR_1);
        Registry.register(Registry.STRUCTURE_PIECE, "jungle_fortress_crossing_3", JUNGLE_FORTRESS_CROSSING_3);
        Registry.register(Registry.STRUCTURE_PIECE, "jungle_fortress_crossing_2", JUNGLE_FORTRESS_CROSSING_2);
        Registry.register(Registry.STRUCTURE_PIECE, "jungle_fortress_crossing_1", JUNGLE_FORTRESS_CROSSING_1);
        Registry.register(Registry.STRUCTURE_PIECE, "jungle_fortress_mushroom_room", JUNGLE_FORTRESS_MUSHROOM_ROOM);
        Registry.register(Registry.STRUCTURE_PIECE, "jungle_fortress_throne", JUNGLE_FORTRESS_THRONE);
        Registry.register(Registry.STRUCTURE_PIECE, "jungle_fortress_stairs", JUNGLE_FORTRESS_STAIRS);
        Registry.register(Registry.STRUCTURE_PIECE, "stronghold_chest_corridor", STRONGHOLD_CHEST_CORRIDOR);
        Registry.register(Registry.STRUCTURE_PIECE, "stronghold_corridor", STRONGHOLD_CORRIDOR);
        Registry.register(Registry.STRUCTURE_PIECE, "stronghold_portal_room", STRONGHOLD_PORTAL_ROOM);
        Registry.register(Registry.STRUCTURE_PIECE, "stronghold_library", STRONGHOLD_LIBRARY);
        Registry.register(Registry.STRUCTURE_PIECE, "stronghold_prison", STRONGHOLD_PRISON);
        Registry.register(Registry.STRUCTURE_PIECE, "stronghold_room_crossing", STRONGHOLD_ROOM_CROSSING);
        Registry.register(Registry.STRUCTURE_PIECE, "stronghold_crossing", STRONGHOLD_CROSSING);
        Registry.register(Registry.STRUCTURE_PIECE, "stronghold_right_turn", STRONGHOLD_RIGHT_TURN);
        Registry.register(Registry.STRUCTURE_PIECE, "stronghold_left_turn", STRONGHOLD_LEFT_TURN);
        Registry.register(Registry.STRUCTURE_PIECE, "stronghold_straight", STRONGHOLD_STRAIGHT);
        Registry.register(Registry.STRUCTURE_PIECE, "stronghold_stairs", STRONGHOLD_STAIRS);
        Registry.register(Registry.STRUCTURE_PIECE, "stronghold_stairs_straight", STRONGHOLD_STAIRS_STRAIGHT);
        Registry.register(Registry.STRUCTURE_PIECE, "stronghold_entrance_stairs", STRONGHOLD_ENTRANCE_STAIRS);
        Registry.register(Registry.STRUCTURE_PIECE, "nether_temple_piece", NETHER_TEMPLE_PIECE);
        Registry.register(Registry.STRUCTURE_PIECE, "badlands_temple_piece", BADLANDS_TEMPLE_PIECE);
        Registry.register(Registry.STRUCTURE_PIECE, "rs_igloo_piece", RS_IGLOO_PIECE);
    }

}
