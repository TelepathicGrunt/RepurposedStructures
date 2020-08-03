package com.telepathicgrunt.repurposedstructures.mixin;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

/**
 * Special thanks to Draylar for this fix for a vanilla bug!
 */
@Mixin(ZombifiedPiglinEntity.class)
public abstract class ZombifiedPiglinSpawnerMixin extends MobEntity implements IAngerable {

    private ZombifiedPiglinSpawnerMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "readAdditional(Lnet/minecraft/nbt/CompoundNBT;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/monster/ZombieEntity;readAdditional(Lnet/minecraft/nbt/CompoundNBT;)V", shift = At.Shift.AFTER),
            cancellable = true
    )
    private void worldCheckAngerFromTag(CompoundNBT tag, CallbackInfo ci) {
        if (!this.world.isRemote) {
            this.angerFromTag((ServerWorld) world, tag);
        }

        ci.cancel();
    }
}