package com.telepathicgrunt.repurposedstructures.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;


@Config(name = "Outposts")
public class RSOutpostsConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public Outposts outposts = new Outposts();

    public static class Outposts {

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("How rare are Nether Brick Outposts in non-warped Nether biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int netherBrickOutpostSpawnrate = 28;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Add Nether Brick Outposts to modded Nether biomes that other nether outposts don't fit in.")
        public boolean addNetherBrickOutpostToModdedBiomes = false;


        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("How rare are Warped Outposts in Warped Nether biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int warpedOutpostSpawnrate = 28;

        @ConfigEntry.Gui.Tooltip()
        @Comment("Add Warped Outposts to modded Nether Warped biomes.")
        public boolean addWarpedOutpostToModdedBiomes = false;


        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("How rare are Crimson Outposts in Warped Nether biomes."
                + "\n1 for spawning in most chunks and 1001 for none.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int crimsonOutpostSpawnrate = 28;

        @ConfigEntry.Gui.Tooltip()
        @Comment("Add Crimson Outposts to modded Nether Warped biomes.")
        public boolean addCrimsonOutpostToModdedBiomes = false;
    }
}
