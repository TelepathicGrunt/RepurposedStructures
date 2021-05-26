package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
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

import java.util.Random;


public class StructureGrass extends Feature<StructureTargetAndRangeConfig> {

    public StructureGrass(Codec<StructureTargetAndRangeConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetAndRangeConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState grass = Blocks.GRASS.getDefaultState();
        BlockState tallGrass = Blocks.TALL_GRASS.getDefaultState();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt((config.range * 2) + 1) - config.range,
                    random.nextInt(3) - 1,
                    random.nextInt((config.range * 2) + 1) - config.range
            );

            if(world.getBlockState(mutable).isAir()){
                if(random.nextFloat() < 0.45f && grass.canPlaceAt(world, mutable)){
                    // expensive. Do this check very last
                    if(!world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                        continue;
                    }

                    world.setBlockState(mutable, grass, 3);
                }
                else if(tallGrass.canPlaceAt(world, mutable)){
                    // expensive. Do this check very last
                    if(!world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                        continue;
                    }

                    world.setBlockState(mutable, tallGrass.with(TallPlantBlock.HALF, DoubleBlockHalf.LOWER), 3);
                    world.setBlockState(mutable.move(Direction.UP), tallGrass.with(TallPlantBlock.HALF, DoubleBlockHalf.UPPER), 3);
                }
            }
        }

        return true;
    }
}