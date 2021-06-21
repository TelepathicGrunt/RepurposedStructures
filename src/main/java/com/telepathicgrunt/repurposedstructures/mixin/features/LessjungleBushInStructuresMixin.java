package com.telepathicgrunt.repurposedstructures.mixin.features;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(TreeFeature.class)
public class LessjungleBushInStructuresMixin {

    @Inject(
            method = "generate(Lnet/minecraft/world/gen/feature/util/FeatureContext;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_lessJungleBushInStructures(FeatureContext<TreeFeatureConfig> context, CallbackInfoReturnable<Boolean> cir) {
        // Detect jungle bush like tree
        if(context.getConfig().foliagePlacer instanceof BushFoliagePlacer && context.getConfig().minimumSize.getMinClippedHeight().orElse(0) < 2){
            // Rate for removal of bush
            if(context.getRandom().nextFloat() < 0.9f){
                ChunkSectionPos chunkPos = ChunkSectionPos.from(context.getOrigin());
                for (StructureFeature<?> structure : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.LESS_JUNGLE_BUSH)) {
                    if (context.getWorld().getStructures(chunkPos, structure).findAny().isPresent()) {
                        cir.setReturnValue(false);
                    }
                }
            }
        }
    }
}
