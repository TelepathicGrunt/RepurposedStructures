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

		// Needs config category but that would break config files made before the update to make this be a category.
		// RIP. (Category is wanted in case config entries shuffle around due to https://github.com/MinecraftForge/MinecraftForge/issues/7489

		ruinsNetherAverageChunkDistance = builder
				.translation("repurposedstructures.config.ruins.ruinsnethermaxchunkdistance")
				.defineInRange("ruinsNetherMaxChunkDistance", 35, 1, 1001);

		ruinsLandWarmAverageChunkDistance = builder
				.translation("repurposedstructures.config.ruins.ruinslandwarmmaxchunkdistance")
				.defineInRange("ruinsLandWarmMaxChunkDistance", 42, 1, 1001);

		ruinsLandHotAverageChunkDistance = builder
				.translation("repurposedstructures.config.ruins.ruinslandhotmaxchunkdistance")
				.defineInRange("ruinsLandHotMaxChunkDistance", 45, 1, 1001);
	}
}
