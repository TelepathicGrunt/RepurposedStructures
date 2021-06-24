package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Temples")
public class RSTemplesConfig implements ConfigData {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Nether Temples in Nether Wastelands.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int netherWastelandTempleMaxChunkDistance = 27;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Nether Basalt Temples in Nether Basalt Delta biomes.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int netherBasaltTempleMaxChunkDistance = 27;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Nether Crimson Temples in Nether Crimson Forest.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int netherCrimsonTempleMaxChunkDistance = 27;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Nether Crimson Temples in Nether Warped Forest.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int netherWarpedTempleMaxChunkDistance = 27;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Nether Soul Temples in Nether Soul Sand Valley.
                1 for spawning in most chunks and 10001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int netherSoulTempleMaxChunkDistance = 27;
}
