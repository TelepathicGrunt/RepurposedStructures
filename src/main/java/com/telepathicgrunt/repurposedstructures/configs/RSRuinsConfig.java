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
            1 for spawning in most chunks and 1001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int ruinsNetherAverageChunkDistance = 35;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Warm Land Ruins in Plains, Forests, Swamps, and non-snowy Taiga biomes.
            1 for spawning in most chunks and 1001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int ruinsLandWarmAverageChunkDistance = 52;


    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Hot Land Ruins in Desert biomes.
            1 for spawning in most chunks and 1001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int ruinsLandHotAverageChunkDistance = 52;


    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Cold Land Ruins in Taiga, Extreme Hills, and Mountain biomes that are not snowy.
            1 for spawning in most chunks and 1001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int ruinsLandColdAverageChunkDistance = 52;


    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Icy Land Ruins in Icy biomes.
            1 for spawning in most chunks and 1001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int ruinsLandIcyAverageChunkDistance = 52;
}
