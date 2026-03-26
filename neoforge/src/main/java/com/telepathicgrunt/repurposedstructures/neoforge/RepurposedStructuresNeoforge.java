package com.telepathicgrunt.repurposedstructures.neoforge;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.configs.neoforge.RSConfigHandler;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.RegisterReloadListenerEvent;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.ServerGoingToStartEvent;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.ServerGoingToStopEvent;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.SetupEvent;
import com.telepathicgrunt.repurposedstructures.modinit.neoforge.RSBiomeModifiers;
import com.telepathicgrunt.repurposedstructures.modinit.neoforge.RSGlobalLootModifier;
import com.telepathicgrunt.repurposedstructures.services.neoforge.NeoResourcefulRegistriesService;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddServerReloadListenersEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;


@Mod(RepurposedStructures.MODID)
public class RepurposedStructuresNeoforge {

    public static IEventBus modEventBusTempHolder = null;

    public RepurposedStructuresNeoforge(IEventBus modEventBus, ModContainer modContainer) {
        RSConfigHandler.setup(modEventBus, modContainer);
        modEventBus.addListener(EventPriority.NORMAL, NeoResourcefulRegistriesService::onRegisterForgeRegistries);

        modEventBusTempHolder = modEventBus;
        RepurposedStructures.init();
        modEventBusTempHolder = null;

        RSBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);
        RSGlobalLootModifier.GLM.register(modEventBus);
        RSGlobalLootModifier.LOOT_CONDITION_TYPE.register(modEventBus);

        modEventBus.addListener(RepurposedStructuresNeoforge::onSetup);

        IEventBus eventBus = NeoForge.EVENT_BUS;
        eventBus.addListener(RepurposedStructuresNeoforge::onServerStarting);
        eventBus.addListener(RepurposedStructuresNeoforge::onServerStopping);
        eventBus.addListener(RepurposedStructuresNeoforge::onAddReloadListeners);
    }

    private static void onSetup(FMLCommonSetupEvent event) {
        SetupEvent.EVENT.invoke(new SetupEvent(event::enqueueWork));
    }

    private static void onServerStarting(ServerAboutToStartEvent event) {
        ServerGoingToStartEvent.EVENT.invoke(new ServerGoingToStartEvent(event.getServer()));
    }

    private static void onServerStopping(ServerStoppingEvent event) {
        ServerGoingToStopEvent.EVENT.invoke(ServerGoingToStopEvent.INSTANCE);
    }

    private static void onAddReloadListeners(AddServerReloadListenersEvent event) {
        RegisterReloadListenerEvent.EVENT.invoke(new RegisterReloadListenerEvent(event::addListener));
    }

}
