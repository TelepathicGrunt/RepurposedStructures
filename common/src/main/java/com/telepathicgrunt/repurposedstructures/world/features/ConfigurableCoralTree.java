package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.ConfigurableCoralConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.List;

public class ConfigurableCoralTree extends ConfigurableCoral {
    public ConfigurableCoralTree(Codec<ConfigurableCoralConfig> codec) {
        super(codec);
    }

    @Override
    protected boolean placeFeature(LevelAccessor levelAccessor, RandomSource randomSource, BlockPos blockPos, BlockState blockState, FeaturePlaceContext<ConfigurableCoralConfig> featurePlaceContext) {
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
        int i = randomSource.nextInt(3) + 1;
        for (int j = 0; j < i; ++j) {
            if (!this.placeCoralBlock(levelAccessor, randomSource, mutableBlockPos, blockState, featurePlaceContext)) {
                return true;
            }
            mutableBlockPos.move(Direction.UP);
        }
        BlockPos blockPos2 = mutableBlockPos.immutable();
        int k = randomSource.nextInt(3) + 2;
        List<Direction> list = Direction.Plane.HORIZONTAL.shuffledCopy(randomSource);
        List<Direction> list2 = list.subList(0, k);
        for (Direction direction : list2) {
            mutableBlockPos.set(blockPos2);
            mutableBlockPos.move(direction);
            int l = randomSource.nextInt(5) + 2;
            int m = 0;
            for (int n = 0; n < l && this.placeCoralBlock(levelAccessor, randomSource, mutableBlockPos, blockState, featurePlaceContext); ++n) {
                mutableBlockPos.move(Direction.UP);
                if (n != 0 && (++m < 2 || !(randomSource.nextFloat() < 0.25f))) continue;
                mutableBlockPos.move(direction);
                m = 0;
            }
        }
        return true;
    }
}
