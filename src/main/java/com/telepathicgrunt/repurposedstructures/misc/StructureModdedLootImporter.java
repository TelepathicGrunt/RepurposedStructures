package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.mixin.BuilderAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.LootContextAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.LootManagerAccessor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import java.util.*;
import java.util.stream.Collectors;

public class StructureModdedLootImporter {

    // Cache the reverse lookup for what loottable goes with what identifier
    private static final Map<LootTable, Identifier> REVERSED_TABLES = new HashMap<>();

    private static Set<Identifier> BLACKLISTED_LOOTTABLES;

    // Need to map loottables by hand to the vanilla structure that our structure is based on. (usually...)
    private static final Map<Identifier, Identifier> TABLE_IMPORTS = createMap();
    private static Map<Identifier, Identifier> createMap() {
        Map<Identifier, Identifier> tableMap = new HashMap<>();
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mineshaft/birch"), new Identifier("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mineshaft/crimson"), new Identifier("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mineshaft/desert"), new Identifier("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mineshaft/end"), new Identifier("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mineshaft/icy"), new Identifier("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mineshaft/jungle"), new Identifier("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mineshaft/nether"), new Identifier("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mineshaft/ocean"), new Identifier("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mineshaft/savanna"), new Identifier("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mineshaft/stone"), new Identifier("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mineshaft/swamp_dark_forest"), new Identifier("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mineshaft/taiga"), new Identifier("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mineshaft/warped"), new Identifier("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/cities/nether"), new Identifier("minecraft:chests/bastion_treasure")); // new Identifier("minecraft:chests/end_city_treasure"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/dungeon/badlands"), new Identifier("minecraft:chests/simple_dungeon"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/dungeon/dark_forest"), new Identifier("minecraft:chests/simple_dungeon"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/dungeon/desert"), new Identifier("minecraft:chests/simple_dungeon"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/dungeon/end"), new Identifier("minecraft:chests/simple_dungeon"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/dungeon/jungle"), new Identifier("minecraft:chests/simple_dungeon"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/dungeon/mushroom"), new Identifier("minecraft:chests/simple_dungeon"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/dungeon/nether"), new Identifier("minecraft:chests/simple_dungeon"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/dungeon/ocean"), new Identifier("minecraft:chests/simple_dungeon"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/dungeon/snow"), new Identifier("minecraft:chests/simple_dungeon"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/dungeon/swamp"), new Identifier("minecraft:chests/simple_dungeon"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/fortress/jungle_center_chest"), new Identifier("minecraft:chests/stronghold_crossing")); // new Identifier("minecraft:chests/nether_bridge"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/fortress/jungle_hallway_chest"), new Identifier("minecraft:chests/stronghold_corridor")); // new Identifier("minecraft:chests/nether_bridge"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/fortress/jungle_shrine_chest"), new Identifier("minecraft:chests/stronghold_crossing")); // new Identifier("minecraft:chests/nether_bridge"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/igloos/grassy_chest"), new Identifier("minecraft:chests/igloo_chest"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/igloos/stone_chest"), new Identifier("minecraft:chests/igloo_chest"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mansion/birch"), new Identifier("minecraft:chests/woodland_mansion"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mansion/desert"), new Identifier("minecraft:chests/woodland_mansion"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mansion/jungle"), new Identifier("minecraft:chests/woodland_mansion"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mansion/oak"), new Identifier("minecraft:chests/woodland_mansion"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mansion/savanna"), new Identifier("minecraft:chests/woodland_mansion"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mansion/snowy"), new Identifier("minecraft:chests/woodland_mansion"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/mansion/taiga"), new Identifier("minecraft:chests/woodland_mansion"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/outpost/badlands_chest"), new Identifier("minecraft:chests/pillager_outpost"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/outpost/birch_chest"), new Identifier("minecraft:chests/pillager_outpost"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/outpost/crimson_chest"), new Identifier("minecraft:chests/pillager_outpost"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/outpost/desert_chest"), new Identifier("minecraft:chests/pillager_outpost"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/outpost/giant_tree_taiga_chest"), new Identifier("minecraft:chests/pillager_outpost"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/outpost/icy_chest"), new Identifier("minecraft:chests/pillager_outpost"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/outpost/nether_brick_chest"), new Identifier("minecraft:chests/pillager_outpost"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/outpost/oak_chest"), new Identifier("minecraft:chests/pillager_outpost"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/outpost/snowy_chest"), new Identifier("minecraft:chests/pillager_outpost"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/outpost/taiga_chest"), new Identifier("minecraft:chests/pillager_outpost"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/outpost/warped_chest"), new Identifier("minecraft:chests/pillager_outpost"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/pyramid/badlands_chest"), new Identifier("minecraft:chests/desert_pyramid"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/pyramid/nether_chest"), new Identifier("minecraft:chests/desert_pyramid"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/pyramid/snowy_chest"), new Identifier("minecraft:chests/desert_pyramid"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/ruin/nether"), new Identifier("minecraft:chests/bastion_other")); // new Identifier("minecraft:chests/underwater_ruin_big"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/ruined_portal/large_portal_chest"), new Identifier("minecraft:chests/ruined_portal"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/ruined_portal/small_portal_chest"), new Identifier("minecraft:chests/ruined_portal"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/shipwreck/crimson/map_chest"), new Identifier("minecraft:chests/shipwreck_map"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/shipwreck/crimson/supply_chest"), new Identifier("minecraft:chests/shipwreck_supply"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/shipwreck/crimson/treasure_chest"), new Identifier("minecraft:chests/shipwreck_treasure"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/shipwreck/end/map_chest"), new Identifier("minecraft:chests/shipwreck_map"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/shipwreck/end/supply_chest"), new Identifier("minecraft:chests/shipwreck_supply"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/shipwreck/end/treasure_chest"), new Identifier("minecraft:chests/end_city_treasure")); //  new Identifier("minecraft:chests/shipwreck_treasure"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/shipwreck/warped/map_chest"), new Identifier("minecraft:chests/shipwreck_map"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/shipwreck/warped/supply_chest"), new Identifier("minecraft:chests/shipwreck_supply"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/shipwreck/warped/treasure_chest"), new Identifier("minecraft:chests/shipwreck_treasure"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/shipwreck/nether_bricks/treasure_chest"), new Identifier("minecraft:chests/shipwreck_treasure"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/stronghold/nether_storage_room"), new Identifier("minecraft:chests/stronghold_crossing"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/stronghold/nether_hallway"), new Identifier("minecraft:chests/stronghold_corridor"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/temple/nether_basalt_chest"), new Identifier("minecraft:chests/nether_bridge")); // new Identifier("minecraft:chests/jungle_temple"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/temple/nether_crimson_chest"), new Identifier("minecraft:chests/nether_bridge")); // new Identifier("minecraft:chests/jungle_temple"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/temple/nether_soul_chest"), new Identifier("minecraft:chests/nether_bridge")); // new Identifier("minecraft:chests/jungle_temple"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/temple/nether_warped_chest"), new Identifier("minecraft:chests/nether_bridge")); //  new Identifier("minecraft:chests/jungle_temple"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/temple/nether_warped_trapped_chest"), new Identifier("minecraft:chests/nether_bridge")); // new Identifier("minecraft:chests/jungle_temple"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/temple/nether_wasteland_chest"), new Identifier("minecraft:chests/nether_bridge")); // new Identifier("minecraft:chests/jungle_temple"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/temple/nether_basalt_dispenser"), new Identifier("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/temple/nether_crimson_dispenser"), new Identifier("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/temple/nether_soul_dispenser"), new Identifier("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/temple/nether_warped_dispenser"), new Identifier("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/temple/nether_wasteland_dispenser"), new Identifier("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_badands_house"), new Identifier("minecraft:chests/village/village_desert_house"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_birch_house"), new Identifier("minecraft:chests/village/village_plains_house"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_dark_forest_house"), new Identifier("minecraft:chests/village/village_plains_house"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_giant_taiga_house"), new Identifier("minecraft:chests/village/village_taiga_house"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_jungle_house"), new Identifier("minecraft:chests/village/village_savanna_house"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_mountains_house"), new Identifier("minecraft:chests/village/village_snowy_house"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_oak_house"), new Identifier("minecraft:chests/village/village_plains_house"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_swamp_house"), new Identifier("minecraft:chests/village/village_plains_house"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_crimson_cartographer"), new Identifier("minecraft:chests/village/village_cartographer"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_crimson_fisher"), new Identifier("minecraft:chests/village/village_fisher"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_crimson_tanner"), new Identifier("minecraft:chests/village/village_tannery"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_crimson_weaponsmith"), new Identifier("minecraft:chests/village/village_weaponsmith"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_crimson_house"), new Identifier("minecraft:chests/village/village_desert_house"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_warped_cartographer"), new Identifier("minecraft:chests/village/village_cartographer"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_warped_fisher"), new Identifier("minecraft:chests/village/village_fisher"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_warped_tanner"), new Identifier("minecraft:chests/village/village_tannery"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_warped_weaponsmith"), new Identifier("minecraft:chests/village/village_weaponsmith"));
        tableMap.put(new Identifier(RepurposedStructures.MODID, "chests/village/village_warped_house"), new Identifier("minecraft:chests/village/village_desert_house"));
        return tableMap;
    }



    public static List<ItemStack> checkAndGetModifiedLoot(LootContext context, LootTable currentLootTable, List<ItemStack> originalLoot){
        if(RepurposedStructures.RSAllConfig.RSMainConfig.importModdedItems)
        {
            // Cache the result of the loottable to the id into our own map.
            Identifier lootTableID = REVERSED_TABLES.computeIfAbsent(
                    currentLootTable,
                    // Will iterate lazily through loottable map for which identifier gives this loottable and return the result
                    (lootTable) -> ((LootManagerAccessor)context.getWorld().getServer().getLootManager()).rs_getTables()
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

    public static List<ItemStack> modifyLootTables(LootContext context, Identifier lootTableID, List<ItemStack> originalLoot)
    {
        Identifier tableToImportLoot = TABLE_IMPORTS.get(lootTableID);
        if(tableToImportLoot == null) return originalLoot; // Safety net

        // Generate random loot that would've been in vanilla chests. (Need to make new context or else we recursively call ourselves infinitely)
        LootContext newContext = copyLootContextWithNewQueryID(context);
        List<ItemStack> newlyGeneratedLoot = newContext.getSupplier(tableToImportLoot).generateLoot(newContext);

        // Remove all vanilla loot so we only have modded loot
        newlyGeneratedLoot.removeIf(itemStack -> {
            RegistryKey<Item> itemKey = Registry.ITEM.getKey(itemStack.getItem()).orElse(null);
            return itemKey != null && itemKey.getValue().getNamespace().equals("minecraft");
        });

        // Add modded loot to my structure's chests
        originalLoot.addAll(newlyGeneratedLoot);
        return originalLoot;
    }

    private static LootContext copyLootContextWithNewQueryID(LootContext oldLootContext){
        LootContext.Builder newContextBuilder = new LootContext.Builder(oldLootContext.getWorld())
                .random(oldLootContext.getRandom())
                .luck(oldLootContext.getLuck());

        ((BuilderAccessor)newContextBuilder).rs_setDrops(((LootContextAccessor)oldLootContext).rs_getDrops());
        ((BuilderAccessor)newContextBuilder).rs_setParameters(((LootContextAccessor)oldLootContext).rs_getParameters());
        return newContextBuilder.build(LootContextTypes.CHEST);
    }

    private static boolean isInBlacklist(Identifier lootTableID){
        if(BLACKLISTED_LOOTTABLES == null){
            String cleanedBlacklist = RepurposedStructures.RSAllConfig.RSMainConfig.blacklistedRSLoottablesFromImportingModdedItems.replace(" ", "");

            if(cleanedBlacklist.equals("")){
                BLACKLISTED_LOOTTABLES = new HashSet<>(); // make empty set instead of ["minecraft:"].
            }
            else {
                BLACKLISTED_LOOTTABLES =
                        Arrays.stream(cleanedBlacklist.split(","))
                            .map(Identifier::new)
                            .collect(Collectors.toSet());
            }
        }

        return BLACKLISTED_LOOTTABLES.contains(lootTableID);
    }
}
