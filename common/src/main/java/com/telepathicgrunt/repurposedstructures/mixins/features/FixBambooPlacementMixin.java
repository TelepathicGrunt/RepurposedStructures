package com.telepathicgrunt.repurposedstructures.mixins.features;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.BambooFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(value = BambooFeature.class, priority = 1200)
public class FixBambooPlacementMixin {

    @Inject(
            method = "place(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/WorldGenLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z", ordinal = 2),
            require = 0
    )
    private void repurposedstructures_fixBambooNonAirCheck(
            WorldGenLevel level,
            ChunkGenerator chunkGenerator,
            RandomSource random,
            BlockPos origin,
            CallbackInfoReturnable<Boolean> cir,
            @Local(name = "bambooPos") BlockPos.MutableBlockPos mutableBlockPos)
    {
        if (!level.isEmptyBlock(mutableBlockPos)) {
            mutableBlockPos.move(Direction.DOWN);
        }
    }
}
