package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.block.Block;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(StructurePiece.class)
public interface StructurePieceAccessor {

    @Accessor("mirror")
    Mirror repurposedstructures_getMirror();

    @Accessor("rotation")
    Rotation repurposedstructures_getRotation();

    @Accessor("SHAPE_CHECK_BLOCKS")
    Set<Block> repurposedstructures_getBLOCKS_NEEDING_POST_PROCESSING();
}