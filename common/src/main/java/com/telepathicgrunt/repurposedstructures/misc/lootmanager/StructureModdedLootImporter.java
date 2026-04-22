package com.telepathicgrunt.repurposedstructures.misc.lootmanager;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.configs.RSMainModdedLootConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import com.telepathicgrunt.repurposedstructures.utils.PlatformHooks;
import net.minecraft.core.registries.BuiltInRegistries;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StructureModdedLootImporter {

    // Need to map loottables by hand to the vanilla structure that our structure is based on. (usually...)
    public static final Map<ResourceLocation, ResourceLocation> TABLE_IMPORTS = createMap();
    private static Set<ResourceLocation> BLACKLISTED_LOOTTABLES;

    public static Map<ResourceLocation, ResourceLocation> createMap() {
        Map<ResourceLocation, ResourceLocation> tableMap = new Object2ObjectOpenHashMap<>();
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/cities/nether"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/bastion_treasure"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/cities/overworld"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_plains_house"));

        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ancient_cities/ocean"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/ancient_city"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ancient_cities/ocean_ice_box"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/ancient_city_ice_box"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ancient_cities/nether"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/ancient_city"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ancient_cities/nether_magma_box"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/ancient_city_ice_box"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ancient_cities/end"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/ancient_city"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ancient_cities/end_spawner_box"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/ancient_city_ice_box"));

        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/bastions/underground/treasure"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_crossing"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/bastions/underground/bridge"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_corridor"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/bastions/underground/other"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_corridor"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/bastions/underground/skeleton_horse_stable"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_corridor"));

        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/dungeons/badlands"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/simple_dungeon"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/dungeons/dark_forest"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/simple_dungeon"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/dungeons/deep"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/simple_dungeon"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/dungeons/desert"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/simple_dungeon"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/dungeons/icy"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/simple_dungeon"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/dungeons/jungle"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/simple_dungeon"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/dungeons/mushroom"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/simple_dungeon"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/dungeons/nether"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/simple_dungeon"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/dungeons/ocean"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/simple_dungeon"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/dungeons/snow"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/simple_dungeon"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/dungeons/swamp"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/simple_dungeon"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "shulker_boxes/dungeons/end"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/simple_dungeon"));

        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/fortresses/jungle_center"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_crossing"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/fortresses/jungle_hallway"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_corridor"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/fortresses/jungle_shrine"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_crossing"));

        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/igloos/grassy"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/igloo_chest"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/igloos/stone"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/igloo_chest"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/igloos/mangrove"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/igloo_chest"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/igloos/mushroom"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/igloo_chest"));

        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mansions/birch"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/woodland_mansion"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mansions/desert"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/woodland_mansion"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mansions/jungle"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/woodland_mansion"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mansions/mangrove"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/woodland_mansion"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mansions/oak"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/woodland_mansion"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mansions/savanna"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/woodland_mansion"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mansions/snowy"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/woodland_mansion"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mansions/taiga"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/woodland_mansion"));

        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/basalt"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/birch"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/crimson"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/dark_forest"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/desert"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/end"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/icy"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/jungle"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/nether"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/ocean"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/savanna"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/soul"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/stone"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/swamp"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/taiga"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/mineshafts/warped"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/abandoned_mineshaft"));

        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/basalt"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/badlands"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/birch"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/crimson"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/desert"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/giant_tree_taiga"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/icy"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/jungle"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/mangrove"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/nether_brick"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/oak"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/ocean"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/savanna"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/soul"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/snowy"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/taiga"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/outposts/warped"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/pillager_outpost"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "shulker_boxes/outposts/end"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));

        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/pyramids/dark_forest"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/desert_pyramid"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/pyramids/end"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/pyramids/flower_forest"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/desert_pyramid"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/pyramids/giant_tree_taiga"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/desert_pyramid"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/pyramids/icy"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/desert_pyramid"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/pyramids/jungle"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/desert_pyramid"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/pyramids/mushroom"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/desert_pyramid"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/pyramids/snowy"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/desert_pyramid"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "trapped_chests/pyramids/badlands"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/desert_pyramid"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "trapped_chests/pyramids/end"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "trapped_chests/pyramids/nether"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/desert_pyramid"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "trapped_chests/pyramids/ocean"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/desert_pyramid"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "dispensers/pyramids/dark_forest"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/desert_pyramid"));

        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ruined_portals/end/large_portal"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/ruined_portal"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ruined_portals/end/small_portal"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/ruined_portal"));

        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ruins/land_hot/large"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_desert_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ruins/land_hot/small"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_desert_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ruins/land_warm/large"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_plains_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ruins/land_warm/small"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_plains_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ruins/land_cold/large"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_taiga_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ruins/land_cold/small"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_taiga_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ruins/land_icy/large"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_snowy_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ruins/land_icy/small"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_snowy_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/ruins/nether"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/bastion_other"));

        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/shipwrecks/crimson/map"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/shipwreck_map"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/shipwrecks/crimson/supply"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/shipwreck_supply"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/shipwrecks/crimson/treasure"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/shipwreck_treasure"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/shipwrecks/end/map"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/shipwreck_map"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/shipwrecks/end/supply"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/shipwreck_supply"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/shipwrecks/end/treasure"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/shipwrecks/warped/map"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/shipwreck_map"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/shipwrecks/warped/supply"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/shipwreck_supply"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/shipwrecks/warped/treasure"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/shipwreck_treasure"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/shipwrecks/nether_bricks/treasure"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/shipwreck_treasure"));

        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "shulker_boxes/strongholds/end_storage_room"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "shulker_boxes/strongholds/end_hallway"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "shulker_boxes/strongholds/end_library"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/strongholds/nether_storage_room"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_crossing"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/strongholds/nether_hallway"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_corridor"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/strongholds/nether_library"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_library"));

        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/temples/basalt"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/nether_bridge"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/temples/crimson"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/nether_bridge"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/temples/soul"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/nether_bridge"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/temples/warped"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/nether_bridge"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/temples/wasteland"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/nether_bridge"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/temples/taiga"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/jungle_temple"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/temples/ocean"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/jungle_temple"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "trapped_chests/temples/warped"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/nether_bridge"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "dispensers/temples/basalt"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/jungle_temple_dispenser"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "dispensers/temples/crimson"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/jungle_temple_dispenser"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "dispensers/temples/soul"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/jungle_temple_dispenser"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "dispensers/temples/warped"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/jungle_temple_dispenser"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "dispensers/temples/wasteland"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/jungle_temple_dispenser"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "dispensers/temples/taiga"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/jungle_temple_dispenser"));

        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/badlands_house"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_desert_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/bamboo_house"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_savanna_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/birch_house"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_plains_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/cherry_house"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_plains_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/dark_forest_house"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_plains_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/giant_taiga_house"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_taiga_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/jungle_house"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_savanna_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/mountains_house"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_snowy_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/mushroom_house"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_plains_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/oak_house"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_plains_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/ocean_house"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_plains_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/ocean_cartographer"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_cartographer"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/swamp_house"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_plains_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/crimson_cartographer"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_cartographer"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/crimson_fisher"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_fisher"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/crimson_tannery"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_tannery"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/crimson_weaponsmith"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_weaponsmith"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/crimson_house"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_desert_house"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/warped_cartographer"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_cartographer"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/warped_fisher"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_fisher"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/warped_tannery"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_tannery"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/warped_weaponsmith"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_weaponsmith"));
        tableMap.put(ResourceLocation.fromNamespaceAndPath(RepurposedStructures.MODID, "chests/villages/warped_house"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_desert_house"));

        // For Better Strongholds compat datapack
        if(PlatformHooks.isModLoaded("betterstrongholds")) {
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/end/armoury"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/end/common"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/end/crypt"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/end/grand_library"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/end/library_md"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/end/mess"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/end/prison_lg"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/end/trap"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/end/treasure"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/nether/common"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_crossing"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/nether/mess"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_crossing"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/nether/armoury"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_corridor"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/nether/crypt"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_corridor"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/nether/prison_lg"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_corridor"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/nether/trap"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_corridor"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/nether/treasure"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_corridor"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/nether/grand_library"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_library"));
            tableMap.put(ResourceLocation.fromNamespaceAndPath("betterstrongholds", "chests/nether/library_md"), ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_library"));
        }
        return tableMap;
    }

    public static boolean isInBlacklist(ResourceLocation lootTableID){
        if(BLACKLISTED_LOOTTABLES == null){
            String cleanedBlacklist = RSMainModdedLootConfig.blacklistedRSLoottablesFromImportingModdedItems.trim();

            if(cleanedBlacklist.isEmpty()){
                BLACKLISTED_LOOTTABLES = new HashSet<>(); // make empty set instead of ["minecraft:"].
            }
            else {
                BLACKLISTED_LOOTTABLES =
                        Arrays.stream(cleanedBlacklist.split(","))
                                .map(String::trim)
                                .map(ResourceLocation::tryParse)
                                .collect(Collectors.toSet());
            }
        }

        return BLACKLISTED_LOOTTABLES.contains(lootTableID);
    }

    public static boolean isFilteredOut(ItemStack itemStack) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(itemStack.getItem());
        return (itemId != null && itemId.getNamespace().equals("minecraft"))
                    || itemStack.is(RSTags.BLACKLISTED_FROM_MODDED_LOOT_IMPORTING);
    }

    public static void checkLoottables(MinecraftServer minecraftServer) {
        boolean invalidLootTableFound = false;
        for(Map.Entry<ResourceLocation, ResourceLocation> entry : TABLE_IMPORTS.entrySet()) {
            if(entry.getKey().getNamespace().equals("betterstrongholds")) {
                continue;
            }

            if(GeneralUtils.isInvalidLootTableFound(minecraftServer, entry)) {
                invalidLootTableFound = true;
            }
        }
        if(GeneralUtils.isMissingLootImporting(minecraftServer, TABLE_IMPORTS.keySet())) {
            invalidLootTableFound = true;
        }
        if(invalidLootTableFound) {
            RepurposedStructures.LOGGER.error("Unknown import/target loot tables found for Repurposed Structures. See above logs and report to TelepathicGrunt please.");
        }
    }
}