package com.telepathicgrunt.repurposedstructures.mixins.entities;

import net.minecraft.world.entity.npc.VillagerTrades;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(VillagerTrades.class)
public interface VillagerTradesAccessor {
    @Mutable
    @Accessor
    static void setWANDERING_TRADER_TRADES(List<Pair<VillagerTrades.ItemListing[], Integer>> WANDERING_TRADER_TRADES) {
        throw new UnsupportedOperationException();
    }
}
