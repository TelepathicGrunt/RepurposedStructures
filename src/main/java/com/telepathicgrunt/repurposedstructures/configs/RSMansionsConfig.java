package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSMansionsConfig
{
	public static class RSMansionsConfigValues
	{
		public ConfigValueListener<Integer> mansionJungleMaxChunkDistance;
		public ConfigValueListener<Integer> mansionBirchMaxChunkDistance;
		public ConfigValueListener<Integer> mansionOakMaxChunkDistance;
		public ConfigValueListener<Integer> mansionSavannaMaxChunkDistance;
		public ConfigValueListener<Integer> mansionTaigaMaxChunkDistance;
		public ConfigValueListener<Integer> mansionDesertMaxChunkDistance;
		public ConfigValueListener<Integer> mansionSnowyMaxChunkDistance;

		public RSMansionsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			mansionBirchMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n Add Birch Mansions to modded Birch biomes.",
							" 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.mansions.mansionbirchmaxchunkdistance")
					.defineInRange("mansionBirchMaxChunkDistance", 140, 1, 1001));

			mansionJungleMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n Add Jungle Mansions to modded Jungle biomes.",
							" 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.mansions.mansionjunglemaxchunkdistance")
					.defineInRange("mansionJungleMaxChunkDistance", 140, 1, 1001));

			mansionOakMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n Add Oak Mansions to modded forest category biomes that are not birch or dark forest.",
							" 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.mansions.mansionoakmaxchunkdistance")
					.defineInRange("mansionOakMaxChunkDistance", 140, 1, 1001));

			mansionSavannaMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n Add Savanna Mansions to modded Savanna biomes.",
							" 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.mansions.mansionsavannamaxchunkdistance")
					.defineInRange("mansionSavannaMaxChunkDistance", 140, 1, 1001));

			mansionTaigaMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n Add Taiga Mansions to modded non-snowy Taiga biomes.",
							" 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.mansions.mansiontaigamaxchunkdistance")
					.defineInRange("mansionTaigaMaxChunkDistance", 140, 1, 1001));

			mansionDesertMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n Add Desert Mansions to modded Desert biomes.",
							" 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.mansions.mansiondesertmaxchunkdistance")
					.defineInRange("mansionDesertMaxChunkDistance", 140, 1, 1001));

			mansionSnowyMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n Add Snowy Mansions to modded Snowy biomes.",
							" 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.mansions.mansionsnowymaxchunkdistance")
					.defineInRange("mansionSnowyMaxChunkDistance", 140, 1, 1001));
		}
	}
}
