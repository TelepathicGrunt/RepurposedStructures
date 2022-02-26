package com.telepathicgrunt.repurposedstructures.mixin.features;

import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LakeFeature.class)
public class NoLakesInStructuresMixin {

    @Inject(
            method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_noLakesInStructures(FeaturePlaceContext<BlockStateConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        long seed = context.level().getSeed();
        ChunkPos chunkPos = new ChunkPos(context.origin());
        ChunkGenerator chunkGenerator = context.chunkGenerator();
        if (chunkGenerator.hasFeatureChunkInRange(RSStructures.VILLAGES_OVERWORLD, seed, chunkPos.x, chunkPos.z, 0) ||
            chunkGenerator.hasFeatureChunkInRange(RSStructures.MANSIONS_OVERWORLD, seed, chunkPos.x, chunkPos.z, 0) ||
            chunkGenerator.hasFeatureChunkInRange(RSStructures.PYRAMIDS_OVERWORLD, seed, chunkPos.x, chunkPos.z, 0) ||
            chunkGenerator.hasFeatureChunkInRange(RSStructures.RUINS_OVERWORLD, seed, chunkPos.x, chunkPos.z, 0) ||
            chunkGenerator.hasFeatureChunkInRange(RSStructures.WITCH_HUTS_OVERWORLD, seed, chunkPos.x, chunkPos.z, 0) ||
            chunkGenerator.hasFeatureChunkInRange(RSStructures.BASTIONS_OVERWORLD, seed, chunkPos.x, chunkPos.z, 0))
        {
            cir.setReturnValue(false);
        }
    }
}
