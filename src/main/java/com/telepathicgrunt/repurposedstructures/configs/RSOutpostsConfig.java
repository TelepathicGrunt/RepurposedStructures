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
		public ConfigValueListener<Integer> netherBrickOutpostSpawnrate;
		public ConfigValueListener<Boolean> addWarpedOutpostToModdedBiomes;
		public ConfigValueListener<Integer> warpedOutpostSpawnrate;
		public ConfigValueListener<Boolean> addCrimsonOutpostToModdedBiomes;
		public ConfigValueListener<Integer> crimsonOutpostSpawnrate;

		public RSOutpostsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Outposts");

				addNetherBrickOutpostToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n How rare are Nether Brick Outposts in non-warped Nether biomes."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.outposts.addnetherbrickoutposttomoddedbiomes")
					.define("addNetherBrickOutpostToModdedBiomes", false));

				netherBrickOutpostSpawnrate = subscriber.subscribe(builder
					.comment("\r\n Add Nether Brick Outposts to modded Nether biomes"
							+ "\n that other nether outposts don't fit in.")
					.translation("repurposedstructures.config.outposts.netherbrickoutpostspawnrate")
					.defineInRange("netherBrickOutpostSpawnrate", 28, 1, 1001));


				addWarpedOutpostToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n How rare are Warped Outposts in Warped Nether biomes."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.outposts.addwarpedoutposttomoddedbiomes")
					.define("addWarpedOutpostToModdedBiomes", false));

				warpedOutpostSpawnrate = subscriber.subscribe(builder
					.comment("\r\n Add Warped Outposts to modded Nether Warped biomes.")
					.translation("repurposedstructures.config.outposts.warpedoutpostspawnrate")
					.defineInRange("warpedOutpostSpawnrate", 28, 1, 1001));


				addCrimsonOutpostToModdedBiomes = subscriber.subscribe(builder
					.comment("\r\n How rare are Crimson Outposts in Warped Nether biomes."
							+ "\n 1 for spawning in most chunks and 1001 for none.")
					.translation("repurposedstructures.config.outposts.addcrimsonoutposttomoddedbiomes")
					.define("addCrimsonOutpostToModdedBiomes", false));

				crimsonOutpostSpawnrate = subscriber.subscribe(builder
					.comment("\r\n Add Crimson Outposts to modded Nether Warped biomes.")
					.translation("repurposedstructures.config.outposts.crimsonoutpostspawnrate")
					.defineInRange("crimsonOutpostSpawnrate", 28, 1, 1001));

			builder.pop();
		}
	}
}
