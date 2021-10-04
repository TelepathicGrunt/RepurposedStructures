package com.telepathicgrunt.repurposedstructures.mixin.features;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.BasaltColumnsFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;


@Mixin(BasaltColumnsFeature.class)
public class NoBasaltColumnsInStructuresMixin {

    @Inject(
            method = "canPlaceAt(Lnet/minecraft/world/level/LevelAccessor;ILnet/minecraft/core/BlockPos$MutableBlockPos;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private static void repurposedstructures_noBasaltColumnsInStructures(LevelAccessor levelAccessor, int seaLevel, BlockPos.MutableBlockPos mutableBlockPos, CallbackInfoReturnable<Boolean> cir) {
        SectionPos sectionPos = SectionPos.of(mutableBlockPos);
        for (StructureFeature<?> structure : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NO_DELTAS)) {
            Optional<? extends StructureStart<?>> structureStart = ((WorldGenLevel)levelAccessor).startsForFeature(sectionPos, structure).findAny();
            boolean checkCenterOnly = RSStructureTagMap.TAGGED_STRUCTURES.get(structure).contains(RSStructureTagMap.STRUCTURE_TAGS.DELTA_CHECK_CENTER_PIECE);
            if (structureStart.isPresent() && (checkCenterOnly ?
                    structureStart.get().getPieces().get(0).getBoundingBox().isInside(mutableBlockPos) :
                    structureStart.get().getPieces().stream().anyMatch(box -> box.getBoundingBox().isInside(mutableBlockPos))))
            {
                cir.setReturnValue(false);
                break;
            }
        }
    }
}
