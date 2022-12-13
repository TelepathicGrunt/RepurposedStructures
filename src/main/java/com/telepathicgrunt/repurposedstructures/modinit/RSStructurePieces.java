package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.LegacyOceanBottomSinglePoolElement;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionStructurePiece;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MirroringSingleJigsawPiece;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;


public final class RSStructurePieces {
    private RSStructurePieces() {}

    public static StructurePoolElementType<MirroringSingleJigsawPiece> MIRROR_SINGLE;
    public static StructurePoolElementType<LegacyOceanBottomSinglePoolElement> LEGACY_OCEAN_BOTTOM;
    public static StructurePieceType MANSION_STRUCTURE_PIECE;

    public static void registerStructurePieces() {
        // needed to make template pool based mansions work
        MIRROR_SINGLE = Registry.register(BuiltInRegistries.STRUCTURE_POOL_ELEMENT, new ResourceLocation(RepurposedStructures.MODID, "mirroring_single_pool_element"), () -> MirroringSingleJigsawPiece.CODEC);
        LEGACY_OCEAN_BOTTOM = Registry.register(BuiltInRegistries.STRUCTURE_POOL_ELEMENT, new ResourceLocation(RepurposedStructures.MODID, "legacy_ocean_bottom_single_pool_element"), () -> LegacyOceanBottomSinglePoolElement.CODEC);
        MANSION_STRUCTURE_PIECE = Registry.register(BuiltInRegistries.STRUCTURE_PIECE, new ResourceLocation(RepurposedStructures.MODID, "mansion_structure_piece"), MansionStructurePiece::new);
    }
}
