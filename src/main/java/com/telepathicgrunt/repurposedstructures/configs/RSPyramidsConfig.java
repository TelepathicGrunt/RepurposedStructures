package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSPyramidsConfig
{
	public static class RSPyramidsConfigValues
	{
		public ConfigValueListener<Integer> netherPyramidMaxChunkDistance;
		public ConfigValueListener<Integer> badlandsPyramidMaxChunkDistance;
		public ConfigValueListener<Integer> pyramidSnowyMaxChunkDistance;
		public ConfigValueListener<Integer> pyramidEndMaxChunkDistance;
		public ConfigValueListener<Integer> pyramidIcyMaxChunkDistance;
		public ConfigValueListener<Integer> pyramidJungleMaxChunkDistance;
		public ConfigValueListener<Integer> pyramidMushroomMaxChunkDistance;
		public ConfigValueListener<Integer> pyramidOceanMaxChunkDistance;
		public ConfigValueListener<Integer> pyramidGiantTreeTaigaMaxChunkDistance;
		public ConfigValueListener<Integer> pyramidFlowerForestMaxChunkDistance;

		// regexpos1

		public RSPyramidsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			netherPyramidMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Nether Pyramids in Nether.",
						 " 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.temples.netherpyramidmaxchunkdistance")
				.defineInRange("netherPyramidMaxChunkDistance", 37, 1, 1001));

			badlandsPyramidMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Badlands Pyramid in non-plateau Badlands biomes.",
						 " 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.temples.badlandspyramidmaxchunkdistance")
				.defineInRange("badlandsPyramidMaxChunkDistance", 40, 1, 1001));

			pyramidSnowyMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Snowy Pyramid in snowy biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.pyramids.pyramidsnowymaxchunkdistance")
				.defineInRange("pyramidSnowyMaxChunkDistance", 40, 1, 1001));

			pyramidEndMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are End Pyramid in End biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.pyramids.pyramidendmaxchunkdistance")
				.defineInRange("pyramidEndMaxChunkDistance", 68, 1, 1001));

			pyramidIcyMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Icy Pyramid in super cold or icy biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.pyramids.pyramidicymaxchunkdistance")
				.defineInRange("pyramidIcyMaxChunkDistance", 37, 1, 1001));

			pyramidJungleMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Jungle Pyramid in Jungle biomes. 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.pyramids.pyramidjunglemaxchunkdistance")
				.defineInRange("pyramidJungleMaxChunkDistance", 44, 1, 1001));

			pyramidMushroomMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Mushroom Pyramid in Mushroom biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.pyramids.pyramidmushroommaxchunkdistance")
				.defineInRange("pyramidMushroomMaxChunkDistance", 24, 1, 1001));

			pyramidOceanMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Ocean Pyramid in Ocean biomes. ",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.pyramids.pyramidoceanmaxchunkdistance")
				.defineInRange("pyramidOceanMaxChunkDistance", 40, 1, 1001));

			pyramidGiantTreeTaigaMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Giant Tree Taiga Pyramid in Giant Tree Taiga biomes.",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.pyramids.pyramidgianttreetaigamaxchunkdistance")
				.defineInRange("pyramidGiantTreeTaigaMaxChunkDistance", 40, 1, 1001));

			pyramidFlowerForestMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Flower Forest Pyramid in Flower Forest biomes. ",
						" 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.pyramids.pyramidflowerforestmaxchunkdistance")
				.defineInRange("pyramidFlowerForestMaxChunkDistance", 36, 1, 1001));
		}
	}
}
