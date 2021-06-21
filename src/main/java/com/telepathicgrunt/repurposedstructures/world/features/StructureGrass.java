package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndRangeConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;


public class StructureGrass extends Feature<StructureTargetAndRangeConfig> {

    public StructureGrass(Codec<StructureTargetAndRangeConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(FeatureContext<StructureTargetAndRangeConfig> context) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState grass = Blocks.GRASS.getDefaultState();
        BlockState tallGrass = Blocks.TALL_GRASS.getDefaultState();

        for(int i = 0; i < context.getConfig().attempts; i++){
            mutable.set(context.getOrigin()).move(
                    context.getRandom().nextInt((context.getConfig().range * 2) + 1) - context.getConfig().range,
                    context.getRandom().nextInt(3) - 1,
                    context.getRandom().nextInt((context.getConfig().range * 2) + 1) - context.getConfig().range
            );

            if(context.getWorld().getBlockState(mutable).isAir()){
                if((context.getRandom().nextFloat() < 0.45f || !context.getWorld().getBlockState(mutable.up()).isAir()) && grass.canPlaceAt(context.getWorld(), mutable)){
                    // expensive. Do this check very last
                    if(!context.getWorld().toServerWorld().getStructureAccessor().getStructureAt(mutable, true, context.getConfig().targetStructure).hasChildren()){
                        continue;
                    }

                    context.getWorld().setBlockState(mutable, grass, 3);
                }
                else if(tallGrass.canPlaceAt(context.getWorld(), mutable)){
                    // expensive. Do this check very last
                    if(!context.getWorld().toServerWorld().getStructureAccessor().getStructureAt(mutable, true, context.getConfig().targetStructure).hasChildren()){
                        continue;
                    }

                    context.getWorld().setBlockState(mutable, tallGrass.with(TallPlantBlock.HALF, DoubleBlockHalf.LOWER), 3);
                    context.getWorld().setBlockState(mutable.move(Direction.UP), tallGrass.with(TallPlantBlock.HALF, DoubleBlockHalf.UPPER), 3);
                }
            }
        }

        return true;
    }
}