package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSOutpostsConfig
{
	public static class RSOutpostsConfigValues
	{
		public ConfigValueListener<Boolean> addNetherBrickOutpostToModdedBiomes;
		public ConfigValueListener<String> blacklistedOutpostBiomes;
		public ConfigValueListener<Integer> netherBrickOutpostMaxChunkDistance;
		public ConfigValueListener<Boolean> addWarpedOutpostToModdedBiomes;
		public ConfigValueListener<Integer> warpedOutpostMaxChunkDistance;
		public ConfigValueListener<Boolean> addCrimsonOutpostToModdedBiomes;
		public ConfigValueListener<Integer> crimsonOutpostMaxChunkDistance;
		public ConfigValueListener<Integer> outpostBirchMaxChunkDistance;
		public ConfigValueListener<Boolean> addOutpostBirchToModdedBiomes;
		public ConfigValueListener<Integer> outpostJungleMaxChunkDistance;
		public ConfigValueListener<Boolean> addOutpostJungleToModdedBiomes;
		public ConfigValueListener<Integer> outpostGiantTreeTaigaMaxChunkDistance;
		public ConfigValueListener<Boolean> addOutpostGiantTreeTaigaToModdedBiomes;
		public ConfigValueListener<Integer> outpostDesertMaxChunkDistance;
		public ConfigValueListener<Boolean> addOutpostDesertToModdedBiomes;
		public ConfigValueListener<Integer> outpostBadlandsMaxChunkDistance;
		public ConfigValueListener<Boolean> addOutpostBadlandsToModdedBiomes;
		public ConfigValueListener<Integer> outpostSnowyMaxChunkDistance;
		public ConfigValueListener<Boolean> addOutpostSnowyToModdedBiomes;
		public ConfigValueListener<Integer> outpostIcyMaxChunkDistance;
		public ConfigValueListener<Boolean> addOutpostIcyToModdedBiomes;
		public ConfigValueListener<Integer> outpostTaigaMaxChunkDistance;
		public ConfigValueListener<Boolean> addOutpostTaigaToModdedBiomes;
		public ConfigValueListener<Integer> outpostOakMaxChunkDistance;
		public ConfigValueListener<Boolean> addOutpostOakToModdedBiomes;
		public ConfigValueListener<Integer> outpostEndMaxChunkDistance;
		public ConfigValueListener<Boolean> addOutpostEndToModdedBiomes;

		public RSOutpostsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{

			blacklistedOutpostBiomes = subscriber.subscribe(builder
					.comment("\n Add the ID/resource location of the biome you don't want",
							" RS's outposts to spawn in. Separate each ID with a comma ,",
							"   Example: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
					.translation("repurposedstructures.config.outposts.blacklistedOutpostBiomes")
					.define("blacklistedOutpostBiomes", " "));

			builder.push("Outposts");

				addNetherBrickOutpostToModdedBiomes = subscriber.subscribe(builder
						.comment("\n How rare are Nether Brick Outposts in non-warped Nether biomes.",
								 " 1 for spawning in most chunks and 1001 for none.")
						.translation("repurposedstructures.config.outposts.addnetherbrickoutposttomoddedbiomes")
						.define("addNetherBrickOutpostToModdedBiomes", true));

				netherBrickOutpostMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n Add Nether Brick Outposts to modded Nether biomes",
								 " that other nether outposts don't fit in.")
						.translation("repurposedstructures.config.outposts.netherbrickoutpostmaxchunkdistance")
						.defineInRange("netherBrickOutpostMaxChunkDistance", 34, 1, 1001));


				addWarpedOutpostToModdedBiomes = subscriber.subscribe(builder
						.comment("\n How rare are Warped Outposts in Warped Nether biomes.",
								 " 1 for spawning in most chunks and 1001 for none.")
						.translation("repurposedstructures.config.outposts.addwarpedoutposttomoddedbiomes")
						.define("addWarpedOutpostToModdedBiomes", true));

				warpedOutpostMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n Add Warped Outposts to modded Nether Warped biomes.")
						.translation("repurposedstructures.config.outposts.warpedoutpostmaxchunkdistance")
						.defineInRange("warpedOutpostMaxChunkDistance", 34, 1, 1001));


				addCrimsonOutpostToModdedBiomes = subscriber.subscribe(builder
						.comment("\n How rare are Crimson Outposts in Warped Nether biomes.",
								 " 1 for spawning in most chunks and 1001 for none.")
						.translation("repurposedstructures.config.outposts.addcrimsonoutposttomoddedbiomes")
						.define("addCrimsonOutpostToModdedBiomes", true));

				crimsonOutpostMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n Add Crimson Outposts to modded Nether Warped biomes.")
						.translation("repurposedstructures.config.outposts.crimsonoutpostspawnrate")
						.defineInRange("crimsonOutpostMaxChunkDistance", 34, 1, 1001));


				outpostBirchMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n How rare are Birch Outposts in Birch Forest biomes.",
								 " 1 for spawning in most chunks and 1001 for none.")
						.translation("repurposedstructures.config.outposts.outpostbirchmaxchunkdistance")
						.defineInRange("outpostBirchMaxChunkDistance", 45, 1, 1001));

				addOutpostBirchToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add Birch Outposts to modded Birch biomes.")
						.translation("repurposedstructures.config.outposts.addoutpostbirchtomoddedbiomes")
						.define("addOutpostBirchToModdedBiomes", true));


				outpostJungleMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n How rare are Jungle Outposts in Jungle biomes.",
								 " 1 for spawning in most chunks and 1001 for none.")
						.translation("repurposedstructures.config.outposts.outpostjunglemaxchunkdistance")
						.defineInRange("outpostJungleMaxChunkDistance", 45, 1, 1001));

				addOutpostJungleToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add Jungle Outposts to modded Jungle biomes.")
						.translation("repurposedstructures.config.outposts.addoutpostjungletomoddedbiomes")
						.define("addOutpostJungleToModdedBiomes", true));


				outpostGiantTreeTaigaMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n How rare are Giant Tree Taiga Outposts in Giant Tree Taiga biomes.",
								 " 1 for spawning in most chunks and 1001 for none.")
						.translation("repurposedstructures.config.outposts.outpostgianttreetaigamaxchunkdistance")
						.defineInRange("outpostGiantTreeTaigaMaxChunkDistance", 45, 1, 1001));

				addOutpostGiantTreeTaigaToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add Giant Tree Taiga Outposts to modded Giant Tree Taiga biomes.")
						.translation("repurposedstructures.config.outposts.addoutpostgianttreetaigatomoddedbiomes")
						.define("addOutpostGiantTreeTaigaToModdedBiomes", true));



				outpostDesertMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n How rare are Desert Outposts in Desert biomes.",
								 " 1 for spawning in most chunks and 1001 for none.")
						.translation("repurposedstructures.config.outposts.outpostdesertmaxchunkdistance")
						.defineInRange("outpostDesertMaxChunkDistance", 45, 1, 1001));

				addOutpostDesertToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add Desert Outposts to modded Desert biomes.")
						.translation("repurposedstructures.config.outposts.addoutpostdeserttomoddedbiomes")
						.define("addOutpostDesertToModdedBiomes", true));



				outpostBadlandsMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n How rare are Badlands Outposts in Badlands biomes.",
								 " 1 for spawning in most chunks and 1001 for none.")
						.translation("repurposedstructures.config.outposts.outpostbadlandsmaxchunkdistance")
						.defineInRange("outpostBadlandsMaxChunkDistance", 45, 1, 1001));

				addOutpostBadlandsToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add Badlands Outposts to modded Badlands biomes.")
						.translation("repurposedstructures.config.outposts.addoutpostbadlandstomoddedbiomes")
						.define("addOutpostBadlandsToModdedBiomes", true));



				outpostSnowyMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n How rare are Snowy Outposts in snowy biomes.",
								 " 1 for spawning in most chunks and 1001 for none.")
						.translation("repurposedstructures.config.outposts.outpostsnowymaxchunkdistance")
						.defineInRange("outpostSnowyMaxChunkDistance", 45, 1, 1001));

				addOutpostSnowyToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add Snowy Outposts to modded snowy biomes.")
						.translation("repurposedstructures.config.outposts.addoutpostsnowytomoddedbiomes")
						.define("addOutpostSnowyToModdedBiomes", true));



				outpostIcyMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n How rare are Icy Outposts in icy/extremely cold biomes.",
								 " 1 for spawning in most chunks and 1001 for none.")
						.translation("repurposedstructures.config.outposts.outposticymaxchunkdistance")
						.defineInRange("outpostIcyMaxChunkDistance", 41, 1, 1001));

				addOutpostIcyToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add Icy Outposts to modded icy/extremely cold biomes.")
						.translation("repurposedstructures.config.outposts.addoutposticytomoddedbiomes")
						.define("addOutpostIcyToModdedBiomes", true));


				outpostOakMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n How rare Taiga Icy Outposts in forest biomes that are not birch or dark forest biomes.",
								" 1 for spawning in most chunks and 1001 for none.")
						.translation("repurposedstructures.config.outposts.outpostoakmaxchunkdistance")
						.defineInRange("outpostOakMaxChunkDistance", 45, 1, 1001));

				addOutpostOakToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add Oak Outposts to modded forest biomes that are not birch or dark forest.")
						.translation("repurposedstructures.config.outposts.addoutpostoaktomoddedbiomes")
						.define("addOutpostOakToModdedBiomes", true));


				outpostTaigaMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n How rare Taiga Icy Outposts in non-snowy and non-giant taiga biomes.",
								" 1 for spawning in most chunks and 1001 for none.")
						.translation("repurposedstructures.config.outposts.outposttaigamaxchunkdistance")
						.defineInRange("outpostTaigaMaxChunkDistance", 45, 1, 1001));

				addOutpostTaigaToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add Taiga Outposts to modded non-snowy and non-giant taiga biomes.")
						.translation("repurposedstructures.config.outposts.addoutposttaigatomoddedbiomes")
						.define("addOutpostTaigaToModdedBiomes", true));


				outpostEndMaxChunkDistance = subscriber.subscribe(builder
						.comment("\n How rare are End Outposts in End biomes.",
								" 1 for spawning in most chunks and 1001 for none.")
						.translation("repurposedstructures.config.outposts.outpostendmaxchunkdistance")
						.defineInRange("outpostEndMaxChunkDistance", 55, 1, 1001));

				addOutpostEndToModdedBiomes = subscriber.subscribe(builder
						.comment("\n Add End Outposts to modded End biomes.")
						.translation("repurposedstructures.config.outposts.addoutpostendtomoddedbiomes")
						.define("addOutpostEndToModdedBiomes", true));

			// regexpos2
			builder.pop();
		}
	}
}
