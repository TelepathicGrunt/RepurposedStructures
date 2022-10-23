package com.telepathicgrunt.repurposedstructures.mixin.resources;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(LootContext.Builder.class)
public interface BuilderAccessor {
    @Mutable
    @Accessor("dynamicDrops")
    void setDynamicDrops(Map<ResourceLocation, LootContext.DynamicDrop> drops);

    @Accessor("params")
    Map<LootContextParam<?>, Object> getParams();
}
