package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSStrongholdsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue strongholdNetherAverageChunkDistance;
	public static ForgeConfigSpec.IntValue strongholdNetherMinHeight;
	public static ForgeConfigSpec.IntValue strongholdNetherMaxHeight;
	public static ForgeConfigSpec.IntValue strongholdNetherSize;

	public static ForgeConfigSpec.IntValue strongholdEndAverageChunkDistance;
	public static ForgeConfigSpec.IntValue strongholdEndMinHeight;
	public static ForgeConfigSpec.IntValue strongholdEndMaxHeight;
	public static ForgeConfigSpec.IntValue strongholdEndSize;
	public static ForgeConfigSpec.IntValue strongholdEndVerticalRange;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		builder.push("Nether");

		strongholdNetherAverageChunkDistance = builder
				.comment("\n Average distance between spawn attempts for Nether-styled Strongholds in Nether-category biomes.",
						" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.stronghold.netherstrongholdmaxchunkdistance")
				.defineInRange("netherStrongholdMaxChunkDistance", 85, 1, 1001);

		strongholdNetherSize = builder
				.comment("\n Size of Nether Stronghold. This number is how many pieces deep a branch can go from the center piece.",
						" 1 for supertiny and 30 for supermassive Strongholds.")
				.translation("repurposedstructures.config.stronghold.netherstrongholdsize")
				.defineInRange("netherStrongholdSize", 15, 1, 30);

		strongholdNetherMinHeight = builder
				.comment("\n Minimum Y height that Nether stronghold's starting point can spawn at.",
						" Note: Strongholds will spawn between min and max y height set in config.")
				.translation("repurposedstructures.config.stronghold.netherstrongholdminheight")
				.defineInRange("netherStrongholdMinHeight", 5, 0, 255);

		strongholdNetherMaxHeight = builder
				.comment("\n Maximum Y height that Nether stronghold's starting point can spawn at.",
						" Note: Strongholds will spawn between min and max y height set in config.",
						" Setting this to below min height config will make strongholds spawn only at min height.")
				.translation("repurposedstructures.config.stronghold.netherstrongholdmaxheight")
				.defineInRange("netherStrongholdMaxHeight", 31, 0, 255);

		builder.pop();

		builder.push("End");

		strongholdEndAverageChunkDistance = builder
				.comment("\n Average distance between spawn attempts for End-styped Strongholds in End biome's islands.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.stronghold_end.strongholdendaveragechunkdistance")
				.defineInRange("strongholdEndAverageChunkDistance", 130 , 1, 1001);

		strongholdEndSize = builder
				.comment("\n Size of End Stronghold. This number is how many pieces deep a branch can go from the center piece.",
						" 1 for supertiny and 30 for supermassive Strongholds.")
				.translation("repurposedstructures.config.stronghold.endstrongholdsize")
				.defineInRange("endStrongholdSize", 15, 1, 30);

		strongholdEndMinHeight = builder
				.comment("\n Minimum Y height that End stronghold's starting point can spawn at.",
						" Note: Strongholds will spawn between min and max y height set in config.")
				.translation("repurposedstructures.config.stronghold.endstrongholdminheight")
				.defineInRange("endStrongholdMinHeight", 5, 0, 255);

		strongholdEndMaxHeight = builder
				.comment("\n Maximum Y height that End stronghold's starting point can spawn at.",
						" Note: Strongholds will spawn between min and max y height set in config.",
						" Setting this to below min height config will make strongholds spawn only at min height.")
				.translation("repurposedstructures.config.stronghold.endstrongholdmaxheight")
				.defineInRange("endStrongholdMaxHeight", 6, 0, 255);

		strongholdEndVerticalRange = builder
				.comment("\n How far above or below the End Stronghold's pieces can generate away from the center piece.")
				.translation("repurposedstructures.config.stronghold.endstrongholdverticalrange")
				.defineInRange("strongholdEndVerticalRange", 45, 0, 255);

		builder.pop();
	}
}
