package com.telepathicgrunt.repurposedstructures.mixin.resources;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;

@Mixin(LootContext.Builder.class)
public interface BuilderAccessor {
    @Mutable
    @Accessor("drops")
    void repurposedstructures_setDrops(Map<ResourceLocation, LootContext.DynamicDrop> drops);

    @Mutable
    @Accessor("parameters")
    void repurposedstructures_setParameters(Map<LootContextParam<?>, Object> parameters);
}
