package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class RSStructurePieces {
    public static StructurePieceType MANSION_PIECE = MansionPieces.Piece::new;

    public static void registerStructurePieces() {
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "mansion_piece"), MANSION_PIECE);
    }
}
