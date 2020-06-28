package com.telepathicgrunt.repurposedstructures.configs;


import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "Repurposed_Structures-Strongholds")
public class RSStrongholdsConfig implements ConfigData
{
	@Comment("\r\n How large the Stronghold is on average as a percentage."
			+ "\r\n Note: The Stonghold is much larger by default. To get something "
			+ "\r\n closer to vanilla stronghold size, use the value of 60."
			+ "\r\n 10 for supertiny Strongholds and 2000 for supermassive Strongholds.")
	@ConfigEntry.BoundedDiscrete(min = 10, max = 2000)
	public double strongholdSizeSH = 100D;

	@Comment("\r\n Make Mob Spawners generate in rooms other than the Portal Room in Strongholds.\r\n"
			+" Note: Spawners in Portal Room will always remain.")
	public boolean allowExtraSpawnersSH = true;

	@Comment("\r\n Controls whether loot chests spawn or not in the Stronghold.")
	public boolean lootChestsSH = true;


	@ConfigEntry.Gui.CollapsibleObject
	public RSStrongholdsConfig.Stonebrick stonebrick = new RSStrongholdsConfig.Stonebrick();

	@ConfigEntry.Gui.CollapsibleObject
	public RSStrongholdsConfig.Nether nether = new RSStrongholdsConfig.Nether();


	public static class Stonebrick {

		@Comment("\r\n Allow Stonebrick-styled Stronghold to replace vanilla Strongholds" +
				"\r\n in any biome. If off, vanilla Strongholds will still generate while " +
				"\r\n this mod's Stonebrick-styled Stronghold will not generate. " +
				"\r\n (Note: Nether Stronghold will still spawn)")
		public boolean allowStonebrickStronghold = true;

		@Comment("\r\n Allow Stonebrick-styled Stronghold to generate in all modded non-Nether biomes " +
				"\r\n regardless if they have vanilla Strongholds or not.")
		public boolean addStonebrickStrongholdToModdedBiomes = false;

		@Comment("\r\n How often Silverfish Blocks will generate in Stonebrick-styled Strongholds as a percentage."
				+ "\r\n Note: Mossy Stone Bricks block cannot be infected by Silverfish"
				+ "\r\n 0 for no Silverfish Blocks and 100 for max spawnrate.")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
		public double silverfishSpawnrateSH = 0.8D;

		@Comment("\r\n Minimum Y height that Stonebrick-styled stronghold's starting point can spawn at."
				+ "\r\n Default is 0."
				+ "\r\n Note: Strongholds will spawn between min and max y height set in config.")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int stonebrickStrongholdMinHeight = 0;

		@Comment("\r\n Minimum Y height that Stonebrick-styled stronghold's starting point can spawn at."
				+ "\r\n Default is 50."
				+ "\r\n Note: Strongholds will spawn between min and max y height set in config."
				+ "\r\n Setting this to below min height config will make strongholds spawn only at min height.")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int stonebrickStrongholdMaxHeight = 45;

		@Comment("\r\n How rare are Stonebrick-styled Strongholds."
				+ "\r\n 1 for spawning in most chunks and 1001 for no spawn.")
		@ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
		public int stonebrickStrongholdSpawnrate = 85;
	}

	public static class Nether {

		@Comment("\r\n Allow Stonebrick-styled Stronghold to generate in modded Nether biomes.")
		public boolean addNetherStrongholdToModdedBiomes = false;

		@Comment("\r\n Minimum Y height that Nether stronghold's starting point can spawn at."
				+"\r\n Default is 30."
				+"\r\n Note: Strongholds will spawn between min and max y height set in config.")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int netherStrongholdMinHeight = 33;

		@Comment("\r\n Minimum Y height that Nether stronghold's starting point can spawn at."
				+"\r\n Default is 35."
				+"\r\n Note: Strongholds will spawn between min and max y height set in config."
				+"\r\n Setting this to below min height config will make strongholds spawn only at min height.")
		@ConfigEntry.BoundedDiscrete(min = 0, max = 255)
		public int netherStrongholdMaxHeight = 36;

		@Comment("\r\n How rare are Nether-styled Strongholds in Nether category biomes."
				+ "\r\n 1 for spawning in most chunks and 1001 for no spawn."
				+ "\r\n Note: Eyes of Ender will work and show the closest Nether Stronghold too.")
		@ConfigEntry.BoundedDiscrete(min = 1, max = 1001)
		public int netherStrongholdSpawnrate = 85;
	}
}
