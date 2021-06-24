package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Temples")
public class RSPyramidsConfig implements ConfigData {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""
                How rare are Nether Pyramids in Nether.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int netherPyramidMaxChunkDistance = 37;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""
                How rare are Badlands Pyramid in Badlands biomes.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int badlandsPyramidMaxChunkDistance = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""
                How rare are Snowy Pyramid in snowy biomes.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidSnowyMaxChunkDistance = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""
                How rare are End Pyramid in End biomes.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidEndMaxChunkDistance = 68;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""
                How rare are Icy Pyramid in biomes that are super cold or has icy words in its name.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidIcyMaxChunkDistance = 37;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("How rare are Jungle Pyramid in Jungle biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidJungleMaxChunkDistance = 44;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""
                How rare are Mushroom Pyramid in Mushroom biomes.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidMushroomMaxChunkDistance = 24;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""
                How rare are Ocean Pyramid in Ocean biomes.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidOceanMaxChunkDistance = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""
                How rare are Giant Tree Taiga Pyramid in Giant Tree Taiga biomes.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidGiantTreeTaigaMaxChunkDistance = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""
                How rare are Flower Forest Pyramid in Flower Forest biomes.
                1 for spawning in most chunks and 1001 for none.
                """)
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidFlowerForestMaxChunkDistance = 36;
}
