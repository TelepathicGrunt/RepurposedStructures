package com.telepathicgrunt.repurposedstructures;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class RSStrongholdsConfig
{
	public static class RSStrongholdsConfigValues
	{
		public ConfigValueListener<Boolean> useVanillaStronghold;
		public ConfigValueListener<Boolean> allowStonebrickStronghold;
		public ConfigValueListener<Boolean> allowNetherStronghold;
		public ConfigValueListener<Integer> strongholdSpawnrate;
		public ConfigValueListener<Double> silverfishSpawnrateSH;
		public ConfigValueListener<Boolean> allowExtraSpawnersSH;
		public ConfigValueListener<Double> strongholdSizeSH;
		public ConfigValueListener<Boolean> lootChestsSH;
		public ConfigValueListener<Integer> normalStrongholdMinHeight;
		public ConfigValueListener<Integer> normalStrongholdMaxHeight;
		public ConfigValueListener<Integer> netherStrongholdMinHeight;
		public ConfigValueListener<Integer> netherStrongholdMaxHeight;
		
		RSStrongholdsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{

			builder.push("Stronghold");

				useVanillaStronghold = subscriber.subscribe(builder
					.comment("\r\n Use vanilla Stronghold instead of using this mod's modded version.\r\n"
							+" Note: The other Stronghold configs here will have no effect on vanilla Strongholds. \r\n"
							+" WARNING: Nether Strongholds will be turned off too when this option is on.")
					.translation("repurposedstructures.config.structure.stronghold.usevanillastronghold")
					.define("useVanillaStronghold", false));

				allowStonebrickStronghold = subscriber.subscribe(builder
					.comment("\r\n Allow Stonebrick styled Stronghold to generate in non-Nether biomes.")
					.translation("repurposedstructures.config.structure.stronghold.allowstonebrickstronghold")
					.define("allowStonebrickStronghold", true));

				allowNetherStronghold = subscriber.subscribe(builder
					.comment("\r\n Allow Nether-styled Strongholds to spawn in Nether category biomes."
							+ "\r\n Note: Eyes of Ender will work and show the closest Nether Stronghold too.")
					.translation("repurposedstructures.config.structure.stronghold.allownetherstronghold")
					.define("allowNetherStronghold", true));
			
				strongholdSpawnrate = subscriber.subscribe(builder
						.comment("\r\n How rare are Strongholds." 
								+ "\n " 
								+ "1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.structure.stronghold.strongholdspawnrate")
						.defineInRange("strongholdSpawnrate", 85, 1, 1001));
	
				silverfishSpawnrateSH = subscriber.subscribe(builder
						.comment("\r\n How often Silverfish Blocks will generate in Strongholds as a percentage." 
								+ "\r\n Note: Mossy Stone Bricks block cannot be infected by Silverfish"
								+ "\n "
								+ "0 for no Silverfish Blocks and 100 for max spawnrate.")
						.translation("repurposedstructures.config.structure.stronghold.silverfishspawnratesh")
						.defineInRange("silverfishSpawnrateSH", 0.8D, 0, 100));
	
				allowExtraSpawnersSH = subscriber.subscribe(builder
						.comment("\r\n Make Mob Spawners generate in rooms other than the Portal Room in Strongholds.\r\n"
								+" Note: Spawners in Portal Room will always remain.")
						.translation("repurposedstructures.config.structure.stronghold.allowextraspawnerssh")
						.define("allowExtraSpawnersSH", true));
	
				strongholdSizeSH = subscriber.subscribe(builder
						.comment("\r\n How large the Stronghold is on average as a percentage." 
								+ "\r\n Note: The Stonghold is much larger by default. To get something "
								+ "\r\n closer to vanilla stronghold size, use the value of 60."
								+ "\n "
								+ "10 for supertiny Strongholds and 2000 for supermassive Strongholds.")
						.translation("repurposedstructures.config.structure.stronghold.strongholdsizesh")
						.defineInRange("strongholdSizeSH", 100D, 10, 2000));

				normalStrongholdMinHeight = subscriber.subscribe(builder
					.comment("\r\n Minimum Y height that normal stronghold's starting point can spawn at. \r\n"
						+ "Default is 0.\r\n"
						+" Note: Strongholds will spawn between min and max y height set in config.\r\n")
					.translation("repurposedstructures.config.feature.stronghold.normalstrongholdminheight")
					.defineInRange("normalStrongholdMinHeight", 0, 0, 255));
				
				normalStrongholdMaxHeight = subscriber.subscribe(builder
					.comment("\r\n Minimum Y height that normal stronghold's starting point can spawn at.\r\n"
						+ " Default is 50.\r\n"
						+" Note: Strongholds will spawn between min and max y height set in config.\r\n"
						+" Setting this to below min height config will make strongholds spawn only at min height.")
					.translation("repurposedstructures.config.feature.stronghold.normalstrongholdmaxheight")
					.defineInRange("normalStrongholdMaxHeight", 50, 0, 255));

				netherStrongholdMinHeight = subscriber.subscribe(builder
					.comment("\r\n Minimum Y height that Nether stronghold's starting point can spawn at. \r\n"
						+ "Default is 35.\r\n"
						+" Note: Strongholds will spawn between min and max y height set in config.\r\n")
					.translation("repurposedstructures.config.feature.stronghold.netherstrongholdminheight")
					.defineInRange("netherStrongholdMinHeight", 35, 0, 255));
				
				netherStrongholdMaxHeight = subscriber.subscribe(builder
					.comment("\r\n Minimum Y height that Nether stronghold's starting point can spawn at. \r\n"
						+ "Default is 40.\r\n"
						+" Note: Strongholds will spawn between min and max y height set in config.\r\n"
						+" Setting this to below min height config will make strongholds spawn only at min height.")
					.translation("repurposedstructures.config.feature.stronghold.netherstrongholdmaxheight")
					.defineInRange("netherStrongholdMaxHeight", 40, 0, 255));
				
				lootChestsSH = subscriber.subscribe(builder
						.comment("\r\n Controls whether loot chests spawn or not in the Stronghold.")
					.translation("repurposedstructures.config.structure.stronghold.lootchestssh")
					.define("lootChestsSH", true));

			builder.pop();
		}
	}
}
