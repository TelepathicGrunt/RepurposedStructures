package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;


public class StructureCrimsonPlants extends Feature<StructureTargetAndLengthConfig> {

    public StructureCrimsonPlants(Codec<StructureTargetAndLengthConfig> config) {
        super(config);
    }


    @Override
    public boolean place(FeaturePlaceContext<StructureTargetAndLengthConfig> context) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState crimsonFungus = Blocks.CRIMSON_FUNGUS.defaultBlockState();
        BlockState crimsonRoots = Blocks.CRIMSON_ROOTS.defaultBlockState();
        BlockState weepingVines = Blocks.WEEPING_VINES.defaultBlockState();
        BlockState weepingVinesPlant = Blocks.WEEPING_VINES_PLANT.defaultBlockState();

        for(int i = 0; i < context.config().attempts; i++) {
            mutable.set(context.origin()).move(
                    context.random().nextInt(7) - 3,
                    context.random().nextInt(4) - 1,
                    context.random().nextInt(7) - 3
            );

            if(context.level().getBlockState(mutable).isAir()) {
                if(context.random().nextFloat() < 0.8f && crimsonRoots.canSurvive(context.level(), mutable)) {

                    context.level().setBlock(mutable, crimsonRoots, 3);
                }
                else if(crimsonFungus.canSurvive(context.level(), mutable)) {

                    context.level().setBlock(mutable, crimsonFungus, 3);
                }
                else if(weepingVines.canSurvive(context.level(), mutable)) {

                    // Biased towards max length if greater than 3
                    int length = context.config().length > 3 ? context.config().length - context.random().nextInt(context.random().nextInt(context.config().length) + 1) : context.random().nextInt(context.config().length);
                    for(int currentLength = 0; currentLength <= length; currentLength++) {
                        if(currentLength == length || !context.level().getBlockState(mutable.below()).isAir()) {
                            context.level().setBlock(mutable, weepingVines, 3);
                            break;
                        }
                        context.level().setBlock(mutable, weepingVinesPlant, 3);
                        mutable.move(Direction.DOWN);
                    }
                }
            }
        }

        return true;
    }
}