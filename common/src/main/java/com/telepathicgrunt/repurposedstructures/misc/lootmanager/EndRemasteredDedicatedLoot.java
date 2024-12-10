package com.telepathicgrunt.repurposedstructures.misc.lootmanager;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.HashMap;
import java.util.Map;

public class EndRemasteredDedicatedLoot {
    private EndRemasteredDedicatedLoot() {}

    public static boolean isEndRemasteredOn = false;
    public static final Map<ResourceKey<LootTable>, ResourceKey<LootTable>> END_REMASTERED_DEDICATED_TABLE_IMPORTS = createEndRemasteredMap();
    private static Map<ResourceKey<LootTable>, ResourceKey<LootTable>> createEndRemasteredMap() {
        Map<ResourceKey<LootTable>, ResourceKey<LootTable>> tableMap = new HashMap<>();
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/mineshafts/birch"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/abandoned_mineshaft"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/mineshafts/ocean"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/abandoned_mineshaft"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/mineshafts/savanna"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/abandoned_mineshaft"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/mineshafts/stone"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/abandoned_mineshaft"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/mineshafts/swamp"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/abandoned_mineshaft"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/mineshafts/dark_forest"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/abandoned_mineshaft"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/mineshafts/taiga"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/abandoned_mineshaft"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/igloos/stone"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/abandoned_mineshaft"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/igloos/mushroom"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/abandoned_mineshaft"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/igloos/grassy"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/abandoned_mineshaft"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/igloos/mangrove"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/abandoned_mineshaft"));

        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/dungeons/badlands"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/simple_dungeon"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/dungeons/dark_forest"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/simple_dungeon"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/dungeons/deep"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/simple_dungeon"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/dungeons/desert"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/simple_dungeon"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/dungeons/jungle"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/simple_dungeon"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/dungeons/icy"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/simple_dungeon"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/dungeons/mushroom"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/simple_dungeon"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/dungeons/ocean"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/simple_dungeon"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/dungeons/snow"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/simple_dungeon"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/dungeons/swamp"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/simple_dungeon"));

        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/fortresses/jungle_shrine"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/jungle_temple"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/mineshafts/jungle"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/jungle_temple"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/pyramids/jungle"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/jungle_temple"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/pyramids/flower_forest"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/jungle_temple"));

        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/mineshafts/desert"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/desert_pyramid"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/pyramids/mushroom"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/desert_pyramid"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/pyramids/giant_tree_taiga"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/desert_pyramid"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/pyramids/dark_forest"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/desert_pyramid"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "trapped_chests/pyramids/badlands"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/desert_pyramid"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "trapped_chests/pyramids/ocean"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/desert_pyramid"));

        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/mineshafts/icy"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/igloo_chest"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/pyramids/snowy"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/igloo_chest"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/pyramids/icy"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/igloo_chest"));

        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/badlands"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/pillager_outpost"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/birch"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/pillager_outpost"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/desert"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/pillager_outpost"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/giant_tree_taiga"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/pillager_outpost"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/icy"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/pillager_outpost"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/jungle"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/pillager_outpost"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/mangrove"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/pillager_outpost"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/oak"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/pillager_outpost"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/ocean"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/pillager_outpost"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/savanna"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/pillager_outpost"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/snowy"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/pillager_outpost"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/taiga"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/pillager_outpost"));

        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/basalt"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/crimson"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/nether_brick"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/soul"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/outposts/warped"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/dungeons/nether"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/mineshafts/basalt"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/mineshafts/crimson"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/mineshafts/nether"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/mineshafts/soul"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/mineshafts/warped"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/temples/basalt"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/temples/crimson"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/temples/soul"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/temples/wasteland"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/temples/warped"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "trapped_chests/temples/warped"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "trapped_chests/pyramids/nether"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/nether_bridge"));

        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/shipwrecks/crimson/treasure"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/buried_treasure"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/shipwrecks/nether_bricks/treasure"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/buried_treasure"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/shipwrecks/warped/treasure"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/buried_treasure"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/bastions/underground/treasure"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/buried_treasure"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/bastions/underground/bridge"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/buried_treasure"));
        tableMap.put(StructureModdedLootImporter.generateKey(RepurposedStructures.MODID, "chests/bastions/underground/skeleton_horse_stable"), StructureModdedLootImporter.generateKey("endrem", "minecraft/chests/buried_treasure"));

        return tableMap;
    }

    public static void checkLoottables(MinecraftServer minecraftServer) {
        if(isEndRemasteredOn) {
            boolean invalidLootTableFound = false;
            for (Map.Entry<ResourceKey<LootTable>, ResourceKey<LootTable>> entry : END_REMASTERED_DEDICATED_TABLE_IMPORTS.entrySet()) {
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
