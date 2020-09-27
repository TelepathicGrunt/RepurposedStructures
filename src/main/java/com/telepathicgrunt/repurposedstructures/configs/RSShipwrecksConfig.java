package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class RSShipwrecksConfig
{
	public static class RSShipwrecksConfigValues
	{
		public ConfigValueListener<Boolean> addEndShipwreckToModdedBiomes;
		public ConfigValueListener<Boolean> addNetherBricksShipwreckToModdedBiomes;
		public ConfigValueListener<Boolean> addCrimsonShipwreckToModdedBiomes;
		public ConfigValueListener<Boolean> addWarpedShipwreckToModdedBiomes;
		public ConfigValueListener<Integer> endShipwreckSpawnrate;
		public ConfigValueListener<Integer> netherBricksShipwreckSpawnrate;
		public ConfigValueListener<Integer> crimsonShipwreckSpawnrate;
		public ConfigValueListener<Integer> warpedShipwreckSpawnrate;
		public ConfigValueListener<String> blacklistedShipwreckBiomes;

		public RSShipwrecksConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Shipwrecks");

				blacklistedShipwreckBiomes = subscriber.subscribe(builder
					.comment("\r\n Add the ID/resource location of the biome you don't want"
							+"\r\n RS's shipwrecks to spawn in. Separate each ID with a comma ,"
							+"\r\n"
							+"\r\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
					.translation("repurposedstructures.config.shipwrecks.blacklistedshipwreckbiomes")
					.define("blacklistedShipwreckBiomes", ""));

				addEndShipwreckToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n Add End Shipwreck to modded End biomes.")
					.translation("repurposedstructures.config.shipwrecks.addendshipwrecktomoddedbiomes")
					.define("addEndShipwreckToModdedBiomes", true));

				endShipwreckSpawnrate = subscriber.subscribe(builder
					.comment("\r\n How rare are End Shipwreck in End Highlands biomes."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.shipwrecks.endshipwreckspawnrate")
					.defineInRange("endShipwreckSpawnrate", 15, 1, 1001));


				addNetherBricksShipwreckToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n Add Nether Bricks Shipwreck to modded non-warped or non-crimson Nether biomes.")
					.translation("repurposedstructures.config.shipwrecks.addnetherbricksshipwrecktomoddedbiomes")
					.define("addNetherBricksShipwreckToModdedBiomes", true));

				netherBricksShipwreckSpawnrate = subscriber.subscribe(builder
					.comment("\r\n How rare are Nether Bricks Shipwreck in any non-warped or non-crimson Nether biome."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.shipwrecks.netherbricksshipwreckspawnrate")
					.defineInRange("netherBricksShipwreckSpawnrate", 51, 1, 1001));


				addCrimsonShipwreckToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n Add Crimson Shipwreck to modded Crimson-named Nether biome.")
					.translation("repurposedstructures.config.shipwrecks.addcrimsonshipwrecktomoddedbiomes")
					.define("addCrimsonShipwreckToModdedBiomes", true));

				crimsonShipwreckSpawnrate = subscriber.subscribe(builder
					.comment("\r\n How rare are Crimson Shipwreck in Crimson Nether biomes."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.shipwrecks.crimsonshipwreckspawnrate")
					.defineInRange("crimsonShipwreckSpawnrate", 41, 1, 1001));


				addWarpedShipwreckToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n Add Warped Shipwreck to modded Warped Nether biomes.")
					.translation("repurposedstructures.config.shipwrecks.addwarpedshipwrecktomoddedbiomes")
					.define("addWarpedShipwreckToModdedBiomes", true));

				warpedShipwreckSpawnrate = subscriber.subscribe(builder
					.comment("\r\n How rare are Warped Shipwreck in Warped Nether biomes."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.shipwrecks.warpedshipwreckspawnrate")
					.defineInRange("warpedShipwreckSpawnrate", 41, 1, 1001));

			builder.pop();
		}
	}
}
