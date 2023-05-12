package com.telepathicgrunt.repurposedstructures.misc.fabricbase.lootmanager;

import com.telepathicgrunt.repurposedstructures.configs.RSMainModdedLootConfig;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.StructureModdedLootImporter;
import com.telepathicgrunt.repurposedstructures.mixins.fabricbase.resources.LootContextAccessor;
import com.telepathicgrunt.repurposedstructures.mixins.fabricbase.resources.LootManagerAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class StructureModdedLootImporterApplier {
    private StructureModdedLootImporterApplier() {}

    // Cache the reverse lookup for what loottable goes with what identifier
    private static final Map<LootTable, ResourceLocation> REVERSED_TABLES = new HashMap<>();

    public static void checkAndGetModifiedLoot(LootContext context, LootTable currentLootTable, List<ItemStack> originalLoot) {
        if(RSMainModdedLootConfig.importModdedItems) {
            // Cache the result of the loottable to the id into our own map.
            ResourceLocation lootTableID = REVERSED_TABLES.computeIfAbsent(
                    currentLootTable,
                    // Will iterate lazily through loottable map for which identifier gives this loottable and return the result
                    (lootTable) -> ((LootManagerAccessor)context.getLevel().getServer().getLootData()).repurposedstructures_getTables()
                            .entrySet()
                            .stream()
                            .filter(entry -> lootTable.equals(entry.getValue()))
                            .map(key -> key.getKey().location())
                            .findFirst()
                            .orElse(null) // null should be ever returned as otherwise, that would be concerning...
            );

            if(lootTableID != null && !StructureModdedLootImporter.isInBlacklist(lootTableID)) {
                StructureModdedLootImporterApplier.modifyLootTables(context, lootTableID, originalLoot);
            }
        }

    }

    public static void modifyLootTables(LootContext context, ResourceLocation lootTableID, List<ItemStack> originalLoot) {
        ResourceLocation tableToImportLoot = StructureModdedLootImporter.TABLE_IMPORTS.get(lootTableID);
        if(tableToImportLoot == null) return; // Safety net

        // Generate random loot that would've been in vanilla chests. (Need to make new context or else we recursively call ourselves infinitely)
        LootContext newContext = copyLootContext(context);
        List<ItemStack> newlyGeneratedLoot = newContext.getResolver().getLootTable(tableToImportLoot).getRandomItems(((LootContextAccessor)newContext).getParams());

        // Remove all vanilla loot so we only have modded loot
        newlyGeneratedLoot.removeIf(itemStack -> {
            ResourceKey<Item> itemKey = BuiltInRegistries.ITEM.getResourceKey(itemStack.getItem()).orElse(null);
            return itemKey != null && itemKey.location().getNamespace().equals("minecraft");
        });

        // Intercept and modify the loot based on other mods being on
        EndRemasteredDedicatedLootApplier.handleDedicatedModCompat(newlyGeneratedLoot, lootTableID, context);

        // Add modded loot to my structure's chests
        originalLoot.addAll(newlyGeneratedLoot);
    }

    static LootContext copyLootContext(LootContext oldLootContext) {
        LootContext.Builder newContextBuilder = new LootContext.Builder(((LootContextAccessor)oldLootContext).getParams())
                .withOptionalRandomSeed(oldLootContext.getRandom().nextLong());

        return newContextBuilder.create(new ResourceLocation("empty"));
    }
}
