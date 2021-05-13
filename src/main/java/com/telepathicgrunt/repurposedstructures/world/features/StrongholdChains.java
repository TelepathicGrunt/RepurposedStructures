package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.GeneralUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Predicate;


public class StrongholdChains extends Feature<NoFeatureConfig> {

    public StrongholdChains(Codec<NoFeatureConfig> configFactory) {
        super(configFactory);
    }

    private static final Predicate<BlockState> NETHER_STRONGHOLD_BLOCKS = (blockState) -> {
        if (blockState == null) {
            return false;
        } else {
            return  blockState.is(Blocks.NETHER_BRICKS)  ||
                    blockState.is(Blocks.CHISELED_NETHER_BRICKS)  ||
                    blockState.is(Blocks.CRACKED_NETHER_BRICKS)  ||
                    blockState.is(Blocks.RED_NETHER_BRICKS)  ||
                    blockState.is(Blocks.MAGMA_BLOCK)  ||
                    blockState.is(Blocks.BLACKSTONE)  ||
                    blockState.is(Blocks.POLISHED_BLACKSTONE)  ||
                    blockState.is(Blocks.POLISHED_BLACKSTONE_BRICKS)  ||
                    blockState.is(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)  ||
                    blockState.is(Blocks.CHISELED_POLISHED_BLACKSTONE)  ||
                    blockState.is(Blocks.GILDED_BLACKSTONE);
        }
    };

    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
        if(GeneralUtils.isWorldBlacklisted(world)) return false;
        if (!world.isEmptyBlock(position) || !world.startsForFeature(SectionPos.of(position), RSStructures.NETHER_STRONGHOLD.get()).findAny().isPresent())
        {
           return false;
        }

        //move up if above is air (only goes 10 blocks up before giving up
        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable().set(position);
        for(int i = 0; i < 10 && world.isEmptyBlock(blockpos$Mutable.above()) && blockpos$Mutable.getY() < 255; i++){
            blockpos$Mutable.move(Direction.UP);
        }

        // generates chains from given position down 1-8 blocks if path is clear and the given position is valid
        int length = 0;
        BlockState aboveBlockstate;

        for (; blockpos$Mutable.getY() > 3 && length < random.nextInt(random.nextInt(random.nextInt(8) + 1) + 1) + 1; blockpos$Mutable.move(Direction.DOWN)) {
            if (world.isEmptyBlock(blockpos$Mutable)) {
                aboveBlockstate = world.getBlockState(blockpos$Mutable.above());

                if (NETHER_STRONGHOLD_BLOCKS.test(aboveBlockstate) || aboveBlockstate.is(Blocks.CHAIN)) {
                    world.setBlock(blockpos$Mutable, Blocks.CHAIN.defaultBlockState(), 2);
                    length++;
                }
            } else {
                return true;
            }
        }

        //attaches lantern at end at a rare chance
        if(blockpos$Mutable.getY() != 3 && random.nextFloat() < 0.075f && world.isEmptyBlock(blockpos$Mutable)){
            world.setBlock(blockpos$Mutable, Blocks.SOUL_LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, true), 2);
        }

        return true;
    }
}