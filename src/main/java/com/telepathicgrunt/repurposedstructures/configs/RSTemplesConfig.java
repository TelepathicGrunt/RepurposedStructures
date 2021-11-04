package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSTemplesConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue netherWastelandTempleMaxChunkDistance;
	public static ForgeConfigSpec.IntValue netherBasaltTempleMaxChunkDistance;
	public static ForgeConfigSpec.IntValue netherCrimsonTempleMaxChunkDistance;
	public static ForgeConfigSpec.IntValue netherWarpedTempleMaxChunkDistance;
	public static ForgeConfigSpec.IntValue netherSoulTempleMaxChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		netherWastelandTempleMaxChunkDistance = builder
			.comment("\n How rare are Nether Temples in Nether Wastelands.",
					 " 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.temples.netherwastelandtemplemaxchunkdistance")
			.defineInRange("netherWastelandTempleMaxChunkDistance", 27, 1, 1001);

		netherBasaltTempleMaxChunkDistance = builder
			.comment("\n How rare are Nether Basalt Temples in Nether Basalt Delta biomes.",
					 " 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.temples.netherbasalttemplemaxchunkdistance")
			.defineInRange("netherBasaltTempleMaxChunkDistance", 27, 1, 1001);

		netherCrimsonTempleMaxChunkDistance = builder
			.comment("\n How rare are Nether Crimson Temples in Nether Crimson Forest.",
					 " 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.temples.nethercrimsontemplemaxchunkdistance")
			.defineInRange("netherCrimsonTempleMaxChunkDistance", 27, 1, 1001);

		netherWarpedTempleMaxChunkDistance = builder
			.comment("\n How rare are Nether Crimson Temples in Nether Warped Forest.",
					 " 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.temples.netherwarpedtemplemaxchunkdistance")
			.defineInRange("netherWarpedTempleMaxChunkDistance", 27, 1, 1001);

		netherSoulTempleMaxChunkDistance = builder
			.comment("\n How rare are Nether Soul Temples in Nether Soul Sand Valley.",
					 " 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.temples.nethersoultemplemaxchunkdistance")
			.defineInRange("netherSoulTempleMaxChunkDistance", 27, 1, 1001);
	}
}
