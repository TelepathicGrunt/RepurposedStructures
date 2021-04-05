package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Temples")
public class RSTemplesConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public Temples temples = new Temples();

    @ConfigEntry.Gui.CollapsibleObject
    public Pyramids pyramids = new Pyramids();

    public static class Temples {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's temples to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedTempleBiomes = "";

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Nether Temples in Nether Wastelands."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int netherWastelandTempleMaxChunkDistance = 27;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Nether Wasteland Temples to modded Nether biomes that other nether temples don't fit in.")
        public boolean addNetherWastelandTempleToModdedBiomes = false;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Nether Basalt Temples in Nether Basalt Delta biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int netherBasaltTempleMaxChunkDistance = 27;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Nether Basalt Temples to modded Nether Basalt biomes.")
        public boolean addNetherBasaltTempleToModdedBiomes = false;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Nether Crimson Temples in Nether Crimson Forest."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int netherCrimsonTempleMaxChunkDistance = 27;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Nether Crimson Temples to modded Nether Crimson Forest biomes.")
        public boolean addNetherCrimsonTempleToModdedBiomes = false;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Nether Crimson Temples in Nether Warped Forest."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int netherWarpedTempleMaxChunkDistance = 27;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Nether Warped Temples to modded Nether Warped Forest biomes.")
        public boolean addNetherWarpedTempleToModdedBiomes = false;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Nether Soul Temples in Nether Soul Sand Valley."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int netherSoulTempleMaxChunkDistance = 27;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Nether Soul Temples to modded Nether Soul Sand Valley biomes.")
        public boolean addNetherSoulTempleToModdedBiomes = false;
    }


    public static class Pyramids {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add the ID/resource location of the biome you don't want"
                +"\nRS's pyramids to spawn in. Separate each ID with a comma ,"
                +"\n"
                +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
        public String blacklistedPyramidBiomes = "";


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Nether Pyramids in Nether."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int netherPyramidMaxChunkDistance = 37;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Nether Pyramids to modded Nether biomes.")
        public boolean addNetherPyramidToModdedBiomes = true;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Badlands Pyramid in Badlands biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int badlandsPyramidMaxChunkDistance = 37;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Badlands Pyramid to modded Badlands biomes.")
        public boolean addBadlandsPyramidToModdedBiomes = true;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Snowy Pyramid in snowy biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidSnowyMaxChunkDistance = 37;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Snowy Pyramid to modded snowy biomes.")
        public boolean addPyramidSnowyToModdedBiomes = true;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are End Pyramid in End biomes. 1 for"
                + "\nspawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidEndMaxChunkDistance = 68;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add End Pyramid to modded end biomes.")
        public boolean addPyramidEndToModdedBiomes = true;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Icy Pyramid in biomes that are super"
                + "\ncold or has icy words in its name. 1 for spawning"
                + "\nin most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidIcyMaxChunkDistance = 37;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Icy Pyramid to modded icy biomes.")
        public boolean addPyramidIcyToModdedBiomes = true;
    }
}
