package com.telepathicgrunt.repurposedstructures.mixins.neoforge.structures;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = StructureTemplatePool.class, priority = 1200)
public class StructurePoolMixin {

    /**
     * Increases the weight limit that mojang slapped on that was a workaround for https://bugs.mojang.com/browse/MC-203131
     * @author - TelepathicGrunt
     * @return - The higher weight that is a more reasonable limit.
     */
    @WrapOperation(
            method = "method_28886",
            at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/Codec;intRange(II)Lcom/mojang/serialization/Codec;"),
            require = 0,
            remap = false
    )
    private static Codec<Integer> repurposedstructures_increaseWeightLimit(int minRange, int maxRange, Operation<Codec<Integer>> original) {
        return original.call(minRange, 5000);
    }
}