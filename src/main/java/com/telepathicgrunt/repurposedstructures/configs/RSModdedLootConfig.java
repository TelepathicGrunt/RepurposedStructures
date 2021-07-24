package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.configs.omegaconfig.api.Comment;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper;
import com.telepathicgrunt.repurposedstructures.utils.ConfigHelper.ConfigValueListener;
import net.minecraftforge.common.ForgeConfigSpec;

public class RSModdedLootConfig
{
	public static class RSModdedLootConfigValues
	{
		public ConfigValueListener<Boolean> importModdedItems;
		public ConfigValueListener<String> blacklistedRSLoottablesFromImportingModdedItems;
		
		public RSModdedLootConfigValues(ForgeConfigSpec.Builder builder, ConfigHelper.Subscriber subscriber)
		{
			importModdedItems = subscriber.subscribe(builder
					.comment("Adds modded loot from vanilla structure's loot tables and injects them into Repurposed Structure's loot tables.",
							"Example: Snowy Pyramid gets all modded items that vanilla Desert Temple can have.")
					.translation("repurposedstructures.config.moddedloot.importmoddeditems")
					.define("importModdedItems", true));

			blacklistedRSLoottablesFromImportingModdedItems = subscriber.subscribe(builder
					.comment("Add the identifiers for Repurposed Structures's loottable you want to turn off the automatic modded item importing code for.",
							"Separate multiple entries with a comma.",
							"Example: \"repurposed_structures:chests/mansions/birch, repurposed_structures:chests/mineshafts/jungle\"")
					.translation("repurposedstructures.config.moddedloot.blacklistedrsloottablesfromimportingmoddeditems")
					.define("blacklistedRSLoottablesFromImportingModdedItems", ""));
		}
	}
}
