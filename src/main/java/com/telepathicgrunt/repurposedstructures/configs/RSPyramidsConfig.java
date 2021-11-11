package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSPyramidsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue pyramidNetherAverageChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidBadlandsAverageChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidSnowyAverageChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidEndAverageChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidIcyAverageChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidJungleAverageChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidMushroomAverageChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidOceanAverageChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidGiantTreeTaigaAverageChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidFlowerForestAverageChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("",
				" Average distance between spawn attempts for Repurposed Structures Pyramids.",
				" 1 for spawning in most chunks and 1001 for none.");

		// Needs config category but that would break config files made before the update to make this be a category.
		// RIP. (Category is wanted in case config entries shuffle around due to https://github.com/MinecraftForge/MinecraftForge/issues/7489

		pyramidBadlandsAverageChunkDistance = builder
			.translation("repurposedstructures.config.temples.badlandspyramidmaxchunkdistance")
			.defineInRange("badlandsPyramidMaxChunkDistance", 40, 1, 1001);

		pyramidSnowyAverageChunkDistance = builder
			.translation("repurposedstructures.config.pyramids.pyramidsnowymaxchunkdistance")
			.defineInRange("pyramidSnowyMaxChunkDistance", 40, 1, 1001);

		pyramidIcyAverageChunkDistance = builder
			.translation("repurposedstructures.config.pyramids.pyramidicymaxchunkdistance")
			.defineInRange("pyramidIcyMaxChunkDistance", 37, 1, 1001);

		pyramidJungleAverageChunkDistance = builder
			.defineInRange("pyramidJungleMaxChunkDistance", 44, 1, 1001);

		pyramidMushroomAverageChunkDistance = builder
			.translation("repurposedstructures.config.pyramids.pyramidmushroommaxchunkdistance")
			.defineInRange("pyramidMushroomMaxChunkDistance", 24, 1, 1001);

		pyramidOceanAverageChunkDistance = builder
			.translation("repurposedstructures.config.pyramids.pyramidoceanmaxchunkdistance")
			.defineInRange("pyramidOceanMaxChunkDistance", 40, 1, 1001);

		pyramidGiantTreeTaigaAverageChunkDistance = builder
			.translation("repurposedstructures.config.pyramids.pyramidgianttreetaigamaxchunkdistance")
			.defineInRange("pyramidGiantTreeTaigaMaxChunkDistance", 40, 1, 1001);

		pyramidFlowerForestAverageChunkDistance = builder
			.translation("repurposedstructures.config.pyramids.pyramidflowerforestmaxchunkdistance")
			.defineInRange("pyramidFlowerForestMaxChunkDistance", 36, 1, 1001);

		pyramidNetherAverageChunkDistance = builder
				.translation("repurposedstructures.config.temples.netherpyramidmaxchunkdistance")
				.defineInRange("netherPyramidMaxChunkDistance", 37, 1, 1001);

		pyramidEndAverageChunkDistance = builder
				.translation("repurposedstructures.config.pyramids.pyramidendmaxchunkdistance")
				.defineInRange("pyramidEndMaxChunkDistance", 68, 1, 1001);
	}
}
