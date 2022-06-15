package com.telepathicgrunt.repurposedstructures.configs;

import eu.midnightdust.lib.config.MidnightConfig;

public class RSModdedLootConfig extends MidnightConfig {

    @Entry
    public static boolean importModdedItems = true;

    @Entry
    public static String blacklistedRSLoottablesFromImportingModdedItems = "";
}