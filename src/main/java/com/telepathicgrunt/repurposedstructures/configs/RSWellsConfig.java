package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSWellsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;

	public static ForgeConfigSpec.IntValue badlandsWellRarityPerChunk;
	public static ForgeConfigSpec.IntValue netherWellRarityPerChunk;
	public static ForgeConfigSpec.IntValue snowWellRarityPerChunk;
	public static ForgeConfigSpec.IntValue mossyStoneWellRarityPerChunk;
	public static ForgeConfigSpec.IntValue forestWellRarityPerChunk;
	public static ForgeConfigSpec.IntValue mushroomWellRarityPerChunk;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		badlandsWellRarityPerChunk  = builder
				.comment("\n Adds Badlands themed wells to Badlands biomes.",
					 " This effects how often wells will attempt to spawn per chunk.",
					 " The chance of a well generating at a chunk is 1/rarityPerChunk.",
					 " 1 for wells spawning in every chunk and 10000 for no wells.")
			.translation("repurposedstructures.config.smallwells.badlandswellrarityperchunk")
			.defineInRange("badlandsWellRarityPerChunk", 250, 1, 10000);

		netherWellRarityPerChunk = builder
				.comment("\n Adds Nether themed wells to Nether biomes.",
					 " This effects how often wells will attempt to spawn per chunk.",
					 " The chance of a well generating at a chunk is 1/rarityPerChunk.",
					 " 1 for wells spawning in every chunk and 10000 for no wells.")
			.translation("repurposedstructures.config.smallwells.netherwellrarityperchunk")
			.defineInRange("netherWellRarityPerChunk", 200, 1, 10000);

		snowWellRarityPerChunk = builder
				.comment("\n Adds Snow themed wells to snowy and icy biomes.",
					 " This effects how often wells will attempt to spawn per chunk.",
					 " The chance of a well generating at a chunk is 1/rarityPerChunk.",
					 " 1 for wells spawning in every chunk and 10000 for no wells.")
			.translation("repurposedstructures.config.smallwells.snowwellrarityperchunk")
			.defineInRange("snowWellRarityPerChunk", 350, 1, 10000);

		mossyStoneWellRarityPerChunk = builder
				.comment("\n Adds mossy stone themed wells to Jungles, Dark Oak, and Swamp biomes.",
					 " This effects how often wells will attempt to spawn per chunk.",
					 " The chance of a well generating at a chunk is 1/rarityPerChunk.",
					 " 1 for wells spawning in every chunk and 10000 for no wells.")
			.translation("repurposedstructures.config.smallwells.mossystonewellrarityperchunk")
			.defineInRange("mossyStoneWellRarityPerChunk", 350, 1, 10000);

		forestWellRarityPerChunk = builder
				.comment("\n Adds a wood themed wells to Forest and Birch Forest biomes.",
					 " This effects how often wells will attempt to spawn per chunk.",
					 " The chance of a well generating at a chunk is 1/rarityPerChunk.",
					 " 1 for wells spawning in every chunk and 10000 for no wells.")
			.translation("repurposedstructures.config.smallwells.forestwellrarityperchunk")
			.defineInRange("forestWellRarityPerChunk", 350, 1, 10000);

		mushroomWellRarityPerChunk = builder
			.comment("\n Adds a mushroom themed wells to Mushroom biomes.",
					" This effects how often wells will attempt to spawn per chunk.",
					" The chance of a well generating at a chunk is 1/rarityPerChunk.",
					" 1 for wells spawning in every chunk and 10000 for no wells.")
			.translation("repurposedstructures.config.smallwells.mushroomwellrarityperchunk")
			.defineInRange("mushroomWellRarityPerChunk", 350, 1, 10000);
	}
}
