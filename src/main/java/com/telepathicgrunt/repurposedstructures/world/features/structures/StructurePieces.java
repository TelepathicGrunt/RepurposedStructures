package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.telepathicgrunt.repurposedstructures.utils.RegUtil;

import net.minecraft.world.gen.feature.structure.IStructurePieceType;


public class StructurePieces
{
	public static IStructurePieceType	MINESHAFT_ROOM_RS				= RSMineshaftPieces.Room::new;
	public static IStructurePieceType	MINESHAFT_CORRIDOR_RS			= RSMineshaftPieces.Corridor::new;
	public static IStructurePieceType	MINESHAFT_CROSSING_RS			= RSMineshaftPieces.Cross::new;
	public static IStructurePieceType	MINESHAFT_STAIRS_RS				= RSMineshaftPieces.Stairs::new;
	public static IStructurePieceType	JUNGLE_FORTRESS_START			= JungleFortressPieces.Start::new;
	public static IStructurePieceType	JUNGLE_FORTRESS_END				= JungleFortressPieces.End::new;
	public static IStructurePieceType	JUNGLE_FORTRESS_ENTRANCE		= JungleFortressPieces.Entrance::new;
	public static IStructurePieceType	JUNGLE_FORTRESS_STRAIGHT		= JungleFortressPieces.Straight::new;
	public static IStructurePieceType	JUNGLE_FORTRESS_CORRIDOR_5		= JungleFortressPieces.Corridor5::new;
	public static IStructurePieceType	JUNGLE_FORTRESS_CORRIDOR_4		= JungleFortressPieces.Corridor4::new;
	public static IStructurePieceType	JUNGLE_FORTRESS_CORRIDOR_3		= JungleFortressPieces.Corridor3::new;
	public static IStructurePieceType	JUNGLE_FORTRESS_CORRIDOR_2		= JungleFortressPieces.Corridor2::new;
	public static IStructurePieceType	JUNGLE_FORTRESS_CORRIDOR_1		= JungleFortressPieces.Corridor::new;
	public static IStructurePieceType	JUNGLE_FORTRESS_CROSSING_3		= JungleFortressPieces.Crossing3::new;
	public static IStructurePieceType	JUNGLE_FORTRESS_CROSSING_2		= JungleFortressPieces.Crossing2::new;
	public static IStructurePieceType	JUNGLE_FORTRESS_CROSSING_1		= JungleFortressPieces.Crossing::new;
	public static IStructurePieceType	JUNGLE_FORTRESS_MUSHROOM_ROOM	= JungleFortressPieces.MushroomRoom::new;
	public static IStructurePieceType	JUNGLE_FORTRESS_THRONE			= JungleFortressPieces.Throne::new;
	public static IStructurePieceType	JUNGLE_FORTRESS_STAIRS			= JungleFortressPieces.Stairs::new;
	public static IStructurePieceType	STRONGHOLD_CHEST_CORRIDOR		= RSStrongholdPieces.ChestCorridor::new;
	public static IStructurePieceType	STRONGHOLD_CORRIDOR				= RSStrongholdPieces.Corridor::new;
	public static IStructurePieceType	STRONGHOLD_PORTAL_ROOM			= RSStrongholdPieces.PortalRoom::new;
	public static IStructurePieceType	STRONGHOLD_LIBRARY				= RSStrongholdPieces.Library::new;
	public static IStructurePieceType	STRONGHOLD_PRISON				= RSStrongholdPieces.Prison::new;
	public static IStructurePieceType	STRONGHOLD_ROOM_CROSSING		= RSStrongholdPieces.RoomCrossing::new;
	public static IStructurePieceType	STRONGHOLD_CROSSING				= RSStrongholdPieces.Crossing::new;
	public static IStructurePieceType	STRONGHOLD_LEFT_TURN			= RSStrongholdPieces.LeftTurn::new;
	public static IStructurePieceType	STRONGHOLD_RIGHT_TURN			= RSStrongholdPieces.RightTurn::new;
	public static IStructurePieceType	STRONGHOLD_STRAIGHT				= RSStrongholdPieces.Straight::new;
	public static IStructurePieceType	STRONGHOLD_STAIRS				= RSStrongholdPieces.Stairs::new;
	public static IStructurePieceType	STRONGHOLD_STAIRS_STRAIGHT		= RSStrongholdPieces.StairsStraight::new;
	public static IStructurePieceType	STRONGHOLD_ENTRANCE_STAIRS		= RSStrongholdPieces.EntranceStairs::new;
	public static IStructurePieceType 	NETHER_TEMPLE_PIECE				= NetherTemplePiece::new;
	public static IStructurePieceType 	BADLANDS_TEMPLE_PIECE			= BadlandsTemplePiece::new;


