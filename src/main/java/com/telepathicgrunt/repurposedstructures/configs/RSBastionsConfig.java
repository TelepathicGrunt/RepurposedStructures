package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSBastionsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue bastionUndergroundAverageChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		bastionUndergroundAverageChunkDistance = builder
				.comment("\n Average distance between spawn attempts for Underground Bastions in",
						" biomes not tagged as ocean, beach, end, nether, or none category.",
						" 1 for spawning in most chunks and 10001 for none.")
				.translation("repurposedstructures.bastionundergroundaveragechunkdistance")
				.defineInRange("bastionUndergroundAverageChunkDistance", 180, 1, 10001);
	}
}
