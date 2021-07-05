package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.map.MapIcon;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import static net.minecraft.village.TradeOffers.PROFESSION_TO_LEVELED_TRADE;

public class MobMapTrades {

    public static void addMapTrades(){

        // Villagers
        // Level 3 trades
        if(RepurposedStructures.RSAllConfig.RSFortressesConfig.jungleFortress.jungleFortressAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 3, (factories) -> factories.add(new TradeOffers.SellMapFactory(13, RSStructures.FORTRESS_JUNGLE, MapIcon.Type.BANNER_GREEN, 12, 5)));
        
        // Level 4 trades
        if(RepurposedStructures.RSAllConfig.RSFortressesConfig.jungleFortress.jungleFortressAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new TradeOffers.SellMapFactory(14, RSStructures.FORTRESS_JUNGLE, MapIcon.Type.BANNER_GREEN, 12, 10)));

        if(RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionBirchAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new TradeOffers.SellMapFactory(14, RSStructures.MANSION_BIRCH, MapIcon.Type.MANSION, 12, 10)));
        if(RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionDesertAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new TradeOffers.SellMapFactory(14, RSStructures.MANSION_DESERT, MapIcon.Type.MANSION, 12, 10)));
        if(RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionJungleAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new TradeOffers.SellMapFactory(14, RSStructures.MANSION_JUNGLE, MapIcon.Type.MANSION, 12, 10)));
        if(RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionOakAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new TradeOffers.SellMapFactory(14, RSStructures.MANSION_OAK, MapIcon.Type.MANSION, 12, 10)));
        if(RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionSavannaAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new TradeOffers.SellMapFactory(14, RSStructures.MANSION_SAVANNA, MapIcon.Type.MANSION, 12, 10)));
        if(RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionSnowyAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new TradeOffers.SellMapFactory(14, RSStructures.MANSION_SNOWY, MapIcon.Type.MANSION, 12, 10)));
        if(RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionTaigaAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new TradeOffers.SellMapFactory(14, RSStructures.MANSION_TAIGA, MapIcon.Type.MANSION, 12, 10)));

        // Wandering Traders
        if(RepurposedStructures.RSAllConfig.RSBastionsConfig.bastionUndergroundAverageChunkDistance != 10001)
            TradeOfferHelper.registerWanderingTraderOffers(2, (factories) -> factories.add(new TradeOffers.SellMapFactory(38, RSStructures.BASTION_UNDERGROUND, MapIcon.Type.BANNER_GRAY, 1, 100)));
    }
}
