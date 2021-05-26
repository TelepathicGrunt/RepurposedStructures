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
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.List;
import java.util.Random;


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
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetAndRangeConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt((config.range * 2) + 1) - config.range,
                    random.nextInt(3) - 1,
                    random.nextInt((config.range * 2) + 1) - config.range
            );

            if(world.getBlockState(mutable).isAir()){

                BlockState chosenFlower = FLOWERS.get(random.nextInt(FLOWERS.size()));

                if(chosenFlower.canPlaceAt(world, mutable)){
                    // expensive. Do this check very last
                    if(!world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                        continue;
                    }

                    if(chosenFlower.getBlock() instanceof TallPlantBlock && world.getBlockState(mutable.up()).isAir()){
                        world.setBlockState(mutable, chosenFlower.with(TallPlantBlock.HALF, DoubleBlockHalf.LOWER), 3);
                        world.setBlockState(mutable.move(Direction.UP), chosenFlower.with(TallPlantBlock.HALF, DoubleBlockHalf.UPPER), 3);
                    }
                    else{
                        world.setBlockState(mutable, chosenFlower, 3);
                    }
                }
            }
        }

        return true;
    }
}