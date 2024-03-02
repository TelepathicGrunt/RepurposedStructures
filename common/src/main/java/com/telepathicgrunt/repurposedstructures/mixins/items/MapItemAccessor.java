package com.telepathicgrunt.repurposedstructures.mixins.items;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

// Source: https://github.com/thebrightspark/AsyncLocator/blob/1.19.x/src/main/java/brightspark/asynclocator/mixins/MapItemAccess.java
@Mixin(MapItem.class)
public interface MapItemAccessor {
    @Invoker
    static void callCreateAndStoreSavedData(ItemStack stack, Level level, int x, int z, int scale, boolean trackingPosition, boolean unlimitedTracking, ResourceKey<Level> dimension) {
        throw new UnsupportedOperationException();
    }
}
