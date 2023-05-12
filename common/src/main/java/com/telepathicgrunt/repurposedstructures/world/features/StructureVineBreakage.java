package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.function.Predicate;


public class StructureVineBreakage extends Feature<StructureTargetAndLengthConfig> {

    public StructureVineBreakage(Codec<StructureTargetAndLengthConfig> config) {
        super(config);
    }

    private static final Predicate<BlockState> FORTRESS_BLOCKS = (blockState) -> {
        if (blockState == null) {
            return false;
        } else {
            return blockState.is(BlockTags.BASE_STONE_OVERWORLD) ||
                    blockState.is(BlockTags.STONE_BRICKS) ||
                    blockState.is(BlockTags.DIRT) |
                    blockState.is(Blocks.INFESTED_CHISELED_STONE_BRICKS) ||
                    blockState.is(Blocks.INFESTED_CRACKED_STONE_BRICKS) ||
                    blockState.is(Blocks.INFESTED_STONE_BRICKS) ||
                    blockState.is(Blocks.INFESTED_MOSSY_STONE_BRICKS) ||
                    blockState.is(Blocks.IRON_BARS);
        }
    };


    @Override
    public boolean place(FeaturePlaceContext<StructureTargetAndLengthConfig> context) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for(int i = 0; i < context.config().attempts; i++) {
            mutable.set(context.origin()).move(
                    context.random().nextInt(7) - 3,
                    context.random().nextInt(5) - 1,
                    context.random().nextInt(7) - 3
            );

            if(!FORTRESS_BLOCKS.test(context.level().getBlockState(mutable)) || !context.level().isEmptyBlock(mutable.below())) {
                continue;
            }

            // create hole in fortress block for vine
            context.level().setBlock(mutable, Blocks.CAVE_AIR.defaultBlockState(), 3);
            BlockPos.MutableBlockPos vineMutablePos = new BlockPos.MutableBlockPos().set(mutable);
            BlockState neighboringBlock = context.level().getBlockState(vineMutablePos);
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                vineMutablePos.set(mutable).move(direction);
                // no floating vines
                while(neighboringBlock.is(BlockTags.REPLACEABLE) || neighboringBlock.is(BlockTags.FLOWERS)) {
                    context.level().setBlock(vineMutablePos, Blocks.CAVE_AIR.defaultBlockState(), 3);
                    neighboringBlock = context.level().getBlockState(vineMutablePos.move(Direction.DOWN));
                }
            }

            BlockPos.MutableBlockPos replacingPlantMutable = new BlockPos.MutableBlockPos().set(mutable);
            BlockState plantState = context.level().getBlockState(replacingPlantMutable.move(Direction.UP));
            while(plantState.is(BlockTags.REPLACEABLE) || plantState.is(BlockTags.FLOWERS)) {
                context.level().setBlock(replacingPlantMutable, Blocks.AIR.defaultBlockState(), 3);
                plantState = context.level().getBlockState(replacingPlantMutable.move(Direction.UP));
            }

            // generates vines from given position down length number of blocks if path is clear and the given position is valid
            vineMutablePos.set(mutable);
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

                        currentBlockstate = Blocks.VINE.defaultBlockState().setValue(VineBlock.getPropertyForFace(direction), Boolean.TRUE);
                        aboveBlockstate = context.level().getBlockState(vineMutablePos.above());

                        if (currentBlockstate.canSurvive(context.level(), vineMutablePos) && context.level().getBlockState(vineMutablePos.relative(direction)).getBlock() != Blocks.MOSS_CARPET) {
                            //places topmost vine that can face upward
                            context.level().setBlock(vineMutablePos, currentBlockstate.setValue(VineBlock.UP, aboveBlockstate.canOcclude()), 2);
                            break;
                        }
                        else if (aboveBlockstate.is(Blocks.VINE)) {
                            //places rest of the vine as long as vine is above
                            context.level().setBlock(vineMutablePos, aboveBlockstate.setValue(VineBlock.UP, false), 2);
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