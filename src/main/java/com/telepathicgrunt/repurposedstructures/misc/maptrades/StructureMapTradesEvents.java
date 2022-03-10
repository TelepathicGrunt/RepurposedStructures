package com.telepathicgrunt.repurposedstructures.misc.maptrades;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.saveddata.maps.MapDecoration;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class StructureMapTradesEvents {
    private StructureMapTradesEvents() {
    }

    public static void setupTradeEvent() {
        ServerLifecycleEvents.SERVER_STARTING.register((MinecraftServer minecraftServer) -> {
            forgeWandererEvent();
            forgeVillagerEvents();
        });
    }

    // copied how forge added trades at server startup unlike fabric api's adding trades at mod init.
    private static void forgeWandererEvent() {
        List<VillagerTrades.ItemListing> generic = NonNullList.create();
        List<VillagerTrades.ItemListing> rare = NonNullList.create();
        generic.addAll(Arrays.asList(VillagerTrades.WANDERING_TRADER_TRADES.get(1)));
        rare.addAll(Arrays.asList(VillagerTrades.WANDERING_TRADER_TRADES.get(2)));
        onWandererTradesEvent(generic, rare);
        VillagerTrades.WANDERING_TRADER_TRADES.put(1, generic.toArray(new VillagerTrades.ItemListing[0]));
        VillagerTrades.WANDERING_TRADER_TRADES.put(2, rare.toArray(new VillagerTrades.ItemListing[0]));
    }

    private static void forgeVillagerEvents() {
        for (Map.Entry<ResourceKey<VillagerProfession>, VillagerProfession> prof : Registry.VILLAGER_PROFESSION.entrySet()) {
            Int2ObjectMap<VillagerTrades.ItemListing[]> trades = VillagerTrades.TRADES.getOrDefault(prof.getValue(), new Int2ObjectOpenHashMap<>());
            Int2ObjectMap<List<VillagerTrades.ItemListing>> mutableTrades = new Int2ObjectOpenHashMap<>();
            for (int i = 1; i < 6; i++) {
                mutableTrades.put(i, NonNullList.create());
            }
            trades.int2ObjectEntrySet().forEach(e -> Arrays.stream(e.getValue()).forEach(mutableTrades.get(e.getIntKey())::add));
            onVillagerTradesEvent(mutableTrades, prof.getKey().location().toString());
            Int2ObjectMap<VillagerTrades.ItemListing[]> newTrades = new Int2ObjectOpenHashMap<>();
            mutableTrades.int2ObjectEntrySet().forEach(e -> newTrades.put(e.getIntKey(), e.getValue().toArray(new VillagerTrades.ItemListing[0])));
            VillagerTrades.TRADES.put(prof.getValue(), newTrades);
        }
    }

    public static void onVillagerTradesEvent(Int2ObjectMap<List<VillagerTrades.ItemListing>> trades, String type) {
        if (RepurposedStructures.structureMapManager.VILLAGER_MAP_TRADES.containsKey(type)) {
            for (VillagerMapObj mapTrade : RepurposedStructures.structureMapManager.VILLAGER_MAP_TRADES.get(type)) {
                MapDecoration.Type icon;
                try {
                    icon = MapDecoration.Type.valueOf(mapTrade.mapIcon.toUpperCase(Locale.ROOT));
                }
                catch (Exception e) {
                    RepurposedStructures.LOGGER.error(e);
                    icon = MapDecoration.Type.MANSION;
                }

                trades.get(mapTrade.tradeLevel).add(new StructureSpecificMaps.TreasureMapForEmeralds(
                        mapTrade.emeraldsRequired,
                        mapTrade.structure,
                        mapTrade.mapName,
                        icon,
                        mapTrade.tradesAllowed,
                        mapTrade.xpReward,
                        mapTrade.spawnRegionSearchRadius));
            }
        }
    }

    public static void onWandererTradesEvent(List<VillagerTrades.ItemListing> generic, List<VillagerTrades.ItemListing> rare) {
        for (Map.Entry<WanderingTraderMapObj.TRADE_TYPE, List<WanderingTraderMapObj>> tradeEntry : RepurposedStructures.structureMapManager.WANDERING_TRADER_MAP_TRADES.entrySet()) {
            for (WanderingTraderMapObj mapTrade : tradeEntry.getValue()) {
                MapDecoration.Type icon;
                try {
                    icon = MapDecoration.Type.valueOf(mapTrade.mapIcon.toUpperCase(Locale.ROOT));
                }
                catch (Exception e) {
                    RepurposedStructures.LOGGER.error(e);
                    icon = MapDecoration.Type.MANSION;
                }

                if (tradeEntry.getKey() == WanderingTraderMapObj.TRADE_TYPE.RARE) {
                    rare.add(new StructureSpecificMaps.TreasureMapForEmeralds(
                            mapTrade.emeraldsRequired,
                            mapTrade.structure,
                            mapTrade.mapName,
                            icon,
                            mapTrade.tradesAllowed,
                            mapTrade.xpReward,
                            mapTrade.spawnRegionSearchRadius));
                }
                else if (tradeEntry.getKey() == WanderingTraderMapObj.TRADE_TYPE.COMMON) {
                    generic.add(new StructureSpecificMaps.TreasureMapForEmeralds(
                            mapTrade.emeraldsRequired,
                            mapTrade.structure,
                            mapTrade.mapName,
                            icon,
                            mapTrade.tradesAllowed,
                            mapTrade.xpReward,
                            mapTrade.spawnRegionSearchRadius));
                }
            }
        }
    }
}
