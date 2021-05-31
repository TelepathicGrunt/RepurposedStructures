package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.feature.SpringFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;


@Mixin(SpringFeature.class)
public class NoLavaFallsInStructuresMixin {

    @Inject(
            method = "place",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void rs_noLava(ISeedReader structureWorldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, LiquidsConfig springFeatureConfig, CallbackInfoReturnable<Boolean> cir) {
        if(springFeatureConfig.state.is(FluidTags.LAVA)) {
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            SectionPos sectionPos;
            for(Direction face : Direction.Plane.HORIZONTAL){
                sectionPos = SectionPos.of(blockPos);
                for (Structure<?> structure : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NO_LAVAFALLS)) {
                    if (structureWorldAccess.startsForFeature(sectionPos, structure).findAny().isPresent()) {
                        cir.setReturnValue(false);
                        break;
                    }
                }
            }
        }
    }
}
