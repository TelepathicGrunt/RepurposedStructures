package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.utils.OpenSimplex2F;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;


public class BoulderGiant extends Feature<NoFeatureConfig> {

    protected long seed;
    protected static OpenSimplex2F noiseGen;

    public void setSeed(long seed) {
        if (this.seed != seed || noiseGen == null) {
            noiseGen = new OpenSimplex2F(seed);
            this.seed = seed;
        }
    }

    public BoulderGiant(Codec<NoFeatureConfig> configFactory) {
        super(configFactory);
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
        setSeed(world.getSeed());

        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable().setPos(position);
        BlockState blockState = world.getBlockState(blockpos$Mutable);

        //Will keeps moving down position until it finds valid ground to generate on while ignoring other boulders
        while (blockpos$Mutable.getY() >= 10) {
            if (!blockState.getBlock().is(Blocks.PODZOL) && !blockState.getBlock().is(Blocks.GRASS_BLOCK) && !isSoil(blockState.getBlock())) {
                //block was air or a non-dirt/grass block. Thus move down one.
                blockpos$Mutable.move(Direction.DOWN);
                blockState = world.getBlockState(blockpos$Mutable);
            } else {
                blockpos$Mutable.move(Direction.UP);
                break; //We hit a valid spot to generate a boulder, time to exit loop
            }
        }

        //if the height is too low or high, just quit.
        if (blockpos$Mutable.getY() <= 10 || blockpos$Mutable.getY() >= 250) {
            return false;
        }

        int chanceRange = RepurposedStructures.RSMainConfig.diamondChanceInGiantBoulders.get();
        boolean disabledDiamonds = chanceRange == 0;
        if(disabledDiamonds) chanceRange = 3000;

        //we are at a valid spot to generate a boulder now. Begin generation.
        for (int currentCount = 0; currentCount < 3; ++currentCount) {
            int startRadius = random.nextInt(4) + 1; // 1 to 4 block radius

            int x = startRadius + random.nextInt(2);
            int y = startRadius + random.nextInt(2);
            int z = startRadius + random.nextInt(2);
            float calculatedDistance = (x + y + z) * 0.333F + 0.5F;

            for (BlockPos blockpos : BlockPos.getAllInBoxMutable(blockpos$Mutable.add(-x, -y, -z), blockpos$Mutable.add(x, y, z))) {
                if (blockpos.distanceSq(blockpos$Mutable) <= calculatedDistance * calculatedDistance) {

                    double noiseValue = noiseGen.noise3_Classic(blockpos.getX() * 0.035D, blockpos.getY() * 0.0075D, blockpos.getZ() * 0.035D);
                    if(blockpos.distanceSq(blockpos$Mutable) > calculatedDistance * calculatedDistance * 0.65f &&
                            noiseValue > -0.3D && noiseValue < 0.3D){
                        continue; // Rough the surface of the boulders a bit
                    }

                    //adds the blocks for generation in this boulder
                    //note, if user turns off an ore, that ore's chance is dumped into the below ore for generation
                    int randomChance = random.nextInt(chanceRange);

                    // 1/3000th chance for diamond ore
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
                     random.nextInt(startRadius * 2) - startRadius,
                    0,
                    random.nextInt(startRadius * 2) - startRadius);

            blockpos$Mutable.move(Direction.UP,
                    world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, blockpos$Mutable.getX(), blockpos$Mutable.getZ())
                            + 1
                            - random.nextInt(2)
                            - blockpos$Mutable.getY());

        }

        //finished generating the boulder
        return true;
    }
}
