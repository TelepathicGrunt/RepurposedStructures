package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.HashMap;
import java.util.Map;


public class StructureFire extends Feature<StructureTargetConfig> {

    private static final Map<ResourceKey<Level>, TagKey<Block>> INFINITE_FIRE_BLOCKS = new HashMap<>() {{
        put(Level.OVERWORLD, BlockTags.INFINIBURN_OVERWORLD);
        put(Level.NETHER, BlockTags.INFINIBURN_NETHER);
        put(Level.END, BlockTags.INFINIBURN_END);
    }};

    public StructureFire(Codec<StructureTargetConfig> config) {
        super(config);
    }

    @Override
    public boolean place(FeaturePlaceContext<StructureTargetConfig> context) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState fire = Blocks.FIRE.defaultBlockState();
        TagKey<Block> infiniteBurningBlocksTagKey = INFINITE_FIRE_BLOCKS.getOrDefault(context.level().getLevel().dimension(), BlockTags.INFINIBURN_OVERWORLD);

        for(int i = 0; i < context.config().attempts; i++) {
            mutable.set(context.origin()).move(
                    context.random().nextInt(7) - 3,
                    -1,
                    context.random().nextInt(7) - 3
            );

            BlockState belowBlock = context.level().getBlockState(mutable.below());
            if(context.level().getBlockState(mutable).isAir() && (belowBlock.getBlock() == Blocks.NETHER_BRICKS || belowBlock.is(infiniteBurningBlocksTagKey))) {

                if(belowBlock.getBlock() == Blocks.NETHER_BRICKS) {
                    context.level().setBlock(mutable.below(), Blocks.NETHERRACK.defaultBlockState(), 3);
                }

                context.level().setBlock(mutable, fire, 3);
            }
        }

        return true;
    }
}