package com.telepathicgrunt.repurposedstructures.events;

import com.telepathicgrunt.repurposedstructures.events.base.EventHandler;
import net.minecraft.world.entity.npc.villager.VillagerTrades;

import java.util.function.Consumer;

public record RegisterWanderingTradesEvent(Consumer<VillagerTrades.ItemListing> basic, Consumer<VillagerTrades.ItemListing> rare, Consumer<VillagerTrades.ItemListing> buying) {

    public static final EventHandler<RegisterWanderingTradesEvent> EVENT = new EventHandler<>();

    public void addBasicTrade(VillagerTrades.ItemListing trade) {
        basic.accept(trade);
    }

    public void addBuyingTrade(VillagerTrades.ItemListing trade) {
        buying.accept(trade);
    }

    public void addRareTrade(VillagerTrades.ItemListing trade) {
        rare.accept(trade);
    }
}
