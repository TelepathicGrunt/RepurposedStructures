package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSRuinedPortalsConfig
{
	public static class RSRuinedPortalsConfigValues
	{

		public ConfigValueListener<Integer> ruinedPortalEndMaxChunkDistance;
		
		public RSRuinedPortalsConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			ruinedPortalEndMaxChunkDistance = subscriber.subscribe(builder
					.comment("\n How rare are End themed Ruined Portals in End category biomes. 1 for spawning in most",
							" chunks and 1001 for none.")
					.translation("repurposedstructures.config.ruinedPortals.ruinedportalendmaxchunkdistance")
					.defineInRange("ruinedPortalEndMaxChunkDistance", 57, 1, 1001));
		}
	}
}
