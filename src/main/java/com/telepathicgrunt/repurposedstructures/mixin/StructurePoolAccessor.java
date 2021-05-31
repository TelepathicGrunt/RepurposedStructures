package com.telepathicgrunt.repurposedstructures.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(StructurePool.class)
public interface StructurePoolAccessor {
    @Accessor("elementCounts")
    List<Pair<StructurePoolElement, Integer>> repurposedstructures_getElementCounts();

    @Mutable
    @Accessor("elementCounts")
    void repurposedstructures_setElementCounts(List<Pair<StructurePoolElement, Integer>> elementCounts);

    @Accessor("elements")
    List<StructurePoolElement> repurposedstructures_getElements();

    @Mutable
    @Accessor("elements")
    void repurposedstructures_setElements(List<StructurePoolElement> elements);
}
