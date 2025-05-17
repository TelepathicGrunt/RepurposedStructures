package com.telepathicgrunt.repurposedstructures.utils.forge;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
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
