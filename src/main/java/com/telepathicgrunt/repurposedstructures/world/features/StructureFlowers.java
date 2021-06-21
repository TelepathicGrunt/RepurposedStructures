package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndRangeConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;


public class StructureFlowers extends Feature<StructureTargetAndRangeConfig> {

    public StructureFlowers(Codec<StructureTargetAndRangeConfig> config) {
        super(config);
    }

    private static final List<BlockState> FLOWERS = ImmutableList.of(
            Blocks.LILY_OF_THE_VALLEY.getDefaultState(),
            Blocks.POPPY.getDefaultState(),
            Blocks.DANDELION.getDefaultState(),
            Blocks.CORNFLOWER.getDefaultState(),
            Blocks.ORANGE_TULIP.getDefaultState(),
            Blocks.PINK_TULIP.getDefaultState(),
            Blocks.RED_TULIP.getDefaultState(),
            Blocks.WHITE_TULIP.getDefaultState(),
            Blocks.ROSE_BUSH.getDefaultState(),
            Blocks.LILAC.getDefaultState(),
            Blocks.PEONY.getDefaultState()
    );

    @Override
    public boolean generate(FeatureContext<StructureTargetAndRangeConfig> context) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int i = 0; i < context.getConfig().attempts; i++){
            mutable.set(context.getOrigin()).move(
                    context.getRandom().nextInt((context.getConfig().range * 2) + 1) - context.getConfig().range,
                    context.getRandom().nextInt(3) - 1,
                    context.getRandom().nextInt((context.getConfig().range * 2) + 1) - context.getConfig().range
            );

            if(context.getWorld().getBlockState(mutable).isAir()){

                BlockState chosenFlower = FLOWERS.get(context.getRandom().nextInt(FLOWERS.size()));

                if(chosenFlower.canPlaceAt(context.getWorld(), mutable)){
                    // expensive. Do this check very last
                    if(!context.getWorld().toServerWorld().getStructureAccessor().getStructureAt(mutable, true, context.getConfig().targetStructure).hasChildren()){
                        continue;
                    }

                    if(chosenFlower.getBlock() instanceof TallPlantBlock && context.getWorld().getBlockState(mutable.up()).isAir()){
                        context.getWorld().setBlockState(mutable, chosenFlower.with(TallPlantBlock.HALF, DoubleBlockHalf.LOWER), 3);
                        context.getWorld().setBlockState(mutable.move(Direction.UP), chosenFlower.with(TallPlantBlock.HALF, DoubleBlockHalf.UPPER), 3);
                    }
                    else{
                        context.getWorld().setBlockState(mutable, chosenFlower, 3);
                    }
                }
            }
        }

        return true;
    }
}