package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.resources.BuilderAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.resources.LootContextAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.resources.LootManagerAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class StructureModdedLootImporter {

    // Cache the reverse lookup for what loottable goes with what identifier
    private static final Map<LootTable, ResourceLocation> REVERSED_TABLES = new HashMap<>();

    private static Set<ResourceLocation> BLACKLISTED_LOOTTABLES;

    // Need to map loottables by hand to the vanilla structure that our structure is based on. (usually...)
    private static final Map<ResourceLocation, ResourceLocation> TABLE_IMPORTS = createMap();
    private static Map<ResourceLocation, ResourceLocation> createMap() {
        Map<ResourceLocation, ResourceLocation> tableMap = new HashMap<>();
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/birch"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/crimson"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/desert"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/end"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/icy"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/jungle"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/nether"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/ocean"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/savanna"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/stone"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/dark_forest"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/swamp"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/taiga"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/warped"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/bastions/underground/treasure"), new ResourceLocation("minecraft:chests/stronghold_corridor")); // new Identifier("minecraft:chests/bastion_treasure")
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/bastions/underground/bridge"), new ResourceLocation("minecraft:chests/stronghold_corridor")); // new Identifier("minecraft:chests/bastion_bridge")
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/bastions/underground/other"), new ResourceLocation("minecraft:chests/stronghold_corridor")); // new Identifier("minecraft:chests/bastion_other")
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/bastions/underground/skeleton_horse_stable"), new ResourceLocation("minecraft:chests/stronghold_corridor")); // new Identifier("minecraft:chests/bastion_hoglin_stable")
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/cities/nether"), new ResourceLocation("minecraft:chests/bastion_treasure")); // new Identifier("minecraft:chests/end_city_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/badlands"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/dark_forest"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/desert"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/end"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/jungle"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/mushroom"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/nether"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/ocean"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/snow"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/swamp"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/fortresses/jungle_center"), new ResourceLocation("minecraft:chests/stronghold_crossing")); // new Identifier("minecraft:chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/fortresses/jungle_hallway"), new ResourceLocation("minecraft:chests/stronghold_corridor")); // new Identifier("minecraft:chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/fortresses/jungle_shrine"), new ResourceLocation("minecraft:chests/stronghold_crossing")); // new Identifier("minecraft:chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/igloos/grassy"), new ResourceLocation("minecraft:chests/igloo_chest"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/igloos/stone"), new ResourceLocation("minecraft:chests/igloo_chest"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansions/birch"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansions/desert"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansions/jungle"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansions/oak"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansions/savanna"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansions/snowy"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansions/taiga"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/badlands"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/birch"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/crimson"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/desert"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/giant_tree_taiga"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/icy"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/jungle"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/nether_brick"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/oak"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/snowy"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/taiga"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/warped"), new ResourceLocation("minecraft:chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "shulker_boxes/outposts/end"), new ResourceLocation("minecraft:chests/end_city_treasure")); // "minecraft:chests/pillager_outpost"
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/badlands"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/nether"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/snowy"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/icy"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/jungle"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/mushroom"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/ocean"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/giant_tree_taiga"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/flower_forest"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/end"), new ResourceLocation("minecraft:chests/end_city_treasure")); // "minecraft:chests/desert_pyramid"
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruins/nether"), new ResourceLocation("minecraft:chests/bastion_other")); // new Identifier("minecraft:chests/underwater_ruin_big"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruins/land_hot/large"), new ResourceLocation("minecraft:chests/village/village_desert_house"));  // new Identifier("minecraft:chests/underwater_ruin_big")
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruins/land_hot/small"), new ResourceLocation("minecraft:chests/village/village_desert_house"));  // new Identifier("minecraft:chests/underwater_ruin_small")
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruins/land_warm/large"), new ResourceLocation("minecraft:chests/village/village_plains_house"));  // new Identifier("minecraft:chests/underwater_ruin_big")
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruins/land_warm/small"), new ResourceLocation("minecraft:chests/village/village_plains_house"));  // new Identifier("minecraft:chests/underwater_ruin_small")
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruined_portals/large_portal"), new ResourceLocation("minecraft:chests/ruined_portal"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruined_portals/small_portal"), new ResourceLocation("minecraft:chests/ruined_portal"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/crimson/map"), new ResourceLocation("minecraft:chests/shipwreck_map"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/crimson/supply"), new ResourceLocation("minecraft:chests/shipwreck_supply"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/crimson/treasure"), new ResourceLocation("minecraft:chests/shipwreck_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/end/map"), new ResourceLocation("minecraft:chests/shipwreck_map"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/end/supply"), new ResourceLocation("minecraft:chests/shipwreck_supply"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/end/treasure"), new ResourceLocation("minecraft:chests/end_city_treasure")); //  new Identifier("minecraft:chests/shipwreck_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/warped/map"), new ResourceLocation("minecraft:chests/shipwreck_map"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/warped/supply"), new ResourceLocation("minecraft:chests/shipwreck_supply"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/warped/treasure"), new ResourceLocation("minecraft:chests/shipwreck_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/nether_bricks/treasure"), new ResourceLocation("minecraft:chests/shipwreck_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/strongholds/nether_storage_room"), new ResourceLocation("minecraft:chests/stronghold_crossing"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/strongholds/nether_hallway"), new ResourceLocation("minecraft:chests/stronghold_corridor"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/strongholds/nether_library"), new ResourceLocation("minecraft:chests/stronghold_library"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temples/nether_basalt"), new ResourceLocation("minecraft:chests/nether_bridge")); // new Identifier("minecraft:chests/jungle_temple"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temples/nether_crimson"), new ResourceLocation("minecraft:chests/nether_bridge")); // new Identifier("minecraft:chests/jungle_temple"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temples/nether_soul"), new ResourceLocation("minecraft:chests/nether_bridge")); // new Identifier("minecraft:chests/jungle_temple"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temples/nether_warped"), new ResourceLocation("minecraft:chests/nether_bridge")); //  new Identifier("minecraft:chests/jungle_temple"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "trapped_chests/temples/nether_warped"), new ResourceLocation("minecraft:chests/nether_bridge")); // new Identifier("minecraft:chests/jungle_temple"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temples/nether_wasteland"), new ResourceLocation("minecraft:chests/nether_bridge")); // new Identifier("minecraft:chests/jungle_temple"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "dispensers/temples/nether_basalt"), new ResourceLocation("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "dispensers/temples/nether_crimson"), new ResourceLocation("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "dispensers/temples/nether_soul"), new ResourceLocation("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "dispensers/temples/nether_warped"), new ResourceLocation("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "dispensers/temples/nether_wasteland"), new ResourceLocation("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/badlands_house"), new ResourceLocation("minecraft:chests/village/village_desert_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/birch_house"), new ResourceLocation("minecraft:chests/village/village_plains_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/dark_forest_house"), new ResourceLocation("minecraft:chests/village/village_plains_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/giant_taiga_house"), new ResourceLocation("minecraft:chests/village/village_taiga_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/jungle_house"), new ResourceLocation("minecraft:chests/village/village_savanna_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/mountains_house"), new ResourceLocation("minecraft:chests/village/village_snowy_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/oak_house"), new ResourceLocation("minecraft:chests/village/village_plains_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/swamp_house"), new ResourceLocation("minecraft:chests/village/village_plains_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/crimson_cartographer"), new ResourceLocation("minecraft:chests/village/village_cartographer"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/crimson_fisher"), new ResourceLocation("minecraft:chests/village/village_fisher"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/crimson_tanner"), new ResourceLocation("minecraft:chests/village/village_tannery"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/crimson_weaponsmith"), new ResourceLocation("minecraft:chests/village/village_weaponsmith"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/crimson_house"), new ResourceLocation("minecraft:chests/village/village_desert_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/warped_cartographer"), new ResourceLocation("minecraft:chests/village/village_cartographer"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/warped_fisher"), new ResourceLocation("minecraft:chests/village/village_fisher"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/warped_tanner"), new ResourceLocation("minecraft:chests/village/village_tannery"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/warped_weaponsmith"), new ResourceLocation("minecraft:chests/village/village_weaponsmith"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/warped_house"), new ResourceLocation("minecraft:chests/village/village_desert_house"));
        return tableMap;
    }



    public static List<ItemStack> checkAndGetModifiedLoot(LootContext context, LootTable currentLootTable, List<ItemStack> originalLoot){
        if(RepurposedStructures.RSAllConfig.RSAllowDisallowConfig.importModdedItems)
        {
            // Cache the result of the loottable to the id into our own map.
            ResourceLocation lootTableID = REVERSED_TABLES.computeIfAbsent(
                    currentLootTable,
                    // Will iterate lazily through loottable map for which identifier gives this loottable and return the result
                    (lootTable) -> ((LootManagerAccessor)context.getLevel().getServer().getLootTables()).repurposedstructures_getTables()
                            .entrySet()
                            .stream()
                            .filter(entry -> lootTable.equals(entry.getValue()))
                            .map(Map.Entry::getKey)
                            .findFirst()
                            .orElse(null) // null should be ever returned as otherwise, that would be concerning...
            );

            if(lootTableID != null &&
                lootTableID.getNamespace().equals(RepurposedStructures.MODID) &&
                !isInBlacklist(lootTableID))
            {
                return StructureModdedLootImporter.modifyLootTables(context, lootTableID, originalLoot);
            }
        }

        return new ArrayList<>();
    }

    public static List<ItemStack> modifyLootTables(LootContext context, ResourceLocation lootTableID, List<ItemStack> originalLoot)
    {
        ResourceLocation tableToImportLoot = TABLE_IMPORTS.get(lootTableID);
        if(tableToImportLoot == null) return originalLoot; // Safety net

        // Generate random loot that would've been in vanilla chests. (Need to make new context or else we recursively call ourselves infinitely)
        LootContext newContext = copyLootContextWithNewQueryID(context);
        List<ItemStack> newlyGeneratedLoot = newContext.getLootTable(tableToImportLoot).getRandomItems(newContext);

        // Remove all vanilla loot so we only have modded loot
        newlyGeneratedLoot.removeIf(itemStack -> {
            ResourceKey<Item> itemKey = Registry.ITEM.getResourceKey(itemStack.getItem()).orElse(null);
            return itemKey != null && itemKey.location().getNamespace().equals("minecraft");
        });

        // Add modded loot to my structure's chests
        originalLoot.addAll(newlyGeneratedLoot);
        return originalLoot;
    }

    private static LootContext copyLootContextWithNewQueryID(LootContext oldLootContext){
        LootContext.Builder newContextBuilder = new LootContext.Builder(oldLootContext.getLevel())
                .withRandom(oldLootContext.getRandom())
                .withLuck(oldLootContext.getLuck());

        ((BuilderAccessor)newContextBuilder).repurposedstructures_setDynamicDrops(((LootContextAccessor)oldLootContext).repurposedstructures_getDynamicDrops());
        ((BuilderAccessor)newContextBuilder).repurposedstructures_setParams(((LootContextAccessor)oldLootContext).repurposedstructures_getParams());
        return newContextBuilder.create(LootContextParamSets.CHEST);
    }

    private static boolean isInBlacklist(ResourceLocation lootTableID){
        if(BLACKLISTED_LOOTTABLES == null){
            String cleanedBlacklist = RepurposedStructures.RSAllConfig.RSAllowDisallowConfig.blacklistedRSLoottablesFromImportingModdedItems.trim();

            if(cleanedBlacklist.equals("")){
                BLACKLISTED_LOOTTABLES = new HashSet<>(); // make empty set instead of ["minecraft:"].
            }
            else {
                BLACKLISTED_LOOTTABLES =
                        Arrays.stream(cleanedBlacklist.split(","))
                            .map(String::trim)
                            .map(ResourceLocation::new)
                            .collect(Collectors.toSet());
            }
        }

        return BLACKLISTED_LOOTTABLES.contains(lootTableID);
    }
}
