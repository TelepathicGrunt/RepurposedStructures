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
		public ConfigValueListener<Integer> witchHutsTaigaMaxChunkDistance;
		public ConfigValueListener<Boolean> addWitchHutsTaigaToModdedBiomes;
		public ConfigValueListener<Integer> witchHutsGiantTreeTaigaMaxChunkDistance;
		public ConfigValueListener<Boolean> addWitchHutsGiantTreeTaigaToModdedBiomes;
		public ConfigValueListener<Integer> witchHutsBirchMaxChunkDistance;
		public ConfigValueListener<Boolean> addWitchHutsBirchToModdedBiomes;
		public ConfigValueListener<Integer> witchHutsDarkForestMaxChunkDistance;
		public ConfigValueListener<Boolean> addWitchHutsDarkForestToModdedBiomes;
		public ConfigValueListener<String> blacklistedWitchHutBiomes;

		public RSWitchHutsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Witch Huts");

				blacklistedWitchHutBiomes = subscriber.subscribe(builder
						.comment("\n Add the ID/resource location of the biome you don't want"
								+"\n RS's witch huts to spawn in. Separate each ID with a comma ,"
								+"\n"
								+"\n Example: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
						.translation("repurposedstructures.config.witch_hut.blacklistedwitchhutbiomes")
						.define("blacklistedWitchHutBiomes", " "));

				witchHutsOakMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n Add Oak Witch Huts to modded Forest biomes that are not birch or dark oak.")
						.translation("repurposedstructures.config.witch_huts.witchhutsoakmaxchunkdistance")
						.defineInRange("witchHutsOakMaxChunkDistance", 48, 1, 1001));

				addWitchHutsOakToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add Oak Witch Huts to modded Forest biomes that are not birch or dark oak.")
						.translation("repurposedstructures.config.witch_huts.addwitchhutsoaktomoddedbiomes")
						.define("addWitchHutsOakToModdedBiomes", true));

				witchHutsTaigaMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n Add Taiga Witch Huts to modded Taiga biomes.")
						.translation("repurposedstructures.config.witch_huts.witchhutstaigamaxchunkdistance")
						.defineInRange("witchHutsTaigaMaxChunkDistance", 48, 1, 1001));

				addWitchHutsTaigaToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add Taiga Witch Huts to modded Taiga biomes.")
						.translation("repurposedstructures.config.witch_huts.addwitchhutstaigatomoddedbiomes")
						.define("addWitchHutsTaigaToModdedBiomes", true));

				witchHutsGiantTreeTaigaMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n Add Giant Tree Taiga Witch Huts to modded Giant Tree Taiga biomes.")
						.translation("repurposedstructures.config.witch_huts.witchhutsgianttreetaigamaxchunkdistance")
						.defineInRange("witchHutsGiantTreeTaigaMaxChunkDistance", 48, 1, 1001));

				addWitchHutsGiantTreeTaigaToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add Giant Tree Taiga Witch Huts to modded Giant Tree Taiga biomes.")
						.translation("repurposedstructures.config.witch_huts.addwitchhutsgianttreetaigatomoddedbiomes")
						.define("addWitchHutsGiantTreeTaigaToModdedBiomes", true));

				witchHutsBirchMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n Add Birch Witch Huts to modded Birch biomes.")
						.translation("repurposedstructures.config.witch_huts.witchhutsbirchmaxchunkdistance")
						.defineInRange("witchHutsBirchMaxChunkDistance", 48, 1, 1001));

				addWitchHutsBirchToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add Birch Witch Huts to modded Birch biomes.")
						.translation("repurposedstructures.config.witch_huts.addwitchhutsbirchtomoddedbiomes")
						.define("addWitchHutsBirchToModdedBiomes", true));

				witchHutsDarkForestMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n Add Dark Forest Witch Huts to modded Dark Forest biomes.")
						.translation("repurposedstructures.config.witch_huts.witchhutsdarkforestmaxchunkdistance")
						.defineInRange("witchHutsDarkForestMaxChunkDistance", 48, 1, 1001));

				addWitchHutsDarkForestToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add Dark Forest Witch Huts to modded Dark Forest biomes.")
						.translation("repurposedstructures.config.witch_huts.addwitchhutsdarkforesttomoddedbiomes")
						.define("addWitchHutsDarkForestToModdedBiomes", true));

			builder.pop();
		}
	}
}
