package com.telepathicgrunt.repurposedstructures.utils.fabric;

import org.jetbrains.annotations.Contract;
import org.quiltmc.loader.api.QuiltLoader;

public class PlatformHooksImpl {

    @Contract(pure = true)
    public static boolean isModLoaded(String modid) {
        return QuiltLoader.isModLoaded(modid);
    }

    @Contract(pure = true)
    public static boolean isDevEnvironment() {
        return QuiltLoader.isDevelopmentEnvironment();
    }
}
