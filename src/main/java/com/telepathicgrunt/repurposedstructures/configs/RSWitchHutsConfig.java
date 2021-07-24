package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSWitchHutsConfig
{
	public static class RSWitchHutsConfigValues
	{
		public ConfigValueListener<Integer> witchHutsOakMaxChunkDistance;
		public ConfigValueListener<Integer> witchHutsTaigaMaxChunkDistance;
		public ConfigValueListener<Integer> witchHutsGiantTreeTaigaMaxChunkDistance;
		public ConfigValueListener<Integer> witchHutsBirchMaxChunkDistance;
		public ConfigValueListener<Integer> witchHutsDarkForestMaxChunkDistance;

		public RSWitchHutsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			witchHutsOakMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n Add Oak Witch Huts to modded Forest biomes that are not birch or dark oak.",
							" 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.witch_huts.witchhutsoakmaxchunkdistance")
					.defineInRange("witchHutsOakMaxChunkDistance", 48, 1, 1001));

			witchHutsTaigaMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n Add Taiga Witch Huts to modded Taiga biomes.",
							" 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.witch_huts.witchhutstaigamaxchunkdistance")
					.defineInRange("witchHutsTaigaMaxChunkDistance", 48, 1, 1001));

			witchHutsGiantTreeTaigaMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n Add Giant Tree Taiga Witch Huts to modded Giant Tree Taiga biomes.",
							" 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.witch_huts.witchhutsgianttreetaigamaxchunkdistance")
					.defineInRange("witchHutsGiantTreeTaigaMaxChunkDistance", 48, 1, 1001));

			witchHutsBirchMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n Add Birch Witch Huts to modded Birch biomes.",
							" 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.witch_huts.witchhutsbirchmaxchunkdistance")
					.defineInRange("witchHutsBirchMaxChunkDistance", 48, 1, 1001));

			witchHutsDarkForestMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n Add Dark Forest Witch Huts to modded Dark Forest biomes.",
							" 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.witch_huts.witchhutsdarkforestmaxchunkdistance")
					.defineInRange("witchHutsDarkForestMaxChunkDistance", 48, 1, 1001));
		}
	}
}
