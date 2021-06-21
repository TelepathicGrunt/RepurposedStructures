package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;


public class StructureSeagrass extends Feature<StructureTargetConfig> {

    public StructureSeagrass(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(FeatureContext<StructureTargetConfig> context) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState tallSeagrass = Blocks.TALL_SEAGRASS.getDefaultState();
        BlockState seagrass = Blocks.SEAGRASS.getDefaultState();

        for(int i = 0; i < context.getConfig().attempts; i++){
            mutable.set(context.getOrigin()).move(
                    context.getRandom().nextInt(7) - 3,
                    -1,
                    context.getRandom().nextInt(7) - 3
            );

            boolean isWater = context.getWorld().getBlockState(mutable).isOf(Blocks.WATER);
            if(!isWater) continue;

            boolean isWaterAbove = context.getWorld().getBlockState(mutable.up()).isOf(Blocks.WATER);
            if(isWaterAbove && context.getRandom().nextFloat() < 0.33f && tallSeagrass.canPlaceAt(context.getWorld(), mutable)){
                // expensive. Do this check very last
                if(!context.getWorld().toServerWorld().getStructureAccessor().getStructureAt(mutable, true, context.getConfig().targetStructure).hasChildren()){
                    continue;
                }

                context.getWorld().setBlockState(mutable, tallSeagrass.with(TallSeagrassBlock.HALF, DoubleBlockHalf.LOWER), 3);
                context.getWorld().setBlockState(mutable.move(Direction.UP), tallSeagrass.with(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER), 3);
            }
            else if(seagrass.canPlaceAt(context.getWorld(), mutable)){
                // expensive. Do this check very last
                if(!context.getWorld().toServerWorld().getStructureAccessor().getStructureAt(mutable, true, context.getConfig().targetStructure).hasChildren()){
                    continue;
                }

                context.getWorld().setBlockState(mutable, Blocks.SEAGRASS.getDefaultState(), 3);
            }
        }

        return true;
    }
}