package com.telepathicgrunt.repurposedstructures.modinit.forge;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.forge.lootmanager.DetectRSLootTables;
import com.telepathicgrunt.repurposedstructures.misc.forge.lootmanager.StructureModdedLootImporterApplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class RSGlobalLootModifier {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, RepurposedStructures.MODID);

    public static final RegistryObject<Codec<StructureModdedLootImporterApplier>> STRUCTURE_MODDED_LOOT_IMPORTER = GLM.register("import_modded_loot", StructureModdedLootImporterApplier.CODEC);

    public static void registerLootData() {
        Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, new ResourceLocation(RepurposedStructures.MODID, "detect_rs_loot_tables"), DetectRSLootTables.DETECT_RS_LOOT_TABLES);
    }
}
