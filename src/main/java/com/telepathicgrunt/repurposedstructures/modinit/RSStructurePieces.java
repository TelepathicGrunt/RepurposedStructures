package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.placements.MinusEightPlacement;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionStructurePiece;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MirroringSingleJigsawPiece;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public final class RSStructurePieces {
    public static final DeferredRegister<StructurePoolElementType<?>> STRUCTURE_POOL_ELEMENT = DeferredRegister.create(Registry.STRUCTURE_POOL_ELEMENT_REGISTRY, RepurposedStructures.MODID);
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE = DeferredRegister.create(Registry.STRUCTURE_PIECE_REGISTRY, RepurposedStructures.MODID);

    public static final RegistryObject<StructurePoolElementType<MirroringSingleJigsawPiece>> MIRROR_SINGLE = STRUCTURE_POOL_ELEMENT.register("mirroring_single_pool_element", () -> () -> MirroringSingleJigsawPiece.CODEC);
    public static final RegistryObject<StructurePieceType> MANSION_STRUCTURE_PIECE = STRUCTURE_PIECE.register("mansion_structure_piece", () -> MansionStructurePiece::new);
}
