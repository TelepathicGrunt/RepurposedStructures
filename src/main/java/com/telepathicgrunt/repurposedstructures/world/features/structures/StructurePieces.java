package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.telepathicgrunt.repurposedstructures.utils.RegUtil;

import net.minecraft.world.gen.feature.structure.IStructurePieceType;


public class StructurePieces
{
    public static IStructurePieceType MINESHAFT_ROOM_RS = RSMineshaftPieces.Room::new;
    public static IStructurePieceType MINESHAFT_CORRIDOR_RS = RSMineshaftPieces.Corridor::new;
    public static IStructurePieceType MINESHAFT_CROSSING_RS = RSMineshaftPieces.Cross::new;
    public static IStructurePieceType MINESHAFT_STAIRS_RS = RSMineshaftPieces.Stairs::new;
    public static IStructurePieceType JUNGLE_FORTRESS_START = JungleFortressPieces.Start::new;
    public static IStructurePieceType JUNGLE_FORTRESS_END = JungleFortressPieces.End::new;
    public static IStructurePieceType JUNGLE_FORTRESS_ENTRANCE = JungleFortressPieces.Entrance::new;
    public static IStructurePieceType JUNGLE_FORTRESS_STRAIGHT = JungleFortressPieces.Straight::new;
    public static IStructurePieceType JUNGLE_FORTRESS_CORRIDOR_5 = JungleFortressPieces.Corridor5::new;
    public static IStructurePieceType JUNGLE_FORTRESS_CORRIDOR_4 = JungleFortressPieces.Corridor4::new;
    public static IStructurePieceType JUNGLE_FORTRESS_CORRIDOR_3 = JungleFortressPieces.Corridor3::new;
    public static IStructurePieceType JUNGLE_FORTRESS_CORRIDOR_2 = JungleFortressPieces.Corridor2::new;
    public static IStructurePieceType JUNGLE_FORTRESS_CORRIDOR_1 = JungleFortressPieces.Corridor::new;
    public static IStructurePieceType JUNGLE_FORTRESS_CROSSING_3 = JungleFortressPieces.Crossing3::new;
    public static IStructurePieceType JUNGLE_FORTRESS_CROSSING_2 = JungleFortressPieces.Crossing2::new;
    public static IStructurePieceType JUNGLE_FORTRESS_CROSSING_1 = JungleFortressPieces.Crossing::new;
    public static IStructurePieceType JUNGLE_FORTRESS_MUSHROOM_ROOM = JungleFortressPieces.MushroomRoom::new;
    public static IStructurePieceType JUNGLE_FORTRESS_THRONE = JungleFortressPieces.Throne::new;
    public static IStructurePieceType JUNGLE_FORTRESS_STAIRS = JungleFortressPieces.Stairs::new;
    public static IStructurePieceType STRONGHOLD_CHEST_CORRIDOR = RSStrongholdPieces.ChestCorridor::new;
    public static IStructurePieceType STRONGHOLD_CORRIDOR = RSStrongholdPieces.Corridor::new;
    public static IStructurePieceType STRONGHOLD_PORTAL_ROOM = RSStrongholdPieces.PortalRoom::new;
    public static IStructurePieceType STRONGHOLD_LIBRARY = RSStrongholdPieces.Library::new;
    public static IStructurePieceType STRONGHOLD_PRISON = RSStrongholdPieces.Prison::new;
    public static IStructurePieceType STRONGHOLD_ROOM_CROSSING = RSStrongholdPieces.RoomCrossing::new;
    public static IStructurePieceType STRONGHOLD_CROSSING = RSStrongholdPieces.Crossing::new;
    public static IStructurePieceType STRONGHOLD_LEFT_TURN = RSStrongholdPieces.LeftTurn::new;
    public static IStructurePieceType STRONGHOLD_RIGHT_TURN = RSStrongholdPieces.RightTurn::new;
    public static IStructurePieceType STRONGHOLD_STRAIGHT = RSStrongholdPieces.Straight::new;
    public static IStructurePieceType STRONGHOLD_STAIRS = RSStrongholdPieces.Stairs::new;
    public static IStructurePieceType STRONGHOLD_STAIRS_STRAIGHT = RSStrongholdPieces.StairsStraight::new;
    public static IStructurePieceType STRONGHOLD_ENTRANCE_STAIRS = RSStrongholdPieces.EntranceStairs::new;
    public static IStructurePieceType NETHER_TEMPLE_PIECE = NetherTemplePiece::new;
    public static IStructurePieceType BADLANDS_TEMPLE_PIECE = BadlandsTemplePiece::new;
    public static IStructurePieceType RS_IGLOO_PIECE = RSIglooPieces.Piece::new;

