package com.telepathicgrunt.repurposedstructures.configs.forge;

import com.telepathicgrunt.repurposedstructures.configs.RSMainModdedLootConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class RSModdedLootConfig {
	public static final ModConfigSpec GENERAL_SPEC;
	public static ModConfigSpec.BooleanValue importModdedItems;
	public static ModConfigSpec.ConfigValue<String> blacklistedRSLoottablesFromImportingModdedItems;

	static {
		ModConfigSpec.Builder configBuilder = new ModConfigSpec.Builder();
		setupConfig(configBuilder);
		GENERAL_SPEC = configBuilder.build();
	}

	private static void setupConfig(ModConfigSpec.Builder builder) {
		importModdedItems = builder
				.comment("Adds modded loot from vanilla structure's loot tables and injects them into Repurposed Structure's loot tables.",
						"Example: Snowy Pyramid gets all modded items that vanilla Desert Temple can have.")
				.translation("repurposedstructures.importmoddeditems")
				.define("importModdedItems", true);

		blacklistedRSLoottablesFromImportingModdedItems = builder
				.comment("Add the identifiers for Repurposed Structures's loottable you want to turn off the automatic modded item importing code for.",
						"Separate multiple entries with a comma.",
						"Example: \"repurposed_structures:chests/mansions/birch, repurposed_structures:chests/mineshafts/jungle\"")
				.translation("repurposedstructures.blacklistedrsloottablesfromimportingmoddeditems")
				.define("blacklistedRSLoottablesFromImportingModdedItems", "");
	}

	public static void copyToCommon() {
		RSMainModdedLootConfig.importModdedItems = importModdedItems.get();
		RSMainModdedLootConfig.blacklistedRSLoottablesFromImportingModdedItems = blacklistedRSLoottablesFromImportingModdedItems.get();
	}
}
