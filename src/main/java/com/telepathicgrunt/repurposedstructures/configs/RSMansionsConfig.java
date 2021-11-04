package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSMansionsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.BooleanValue pillarOnlyToLand;
	public static ForgeConfigSpec.IntValue mansionJungleMaxChunkDistance;
	public static ForgeConfigSpec.IntValue mansionBirchMaxChunkDistance;
	public static ForgeConfigSpec.IntValue mansionOakMaxChunkDistance;
	public static ForgeConfigSpec.IntValue mansionSavannaMaxChunkDistance;
	public static ForgeConfigSpec.IntValue mansionTaigaMaxChunkDistance;
	public static ForgeConfigSpec.IntValue mansionDesertMaxChunkDistance;
	public static ForgeConfigSpec.IntValue mansionSnowyMaxChunkDistance;

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

		mansionBirchMaxChunkDistance = builder
				.comment("\n Add Birch Mansions to modded Birch biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.mansions.mansionbirchmaxchunkdistance")
				.defineInRange("mansionBirchMaxChunkDistance", 180, 1, 1001);

		mansionJungleMaxChunkDistance = builder
				.comment("\n Add Jungle Mansions to modded Jungle biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.mansions.mansionjunglemaxchunkdistance")
				.defineInRange("mansionJungleMaxChunkDistance", 225, 1, 1001);

		mansionOakMaxChunkDistance = builder
				.comment("\n Add Oak Mansions to modded forest category biomes that are not birch or dark forest.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.mansions.mansionoakmaxchunkdistance")
				.defineInRange("mansionOakMaxChunkDistance", 205, 1, 1001);

		mansionSavannaMaxChunkDistance = builder
				.comment("\n Add Savanna Mansions to modded Savanna biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.mansions.mansionsavannamaxchunkdistance")
				.defineInRange("mansionSavannaMaxChunkDistance", 225, 1, 1001);

		mansionTaigaMaxChunkDistance = builder
				.comment("\n Add Taiga Mansions to modded non-snowy Taiga biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.mansions.mansiontaigamaxchunkdistance")
				.defineInRange("mansionTaigaMaxChunkDistance", 205, 1, 1001);

		mansionDesertMaxChunkDistance = builder
				.comment("\n Add Desert Mansions to modded Desert biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.mansions.mansiondesertmaxchunkdistance")
				.defineInRange("mansionDesertMaxChunkDistance", 225, 1, 1001);

		mansionSnowyMaxChunkDistance = builder
				.comment("\n Add Snowy Mansions to modded Snowy biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.mansions.mansionsnowymaxchunkdistance")
				.defineInRange("mansionSnowyMaxChunkDistance", 225, 1, 1001);
	}
}
