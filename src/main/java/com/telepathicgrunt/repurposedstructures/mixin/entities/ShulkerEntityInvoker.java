package com.telepathicgrunt.repurposedstructures.mixin.entities;

import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ShulkerEntity.class)
public interface ShulkerEntityInvoker {
    @Invoker("setAttachedFace")
    void repurposedstructures_callSetAttachedFace(Direction face);
}
