package com.telepathicgrunt.repurposedstructures.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;


@Config(name = "Main")
public class RSMainConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public Misc misc = new Misc();

    @ConfigEntry.Gui.CollapsibleObject
    public JungleFortress jungleFortress = new JungleFortress();

    @ConfigEntry.Gui.CollapsibleObject
    public Shipwrecks shipwrecks = new Shipwrecks();

    @ConfigEntry.Gui.CollapsibleObject
    public Igloos igloos = new Igloos();


    public static class Misc {
        @ConfigEntry.Gui.Tooltip
        @Comment("Add RS swamp trees to modded biomes of same categories/type.")
        public boolean addSwampTreeToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Add RS boulders to modded biomes of same categories/type " +
                "\ninstead of just vanilla biomes.")
        public boolean addBoulderToModdedBiomes = false;

        @ConfigEntry.Gui.Tooltip(count = 3)
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's swamp trees to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedSwampTreeBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 3)
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's boulders to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedBoulderBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Adds large tree somewhat uncommonly to Swamp biome"
                +"\nand replaces all vanilla trees in Swamp Hills biome.")
        public boolean hornedSwampTree = true;

        @ConfigEntry.Gui.Tooltip
        @Comment("Adds tiny boulders to normal/snowy Taiga Mountains biomes.")
        public boolean boulderTiny = true;

        @ConfigEntry.Gui.Tooltip(count = 3)
        @Comment("Replaces boulders in Giant Tree/Spruce Taiga Hills"
                + "\nbiomes with giant boulders that can contain Coal,"
                + "\nIron, and extremely rarely, Diamond Ores.")
        public boolean boulderGiant = true;

        @ConfigEntry.Gui.Tooltip(count = 3)
        @Comment("1 out of ___ chance of Diamond Ore when placing"
                + "\na block in giant Boulders. Lower number = more common."
                + "\nEnter 0 to disable Diamond Ores completely.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000000)
        public int diamondChanceInGiantBoulders = 7000;

        @ConfigEntry.Gui.Tooltip
        @Comment("How many Giant Boulders per chunk. (Can be decimal too)")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        public double giantBouldersPerChunk = 1.0D;


    }

    public static class JungleFortress {


        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("How rare are Jungle Fortresses."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int jungleFortressSpawnrate = 32;

        @ConfigEntry.Gui.Tooltip
        @Comment("Add Jungle Fortress to modded jungle biomes.")
        public boolean addJungleFortressToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 3)
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's fortresses to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedFortressBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 3)
        @Comment("% of fortress is Silverfish Blocks."
                + "\nNote: Mossy Stone Bricks block cannot be infested."
                + "\n0 for no Silverfish Blocks and 100 for max spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        public double silverfishSpawnrateJF = 0.5D;

        @ConfigEntry.Gui.Tooltip
        @Comment("Controls whether loot chests spawn or not.")
        public boolean lootChestsJF = true;

    }
    public static class Shipwrecks {

        @ConfigEntry.Gui.Tooltip(count = 1)
        @Comment("Add End Shipwreck to modded End biomes.")
        public boolean addEndShipwreckToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 3)
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's shipwrecks to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedShipwreckBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("How rare are End Shipwreck in End Highlands biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int endShipwreckSpawnrate = 15;

    }

    public static class Igloos {

        @ConfigEntry.Gui.Tooltip(count = 3)
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's igloos to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedIglooBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("How rare are Grassy Igloos in Plains and Forests."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int grassyIglooSpawnrate = 20;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Add Grassy Igloos to modded biomes that are"
                + "\nmost likely grassy fields or temperate forests.")
        public boolean addGrassyIglooToModdedBiomes = false;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("How rare are Stone Igloos in Giant Tree Taiga biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int stoneIglooSpawnrate = 20;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Add Stone Igloos to modded biomes that are"
                + "\nmost likely Giant Tree Taiga variants.")
        public boolean addStoneIglooToModdedBiomes = false;

    }
}
