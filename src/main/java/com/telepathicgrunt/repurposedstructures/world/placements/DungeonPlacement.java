package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorContext;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;

import java.util.Random;
import java.util.stream.Stream;

public class DungeonPlacement extends Decorator<RangeDecoratorConfig>
{
    public DungeonPlacement(Codec<RangeDecoratorConfig> config) {
		super(config);
    }

	@Override
	public final Stream<BlockPos> getPositions(DecoratorContext context, Random random, RangeDecoratorConfig config, BlockPos pos) {
		int x = random.nextInt(16) + pos.getX();
		int z = random.nextInt(16) + pos.getZ();
		int y = config.heightProvider.get(random, context);
		return Stream.of(new BlockPos(x, y, z));
	}
}