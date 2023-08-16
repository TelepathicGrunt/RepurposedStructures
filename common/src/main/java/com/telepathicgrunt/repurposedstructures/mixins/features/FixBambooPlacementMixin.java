package com.telepathicgrunt.repurposedstructures.mixins.features;

import com.telepathicgrunt.repurposedstructures.mixins.world.WorldGenRegionAccessor;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.BambooFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;


@Mixin(BambooFeature.class)
public class FixBambooPlacementMixin {

    @Inject(
            method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/WorldGenLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z", ordinal = 2),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            require = 0
    )
    private void repurposedstructures_fixBambooNonAirCheck(FeaturePlaceContext<ProbabilityFeatureConfiguration> featurePlaceContext,
                                                           CallbackInfoReturnable<Boolean> cir,
                                                           int i,
                                                           BlockPos blockPos,
                                                           WorldGenLevel worldGenLevel,
                                                           RandomSource randomSource,
                                                           ProbabilityFeatureConfiguration probabilityFeatureConfiguration,
                                                           BlockPos.MutableBlockPos mutableBlockPos)
    {
        if (!worldGenLevel.isEmptyBlock(mutableBlockPos)) {
            mutableBlockPos.move(Direction.DOWN);
        }
    }
}
