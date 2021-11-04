package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.configs.RSBastionsConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSFortressesConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSMansionsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.world.storage.MapDecoration;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;

public final class MobMapTrades {
    private MobMapTrades() {}

    public static void onVillagerTradesEvent(VillagerTradesEvent event)
    {
        if(event.getType() == VillagerProfession.CARTOGRAPHER)
        {
            //level 3 trades
            if(RSFortressesConfig.jungleFortressMaxChunkDistance.get() != 1001)
                event.getTrades().get(3).add(new VillagerTrades.EmeraldForMapTrade(13, RSStructures.FORTRESS_JUNGLE.get(), MapDecoration.Type.BANNER_GREEN, 12, 5));

            //level 4 trades
            if(RSFortressesConfig.jungleFortressMaxChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.EmeraldForMapTrade(14, RSStructures.FORTRESS_JUNGLE.get(), MapDecoration.Type.BANNER_GREEN, 12, 10));

            if(RSMansionsConfig.mansionBirchMaxChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.EmeraldForMapTrade(14, RSStructures.MANSION_BIRCH.get(), MapDecoration.Type.MANSION, 12, 10));
            if(RSMansionsConfig.mansionDesertMaxChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.EmeraldForMapTrade(14, RSStructures.MANSION_DESERT.get(), MapDecoration.Type.MANSION, 12, 10));
            if(RSMansionsConfig.mansionJungleMaxChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.EmeraldForMapTrade(14, RSStructures.MANSION_JUNGLE.get(), MapDecoration.Type.MANSION, 12, 10));
            if(RSMansionsConfig.mansionOakMaxChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.EmeraldForMapTrade(14, RSStructures.MANSION_OAK.get(), MapDecoration.Type.MANSION, 12, 10));
            if(RSMansionsConfig.mansionSavannaMaxChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.EmeraldForMapTrade(14, RSStructures.MANSION_SAVANNA.get(), MapDecoration.Type.MANSION, 12, 10));
            if(RSMansionsConfig.mansionSnowyMaxChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.EmeraldForMapTrade(14, RSStructures.MANSION_SNOWY.get(), MapDecoration.Type.MANSION, 12, 10));
            if(RSMansionsConfig.mansionTaigaMaxChunkDistance.get() != 1001)
                event.getTrades().get(4).add(new VillagerTrades.EmeraldForMapTrade(14, RSStructures.MANSION_TAIGA.get(), MapDecoration.Type.MANSION, 12, 10));
        }
    }


    public static void onWandererTradesEvent(WandererTradesEvent event)
    {
        if(RSBastionsConfig.bastionUndergroundMaxChunkDistance.get() != 10001)
            event.getRareTrades().add(new VillagerTrades.EmeraldForMapTrade(38, RSStructures.BASTION_UNDERGROUND.get(), MapDecoration.Type.BANNER_GRAY, 1, 100));
    }
}
