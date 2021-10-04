package com.telepathicgrunt.repurposedstructures.mixin.resources;

import net.minecraft.loot.IRandomRange;
import net.minecraft.loot.RandomRanges;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(RandomRanges.class)
public interface RandomRangesAccessor {
    @Accessor("GENERATORS")
    static Map<ResourceLocation, Class<? extends IRandomRange>> repurposedstructures_getGENERATORS() {
        throw new UnsupportedOperationException();
    }
}
