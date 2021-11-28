package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;


public class StructureVineAndLeaves extends Feature<StructureTargetAndLengthConfig> {

    public StructureVineAndLeaves(Codec<StructureTargetAndLengthConfig> config) {
        super(config);
    }


    @Override
    public boolean place(FeaturePlaceContext<StructureTargetAndLengthConfig> context) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for(int i = 0; i < context.config().attempts; i++) {
            mutable.set(context.origin()).move(
                    context.random().nextInt(7) - 3,
                    context.random().nextInt(4) - 1,
                    context.random().nextInt(7) - 3
            );

            if(!context.level().isEmptyBlock(mutable)) {
                continue;
            }

            // generates vines from given position down length number of blocks if path is clear and the given position is valid
            int length = 0;
            BlockPos.MutableBlockPos vineMutablePos = new BlockPos.MutableBlockPos().set(mutable);
            ChunkPos currentChunkPos = new ChunkPos(vineMutablePos);
            BlockState currentBlockstate;
            BlockState aboveBlockstate;
            // Biased towards max length
            int maxLength = context.config().length - context.random().nextInt(context.random().nextInt(context.config().length) + 1);
            int targetY = vineMutablePos.getY() - maxLength;

            for (; vineMutablePos.getY() >= targetY; vineMutablePos.move(Direction.DOWN)) {
                if (context.level().isEmptyBlock(vineMutablePos)) {
                    for (Direction direction : Direction.Plane.HORIZONTAL) {
                        mutable.set(vineMutablePos).move(direction);
                        ChunkPos newChunkPos = new ChunkPos(mutable);
                        // Prevent floating vines at chunk borders
                        if(newChunkPos.x != currentChunkPos.x || newChunkPos.z != currentChunkPos.z) continue;

                        if(length == 0 &&
                            context.level().getBlockState(vineMutablePos.above()).canOcclude() &&
                            context.level().getBlockState(mutable).isAir() &&
                            context.level().getBlockState(mutable.above()).canOcclude())
                        {
                            context.level().setBlock(mutable, Blocks.JUNGLE_LEAVES.defaultBlockState(), 3);
                        }

                        currentBlockstate = Blocks.VINE.defaultBlockState().setValue(VineBlock.getPropertyForFace(direction), Boolean.TRUE);
                        aboveBlockstate = context.level().getBlockState(vineMutablePos.above());


                        if (currentBlockstate.canSurvive(context.level(), vineMutablePos) && context.level().getBlockState(vineMutablePos.relative(direction)).getBlock() != Blocks.MOSS_CARPET) {
                            //places topmost vine that can face upward
                            context.level().setBlock(vineMutablePos, currentBlockstate.setValue(VineBlock.UP, aboveBlockstate.canOcclude()), 2);
                            length++;
                            break;
                        }
                        else if (aboveBlockstate.is(Blocks.VINE)) {
                            //places rest of the vine as long as vine is above
                            context.level().setBlock(vineMutablePos, aboveBlockstate.setValue(VineBlock.UP, false), 2);
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