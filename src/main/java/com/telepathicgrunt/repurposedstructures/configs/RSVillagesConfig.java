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
		public ConfigValueListener<Integer> badlandsVillageSpawnrate;
		public ConfigValueListener<Integer> birchVillageSpawnrate;
		public ConfigValueListener<Integer> darkForestVillageSpawnrate;
		public ConfigValueListener<Integer> jungleVillageSpawnrate;
		public ConfigValueListener<Integer> swampVillageSpawnrate;

		public RSVillagesConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Villages");
				
				addVillagesToModdedBiomes = subscriber.subscribe(builder
						.comment("\r\n Add the custom villages to modded biomes of the same categories/type.")
					.translation("repurposedstructures.config.villages.addVillagesToModdedBiomes")
					.define("addVillagesToModdedBiomes", false));

				builder.push("Spawnrate");
				
					badlandsVillageSpawnrate = subscriber.subscribe(builder
						.comment("\r\n How rare are Badlands Villages in Badland biomes.\r\n"
							+"\n "
							+" 1 for spawning in most chunks and 1001 for no spawn.")
        					.translation("repurposedstructures.config.villages.badlandsVillageSpawnrate")
        					.defineInRange("badlandsVillageSpawnrate", 17, 1, 1001));

					birchVillageSpawnrate = subscriber.subscribe(builder
						.comment("\r\n How rare are Birch Villages in Birch biomes.\r\n"
							+"\n "
							+" 1 for spawning in most chunks and 1001 for no spawn.")
        					.translation("repurposedstructures.config.villages.birchvillagespawnrate")
        					.defineInRange("birchVillageSpawnrate", 24, 1, 1001));
					
					darkForestVillageSpawnrate = subscriber.subscribe(builder
						.comment("\r\n How rare are Dark Forest Villages in Dark Forest biomes.\r\n"
							+"\n "
							+" 1 for spawning in most chunks and 1001 for no spawn.")
        					.translation("repurposedstructures.config.villages.darkforestvillagespawnrate")
        					.defineInRange("darkForestVillageSpawnrate", 24, 1, 1001));
					
					jungleVillageSpawnrate = subscriber.subscribe(builder
						.comment("\r\n How rare are Jungle Villages in Jungle biomes.\r\n"
							+"\n "
							+" 1 for spawning in most chunks and 1001 for no spawn.")
        					.translation("repurposedstructures.config.villages.junglevillagespawnrate")
        					.defineInRange("jungleVillageSpawnrate", 26, 1, 1001));
					
					swampVillageSpawnrate = subscriber.subscribe(builder
						.comment("\r\n How rare are Swamp Villages in Swamp biomes.\r\n"
							+"\n "
							+" 1 for spawning in most chunks and 1001 for no spawn.")
        					.translation("repurposedstructures.config.villages.swampvillagespawnrate")
        					.defineInRange("swampVillageSpawnrate", 24, 1, 1001));
				builder.pop();
					
			builder.pop();
		}
	}
}
