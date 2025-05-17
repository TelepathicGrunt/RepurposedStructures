package com.telepathicgrunt.repurposedstructures.forge.services;

import com.telepathicgrunt.repurposedstructures.services.PlatformService;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.jetbrains.annotations.Contract;

public class ForgePlatformService implements PlatformService {

    @Contract(pure = true)
    public boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }

    @Contract(pure = true)
    public boolean isDevEnvironment() {
        return !FMLEnvironment.production;
    }
}
