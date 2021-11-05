package com.telepathicgrunt.repurposedstructures.misc;

import net.blay09.mods.waystones.config.WaystonesConfig;

public final class WaystonesCompat {
    private WaystonesCompat() {}

    /**
     * Done here to prevent classloading crashes by not calling this method if Waystones mod isn't on.
     */
    public static boolean waystonesForcedSpawning() {
        return WaystonesConfig.COMMON.forceSpawnInVillages.get();
    }
}
