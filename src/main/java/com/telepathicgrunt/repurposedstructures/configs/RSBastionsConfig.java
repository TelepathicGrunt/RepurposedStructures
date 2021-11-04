package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSBastionsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue bastionUndergroundMaxChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		bastionUndergroundMaxChunkDistance = builder
				.comment("How rare are Underground Bastions in non-ocean and non-beach Overworld biomes.",
						"1 for spawning in most chunks and 10001 for none.")
				.translation("repurposedstructures.config.pyramids.bastionundergroundmaxchunkdistance")
				.defineInRange("bastionUndergroundMaxChunkDistance", 500, 1, 10001);
	}
}
