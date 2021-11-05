package com.telepathicgrunt.repurposedstructures.misc;


import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Method;

public final class WaystonesCompat {
    private WaystonesCompat() {}

    /**
     * Done here to prevent classloading crashes by not calling this method if Waystones mod isn't on.
     */
    public static boolean waystonesForcedSpawning() {
        try {
            Class<?> waystoneClass = Class.forName("net.blay09.mods.waystones.config.WaystonesConfig");
            Method method1 = waystoneClass.getMethod("getActive");
            Object waystoneClassData = method1.invoke(null);
            Method method2 = waystoneClassData.getClass().getMethod("forceSpawnInVillages");
            return (Boolean)method2.invoke(waystoneClassData);
        }
        catch (Exception error) {
            LogManager.getLogger().error("Could not get Waystone's forceSpawnInVillages config value", error);
        }
        return false;
    }
}
