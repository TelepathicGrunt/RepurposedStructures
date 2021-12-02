package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSStrongholdsConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	
	public static ForgeConfigSpec.IntValue strongholdNetherAverageChunkDistance;
	public static ForgeConfigSpec.ConfigValue<Integer> strongholdNetherMinHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> strongholdNetherMaxHeight;
	public static ForgeConfigSpec.IntValue strongholdNetherSize;

	public static ForgeConfigSpec.IntValue strongholdEndAverageChunkDistance;
	public static ForgeConfigSpec.ConfigValue<Integer> strongholdEndMinHeight;
	public static ForgeConfigSpec.ConfigValue<Integer> strongholdEndMaxHeight;
	public static ForgeConfigSpec.IntValue strongholdEndSize;
	public static ForgeConfigSpec.ConfigValue<Integer> strongholdEndVerticalRange;

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
				.translation("repurposedstructures.netherstrongholdaveragechunkdistance")
				.defineInRange("netherStrongholdAverageChunkDistance", 100, 1, 1001);

		strongholdNetherSize = builder
				.comment("\n Size of Nether Stronghold. This number is how many pieces deep a branch can go from the center piece.",
						" 1 for supertiny and 30 for supermassive Strongholds.")
				.translation("repurposedstructures.netherstrongholdsize")
				.defineInRange("netherStrongholdSize", 15, 1, 30);

		strongholdNetherMinHeight = builder
				.comment("\n Minimum Y height that Nether stronghold's starting point can spawn at.",
						" Note: Strongholds will spawn between min and max y height set in config.")
				.translation("repurposedstructures.netherstrongholdminheight")
				.define("netherStrongholdMinHeight", 5);

		strongholdNetherMaxHeight = builder
				.comment("\n Maximum Y height that Nether stronghold's starting point can spawn at.",
						" Note: Strongholds will spawn between min and max y height set in config.",
						" Setting this to below min height config will make strongholds spawn only at min height.")
				.translation("repurposedstructures.netherstrongholdmaxheight")
				.define("netherStrongholdMaxHeight", 31);

		builder.pop();

		builder.push("End");

		strongholdEndAverageChunkDistance = builder
				.comment("\n Average distance between spawn attempts for End-styped Strongholds in End biome's islands.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.strongholdendaveragechunkdistance")
				.defineInRange("strongholdEndAverageChunkDistance", 130, 1, 1001);

		strongholdEndSize = builder
				.comment("\n Size of End Stronghold. This number is how many pieces deep a branch can go from the center piece.",
						" 1 for supertiny and 30 for supermassive Strongholds.")
				.translation("repurposedstructures.endstrongholdsize")
				.defineInRange("endStrongholdSize", 15, 1, 30);

		strongholdEndMinHeight = builder
				.comment("\n Minimum Y height that End stronghold's starting point can spawn at.",
						" Note: Strongholds will spawn between min and max y height set in config.")
				.translation("repurposedstructures.endstrongholdminheight")
				.define("endStrongholdMinHeight", 5);

		strongholdEndMaxHeight = builder
				.comment("\n Maximum Y height that End stronghold's starting point can spawn at.",
						" Note: Strongholds will spawn between min and max y height set in config.",
						" Setting this to below min height config will make strongholds spawn only at min height.")
				.translation("repurposedstructures.endstrongholdmaxheight")
				.define("endStrongholdMaxHeight", 6);

		strongholdEndVerticalRange = builder
				.comment("\n How far above or below the End Stronghold's pieces can generate away from the center piece.")
				.translation("repurposedstructures.endstrongholdverticalrange")
				.define("strongholdEndVerticalRange", 45);

		builder.pop();
	}
}
