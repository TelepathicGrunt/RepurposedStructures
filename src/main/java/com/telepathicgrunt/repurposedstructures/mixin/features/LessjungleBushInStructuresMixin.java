package com.telepathicgrunt.repurposedstructures.mixin.features;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(TreeFeature.class)
public class LessjungleBushInStructuresMixin {

    @Inject(
            method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_lessJungleBushInStructures(FeaturePlaceContext<TreeConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        // Detect jungle bush like tree
        if(context.config().foliagePlacer instanceof BushFoliagePlacer && context.config().minimumSize.minClippedHeight().orElse(0) < 2) {
            // Rate for removal of bush
            if(context.random().nextFloat() < 0.9f) {
                if (GeneralUtils.inStructureBounds(context.level(), SectionPos.of(context.origin()), RSStructureTagMap.STRUCTURE_TAGS.LESS_JUNGLE_BUSH)) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
