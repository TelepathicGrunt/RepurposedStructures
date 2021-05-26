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
        public int badlandsPyramidMaxChunkDistance = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Badlands Pyramid to modded Badlands biomes.")
        public boolean addBadlandsPyramidToModdedBiomes = true;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Snowy Pyramid in snowy biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidSnowyMaxChunkDistance = 40;

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


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Jungle Pyramid in Jungle biomes. 1"
                + "\nfor spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidJungleMaxChunkDistance = 44;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Jungle Pyramid to modded Jungle biomes.")
        public boolean addPyramidJungleToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Mushroom Pyramid in Mushroom biomes. 1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidMushroomMaxChunkDistance = 24;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Mushroom Pyramid to modded Mushroom biomes.")
        public boolean addPyramidMushroomToModdedBiomes = true;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Ocean Pyramid in Ocean biomes. 1 for spawning in most chunks and 1001 for none. ")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidOceanMaxChunkDistance = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("{config_modded_biome_comment}")
        public boolean addPyramidOceanToModdedBiomes = true;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Giant Tree Taiga Pyramid to modded taiga biomes with giant or redwood in its name.")
        public boolean addPyramidGiantTreeTaigaToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Giant Tree Taiga Pyramid in Giant Tree Taiga biomes. 1 for spawning in most chunks and 1001 for none. ")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidGiantTreeTaigaMaxChunkDistance = 40;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Flower Forest Pyramid to modded Plains or Forest biomes with flower or blossom in its name.")
        public boolean addPyramidFlowerForestToModdedBiomes = true;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("How rare are Flower Forest Pyramid in Flower Forest biomes. 1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int pyramidFlowerForestMaxChunkDistance = 36;


        // regexpos1

        // regexpos2
    }
}
