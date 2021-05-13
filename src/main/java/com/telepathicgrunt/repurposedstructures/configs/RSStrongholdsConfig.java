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
					.comment("\n Add the ID/resource location of the biome you don't want",
							" RS's strongholds to spawn in. Separate each ID with a comma ,",
							"   Example: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
					.translation("repurposedstructures.config.stronghold.blacklistedstrongholdbiomes")
					.define("blacklistedStrongholdBiomes", " "));

				strongholdSize = subscriber.subscribe(builder
					.comment("\n How large the Stronghold is on average as a percentage.",
							 " Note: The Stonghold is much larger by default. To get something ",
							 " closer to vanilla stronghold size, use the value of 60.",
							 " 10 for supertiny Strongholds and 2000 for supermassive Strongholds.")
					.translation("repurposedstructures.config.stronghold.strongholdsizesh")
					.defineInRange("strongholdSizeSH", 100D, 10, 2000));

				allowExtraSpawners = subscriber.subscribe(builder
					.comment("\n Make Mob Spawners generate in rooms other than the Portal Room in Strongholds.",
							" Note: Spawners in Portal Room will always remain.")
					.translation("repurposedstructures.config.stronghold.allowextraspawnerssh")
					.define("allowExtraSpawnersSH", true));

				lootChests = subscriber.subscribe(builder
					.comment("\n Controls whether loot chests spawn or not in the Stronghold.")
					.translation("repurposedstructures.config.stronghold.lootchestssh")
					.define("lootChestsSH", true));


				builder.push("Nether");

					netherStrongholdMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n How rare are Nether-styled Strongholds in Nether-category biomes.",
								 " 1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.stronghold.netherstrongholdmaxChunkDistance")
						.defineInRange("netherStrongholdMaxChunkDistance", 85, 1, 1001));

					addNetherStrongholdToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Allow Nether-styled Stronghold to generate in modded Nether biomes.")
						.translation("repurposedstructures.config.stronghold.addnetherstrongholdtomoddedbiomes")
						.define("addNetherStrongholdToModdedBiomes", true));

					allowNetherStronghold = subscriber.subscribe(builder
						.comment("\n Allow Nether-styled Strongholds to spawn in Nether category biomes.",
								 " Note: Eyes of Ender will work and show the closest Nether Stronghold too.")
						.translation("repurposedstructures.config.stronghold.allownetherstronghold")
						.define("allowNetherStronghold", true));

					netherStrongholdMinHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that Nether stronghold's starting point can spawn at. ",
							" Default is 35.",
							" Note: Strongholds will spawn between min and max y height set in config.")
						.translation("repurposedstructures.config.feature.stronghold.netherstrongholdminheight")
						.defineInRange("netherStrongholdMinHeight", 10, 0, 255));

					netherStrongholdMaxHeight = subscriber.subscribe(builder
						.comment("\n Minimum Y height that Nether stronghold's starting point can spawn at. ",
							" Default is 40.",
							" Note: Strongholds will spawn between min and max y height set in config.",
							" Setting this to below min height config will make strongholds spawn only at min height.")
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
