package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class RSTemplesConfig
{
	public static class RSTemplesConfigValues
	{
		public ConfigValueListener<Boolean> addNetherWastelandTempleToModdedBiomes;
		public ConfigValueListener<Integer> netherWastelandTempleSpawnrate;

		public ConfigValueListener<Boolean> addNetherBasaltTempleToModdedBiomes;
		public ConfigValueListener<Integer> netherBasaltTempleSpawnrate;

		public ConfigValueListener<Boolean> addNetherCrimsonTempleToModdedBiomes;
		public ConfigValueListener<Integer> netherCrimsonTempleSpawnrate;

		public ConfigValueListener<Boolean> addNetherWarpedTempleToModdedBiomes;
		public ConfigValueListener<Integer> netherWarpedTempleSpawnrate;

		public ConfigValueListener<Boolean> addNetherSoulTempleToModdedBiomes;
		public ConfigValueListener<Integer> netherSoulTempleSpawnrate;

		public ConfigValueListener<Boolean> addNetherPyramidToModdedBiomes;
		public ConfigValueListener<Integer> netherPyramidSpawnrate;

		public ConfigValueListener<Boolean> addBadlandsPyramidToModdedBiomes;
		public ConfigValueListener<Integer> badlandsPyramidSpawnrate;

		public ConfigValueListener<String> blacklistedTempleBiomes;
		public ConfigValueListener<String> blacklistedPyramidBiomes;

		public RSTemplesConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Temples");

				blacklistedTempleBiomes = subscriber.subscribe(builder
					.comment("\r\n Add the ID/resource location of the biome you don't want"
							+"\r\n temples to spawn in. Separate each ID with a comma ,"
							+"\r\n"
							+"\r\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
					.translation("repurposedstructures.config.temples.blacklistedtemplebiomes")
					.define("blacklistedTempleBiomes", ""));

				addNetherWastelandTempleToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n Add Nether Wasteland Temples to modded Nether biomes"
							+ "\n that other nether temples don't fit in.")
					.translation("repurposedstructures.config.temples.addnetherwastelandtempletomoddedbiomes")
					.define("addNetherWastelandTempleToModdedBiomes", false));

				netherWastelandTempleSpawnrate = subscriber.subscribe(builder
					.comment("\r\n How rare are Nether Temples in Nether Wastelands."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.temples.netherwastelandtemplespawnrate")
					.defineInRange("netherWastelandTempleSpawnrate", 29, 1, 1001));


				addNetherBasaltTempleToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n Add Nether Basalt Temples to modded Nether Basalt biomes.")
					.translation("repurposedstructures.config.temples.addnetherbasalttempletomoddedbiomes")
					.define("addNetherBasaltTempleToModdedBiomes", false));

				netherBasaltTempleSpawnrate = subscriber.subscribe(builder
					.comment("\r\n How rare are Nether Basalt Temples in Nether Basalt Delta biomes."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.temples.netherbasalttemplespawnrate")
					.defineInRange("netherBasaltTempleSpawnrate", 29, 1, 1001));


				addNetherCrimsonTempleToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n Add Nether Crimson Temples to modded Nether Crimson Forest biomes.")
					.translation("repurposedstructures.config.temples.addnethercrimsontempletomoddedbiomes")
					.define("addNetherCrimsonTempleToModdedBiomes", false));

				netherCrimsonTempleSpawnrate = subscriber.subscribe(builder
					.comment("\r\n How rare are Nether Crimson Temples in Nether Crimson Forest."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.temples.nethercrimsontemplespawnrate")
					.defineInRange("netherCrimsonTempleSpawnrate", 29, 1, 1001));


				addNetherWarpedTempleToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n Add Nether Warped Temples to modded Nether Warped Forest biomes.")
					.translation("repurposedstructures.config.temples.addnetherwarpedtempletomoddedbiomes")
					.define("addNetherWarpedTempleToModdedBiomes", false));

				netherWarpedTempleSpawnrate = subscriber.subscribe(builder
					.comment("\r\n How rare are Nether Crimson Temples in Nether Warped Forest."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.temples.netherwarpedtemplespawnrate")
					.defineInRange("netherWarpedTempleSpawnrate", 29, 1, 1001));


				addNetherSoulTempleToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n Add Nether Soul Temples to modded Nether Soul Sand Valley biomes.")
					.translation("repurposedstructures.config.temples.addnethersoultempletomoddedbiomes")
					.define("addNetherSoulTempleToModdedBiomes", false));

				netherSoulTempleSpawnrate = subscriber.subscribe(builder
					.comment("\r\n How rare are Nether Soul Temples in Nether Soul Sand Valley."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.temples.nethersoultemplespawnrate")
					.defineInRange("netherSoulTempleSpawnrate", 29, 1, 1001));

			builder.pop();

			builder.push("Pyramids");

				addNetherPyramidToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n Add Nether Pyramids to modded Nether biomes.")
					.translation("repurposedstructures.config.temples.addnetherpyramidtomoddedbiomes")
					.define("addNetherPyramidToModdedBiomes", false));

				blacklistedPyramidBiomes = subscriber.subscribe(builder
					.comment("\r\n Add the ID/resource location of the biome you don't want"
							+"\r\n pyramids to spawn in. Separate each ID with a comma ,"
							+"\r\n"
							+"\r\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
					.translation("repurposedstructures.config.temples.blacklistedpyramidbiomes")
					.define("blacklistedPyramidBiomes", ""));

				netherPyramidSpawnrate = subscriber.subscribe(builder
					.comment("\r\n How rare are Nether Pyramids in Nether."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.temples.netherpyramidspawnrate")
					.defineInRange("netherPyramidSpawnrate", 37, 1, 1001));


				addBadlandsPyramidToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n Add Badlands Pyramid to modded Badlands biomes.")
					.translation("repurposedstructures.config.temples.addbadlandspyramidtomoddedbiomes")
					.define("addBadlandsPyramidToModdedBiomes", false));

				badlandsPyramidSpawnrate = subscriber.subscribe(builder
					.comment("\r\n How rare are Badlands Pyramid in non-plateau Badlands biomes."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.temples.badlandspyramidspawnrate")
					.defineInRange("badlandsPyramidSpawnrate", 20, 1, 1001));

			builder.pop();
		}
	}
}
