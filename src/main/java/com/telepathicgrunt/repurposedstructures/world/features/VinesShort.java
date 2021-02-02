package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;


public class VinesShort extends Feature<NoFeatureConfig> {

    public VinesShort(Codec<NoFeatureConfig> configFactory) {
        super(configFactory);
    }


    @Override
    public boolean generate(ISeedReader world, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
        if (!world.isAirBlock(position)) {
            return false;
        }

        // generates vines from given position down 4-6 blocks if path is clear and the given position is valid
        // Also won't generate vines below Y = 15.
        int length = 0;
        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable().setPos(position);
        BlockState currentBlockstate;
        BlockState aboveBlockstate;
        ChunkPos currentChunkPos = new ChunkPos(position);

        for (; blockpos$Mutable.getY() > 15 && length < random.nextInt(3) + 4; blockpos$Mutable.move(Direction.DOWN)) {
            if (world.isAirBlock(blockpos$Mutable)) {
                for (Direction direction : Direction.Plane.HORIZONTAL) {

                    // Attempt to prevent floating vines in jungle fortress
                    ChunkPos facingChunkPos = new ChunkPos(blockpos$Mutable.move(direction));
                    blockpos$Mutable.move(direction.getOpposite());
                    if(!currentChunkPos.equals(facingChunkPos)){
                        break;
                    }

                    currentBlockstate = Blocks.VINE.getDefaultState().with(VineBlock.getPropertyFor(direction), Boolean.TRUE);
                    aboveBlockstate = world.getBlockState(blockpos$Mutable.up());

                    if (currentBlockstate.isValidPosition(world, blockpos$Mutable)) {
                        //places topmost vine that can face upward
                        //tick scheduled so it can break if block it was attached to was removed later in worldgen
                        world.setBlockState(blockpos$Mutable, currentBlockstate.with(VineBlock.UP, aboveBlockstate.isSolid()), 2);
                        length++;
                        break;
                    }
                    else if (aboveBlockstate.isIn(Blocks.VINE)) {
                        //places rest of the vine as long as vine is above
                        //tick scheduled so it can break if block it was attached to was removed later in worldgen
                        world.setBlockState(blockpos$Mutable, aboveBlockstate.with(VineBlock.UP, false), 2);
                        length++;
                        break;
                    }
                }
            } else {
                break;
            }
        }

        return true;
    }
}