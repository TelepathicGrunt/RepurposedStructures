package com.telepathicgrunt.repurposedstructures.mixin.entities;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.monster.Shulker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Shulker.class)
public interface ShulkerEntityInvoker {
    @Invoker("setAttachedFace")
    void repurposedstructures_callSetAttachedFace(Direction face);
}
