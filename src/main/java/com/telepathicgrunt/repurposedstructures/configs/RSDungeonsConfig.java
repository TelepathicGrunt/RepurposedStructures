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



                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int badlandsDungeonAttemptsPerChunk = 6;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int darkForestDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int desertDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Spawn attempts per chunk. Replaces Vanilla Dungeons below y = 0
                0 for no Dungeons at all and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int deepDungeonAttemptsPerChunk = 4;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int jungleDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int mushroomDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int snowDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int icyDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int swampDungeonAttemptsPerChunk = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Add End themed dungeon to biomes outside the Enderdragon island.
                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int endDungeonAttemptsPerChunk = 12;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Add Nether themed dungeon to Nether biomes.
                Spawn attempts per chunk.
                0 for no Dungeons at all and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int netherDungeonAttemptsPerChunk = 12;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Add ocean themed dungeon to ocean biomes. Will spawn on
                ocean floor and in water filled caves and ravines.
                Spawn attempts per chunk.
                0 for no dungeons at all and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int oceanDungeonAttemptsPerChunk = 2;

    }

    public static class MinHeight {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Minimum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.""")
        public int badlandsDungeonMinHeight = 35;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Minimum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.""")
        public int darkForestDungeonMinHeight = 35;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Minimum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.""")
        public int desertDungeonMinHeight = 35;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Minimum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.""")
        public int deepDungeonMinHeight = -60;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Minimum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.""")
        public int jungleDungeonMinHeight = 35;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Minimum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.""")
        public int mushroomDungeonMinHeight = 35;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Minimum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.""")
        public int snowDungeonMinHeight = 35;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Minimum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.""")
        public int icyDungeonMinHeight = 35;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Minimum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.""")
        public int swampDungeonMinHeight = 35;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Minimum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.""")
        public int endDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Minimum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.""")
        public int netherDungeonMinHeight = 2;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Minimum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.""")
        public int oceanDungeonMinHeight = 20;
    }


    public static class MaxHeight {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Maximum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        public int badlandsDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Maximum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        public int darkForestDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Maximum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        public int desertDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Maximum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        public int deepDungeonMaxHeight = -10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Maximum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        public int jungleDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Maximum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        public int mushroomDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Maximum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        public int snowDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Maximum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        public int icyDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Maximum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        public int swampDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Maximum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        public int endDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Maximum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        public int netherDungeonMaxHeight = 255;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Maximum Y height that this dungeon can spawn at.
                Note: The dungeon will spawn between min and max y height set in config.
                Setting this to below min height config will make dungeon spawn only at min height.""")
        public int oceanDungeonMaxHeight = 255;
    }
}
