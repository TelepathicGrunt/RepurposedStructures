package com.telepathicgrunt.repurposedstructures;

import com.google.common.collect.ImmutableList;
import com.telepathicgrunt.repurposedstructures.configs.RSModdedLootConfig;
import com.telepathicgrunt.repurposedstructures.events.RegisterVillagerTradesEvent;
import com.telepathicgrunt.repurposedstructures.events.RegisterWanderingTradesEvent;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.RegisterReloadListenerEvent;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.ServerGoingToStartEvent;
import com.telepathicgrunt.repurposedstructures.events.lifecycle.ServerGoingToStopEvent;
import com.telepathicgrunt.repurposedstructures.misc.FabricReloadListener;
import com.telepathicgrunt.repurposedstructures.mixins.entities.VillagerTradesAccessor;
import com.telepathicgrunt.repurposedstructures.world.biomemodifiers.BiomeModifier;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RepurposedStructuresFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        RSModdedLootConfig.setup();
        RepurposedStructures.init();
        BiomeModifier.addFeatures();

        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            ServerGoingToStartEvent.EVENT.invoke(new ServerGoingToStartEvent(server));

            setupWanderingTrades();
            setupVillagerTrades();
        });

        ServerLifecycleEvents.SERVER_STOPPING.register(server ->
                ServerGoingToStopEvent.EVENT.invoke(ServerGoingToStopEvent.INSTANCE));

        RegisterReloadListenerEvent.EVENT.invoke(new RegisterReloadListenerEvent((id, listener) ->
                ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new FabricReloadListener(id, listener))));
    }

    private static void setupWanderingTrades() {
        List<Pair<VillagerTrades.ItemListing[], Integer>> trades = VillagerTrades.WANDERING_TRADER_TRADES;
        List<VillagerTrades.ItemListing> buying = Arrays.stream(trades.get(0).getKey()).collect(Collectors.toCollection(ArrayList::new));
        List<VillagerTrades.ItemListing> rare = Arrays.stream(trades.get(1).getKey()).collect(Collectors.toCollection(ArrayList::new));
        List<VillagerTrades.ItemListing> basic = Arrays.stream(trades.get(2).getKey()).collect(Collectors.toCollection(ArrayList::new));
        RegisterWanderingTradesEvent.EVENT.invoke(new RegisterWanderingTradesEvent(basic::add, rare::add, buying::add));
        VillagerTradesAccessor.setWANDERING_TRADER_TRADES(ImmutableList.<Pair<VillagerTrades.ItemListing[], Integer>>builder().add(
                Pair.of(buying.toArray(VillagerTrades.ItemListing[]::new), trades.get(0).getValue()),
                Pair.of(rare.toArray(VillagerTrades.ItemListing[]::new), trades.get(1).getValue()),
                Pair.of(basic.toArray(VillagerTrades.ItemListing[]::new), trades.get(2).getValue())
        ).build());
    }

    private static void setupVillagerTrades() {
        var trades = VillagerTrades.TRADES;
        for (var profession : BuiltInRegistries.VILLAGER_PROFESSION.registryKeySet()) {
            if (profession == null) continue;
            Int2ObjectMap<VillagerTrades.ItemListing[]> profTrades = trades.computeIfAbsent(profession, key -> new Int2ObjectOpenHashMap<>());
            Int2ObjectMap<List<VillagerTrades.ItemListing>> listings = new Int2ObjectOpenHashMap<>();
            for (int i = 1; i <= 5; i++) {
                if (profTrades.containsKey(i)) {
                    List<VillagerTrades.ItemListing> list = Arrays.stream(profTrades.get(i)).collect(Collectors.toList());
                    listings.put(i, list);
                } else {
                    listings.put(i, new ArrayList<>());
                }
            }
            RegisterVillagerTradesEvent.EVENT.invoke(new RegisterVillagerTradesEvent(profession, (i, listing) -> listings.get(i.intValue()).add(listing)));
            for (int i = 1; i <= 5; i++) {
                profTrades.put(i, listings.get(i).toArray(new VillagerTrades.ItemListing[0]));
            }
        }
    }
}
