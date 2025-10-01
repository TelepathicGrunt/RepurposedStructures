package com.telepathicgrunt.repurposedstructures.services.neoforge;

import com.telepathicgrunt.repurposedstructures.services.PlatformService;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;

public class NeoPlatformService implements PlatformService {

    @Override
    public boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }

    @Override
    public boolean isDevEnvironment() {
        return !FMLEnvironment.isProduction();
    }
}
