package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.Set;


public class JungleStructuresVines extends Feature<NoFeatureConfig> {

    public JungleStructuresVines(Codec<NoFeatureConfig> configFactory) {
        super(configFactory);
    }

    Set<Block> FORTRESS_BLOCKS_SET = Sets.newHashSet(
            Blocks.STONE_BRICKS,
            Blocks.CHISELED_STONE_BRICKS,
            Blocks.CRACKED_STONE_BRICKS,
            Blocks.MOSSY_STONE_BRICKS,
            Blocks.INFESTED_CHISELED_STONE_BRICKS,
            Blocks.INFESTED_CRACKED_STONE_BRICKS,
            Blocks.INFESTED_MOSSY_STONE_BRICKS,
            Blocks.INFESTED_STONE_BRICKS,
            Blocks.IRON_BARS,
            Blocks.STONE);

    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
        if(GeneralUtils.isWorldBlacklisted(world)) return false;

        BlockPos.Mutable mutable = new BlockPos.Mutable().set(position);
        //Place vines without replacing blocks.
        if (world.isEmptyBlock(mutable))
        {
            if(world.startsForFeature(SectionPos.of(mutable), RSStructures.JUNGLE_VILLAGE.get()).findAny().isPresent() ||
                    world.startsForFeature(SectionPos.of(mutable), RSStructures.JUNGLE_FORTRESS.get()).findAny().isPresent())
            {
                RSFeatures.SHORT_VINES.get().place(world, chunkGenerator, random, mutable, NoFeatureConfig.NONE);
                return true;
            }
        }
        //Place vines and can replace Stone Bricks if it has air below.
        if (FORTRESS_BLOCKS_SET.contains(world.getBlockState(mutable).getBlock()) && world.isEmptyBlock(mutable.move(Direction.DOWN))) {
            if (world.startsForFeature(SectionPos.of(mutable.move(Direction.UP)), RSStructures.JUNGLE_FORTRESS.get()).findAny().isPresent()) {

                world.setBlock(mutable, Blocks.AIR.defaultBlockState(), 3);
                RSFeatures.SHORT_VINES.get().place(world, chunkGenerator, random, mutable, NoFeatureConfig.NONE);

                //make sure we dont cause floating vine by scheduling a tick update so vine breaks itself
                for(Direction facing : Direction.Plane.HORIZONTAL){
                    mutable.set(position).move(facing);
                    BlockState state = world.getBlockState(mutable);

                    // no floating vines
                    while(state.getMaterial() == Material.REPLACEABLE_PLANT){
                        world.setBlock(mutable, Blocks.AIR.defaultBlockState(), 3);
                        state = world.getBlockState(mutable.move(Direction.DOWN));
                    }
                }

                return true;
            }
        }

        return false;
    }
}