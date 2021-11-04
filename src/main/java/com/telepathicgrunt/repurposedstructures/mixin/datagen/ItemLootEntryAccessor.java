package com.telepathicgrunt.repurposedstructures.mixin.datagen;

import net.minecraft.item.Item;
import net.minecraft.loot.ItemLootEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemLootEntry.class)
public interface ItemLootEntryAccessor {
    @Accessor
    Item getItem();
}
