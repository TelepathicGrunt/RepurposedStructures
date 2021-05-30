package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Bastions")
public class RSBastionsConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public RSBastionsConfig.Blacklists blacklist = new RSBastionsConfig.Blacklists();

    @ConfigEntry.Gui.CollapsibleObject
    public MaxChunkDistance maxChunkDistance = new MaxChunkDistance();

    public static class Blacklists {
        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's underground bastions to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedUndergroundBastionsBiomes = "";


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Underground Bastions to modded non-ocean and non-beach Overworld biomes.")
        public boolean addBastionUndergroundToModdedBiomes = true;
    }

    public static class MaxChunkDistance {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Underground Bastions in non-ocean and non-beach Overworld biomes. 1 for spawning in most chunks and 10001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
        public int bastionUndergroundMaxChunkDistance = 800;
    }
}
