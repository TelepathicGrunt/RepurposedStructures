package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSWellsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;

	public static ForgeConfigSpec.IntValue wellBadlandsRarityPerChunk;
	public static ForgeConfigSpec.IntValue wellNetherRarityPerChunk;
	public static ForgeConfigSpec.IntValue wellSnowRarityPerChunk;
	public static ForgeConfigSpec.IntValue wellMossyStoneRarityPerChunk;
	public static ForgeConfigSpec.IntValue wellForestRarityPerChunk;
	public static ForgeConfigSpec.IntValue wellMushroomRarityPerChunk;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("",
				" Rarity of Repurposed Structures Wells.",
				" This affects the chances of a well spawning in a chunk.",
				" The chance of a well generating at a chunk is 1/rarityPerChunk.",
				" 1 for wells spawning in every chunk and 10000 for no wells.");

		// Needs config category but that would break config files made before the update to make this be a category.
		// RIP. (Category is wanted in case config entries shuffle around due to https://github.com/MinecraftForge/MinecraftForge/issues/7489

		wellBadlandsRarityPerChunk = builder
			.translation("repurposedstructures.config.smallwells.badlandswellrarityperchunk")
			.defineInRange("badlandsWellRarityPerChunk", 250, 1, 10000);

		wellNetherRarityPerChunk = builder
			.translation("repurposedstructures.config.smallwells.netherwellrarityperchunk")
			.defineInRange("netherWellRarityPerChunk", 200, 1, 10000);

		wellSnowRarityPerChunk = builder
			.translation("repurposedstructures.config.smallwells.snowwellrarityperchunk")
			.defineInRange("snowWellRarityPerChunk", 350, 1, 10000);

		wellMossyStoneRarityPerChunk = builder
			.translation("repurposedstructures.config.smallwells.mossystonewellrarityperchunk")
			.defineInRange("mossyStoneWellRarityPerChunk", 350, 1, 10000);

		wellForestRarityPerChunk = builder
			.translation("repurposedstructures.config.smallwells.forestwellrarityperchunk")
			.defineInRange("forestWellRarityPerChunk", 350, 1, 10000);

		wellMushroomRarityPerChunk = builder
			.translation("repurposedstructures.config.smallwells.mushroomwellrarityperchunk")
			.defineInRange("mushroomWellRarityPerChunk", 350, 1, 10000);
	}
}
