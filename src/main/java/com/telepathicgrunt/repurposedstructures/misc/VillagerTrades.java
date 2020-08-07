package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RSFeatures;
import net.minecraft.item.map.MapIcon;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VillagerTrades {

    public static void addMapTrades(){

        //level 3 trades
        List<TradeOffers.Factory> cartographer_trades = new ArrayList<>(Arrays.asList(TradeOffers.PROFESSION_TO_LEVELED_TRADE.get(VillagerProfession.CARTOGRAPHER).get(3)));
        cartographer_trades.add(new TradeOffers.SellMapFactory(13, RSFeatures.JUNGLE_FORTRESS, MapIcon.Type.BANNER_GREEN, 12, 5));
        TradeOffers.Factory[] result = new TradeOffers.Factory[]{};
        TradeOffers.PROFESSION_TO_LEVELED_TRADE.get(VillagerProfession.CARTOGRAPHER).put(3, cartographer_trades.toArray(result));

        //level 4 trades
        cartographer_trades = new ArrayList<>(Arrays.asList(TradeOffers.PROFESSION_TO_LEVELED_TRADE.get(VillagerProfession.CARTOGRAPHER).get(4)));
        cartographer_trades.add(new TradeOffers.SellMapFactory(14, RSFeatures.JUNGLE_FORTRESS, MapIcon.Type.BANNER_GREEN, 12, 10));
        result = new TradeOffers.Factory[]{};
        TradeOffers.PROFESSION_TO_LEVELED_TRADE.get(VillagerProfession.CARTOGRAPHER).put(4, cartographer_trades.toArray(result));
    }
}
