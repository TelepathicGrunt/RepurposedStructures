package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;


public class StructureVineAndLeaves extends Feature<StructureTargetAndLengthConfig> {

    public StructureVineAndLeaves(Codec<StructureTargetAndLengthConfig> config) {
        super(config);
    }


    @Override
    public boolean generate(FeatureContext<StructureTargetAndLengthConfig> context) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int i = 0; i < context.getConfig().attempts; i++){
            mutable.set(context.getOrigin()).move(
                    context.getRandom().nextInt(7) - 3,
                    context.getRandom().nextInt(4) - 1,
                    context.getRandom().nextInt(7) - 3
            );

            if(!context.getWorld().isAir(mutable) || !context.getWorld().toServerWorld().getStructureAccessor().getStructureAt(mutable, true, context.getConfig().targetStructure).hasChildren()){
                continue;
            }

            // generates vines from given position down length number of blocks if path is clear and the given position is valid
            int length = 0;
            BlockPos.Mutable vineMutablePos = new BlockPos.Mutable().set(mutable);
            ChunkPos currentChunkPos = new ChunkPos(vineMutablePos);
            BlockState currentBlockstate;
            BlockState aboveBlockstate;
            // Biased towards max length
            int maxLength = context.getConfig().length - context.getRandom().nextInt(context.getRandom().nextInt(context.getConfig().length) + 1);

            for (; length < maxLength; vineMutablePos.move(Direction.DOWN)) {
                if (context.getWorld().isAir(vineMutablePos)) {
                    for (Direction direction : Direction.Type.HORIZONTAL) {
                        mutable.set(vineMutablePos).move(direction);
                        ChunkPos newChunkPos = new ChunkPos(mutable);
                        // Prevent floating vines at chunk borders
                        if(newChunkPos.x != currentChunkPos.x || newChunkPos.z != currentChunkPos.z) continue;

                        if(length == 0 &&
                            context.getWorld().getBlockState(vineMutablePos.up()).isOpaque() &&
                            context.getWorld().getBlockState(mutable).isAir() &&
                            context.getWorld().getBlockState(mutable.up()).isOpaque())
                        {
                            context.getWorld().setBlockState(mutable, Blocks.JUNGLE_LEAVES.getDefaultState(), 3);
                        }

                        currentBlockstate = Blocks.VINE.getDefaultState().with(VineBlock.getFacingProperty(direction), Boolean.TRUE);
                        aboveBlockstate = context.getWorld().getBlockState(vineMutablePos.up());


                        if (currentBlockstate.canPlaceAt(context.getWorld(), vineMutablePos) && context.getWorld().getBlockState(vineMutablePos.offset(direction)).getBlock() != Blocks.MOSS_CARPET) {
                            //places topmost vine that can face upward
                            context.getWorld().setBlockState(vineMutablePos, currentBlockstate.with(VineBlock.UP, aboveBlockstate.isOpaque()), 2);
                            length++;
                            break;
                        }
                        else if (aboveBlockstate.isOf(Blocks.VINE)) {
                            //places rest of the vine as long as vine is above
                            context.getWorld().setBlockState(vineMutablePos, aboveBlockstate.with(VineBlock.UP, false), 2);
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