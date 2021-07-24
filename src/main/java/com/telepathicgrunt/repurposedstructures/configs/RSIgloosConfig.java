package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSIgloosConfig
{
	public static class RSIgloosConfigValues
	{
		public ConfigValueListener<Integer> grassyIglooMaxChunkDistance;
		public ConfigValueListener<Integer> stoneIglooMaxChunkDistance;
		
		public RSIgloosConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			grassyIglooMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Grassy Igloos in Plains and Forests.",
							" 1 for spawning in most chunks and 1001 for no spawn.")
					.translation("repurposedstructures.config.igloo.grassyigloomaxchunkdistance")
					.defineInRange("grassyIglooMaxChunkDistance", 20, 1, 1001));

			stoneIglooMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Stone Igloos in Giant Tree Taiga biomes.",
							" 1 for spawning in most chunks and 1001 for no spawn.")
					.translation("repurposedstructures.config.igloo.stoneigloomaxchunkdistance")
					.defineInRange("stoneIglooMaxChunkDistance", 20, 1, 1001));
		}
	}
}
