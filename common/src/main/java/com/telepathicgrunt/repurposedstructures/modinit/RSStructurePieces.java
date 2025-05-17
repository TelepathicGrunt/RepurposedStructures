package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.registry.RegistryEntry;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistries;
import com.telepathicgrunt.repurposedstructures.modinit.registry.ResourcefulRegistry;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.LegacyOceanBottomSinglePoolElement;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionStructurePiece;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MirroringSingleJigsawPiece;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;


public final class RSStructurePieces {
    public static final ResourcefulRegistry<StructurePoolElementType<?>> STRUCTURE_POOL_ELEMENT = ResourcefulRegistries.create(BuiltInRegistries.STRUCTURE_POOL_ELEMENT, RepurposedStructures.MODID);
    public static final ResourcefulRegistry<StructurePieceType> STRUCTURE_PIECE = ResourcefulRegistries.create(BuiltInRegistries.STRUCTURE_PIECE, RepurposedStructures.MODID);

    public static final RegistryEntry<StructurePoolElementType<MirroringSingleJigsawPiece>> MIRROR_SINGLE = STRUCTURE_POOL_ELEMENT.register("mirroring_single_pool_element", () -> () -> MirroringSingleJigsawPiece.CODEC);
    public static final RegistryEntry<StructurePoolElementType<LegacyOceanBottomSinglePoolElement>> LEGACY_OCEAN_BOTTOM = STRUCTURE_POOL_ELEMENT.register("legacy_ocean_bottom_single_pool_element", () -> () -> LegacyOceanBottomSinglePoolElement.CODEC);
    public static final RegistryEntry<StructurePieceType> MANSION_STRUCTURE_PIECE = STRUCTURE_PIECE.register("mansion_structure_piece", () -> MansionStructurePiece::new);
}
