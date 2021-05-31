package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;


@Mixin(TreeFeature.class)
public class LessjungleBushInStructuresMixin {

    @Inject(
            method = "generate(Lnet/minecraft/world/StructureWorldAccess;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/feature/TreeFeatureConfig;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_lessJungleBushInStructures(StructureWorldAccess structureWorldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, TreeFeatureConfig config, CallbackInfoReturnable<Boolean> cir) {
        // Detect jungle bush like tree
        if(config.foliagePlacer instanceof BushFoliagePlacer && config.minimumSize.getMinClippedHeight().orElse(0) < 2){
            // Rate for removal of bush
            if(random.nextFloat() < 0.9f){
                ChunkSectionPos chunkPos = ChunkSectionPos.from(blockPos);
                for (StructureFeature<?> structure : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.LESS_JUNGLE_BUSH)) {
                    if (structureWorldAccess.getStructures(chunkPos, structure).findAny().isPresent()) {
                        cir.setReturnValue(false);
                    }
                }
            }
        }
    }
}
