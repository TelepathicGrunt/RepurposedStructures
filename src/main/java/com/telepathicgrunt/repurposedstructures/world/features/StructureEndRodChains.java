package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndRodBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.Set;


public class StructureEndRodChains extends Feature<StructureTargetConfig> {

    private static final Set<Block> ALLOWED_ATTACHEMENT_BLOCKS = Set.of(
            Blocks.CHAIN,
            Blocks.OBSIDIAN,
            Blocks.CRYING_OBSIDIAN,
            Blocks.PURPUR_BLOCK,
            Blocks.PURPUR_PILLAR,
            Blocks.PURPUR_SLAB,
            Blocks.PURPUR_STAIRS);

    public StructureEndRodChains(Codec<StructureTargetConfig> config) {
        super(config);
    }

    @Override
    public boolean place(FeaturePlaceContext<StructureTargetConfig> context) {

        WorldGenLevel world = context.level();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for(int i = 0; i < context.config().attempts; i++) {
            mutable.set(context.origin()).move(
                    context.random().nextInt(7) - 3,
                    -1,
                    context.random().nextInt(7) - 3
            );

            if(!world.getBlockState(mutable).isAir()) {
                continue;
            }

            // generates chains from given position down 1-8 blocks if path is clear and the given position is valid
            int length = 0;
            BlockState belowBlockstate;
            boolean exitEarly = false;

            for (; mutable.getY() < world.getMaxBuildHeight() - 3 && length < context.random().nextInt(context.random().nextInt(context.random().nextInt(8) + 1) + 1) + 1; mutable.move(Direction.UP)) {
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

            //attaches end rod at end at a decent chance
            if(mutable.getY() != world.getMaxBuildHeight() - 3 &&
                context.random().nextFloat() < 0.475f &&
                world.isEmptyBlock(mutable.above()) &&
                world.isEmptyBlock(mutable))
            {
                world.setBlock(mutable, Blocks.END_ROD.defaultBlockState().setValue(EndRodBlock.FACING, Direction.UP), 2);
            }
        }

        return true;
    }
}