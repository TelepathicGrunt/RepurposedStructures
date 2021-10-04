package com.telepathicgrunt.repurposedstructures.mixin.features;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.foliageplacer.BushFoliagePlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;


@Mixin(TreeFeature.class)
public class LessjungleBushInStructuresMixin {

    @Inject(
            method = "place(Lnet/minecraft/world/ISeedReader;Lnet/minecraft/world/gen/ChunkGenerator;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/feature/BaseTreeFeatureConfig;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_lessJungleBushInStructures(ISeedReader structureWorldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, BaseTreeFeatureConfig config, CallbackInfoReturnable<Boolean> cir) {
        // Detect jungle bush like tree
        if(config.foliagePlacer instanceof BushFoliagePlacer && config.minimumSize.minClippedHeight().orElse(0) < 2){
            // Rate for removal of bush
            if(random.nextFloat() < 0.9f){
                SectionPos sectionPos = SectionPos.of(blockPos);
                for (Structure<?> structure : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.LESS_JUNGLE_BUSH)) {
                    if (structureWorldAccess.startsForFeature(sectionPos, structure).findAny().isPresent()) {
                        cir.setReturnValue(false);
                        break;
                    }
                }
            }
        }
    }
}
