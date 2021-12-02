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
		builder.push("Wells");
		witchHutsOakAverageChunkDistance = builder
				.translation("repurposedstructures.witchhutsoakaveragechunkdistance")
				.defineInRange("witchHutsOakAverageChunkDistance", 48, 1, 1001);

		witchHutsTaigaAverageChunkDistance = builder
				.translation("repurposedstructures.witchhutstaigaaveragechunkdistance")
				.defineInRange("witchHutsTaigaAverageChunkDistance", 48, 1, 1001);

		witchHutsGiantTreeTaigaAverageChunkDistance = builder
				.translation("repurposedstructures.witchhutsgianttreetaigaaveragechunkdistance")
				.defineInRange("witchHutsGiantTreeTaigaAverageChunkDistance", 48, 1, 1001);

		witchHutsBirchAverageChunkDistance = builder
				.translation("repurposedstructures.witchhutsbirchaveragechunkdistance")
				.defineInRange("witchHutsBirchAverageChunkDistance", 48, 1, 1001);

		witchHutsDarkForestAverageChunkDistance = builder
				.translation("repurposedstructures.witchhutsdarkforestaveragechunkdistance")
				.defineInRange("witchHutsDarkForestAverageChunkDistance", 48, 1, 1001);
		builder.pop();
	}
}
