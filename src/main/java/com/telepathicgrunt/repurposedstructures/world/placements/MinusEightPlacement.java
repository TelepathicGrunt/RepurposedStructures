package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSPlacements;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.Stream;

public class MinusEightPlacement extends PlacementModifier {
	private static final MinusEightPlacement INSTANCE = new MinusEightPlacement();
	public static final Codec<MinusEightPlacement> CODEC = Codec.unit(() -> INSTANCE);

	public static MinusEightPlacement subtractedEight() {
		return INSTANCE;
	}

	@Override
	public Stream<BlockPos> getPositions(PlacementContext placementContext, RandomSource random, BlockPos blockPos) {
		return Stream.of(new BlockPos(blockPos.getX() - 8, blockPos.getY(), blockPos.getZ() - 8));
	}

	@Override
	public PlacementModifierType<?> type() {
		return RSPlacements.MINUS_EIGHT_PLACEMENT;
	}
}
