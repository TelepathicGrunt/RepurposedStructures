package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructureWarpedPlants extends Feature<StructureTargetAndLengthConfig> {

    public StructureWarpedPlants(Codec<StructureTargetAndLengthConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetAndLengthConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState netherSprouts = Blocks.NETHER_SPROUTS.getDefaultState();
        BlockState twistingFungus = Blocks.WARPED_FUNGUS.getDefaultState();
        BlockState twistingRoots = Blocks.WARPED_ROOTS.getDefaultState();
        BlockState twistingVines = Blocks.TWISTING_VINES.getDefaultState();
        BlockState twistingVinesPlant = Blocks.TWISTING_VINES_PLANT.getDefaultState();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(7) - 3,
                    -1,
                    random.nextInt(7) - 3
            );

            if(world.getBlockState(mutable).isAir()){
                if(random.nextFloat() < 0.5f && netherSprouts.canPlaceAt(world, mutable)){
                    // expensive. Do this check very last
                    if(!world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                        continue;
                    }

                    world.setBlockState(mutable, netherSprouts, 3);
                }
                else if(random.nextFloat() < 0.4f && twistingRoots.canPlaceAt(world, mutable)){
                    // expensive. Do this check very last
                    if(!world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                        continue;
                    }

                    world.setBlockState(mutable, twistingRoots, 3);
                }
                else if(random.nextFloat() < 0.3f && twistingFungus.canPlaceAt(world, mutable)){
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

                    // Biased towards max length if greater than 3
                    int length = config.length > 3 ? config.length - random.nextInt(random.nextInt(config.length) + 1) : random.nextInt(config.length);
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