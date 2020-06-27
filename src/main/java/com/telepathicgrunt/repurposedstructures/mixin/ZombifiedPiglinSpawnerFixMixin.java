package com.telepathicgrunt.repurposedstructures.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


/**
 * Special thanks to Draylar for this mixin to fix log spam from mojang bug with putting neutral mobs in mob spawners!
 *@Redirect(
 *             method = "readCustomDataFromTag",
 *             at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/EndermanEntity;angerFromTag(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/nbt/CompoundTag;)V")
 *     )
 *     private void patchEndermanSpawner(EndermanEntity entity, ServerWorld world, CompoundTag tag) {
 *         if(!entity.world.isClient) {
 *             entity.angerFromTag((ServerWorld) entity.world, tag);
 *         }
 *     }
 */
@Mixin(ZombifiedPiglinEntity.class)
public class ZombifiedPiglinSpawnerFixMixin {

    @Inject(
            method = "readCustomDataFromTag",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/ZombifiedPiglinEntity;angerFromTag(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/nbt/CompoundTag;)V"),
            cancellable = true
    )
    public void patchZombifiedPiglinSpawner(CompoundTag tag, CallbackInfo ci) {
        if(((ZombifiedPiglinEntity)(Object)this).world.isClient) {
            ci.cancel();
        }
    }
}
