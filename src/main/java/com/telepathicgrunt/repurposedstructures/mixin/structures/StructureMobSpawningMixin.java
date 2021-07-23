package com.telepathicgrunt.repurposedstructures.mixin.structures;

import com.telepathicgrunt.repurposedstructures.misc.MobSpawningOverTime;
import net.minecraft.core.BlockPos;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseBasedChunkGenerator.class)
public class StructureMobSpawningMixin {

    @Inject(
            method = "getMobsAt(Lnet/minecraft/world/level/biome/Biome;Lnet/minecraft/world/level/StructureFeatureManager;Lnet/minecraft/world/entity/MobCategory;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/util/random/WeightedRandomList;",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_structureMobs(Biome biome, StructureFeatureManager accessor, MobCategory group, BlockPos pos, CallbackInfoReturnable<WeightedRandomList<MobSpawnSettings.SpawnerData>> cir) {
        WeightedRandomList<MobSpawnSettings.SpawnerData> pool = MobSpawningOverTime.getStructureSpawns(biome, accessor, group, pos);
        if(pool != null && !pool.isEmpty()) cir.setReturnValue(pool);
    }
}