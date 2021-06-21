package com.telepathicgrunt.repurposedstructures.mixin.structures;

import net.minecraft.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(Structure.class)
public interface TemplateAccessor {

    @Accessor("blockInfoLists")
    List<Structure.PalettedBlockInfoList> repurposedstructures_getBlocks();
}
