package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndRangeConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructureGrass extends Feature<StructureTargetAndRangeConfig> {

    public StructureGrass(Codec<StructureTargetAndRangeConfig> config) {
        super(config);
    }


    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetAndRangeConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState grass = Blocks.GRASS.defaultBlockState();
        BlockState tallGrass = Blocks.TALL_GRASS.defaultBlockState();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt((config.range * 2) + 1) - config.range,
                    random.nextInt(3) - 1,
                    random.nextInt((config.range * 2) + 1) - config.range
            );

            if(world.getBlockState(mutable).isAir()){
                if((random.nextFloat() < 0.45f || !world.getBlockState(mutable.above()).isAir()) && grass.canSurvive(world, mutable)){
                    world.setBlock(mutable, grass, 3);
                }
                else if(tallGrass.canSurvive(world, mutable)){
                    world.setBlock(mutable, tallGrass.setValue(TallFlowerBlock.HALF, DoubleBlockHalf.LOWER), 3);
                    world.setBlock(mutable.move(Direction.UP), tallGrass.setValue(TallFlowerBlock.HALF, DoubleBlockHalf.UPPER), 3);
                }
            }
        }

        return true;
    }
}