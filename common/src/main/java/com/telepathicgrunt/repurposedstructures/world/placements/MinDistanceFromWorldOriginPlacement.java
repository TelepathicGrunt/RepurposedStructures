package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.function.Consumer;

public record MinDistanceFromWorldOriginPlacement(int minDistanceFromWorldOrigin) implements PlacementModifier {
    public static final MapCodec<MinDistanceFromWorldOriginPlacement> CODEC = ExtraCodecs.NON_NEGATIVE_INT.fieldOf("min_distance_from_world_origin").xmap(MinDistanceFromWorldOriginPlacement::new, countPlacement -> countPlacement.minDistanceFromWorldOrigin);

    @Override
    public void modify(PlacementContext placementContext, RandomSource randomSource, BlockPos blockPos, Consumer<BlockPos> output) {
        if (blockPos.distManhattan(BlockPos.ZERO) > minDistanceFromWorldOrigin) {
            output.accept(blockPos);
        }
    }

    @Override
    public MapCodec<MinDistanceFromWorldOriginPlacement> codec() {
        return CODEC;
    }
}
