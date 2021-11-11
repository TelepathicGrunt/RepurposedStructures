package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSMansionsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.BooleanValue pillarOnlyToLand;
	public static ForgeConfigSpec.IntValue mansionJungleAverageChunkDistance;
	public static ForgeConfigSpec.IntValue mansionBirchAverageChunkDistance;
	public static ForgeConfigSpec.IntValue mansionOakAverageChunkDistance;
	public static ForgeConfigSpec.IntValue mansionSavannaAverageChunkDistance;
	public static ForgeConfigSpec.IntValue mansionTaigaAverageChunkDistance;
	public static ForgeConfigSpec.IntValue mansionDesertAverageChunkDistance;
	public static ForgeConfigSpec.IntValue mansionSnowyAverageChunkDistance;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {

		pillarOnlyToLand = builder
				.comment("\n Only make supports downward if there is land below.",
						" (Helps make structure look better in floating island worlds instead of support going down to void at world bottom)")
				.translation("repurposedstructures.config.mansions.mansionbirchmaxchunkdistance")
				.define("pillarOnlyToLand", true);


		builder.comment("",
				" Average distance between spawn attempts for Repurposed Structures Mansions.",
				" 1 for spawning in most chunks and 1001 for none.");

		// Needs config category but that would break config files made before the update to make this be a category.
		// RIP. (Category is wanted in case config entries shuffle around due to https://github.com/MinecraftForge/MinecraftForge/issues/7489

		mansionBirchAverageChunkDistance = builder
				.translation("repurposedstructures.config.mansions.mansionbirchmaxchunkdistance")
				.defineInRange("mansionBirchMaxChunkDistance", 180, 1, 1001);

		mansionJungleAverageChunkDistance = builder
				.translation("repurposedstructures.config.mansions.mansionjunglemaxchunkdistance")
				.defineInRange("mansionJungleMaxChunkDistance", 225, 1, 1001);

		mansionOakAverageChunkDistance = builder
				.translation("repurposedstructures.config.mansions.mansionoakmaxchunkdistance")
				.defineInRange("mansionOakMaxChunkDistance", 205, 1, 1001);

		mansionSavannaAverageChunkDistance = builder
				.translation("repurposedstructures.config.mansions.mansionsavannamaxchunkdistance")
				.defineInRange("mansionSavannaMaxChunkDistance", 225, 1, 1001);

		mansionTaigaAverageChunkDistance = builder
				.translation("repurposedstructures.config.mansions.mansiontaigamaxchunkdistance")
				.defineInRange("mansionTaigaMaxChunkDistance", 205, 1, 1001);

		mansionDesertAverageChunkDistance = builder
				.translation("repurposedstructures.config.mansions.mansiondesertmaxchunkdistance")
				.defineInRange("mansionDesertMaxChunkDistance", 225, 1, 1001);

		mansionSnowyAverageChunkDistance = builder
				.translation("repurposedstructures.config.mansions.mansionsnowymaxchunkdistance")
				.defineInRange("mansionSnowyMaxChunkDistance", 225, 1, 1001);
	}
}
