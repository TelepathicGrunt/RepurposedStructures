package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Villages")
public class RSVillagesConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Badlands Villages in Badland biomes.
            1 for spawning in most chunks and 1001 for no spawn.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int badlandsVillageAverageChunkDistance = 30;
    
    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Birch Villages in Birch biomes.
            1 for spawning in most chunks and 1001 for no spawn.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int birchVillageAverageChunkDistance = 38;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Dark Forest Villages in Dark Forest biomes.
            1 for spawning in most chunks and 1001 for no spawn.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int darkForestVillageAverageChunkDistance = 38;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Jungle Villages in Jungle biomes.
            1 for spawning in most chunks and 1001 for no spawn.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int jungleVillageAverageChunkDistance = 38;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Swamp Villages in Swamp biomes.
            1 for spawning in most chunks and 1001 for no spawn.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int swampVillageAverageChunkDistance = 38;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Mountains Villages in Mountains biomes.
            1 for spawning in most chunks and 1001 for no spawn.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int mountainsVillageAverageChunkDistance = 38;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Giant Taiga Villages in Giant Taiga biomes.
            1 for spawning in most chunks and 1001 for no spawn.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int giantTaigaVillageAverageChunkDistance = 38;


    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Crimson Village in Crimson Forest biomes.
            1 for spawning in most chunks and 1001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int crimsonVillageAverageChunkDistance = 30;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Warped Village in Warped Forest biomes.
            1 for spawning in most chunks and 1001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int warpedVillageAverageChunkDistance = 30;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.RequiresRestart
    @Comment("""



            How rare are Oak Villages in forest category biomes that are not birch or dark forest.
            1 for spawning in most chunks and 1001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int oakVillageAverageChunkDistance = 38;
}
