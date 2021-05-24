package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;


public class RSStructurePieces {
    public static IStructurePieceType MANSION_PIECE = MansionPieces.MansionTemplate::new;

    public static void registerStructurePieces() {
        Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(RepurposedStructures.MODID, "mansion_piece"), MANSION_PIECE);
    }
}
