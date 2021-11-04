package com.telepathicgrunt.repurposedstructures.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RSModdedLootConfig {
	public static final ForgeConfigSpec GENERAL_SPEC;
	public static ForgeConfigSpec.BooleanValue importModdedItems;
	public static ForgeConfigSpec.ConfigValue<String> blacklistedRSLoottablesFromImportingModdedItems;

	static {
		ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ForgeConfigSpec.Builder builder) {
		importModdedItems = builder
				.comment("Adds modded loot from vanilla structure's loot tables and injects them into Repurposed Structure's loot tables.",
						"Example: Snowy Pyramid gets all modded items that vanilla Desert Temple can have.")
				.translation("repurposedstructures.config.moddedloot.importmoddeditems")
				.define("importModdedItems", true);

		blacklistedRSLoottablesFromImportingModdedItems = builder
				.comment("Add the identifiers for Repurposed Structures's loottable you want to turn off the automatic modded item importing code for.",
						"Separate multiple entries with a comma.",
						"Example: \"repurposed_structures:chests/mansions/birch, repurposed_structures:chests/mineshafts/jungle\"")
				.translation("repurposedstructures.config.moddedloot.blacklistedrsloottablesfromimportingmoddeditems")
				.define("blacklistedRSLoottablesFromImportingModdedItems", "");
	}
}
