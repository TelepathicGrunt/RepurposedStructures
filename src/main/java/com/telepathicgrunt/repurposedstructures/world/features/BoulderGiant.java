package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class BoulderGiant extends Feature<DefaultFeatureConfig> {
    private final static int START_RADIUS = 4;

    public BoulderGiant(Codec<DefaultFeatureConfig> configFactory) {
        super(configFactory);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config) {

        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable().set(position);
        BlockState blockState = world.getBlockState(blockpos$Mutable);

        //Will keeps moving down position until it finds valid ground to generate on while ignoring other boulders
        while (blockpos$Mutable.getY() >= 6) {
            if (blockState.getMaterial() == Material.AIR || (blockState.getBlock() != Blocks.GRASS_BLOCK && !isSoil(blockState.getBlock()))) {
                //block was air or a non-dirt/grass block. Thus move down one.
                blockpos$Mutable.move(Direction.DOWN);
                blockState = world.getBlockState(blockpos$Mutable);
            } else {
                blockpos$Mutable.move(Direction.UP, 2);
                break; //We hit a valid spot to generate a boulder, time to exit loop
            }
        }

        //if the height is too low or high, just quit.
        if (blockpos$Mutable.getY() <= 10 || blockpos$Mutable.getY() >= 250) {
            return false;
        }

        int chanceRange = RepurposedStructures.RSAllConfig.RSMainConfig.misc.diamondChanceInGiantBoulders;
        boolean disabledDiamonds = chanceRange == 0;
        if(disabledDiamonds) chanceRange = 3000;

        //we are at a valid spot to generate a boulder now. Begin generation.
        for (int currentCount = 0; currentCount < 3; ++currentCount) {
            int x = START_RADIUS + random.nextInt(2);
            int y = START_RADIUS + random.nextInt(2);
            int z = START_RADIUS + random.nextInt(2);
            float calculatedDistance = (x + y + z) * 0.333F + 0.5F;

            for (BlockPos blockpos : BlockPos.iterate(blockpos$Mutable.add(-x, -y, -z), blockpos$Mutable.add(x, y, z))) {
                if (blockpos.getSquaredDistance(blockpos$Mutable) <= calculatedDistance * calculatedDistance) {
                    //adds the blocks for generation in this boulder
                    //note, if user turns off an ore, that ore's chance is dumped into the below ore for generation
                    int randomChance = random.nextInt(chanceRange);

                    if (!disabledDiamonds && randomChance == 0) {
                        world.setBlockState(blockpos, Blocks.DIAMOND_ORE.getDefaultState(), 4);
                    }

                    // 3/3000th chance for iron ore
                    else if (randomChance <= chanceRange * 0.001F) {
                        world.setBlockState(blockpos, Blocks.IRON_ORE.getDefaultState(), 4);
                    }

                    // 150/3000th chance for coal ore
                    else if (randomChance <= chanceRange * 0.05F) {
                        world.setBlockState(blockpos, Blocks.COAL_ORE.getDefaultState(), 4);
                    }

                    // 770/3000th chance for andesite
                    else if (randomChance <= chanceRange * 0.257F) {
                        world.setBlockState(blockpos, Blocks.ANDESITE.getDefaultState(), 4);
                    }

                    // 750/3000th chance for cobblestone
                    else if (randomChance <= chanceRange * 0.25F) {
                        world.setBlockState(blockpos, Blocks.COBBLESTONE.getDefaultState(), 4);
                    }

                    // 1327/3000th chance for mossyCobblestone
                    else {
                        world.setBlockState(blockpos, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 4);
                    }
                }
            }
            blockpos$Mutable.move(
                    -(START_RADIUS + 1) + random.nextInt(2 + START_RADIUS * 2),
                    -random.nextInt(2),
                    -(START_RADIUS + 1) + random.nextInt(2 + START_RADIUS * 2));

        }

        //finished generating the boulder
        return true;
    }
}
