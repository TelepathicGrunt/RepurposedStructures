package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.Random;
import java.util.stream.Stream;

public class SnapToLowerNonAirPlacement extends Placement<NoPlacementConfig>
{
    public SnapToLowerNonAirPlacement(Codec<NoPlacementConfig> config) {
		super(config);
    }

	@Override
	public final Stream<BlockPos> getPositions(WorldDecoratingHelper world, Random random, NoPlacementConfig config, BlockPos pos) {
		BlockPos.Mutable mutable = new BlockPos.Mutable().set(pos);
		while(world.getBlockState(mutable).isAir() && mutable.getY() > 0){
			mutable.move(Direction.DOWN);
		}
		return Stream.of(mutable.immutable());
	}
}