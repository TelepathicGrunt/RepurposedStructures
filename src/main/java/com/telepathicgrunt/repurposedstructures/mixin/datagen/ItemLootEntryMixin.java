package com.telepathicgrunt.repurposedstructures.mixin.datagen;

import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.StandaloneLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ItemLootEntry.Serializer.class)
public abstract class ItemLootEntryMixin extends StandaloneLootEntry.Serializer<ItemLootEntry>{

    /**
     * If you are not datagenning within Repurposed Structures's project and this mixin runs, then let me know as this should not be.
     * @author TelepathicGrunt.
     */
    @Overwrite
    public void serializeCustom(JsonObject jsonObject, ItemLootEntry itemLootEntry, JsonSerializationContext jsonSerializationContext) {
        ResourceLocation resourcelocation = Registry.ITEM.getKey(((ItemLootEntryAccessor)itemLootEntry).getItem());
        if (resourcelocation == null) {
            throw new IllegalArgumentException("Can't serialize unknown item " + ((ItemLootEntryAccessor)itemLootEntry).getItem());
        } else {
            jsonObject.addProperty("name", resourcelocation.toString());
        }
        super.serializeCustom(jsonObject, itemLootEntry, jsonSerializationContext);
    }
}
