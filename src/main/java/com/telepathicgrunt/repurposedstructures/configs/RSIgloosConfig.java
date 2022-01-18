package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSIgloosConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue grassyIglooAverageChunkDistance;
	public static ForgeConfigSpec.IntValue stoneIglooAverageChunkDistance;
	public static ForgeConfigSpec.IntValue mushroomIglooAverageChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		builder.comment("",
				" Average distance between spawn attempts for Repurposed Structures Igloos.",
				" 1 for spawning in most chunks and 1001 for none.");

		builder.push("Igloos");
			grassyIglooAverageChunkDistance = builder
					.translation("repurposedstructures.grassyiglooaveragechunkdistance")
					.defineInRange("grassyIglooAverageChunkDistance", 28, 1, 1001);

			stoneIglooAverageChunkDistance = builder
					.translation("repurposedstructures.stoneiglooaveragechunkdistance")
					.defineInRange("stoneIglooAverageChunkDistance", 28, 1, 1001);

		mushroomIglooAverageChunkDistance = builder
					.translation("repurposedstructures.mushroomiglooaveragechunkdistance")
					.defineInRange("mushroomIglooAverageChunkDistance", 39, 1, 1001);
		builder.pop();
	}
}
