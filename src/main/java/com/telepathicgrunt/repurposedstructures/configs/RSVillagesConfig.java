package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class RSVillagesConfig
{
	public static class RSVillagesConfigValues
	{
		public ConfigValueListener<Boolean> addVillagesToModdedBiomes;
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
		public ConfigValueListener<String> blacklistedVillageBiomes;

		public RSVillagesConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Villages");
				
				addVillagesToModdedBiomes = subscriber.subscribe(builder
						.comment("\r\n Add the custom villages to modded biomes of the same categories/type.")
					.translation("repurposedstructures.config.villages.addVillagesToModdedBiomes")
					.define("addVillagesToModdedBiomes", true));

				blacklistedVillageBiomes = subscriber.subscribe(builder
					.comment("\r\n Add the ID/resource location of the biome you don't want"
							+"\r\n RS's village to spawn in. Separate each ID with a comma ,"
							+"\r\n"
							+"\r\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
					.translation("repurposedstructures.config.villages.blacklistedvillagebiomes")
					.define("blacklistedVillageBiomes", ""));

				builder.push("MaxChunkDistance");
				
					badlandsVillageMaxChunkDistance = subscriber.subscribe(builder
						.comment("\r\n How rare are Badlands Villages in Badland biomes.\r\n"
							+"\n "
							+" 1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.villages.badlandsvillagemaxchunkdistance")
						.defineInRange("badlandsVillageMaxChunkDistance", 17, 1, 1001));

					birchVillageMaxChunkDistance = subscriber.subscribe(builder
						.comment("\r\n How rare are Birch Villages in Birch biomes.\r\n"
							+"\n "
							+" 1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.villages.birchvillagemaxchunkdistance")
						.defineInRange("birchVillageMaxChunkDistance", 24, 1, 1001));
					
					darkForestVillageMaxChunkDistance = subscriber.subscribe(builder
						.comment("\r\n How rare are Dark Forest Villages in Dark Forest biomes.\r\n"
							+"\n "
							+" 1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.villages.darkforestvillagemaxchunkdistance")
						.defineInRange("darkForestVillageMaxChunkDistance", 24, 1, 1001));
					
					jungleVillageMaxChunkDistance = subscriber.subscribe(builder
						.comment("\r\n How rare are Jungle Villages in Jungle biomes.\r\n"
							+"\n "
							+" 1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.villages.junglevillagemaxchunkdistance")
						.defineInRange("jungleVillageMaxChunkDistance", 26, 1, 1001));

					swampVillageMaxChunkDistance = subscriber.subscribe(builder
						.comment("\r\n How rare are Swamp Villages in Swamp biomes.\r\n"
							+"\n "
							+" 1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.villages.swampvillagemaxchunkdistance")
						.defineInRange("swampVillageMaxChunkDistance", 24, 1, 1001));
					
					mountainsVillageMaxChunkDistance = subscriber.subscribe(builder
						.comment("\r\n How rare are Mountains Villages in Mountains biomes.\r\n"
							+"\n "
							+" 1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.villages.mountainsvillagemaxchunkdistance")
						.defineInRange("mountainsVillageMaxChunkDistance", 24, 1, 1001));
					
					giantTaigaVillageMaxChunkDistance = subscriber.subscribe(builder
						.comment("\r\n How rare are Giant Taiga Villages in Giant Taiga biomes.\r\n"
							+"\n "
							+" 1 for spawning in most chunks and 1001 for no spawn.")
						.translation("repurposedstructures.config.villages.gianttaigavillagemaxchunkdistance")
						.defineInRange("giantTaigaVillageMaxChunkDistance", 24, 1, 1001));

					crimsonVillageMaxChunkDistance = subscriber.subscribe(builder
						.comment("\r\n How rare are Crimson Village in Crimson Forest biomes."
								+ "\n 1 for spawning in most chunks and 1001 for none.")
						.translation("repurposedstructures.config.villages.crimsonvillagemaxchunkdistance")
						.defineInRange("crimsonVillageMaxChunkDistance", 30, 1, 1001));

					warpedVillageMaxChunkDistance = subscriber.subscribe(builder
						.comment("\r\n How rare are Warped Village in Warped Forest biomes."
								+ "\n 1 for spawning in most chunks and 1001 for none.")
						.translation("repurposedstructures.config.villages.warpedvillagemaxchunkdistance")
						.defineInRange("warpedVillageMaxChunkDistance", 30, 1, 1001));

					villageOakMaxChunkDistance = subscriber.subscribe(builder
						.comment("How rare are Oak Villages in forest category"
								+ "\nbiomes that are not birch or dark forest.")
						.translation("repurposedstructures.config.village.villageoakmaxchunkdistance")
						.defineInRange("villageOakMaxChunkDistance", 30, 1, 1001));
				builder.pop();
					
			builder.pop();
		}
	}
}
