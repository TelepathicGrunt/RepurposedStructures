package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockPileConfiguration;


public class UnderwaterBlockPileFeature extends Feature<BlockPileConfiguration> {
    public UnderwaterBlockPileFeature(Codec<BlockPileConfiguration> blockPileConfigurationCodec) {
        super(blockPileConfigurationCodec);
    }

    public boolean place(FeaturePlaceContext<BlockPileConfiguration> placeContext) {
        BlockPos blockpos = placeContext.origin();
        WorldGenLevel worldgenlevel = placeContext.level();
        RandomSource randomsource = placeContext.random();
        BlockPileConfiguration blockpileconfiguration = placeContext.config();
        if (blockpos.getY() < worldgenlevel.getMinBuildHeight() + 5) {
            return false;
        }
        else {
            int i = 2 + randomsource.nextInt(2);
            int j = 2 + randomsource.nextInt(2);

            for(BlockPos blockpos1 : BlockPos.betweenClosed(blockpos.offset(-i, 0, -j), blockpos.offset(i, 1, j))) {
                int xOffset = blockpos.getX() - blockpos1.getX();
                int zOffset = blockpos.getZ() - blockpos1.getZ();
                if ((float)(xOffset * xOffset + zOffset * zOffset) <= randomsource.nextFloat() * 10.0F - randomsource.nextFloat() * 6.0F) {
                    this.tryPlaceBlock(worldgenlevel, blockpos1, randomsource, blockpileconfiguration);
                }
                else if ((double)randomsource.nextFloat() < 0.031D) {
                    this.tryPlaceBlock(worldgenlevel, blockpos1, randomsource, blockpileconfiguration);
                }
            }

            return true;
        }
    }

    private boolean mayPlaceOn(LevelAccessor levelAccessor, BlockPos pos, RandomSource randomSource) {
        BlockPos blockpos = pos.below();
        BlockState blockstate = levelAccessor.getBlockState(blockpos);
        return blockstate.is(Blocks.DIRT_PATH) ? randomSource.nextBoolean() : blockstate.isFaceSturdy(levelAccessor, blockpos, Direction.UP);
    }

    private void tryPlaceBlock(LevelAccessor levelAccessor, BlockPos blockPos, RandomSource randomSource, BlockPileConfiguration stateProvider) {
        boolean isWaterLocation = levelAccessor.isWaterAt(blockPos);
        if ((levelAccessor.isEmptyBlock(blockPos) || isWaterLocation) && this.mayPlaceOn(levelAccessor, blockPos, randomSource)) {
            BlockState resultState = stateProvider.stateProvider.getState(randomSource, blockPos);
            if (isWaterLocation && resultState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                resultState = resultState.setValue(BlockStateProperties.WATERLOGGED, true);
            }
            levelAccessor.setBlock(blockPos, resultState, 4);
        }
    }
}