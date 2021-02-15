package com.telepathicgrunt.repurposedstructures.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;


@Config(name = "Outposts")
public class RSOutpostsConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("Add the ID/resource location of the biome you don't want"
            +"\nRS's outposts to spawn in. Separate each ID with a comma ,"
            +"\n"
            +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
    public String blacklistedOutpostBiomes = "";

    @ConfigEntry.Gui.CollapsibleObject
    public Outposts outposts = new Outposts();

    public static class Outposts {


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Nether Brick Outposts in non-warped Nether biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int netherBrickOutpostMaxChunkDistance = 34;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Nether Brick Outposts to modded Nether biomes that other nether outposts don't fit in.")
        public boolean addNetherBrickOutpostToModdedBiomes = true;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Warped Outposts in Warped Nether biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int warpedOutpostMaxChunkDistance = 34;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Warped Outposts to modded Nether Warped biomes.")
        public boolean addWarpedOutpostToModdedBiomes = true;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Crimson Outposts in Warped Nether biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int crimsonOutpostMaxChunkDistance = 34;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Crimson Outposts to modded Nether Warped biomes.")
        public boolean addCrimsonOutpostToModdedBiomes = true;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Birch Outposts to modded Birch biomes.")
        public boolean addOutpostBirchToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Birch Outposts in Birch Forest"
                + "\nbiomes. 1 for spawning in most chunks and 1001"
                + "\nfor none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostBirchMaxChunkDistance = 45;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Jungle Outposts to modded Jungle"
                + "\nbiomes.")
        public boolean addOutpostJungleToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Jungle Outposts in Jungle"
                + "\nbiomes. 1 for spawning in most chunks and"
                + "\n1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostJungleMaxChunkDistance = 45;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Giant Tree Taiga Outposts to modded Giant Tree Taiga"
                + "\nbiomes.")
        public boolean addOutpostGiantTreeTaigaToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Giant Tree Taiga Outposts in Giant Tree Taiga"
                + "\nbiomes. 1 for spawning in most chunks and"
                + "\n1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostGiantTreeTaigaMaxChunkDistance = 45;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Desert Outposts to modded Desert"
                + "\nbiomes.")
        public boolean addOutpostDesertToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Desert Outposts in Desert"
                + "\nbiomes. 1 for spawning in most chunks and"
                + "\n1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostDesertMaxChunkDistance = 45;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Badlands Outposts to modded Badlands"
                + "\nbiomes.")
        public boolean addOutpostBadlandsToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Badlands Outposts in Badlands"
                + "\nbiomes. 1 for spawning in most chunks and"
                + "\n1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostBadlandsMaxChunkDistance = 45;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Snowy Outposts to modded snowy biomes.")
        public boolean addOutpostSnowyToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Snowy Outposts in snowy"
                + "\nbiomes. 1 for spawning in most chunks and"
                + "\n1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostSnowyMaxChunkDistance = 45;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Icy Outposts to modded icy/extremely"
                + "\ncold biomes.")
        public boolean addOutpostIcyToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Icy Outposts in icy/extremely"
                + "\ncold biomes. 1 for spawning in most chunks"
                + "\nand 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostIcyMaxChunkDistance = 41;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Taiga Outposts to modded non-snowy and"
                + "\nnon-giant taiga biomes.")
        public boolean addOutpostTaigaToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Taiga Outposts in non-snowy and"
                + "\nnon-giant taiga biomes. 1 for spawning in most"
                + "\nchunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostTaigaMaxChunkDistance = 45;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Oak Outposts to modded forest biomes that are"
                + "\nnot birch or dark forest.")
        public boolean addOutpostOakToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Oak Outposts in forest biomes that"
                + "\nare not birch or dark forest. 1 for spawning in"
                + "\nmost chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostOakMaxChunkDistance = 45;
    }
}
