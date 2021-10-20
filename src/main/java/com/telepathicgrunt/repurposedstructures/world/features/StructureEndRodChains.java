package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndRodBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class StructureEndRodChains extends Feature<StructureTargetConfig> {

    private static final Set<Block> ALLOWED_ATTACHEMENT_BLOCKS = new HashSet<Block>(){{
        add(Blocks.CHAIN);
        add(Blocks.OBSIDIAN);
        add(Blocks.CRYING_OBSIDIAN);
        add(Blocks.PURPUR_BLOCK);
        add(Blocks.PURPUR_PILLAR);
        add(Blocks.PURPUR_SLAB);
        add(Blocks.PURPUR_STAIRS);
    }};

    public StructureEndRodChains(Codec<StructureTargetConfig> config) {
        super(config);
    }


    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(7) - 3,
                    -1,
                    random.nextInt(7) - 3
            );
            // generates chains from given position down 1-8 blocks if path is clear and the given position is valid
            int length = 0;
            BlockState belowBlockstate;
            boolean exitEarly = false;

            for (; mutable.getY() < world.getMaxBuildHeight() - 3 && length < random.nextInt(random.nextInt(random.nextInt(8) + 1) + 1) + 1; mutable.move(Direction.UP)) {
                if (world.isEmptyBlock(mutable)) {
                    belowBlockstate = world.getBlockState(mutable.below());
                    Block belowBlock = belowBlockstate.getBlock();

                    if (ALLOWED_ATTACHEMENT_BLOCKS.contains(belowBlock)) {
                        world.setBlock(mutable, Blocks.CHAIN.defaultBlockState(), 2);
                        if(belowBlock instanceof SlabBlock) {
                            world.setBlock(mutable.below(), belowBlockstate.setValue(SlabBlock.TYPE, SlabType.DOUBLE), 3);
                        }
                        length++;
                    }
                }
                else {
                    exitEarly = true;
                }
            }

            if(exitEarly) continue;

            //attaches End Rod at a decent chance
            if(mutable.getY() != world.getMaxBuildHeight() - 3 &&
                    random.nextFloat() < 0.475f &&
                    world.isEmptyBlock(mutable.above()) &&
                    world.isEmptyBlock(mutable))
            {
                world.setBlock(mutable, Blocks.END_ROD.defaultBlockState().setValue(EndRodBlock.FACING, Direction.UP), 2);
            }
        }

        return true;
    }
}