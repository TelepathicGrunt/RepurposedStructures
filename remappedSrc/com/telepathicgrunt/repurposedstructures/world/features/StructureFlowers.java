package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndRangeConfig;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;


public class StructureFlowers extends Feature<StructureTargetAndRangeConfig> {

    public StructureFlowers(Codec<StructureTargetAndRangeConfig> config) {
        super(config);
    }

    private static final List<BlockState> FLOWERS = ImmutableList.of(
            Blocks.LILY_OF_THE_VALLEY.defaultBlockState(),
            Blocks.POPPY.defaultBlockState(),
            Blocks.DANDELION.defaultBlockState(),
            Blocks.CORNFLOWER.defaultBlockState(),
            Blocks.ORANGE_TULIP.defaultBlockState(),
            Blocks.PINK_TULIP.defaultBlockState(),
            Blocks.RED_TULIP.defaultBlockState(),
            Blocks.WHITE_TULIP.defaultBlockState(),
            Blocks.ROSE_BUSH.defaultBlockState(),
            Blocks.LILAC.defaultBlockState(),
            Blocks.PEONY.defaultBlockState()
    );

    @Override
    public boolean place(FeaturePlaceContext<StructureTargetAndRangeConfig> context) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for(int i = 0; i < context.config().attempts; i++){
            mutable.set(context.origin()).move(
                    context.random().nextInt((context.config().range * 2) + 1) - context.config().range,
                    context.random().nextInt(3) - 1,
                    context.random().nextInt((context.config().range * 2) + 1) - context.config().range
            );

            if(context.level().getBlockState(mutable).isAir()){

                BlockState chosenFlower = FLOWERS.get(context.random().nextInt(FLOWERS.size()));

                if(chosenFlower.canSurvive(context.level(), mutable)){

                    if(chosenFlower.getBlock() instanceof DoublePlantBlock && context.level().getBlockState(mutable.above()).isAir()){
                        context.level().setBlock(mutable, chosenFlower.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER), 3);
                        context.level().setBlock(mutable.move(Direction.UP), chosenFlower.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 3);
                    }
                    else{
                        context.level().setBlock(mutable, chosenFlower, 3);
                    }
                }
            }
        }

        return true;
    }
}