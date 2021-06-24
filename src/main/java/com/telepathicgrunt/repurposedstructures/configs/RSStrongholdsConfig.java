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

	public static class Nether {
		@ConfigEntry.Gui.Tooltip(count = 0)
		@ConfigEntry.Gui.PrefixText
		@ConfigEntry.Gui.RequiresRestart
		@Comment("Size of Nether Stronghold. This number is how many pieces deep a branch can go from the center piece."
				+ "\n1 for supertiny and 20 for supermassive Strongholds.")
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
		public int netherStrongholdMaxChunkDistance = 100;

		@ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
		@Comment("Min Y height that the starting point can spawn at."
				+"\nDefault is 30.")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int netherStrongholdMinHeight = 6;

		@ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
        @ConfigEntry.Gui.RequiresRestart
		@Comment("""
				Max Y height that the starting point can spawn at.
				Default is 35.
				If below min height, this will be read as min.""")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int netherStrongholdMaxHeight = 36;
	}
}
