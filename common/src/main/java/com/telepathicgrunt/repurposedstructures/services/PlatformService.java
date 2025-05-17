package com.telepathicgrunt.repurposedstructures.services;

import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import org.apache.commons.lang3.NotImplementedException;

public interface PlatformService {

    PlatformService INSTANCE = GeneralUtils.loadService(PlatformService.class);

    default boolean isModLoaded(String modid) {
        throw new NotImplementedException("PlatformHooks isModLoaded is not implemented!");
    }

    default boolean isDevEnvironment() {
        throw new NotImplementedException("PlatformHooks isDevEnvironment is not implemented!");
    }
}
