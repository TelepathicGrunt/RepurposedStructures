package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;


public class StructureChains extends Feature<StructureTargetConfig> {

    public StructureChains(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean place(FeaturePlaceContext<StructureTargetConfig> context) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for(int i = 0; i < context.config().attempts; i++){
            mutable.set(context.origin()).move(
                    context.random().nextInt(11) - 5,
                    context.random().nextInt(3) - 1,
                    context.random().nextInt(11) - 5
            );

            if(!context.level().getBlockState(mutable).isAir()){
                continue;
            }

            // generates chains from given position down 1-8 blocks if path is clear and the given position is valid
            int length = 0;
            BlockState aboveBlockstate;
            boolean exitEarly = false;

            for (; mutable.getY() > 3 && length < context.random().nextInt(context.random().nextInt(context.random().nextInt(8) + 1) + 1) + 1; mutable.move(Direction.DOWN)) {
                if (context.level().isEmptyBlock(mutable)) {
                    aboveBlockstate = context.level().getBlockState(mutable.above());

                    if (aboveBlockstate.isFaceSturdy(context.level(), mutable.above(), Direction.DOWN) || aboveBlockstate.is(Blocks.CHAIN)) {
                        context.level().setBlock(mutable, Blocks.CHAIN.defaultBlockState(), 2);
                        length++;
                    }
                }
                else {
                    exitEarly = true;
                }
            }

            if(exitEarly) continue;

            //attaches lantern at end at a rare chance
            if(mutable.getY() != 3 && context.random().nextFloat() < 0.075f && context.level().isEmptyBlock(mutable)){
                if(context.level().getBiome(mutable).getBiomeCategory() == Biome.BiomeCategory.NETHER){
                    context.level().setBlock(mutable, Blocks.SOUL_LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, true), 2);
                }
                else{
                    context.level().setBlock(mutable, Blocks.LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, true), 2);
                }
            }
        }

        return true;
    }
}