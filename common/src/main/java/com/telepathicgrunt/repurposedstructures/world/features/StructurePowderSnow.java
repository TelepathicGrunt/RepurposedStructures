package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.primitives.Doubles;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.utils.OpenSimplex2F;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureRangeConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;


public class StructurePowderSnow extends Feature<StructureRangeConfig> {

    protected long seed;
    private OpenSimplex2F noiseGenerator = null;

    public StructurePowderSnow(Codec<StructureRangeConfig> config) {
        super(config);
    }

    public void setSeed(long seed) {
        if (this.seed != seed || noiseGenerator == null) {
            noiseGenerator = new OpenSimplex2F(seed);
            this.seed = seed;
        }
    }

    @Override
    public boolean place(FeaturePlaceContext<StructureRangeConfig> context) {
        setSeed(context.level().getSeed());

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        int range = context.config().range;
        double thresholds = range / 9d;
        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                mutable.set(context.origin()).move(x, -1, z);
                double noiseManip = noiseGenerator.noise3_Classic(mutable.getX() / 8d, 0, mutable.getZ() / 8d);
                double distance = Math.sqrt(x * x + z * z) + noiseManip;
                if (distance < range - 0.1d) {
                    for(int i = 0; i < 2; i++) {
                        mutable.move(Direction.DOWN);
                        BlockState belowState = context.level().getBlockState(mutable);
                        if(belowState.canOcclude() && !belowState.is(Blocks.SNOW)) {
                            mutable.move(Direction.UP);
                            break;
                        }
                    }

                    BlockState currentState = context.level().getBlockState(mutable);
                    boolean isSnowLayer = currentState.is(Blocks.SNOW);

                    if (isSnowLayer || currentState.isAir() || currentState.is(Blocks.SNOW_BLOCK)) {
                        int stage = (int) Doubles.constrainToRange(9 - (distance / thresholds), 0, 9);
                        if (isSnowLayer) {
                            int originalSnowLayer = currentState.getValue(SnowLayerBlock.LAYERS);
                            stage = Math.max(stage, originalSnowLayer);
                        }
                        else if (currentState.is(Blocks.SNOW_BLOCK)) {
                            stage = 9;
                        }
                        else if (currentState.is(Blocks.POWDER_SNOW)) {
                            stage = 9;
                        }

                        BlockState chosenBlock;
                        if (stage == 9) {
                            chosenBlock = Blocks.POWDER_SNOW.defaultBlockState();
                        }
                        else if (stage == 8) {
                            chosenBlock = Blocks.SNOW_BLOCK.defaultBlockState();
                        }
                        else {
                            chosenBlock = Blocks.SNOW.defaultBlockState().setValue(SnowLayerBlock.LAYERS, (int) Doubles.constrainToRange(stage, 1, 8));
                        }

                        if (currentState.is(Blocks.SNOW) && !chosenBlock.canSurvive(context.level(), mutable)) {
                            mutable.move(Direction.DOWN);
                            BlockState belowState = context.level().getBlockState(mutable);
                            if (belowState.is(Blocks.DIRT_PATH) || belowState.is(BlockTags.DIRT) || belowState.is(BlockTags.ICE) || (belowState.is(Blocks.POWDER_SNOW))) {
                                context.level().setBlock(mutable, Blocks.SNOW_BLOCK.defaultBlockState(), 3);
                            }
                            mutable.move(Direction.UP);
                        }

                        if (Blocks.SNOW.canSurvive(Blocks.SNOW.defaultBlockState(), context.level(), mutable)) {
                            context.level().setBlock(mutable, chosenBlock, 3);
                        }

                        if (currentState.is(Blocks.SNOW_BLOCK) || currentState.is(Blocks.POWDER_SNOW)) {
                            mutable.move(Direction.DOWN);
                            BlockState belowState = context.level().getBlockState(mutable);
                            if (belowState.is(Blocks.DIRT_PATH) || belowState.is(BlockTags.DIRT) || belowState.is(BlockTags.ICE)) {
                                context.level().setBlock(mutable, Blocks.POWDER_SNOW.defaultBlockState(), 3);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}