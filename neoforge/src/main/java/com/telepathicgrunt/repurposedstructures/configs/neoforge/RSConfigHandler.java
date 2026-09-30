package com.telepathicgrunt.repurposedstructures.configs.neoforge;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;

public class RSConfigHandler {

    public static void setup(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(RSConfigHandler::onConfigLoad);
        modEventBus.addListener(RSConfigHandler::onConfigReload);

        modContainer.registerConfig(ModConfig.Type.LOCAL, RSModdedLootConfig.GENERAL_SPEC, "repurposed_structures-neoforge/modded_loot.toml");
    }

    private static void onConfigLoad(ModConfigEvent.Loading event) {
        copyToCommon(event.getConfig().getSpec());
    }

    private static void onConfigReload(ModConfigEvent.Reloading event) {
        copyToCommon(event.getConfig().getSpec());
    }

    private static void copyToCommon(IConfigSpec spec) {
        if (spec == RSModdedLootConfig.GENERAL_SPEC) RSModdedLootConfig.copyToCommon();
    }
}
