package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RSFeatures;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.world.storage.MapDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VillagerTradesModification {

    public static void addMapTrades(){

        //level 3 trades
        List<VillagerTrades.ITrade> cartographer_trades = new ArrayList<>(Arrays.asList(VillagerTrades.VILLAGER_DEFAULT_TRADES.get(VillagerProfession.CARTOGRAPHER).get(3)));
        cartographer_trades.add(new VillagerTrades.EmeraldForMapTrade(13, RSFeatures.JUNGLE_FORTRESS, MapDecoration.Type.BANNER_GREEN, 12, 5));
        VillagerTrades.ITrade[] result = new VillagerTrades.ITrade[]{};
        VillagerTrades.VILLAGER_DEFAULT_TRADES.get(VillagerProfession.CARTOGRAPHER).put(3, cartographer_trades.toArray(result));

        //level 4 trades
        cartographer_trades = new ArrayList<>(Arrays.asList(VillagerTrades.VILLAGER_DEFAULT_TRADES.get(VillagerProfession.CARTOGRAPHER).get(4)));
        cartographer_trades.add(new VillagerTrades.EmeraldForMapTrade(14, RSFeatures.JUNGLE_FORTRESS, MapDecoration.Type.BANNER_GREEN, 12, 10));
        result = new VillagerTrades.ITrade[]{};
        VillagerTrades.VILLAGER_DEFAULT_TRADES.get(VillagerProfession.CARTOGRAPHER).put(4, cartographer_trades.toArray(result));
    }
}
