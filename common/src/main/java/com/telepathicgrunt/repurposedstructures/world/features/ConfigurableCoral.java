package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import com.telepathicgrunt.repurposedstructures.world.features.configs.ConfigurableCoralConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SeaPickleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public abstract class ConfigurableCoral extends Feature<ConfigurableCoralConfig> {

    public ConfigurableCoral(Codec<ConfigurableCoralConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<ConfigurableCoralConfig> featurePlaceContext) {
        RandomSource randomSource = featurePlaceContext.random();
        WorldGenLevel level = featurePlaceContext.level();
        BlockPos blockPos = featurePlaceContext.origin();
        int selectionSize = featurePlaceContext.config().mainBlocks.size();
        if (selectionSize == 0) {
            return false;
        }
        BlockState state = featurePlaceContext.config().mainBlocks.get(randomSource.nextInt(selectionSize)).value().defaultBlockState();
        return this.placeFeature(
                level,
                randomSource,
                blockPos,
                state,
                featurePlaceContext);
    }

    protected abstract boolean placeFeature(LevelAccessor level, RandomSource randomSource, BlockPos blockPos, BlockState blockState, FeaturePlaceContext<ConfigurableCoralConfig> featurePlaceContext);

    protected boolean placeCoralBlock(LevelAccessor levelAccessor, RandomSource randomSource, BlockPos blockPos, BlockState blockState, FeaturePlaceContext<ConfigurableCoralConfig> featurePlaceContext) {
        BlockPos blockPos2 = blockPos.above();
        BlockState blockState2 = levelAccessor.getBlockState(blockPos);
        if (!blockState2.getFluidState().is(FluidTags.WATER) && (!blockState2.is(BlockTags.CORALS) && !blockState2.is(RSTags.DEAD_CORALS)) || !levelAccessor.getFluidState(blockPos2).is(FluidTags.WATER)) {
            return false;
        }
        levelAccessor.setBlock(blockPos, blockState, 3);
        if (randomSource.nextFloat() < 0.25f) {
            int selectionSize = featurePlaceContext.config().floorBlocks.size();
            if (selectionSize == 0) {
                return false;
            }
            BlockState floorState = featurePlaceContext.config().floorBlocks.get(randomSource.nextInt(selectionSize)).value().defaultBlockState();
            levelAccessor.setBlock(blockPos2, floorState, 3);
        }
        else if (randomSource.nextFloat() < 0.05f) {
            levelAccessor.setBlock(blockPos2, Blocks.SEA_PICKLE.defaultBlockState().setValue(SeaPickleBlock.PICKLES, randomSource.nextInt(4) + 1), 2);
        }

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos blockPos3;
            if (!(randomSource.nextFloat() < 0.2f) || !levelAccessor.getFluidState(blockPos3 = blockPos.relative(direction)).is(FluidTags.WATER)) continue;

            int selectionSize = featurePlaceContext.config().wallBlocks.size();
            if (selectionSize == 0) {
                return false;
            }
            BlockState wallState = featurePlaceContext.config().wallBlocks.get(randomSource.nextInt(selectionSize)).value().defaultBlockState();
            if (wallState.hasProperty(BaseCoralWallFanBlock.FACING)) {
                wallState = wallState.setValue(BaseCoralWallFanBlock.FACING, direction);
            }
            levelAccessor.setBlock(blockPos3, wallState, 3);
        }
        return true;
    }
}

