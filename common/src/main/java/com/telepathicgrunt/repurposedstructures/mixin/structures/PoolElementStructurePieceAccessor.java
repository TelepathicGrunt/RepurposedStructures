package com.telepathicgrunt.repurposedstructures.mixin.structures;

import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PoolElementStructurePiece.class)
public interface PoolElementStructurePieceAccessor {
    @Accessor("structureTemplateManager")
    StructureTemplateManager getStructureManager();

    @Mutable
    @Accessor("rotation")
    void setRotation(Rotation rotation);
}
