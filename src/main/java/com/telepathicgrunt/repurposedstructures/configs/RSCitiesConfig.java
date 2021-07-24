package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSCitiesConfig
{
	public static class RSCitiesConfigValues
	{
		public ConfigValueListener<Integer> citiesNetherMaxChunkDistance;
		
		public RSCitiesConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			citiesNetherMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Nether Cities. 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.cities.citiesnethermaxchunkdistance")
					.defineInRange("citiesNetherMaxChunkDistance", 120, 1, 1001));
		}
	}
}
