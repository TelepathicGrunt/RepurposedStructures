package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSVillagesConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;

	public static ForgeConfigSpec.IntValue badlandsVillageMaxChunkDistance;
	public static ForgeConfigSpec.IntValue birchVillageMaxChunkDistance;
	public static ForgeConfigSpec.IntValue darkForestVillageMaxChunkDistance;
	public static ForgeConfigSpec.IntValue jungleVillageMaxChunkDistance;
	public static ForgeConfigSpec.IntValue swampVillageMaxChunkDistance;
	public static ForgeConfigSpec.IntValue mountainsVillageMaxChunkDistance;
	public static ForgeConfigSpec.IntValue giantTaigaVillageMaxChunkDistance;
	public static ForgeConfigSpec.IntValue oakVillageMaxChunkDistance;
	public static ForgeConfigSpec.IntValue crimsonVillageMaxChunkDistance;
	public static ForgeConfigSpec.IntValue warpedVillageMaxChunkDistance;
	public static ForgeConfigSpec.IntValue mushroomVillageAverageChunkDistance;
	// regexpos1

	public static ForgeConfigSpec.IntValue badlandsVillageSize;
	public static ForgeConfigSpec.IntValue birchVillageSize;
	public static ForgeConfigSpec.IntValue darkForestVillageSize;
	public static ForgeConfigSpec.IntValue jungleVillageSize;
	public static ForgeConfigSpec.IntValue swampVillageSize;
	public static ForgeConfigSpec.IntValue mountainsVillageSize;
	public static ForgeConfigSpec.IntValue mushroomVillageSize;
	public static ForgeConfigSpec.IntValue giantTaigaVillageSize;
	public static ForgeConfigSpec.IntValue oakVillageSize;
	public static ForgeConfigSpec.IntValue crimsonVillageSize;
	public static ForgeConfigSpec.IntValue warpedVillageSize;
	
	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		builder.push("Spawnrates");

		badlandsVillageMaxChunkDistance = builder
			.comment("\n How rare are Badlands Villages in Badland biomes.",
				" 1 for spawning in most chunks and 1001 for no spawn.")
			.translation("repurposedstructures.config.villages.badlandsvillagemaxchunkdistance")
			.defineInRange("badlandsVillageMaxChunkDistance", 34, 1, 1001);

		birchVillageMaxChunkDistance = builder
			.comment("\n How rare are Birch Villages in Birch biomes.",
				" 1 for spawning in most chunks and 1001 for no spawn.")
			.translation("repurposedstructures.config.villages.birchvillagemaxchunkdistance")
			.defineInRange("birchVillageMaxChunkDistance", 47, 1, 1001);

		darkForestVillageMaxChunkDistance = builder
			.comment("\n How rare are Dark Forest Villages in Dark Forest biomes.",
				" 1 for spawning in most chunks and 1001 for no spawn.")
			.translation("repurposedstructures.config.villages.darkforestvillagemaxchunkdistance")
			.defineInRange("darkForestVillageMaxChunkDistance", 47, 1, 1001);

		jungleVillageMaxChunkDistance = builder
			.comment("\n How rare are Jungle Villages in Jungle biomes.",
				" 1 for spawning in most chunks and 1001 for no spawn.")
			.translation("repurposedstructures.config.villages.junglevillagemaxchunkdistance")
			.defineInRange("jungleVillageMaxChunkDistance", 47, 1, 1001);

		swampVillageMaxChunkDistance = builder
			.comment("\n How rare are Swamp Villages in Swamp biomes.",
				" 1 for spawning in most chunks and 1001 for no spawn.")
			.translation("repurposedstructures.config.villages.swampvillagemaxchunkdistance")
			.defineInRange("swampVillageMaxChunkDistance", 47, 1, 1001);

		mountainsVillageMaxChunkDistance = builder
			.comment("\n How rare are Mountains Villages in Mountains biomes.",
				" 1 for spawning in most chunks and 1001 for no spawn.")
			.translation("repurposedstructures.config.villages.mountainsvillagemaxchunkdistance")
			.defineInRange("mountainsVillageMaxChunkDistance", 47, 1, 1001);

		mushroomVillageAverageChunkDistance = builder
				.comment("\n How rare are Mushroom Villages.",
						" 1 for spawning in most chunks and 10001 for none.")
				.translation("repurposedstructures.config.villages.villagemushroomaveragechunkdistance")
			.defineInRange("villageMushroomAverageChunkDistance", 24, 1, 1001);

		giantTaigaVillageMaxChunkDistance = builder
			.comment("\n How rare are Giant Taiga Villages in Giant Taiga biomes.",
				" 1 for spawning in most chunks and 1001 for no spawn.")
			.translation("repurposedstructures.config.villages.gianttaigavillagemaxchunkdistance")
			.defineInRange("giantTaigaVillageMaxChunkDistance", 47, 1, 1001);

		oakVillageMaxChunkDistance = builder
				.comment("\n How rare are Oak Villages in forest category",
						" biomes that are not birch or dark forest.")
				.translation("repurposedstructures.config.village.villageoakmaxchunkdistance")
				.defineInRange("oakVillageMaxChunkDistance", 47, 1, 1001);

		crimsonVillageMaxChunkDistance = builder
			.comment("\n How rare are Crimson Village in Crimson Forest biomes.",
					 " 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.villages.crimsonvillagemaxchunkdistance")
			.defineInRange("crimsonVillageMaxChunkDistance", 30, 1, 1001);

		warpedVillageMaxChunkDistance = builder
			.comment("\n How rare are Warped Village in Warped Forest biomes.",
					 " 1 for spawning in most chunks and 1001 for none.")
			.translation("repurposedstructures.config.villages.warpedvillagemaxchunkdistance")
			.defineInRange("warpedVillageMaxChunkDistance", 30, 1, 1001);


		// regexpos2

		builder.pop();

		builder.push("Size");

		badlandsVillageSize = builder
				.comment("\n Size of the village. This is how many pieces long a path can be from the start piece.")
				.translation("repurposedstructures.config.badlandsvillagesize")
				.defineInRange("badlandsVillageSize", 10, 1, 30);

		birchVillageSize = builder
				.comment("\n Size of the village. This is how many pieces long a path can be from the start piece.")
				.translation("repurposedstructures.config.birchvillagesize")
				.defineInRange("birchVillageSize", 6, 1, 30);

		darkForestVillageSize = builder
				.comment("\n Size of the village. This is how many pieces long a path can be from the start piece.")
				.translation("repurposedstructures.config.darkforestvillagesize")
				.defineInRange("darkForestVillageSize", 6, 1, 30);

		jungleVillageSize = builder
				.comment("\n Size of the village. This is how many pieces long a path can be from the start piece.")
				.translation("repurposedstructures.config.junglevillagesize")
				.defineInRange("jungleVillageSize", 8, 1, 30);

		swampVillageSize = builder
				.comment("\n Size of the village. This is how many pieces long a path can be from the start piece.")
				.translation("repurposedstructures.config.swampvillagesize")
				.defineInRange("swampVillageSize", 6, 1, 30);

		mountainsVillageSize = builder
				.comment("\n Size of the village. This is how many pieces long a path can be from the start piece.")
				.translation("repurposedstructures.config.mountainsvillagesize")
				.defineInRange("mountainsVillageSize", 6, 1, 30);

		mushroomVillageSize = builder
				.comment("\n Size of the village. This is how many pieces long a path can be from the start piece.")
				.translation("repurposedstructures.config.mushroomvillagesize")
				.defineInRange("mushroomVillageSize", 8, 1, 30);

		giantTaigaVillageSize = builder
				.comment("\n Size of the village. This is how many pieces long a path can be from the start piece.")
				.translation("repurposedstructures.config.gianttaigavillagesize")
				.defineInRange("giantTaigaVillageSize", 6, 1, 30);

		oakVillageSize = builder
				.comment("\n Size of the village. This is how many pieces long a path can be from the start piece.")
				.translation("repurposedstructures.config.oakvillagesize")
				.defineInRange("oakVillageSize", 6, 1, 30);

		crimsonVillageSize = builder
				.comment("\n Size of the village. This is how many pieces long a path can be from the start piece.")
				.translation("repurposedstructures.config.crimsonvillagesize")
				.defineInRange("crimsonVillageSize", 6, 1, 30);

		warpedVillageSize = builder
				.comment("\n Size of the village. This is how many pieces long a path can be from the start piece.")
				.translation("repurposedstructures.config.warpedvillagesize")
				.defineInRange("warpedVillageSize", 6, 1, 30);

		builder.pop();
	}
}
