package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.placements.configs.SingleIntConfig;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.SimplePlacement;

import java.util.Random;
import java.util.stream.Stream;

public class OffsetYPlacement extends SimplePlacement<SingleIntConfig>
{
    public OffsetYPlacement(Codec<SingleIntConfig> config) {
		super(config);
    }

	@Override
	public final Stream<BlockPos> place(Random random, SingleIntConfig config, BlockPos pos) {
		int x = pos.getX();
		int z = pos.getZ();
		int y = config.yOffset + pos.getY();
		return Stream.of(new BlockPos(x, y, z));
	}
}