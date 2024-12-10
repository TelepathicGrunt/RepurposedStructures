package com.telepathicgrunt.repurposedstructures.misc.neoforge.lootmanager;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.configs.neoforge.RSModdedLootConfig;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.StructureModdedLootImporter;
import com.telepathicgrunt.repurposedstructures.mixins.resources.LootContextAccessor;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class StructureModdedLootImporterApplier extends LootModifier {

    public static final Supplier<MapCodec<StructureModdedLootImporterApplier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.mapCodec(inst -> codecStart(inst).apply(inst, StructureModdedLootImporterApplier::new)));

    public StructureModdedLootImporterApplier(final LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (!RSModdedLootConfig.importModdedItems.get()) {
            return generatedLoot; // easier blacklist for users
        }

        ResourceKey<LootTable> key = ResourceKey.create(Registries.LOOT_TABLE, context.getQueriedLootTableId());
        if (StructureModdedLootImporter.isInBlacklist(key)) {
            return generatedLoot; // easier blacklist for users
        }

        ResourceKey<LootTable> tableToImportLoot = StructureModdedLootImporter.TABLE_IMPORTS.get(key);
        if (tableToImportLoot == null) {
            return generatedLoot; // Safety net
        }

        // Generate random loot that would've been in vanilla chests. (Need to make new context or else we recursively call ourselves infinitely)
        LootContext newContext = copyLootContextWithNewQueryID(context, tableToImportLoot.location());
        Optional<Holder.Reference<LootTable>> optionalLootTableReference = context.getResolver().get(tableToImportLoot);

        List<ItemStack> newlyGeneratedLoot = optionalLootTableReference.isPresent() ?
                optionalLootTableReference.get().value().getRandomItems(((LootContextAccessor)newContext).getParams()) : new ArrayList<>();

        // Remove all vanilla loot so we only have modded loot
        newlyGeneratedLoot.removeIf(itemStack -> BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getNamespace().equals("minecraft"));

        // Intercept and modify the loot based on other mods being on
        EndRemasteredDedicatedLootApplier.handleDedicatedModCompat(newlyGeneratedLoot, context);

        // Add modded loot to my structure's chests
        generatedLoot.addAll(newlyGeneratedLoot);
        return generatedLoot;
    }

    protected static LootContext copyLootContextWithNewQueryID(LootContext oldLootContext, ResourceLocation newQueryID){
        LootContext.Builder newContextBuilder = new LootContext.Builder(((LootContextAccessor)oldLootContext).getParams())
                .withOptionalRandomSeed(oldLootContext.getRandom().nextLong())
                .withQueriedLootTableId(newQueryID);

        return newContextBuilder.create(Optional.empty());
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}