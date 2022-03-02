package com.telepathicgrunt.repurposedstructures.mixin.entities;

import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Cat.class)
public abstract class CatEntityMixin extends Mob {

    protected CatEntityMixin(EntityType<? extends TamableAnimal> entityType, Level world) {
        super(entityType, world);
    }

    @Shadow
    public abstract void setCatType(int catType);

    /**
     * Allow witch hut cats to spawn in Repurposed Structures's witch huts.
     * @author TelepathicGrunt
     */
    @Inject(method = "finalizeSpawn(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/MobSpawnType;Lnet/minecraft/world/entity/SpawnGroupData;Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/world/entity/SpawnGroupData;",
            at = @At(value = "TAIL"))
    private void repurposedstructures_spawnWitchHutCats(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason, SpawnGroupData entityData, CompoundTag entityTag, CallbackInfoReturnable<SpawnGroupData> cir) {
        ServerLevel serverLevel = world.getLevel();
        BlockPos pos = blockPosition();

        if (serverLevel != null) {
            Registry<ConfiguredStructureFeature<?,?>> configuredStructureFeatureRegistry = serverLevel.registryAccess().registryOrThrow(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY);
            for (Holder<ConfiguredStructureFeature<?, ?>> configuredStructureFeature : configuredStructureFeatureRegistry.getTag(RSTags.SPAWNS_BLACK_CATS).get()) {
                if(serverLevel.structureFeatureManager().getStructureAt(pos, configuredStructureFeature.value()).isValid()) {
                    setCatType(Cat.TYPE_ALL_BLACK);
                    setPersistenceRequired();
                    return;
                }
            }
        }
    }
}
