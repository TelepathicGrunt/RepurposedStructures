package com.telepathicgrunt.repurposedstructures.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;


@Config(name = "Mansions")
public class RSMansionsConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public RSMansionsConfig.Blacklists blacklist = new RSMansionsConfig.Blacklists();

    @ConfigEntry.Gui.CollapsibleObject
    public MaxChunkDistance maxChunkDistance = new MaxChunkDistance();

    public static class Blacklists {
        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's mansions to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedMansionBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Birch Mansions to modded Birch biomes.")
        public boolean addMansionBirchToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Jungle Mansions to modded Jungle biomes.")
        public boolean addMansionJungleToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Oak Mansions to modded forest category biomes"
                + "\nthat are not birch or dark forest.")
        public boolean addMansionOakToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Savanna Mansions to modded Savanna biomes.")
        public boolean addMansionSavannaToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Taiga Mansions to modded non-snowy Taiga"
                + "\nbiomes.")
        public boolean addMansionTaigaToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Desert Mansions to modded Desert biomes.")
        public boolean addMansionDesertToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Snowy Mansions to modded Snowy biomes.")
        public boolean addMansionSnowyToModdedBiomes = true;

    }

    public static class MaxChunkDistance {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Birch Mansions in Birch biomes. 1"
                + "\nfor spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int mansionBirchMaxChunkDistance = 140;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Jungle Mansions in Jungle biomes. 1"
                + "\nfor spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int mansionJungleMaxChunkDistance = 140;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Oak Mansions in in forest category"
                + "\nbiomes that are not birch or dark forest. 1 for"
                + "\nspawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int mansionOakMaxChunkDistance = 140;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Savanna Mansions in Savanna biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int mansionSavannaMaxChunkDistance = 140;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Taiga Mansions in non-snowy Taiga"
                + "\nbiomes. 1 for spawning in most chunks and 1001 for"
                + "\nnone.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int mansionTaigaMaxChunkDistance = 140;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Desert Mansions in Desert biomes. 1"
                + "\nfor spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int mansionDesertMaxChunkDistance = 140;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Snowy Mansions in Snowy biomes. 1"
                + "\nfor spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int mansionSnowyMaxChunkDistance = 140;

    }
}
