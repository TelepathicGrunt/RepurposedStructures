package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import org.apache.commons.lang3.EnumUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public final class MobMapTrades {
    private MobMapTrades() {}


    public static final Map<VillagerProfession, List<MapTradeFinalized>> VILLAGER_MAP_TRADES = new HashMap<>();
    public static final Map<WandingTraderTradeEntry.TRADE_TYPE, List<MapTradeFinalized>> WANDERING_TRADER_MAP_TRADES = new HashMap<>();

    public static void setupMapTrades() {
        setupVillagerMap(RepurposedStructures.omegaMapTradeConfig.villagerMapTrades);
        setupWanderingTraderMap(RepurposedStructures.omegaMapTradeConfig.wanderingTraderMapTrades);
    }

    public static record MapTradeFinalized(StructureFeature<?> structureFeature, MapDecoration.Type mapIcon, int tradeLevel, int emeraldsRequired, int tradesAllowed, int xpReward) { }

    // Needed so that config api can handle reading/writing the entry to and from file.
    public static class VillagerTradeEntry {
        public final String structure;
        public final String mapIcon;
        public final int tradeLevel;
        public final int emeraldsRequired;
        public final int tradesAllowed;
        public final int xpReward;

        public VillagerTradeEntry(String structure, String mapIcon, int tradeLevel, int emeraldsRequired, int tradesAllowed, int xpReward) {
            this.structure = structure;
            this.mapIcon = mapIcon;
            this.tradeLevel = tradeLevel;
            this.emeraldsRequired = emeraldsRequired;
            this.tradesAllowed = tradesAllowed;
            this.xpReward = xpReward;
        }
    }

    // Needed so that config api can handle reading/writing the entry to and from file.
    public static class WandingTraderTradeEntry {
        public enum TRADE_TYPE {
            RARE,
            COMMON
        }

        public final String structure;
        public final String mapIcon;
        public final int emeraldsRequired;
        public final int tradesAllowed;
        public final int xpReward;

        public WandingTraderTradeEntry(String structure, String mapIcon, int emeraldsRequired, int tradesAllowed, int xpReward) {
            this.structure = structure;
            this.mapIcon = mapIcon;
            this.emeraldsRequired = emeraldsRequired;
            this.tradesAllowed = tradesAllowed;
            this.xpReward = xpReward;
        }
    }

    private static void setupVillagerMap(Map<String, List<VillagerTradeEntry>> configMap) {

        for(Map.Entry<String, List<VillagerTradeEntry>> configMapEntry : configMap.entrySet()) {

            Optional<VillagerProfession> villagerProfession = Registry.VILLAGER_PROFESSION.getOptional(new ResourceLocation(configMapEntry.getKey()));
            if(villagerProfession.isEmpty()) {
                RepurposedStructures.LOGGER.warn("Repurposed Structures: Unknown key {} was found in the {} config. Skipping that entry...", configMapEntry.getKey(), "villager map trades");
                continue;
            }

            for(VillagerTradeEntry villagerTradeEntry : configMapEntry.getValue()) {
                // Parse and make sure the entity type exists
                StructureFeature<?> structureFeature = Registry.STRUCTURE_FEATURE.get((new ResourceLocation(villagerTradeEntry.structure)));
                if(structureFeature == null) {
                    RepurposedStructures.LOGGER.warn("Repurposed Structures (second): Unknown Structure {} was found in the {} config. Skipping that entry...", villagerTradeEntry.structure, "villager map trades");
                    continue;
                }

                MapDecoration.Type mapIcon = EnumUtils.getEnum(MapDecoration.Type.class, villagerTradeEntry.mapIcon.toUpperCase(Locale.ROOT));
                if(mapIcon == null) {
                    mapIcon = MapDecoration.Type.MANSION;
                }

                MapTradeFinalized finalizedTrade = new MapTradeFinalized(
                        structureFeature,
                        mapIcon,
                        villagerTradeEntry.tradeLevel,
                        villagerTradeEntry.emeraldsRequired,
                        villagerTradeEntry.tradesAllowed,
                        villagerTradeEntry.xpReward);

                if(!MobMapTrades.VILLAGER_MAP_TRADES.containsKey(villagerProfession.get())) {
                    MobMapTrades.VILLAGER_MAP_TRADES.put(villagerProfession.get(), new ArrayList<>());
                }
                MobMapTrades.VILLAGER_MAP_TRADES.get(villagerProfession.get()).add(finalizedTrade);
            }
        }
    }

    private static void setupWanderingTraderMap(Map<String, List<WandingTraderTradeEntry>> configMap) {

        for(Map.Entry<String, List<WandingTraderTradeEntry>> configMapEntry : configMap.entrySet()) {

            WandingTraderTradeEntry.TRADE_TYPE tradeType = EnumUtils.getEnum(WandingTraderTradeEntry.TRADE_TYPE.class, configMapEntry.getKey());
            if(tradeType == null) {
                RepurposedStructures.LOGGER.warn("Repurposed Structures: Unknown key {} was found in the {} config. Skipping that entry...", configMapEntry.getKey(), "wandering trader map trades");
                continue;
            }

            for(WandingTraderTradeEntry wandingTraderTradeEntry : configMapEntry.getValue()) {
                // Parse and make sure the entity type exists
                StructureFeature<?> structureFeature = Registry.STRUCTURE_FEATURE.get(new ResourceLocation(wandingTraderTradeEntry.structure));
                if(structureFeature == null) {
                    RepurposedStructures.LOGGER.warn("Repurposed Structures (second): Unknown Structure {} was found in the {} config. Skipping that entry...", wandingTraderTradeEntry.structure, "wandering trader map trades");
                    continue;
                }

                MapDecoration.Type mapIcon = EnumUtils.getEnum(MapDecoration.Type.class, wandingTraderTradeEntry.mapIcon.toUpperCase(Locale.ROOT));
                if(mapIcon == null) {
                    mapIcon = MapDecoration.Type.MANSION;
                }

                MapTradeFinalized finalizedTrade = new MapTradeFinalized(
                        structureFeature,
                        mapIcon,
                        0,
                        wandingTraderTradeEntry.emeraldsRequired,
                        wandingTraderTradeEntry.tradesAllowed,
                        wandingTraderTradeEntry.xpReward);

                if(!MobMapTrades.WANDERING_TRADER_MAP_TRADES.containsKey(tradeType)) {
                    MobMapTrades.WANDERING_TRADER_MAP_TRADES.put(tradeType, new ArrayList<>());
                }
                MobMapTrades.WANDERING_TRADER_MAP_TRADES.get(tradeType).add(finalizedTrade);
            }
        }
    }

    public static void addMapTrades() {
        for (Map.Entry<VillagerProfession, List<MapTradeFinalized>> villagerProfessionListEntry : VILLAGER_MAP_TRADES.entrySet()) {
            for (MapTradeFinalized mapTrade : villagerProfessionListEntry.getValue()) {
                TradeOfferHelper.registerVillagerOffers(villagerProfessionListEntry.getKey(), mapTrade.tradeLevel(), (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(mapTrade.emeraldsRequired(), mapTrade.structureFeature(), mapTrade.mapIcon(), mapTrade.tradesAllowed(), mapTrade.xpReward())));
            }
        }

        for (Map.Entry<WandingTraderTradeEntry.TRADE_TYPE, List<MapTradeFinalized>> tradeEntry : WANDERING_TRADER_MAP_TRADES.entrySet()) {
            for (MapTradeFinalized mapTrade : tradeEntry.getValue()) {
                if (tradeEntry.getKey() == WandingTraderTradeEntry.TRADE_TYPE.RARE) {
                    TradeOfferHelper.registerWanderingTraderOffers(2, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(mapTrade.emeraldsRequired(), mapTrade.structureFeature(), mapTrade.mapIcon(), mapTrade.tradesAllowed(), mapTrade.xpReward())));
                } else if (tradeEntry.getKey() == WandingTraderTradeEntry.TRADE_TYPE.COMMON) {
                    TradeOfferHelper.registerWanderingTraderOffers(1, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(mapTrade.emeraldsRequired(), mapTrade.structureFeature(), mapTrade.mapIcon(), mapTrade.tradesAllowed(), mapTrade.xpReward())));
                }
            }
        }
    }
}
