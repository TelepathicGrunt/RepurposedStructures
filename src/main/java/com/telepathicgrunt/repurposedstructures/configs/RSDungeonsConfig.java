package com.telepathicgrunt.repurposedstructures.configs;


import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "Dungeons")
public class RSDungeonsConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.Gui.PrefixText
	@Comment("Add RS dungeons to modded biomes of same categories/type.")
	public boolean addDungeonsToModdedBiomes = true;

    @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @Comment("Add the ID/resource location of the biome you don't want"
            +"\nRS's dungeons to spawn in. Separate each ID with a comma ,"
            +"\n"
            +"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
    public String blacklistedDungeonBiomes = "";

    @ConfigEntry.Gui.CollapsibleObject
    public AttemptsPerChunk attemptsPerChunk = new AttemptsPerChunk();

	@ConfigEntry.Gui.CollapsibleObject
    public MinHeight minHeight = new MinHeight();

	@ConfigEntry.Gui.CollapsibleObject
    public MaxHeight maxHeight = new MaxHeight();


    public static class AttemptsPerChunk {
        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @Comment("Replaces vanilla dungeon in Badlands biomes."
                + "\nSpawn attempts per chunk."
                + "\n0 for no Dungeons at all and 1000 for max spawnrate."
                + "\nNote: When set to 0, Vanilla Dungeons spawns again.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int badlandsDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Replaces vanilla dungeon in Dark Forest biomes."
                + "\nSpawn attempts per chunk."
                + "\n0 for no Dungeons at all and 1000 for max spawnrate."
                + "\nNote: When set to 0, Vanilla Dungeons spawns again.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int darkForestDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Replaces vanilla dungeon in Desert biomes."
                + "\nSpawn attempts per chunk."
                + "\n0 for no Dungeons at all and 1000 for max spawnrate."
                + "\nNote: When set to 0, Vanilla Dungeons spawns again.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int desertDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Replaces vanilla dungeon in Jungle biomes."
                + "\nSpawn attempts per chunk."
                + "\n0 for no Dungeons at all and 1000 for max spawnrate."
                + "\nNote: When set to 0, Vanilla Dungeons spawns again.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int jungleDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Replaces vanilla dungeon in Mushroom biomes."
                + "\nSpawn attempts per chunk."
                + "\n0 for no Dungeons at all and 1000 for max spawnrate."
                + "\nNote: When set to 0, Vanilla Dungeons spawns again.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int mushroomDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Replaces vanilla dungeon in icy/snowy biomes."
                + "\nSpawn attempts per chunk."
                + "\n0 for no Dungeons at all and 1000 for max spawnrate."
                + "\nNote: When set to 0, Vanilla Dungeons spawns again.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int snowDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Replaces vanilla dungeon in Swamp biomes."
                + "\nSpawn attempts per chunk."
                + "\n0 for no Dungeons at all and 1000 for max spawnrate."
                + "\nNote: When set to 0, Vanilla Dungeons spawns again.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int swampDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Add End themed dungeon to biomes outside the Enderdragon island."
                + "\nSpawn attempts per chunk."
                + "\n0 for no Dungeons at all and 1000 for max spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int endDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Add Nether themed dungeon to Nether biomes."
                + "\nSpawn attempts per chunk."
                + "\n0 for no Dungeons at all and 1000 for max spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int netherDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
        @Comment("Add ocean themed dungeon to ocean biomes. Will spawn on"
                + "\nocean floor and in water filled caves and ravines."
                + "\nSpawn attempts per chunk."
                + "\n0 for no dungeons at all and 1000 for max spawnrate."
                + "\nNote: Vanilla Dungeons will still generate if this"
                + "\nis on which is unlike the other dungeons from this mod"
                + "\nas those would normally replace the Vanilla Dungeons.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int oceanDungeonAttemptsPerChunk = 6;

    }

    public static class MinHeight {

        @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
        @Comment("Minimum Y height that this dungeon can spawn at. Default is 2."
                + "\nNote: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int badlandsDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Minimum Y height that this dungeon can spawn at. Default is 2."
                + "\nNote: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int darkForestDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Minimum Y height that this dungeon can spawn at. Default is 2."
                + "\nNote: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int desertDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Minimum Y height that this dungeon can spawn at. Default is 2."
                + "\nNote: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int jungleDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Minimum Y height that this dungeon can spawn at. Default is 2."
                + "\nNote: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int mushroomDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Minimum Y height that this dungeon can spawn at. Default is 2."
                + "\nNote: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int snowDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Minimum Y height that this dungeon can spawn at. Default is 2."
                + "\nNote: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int swampDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Minimum Y height that this dungeon can spawn at. Default is 2."
                + "\nNote: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int endDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Minimum Y height that this dungeon can spawn at. Default is 2."
                + "\nNote: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int netherDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Minimum Y height that this dungeon can spawn at. Default is 3."
                + "\nNote: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 3, max = 255)
        public int oceanDungeonMinHeight = 3;
    }


    public static class MaxHeight {

        @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
        @Comment("Maximum Y height that this dungeon can spawn at. Default is 255."
                + "\nNote: The dungeon will spawn between min and max y height set in config."
                + "\nSetting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int badlandsDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Maximum Y height that this dungeon can spawn at. Default is 255."
                + "\nNote: The dungeon will spawn between min and max y height set in config."
                + "\nSetting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int darkForestDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Maximum Y height that this dungeon can spawn at. Default is 255."
                + "\nNote: The dungeon will spawn between min and max y height set in config."
                + "\nSetting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int desertDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Maximum Y height that this dungeon can spawn at. Default is 255."
                + "\nNote: The dungeon will spawn between min and max y height set in config."
                + "\nSetting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int jungleDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Maximum Y height that this dungeon can spawn at. Default is 255."
                + "\nNote: The dungeon will spawn between min and max y height set in config."
                + "\nSetting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int mushroomDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Maximum Y height that this dungeon can spawn at. Default is 255."
                + "\nNote: The dungeon will spawn between min and max y height set in config."
                + "\nSetting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int snowDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Maximum Y height that this dungeon can spawn at. Default is 255."
                + "\nNote: The dungeon will spawn between min and max y height set in config."
                + "\nSetting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int swampDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Maximum Y height that this dungeon can spawn at. Default is 255."
                + "\nNote: The dungeon will spawn between min and max y height set in config."
                + "\nSetting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int endDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Maximum Y height that this dungeon can spawn at. Default is 255."
                + "\nNote: The dungeon will spawn between min and max y height set in config."
                + "\nSetting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int netherDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("Maximum Y height that this dungeon can spawn at. Default is 255."
                + "\nNote: The dungeon will spawn between min and max y height set in config."
                + "\nSetting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 3, max = 255)
        public int oceanDungeonMaxHeight = 255;
    }
}
