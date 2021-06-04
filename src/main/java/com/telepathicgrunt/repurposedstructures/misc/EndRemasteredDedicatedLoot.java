package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndRemasteredDedicatedLoot {
    public static boolean isEndRemasteredOn = false;
    private static final Map<ResourceLocation, ResourceLocation> END_REMASTERED_DEDICATED_TABLE_IMPORTS = createEndRemasteredMap();
    private static Map<ResourceLocation, ResourceLocation> createEndRemasteredMap() {
        Map<ResourceLocation, ResourceLocation> tableMap = new HashMap<>();
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/birch"), new ResourceLocation("endrem:external_chests/lost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/savanna"), new ResourceLocation("endrem:external_chests/lost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/stone"), new ResourceLocation("endrem:external_chests/lost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/swamp"), new ResourceLocation("endrem:external_chests/lost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/dark_forest"), new ResourceLocation("endrem:external_chests/lost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/taiga"), new ResourceLocation("endrem:external_chests/lost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/igloos/stone_chest"), new ResourceLocation("endrem:external_chests/lost"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/fortress/jungle_shrine_chest"), new ResourceLocation("endrem:external_chests/rogue"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/jungle"), new ResourceLocation("endrem:external_chests/rogue"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/jungle_chest"), new ResourceLocation("endrem:external_chests/rogue"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramid/jungle_chest"), new ResourceLocation("endrem:external_chests/rogue"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/desert_chest"), new ResourceLocation("endrem:external_chests/old"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramid/badlands_chest"), new ResourceLocation("endrem:external_chests/old"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/desert"), new ResourceLocation("endrem:external_chests/old"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshaft/icy"), new ResourceLocation("endrem:external_chests/cold"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/icy_chest"), new ResourceLocation("endrem:external_chests/cold"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramid/snowy_chest"), new ResourceLocation("endrem:external_chests/cold"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramid/icy_chest"), new ResourceLocation("endrem:external_chests/cold"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/snowy_chest"), new ResourceLocation("endrem:external_chests/cold"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/badlands_chest"), new ResourceLocation("endrem:external_chests/corrupted"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/birch_chest"), new ResourceLocation("endrem:external_chests/corrupted"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/giant_tree_taiga_chest"), new ResourceLocation("endrem:external_chests/corrupted"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/oak_chest"), new ResourceLocation("endrem:external_chests/corrupted"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outpost/taiga_chest"), new ResourceLocation("endrem:external_chests/corrupted"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramid/nether_chest"), new ResourceLocation("endrem:external_chests/nether"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_basalt_chest"), new ResourceLocation("endrem:external_chests/nether"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_crimson_chest"), new ResourceLocation("endrem:external_chests/nether"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_soul_chest"), new ResourceLocation("endrem:external_chests/nether"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_warped_chest"), new ResourceLocation("endrem:external_chests/nether"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temple/nether_wasteland_chest"), new ResourceLocation("endrem:external_chests/nether"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwreck/crimson/treasure_chest"), new ResourceLocation("endrem:external_chests/black"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwreck/nether_bricks/treasure_chest"), new ResourceLocation("endrem:external_chests/black"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwreck/warped/treasure_chest"), new ResourceLocation("endrem:external_chests/black"));

        return tableMap;
    }

    protected static void handleDedicatedModCompat(List<ItemStack> currentLoot, LootContext oldLootContext){
        // Remove their eyes from the default importing and instead, import the correct eyes they really want for this structure.
        if(isEndRemasteredOn){
            // Remove incorrect End Remastered loot
            currentLoot.removeIf(itemStack -> itemStack.getItem().getRegistryName().getNamespace().equals("endrem"));

            // Get correct pool they want us to use
            ResourceLocation tableToImportLoot = END_REMASTERED_DEDICATED_TABLE_IMPORTS.get(oldLootContext.getQueriedLootTableId());
            if(tableToImportLoot == null) return; // No entry found

            // Generate End Remastered's dedicated loot
            LootContext newContext = StructureModdedLootImporter.copyLootContextWithNewQueryID(oldLootContext, tableToImportLoot);
            List<ItemStack> endRemasteredLoot = oldLootContext.getLootTable(tableToImportLoot).getRandomItems(newContext);

            currentLoot.addAll(endRemasteredLoot);
        }
    }
}
