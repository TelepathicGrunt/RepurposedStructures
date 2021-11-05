package com.telepathicgrunt.repurposedstructures.misc;


import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Method;

public final class WaystonesBlaysCompat {
    private WaystonesBlaysCompat() {}

    /**
     * Done here to match where forge version does blays waystones compat
     */
    public static boolean waystonesVillageAndForcedSpawning() {
        try {
            Class<?> waystoneClass = Class.forName("net.blay09.mods.waystones.config.WaystonesConfig");
            Method method1 = waystoneClass.getMethod("getActive");
            Object waystoneClassData = method1.invoke(null);
            Method method2 = waystoneClassData.getClass().getMethod("spawnInVillages");
            Method method3 = waystoneClassData.getClass().getMethod("forceSpawnInVillages");
            return (Boolean)method2.invoke(waystoneClassData) && (Boolean)method3.invoke(waystoneClassData);
        }
        catch (Exception error) {
            LogManager.getLogger().error("Could not get Blays Waystone's spawnInVillages or forceSpawnInVillages config value", error);
        }
        return false;
    }
}
