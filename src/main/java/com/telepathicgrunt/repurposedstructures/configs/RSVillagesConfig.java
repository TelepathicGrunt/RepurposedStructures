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
        					.defineInRange("badlandsVillageSpawnrate", 15, 1, 1001));
				builder.pop();
					
			builder.pop();
		}
	}
}
