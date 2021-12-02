package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChorusFlowerBlock;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;


public class StructureChorus extends Feature<StructureTargetConfig> {

    public StructureChorus(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean place(FeaturePlaceContext<StructureTargetConfig> context) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState chorusFlower = Blocks.CHORUS_FLOWER.defaultBlockState();

        for(int i = 0; i < context.config().attempts; i++) {
            mutable.set(context.origin()).move(
                    context.random().nextInt(7) - 3,
                    -1,
                    context.random().nextInt(7) - 3
            );

            if(context.level().getBlockState(mutable).isAir() && context.level().getBlockState(mutable.above()).isAir() && context.level().getBlockState(mutable.move(Direction.DOWN)).canOcclude()) {
                

                context.level().setBlock(mutable, Blocks.END_STONE.defaultBlockState(), 3);
                if(context.random().nextFloat() < 0.33f) {
                    context.level().setBlock(
                            mutable.move(Direction.UP),
                            chorusFlower.setValue(ChorusFlowerBlock.AGE, 5 - context.random().nextInt(context.random().nextInt(6) + 1)),
                            3);
                    continue;
                }

                // check to make sure this chorus stem can be placed
                boolean isValidSpot = true;
                for(Direction direction : Direction.Plane.HORIZONTAL) {
                    mutable.move(direction);
                    if(context.level().getBlockState(mutable).is(Blocks.CHORUS_PLANT)) {
                        isValidSpot = false;
                        break;
                    }
                    mutable.move(direction.getOpposite());
                }
                if(!isValidSpot) continue;

                mutable.move(Direction.UP);
                context.level().setBlock(mutable,
                        Blocks.CHORUS_PLANT.defaultBlockState()
                                .setValue(ChorusPlantBlock.DOWN, true)
                                .setValue(ChorusPlantBlock.UP, true),
                        3);

                Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(context.random());
                if(context.random().nextFloat() < 0.33f || !context.level().getBlockState(mutable.relative(direction)).isAir()) {
                    context.level().setBlock(
                            mutable.move(Direction.UP),
                            chorusFlower.setValue(ChorusFlowerBlock.AGE, 5 - context.random().nextInt(context.random().nextInt(6) + 1)),
                            3);
                    continue;
                }

                context.level().setBlock(mutable.move(Direction.UP),
                        Blocks.CHORUS_PLANT.defaultBlockState()
                            .setValue(ChorusPlantBlock.DOWN, true)
                            .setValue(PipeBlock.PROPERTY_BY_DIRECTION.get(direction), true),
                        3);

                context.level().setBlock(mutable.move(direction), chorusFlower.setValue(ChorusFlowerBlock.AGE, context.random().nextInt(5)), 3);
            }
        }

        return true;
    }
}