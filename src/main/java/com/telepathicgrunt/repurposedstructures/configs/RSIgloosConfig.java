package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSIgloosConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue grassyIglooAverageChunkDistance;
	public static ForgeConfigSpec.IntValue stoneIglooAverageChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		grassyIglooAverageChunkDistance = builder
				.comment("\n Average distance between spawn attempts for Grassy Igloos in Plains and Forests.",
						" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.grassyiglooaveragechunkdistance")
				.defineInRange("grassyIglooAverageChunkDistance", 24, 1, 1001);

		stoneIglooAverageChunkDistance = builder
				.comment("\n Average distance between spawn attempts for Stone Igloos in Giant Tree Taiga biomes.",
						" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.stoneiglooaveragechunkdistance")
				.defineInRange("stoneIglooAverageChunkDistance", 24, 1, 1001);
	}
}
