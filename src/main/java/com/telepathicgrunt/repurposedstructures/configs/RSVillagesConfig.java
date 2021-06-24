package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Villages")
public class RSVillagesConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("""
            How rare are Badlands Villages in Badland biomes.
            1 for spawning in most chunks and 1001 for no spawn.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int badlandsVillageMaxChunkDistance = 30;
    
    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("""
            How rare are Birch Villages in Birch biomes.
            1 for spawning in most chunks and 1001 for no spawn.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int birchVillageMaxChunkDistance = 38;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("""
            How rare are Dark Forest Villages in Dark Forest biomes.
            1 for spawning in most chunks and 1001 for no spawn.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int darkForestVillageMaxChunkDistance = 38;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("""
            How rare are Jungle Villages in Jungle biomes.
            1 for spawning in most chunks and 1001 for no spawn.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int jungleVillageMaxChunkDistance = 38;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("""
            How rare are Swamp Villages in Swamp biomes.
            1 for spawning in most chunks and 1001 for no spawn.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int swampVillageMaxChunkDistance = 38;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("""
            How rare are Mountains Villages in Mountains biomes.
            1 for spawning in most chunks and 1001 for no spawn.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int mountainsVillageMaxChunkDistance = 38;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("""
            How rare are Giant Taiga Villages in Giant Taiga biomes.
            1 for spawning in most chunks and 1001 for no spawn.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int giantTaigaVillageMaxChunkDistance = 38;


    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("""
            How rare are Crimson Village in Crimson Forest biomes.
            1 for spawning in most chunks and 1001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int crimsonVillageMaxChunkDistance = 30;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("""
            How rare are Warped Village in Warped Forest biomes.
            1 for spawning in most chunks and 1001 for none.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int warpedVillageMaxChunkDistance = 30;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("""
            How rare are Oak Villages in forest category
            biomes that are not birch or dark forest.""")
    @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
    public int villageOakMaxChunkDistance = 38;
}
