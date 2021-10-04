package com.telepathicgrunt.repurposedstructures.mixin.features;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.levelgen.feature.DeltaFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;


@Mixin(DeltaFeature.class)
public class NoDeltasInStructuresMixin {

    @Inject(
            method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_noDeltasInStructures(FeaturePlaceContext<DeltaFeatureConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        SectionPos sectionPos = SectionPos.of(context.origin());
        for (StructureFeature<?> structure : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NO_DELTAS)) {
            Optional<? extends StructureStart<?>> structureStart = context.level().startsForFeature(sectionPos, structure).findAny();
            boolean checkCenterOnly = RSStructureTagMap.TAGGED_STRUCTURES.get(structure).contains(RSStructureTagMap.STRUCTURE_TAGS.DELTA_CHECK_CENTER_PIECE);
            if (structureStart.isPresent() && (checkCenterOnly ?
                    structureStart.get().getPieces().get(0).getBoundingBox().isInside(context.origin()) :
                    structureStart.get().getPieces().stream().anyMatch(box -> box.getBoundingBox().isInside(context.origin()))))
            {
                cir.setReturnValue(false);
                break;
            }
        }
    }
}
