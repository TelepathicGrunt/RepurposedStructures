package com.telepathicgrunt.repurposedstructures.misc.maptrades;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class StructureMapTradesEvents {
    private StructureMapTradesEvents() {}

    public static void onVillagerTradesEvent(VillagerTradesEvent event) {
        ResourceLocation currentVillager = ForgeRegistries.VILLAGER_PROFESSIONS.getKey(event.getType());
        if (currentVillager != null && RepurposedStructures.structureMapManager.VILLAGER_MAP_TRADES.containsKey(currentVillager.toString())) {
            for (VillagerMapObj mapTrade : RepurposedStructures.structureMapManager.VILLAGER_MAP_TRADES.get(currentVillager.toString())) {
                MapDecoration.Type icon;
                try {
                    icon = MapDecoration.Type.valueOf(mapTrade.mapIcon.toUpperCase(Locale.ROOT));
                }
                catch (Exception e) {
                    RepurposedStructures.LOGGER.error(e);
                    icon = MapDecoration.Type.MANSION;
                }

                event.getTrades().get(mapTrade.tradeLevel).add(new StructureSpecificMaps.TreasureMapForEmeralds(
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

    public static void onWandererTradesEvent(WandererTradesEvent event) {
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
                    event.getRareTrades().add(new StructureSpecificMaps.TreasureMapForEmeralds(
                            mapTrade.emeraldsRequired,
                            mapTrade.structure,
                            mapTrade.mapName,
                            icon,
                            mapTrade.tradesAllowed,
                            mapTrade.xpReward,
                            mapTrade.spawnRegionSearchRadius));
                }
                else if (tradeEntry.getKey() == WanderingTraderMapObj.TRADE_TYPE.COMMON) {
                    event.getGenericTrades().add(new StructureSpecificMaps.TreasureMapForEmeralds(
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
