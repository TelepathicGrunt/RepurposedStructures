package com.telepathicgrunt.repurposedstructures.utils.neoforge;

import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import org.jetbrains.annotations.Contract;

public class PlatformHooksImpl {

    @Contract(pure = true)
    public static boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }

    @Contract(pure = true)
    public static boolean isDevEnvironment() {
        return !FMLEnvironment.production;
    }
}
