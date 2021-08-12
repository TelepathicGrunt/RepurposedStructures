package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.world.gen.feature.structure.Structure;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(targets = "net/minecraft/entity/passive/DolphinEntity$SwimToTreasureGoal")
public abstract class DolphinEntitySwimToTreasureGoalMixin {

    @Final
    @Shadow
    private DolphinEntity dolphin;

    /**
     * Allow Dolphins to find Ocean Pyramids
     * @author TelepathicGrunt
     */
    @ModifyArg(method = "Lnet/minecraft/entity/passive/DolphinEntity$SwimToTreasureGoal;start()V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/server/ServerWorld;findNearestMapFeature(Lnet/minecraft/world/gen/feature/structure/Structure;Lnet/minecraft/util/math/BlockPos;IZ)Lnet/minecraft/util/math/BlockPos;", ordinal = 0))
    private Structure<?> repurposedstructures_dolphinStructureLocate(Structure<?> structure){
        if(RepurposedStructures.RSPyramidsConfig.pyramidOceanMaxChunkDistance.get() != 1001 && dolphin.level.random.nextFloat() < 0.24f){
            return RSStructures.PYRAMID_OCEAN.get();
        }
        return structure;
    }
}
