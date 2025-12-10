package com.telepathicgrunt.repurposedstructures.misc.lootmanager;

import com.telepathicgrunt.repurposedstructures.configs.RSMainModdedLootConfig;
import com.telepathicgrunt.repurposedstructures.mixins.resources.LootContextAccessor;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class StructureModdedLootImporterApplier {
    private StructureModdedLootImporterApplier() {}

    public static void checkAndGetModifiedLoot(LootContext context, LootTable currentLootTable, List<ItemStack> originalLoot) {
        if (RSMainModdedLootConfig.importModdedItems) {

            Identifier lootTableID = ((Registry<LootTable>)context.getLevel().getServer().reloadableRegistries().lookup().lookupOrThrow(Registries.LOOT_TABLE)).getKey(currentLootTable);
            if (lootTableID != null) {
                ResourceKey<LootTable> key = ResourceKey.create(Registries.LOOT_TABLE, lootTableID);
                if (!StructureModdedLootImporter.isInBlacklist(key)) {
                    StructureModdedLootImporterApplier.modifyLootTables(context, key, originalLoot);
                }
            }
        }

    }

    public static void modifyLootTables(LootContext context, ResourceKey<LootTable> lootTableID, List<ItemStack> originalLoot) {
        ResourceKey<LootTable> tableToImportLoot = StructureModdedLootImporter.TABLE_IMPORTS.get(lootTableID);
        if (tableToImportLoot == null) {
            return; // Safety net
        }

        // Generate random loot that would've been in vanilla chests. (Need to make new context or else we recursively call ourselves infinitely)
        LootContext newContext = copyLootContext(context);
        Optional<Holder.Reference<LootTable>> optionalLootTableReference = context.getResolver().get(tableToImportLoot);

        List<ItemStack> newlyGeneratedLoot = optionalLootTableReference.isPresent() ?
                optionalLootTableReference.get().value().getRandomItems(((LootContextAccessor)newContext).repurposedstructures$getParams()) : new ArrayList<>();

        // Remove all vanilla loot so we only have modded loot
        newlyGeneratedLoot.removeIf(itemStack -> {
            ResourceKey<Item> itemKey = BuiltInRegistries.ITEM.getResourceKey(itemStack.getItem()).orElse(null);
            return itemKey != null && itemKey.identifier().getNamespace().equals("minecraft");
        });

        // Intercept and modify the loot based on other mods being on
        EndRemasteredDedicatedLootApplier.handleDedicatedModCompat(newlyGeneratedLoot, lootTableID, context);

        // Add modded loot to my structure's chests
        originalLoot.addAll(newlyGeneratedLoot);
    }

    static LootContext copyLootContext(LootContext oldLootContext) {
        LootContext.Builder newContextBuilder = new LootContext.Builder(((LootContextAccessor)oldLootContext).repurposedstructures$getParams())
                .withOptionalRandomSeed(oldLootContext.getRandom().nextLong());

        return newContextBuilder.create(Optional.empty());
    }
}
