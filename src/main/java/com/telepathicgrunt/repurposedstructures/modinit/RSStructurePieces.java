package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MirroringSingleJigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.IJigsawDeserializer;


public final class RSStructurePieces {
    private RSStructurePieces() {}

    public static IJigsawDeserializer<MirroringSingleJigsawPiece> MIRROR_SINGLE;

    public static void registerStructurePieces() {
        // needed to make template pool based mansions work
        MIRROR_SINGLE = IJigsawDeserializer.register(RepurposedStructures.MODID + ":mirroring_single_pool_element", MirroringSingleJigsawPiece.CODEC);
    }
}
