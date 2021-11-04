package com.telepathicgrunt.repurposedstructures.mixin.datagen;

import net.minecraft.loot.StandaloneLootEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StandaloneLootEntry.class)
public interface StandaloneLootEntryAccessor {
    @Accessor
    int getWeight();
}
