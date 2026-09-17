package com.telepathicgrunt.repurposedstructures.world.features;

import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SeaPickleBlock;
import net.minecraft.world.level.block.state.BlockState;

public abstract class ConfigurableCoral {

    public boolean placeStart(
        final WorldGenLevel level,
        final RandomSource random,
        final BlockPos origin,
        HolderSet<Block> mainBlocks,
        HolderSet<Block> wallBlocks,
        HolderSet<Block> floorBlocks)
    {

        int selectionSize = mainBlocks.size();
        if (selectionSize == 0) {
            return false;
        }
        BlockState state = mainBlocks.get(random.nextInt(selectionSize)).value().defaultBlockState();
        return this.placeFeature(
                level,
                random,
                origin,
                state,
                mainBlocks,
                wallBlocks,
                floorBlocks);
    }

    protected abstract boolean placeFeature(
            LevelAccessor level,
            RandomSource randomSource,
            BlockPos blockPos,
            BlockState blockState,
            HolderSet<Block> mainBlocks,
            HolderSet<Block> wallBlocks,
            HolderSet<Block> floorBlocks);

    protected boolean placeCoralBlock(
            LevelAccessor levelAccessor,
            RandomSource randomSource,
            BlockPos blockPos,
            BlockState blockState,
            HolderSet<Block> mainBlocks,
            HolderSet<Block> wallBlocks,
            HolderSet<Block> floorBlocks)
    {
        BlockPos blockPos2 = blockPos.above();
        BlockState blockState2 = levelAccessor.getBlockState(blockPos);
        if (!blockState2.getFluidState().is(FluidTags.WATER) && (!blockState2.is(BlockTags.CORALS) && !blockState2.is(RSTags.DEAD_CORALS)) || !levelAccessor.getFluidState(blockPos2).is(FluidTags.WATER)) {
            return false;
        }
        levelAccessor.setBlock(blockPos, blockState, 3);
        if (randomSource.nextFloat() < 0.25f) {
            int selectionSize = floorBlocks.size();
            if (selectionSize == 0) {
                return false;
            }
            BlockState floorState = floorBlocks.get(randomSource.nextInt(selectionSize)).value().defaultBlockState();
            levelAccessor.setBlock(blockPos2, floorState, 3);
        }
        else if (randomSource.nextFloat() < 0.05f) {
            levelAccessor.setBlock(blockPos2, Blocks.SEA_PICKLE.defaultBlockState().setValue(SeaPickleBlock.PICKLES, randomSource.nextInt(4) + 1), 2);
        }

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos blockPos3;
            if (!(randomSource.nextFloat() < 0.2f) || !levelAccessor.getFluidState(blockPos3 = blockPos.relative(direction)).is(FluidTags.WATER)) continue;

            int selectionSize = wallBlocks.size();
            if (selectionSize == 0) {
                return false;
            }
            BlockState wallState = wallBlocks.get(randomSource.nextInt(selectionSize)).value().defaultBlockState();
            if (wallState.hasProperty(BaseCoralWallFanBlock.FACING)) {
                wallState = wallState.setValue(BaseCoralWallFanBlock.FACING, direction);
            }
            levelAccessor.setBlock(blockPos3, wallState, 3);
        }
        return true;
    }
}

