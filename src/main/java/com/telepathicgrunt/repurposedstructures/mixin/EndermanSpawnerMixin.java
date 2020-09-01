package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Special thanks to Draylar for this fix for a vanilla bug!
 */
@Mixin(EndermanEntity.class)
public abstract class EndermanSpawnerMixin extends MobEntity implements IAngerable {

    private EndermanSpawnerMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "readAdditional(Lnet/minecraft/nbt/CompoundNBT;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/monster/EndermanEntity;setHeldBlockState(Lnet/minecraft/block/BlockState;)V", shift = At.Shift.AFTER),
            cancellable = true
    )
    private void worldCheckAngerFromTag(CompoundNBT tag, CallbackInfo ci) {
        if (!this.world.isRemote) {
            this.angerFromTag((ServerWorld) world, tag);
        }

        ci.cancel();
    }
}