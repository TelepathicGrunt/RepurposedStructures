package com.telepathicgrunt.repurposedstructures.configs;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;


@Config(name = "Repurposed_Structures-Wells")
public class RSWellsConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    @Comment("Add RS wells to modded biomes of same categories/type.")
    public boolean addWellsToModdedBiomes = false;

    @ConfigEntry.Gui.Tooltip
    @Comment("Determines if Wells can have a chance of spawning a Bell.")
    public boolean canHaveBells = true;


    @ConfigEntry.Gui.CollapsibleObject
    public Spawnrate spawnrate = new Spawnrate();


    public static class Spawnrate {

        @ConfigEntry.Gui.Tooltip(count = 4)
        @Comment("Adds Badlands themed wells to Badlands biomes."
                + "\nChanges how often wells attempt to spawn per chunk."
                + "\nChance of a well generating in a chunk is 1/spawnrate."
                + "\n1 for spawning in every chunk and 10000 for no wells.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int badlandsWellSpawnrate = 350;

        @ConfigEntry.Gui.Tooltip(count = 4)
        @Comment("Adds Nether themed wells to Nether biomes."
                + "\nChanges how often wells attempt to spawn per chunk."
                + "\nChance of a well generating in a chunk is 1/spawnrate."
                + "\n1 for spawning in every chunk and 10000 for no wells.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int netherWellSpawnrate = 350;

        @ConfigEntry.Gui.Tooltip(count = 4)
        @Comment("Adds Snow themed wells to snowy and icy biomes."
                + "\nChanges how often wells attempt to spawn per chunk."
                + "\nChance of a well generating in a chunk is 1/spawnrate."
                + "\n1 for spawning in every chunk and 10000 for no wells.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int snowWellSpawnrate = 350;

        @ConfigEntry.Gui.Tooltip(count = 4)
        @Comment("Adds mossy stone themed wells to Jungles, Dark Oak, and Swamp biomes."
                + "\nChanges how often wells attempt to spawn per chunk."
                + "\nChance of a well generating in a chunk is 1/spawnrate."
                + "\n1 for spawning in every chunk and 10000 for no wells.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int mossyStoneWellSpawnrate = 350;

        @ConfigEntry.Gui.Tooltip(count = 4)
        @Comment("Adds a wood themed wells to Forest and Birch Forest biomes."
                + "\nChanges how often wells attempt to spawn per chunk."
                + "\nChance of a well generating in a chunk is 1/spawnrate."
                + "\n1 for spawning in every chunk and 10000 for no wells.")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int forestWellSpawnrate = 350;

    }
}
