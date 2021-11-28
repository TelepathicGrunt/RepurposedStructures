package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;


public class StructureWarpedPlants extends Feature<StructureTargetAndLengthConfig> {

    public StructureWarpedPlants(Codec<StructureTargetAndLengthConfig> config) {
        super(config);
    }


    @Override
    public boolean place(FeaturePlaceContext<StructureTargetAndLengthConfig> context) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState netherSprouts = Blocks.NETHER_SPROUTS.defaultBlockState();
        BlockState twistingFungus = Blocks.WARPED_FUNGUS.defaultBlockState();
        BlockState twistingRoots = Blocks.WARPED_ROOTS.defaultBlockState();
        BlockState twistingVines = Blocks.TWISTING_VINES.defaultBlockState();
        BlockState twistingVinesPlant = Blocks.TWISTING_VINES_PLANT.defaultBlockState();

        for(int i = 0; i < context.config().attempts; i++) {
            mutable.set(context.origin()).move(
                    context.random().nextInt(7) - 3,
                    -1,
                    context.random().nextInt(7) - 3
            );

            if(context.level().getBlockState(mutable).isAir()) {
                if(context.random().nextFloat() < 0.5f && netherSprouts.canSurvive(context.level(), mutable)) {

                    context.level().setBlock(mutable, netherSprouts, 3);
                }
                else if(context.random().nextFloat() < 0.4f && twistingRoots.canSurvive(context.level(), mutable)) {

                    context.level().setBlock(mutable, twistingRoots, 3);
                }
                else if(context.random().nextFloat() < 0.3f && twistingFungus.canSurvive(context.level(), mutable)) {

                    context.level().setBlock(mutable, twistingFungus, 3);
                }
                else if(twistingVines.canSurvive(context.level(), mutable)) {

                    // Biased towards max length if greater than 3
                    int length = context.config().length > 3 ? context.config().length - context.random().nextInt(context.random().nextInt(context.config().length) + 1) : context.random().nextInt(context.config().length);
                    for(int currentLength = 0; currentLength <= length; currentLength++) {
                        if(currentLength == length || !context.level().getBlockState(mutable.above()).isAir()) {
                            context.level().setBlock(mutable, twistingVines, 3);
                            break;
                        }
                        context.level().setBlock(mutable, twistingVinesPlant, 3);
                        mutable.move(Direction.UP);
                    }
                }
            }
        }

        return true;
    }
}