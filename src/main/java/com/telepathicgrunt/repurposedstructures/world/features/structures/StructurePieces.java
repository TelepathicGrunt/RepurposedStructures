package com.telepathicgrunt.repurposedstructures.world.features.structures;

import com.telepathicgrunt.repurposedstructures.utils.RegUtil;

import net.minecraft.world.gen.feature.structure.IStructurePieceType;


public class StructurePieces
{
	public static IStructurePieceType	MSCORRIDORRS	= RSMineshaftPieces.Corridor::new;
	public static IStructurePieceType	MSCROSSINGRS	= RSMineshaftPieces.Cross::new;
	public static IStructurePieceType	MSROOMRS		= RSMineshaftPieces.Room::new;
	public static IStructurePieceType	MSSTAIRSRS		= RSMineshaftPieces.Stairs::new;
	public static IStructurePieceType	JFCR			= JungleFortressPieces.Crossing3::new;
	public static IStructurePieceType	JFEF			= JungleFortressPieces.End::new;
	public static IStructurePieceType	JFS				= JungleFortressPieces.Straight::new;
	public static IStructurePieceType	JFCCS			= JungleFortressPieces.Corridor3::new;
	public static IStructurePieceType	JFCTB			= JungleFortressPieces.Corridor4::new;
	public static IStructurePieceType	JFCE			= JungleFortressPieces.Entrance::new;
	public static IStructurePieceType	JFSCSC			= JungleFortressPieces.Crossing2::new;
	public static IStructurePieceType	JFSCLT			= JungleFortressPieces.Corridor::new;
	public static IStructurePieceType	JFSC			= JungleFortressPieces.Corridor5::new;
	public static IStructurePieceType	JFSCRT			= JungleFortressPieces.Corridor2::new;
	public static IStructurePieceType	JFCSR			= JungleFortressPieces.NetherStalkRoom::new;
	public static IStructurePieceType	JFMT			= JungleFortressPieces.Throne::new;
	public static IStructurePieceType	JFRC			= JungleFortressPieces.Crossing::new;
	public static IStructurePieceType	JFSR			= JungleFortressPieces.Stairs::new;
	public static IStructurePieceType	JFSTART			= JungleFortressPieces.Start::new;
	public static IStructurePieceType	SHCCRS			= RSStrongholdPieces.ChestCorridor::new;
	public static IStructurePieceType	SHFCRS			= RSStrongholdPieces.Corridor::new;
	public static IStructurePieceType	SH5CRS			= RSStrongholdPieces.Crossing::new;
	public static IStructurePieceType	SHLTRS			= RSStrongholdPieces.LeftTurn::new;
	public static IStructurePieceType	SHLIRS			= RSStrongholdPieces.Library::new;
	public static IStructurePieceType	SHPRRS			= RSStrongholdPieces.PortalRoom::new;
	public static IStructurePieceType	SHPHRS			= RSStrongholdPieces.Prison::new;
	public static IStructurePieceType	SHRTRS			= RSStrongholdPieces.RightTurn::new;
	public static IStructurePieceType	SHRCRS			= RSStrongholdPieces.RoomCrossing::new;
	public static IStructurePieceType	SHSDRS			= RSStrongholdPieces.Stairs::new;
	public static IStructurePieceType	SHSTARTRS		= RSStrongholdPieces.EntranceStairs::new;
	public static IStructurePieceType	SHSRS			= RSStrongholdPieces.Straight::new;
	public static IStructurePieceType	SHSSDRS			= RSStrongholdPieces.StairsStraight::new;
	public static IStructurePieceType 	NTP				= NetherTemplePiece::new;


	public static void registerStructurePieces()
	{
		RegUtil.register(MSCORRIDORRS,	"MSCorridorRS");
		RegUtil.register(MSCROSSINGRS,	"MSCrossingRS");
		RegUtil.register(MSROOMRS, 		"MSRoomRS");
		RegUtil.register(MSSTAIRSRS, 	"MSStairsRS");
		RegUtil.register(JFCR, 			"JFCr");
		RegUtil.register(JFEF, 			"JFEF");
		RegUtil.register(JFS, 			"JFS");
		RegUtil.register(JFCCS, 		"JFCCS");
		RegUtil.register(JFCTB, 		"JFCTB");
		RegUtil.register(JFCE, 			"JFCE");
		RegUtil.register(JFSCSC, 		"JFSCSC");
		RegUtil.register(JFSCLT, 		"JFSCLT");
		RegUtil.register(JFSC,			"JFSC");
		RegUtil.register(JFSCRT, 		"JFSCRT");
		RegUtil.register(JFCSR, 		"JFCSR");
		RegUtil.register(JFMT, 			"JFMT");
		RegUtil.register(JFRC, 			"JFRC");
		RegUtil.register(JFSR, 			"JFSR");
		RegUtil.register(JFSTART, 		"JFStart");
		RegUtil.register(SHCCRS, 		"SHCCRS");
		RegUtil.register(SHFCRS, 		"SHFCRS");
		RegUtil.register(SH5CRS, 		"SH5CRS");
		RegUtil.register(SHLTRS, 		"SHLTRS");
		RegUtil.register(SHLIRS, 		"SHLiRS");
		RegUtil.register(SHPRRS, 		"SHPRRS");
		RegUtil.register(SHPHRS,		"SHPHRS");
		RegUtil.register(SHRTRS, 		"SHRTRS");
		RegUtil.register(SHRCRS, 		"SHRCRS");
		RegUtil.register(SHSDRS, 		"SHSDRS");
		RegUtil.register(SHSTARTRS, 	"SHStartRS");
		RegUtil.register(SHSRS, 		"SHSRS");
		RegUtil.register(SHSSDRS, 		"SHSSDRS");
		RegUtil.register(NTP, 			"NTP");
	}

}
