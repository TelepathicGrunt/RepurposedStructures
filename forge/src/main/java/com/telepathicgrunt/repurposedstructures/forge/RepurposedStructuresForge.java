package com.telepathicgrunt.repurposedstructures.forge;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.configs.RsConfigHandler;
import com.telepathicgrunt.repurposedstructures.events.RegisterVillagerTradesEvent;
import com.telepathicgrunt.repurposedstructures.events.RegisterWanderingTradesEvent;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.RegisterReloadListenerEvent;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.ServerGoingToStartEvent;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.ServerGoingToStopEvent;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.SetupEvent;
import com.telepathicgrunt.repurposedstructures.modinit.RSBiomeModifiers;
import com.telepathicgrunt.repurposedstructures.modinit.RSGlobalLootModifier;
import com.telepathicgrunt.repurposedstructures.modinit.registry.forge.ResourcefulRegistriesImpl;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(RepurposedStructures.MODID)
public class RepurposedStructuresForge {

    public RepurposedStructuresForge() {
        RsConfigHandler.setup();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(EventPriority.NORMAL, ResourcefulRegistriesImpl::onRegisterForgeRegistries);

        RepurposedStructures.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        RSBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);
        RSGlobalLootModifier.GLM.register(modEventBus);

        modEventBus.addListener(RepurposedStructuresForge::onSetup);
        modEventBus.addListener(RepurposedStructuresForge::onServerStarting);
        modEventBus.addListener(RepurposedStructuresForge::onServerStopping);
        modEventBus.addListener(RepurposedStructuresForge::onAddVillagerTrades);
        modEventBus.addListener(RepurposedStructuresForge::onWanderingTrades);
        modEventBus.addListener(RepurposedStructuresForge::onAddReloadListeners);
    }

    private static void onSetup(FMLCommonSetupEvent event) {
        SetupEvent.EVENT.invoke(new SetupEvent(event::enqueueWork));
        event.enqueueWork(RSGlobalLootModifier::registerLootData);
    }

    private static void onServerStarting(ServerStartingEvent event) {
        ServerGoingToStartEvent.EVENT.invoke(new ServerGoingToStartEvent(event.getServer()));
    }

    private static void onServerStopping(ServerStoppingEvent event) {
        ServerGoingToStopEvent.EVENT.invoke(ServerGoingToStopEvent.INSTANCE);
    }

    private static void onAddVillagerTrades(VillagerTradesEvent event) {
        RegisterVillagerTradesEvent.EVENT.invoke(new RegisterVillagerTradesEvent(event.getType(), (i, listing) -> event.getTrades().get(i.intValue()).add(listing)));
    }

    private static void onWanderingTrades(WandererTradesEvent event) {
        RegisterWanderingTradesEvent.EVENT.invoke(new RegisterWanderingTradesEvent(event.getGenericTrades()::add, event.getRareTrades()::add));
    }

    private static void onAddReloadListeners(AddReloadListenerEvent event) {
        RegisterReloadListenerEvent.EVENT.invoke(new RegisterReloadListenerEvent((id, listener) -> event.addListener(listener)));
    }

}
