package com.telepathicgrunt.repurposedstructures.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;


@Config(name = "Main")
public class RSMainConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("Add the identifiers for the dimension that you want"
            +"\n no Repurposed Structures structure to spawn in."
            +"\n Separate multiple entries with a comma."
            +"\n"
            +"\nExample: \"minecraft:the_end,awesome_mod:awesome_dimension\"")
    public String blacklistedDimensions = "the_bumblezone:the_bumblezone";


    @ConfigEntry.Gui.CollapsibleObject
    public Misc misc = new Misc();

    @ConfigEntry.Gui.CollapsibleObject
    public JungleFortress jungleFortress = new JungleFortress();

    @ConfigEntry.Gui.CollapsibleObject
    public Igloos igloos = new Igloos();

    @ConfigEntry.Gui.CollapsibleObject
    public RuinedPortals ruinedPortals = new RuinedPortals();

    @ConfigEntry.Gui.CollapsibleObject
    public Ruins ruins = new Ruins();
    public static class Misc {
        @ConfigEntry.Gui.Tooltip
        @Comment("Add RS swamp trees to modded biomes of same categories/type.")
        public boolean addSwampTreeToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add RS boulders to modded biomes of same categories/type " +
                "\ninstead of just vanilla biomes.")
        public boolean addBoulderToModdedBiomes = false;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's swamp trees to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedSwampTreeBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's boulders to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedBoulderBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Adds large tree somewhat uncommonly to Swamp biome"
                +"\nand replaces all vanilla trees in Swamp Hills biome.")
        public boolean hornedSwampTree = true;

        @ConfigEntry.Gui.Tooltip
        @Comment("Adds tiny boulders to normal/snowy Taiga Mountains biomes.")
        public boolean boulderTiny = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Replaces boulders in Giant Tree/Spruce Taiga Hills"
                + "\nbiomes with giant boulders that can contain Coal,"
                + "\nIron, and extremely rarely, Diamond Ores.")
        public boolean boulderGiant = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("1 out of ___ chance of Diamond Ore when placing"
                + "\na block in giant Boulders. Lower number = more common."
                + "\nEnter 0 to disable Diamond Ores completely.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000000)
        public int diamondChanceInGiantBoulders = 7000;

        @ConfigEntry.Gui.Tooltip
        @Comment("How many Giant Boulders per chunk. (Can be decimal too)")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        public double giantBouldersPerChunk = 0.5D;


    }

    public static class JungleFortress {


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Jungle Fortresses."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int jungleFortressMaxChunkDistance = 32;

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
        @Comment("% of fortress is Silverfish Blocks."
                + "\nNote: Mossy Stone Bricks block cannot be infested."
                + "\n0 for no Silverfish Blocks and 100 for max spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        public double silverfishSpawnrate = 0.5D;

        @ConfigEntry.Gui.Tooltip
        @Comment("Controls whether loot chests spawn or not.")
        public boolean lootChests = true;

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

}
