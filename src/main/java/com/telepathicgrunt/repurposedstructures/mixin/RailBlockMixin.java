package com.telepathicgrunt.repurposedstructures.mixin;


import net.minecraft.block.BlockState;
import net.minecraft.block.RailBlock;
import net.minecraft.block.enums.RailShape;
import net.minecraft.util.BlockRotation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RailBlock.class)
public class RailBlockMixin {

    /**
     * Fixes https://bugs.mojang.com/browse/MC-196102
     * @author TelepathicGrunt
     */
    @Inject(method = "rotate(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/BlockRotation;)Lnet/minecraft/block/BlockState;",
            at = @At(value = "RETURN"), cancellable = true)
    private void rs_fixMC196102Bug(BlockState state, BlockRotation rotation, CallbackInfoReturnable<BlockState> cir){
        if(rotation == BlockRotation.CLOCKWISE_180){
            RailShape railShape = state.get(RailBlock.SHAPE);
            if(railShape == RailShape.NORTH_SOUTH || railShape == RailShape.EAST_WEST){
                cir.setReturnValue(state);
            }
        }
    }
}
