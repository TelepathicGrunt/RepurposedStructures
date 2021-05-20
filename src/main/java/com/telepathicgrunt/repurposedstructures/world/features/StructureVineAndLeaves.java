package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
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
                    random.nextInt(21) - 10,
                    random.nextInt(21) - 10,
                    random.nextInt(21) - 10
            );

            if(!world.isAir(mutable) || !world.toServerWorld().getStructureAccessor().getStructureAt(mutable, true, config.targetStructure).hasChildren()){
                continue;
            }

            // generates vines from given position down length number of blocks if path is clear and the given position is valid
            int length = 0;
            BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable().set(mutable);
            BlockState currentBlockstate;
            BlockState aboveBlockstate;

            for (; length < random.nextInt(config.length); blockpos$Mutable.move(Direction.DOWN)) {
                if (world.isAir(blockpos$Mutable)) {
                    for (Direction direction : Direction.Type.HORIZONTAL) {

                        currentBlockstate = Blocks.VINE.getDefaultState().with(VineBlock.getFacingProperty(direction), Boolean.TRUE);
                        aboveBlockstate = world.getBlockState(blockpos$Mutable.up());

                        if(world.getBlockState(blockpos$Mutable.offset(direction)).isAir()){
                            world.setBlockState(blockpos$Mutable.offset(direction), Blocks.JUNGLE_LEAVES.getDefaultState(), 3);
                        }

                        if (currentBlockstate.canPlaceAt(world, blockpos$Mutable)) {
                            //places topmost vine that can face upward
                            //tick scheduled so it can break if block it was attached to was removed later in worldgen
                            world.setBlockState(blockpos$Mutable, currentBlockstate.with(VineBlock.UP, aboveBlockstate.isOpaque()), 2);
                            world.getBlockTickScheduler().schedule(blockpos$Mutable.toImmutable(), currentBlockstate.getBlock(), 1);
                            length++;
                            break;
                        }
                        else if (aboveBlockstate.isOf(Blocks.VINE)) {
                            //places rest of the vine as long as vine is above
                            //tick scheduled so it can break if block it was attached to was removed later in worldgen
                            world.setBlockState(blockpos$Mutable, aboveBlockstate.with(VineBlock.UP, false), 2);
                            world.getBlockTickScheduler().schedule(blockpos$Mutable.toImmutable(), aboveBlockstate.getBlock(), 1);
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