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
    Mirror rs_getMirror();

    @Accessor("rotation")
    Rotation rs_getRotation();

    @Accessor("BLOCKS_NEEDING_POSTPROCESSING")
    Set<Block> rs_getBLOCKS_NEEDING_POST_PROCESSING();
}