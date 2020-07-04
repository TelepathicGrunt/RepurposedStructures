package com.telepathicgrunt.repurposedstructures.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;


@Config(name = "Repurposed_Structures-Mineshafts")
public class RSMineshaftsConfig implements ConfigData {


    @ConfigEntry.Gui.Tooltip
    @Comment("Controls whether loot chests spawn or not in modded Mineshafts.")
    public boolean lootChestsMS = true;

    @ConfigEntry.Gui.Tooltip
    @Comment("Add the custom Mineshafts to modded biomes of the same categories/type.")
    public boolean addMineshaftsToModdedBiomes = false;

    @ConfigEntry.Gui.CollapsibleObject
    public Spawnrate spawnrate = new Spawnrate();

    @ConfigEntry.Gui.CollapsibleObject
    public MinHeight minHeight = new MinHeight();

    @ConfigEntry.Gui.CollapsibleObject
    public MaxHeight maxHeight = new MaxHeight();


    public static class Spawnrate {
        
        @ConfigEntry.Gui.Tooltip(count = 4)
        @Comment("Replaces Mineshafts in Birch biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int birchMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 4)
        @Comment("Replaces Mineshafts in Jungle biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int jungleMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 4)
        @Comment("Replaces Mineshafts in Desert biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int desertMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 4)
        @Comment("Replaces Mineshafts in Mountain (Extreme Hills) biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int stoneMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 4)
        @Comment("Replaces Mineshafts in Savanna biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int savannaMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 5)
        @Comment("Replaces Mineshafts in Snowy/Icy biomes."
                + "\nNote: Snowy Taiga Biomes will get Ice Mineshaft instead of Taiga theme."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int icyMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 4)
        @Comment("Replaces Mineshafts in Ocean biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int oceanMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 4)
        @Comment("Replaces Mineshafts in Taiga biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int taigaMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 4)
        @Comment("Replaces Mineshafts in Swamps and Dark Forests."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate."
                + "\nNote: Set this to 0 and restart to spawn Vanilla Mineshafts.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int swampAndDarkForestMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 3)
        @Comment("Add End themed Mineshafts to biomes outside the Enderdragon island."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int endMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 3)
        @Comment("Add Nether themed Mineshafts to Nether biomes."
                + "\nHow often Mineshafts will spawn."
                + "\n0 for no Mineshafts and 1000 for max spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int netherMineshaftSpawnrate = 40;
    }


    public static class MinHeight {

        @ConfigEntry.Gui.Tooltip
        @Comment("Min Y height of Mineshaft. Default is 5.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int birchMineshaftMinHeight = 5;
        
        @ConfigEntry.Gui.Tooltip
        @Comment("Min Y height of Mineshaft. Default is 5.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int jungleMineshaftMinHeight = 5;
        
        @ConfigEntry.Gui.Tooltip
        @Comment("Min Y height of Mineshaft. Default is 5.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int desertMineshaftMinHeight = 5;

        @ConfigEntry.Gui.Tooltip
        @Comment("Min Y height of Mineshaft. Default is 5.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int stoneMineshaftMinHeight = 5;

        @ConfigEntry.Gui.Tooltip
        @Comment("Min Y height of Mineshaft. Default is 5.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int savannaMineshaftMinHeight = 5;

        @ConfigEntry.Gui.Tooltip
        @Comment("Min Y height of Mineshaft. Default is 5.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int icyMineshaftMinHeight = 5;

        @ConfigEntry.Gui.Tooltip
        @Comment("Min Y height of Mineshaft. Default is 5.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int oceanMineshaftMinHeight = 5;
        
        @ConfigEntry.Gui.Tooltip
        @Comment("Min Y height of Mineshaft. Default is 5.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int taigaMineshaftMinHeight = 5;

        @ConfigEntry.Gui.Tooltip
        @Comment("Min Y height of Mineshaft. Default is 5.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int swampAndDarkForestMineshaftMinHeight = 5;

        @ConfigEntry.Gui.Tooltip
        @Comment("Min Y height of Mineshaft. Default is 30.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int endMineshaftMinHeight = 30;

        @ConfigEntry.Gui.Tooltip
        @Comment("Min Y height of Mineshaft. Default is 5.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int netherMineshaftMinHeight = 5;
    }


    public static class MaxHeight {

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int birchMineshaftMaxHeight = 45;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int jungleMineshaftMaxHeight = 45;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int desertMineshaftMaxHeight = 45;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int stoneMineshaftMaxHeight = 45;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int savannaMineshaftMaxHeight = 45;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int icyMineshaftMaxHeight = 45;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Max Y height of Mineshaft. Default is 25."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int oceanMineshaftMaxHeight = 25;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int taigaMineshaftMaxHeight = 45;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Max Y height of Mineshaft. Default is 45."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int swampAndDarkForestMineshaftMaxHeight = 45;
        
        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Max Y height of Mineshaft. Default is 37."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int endMineshaftMaxHeight = 37;

        @ConfigEntry.Gui.Tooltip(count = 2)
        @Comment("Max Y height of Mineshaft. Default is 20."
                + "\nIf below min height, this will be read as min.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int netherMineshaftMaxHeight = 13;
    }
}
