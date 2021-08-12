package com.telepathicgrunt.repurposedstructures.mixin.entities;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(targets = "net/minecraft/world/entity/animal/Dolphin$DolphinSwimToTreasureGoal")
public abstract class DolphinEntityDolphinSwimToTreasureGoalMixin {

    @Final
    @Shadow
    private Dolphin dolphin;

    /**
     * Allow Dolphins to find Ocean Pyramids
     * @author TelepathicGrunt
     */
    @ModifyArg(method = "Lnet/minecraft/world/entity/animal/Dolphin$DolphinSwimToTreasureGoal;start()V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;findNearestMapFeature(Lnet/minecraft/world/level/levelgen/feature/StructureFeature;Lnet/minecraft/core/BlockPos;IZ)Lnet/minecraft/core/BlockPos;", ordinal = 0))
    private StructureFeature<?> repurposedstructures_dolphinStructureLocate(StructureFeature<?> structure){
        if(RepurposedStructures.RSAllConfig.RSPyramidsConfig.pyramidOceanAverageChunkDistance != 1001 && dolphin.level.random.nextFloat() < 0.24f){
            return RSStructures.PYRAMID_OCEAN;
        }
        return structure;
    }
}
