package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSWitchHutsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue witchHutsOakAverageChunkDistance;
	public static ForgeConfigSpec.IntValue witchHutsTaigaAverageChunkDistance;
	public static ForgeConfigSpec.IntValue witchHutsGiantTreeTaigaAverageChunkDistance;
	public static ForgeConfigSpec.IntValue witchHutsBirchAverageChunkDistance;
	public static ForgeConfigSpec.IntValue witchHutsDarkForestAverageChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("",
				" Average distance between spawn attempts for Repurposed Structures Witch Huts.",
				" 1 for spawning in most chunks and 1001 for no spawn.");

		// Needs config category but that would break config files made before the update to make this be a category.
		// RIP. (Category is wanted in case config entries shuffle around due to https://github.com/MinecraftForge/MinecraftForge/issues/7489

		witchHutsOakAverageChunkDistance = builder
				.translation("repurposedstructures.config.witch_huts.witchhutsoakmaxchunkdistance")
				.defineInRange("witchHutsOakMaxChunkDistance", 48, 1, 1001);

		witchHutsTaigaAverageChunkDistance = builder
				.translation("repurposedstructures.config.witch_huts.witchhutstaigamaxchunkdistance")
				.defineInRange("witchHutsTaigaMaxChunkDistance", 48, 1, 1001);

		witchHutsGiantTreeTaigaAverageChunkDistance = builder
				.translation("repurposedstructures.config.witch_huts.witchhutsgianttreetaigamaxchunkdistance")
				.defineInRange("witchHutsGiantTreeTaigaMaxChunkDistance", 48, 1, 1001);

		witchHutsBirchAverageChunkDistance = builder
				.translation("repurposedstructures.config.witch_huts.witchhutsbirchmaxchunkdistance")
				.defineInRange("witchHutsBirchMaxChunkDistance", 48, 1, 1001);

		witchHutsDarkForestAverageChunkDistance = builder
				.translation("repurposedstructures.config.witch_huts.witchhutsdarkforestmaxchunkdistance")
				.defineInRange("witchHutsDarkForestMaxChunkDistance", 48, 1, 1001);
	}
}
