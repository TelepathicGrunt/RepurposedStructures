package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSPyramidsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue pyramidNetherAverageChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidBadlandsAverageChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidDarkForestAverageChunkDistance;
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
		builder.push("Pyramids");
		pyramidBadlandsAverageChunkDistance = builder
			.translation("repurposedstructures.badlandspyramidaveragechunkdistance")
			.defineInRange("badlandsPyramidAverageChunkDistance", 40, 1, 1001);

		pyramidDarkForestAverageChunkDistance = builder
			.translation("repurposedstructures.pyramiddarkforestaveragechunkdistance")
			.defineInRange("pyramidDarkForestAverageChunkDistance", 44, 1, 1001);

		pyramidSnowyAverageChunkDistance = builder
			.translation("repurposedstructures.pyramidsnowyaveragechunkdistance")
			.defineInRange("pyramidSnowyAverageChunkDistance", 40, 1, 1001);

		pyramidIcyAverageChunkDistance = builder
			.translation("repurposedstructures.pyramidicyaveragechunkdistance")
			.defineInRange("pyramidIcyAverageChunkDistance", 34, 1, 1001);

		pyramidJungleAverageChunkDistance = builder
			.defineInRange("pyramidJungleAverageChunkDistance", 44, 1, 1001);

		pyramidMushroomAverageChunkDistance = builder
			.translation("repurposedstructures.pyramidmushroomaveragechunkdistance")
			.defineInRange("pyramidMushroomAverageChunkDistance", 37, 1, 1001);

		pyramidOceanAverageChunkDistance = builder
			.translation("repurposedstructures.pyramidoceanaveragechunkdistance")
			.defineInRange("pyramidOceanAverageChunkDistance", 40, 1, 1001);

		pyramidGiantTreeTaigaAverageChunkDistance = builder
			.translation("repurposedstructures.pyramidgianttreetaigaaveragechunkdistance")
			.defineInRange("pyramidGiantTreeTaigaAverageChunkDistance", 40, 1, 1001);

		pyramidFlowerForestAverageChunkDistance = builder
			.translation("repurposedstructures.pyramidflowerforestaveragechunkdistance")
			.defineInRange("pyramidFlowerForestAverageChunkDistance", 34, 1, 1001);

		pyramidNetherAverageChunkDistance = builder
				.translation("repurposedstructures.netherpyramidaveragechunkdistance")
				.defineInRange("netherPyramidAverageChunkDistance", 37, 1, 1001);

		pyramidEndAverageChunkDistance = builder
				.translation("repurposedstructures.pyramidendaveragechunkdistance")
				.defineInRange("pyramidEndAverageChunkDistance", 68, 1, 1001);
		builder.pop();
	}
}
