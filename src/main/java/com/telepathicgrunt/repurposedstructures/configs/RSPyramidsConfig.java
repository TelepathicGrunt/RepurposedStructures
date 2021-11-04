package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSPyramidsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue netherPyramidMaxChunkDistance;
	public static ForgeConfigSpec.IntValue badlandsPyramidMaxChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidSnowyMaxChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidEndMaxChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidIcyMaxChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidJungleMaxChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidMushroomMaxChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidOceanMaxChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidGiantTreeTaigaMaxChunkDistance;
	public static ForgeConfigSpec.IntValue pyramidFlowerForestMaxChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		netherPyramidMaxChunkDistance = builder
			.comment("\n How rare are Nether Pyramids in Nether.",
					 " 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.temples.netherpyramidmaxchunkdistance")
			.defineInRange("netherPyramidMaxChunkDistance", 37, 1, 1001);

		badlandsPyramidMaxChunkDistance = builder
			.comment("\n How rare are Badlands Pyramid in non-plateau Badlands biomes.",
					 " 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.temples.badlandspyramidmaxchunkdistance")
			.defineInRange("badlandsPyramidMaxChunkDistance", 40, 1, 1001);

		pyramidSnowyMaxChunkDistance = builder
			.comment("\n How rare are Snowy Pyramid in snowy biomes.",
					" 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.pyramids.pyramidsnowymaxchunkdistance")
			.defineInRange("pyramidSnowyMaxChunkDistance", 40, 1, 1001);

		pyramidEndMaxChunkDistance = builder
			.comment("\n How rare are End Pyramid in End biomes.",
					" 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.pyramids.pyramidendmaxchunkdistance")
			.defineInRange("pyramidEndMaxChunkDistance", 68, 1, 1001);

		pyramidIcyMaxChunkDistance = builder
			.comment("\n How rare are Icy Pyramid in super cold or icy biomes.",
					" 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.pyramids.pyramidicymaxchunkdistance")
			.defineInRange("pyramidIcyMaxChunkDistance", 37, 1, 1001);

		pyramidJungleMaxChunkDistance = builder
			.comment("\n How rare are Jungle Pyramid in Jungle biomes. 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.pyramids.pyramidjunglemaxchunkdistance")
			.defineInRange("pyramidJungleMaxChunkDistance", 44, 1, 1001);

		pyramidMushroomMaxChunkDistance = builder
			.comment("\n How rare are Mushroom Pyramid in Mushroom biomes.",
					" 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.pyramids.pyramidmushroommaxchunkdistance")
			.defineInRange("pyramidMushroomMaxChunkDistance", 24, 1, 1001);

		pyramidOceanMaxChunkDistance = builder
			.comment("\n How rare are Ocean Pyramid in Ocean biomes.",
					" 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.pyramids.pyramidoceanmaxchunkdistance")
			.defineInRange("pyramidOceanMaxChunkDistance", 40, 1, 1001);

		pyramidGiantTreeTaigaMaxChunkDistance = builder
			.comment("\n How rare are Giant Tree Taiga Pyramid in Giant Tree Taiga biomes.",
					" 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.pyramids.pyramidgianttreetaigamaxchunkdistance")
			.defineInRange("pyramidGiantTreeTaigaMaxChunkDistance", 40, 1, 1001);

		pyramidFlowerForestMaxChunkDistance = builder
			.comment("\n How rare are Flower Forest Pyramid in Flower Forest biomes.",
					" 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.pyramids.pyramidflowerforestmaxchunkdistance")
			.defineInRange("pyramidFlowerForestMaxChunkDistance", 36, 1, 1001);
	}
}
