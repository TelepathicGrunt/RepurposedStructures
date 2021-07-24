package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.configurations.NoneDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.DecorationContext;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;

import java.util.Random;
import java.util.stream.Stream;

public class SnapToLowerNonAirPlacement extends FeatureDecorator<NoneDecoratorConfiguration>
{
    public SnapToLowerNonAirPlacement(Codec<NoneDecoratorConfiguration> config) {
		super(config);
    }

	@Override
	public final Stream<BlockPos> getPositions(DecorationContext context, Random random, NoneDecoratorConfiguration config, BlockPos pos) {
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(pos);
		while(context.getBlockState(mutable).isAir() && mutable.getY() > context.getMinGenY()){
			mutable.move(Direction.DOWN);
		}
		return Stream.of(mutable.immutable());
	}
}