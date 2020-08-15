package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorContext;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.decorator.SimpleDecorator;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RSDungeonPlacement extends SimpleDecorator<RangeDecoratorConfig>
{
    public RSDungeonPlacement(Codec<RangeDecoratorConfig> config) {
	super(config);
    }

	@Override
	public Stream<BlockPos> getPositions(Random random, RangeDecoratorConfig config, BlockPos pos) {
		int range = Math.max(config.maximum-config.bottomOffset, 1);
		int x = random.nextInt(16) + pos.getX();
		int z = random.nextInt(16) + pos.getZ();
		int y = random.nextInt(range) + config.bottomOffset;
		return Stream.of(new BlockPos(x, y, z));
	}
}