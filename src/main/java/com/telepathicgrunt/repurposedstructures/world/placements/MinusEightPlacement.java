package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorContext;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;

import java.util.Random;
import java.util.stream.Stream;

public class MinusEightPlacement extends Decorator<NopeDecoratorConfig>
{
    public MinusEightPlacement(Codec<NopeDecoratorConfig> config) {
	super(config);
    }

	@Override
	public Stream<BlockPos> getPositions(DecoratorContext context, Random random, NopeDecoratorConfig config, BlockPos pos) {
		return Stream.of(pos.add(-8, 0, -8));
	}
}