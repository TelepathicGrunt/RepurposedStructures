package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Mineshafts")
public class RSMineshaftsConfig implements ConfigData {


    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("Controls whether loot chests spawn or not in modded Mineshafts.")
    public boolean lootChestsMS = true;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("Add the custom Mineshafts to modded biomes of the same categories/type.")
    public boolean addMineshaftsToModdedBiomes = true;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("Add the ID/resource location of the biome you don't want"
            +"\nRS's mineshafts to spawn in. Separate each ID with a comma ,"
            +"\n"
            +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
    public String blacklistedMineshaftBiomes = "betterend:sulphur_springs";

    @ConfigEntry.Gui.CollapsibleObject
    public Spawnrate spawnrate = new Spawnrate();

    @ConfigEntry.Gui.CollapsibleObject
    public MinHeight minHeight = new MinHeight();

    @ConfigEntry.Gui.CollapsibleObject
    public MaxHeight maxHeight = new MaxHeight();

    @ConfigEntry.Gui.CollapsibleObject
    public Size size = new Size();

    @ConfigEntry.Gui.CollapsibleObject
    public Misc misc = new Misc();

    public static class Spawnrate {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Replaces Mineshafts in Birch biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int birchMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Replaces Mineshafts in Jungle biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int jungleMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Replaces Mineshafts in Desert biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int desertMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Replaces Mineshafts in Mountain (Extreme Hills) biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int stoneMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Replaces Mineshafts in Savanna biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int savannaMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Replaces Mineshafts in Snowy/Icy biomes."
                + "\nNote: Snowy Taiga Biomes will get Ice Mineshaft instead of Taiga theme."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int icyMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Replaces Mineshafts in Ocean biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int oceanMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Replaces Mineshafts in Taiga biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int taigaMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Replaces Mineshafts in Swamps and Dark Forests."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int swampAndDarkForestMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add End themed Mineshafts to biomes outside the Enderdragon island."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int endMineshaftSpawnrate = 60;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Nether themed Mineshafts to non-crimson and non-warped Nether biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int netherMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Crimson themed Mineshafts to Crimson Nether biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int crimsonMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Add Warped themed Mineshafts to Warped Nether biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int warpedMineshaftSpawnrate = 40;
    }


    public static class MinHeight {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Min Y height of Mineshaft. Default is 8.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int birchMineshaftMinHeight = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Min Y height of Mineshaft. Default is 8.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int jungleMineshaftMinHeight = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Min Y height of Mineshaft. Default is 8.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int desertMineshaftMinHeight = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Min Y height of Mineshaft. Default is 8.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int stoneMineshaftMinHeight = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Min Y height of Mineshaft. Default is 8.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int savannaMineshaftMinHeight = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Min Y height of Mineshaft. Default is 8.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int icyMineshaftMinHeight = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Min Y height of Mineshaft. Default is 8.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int oceanMineshaftMinHeight = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Min Y height of Mineshaft. Default is 8.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int taigaMineshaftMinHeight = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Min Y height of Mineshaft. Default is 8.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int swampAndDarkForestMineshaftMinHeight = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Min Y height of Mineshaft. Default is 30.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int endMineshaftMinHeight = 30;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Min Y height of Mineshaft. Default is 8.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int netherMineshaftMinHeight = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Min Y height of Mineshaft. Default is 8.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int crimsonMineshaftMinHeight = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Min Y height of Mineshaft. Default is 8.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int warpedMineshaftMinHeight = 8;
    }


    public static class MaxHeight {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int birchMineshaftMaxHeight = 45;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int jungleMineshaftMaxHeight = 45;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int desertMineshaftMaxHeight = 45;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int stoneMineshaftMaxHeight = 45;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int savannaMineshaftMaxHeight = 45;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int icyMineshaftMaxHeight = 45;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Max Y height of Mineshaft. Default is 25."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int oceanMineshaftMaxHeight = 25;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int taigaMineshaftMaxHeight = 45;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int swampAndDarkForestMineshaftMaxHeight = 45;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Max Y height of Mineshaft. Default is 37."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int endMineshaftMaxHeight = 37;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Max Y height of Mineshaft. Default is 13."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int netherMineshaftMaxHeight = 13;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Max Y height of Mineshaft. Default is 13."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int crimsonMineshaftMaxHeight = 13;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Max Y height of Mineshaft. Default is 13."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int warpedMineshaftMaxHeight = 13;
    }
    public static class Size {
        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 30)
        public int birchMineshaftSize = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 30)
        public int jungleMineshaftSize = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 30)
        public int desertMineshaftSize = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 30)
        public int stoneMineshaftSize = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 30)
        public int savannaMineshaftSize = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 30)
        public int icyMineshaftSize = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 30)
        public int oceanMineshaftSize = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 30)
        public int taigaMineshaftSize = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 30)
        public int endMineshaftSize = 12;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 30)
        public int netherMineshaftSize = 11;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 30)
        public int crimsonMineshaftSize = 11;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Size of the mineshaft. This is how many pieces long a branch can be from the start piece.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 30)
        public int warpedMineshaftSize = 11;
    }

    public static class Misc {
        @ConfigEntry.Gui.Tooltip
        @Comment("Add End Mineshafts to End Barrens and End Islands biome.")
        public boolean barrensIslandsEndMineshafts = false;
    }
}
