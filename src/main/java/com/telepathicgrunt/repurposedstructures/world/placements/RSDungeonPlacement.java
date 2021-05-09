package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.placement.SimplePlacement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

import java.util.Random;
import java.util.stream.Stream;

public class RSDungeonPlacement extends SimplePlacement<TopSolidRangeConfig>
{
    public RSDungeonPlacement(Codec<TopSolidRangeConfig> config) {
	super(config);
    }

	public Stream<BlockPos> place(Random random, TopSolidRangeConfig config, BlockPos pos) {
		int range = Math.max(config.maximum - config.bottomOffset, 1);
		int x = random.nextInt(16) + pos.getX();
		int z = random.nextInt(16) + pos.getZ();
		int y = random.nextInt(range) + config.bottomOffset;
		return Stream.of(new BlockPos(x, y, z));
    }
}