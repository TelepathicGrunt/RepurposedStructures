package com.telepathicgrunt.repurposedstructures.mixin.resources;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(LootContext.Builder.class)
public interface BuilderAccessor {
    @Accessor("dynamicDrops")
    Map<ResourceLocation, LootContext.DynamicDrop> getDynamicDrops();

    @Accessor("params")
    Map<LootContextParam<?>, Object> getParams();
}
