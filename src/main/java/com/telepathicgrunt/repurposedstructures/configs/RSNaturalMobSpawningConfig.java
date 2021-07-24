package com.telepathicgrunt.repurposedstructures.configs;


import com.telepathicgrunt.repurposedstructures.configs.omegaconfig.api.Comment;
import com.telepathicgrunt.repurposedstructures.configs.omegaconfig.api.Config;
import com.telepathicgrunt.repurposedstructures.misc.MobSpawningOverTime;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RSNaturalMobSpawningConfig implements Config {

    @Override
    public String getName() {
        return "repurposed_structures-forge/natural_mob_spawning_configs";
    }

    @Override
    public String getExtension() {
        return "json5";
    }

    @Override
    public void save() {
        // Add logic here when adding new mob spawn structures to config."
        // The logic needs to do a putIfAbsent to add the missing structures between the config versions when updating."

        // Fix typo in old config value"
        if(configVersion == 1){
            List<MobSpawningOverTime.PublicMobSpawnEntry> mobList = appendMobSpawns.get("repurposed_structures:mineshaft_end");
            for(int i = 0; i < mobList.size(); i++){
                if(mobList.get(i).type.equals("minecraft:endermen")){
                    mobList.set(i, new MobSpawningOverTime.PublicMobSpawnEntry(
                                    "minecraft:enderman",
                                    mobList.get(i).weight,
                                    mobList.get(i).minGroupSize,
                                    mobList.get(i).maxGroupSize));
                }
            }
        }

        configVersion = 2;
        Config.super.save();
    }


    @Comment("\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n// In the key part, specify the name of the structures from Repurposed Structures that"+
            "\n// you want to affect. Then in the value part, add entries for all the mobs you would like"+
            "\n// to spawn over time in the structure's bounds. This entry will ignore biome's mob spawns"+
            "\n// entirely when spawning a mob in the structure's boundary after worldgen is done."+
            "\n// This means you will not see the biome's mobs in the structure."+
            "\n"+
            "\n// The change only applies to SpawnGroups of the mobs you add to this config."+
            "\n// Example: adding Wither Skeletons here to Birch Villages will make all Monster"+
            "\n// SpawnGroup spawns be Wither Skeletons in the village but Creature and other"+
            "\n// SpawnGroups will spawn just fine over time in the village such as sheep or cow."+
            "\n"+
            "\n// Use \"all\" as the key to affect all of RS's structures."+
            "\n// You can find entity's identifiers by doing \"/effect @e[type=\" command in game."+
            "\n// All of RS's structure identifiers can be found by doing \"/locate\" command in game."+
            "\n"+
            "\n// NOTE: replaceMobSpawns will always override appendMobSpawns for the same structure and mob's SpawnGroups."+
            "\n// And if you add   \"optionalSpawn\": true   under maxGroupSize for an entry, RS will not print a warning if the entity is not found."
    )
    public final Map<String, List<MobSpawningOverTime.PublicMobSpawnEntry>> replaceMobSpawns = new HashMap<String, List<MobSpawningOverTime.PublicMobSpawnEntry>>() {{
        put("repurposed_structures:bastion_underground", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:skeleton", 1, 1, 4)));
        put("repurposed_structures:stronghold_nether", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:blaze", 10, 2, 3),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:zombified_piglin", 3, 4, 4),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:wither_skeleton", 10, 5, 5),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:skeleton", 2, 5, 5),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:magma_cube", 3, 4, 4)));
        put("repurposed_structures:city_nether", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:blaze", 120, 1, 4),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:wither_skeleton", 10, 2, 3)));
        put("repurposed_structures:witch_hut_oak", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:witch", 1, 1, 1),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:cat", 1, 1, 1)));
        put("repurposed_structures:witch_hut_taiga", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:witch", 1, 1, 1),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:cat", 1, 1, 1)));
        put("repurposed_structures:witch_hut_birch", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:witch", 1, 1, 1),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:cat", 1, 1, 1)));
        put("repurposed_structures:witch_hut_dark_forest", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:witch", 1, 1, 1),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:cat", 1, 1, 1)));
        put("repurposed_structures:witch_hut_giant_tree_taiga", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:witch", 1, 1, 1),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:cat", 1, 1, 1)));
        put("repurposed_structures:outpost_nether_brick", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:piglin", 10, 1, 1)));
        put("repurposed_structures:outpost_warped", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:piglin", 10, 1, 1)));
        put("repurposed_structures:outpost_crimson", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:piglin", 10, 1, 1)));
        put("repurposed_structures:outpost_birch", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1)));
        put("repurposed_structures:outpost_giant_tree_taiga", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1)));
        put("repurposed_structures:outpost_desert", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1)));
        put("repurposed_structures:outpost_jungle", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1)));
        put("repurposed_structures:outpost_badlands", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1)));
        put("repurposed_structures:outpost_icy", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1)));
        put("repurposed_structures:outpost_snowy", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1)));
        put("repurposed_structures:outpost_taiga", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1)));
        put("repurposed_structures:outpost_oak", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:pillager", 1, 1, 1)));
        put("repurposed_structures:outpost_end", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:phantom", 10, 1, 1)));
    }};


    @Comment("\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n// In the key part, specify the name of the structures from Repurposed Structures that"+
            "\n// you want to affect. Then in the value part, add entries for all the mobs you would like"+
            "\n// to spawn over time in the structure's bounds. This entry will combine with the biome's"+
            "\n// mob spawns when spawning a mob in the structure's boundary after worldgen is done."+
            "\n// This means you will see both biome's and structure's mobs in the structure."+
            "\n"+
            "\n// Use \"all\" as the key to affect all of RS's structures."+
            "\n// You can find entity's identifiers by doing \"/effect @e[type=\" command in game."+
            "\n// All of RS's structure identifiers can be found by doing \"/locate\" command in game."+
            "\n// And if you add   \"optionalSpawn\": true   under maxGroupSize for an entry, RS will not print a warning if the entity is not found."
    )
    public final Map<String, List<MobSpawningOverTime.PublicMobSpawnEntry>> appendMobSpawns = new HashMap<String, List<MobSpawningOverTime.PublicMobSpawnEntry>>() {{
            put("repurposed_structures:mineshaft_end", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:endermite", 10, 2, 5),
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:enderman", 5, 1, 3)));
            put("repurposed_structures:shipwreck_nether_bricks", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:wither_skeleton", 25, 1, 1)));
            put("repurposed_structures:shipwreck_crimson", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:wither_skeleton", 25, 1, 1)));
            put("repurposed_structures:shipwreck_warped", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:wither_skeleton", 25, 1, 1)));
            put("repurposed_structures:fortress_jungle", Arrays.asList(
                    new MobSpawningOverTime.PublicMobSpawnEntry("minecraft:wither_skeleton", 27, 1, 1)));
        }};


    @Comment("\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n// for internal use only. Do not change this."
    )
    public int configVersion = 2;
}
