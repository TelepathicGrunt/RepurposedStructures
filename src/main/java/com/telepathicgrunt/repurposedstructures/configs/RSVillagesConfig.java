package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSVillagesConfig
{
	public static class RSVillagesConfigValues
	{
		public ConfigValueListener<Integer> badlandsVillageMaxChunkDistance;
		public ConfigValueListener<Integer> birchVillageMaxChunkDistance;
		public ConfigValueListener<Integer> darkForestVillageMaxChunkDistance;
		public ConfigValueListener<Integer> jungleVillageMaxChunkDistance;
		public ConfigValueListener<Integer> swampVillageMaxChunkDistance;
		public ConfigValueListener<Integer> mountainsVillageMaxChunkDistance;
		public ConfigValueListener<Integer> giantTaigaVillageMaxChunkDistance;
		public ConfigValueListener<Integer> oakVillageMaxChunkDistance;
		public ConfigValueListener<Integer> crimsonVillageMaxChunkDistance;
		public ConfigValueListener<Integer> warpedVillageMaxChunkDistance;

		public ConfigValueListener<Integer> badlandsVillageSize;
		public ConfigValueListener<Integer> birchVillageSize;
		public ConfigValueListener<Integer> darkForestVillageSize;
		public ConfigValueListener<Integer> jungleVillageSize;
		public ConfigValueListener<Integer> swampVillageSize;
		public ConfigValueListener<Integer> mountainsVillageSize;
		public ConfigValueListener<Integer> giantTaigaVillageSize;
		public ConfigValueListener<Integer> oakVillageSize;
		public ConfigValueListener<Integer> crimsonVillageSize;
		public ConfigValueListener<Integer> warpedVillageSize;

		public RSVillagesConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Spawnrates");

			badlandsVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Badlands Villages in Badland biomes.",
					" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.villages.badlandsvillagemaxchunkdistance")
				.defineInRange("badlandsVillageMaxChunkDistance", 34, 1, 1001));

			birchVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Birch Villages in Birch biomes.",
					" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.villages.birchvillagemaxchunkdistance")
				.defineInRange("birchVillageMaxChunkDistance", 47, 1, 1001));

			darkForestVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Dark Forest Villages in Dark Forest biomes.",
					" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.villages.darkforestvillagemaxchunkdistance")
				.defineInRange("darkForestVillageMaxChunkDistance", 47, 1, 1001));

			jungleVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Jungle Villages in Jungle biomes.",
					" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.villages.junglevillagemaxchunkdistance")
				.defineInRange("jungleVillageMaxChunkDistance", 47, 1, 1001));

			swampVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Swamp Villages in Swamp biomes.",
					" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.villages.swampvillagemaxchunkdistance")
				.defineInRange("swampVillageMaxChunkDistance", 47, 1, 1001));

			mountainsVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Mountains Villages in Mountains biomes.",
					" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.villages.mountainsvillagemaxchunkdistance")
				.defineInRange("mountainsVillageMaxChunkDistance", 47, 1, 1001));

			giantTaigaVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Giant Taiga Villages in Giant Taiga biomes.",
					" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.villages.gianttaigavillagemaxchunkdistance")
				.defineInRange("giantTaigaVillageMaxChunkDistance", 47, 1, 1001));

			oakVillageMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Oak Villages in forest category",
							" biomes that are not birch or dark forest.")
					.translation("repurposedstructures.config.village.villageoakmaxchunkdistance")
					.defineInRange("oakVillageMaxChunkDistance", 47, 1, 1001));

			crimsonVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Crimson Village in Crimson Forest biomes.",
						 " 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.villages.crimsonvillagemaxchunkdistance")
				.defineInRange("crimsonVillageMaxChunkDistance", 30, 1, 1001));

			warpedVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Warped Village in Warped Forest biomes.",
						 " 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.villages.warpedvillagemaxchunkdistance")
				.defineInRange("warpedVillageMaxChunkDistance", 30, 1, 1001));

			builder.pop();

			builder.push("Size");

			badlandsVillageSize = subscriber.subscribe(builder
					.comment("\n Size of the Village. This is how many pieces long a path can be from the start piece.")
					.translation("repurposedstructures.config.badlandsvillagesize")
					.defineInRange("badlandsVillageSize", 10, 1, 30));

			birchVillageSize = subscriber.subscribe(builder
					.comment("\n Size of the Village. This is how many pieces long a path can be from the start piece.")
					.translation("repurposedstructures.config.birchvillagesize")
					.defineInRange("birchVillageSize", 6, 1, 30));

			darkForestVillageSize = subscriber.subscribe(builder
					.comment("\n Size of the Village. This is how many pieces long a path can be from the start piece.")
					.translation("repurposedstructures.config.darkforestvillagesize")
					.defineInRange("darkForestVillageSize", 6, 1, 30));

			jungleVillageSize = subscriber.subscribe(builder
					.comment("\n Size of the Village. This is how many pieces long a path can be from the start piece.")
					.translation("repurposedstructures.config.junglevillagesize")
					.defineInRange("jungleVillageSize", 8, 1, 30));

			swampVillageSize = subscriber.subscribe(builder
					.comment("\n Size of the Village. This is how many pieces long a path can be from the start piece.")
					.translation("repurposedstructures.config.swampvillagesize")
					.defineInRange("swampVillageSize", 6, 1, 30));

			mountainsVillageSize = subscriber.subscribe(builder
					.comment("\n Size of the Village. This is how many pieces long a path can be from the start piece.")
					.translation("repurposedstructures.config.mountainsvillagesize")
					.defineInRange("mountainsVillageSize", 6, 1, 30));

			giantTaigaVillageSize = subscriber.subscribe(builder
					.comment("\n Size of the Village. This is how many pieces long a path can be from the start piece.")
					.translation("repurposedstructures.config.gianttaigavillagesize")
					.defineInRange("giantTaigaVillageSize", 6, 1, 30));

			oakVillageSize = subscriber.subscribe(builder
					.comment("\n Size of the Village. This is how many pieces long a path can be from the start piece.")
					.translation("repurposedstructures.config.oakvillagesize")
					.defineInRange("oakVillageSize", 6, 1, 30));

			crimsonVillageSize = subscriber.subscribe(builder
					.comment("\n Size of the Village. This is how many pieces long a path can be from the start piece.")
					.translation("repurposedstructures.config.crimsonvillagesize")
					.defineInRange("crimsonVillageSize", 6, 1, 30));

			warpedVillageSize = subscriber.subscribe(builder
					.comment("\n Size of the Village. This is how many pieces long a path can be from the start piece.")
					.translation("repurposedstructures.config.warpedvillagesize")
					.defineInRange("warpedVillageSize", 6, 1, 30));

			builder.pop();
		}
	}
}
