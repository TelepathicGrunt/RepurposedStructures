package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndRangeConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.List;
import java.util.Random;


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
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetAndRangeConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt((config.range * 2) + 1) - config.range,
                    random.nextInt(3) - 1,
                    random.nextInt((config.range * 2) + 1) - config.range
            );

            if(world.getBlockState(mutable).isAir()){

                BlockState chosenFlower = FLOWERS.get(random.nextInt(FLOWERS.size()));

                if(chosenFlower.canSurvive(world, mutable)){
                    // expensive. Do this check very last
                    if(!world.startsForFeature(SectionPos.of(mutable), config.targetStructure).findAny().isPresent()){
                        continue;
                    }

                    if(chosenFlower.getBlock() instanceof TallFlowerBlock && world.getBlockState(mutable.above()).isAir()){
                        world.setBlock(mutable, chosenFlower.setValue(TallFlowerBlock.HALF, DoubleBlockHalf.LOWER), 3);
                        world.setBlock(mutable.move(Direction.UP), chosenFlower.setValue(TallFlowerBlock.HALF, DoubleBlockHalf.UPPER), 3);
                    }
                    else{
                        world.setBlock(mutable, chosenFlower, 3);
                    }
                }
            }
        }

        return true;
    }
}