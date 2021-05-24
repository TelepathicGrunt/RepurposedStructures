package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructureWarpedPlants extends Feature<StructureTargetAndLengthConfig> {

    public StructureWarpedPlants(Codec<StructureTargetAndLengthConfig> config) {
        super(config);
    }


    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetAndLengthConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState netherSprouts = Blocks.NETHER_SPROUTS.defaultBlockState();
        BlockState twistingFungus = Blocks.WARPED_FUNGUS.defaultBlockState();
        BlockState twistingRoots = Blocks.WARPED_ROOTS.defaultBlockState();
        BlockState twistingVines = Blocks.TWISTING_VINES.defaultBlockState();
        BlockState twistingVinesPlant = Blocks.TWISTING_VINES_PLANT.defaultBlockState();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(7) - 3,
                    -1,
                    random.nextInt(7) - 3
            );

            if(world.getBlockState(mutable).isAir()){
                if(random.nextFloat() < 0.5f && netherSprouts.canSurvive(world, mutable)){
                    // expensive. Do this check very last
                    if(!world.getLevel().structureFeatureManager().getStructureAt(mutable, true, config.targetStructure).isValid()){
                        continue;
                    }

                    world.setBlock(mutable, netherSprouts, 3);
                }
                else if(random.nextFloat() < 0.4f && twistingRoots.canSurvive(world, mutable)){
                    // expensive. Do this check very last
                    if(!world.getLevel().structureFeatureManager().getStructureAt(mutable, true, config.targetStructure).isValid()){
                        continue;
                    }

                    world.setBlock(mutable, twistingRoots, 3);
                }
                else if(random.nextFloat() < 0.3f && twistingFungus.canSurvive(world, mutable)){
                    // expensive. Do this check very last
                    if(!world.getLevel().structureFeatureManager().getStructureAt(mutable, true, config.targetStructure).isValid()){
                        continue;
                    }

                    world.setBlock(mutable, twistingFungus, 3);
                }
                else if(twistingVines.canSurvive(world, mutable)){
                    // expensive. Do this check very last
                    if(!world.getLevel().structureFeatureManager().getStructureAt(mutable, true, config.targetStructure).isValid()){
                        continue;
                    }

                    // Biased towards max length if greater than 3
                    int length = config.length > 3 ? config.length - random.nextInt(random.nextInt(config.length) + 1) : random.nextInt(config.length);
                    for(int currentLength = 0; currentLength <= length; currentLength++){
                        if(currentLength == length || !world.getBlockState(mutable.above()).isAir()){
                            world.setBlock(mutable, twistingVines, 3);
                            break;
                        }
                        world.setBlock(mutable, twistingVinesPlant, 3);
                        mutable.move(Direction.UP);
                    }
                }
            }
        }

        return true;
    }
}