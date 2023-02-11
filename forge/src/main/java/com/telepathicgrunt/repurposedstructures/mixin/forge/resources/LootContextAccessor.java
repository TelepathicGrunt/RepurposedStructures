package com.telepathicgrunt.repurposedstructures.mixin.forge.resources;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LootContext.class)
public interface LootContextAccessor {
    @Accessor(value = "queriedLootTableId", remap = false)
    void repurposedstructures_setQueriedLootTableId(ResourceLocation queriedLootTableId);
}
