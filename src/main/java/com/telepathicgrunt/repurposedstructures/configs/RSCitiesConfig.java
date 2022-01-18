package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSCitiesConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;

	public static ForgeConfigSpec.IntValue citiesNetherAverageChunkDistance;
	public static ForgeConfigSpec.IntValue citiesOverworldAverageChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		citiesNetherAverageChunkDistance = builder
				.comment("\n Average distance between spawn attempts for Nether Cities.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.citiesnetheraveragechunkdistance")
				.defineInRange("citiesNetherAverageChunkDistance", 110, 1, 1001);

		citiesOverworldAverageChunkDistance = builder
				.comment("\n Average distance between spawn attempts for Overworld Cities.",
						" 1 for spawning in most chunks and 10001 for none.")
				.translation("repurposedstructures.citiesoverworldaveragechunkdistance")
				.defineInRange("citiesOverworldAverageChunkDistance", 1200, 1, 10001);
	}
}
