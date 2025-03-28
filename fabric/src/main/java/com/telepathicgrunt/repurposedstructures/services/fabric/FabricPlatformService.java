package com.telepathicgrunt.repurposedstructures.services.fabric;

import com.telepathicgrunt.repurposedstructures.services.PlatformService;
import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatformService implements PlatformService {

    @Override
    public boolean isModLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    @Override
    public boolean isDevEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
