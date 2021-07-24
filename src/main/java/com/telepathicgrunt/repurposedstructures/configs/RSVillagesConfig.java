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
		public ConfigValueListener<Integer> crimsonVillageMaxChunkDistance;
		public ConfigValueListener<Integer> warpedVillageMaxChunkDistance;
		public ConfigValueListener<Integer> villageOakMaxChunkDistance;

		public RSVillagesConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			badlandsVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Badlands Villages in Badland biomes.",
					" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.villages.badlandsvillagemaxchunkdistance")
				.defineInRange("badlandsVillageMaxChunkDistance", 30, 1, 1001));

			birchVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Birch Villages in Birch biomes.",
					" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.villages.birchvillagemaxchunkdistance")
				.defineInRange("birchVillageMaxChunkDistance", 38, 1, 1001));

			darkForestVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Dark Forest Villages in Dark Forest biomes.",
					" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.villages.darkforestvillagemaxchunkdistance")
				.defineInRange("darkForestVillageMaxChunkDistance", 38, 1, 1001));

			jungleVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Jungle Villages in Jungle biomes.",
					" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.villages.junglevillagemaxchunkdistance")
				.defineInRange("jungleVillageMaxChunkDistance", 38, 1, 1001));

			swampVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Swamp Villages in Swamp biomes.",
					" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.villages.swampvillagemaxchunkdistance")
				.defineInRange("swampVillageMaxChunkDistance", 38, 1, 1001));

			mountainsVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Mountains Villages in Mountains biomes.",
					" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.villages.mountainsvillagemaxchunkdistance")
				.defineInRange("mountainsVillageMaxChunkDistance", 38, 1, 1001));

			giantTaigaVillageMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Giant Taiga Villages in Giant Taiga biomes.",
					" 1 for spawning in most chunks and 1001 for no spawn.")
				.translation("repurposedstructures.config.villages.gianttaigavillagemaxchunkdistance")
				.defineInRange("giantTaigaVillageMaxChunkDistance", 38, 1, 1001));

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

			villageOakMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Oak Villages in forest category",
						 " biomes that are not birch or dark forest.")
				.translation("repurposedstructures.config.village.villageoakmaxchunkdistance")
				.defineInRange("villageOakMaxChunkDistance", 38, 1, 1001));
		}
	}
}
