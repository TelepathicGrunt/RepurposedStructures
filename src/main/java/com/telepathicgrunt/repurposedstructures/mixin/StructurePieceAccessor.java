package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.block.Block;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(StructurePiece.class)
public interface StructurePieceAccessor {

    @Accessor("mirror")
    BlockMirror getMirror();

    @Accessor("rotation")
    BlockRotation getRotation();

    @Accessor("BLOCKS_NEEDING_POST_PROCESSING")
    Set<Block> getBLOCKS_NEEDING_POST_PROCESSING();
}