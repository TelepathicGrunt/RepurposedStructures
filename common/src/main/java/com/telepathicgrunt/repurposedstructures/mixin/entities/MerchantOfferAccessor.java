package com.telepathicgrunt.repurposedstructures.mixin.entities;

import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

// Source: https://github.com/thebrightspark/AsyncLocator/blob/1.19.x/src/main/java/brightspark/asynclocator/mixins/MerchantOfferAccess.java
@Mixin(MerchantOffer.class)
public interface MerchantOfferAccessor {

    @Mutable
    @Accessor("maxUses")
    void setMaxUses(int maxUses);
}
