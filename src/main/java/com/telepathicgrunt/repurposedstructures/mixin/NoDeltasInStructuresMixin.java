package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BasaltDeltasFeature;
import net.minecraft.world.gen.feature.structure.BasaltDeltasStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.Random;


@Mixin(BasaltDeltasStructure.class)
public class NoDeltasInStructuresMixin {

    @Inject(
            method = "place(Lnet/minecraft/world/ISeedReader;Lnet/minecraft/world/gen/ChunkGenerator;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/feature/BasaltDeltasFeature;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_noDeltasInStructures(ISeedReader serverWorldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, BasaltDeltasFeature config, CallbackInfoReturnable<Boolean> cir) {
        SectionPos sectionPos = SectionPos.of(blockPos);
        for (Structure<?> structure : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NO_DELTAS)) {
            Optional<? extends StructureStart<?>> structureStart = serverWorldAccess.startsForFeature(sectionPos, structure).findAny();
            boolean checkCenterOnly = RSStructureTagMap.TAGGED_STRUCTURES.get(structure).contains(RSStructureTagMap.STRUCTURE_TAGS.DELTA_CHECK_CENTER_PIECE);
            if (structureStart.isPresent() && (checkCenterOnly ?
                    structureStart.get().getPieces().get(0).getBoundingBox().isInside(blockPos) :
                    structureStart.get().getPieces().stream().anyMatch(box -> box.getBoundingBox().isInside(blockPos))))
            {
                cir.setReturnValue(false);
                break;
            }
        }
    }
}
