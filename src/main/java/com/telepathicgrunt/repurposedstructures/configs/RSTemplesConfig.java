package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSTemplesConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue netherWastelandTempleAverageChunkDistance;
	public static ForgeConfigSpec.IntValue netherBasaltTempleAverageChunkDistance;
	public static ForgeConfigSpec.IntValue netherCrimsonTempleAverageChunkDistance;
	public static ForgeConfigSpec.IntValue netherWarpedTempleAverageChunkDistance;
	public static ForgeConfigSpec.IntValue netherSoulTempleAverageChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("",
				" Average distance between spawn attempts for Repurposed Structures Temples.",
				" 1 for spawning in most chunks and 1001 for none.");

		// Needs config category but that would break config files made before the update to make this be a category.
		// RIP. (Category is wanted in case config entries shuffle around due to https://github.com/MinecraftForge/MinecraftForge/issues/7489

		netherWastelandTempleAverageChunkDistance = builder
			.translation("repurposedstructures.config.temples.netherwastelandtemplemaxchunkdistance")
			.defineInRange("netherWastelandTempleMaxChunkDistance", 27, 1, 1001);

		netherBasaltTempleAverageChunkDistance = builder
			.translation("repurposedstructures.config.temples.netherbasalttemplemaxchunkdistance")
			.defineInRange("netherBasaltTempleMaxChunkDistance", 27, 1, 1001);

		netherCrimsonTempleAverageChunkDistance = builder
			.translation("repurposedstructures.config.temples.nethercrimsontemplemaxchunkdistance")
			.defineInRange("netherCrimsonTempleMaxChunkDistance", 27, 1, 1001);

		netherWarpedTempleAverageChunkDistance = builder
			.translation("repurposedstructures.config.temples.netherwarpedtemplemaxchunkdistance")
			.defineInRange("netherWarpedTempleMaxChunkDistance", 27, 1, 1001);

		netherSoulTempleAverageChunkDistance = builder
			.translation("repurposedstructures.config.temples.nethersoultemplemaxchunkdistance")
			.defineInRange("netherSoulTempleMaxChunkDistance", 27, 1, 1001);
	}
}
