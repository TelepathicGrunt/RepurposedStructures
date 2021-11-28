package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;


public class StructureSeagrass extends Feature<StructureTargetConfig> {

    public StructureSeagrass(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean place(FeaturePlaceContext<StructureTargetConfig> context) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState tallSeagrass = Blocks.TALL_SEAGRASS.defaultBlockState();
        BlockState seagrass = Blocks.SEAGRASS.defaultBlockState();

        for(int i = 0; i < context.config().attempts; i++) {
            mutable.set(context.origin()).move(
                    context.random().nextInt(7) - 3,
                    -1,
                    context.random().nextInt(7) - 3
            );

            boolean isWater = context.level().getBlockState(mutable).is(Blocks.WATER);
            if(!isWater) continue;

            boolean isWaterAbove = context.level().getBlockState(mutable.above()).is(Blocks.WATER);
            if(isWaterAbove && context.random().nextFloat() < 0.33f && tallSeagrass.canSurvive(context.level(), mutable)) {
                

                context.level().setBlock(mutable, tallSeagrass.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.LOWER), 3);
                context.level().setBlock(mutable.move(Direction.UP), tallSeagrass.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER), 3);
            }
            else if(seagrass.canSurvive(context.level(), mutable)) {
                

                context.level().setBlock(mutable, Blocks.SEAGRASS.defaultBlockState(), 3);
            }
        }

        return true;
    }
}