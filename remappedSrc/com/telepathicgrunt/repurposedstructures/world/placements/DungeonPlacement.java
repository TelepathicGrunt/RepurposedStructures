package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import java.util.Random;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.DecorationContext;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;

public class DungeonPlacement extends FeatureDecorator<RangeDecoratorConfiguration>
{
    public DungeonPlacement(Codec<RangeDecoratorConfiguration> config) {
		super(config);
    }

	@Override
	public final Stream<BlockPos> getPositions(DecorationContext context, Random random, RangeDecoratorConfiguration config, BlockPos pos) {
		int x = random.nextInt(16) + pos.getX();
		int z = random.nextInt(16) + pos.getZ();
		int y = config.height.sample(random, context);
		return Stream.of(new BlockPos(x, y, z));
	}
}