package com.telepathicgrunt.repurposedstructures.misc;

import net.blay09.mods.waystones.config.WaystonesConfig;

public final class WaystonesBlaysCompat {
    private WaystonesBlaysCompat() {}

    /**
     * Done here to prevent classloading crashes by not calling this method if Waystones mod isn't on.
     */
    public static boolean waystonesVillageAndForcedSpawning() {
        return WaystonesConfig.COMMON.spawnInVillages.get() && WaystonesConfig.COMMON.forceSpawnInVillages.get();
    }
}
