package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.RSFeatures;
import com.telepathicgrunt.repurposedstructures.RSStructures;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
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

        for (StructureFeature<DefaultFeatureConfig> village : RSStructures.OVERWORLD_VILLAGE_LIST) {
            if (structureWorldAccess.getStructures(ChunkSectionPos.from(blockPos), village).findAny().isPresent()) {
                cir.setReturnValue(false);
            }
        }
    }
}
