package com.telepathicgrunt.repurposedstructures.configs;


import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "Strongholds")
public class RSStrongholdsConfig implements ConfigData
{
	@ConfigEntry.Gui.Tooltip(count = 4)
	@Comment("Size of average Stronghold as a percentage."
			+ "\nNote: RS Stonghold is much larger by default."
			+ "\nTo get closer to vanilla's size, enter 60 here."
			+ "\n10 for supertiny and 2000 for supermassive Strongholds.")
	@ConfigEntry.BoundedDiscrete(min = 10, max = 2000)
	public double strongholdSizeSH = 100D;

	@ConfigEntry.Gui.Tooltip(count = 2)
	@Comment("Add Mob Spawners to rooms other than the Portal Room."
			+"\nNote: Spawners in Portal Room will always spawn.")
	public boolean allowExtraSpawnersSH = true;

	@ConfigEntry.Gui.Tooltip
	@Comment("Controls whether loot chests spawn or not in the Stronghold.")
	public boolean lootChestsSH = true;


	@ConfigEntry.Gui.CollapsibleObject
	public RSStrongholdsConfig.Stonebrick stonebrick = new RSStrongholdsConfig.Stonebrick();

	@ConfigEntry.Gui.CollapsibleObject
	public RSStrongholdsConfig.Nether nether = new RSStrongholdsConfig.Nether();


	public static class Stonebrick {

		@ConfigEntry.Gui.Tooltip(count = 3)
		@Comment("Stonebrick-styled Stronghold replaces vanilla Strongholds" +
				"\nin any biome. If off, vanilla Strongholds will generate" +
				"\ninstead but Nether Strongholds will still be active.")
		public boolean allowStonebrickStronghold = true;

		@ConfigEntry.Gui.Tooltip(count = 2)
		@Comment("Add Stonebrick-styled Stronghold to all modded non-Nether" +
				"\nbiomes even if they have don't have vanilla Strongholds.")
		public boolean addStonebrickStrongholdToModdedBiomes = true;

		@ConfigEntry.Gui.Tooltip(count = 3)
		@Comment("Percentage of Stonebrick-styled Strongholds is Silverfish Blocks."
				+ "\nNote: Mossy Stone Bricks block cannot be infected by Silverfish."
				+ "\n0 for no Silverfish Blocks and 100 for max spawnrate.")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
		public double silverfishSpawnrateSH = 0.8D;

		@ConfigEntry.Gui.Tooltip(count = 2)
		@Comment("Min Y height that the starting point can spawn at."
				+ "\nDefault is 0.")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int stonebrickStrongholdMinHeight = 0;

		@ConfigEntry.Gui.Tooltip(count = 3)
		@Comment("Max Y height that the starting point can spawn at."
				+ "\nDefault is 50."
				+ "\nIf below min height, this will be read as min.")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int stonebrickStrongholdMaxHeight = 45;

		@ConfigEntry.Gui.Tooltip(count = 2)
		@Comment("How rare are Stonebrick-styled Strongholds."
				+ "\n1 for spawning in most chunks and 1001 for no spawn.")
		@ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
		public int stonebrickStrongholdSpawnrate = 85;

		@ConfigEntry.Gui.Tooltip(count = 2)
		@Comment("How rare are Chains in this Stronghold. (Can have Lantern attached)")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
		public int stonebrickStrongholdChainSpawnrate = 35;
	}

	public static class Nether {

		@ConfigEntry.Gui.Tooltip(count = 3)
		@Comment("How rare are Nether-styled Strongholds in Nether-category biomes."
				+ "\n1 for spawning in most chunks and 1001 for no spawn."
				+ "\nNote: Eyes of Ender will work and show the closest Nether Stronghold too.")
		@ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
		public int netherStrongholdSpawnrate = 85;

		@ConfigEntry.Gui.Tooltip(count = 2)
		@Comment("Allow Nether-styled Stronghold to"
				+ "\ngenerate in modded Nether biomes.")
		public boolean addNetherStrongholdToModdedBiomes = false;

		@ConfigEntry.Gui.Tooltip(count = 2)
		@Comment("Min Y height that the starting point can spawn at."
				+"\nDefault is 30.")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int netherStrongholdMinHeight = 33;

		@ConfigEntry.Gui.Tooltip(count = 3)
		@Comment("Max Y height that the starting point can spawn at."
				+"\nDefault is 35."
				+ "\nIf below min height, this will be read as min.")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int netherStrongholdMaxHeight = 36;

		@ConfigEntry.Gui.Tooltip(count = 2)
		@Comment("How rare are Chains in this Stronghold. (Can have Soul Lantern attached)")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 1000)
		public int netherStrongholdChainSpawnrate = 50;
	}
}
