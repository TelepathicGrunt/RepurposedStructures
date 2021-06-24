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
    @Comment("How rare are Grassy Igloos in Plains and Forests."
            + "\n1 for spawning in most chunks and 1001 for none.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int grassyIglooMaxChunkDistance = 20;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.RequiresRestart
    @Comment("How rare are Stone Igloos in Giant Tree Taiga biomes."
            + "\n1 for spawning in most chunks and 1001 for none.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int stoneIglooMaxChunkDistance = 20;
}
