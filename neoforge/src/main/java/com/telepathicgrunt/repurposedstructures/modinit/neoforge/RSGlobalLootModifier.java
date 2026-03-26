package com.telepathicgrunt.repurposedstructures.modinit.neoforge;

import com.mojang.serialization.MapCodec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.neoforge.lootmanager.DetectRSLootTables;
import com.telepathicgrunt.repurposedstructures.misc.neoforge.lootmanager.StructureModdedLootImporterApplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class RSGlobalLootModifier {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, RepurposedStructures.MODID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<StructureModdedLootImporterApplier>> STRUCTURE_MODDED_LOOT_IMPORTER = GLM.register("import_modded_loot", StructureModdedLootImporterApplier.CODEC);

    public static final DeferredRegister<MapCodec<? extends LootItemCondition>> LOOT_CONDITION_TYPE = DeferredRegister.create(BuiltInRegistries.LOOT_CONDITION_TYPE, RepurposedStructures.MODID);

    public static final DeferredHolder<MapCodec<? extends LootItemCondition>, MapCodec<DetectRSLootTables>> DETECT_RS_LOOT_TABLES = LOOT_CONDITION_TYPE.register("detect_rs_loot_tables", () -> DetectRSLootTables.CODEC);
}
