package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.Set;


public class JungleStructuresVines extends Feature<DefaultFeatureConfig> {

    public JungleStructuresVines(Codec<DefaultFeatureConfig> configFactory) {
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
    public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config) {
        //Place vines without replacing blocks.
        if (world.isAir(position))
        {
            if(structureAccessor.getStructuresWithChildren(ChunkSectionPos.from(position), RSFeatures.JUNGLE_VILLAGE).findAny().isPresent() ||
                    structureAccessor.getStructuresWithChildren(ChunkSectionPos.from(position), RSFeatures.JUNGLE_FORTRESS).findAny().isPresent())
            {
                RSFeatures.SHORT_VINES.generate(world, structureAccessor, chunkGenerator, random, position, DefaultFeatureConfig.DEFAULT);
                return true;
            }
        }
        //Place vines and can replace Stone Bricks if it has air below.
        if (FORTRESS_BLOCKS_SET.contains(world.getBlockState(position).getBlock()) && world.isAir(position.down())) {
            if (structureAccessor.getStructuresWithChildren(ChunkSectionPos.from(position), RSFeatures.JUNGLE_FORTRESS).findAny().isPresent()) {
                world.setBlockState(position, Blocks.AIR.getDefaultState(), 3);
                RSFeatures.SHORT_VINES.generate(world, structureAccessor, chunkGenerator, random, position, DefaultFeatureConfig.DEFAULT);
                return true;
            }
        }

        return false;
    }
}