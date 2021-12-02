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
		builder.push("Wells");
			wellBadlandsRarityPerChunk = builder
				.translation("repurposedstructures.badlandswellrarityperchunk")
				.defineInRange("badlandsWellRarityPerChunk", 450, 1, 10000);

			wellNetherRarityPerChunk = builder
				.translation("repurposedstructures.netherwellrarityperchunk")
				.defineInRange("netherWellRarityPerChunk", 250, 1, 10000);

			wellSnowRarityPerChunk = builder
				.translation("repurposedstructures.snowwellrarityperchunk")
				.defineInRange("snowWellRarityPerChunk", 450, 1, 10000);

			wellMossyStoneRarityPerChunk = builder
				.translation("repurposedstructures.mossystonewellrarityperchunk")
				.defineInRange("mossyStoneWellRarityPerChunk", 450, 1, 10000);

			wellForestRarityPerChunk = builder
				.translation("repurposedstructures.forestwellrarityperchunk")
				.defineInRange("forestWellRarityPerChunk", 450, 1, 10000);

			wellMushroomRarityPerChunk = builder
				.translation("repurposedstructures.mushroomwellrarityperchunk")
				.defineInRange("mushroomWellRarityPerChunk", 350, 1, 10000);
		builder.pop();
	}
}
