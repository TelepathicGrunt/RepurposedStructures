package com.telepathicgrunt.repurposedstructures.configs;


import com.telepathicgrunt.repurposedstructures.misc.MobSpawningOverTime;
import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;

import java.util.List;
import java.util.Map;


public class RSNaturalMobSpawningOmegaConfig implements Config {

    @Override
    public String getName() {
        return "repurposed_structures-fabric/natural_mob_spawning_configs";
    }

    @Override
    public String getExtension() {
        return "json5";
    }

    @Override
    public void save() {
        // Add logic here when adding new mob spawn structures to config.
        // The logic needs to do a putIfAbsent to add the missing structures between the config versions when updating.

        configVersion = 3;
        Config.super.save();
    }

    private void addEntries(Map<String, List<MobSpawningOverTime.PublicMobSpawnEntry>> map, String key, List<MobSpawningOverTime.PublicMobSpawnEntry> entry) {
        // assign entry
        if(map.putIfAbsent(key, entry) != null) {
            map.get(key).addAll(entry); // append entry
        }
    }

    @Comment("""





            // In the key part, specify the name of the structures from Repurposed Structures that
            // you want to affect. Then in the value part, add entries for all the mobs you would like
            // to spawn over time in the structure's bounds. This entry will ignore biome's mob spawns
            // entirely when spawning a mob in the structure's boundary after worldgen is done.
            // This means you will not see the biome's mobs in the structure.
            
            // The change only applies to SpawnGroups of the mobs you add to this config.
            // Example: adding Wither Skeletons here to Birch Villages will make all Monster
            // SpawnGroup spawns be Wither Skeletons in the village but Creature and other
            // SpawnGroups will spawn just fine over time in the village such as sheep or cow.
            
            // Use "all" as the key to affect all of RS's structures.
            // You can find entity's identifiers by doing "/effect @e[type=" command in game.
            // All of RS's structure identifiers can be found by doing "/locate" command in game.
            
            // NOTE: replaceMobSpawns will always override appendMobSpawns for the same structure and mob's SpawnGroups.
            """)
    public final Map<String, List<MobSpawningOverTime.PublicMobSpawnEntry>> replaceMobSpawns = Map.ofEntries(
            Map.entry("repurposed_structures:bastion_underground", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:skeleton", 10, 1, 4))),
            Map.entry("repurposed_structures:city_nether", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:blaze", 120, 1, 4),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:wither_skeleton", 10, 2, 3))),
            Map.entry("repurposed_structures:witch_hut_oak", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:witch", 10, 1, 1),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:cat", 10, 1, 1))),
            Map.entry("repurposed_structures:witch_hut_taiga", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:witch", 10, 1, 1),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:cat", 10, 1, 1))),
            Map.entry("repurposed_structures:witch_hut_birch", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:witch", 10, 1, 1),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:cat", 10, 1, 1))),
            Map.entry("repurposed_structures:witch_hut_dark_forest", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:witch", 10, 1, 1),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:cat", 10, 1, 1))),
            Map.entry("repurposed_structures:witch_hut_giant_tree_taiga", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:witch", 10, 1, 1),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:cat", 10, 1, 1)))
    );


    @Comment("""





            // for internal use only. Do not change this.
            """)
    public int configVersion = 3;
}
