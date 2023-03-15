package com.telepathicgrunt.repurposedstructures.mixins.forge.structures;

import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StructureTemplatePool.class)
public class StructurePoolMixin {

    /**
     * Increases the weight limit that mojang slapped on that was a workaround for https://bugs.mojang.com/browse/MC-203131
     * @author - TelepathicGrunt
     * @return - The higher weight that is a more reasonable limit.
     */
    @ModifyConstant(
            method = {
                "method_28886",
                "lambda$static$1(Lcom/mojang/serialization/codecs/RecordCodecBuilder$Instance;)Lcom/mojang/datafixers/kinds/App;",
                "Lnet/minecraft/world/level/levelgen/structure/pools/StructureTemplatePool;m_254834_(Lcom/mojang/serialization/codecs/RecordCodecBuilder$Instance;)Lcom/mojang/datafixers/kinds/App;"
            },
            constant = @Constant(intValue = 150),
            require = 0,
            remap = false
    )
    private static int repurposedstructures_increaseWeightLimit(int constant) {
        return 5000;
    }
}