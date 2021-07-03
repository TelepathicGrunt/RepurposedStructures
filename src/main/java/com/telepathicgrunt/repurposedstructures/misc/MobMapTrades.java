package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.item.map.MapIcon;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import static net.minecraft.village.TradeOffers.PROFESSION_TO_LEVELED_TRADE;

public class MobMapTrades {

    public static void addMapTrades(){

        // DO NOT USE Fabric API's TradeOfferHelper.registerVillagerOffers or TradeOfferHelper.registerWanderingTraderOffers
        // It is broken and will duplicate older entries like mad. And it breaks any mod that doesn't use the API to add trades.

        // Villagers
        // Level 3 trades
        addTrade(VillagerProfession.CARTOGRAPHER, 3, new TradeOffers.SellMapFactory(13, RSStructures.FORTRESS_JUNGLE, MapIcon.Type.BANNER_GREEN, 12, 5));
        
        // Level 4 trades
        addTrade(VillagerProfession.CARTOGRAPHER, 4, new TradeOffers.SellMapFactory(14, RSStructures.FORTRESS_JUNGLE, MapIcon.Type.BANNER_GREEN, 12, 10));
        addTrade(VillagerProfession.CARTOGRAPHER, 4, new TradeOffers.SellMapFactory(14, RSStructures.MANSION_BIRCH, MapIcon.Type.MANSION, 12, 10));
        addTrade(VillagerProfession.CARTOGRAPHER, 4, new TradeOffers.SellMapFactory(14, RSStructures.MANSION_DESERT, MapIcon.Type.MANSION, 12, 10));
        addTrade(VillagerProfession.CARTOGRAPHER, 4, new TradeOffers.SellMapFactory(14, RSStructures.MANSION_JUNGLE, MapIcon.Type.MANSION, 12, 10));
        addTrade(VillagerProfession.CARTOGRAPHER, 4, new TradeOffers.SellMapFactory(14, RSStructures.MANSION_OAK, MapIcon.Type.MANSION, 12, 10));
        addTrade(VillagerProfession.CARTOGRAPHER, 4, new TradeOffers.SellMapFactory(14, RSStructures.MANSION_SAVANNA, MapIcon.Type.MANSION, 12, 10));
        addTrade(VillagerProfession.CARTOGRAPHER, 4, new TradeOffers.SellMapFactory(14, RSStructures.MANSION_SNOWY, MapIcon.Type.MANSION, 12, 10));
        addTrade(VillagerProfession.CARTOGRAPHER, 4, new TradeOffers.SellMapFactory(14, RSStructures.MANSION_TAIGA, MapIcon.Type.MANSION, 12, 10));
    }

    public static void addTrade(VillagerProfession profession, int level, TradeOffers.Factory trade) {
        TradeOffers.Factory[] fixedTrades = PROFESSION_TO_LEVELED_TRADE.get(profession).get(level);
        int newSize = fixedTrades.length + 1;

        TradeOffers.Factory[] newTrades = new TradeOffers.Factory[newSize];
        System.arraycopy(fixedTrades, 0, newTrades, 0, fixedTrades.length);
        newTrades[newSize - 1] = trade;

        PROFESSION_TO_LEVELED_TRADE.get(profession).put(level, newTrades);
    }
}
