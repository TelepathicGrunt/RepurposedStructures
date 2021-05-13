package com.telepathicgrunt.repurposedstructures.configs;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "Strongholds")
public class RSStrongholdsConfig implements ConfigData
{
	@ConfigEntry.Gui.Tooltip(count = 0)
	@ConfigEntry.Gui.PrefixText
	@Comment("Size of average Stronghold as a percentage."
			+ "\nNote: RS Stonghold is much larger by default."
			+ "\nTo get closer to vanilla's size, enter 60 here."
			+ "\n10 for supertiny and 2000 for supermassive Strongholds.")
	@ConfigEntry.BoundedDiscrete(min = 10, max = 2000)
	public double strongholdSizeSH = 100D;

	@ConfigEntry.Gui.Tooltip(count = 0)
	@ConfigEntry.Gui.PrefixText
	@Comment("Add Mob Spawners to rooms other than the Portal Room."
			+"\nNote: Spawners in Portal Room will always spawn.")
	public boolean allowExtraSpawnersSH = true;

	@ConfigEntry.Gui.Tooltip(count = 0)
	@ConfigEntry.Gui.PrefixText
	@Comment("Controls whether loot chests spawn or not in the Stronghold.")
	public boolean lootChestsSH = true;

	@ConfigEntry.Gui.Tooltip(count = 0)
	@ConfigEntry.Gui.PrefixText
	@Comment("Add the ID/resource location of the biome you don't want"
			+"\nRS's strongholds to spawn in. Separate each ID with a comma ,"
			+"\n"
			+"\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
	public String blacklistedStrongholdBiomes = "";

	@ConfigEntry.Gui.CollapsibleObject
	public RSStrongholdsConfig.Nether nether = new RSStrongholdsConfig.Nether();

	public static class Nether {

		@ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
		@Comment("How rare are Nether-styled Strongholds in Nether-category biomes."
				+ "\n1 for spawning in most chunks and 1001 for no spawn."
				+ "\nNote: Eyes of Ender will work and show the closest Nether Stronghold too.")
		@ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
		public int netherStrongholdMaxChunkDistance = 85;

		@ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
		@Comment("Allow Nether-styled Stronghold to"
				+ "\ngenerate in modded Nether biomes.")
		public boolean addNetherStrongholdToModdedBiomes = true;

		@ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
		@Comment("Min Y height that the starting point can spawn at."
				+"\nDefault is 30.")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int netherStrongholdMinHeight = 10;

		@ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
		@Comment("Max Y height that the starting point can spawn at."
				+"\nDefault is 35."
				+ "\nIf below min height, this will be read as min.")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int netherStrongholdMaxHeight = 36;

		@ConfigEntry.Gui.Tooltip(count = 0)
        @ConfigEntry.Gui.PrefixText
		@Comment("How rare are Chains in this Stronghold. (Can have Soul Lantern attached)")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
		public int netherStrongholdChainSpawnrate = 50;
	}
}
