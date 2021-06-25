package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;


public class StructureChains extends Feature<StructureTargetConfig> {

    public StructureChains(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(FeatureContext<StructureTargetConfig> context) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int i = 0; i < context.getConfig().attempts; i++){
            mutable.set(context.getOrigin()).move(
                    context.getRandom().nextInt(11) - 5,
                    context.getRandom().nextInt(3) - 1,
                    context.getRandom().nextInt(11) - 5
            );

            if(!context.getWorld().getBlockState(mutable).isAir()){
                continue;
            }

            // generates chains from given position down 1-8 blocks if path is clear and the given position is valid
            int length = 0;
            BlockState aboveBlockstate;
            boolean exitEarly = false;

            for (; mutable.getY() > 3 && length < context.getRandom().nextInt(context.getRandom().nextInt(context.getRandom().nextInt(8) + 1) + 1) + 1; mutable.move(Direction.DOWN)) {
                if (context.getWorld().isAir(mutable)) {
                    aboveBlockstate = context.getWorld().getBlockState(mutable.up());

                    if (aboveBlockstate.isSideSolidFullSquare(context.getWorld(), mutable.up(), Direction.DOWN) || aboveBlockstate.isOf(Blocks.CHAIN)) {
                        context.getWorld().setBlockState(mutable, Blocks.CHAIN.getDefaultState(), 2);
                        length++;
                    }
                }
                else {
                    exitEarly = true;
                }
            }

            if(exitEarly) continue;

            //attaches lantern at end at a rare chance
            if(mutable.getY() != 3 && context.getRandom().nextFloat() < 0.075f && context.getWorld().isAir(mutable)){
                if(context.getWorld().getBiome(mutable).getCategory() == Biome.Category.NETHER){
                    context.getWorld().setBlockState(mutable, Blocks.SOUL_LANTERN.getDefaultState().with(LanternBlock.HANGING, true), 2);
                }
                else{
                    context.getWorld().setBlockState(mutable, Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING, true), 2);
                }
            }
        }

        return true;
    }
}