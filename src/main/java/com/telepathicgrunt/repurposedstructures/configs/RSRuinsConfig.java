package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSRuinsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue ruinsNetherAverageChunkDistance;
	public static ForgeConfigSpec.IntValue ruinsLandWarmAverageChunkDistance;
	public static ForgeConfigSpec.IntValue ruinsLandHotAverageChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("",
				" Average distance between spawn attempts for Repurposed Structures Ruins.",
				" 1 for spawning in most chunks and 1001 for none.");
		builder.push("Ruins");
		ruinsNetherAverageChunkDistance = builder
				.translation("repurposedstructures.ruinsnetheraveragechunkdistance")
				.defineInRange("ruinsNetherAverageChunkDistance", 35, 1, 1001);

		ruinsLandWarmAverageChunkDistance = builder
				.translation("repurposedstructures.ruinslandwarmaveragechunkdistance")
				.defineInRange("ruinsLandWarmAverageChunkDistance", 45, 1, 1001);

		ruinsLandHotAverageChunkDistance = builder
				.translation("repurposedstructures.ruinslandhotaveragechunkdistance")
				.defineInRange("ruinsLandHotAverageChunkDistance", 45, 1, 1001);
		builder.pop();
	}
}
