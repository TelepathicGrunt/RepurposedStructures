package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSRuinsConfig
{
	public static class RSRuinsConfigValues
	{
		public ConfigValueListener<Integer> ruinsNetherMaxChunkDistance;
		public ConfigValueListener<Integer> ruinsLandWarmMaxChunkDistance;
		public ConfigValueListener<Integer> ruinsLandHotMaxChunkDistance;
		
		public RSRuinsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			ruinsNetherMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Nether Ruins. 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.ruins.ruinsnethermaxchunkdistance")
					.defineInRange("ruinsNetherMaxChunkDistance", 35, 1, 1001));

			ruinsLandWarmMaxChunkDistance = subscriber.subscribe(builder
					.comment("How rare are Warm Land Ruins in Plains, Forests, Swamps, and non-snowy Taiga biomes.",
							"\n1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.ruins.ruinslandwarmmaxchunkdistance")
					.defineInRange("ruinsLandWarmMaxChunkDistance", 42, 1, 1001));

			ruinsLandHotMaxChunkDistance = subscriber.subscribe(builder
					.comment("How rare are Hot Land Ruins in Desert biomes.",
							"\n1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.ruins.ruinslandhotmaxchunkdistance")
					.defineInRange("ruinsLandHotMaxChunkDistance", 45, 1, 1001));
		}
	}
}
