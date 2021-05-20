package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructureSeagrass extends Feature<StructureTargetConfig> {

    public StructureSeagrass(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState tallSeagrass = Blocks.TALL_SEAGRASS.getDefaultState();
        BlockState seagrass = Blocks.SEAGRASS.getDefaultState();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(7) - 3,
                    random.nextInt(7) - 3,
                    random.nextInt(7) - 3
            );

            boolean isWater = world.getBlockState(mutable).isOf(Blocks.WATER);
            if(!isWater) continue;

            boolean isWaterAbove = world.getBlockState(mutable.up()).isOf(Blocks.WATER);
            if(isWaterAbove && random.nextFloat() < 0.33f && tallSeagrass.canPlaceAt(world, mutable)){
                // expensive. Do this check very last
                if(!world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                    continue;
                }

                world.setBlockState(mutable, tallSeagrass.with(TallSeagrassBlock.HALF, DoubleBlockHalf.LOWER), 3);
                world.setBlockState(mutable.move(Direction.UP), tallSeagrass.with(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER), 3);
            }
            else if(seagrass.canPlaceAt(world, mutable)){
                // expensive. Do this check very last
                if(!world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                    continue;
                }

                world.setBlockState(mutable, Blocks.SEAGRASS.getDefaultState(), 3);
            }
        }

        return true;
    }
}