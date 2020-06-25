package com.telepathicgrunt.repurposedstructures.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;


@Config(name = "Repurposed_Structures-Wells")
public class RSWellsConfig implements ConfigData {

    @Comment("\r\n Add the custom wells to modded biomes of the same categories/type.")
    public boolean addWellsToModdedBiomes = false;

    @Comment("\r\n Determines if Wells can have a chance of spawning a Bell.")
    public boolean canHaveBells = true;


    @ConfigEntry.Gui.CollapsibleObject
    public Spawnrate spawnrate = new Spawnrate();


    public static class Spawnrate {

        @Comment("\r\n Adds Badlands themed wells to Badlands biomes."
                + "\r\n This effects how often wells will attempt to spawn per chunk."
                + "\r\n The chance of a well generating at a chunk is 1/spawnrate."
                + "\r\n 1 for wells spawning in every chunk and 10000 for no wells.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int badlandsWellSpawnrate = 350;

        @Comment("\r\n Adds Nether themed wells to Nether biomes."
                + "\r\n This effects how often wells will attempt to spawn per chunk."
                + "\r\n The chance of a well generating at a chunk is 1/spawnrate."
                + "\r\n 1 for wells spawning in every chunk and 10000 for no wells.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int netherWellSpawnrate = 350;

        @Comment("\r\n Adds Snow themed wells to snowy and icy biomes."
                + "\r\n This effects how often wells will attempt to spawn per chunk."
                + "\r\n The chance of a well generating at a chunk is 1/spawnrate."
                + "\r\n 1 for wells spawning in every chunk and 10000 for no wells.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int snowWellSpawnrate = 350;

        @Comment("\r\n Adds mossy stone themed wells to Jungles, Dark Oak, and Swamp biomes."
                + "\r\n This effects how often wells will attempt to spawn per chunk."
                + "\r\n The chance of a well generating at a chunk is 1/spawnrate."
                + "\r\n 1 for wells spawning in every chunk and 10000 for no wells.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int mossyStoneWellSpawnrate = 350;

        @Comment("\r\n Adds a wood themed wells to Forest and Birch Forest biomes."
                + "\r\n This effects how often wells will attempt to spawn per chunk."
                + "\r\n The chance of a well generating at a chunk is 1/spawnrate."
                + "\r\n 1 for wells spawning in every chunk and 10000 for no wells.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int forestWellSpawnrate = 350;

    }
}
