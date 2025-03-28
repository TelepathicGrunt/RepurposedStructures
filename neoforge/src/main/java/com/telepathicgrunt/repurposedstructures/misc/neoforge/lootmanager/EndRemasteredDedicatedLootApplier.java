package com.telepathicgrunt.repurposedstructures.misc.neoforge.lootmanager;

import com.telepathicgrunt.repurposedstructures.misc.lootmanager.EndRemasteredDedicatedLoot;
import com.telepathicgrunt.repurposedstructures.mixins.resources.LootContextAccessor;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EndRemasteredDedicatedLootApplier {
    private EndRemasteredDedicatedLootApplier() {}

    protected static void handleDedicatedModCompat(List<ItemStack> currentLoot, LootContext oldLootContext){
        // Remove their eyes from the default importing and instead, import the correct eyes they really want for this structure.
        if(EndRemasteredDedicatedLoot.isEndRemasteredOn) {
            // Remove incorrect End Remastered loot
            currentLoot.removeIf(itemStack -> BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getNamespace().equals("endrem"));

            ResourceKey<LootTable> key = ResourceKey.create(Registries.LOOT_TABLE, oldLootContext.getQueriedLootTableId());

            // Get correct pool they want us to use
            ResourceKey<LootTable> tableToImportLoot = EndRemasteredDedicatedLoot.END_REMASTERED_DEDICATED_TABLE_IMPORTS.get(key);
            if(tableToImportLoot == null) return; // No entry found

            // Generate End Remastered's dedicated loot
            LootContext newContext = StructureModdedLootImporterApplier.copyLootContextWithNewQueryID(oldLootContext, tableToImportLoot.location());
            Optional<Holder.Reference<LootTable>> optionalLootTableReference = oldLootContext.getResolver().get(tableToImportLoot);

            List<ItemStack> endRemasteredLoot = optionalLootTableReference.isPresent() ?
                    optionalLootTableReference.get().value().getRandomItems(((LootContextAccessor)newContext).repurposedstructures$getParams()) : new ArrayList<>();

            currentLoot.addAll(endRemasteredLoot);
        }
    }
}
