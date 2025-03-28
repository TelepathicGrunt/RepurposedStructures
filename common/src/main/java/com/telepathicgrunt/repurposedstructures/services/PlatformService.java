package com.telepathicgrunt.repurposedstructures.services;

import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import org.jetbrains.annotations.Contract;

public interface PlatformService {
    PlatformService INSTANCE = GeneralUtils.loadService(PlatformService.class);

    @Contract(pure = true)
    boolean isModLoaded(String modid);

    @Contract(pure = true)
    boolean isDevEnvironment();
}
