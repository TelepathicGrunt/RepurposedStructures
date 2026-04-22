package com.telepathicgrunt.repurposedstructures.misc.lootmanager;

import com.telepathicgrunt.repurposedstructures.configs.RSMainModdedLootConfig;
import com.telepathicgrunt.repurposedstructures.mixins.resources.LootContextAccessor;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
        if(RSMainModdedLootConfig.importModdedItems) {

            ResourceLocation lootTableID = context.getLevel().getServer().reloadableRegistries().get().registryOrThrow(Registries.LOOT_TABLE).getKey(currentLootTable);
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
        Optional<Holder.Reference<LootTable>> optionalLootTableReference = context.getResolver().get(Registries.LOOT_TABLE, ResourceKey.create(Registries.LOOT_TABLE, tableToImportLoot));

        List<ItemStack> newlyGeneratedLoot = optionalLootTableReference.isPresent() ?
                optionalLootTableReference.get().value().getRandomItems(((LootContextAccessor)newContext).getParams()) : new ArrayList<>();

        // Intercept and modify the loot based on other mods being on
        EndRemasteredDedicatedLootApplier.handleDedicatedModCompat(newlyGeneratedLoot, lootTableID, context);

        // Remove all vanilla loot so we only have modded loot
        newlyGeneratedLoot.removeIf(StructureModdedLootImporter::isFilteredOut);

        // Add modded loot to my structure's chests
        originalLoot.addAll(newlyGeneratedLoot);
    }

    static LootContext copyLootContext(LootContext oldLootContext) {
        LootContext.Builder newContextBuilder = new LootContext.Builder(((LootContextAccessor)oldLootContext).getParams())
                .withOptionalRandomSeed(oldLootContext.getRandom().nextLong());

        return newContextBuilder.create(Optional.empty());
    }
}
