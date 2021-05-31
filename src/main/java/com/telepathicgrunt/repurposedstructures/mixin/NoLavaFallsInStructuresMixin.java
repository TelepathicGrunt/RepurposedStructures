package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.SpringFeature;
import net.minecraft.world.gen.feature.SpringFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;


@Mixin(SpringFeature.class)
public class NoLavaFallsInStructuresMixin {

    @Inject(
            method = "generate",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_noLava(StructureWorldAccess structureWorldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, SpringFeatureConfig springFeatureConfig, CallbackInfoReturnable<Boolean> cir) {
        if(springFeatureConfig.state.isIn(FluidTags.LAVA)) {
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            ChunkSectionPos chunkPos;
            for(Direction face : Direction.Type.HORIZONTAL){
                mutable.set(blockPos).move(face);
                chunkPos = ChunkSectionPos.from(mutable);
                for (StructureFeature<?> structure : RSStructureTagMap.REVERSED_TAGGED_STRUCTURES.get(RSStructureTagMap.STRUCTURE_TAGS.NO_LAVAFALLS)) {
                    if (structureWorldAccess.getStructures(chunkPos, structure).findAny().isPresent()) {
                        cir.setReturnValue(false);
                    }
                }
            }
        }
    }
}
