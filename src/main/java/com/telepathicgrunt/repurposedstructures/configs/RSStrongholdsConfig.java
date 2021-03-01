package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSStrongholdsConfig
{
	public static class RSStrongholdsConfigValues
	{
		public ConfigValueListener<Double> strongholdSize;
		public ConfigValueListener<Boolean> lootChests;
		public ConfigValueListener<Boolean> allowExtraSpawners;

		public ConfigValueListener<Boolean> turnOffVanillaStrongholds;
		public ConfigValueListener<Boolean> allowStonebrickStrongholdToVanillaBiomes;
		public ConfigValueListener<Boolean> addStonebrickStrongholdToModdedBiomes;
		public ConfigValueListener<Integer> stonebrickStrongholdMaxChunkDistance;
		public ConfigValueListener<Integer> stonebrickStrongholdMinHeight;
		public ConfigValueListener<Integer> stonebrickStrongholdMaxHeight;
		public ConfigValueListener<Double> silverfishSpawnrate;
		public ConfigValueListener<Integer> stonebrickStrongholdChainSpawnrate;

		public ConfigValueListener<Boolean> allowNetherStronghold;
		public ConfigValueListener<Boolean> addNetherStrongholdToModdedBiomes;
		public ConfigValueListener<Integer> netherStrongholdMaxChunkDistance;
		public ConfigValueListener<Integer> netherStrongholdMinHeight;
		public ConfigValueListener<Integer> netherStrongholdMaxHeight;
		public ConfigValueListener<Integer> netherStrongholdChainSpawnrate;

		public ConfigValueListener<String> blacklistedStrongholdBiomes;
		
		public RSStrongholdsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Stronghold");

				blacklistedStrongholdBiomes = subscriber.subscribe(builder
					.comment("\n Add the ID/resource location of the biome you don't want"
							+"\n RS's strongholds to spawn in. Separate each ID with a comma ,"
							+"\n"
							+"\n Example: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
					.translation("repurposedstructures.config.stronghold.blacklistedstrongholdbiomes")
					.define("blacklistedStrongholdBiomes", " "));

				strongholdSize = subscriber.subscribe(builder
					.comment("\n How large the Stronghold is on average as a percentage."
							+ "\n Note: The Stonghold is much larger by default. To get something "
							+ "\n closer to vanilla stronghold size, use the value of 60."
							+ "\n 10 for supertiny Strongholds and 2000 for supermassive Strongholds.")
					.translation("repurposedstructures.config.stronghold.strongholdsizesh")
					.defineInRange("strongholdSizeSH", 100D, 10, 2000));

				allowExtraSpawners = subscriber.subscribe(builder
					.comment("\n Make Mob Spawners generate in rooms other than the Portal Room in Strongholds.\n"
							+" Note: Spawners in Portal Room will always remain.")
					.translation("repurposedstructures.config.stronghold.allowextraspawnerssh")
					.define("allowExtraSpawnersSH", true));

				lootChests = subscriber.subscribe(builder
					.comment("\n Controls whether loot chests spawn or not in the Stronghold.")
					.translation("repurposedstructures.config.stronghold.lootchestssh")
					.define("lootChestsSH", true));

				turnOffVanillaStrongholds = subscriber.subscribe(builder
					.comment("\n Makes vanilla Strongholds no longer spawn at all." +
							"\n Will not affect Repurposed Structures's own Stonebrick Stronghold." +
							"\n If BetterStrongholds mod is on, this config has no effect as is treated as false.")
					.translation("repurposedstructures.config.stronghold.turnoffvanillastrongholds")
					.define("turnOffVanillaStrongholds", true));


			builder.push("Stonebrick");
					allowStonebrickStrongholdToVanillaBiomes = subscriber.subscribe(builder
						.comment("\n Add Stonebrick-styled Stronghold which replaces vanilla Strongholds in any" +
								"\n vanilla non-Nether biome. This option does not affect Nether Strongholds." +
								"\n If this is set to true and turnOffVanillaStrongholds is off, RS's Stonebrick" +
								"\n Strongholds will still be added to vanilla biomes.")
						.translation("repurposedstructures.config.stronghold.allowstonebrickstrongholdtovanillabiomes")
						.define("allowStonebrickStrongholdToVanillaBiomes", true));

					addStonebrickStrongholdToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add Stonebrick-styled Stronghold to all modded non-Nether biomes" +
								"\n even if they have don't have vanilla Strongholds. It will replace" +
								"\n the vanilla Strongholds in those biomes if this option is on.")
						.translation("repurposedstructures.config.stronghold.addstonebrickstrongholdtomoddedbiomes")
						.define("addStonebrickStrongholdToModdedBiomes", true));

					stonebrickStrongholdMaxChunkDistance = subscriber.subscribe(builder
							.comment("\n How rare are Stonebrick-styled Strongholds."
									+ "\n 1 for spawning in most chunks and 1001 for no spawn.")
							.translation("repurposedstructures.config.stronghold.stonebrickstrongholdmaxchunkdistance")
							.defineInRange("stonebrickStrongholdMaxChunkDistance", 85, 1, 1001));

					silverfishSpawnrate = subscriber.subscribe(builder
							.comment("\n How often Silverfish Blocks will generate in Strongholds as a percentage."
									+ "\n Note: Mossy Stone Bricks block cannot be infected by Silverfish"
									+ "\n 0 for no Silverfish Blocks and 100 for max spawnrate.")
							.translation("repurposedstructures.config.stronghold.silverfishspawnratesh")
							.defineInRange("silverfishSpawnrateSH", 0.8D, 0, 100));

					stonebrickStrongholdMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that normal stronghold's starting point can spawn at. \n"
							+" Default is 0.\n"
							+" Note: Strongholds will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.feature.stronghold.stonebrickstrongholdminheight")
						.defineInRange("stonebrickStrongholdMinHeight", 0, 0, 255));

					stonebrickStrongholdMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that normal stronghold's starting point can spawn at.\n"
							+" Default is 50.\n"
							+" Note: Strongholds will spawn between min and max y height set in config.\n"
							+" Setting this to below min height config will make strongholds spawn only at min height.")
						.translation("repurposedstructures.config.feature.stronghold.stonebrickstrongholdmaxheight")
						.defineInRange("stonebrickStrongholdMaxHeight", 45, 0, 255));

					stonebrickStrongholdChainSpawnrate = subscriber.subscribe(builder
						.comment("\n How rare are Chains in this Stronghold. (Can have Lantern attached)")
						.translation("repurposedstructures.config.feature.stronghold.stonebrickstrongholdchainspawnrate")
						.defineInRange("stonebrickStrongholdChainSpawnrate", 35, 0, 1000));

				builder.pop();

				builder.push("Nether");

					netherStrongholdMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n How rare are Nether-styled Strongholds in Nether-category biomes."
								+ "\n 1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.stronghold.netherstrongholdmaxChunkDistance")
						.defineInRange("netherStrongholdMaxChunkDistance", 85, 1, 1001));

					addNetherStrongholdToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Allow Nether-styled Stronghold to generate in modded Nether biomes.")
						.translation("repurposedstructures.config.stronghold.addnetherstrongholdtomoddedbiomes")
						.define("addNetherStrongholdToModdedBiomes", true));

					allowNetherStronghold = subscriber.subscribe(builder
						.comment("\n Allow Nether-styled Strongholds to spawn in Nether category biomes."
								+ "\n Note: Eyes of Ender will work and show the closest Nether Stronghold too.")
						.translation("repurposedstructures.config.stronghold.allownetherstronghold")
						.define("allowNetherStronghold", true));

					netherStrongholdMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that Nether stronghold's starting point can spawn at. \n"
							+" Default is 35.\n"
							+" Note: Strongholds will spawn between min and max y height set in config.\n")
						.translation("repurposedstructures.config.feature.stronghold.netherstrongholdminheight")
						.defineInRange("netherStrongholdMinHeight", 10, 0, 255));

					netherStrongholdMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that Nether stronghold's starting point can spawn at. \n"
							+" Default is 40.\n"
							+" Note: Strongholds will spawn between min and max y height set in config.\n"
							+" Setting this to below min height config will make strongholds spawn only at min height.")
						.translation("repurposedstructures.config.feature.stronghold.netherstrongholdmaxheight")
						.defineInRange("netherStrongholdMaxHeight", 36, 0, 255));

					netherStrongholdChainSpawnrate = subscriber.subscribe(builder
						.comment("\n How rare are Chains in this Stronghold. (Can have Soul Lantern attached)")
						.translation("repurposedstructures.config.feature.stronghold.netherstrongholdchainspawnrate")
						.defineInRange("netherStrongholdChainSpawnrate", 50, 0, 1000));

				builder.pop();
			builder.pop();
		}
	}
}
