package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Ruins")
public class RSRuinsConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Nether Ruins in Nether category biomes.
            1 for spawning in most chunks and 10001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
    public int ruinsNetherMaxChunkDistance = 35;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Warm Land Ruins in Plains, Forests, Swamps, and non-snowy Taiga biomes.
            1 for spawning in most chunks and 10001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
    public int ruinsLandWarmMaxChunkDistance = 36;

    // regexpos1

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Hot Land Ruins in Desert biomes.
            1 for spawning in most chunks and 10001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
    public int ruinsLandHotMaxChunkDistance = 39;
    // regexpos2
}
