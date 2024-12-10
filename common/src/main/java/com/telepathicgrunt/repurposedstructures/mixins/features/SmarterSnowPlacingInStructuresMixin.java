package com.telepathicgrunt.repurposedstructures.mixins.features;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.SnowAndFreezeFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;


@Mixin(value = SnowAndFreezeFeature.class, priority = 1200)
public class SmarterSnowPlacingInStructuresMixin {

    @WrapOperation(
        method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
        at = @At(value = "INVOKE",
                target = "Lnet/minecraft/world/level/biome/Biome;shouldSnow(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z",
                ordinal = 0),
        require = 0
    )
    private boolean repurposedstructures_smarterSnowPlacingInStructures(Biome biome, LevelReader level, BlockPos position, Operation<Boolean> original) {
        if (level instanceof WorldGenRegion worldGenRegion) {
            BlockState state = level.getBlockState(position);

            if (state.is(Blocks.SNOW) && state.hasProperty(SnowLayerBlock.LAYERS) && state.getValue(SnowLayerBlock.LAYERS) > 1) {

                Registry<Structure> structureRegistry = worldGenRegion.registryAccess().lookupOrThrow(Registries.STRUCTURE);

                List<StructureStart> structureStarts = GeneralUtils.inboundsValidStartsForAllStructure(
                        worldGenRegion,
                        position,
                        struct -> structureRegistry.get(structureRegistry.getResourceKey(struct).get()).get().is(RSTags.SMARTER_SNOW_PLACING));

                if (!structureStarts.isEmpty()) {
                    return false;
                }
            }
        }

        return original.call(biome, level, position);
    }
}
