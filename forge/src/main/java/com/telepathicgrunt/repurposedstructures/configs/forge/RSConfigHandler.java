package com.telepathicgrunt.repurposedstructures.configs.forge;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
