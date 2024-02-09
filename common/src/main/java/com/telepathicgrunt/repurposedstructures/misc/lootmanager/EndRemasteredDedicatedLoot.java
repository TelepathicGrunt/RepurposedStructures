package com.telepathicgrunt.repurposedstructures.misc.lootmanager;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;

import java.util.HashMap;
import java.util.Map;

public class EndRemasteredDedicatedLoot {
    private EndRemasteredDedicatedLoot() {}

    public static boolean isEndRemasteredOn = false;
    public static final Map<ResourceLocation, ResourceLocation> END_REMASTERED_DEDICATED_TABLE_IMPORTS = createEndRemasteredMap();
    private static Map<ResourceLocation, ResourceLocation> createEndRemasteredMap() {
        Map<ResourceLocation, ResourceLocation> tableMap = new HashMap<>();
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/birch"), new ResourceLocation("endrem:minecraft/chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/ocean"), new ResourceLocation("endrem:minecraft/chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/savanna"), new ResourceLocation("endrem:minecraft/chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/stone"), new ResourceLocation("endrem:minecraft/chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/swamp"), new ResourceLocation("endrem:minecraft/chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/dark_forest"), new ResourceLocation("endrem:minecraft/chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/taiga"), new ResourceLocation("endrem:minecraft/chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/igloos/stone"), new ResourceLocation("endrem:minecraft/chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/igloos/mushroom"), new ResourceLocation("endrem:minecraft/chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/igloos/grassy"), new ResourceLocation("endrem:minecraft/chests/abandoned_mineshaft"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/igloos/mangrove"), new ResourceLocation("endrem:minecraft/chests/abandoned_mineshaft"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/badlands"), new ResourceLocation("endrem:minecraft/chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/dark_forest"), new ResourceLocation("endrem:minecraft/chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/deep"), new ResourceLocation("endrem:minecraft/chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/desert"), new ResourceLocation("endrem:minecraft/chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/jungle"), new ResourceLocation("endrem:minecraft/chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/icy"), new ResourceLocation("endrem:minecraft/chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/mushroom"), new ResourceLocation("endrem:minecraft/chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/ocean"), new ResourceLocation("endrem:minecraft/chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/snow"), new ResourceLocation("endrem:minecraft/chests/simple_dungeon"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/swamp"), new ResourceLocation("endrem:minecraft/chests/simple_dungeon"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/fortresses/jungle_shrine"), new ResourceLocation("endrem:minecraft/chests/jungle_temple"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/jungle"), new ResourceLocation("endrem:minecraft/chests/jungle_temple"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/jungle"), new ResourceLocation("endrem:minecraft/chests/jungle_temple"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/flower_forest"), new ResourceLocation("endrem:minecraft/chests/jungle_temple"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/desert"), new ResourceLocation("endrem:minecraft/chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/mushroom"), new ResourceLocation("endrem:minecraft/chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/giant_tree_taiga"), new ResourceLocation("endrem:minecraft/chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/dark_forest"), new ResourceLocation("endrem:minecraft/chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "trapped_chests/pyramids/badlands"), new ResourceLocation("endrem:minecraft/chests/desert_pyramid"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "trapped_chests/pyramids/ocean"), new ResourceLocation("endrem:minecraft/chests/desert_pyramid"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/icy"), new ResourceLocation("endrem:minecraft/chests/igloo_chest"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/snowy"), new ResourceLocation("endrem:minecraft/chests/igloo_chest"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/pyramids/icy"), new ResourceLocation("endrem:minecraft/chests/igloo_chest"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/badlands"), new ResourceLocation("endrem:minecraft/chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/birch"), new ResourceLocation("endrem:minecraft/chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/desert"), new ResourceLocation("endrem:minecraft/chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/giant_tree_taiga"), new ResourceLocation("endrem:minecraft/chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/icy"), new ResourceLocation("endrem:minecraft/chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/jungle"), new ResourceLocation("endrem:minecraft/chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/mangrove"), new ResourceLocation("endrem:minecraft/chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/oak"), new ResourceLocation("endrem:minecraft/chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/ocean"), new ResourceLocation("endrem:minecraft/chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/savanna"), new ResourceLocation("endrem:minecraft/chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/snowy"), new ResourceLocation("endrem:minecraft/chests/pillager_outpost"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/taiga"), new ResourceLocation("endrem:minecraft/chests/pillager_outpost"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/basalt"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/crimson"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/nether_brick"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/soul"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/outposts/warped"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/dungeons/nether"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/basalt"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/crimson"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/nether"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/soul"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/mineshafts/warped"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temples/basalt"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temples/crimson"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temples/soul"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temples/wasteland"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/temples/warped"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "trapped_chests/temples/warped"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "trapped_chests/pyramids/nether"), new ResourceLocation("endrem:minecraft/chests/nether_bridge"));

        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/crimson/treasure"), new ResourceLocation("endrem:minecraft/chests/buried_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/nether_bricks/treasure"), new ResourceLocation("endrem:minecraft/chests/buried_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/shipwrecks/warped/treasure"), new ResourceLocation("endrem:minecraft/chests/buried_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/bastions/underground/treasure"), new ResourceLocation("endrem:minecraft/chests/buried_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/bastions/underground/bridge"), new ResourceLocation("endrem:minecraft/chests/buried_treasure"));
        tableMap.put(new ResourceLocation(RepurposedStructures.MODID, "chests/bastions/underground/skeleton_horse_stable"), new ResourceLocation("endrem:minecraft/chests/buried_treasure"));

        return tableMap;
    }

    public static void checkLoottables(MinecraftServer minecraftServer) {
        if(isEndRemasteredOn) {
            boolean invalidLootTableFound = false;
            for (Map.Entry<ResourceLocation, ResourceLocation> entry : END_REMASTERED_DEDICATED_TABLE_IMPORTS.entrySet()) {
                if (GeneralUtils.isInvalidLootTableFound(minecraftServer, entry)) {
                    invalidLootTableFound = true;
                }
            }
            if (invalidLootTableFound) {
                RepurposedStructures.LOGGER.error("Unknown import/target loot tables found for Repurposed Structures. See above logs and report to TelepathicGrunt please.");
            }
        }
    }
}
