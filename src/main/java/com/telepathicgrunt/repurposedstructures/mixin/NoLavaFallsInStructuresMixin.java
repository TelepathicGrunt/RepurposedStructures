package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructureTagMap;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.*;
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
            cancellable = true,
            require = -1
    )
    private void noLava(StructureWorldAccess structureWorldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, SpringFeatureConfig springFeatureConfig, CallbackInfoReturnable<Boolean> cir) {
        if(springFeatureConfig.state.isIn(FluidTags.LAVA)) {
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for(Direction face : Direction.Type.HORIZONTAL){
                if(structureWorldAccess.getStructures(ChunkSectionPos.from(mutable.set(blockPos).move(face)), RSStructures.ICY_MINESHAFT).findAny().isPresent()){
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
