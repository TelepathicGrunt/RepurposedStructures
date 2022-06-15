package com.telepathicgrunt.repurposedstructures.misc.lootmanager;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.configs.RSModdedLootConfig;
import com.telepathicgrunt.repurposedstructures.mixin.resources.BuilderAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.resources.LootContextAccessor;
import com.telepathicgrunt.repurposedstructures.mixin.resources.LootManagerAccessor;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class StructureModdedLootImporter {
    private StructureModdedLootImporter() {}

    // Cache the reverse lookup for what loottable goes with what identifier
    private static final Map<LootTable, ResourceLocation> REVERSED_TABLES = new HashMap<>();

    private static Set<ResourceLocation> BLACKLISTED_LOOTTABLES;

    // Need to map loottables by hand to the vanilla structure that our structure is based on. (usually...)
    private static final Map<ResourceLocation, ResourceLocation> TABLE_IMPORTS = createMap();
    private static Map<ResourceLocation, ResourceLocation> createMap() {
        Map<ResourceLocation, ResourceLocation> tableMap = new HashMap<>();
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/cities/nether"), new ResourceLocation("minecraft:chests/bastion_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/cities/overworld"), new ResourceLocation("minecraft:chests/village/village_plains_house"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/bastions/underground/treasure"), new ResourceLocation("minecraft:chests/stronghold_crossing"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/bastions/underground/bridge"), new ResourceLocation("minecraft:chests/stronghold_corridor"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/bastions/underground/other"), new ResourceLocation("minecraft:chests/stronghold_corridor"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/bastions/underground/skeleton_horse_stable"), new ResourceLocation("minecraft:chests/stronghold_corridor"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/badlands"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/dark_forest"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/deep"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/desert"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/icy"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/jungle"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/mushroom"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/nether"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/ocean"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/snow"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/swamp"), new ResourceLocation("minecraft:chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "shulker_boxes/dungeons/end"), new ResourceLocation("minecraft:chests/simple_dungeon"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/fortresses/jungle_center"), new ResourceLocation("minecraft:chests/stronghold_crossing"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/fortresses/jungle_hallway"), new ResourceLocation("minecraft:chests/stronghold_corridor"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/fortresses/jungle_shrine"), new ResourceLocation("minecraft:chests/stronghold_crossing"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/igloos/grassy"), new ResourceLocation("minecraft:chests/igloo_chest"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/igloos/stone"), new ResourceLocation("minecraft:chests/igloo_chest"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/igloos/mushroom"), new ResourceLocation("minecraft:chests/igloo_chest"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansions/birch"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansions/desert"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansions/jungle"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansions/oak"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansions/savanna"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansions/snowy"), new ResourceLocation("minecraft:chests/woodland_mansion"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mansions/taiga"), new ResourceLocation("minecraft:chests/woodland_mansion"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/birch"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/crimson"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/dark_forest"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/desert"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/end"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/icy"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/jungle"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/nether"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/ocean"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/savanna"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/stone"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/swamp"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/taiga"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/warped"), new ResourceLocation("minecraft:chests/abandoned_mineshaft"));

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
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "shulker_boxes/outposts/end"), new ResourceLocation("minecraft:chests/end_city_treasure"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/dark_forest"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/end"), new ResourceLocation("minecraft:chests/end_city_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/flower_forest"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/giant_tree_taiga"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/icy"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/jungle"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/mushroom"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/snowy"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "trapped_chests/pyramids/badlands"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "trapped_chests/pyramids/end"), new ResourceLocation("minecraft:chests/end_city_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "trapped_chests/pyramids/nether"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "trapped_chests/pyramids/ocean"), new ResourceLocation("minecraft:chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "dispensers/pyramids/dark_forest"), new ResourceLocation("minecraft:chests/desert_pyramid"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruined_portals/end/large_portal"), new ResourceLocation("minecraft:chests/ruined_portal"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruined_portals/end/small_portal"), new ResourceLocation("minecraft:chests/ruined_portal"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruins/land_hot/large"), new ResourceLocation("minecraft:chests/village/village_desert_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruins/land_hot/small"), new ResourceLocation("minecraft:chests/village/village_desert_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruins/land_warm/large"), new ResourceLocation("minecraft:chests/village/village_plains_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruins/land_warm/small"), new ResourceLocation("minecraft:chests/village/village_plains_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruins/land_cold/large"), new ResourceLocation("minecraft:chests/village/village_taiga_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruins/land_cold/small"), new ResourceLocation("minecraft:chests/village/village_taiga_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruins/land_icy/large"), new ResourceLocation("minecraft:chests/village/village_snowy_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruins/land_icy/small"), new ResourceLocation("minecraft:chests/village/village_snowy_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/ruins/nether"), new ResourceLocation("minecraft:chests/bastion_other"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/crimson/map"), new ResourceLocation("minecraft:chests/shipwreck_map"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/crimson/supply"), new ResourceLocation("minecraft:chests/shipwreck_supply"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/crimson/treasure"), new ResourceLocation("minecraft:chests/shipwreck_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/end/map"), new ResourceLocation("minecraft:chests/shipwreck_map"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/end/supply"), new ResourceLocation("minecraft:chests/shipwreck_supply"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/end/treasure"), new ResourceLocation("minecraft:chests/end_city_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/warped/map"), new ResourceLocation("minecraft:chests/shipwreck_map"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/warped/supply"), new ResourceLocation("minecraft:chests/shipwreck_supply"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/warped/treasure"), new ResourceLocation("minecraft:chests/shipwreck_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/nether_bricks/treasure"), new ResourceLocation("minecraft:chests/shipwreck_treasure"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "shulker_boxes/strongholds/end_storage_room"), new ResourceLocation("minecraft:chests/end_city_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "shulker_boxes/strongholds/end_hallway"), new ResourceLocation("minecraft:chests/end_city_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "shulker_boxes/strongholds/end_library"), new ResourceLocation("minecraft:chests/end_city_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/strongholds/nether_storage_room"), new ResourceLocation("minecraft:chests/stronghold_crossing"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/strongholds/nether_hallway"), new ResourceLocation("minecraft:chests/stronghold_corridor"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/strongholds/nether_library"), new ResourceLocation("minecraft:chests/stronghold_library"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temples/basalt"), new ResourceLocation("minecraft:chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temples/crimson"), new ResourceLocation("minecraft:chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temples/soul"), new ResourceLocation("minecraft:chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temples/warped"), new ResourceLocation("minecraft:chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temples/wasteland"), new ResourceLocation("minecraft:chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "trapped_chests/temples/warped"), new ResourceLocation("minecraft:chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "dispensers/temples/basalt"), new ResourceLocation("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "dispensers/temples/crimson"), new ResourceLocation("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "dispensers/temples/soul"), new ResourceLocation("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "dispensers/temples/warped"), new ResourceLocation("minecraft:chests/jungle_temple_dispenser"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "dispensers/temples/wasteland"), new ResourceLocation("minecraft:chests/jungle_temple_dispenser"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/badlands_house"), new ResourceLocation("minecraft:chests/village/village_desert_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/birch_house"), new ResourceLocation("minecraft:chests/village/village_plains_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/dark_forest_house"), new ResourceLocation("minecraft:chests/village/village_plains_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/giant_taiga_house"), new ResourceLocation("minecraft:chests/village/village_taiga_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/jungle_house"), new ResourceLocation("minecraft:chests/village/village_savanna_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/mountains_house"), new ResourceLocation("minecraft:chests/village/village_snowy_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/mushroom_house"), new ResourceLocation("minecraft:chests/village/village_plains_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/oak_house"), new ResourceLocation("minecraft:chests/village/village_plains_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/swamp_house"), new ResourceLocation("minecraft:chests/village/village_plains_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/crimson_cartographer"), new ResourceLocation("minecraft:chests/village/village_cartographer"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/crimson_fisher"), new ResourceLocation("minecraft:chests/village/village_fisher"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/crimson_tannery"), new ResourceLocation("minecraft:chests/village/village_tannery"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/crimson_weaponsmith"), new ResourceLocation("minecraft:chests/village/village_weaponsmith"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/crimson_house"), new ResourceLocation("minecraft:chests/village/village_desert_house"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/warped_cartographer"), new ResourceLocation("minecraft:chests/village/village_cartographer"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/warped_fisher"), new ResourceLocation("minecraft:chests/village/village_fisher"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/warped_tannery"), new ResourceLocation("minecraft:chests/village/village_tannery"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/warped_weaponsmith"), new ResourceLocation("minecraft:chests/village/village_weaponsmith"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/villages/warped_house"), new ResourceLocation("minecraft:chests/village/village_desert_house"));

        // For Better Strongholds compat datapack
        if(FabricLoader.getInstance().isModLoaded("betterstrongholds")) {
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/end/armoury"), new ResourceLocation("minecraft:chests/end_city_treasure"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/end/common"), new ResourceLocation("minecraft:chests/end_city_treasure"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/end/crypt"), new ResourceLocation("minecraft:chests/end_city_treasure"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/end/grand_library"), new ResourceLocation("minecraft:chests/end_city_treasure"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/end/library_md"), new ResourceLocation("minecraft:chests/end_city_treasure"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/end/mess"), new ResourceLocation("minecraft:chests/end_city_treasure"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/end/prison_lg"), new ResourceLocation("minecraft:chests/end_city_treasure"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/end/trap"), new ResourceLocation("minecraft:chests/end_city_treasure"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/end/treasure"), new ResourceLocation("minecraft:chests/end_city_treasure"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/nether/common"), new ResourceLocation("minecraft:chests/stronghold_crossing"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/nether/mess"), new ResourceLocation("minecraft:chests/stronghold_crossing"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/nether/armoury"), new ResourceLocation("minecraft:chests/stronghold_corridor"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/nether/crypt"), new ResourceLocation("minecraft:chests/stronghold_corridor"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/nether/prison_lg"), new ResourceLocation("minecraft:chests/stronghold_corridor"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/nether/trap"), new ResourceLocation("minecraft:chests/stronghold_corridor"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/nether/treasure"), new ResourceLocation("minecraft:chests/stronghold_corridor"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/nether/grand_library"), new ResourceLocation("minecraft:chests/stronghold_library"));
            tableMap.put(new ResourceLocation("betterstrongholds", "chests/nether/library_md"), new ResourceLocation("minecraft:chests/stronghold_library"));
        }
        return tableMap;
    }



    public static List<ItemStack> checkAndGetModifiedLoot(LootContext context, LootTable currentLootTable, List<ItemStack> originalLoot) {
        if(RSModdedLootConfig.importModdedItems) {
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

            if(lootTableID != null && !isInBlacklist(lootTableID)) {
                return StructureModdedLootImporter.modifyLootTables(context, lootTableID, originalLoot);
            }
        }

        return new ArrayList<>();
    }

    public static List<ItemStack> modifyLootTables(LootContext context, ResourceLocation lootTableID, List<ItemStack> originalLoot) {
        ResourceLocation tableToImportLoot = TABLE_IMPORTS.get(lootTableID);
        if(tableToImportLoot == null) return originalLoot; // Safety net

        // Generate random loot that would've been in vanilla chests. (Need to make new context or else we recursively call ourselves infinitely)
        LootContext newContext = copyLootContext(context);
        List<ItemStack> newlyGeneratedLoot = newContext.getLootTable(tableToImportLoot).getRandomItems(newContext);

        // Remove all vanilla loot so we only have modded loot
        newlyGeneratedLoot.removeIf(itemStack -> {
            ResourceKey<Item> itemKey = Registry.ITEM.getResourceKey(itemStack.getItem()).orElse(null);
            return itemKey != null && itemKey.location().getNamespace().equals("minecraft");
        });

        // Intercept and modify the loot based on other mods being on
        EndRemasteredDedicatedLoot.handleDedicatedModCompat(newlyGeneratedLoot, lootTableID, context);

        // Add modded loot to my structure's chests
        originalLoot.addAll(newlyGeneratedLoot);
        return originalLoot;
    }

    protected static LootContext copyLootContext(LootContext oldLootContext) {
        LootContext.Builder newContextBuilder = new LootContext.Builder(oldLootContext.getLevel())
                .withRandom(oldLootContext.getRandom())
                .withLuck(oldLootContext.getLuck());

        ((BuilderAccessor)newContextBuilder).repurposedstructures_setDynamicDrops(((LootContextAccessor)oldLootContext).repurposedstructures_getDynamicDrops());
        ((BuilderAccessor)newContextBuilder).repurposedstructures_setParams(((LootContextAccessor)oldLootContext).repurposedstructures_getParams());
        return newContextBuilder.create(LootContextParamSets.CHEST);
    }

    private static boolean isInBlacklist(ResourceLocation lootTableID) {
        if(BLACKLISTED_LOOTTABLES == null) {
            String cleanedBlacklist = RSModdedLootConfig.blacklistedRSLoottablesFromImportingModdedItems.trim();

            if(cleanedBlacklist.equals("")) {
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
