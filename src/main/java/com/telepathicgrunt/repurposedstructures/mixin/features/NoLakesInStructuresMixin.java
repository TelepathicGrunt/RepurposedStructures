package com.telepathicgrunt.repurposedstructures.mixin.features;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LakeFeature.class)
public class NoLakesInStructuresMixin {

    @Inject(
            method = "generate(Lnet/minecraft/world/gen/feature/util/FeatureContext;)Z",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/StructureWorldAccess;getStructures(Lnet/minecraft/util/math/ChunkSectionPos;Lnet/minecraft/world/gen/feature/StructureFeature;)Ljava/util/stream/Stream;"),
            cancellable = true
    )
    private void repurposedstructures_noLakesInStructures(FeatureContext<SingleStateFeatureConfig> context, CallbackInfoReturnable<Boolean> cir) {
        ChunkSectionPos chunkPos = ChunkSectionPos.from(context.getOrigin());
        for (StructureFeature<?> structure : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NO_LAKES)) {
            if (context.getWorld().getStructures(chunkPos, structure).findAny().isPresent()) {
                cir.setReturnValue(false);
            }
        }
    }
}
