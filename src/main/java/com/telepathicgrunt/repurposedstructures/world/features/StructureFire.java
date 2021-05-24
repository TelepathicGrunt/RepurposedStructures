package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class StructureFire extends Feature<StructureTargetConfig> {

    private static final Map<RegistryKey<World>, ITag.INamedTag<Block>> INFINITE_FIRE_BLOCKS = new HashMap<RegistryKey<World>, ITag.INamedTag<Block>>() {{
        put(World.OVERWORLD, BlockTags.INFINIBURN_OVERWORLD);
        put(World.NETHER, BlockTags.INFINIBURN_NETHER);
        put(World.END, BlockTags.INFINIBURN_END);
    }};

    public StructureFire(Codec<StructureTargetConfig> config) {
        super(config);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState fire = Blocks.FIRE.defaultBlockState();
        ITag.INamedTag<Block> infiniteBurningBlocks = INFINITE_FIRE_BLOCKS.getOrDefault(world.getLevel().dimension(), BlockTags.INFINIBURN_OVERWORLD);

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(7) - 3,
                    -1,
                    random.nextInt(7) - 3
            );

            Block belowBlock = world.getBlockState(mutable.below()).getBlock();
            if(world.getBlockState(mutable).isAir() && (belowBlock.is(Blocks.NETHER_BRICKS) || infiniteBurningBlocks.contains(belowBlock))){
                // expensive. Do this check very last
                if(!world.getLevel().structureFeatureManager().getStructureAt(mutable, true, config.targetStructure).isValid()){
                    continue;
                }

                if(belowBlock.is(Blocks.NETHER_BRICKS)){
                    world.setBlock(mutable.below(), Blocks.NETHERRACK.defaultBlockState(), 3);
                }

                world.setBlock(mutable, fire, 3);
            }
        }

        return true;
    }
}