package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSMansionsConfig
{
	public static class RSMansionsConfigValues
	{
		public ConfigValueListener<Integer> mansionJungleMaxChunkDistance;
		public ConfigValueListener<Boolean> addMansionJungleToModdedBiomes;
		public ConfigValueListener<Integer> mansionBirchMaxChunkDistance;
		public ConfigValueListener<Boolean> addMansionBirchToModdedBiomes;
		public ConfigValueListener<Integer> mansionOakMaxChunkDistance;
		public ConfigValueListener<Boolean> addMansionOakToModdedBiomes;
		public ConfigValueListener<Integer> mansionSavannaMaxChunkDistance;
		public ConfigValueListener<Boolean> addMansionSavannaToModdedBiomes;
		public ConfigValueListener<Integer> mansionTaigaMaxChunkDistance;
		public ConfigValueListener<Boolean> addMansionTaigaToModdedBiomes;
		public ConfigValueListener<Integer> mansionDesertMaxChunkDistance;
		public ConfigValueListener<Boolean> addMansionDesertToModdedBiomes;
		public ConfigValueListener<Integer> mansionSnowyMaxChunkDistance;
		public ConfigValueListener<Boolean> addMansionSnowyToModdedBiomes;
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
						.defineInRange("mansionBirchMaxChunkDistance", 140, 1, 1001));

				addMansionBirchToModdedBiomes = subscriber.subscribe(builder
						.comment("Add Birch Mansions to modded Birch biomes.")
						.translation("repurposedstructures.config.mansions.addmansionbirchtomoddedbiomes")
						.define("addMansionBirchToModdedBiomes", true));

				mansionJungleMaxChunkDistance = subscriber.subscribe(builder
						.comment("Add Jungle Mansions to modded Jungle biomes.")
						.translation("repurposedstructures.config.mansions.mansionjunglemaxchunkdistance")
						.defineInRange("mansionJungleMaxChunkDistance", 140, 1, 1001));

				addMansionJungleToModdedBiomes = subscriber.subscribe(builder
						.comment("Add Jungle Mansions to modded Jungle biomes.")
						.translation("repurposedstructures.config.mansions.addmansionjungletomoddedbiomes")
						.define("addMansionJungleToModdedBiomes", true));

				mansionOakMaxChunkDistance = subscriber.subscribe(builder
						.comment("Add Oak Mansions to modded forest category biomes"
								+ "\nthat are not birch or dark forest.")
						.translation("repurposedstructures.config.mansions.mansionoakmaxchunkdistance")
						.defineInRange("mansionOakMaxChunkDistance", 140, 1, 1001));

				addMansionOakToModdedBiomes = subscriber.subscribe(builder
						.comment("Add Oak Mansions to modded forest category biomes"
								+ "\nthat are not birch or dark forest.")
						.translation("repurposedstructures.config.mansions.addmansionoaktomoddedbiomes")
						.define("addMansionOakToModdedBiomes", true));

				mansionSavannaMaxChunkDistance = subscriber.subscribe(builder
						.comment("Add Savanna Mansions to modded Savanna biomes.")
						.translation("repurposedstructures.config.mansions.mansionsavannamaxchunkdistance")
						.defineInRange("mansionSavannaMaxChunkDistance", 140, 1, 1001));

				addMansionSavannaToModdedBiomes = subscriber.subscribe(builder
						.comment("Add Savanna Mansions to modded Savanna biomes.")
						.translation("repurposedstructures.config.mansions.addmansionsavannatomoddedbiomes")
						.define("addMansionSavannaToModdedBiomes", true));

				mansionTaigaMaxChunkDistance = subscriber.subscribe(builder
						.comment("Add Taiga Mansions to modded non-snowy Taiga biomes.")
						.translation("repurposedstructures.config.mansions.mansiontaigamaxchunkdistance")
						.defineInRange("mansionTaigaMaxChunkDistance", 140, 1, 1001));

				addMansionTaigaToModdedBiomes = subscriber.subscribe(builder
						.comment("Add Taiga Mansions to modded non-snowy Taiga biomes.")
						.translation("repurposedstructures.config.mansions.addmansiontaigatomoddedbiomes")
						.define("addMansionTaigaToModdedBiomes", true));

				mansionDesertMaxChunkDistance = subscriber.subscribe(builder
						.comment("Add Desert Mansions to modded Desert biomes.")
						.translation("repurposedstructures.config.mansions.mansiondesertmaxchunkdistance")
						.defineInRange("mansionDesertMaxChunkDistance", 140, 1, 1001));

				addMansionDesertToModdedBiomes = subscriber.subscribe(builder
						.comment("Add Desert Mansions to modded Desert biomes.")
						.translation("repurposedstructures.config.mansions.addmansiondeserttomoddedbiomes")
						.define("addMansionDesertToModdedBiomes", true));

				mansionSnowyMaxChunkDistance = subscriber.subscribe(builder
						.comment("Add Snowy Mansions to modded Snowy biomes.")
						.translation("repurposedstructures.config.mansions.mansionsnowymaxchunkdistance")
						.defineInRange("mansionSnowyMaxChunkDistance", 140, 1, 1001));

				addMansionSnowyToModdedBiomes = subscriber.subscribe(builder
						.comment("Add Snowy Mansions to modded Snowy biomes.")
						.translation("repurposedstructures.config.mansions.addmansionsnowytomoddedbiomes")
						.define("addMansionSnowyToModdedBiomes", true));

			builder.pop();
		}
	}
}
