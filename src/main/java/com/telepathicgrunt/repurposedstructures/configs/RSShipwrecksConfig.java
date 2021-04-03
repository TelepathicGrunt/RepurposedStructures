package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSShipwrecksConfig
{
	public static class RSShipwrecksConfigValues
	{
		public ConfigValueListener<Boolean> addEndShipwreckToModdedBiomes;
		public ConfigValueListener<Boolean> addNetherBricksShipwreckToModdedBiomes;
		public ConfigValueListener<Boolean> addCrimsonShipwreckToModdedBiomes;
		public ConfigValueListener<Boolean> addWarpedShipwreckToModdedBiomes;
		public ConfigValueListener<Integer> endShipwreckMaxChunkDistance;
		public ConfigValueListener<Integer> netherBricksShipwreckMaxChunkDistance;
		public ConfigValueListener<Integer> crimsonShipwreckMaxChunkDistance;
		public ConfigValueListener<Integer> warpedShipwreckMaxChunkDistance;
		public ConfigValueListener<String> blacklistedShipwreckBiomes;

		public RSShipwrecksConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Shipwrecks");

				blacklistedShipwreckBiomes = subscriber.subscribe(builder
					.comment("\n Add the ID/resource location of the biome you don't want",
							" RS's shipwrecks to spawn in. Separate each ID with a comma ,",
							"   Example: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
					.translation("repurposedstructures.config.shipwrecks.blacklistedshipwreckbiomes")
					.define("blacklistedShipwreckBiomes", " "));

				addEndShipwreckToModdedBiomes = subscriber.subscribe(builder
					.comment("\n Add End Shipwreck to modded End biomes.")
					.translation("repurposedstructures.config.shipwrecks.addendshipwrecktomoddedbiomes")
					.define("addEndShipwreckToModdedBiomes", true));

				endShipwreckMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are End Shipwreck in End Highlands biomes.",
							 " 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.shipwrecks.endshipwreckmaxchunkdistance")
					.defineInRange("endShipwreckMaxChunkDistance", 24, 1, 1001));


				addNetherBricksShipwreckToModdedBiomes = subscriber.subscribe(builder
					.comment("\n Add Nether Bricks Shipwreck to modded non-warped or non-crimson Nether biomes.")
					.translation("repurposedstructures.config.shipwrecks.addnetherbricksshipwrecktomoddedbiomes")
					.define("addNetherBricksShipwreckToModdedBiomes", true));

				netherBricksShipwreckMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Nether Bricks Shipwreck in any non-warped or non-crimson Nether biome.",
							 " 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.shipwrecks.netherbricksshipwreckmaxchunkdistance")
					.defineInRange("netherBricksShipwreckMaxChunkDistance", 51, 1, 1001));


				addCrimsonShipwreckToModdedBiomes = subscriber.subscribe(builder
					.comment("\n Add Crimson Shipwreck to modded Crimson-named Nether biome.")
					.translation("repurposedstructures.config.shipwrecks.addcrimsonshipwrecktomoddedbiomes")
					.define("addCrimsonShipwreckToModdedBiomes", true));

				crimsonShipwreckMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Crimson Shipwreck in Crimson Nether biomes.",
							 " 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.shipwrecks.crimsonshipwreckmaxchunkdistance")
					.defineInRange("crimsonShipwreckMaxChunkDistance", 41, 1, 1001));


				addWarpedShipwreckToModdedBiomes = subscriber.subscribe(builder
					.comment("\n Add Warped Shipwreck to modded Warped Nether biomes.")
					.translation("repurposedstructures.config.shipwrecks.addwarpedshipwrecktomoddedbiomes")
					.define("addWarpedShipwreckToModdedBiomes", true));

				warpedShipwreckMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Warped Shipwreck in Warped Nether biomes.",
							 " 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.shipwrecks.warpedshipwreckmaxchunkdistance")
					.defineInRange("warpedShipwreckMaxChunkDistance", 41, 1, 1001));

			builder.pop();
		}
	}
}
