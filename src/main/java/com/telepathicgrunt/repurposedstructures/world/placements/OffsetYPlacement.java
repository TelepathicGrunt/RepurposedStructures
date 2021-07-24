package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.placements.configs.SingleIntConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.placement.DecorationContext;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;

import java.util.Random;
import java.util.stream.Stream;

public class OffsetYPlacement extends FeatureDecorator<SingleIntConfig>
{
    public OffsetYPlacement(Codec<SingleIntConfig> config) {
		super(config);
    }

	@Override
	public final Stream<BlockPos> getPositions(DecorationContext context, Random random, SingleIntConfig config, BlockPos pos) {
		int x = pos.getX();
		int z = pos.getZ();
		int y = config.yOffset + pos.getY();
		return Stream.of(new BlockPos(x, y, z));
	}
}