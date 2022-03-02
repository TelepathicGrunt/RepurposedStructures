package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.configs.omegaconfig.api.Comment;
import com.telepathicgrunt.repurposedstructures.configs.omegaconfig.api.Config;

import java.util.HashMap;
import java.util.Map;

public class RSBiomeDimConfig implements Config {

    @Override
    public String getName() {
        return "repurposed_structures-forge/biome_dimension_allow_disallow_configs";
    }

    @Override
    public String getExtension() {
        return "json5";
    }

    @Override
    public void save() {

        if(configVersion == 1){
            // Simplify abyss config entries
            addEntries(disallowedDimensions, "all", "theabyss:.+");
            addEntries(disallowedDimensions, "all", "the_bumblezone:the_bumblezone");
            addEntries(disallowedDimensions, "all", "twilightforest:twilightforest");
            addEntries(disallowedDimensions, "all", "undergarden:undergarden");
            addEntries(disallowedDimensions, "all", "the_midnight:the_midnight");
            addEntries(disallowedDimensions, "all", "advancedrocketry:space");
            addEntries(disallowedDimensions, "all", "pokecube:secret_base");
            addEntries(disallowedDimensions, "all", "pokecube_legends:distorted_world");
            addEntries(disallowedDimensions, "all", "pokecube_legends:ultraspace");
            addEntries(disallowedDimensions, "all", "dystopia:dystopia");
            addEntries(disallowedDimensions, "all", "elvenation:elvenia_dimension");
            addEntries(disallowedDimensions, "all", "futurepack:.+");
            addEntries(disallowedDimensions, "all", "the_afterlight:.+");
            addEntries(disallowedDimensions, "all", "lotr:middle_earth");
            addEntries(disallowedDimensions, "all", "thebeginning:.+");

            // Add new config entries if they don't exist as we update the config version
            addEntries(disallowedDimensions, "repurposed_structures:well_badlands", "aoa3:barathos");
            addEntries(disallowedDimensions, "repurposed_structures:dungeons_desert", "lotr:middle_earth");

            addEntries(allowedDimensions, "repurposed_structures:dungeons_jungle", "futurepack:tyros");
            addEntries(allowedDimensions, "repurposed_structures:well_mossy_stone", "futurepack:tyros");
            configVersion = 2;
        }

        if(configVersion == 2){
            removeEntries(disallowedDimensions, "repurposed_structures:dungeons_desert", "lotr:middle_earth");
            addEntries(disallowedDimensions, "all", "lotr:middle_earth");
            addEntries(allowedDimensions, "repurposed_structures:well_forest", "lotr:middle_earth");
            addEntries(allowedDimensions, "repurposed_structures:well_snow", "lotr:middle_earth");
            addEntries(allowedDimensions, "repurposed_structures:well_mossy_stone", "lotr:middle_earth");
            configVersion = 3;
        }

        if(configVersion == 3){
            configVersion = 4;
        }

        if(configVersion == 4){
            configVersion = 5;
        }

        if(configVersion == 5){
            addEntries(disallowedDimensions, "all", "thebeginning:.+");
            configVersion = 6;
        }

        // Oops. have to skip this number
        if(configVersion == 6){
            configVersion = 7;
        }

        if(configVersion == 7) {
            addEntries(disallowedBiomes, "repurposed_structures:dungeons_badlands", "terralith:snowy_badlands");
            addEntries(disallowedBiomes, "repurposed_structures:dungeons_snow", "terralith:gravel_desert");
            addEntries(disallowedBiomes, "repurposed_structures:dungeons_desert", "terralith:red_oasis");
            addEntries(disallowedBiomes, "repurposed_structures:dungeons_neutral_ocean", "terralith:skylands");

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
            configVersion = 8;
        }

        if(configVersion == 8) {
            removeEntries(allowedBiomes, "repurposed_structures:dungeons_snow", "terralith:alpine_highlands");
            configVersion = 9;
        }

        if(configVersion == 9) {
            configVersion = 10;
        }

        configVersion = 10;
        Config.super.save();
    }

    private void fixKeyEntry(Map<String, String> map, String oldKey, String newKey){
        for(String entry : map.get(oldKey).split(",")){
            addEntries(map, newKey, entry.trim());
        }
        map.remove(oldKey);
    }

    private void addEntries(Map<String, String> map, String key, String entry){
        // assign entry
        if(map.putIfAbsent(key, entry) != null && !map.get(key).contains(entry)){
            map.put(key, map.get(key) + ", " + entry); // append entry
        }
    }

    private void removeEntries(Map<String, String> map, String key, String entry){
        if(map.containsKey(key) && map.get(key).contains(entry)){
            String newEntry = map.get(key)
                    .replace(entry+", ", "")
                    .replace(entry+",", "")
                    .replace(entry, "");

            if(newEntry.isEmpty()){
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
            //  https://github.com/TelepathicGrunt/RepurposedStructures/blob/27c8c23d5b6ee1ba1f894df874d62e5982d39fd5/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L251-L273"""
    )
    public final Map<String, String> disallowedDimensions = new HashMap<>();

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
            //  https://github.com/TelepathicGrunt/RepurposedStructures/blob/27c8c23d5b6ee1ba1f894df874d62e5982d39fd5/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L251-L273"""
    )
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
            // Then it will remove the Underground Bastions from all mushroom category biomes including both modded and vanilla's.\\n
            // Use "all" as the key to affect all of RS's structures and configuredfeatures.
            // You can find biome identifiers by doing "/locatebiome" command in game.
            // All of RS's structure identifiers can be found by doing "/locate" command in game.
            // RS's dungeons and wells identifiers can be found here on GitHub:
            //  https://github.com/TelepathicGrunt/RepurposedStructures/blob/27c8c23d5b6ee1ba1f894df874d62e5982d39fd5/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L251-L273"""
    )
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
            //  https://github.com/TelepathicGrunt/RepurposedStructures/blob/27c8c23d5b6ee1ba1f894df874d62e5982d39fd5/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L251-L273"""
    )
    public final Map<String, String> allowedBiomes = new HashMap<>();


    @Comment("""







            // for internal use only. Do not change this."""
    )
    public int configVersion = 1; // DO NOT TOUCH ANYMORE
}
