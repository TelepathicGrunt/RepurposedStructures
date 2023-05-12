package com.telepathicgrunt.repurposedstructures.misc.fabricbase.lootmanager;

import com.telepathicgrunt.repurposedstructures.misc.lootmanager.EndRemasteredDedicatedLoot;
import com.telepathicgrunt.repurposedstructures.mixins.fabricbase.resources.LootContextAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.List;

public class EndRemasteredDedicatedLootApplier {
    private EndRemasteredDedicatedLootApplier() {}

    protected static void handleDedicatedModCompat(List<ItemStack> currentLoot, ResourceLocation lootTableID, LootContext oldLootContext){
        // Remove their eyes from the default importing and instead, import the correct eyes they really want for this structure.
        if(EndRemasteredDedicatedLoot.isEndRemasteredOn) {
            // Remove incorrect End Remastered loot
            currentLoot.removeIf(itemStack -> BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getNamespace().equals("endrem"));

            // Get correct pool they want us to use
            ResourceLocation tableToImportLoot = EndRemasteredDedicatedLoot.END_REMASTERED_DEDICATED_TABLE_IMPORTS.get(lootTableID);
            if(tableToImportLoot == null) return; // No entry found

            // Generate End Remastered's dedicated loot
            LootContext newContext = StructureModdedLootImporterApplier.copyLootContext(oldLootContext);
            List<ItemStack> endRemasteredLoot = oldLootContext.getResolver().getLootTable(tableToImportLoot).getRandomItems(((LootContextAccessor)newContext).getParams());

            currentLoot.addAll(endRemasteredLoot);
        }
    }
}
