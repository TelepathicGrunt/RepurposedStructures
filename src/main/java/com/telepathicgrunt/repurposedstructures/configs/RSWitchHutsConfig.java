package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "WitchHuts")
public class RSWitchHutsConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public MaxChunkDistance maxChunkDistance = new MaxChunkDistance();

    public static class MaxChunkDistance {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Oak Witch Huts in Forest biomes that are not birch or dark oak.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int witchHutsOakMaxChunkDistance = 48;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                 How rare are Taiga Witch Huts in Taiga biomes.
                 1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int witchHutsTaigaMaxChunkDistance = 48;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Birch Witch Huts in Birch biomes.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int witchHutsBirchMaxChunkDistance = 48;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Dark Forest Witch Huts in Dark Forest biomes.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int witchHutsDarkForestMaxChunkDistance = 48;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Giant Tree Taiga Witch Huts in Giant Tree Taiga biomes.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int witchHutsGiantTreeTaigaMaxChunkDistance = 48;
    }
}
