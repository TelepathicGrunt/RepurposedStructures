package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Villages")
public class RSVillagesConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public Spawnrate spawnrate = new Spawnrate();

    @ConfigEntry.Gui.CollapsibleObject
    public Size size = new Size();

    public static class Spawnrate {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Badlands Villages in Badland biomes.
                1 for spawning in most chunks and 1001 for no spawn.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int badlandsVillageAverageChunkDistance = 34;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Birch Villages in Birch biomes.
                1 for spawning in most chunks and 1001 for no spawn.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int birchVillageAverageChunkDistance = 47;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Dark Forest Villages in Dark Forest biomes.
                1 for spawning in most chunks and 1001 for no spawn.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int darkForestVillageAverageChunkDistance = 47;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Jungle Villages in Jungle biomes.
                1 for spawning in most chunks and 1001 for no spawn.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int jungleVillageAverageChunkDistance = 47;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Swamp Villages in Swamp biomes.
                1 for spawning in most chunks and 1001 for no spawn.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int swampVillageAverageChunkDistance = 47;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Mountains Villages in Mountains biomes.
                1 for spawning in most chunks and 1001 for no spawn.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int mountainsVillageAverageChunkDistance = 47;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Giant Taiga Villages in Giant Taiga biomes.
                1 for spawning in most chunks and 1001 for no spawn.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int giantTaigaVillageAverageChunkDistance = 47;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Oak Villages in forest category biomes that are not birch or dark forest.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int oakVillageAverageChunkDistance = 47;


        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Crimson Village in Crimson Forest biomes.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int crimsonVillageAverageChunkDistance = 30;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                How rare are Warped Village in Warped Forest biomes.
                1 for spawning in most chunks and 1001 for none.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
        public int warpedVillageAverageChunkDistance = 30;
    }


    public static class Size {
        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the village. This is how many pieces long a path can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int badlandsVillageSize = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the village. This is how many pieces long a path can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int birchVillageSize = 6;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the village. This is how many pieces long a path can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int darkForestVillageSize = 6;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the village. This is how many pieces long a path can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int jungleVillageSize = 8;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the village. This is how many pieces long a path can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int swampVillageSize = 6;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the village. This is how many pieces long a path can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int mountainsVillageSize = 6;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the village. This is how many pieces long a path can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int giantTaigaVillageSize = 6;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the village. This is how many pieces long a path can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int oakVillageSize = 6;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the village. This is how many pieces long a path can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int crimsonVillageSize = 6;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the village. This is how many pieces long a path can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int warpedVillageSize = 6;

    }
}
