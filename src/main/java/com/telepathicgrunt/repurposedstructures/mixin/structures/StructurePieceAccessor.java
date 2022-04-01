package com.telepathicgrunt.repurposedstructures.mixin.structures;

import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StructurePiece.class)
public interface StructurePieceAccessor {
    @Accessor("boundingBox")
    void setBoundingBox(BoundingBox boundingBox);
}
