package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Main")
public class RSMainConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("Add the identifiers for the dimension that you want"
            +"\n no Repurposed Structures structure to spawn in."
            +"\n Separate multiple entries with a comma."
            +"\nExample: \"minecraft:the_end,awesome_mod:awesome_dimension\"")
    public String blacklistedDimensions = "the_bumblezone:the_bumblezone,the_aether:the_aether";

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("Adds modded loot from vanilla structure's loot tables and injects them into Repurposed Structure's loot tables."
            +"\nExample: Snowy Pyramid gets all modded items that vanilla Desert Temple can have")
    public boolean importModdedItems = true;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("Add the identifiers for Repurposed Structures's loottable you want to"
            +"\n turn off the automatic modded item importing code for. "
            +"\n Separate multiple entries with a comma."
            +"\nExample: \"repurposed_structures:chests/mansion/birch,repurposed_structures:chests/mineshaft/jungle\"")
    public String blacklistedRSLoottablesFromImportingModdedItems = "";

    @ConfigEntry.Gui.CollapsibleObject
    public JungleFortress jungleFortress = new JungleFortress();

    @ConfigEntry.Gui.CollapsibleObject
    public Igloos igloos = new Igloos();

    @ConfigEntry.Gui.CollapsibleObject
    public RuinedPortals ruinedPortals = new RuinedPortals();

    @ConfigEntry.Gui.CollapsibleObject
    public Ruins ruins = new Ruins();

    @ConfigEntry.Gui.CollapsibleObject
    public Cities cities = new Cities();

    public static class JungleFortress {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Jungle Fortresses."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int jungleFortressMaxChunkDistance = 50;

        @ConfigEntry.Gui.Tooltip
        @Comment("Add Jungle Fortress to modded jungle biomes.")
        public boolean addJungleFortressToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's fortresses to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedFortressBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Size of the fortress. This is how many pieces long a branch can be from the start piece.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 18)
        public int jungleFortressSize = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Min Y height that the starting point can spawn at."
                +"\nDefault is 56.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
        public int jungleFortressMinHeight = 56;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Max Y height that the starting point can spawn at."
                +"\nDefault is 63."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
        public int jungleFortressMaxHeight = 63;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How far above or below the fortress's pieces can generate away from the center piece.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
        public int jungleFortressVerticalRange = 27;
    }

    public static class Igloos {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's igloos to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedIglooBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Grassy Igloos in Plains and Forests."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int grassyIglooMaxChunkDistance = 20;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Grassy Igloos to modded biomes that are"
                + "\nmost likely grassy fields or temperate forests.")
        public boolean addGrassyIglooToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Stone Igloos in Giant Tree Taiga biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int stoneIglooMaxChunkDistance = 20;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Stone Igloos to modded biomes that are"
                + "\nmost likely Giant Tree Taiga variants.")
        public boolean addStoneIglooToModdedBiomes = true;

    }

    public static class RuinedPortals {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's ruined portals to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedRuinedPortalsBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add End themed ruined portals to modded"
                + "\nEnd category biomes.")
        public boolean addRuinedPortalEndToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are End themed Ruined Portals in"
                + "\nEnd category biomes. 1 for spawning in most"
                + "\nchunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int ruinedPortalEndMaxChunkDistance = 57;
    }


    public static class Ruins {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's Ruins to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedRuinsBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Nether Ruins to modded End category biomes.")
        public boolean addRuinsNetherToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Nether Ruins in"
                + "\nNether category biomes. 1 for spawning in most"
                + "\nchunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int ruinsNetherMaxChunkDistance = 35;
    }

    public static class Cities {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's cities to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedCitiesBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Nether Cities to modded Nether biomes.")
        public boolean addCityNetherToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Nether Cities in Nether biomes. 1"
                + "\nfor spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int cityNetherMaxChunkDistance = 120;
    }
}
