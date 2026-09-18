package com.telepathicgrunt.repurposedstructures.mixins.features;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.SteppedColumnClusterFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;


@Mixin(SteppedColumnClusterFeature.class)
public class NoSteppedColumnClusterFeatureMixin {

    @WrapOperation(
            method = "place(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/feature/SteppedColumnClusterFeature;canPlaceAt(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/core/BlockPos$MutableBlockPos;)Z")
    )
    private boolean repurposedstructures_noBasaltColumnsInStructures1(SteppedColumnClusterFeature instance, WorldGenLevel level, BlockPos.MutableBlockPos cursor, Operation<Boolean> original, @Local(argsOnly = true) RandomSource random) {
        if (!original.call(instance, level, cursor)) { //canPlaceAt
            return false;
        }

        if (!(level instanceof WorldGenRegion worldGenRegion)) {
            return true;
        }

        Registry<Structure> structureRegistry = worldGenRegion.registryAccess().lookupOrThrow(Registries.STRUCTURE);

        List<StructureStart> structureStartsNoColumns = GeneralUtils.inboundsValidStartsForAllStructure(
                worldGenRegion,
                cursor,
                struct -> structureRegistry.get(structureRegistry.getResourceKey(struct).get()).get().is(RSTags.NO_COLUMNS));

        List<StructureStart> structureStartsReducedColumns = GeneralUtils.inboundsValidStartsForAllStructure(
                worldGenRegion,
                cursor,
                struct -> structureRegistry.get(structureRegistry.getResourceKey(struct).get()).get().is(RSTags.REDUCED_COLUMNS));

        return structureStartsNoColumns.isEmpty() && (structureStartsReducedColumns.isEmpty() || random.nextFloat() < 0.25f);
    }

    @WrapOperation(
            method = "findSurface(Lnet/minecraft/world/level/WorldGenLevel;ILnet/minecraft/core/BlockPos$MutableBlockPos;I)Lnet/minecraft/core/BlockPos;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/feature/SteppedColumnClusterFeature;canPlaceAt(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/core/BlockPos$MutableBlockPos;)Z")
    )
    private static boolean repurposedstructures_noBasaltColumnsInStructures2(SteppedColumnClusterFeature instance, WorldGenLevel level, BlockPos.MutableBlockPos cursor, Operation<Boolean> original) {
        if (!original.call(instance, level, cursor)) { //canPlaceAt
            return false;
        }

        if (!(level instanceof WorldGenRegion worldGenRegion)) {
            return true;
        }

        Registry<Structure> structureRegistry = worldGenRegion.registryAccess().lookupOrThrow(Registries.STRUCTURE);

        List<StructureStart> structureStarts = GeneralUtils.inboundsValidStartsForAllStructure(
                worldGenRegion,
                cursor,
                struct -> structureRegistry.get(structureRegistry.getResourceKey(struct).get()).get().is(RSTags.NO_COLUMNS));

        return structureStarts.isEmpty();
    }
}
