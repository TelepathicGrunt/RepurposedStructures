package com.telepathicgrunt.repurposedstructures.configs.fabricbase;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import eu.midnightdust.lib.config.MidnightConfig;

public class ModMenuCompat implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> MidnightConfig.getScreen(parent, RepurposedStructures.MODID);
    }
}