	public static void registerStructurePieces()
	{
		RegUtil.register(MINESHAFT_ROOM_RS,				"MINESHAFT_ROOM_RS");
		RegUtil.register(MINESHAFT_CORRIDOR_RS,			"MINESHAFT_CORRIDOR_RS");
		RegUtil.register(MINESHAFT_CROSSING_RS,			"MINESHAFT_CROSSING_RS");
		RegUtil.register(MINESHAFT_STAIRS_RS,			"MINESHAFT_STAIRS_RS");
		RegUtil.register(JUNGLE_FORTRESS_START,			"JUNGLE_FORTRESS_START");
		RegUtil.register(JUNGLE_FORTRESS_ENTRANCE, 		"JUNGLE_FORTRESS_ENTRANCE");
		RegUtil.register(JUNGLE_FORTRESS_END,			"JUNGLE_FORTRESS_END");
		RegUtil.register(JUNGLE_FORTRESS_STRAIGHT,		"JUNGLE_FORTRESS_STRAIGHT");
		RegUtil.register(JUNGLE_FORTRESS_CORRIDOR_5,	"JUNGLE_FORTRESS_CORRIDOR_5");
		RegUtil.register(JUNGLE_FORTRESS_CORRIDOR_4, 	"JUNGLE_FORTRESS_CORRIDOR_4");
		RegUtil.register(JUNGLE_FORTRESS_CORRIDOR_3, 	"JUNGLE_FORTRESS_CORRIDOR_3");
		RegUtil.register(JUNGLE_FORTRESS_CORRIDOR_2, 	"JUNGLE_FORTRESS_CORRIDOR_2");
		RegUtil.register(JUNGLE_FORTRESS_CORRIDOR_1, 	"JUNGLE_FORTRESS_CORRIDOR_1");
		RegUtil.register(JUNGLE_FORTRESS_CROSSING_3,	"JUNGLE_FORTRESS_CROSSING_3");
		RegUtil.register(JUNGLE_FORTRESS_CROSSING_2, 	"JUNGLE_FORTRESS_CROSSING_2");
		RegUtil.register(JUNGLE_FORTRESS_CROSSING_1,	"JUNGLE_FORTRESS_CROSSING_1");
		RegUtil.register(JUNGLE_FORTRESS_MUSHROOM_ROOM,	"JUNGLE_FORTRESS_MUSHROOM_ROOM");
		RegUtil.register(JUNGLE_FORTRESS_THRONE,		"JUNGLE_FORTRESS_THRONE");
		RegUtil.register(JUNGLE_FORTRESS_STAIRS,		"JUNGLE_FORTRESS_STAIRS");
		RegUtil.register(STRONGHOLD_CHEST_CORRIDOR,		"STRONGHOLD_CHEST_CORRIDOR");
		RegUtil.register(STRONGHOLD_CORRIDOR,			"STRONGHOLD_CORRIDOR");
		RegUtil.register(STRONGHOLD_PORTAL_ROOM,		"STRONGHOLD_PORTAL_ROOM");
		RegUtil.register(STRONGHOLD_LIBRARY,			"STRONGHOLD_LIBRARY");
		RegUtil.register(STRONGHOLD_PRISON,				"STRONGHOLD_PRISON");
		RegUtil.register(STRONGHOLD_ROOM_CROSSING,		"STRONGHOLD_ROOM_CROSSING");
		RegUtil.register(STRONGHOLD_CROSSING,			"STRONGHOLD_CROSSING");
		RegUtil.register(STRONGHOLD_RIGHT_TURN,			"STRONGHOLD_RIGHT_TURN");
		RegUtil.register(STRONGHOLD_LEFT_TURN,			"STRONGHOLD_LEFT_TURN");
		RegUtil.register(STRONGHOLD_STRAIGHT,			"STRONGHOLD_STRAIGHT");
		RegUtil.register(STRONGHOLD_STAIRS,				"STRONGHOLD_STAIRS");
		RegUtil.register(STRONGHOLD_STAIRS_STRAIGHT,	"STRONGHOLD_STAIRS_STRAIGHT");
		RegUtil.register(STRONGHOLD_ENTRANCE_STAIRS,	"STRONGHOLD_ENTRANCE_STAIRS");
		RegUtil.register(NETHER_TEMPLE_PIECE,			"NETHER_TEMPLE_PIECE");
		RegUtil.register(BADLANDS_TEMPLE_PIECE,			"BADLANDS_TEMPLE_PIECE");
	}

}
