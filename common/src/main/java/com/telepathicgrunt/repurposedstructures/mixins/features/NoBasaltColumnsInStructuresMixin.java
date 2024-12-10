package com.telepathicgrunt.repurposedstructures.mixins.features;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.BasaltColumnsFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;


@Mixin(BasaltColumnsFeature.class)
public class NoBasaltColumnsInStructuresMixin {

    @WrapOperation(
            method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/feature/BasaltColumnsFeature;canPlaceAt(Lnet/minecraft/world/level/LevelAccessor;ILnet/minecraft/core/BlockPos$MutableBlockPos;)Z")
    )
    private boolean repurposedstructures_noBasaltColumnsInStructures1(LevelAccessor levelAccessor, int sealevel, BlockPos.MutableBlockPos mutableBlockPos, Operation<Boolean> original) {
        if (!original.call(levelAccessor, sealevel, mutableBlockPos)) { //canPlaceAt
            return false;
        }

        if (!(levelAccessor instanceof WorldGenRegion worldGenRegion)) {
            return true;
        }

        Registry<Structure> structureRegistry = worldGenRegion.registryAccess().lookupOrThrow(Registries.STRUCTURE);

        List<StructureStart> structureStarts = GeneralUtils.inboundsValidStartsForAllStructure(
                worldGenRegion,
                mutableBlockPos,
                struct -> structureRegistry.get(structureRegistry.getResourceKey(struct).get()).get().is(RSTags.NO_BASALT));

        return structureStarts.isEmpty();
    }

    @WrapOperation(
            method = "findSurface(Lnet/minecraft/world/level/LevelAccessor;ILnet/minecraft/core/BlockPos$MutableBlockPos;I)Lnet/minecraft/core/BlockPos;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/feature/BasaltColumnsFeature;canPlaceAt(Lnet/minecraft/world/level/LevelAccessor;ILnet/minecraft/core/BlockPos$MutableBlockPos;)Z")
    )
    private static boolean repurposedstructures_noBasaltColumnsInStructures2(LevelAccessor levelAccessor, int sealevel, BlockPos.MutableBlockPos mutableBlockPos, Operation<Boolean> original) {
        if (!original.call(levelAccessor, sealevel, mutableBlockPos)) { //canPlaceAt
            return false;
        }

        if (!(levelAccessor instanceof WorldGenRegion worldGenRegion)) {
            return true;
        }

        Registry<Structure> structureRegistry = worldGenRegion.registryAccess().lookupOrThrow(Registries.STRUCTURE);

        List<StructureStart> structureStarts = GeneralUtils.inboundsValidStartsForAllStructure(
                worldGenRegion,
                mutableBlockPos,
                struct -> structureRegistry.get(structureRegistry.getResourceKey(struct).get()).get().is(RSTags.NO_BASALT));

        return structureStarts.isEmpty();
    }
}
