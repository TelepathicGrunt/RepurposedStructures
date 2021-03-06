package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CatEntity.class)
public abstract class CatEntityMixin extends MobEntity {

    protected CatEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract void setCatType(int catType);

    /**
     * Allow witch hut cats to spawn in repurposed Structures's witch huts.
     * @author TelepathicGrunt
     */
    @Inject(method = "finalizeSpawn(Lnet/minecraft/world/IServerWorld;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/entity/SpawnReason;Lnet/minecraft/entity/ILivingEntityData;Lnet/minecraft/nbt/CompoundNBT;)Lnet/minecraft/entity/ILivingEntityData;",
            at = @At(value = "TAIL"))
    private void repurposedstructures_spawnWitchHutCats(IServerWorld world, DifficultyInstance difficulty, SpawnReason spawnReason, ILivingEntityData entityData, CompoundNBT entityTag, CallbackInfoReturnable<ILivingEntityData> cir){
        ServerWorld world2 = world.getLevel();
        BlockPos pos = blockPosition();

        for(Structure<?> structureFeature : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.WITCH_HUTS)){
            if (world2.structureFeatureManager().getStructureAt(pos, true, structureFeature).isValid()) {
                setCatType(10);
                setPersistenceRequired();
                return;
            }
        }
    }
}
