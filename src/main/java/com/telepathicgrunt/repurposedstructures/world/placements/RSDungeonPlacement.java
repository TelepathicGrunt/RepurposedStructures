package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorContext;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RSDungeonPlacement extends Decorator<RangeDecoratorConfig>
{
    public RSDungeonPlacement(Codec<RangeDecoratorConfig> config) {
	super(config);
    }

	@Override
	public Stream<BlockPos> getPositions(DecoratorContext context, Random random, RangeDecoratorConfig config, BlockPos pos) {
		int range = Math.max(config.maximum-config.bottomOffset, 1);
		return IntStream.range(0, 1).mapToObj((index) -> {
			int x = random.nextInt(16) + pos.getX();
			int z = random.nextInt(16) + pos.getZ();
			int y = random.nextInt(range)+config.bottomOffset;
			return new BlockPos(x, y, z);
		});
	}
}