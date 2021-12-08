package com.telepathicgrunt.repurposedstructures.configs;


import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Config;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RSAllowDisallowOmegaConfig implements Config {

    @Override
    public String getName() {
        return "repurposed_structures-fabric/biome_dimension_allow_disallow_configs";
    }

    @Override
    public String getExtension() {
        return "json5";
    }

    @Override
    public void save() {

        if(configVersion == 1) {
            addEntries(disallowedBiomes, "repurposed_structures:dungeons_neutral_ocean", "terrestria:lush_desert");
            addEntries(disallowedBiomes, "repurposed_structures:dungeons_lukewarm_ocean", "terrestria:lush_desert");
            addEntries(disallowedBiomes, "repurposed_structures:dungeons_frozen_ocean", "terrestria:lush_desert");
            addEntries(disallowedBiomes, "repurposed_structures:dungeons_cold_ocean", "terrestria:lush_desert");
            addEntries(disallowedBiomes, "repurposed_structures:dungeons_warm_ocean", "terrestria:lush_desert");
            configVersion = 2;
        }

        if(configVersion == 2) {
            configVersion = 3;
        }

        if(configVersion == 3) {
            addEntries(disallowedBiomes, "repurposed_structures:dungeons_badlands", "terralith:snowy_badlands");
            addEntries(disallowedBiomes, "repurposed_structures:dungeons_snow", "terralith:gravel_desert");
            addEntries(disallowedBiomes, "repurposed_structures:dungeons_desert", "terralith:red_oasis");
            addEntries(disallowedBiomes, "repurposed_structures:dungeons_neutral_ocean", "terralith:skylands");
            addEntries(disallowedBiomes, "repurposed_structures:mansion_desert", "terralith:red_oasis");
            addEntries(disallowedBiomes, "repurposed_structures:mansion_snowy", "terralith:gravel_desert");
            addEntries(disallowedBiomes, "repurposed_structures:mineshaft_savanna", "terralith:brushland");
            addEntries(disallowedBiomes, "repurposed_structures:mineshaft_stone", "terralith:volcanic_crater");
            addEntries(disallowedBiomes, "repurposed_structures:mineshaft_stone", "terralith:volcanic_peaks");
            addEntries(disallowedBiomes, "repurposed_structures:village_mountains", "terralith:volcanic_crater");
            addEntries(disallowedBiomes, "repurposed_structures:village_mountains", "terralith:volcanic_peaks");
            addEntries(disallowedBiomes, "repurposed_structures:village_badlands", "terralith:snowy_badlands");
            addEntries(disallowedBiomes, "repurposed_structures:outpost_badlands", "terralith:snowy_badlands");
            addEntries(disallowedBiomes, "repurposed_structures:pyramid_badlands", "terralith:snowy_badlands");

            addEntries(allowedBiomes, "repurposed_structures:dungeons_badlands", "terralith:savanna_badlands");
            addEntries(allowedBiomes, "repurposed_structures:dungeons_badlands", "terralith:red_oasis");
            addEntries(allowedBiomes, "repurposed_structures:dungeons_dark_forest", "terralith:mirage_isles");
            addEntries(allowedBiomes, "repurposed_structures:dungeons_desert", "terralith:cave/desert_caves");
            addEntries(allowedBiomes, "repurposed_structures:dungeons_snow", "terralith:alpine_grove");
            addEntries(allowedBiomes, "repurposed_structures:dungeons_snow", "terralith:alpine_highlands");
            addEntries(allowedBiomes, "repurposed_structures:dungeons_snow", "terralith:cold_shrubland");
            addEntries(allowedBiomes, "repurposed_structures:dungeons_icy", "terralith:cave/frostfire_caves");
            addEntries(allowedBiomes, "repurposed_structures:dungeons_icy", "terralith:cave/ice_caves");
            addEntries(allowedBiomes, "repurposed_structures:dungeons_icy", "terralith:cave/fungal_caves");

            addEntries(allowedBiomes, "repurposed_structures:igloo_grassy", "terralith:brushland");
            addEntries(allowedBiomes, "repurposed_structures:igloo_stone", "terralith:amethyst_canyon");
            addEntries(allowedBiomes, "repurposed_structures:igloo_stone", "terralith:basalt_cliffs");
            addEntries(allowedBiomes, "repurposed_structures:igloo_stone", "terralith:emerald_peaks");
            addEntries(allowedBiomes, "repurposed_structures:igloo_stone", "terralith:granite_cliffs");
            addEntries(allowedBiomes, "repurposed_structures:igloo_stone", "terralith:haze_mountain");
            addEntries(allowedBiomes, "repurposed_structures:igloo_stone", "terralith:mountain_steppe");
            addEntries(allowedBiomes, "repurposed_structures:igloo_stone", "terralith:painted_mountains");
            addEntries(allowedBiomes, "repurposed_structures:igloo_stone", "terralith:rocky_mountains");
            addEntries(allowedBiomes, "repurposed_structures:igloo_stone", "terralith:shield_clearing");
            addEntries(allowedBiomes, "repurposed_structures:igloo_stone", "terralith:steppe");
            addEntries(allowedBiomes, "repurposed_structures:igloo_stone", "terralith:stony_spires");
            addEntries(allowedBiomes, "repurposed_structures:igloo_stone", "terralith:valley_clearing");
            addEntries(allowedBiomes, "repurposed_structures:mansion_birch", "terralith:temperate_highlands");
            addEntries(allowedBiomes, "repurposed_structures:mansion_birch", "terralith:white_cliffs");
            addEntries(allowedBiomes, "repurposed_structures:mansion_savanna", "terralith:hot_shrubland");
            addEntries(allowedBiomes, "repurposed_structures:mansion_savanna", "terralith:sakura_grove");
            addEntries(allowedBiomes, "repurposed_structures:mansion_savanna", "terralith:sakura_valley");
            addEntries(allowedBiomes, "repurposed_structures:mansion_snowy", "terralith:alpine_grove");
            addEntries(allowedBiomes, "repurposed_structures:mansion_snowy", "terralith:alpine_highlands");
            addEntries(allowedBiomes, "repurposed_structures:mansion_snowy", "terralith:cold_shrubland");
            addEntries(allowedBiomes, "repurposed_structures:mansion_snowy", "terralith:snowy_badlands");
            addEntries(allowedBiomes, "repurposed_structures:mansion_snowy", "terralith:snowy_shield");
            addEntries(allowedBiomes, "repurposed_structures:mansion_taiga", "terralith:forested_highlands");
            addEntries(allowedBiomes, "repurposed_structures:mansion_taiga", "terralith:lavender_forest");
            addEntries(allowedBiomes, "repurposed_structures:mansion_taiga", "terralith:lavender_valley");
            addEntries(allowedBiomes, "repurposed_structures:mansion_taiga", "terralith:moonlight_grove");
            addEntries(allowedBiomes, "repurposed_structures:mansion_taiga", "terralith:moonlight_valley");
            addEntries(allowedBiomes, "repurposed_structures:mansion_taiga", "terralith:rocky_shrubland");
            addEntries(allowedBiomes, "repurposed_structures:mansion_taiga", "terralith:wintry_forest");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_birch", "terralith:wintry_forest");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_birch", "terralith:white_cliffs");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_dark_forest", "terralith:mirage_isles");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_desert", "terralith:cave/desert_caves");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_icy", "terralith:cave/frostfire_caves");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_icy", "terralith:cave/ice_caves");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_savanna", "terralith:arid_highlands");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_savanna", "terralith:hot_shrubland");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_savanna", "terralith:sakura_grove");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_savanna", "terralith:sakura_valley");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_savanna", "terralith:savanna_badlands");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_stone", "terralith:amethyst_canyon");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_stone", "terralith:basalt_cliffs");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_stone", "terralith:emerald_peaks");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_stone", "terralith:granite_cliffs");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_stone", "terralith:gravel_desert");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_stone", "terralith:mountain_steppe");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_stone", "terralith:steppe");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_stone", "terralith:shield_clearing");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_stone", "terralith:valley_clearing");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_taiga", "terralith:cloud_forest");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_taiga", "terralith:forested_highlands");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_taiga", "terralith:lavender_forest");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_taiga", "terralith:lavender_valley");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_taiga", "terralith:moonlight_grove");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_taiga", "terralith:moonlight_valley");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_taiga", "terralith:rocky_shrubland");
            addEntries(allowedBiomes, "repurposed_structures:mineshaft_taiga", "terralith:shrubland");
            addEntries(allowedBiomes, "repurposed_structures:outpost_badlands", "terralith:savanna_badlands");
            addEntries(allowedBiomes, "repurposed_structures:outpost_birch", "terralith:temperate_highlands");
            addEntries(allowedBiomes, "repurposed_structures:outpost_birch", "terralith:white_cliffs");
            addEntries(allowedBiomes, "repurposed_structures:outpost_snowy", "terralith:alpine_grove");
            addEntries(allowedBiomes, "repurposed_structures:outpost_snowy", "terralith:alpine_highlands");
            addEntries(allowedBiomes, "repurposed_structures:outpost_snowy", "terralith:cold_shrubland");
            addEntries(allowedBiomes, "repurposed_structures:outpost_snowy", "terralith:snowy_shield");
            addEntries(allowedBiomes, "repurposed_structures:outpost_taiga", "terralith:forested_highlands");
            addEntries(allowedBiomes, "repurposed_structures:outpost_taiga", "terralith:lavender_forest");
            addEntries(allowedBiomes, "repurposed_structures:outpost_taiga", "terralith:lavender_valley");
            addEntries(allowedBiomes, "repurposed_structures:outpost_taiga", "terralith:moonlight_grove");
            addEntries(allowedBiomes, "repurposed_structures:outpost_taiga", "terralith:moonlight_valley");
            addEntries(allowedBiomes, "repurposed_structures:outpost_taiga", "terralith:rocky_shrubland");
            addEntries(allowedBiomes, "repurposed_structures:outpost_taiga", "terralith:wintry_forest");
            addEntries(allowedBiomes, "repurposed_structures:pyramid_badlands", "terralith:savanna_badlands");
            addEntries(allowedBiomes, "repurposed_structures:pyramid_flower_forest", "terralith:blooming_plateau");
            addEntries(allowedBiomes, "repurposed_structures:pyramid_flower_forest", "terralith:blooming_valley");
            addEntries(allowedBiomes, "repurposed_structures:pyramid_snowy", "terralith:alpine_grove");
            addEntries(allowedBiomes, "repurposed_structures:pyramid_snowy", "terralith:alpine_highlands");
            addEntries(allowedBiomes, "repurposed_structures:pyramid_snowy", "terralith:cold_shrubland");
            addEntries(allowedBiomes, "repurposed_structures:pyramid_snowy", "terralith:snowy_shield");
            addEntries(allowedBiomes, "repurposed_structures:village_badlands", "terralith:savanna_badlands");
            addEntries(allowedBiomes, "repurposed_structures:village_birch", "terralith:temperate_highlands");
            addEntries(allowedBiomes, "repurposed_structures:village_birch", "terralith:white_cliffs");
            addEntries(allowedBiomes, "repurposed_structures:village_dark_oak", "terralith:mirage_isles");
            addEntries(allowedBiomes, "repurposed_structures:village_mountains", "terralith:amethyst_canyon");
            addEntries(allowedBiomes, "repurposed_structures:village_mountains", "terralith:basalt_cliffs");
            addEntries(allowedBiomes, "repurposed_structures:village_mountains", "terralith:emerald_peaks");
            addEntries(allowedBiomes, "repurposed_structures:village_mountains", "terralith:granite_cliffs");
            addEntries(allowedBiomes, "repurposed_structures:village_mountains", "terralith:highlands");
            addEntries(allowedBiomes, "repurposed_structures:village_mountains", "terralith:mountain_steppe");
            addEntries(allowedBiomes, "repurposed_structures:village_mountains", "terralith:rocky_mountains");
            addEntries(allowedBiomes, "repurposed_structures:village_mountains", "terralith:steppe");
            addEntries(allowedBiomes, "repurposed_structures:village_mountains", "terralith:stony_spires");
            addEntries(allowedBiomes, "repurposed_structures:village_mountains", "terralith:shield_clearing");
            addEntries(allowedBiomes, "repurposed_structures:village_mountains", "terralith:valley_clearing");
            addEntries(allowedBiomes, "repurposed_structures:witch_hut_birch", "terralith:temperate_highlands");
            addEntries(allowedBiomes, "repurposed_structures:witch_hut_birch", "terralith:white_cliffs");
            addEntries(allowedBiomes, "repurposed_structures:witch_hut_dark_forest", "terralith:mirage_isles");
            addEntries(allowedBiomes, "repurposed_structures:witch_hut_oak", "terralith:brushland");
            addEntries(allowedBiomes, "repurposed_structures:witch_hut_taiga", "terralith:bryce_canyon");
            addEntries(allowedBiomes, "repurposed_structures:witch_hut_taiga", "terralith:cloud_forest");
            addEntries(allowedBiomes, "repurposed_structures:witch_hut_taiga", "terralith:lavender_forest");
            addEntries(allowedBiomes, "repurposed_structures:witch_hut_taiga", "terralith:lavender_valley");
            addEntries(allowedBiomes, "repurposed_structures:witch_hut_taiga", "terralith:moonlight_grove");
            addEntries(allowedBiomes, "repurposed_structures:witch_hut_taiga", "terralith:moonlight_valley");
            addEntries(allowedBiomes, "repurposed_structures:witch_hut_taiga", "terralith:rocky_shrubland");
            addEntries(allowedBiomes, "repurposed_structures:witch_hut_taiga", "terralith:shrubland");
            configVersion = 4;
        }

        configVersion = 4;
        Config.super.save();
    }

    private void addEntries(Map<String, String> map, String key, String entry) {
        // assign entry
        if(map.putIfAbsent(key, entry) != null && !map.get(key).contains(entry)) {
            map.put(key, map.get(key) + ", " + entry); // append entry
        }
    }

    private void removeEntries(Map<String, String> map, String key, String entry) {
        if(map.containsKey(key) && map.get(key).contains(entry)) {
            String newEntry = map.get(key)
                    .replace(entry+", ", "")
                    .replace(entry+",", "")
                    .replace(entry, "");

            if(newEntry.isEmpty()) {
                map.remove(key);
            }
            else{
                map.put(key, newEntry);
            }
        }
    }



    @Comment("""





            // In the key part, specify the name of the structures or configuredfeatures from
            // Repurposed Structures that you want to affect. Then in the value part, add the identifiers
            // or regex for the dimension that you want Repurposed Structures stuff to NOT spawn in.
            
            // Separate multiple entries with a comma.
            // Example usage (the actual config entry to edit are the lines not starting with // further down):
            //  "disallowedDimensions": {
            //    "repurposed_structures:village_birch": "minecraft:overworld, awesome_mod:.+"
            //  }
            
            // In this example, no Birch village will spawn in the overworld because we specified that dimension's identifier.
            // Then the village will not spawn in any of awesome_mod's dimension because "awesome_mod:.+" is regex that will
            // match all dimensions that starts with "awesome_mod:" in their identifier. Powerful stuff!
            
            // Use "all" as the key to affect all of RS's structures and configuredfeatures.
            // You can find dimension identifiers by doing "/execute in" command in game.
            // All of RS's structure identifiers can be found by doing "/locate" command in game.
            // RS's dungeons and wells identifiers can be found here on GitHub:
            //  https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/blob/7f8021cbc073c9919fa0b08dc3b746f9a0e854af/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L268-L290
            """)
    public final Map<String, String> disallowedDimensions = Stream.of(
            new AbstractMap.SimpleEntry<>("all",
                    "the_bumblezone:the_bumblezone, " +
                    "the_aether:the_aether, " +
                    "agape:.+")
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    @Comment("""





            // RS's Structures and ConfiguredFeatures has default settings of what dimensions they are added to.
            // This allowedDimensions config is for adding them to more dimension or for overriding disallowedDimensions config.
            // NOTE: A Structure or ConfiguredFeature must be added to both the dimension and to the biomes in the dimension to spawn.
            
            // In the key part, specify the name of the structures or configuredfeatures from
            // Repurposed Structures that you want to affect. Then in the value part, add the identifiers
            // or regex for the dimension that you want Repurposed Structures stuff to ALWAYS spawn in.
            
            // Separate multiple entries with a comma.
            // Example usage (the actual config entry to edit are the lines not starting with // further down):
            //  "allowedDimensions": {
            //    "repurposed_structures:stronghold_nether": "minecraft:overworld, firey_realms:.+"
            //  },
             
            // In this example, Nether Strongholds will spawn in the overworld because we specified that dimension's identifier.
            // Then the Nether Strongholds will also spawn in any of awesome_mod's dimension because "firey_realms:.+" is regex that will
            // match all dimensions that starts with "firey_realms:" in their identifier. Powerful stuff!
            
            // Use "all" as the key to affect all of RS's structures and configuredfeatures.
            // You can find dimension identifiers by doing "/execute in" command in game.
            // All of RS's structure identifiers can be found by doing "/locate" command in game.
            // RS's dungeons and wells identifiers can be found here on GitHub:
            //  https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/blob/7f8021cbc073c9919fa0b08dc3b746f9a0e854af/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L268-L290
            """)
    public final Map<String, String> allowedDimensions = new HashMap<>();

    @Comment("""





            // RS's Structures and ConfiguredFeatures has default settings of what biomes they are added to.
            // This disallowedBiomes config is for overriding that internal default setting.
            
            // In the key part, specify the name of the structures or configuredfeatures from
            // Repurposed Structures that you want to affect. Then in the value part, add the identifiers
            // or regex for the biomes that you want Repurposed Structures stuff to NOT spawn in.
            // You can also do biome categories as well by doing #swamp to remove from all swamp category biomes.
            
            // Separate multiple entries with a comma.
            // Example usage (the actual config entry to edit are the lines not starting with // further down):
            //  "disallowedBiomes": {
            //    "repurposed_structures:bastion_underground": "minecraft:flower_forest, peaceful_lands:.+, #mushroom"
            //  }
            
            // In this example, Underground Bastions are remvoed from Flower Forest biome because we specified that biomes's identifier.
            // Then the Underground Bastions will also be removed from all of peaceful_lands's biomes because "peaceful_lands:.+" is regex
            // that will match all biomes that starts with "peaceful_lands:" in their identifier. Powerful stuff!
            // Then it will remove the Underground Bastions from all mushroom category biomes including both modded and vanilla's.
            
            // Use "all" as the key to affect all of RS's structures and configuredfeatures.
            // You can find biome identifiers by doing "/locatebiome" command in game.
            // All of RS's structure identifiers can be found by doing "/locate" command in game.
            // RS's dungeons and wells identifiers can be found here on GitHub:
            //  https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/blob/7f8021cbc073c9919fa0b08dc3b746f9a0e854af/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L268-L290
            """)
    public final Map<String, String> disallowedBiomes = new HashMap<>();

    @Comment("""





            // RS's Structures and ConfiguredFeatures has default settings of what biomes they are added to.
            // This allowedBiomes config is for adding them to more biomes or for overriding disallowedBiomes config.
            // NOTE: A Structure or ConfiguredFeature must be added to both the dimension and to the biomes in the dimension to spawn.
            
            // In the key part, specify the name of the structures or configuredfeatures from
            // Repurposed Structures that you want to affect. Then in the value part, add the identifiers
            // or regex for the biomes that you want Repurposed Structures stuff to ALWAYS spawn in.
            // You can also do biome categories as well by doing #forest to add to all forest category biomes.
            
            // Separate multiple entries with a comma.
            // Example usage (the actual config entry to edit are the lines not starting with // further down):
            //  "allowedBiomes": {
            //    "repurposed_structures:mansion_taiga": "minecraft:badlands, fantasy_overworld:.+, #desert"
            //  }
            
            // In this example, Taiga Mansions will spawn in the one Badlands biome because we specified that biomes's identifier.
            // Then the Taiga Mansions will also spawn in all of fantasy_overworld's biomes because "fantasy_overworld:.+" is regex
            // that will match all biomes that starts with "fantasy_overworld:" in their identifier. Powerful stuff!
            // Then it will add the Taiga Mansion to all Desert category biomes including both modded and vanilla's.
            
            // Use "all" as the key to affect all of RS's structures and configuredfeatures.
            // You can find biome identifiers by doing "/locatebiome" command in game.
            // All of RS's structure identifiers can be found by doing "/locate" command in game.
            // RS's dungeons and wells identifiers can be found here on GitHub:
            //  https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/blob/7f8021cbc073c9919fa0b08dc3b746f9a0e854af/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L268-L290
            """)
    public final Map<String, String> allowedBiomes = new HashMap<>();

    @Comment("""







            // for internal use only. Do not change this."""
    )
    public int configVersion = 3;
}
