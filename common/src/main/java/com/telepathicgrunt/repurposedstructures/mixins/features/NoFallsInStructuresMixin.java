package com.telepathicgrunt.repurposedstructures.mixins.features;

import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.SpringFeature;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;


@Mixin(SpringFeature.class)
public class NoFallsInStructuresMixin {

    @Inject(
            method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void repurposedstructures_noLavaInStructures(FeaturePlaceContext<SpringConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        if (!(context.level() instanceof WorldGenRegion worldGenRegion)) {
            return;
        }

        if (context.config().state.is(FluidTags.LAVA)) {
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            for (Direction face : Direction.Plane.HORIZONTAL) {
                mutable.set(context.origin()).move(face);

                Registry<Structure> structureRegistry = worldGenRegion.registryAccess().registry(Registries.STRUCTURE).get();

                List<StructureStart> structureStarts = GeneralUtils.inboundsValidStartsForAllStructure(
                        worldGenRegion,
                        mutable,
                        struct -> structureRegistry.getHolderOrThrow(structureRegistry.getResourceKey(struct).get()).is(RSTags.NO_LAVAFALLS));

                if (!structureStarts.isEmpty()) {
                    cir.setReturnValue(false);
                    return;
                }
            }
        }
        else if (context.config().state.is(FluidTags.WATER)) {
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            for(Direction face : Direction.Plane.HORIZONTAL) {
                mutable.set(context.origin()).move(face);

                Registry<Structure> structureRegistry = worldGenRegion.registryAccess().registry(Registries.STRUCTURE).get();

                List<StructureStart> structureStarts = GeneralUtils.inboundsValidStartsForAllStructure(
                        worldGenRegion,
                        mutable,
                        struct -> structureRegistry.getHolderOrThrow(structureRegistry.getResourceKey(struct).get()).is(RSTags.NO_WATERFALLS));

                if (!structureStarts.isEmpty()) {
                    cir.setReturnValue(false);
                    return;
                }
            }
        }
    }
}
