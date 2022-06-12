package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.DetectRSLootTables;
import com.telepathicgrunt.repurposedstructures.misc.lootmanager.StructureModdedLootImporter;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class RSGlobalLootModifier {
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, RepurposedStructures.MODID);

    public static final RegistryObject<StructureModdedLootImporter.Serializer> STRUCTURE_MODDED_LOOT_IMPORTER = GLM.register("import_structure_modded_loot", StructureModdedLootImporter.Serializer::new);

    public static void registerLootData() {
        Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(RepurposedStructures.MODID, "detect_rs_loot_tables"), DetectRSLootTables.DETECT_RS_LOOT_TABLES);
    }
}
