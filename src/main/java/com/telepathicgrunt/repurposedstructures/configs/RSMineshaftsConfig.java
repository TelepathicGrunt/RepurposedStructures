package com.telepathicgrunt.repurposedstructures.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;


@Config(name = "Repurposed_Structures-Mineshafts")
public class RSMineshaftsConfig implements ConfigData {


    @Comment("\r\n Controls whether loot chests spawn or not in modded Mineshafts.")
    public boolean lootChestsMS = true;

    @Comment("\r\n Add the custom Mineshafts to modded biomes of the same categories/type.")
    public boolean addMineshaftsToModdedBiomes = false;

    @ConfigEntry.Gui.CollapsibleObject
    public Spawnrate spawnrate = new Spawnrate();

    @ConfigEntry.Gui.CollapsibleObject
    public MinHeight minHeight = new MinHeight();

    @ConfigEntry.Gui.CollapsibleObject
    public MaxHeight maxHeight = new MaxHeight();


    public static class Spawnrate {

        @Comment("\r\n Replace Mineshafts in Birch biomes with a Birch themed Mineshaft."
                + "\r\n How often Mineshafts will spawn.\r\n "
                + "0 for no Mineshafts and 1000 for max spawnrate."
                + "\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int birchMineshaftSpawnrate = 40;

        @Comment("\r\n Replace Mineshafts in Jungle biomes with a Jungle themed Mineshaft."
                + "\r\n How often Mineshafts will spawn.\r\n "
                + "0 for no Mineshafts and 1000 for max spawnrate."
                + "\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int jungleMineshaftSpawnrate = 40;

        @Comment("\r\n Replace Mineshafts in Desert biomes with a Desert themed Mineshaft."
                + "\r\n How often Mineshafts will spawn.\r\n "
                + "0 for no Mineshafts and 1000 for max spawnrate."
                + "\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int desertMineshaftSpawnrate = 40;

        @Comment("\r\n Replace Mineshafts in Mountain (Extreme Hills) with a Mountain themed Mineshaft."
                + "\r\n How often Mineshafts will spawn.\r\n "
                + "0 for no Mineshafts and 1000 for max spawnrate."
                + "\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int stoneMineshaftSpawnrate = 40;

        @Comment("\r\n Replace Mineshafts in Savanna biomes with a Savanna themed Mineshaft."
                + "\r\n How often Mineshafts will spawn.\r\n "
                + "0 for no Mineshafts and 1000 for max spawnrate."
                + "\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int savannaMineshaftSpawnrate = 40;

        @Comment("\r\n Replace Mineshafts in Snowy/Icy biomes with an Ice themed Mineshaft."
                + "\r\n Note: Snowy Taiga Biomes will get Ice Mineshaft instead of Taiga theme."
                + "\r\n How often Mineshafts will spawn.\r\n "
                + "0 for no Mineshafts and 1000 for max spawnrate."
                + "\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int icyMineshaftSpawnrate = 40;

        @Comment("\r\n Replace Mineshafts in Ocean biomes with an Ocean themed Mineshaft."
                + "\r\n How often Mineshafts will spawn.\r\n "
                + "0 for no Mineshafts and 1000 for max spawnrate."
                + "\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int oceanMineshaftSpawnrate = 40;

        @Comment("\r\n Replace Mineshafts in Taiga biomes with a Taiga themed Mineshaft."
                + "\r\n How often Mineshafts will spawn.\r\n "
                + "0 for no Mineshafts and 1000 for max spawnrate."
                + " Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int taigaMineshaftSpawnrate = 40;

        @Comment("\r\n Replace Mineshafts in Swamps and Dark Forests with a"
                + "\r\n swampy/dark oak themed Mineshaft."
                + "\r\n Note: Vanilla Mineshafts will spawn again when this is set to 0 and game is restarted.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int swampAndDarkForestMineshaftSpawnrate = 40;

        @Comment("\r\n Add End themed Mineshafts to End biomes outside the Enderdragon island."
                + "\r\n How often Mineshafts will spawn.\r\n "
                + "0 for no Mineshafts and 1000 for max spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int endMineshaftSpawnrate = 40;

        @Comment("\r\n Add Nether themed Mineshafts to Nether biomes."
                + "\r\n How often Mineshafts will spawn.\r\n "
                + "0 for no Mineshafts and 1000 for max spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int netherMineshaftSpawnrate = 40;
    }


    public static class MinHeight {

        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 0.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make mineshaft spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int birchMineshaftMinHeight = 5;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 5.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int jungleMineshaftMinHeight = 5;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 5.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int desertMineshaftMinHeight = 5;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 5.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int stoneMineshaftMinHeight = 5;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 5.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int savannaMineshaftMinHeight = 5;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 5.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int icyMineshaftMinHeight = 5;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 5.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int oceanMineshaftMinHeight = 5;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 5.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int taigaMineshaftMinHeight = 5;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 5.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int swampAndDarkForestMineshaftMinHeight = 5;

        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 30.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int endMineshaftMinHeight = 30;

        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 5.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int netherMineshaftMinHeight = 5;
    }


    public static class MaxHeight {

        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 45.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make mineshaft spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int birchMineshaftMaxHeight = 45;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 45.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make mineshaft spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int jungleMineshaftMaxHeight = 45;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 45.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make mineshaft spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int desertMineshaftMaxHeight = 45;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 45.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make mineshaft spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int stoneMineshaftMaxHeight = 45;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 45.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make mineshaft spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int savannaMineshaftMaxHeight = 45;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 45.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make mineshaft spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int icyMineshaftMaxHeight = 45;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 25.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make mineshaft spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int oceanMineshaftMaxHeight = 25;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 45.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make mineshaft spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int taigaMineshaftMaxHeight = 45;


        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 45.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make mineshaft spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int swampAndDarkForestMineshaftMaxHeight = 45;

        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 37.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make mineshaft spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int endMineshaftMaxHeight = 37;

        @Comment("\r\n Minimum Y height that this mineshaft can spawn at. Default is 20.\r\n"
                + " Note: The mineshaft will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make mineshaft spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 5, max = 255)
        public int netherMineshaftMaxHeight = 13;
    }
}
