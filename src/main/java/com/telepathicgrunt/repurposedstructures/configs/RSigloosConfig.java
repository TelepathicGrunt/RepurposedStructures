package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Igloos")
public class RSigloosConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Grassy Igloos in Plains and Forests.
            1 for spawning in most chunks and 10001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
    public int grassyIglooMaxChunkDistance = 20;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Stone Igloos in Giant Tree Taiga biomes.
            1 for spawning in most chunks and 10001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
    public int stoneIglooMaxChunkDistance = 20;
}
