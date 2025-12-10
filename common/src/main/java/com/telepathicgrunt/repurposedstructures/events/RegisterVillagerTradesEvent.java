package com.telepathicgrunt.repurposedstructures.events;

import com.telepathicgrunt.repurposedstructures.events.base.EventHandler;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.npc.villager.VillagerTrades;

import java.util.function.BiConsumer;

public record RegisterVillagerTradesEvent(ResourceKey<VillagerProfession> type, BiConsumer<Integer, VillagerTrades.ItemListing> trade) {

    public static final EventHandler<RegisterVillagerTradesEvent> EVENT = new EventHandler<>();

    public void addTrade(int level, VillagerTrades.ItemListing trade) {
        this.trade.accept(level, trade);
    }
}
