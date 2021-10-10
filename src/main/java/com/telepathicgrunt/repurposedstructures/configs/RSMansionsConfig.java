package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Mansions")
public class RSMansionsConfig implements ConfigData {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("""



                Only make supports downward if there is land below.
                (Helps make structure look better in floating island worlds instead of support going down to void at world bottom)""")
        public boolean pillarOnlyToLand = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Birch Mansions in Birch biomes.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int mansionBirchAverageChunkDistance = 180;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""
                How rare are Jungle Mansions in Jungle biomes.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int mansionJungleAverageChunkDistance = 225;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Oak Mansions in in forest category
                biomes that are not birch or dark forest.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int mansionOakAverageChunkDistance = 205;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Savanna Mansions in Savanna biomes.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int mansionSavannaAverageChunkDistance = 225;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Taiga Mansions in non-snowy Taiga biomes.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int mansionTaigaAverageChunkDistance = 205;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Desert Mansions in Desert biomes.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int mansionDesertAverageChunkDistance = 225;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Snowy Mansions in Snowy biomes.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int mansionSnowyAverageChunkDistance = 225;
}
