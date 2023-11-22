package com.telepathicgrunt.repurposedstructures.modinit.neoforge;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.neoforge.lootmanager.DetectRSLootTables;
import com.telepathicgrunt.repurposedstructures.misc.neoforge.lootmanager.StructureModdedLootImporterApplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class RSGlobalLootModifier {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, RepurposedStructures.MODID);

    public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<StructureModdedLootImporterApplier>> STRUCTURE_MODDED_LOOT_IMPORTER = GLM.register("import_modded_loot", StructureModdedLootImporterApplier.CODEC);

    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITION_TYPE = DeferredRegister.create(BuiltInRegistries.LOOT_CONDITION_TYPE, RepurposedStructures.MODID);

    public static final DeferredHolder<LootItemConditionType, LootItemConditionType> DETECT_RS_LOOT_TABLES = LOOT_CONDITION_TYPE.register("detect_rs_loot_tables", () -> DetectRSLootTables.DETECT_RS_LOOT_TABLES);
}
