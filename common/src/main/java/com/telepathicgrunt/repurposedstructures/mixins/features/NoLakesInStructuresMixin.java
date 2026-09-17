package com.telepathicgrunt.repurposedstructures.mixins.features;

import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;


@Mixin(LakeFeature.class)
public class NoLakesInStructuresMixin {

    @Inject(
            method = "place(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_noLakesInStructures(
            WorldGenLevel level,
            ChunkGenerator chunkGenerator,
            RandomSource random,
            BlockPos origin,
            CallbackInfoReturnable<Boolean> cir)
    {
        if (!(level instanceof WorldGenRegion worldGenRegion)) {
            return;
        }

        Registry<Structure> structureRegistry = worldGenRegion.registryAccess().lookupOrThrow(Registries.STRUCTURE);

        List<StructureStart> structureStarts = GeneralUtils.inboundsValidStartsForAllStructure(
                worldGenRegion,
                origin,
                struct -> structureRegistry.get(structureRegistry.getResourceKey(struct).get()).get().is(RSTags.NO_LAKES));

        if (!structureStarts.isEmpty()) {
            cir.setReturnValue(false);
            return;
        }
    }
}
