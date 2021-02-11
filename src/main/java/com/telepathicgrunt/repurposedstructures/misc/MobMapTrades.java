package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.entity.mob.PiglinActivity;
import net.minecraft.item.map.MapIcon;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MobMapTrades {

    public static void addMapTrades(){

        // Villagers
        // Level 3 trades
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 3, factories -> {
            factories.add(new TradeOffers.SellMapFactory(13, RSStructures.JUNGLE_FORTRESS, MapIcon.Type.BANNER_GREEN, 12, 5));
        });

        // Level 4 trades
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, factories -> {
            factories.add(new TradeOffers.SellMapFactory(14, RSStructures.JUNGLE_FORTRESS, MapIcon.Type.BANNER_GREEN, 12, 10));
            factories.add(new TradeOffers.SellMapFactory(14, RSStructures.MANSION_BIRCH, MapIcon.Type.MANSION, 12, 10));
        });

        // Piglins
        // .......
    }
}
