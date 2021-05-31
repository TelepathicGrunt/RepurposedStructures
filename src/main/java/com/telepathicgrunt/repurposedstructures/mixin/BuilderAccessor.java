package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(LootContext.Builder.class)
public interface BuilderAccessor {
    @Mutable
    @Accessor("drops")
    void repurposedstructures_setDrops(Map<Identifier, LootContext.Dropper> drops);

    @Mutable
    @Accessor("parameters")
    void repurposedstructures_setParameters(Map<LootContextParameter<?>, Object> parameters);
}
