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
				.translation("repurposedstructures.mansionbirchaveragechunkdistance")
				.define("pillarOnlyToLand", true);


		builder.comment("",
				" Average distance between spawn attempts for Repurposed Structures Mansions.",
				" 1 for spawning in most chunks and 1001 for none.");
		builder.push("Mansions");

		mansionBirchAverageChunkDistance = builder
				.translation("repurposedstructures.mansionbirchaveragechunkdistance")
				.defineInRange("mansionBirchAverageChunkDistance", 180, 1, 1001);

		mansionJungleAverageChunkDistance = builder
				.translation("repurposedstructures.mansionjungleaveragechunkdistance")
				.defineInRange("mansionJungleAverageChunkDistance", 225, 1, 1001);

		mansionOakAverageChunkDistance = builder
				.translation("repurposedstructures.mansionoakaveragechunkdistance")
				.defineInRange("mansionOakAverageChunkDistance", 205, 1, 1001);

		mansionSavannaAverageChunkDistance = builder
				.translation("repurposedstructures.mansionsavannaaveragechunkdistance")
				.defineInRange("mansionSavannaAverageChunkDistance", 225, 1, 1001);

		mansionTaigaAverageChunkDistance = builder
				.translation("repurposedstructures.mansiontaigaaveragechunkdistance")
				.defineInRange("mansionTaigaAverageChunkDistance", 205, 1, 1001);

		mansionDesertAverageChunkDistance = builder
				.translation("repurposedstructures.mansiondesertaveragechunkdistance")
				.defineInRange("mansionDesertAverageChunkDistance", 225, 1, 1001);

		mansionSnowyAverageChunkDistance = builder
				.translation("repurposedstructures.mansionsnowyaveragechunkdistance")
				.defineInRange("mansionSnowyAverageChunkDistance", 225, 1, 1001);
		builder.pop();
	}
}
