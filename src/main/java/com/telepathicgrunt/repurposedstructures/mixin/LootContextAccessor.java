package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(LootContext.class)
public interface LootContextAccessor {
    @Accessor("parameters")
    Map<LootContextParameter<?>, Object> repurposedstructures_getParameters();

    @Accessor("drops")
    Map<Identifier, LootContext.Dropper> repurposedstructures_getDrops();
}
