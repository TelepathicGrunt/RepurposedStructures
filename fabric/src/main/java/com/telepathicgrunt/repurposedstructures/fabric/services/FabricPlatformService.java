package com.telepathicgrunt.repurposedstructures.fabric.services;

import com.telepathicgrunt.repurposedstructures.services.PlatformService;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.Contract;

public class FabricPlatformService implements PlatformService {

    @Contract(pure = true)
    public boolean isModLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    @Contract(pure = true)
    public boolean isDevEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
