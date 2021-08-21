package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSShipwrecksConfig
{
	public static class RSShipwrecksConfigValues
	{
		public ConfigValueListener<Integer> endShipwreckMaxChunkDistance;
		public ConfigValueListener<Integer> netherBricksShipwreckMaxChunkDistance;
		public ConfigValueListener<Integer> crimsonShipwreckMaxChunkDistance;
		public ConfigValueListener<Integer> warpedShipwreckMaxChunkDistance;

		public RSShipwrecksConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Shipwrecks");

				endShipwreckMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are End Shipwreck in End Highlands biomes.",
							 " 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.shipwrecks.endshipwreckmaxchunkdistance")
					.defineInRange("endShipwreckMaxChunkDistance", 24, 1, 1001));

				netherBricksShipwreckMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Nether Bricks Shipwreck in any non-warped or non-crimson Nether biome.",
							 " 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.shipwrecks.netherbricksshipwreckmaxchunkdistance")
					.defineInRange("netherBricksShipwreckMaxChunkDistance", 21, 1, 1001));

				crimsonShipwreckMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Crimson Shipwreck in Crimson Nether biomes.",
							 " 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.shipwrecks.crimsonshipwreckmaxchunkdistance")
					.defineInRange("crimsonShipwreckMaxChunkDistance", 18, 1, 1001));

				warpedShipwreckMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Warped Shipwreck in Warped Nether biomes.",
							 " 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.shipwrecks.warpedshipwreckmaxchunkdistance")
					.defineInRange("warpedShipwreckMaxChunkDistance", 18, 1, 1001));

			builder.pop();
		}
	}
}
