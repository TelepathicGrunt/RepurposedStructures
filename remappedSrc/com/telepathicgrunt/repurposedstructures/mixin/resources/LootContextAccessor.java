package com.telepathicgrunt.repurposedstructures.mixin.resources;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;

@Mixin(LootContext.class)
public interface LootContextAccessor {
    @Accessor("parameters")
    Map<LootContextParam<?>, Object> repurposedstructures_getParameters();

    @Accessor("drops")
    Map<ResourceLocation, LootContext.DynamicDrop> repurposedstructures_getDrops();
}
