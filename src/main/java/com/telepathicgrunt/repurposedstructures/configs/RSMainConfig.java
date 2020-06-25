package com.telepathicgrunt.repurposedstructures.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;


@Config(name = "Repurposed_Structures-Main")
public class RSMainConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public Misc misc = new Misc();

    @ConfigEntry.Gui.CollapsibleObject
    public JungleFortress jungleFortress = new JungleFortress();

    @ConfigEntry.Gui.CollapsibleObject
    public Temples temples = new Temples();

    @ConfigEntry.Gui.CollapsibleObject
    public Igloos igloos = new Igloos();


    public static class Misc {
        @Comment("\r\n Add the custom features to modded biomes of the same categories/type.")
        public boolean addMiscToModdedBiomes = false;

        @Comment("\r\n Adds tiny boulders to Taiga Mountains and Snowy Taiga Mountains biomes "
                + "\r\n that can contain small amounts of Coal and Iron ores.")
        public boolean boulderTiny = true;

        @Comment("\r\n Replaces boulders in Giant Tree Taiga Hills and Giant Spruce Taiga Hills"
                + "\r\n biomes with a larger boulder that can contain Coal, Iron, and extremely"
                + "\r\n rarely, can also have Diamond Ores.")
        public boolean boulderGiant = true;

        @Comment("\r\n Adds a large tree somewhat uncommonly to Swamp biome and replaces"
                + "\r\n all vanilla trees in Swamp Hills biome with the larger tree.")
        public boolean hornedSwampTree = true;
    }

    public static class JungleFortress {


        @Comment("\r\n How rare are Jungle Fortresses."
                + "\r\n "
                + "\r\n 1 for spawning in most chunks and 1001 for no spawn.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int jungleFortressSpawnrate = 32;

        @Comment("\r\n How often Silverfish Blocks will generate in Jungle Fortress as a percentage."
                + "\r\n Note: Mossy Stone Bricks block cannot be infected by Silverfish"
                + "\n "
                + "\r\n 0 for no Silverfish Blocks and 100 for max spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        public Double silverfishSpawnrateJF = 0.5D;

        @Comment("\r\n Silverfish Mob Spawners generate in Stone Fortresses."
                + "\r\n If turned off, the spawners will become Skeleton spawners.")
        public boolean allowSilverfishSpawnerJF = true;

        @Comment("\r\n Controls whether loot chests spawn or not in Jungle Fortresses.")
        public boolean lootChestsJF = true;

        @Comment("\r\n Add Jungle Fortress to modded jungle biomes.")
        public boolean addJungleFortressToModdedBiomes = false;

    }

    public static class Temples {

        @Comment("\r\n How rare are Nether Temples in Nether."
                + "\n "
                + "\r\n 1 for spawning in most chunks and 1001 for no spawn.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int netherTempleSpawnrate = 20;

        @Comment("\r\n Controls whether loot chests spawn or not in Nether Temples.")
        public boolean lootChestsNT = true;

        @Comment("\r\n Add Jungle Fortress to modded jungle biomes.")
        public boolean addNetherTempleToModdedBiomes = false;

        @Comment("\r\n How rare are Nether Temples in Nether."
                + "\n "
                + "\r\n 1 for spawning in most chunks and 1001 for no spawn.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int badlandsTempleSpawnrate = 20;

        @Comment("\r\n Controls whether loot chests spawn or not in Badlands Temples.")
        public boolean lootChestsBT = true;

        @Comment("\r\n Add Jungle Fortress to modded jungle biomes.")
        public boolean addBadlandsTempleToModdedBiomes = false;
    }

    public static class Igloos {

        @Comment("\r\n How rare are Grassy Igloos in Plains and Forests."
                + "\n "
                + "\r\n 1 for spawning in most chunks and 1001 for no spawn.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int grassyIglooSpawnrate = 20;

        @Comment("\r\n Add Grassy Igloos to modded biomes that are"
                + "\r\n most likely grassy fields or temperate forests.")
        public boolean addGrassyIglooToModdedBiomes = false;

        @Comment("\r\n How rare are Stone Igloos in Giant Tree Taiga biomes."
                + "\n "
                + "\r\n 1 for spawning in most chunks and 1001 for no spawn.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int stoneIglooSpawnrate = 20;

        @Comment("\r\n Add Stone Igloos to modded biomes that are"
                + "\r\n most likely Giant Tree Taiga variants.")
        public boolean addStoneIglooToModdedBiomes = false;

    }
}
