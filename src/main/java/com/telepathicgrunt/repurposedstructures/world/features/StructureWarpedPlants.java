package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructureWarpedPlants extends Feature<StructureTargetConfig> {

    public StructureWarpedPlants(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState twistingFungus = Blocks.WARPED_FUNGUS.getDefaultState();
        BlockState twistingRoots = Blocks.WARPED_ROOTS.getDefaultState();
        BlockState twistingVines = Blocks.TWISTING_VINES.getDefaultState();
        BlockState twistingVinesPlant = Blocks.TWISTING_VINES_PLANT.getDefaultState();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(21) - 10,
                    random.nextInt(21) - 10,
                    random.nextInt(21) - 10
            );

            if(world.getBlockState(mutable).isAir()){
                if(random.nextFloat() < 0.8f && twistingRoots.canPlaceAt(world, mutable)){
                    // expensive. Do this check very last
                    if(!world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                        continue;
                    }

                    world.setBlockState(mutable, twistingRoots, 3);
                }
                else if(twistingFungus.canPlaceAt(world, mutable)){
                    // expensive. Do this check very last
                    if(!world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                        continue;
                    }

                    world.setBlockState(mutable, twistingFungus, 3);
                }
                else if(twistingVines.canPlaceAt(world, mutable)){
                    // expensive. Do this check very last
                    if(!world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                        continue;
                    }
                    
                    int length = random.nextInt(3);
                    for(int currentLength = 0; currentLength <= length; currentLength++){
                        if(currentLength == length || !world.getBlockState(mutable.up()).isAir()){
                            world.setBlockState(mutable, twistingVines, 3);
                            break;
                        }
                        world.setBlockState(mutable, twistingVinesPlant, 3);
                        mutable.move(Direction.UP);
                    }
                }
            }
        }

        return true;
    }
}