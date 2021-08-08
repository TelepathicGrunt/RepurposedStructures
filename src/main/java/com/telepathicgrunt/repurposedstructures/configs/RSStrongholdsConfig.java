package com.telepathicgrunt.repurposedstructures.configs;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "Strongholds")
public class RSStrongholdsConfig implements ConfigData
{
	@ConfigEntry.Gui.CollapsibleObject
	public RSStrongholdsConfig.Nether nether = new RSStrongholdsConfig.Nether();

	@ConfigEntry.Gui.CollapsibleObject
	public RSStrongholdsConfig.End end = new RSStrongholdsConfig.End();

	public static class Nether {
		@ConfigEntry.Gui.Tooltip(count = 0)
		@ConfigEntry.Gui.PrefixText
		@ConfigEntry.Gui.RequiresRestart
		@Comment("""
    
    
    
				Size of Nether Stronghold.
				This number is how many pieces deep a branch can go from the center piece.
				1 for supertiny and 30 for supermassive Strongholds.""")
		@ConfigEntry.BoundedDiscrete(min = 1, max = 30)
		public int netherStrongholdSize = 15;

		@ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
		@Comment("""



				How rare are Nether-styled Strongholds in Nether-category biomes.
				1 for spawning in most chunks and 1001 for no spawn.
				Note: Eyes of Ender will work and show the closest Nether Stronghold too.""")
		@ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
		public int netherStrongholdAverageChunkDistance = 100;

		@ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
		@Comment("""



				Min Y height that the starting point can spawn at.""")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int netherStrongholdMinHeight = 10;

		@ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
		@Comment("""



				Max Y height that the starting point can spawn at.
				If below min height, this will be read as min.""")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int netherStrongholdMaxHeight = 31;
	}


	public static class End {
		@ConfigEntry.Gui.Tooltip(count = 0)
		@ConfigEntry.Gui.PrefixText
		@ConfigEntry.Gui.RequiresRestart
		@Comment("""
    
    
    
				Size of End Stronghold.
				This number is how many pieces deep a branch can go from the center piece.
				1 for supertiny and 30 for supermassive Strongholds.""")
		@ConfigEntry.BoundedDiscrete(min = 1, max = 30)
		public int endStrongholdSize = 15;

		@ConfigEntry.Gui.Tooltip(count = 0)
		@ConfigEntry.Gui.PrefixText
		@ConfigEntry.Gui.RequiresRestart
		@Comment("""



				How rare are End-styled Strongholds in End-category biome's islands.
				1 for spawning in most chunks and 10001 for no spawn.
				Note: Eyes of Ender will work and show the closest End Stronghold too.""")
		@ConfigEntry.BoundedDiscrete(min = 1, max = 10001)
		public int endStrongholdAverageChunkDistance = 130;

		@ConfigEntry.Gui.Tooltip(count = 0)
		@ConfigEntry.Gui.PrefixText
		@ConfigEntry.Gui.RequiresRestart
		@Comment("""



				Min Y height that the starting point can spawn at.""")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int endStrongholdMinHeight = 4;

		@ConfigEntry.Gui.Tooltip(count = 0)
		@ConfigEntry.Gui.PrefixText
		@ConfigEntry.Gui.RequiresRestart
		@Comment("""



				Max Y height that the starting point can spawn at.
				If below min height, this will be read as min.""")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int endStrongholdMaxHeight = 5;

		// regexpos1
	}
}
