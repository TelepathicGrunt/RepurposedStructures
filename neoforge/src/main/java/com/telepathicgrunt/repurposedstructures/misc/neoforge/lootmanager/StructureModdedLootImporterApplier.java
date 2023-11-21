package com.telepathicgrunt.repurposedstructures.misc.neoforge.lootmanager;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.configs.neoforge.RSModdedLootConfig;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.StructureModdedLootImporter;
import com.telepathicgrunt.repurposedstructures.mixins.resources.LootContextAccessor;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.List;
import java.util.Optional;
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
        List<ItemStack> newlyGeneratedLoot = context.getResolver().getLootTable(tableToImportLoot).getRandomItems(((LootContextAccessor)newContext).getParams());

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
                .withOptionalRandomSeed(oldLootContext.getRandom().nextLong());

        return newContextBuilder.create(Optional.empty());
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}