package com.telepathicgrunt.repurposedstructures.mixin.structures;

import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Group;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("UnresolvedMixinReference")
@Mixin(StructureTemplatePool.class)
public class StructurePoolMixin {

    /**
     * Increases the weight limit that mojang slapped on that was a workaround for https://bugs.mojang.com/browse/MC-203131
     * @author - TelepathicGrunt
     * @return - The higher weight that is a more reasonable limit.
     */
    @Group(name = "repurposedstructures_structurepoolmixin")
    @ModifyConstant(
            method = "lambda$static$1",
            constant = @Constant(intValue = 150),
            remap = false
    )
    private static int repurposedstructures_increaseWeightLimitDev(int constant) {
        return 5000;
    }

    @Group(name = "repurposedstructures_structurepoolmixin")
    @ModifyConstant(
            method = "method_28886",
            constant = @Constant(intValue = 150),
            remap = false
    )
    private static int repurposedstructures_increaseWeightLimitProd(int constant) {
        return 5000;
    }
}