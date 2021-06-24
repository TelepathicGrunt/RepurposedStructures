package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Mansions")
public class RSMansionsConfig implements ConfigData {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Birch Mansions in Birch biomes.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int mansionBirchMaxChunkDistance = 140;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""
                How rare are Jungle Mansions in Jungle biomes.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int mansionJungleMaxChunkDistance = 160;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Oak Mansions in in forest category
                biomes that are not birch or dark forest.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int mansionOakMaxChunkDistance = 140;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Savanna Mansions in Savanna biomes.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int mansionSavannaMaxChunkDistance = 160;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Taiga Mansions in non-snowy Taiga biomes.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int mansionTaigaMaxChunkDistance = 155;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Desert Mansions in Desert biomes.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int mansionDesertMaxChunkDistance = 160;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Snowy Mansions in Snowy biomes.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int mansionSnowyMaxChunkDistance = 160;
}
