package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.saveddata.maps.MapDecoration;

public final class MobMapTrades {
    private MobMapTrades() {}

    public static void addMapTrades() {

        // Villagers
        // Level 3 trades
        if(RepurposedStructures.RSAllConfig.RSFortressesConfig.jungleFortress.jungleFortressAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 3, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(13, RSStructures.FORTRESS_JUNGLE, MapDecoration.Type.BANNER_GREEN, 12, 5)));
        
        // Level 4 trades
        if(RepurposedStructures.RSAllConfig.RSFortressesConfig.jungleFortress.jungleFortressAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.FORTRESS_JUNGLE, MapDecoration.Type.BANNER_GREEN, 12, 10)));

        if(RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionBirchAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.MANSION_BIRCH, MapDecoration.Type.MANSION, 12, 10)));
        if(RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionDesertAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.MANSION_DESERT, MapDecoration.Type.MANSION, 12, 10)));
        if(RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionJungleAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.MANSION_JUNGLE, MapDecoration.Type.MANSION, 12, 10)));
        if(RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionOakAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.MANSION_OAK, MapDecoration.Type.MANSION, 12, 10)));
        if(RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionSavannaAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.MANSION_SAVANNA, MapDecoration.Type.MANSION, 12, 10)));
        if(RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionSnowyAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.MANSION_SNOWY, MapDecoration.Type.MANSION, 12, 10)));
        if(RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionTaigaAverageChunkDistance != 1001)
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.MANSION_TAIGA, MapDecoration.Type.MANSION, 12, 10)));

        // Wandering Traders
        if(RepurposedStructures.RSAllConfig.RSBastionsConfig.bastionUndergroundAverageChunkDistance != 10001)
            TradeOfferHelper.registerWanderingTraderOffers(2, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(38, RSStructures.BASTION_UNDERGROUND, MapDecoration.Type.BANNER_GRAY, 1, 100)));
        if(RepurposedStructures.RSAllConfig.RSVillagesConfig.spawnrate.villageMushroomAverageChunkDistance != 1001)
            TradeOfferHelper.registerWanderingTraderOffers(2, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(45, RSStructures.VILLAGE_MUSHROOM, MapDecoration.Type.MANSION, 1, 100)));
        if(RepurposedStructures.RSAllConfig.RSCitiesConfig.cityOverworldAverageChunkDistance != 10001)
            TradeOfferHelper.registerWanderingTraderOffers(2, (factories) -> factories.add(new VillagerTrades.TreasureMapForEmeralds(52, RSStructures.CITY_OVERWORLD, MapDecoration.Type.MANSION, 1, 200)));
    }
}
