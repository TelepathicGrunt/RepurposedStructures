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
		builder.push("Temples");
		netherWastelandTempleAverageChunkDistance = builder
			.translation("repurposedstructures.netherwastelandtempleaveragechunkdistance")
			.defineInRange("netherWastelandTempleAverageChunkDistance", 27, 1, 1001);

		netherBasaltTempleAverageChunkDistance = builder
			.translation("repurposedstructures.netherbasalttempleaveragechunkdistance")
			.defineInRange("netherBasaltTempleAverageChunkDistance", 27, 1, 1001);

		netherCrimsonTempleAverageChunkDistance = builder
			.translation("repurposedstructures.nethercrimsontempleaveragechunkdistance")
			.defineInRange("netherCrimsonTempleAverageChunkDistance", 27, 1, 1001);

		netherWarpedTempleAverageChunkDistance = builder
			.translation("repurposedstructures.netherwarpedtempleaveragechunkdistance")
			.defineInRange("netherWarpedTempleAverageChunkDistance", 27, 1, 1001);

		netherSoulTempleAverageChunkDistance = builder
			.translation("repurposedstructures.nethersoultempleaveragechunkdistance")
			.defineInRange("netherSoulTempleAverageChunkDistance", 27, 1, 1001);
		builder.pop();
	}
}
