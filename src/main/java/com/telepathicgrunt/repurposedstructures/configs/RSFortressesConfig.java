package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSFortressesConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue jungleFortressMaxChunkDistance;
	public static ForgeConfigSpec.IntValue jungleFortressSize;
	public static ForgeConfigSpec.IntValue jungleFortressMinHeight;
	public static ForgeConfigSpec.IntValue jungleFortressMaxHeight;
	public static ForgeConfigSpec.IntValue jungleFortressVerticalRange;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		jungleFortressMaxChunkDistance = builder
				.comment("\n How rare are Jungle Fortresses.",
						" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.junglefortress.junglefortressmaxchunkdistance")
				.defineInRange("jungleFortressMaxChunkDistance", 50, 1, 1001);

		jungleFortressSize = builder
				.comment("\n Size of the fortress. This is how many pieces long a branch can be from the start piece.")
				.translation("repurposedstructures.config.junglefortress.jungleFortressSize")
				.defineInRange("jungleFortressSize", 10, 1, 30);

		jungleFortressMinHeight = builder
				.comment("\n Min Y height that the starting point can spawn at.")
				.translation("repurposedstructures.config.junglefortress.junglefortressminheight")
				.defineInRange("jungleFortressMinHeight", 56, 0, 255);

		jungleFortressMaxHeight = builder
				.comment("\n Max Y height that the starting point can spawn at.",
						"\nIf below min height, this will be read as min.")
				.translation("repurposedstructures.config.junglefortress.junglefortressmaxheight")
				.defineInRange("jungleFortressMaxHeight", 63, 0, 255);

		jungleFortressVerticalRange = builder
				.comment("\n How far above or below the fortress's pieces can generate away from the center piece.")
				.translation("repurposedstructures.config.junglefortress.junglefortressverticalrange")
				.defineInRange("jungleFortressVerticalRange", 33, 0, 255);
	}
}
