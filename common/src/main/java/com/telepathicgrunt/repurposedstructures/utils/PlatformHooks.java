package com.telepathicgrunt.repurposedstructures.utils;

import dev.architectury.injectables.annotations.ExpectPlatform;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Contract;

public class PlatformHooks {

    @ExpectPlatform
    @Contract(pure = true)
    public static boolean isModLoaded(String modid) {
        throw new NotImplementedException("PlatformHooks isModLoaded is not implemented!");
    }

    @ExpectPlatform
    @Contract(pure = true)
    public static boolean isDevEnvironment() {
        throw new NotImplementedException("PlatformHooks isDevEnvironment is not implemented!");
    }
}
