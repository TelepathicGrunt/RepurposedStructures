package com.telepathicgrunt.repurposedstructures.configs;


import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "Repurposed_Structures-Dungeons")
public class RSDungeonsConfig implements ConfigData {

	@Comment("\r\n Add the custom dungeons to modded biomes of the same categories/type.")
	public boolean addDungeonsToModdedBiomes = false;


    @ConfigEntry.Gui.CollapsibleObject
    public Spawnrate spawnrate = new Spawnrate();

	@ConfigEntry.Gui.CollapsibleObject
    public MinHeight minHeight = new MinHeight();

	@ConfigEntry.Gui.CollapsibleObject
    public MaxHeight maxHeight = new MaxHeight();


    public static class Spawnrate {
        @Comment("\r\n Replace vanilla dungeon in Badlands biomes with Badlands themed dungeon.\r\n"
                + " How often dungeons will attempt to spawn per chunk.\r\n "
                + " 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
                + " Note: Vanilla Dungeons will spawn again when this is set to 0.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int badlandsDungeonSpawnrate = 8;

        @Comment("\r\n Replace vanilla dungeon in Dark Forest biomes with Dark Forest themed dungeon.\r\n"
                + " How often dungeons will attempt to spawn per chunk.\r\n "
                + " 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
                + " Note: Vanilla Dungeons will spawn again when this is set to 0.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int darkForestDungeonSpawnrate = 8;

        @Comment("\r\n Replace vanilla dungeon in Desert biomes with Desert themed dungeon.\r\n"
                + " How often dungeons will attempt to spawn per chunk.\r\n "
                + " 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
                + " Note: Vanilla Dungeons will spawn again when this is set to 0.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int desertDungeonSpawnrate = 8;

        @Comment("\r\n Replace vanilla dungeon in Jungle biomes with Jungle themed dungeon.\r\n"
                + " How often dungeons will attempt to spawn per chunk.\r\n "
                + " 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
                + " Note: Vanilla Dungeons will spawn again when this is set to 0.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int jungleDungeonSpawnrate = 8;

        @Comment("\r\n Replace vanilla dungeon in Mushroom biomes with Mushroom themed dungeon.\r\n"
                + " How often dungeons will attempt to spawn per chunk.\r\n "
                + " 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
                + " Note: Vanilla Dungeons will spawn again when this is set to 0.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int mushroomDungeonSpawnrate = 8;

        @Comment("\r\n Replace vanilla dungeon in icy/snow biomes with icy/snow themed dungeon.\r\n"
                + " How often dungeons will attempt to spawn per chunk.\r\n "
                + " 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
                + " Note: Vanilla Dungeons will spawn again when this is set to 0.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int snowDungeonSpawnrate = 8;

        @Comment("\r\n Replace vanilla dungeon in Swamp biomes with Swamp themed dungeon.\r\n"
                + " How often dungeons will attempt to spawn per chunk.\r\n "
                + " 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.\r\n"
                + " Note: Vanilla Dungeons will spawn again when this is set to 0.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int swampDungeonSpawnrate = 8;

        @Comment("\r\n Add End themed dungeon to End biomes outside the Enderdragon island.\r\n"
                + " How often dungeons will attempt to spawn per chunk.\r\n "
                + " 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int endDungeonSpawnrate = 8;

        @Comment("\r\n Add Nether themed dungeon to Nether biomes.\r\n"
                + " How often dungeons will attempt to spawn per chunk.\r\n "
                + " 0 for no Dungeons at all and 1000 for max Dungeon spawnrate.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int netherDungeonSpawnrate = 8;

        @Comment("\r\n Add ocean themed dungeon to ocean biomes. These will spawn on"
                + "\r\n the ocean's floor and inside water filled caves and ravines.\r\n"
                + "\r\n"
                + " How often dungeons will attempt to spawn per chunk.\r\n "
                + " 0 for no dungeons at all and 1000 for max dungeon spawnrate."
                + "\r\n"
                + "\r\n Note: Vanilla Dungeons will still generate if the biome has "
                + "\r\n them which is unlike the other modded dungeons from this mod"
                + "\r\n as those would normally replace the Vanilla Dungeons.")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int oceanDungeonSpawnrate = 8;

    }

    public static class MinHeight {


        @Comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int darkForestDungeonMinHeight = 2;

        @Comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int desertDungeonMinHeight = 2;

        @Comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int jungleDungeonMinHeight = 2;

        @Comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int mushroomDungeonMinHeight = 2;

        @Comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int snowDungeonMinHeight = 2;

        @Comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int swampDungeonMinHeight = 2;

        @Comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int endDungeonMinHeight = 2;

        @Comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 2.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int netherDungeonMinHeight = 2;

        @Comment("\r\n Minimum Y height that this dungeon can spawn at. Default is 3.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.")
        @ConfigEntry.BoundedDiscrete(min = 3, max = 255)
        public int oceanDungeonMinHeight = 3;
    }


    public static class MaxHeight {

        @Comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int badlandsDungeonMaxHeight = 255;

        @Comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int darkForestDungeonMaxHeight = 255;

        @Comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int desertDungeonMaxHeight = 255;

        @Comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int jungleDungeonMaxHeight = 255;

        @Comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int mushroomDungeonMaxHeight = 255;

        @Comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int snowDungeonMaxHeight = 255;

        @Comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int swampDungeonMaxHeight = 255;

        @Comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int endDungeonMaxHeight = 255;

        @Comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 2, max = 255)
        public int netherDungeonMaxHeight = 255;

        @Comment("\r\n Maximum Y height that this dungeon can spawn at. Default is 255.\r\n"
                + " Note: The dungeon will spawn between min and max y height set in config.\r\n"
                + " Setting this to below min height config will make dungeon spawn only at min height.")
        @ConfigEntry.BoundedDiscrete(min = 3, max = 255)
        public int oceanDungeonMaxHeight = 255;
    }
}
