package com.telepathicgrunt.repurposedstructures.misc;


import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Method;

public final class WaystonesFabricCompat {
    private WaystonesFabricCompat() {}

    /**
     * Done here to prevent classloading crashes by not calling this method if Waystones mod isn't on.
     */
    public static boolean waystonesVillageSpawning() {
        try {
            Class<?> waystoneClass = Class.forName("wraith.waystones.util.Config");
            Method method1 = waystoneClass.getMethod("getInstance");
            Object waystoneClassData = method1.invoke(null);
            Method method2 = waystoneClassData.getClass().getMethod("generateInVillages");
            return (Boolean)method2.invoke(waystoneClassData);
        }
        catch (Exception error) {
            LogManager.getLogger().error("Could not get Fabric Waystone's generateInVillages config value", error);
        }
        return false;
    }
}
