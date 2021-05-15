package com.telepathicgrunt.repurposedstructures.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(StructurePool.class)
public interface StructurePoolAccessor {
    @Accessor("elementCounts")
    List<Pair<StructurePoolElement, Integer>> rs_getElementCounts();
}
