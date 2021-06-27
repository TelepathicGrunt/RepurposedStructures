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
        configVersion = 1;

        Config.super.save();
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
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:skeleton", 1, 1, 4))),
            Map.entry("repurposed_structures:stronghold_nether", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:blaze", 10, 2, 3),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:zombified_piglin", 3, 4, 4),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:wither_skeleton", 10, 5, 5),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:skeleton", 2, 5, 5),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:magma_cube", 3, 4, 4))),
            Map.entry("repurposed_structures:city_nether", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:blaze", 120, 1, 4),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:wither_skeleton", 10, 2, 3))),
            Map.entry("repurposed_structures:witch_hut_oak", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:witch", 1, 1, 1),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:cat", 1, 1, 1))),
            Map.entry("repurposed_structures:witch_hut_taiga", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:witch", 1, 1, 1),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:cat", 1, 1, 1))),
            Map.entry("repurposed_structures:witch_hut_birch", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:witch", 1, 1, 1),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:cat", 1, 1, 1))),
            Map.entry("repurposed_structures:witch_hut_dark_forest", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:witch", 1, 1, 1),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:cat", 1, 1, 1))),
            Map.entry("repurposed_structures:witch_hut_giant_tree_taiga", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:witch", 1, 1, 1),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:cat", 1, 1, 1))),
            Map.entry("repurposed_structures:outpost_nether_brick", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:piglin", 10, 1, 1))),
            Map.entry("repurposed_structures:outpost_warped", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:piglin", 10, 1, 1))),
            Map.entry("repurposed_structures:outpost_crimson", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:piglin", 10, 1, 1))),
            Map.entry("repurposed_structures:outpost_birch", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1))),
            Map.entry("repurposed_structures:outpost_giant_tree_taiga", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1))),
            Map.entry("repurposed_structures:outpost_desert", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1))),
            Map.entry("repurposed_structures:outpost_jungle", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1))),
            Map.entry("repurposed_structures:outpost_badlands", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1))),
            Map.entry("repurposed_structures:outpost_icy", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1))),
            Map.entry("repurposed_structures:outpost_snowy", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1))),
            Map.entry("repurposed_structures:outpost_taiga", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1))),
            Map.entry("repurposed_structures:outpost_oak", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1))),
            Map.entry("repurposed_structures:outpost_end", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:phantom", 10, 1, 1)))
    );


    @Comment("""





            // In the key part, specify the name of the structures from Repurposed Structures that
            // you want to affect. Then in the value part, add entries for all the mobs you would like
            // to spawn over time in the structure's bounds. This entry will combine with the biome's
            // mob spawns when spawning a mob in the structure's boundary after worldgen is done.
            // This means you will see both biome's and structure's mobs in the structure.
            
            // Use "all" as the key to affect all of RS's structures.
            // You can find entity's identifiers by doing "/effect @e[type=" command in game.
            // All of RS's structure identifiers can be found by doing "/locate" command in game.
            """)
    public final Map<String, List<MobSpawningOverTime.PublicMobSpawnEntry>> appendMobSpawns = Map.ofEntries(
            Map.entry("repurposed_structures:mineshaft_end", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:endermite", 10, 2, 5),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:endermen", 5, 1, 3))),
            Map.entry("repurposed_structures:shipwreck_nether_bricks", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:wither_skeleton", 25, 1, 1))),
            Map.entry("repurposed_structures:shipwreck_crimson", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:wither_skeleton", 25, 1, 1))),
            Map.entry("repurposed_structures:shipwreck_warped", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:wither_skeleton", 25, 1, 1))),
            Map.entry("repurposed_structures:fortress_jungle", List.of(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:wither_skeleton", 27, 1, 1)))
    );


    @Comment("""





            // for internal use only. Do not change this.
            """)
    public int configVersion = 1;
}
