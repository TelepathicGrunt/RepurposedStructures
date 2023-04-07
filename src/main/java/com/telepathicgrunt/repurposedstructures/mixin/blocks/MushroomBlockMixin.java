package com.telepathicgrunt.repurposedstructures.mixin.blocks;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Fixes https://bugs.mojang.com/browse/MC-213695 bug for everyone.
 * You're welcome from this pain of existence.
 */
@Mixin(MushroomBlock.class)
public class MushroomBlockMixin {

    @ModifyExpressionValue(method = "canSurvive(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/LevelReader;getRawBrightness(Lnet/minecraft/core/BlockPos;I)I"))
    private int repurposedstructures_allowMushroomBlocksToSpawnInStructures(int lightValue, BlockState blockState, LevelReader levelReader) {
        if (levelReader instanceof WorldGenRegion) {
            return 0;
        }
        return lightValue;
    }
}

