package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Cities")
public class RSCitiesConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("How rare are Nether Cities in Nether biomes."
            + "\n1 for spawning in most chunks and 1001 for none.")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int cityNetherMaxChunkDistance = 120;
}
