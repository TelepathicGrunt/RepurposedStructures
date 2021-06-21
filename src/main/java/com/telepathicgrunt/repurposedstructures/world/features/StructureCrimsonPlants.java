package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;


public class StructureCrimsonPlants extends Feature<StructureTargetAndLengthConfig> {

    public StructureCrimsonPlants(Codec<StructureTargetAndLengthConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(FeatureContext<StructureTargetAndLengthConfig> context) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState crimsonFungus = Blocks.CRIMSON_FUNGUS.getDefaultState();
        BlockState crimsonRoots = Blocks.CRIMSON_ROOTS.getDefaultState();
        BlockState weepingVines = Blocks.WEEPING_VINES.getDefaultState();
        BlockState weepingVinesPlant = Blocks.WEEPING_VINES_PLANT.getDefaultState();

        for(int i = 0; i < context.getConfig().attempts; i++){
            mutable.set(context.getOrigin()).move(
                    context.getRandom().nextInt(7) - 3,
                    context.getRandom().nextInt(4) - 1,
                    context.getRandom().nextInt(7) - 3
            );

            if(context.getWorld().getBlockState(mutable).isAir()){
                if(context.getRandom().nextFloat() < 0.8f && crimsonRoots.canPlaceAt(context.getWorld(), mutable)){
                    // expensive. Do this check very last
                    if(!context.getWorld().toServerWorld().getStructureAccessor().getStructureAt(mutable, true, context.getConfig().targetStructure).hasChildren()){
                        continue;
                    }

                    context.getWorld().setBlockState(mutable, crimsonRoots, 3);
                }
                else if(crimsonFungus.canPlaceAt(context.getWorld(), mutable)){
                    // expensive. Do this check very last
                    if(!context.getWorld().toServerWorld().getStructureAccessor().getStructureAt(mutable, true, context.getConfig().targetStructure).hasChildren()){
                        continue;
                    }

                    context.getWorld().setBlockState(mutable, crimsonFungus, 3);
                }
                else if(weepingVines.canPlaceAt(context.getWorld(), mutable)){
                    // expensive. Do this check very last
                    if(!context.getWorld().toServerWorld().getStructureAccessor().getStructureAt(mutable, true, context.getConfig().targetStructure).hasChildren()){
                        continue;
                    }

                    // Biased towards max length if greater than 3
                    int length = context.getConfig().length > 3 ? context.getConfig().length - context.getRandom().nextInt(context.getRandom().nextInt(context.getConfig().length) + 1) : context.getRandom().nextInt(context.getConfig().length);
                    for(int currentLength = 0; currentLength <= length; currentLength++){
                        if(currentLength == length || !context.getWorld().getBlockState(mutable.down()).isAir()){
                            context.getWorld().setBlockState(mutable, weepingVines, 3);
                            break;
                        }
                        context.getWorld().setBlockState(mutable, weepingVinesPlant, 3);
                        mutable.move(Direction.DOWN);
                    }
                }
            }
        }

        return true;
    }
}