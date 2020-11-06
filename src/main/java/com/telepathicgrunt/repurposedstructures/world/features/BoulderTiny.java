package com.telepathicgrunt.repurposedstructures.world.features;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class BoulderTiny extends Feature<DefaultFeatureConfig> {

    public BoulderTiny() {
        super(DefaultFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, DefaultFeatureConfig config) {

        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable().set(position.down());
        Block block = world.getBlockState(blockpos$Mutable).getBlock();

        //boulder can only generate on grass/dirt
        if (!block.is(Blocks.PODZOL) && !block.is(Blocks.GRASS_BLOCK) && !isSoil(block)) {
            return false;
        }

        for (int currentCount = 0; currentCount < 3; ++currentCount) {
            int x = random.nextInt(2);
            int y = random.nextInt(2);
            int z = random.nextInt(2);
            float calculatedDistance = (x + y + z) * 0.333F + 0.5F;

            for (BlockPos blockpos : BlockPos.iterate(blockpos$Mutable.add(-x, -y, -z), blockpos$Mutable.add(x, y, z))) {
                if (blockpos.getSquaredDistance(blockpos$Mutable) <= calculatedDistance * calculatedDistance) {
                    //adds the blocks for generation in this boulder
                    //note, if user turns off an ore, that ore's chance is dumped into the below ore for generation
                    int randomChance = random.nextInt(1000);

                    // 35/1000th chance for iron ore
                    if (randomChance <= 35) {
                        world.setBlockState(blockpos.up(), Blocks.IRON_ORE.getDefaultState(), 4);
                    }

                    // 65/1000th chance for coal ore
                    else if (randomChance <= 100) {
                        world.setBlockState(blockpos.up(), Blocks.COAL_ORE.getDefaultState(), 4);
                    }

                    // 250/1000th chance for andesite
                    else if (randomChance <= 350) {
                        world.setBlockState(blockpos.up(), Blocks.ANDESITE.getDefaultState(), 4);
                    }

                    // 250/1000th chance for cobblestone
                    else if (randomChance <= 600) {
                        world.setBlockState(blockpos.up(), Blocks.COBBLESTONE.getDefaultState(), 4);
                    }

                    // 400/1000th chance for mossyCobblestone
                    else {
                        world.setBlockState(blockpos.up(), Blocks.MOSSY_COBBLESTONE.getDefaultState(), 4);
                    }
                }
            }
            blockpos$Mutable.move(
                    -1 + random.nextInt(2),
                    -random.nextInt(2),
                    -1 + random.nextInt(2));
        }

        //finished generating the boulder
        return true;
    }
}
