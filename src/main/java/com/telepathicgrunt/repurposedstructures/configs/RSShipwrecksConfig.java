package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class RSShipwrecksConfig
{
	public static class RSShipwrecksConfigValues
	{
		public ConfigValueListener<Boolean> addEndShipwreckToModdedBiomes;
		public ConfigValueListener<Integer> endShipwreckSpawnrate;
		public ConfigValueListener<String> blacklistedShipwreckBiomes;

		public RSShipwrecksConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Shipwrecks");

				addEndShipwreckToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n Add End Shipwreck to modded End biomes.")
					.translation("repurposedstructures.config.shipwrecks.addendshipwrecktomoddedbiomes")
					.define("addEndShipwreckToModdedBiomes", false));

				endShipwreckSpawnrate = subscriber.subscribe(builder
					.comment("\r\n How rare are End Shipwreck in End Highlands biomes."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.shipwrecks.endshipwreckspawnrate")
					.defineInRange("endShipwreckSpawnrate", 18, 1, 1001));

				blacklistedShipwreckBiomes = subscriber.subscribe(builder
					.comment("\r\n Add the ID/resource location of the biome you don't want"
							+"\r\n RS's shipwrecks to spawn in. Separate each ID with a comma ,"
							+"\r\n"
							+"\r\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
					.translation("repurposedstructures.config.shipwrecks.blacklistedshipwreckbiomes")
					.define("blacklistedShipwreckBiomes", ""));

			builder.pop();
		}
	}
}
