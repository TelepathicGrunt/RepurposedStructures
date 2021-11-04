package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.misc.DetectRSLootTables;
import com.telepathicgrunt.repurposedstructures.misc.StructureModdedLootImporter;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class RSGlobalLootModifier {
    private RSGlobalLootModifier() {}

    public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, RepurposedStructures.MODID);

    public static final RegistryObject<StructureModdedLootImporter.Serializer> STRUCTURE_MODDED_LOOT_IMPORTER = GLM.register("import_structure_modded_loot", StructureModdedLootImporter.Serializer::new);

    public static void registerLootData()
    {
        Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(RepurposedStructures.MODID, "detect_rs_loot_tables"), DetectRSLootTables.DETECT_RS_LOOT_TABLES);
    }
}
