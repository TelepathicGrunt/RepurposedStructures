package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSTemplesConfig
{
	public static class RSTemplesConfigValues
	{
		public ConfigValueListener<String> blacklistedTempleBiomes;
		public ConfigValueListener<String> blacklistedPyramidBiomes;


		public ConfigValueListener<Boolean> addNetherWastelandTempleToModdedBiomes;
		public ConfigValueListener<Integer> netherWastelandTempleMaxChunkDistance;

		public ConfigValueListener<Boolean> addNetherBasaltTempleToModdedBiomes;
		public ConfigValueListener<Integer> netherBasaltTempleMaxChunkDistance;

		public ConfigValueListener<Boolean> addNetherCrimsonTempleToModdedBiomes;
		public ConfigValueListener<Integer> netherCrimsonTempleMaxChunkDistance;

		public ConfigValueListener<Boolean> addNetherWarpedTempleToModdedBiomes;
		public ConfigValueListener<Integer> netherWarpedTempleMaxChunkDistance;

		public ConfigValueListener<Boolean> addNetherSoulTempleToModdedBiomes;
		public ConfigValueListener<Integer> netherSoulTempleMaxChunkDistance;


		public ConfigValueListener<Boolean> addNetherPyramidToModdedBiomes;
		public ConfigValueListener<Integer> netherPyramidMaxChunkDistance;

		public ConfigValueListener<Boolean> addBadlandsPyramidToModdedBiomes;
		public ConfigValueListener<Integer> badlandsPyramidMaxChunkDistance;

		public ConfigValueListener<Integer> pyramidSnowyMaxChunkDistance;
		public ConfigValueListener<Boolean> addPyramidSnowyToModdedBiomes;

		public ConfigValueListener<Integer> pyramidEndMaxChunkDistance;
		public ConfigValueListener<Boolean> addPyramidEndToModdedBiomes;

		public ConfigValueListener<Integer> pyramidIcyMaxChunkDistance;
		public ConfigValueListener<Boolean> addPyramidIcyToModdedBiomes;

		// regexpos1

		public RSTemplesConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Temples");

				blacklistedTempleBiomes = subscriber.subscribe(builder
					.comment("\n Add the ID/resource location of the biome you don't want",
							" temples to spawn in. Separate each ID with a comma ,",
							"   Example: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
					.translation("repurposedstructures.config.temples.blacklistedtemplebiomes")
					.define("blacklistedTempleBiomes", " "));

				addNetherWastelandTempleToModdedBiomes = subscriber.subscribe(builder
					.comment("\n Add Nether Wasteland Temples to modded Nether biomes that other nether temples don't fit in.")
					.translation("repurposedstructures.config.temples.addnetherwastelandtempletomoddedbiomes")
					.define("addNetherWastelandTempleToModdedBiomes", true));

				netherWastelandTempleMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Nether Temples in Nether Wastelands.",
							 " 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.temples.netherwastelandtemplemaxchunkdistance")
					.defineInRange("netherWastelandTempleMaxChunkDistance", 27, 1, 1001));


				addNetherBasaltTempleToModdedBiomes = subscriber.subscribe(builder
					.comment("\n Add Nether Basalt Temples to modded Nether Basalt biomes.")
					.translation("repurposedstructures.config.temples.addnetherbasalttempletomoddedbiomes")
					.define("addNetherBasaltTempleToModdedBiomes", true));

				netherBasaltTempleMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Nether Basalt Temples in Nether Basalt Delta biomes.",
							 " 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.temples.netherbasalttemplemaxchunkdistance")
					.defineInRange("netherBasaltTempleMaxChunkDistance", 27, 1, 1001));


				addNetherCrimsonTempleToModdedBiomes = subscriber.subscribe(builder
					.comment("\n Add Nether Crimson Temples to modded Nether Crimson Forest biomes.")
					.translation("repurposedstructures.config.temples.addnethercrimsontempletomoddedbiomes")
					.define("addNetherCrimsonTempleToModdedBiomes", true));

				netherCrimsonTempleMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Nether Crimson Temples in Nether Crimson Forest.",
							 " 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.temples.nethercrimsontemplemaxchunkdistance")
					.defineInRange("netherCrimsonTempleMaxChunkDistance", 27, 1, 1001));


				addNetherWarpedTempleToModdedBiomes = subscriber.subscribe(builder
					.comment("\n Add Nether Warped Temples to modded Nether Warped Forest biomes.")
					.translation("repurposedstructures.config.temples.addnetherwarpedtempletomoddedbiomes")
					.define("addNetherWarpedTempleToModdedBiomes", true));

				netherWarpedTempleMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Nether Crimson Temples in Nether Warped Forest.",
							 " 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.temples.netherwarpedtemplemaxchunkdistance")
					.defineInRange("netherWarpedTempleMaxChunkDistance", 27, 1, 1001));


				addNetherSoulTempleToModdedBiomes = subscriber.subscribe(builder
					.comment("\n Add Nether Soul Temples to modded Nether Soul Sand Valley biomes.")
					.translation("repurposedstructures.config.temples.addnethersoultempletomoddedbiomes")
					.define("addNetherSoulTempleToModdedBiomes", true));

				netherSoulTempleMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Nether Soul Temples in Nether Soul Sand Valley.",
							 " 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.temples.nethersoultemplemaxchunkdistance")
					.defineInRange("netherSoulTempleMaxChunkDistance", 27, 1, 1001));

			builder.pop();

			builder.push("Pyramids");

				addNetherPyramidToModdedBiomes = subscriber.subscribe(builder
					.comment("\n Add Nether Pyramids to modded Nether biomes.")
					.translation("repurposedstructures.config.temples.addnetherpyramidtomoddedbiomes")
					.define("addNetherPyramidToModdedBiomes", true));

				blacklistedPyramidBiomes = subscriber.subscribe(builder
					.comment("\n Add the ID/resource location of the biome you don't want",
							" pyramids to spawn in. Separate each ID with a comma ,",
							"   Example: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
					.translation("repurposedstructures.config.temples.blacklistedpyramidbiomes")
					.define("blacklistedPyramidBiomes", " "));

				netherPyramidMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Nether Pyramids in Nether.",
							 " 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.temples.netherpyramidmaxchunkdistance")
					.defineInRange("netherPyramidMaxChunkDistance", 37, 1, 1001));


				addBadlandsPyramidToModdedBiomes = subscriber.subscribe(builder
					.comment("\n Add Badlands Pyramid to modded Badlands biomes.")
					.translation("repurposedstructures.config.temples.addbadlandspyramidtomoddedbiomes")
					.define("addBadlandsPyramidToModdedBiomes", true));

				badlandsPyramidMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Badlands Pyramid in non-plateau Badlands biomes.",
							 " 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.temples.badlandspyramidmaxchunkdistance")
					.defineInRange("badlandsPyramidMaxChunkDistance", 37, 1, 1001));

                pyramidSnowyMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are Snowy Pyramid in snowy biomes.",
							" 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.pyramids.pyramidsnowymaxchunkdistance")
                    .defineInRange("pyramidSnowyMaxChunkDistance", 37, 1, 1001));

                addPyramidSnowyToModdedBiomes = subscriber.subscribe(builder
                    .comment("\n Add Snowy Pyramid to modded snowy biomes.")
                    .translation("repurposedstructures.config.pyramids.addpyramidsnowytomoddedbiomes")
                    .define("addPyramidSnowyToModdedBiomes", true));

                pyramidEndMaxChunkDistance = subscriber.subscribe(builder
                    .comment("\n How rare are End Pyramid in End biomes.",
							" 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.pyramids.pyramidendmaxchunkdistance")
                    .defineInRange("pyramidEndMaxChunkDistance", 68, 1, 1001));

                addPyramidEndToModdedBiomes = subscriber.subscribe(builder
                    .comment("\n Add End Pyramid to modded end biomes.")
                    .translation("repurposedstructures.config.pyramids.addpyramidendtomoddedbiomes")
                    .define("addPyramidEndToModdedBiomes", true));

                pyramidIcyMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Icy Pyramid in super cold or icy biomes.",
							" 1 for spawning in most chunks and 1001 for none.")
                    .translation("repurposedstructures.config.pyramids.pyramidicymaxchunkdistance")
                    .defineInRange("pyramidIcyMaxChunkDistance", 37, 1, 1001));

                addPyramidIcyToModdedBiomes = subscriber.subscribe(builder
                    .comment("\n Add Icy Pyramid to modded icy biomes.")
                    .translation("repurposedstructures.config.pyramids.addpyramidicytomoddedbiomes")
                    .define("addPyramidIcyToModdedBiomes", true));

                
			// regexpos2

			builder.pop();
		}
	}
}
