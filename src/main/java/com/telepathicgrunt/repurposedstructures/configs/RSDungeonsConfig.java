package com.telepathicgrunt.repurposedstructures.configs;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "Dungeons")
public class RSDungeonsConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public AttemptsPerChunk attemptsPerChunk = new AttemptsPerChunk();

	@ConfigEntry.Gui.CollapsibleObject
    public MinHeight minHeight = new MinHeight();

	@ConfigEntry.Gui.CollapsibleObject
    public MaxHeight maxHeight = new MaxHeight();


    public static class AttemptsPerChunk {
        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Replaces vanilla dungeon in Badlands biomes.
                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.
                Note: When set to 0, Vanilla Dungeons spawns again.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int badlandsDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Replaces vanilla dungeon in Dark Forest biomes.
                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.
                Note: When set to 0, Vanilla Dungeons spawns again.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int darkForestDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Replaces vanilla dungeon in Desert biomes.
                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.
                Note: When set to 0, Vanilla Dungeons spawns again.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int desertDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Replaces vanilla dungeon in Jungle biomes.
                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.
                Note: When set to 0, Vanilla Dungeons spawns again.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int jungleDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Replaces vanilla dungeon in Mushroom biomes.
                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.
                Note: When set to 0, Vanilla Dungeons spawns again.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int mushroomDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Replaces vanilla dungeon in snowy biomes.
                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.
                Note: When set to 0, Vanilla Dungeons spawns again.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int snowDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Replaces vanilla dungeon in icy biomes (non-ocean biomes that are super cold or has frozen/ice/icy in name)
                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.
                Note: When set to 0, Vanilla Dungeons spawns again.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int icyDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Replaces vanilla dungeon in Swamp biomes.
                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.
                Note: When set to 0, Vanilla Dungeons spawns again.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int swampDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Add End themed dungeon to biomes outside the Enderdragon island.
                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int endDungeonAttemptsPerChunk = 12;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Add Nether themed dungeon to Nether biomes.
                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int netherDungeonAttemptsPerChunk = 12;

        @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Add ocean themed dungeon to ocean biomes. Will spawn on
                ocean floor and in water filled caves and ravines.
                Spawn attempts per chunk.
                0 for no dungeons at all and 1000 for max spawnrate.
                Note: Vanilla Dungeons will still generate if this
                is on which is unlike the other dungeons from this mod
                as those would normally replace the Vanilla Dungeons.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int oceanDungeonAttemptsPerChunk = 5;

    }

    public static class MinHeight {

        @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Minimum Y height that this dungeon can spawn at. Default is 2.
                Note: The dungeon will spawn between min and max y height set in config.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int badlandsDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Minimum Y height that this dungeon can spawn at. Default is 2.
                Note: The dungeon will spawn between min and max y height set in config.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int darkForestDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Minimum Y height that this dungeon can spawn at. Default is 2.
                Note: The dungeon will spawn between min and max y height set in config.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int desertDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Minimum Y height that this dungeon can spawn at. Default is 2.
                Note: The dungeon will spawn between min and max y height set in config.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int jungleDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Minimum Y height that this dungeon can spawn at. Default is 2.
                Note: The dungeon will spawn between min and max y height set in config.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int mushroomDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Minimum Y height that this dungeon can spawn at. Default is 2.
                Note: The dungeon will spawn between min and max y height set in config.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int snowDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Minimum Y height that this dungeon can spawn at. Default is 2.
                Note: The dungeon will spawn between min and max y height set in config.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int icyDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Minimum Y height that this dungeon can spawn at. Default is 2.
                Note: The dungeon will spawn between min and max y height set in config.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int swampDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Minimum Y height that this dungeon can spawn at. Default is 2.
                Note: The dungeon will spawn between min and max y height set in config.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int endDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Minimum Y height that this dungeon can spawn at. Default is 2.
                Note: The dungeon will spawn between min and max y height set in config.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int netherDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Minimum Y height that this dungeon can spawn at. Default is 3.
                Note: The dungeon will spawn between min and max y height set in config.""")
        @ConfigEntry.BoundedDiscrete(min = 3, max = 255)
        public int oceanDungeonMinHeight = 3;
    }


    public static class MaxHeight {

        @ConfigEntry.Gui.Tooltip(count = 0)
    @ConfigEntry.Gui.PrefixText
    @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Maximum Y height that this dungeon can spawn at. Default is 255.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int badlandsDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Maximum Y height that this dungeon can spawn at. Default is 255.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int darkForestDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Maximum Y height that this dungeon can spawn at. Default is 255.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int desertDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Maximum Y height that this dungeon can spawn at. Default is 255.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int jungleDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Maximum Y height that this dungeon can spawn at. Default is 255.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int mushroomDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Maximum Y height that this dungeon can spawn at. Default is 255.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int snowDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Maximum Y height that this dungeon can spawn at. Default is 255.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int icyDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Maximum Y height that this dungeon can spawn at. Default is 255.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int swampDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Maximum Y height that this dungeon can spawn at. Default is 255.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int endDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Maximum Y height that this dungeon can spawn at. Default is 255.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int netherDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                Maximum Y height that this dungeon can spawn at. Default is 255.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        @ConfigEntry.BoundedDiscrete(min = 3, max = 255)
        public int oceanDungeonMaxHeight = 255;
    }
}
