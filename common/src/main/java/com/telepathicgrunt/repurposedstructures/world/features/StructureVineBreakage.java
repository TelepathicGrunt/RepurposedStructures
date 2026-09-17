package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.function.Predicate;


public record StructureVineBreakage(
        int attempts,
        int length
) implements Feature {

    public static final MapCodec<StructureVineBreakage> CODEC = RecordCodecBuilder.<StructureVineBreakage>mapCodec((structureVineBreakageInstance) -> structureVineBreakageInstance.group(
                    Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(structureVineBreakage -> structureVineBreakage.attempts),
                    Codec.intRange(1, 200).fieldOf("length").forGetter(structureVineBreakage -> structureVineBreakage.length)
            ).apply(structureVineBreakageInstance, StructureVineBreakage::new));

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
    public MapCodec<StructureVineBreakage> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for(int i = 0; i < attempts; i++) {
            mutable.set(origin).move(
                    random.nextInt(7) - 3,
                    random.nextInt(5) - 1,
                    random.nextInt(7) - 3
            );

            if(!FORTRESS_BLOCKS.test(level.getBlockState(mutable)) || !level.isEmptyBlock(mutable.below())) {
                continue;
            }

            // create hole in fortress block for vine
            level.setBlock(mutable, Blocks.CAVE_AIR.defaultBlockState(), 3);
            BlockPos.MutableBlockPos vineMutablePos = new BlockPos.MutableBlockPos().set(mutable);
            BlockState neighboringBlock = level.getBlockState(vineMutablePos);
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                vineMutablePos.set(mutable).move(direction);
                // no floating vines
                while(mutable.getY() > level.getMinY() &&
                        mutable.getY() < level.getMaxY() &&
                        (neighboringBlock.is(BlockTags.REPLACEABLE_BY_TREES) || neighboringBlock.is(BlockTags.FLOWERS)))
                {
                    level.setBlock(vineMutablePos, Blocks.CAVE_AIR.defaultBlockState(), 3);
                    neighboringBlock = level.getBlockState(vineMutablePos.move(Direction.DOWN));
                }
            }

            BlockPos.MutableBlockPos replacingPlantMutable = new BlockPos.MutableBlockPos().set(mutable);
            BlockState plantState = level.getBlockState(replacingPlantMutable.move(Direction.UP));
            while(mutable.getY() > level.getMinY() &&
                    mutable.getY() < level.getMaxY() &&
                    (plantState.is(BlockTags.REPLACEABLE_BY_TREES) || plantState.is(BlockTags.FLOWERS)))
            {
                level.setBlock(replacingPlantMutable, Blocks.AIR.defaultBlockState(), 3);
                plantState = level.getBlockState(replacingPlantMutable.move(Direction.UP));
            }

            // generates vines from given position down length number of blocks if path is clear and the given position is valid
            vineMutablePos.set(mutable);
            ChunkPos currentChunkPos = ChunkPos.containing(vineMutablePos);
            BlockState currentBlockstate;
            BlockState aboveBlockstate;
            // Biased towards max length
            int maxLength = length - random.nextInt(random.nextInt(length) + 1);
            int targetY = vineMutablePos.getY() - maxLength;

            for (; vineMutablePos.getY() >= targetY; vineMutablePos.move(Direction.DOWN)) {
                if (level.isEmptyBlock(vineMutablePos)) {
                    for (Direction direction : Direction.Plane.HORIZONTAL) {
                        mutable.set(vineMutablePos).move(direction);
                        ChunkPos newChunkPos = ChunkPos.containing(mutable);
                        // Prevent floating vines at chunk borders
                        if(newChunkPos.x() != currentChunkPos.x() || newChunkPos.z() != currentChunkPos.z()) continue;

                        currentBlockstate = Blocks.VINE.defaultBlockState().setValue(VineBlock.getPropertyForFace(direction), Boolean.TRUE);
                        aboveBlockstate = level.getBlockState(vineMutablePos.above());

                        if (currentBlockstate.canSurvive(level, vineMutablePos) && level.getBlockState(vineMutablePos.relative(direction)).getBlock() != Blocks.MOSS_CARPET) {
                            //places topmost vine that can face upward
                            level.setBlock(vineMutablePos, currentBlockstate.setValue(VineBlock.UP, aboveBlockstate.canOcclude()), 2);
                            break;
                        }
                        else if (aboveBlockstate.is(Blocks.VINE)) {
                            //places rest of the vine as long as vine is above
                            level.setBlock(vineMutablePos, aboveBlockstate.setValue(VineBlock.UP, false), 2);
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