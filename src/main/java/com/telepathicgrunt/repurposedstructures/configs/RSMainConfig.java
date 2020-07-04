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
        @ConfigEntry.Gui.Tooltip
        @Comment("Add RS features to modded biomes of same categories/type.")
        public boolean addMiscToModdedBiomes = false;

        @ConfigEntry.Gui.Tooltip
        @Comment("Adds tiny boulders to normal/snowy Taiga Mountains biomes.")
        public boolean boulderTiny = true;

        @ConfigEntry.Gui.Tooltip(count = 3)
        @Comment("Replaces boulders in Giant Tree/Spruce Taiga Hills"
                + "\nbiomes with giant boulders that can contain Coal,"
                + "\nIron, and extremely rarely, Diamond Ores.")
        public boolean boulderGiant = true;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Adds large tree somewhat uncommonly to Swamp biome"
                +"\nand replaces all vanilla trees in Swamp Hills biome.")
        public boolean hornedSwampTree = true;
    }

    public static class JungleFortress {


        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("How rare are Jungle Fortresses."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int jungleFortressSpawnrate = 32;

        @ConfigEntry.Gui.Tooltip(count = 3)
        @Comment("% of fortress is Silverfish Blocks."
                + "\nNote: Mossy Stone Bricks block cannot be infested."
                + "\n0 for no Silverfish Blocks and 100 for max spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
        public Double silverfishSpawnrateJF = 0.5D;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Allow generation of 2 Silverfish Mob Spawners."
                + "\nIf turned off, the spawners will become Skeleton spawners.")
        public boolean allowSilverfishSpawnerJF = true;

        @ConfigEntry.Gui.Tooltip
        @Comment("Controls whether loot chests spawn or not.")
        public boolean lootChestsJF = true;

        @ConfigEntry.Gui.Tooltip
        @Comment("Add Jungle Fortress to modded jungle biomes.")
        public boolean addJungleFortressToModdedBiomes = false;

    }

    public static class Temples {

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("How rare are Nether Pyramids in Nether."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int netherPyramidSpawnrate = 32;

        @ConfigEntry.Gui.Tooltip
        @Comment("Add Nether Pyramids to modded Nether biomes.")
        public boolean addNetherPyramidToModdedBiomes = false;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("How rare are Nether Temples in Nether."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int netherTempleSpawnrate = 26;

        @ConfigEntry.Gui.Tooltip
        @Comment("Controls whether loot chests spawn or not in Nether Temples.")
        public boolean lootChestsNT = true;

        @ConfigEntry.Gui.Tooltip
        @Comment("Add Nether Temples to modded Nether biomes.")
        public boolean addNetherTempleToModdedBiomes = false;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("How rare are Nether Temples in Nether."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int badlandsTempleSpawnrate = 20;

        @ConfigEntry.Gui.Tooltip
        @Comment("Controls whether loot chests spawn or not in Badlands Temples.")
        public boolean lootChestsBT = true;

        @ConfigEntry.Gui.Tooltip
        @Comment("Add Badlands Temple to modded jungle biomes.")
        public boolean addBadlandsTempleToModdedBiomes = false;

    }

    public static class Igloos {

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
