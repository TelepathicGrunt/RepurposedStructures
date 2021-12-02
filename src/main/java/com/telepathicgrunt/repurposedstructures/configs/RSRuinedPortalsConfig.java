package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSRuinedPortalsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue ruinedPortalEndAverageChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		ruinedPortalEndAverageChunkDistance = builder
				.comment("\n Average distance between spawn attempts for End themed Ruined Portals in End category biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.ruinedportalendaveragechunkdistance")
				.defineInRange("ruinedPortalEndAverageChunkDistance", 57, 1, 1001);
	}
}
