package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSWitchHutsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue witchHutsOakMaxChunkDistance;
	public static ForgeConfigSpec.IntValue witchHutsTaigaMaxChunkDistance;
	public static ForgeConfigSpec.IntValue witchHutsGiantTreeTaigaMaxChunkDistance;
	public static ForgeConfigSpec.IntValue witchHutsBirchMaxChunkDistance;
	public static ForgeConfigSpec.IntValue witchHutsDarkForestMaxChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		witchHutsOakMaxChunkDistance = builder
				.comment("\n Add Oak Witch Huts to modded Forest biomes that are not birch or dark oak.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.witch_huts.witchhutsoakmaxchunkdistance")
				.defineInRange("witchHutsOakMaxChunkDistance", 48, 1, 1001);

		witchHutsTaigaMaxChunkDistance = builder
				.comment("\n Add Taiga Witch Huts to modded Taiga biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.witch_huts.witchhutstaigamaxchunkdistance")
				.defineInRange("witchHutsTaigaMaxChunkDistance", 48, 1, 1001);

		witchHutsGiantTreeTaigaMaxChunkDistance = builder
				.comment("\n Add Giant Tree Taiga Witch Huts to modded Giant Tree Taiga biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.witch_huts.witchhutsgianttreetaigamaxchunkdistance")
				.defineInRange("witchHutsGiantTreeTaigaMaxChunkDistance", 48, 1, 1001);

		witchHutsBirchMaxChunkDistance = builder
				.comment("\n Add Birch Witch Huts to modded Birch biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.witch_huts.witchhutsbirchmaxchunkdistance")
				.defineInRange("witchHutsBirchMaxChunkDistance", 48, 1, 1001);

		witchHutsDarkForestMaxChunkDistance = builder
				.comment("\n Add Dark Forest Witch Huts to modded Dark Forest biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.witch_huts.witchhutsdarkforestmaxchunkdistance")
				.defineInRange("witchHutsDarkForestMaxChunkDistance", 48, 1, 1001);
	}
}
