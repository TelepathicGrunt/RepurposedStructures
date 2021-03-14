package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.loot.LootContext;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LootContext.class)
public interface LootContextAccessor {
    @Accessor("queriedLootTableId")
    void rs_setQueriedLootTableId(ResourceLocation queriedLootTableId);
}
