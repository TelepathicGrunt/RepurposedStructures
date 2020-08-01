package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class RSStrongholdsConfig
{
	public static class RSStrongholdsConfigValues
	{
		public ConfigValueListener<Double> strongholdSize;
		public ConfigValueListener<Boolean> lootChests;
		public ConfigValueListener<Boolean> allowExtraSpawners;

		public ConfigValueListener<Boolean> allowStonebrickStronghold;
		public ConfigValueListener<Boolean> addStonebrickStrongholdToModdedBiomes;
		public ConfigValueListener<Integer> stonebrickStrongholdSpawnrate;
		public ConfigValueListener<Integer> stonebrickStrongholdMinHeight;
		public ConfigValueListener<Integer> stonebrickStrongholdMaxHeight;
		public ConfigValueListener<Double> silverfishSpawnrate;
		public ConfigValueListener<Integer> stonebrickStrongholdChainSpawnrate;

		public ConfigValueListener<Boolean> allowNetherStronghold;
		public ConfigValueListener<Boolean> addNetherStrongholdToModdedBiomes;
		public ConfigValueListener<Integer> netherStrongholdSpawnrate;
		public ConfigValueListener<Integer> netherStrongholdMinHeight;
		public ConfigValueListener<Integer> netherStrongholdMaxHeight;
		public ConfigValueListener<Integer> netherStrongholdChainSpawnrate;

		
		public RSStrongholdsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{

			builder.push("Stronghold");

				strongholdSize = subscriber.subscribe(builder
					.comment("\r\n How large the Stronghold is on average as a percentage."
							+ "\r\n Note: The Stonghold is much larger by default. To get something "
							+ "\r\n closer to vanilla stronghold size, use the value of 60."
							+ "\n "
							+ "10 for supertiny Strongholds and 2000 for supermassive Strongholds.")
					.translation("repurposedstructures.config.structure.stronghold.strongholdsizesh")
					.defineInRange("strongholdSizeSH", 100D, 10, 2000));

				allowExtraSpawners = subscriber.subscribe(builder
					.comment("\r\n Make Mob Spawners generate in rooms other than the Portal Room in Strongholds.\r\n"
							+" Note: Spawners in Portal Room will always remain.")
					.translation("repurposedstructures.config.structure.stronghold.allowextraspawnerssh")
					.define("allowExtraSpawnersSH", true));

				lootChests = subscriber.subscribe(builder
					.comment("\r\n Controls whether loot chests spawn or not in the Stronghold.")
					.translation("repurposedstructures.config.structure.stronghold.lootchestssh")
					.define("lootChestsSH", true));

				builder.push("Stonebrick");
					allowStonebrickStronghold = subscriber.subscribe(builder
						.comment("\r\nStonebrick-styled Stronghold replaces vanilla Strongholds in any" +
								"\nbiome that has it. If off, vanilla Strongholds will generate" +
								"\nonce again but Nether Strongholds will still be active.\r\n")
						.translation("repurposedstructures.config.structure.stronghold.allowstonebrickstronghold")
						.define("allowStonebrickStronghold", true));

					addStonebrickStrongholdToModdedBiomes = subscriber.subscribe(builder
						.comment("\r\nAdd Stonebrick-styled Stronghold to all modded non-Nether" +
								"\nbiomes even if they have don't have vanilla Strongholds.\r\n")
						.translation("repurposedstructures.config.structure.stronghold.addstonebrickstrongholdtomoddedbiomes")
						.define("addStonebrickStrongholdToModdedBiomes", true));

					stonebrickStrongholdSpawnrate = subscriber.subscribe(builder
							.comment("\r\n How rare are Stonebrick-styled Strongholds."
									+ "\n "
									+ "1 for spawning in most chunks and 1001 for no spawn.")
							.translation("repurposedstructures.config.structure.stronghold.stonebrickstrongholdspawnrate")
							.defineInRange("stonebrickStrongholdSpawnrate", 85, 1, 1001));

					silverfishSpawnrate = subscriber.subscribe(builder
							.comment("\r\n How often Silverfish Blocks will generate in Strongholds as a percentage."
									+ "\r\n Note: Mossy Stone Bricks block cannot be infected by Silverfish"
									+ "\n "
									+ "0 for no Silverfish Blocks and 100 for max spawnrate.")
							.translation("repurposedstructures.config.structure.stronghold.silverfishspawnratesh")
							.defineInRange("silverfishSpawnrateSH", 0.8D, 0, 100));

					stonebrickStrongholdMinHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that normal stronghold's starting point can spawn at. \r\n"
							+ "Default is 0.\r\n"
							+" Note: Strongholds will spawn between min and max y height set in config.\r\n")
						.translation("repurposedstructures.config.feature.stronghold.stonebrickstrongholdminheight")
						.defineInRange("stonebrickStrongholdMinHeight", 0, 0, 255));

					stonebrickStrongholdMaxHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that normal stronghold's starting point can spawn at.\r\n"
							+ " Default is 50.\r\n"
							+" Note: Strongholds will spawn between min and max y height set in config.\r\n"
							+" Setting this to below min height config will make strongholds spawn only at min height.")
						.translation("repurposedstructures.config.feature.stronghold.stonebrickstrongholdmaxheight")
						.defineInRange("stonebrickStrongholdMaxHeight", 45, 0, 255));

					stonebrickStrongholdChainSpawnrate = subscriber.subscribe(builder
						.comment("\r\n How rare are Chains in this Stronghold. (Can have Lantern attached)\r\n")
						.translation("repurposedstructures.config.feature.stronghold.stonebrickstrongholdchainspawnrate")
						.defineInRange("stonebrickStrongholdChainSpawnrate", 35, 0, 1000));

				builder.pop();

				builder.push("Nether");

					netherStrongholdSpawnrate = subscriber.subscribe(builder
						.comment("\r\n How rare are Nether-styled Strongholds in Nether-category biomes."
								+ "\n "
								+ "1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.structure.stronghold.netherstrongholdspawnrate")
						.defineInRange("netherStrongholdSpawnrate", 85, 1, 1001));

					addNetherStrongholdToModdedBiomes  = subscriber.subscribe(builder
						.comment("\r\nAllow Nether-styled Stronghold to"
								+ "\ngenerate in modded Nether biomes.\r\n")
						.translation("repurposedstructures.config.structure.stronghold.addnetherstrongholdtomoddedbiomes ")
						.define("addNetherStrongholdToModdedBiomes ", true));

					allowNetherStronghold = subscriber.subscribe(builder
						.comment("\r\n Allow Nether-styled Strongholds to spawn in Nether category biomes."
								+ "\r\n Note: Eyes of Ender will work and show the closest Nether Stronghold too.")
						.translation("repurposedstructures.config.structure.stronghold.allownetherstronghold")
						.define("allowNetherStronghold", true));

					netherStrongholdMinHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that Nether stronghold's starting point can spawn at. \r\n"
							+ "Default is 35.\r\n"
							+" Note: Strongholds will spawn between min and max y height set in config.\r\n")
						.translation("repurposedstructures.config.feature.stronghold.netherstrongholdminheight")
						.defineInRange("netherStrongholdMinHeight", 33, 0, 255));

					netherStrongholdMaxHeight = subscriber.subscribe(builder
						.comment("\r\n Minimum Y height that Nether stronghold's starting point can spawn at. \r\n"
							+ "Default is 40.\r\n"
							+" Note: Strongholds will spawn between min and max y height set in config.\r\n"
							+" Setting this to below min height config will make strongholds spawn only at min height.")
						.translation("repurposedstructures.config.feature.stronghold.netherstrongholdmaxheight")
						.defineInRange("netherStrongholdMaxHeight", 36, 0, 255));

					netherStrongholdChainSpawnrate = subscriber.subscribe(builder
						.comment("\r\n How rare are Chains in this Stronghold. (Can have Soul Lantern attached)\r\n")
						.translation("repurposedstructures.config.feature.stronghold.netherstrongholdchainspawnrate")
						.defineInRange("netherStrongholdChainSpawnrate", 50, 0, 1000));

				builder.pop();
			builder.pop();
		}
	}
}
