package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;
import net.minecraft.world.gen.decorator.SimpleDecorator;

import java.util.Random;
import java.util.stream.Stream;

public class RSMinusEightPlacement extends SimpleDecorator<NopeDecoratorConfig>
{
    public RSMinusEightPlacement(Codec<NopeDecoratorConfig> config) {
	super(config);
    }

	@Override
	public Stream<BlockPos> getPositions(Random random, NopeDecoratorConfig config, BlockPos pos) {
		return Stream.of(pos.add(-8, 0, -8));
	}
}