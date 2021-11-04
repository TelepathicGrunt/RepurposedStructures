package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSIgloosConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue grassyIglooMaxChunkDistance;
	public static ForgeConfigSpec.IntValue stoneIglooMaxChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		grassyIglooMaxChunkDistance = builder
				.comment("\n How rare are Grassy Igloos in Plains and Forests.",
						" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.igloo.grassyigloomaxchunkdistance")
				.defineInRange("grassyIglooMaxChunkDistance", 20, 1, 1001);

		stoneIglooMaxChunkDistance = builder
				.comment("\n How rare are Stone Igloos in Giant Tree Taiga biomes.",
						" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.igloo.stoneigloomaxchunkdistance")
				.defineInRange("stoneIglooMaxChunkDistance", 20, 1, 1001);
	}
}
