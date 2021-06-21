package com.telepathicgrunt.repurposedstructures.mixin.features;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.SpringFeature;
import net.minecraft.world.gen.feature.SpringFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(SpringFeature.class)
public class NoLavaFallsInStructuresMixin {

    @Inject(
            method = "generate(Lnet/minecraft/world/gen/feature/util/FeatureContext;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_noLavaInStructures(FeatureContext<SpringFeatureConfig> context, CallbackInfoReturnable<Boolean> cir) {
        if(context.getConfig().state.isIn(FluidTags.LAVA)) {
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            ChunkSectionPos chunkPos;
            for(Direction face : Direction.Type.HORIZONTAL){
                mutable.set(context.getOrigin()).move(face);
                chunkPos = ChunkSectionPos.from(mutable);
                for (StructureFeature<?> structure : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NO_LAVAFALLS)) {
                    if (context.getWorld().getStructures(chunkPos, structure).findAny().isPresent()) {
                        cir.setReturnValue(false);
                    }
                }
            }
        }
    }
}
