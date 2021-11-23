package com.telepathicgrunt.repurposedstructures.configs;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;


@Config(name = "Mineshafts")
public class RSMineshaftsConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public Spawnrate spawnrate = new Spawnrate();

    @ConfigEntry.Gui.CollapsibleObject
    public MinHeight minHeight = new MinHeight();

    @ConfigEntry.Gui.CollapsibleObject
    public MaxHeight maxHeight = new MaxHeight();

    @ConfigEntry.Gui.CollapsibleObject
    public Size size = new Size();

    @ConfigEntry.Gui.CollapsibleObject
    public Misc misc = new Misc();

    public static class Spawnrate {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Replaces Mineshafts in Birch biomes.
                How often Mineshafts will spawn.
                0 for no Mineshafts and 1000 for max spawnrate.
                Note: Set this to 0 and restart to spawn Vanilla Mineshafts.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int birchMineshaftSpawnrate = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Replaces Mineshafts in Jungle biomes.
                How often Mineshafts will spawn.
                0 for no Mineshafts and 1000 for max spawnrate.
                Note: Set this to 0 and restart to spawn Vanilla Mineshafts.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int jungleMineshaftSpawnrate = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Replaces Mineshafts in Desert biomes.
                How often Mineshafts will spawn.
                0 for no Mineshafts and 1000 for max spawnrate.
                Note: Set this to 0 and restart to spawn Vanilla Mineshafts.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int desertMineshaftSpawnrate = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Replaces Mineshafts in Mountain (Extreme Hills) biomes.
                How often Mineshafts will spawn.
                0 for no Mineshafts and 1000 for max spawnrate.
                Note: Set this to 0 and restart to spawn Vanilla Mineshafts.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int stoneMineshaftSpawnrate = 25;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Replaces Mineshafts in Savanna biomes.
                How often Mineshafts will spawn.
                0 for no Mineshafts and 1000 for max spawnrate.
                Note: Set this to 0 and restart to spawn Vanilla Mineshafts.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int savannaMineshaftSpawnrate = 20;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Replaces Mineshafts in Snowy/Icy biomes.
                Note: Snowy Taiga Biomes will get Ice Mineshaft instead of Taiga theme.
                How often Mineshafts will spawn.
                0 for no Mineshafts and 1000 for max spawnrate.
                Note: Set this to 0 and restart to spawn Vanilla Mineshafts.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int icyMineshaftSpawnrate = 20;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Replaces Mineshafts in Ocean biomes.
                How often Mineshafts will spawn.
                0 for no Mineshafts and 1000 for max spawnrate.
                Note: Set this to 0 and restart to spawn Vanilla Mineshafts.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int oceanMineshaftSpawnrate = 15;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Replaces Mineshafts in Taiga biomes.
                How often Mineshafts will spawn.
                0 for no Mineshafts and 1000 for max spawnrate.
                Note: Set this to 0 and restart to spawn Vanilla Mineshafts.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int taigaMineshaftSpawnrate = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Replaces Mineshafts in Swamps.
                How often Mineshafts will spawn.
                0 for no Mineshafts and 1000 for max spawnrate.
                Note: Set this to 0 and restart to spawn Vanilla Mineshafts.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int swampMineshaftSpawnrate = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Replaces Mineshafts in Dark Forests.
                How often Mineshafts will spawn.
                0 for no Mineshafts and 1000 for max spawnrate.
                Note: Set this to 0 and restart to spawn Vanilla Mineshafts.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int darkForestMineshaftSpawnrate = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Add End themed Mineshafts to biomes outside the Enderdragon island.
                How often Mineshafts will spawn.
                0 for no Mineshafts and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int endMineshaftSpawnrate = 60;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Add Nether themed Mineshafts to non-crimson and non-warped Nether biomes.
                How often Mineshafts will spawn.
                0 for no Mineshafts and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int netherMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Add Crimson themed Mineshafts to Crimson Nether biomes.
                How often Mineshafts will spawn.
                0 for no Mineshafts and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int crimsonMineshaftSpawnrate = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Add Warped themed Mineshafts to Warped Nether biomes.
                How often Mineshafts will spawn.
                0 for no Mineshafts and 1000 for max spawnrate.""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
        public int warpedMineshaftSpawnrate = 40;
    }


    public static class MinHeight {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Min Y height of Mineshaft""")
        public int birchMineshaftMinHeight = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Min Y height of Mineshaft""")
        public int jungleMineshaftMinHeight = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Min Y height of Mineshaft""")
        public int desertMineshaftMinHeight = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Min Y height of Mineshaft""")
        public int stoneMineshaftMinHeight = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Min Y height of Mineshaft""")
        public int savannaMineshaftMinHeight = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Min Y height of Mineshaft""")
        public int icyMineshaftMinHeight = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Min Y height of Mineshaft""")
        public int oceanMineshaftMinHeight = 15;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Min Y height of Mineshaft""")
        public int taigaMineshaftMinHeight = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Min Y height of Mineshaft""")
        public int swampMineshaftMinHeight = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Min Y height of Mineshaft""")
        public int darkForestMineshaftMinHeight = 40;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Min Y height of Mineshaft""")
        public int netherMineshaftMinHeight = 6;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Min Y height of Mineshaft""")
        public int crimsonMineshaftMinHeight = 6;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Min Y height of Mineshaft""")
        public int warpedMineshaftMinHeight = 6;
    }


    public static class MaxHeight {

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Max Y height of Mineshaft
                If below min height, this will be read as min.""")
        public int birchMineshaftMaxHeight = 55;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Max Y height of Mineshaft
                If below min height, this will be read as min.""")
        public int jungleMineshaftMaxHeight = 55;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Max Y height of Mineshaft
                If below min height, this will be read as min.""")
        public int desertMineshaftMaxHeight = 55;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Max Y height of Mineshaft
                If below min height, this will be read as min.""")
        public int stoneMineshaftMaxHeight = 150;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Max Y height of Mineshaft
                If below min height, this will be read as min.""")
        public int savannaMineshaftMaxHeight = 120;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Max Y height of Mineshaft
                If below min height, this will be read as min.""")
        public int icyMineshaftMaxHeight = 150;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Max Y height of Mineshaft
                If below min height, this will be read as min.""")
        public int oceanMineshaftMaxHeight = 30;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Max Y height of Mineshaft
                If below min height, this will be read as min.""")
        public int taigaMineshaftMaxHeight = 55;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Max Y height of Mineshaft
                If below min height, this will be read as min.""")
        public int swampMineshaftMaxHeight = 55;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Max Y height of Mineshaft
                If below min height, this will be read as min.""")
        public int darkForestMineshaftMaxHeight = 55;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Max Y height of Mineshaft
                If below min height, this will be read as min.""")
        public int netherMineshaftMaxHeight = 17;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Max Y height of Mineshaft
                If below min height, this will be read as min.""")
        public int crimsonMineshaftMaxHeight = 14;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Max Y height of Mineshaft
                If below min height, this will be read as min.""")
        public int warpedMineshaftMaxHeight = 14;
    }
    public static class Size {
        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the mineshaft. This is how many pieces long a branch can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int birchMineshaftSize = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the mineshaft. This is how many pieces long a branch can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int jungleMineshaftSize = 6;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the mineshaft. This is how many pieces long a branch can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int desertMineshaftSize = 6;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the mineshaft. This is how many pieces long a branch can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int stoneMineshaftSize = 9;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the mineshaft. This is how many pieces long a branch can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int savannaMineshaftSize = 9;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the mineshaft. This is how many pieces long a branch can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int icyMineshaftSize = 9;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the mineshaft. This is how many pieces long a branch can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int oceanMineshaftSize = 9;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the mineshaft. This is how many pieces long a branch can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int taigaMineshaftSize = 9;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the mineshaft. This is how many pieces long a branch can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int swampMineshaftSize = 9;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the mineshaft. This is how many pieces long a branch can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int darkForestMineshaftSize = 9;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the mineshaft. This is how many pieces long a branch can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int endMineshaftSize = 11;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the mineshaft. This is how many pieces long a branch can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int netherMineshaftSize = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the mineshaft. This is how many pieces long a branch can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int crimsonMineshaftSize = 10;

        @ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.RequiresRestart
        @Comment("""



                Size of the mineshaft. This is how many pieces long a branch can be from the start piece.""")
        @ConfigEntry.BoundedDiscrete(min = 1, max = 30)
        public int warpedMineshaftSize = 10;
    }

    public static class Misc {
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.Tooltip(count = 0)
        @Comment("""



                The minimum thickness of End islands that the End Mineshaft can spawn in.
                So 30 means the End Mineshaft will spawn in land that is at least 30 blocks vertically in the area.
                Do 0 to turn off this check and allow the End Mineshaft to spawn anywhere including floating in midair""")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
        public Integer endMineshaftMinIslandThickness = 30;
    }
}
