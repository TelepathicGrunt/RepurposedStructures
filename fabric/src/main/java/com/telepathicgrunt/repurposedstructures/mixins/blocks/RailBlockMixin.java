package com.telepathicgrunt.repurposedstructures.mixins.blocks;


import net.minecraft.world.level.block.RailBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
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
    @Inject(method = "rotate(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/Rotation;)Lnet/minecraft/world/level/block/state/BlockState;",
            at = @At(value = "RETURN"), cancellable = true)
    private void repurposedstructures_fixMC196102Bug(BlockState state, Rotation rotation, CallbackInfoReturnable<BlockState> cir) {
        if(rotation == Rotation.CLOCKWISE_180) {
            RailShape railShape = state.getValue(RailBlock.SHAPE);
            if(railShape == RailShape.NORTH_SOUTH || railShape == RailShape.EAST_WEST) {
                cir.setReturnValue(state);
            }
        }
    }
}
