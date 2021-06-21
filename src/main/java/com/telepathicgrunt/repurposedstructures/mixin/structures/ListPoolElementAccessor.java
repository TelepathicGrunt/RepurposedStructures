package com.telepathicgrunt.repurposedstructures.mixin.structures;

import net.minecraft.structure.pool.ListPoolElement;
import net.minecraft.structure.pool.StructurePoolElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(ListPoolElement.class)
public interface ListPoolElementAccessor {
    @Accessor("elements")
    List<StructurePoolElement> repurposedstructures_getElements();
}
