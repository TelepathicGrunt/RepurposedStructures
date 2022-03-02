package com.telepathicgrunt.repurposedstructures.mixin.structures;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(StructureTemplatePool.class)
public interface StructurePoolAccessor {
    @Accessor("rawTemplates")
    List<Pair<StructurePoolElement, Integer>> repurposedstructures_getRawTemplates();

    @Mutable
    @Accessor("rawTemplates")
    void repurposedstructures_setRawTemplates(List<Pair<StructurePoolElement, Integer>> elementCounts);

    @Accessor("templates")
    List<StructurePoolElement> repurposedstructures_getTemplates();

    @Mutable
    @Accessor("templates")
    void repurposedstructures_setTemplates(List<StructurePoolElement> elements);
}
