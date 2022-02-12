package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.EnumUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class MobMapTrades {
    private MobMapTrades() {}

    public static final Map<VillagerProfession, List<MapTradeFinalized>> VILLAGER_MAP_TRADES = new HashMap<>();
    public static final Map<WandingTraderTradeEntry.TRADE_TYPE, List<MapTradeFinalized>> WANDERING_TRADER_MAP_TRADES = new HashMap<>();

    public static void setupMapTrades() {
        setupVillagerMap(VILLAGER_MAP_TRADES, RepurposedStructures.omegaMapTradeConfig.villagerMapTrades, "villager map trades");
        setupWanderingTraderMap(WANDERING_TRADER_MAP_TRADES, RepurposedStructures.omegaMapTradeConfig.wanderingTraderMapTrades, "wandering trader map trades");
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

    private static void setupVillagerMap(Map<VillagerProfession, List<MapTradeFinalized>> tradeMapToFill,
                                 Map<String, List<VillagerTradeEntry>> configMap,
                                 String errorMsg) {

        for(Map.Entry<String, List<VillagerTradeEntry>> configMapEntry : configMap.entrySet()) {

            VillagerProfession villagerProfession = ForgeRegistries.PROFESSIONS.getValue(new ResourceLocation(configMapEntry.getKey()));
            if(villagerProfession == null) {
                RepurposedStructures.LOGGER.warn("Repurposed Structures: Unknown key {} was found in the {} config. Skipping that entry...", configMapEntry.getKey(), errorMsg);
                continue;
            }

            for(VillagerTradeEntry villagerTradeEntry : configMapEntry.getValue()) {
                // Parse and make sure the entity type exists
                StructureFeature<?> structureFeature = ForgeRegistries.STRUCTURE_FEATURES.getValue(new ResourceLocation(villagerTradeEntry.structure));
                if(structureFeature == null) {
                    RepurposedStructures.LOGGER.warn("Repurposed Structures (second): Unknown Structure {} was found in the {} config. Skipping that entry...", villagerTradeEntry.structure, errorMsg);
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

                if(!tradeMapToFill.containsKey(villagerProfession)) {
                    tradeMapToFill.put(villagerProfession, new ArrayList<>());
                }
                tradeMapToFill.get(villagerProfession).add(finalizedTrade);
            }
        }
    }

    private static void setupWanderingTraderMap(Map<WandingTraderTradeEntry.TRADE_TYPE, List<MapTradeFinalized>> tradeMapToFill,
                                 Map<String, List<WandingTraderTradeEntry>> configMap,
                                 String errorMsg) {

        for(Map.Entry<String, List<WandingTraderTradeEntry>> configMapEntry : configMap.entrySet()) {

            WandingTraderTradeEntry.TRADE_TYPE tradeType = EnumUtils.getEnum(WandingTraderTradeEntry.TRADE_TYPE.class, configMapEntry.getKey());
            if(tradeType == null) {
                RepurposedStructures.LOGGER.warn("Repurposed Structures: Unknown key {} was found in the {} config. Skipping that entry...", configMapEntry.getKey(), errorMsg);
                continue;
            }

            for(WandingTraderTradeEntry wandingTraderTradeEntry : configMapEntry.getValue()) {
                // Parse and make sure the entity type exists
                StructureFeature<?> structureFeature = ForgeRegistries.STRUCTURE_FEATURES.getValue(new ResourceLocation(wandingTraderTradeEntry.structure));
                if(structureFeature == null) {
                    RepurposedStructures.LOGGER.warn("Repurposed Structures (second): Unknown Structure {} was found in the {} config. Skipping that entry...", wandingTraderTradeEntry.structure, errorMsg);
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

                if(!tradeMapToFill.containsKey(tradeType)) {
                    tradeMapToFill.put(tradeType, new ArrayList<>());
                }
                tradeMapToFill.get(tradeType).add(finalizedTrade);
            }
        }
    }

    public static void onVillagerTradesEvent(VillagerTradesEvent event) {
        if (VILLAGER_MAP_TRADES.containsKey(event.getType())) {
            for (MapTradeFinalized mapTrade : VILLAGER_MAP_TRADES.get(event.getType())) {
                event.getTrades().get(mapTrade.tradeLevel()).add(new VillagerTrades.TreasureMapForEmeralds(mapTrade.emeraldsRequired(), mapTrade.structureFeature(), mapTrade.mapIcon(), mapTrade.tradesAllowed(), mapTrade.xpReward()));
            }
        }
    }

    public static void onWandererTradesEvent(WandererTradesEvent event) {
        for (Map.Entry<WandingTraderTradeEntry.TRADE_TYPE, List<MapTradeFinalized>> tradeEntry : WANDERING_TRADER_MAP_TRADES.entrySet()) {
            for (MapTradeFinalized mapTrade : tradeEntry.getValue()) {
                if (tradeEntry.getKey() == WandingTraderTradeEntry.TRADE_TYPE.RARE) {
                    event.getRareTrades().add(new VillagerTrades.TreasureMapForEmeralds(mapTrade.emeraldsRequired(), mapTrade.structureFeature(), mapTrade.mapIcon(), mapTrade.tradesAllowed(), mapTrade.xpReward()));
                }
                else if (tradeEntry.getKey() == WandingTraderTradeEntry.TRADE_TYPE.COMMON) {
                    event.getGenericTrades().add(new VillagerTrades.TreasureMapForEmeralds(mapTrade.emeraldsRequired(), mapTrade.structureFeature(), mapTrade.mapIcon(), mapTrade.tradesAllowed(), mapTrade.xpReward()));
                }
            }
        }
    }
}
