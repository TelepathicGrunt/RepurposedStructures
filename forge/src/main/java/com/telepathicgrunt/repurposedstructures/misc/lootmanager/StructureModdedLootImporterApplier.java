package com.telepathicgrunt.repurposedstructures.misc.lootmanager;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.configs.RSModdedLootConfig;
import com.telepathicgrunt.repurposedstructures.mixin.forge.resources.LootContextAccessor;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import java.util.List;
import java.util.function.Supplier;

public class StructureModdedLootImporterApplier extends LootModifier {

    public static final Supplier<Codec<StructureModdedLootImporterApplier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, StructureModdedLootImporterApplier::new)));

    public StructureModdedLootImporterApplier(final LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if(!RSModdedLootConfig.importModdedItems.get() || StructureModdedLootImporter.isInBlacklist(context.getQueriedLootTableId()))
            return generatedLoot; // easier blacklist for users

        ResourceLocation tableToImportLoot = StructureModdedLootImporter.TABLE_IMPORTS.get(context.getQueriedLootTableId());
        if(tableToImportLoot == null)
            return generatedLoot; // Safety net

        // Generate random loot that would've been in vanilla chests. (Need to make new context or else we recursively call ourselves infinitely)
        LootContext newContext = copyLootContextWithNewQueryID(context, tableToImportLoot);
        List<ItemStack> newlyGeneratedLoot = context.getLootTable(tableToImportLoot).getRandomItems(newContext);

        // Remove all vanilla loot so we only have modded loot
        newlyGeneratedLoot.removeIf(itemStack -> BuiltInRegistries.ITEM.getKey(itemStack.getItem()).getNamespace().equals("minecraft"));

        // Intercept and modify the loot based on other mods being on
        EndRemasteredDedicatedLootApplier.handleDedicatedModCompat(newlyGeneratedLoot, context);

        // Add modded loot to my structure's chests
        generatedLoot.addAll(newlyGeneratedLoot);
        return generatedLoot;
    }

    protected static LootContext copyLootContextWithNewQueryID(LootContext oldLootContext, ResourceLocation newQueryID){
        LootContext newContext = new LootContext.Builder(oldLootContext).create(LootContextParamSets.CHEST);
        ((LootContextAccessor)newContext).repurposedstructures_setQueriedLootTableId(newQueryID); // The normal method won't set it as the newContext already has queriedID.
        return newContext;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}