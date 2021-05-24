package com.telepathicgrunt.repurposedstructures.mixin;


import com.telepathicgrunt.repurposedstructures.misc.LithiumDoublePairList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.util.math.shapes.IDoubleListMerger;
import net.minecraft.util.math.shapes.VoxelShapes;
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
            method = "createIndexMerger",
            at = @At(
                    shift = At.Shift.BEFORE,
                    value = "NEW",
                    target = "net/minecraft/util/math/shapes/IndirectMerger"
            ),
            cancellable = true
    )
    private static void rs_injectCustomListPair(int size, DoubleList a, DoubleList b, boolean flag1, boolean flag2, CallbackInfoReturnable<IDoubleListMerger> cir) {
        cir.setReturnValue(new LithiumDoublePairList(a, b, flag1, flag2));
    }
}