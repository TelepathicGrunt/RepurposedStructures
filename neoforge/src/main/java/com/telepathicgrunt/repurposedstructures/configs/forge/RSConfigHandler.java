package com.telepathicgrunt.repurposedstructures.configs.forge;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;

public class RSConfigHandler {

    public static void setup() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(RSConfigHandler::onConfigLoad);
        bus.addListener(RSConfigHandler::onConfigReload);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RSModdedLootConfig.GENERAL_SPEC, "repurposed_structures-forge/modded_loot.toml");
    }

    private static void onConfigLoad(ModConfigEvent.Loading event) {
        copyToCommon(event.getConfig().getSpec());
    }

    private static void onConfigReload(ModConfigEvent.Reloading event) {
        copyToCommon(event.getConfig().getSpec());
    }

    private static void copyToCommon(IConfigSpec<?> spec) {
        if (spec == RSModdedLootConfig.GENERAL_SPEC) RSModdedLootConfig.copyToCommon();
    }
}
