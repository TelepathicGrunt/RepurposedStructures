package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ConnectingBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructureChorus extends Feature<StructureTargetConfig> {

    public StructureChorus(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(7) - 3,
                    -1,
                    random.nextInt(7) - 3
            );

            if(world.getBlockState(mutable).isAir() && world.getBlockState(mutable.move(Direction.DOWN)).isOpaque()){
                // expensive. Do this check very last
                if(!world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                    continue;
                }

                world.setBlockState(mutable, Blocks.END_STONE.getDefaultState(), 3);
                if(random.nextFloat() < 0.33f){
                    world.setBlockState(mutable.move(Direction.UP), Blocks.CHORUS_FLOWER.getDefaultState(), 3);
                    continue;
                }

                world.setBlockState(mutable.move(Direction.UP), Blocks.CHORUS_PLANT.getDefaultState(), 3);
                Direction direction = Direction.Type.HORIZONTAL.random(random);
                if(random.nextFloat() < 0.33f || !world.getBlockState(mutable.offset(direction)).isAir()) {
                    world.setBlockState(mutable.move(Direction.UP), Blocks.CHORUS_FLOWER.getDefaultState(), 3);
                    continue;
                }

                world.setBlockState(mutable.move(Direction.UP), Blocks.CHORUS_PLANT.getDefaultState().with(ConnectingBlock.FACING_PROPERTIES.get(direction), true), 3);
                world.setBlockState(mutable.move(direction), Blocks.CHORUS_FLOWER.getDefaultState(), 3);
            }
        }

        return true;
    }
}