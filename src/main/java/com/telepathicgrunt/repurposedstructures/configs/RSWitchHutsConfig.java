package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSWitchHutsConfig
{
	public static class RSWitchHutsConfigValues
	{
		public ConfigValueListener<Integer> witchHutsOakMaxChunkDistance;
		public ConfigValueListener<Boolean> addWitchHutsOakToModdedBiomes;

		public ConfigValueListener<String> blacklistedWitchHutBiomes;

		public RSWitchHutsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Witch Huts");

				blacklistedWitchHutBiomes = subscriber.subscribe(builder
						.comment("\r\n Add the ID/resource location of the biome you don't want"
								+"\r\n RS's witch huts to spawn in. Separate each ID with a comma ,"
								+"\r\n"
								+"\r\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
						.translation("repurposedstructures.config.witch_hut.blacklistedwitchhutbiomes")
						.define("blacklistedWitchHutBiomes", ""));

				witchHutsOakMaxChunkDistance = subscriber.subscribe(builder
						.comment("Add Oak Witch Huts to modded Forest biomes that are not birch or dark oak.")
						.translation("repurposedstructures.config.witch_huts.witchhutsoakmaxchunkdistance")
						.defineInRange("witchHutsOakMaxChunkDistance", 48, 1, 1001));

				addWitchHutsOakToModdedBiomes = subscriber.subscribe(builder
						.comment("Add Oak Witch Huts to modded Forest biomes that are not birch or dark oak.")
						.translation("repurposedstructures.config.witch_huts.addwitchhutsoaktomoddedbiomes")
						.define("addWitchHutsOakToModdedBiomes", true));

			// regexpos1
			builder.pop();
		}
	}
}
