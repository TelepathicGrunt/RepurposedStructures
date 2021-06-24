package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Wells")
public class RSWellsConfig implements ConfigData {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Adds Badlands themed wells to Badlands biomes`.
                Changes how often wells attempt to spawn per chunk.
                Chance of a well generating in a chunk is 1/rarityPerChunk.
                1 for spawning in every chunk and 10000 for no wells.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int badlandsWellRarityPerChunk = 350;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Adds Nether themed wells to Nether biomes.
                Changes how often wells attempt to spawn per chunk.
                Chance of a well generating in a chunk is 1/rarityPerChunk.
                1 for spawning in every chunk and 10000 for no wells.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int netherWellRarityPerChunk = 350;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Adds Snow themed wells to snowy and icy biomes.
                Changes how often wells attempt to spawn per chunk.
                Chance of a well generating in a chunk is 1/rarityPerChunk.
                1 for spawning in every chunk and 10000 for no wells.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int snowWellRarityPerChunk = 350;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Adds mossy stone themed wells to Jungles, Dark Oak, and Swamp biomes.
                Changes how often wells attempt to spawn per chunk.
                Chance of a well generating in a chunk is 1/rarityPerChunk.
                1 for spawning in every chunk and 10000 for no wells.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int mossyStoneWellRarityPerChunk = 350;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Adds a wood themed wells to Forest and Birch Forest biomes.
                Changes how often wells attempt to spawn per chunk.
                Chance of a well generating in a chunk is 1/rarityPerChunk.
                1 for spawning in every chunk and 10000 for no wells.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int forestWellRarityPerChunk = 350;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Adds a mushroom themed wells to Mushroom biomes.
                Changes how often wells attempt to spawn per chunk.
                Chance of a well generating in a chunk is 1/rarityPerChunk.
                1 for spawning in every chunk and 10000 for no wells.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
        public int mushroomWellRarityPerChunk = 350;
}
