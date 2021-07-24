package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSTemplesConfig
{
	public static class RSTemplesConfigValues
	{
		public ConfigValueListener<Integer> netherWastelandTempleMaxChunkDistance;
		public ConfigValueListener<Integer> netherBasaltTempleMaxChunkDistance;
		public ConfigValueListener<Integer> netherCrimsonTempleMaxChunkDistance;
		public ConfigValueListener<Integer> netherWarpedTempleMaxChunkDistance;
		public ConfigValueListener<Integer> netherSoulTempleMaxChunkDistance;

		// regexpos1

		public RSTemplesConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			netherWastelandTempleMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Nether Temples in Nether Wastelands.",
						 " 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.temples.netherwastelandtemplemaxchunkdistance")
				.defineInRange("netherWastelandTempleMaxChunkDistance", 27, 1, 1001));

			netherBasaltTempleMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Nether Basalt Temples in Nether Basalt Delta biomes.",
						 " 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.temples.netherbasalttemplemaxchunkdistance")
				.defineInRange("netherBasaltTempleMaxChunkDistance", 27, 1, 1001));

			netherCrimsonTempleMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Nether Crimson Temples in Nether Crimson Forest.",
						 " 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.temples.nethercrimsontemplemaxchunkdistance")
				.defineInRange("netherCrimsonTempleMaxChunkDistance", 27, 1, 1001));

			netherWarpedTempleMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Nether Crimson Temples in Nether Warped Forest.",
						 " 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.temples.netherwarpedtemplemaxchunkdistance")
				.defineInRange("netherWarpedTempleMaxChunkDistance", 27, 1, 1001));

			netherSoulTempleMaxChunkDistance = subscriber.subscribe(builder
				.comment("\n How rare are Nether Soul Temples in Nether Soul Sand Valley.",
						 " 1 for spawning in most chunks and 1001 for none.")
				.translation("repurposedstructures.config.temples.nethersoultemplemaxchunkdistance")
				.defineInRange("netherSoulTempleMaxChunkDistance", 27, 1, 1001));
		}
	}
}
