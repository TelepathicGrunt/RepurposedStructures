package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;


public class StructureChorus extends Feature<StructureTargetConfig> {

    public StructureChorus(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(FeatureContext<StructureTargetConfig> context) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState chorusFlower = Blocks.CHORUS_FLOWER.getDefaultState();

        for(int i = 0; i < context.getConfig().attempts; i++){
            mutable.set(context.getOrigin()).move(
                    context.getRandom().nextInt(7) - 3,
                    -1,
                    context.getRandom().nextInt(7) - 3
            );

            if(context.getWorld().getBlockState(mutable).isAir() && context.getWorld().getBlockState(mutable.up()).isAir() && context.getWorld().getBlockState(mutable.move(Direction.DOWN)).isOpaque()){
                // expensive. Do this check very last
                if(!context.getWorld().toServerWorld().getStructureAccessor().getStructureAt(mutable, true, context.getConfig().targetStructure).hasChildren()){
                    continue;
                }

                context.getWorld().setBlockState(mutable, Blocks.END_STONE.getDefaultState(), 3);
                if(context.getRandom().nextFloat() < 0.33f){
                    context.getWorld().setBlockState(
                            mutable.move(Direction.UP),
                            chorusFlower.with(ChorusFlowerBlock.AGE, 5 - context.getRandom().nextInt(context.getRandom().nextInt(6) + 1)),
                            3);
                    continue;
                }

                // check to make sure this chorus stem can be placed
                boolean isValidSpot = true;
                for(Direction direction : Direction.Type.HORIZONTAL){
                    mutable.move(direction);
                    if(context.getWorld().getBlockState(mutable).isOf(Blocks.CHORUS_PLANT)){
                        isValidSpot = false;
                        break;
                    }
                    mutable.move(direction.getOpposite());
                }
                if(!isValidSpot) continue;

                mutable.move(Direction.UP);
                context.getWorld().setBlockState(mutable,
                        Blocks.CHORUS_PLANT.getDefaultState()
                                .with(ChorusPlantBlock.DOWN, true)
                                .with(ChorusPlantBlock.UP, true),
                        3);

                Direction direction = Direction.Type.HORIZONTAL.random(context.getRandom());
                if(context.getRandom().nextFloat() < 0.33f || !context.getWorld().getBlockState(mutable.offset(direction)).isAir()) {
                    context.getWorld().setBlockState(
                            mutable.move(Direction.UP),
                            chorusFlower.with(ChorusFlowerBlock.AGE, 5 - context.getRandom().nextInt(context.getRandom().nextInt(6) + 1)),
                            3);
                    continue;
                }

                context.getWorld().setBlockState(mutable.move(Direction.UP),
                        Blocks.CHORUS_PLANT.getDefaultState()
                            .with(ChorusPlantBlock.DOWN, true)
                            .with(ConnectingBlock.FACING_PROPERTIES.get(direction), true),
                        3);

                context.getWorld().setBlockState(mutable.move(direction), chorusFlower.with(ChorusFlowerBlock.AGE, context.getRandom().nextInt(5)), 3);
            }
        }

        return true;
    }
}