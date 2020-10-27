package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSFeatures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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
        //Place vines without replacing blocks.
        if (world.isAirBlock(position))
        {
            if(world.getStructures(SectionPos.from(position), RSStructures.JUNGLE_VILLAGE.get()).findAny().isPresent() ||
                    world.getStructures(SectionPos.from(position), RSStructures.JUNGLE_FORTRESS.get()).findAny().isPresent())
            {
                RSFeatures.SHORT_VINES.get().generate(world, chunkGenerator, random, position, NoFeatureConfig.NO_FEATURE_CONFIG);
                return true;
            }
        }
        //Place vines and can replace Stone Bricks if it has air below.
        if (FORTRESS_BLOCKS_SET.contains(world.getBlockState(position).getBlock()) && world.isAirBlock(position.down())) {
            if (world.getStructures(SectionPos.from(position), RSStructures.JUNGLE_FORTRESS.get()).findAny().isPresent()) {
                world.setBlockState(position, Blocks.AIR.getDefaultState(), 3);
                RSFeatures.SHORT_VINES.get().generate(world, chunkGenerator, random, position, NoFeatureConfig.NO_FEATURE_CONFIG);
                return true;
            }
        }

        return false;
    }
}