package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSShipwrecksConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue endShipwreckMaxChunkDistance;
	public static ForgeConfigSpec.IntValue netherBricksShipwreckMaxChunkDistance;
	public static ForgeConfigSpec.IntValue crimsonShipwreckMaxChunkDistance;
	public static ForgeConfigSpec.IntValue warpedShipwreckMaxChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		builder.push("Shipwrecks");

			endShipwreckMaxChunkDistance = builder
				.comment("\n How rare are End Shipwreck in End Highlands biomes.",
						 " 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.shipwrecks.endshipwreckmaxchunkdistance")
				.defineInRange("endShipwreckMaxChunkDistance", 24, 1, 1001);

			netherBricksShipwreckMaxChunkDistance = builder
				.comment("\n How rare are Nether Bricks Shipwreck in any non-warped or non-crimson Nether biome.",
						 " 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.shipwrecks.netherbricksshipwreckmaxchunkdistance")
				.defineInRange("netherBricksShipwreckMaxChunkDistance", 21, 1, 1001);

			crimsonShipwreckMaxChunkDistance = builder
				.comment("\n How rare are Crimson Shipwreck in Crimson Nether biomes.",
						 " 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.shipwrecks.crimsonshipwreckmaxchunkdistance")
				.defineInRange("crimsonShipwreckMaxChunkDistance", 18, 1, 1001);

			warpedShipwreckMaxChunkDistance = builder
				.comment("\n How rare are Warped Shipwreck in Warped Nether biomes.",
						 " 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.shipwrecks.warpedshipwreckmaxchunkdistance")
				.defineInRange("warpedShipwreckMaxChunkDistance", 18, 1, 1001);

		builder.pop();
	}
}
