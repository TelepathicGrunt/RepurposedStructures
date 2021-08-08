package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSStrongholdsConfig
{
	public static class RSStrongholdsConfigValues
	{
		public ConfigValueListener<Integer> netherStrongholdMaxChunkDistance;
		public ConfigValueListener<Integer> netherStrongholdMinHeight;
		public ConfigValueListener<Integer> netherStrongholdMaxHeight;
		public ConfigValueListener<Integer> netherStrongholdSize;

		public ConfigValueListener<Integer> strongholdEndMaxChunkDistance;
		public ConfigValueListener<Integer> strongholdEndMinHeight;
		public ConfigValueListener<Integer> strongholdEndMaxHeight;
		public ConfigValueListener<Integer> strongholdEndSize;

		// regexpos1
		
		public RSStrongholdsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			builder.push("Nether");

			netherStrongholdMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are Nether-styled Strongholds in Nether-category biomes.",
							" 1 for spawning in most chunks and 1001 for no spawn.")
					.translation("repurposedstructures.config.stronghold.netherstrongholdmaxChunkDistance")
					.defineInRange("netherStrongholdMaxChunkDistance", 85, 1, 1001));

			netherStrongholdSize = subscriber.subscribe(builder
					.comment("\n Size of Nether Stronghold. This number is how many pieces deep a branch can go from the center piece.",
							" 1 for supertiny and 30 for supermassive Strongholds.")
					.translation("repurposedstructures.config.stronghold.netherstrongholdsizesh")
					.defineInRange("strongholdSizeSH", 15, 1, 30));

			netherStrongholdMinHeight = subscriber.subscribe(builder
					.comment("\n Minimum Y height that Nether stronghold's starting point can spawn at.",
							" Note: Strongholds will spawn between min and max y height set in config.")
					.translation("repurposedstructures.config.feature.stronghold.netherstrongholdminheight")
					.defineInRange("netherStrongholdMinHeight", 10, 0, 255));

			netherStrongholdMaxHeight = subscriber.subscribe(builder
					.comment("\n Maximum Y height that Nether stronghold's starting point can spawn at.",
							" Note: Strongholds will spawn between min and max y height set in config.",
							" Setting this to below min height config will make strongholds spawn only at min height.")
					.translation("repurposedstructures.config.feature.stronghold.netherstrongholdmaxheight")
					.defineInRange("netherStrongholdMaxHeight", 31, 0, 255));

			builder.pop();

			builder.push("End");

			strongholdEndMaxChunkDistance = subscriber.subscribe(builder
					.comment("\nHow rare are End Strongholds in End biome's islands.",
							" 1 for spawning in most chunks and 10001 for none.")
					.translation("repurposedstructures.config.stronghold_end.strongholdendaveragechunkdistance")
					.defineInRange("strongholdEndAverageChunkDistance", 160 , 1, 1001));

			strongholdEndSize = subscriber.subscribe(builder
					.comment("\n Size of End Stronghold. This number is how many pieces deep a branch can go from the center piece.",
							" 1 for supertiny and 30 for supermassive Strongholds.")
					.translation("repurposedstructures.config.stronghold.endstrongholdsizesh")
					.defineInRange("strongholdSizeSH", 15, 1, 30));

			strongholdEndMinHeight = subscriber.subscribe(builder
					.comment("\n Minimum Y height that End stronghold's starting point can spawn at.",
							" Note: Strongholds will spawn between min and max y height set in config.")
					.translation("repurposedstructures.config.feature.stronghold.endstrongholdminheight")
					.defineInRange("netherStrongholdMinHeight", 5, 0, 255));

			strongholdEndMaxHeight = subscriber.subscribe(builder
					.comment("\n Maximum Y height that End stronghold's starting point can spawn at.",
							" Note: Strongholds will spawn between min and max y height set in config.",
							" Setting this to below min height config will make strongholds spawn only at min height.")
					.translation("repurposedstructures.config.feature.stronghold.endstrongholdmaxheight")
					.defineInRange("netherStrongholdMaxHeight", 6, 0, 255));

			builder.pop();
		}
	}
}
