package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MirroringSingleJigsawPiece;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElementType;
import net.minecraft.world.level.levelgen.structure.WoodlandMansionPieces;


public final class RSStructurePieces {
    private RSStructurePieces() {}

    public static StructurePoolElementType<MirroringSingleJigsawPiece> MIRROR_SINGLE;

    public static void registerStructurePieces() {
        // dummy value
        Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(RepurposedStructures.MODID, "mansion_piece"), WoodlandMansionPieces.WoodlandMansionPiece::new);

        // needed to make template pool based mansions work
        MIRROR_SINGLE = StructurePoolElementType.register(RepurposedStructures.MODID + ":mirroring_single_pool_element", MirroringSingleJigsawPiece.CODEC);
    }
}
