package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorContext;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;

import java.util.Random;
import java.util.stream.Stream;

public class SnapToLowerNonAirPlacement extends Decorator<NopeDecoratorConfig>
{
    public SnapToLowerNonAirPlacement(Codec<NopeDecoratorConfig> config) {
		super(config);
    }

	@Override
	public final Stream<BlockPos> getPositions(DecoratorContext context, Random random, NopeDecoratorConfig config, BlockPos pos) {
		BlockPos.Mutable mutable = new BlockPos.Mutable().set(pos);
		while(context.getBlockState(mutable).isAir() && mutable.getY() > context.getMinY()){
			mutable.move(Direction.DOWN);
		}
		return Stream.of(mutable.toImmutable());
	}
}