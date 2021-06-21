package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;


public class StructureNetherwart extends Feature<StructureTargetConfig> {

    public StructureNetherwart(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(FeatureContext<StructureTargetConfig> context) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState netherwart = Blocks.NETHER_WART.getDefaultState();

        for(int i = 0; i < context.getConfig().attempts; i++){
            mutable.set(context.getOrigin()).move(
                    context.getRandom().nextInt(10) - 5,
                    -1,
                    context.getRandom().nextInt(10) - 5
            );

            if(netherwart.canPlaceAt(context.getWorld(), mutable)){
                // expensive. Do this check very last
                if(!context.getWorld().toServerWorld().getStructureAccessor().getStructureAt(mutable, true, context.getConfig().targetStructure).hasChildren()){
                    continue;
                }

                context.getWorld().setBlockState(mutable, netherwart.with(NetherWartBlock.AGE, context.getRandom().nextInt(4)), 3);
            }
        }

        return true;
    }
}