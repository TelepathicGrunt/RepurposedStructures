package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSRuinsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue ruinsNetherMaxChunkDistance;
	public static ForgeConfigSpec.IntValue ruinsLandWarmMaxChunkDistance;
	public static ForgeConfigSpec.IntValue ruinsLandHotMaxChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		ruinsNetherMaxChunkDistance = builder
				.comment("\n How rare are Nether Ruins. 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.ruins.ruinsnethermaxchunkdistance")
				.defineInRange("ruinsNetherMaxChunkDistance", 35, 1, 1001);

		ruinsLandWarmMaxChunkDistance = builder
				.comment("How rare are Warm Land Ruins in Plains, Forests, Swamps, and non-snowy Taiga biomes.",
						"\n1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.ruins.ruinslandwarmmaxchunkdistance")
				.defineInRange("ruinsLandWarmMaxChunkDistance", 42, 1, 1001);

		ruinsLandHotMaxChunkDistance = builder
				.comment("How rare are Hot Land Ruins in Desert biomes.",
						"\n1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.ruins.ruinslandhotmaxchunkdistance")
				.defineInRange("ruinsLandHotMaxChunkDistance", 45, 1, 1001);
	}
}
