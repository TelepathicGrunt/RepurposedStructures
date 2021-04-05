package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ShulkerEntity.class)
public interface ShulkerEntityAccessor {
    @Invoker("findAttachSide")
    Direction rs_callFindAttachSide(BlockPos pos);
}
