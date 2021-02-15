package com.telepathicgrunt.repurposedstructures.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;


@Config(name = "WitchHuts")
public class RSWitchHutsConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public RSWitchHutsConfig.Blacklists blacklist = new RSWitchHutsConfig.Blacklists();

    @ConfigEntry.Gui.CollapsibleObject
    public MaxChunkDistance maxChunkDistance = new MaxChunkDistance();

    public static class Blacklists {
        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's witch huts to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedWitchHutsBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Oak Witch Huts to modded Forest biomes that"
                + "\nare not birch or dark oak.")
        public boolean addWitchHutsOakToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Taiga Witch Huts to modded Taiga biomes.")
        public boolean addWitchHutsTaigaToModdedBiomes = true;
        // regexpos1
    }

    public static class MaxChunkDistance {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Oak Witch Huts in Forest biomes that"
                + "\nare not birch or dark oak. 1 for spawning in most"
                + "\nchunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int witchHutsOakMaxChunkDistance = 48;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Taiga Witch Huts in Taiga biomes. 1"
                + "\nfor spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int witchHutsTaigaMaxChunkDistance = 48;
        // regexpos2
    }
}
