package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.HashMap;
import java.util.Map;


public class StructureFire extends Feature<StructureTargetConfig> {

    private static final Map<RegistryKey<World>, Tag<Block>> INFINITE_FIRE_BLOCKS = new HashMap<>() {{
        put(World.OVERWORLD, BlockTags.INFINIBURN_OVERWORLD);
        put(World.NETHER, BlockTags.INFINIBURN_NETHER);
        put(World.END, BlockTags.INFINIBURN_END);
    }};

    public StructureFire(Codec<StructureTargetConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(FeatureContext<StructureTargetConfig> context) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState fire = Blocks.FIRE.getDefaultState();
        Tag<Block> infiniteBurningBlocks = INFINITE_FIRE_BLOCKS.getOrDefault(context.getWorld().toServerWorld().getRegistryKey(), BlockTags.INFINIBURN_OVERWORLD);

        for(int i = 0; i < context.getConfig().attempts; i++){
            mutable.set(context.getOrigin()).move(
                    context.getRandom().nextInt(7) - 3,
                    -1,
                    context.getRandom().nextInt(7) - 3
            );

            Block belowBlock = context.getWorld().getBlockState(mutable.down()).getBlock();
            if(context.getWorld().getBlockState(mutable).isAir() && (belowBlock == Blocks.NETHER_BRICKS || infiniteBurningBlocks.contains(belowBlock))){

                if(belowBlock == Blocks.NETHER_BRICKS){
                    context.getWorld().setBlockState(mutable.down(), Blocks.NETHERRACK.getDefaultState(), 3);
                }

                context.getWorld().setBlockState(mutable, fire, 3);
            }
        }

        return true;
    }
}