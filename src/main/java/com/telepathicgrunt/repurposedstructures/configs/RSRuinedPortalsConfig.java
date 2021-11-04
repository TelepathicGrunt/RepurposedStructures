package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSRuinedPortalsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue ruinedPortalEndMaxChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		ruinedPortalEndMaxChunkDistance = builder
				.comment("\n How rare are End themed Ruined Portals in End category biomes. 1 for spawning in most",
						" chunks and 1001 for none.")
				.translation("repurposedstructures.config.ruinedPortals.ruinedportalendmaxchunkdistance")
				.defineInRange("ruinedPortalEndMaxChunkDistance", 57, 1, 1001);
	}
}
