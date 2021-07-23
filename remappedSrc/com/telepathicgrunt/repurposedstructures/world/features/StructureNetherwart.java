package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;


public class StructureNetherwart extends Feature<StructureTargetConfig> {

    public StructureNetherwart(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean place(FeaturePlaceContext<StructureTargetConfig> context) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState netherwart = Blocks.NETHER_WART.defaultBlockState();

        for(int i = 0; i < context.config().attempts; i++){
            mutable.set(context.origin()).move(
                    context.random().nextInt(10) - 5,
                    -1,
                    context.random().nextInt(10) - 5
            );

            if(netherwart.canSurvive(context.level(), mutable)){
                

                context.level().setBlock(mutable, netherwart.setValue(NetherWartBlock.AGE, context.random().nextInt(4)), 3);
            }
        }

        return true;
    }
}