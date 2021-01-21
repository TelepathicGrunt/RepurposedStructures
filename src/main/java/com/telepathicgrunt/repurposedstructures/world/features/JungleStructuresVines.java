package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
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
    public boolean generate(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {

        BlockPos.Mutable mutable = new BlockPos.Mutable().setPos(position);
        //Place vines without replacing blocks.
        if (world.isAirBlock(mutable))
        {
            if(world.getStructures(SectionPos.from(mutable), RSStructures.JUNGLE_VILLAGE.get()).findAny().isPresent() ||
                    world.getStructures(SectionPos.from(mutable), RSStructures.JUNGLE_FORTRESS.get()).findAny().isPresent())
            {
                RSFeatures.SHORT_VINES.get().generate(world, chunkGenerator, random, mutable, NoFeatureConfig.NO_FEATURE_CONFIG);
                return true;
            }
        }
        //Place vines and can replace Stone Bricks if it has air below.
        if (FORTRESS_BLOCKS_SET.contains(world.getBlockState(mutable).getBlock()) && world.isAirBlock(mutable.move(Direction.DOWN))) {
            if (world.getStructures(SectionPos.from(mutable.move(Direction.UP)), RSStructures.JUNGLE_FORTRESS.get()).findAny().isPresent()) {

                world.setBlockState(mutable, Blocks.AIR.getDefaultState(), 3);
                RSFeatures.SHORT_VINES.get().generate(world, chunkGenerator, random, mutable, NoFeatureConfig.NO_FEATURE_CONFIG);

                //make sure we dont cause floating vine by scheduling a tick update so vine breaks itself
                for(Direction facing : Direction.Plane.HORIZONTAL){
                    mutable.setPos(position).move(facing);
                    BlockState state = world.getBlockState(mutable);

                    // no floating vines
                    while(state.getMaterial() == Material.TALL_PLANTS){
                        world.setBlockState(mutable, Blocks.AIR.getDefaultState(), 3);
                        state = world.getBlockState(mutable.move(Direction.DOWN));
                    }
                }

                return true;
            }
        }

        return false;
    }
}