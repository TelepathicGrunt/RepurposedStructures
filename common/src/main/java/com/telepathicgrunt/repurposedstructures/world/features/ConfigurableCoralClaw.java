package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.ConfigurableCoralConfig;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.List;
import java.util.stream.Stream;

public class ConfigurableCoralClaw extends ConfigurableCoral {
    public ConfigurableCoralClaw(Codec<ConfigurableCoralConfig> codec) {
        super(codec);
    }

    @Override
    protected boolean placeFeature(LevelAccessor levelAccessor, RandomSource randomSource, BlockPos blockPos, BlockState blockState, FeaturePlaceContext<ConfigurableCoralConfig> featurePlaceContext) {
        if (!this.placeCoralBlock(levelAccessor, randomSource, blockPos, blockState, featurePlaceContext)) {
            return false;
        }
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource);
        int i = randomSource.nextInt(2) + 2;
        List<Direction> list = Util.toShuffledList(Stream.of(direction, direction.getClockWise(), direction.getCounterClockWise()), randomSource);
        List<Direction> list2 = list.subList(0, i);
        block0: for (Direction direction2 : list2) {
            int l;
            int k;
            Direction direction3;
            BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
            int j = randomSource.nextInt(2) + 1;
            mutableBlockPos.move(direction2);
            if (direction2 == direction) {
                direction3 = direction;
                k = randomSource.nextInt(3) + 2;
            } else {
                mutableBlockPos.move(Direction.UP);
                Direction[] directions = new Direction[]{direction2, Direction.UP};
                direction3 = Util.getRandom(directions, randomSource);
                k = randomSource.nextInt(3) + 3;
            }
            for (l = 0; l < j && this.placeCoralBlock(levelAccessor, randomSource, mutableBlockPos, blockState, featurePlaceContext); ++l) {
                mutableBlockPos.move(direction3);
            }
            mutableBlockPos.move(direction3.getOpposite());
            mutableBlockPos.move(Direction.UP);
            for (l = 0; l < k; ++l) {
                mutableBlockPos.move(direction);
                if (!this.placeCoralBlock(levelAccessor, randomSource, mutableBlockPos, blockState, featurePlaceContext)) continue block0;
                if (!(randomSource.nextFloat() < 0.25f)) continue;
                mutableBlockPos.move(Direction.UP);
            }
        }
        return true;
    }
}