    public static void registerStructurePieces() {
	RegUtil.register(MINESHAFT_ROOM_RS, "mineshaft_room_rs");
	RegUtil.register(MINESHAFT_CORRIDOR_RS, "mineshaft_corridor_rs");
	RegUtil.register(MINESHAFT_CROSSING_RS, "mineshaft_crossing_rs");
	RegUtil.register(MINESHAFT_STAIRS_RS, "mineshaft_stairs_rs");
	RegUtil.register(JUNGLE_FORTRESS_START, "jungle_fortress_start");
	RegUtil.register(JUNGLE_FORTRESS_ENTRANCE, "jungle_fortress_entrance");
	RegUtil.register(JUNGLE_FORTRESS_END, "jungle_fortress_end");
	RegUtil.register(JUNGLE_FORTRESS_STRAIGHT, "jungle_fortress_straight");
	RegUtil.register(JUNGLE_FORTRESS_CORRIDOR_5, "jungle_fortress_corridor_5");
	RegUtil.register(JUNGLE_FORTRESS_CORRIDOR_4, "jungle_fortress_corridor_4");
	RegUtil.register(JUNGLE_FORTRESS_CORRIDOR_3, "jungle_fortress_corridor_3");
	RegUtil.register(JUNGLE_FORTRESS_CORRIDOR_2, "jungle_fortress_corridor_2");
	RegUtil.register(JUNGLE_FORTRESS_CORRIDOR_1, "jungle_fortress_corridor_1");
	RegUtil.register(JUNGLE_FORTRESS_CROSSING_3, "jungle_fortress_crossing_3");
	RegUtil.register(JUNGLE_FORTRESS_CROSSING_2, "jungle_fortress_crossing_2");
	RegUtil.register(JUNGLE_FORTRESS_CROSSING_1, "jungle_fortress_crossing_1");
	RegUtil.register(JUNGLE_FORTRESS_MUSHROOM_ROOM, "jungle_fortress_mushroom_room");
	RegUtil.register(JUNGLE_FORTRESS_THRONE, "jungle_fortress_throne");
	RegUtil.register(JUNGLE_FORTRESS_STAIRS, "jungle_fortress_stairs");
	RegUtil.register(STRONGHOLD_CHEST_CORRIDOR, "stronghold_chest_corridor");
	RegUtil.register(STRONGHOLD_CORRIDOR, "stronghold_corridor");
	RegUtil.register(STRONGHOLD_PORTAL_ROOM, "stronghold_portal_room");
	RegUtil.register(STRONGHOLD_LIBRARY, "stronghold_library");
	RegUtil.register(STRONGHOLD_PRISON, "stronghold_prison");
	RegUtil.register(STRONGHOLD_ROOM_CROSSING, "stronghold_room_crossing");
	RegUtil.register(STRONGHOLD_CROSSING, "stronghold_crossing");
	RegUtil.register(STRONGHOLD_RIGHT_TURN, "stronghold_right_turn");
	RegUtil.register(STRONGHOLD_LEFT_TURN, "stronghold_left_turn");
	RegUtil.register(STRONGHOLD_STRAIGHT, "stronghold_straight");
	RegUtil.register(STRONGHOLD_STAIRS, "stronghold_stairs");
	RegUtil.register(STRONGHOLD_STAIRS_STRAIGHT, "stronghold_stairs_straight");
	RegUtil.register(STRONGHOLD_ENTRANCE_STAIRS, "stronghold_entrance_stairs");
	RegUtil.register(NETHER_TEMPLE_PIECE, "nether_temple_piece");
	RegUtil.register(BADLANDS_TEMPLE_PIECE, "badlands_temple_piece");
	RegUtil.register(RS_IGLOO_PIECE, "rs_igloo_piece");
    }

}
