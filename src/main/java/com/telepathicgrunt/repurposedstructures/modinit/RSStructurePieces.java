package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.FortressJunglePieces;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class RSStructurePieces {
    public static StructurePieceType JUNGLE_FORTRESS_START = FortressJunglePieces.Start::new;
    public static StructurePieceType JUNGLE_FORTRESS_END = FortressJunglePieces.End::new;
    public static StructurePieceType JUNGLE_FORTRESS_ENTRANCE = FortressJunglePieces.Entrance::new;
    public static StructurePieceType JUNGLE_FORTRESS_STRAIGHT = FortressJunglePieces.Straight::new;
    public static StructurePieceType JUNGLE_FORTRESS_CORRIDOR_5 = FortressJunglePieces.Corridor5::new;
    public static StructurePieceType JUNGLE_FORTRESS_CORRIDOR_4 = FortressJunglePieces.Corridor4::new;
    public static StructurePieceType JUNGLE_FORTRESS_CORRIDOR_3 = FortressJunglePieces.Corridor3::new;
    public static StructurePieceType JUNGLE_FORTRESS_CORRIDOR_2 = FortressJunglePieces.Corridor2::new;
    public static StructurePieceType JUNGLE_FORTRESS_CORRIDOR_1 = FortressJunglePieces.Corridor::new;
    public static StructurePieceType JUNGLE_FORTRESS_CROSSING_3 = FortressJunglePieces.Crossing3::new;
    public static StructurePieceType JUNGLE_FORTRESS_CROSSING_2 = FortressJunglePieces.Crossing2::new;
    public static StructurePieceType JUNGLE_FORTRESS_CROSSING_1 = FortressJunglePieces.Crossing::new;
    public static StructurePieceType JUNGLE_FORTRESS_MUSHROOM_ROOM = FortressJunglePieces.MushroomRoom::new;
    public static StructurePieceType JUNGLE_FORTRESS_THRONE = FortressJunglePieces.Throne::new;
    public static StructurePieceType JUNGLE_FORTRESS_STAIRS = FortressJunglePieces.Stairs::new;
    public static StructurePieceType MANSION_PIECE = MansionPieces.Piece::new;

    public static void registerStructurePieces() {
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_start"), JUNGLE_FORTRESS_START);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_entrance"), JUNGLE_FORTRESS_ENTRANCE);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_end"), JUNGLE_FORTRESS_END);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_straight"), JUNGLE_FORTRESS_STRAIGHT);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_corridor_5"), JUNGLE_FORTRESS_CORRIDOR_5);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_corridor_4"), JUNGLE_FORTRESS_CORRIDOR_4);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_corridor_3"), JUNGLE_FORTRESS_CORRIDOR_3);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_corridor_2"), JUNGLE_FORTRESS_CORRIDOR_2);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_corridor_1"), JUNGLE_FORTRESS_CORRIDOR_1);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_crossing_3"), JUNGLE_FORTRESS_CROSSING_3);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_crossing_2"), JUNGLE_FORTRESS_CROSSING_2);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_crossing_1"), JUNGLE_FORTRESS_CROSSING_1);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_mushroom_room"), JUNGLE_FORTRESS_MUSHROOM_ROOM);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_throne"), JUNGLE_FORTRESS_THRONE);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_stairs"), JUNGLE_FORTRESS_STAIRS);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "mansion_piece"), MANSION_PIECE);
    }
}
