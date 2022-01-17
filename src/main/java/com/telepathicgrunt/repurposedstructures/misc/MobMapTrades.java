package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.configs.RSBastionsConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSCitiesConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSFortressesConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSMansionsConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSVillagesConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;

public final class MobMapTrades {
    private MobMapTrades() {}

    public static void onVillagerTradesEvent(VillagerTradesEvent event) {
        if(event.getType() == VillagerProfession.CARTOGRAPHER) {
            //level 3 trades
            if(RSFortressesConfig.jungleFortressAverageChunkDistance.get() != 1001)
                event.getTrades().get(3).add(new VillagerTrades.TreasureMapForEmeralds(13, RSStructures.FORTRESS_JUNGLE.get(), MapDecoration.Type.BANNER_GREEN, 12, 5));

            //level 4 trades
            if(RSFortressesConfig.jungleFortressAverageChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.FORTRESS_JUNGLE.get(), MapDecoration.Type.BANNER_GREEN, 12, 10));

            if(RSMansionsConfig.mansionBirchAverageChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.MANSION_BIRCH.get(), MapDecoration.Type.MANSION, 12, 10));
            if(RSMansionsConfig.mansionDesertAverageChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.MANSION_DESERT.get(), MapDecoration.Type.MANSION, 12, 10));
            if(RSMansionsConfig.mansionJungleAverageChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.MANSION_JUNGLE.get(), MapDecoration.Type.MANSION, 12, 10));
            if(RSMansionsConfig.mansionOakAverageChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.MANSION_OAK.get(), MapDecoration.Type.MANSION, 12, 10));
            if(RSMansionsConfig.mansionSavannaAverageChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.MANSION_SAVANNA.get(), MapDecoration.Type.MANSION, 12, 10));
            if(RSMansionsConfig.mansionSnowyAverageChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.MANSION_SNOWY.get(), MapDecoration.Type.MANSION, 12, 10));
            if(RSMansionsConfig.mansionTaigaAverageChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.TreasureMapForEmeralds(14, RSStructures.MANSION_TAIGA.get(), MapDecoration.Type.MANSION, 12, 10));
        }
    }


    public static void onWandererTradesEvent(WandererTradesEvent event) {
        if(RSBastionsConfig.bastionUndergroundAverageChunkDistance.get() != 10001)
            event.getRareTrades().add(new VillagerTrades.TreasureMapForEmeralds(38, RSStructures.BASTION_UNDERGROUND.get(), MapDecoration.Type.BANNER_GRAY, 1, 100));
        if(RSVillagesConfig.villageMushroomAverageChunkDistance.get() != 1001)
            event.getRareTrades().add(new VillagerTrades.TreasureMapForEmeralds(45, RSStructures.VILLAGE_MUSHROOM.get(), MapDecoration.Type.MANSION, 1, 100));
        if(RSCitiesConfig.citiesOverworldAverageChunkDistance.get() != 10001)
            event.getRareTrades().add(new VillagerTrades.TreasureMapForEmeralds(52, RSStructures.CITY_OVERWORLD.get(), MapDecoration.Type.MANSION, 1, 200));
    }
}
