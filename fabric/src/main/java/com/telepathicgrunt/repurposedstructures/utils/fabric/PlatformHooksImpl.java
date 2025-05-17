package com.telepathicgrunt.repurposedstructures.utils.fabric;

import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.Contract;

public class PlatformHooksImpl {

    @Contract(pure = true)
    public static boolean isModLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    @Contract(pure = true)
    public static boolean isDevEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
