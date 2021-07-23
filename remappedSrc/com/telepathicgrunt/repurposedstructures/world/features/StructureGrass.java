package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndRangeConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;


public class StructureGrass extends Feature<StructureTargetAndRangeConfig> {

    public StructureGrass(Codec<StructureTargetAndRangeConfig> config) {
        super(config);
    }


    @Override
    public boolean place(FeaturePlaceContext<StructureTargetAndRangeConfig> context) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState grass = Blocks.GRASS.defaultBlockState();
        BlockState tallGrass = Blocks.TALL_GRASS.defaultBlockState();

        for(int i = 0; i < context.config().attempts; i++){
            mutable.set(context.origin()).move(
                    context.random().nextInt((context.config().range * 2) + 1) - context.config().range,
                    context.random().nextInt(3) - 1,
                    context.random().nextInt((context.config().range * 2) + 1) - context.config().range
            );

            if(context.level().getBlockState(mutable).isAir()){
                if((context.random().nextFloat() < 0.45f || !context.level().getBlockState(mutable.above()).isAir()) && grass.canSurvive(context.level(), mutable)){

                    context.level().setBlock(mutable, grass, 3);
                }
                else if(tallGrass.canSurvive(context.level(), mutable)){

                    context.level().setBlock(mutable, tallGrass.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER), 3);
                    context.level().setBlock(mutable.move(Direction.UP), tallGrass.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 3);
                }
            }
        }

        return true;
    }
}