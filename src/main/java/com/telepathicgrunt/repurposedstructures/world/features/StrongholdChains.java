package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RSFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Predicate;


public class StrongholdChains extends Feature<DefaultFeatureConfig> {

    public StrongholdChains(Codec<DefaultFeatureConfig> configFactory) {
        super(configFactory);
    }

    private static final Predicate<BlockState> STRONGHOLD_BLOCKS = (blockState) -> {
        if (blockState == null) {
            return false;
        } else {
            return  blockState.isOf(Blocks.NETHER_BRICKS)  ||
                    blockState.isOf(Blocks.CHISELED_NETHER_BRICKS)  ||
                    blockState.isOf(Blocks.CRACKED_NETHER_BRICKS)  ||
                    blockState.isOf(Blocks.RED_NETHER_BRICKS)  ||
                    blockState.isOf(Blocks.MAGMA_BLOCK)  ||
                    blockState.isOf(Blocks.BLACKSTONE)  ||
                    blockState.isOf(Blocks.POLISHED_BLACKSTONE)  ||
                    blockState.isOf(Blocks.POLISHED_BLACKSTONE_BRICKS)  ||
                    blockState.isOf(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)  ||
                    blockState.isOf(Blocks.CHISELED_POLISHED_BLACKSTONE)  ||
                    blockState.isOf(Blocks.GILDED_BLACKSTONE)  ||
                    blockState.isOf(Blocks.COBBLESTONE)  ||
                    blockState.isOf(Blocks.STONE_BRICKS)  ||
                    blockState.isOf(Blocks.CHISELED_STONE_BRICKS)  ||
                    blockState.isOf(Blocks.CRACKED_STONE_BRICKS)  ||
                    blockState.isOf(Blocks.MOSSY_STONE_BRICKS)  ||
                    blockState.isOf(Blocks.INFESTED_CHISELED_STONE_BRICKS) ||
                    blockState.isOf(Blocks.INFESTED_CRACKED_STONE_BRICKS) ||
                    blockState.isOf(Blocks.INFESTED_STONE_BRICKS) ||
                    blockState.isOf(Blocks.INFESTED_MOSSY_STONE_BRICKS);
        }
    };

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config) {
        if (!world.isAir(position) ||
                (!world.getStructures(ChunkSectionPos.from(position), RSFeatures.STONEBRICK_STRONGHOLD).findAny().isPresent() &&
                 !world.getStructures(ChunkSectionPos.from(position), RSFeatures.NETHER_STRONGHOLD).findAny().isPresent()))
        {
           return false;
        }

        //move up if above is air (only goes 10 blocks up before giving up
        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable().set(position);
        for(int i = 0; i < 10 && world.isAir(blockpos$Mutable.up()) && blockpos$Mutable.getY() < 255; i++){
            blockpos$Mutable.move(Direction.UP);
        }

        // generates chains from given position down 1-8 blocks if path is clear and the given position is valid
        int length = 0;
        BlockState aboveBlockstate;

        for (; blockpos$Mutable.getY() > 3 && length < random.nextInt(random.nextInt(random.nextInt(8) + 1) + 1) + 1; blockpos$Mutable.move(Direction.DOWN)) {
            if (world.isAir(blockpos$Mutable)) {
                aboveBlockstate = world.getBlockState(blockpos$Mutable.up());

                if (STRONGHOLD_BLOCKS.test(aboveBlockstate) || aboveBlockstate.isOf(Blocks.CHAIN)) {
                    world.setBlockState(blockpos$Mutable, Blocks.CHAIN.getDefaultState(), 2);
                    length++;
                }
            } else {
                return true;
            }
        }

        //attaches lantern at end at a rare chance
        if(random.nextFloat() < 0.075f && world.isAir(blockpos$Mutable)){
            if(world.getBiome(blockpos$Mutable).getCategory() == Biome.Category.NETHER){
                world.setBlockState(blockpos$Mutable, Blocks.SOUL_LANTERN.getDefaultState().with(LanternBlock.HANGING, true), 2);
            }
            else{
                world.setBlockState(blockpos$Mutable, Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING, true), 2);
            }
        }

        return true;
    }
}