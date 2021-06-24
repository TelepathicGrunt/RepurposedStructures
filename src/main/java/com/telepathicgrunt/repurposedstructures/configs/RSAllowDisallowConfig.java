package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.HashMap;
import java.util.Map;


@Config(name = "Biome/Dimension/Modded Loot")
public class RSAllowDisallowConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("""



            Adds modded loot from vanilla structure's loot tables and injects them into Repurposed Structure's loot tables.
            Example: Snowy Pyramid gets all modded items that vanilla Desert Temple can have""")
    public boolean importModdedItems = true;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            Add the identifiers for Repurposed Structures's loottable you want to
             turn off the automatic modded item importing code for.\s
             Separate multiple entries with a comma.
            Example: "repurposed_structures:chests/mansions/birch,repurposed_structures:chests/mineshafts/jungle\"""")
    public String blacklistedRSLoottablesFromImportingModdedItems = "";

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("""



             If you are in the config GUI in-game and are looking for the configs to
             allow or disallow Repurposed Structure's structures from dimensions
             or biomes, please take a look at the config file in the config folder.
             Cloth Config API cannot show maps so you will need to edit the file itself.""")
    public String seeConfigFileForBiomeAndDimensionAllowDisallow = "";


    @ConfigEntry.Gui.Excluded
    @Comment("""



            In the key part, specify the name of the structures or configuredfeatures from
            Repurposed Structures that you want to affect. Then in the value part, add the identifiers
            or regex for the dimension that you want Repurposed Structures stuff to NOT spawn in.
            
            Separate multiple entries with a comma.
            Example:
            "disallowedDimensions": {
              "repurposed_structures:village_birch": "minecraft:overworld, awesome_mod:.+"
            }
            
            In this example, no Birch village will spawn in the overworld because we specified that dimension's identifier.
            Then the village will not spawn in any of awesome_mod's dimension because "awesome_mod:.+" is regex that will
            match all dimensions that starts with "awesome_mod:" in their identifier. Powerful stuff!
            
            Use "all" as the key to affect all of RS's structures and configuredfeatures.
            You can find dimension identifiers by doing "/execute in" command in game.
            All of RS's structure identifiers can be found by doing "/locate" command in game.
            RS's dungeons and wells identifiers can be found here on GitHub:
              https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/blob/7f8021cbc073c9919fa0b08dc3b746f9a0e854af/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L268-L290
            """)
    public Map<String, String> disallowedDimensions = Map.of(
            "all", "the_bumblezone:the_bumblezone, the_aether:the_aether"
    );

    @ConfigEntry.Gui.Excluded
    @Comment("""



            RS's Structures and ConfiguredFeatures has default settings of what dimensions they are added to.
            This allowedDimensions config is for adding them to more dimension or for overriding disallowedDimensions config.
            NOTE: A Structure or ConfiguredFeature must be added to both the dimension and to the biomes in the dimension to spawn.
            
            In the key part, specify the name of the structures or configuredfeatures from
            Repurposed Structures that you want to affect. Then in the value part, add the identifiers
            or regex for the dimension that you want Repurposed Structures stuff to ALWAYS spawn in.
            
            Separate multiple entries with a comma.
            Example:
            "allowedDimensions": {
              "repurposed_structures:stronghold_nether": "minecraft:overworld, firey_realms:.+"
            },
             
            In this example, Nether Strongholds will spawn in the overworld because we specified that dimension's identifier.
            Then the Nether Strongholds will also spawn in any of awesome_mod's dimension because "firey_realms:.+" is regex that will
            match all dimensions that starts with "firey_realms:" in their identifier. Powerful stuff!
            
            Use "all" as the key to affect all of RS's structures and configuredfeatures.
            You can find dimension identifiers by doing "/execute in" command in game.
            All of RS's structure identifiers can be found by doing "/locate" command in game.
            RS's dungeons and wells identifiers can be found here on GitHub:
              https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/blob/7f8021cbc073c9919fa0b08dc3b746f9a0e854af/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L268-L290
            """)
    public Map<String, String> allowedDimensions = Map.of();

    @ConfigEntry.Gui.Excluded
    @Comment("""



            RS's Structures and ConfiguredFeatures has default settings of what biomes they are added to.
            This disallowedBiomes config is for overriding that internal default setting.
            
            In the key part, specify the name of the structures or configuredfeatures from
            Repurposed Structures that you want to affect. Then in the value part, add the identifiers
            or regex for the biomes that you want Repurposed Structures stuff to NOT spawn in.
            You can also do biome categories as well by doing #swamp to remove from all swamp category biomes.
            
            Separate multiple entries with a comma.
            Example:
            "disallowedBiomes": {
              "repurposed_structures:bastion_underground": "minecraft:flower_forest, peaceful_lands:.+, #mushroom"
            }
            
            In this example, Underground Bastions are remvoed from Flower Forest biome because we specified that biomes's identifier.
            Then the Underground Bastions will also be removed from all of peaceful_lands's biomes because "peaceful_lands:.+" is regex
            that will match all biomes that starts with "peaceful_lands:" in their identifier. Powerful stuff!
            Then it will remove the Underground Bastions from all mushroom category biomes including both modded and vanilla's.
            
            Use "all" as the key to affect all of RS's structures and configuredfeatures.
            You can find biome identifiers by doing "/locatebiome" command in game.
            All of RS's structure identifiers can be found by doing "/locate" command in game.
            RS's dungeons and wells identifiers can be found here on GitHub:
              https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/blob/7f8021cbc073c9919fa0b08dc3b746f9a0e854af/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L268-L290
            """)
    public Map<String, String> disallowedBiomes = Map.of();

    @ConfigEntry.Gui.Excluded
    @Comment("""



            RS's Structures and ConfiguredFeatures has default settings of what biomes they are added to.
            This allowedBiomes config is for adding them to more biomes or for overriding disallowedBiomes config.
            NOTE: A Structure or ConfiguredFeature must be added to both the dimension and to the biomes in the dimension to spawn.
            
            In the key part, specify the name of the structures or configuredfeatures from
            Repurposed Structures that you want to affect. Then in the value part, add the identifiers
            or regex for the biomes that you want Repurposed Structures stuff to ALWAYS spawn in.
            You can also do biome categories as well by doing #forest to add to all forest category biomes.
            
            Separate multiple entries with a comma.
            Example:
            "allowedBiomes": {
              "repurposed_structures:mansion_taiga": "minecraft:badlands, fantasy_overworld:.+, #desert"
            }
            
            In this example, Taiga Mansions will spawn in the one Badlands biome because we specified that biomes's identifier.
            Then the Taiga Mansions will also spawn in all of fantasy_overworld's biomes because "fantasy_overworld:.+" is regex
            that will match all biomes that starts with "fantasy_overworld:" in their identifier. Powerful stuff!
            Then it will add the Taiga Mansion to all Desert category biomes including both modded and vanilla's.
            
            Use "all" as the key to affect all of RS's structures and configuredfeatures.
            You can find biome identifiers by doing "/locatebiome" command in game.
            All of RS's structure identifiers can be found by doing "/locate" command in game.
            RS's dungeons and wells identifiers can be found here on GitHub:
              https://github.com/TelepathicGrunt/RepurposedStructures-Fabric/blob/7f8021cbc073c9919fa0b08dc3b746f9a0e854af/src/main/java/com/telepathicgrunt/repurposedstructures/modinit/RSConfiguredFeatures.java#L268-L290
            """)
    public Map<String, String> allowedBiomes = Map.of();
}
