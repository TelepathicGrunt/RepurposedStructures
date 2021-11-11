package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSShipwrecksConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue shipwreckEndAverageChunkDistance;
	public static ForgeConfigSpec.IntValue shipwreckNetherBricksAverageChunkDistance;
	public static ForgeConfigSpec.IntValue shipwreckCrimsonAverageChunkDistance;
	public static ForgeConfigSpec.IntValue shipwreckWarpedAverageChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("-----------------------------------------------------------------------------------------",
				" Average distance between spawn attempts for Repurposed Structures Shipwrecks.",
				" 1 for spawning in most chunks and 1001 for none.");
		builder.push("Shipwrecks");

			shipwreckEndAverageChunkDistance = builder
				.translation("repurposedstructures.config.shipwrecks.endshipwreckmaxchunkdistance")
				.defineInRange("endShipwreckMaxChunkDistance", 24, 1, 1001);

			shipwreckNetherBricksAverageChunkDistance = builder
				.translation("repurposedstructures.config.shipwrecks.netherbricksshipwreckmaxchunkdistance")
				.defineInRange("netherBricksShipwreckMaxChunkDistance", 21, 1, 1001);

			shipwreckCrimsonAverageChunkDistance = builder
				.translation("repurposedstructures.config.shipwrecks.crimsonshipwreckmaxchunkdistance")
				.defineInRange("crimsonShipwreckMaxChunkDistance", 18, 1, 1001);

			shipwreckWarpedAverageChunkDistance = builder
				.translation("repurposedstructures.config.shipwrecks.warpedshipwreckmaxchunkdistance")
				.defineInRange("warpedShipwreckMaxChunkDistance", 18, 1, 1001);

		builder.pop();
	}
}
