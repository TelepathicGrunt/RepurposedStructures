package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructureNetherwart extends Feature<StructureTargetConfig> {

    public StructureNetherwart(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState netherwart = Blocks.NETHER_WART.getDefaultState();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(10) - 5,
                    -1,
                    random.nextInt(10) - 5
            );

            if(netherwart.canPlaceAt(world, mutable)){
                // expensive. Do this check very last
                if(!world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                    continue;
                }

                world.setBlockState(mutable, netherwart.with(NetherWartBlock.AGE, random.nextInt(5)), 3);
            }
        }

        return true;
    }
}