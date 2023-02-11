package com.telepathicgrunt.repurposedstructures.compat;

import net.minecraftforge.fml.ModList;

public class CompatCommon {
    private static final String ASYNC_LOCATOR = "asynclocator";

    private static boolean isLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    public static boolean isAsyncLocatorLoaded() {
        return isLoaded(ASYNC_LOCATOR);
    }
}
