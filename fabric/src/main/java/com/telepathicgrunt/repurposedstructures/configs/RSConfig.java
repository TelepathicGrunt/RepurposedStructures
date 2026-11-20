package com.telepathicgrunt.repurposedstructures.configs;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import eu.midnightdust.lib.config.MidnightConfig;
import org.jetbrains.annotations.ApiStatus;

public class RSConfig extends MidnightConfig {

    @Entry
    public static boolean disableLogspamHiding = false;

    @ApiStatus.Internal
    public static void setup() {
        MidnightConfig.init(RepurposedStructures.MODID, RSConfig.class);
        copyConfigsToCommon();
    }

    /**
     * This is used to have a 'common' config in the common project but custom configs on both sides.
     */
    @ApiStatus.Internal
    public static void copyConfigsToCommon() {
        RSMainConfig.disableLogspamHiding = disableLogspamHiding;
    }
}