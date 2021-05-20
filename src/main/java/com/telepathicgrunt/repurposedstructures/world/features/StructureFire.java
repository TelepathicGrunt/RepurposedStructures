package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class StructureFire extends Feature<StructureTargetConfig> {

    private static final Map<RegistryKey<World>, Tag<Block>> INFINITE_FIRE_BLOCKS = new HashMap<RegistryKey<World>, Tag<Block>>() {{
        put(World.OVERWORLD, BlockTags.INFINIBURN_OVERWORLD);
        put(World.NETHER, BlockTags.INFINIBURN_NETHER);
        put(World.END, BlockTags.INFINIBURN_END);
    }};

    public StructureFire(Codec<StructureTargetConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockState fire = Blocks.FIRE.getDefaultState();
        Tag<Block> infiniteBurningBlocks = INFINITE_FIRE_BLOCKS.getOrDefault(world.toServerWorld().getRegistryKey(), BlockTags.INFINIBURN_OVERWORLD);

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(7) - 3,
                    -1,
                    random.nextInt(7) - 3
            );

            if(world.getBlockState(mutable).isAir() && infiniteBurningBlocks.contains(world.getBlockState(mutable.down()).getBlock())){
                // expensive. Do this check very last
                if(!world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                    continue;
                }

                world.setBlockState(mutable, fire, 3);
            }
        }

        return true;
    }
}