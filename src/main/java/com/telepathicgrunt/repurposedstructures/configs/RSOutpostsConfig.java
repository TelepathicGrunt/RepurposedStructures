package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
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

			builder.pop();
		}
	}
}
