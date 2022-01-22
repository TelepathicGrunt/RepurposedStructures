package com.telepathicgrunt.repurposedstructures.mixin.features;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.SpringFeature;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(SpringFeature.class)
public class NoLavaFallsInStructuresMixin {

    @Inject(
            method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_noLavaInStructures(FeaturePlaceContext<SpringConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        if(context.config().state.is(FluidTags.LAVA)) {
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            for(Direction face : Direction.Plane.HORIZONTAL) {
                mutable.set(context.origin()).move(face);
                if (GeneralUtils.inStructureBounds(context.level(), SectionPos.of(mutable), RSStructureTagMap.STRUCTURE_TAGS.NO_LAVAFALLS)) {
                    cir.setReturnValue(false);
                    break;
                }
            }
        }
    }
}
