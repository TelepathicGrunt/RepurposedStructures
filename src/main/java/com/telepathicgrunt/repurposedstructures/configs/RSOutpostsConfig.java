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

		public RSOutpostsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{

			blacklistedOutpostBiomes = subscriber.subscribe(builder
					.comment("\r\n Add the ID/resource location of the biome you don't want"
							+"\r\n RS's outposts to spawn in. Separate each ID with a comma ,"
							+"\r\n"
							+"\r\nExample: \"minecraft:ice_spikes,awesome_mod:awesome_biome\"")
					.translation("repurposedstructures.config.outposts.blacklistedOutpostBiomes")
					.define("blacklistedOutpostBiomes", ""));

			builder.push("Outposts");

				addNetherBrickOutpostToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n How rare are Nether Brick Outposts in non-warped Nether biomes."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.outposts.addnetherbrickoutposttomoddedbiomes")
					.define("addNetherBrickOutpostToModdedBiomes", true));

				netherBrickOutpostMaxChunkDistance = subscriber.subscribe(builder
					.comment("\r\n Add Nether Brick Outposts to modded Nether biomes"
							+ "\n that other nether outposts don't fit in.")
					.translation("repurposedstructures.config.outposts.netherbrickoutpostmaxchunkdistance")
					.defineInRange("netherBrickOutpostMaxChunkDistance", 34, 1, 1001));


				addWarpedOutpostToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n How rare are Warped Outposts in Warped Nether biomes."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.outposts.addwarpedoutposttomoddedbiomes")
					.define("addWarpedOutpostToModdedBiomes", true));

				warpedOutpostMaxChunkDistance = subscriber.subscribe(builder
					.comment("\r\n Add Warped Outposts to modded Nether Warped biomes.")
					.translation("repurposedstructures.config.outposts.warpedoutpostmaxchunkdistance")
					.defineInRange("warpedOutpostMaxChunkDistance", 34, 1, 1001));


				addCrimsonOutpostToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n How rare are Crimson Outposts in Warped Nether biomes."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.outposts.addcrimsonoutposttomoddedbiomes")
					.define("addCrimsonOutpostToModdedBiomes", true));

				crimsonOutpostMaxChunkDistance = subscriber.subscribe(builder
					.comment("\r\n Add Crimson Outposts to modded Nether Warped biomes.")
					.translation("repurposedstructures.config.outposts.crimsonoutpostspawnrate")
					.defineInRange("crimsonOutpostMaxChunkDistance", 34, 1, 1001));


				outpostBirchMaxChunkDistance = subscriber.subscribe(builder
					.comment("How rare are Birch Outposts in Birch Forest"
							+ "\nbiomes. 1 for spawning in most chunks and 1001"
							+ "\nfor none.")
					.translation("repurposedstructures.config.outposts.outpostbirchmaxchunkdistance")
					.defineInRange("outpostBirchMaxChunkDistance", 43, 1, 1001));

				addOutpostBirchToModdedBiomes = subscriber.subscribe(builder
					.comment("Add Birch Outposts to modded Birch biomes.")
					.translation("repurposedstructures.config.outposts.addoutpostbirchtomoddedbiomes")
					.define("addOutpostBirchToModdedBiomes", true));


			outpostJungleMaxChunkDistance = subscriber.subscribe(builder
					.comment("How rare are Jungle Outposts in Jungle"
							+ "\nbiomes. 1 for spawning in most chunks and"
							+ "\n1001 for none.")
					.translation("repurposedstructures.config.outposts.outpostjunglemaxchunkdistance")
					.defineInRange("outpostJungleMaxChunkDistance", 43, 1, 1001));

			addOutpostJungleToModdedBiomes = subscriber.subscribe(builder
					.comment("Add Jungle Outposts to modded Jungle biomes.")
					.translation("repurposedstructures.config.outposts.addoutpostjungletomoddedbiomes")
					.define("addOutpostJungleToModdedBiomes", true));


			outpostGiantTreeTaigaMaxChunkDistance = subscriber.subscribe(builder
					.comment("How rare are Giant Tree Taiga Outposts in Giant Tree Taiga"
							+ "\nbiomes. 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.outposts.outpostgianttreetaigamaxchunkdistance")
					.defineInRange("outpostGiantTreeTaigaMaxChunkDistance", 43, 1, 1001));

			addOutpostGiantTreeTaigaToModdedBiomes = subscriber.subscribe(builder
					.comment("Add Giant Tree Taiga Outposts to modded Giant Tree Taiga biomes.")
							.translation("repurposedstructures.config.outposts.addoutpostgianttreetaigatomoddedbiomes")
							.define("addOutpostGiantTreeTaigaToModdedBiomes", true));



			outpostDesertMaxChunkDistance = subscriber.subscribe(builder
					.comment("How rare are Desert Outposts in Desert"
							+ "\nbiomes. 1 for spawning in most chunks and"
							+ "\n1001 for none.")
					.translation("repurposedstructures.config.outposts.outpostdesertmaxchunkdistance")
					.defineInRange("outpostDesertMaxChunkDistance", 43, 1, 1001));

			addOutpostDesertToModdedBiomes = subscriber.subscribe(builder
					.comment("Add Desert Outposts to modded Desert biomes.")
							.translation("repurposedstructures.config.outposts.addoutpostdeserttomoddedbiomes")
							.define("addOutpostDesertToModdedBiomes", true));



			outpostBadlandsMaxChunkDistance = subscriber.subscribe(builder
					.comment("How rare are Badlands Outposts in Badlands"
							+ "\nbiomes. 1 for spawning in most chunks and"
							+ "\n1001 for none.")
					.translation("repurposedstructures.config.outposts.outpostbadlandsmaxchunkdistance")
					.defineInRange("outpostBadlandsMaxChunkDistance", 41, 1, 1001));

			addOutpostBadlandsToModdedBiomes = subscriber.subscribe(builder
					.comment("Add Badlands Outposts to modded Badlands biomes.")
							.translation("repurposedstructures.config.outposts.addoutpostbadlandstomoddedbiomes")
							.define("addOutpostBadlandsToModdedBiomes", true));



			outpostSnowyMaxChunkDistance = subscriber.subscribe(builder
					.comment("How rare are Snowy Outposts in snowy"
							+ "\nbiomes. 1 for spawning in most chunks and"
							+ "\n1001 for none.")
					.translation("repurposedstructures.config.outposts.outpostsnowymaxchunkdistance")
					.defineInRange("outpostSnowyMaxChunkDistance", 43, 1, 1001));

			addOutpostSnowyToModdedBiomes = subscriber.subscribe(builder
					.comment("Add Snowy Outposts to modded snowy biomes.")
					.translation("repurposedstructures.config.outposts.addoutpostsnowytomoddedbiomes")
					.define("addOutpostSnowyToModdedBiomes", true));



			outpostIcyMaxChunkDistance = subscriber.subscribe(builder
					.comment("How rare are Icy Outposts in icy/extremely"
							+ "\ncold biomes. 1 for spawning in most chunks"
							+ "\nand 1001 for none.")
					.translation("repurposedstructures.config.outposts.outposticymaxchunkdistance")
					.defineInRange("outpostIcyMaxChunkDistance", 41, 1, 1001));

			addOutpostIcyToModdedBiomes = subscriber.subscribe(builder
					.comment("Add Icy Outposts to modded icy/extremely"
							+ "\ncold biomes.")
					.translation("repurposedstructures.config.outposts.addoutposticytomoddedbiomes")
					.define("addOutpostIcyToModdedBiomes", true));


			outpostOakMaxChunkDistance = subscriber.subscribe(builder
					.comment("Add Oak Outposts to modded forest biomes that are"
							+ "\nnot birch or dark forest.")
					.translation("repurposedstructures.config.outposts.outpostoakmaxchunkdistance")
					.defineInRange("outpostOakMaxChunkDistance", 43, 1, 1001));

			addOutpostOakToModdedBiomes = subscriber.subscribe(builder
					.comment("Add Oak Outposts to modded forest biomes that are"
							+ "\nnot birch or dark forest.")
					.translation("repurposedstructures.config.outposts.addoutpostoaktomoddedbiomes")
					.define("addOutpostOakToModdedBiomes", true));


			outpostTaigaMaxChunkDistance = subscriber.subscribe(builder
					.comment("Add Taiga Outposts to modded non-snowy and"
							+ "\nnon-giant taiga biomes.")
					.translation("repurposedstructures.config.outposts.outposttaigamaxchunkdistance")
					.defineInRange("outpostTaigaMaxChunkDistance", 43, 1, 1001));

			addOutpostTaigaToModdedBiomes = subscriber.subscribe(builder
					.comment("Add Taiga Outposts to modded non-snowy and"
							+ "\nnon-giant taiga biomes.")
					.translation("repurposedstructures.config.outposts.addoutposttaigatomoddedbiomes")
					.define("addOutpostTaigaToModdedBiomes", true));

			builder.pop();
		}
	}
}
