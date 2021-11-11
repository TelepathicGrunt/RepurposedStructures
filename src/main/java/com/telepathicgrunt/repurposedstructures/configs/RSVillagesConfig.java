package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.biomeinjection.Villages;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSVillagesConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;

	public static ForgeConfigSpec.IntValue villageBadlandsAverageChunkDistance;
	public static ForgeConfigSpec.IntValue villageBirchAverageChunkDistance;
	public static ForgeConfigSpec.IntValue villageDarkForestAverageChunkDistance;
	public static ForgeConfigSpec.IntValue villageJungleAverageChunkDistance;
	public static ForgeConfigSpec.IntValue villageSwampAverageChunkDistance;
	public static ForgeConfigSpec.IntValue villageMountainsAverageChunkDistance;
	public static ForgeConfigSpec.IntValue villageGiantTaigaAverageChunkDistance;
	public static ForgeConfigSpec.IntValue villageOakAverageChunkDistance;
	public static ForgeConfigSpec.IntValue villageCrimsonAverageChunkDistance;
	public static ForgeConfigSpec.IntValue villageWarpedAverageChunkDistance;
	public static ForgeConfigSpec.IntValue villageMushroomAverageChunkDistance;
	// regexpos1

	public static ForgeConfigSpec.IntValue villageBadlandsSize;
	public static ForgeConfigSpec.IntValue villageBirchSize;
	public static ForgeConfigSpec.IntValue villageDarkForestSize;
	public static ForgeConfigSpec.IntValue villageJungleSize;
	public static ForgeConfigSpec.IntValue villageSwampSize;
	public static ForgeConfigSpec.IntValue villageMountainsSize;
	public static ForgeConfigSpec.IntValue villageMushroomSize;
	public static ForgeConfigSpec.IntValue villageGiantTaigaSize;
	public static ForgeConfigSpec.IntValue villageOakSize;
	public static ForgeConfigSpec.IntValue villageCrimsonSize;
	public static ForgeConfigSpec.IntValue villageWarpedSize;
	
	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("-----------------------------------------------------------------------------------------",
				" Average distance between spawn attempts for Repurposed Structures Villages ",
				" 1 for spawning in most chunks and 1001 for no spawn.");
		builder.push("Spawnrates");

		villageBadlandsAverageChunkDistance = builder
			.translation("repurposedstructures.config.villages.badlandsvillagemaxchunkdistance")
			.defineInRange("badlandsVillageMaxChunkDistance", 34, 1, 1001);

		villageBirchAverageChunkDistance = builder
			.translation("repurposedstructures.config.villages.birchvillagemaxchunkdistance")
			.defineInRange("birchVillageMaxChunkDistance", 47, 1, 1001);

		villageDarkForestAverageChunkDistance = builder
			.translation("repurposedstructures.config.villages.darkforestvillagemaxchunkdistance")
			.defineInRange("darkForestVillageMaxChunkDistance", 47, 1, 1001);

		villageJungleAverageChunkDistance = builder
			.translation("repurposedstructures.config.villages.junglevillagemaxchunkdistance")
			.defineInRange("jungleVillageMaxChunkDistance", 47, 1, 1001);

		villageSwampAverageChunkDistance = builder
			.translation("repurposedstructures.config.villages.swampvillagemaxchunkdistance")
			.defineInRange("swampVillageMaxChunkDistance", 47, 1, 1001);

		villageMountainsAverageChunkDistance = builder
			.translation("repurposedstructures.config.villages.mountainsvillagemaxchunkdistance")
			.defineInRange("mountainsVillageMaxChunkDistance", 47, 1, 1001);

		villageMushroomAverageChunkDistance = builder
			.translation("repurposedstructures.config.villages.villagemushroomaveragechunkdistance")
			.defineInRange("villageMushroomAverageChunkDistance", 24, 1, 1001);

		villageGiantTaigaAverageChunkDistance = builder
			.translation("repurposedstructures.config.villages.gianttaigavillagemaxchunkdistance")
			.defineInRange("giantTaigaVillageMaxChunkDistance", 47, 1, 1001);

		villageOakAverageChunkDistance = builder
			.translation("repurposedstructures.config.village.villageoakmaxchunkdistance")
			.defineInRange("oakVillageMaxChunkDistance", 47, 1, 1001);

		villageCrimsonAverageChunkDistance = builder
			.translation("repurposedstructures.config.villages.crimsonvillagemaxchunkdistance")
			.defineInRange("crimsonVillageMaxChunkDistance", 30, 1, 1001);

		villageWarpedAverageChunkDistance = builder
			.translation("repurposedstructures.config.villages.warpedvillagemaxchunkdistance")
			.defineInRange("warpedVillageMaxChunkDistance", 30, 1, 1001);

		builder.pop();

		// regexpos2

		builder.comment("-----------------------------------------------------------------------------------------",
				" Size of the village. This is how many pieces long a path can be from the start piece.");
		builder.push("Size");

		villageBadlandsSize = builder

				.translation("repurposedstructures.config.badlandsvillagesize")
				.defineInRange("badlandsVillageSize", 10, 1, 30);

		villageBirchSize = builder
				.translation("repurposedstructures.config.birchvillagesize")
				.defineInRange("birchVillageSize", 6, 1, 30);

		villageDarkForestSize = builder
				.translation("repurposedstructures.config.darkforestvillagesize")
				.defineInRange("darkForestVillageSize", 6, 1, 30);

		villageJungleSize = builder
				.translation("repurposedstructures.config.junglevillagesize")
				.defineInRange("jungleVillageSize", 8, 1, 30);

		villageSwampSize = builder
				.translation("repurposedstructures.config.swampvillagesize")
				.defineInRange("swampVillageSize", 6, 1, 30);

		villageMountainsSize = builder
				.translation("repurposedstructures.config.mountainsvillagesize")
				.defineInRange("mountainsVillageSize", 6, 1, 30);

		villageMushroomSize = builder
				.translation("repurposedstructures.config.mushroomvillagesize")
				.defineInRange("mushroomVillageSize", 8, 1, 30);

		villageGiantTaigaSize = builder
				.translation("repurposedstructures.config.gianttaigavillagesize")
				.defineInRange("giantTaigaVillageSize", 6, 1, 30);

		villageOakSize = builder
				.translation("repurposedstructures.config.oakvillagesize")
				.defineInRange("oakVillageSize", 6, 1, 30);

		villageCrimsonSize = builder
				.translation("repurposedstructures.config.crimsonvillagesize")
				.defineInRange("crimsonVillageSize", 6, 1, 30);

		villageWarpedSize = builder
				.translation("repurposedstructures.config.warpedvillagesize")
				.defineInRange("warpedVillageSize", 6, 1, 30);

		builder.pop();
	}
}
