package com.telepathicgrunt.repurposedstructures.mixin;


import com.telepathicgrunt.repurposedstructures.utils.LithiumDoublePairList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.util.shape.PairList;
import net.minecraft.util.shape.VoxelShapes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * From Lithium (LGPLv3)
 * Source: https://github.com/CaffeineMC/lithium-fabric/blob/1.16.x/dev/src/main/java/me/jellysquid/mods/lithium/mixin/shapes/shape_merging/VoxelShapesMixin.java
 *
 * We need this to help optimize the structure bounding boxes a bit when Lithium isn't on.
 */
@Mixin(VoxelShapes.class)
public class VoxelShapesMixin {
    /**
     * Replaces the returned list pair with our own optimized type.
     */
    @Inject(
            method = "createListPair",
            at = @At(
                    shift = At.Shift.BEFORE,
                    value = "NEW",
                    target = "net/minecraft/util/shape/SimplePairList"
            ),
            cancellable = true
    )
    private static void repurposedstructures_injectCustomListPair(int size, DoubleList a, DoubleList b, boolean flag1, boolean flag2, CallbackInfoReturnable<PairList> cir) {
        cir.setReturnValue(new LithiumDoublePairList(a, b, flag1, flag2));
    }
}