package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSFortressesConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue jungleFortressAverageChunkDistance;
	public static ForgeConfigSpec.IntValue jungleFortressSize;
	public static ForgeConfigSpec.ConfigValue<Integer> jungleFortressMinHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> jungleFortressMaxHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> jungleFortressVerticalRange;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		jungleFortressAverageChunkDistance = builder
				.comment("\n Average distance between spawn attempts for Jungle Fortresses.",
						" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.junglefortressaveragechunkdistance")
				.defineInRange("jungleFortressAverageChunkDistance", 50, 1, 1001);

		jungleFortressSize = builder
				.comment("\n Size of the fortress. This is how many pieces long a branch can be from the start piece.")
				.translation("repurposedstructures.jungleFortressSize")
				.defineInRange("jungleFortressSize", 10, 1, 30);

		jungleFortressMinHeight = builder
				.comment("\n Min Y height that the starting point can spawn at.")
				.translation("repurposedstructures.junglefortressminheight")
				.define("jungleFortressMinHeight", 56);

		jungleFortressMaxHeight = builder
				.comment("\n Max Y height that the starting point can spawn at.",
						"\nIf below min height, this will be read as min.")
				.translation("repurposedstructures.junglefortressmaxheight")
				.define("jungleFortressMaxHeight", 65);

		jungleFortressVerticalRange = builder
				.comment("\n How far above or below the fortress's pieces can generate away from the center piece.")
				.translation("repurposedstructures.junglefortressverticalrange")
				.define("jungleFortressVerticalRange", 33);
	}
}
