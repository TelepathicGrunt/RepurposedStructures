package com.telepathicgrunt.repurposedstructures.misc;

import com.google.gson.JsonObject;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StructureModdedLootImporter extends LootModifier {

    private static final Map<ResourceLocation, ResourceLocation> TABLE_IMPORTS = createMap();
    private static Map<ResourceLocation, ResourceLocation> createMap() {
        Map<ResourceLocation, ResourceLocation> tableMap = new HashMap<>();
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/birch"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/crimson"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/desert"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/end"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/icy"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/jungle"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/nether"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/ocean"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/savanna"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/stone"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/swamp_or_dark_forest"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/taiga"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/warped"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        return tableMap;
    }

    public StructureModdedLootImporter(final ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        ResourceLocation tableToImportLoot = TABLE_IMPORTS.get(context.getQueriedLootTableId());
        if(tableToImportLoot == null) return generatedLoot; // Safety net

        // Generate random loot that would've been in vanilla chests
        List<ItemStack> newlyGeneratedLoot = context.getSupplier(tableToImportLoot).generate(context);

        // Remove all vanilla loot so we only have modded loot
        newlyGeneratedLoot.removeIf(itemStack -> itemStack.getItem().getRegistryName().getNamespace().equals("minecraft"));

        // Add modded loot to my structure's chests
        generatedLoot.addAll(newlyGeneratedLoot);
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<StructureModdedLootImporter> {
        @Override
        public StructureModdedLootImporter read(ResourceLocation location, JsonObject object, ILootCondition[] conditions) {
             return new StructureModdedLootImporter(conditions);
        }

        @Override
        public JsonObject write(StructureModdedLootImporter instance) {
            return this.makeConditions(instance.conditions);
        }
    }
}