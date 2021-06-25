package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;


public class StructureWarpedPlants extends Feature<StructureTargetAndLengthConfig> {

    public StructureWarpedPlants(Codec<StructureTargetAndLengthConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(FeatureContext<StructureTargetAndLengthConfig> context) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState netherSprouts = Blocks.NETHER_SPROUTS.getDefaultState();
        BlockState twistingFungus = Blocks.WARPED_FUNGUS.getDefaultState();
        BlockState twistingRoots = Blocks.WARPED_ROOTS.getDefaultState();
        BlockState twistingVines = Blocks.TWISTING_VINES.getDefaultState();
        BlockState twistingVinesPlant = Blocks.TWISTING_VINES_PLANT.getDefaultState();

        for(int i = 0; i < context.getConfig().attempts; i++){
            mutable.set(context.getOrigin()).move(
                    context.getRandom().nextInt(7) - 3,
                    -1,
                    context.getRandom().nextInt(7) - 3
            );

            if(context.getWorld().getBlockState(mutable).isAir()){
                if(context.getRandom().nextFloat() < 0.5f && netherSprouts.canPlaceAt(context.getWorld(), mutable)){

                    context.getWorld().setBlockState(mutable, netherSprouts, 3);
                }
                else if(context.getRandom().nextFloat() < 0.4f && twistingRoots.canPlaceAt(context.getWorld(), mutable)){

                    context.getWorld().setBlockState(mutable, twistingRoots, 3);
                }
                else if(context.getRandom().nextFloat() < 0.3f && twistingFungus.canPlaceAt(context.getWorld(), mutable)){

                    context.getWorld().setBlockState(mutable, twistingFungus, 3);
                }
                else if(twistingVines.canPlaceAt(context.getWorld(), mutable)){

                    // Biased towards max length if greater than 3
                    int length = context.getConfig().length > 3 ? context.getConfig().length - context.getRandom().nextInt(context.getRandom().nextInt(context.getConfig().length) + 1) : context.getRandom().nextInt(context.getConfig().length);
                    for(int currentLength = 0; currentLength <= length; currentLength++){
                        if(currentLength == length || !context.getWorld().getBlockState(mutable.up()).isAir()){
                            context.getWorld().setBlockState(mutable, twistingVines, 3);
                            break;
                        }
                        context.getWorld().setBlockState(mutable, twistingVinesPlant, 3);
                        mutable.move(Direction.UP);
                    }
                }
            }
        }

        return true;
    }
}