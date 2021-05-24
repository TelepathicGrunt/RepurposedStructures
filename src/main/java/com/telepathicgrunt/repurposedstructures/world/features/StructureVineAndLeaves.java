package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;


public class StructureVineAndLeaves extends Feature<StructureTargetAndLengthConfig> {

    public StructureVineAndLeaves(Codec<StructureTargetAndLengthConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos position, StructureTargetAndLengthConfig config) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int i = 0; i < config.attempts; i++){
            mutable.set(position).move(
                    random.nextInt(7) - 3,
                    random.nextInt(4) - 1,
                    random.nextInt(7) - 3
            );

            if(!world.isAir(mutable) || !world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                continue;
            }

            // generates vines from given position down length number of blocks if path is clear and the given position is valid
            int length = 0;
            BlockPos.Mutable vineMutablePos = new BlockPos.Mutable().set(mutable);
            ChunkPos currentChunkPos = new ChunkPos(vineMutablePos);
            BlockState currentBlockstate;
            BlockState aboveBlockstate;
            // Biased towards max length
            int maxLength = config.length - random.nextInt(random.nextInt(config.length) + 1);

            for (; length < maxLength; vineMutablePos.move(Direction.DOWN)) {
                if (world.isAir(vineMutablePos)) {
                    for (Direction direction : Direction.Type.HORIZONTAL) {
                        mutable.set(vineMutablePos).move(direction);
                        ChunkPos newChunkPos = new ChunkPos(mutable);
                        // Prevent floating vines at chunk borders
                        if(newChunkPos.x != currentChunkPos.x || newChunkPos.z != currentChunkPos.z) continue;

                        if(length == 0 &&
                            world.getBlockState(vineMutablePos.up()).isOpaque() &&
                            world.getBlockState(mutable).isAir() &&
                            world.getBlockState(mutable.up()).isOpaque())
                        {
                            world.setBlockState(mutable, Blocks.JUNGLE_LEAVES.getDefaultState(), 3);
                        }

                        currentBlockstate = Blocks.VINE.getDefaultState().with(VineBlock.getFacingProperty(direction), Boolean.TRUE);
                        aboveBlockstate = world.getBlockState(vineMutablePos.up());


                        if (currentBlockstate.canPlaceAt(world, vineMutablePos)) {
                            //places topmost vine that can face upward
                            world.setBlockState(vineMutablePos, currentBlockstate.with(VineBlock.UP, aboveBlockstate.isOpaque()), 2);
                            length++;
                            break;
                        }
                        else if (aboveBlockstate.isOf(Blocks.VINE)) {
                            //places rest of the vine as long as vine is above
                            world.setBlockState(vineMutablePos, aboveBlockstate.with(VineBlock.UP, false), 2);
                            length++;
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }

        return true;
    }
}