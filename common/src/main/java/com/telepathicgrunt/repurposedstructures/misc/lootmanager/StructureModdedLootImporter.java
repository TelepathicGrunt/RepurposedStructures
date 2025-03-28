package com.telepathicgrunt.repurposedstructures.misc.lootmanager;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.configs.RSMainModdedLootConfig;
import com.telepathicgrunt.repurposedstructures.services.PlatformService;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class StructureModdedLootImporter {

    // Need to map loottables by hand to the vanilla structure that our structure is based on. (usually...)
    public static final Map<ResourceKey<LootTable>, ResourceKey<LootTable>> TABLE_IMPORTS = createMap();
    private static Set<ResourceKey<LootTable>> BLACKLISTED_LOOTTABLES;

    public static Map<ResourceKey<LootTable>, ResourceKey<LootTable>> createMap() {
        Map<ResourceKey<LootTable>, ResourceKey<LootTable>> tableMap = new Object2ObjectOpenHashMap<>();
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/cities/nether"), generateKey("minecraft", "chests/bastion_treasure"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/cities/overworld"), generateKey("minecraft", "chests/village/village_plains_house"));

        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ancient_cities/ocean"), generateKey("minecraft", "chests/ancient_city"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ancient_cities/ocean_ice_box"), generateKey("minecraft", "chests/ancient_city_ice_box"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ancient_cities/nether"), generateKey("minecraft", "chests/ancient_city"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ancient_cities/nether_magma_box"), generateKey("minecraft", "chests/ancient_city_ice_box"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ancient_cities/end"), generateKey("minecraft", "chests/ancient_city"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ancient_cities/end_spawner_box"), generateKey("minecraft", "chests/ancient_city_ice_box"));

        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/bastions/underground/treasure"), generateKey("minecraft", "chests/stronghold_crossing"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/bastions/underground/bridge"), generateKey("minecraft", "chests/stronghold_corridor"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/bastions/underground/other"), generateKey("minecraft", "chests/stronghold_corridor"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/bastions/underground/skeleton_horse_stable"), generateKey("minecraft", "chests/stronghold_corridor"));

        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/dungeons/badlands"), generateKey("minecraft", "chests/simple_dungeon"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/dungeons/dark_forest"), generateKey("minecraft", "chests/simple_dungeon"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/dungeons/deep"), generateKey("minecraft", "chests/simple_dungeon"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/dungeons/desert"), generateKey("minecraft", "chests/simple_dungeon"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/dungeons/icy"), generateKey("minecraft", "chests/simple_dungeon"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/dungeons/jungle"), generateKey("minecraft", "chests/simple_dungeon"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/dungeons/mushroom"), generateKey("minecraft", "chests/simple_dungeon"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/dungeons/nether"), generateKey("minecraft", "chests/simple_dungeon"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/dungeons/ocean"), generateKey("minecraft", "chests/simple_dungeon"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/dungeons/snow"), generateKey("minecraft", "chests/simple_dungeon"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/dungeons/swamp"), generateKey("minecraft", "chests/simple_dungeon"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "shulker_boxes/dungeons/end"), generateKey("minecraft", "chests/simple_dungeon"));

        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/fortresses/jungle_center"), generateKey("minecraft", "chests/stronghold_crossing"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/fortresses/jungle_hallway"), generateKey("minecraft", "chests/stronghold_corridor"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/fortresses/jungle_shrine"), generateKey("minecraft", "chests/stronghold_crossing"));

        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/igloos/grassy"), generateKey("minecraft", "chests/igloo_chest"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/igloos/stone"), generateKey("minecraft", "chests/igloo_chest"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/igloos/mangrove"), generateKey("minecraft", "chests/igloo_chest"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/igloos/mushroom"), generateKey("minecraft", "chests/igloo_chest"));

        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mansions/birch"), generateKey("minecraft", "chests/woodland_mansion"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mansions/desert"), generateKey("minecraft", "chests/woodland_mansion"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mansions/jungle"), generateKey("minecraft", "chests/woodland_mansion"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mansions/mangrove"), generateKey("minecraft", "chests/woodland_mansion"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mansions/oak"), generateKey("minecraft", "chests/woodland_mansion"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mansions/savanna"), generateKey("minecraft", "chests/woodland_mansion"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mansions/snowy"), generateKey("minecraft", "chests/woodland_mansion"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mansions/taiga"), generateKey("minecraft", "chests/woodland_mansion"));

        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/basalt"), generateKey("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/birch"), generateKey("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/crimson"), generateKey("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/dark_forest"), generateKey("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/desert"), generateKey("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/end"), generateKey("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/icy"), generateKey("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/jungle"), generateKey("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/nether"), generateKey("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/ocean"), generateKey("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/savanna"), generateKey("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/soul"), generateKey("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/stone"), generateKey("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/swamp"), generateKey("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/taiga"), generateKey("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/mineshafts/warped"), generateKey("minecraft", "chests/abandoned_mineshaft"));

        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/basalt"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/badlands"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/birch"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/crimson"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/desert"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/giant_tree_taiga"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/icy"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/jungle"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/mangrove"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/nether_brick"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/oak"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/ocean"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/savanna"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/soul"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/snowy"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/taiga"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/outposts/warped"), generateKey("minecraft", "chests/pillager_outpost"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "shulker_boxes/outposts/end"), generateKey("minecraft", "chests/end_city_treasure"));

        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/pyramids/dark_forest"), generateKey("minecraft", "chests/desert_pyramid"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/pyramids/end"), generateKey("minecraft", "chests/end_city_treasure"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/pyramids/flower_forest"), generateKey("minecraft", "chests/desert_pyramid"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/pyramids/giant_tree_taiga"), generateKey("minecraft", "chests/desert_pyramid"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/pyramids/icy"), generateKey("minecraft", "chests/desert_pyramid"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/pyramids/jungle"), generateKey("minecraft", "chests/desert_pyramid"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/pyramids/mushroom"), generateKey("minecraft", "chests/desert_pyramid"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/pyramids/snowy"), generateKey("minecraft", "chests/desert_pyramid"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "trapped_chests/pyramids/badlands"), generateKey("minecraft", "chests/desert_pyramid"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "trapped_chests/pyramids/end"), generateKey("minecraft", "chests/end_city_treasure"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "trapped_chests/pyramids/nether"), generateKey("minecraft", "chests/desert_pyramid"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "trapped_chests/pyramids/ocean"), generateKey("minecraft", "chests/desert_pyramid"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "dispensers/pyramids/dark_forest"), generateKey("minecraft", "chests/desert_pyramid"));

        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ruined_portals/end/large_portal"), generateKey("minecraft", "chests/ruined_portal"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ruined_portals/end/small_portal"), generateKey("minecraft", "chests/ruined_portal"));

        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ruins/land_hot/large"), generateKey("minecraft", "chests/village/village_desert_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ruins/land_hot/small"), generateKey("minecraft", "chests/village/village_desert_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ruins/land_warm/large"), generateKey("minecraft", "chests/village/village_plains_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ruins/land_warm/small"), generateKey("minecraft", "chests/village/village_plains_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ruins/land_cold/large"), generateKey("minecraft", "chests/village/village_taiga_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ruins/land_cold/small"), generateKey("minecraft", "chests/village/village_taiga_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ruins/land_icy/large"), generateKey("minecraft", "chests/village/village_snowy_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ruins/land_icy/small"), generateKey("minecraft", "chests/village/village_snowy_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/ruins/nether"), generateKey("minecraft", "chests/bastion_other"));

        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/shipwrecks/crimson/map"), generateKey("minecraft", "chests/shipwreck_map"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/shipwrecks/crimson/supply"), generateKey("minecraft", "chests/shipwreck_supply"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/shipwrecks/crimson/treasure"), generateKey("minecraft", "chests/shipwreck_treasure"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/shipwrecks/end/map"), generateKey("minecraft", "chests/shipwreck_map"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/shipwrecks/end/supply"), generateKey("minecraft", "chests/shipwreck_supply"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/shipwrecks/end/treasure"), generateKey("minecraft", "chests/end_city_treasure"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/shipwrecks/warped/map"), generateKey("minecraft", "chests/shipwreck_map"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/shipwrecks/warped/supply"), generateKey("minecraft", "chests/shipwreck_supply"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/shipwrecks/warped/treasure"), generateKey("minecraft", "chests/shipwreck_treasure"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/shipwrecks/nether_bricks/treasure"), generateKey("minecraft", "chests/shipwreck_treasure"));

        tableMap.put(generateKey(RepurposedStructures.MODID, "shulker_boxes/strongholds/end_storage_room"), generateKey("minecraft", "chests/end_city_treasure"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "shulker_boxes/strongholds/end_hallway"), generateKey("minecraft", "chests/end_city_treasure"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "shulker_boxes/strongholds/end_library"), generateKey("minecraft", "chests/end_city_treasure"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/strongholds/nether_storage_room"), generateKey("minecraft", "chests/stronghold_crossing"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/strongholds/nether_hallway"), generateKey("minecraft", "chests/stronghold_corridor"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/strongholds/nether_library"), generateKey("minecraft", "chests/stronghold_library"));

        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/temples/basalt"), generateKey("minecraft", "chests/nether_bridge"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/temples/crimson"), generateKey("minecraft", "chests/nether_bridge"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/temples/soul"), generateKey("minecraft", "chests/nether_bridge"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/temples/warped"), generateKey("minecraft", "chests/nether_bridge"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/temples/wasteland"), generateKey("minecraft", "chests/nether_bridge"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/temples/taiga"), generateKey("minecraft", "chests/jungle_temple"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/temples/ocean"), generateKey("minecraft", "chests/jungle_temple"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "trapped_chests/temples/warped"), generateKey("minecraft", "chests/nether_bridge"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "dispensers/temples/basalt"), generateKey("minecraft", "chests/jungle_temple_dispenser"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "dispensers/temples/crimson"), generateKey("minecraft", "chests/jungle_temple_dispenser"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "dispensers/temples/soul"), generateKey("minecraft", "chests/jungle_temple_dispenser"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "dispensers/temples/warped"), generateKey("minecraft", "chests/jungle_temple_dispenser"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "dispensers/temples/wasteland"), generateKey("minecraft", "chests/jungle_temple_dispenser"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "dispensers/temples/taiga"), generateKey("minecraft", "chests/jungle_temple_dispenser"));

        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/badlands_house"), generateKey("minecraft", "chests/village/village_desert_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/bamboo_house"), generateKey("minecraft", "chests/village/village_savanna_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/birch_house"), generateKey("minecraft", "chests/village/village_plains_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/cherry_house"), generateKey("minecraft", "chests/village/village_plains_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/dark_forest_house"), generateKey("minecraft", "chests/village/village_plains_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/giant_taiga_house"), generateKey("minecraft", "chests/village/village_taiga_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/jungle_house"), generateKey("minecraft", "chests/village/village_savanna_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/mountains_house"), generateKey("minecraft", "chests/village/village_snowy_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/mushroom_house"), generateKey("minecraft", "chests/village/village_plains_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/oak_house"), generateKey("minecraft", "chests/village/village_plains_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/ocean_house"), generateKey("minecraft", "chests/village/village_plains_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/ocean_cartographer"), generateKey("minecraft", "chests/village/village_cartographer"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/swamp_house"), generateKey("minecraft", "chests/village/village_plains_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/crimson_cartographer"), generateKey("minecraft", "chests/village/village_cartographer"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/crimson_fisher"), generateKey("minecraft", "chests/village/village_fisher"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/crimson_tannery"), generateKey("minecraft", "chests/village/village_tannery"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/crimson_weaponsmith"), generateKey("minecraft", "chests/village/village_weaponsmith"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/crimson_house"), generateKey("minecraft", "chests/village/village_desert_house"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/warped_cartographer"), generateKey("minecraft", "chests/village/village_cartographer"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/warped_fisher"), generateKey("minecraft", "chests/village/village_fisher"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/warped_tannery"), generateKey("minecraft", "chests/village/village_tannery"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/warped_weaponsmith"), generateKey("minecraft", "chests/village/village_weaponsmith"));
        tableMap.put(generateKey(RepurposedStructures.MODID, "chests/villages/warped_house"), generateKey("minecraft", "chests/village/village_desert_house"));

        // For Better Strongholds compat datapack
        if(PlatformService.INSTANCE.isModLoaded("betterstrongholds")) {
            tableMap.put(generateKey("betterstrongholds", "chests/end/armoury"), generateKey("minecraft", "chests/end_city_treasure"));
            tableMap.put(generateKey("betterstrongholds", "chests/end/common"), generateKey("minecraft", "chests/end_city_treasure"));
            tableMap.put(generateKey("betterstrongholds", "chests/end/crypt"), generateKey("minecraft", "chests/end_city_treasure"));
            tableMap.put(generateKey("betterstrongholds", "chests/end/grand_library"), generateKey("minecraft", "chests/end_city_treasure"));
            tableMap.put(generateKey("betterstrongholds", "chests/end/library_md"), generateKey("minecraft", "chests/end_city_treasure"));
            tableMap.put(generateKey("betterstrongholds", "chests/end/mess"), generateKey("minecraft", "chests/end_city_treasure"));
            tableMap.put(generateKey("betterstrongholds", "chests/end/prison_lg"), generateKey("minecraft", "chests/end_city_treasure"));
            tableMap.put(generateKey("betterstrongholds", "chests/end/trap"), generateKey("minecraft", "chests/end_city_treasure"));
            tableMap.put(generateKey("betterstrongholds", "chests/end/treasure"), generateKey("minecraft", "chests/end_city_treasure"));
            tableMap.put(generateKey("betterstrongholds", "chests/nether/common"), generateKey("minecraft", "chests/stronghold_crossing"));
            tableMap.put(generateKey("betterstrongholds", "chests/nether/mess"), generateKey("minecraft", "chests/stronghold_crossing"));
            tableMap.put(generateKey("betterstrongholds", "chests/nether/armoury"), generateKey("minecraft", "chests/stronghold_corridor"));
            tableMap.put(generateKey("betterstrongholds", "chests/nether/crypt"), generateKey("minecraft", "chests/stronghold_corridor"));
            tableMap.put(generateKey("betterstrongholds", "chests/nether/prison_lg"), generateKey("minecraft", "chests/stronghold_corridor"));
            tableMap.put(generateKey("betterstrongholds", "chests/nether/trap"), generateKey("minecraft", "chests/stronghold_corridor"));
            tableMap.put(generateKey("betterstrongholds", "chests/nether/treasure"), generateKey("minecraft", "chests/stronghold_corridor"));
            tableMap.put(generateKey("betterstrongholds", "chests/nether/grand_library"), generateKey("minecraft", "chests/stronghold_library"));
            tableMap.put(generateKey("betterstrongholds", "chests/nether/library_md"), generateKey("minecraft", "chests/stronghold_library"));
        }
        return tableMap;
    }

    public static boolean isInBlacklist(ResourceKey<LootTable> lootTableID){
        if (BLACKLISTED_LOOTTABLES == null){
            String cleanedBlacklist = RSMainModdedLootConfig.blacklistedRSLoottablesFromImportingModdedItems.trim();

            if (cleanedBlacklist.isEmpty()){
                BLACKLISTED_LOOTTABLES = new HashSet<>(); // make empty set instead of ["minecraft:"].
            }
            else {
                BLACKLISTED_LOOTTABLES =
                        Arrays.stream(cleanedBlacklist.split(","))
                                .map(entry -> ResourceLocation.tryParse(entry.trim()))
                                .filter(Objects::nonNull)
                                .map(entry -> ResourceKey.create(Registries.LOOT_TABLE, entry))
                                .collect(Collectors.toSet());
            }
        }

        return BLACKLISTED_LOOTTABLES.contains(lootTableID);
    }

    public static void checkLoottables(MinecraftServer minecraftServer) {
        boolean invalidLootTableFound = false;
        for (Map.Entry<ResourceKey<LootTable>, ResourceKey<LootTable>> entry : TABLE_IMPORTS.entrySet()) {
            if(entry.getKey().location().getNamespace().equals("betterstrongholds")) {
                continue;
            }

            if (GeneralUtils.isInvalidLootTableFound(minecraftServer, entry)) {
                invalidLootTableFound = true;
            }
        }

        if (GeneralUtils.isMissingLootImporting(minecraftServer, TABLE_IMPORTS.keySet())) {
            invalidLootTableFound = true;
        }

        if (invalidLootTableFound) {
            RepurposedStructures.LOGGER.error("Unknown import/target loot tables found for Repurposed Structures. See above logs and report to TelepathicGrunt please.");
        }
    }

    public static ResourceKey<LootTable> generateKey(String namespace, String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(namespace, path));
    }
}