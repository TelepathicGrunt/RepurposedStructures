package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSBastionsConfig
{
	public static class RSBastionsConfigValues
	{
		public ConfigValueListener<Integer> bastionUndergroundMaxChunkDistance;
		
		public RSBastionsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			bastionUndergroundMaxChunkDistance = subscriber.subscribe(builder
					.comment("How rare are Underground Bastions in non-ocean and non-beach Overworld biomes.",
							"1 for spawning in most chunks and 10001 for none.")
					.translation("repurposedstructures.config.pyramids.bastionundergroundmaxchunkdistance")
					.defineInRange("bastionUndergroundMaxChunkDistance", 500, 1, 10001));
		}
	}
}
