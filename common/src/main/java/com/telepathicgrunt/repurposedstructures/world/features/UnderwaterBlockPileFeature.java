package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;


public record UnderwaterBlockPileFeature(Holder<BlockStateProvider> stateProvider) implements Feature {

    public static final MapCodec<UnderwaterBlockPileFeature> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(BlockStateProvider.CODEC.fieldOf("state_provider").forGetter(UnderwaterBlockPileFeature::stateProvider)
        ).apply(i, UnderwaterBlockPileFeature::new)
    );

    @Override
    public MapCodec<UnderwaterBlockPileFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {
        if (origin.getY() < level.getMinY() + 5) {
            return false;
        }
        else {
            int i = 2 + random.nextInt(2);
            int j = 2 + random.nextInt(2);

            for(BlockPos blockpos1 : BlockPos.betweenClosed(origin.offset(-i, 0, -j), origin.offset(i, 1, j))) {
                int xOffset = origin.getX() - blockpos1.getX();
                int zOffset = origin.getZ() - blockpos1.getZ();
                if ((float)(xOffset * xOffset + zOffset * zOffset) <= random.nextFloat() * 10.0F - random.nextFloat() * 6.0F) {
                    this.tryPlaceBlock(level, blockpos1, random);
                }
                else if ((double) random.nextFloat() < 0.031D) {
                    this.tryPlaceBlock(level, blockpos1, random);
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

    private void tryPlaceBlock(WorldGenLevel level, BlockPos blockPos, RandomSource randomSource) {
        boolean isWaterLocation = level.isWaterAt(blockPos);
        if ((level.isEmptyBlock(blockPos) || isWaterLocation) && this.mayPlaceOn(level, blockPos, randomSource)) {
            BlockState resultState = stateProvider.value().getState(level, randomSource, blockPos);
            if (isWaterLocation && resultState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                resultState = resultState.setValue(BlockStateProperties.WATERLOGGED, true);
            }
            level.setBlock(blockPos, resultState, 4);
        }
    }
}