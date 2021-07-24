package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.configurations.NoneDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.DecorationContext;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;

import java.util.Random;
import java.util.stream.Stream;

public class MinusEightPlacement extends FeatureDecorator<NoneDecoratorConfiguration>
{
    public MinusEightPlacement(Codec<NoneDecoratorConfiguration> config) {
	super(config);
    }

	@Override
	public Stream<BlockPos> getPositions(DecorationContext context, Random random, NoneDecoratorConfiguration config, BlockPos pos) {
		return Stream.of(pos.offset(-8, 0, -8));
	}
}