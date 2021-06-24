package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Outposts")
public class RSOutpostsConfig implements ConfigData {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Nether Brick Outposts in non-warped Nether biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int netherBrickOutpostMaxChunkDistance = 34;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Warped Outposts in Warped Nether biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int warpedOutpostMaxChunkDistance = 34;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Crimson Outposts in Warped Nether biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int crimsonOutpostMaxChunkDistance = 34;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Birch Outposts in Birch Forest"
                + "\nbiomes. 1 for spawning in most chunks and 1001"
                + "\nfor none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostBirchMaxChunkDistance = 45;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Jungle Outposts in Jungle"
                + "\nbiomes. 1 for spawning in most chunks and"
                + "\n1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostJungleMaxChunkDistance = 45;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Giant Tree Taiga Outposts in Giant Tree Taiga"
                + "\nbiomes. 1 for spawning in most chunks and"
                + "\n1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostGiantTreeTaigaMaxChunkDistance = 45;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Desert Outposts in Desert"
                + "\nbiomes. 1 for spawning in most chunks and"
                + "\n1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostDesertMaxChunkDistance = 45;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Badlands Outposts in Badlands"
                + "\nbiomes. 1 for spawning in most chunks and"
                + "\n1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostBadlandsMaxChunkDistance = 45;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Snowy Outposts in snowy"
                + "\nbiomes. 1 for spawning in most chunks and"
                + "\n1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostSnowyMaxChunkDistance = 45;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Icy Outposts in icy/extremely"
                + "\ncold biomes. 1 for spawning in most chunks"
                + "\nand 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostIcyMaxChunkDistance = 41;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Taiga Outposts in non-snowy and"
                + "\nnon-giant taiga biomes. 1 for spawning in most"
                + "\nchunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostTaigaMaxChunkDistance = 45;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Oak Outposts in forest biomes that"
                + "\nare not birch or dark forest. 1 for spawning in"
                + "\nmost chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostOakMaxChunkDistance = 45;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are End Outposts in End biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int outpostEndMaxChunkDistance = 61;

}
