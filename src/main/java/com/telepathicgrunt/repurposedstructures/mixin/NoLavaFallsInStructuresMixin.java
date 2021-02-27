package com.telepathicgrunt.repurposedstructures.mixin;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.feature.SpringFeature;
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
    private void noLava(ISeedReader structureWorldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, LiquidsConfig springFeatureConfig, CallbackInfoReturnable<Boolean> cir) {
        if(springFeatureConfig.state.isTagged(FluidTags.LAVA)) {
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for(Direction face : Direction.Plane.HORIZONTAL){
                if(structureWorldAccess.getStructures(SectionPos.from(mutable.setPos(blockPos).move(face)), RSStructures.ICY_MINESHAFT.get()).findAny().isPresent()){
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
