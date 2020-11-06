package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;


@Mixin(LakeFeature.class)
public class NoVillageLakesMixin {

    @Inject(
            method = "generate",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/StructureWorldAccess;getStructures(Lnet/minecraft/util/math/ChunkSectionPos;Lnet/minecraft/world/gen/feature/StructureFeature;)Ljava/util/stream/Stream;"),
            cancellable = true
    )
    private void checkForRSVillages(StructureWorldAccess structureWorldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, SingleStateFeatureConfig singleStateFeatureConfig, CallbackInfoReturnable<Boolean> cir) {

        for (StructureFeature<?> village : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.OVERWORLD_VILLAGE)) {
            if (structureWorldAccess.getStructures(ChunkSectionPos.from(blockPos), village).findAny().isPresent()) {
                cir.setReturnValue(false);
            }
        }
    }
}
