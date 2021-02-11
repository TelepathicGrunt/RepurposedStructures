package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSMansionsConfig
{
	public static class RSMansionsConfigValues
	{
		public ConfigValueListener<Integer> mansionBirchMaxChunkDistance;
		public ConfigValueListener<Boolean> addMansionBirchToModdedBiomes;
		public ConfigValueListener<String> blacklistedMansionBiomes;

		public RSMansionsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Mansions");

				blacklistedMansionBiomes = subscriber.subscribe(builder
						.comment("\r\n Add the ID/resource location of the biome you don't want"
								+"\r\n RS's mansions to spawn in. Separate each ID with a comma ,"
								+"\r\n"
								+"\r\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
						.translation("repurposedstructures.config.mansions.blacklistedmansionbiomes")
						.define("blacklistedMansionBiomes", ""));

				mansionBirchMaxChunkDistance = subscriber.subscribe(builder
						.comment("Add Birch Mansions to modded Birch biomes.")
						.translation("repurposedstructures.config.mansions.mansionbirchmaxchunkdistance")
						.defineInRange("mansionBirchMaxChunkDistance", 120, 1, 1001));

				addMansionBirchToModdedBiomes = subscriber.subscribe(builder
						.comment("Add Birch Mansions to modded Birch biomes.")
						.translation("repurposedstructures.config.mansions.addmansionbirchtomoddedbiomes")
						.define("addMansionBirchToModdedBiomes", true));

			builder.pop();
		}
	}
}
