package com.telepathicgrunt.repurposedstructures.mixin.resources;

import net.minecraft.loot.LootContext;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LootContext.class)
public interface LootContextAccessor {
    @Accessor(value = "queriedLootTableId", remap = false)
    void repurposedstructures_setQueriedLootTableId(ResourceLocation queriedLootTableId);
}
