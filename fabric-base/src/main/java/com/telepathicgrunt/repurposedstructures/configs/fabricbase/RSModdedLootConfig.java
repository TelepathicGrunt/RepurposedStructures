package com.telepathicgrunt.repurposedstructures.configs.fabricbase;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.configs.RSMainModdedLootConfig;
import eu.midnightdust.lib.config.MidnightConfig;
import org.jetbrains.annotations.ApiStatus;

public class RSModdedLootConfig extends MidnightConfig {

    @Entry
    public static boolean importModdedItems = true;

    @Entry
    public static String blacklistedRSLoottablesFromImportingModdedItems = "";

    @ApiStatus.Internal
    public static void setup() {
        MidnightConfig.init(RepurposedStructures.MODID, RSModdedLootConfig.class);
        copyConfigsToCommon();
    }

    /**
     * This is used to have a 'common' config in the common project but custom configs on both sides.
     */
    @ApiStatus.Internal
    public static void copyConfigsToCommon() {
        RSMainModdedLootConfig.importModdedItems = importModdedItems;
        RSMainModdedLootConfig.blacklistedRSLoottablesFromImportingModdedItems = blacklistedRSLoottablesFromImportingModdedItems;
    }
}