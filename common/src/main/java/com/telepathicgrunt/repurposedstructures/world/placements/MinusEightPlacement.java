package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.function.Consumer;

public record MinusEightPlacement() implements PlacementModifier {
	private static final MinusEightPlacement INSTANCE = new MinusEightPlacement();
	public static final MapCodec<MinusEightPlacement> CODEC = MapCodec.unit(() -> INSTANCE);

	@Override
	public void modify(PlacementContext placementContext, RandomSource random, BlockPos blockPos, Consumer<BlockPos> output) {
		output.accept(new BlockPos(blockPos.getX() - 8, blockPos.getY(), blockPos.getZ() - 8));
	}

	@Override
	public MapCodec<MinusEightPlacement> codec() {
		return CODEC;
	}
}
