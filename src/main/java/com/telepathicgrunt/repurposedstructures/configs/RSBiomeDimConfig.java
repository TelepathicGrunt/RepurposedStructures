package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.configs.omegaconfig.api.Comment;
import com.telepathicgrunt.repurposedstructures.configs.omegaconfig.api.Config;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            String blacklistedDims = disallowedDimensions.get("all");
            disallowedDimensions.put("all", blacklistedDims.replace("theabyss:theabyssdim, theabyss:theabyssiceworld, theabyss:death, theabyss:the_end_of_time, theabyss:the_end_of_time_2, theabyss:dream, theabyss:dream_2, theabyss:dream_3, theabyss:radio, theabyss:theabyssdimgroundlands, theabyss:theabyssdimskylands",
                    "theabyss:.+"));

            // Add new config entries if they don't exist as we update the config version
            addEntries(disallowedDimensions, "repurposed_structures:village_badlands", "aoa3:barathos");
            addEntries(disallowedDimensions, "repurposed_structures:outpost_badlands", "aoa3:barathos");
            addEntries(disallowedDimensions, "repurposed_structures:well_badlands", "aoa3:barathos");
            addEntries(disallowedDimensions, "repurposed_structures:mineshaft_desert", "atum:atum");
            addEntries(disallowedDimensions, "repurposed_structures:outpost_jungle", "tropicraft:tropics");
            addEntries(disallowedDimensions, "repurposed_structures:mansion_jungle", "tropicraft:tropics");
            addEntries(disallowedDimensions, "repurposed_structures:mineshaft_desert", "lotr:middle_earth");
            addEntries(disallowedDimensions, "repurposed_structures:mansion_desert", "lotr:middle_earth");
            addEntries(disallowedDimensions, "repurposed_structures:dungeon_desert", "lotr:middle_earth");
            addEntries(disallowedDimensions, "repurposed_structures:ruins_land_hot", "lotr:middle_earth");
            addEntries(disallowedDimensions, "repurposed_structures:outpost_desert", "lotr:middle_earth");
            addEntries(disallowedDimensions, "repurposed_structures:outpost_oak", "lotr:middle_earth");
            addEntries(disallowedDimensions, "repurposed_structures:outpost_snowy", "lotr:middle_earth");

            addEntries(allowedDimensions, "repurposed_structures:bastion_underground", "dystopia:dystopia");
            addEntries(allowedDimensions, "repurposed_structures:bastion_underground", "elvenation:elvenia_dimension");
            addEntries(allowedDimensions, "repurposed_structures:bastion_underground", "futurepack:tyros");
            addEntries(allowedDimensions, "repurposed_structures:ruins_land_warm", "dystopia:dystopia");
            addEntries(allowedDimensions, "repurposed_structures:ruins_land_warm", "elvenation:elvenia_dimension");
            addEntries(allowedDimensions, "repurposed_structures:ruins_land_warm", "futurepack:tyros");
            addEntries(allowedDimensions, "repurposed_structures:igloo_grassy", "elvenation:elvenia_dimension");
            addEntries(allowedDimensions, "repurposed_structures:mineshaft_jungle", "futurepack:tyros");
            addEntries(allowedDimensions, "repurposed_structures:pyramid_jungle", "futurepack:tyros");
            addEntries(allowedDimensions, "repurposed_structures:dungeon_jungle", "futurepack:tyros");
            addEntries(allowedDimensions, "repurposed_structures:well_mossy_stone", "futurepack:tyros");

            addEntries(disallowedBiomes, "repurposed_structures:village_oak", "lotr:lindon_woodlands");
            addEntries(disallowedBiomes, "repurposed_structures:village_oak", "lotr:snowy_northlands_forest");
            addEntries(disallowedBiomes, "repurposed_structures:witch_hut_oak", "lotr:lindon_woodlands");
            addEntries(disallowedBiomes, "repurposed_structures:witch_hut_oak", "lotr:snowy_northlands_forest");

            addEntries(allowedBiomes, "repurposed_structures:village_birch", "lotr:lindon_woodlands");
            addEntries(allowedBiomes, "repurposed_structures:witch_hut_birch", "lotr:lindon_woodlands");
            addEntries(allowedBiomes, "repurposed_structures:witch_hut_taiga", "lotr:snowy_northlands_forest");
            configVersion = 2;
        }

        if(configVersion == 2){
            removeEntries(disallowedDimensions, "repurposed_structures:mineshaft_desert", "lotr:middle_earth");
            removeEntries(disallowedDimensions, "repurposed_structures:mansion_desert", "lotr:middle_earth");
            removeEntries(disallowedDimensions, "repurposed_structures:dungeon_desert", "lotr:middle_earth");
            removeEntries(disallowedDimensions, "repurposed_structures:ruins_land_hot", "lotr:middle_earth");
            removeEntries(disallowedDimensions, "repurposed_structures:outpost_desert", "lotr:middle_earth");
            removeEntries(disallowedDimensions, "repurposed_structures:outpost_oak", "lotr:middle_earth");
            removeEntries(disallowedDimensions, "repurposed_structures:outpost_snowy", "lotr:middle_earth");
            removeEntries(disallowedBiomes, "repurposed_structures:village_oak", "lotr:lindon_woodlands");
            removeEntries(disallowedBiomes, "repurposed_structures:village_oak", "lotr:snowy_northlands_forest");
            removeEntries(disallowedBiomes, "repurposed_structures:witch_hut_oak", "lotr:lindon_woodlands");
            removeEntries(disallowedBiomes, "repurposed_structures:witch_hut_oak", "lotr:snowy_northlands_forest");
            removeEntries(allowedBiomes, "repurposed_structures:village_birch", "lotr:lindon_woodlands");
            removeEntries(allowedBiomes, "repurposed_structures:witch_hut_birch", "lotr:lindon_woodlands");
            removeEntries(allowedBiomes, "repurposed_structures:witch_hut_taiga", "lotr:snowy_northlands_forest");
            removeEntries(disallowedBiomes, "repurposed_structures:village_oak", "lotr:lindon_woodlands");
            removeEntries(disallowedBiomes, "repurposed_structures:village_oak", "lotr:snowy_northlands_forest");
            removeEntries(allowedBiomes, "repurposed_structures:village_birch", "lotr:lindon_woodlands");

            addEntries(disallowedDimensions, "all", "lotr:middle_earth");
            addEntries(allowedDimensions, "repurposed_structures:pyramid_snowy", "lotr:middle_earth");
            addEntries(allowedDimensions, "repurposed_structures:warm_land_ruins", "lotr:middle_earth");
            addEntries(allowedDimensions, "repurposed_structures:well_forest", "lotr:middle_earth");
            addEntries(allowedDimensions, "repurposed_structures:well_snow", "lotr:middle_earth");
            addEntries(allowedDimensions, "repurposed_structures:well_mossy_stone", "lotr:middle_earth");
        }

        if(configVersion == 3){
            fixKeyEntry(allowedDimensions, "repurposed_structures:dungeon_jungle", "repurposed_structures:dungeons_jungle");
            fixKeyEntry(allowedDimensions, "repurposed_structures:warm_land_ruins", "repurposed_structures:ruins_land_warm");
        }

        if(configVersion == 4){
            addEntries(disallowedBiomes, "repurposed_structures:village_oak", "vampirism:vampire_forest");
            addEntries(disallowedBiomes, "repurposed_structures:witch_hut_oak", "vampirism:vampire_forest");
            addEntries(disallowedBiomes, "repurposed_structures:outpost_oak", "vampirism:vampire_forest");
            addEntries(disallowedBiomes, "repurposed_structures:well_forest", "vampirism:vampire_forest");
        }

        if(configVersion == 5){
            addEntries(disallowedDimensions, "all", "thebeginning:+");
        }

        configVersion = 6;
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


    @Comment("\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n// In the key part, specify the name of the structures or configuredfeatures from"+
            "\n// Repurposed Structures that you want to affect. Then in the value part, add the identifiers"+
            "\n// or regex for the dimension that you want Repurposed Structures stuff to NOT spawn in."+
            "\n"+
            "\n// Separate multiple entries with a comma."+
            "\n// Example usage (the actual config entry to edit are the lines not starting with // further down):"+
            "\n//  \"disallowedDimensions\": {"+
            "\n//    \"repurposed_structures:village_birch\": \"minecraft:overworld, awesome_mod:.+\""+
            "\n//  }"+
            "\n"+
            "\n// In this example, no Birch village will spawn in the overworld because we specified that dimension's identifier."+
            "\n// Then the village will not spawn in any of awesome_mod's dimension because \"awesome_mod:.+\" is regex that will"+
            "\n// match all dimensions that starts with \"awesome_mod:\" in their identifier. Powerful stuff!"+
            "\n"+
            "\n// Use \"all\" as the key to affect all of RS's structures and configuredfeatures."+
            "\n// You can find dimension identifiers by doing \"/execute in\" command in game."+
            "\n// All of RS's structure identifiers can be found by doing \"/locate\" command in game."+
            "\n// RS's dungeons and wells identifiers can be found here on GitHub:"+
            "\n//  https://github.com/TelepathicGrunt/RepurposedStructures/blob/27c8c23d5b6ee1ba1f894df874d62e5982d39fd5/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L251-L273"
    )
    public final Map<String, String> disallowedDimensions = Stream.of(
            new AbstractMap.SimpleEntry<>("all",
                    "the_bumblezone:the_bumblezone, " +
                    "twilightforest:twilightforest, " +
                    "undergarden:undergarden, " +
                    "the_midnight:the_midnight, " +
                    "advancedrocketry:space, " +
                    "theabyss:.+, " +
                    "pokecube:secret_base, " +
                    "pokecube_legends:distorted_world, " +
                    "pokecube_legends:ultraspace, " +
                    "dystopia:dystopia, " +
                    "elvenation:elvenia_dimension, " +
                    "futurepack:.+, " +
                    "the_afterlight:.+, " +
                    "lotr:middle_earth," +
                    "thebeginning:+"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:village_badlands", "aoa3:barathos"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:outpost_badlands", "aoa3:barathos"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:well_badlands", "aoa3:barathos"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:mineshaft_desert", "atum:atum")
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    @Comment("\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n// RS's Structures and ConfiguredFeatures has default settings of what dimensions they are added to."+
            "\n// This allowedDimensions config is for adding them to more dimension or for overriding disallowedDimensions config."+
            "\n// NOTE: A Structure or ConfiguredFeature must be added to both the dimension and to the biomes in the dimension to spawn."+
            "\n"+
            "\n// In the key part, specify the name of the structures or configuredfeatures from"+
            "\n// Repurposed Structures that you want to affect. Then in the value part, add the identifiers"+
            "\n// or regex for the dimension that you want Repurposed Structures stuff to ALWAYS spawn in."+
            "\n"+
            "\n// Separate multiple entries with a comma."+
            "\n// Example usage (the actual config entry to edit are the lines not starting with // further down):"+
            "\n//  \"allowedDimensions\": {"+
            "\n//    \"repurposed_structures:stronghold_nether\": \"minecraft:overworld, firey_realms:.+\""+
            "\n//  },"+
            "\n"+
            "\n// In this example, Nether Strongholds will spawn in the overworld because we specified that dimension's identifier."+
            "\n// Then the Nether Strongholds will also spawn in any of awesome_mod's dimension because \"firey_realms:.+\" is regex that will"+
            "\n// match all dimensions that starts with \"firey_realms:\" in their identifier. Powerful stuff!"+
            "\n"+
            "\n// Use \"all\" as the key to affect all of RS's structures and configuredfeatures."+
            "\n// You can find dimension identifiers by doing \"/execute in\" command in game."+
            "\n// All of RS's structure identifiers can be found by doing \"/locate\" command in game."+
            "\n// RS's dungeons and wells identifiers can be found here on GitHub:"+
            "\n//  https://github.com/TelepathicGrunt/RepurposedStructures/blob/27c8c23d5b6ee1ba1f894df874d62e5982d39fd5/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L251-L273"
    )
    public final Map<String, String> allowedDimensions = Stream.of(
            new AbstractMap.SimpleEntry<>("repurposed_structures:bastion_underground", "dystopia:dystopia, elvenation:elvenia_dimension, futurepack:tyros"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:ruins_land_warm", "dystopia:dystopia, elvenation:elvenia_dimension, futurepack:tyros, lotr:middle_earth"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:igloo_grassy", "elvenation:elvenia_dimension"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:mineshaft_jungle", "futurepack:tyros"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:pyramid_jungle", "futurepack:tyros"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:pyramid_snowy", "lotr:middle_earth"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:dungeons_jungle", "futurepack:tyros"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:well_forest", "lotr:middle_earth"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:well_snow", "lotr:middle_earth"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:well_mossy_stone", "futurepack:tyros, lotr:middle_earth")
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    @Comment("\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n// RS's Structures and ConfiguredFeatures has default settings of what biomes they are added to."+
            "\n// This disallowedBiomes config is for overriding that internal default setting."+
            "\n"+
            "\n// In the key part, specify the name of the structures or configuredfeatures from"+
            "\n// Repurposed Structures that you want to affect. Then in the value part, add the identifiers"+
            "\n// or regex for the biomes that you want Repurposed Structures stuff to NOT spawn in."+
            "\n// You can also do biome categories as well by doing #swamp to remove from all swamp category biomes."+
            "\n"+
            "\n// Separate multiple entries with a comma."+
            "\n// Example usage (the actual config entry to edit are the lines not starting with // further down):"+
            "\n//  \"disallowedBiomes\": {"+
            "\n//    \"repurposed_structures:bastion_underground\": \"minecraft:flower_forest, peaceful_lands:.+, #mushroom\""+
            "\n//  }"+
            "\n"+
            "\n// In this example, Underground Bastions are remvoed from Flower Forest biome because we specified that biomes's identifier."+
            "\n// Then the Underground Bastions will also be removed from all of peaceful_lands's biomes because \"peaceful_lands:.+\" is regex"+
            "\n// that will match all biomes that starts with \"peaceful_lands:\" in their identifier. Powerful stuff!"+
            "\n// Then it will remove the Underground Bastions from all mushroom category biomes including both modded and vanilla's."+
            "\\n"+
            "\n// Use \"all\" as the key to affect all of RS's structures and configuredfeatures."+
            "\n// You can find biome identifiers by doing \"/locatebiome\" command in game."+
            "\n// All of RS's structure identifiers can be found by doing \"/locate\" command in game."+
            "\n// RS's dungeons and wells identifiers can be found here on GitHub:"+
            "\n//  https://github.com/TelepathicGrunt/RepurposedStructures/blob/27c8c23d5b6ee1ba1f894df874d62e5982d39fd5/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L251-L273"
    )
    public final Map<String, String> disallowedBiomes = Stream.of(
            new AbstractMap.SimpleEntry<>("repurposed_structures:village_oak", "vampirism:vampire_forest"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:witch_hut_oak", "vampirism:vampire_forest"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:outpost_oak", "vampirism:vampire_forest"),
            new AbstractMap.SimpleEntry<>("repurposed_structures:well_forest", "vampirism:vampire_forest")
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    @Comment("\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n// RS's Structures and ConfiguredFeatures has default settings of what biomes they are added to."+
            "\n// This allowedBiomes config is for adding them to more biomes or for overriding disallowedBiomes config."+
            "\n// NOTE: A Structure or ConfiguredFeature must be added to both the dimension and to the biomes in the dimension to spawn."+
            "\n"+
            "\n// In the key part, specify the name of the structures or configuredfeatures from"+
            "\n// Repurposed Structures that you want to affect. Then in the value part, add the identifiers"+
            "\n// or regex for the biomes that you want Repurposed Structures stuff to ALWAYS spawn in."+
            "\n// You can also do biome categories as well by doing #forest to add to all forest category biomes."+
            "\n"+
            "\n// Separate multiple entries with a comma."+
            "\n// Example usage (the actual config entry to edit are the lines not starting with // further down):"+
            "\n//  \"allowedBiomes\": {"+
            "\n//    \"repurposed_structures:mansion_taiga\": \"minecraft:badlands, fantasy_overworld:.+, #desert\""+
            "\n//  }"+
            "\n"+
            "\n// In this example, Taiga Mansions will spawn in the one Badlands biome because we specified that biomes's identifier."+
            "\n// Then the Taiga Mansions will also spawn in all of fantasy_overworld's biomes because \"fantasy_overworld:.+\" is regex"+
            "\n// that will match all biomes that starts with \"fantasy_overworld:\" in their identifier. Powerful stuff!"+
            "\n// Then it will add the Taiga Mansion to all Desert category biomes including both modded and vanilla's."+
            "\n"+
            "\n// Use \"all\" as the key to affect all of RS's structures and configuredfeatures."+
            "\n// You can find biome identifiers by doing \"/locatebiome\" command in game."+
            "\n// All of RS's structure identifiers can be found by doing \"/locate\" command in game."+
            "\n// RS's dungeons and wells identifiers can be found here on GitHub:"+
            "\n//  https://github.com/TelepathicGrunt/RepurposedStructures/blob/27c8c23d5b6ee1ba1f894df874d62e5982d39fd5/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L251-L273"
    )
    public final Map<String, String> allowedBiomes = new HashMap<>();


    @Comment("\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n"+
            "\n// for internal use only. Do not change this."
    )
    public int configVersion = 6;
}
