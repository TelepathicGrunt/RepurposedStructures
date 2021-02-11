package com.telepathicgrunt.repurposedstructures.misc;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.world.storage.MapDecoration;
import net.minecraftforge.event.village.VillagerTradesEvent;

public class MobMapTrades {

    public static void onVillagerTradesEvent(VillagerTradesEvent event)
    {
        if(event.getType() == VillagerProfession.CARTOGRAPHER)
        {
            //level 3 trades
            event.getTrades().get(3).add(new VillagerTrades.EmeraldForMapTrade(13, RSStructures.JUNGLE_FORTRESS.get(), MapDecoration.Type.BANNER_GREEN, 12, 5));

            //level 4 trades
            event.getTrades().get(4).add(new VillagerTrades.EmeraldForMapTrade(14, RSStructures.JUNGLE_FORTRESS.get(), MapDecoration.Type.BANNER_GREEN, 12, 10));
            event.getTrades().get(4).add(new VillagerTrades.EmeraldForMapTrade(14, RSStructures.MANSION_BIRCH.get(), MapDecoration.Type.BANNER_GREEN, 12, 10));
        }
    }
}
