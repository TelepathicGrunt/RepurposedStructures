package com.telepathicgrunt.repurposedstructures.mixins.features;

import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.SpringFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;


@Mixin(SpringFeature.class)
public class NoFallsInStructuresMixin {

    @Inject(
            method = "place(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_noLavaInStructures(
            WorldGenLevel level,
            ChunkGenerator chunkGenerator,
            RandomSource random,
            BlockPos origin,
            CallbackInfoReturnable<Boolean> cir)
    {
        if (!(level instanceof WorldGenRegion worldGenRegion)) {
            return;
        }

        FluidState fluidState = ((SpringFeature)(Object)(this)).state();
        if (fluidState.is(FluidTags.LAVA)) {
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            for (Direction face : Direction.Plane.HORIZONTAL) {
                mutable.set(origin).move(face);

                Registry<Structure> structureRegistry = worldGenRegion.registryAccess().lookupOrThrow(Registries.STRUCTURE);

                List<StructureStart> structureStarts = GeneralUtils.inboundsValidStartsForAllStructure(
                        worldGenRegion,
                        mutable,
                        struct -> structureRegistry.get(structureRegistry.getResourceKey(struct).get()).get().is(RSTags.NO_LAVAFALLS));

                if (!structureStarts.isEmpty()) {
                    cir.setReturnValue(false);
                    return;
                }
            }
        }
        else if (fluidState.is(FluidTags.WATER)) {
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            for(Direction face : Direction.Plane.HORIZONTAL) {
                mutable.set(origin).move(face);

                Registry<Structure> structureRegistry = worldGenRegion.registryAccess().lookupOrThrow(Registries.STRUCTURE);

                List<StructureStart> structureStarts = GeneralUtils.inboundsValidStartsForAllStructure(
                        worldGenRegion,
                        mutable,
                        struct -> structureRegistry.get(structureRegistry.getResourceKey(struct).get()).get().is(RSTags.NO_WATERFALLS));

                if (!structureStarts.isEmpty()) {
                    cir.setReturnValue(false);
                    return;
                }
            }
        }
    }
}
