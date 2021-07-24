package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSWellsConfig
{

	public static class RSWellsConfigValues
	{
		public ConfigValueListener<Boolean> canHaveBells;
		public ConfigValueListener<Integer> badlandsWellRarityPerChunk;
		public ConfigValueListener<Integer> netherWellRarityPerChunk;
		public ConfigValueListener<Integer> snowWellRarityPerChunk;
		public ConfigValueListener<Integer> mossyStoneWellRarityPerChunk;
		public ConfigValueListener<Integer> forestWellRarityPerChunk;
		public ConfigValueListener<Integer> mushroomWellRarityPerChunk;

		public RSWellsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			badlandsWellRarityPerChunk  = subscriber.subscribe(builder
					.comment("\n Adds Badlands themed wells to Badlands biomes.",
						 " This effects how often wells will attempt to spawn per chunk.",
						 " The chance of a well generating at a chunk is 1/rarityPerChunk.",
						 " 1 for wells spawning in every chunk and 10000 for no wells.")
				.translation("repurposedstructures.config.smallwells.badlandswellrarityperchunk")
				.defineInRange("badlandsWellRarityPerChunk", 250, 1, 10000));

			netherWellRarityPerChunk = subscriber.subscribe(builder
					.comment("\n Adds Nether themed wells to Nether biomes.",
						 " This effects how often wells will attempt to spawn per chunk.",
						 " The chance of a well generating at a chunk is 1/rarityPerChunk.",
						 " 1 for wells spawning in every chunk and 10000 for no wells.")
				.translation("repurposedstructures.config.smallwells.netherwellrarityperchunk")
				.defineInRange("netherWellRarityPerChunk", 200, 1, 10000));

			snowWellRarityPerChunk = subscriber.subscribe(builder
					.comment("\n Adds Snow themed wells to snowy and icy biomes.",
						 " This effects how often wells will attempt to spawn per chunk.",
						 " The chance of a well generating at a chunk is 1/rarityPerChunk.",
						 " 1 for wells spawning in every chunk and 10000 for no wells.")
				.translation("repurposedstructures.config.smallwells.snowwellrarityperchunk")
				.defineInRange("snowWellRarityPerChunk", 350, 1, 10000));

			mossyStoneWellRarityPerChunk = subscriber.subscribe(builder
					.comment("\n Adds mossy stone themed wells to Jungles, Dark Oak, and Swamp biomes.",
						 " This effects how often wells will attempt to spawn per chunk.",
						 " The chance of a well generating at a chunk is 1/rarityPerChunk.",
						 " 1 for wells spawning in every chunk and 10000 for no wells.")
				.translation("repurposedstructures.config.smallwells.mossystonewellrarityperchunk")
				.defineInRange("mossyStoneWellRarityPerChunk", 350, 1, 10000));

			forestWellRarityPerChunk = subscriber.subscribe(builder
					.comment("\n Adds a wood themed wells to Forest and Birch Forest biomes.",
						 " This effects how often wells will attempt to spawn per chunk.",
						 " The chance of a well generating at a chunk is 1/rarityPerChunk.",
						 " 1 for wells spawning in every chunk and 10000 for no wells.")
				.translation("repurposedstructures.config.smallwells.forestwellrarityperchunk")
				.defineInRange("forestWellRarityPerChunk", 350, 1, 10000));

			mushroomWellRarityPerChunk = subscriber.subscribe(builder
				.comment("\n Adds a mushroom themed wells to Mushroom biomes.",
						" This effects how often wells will attempt to spawn per chunk.",
						" The chance of a well generating at a chunk is 1/rarityPerChunk.",
						" 1 for wells spawning in every chunk and 10000 for no wells.")
				.translation("repurposedstructures.config.smallwells.mushroomwellrarityperchunk")
				.defineInRange("mushroomWellRarityPerChunk", 350, 1, 10000));
		}
	}
}
