package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;


public record StructureVine(
        int attempts,
        int length,
        int xzRange,
        int heightRange
) implements Feature {

    public static final MapCodec<StructureVine> CODEC = RecordCodecBuilder.<StructureVine>mapCodec((structureVineInstance) -> structureVineInstance.group(
                    Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(structureVine -> structureVine.attempts),
                    Codec.intRange(1, 200).fieldOf("length").forGetter(structureVine -> structureVine.length),
                    Codec.intRange(1, 200).fieldOf("xz_range").forGetter(structureVine -> structureVine.xzRange),
                    Codec.intRange(1, 200).fieldOf("height_range").orElse(5).forGetter(structureVine -> structureVine.heightRange)
            ).apply(structureVineInstance, StructureVine::new))
            .validate((structureVine) -> structureVine.heightRange <= 0 ?
                    DataResult.error(() -> "height must be greater than 0") : DataResult.success(structureVine));

    @Override
    public MapCodec<StructureVine> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int i = 0; i < attempts; i++) {
            mutable.set(origin).move(
                    random.nextInt((xzRange * 2) + 1) - xzRange,
                    random.nextInt(heightRange) - 1,
                    random.nextInt((xzRange * 2) + 1) - xzRange
            );

            if (!level.isEmptyBlock(mutable)) {
                continue;
            }

            // generates vines from given position down length number of blocks if path is clear and the given position is valid
            BlockPos.MutableBlockPos vineMutablePos = new BlockPos.MutableBlockPos().set(mutable);
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
                        if (newChunkPos.x() != currentChunkPos.x() || newChunkPos.z() != currentChunkPos.z()) continue;

                        currentBlockstate = Blocks.VINE.defaultBlockState().setValue(VineBlock.getPropertyForFace(direction), Boolean.TRUE);
                        aboveBlockstate = level.getBlockState(vineMutablePos.above());

                        if (currentBlockstate.canSurvive(level, vineMutablePos) && level.getBlockState(vineMutablePos.relative(direction)).getBlock() != Blocks.MOSS_CARPET) {
                            //places topmost vine that can face upward
                            level.setBlock(vineMutablePos, currentBlockstate.setValue(VineBlock.UP, aboveBlockstate.canOcclude()), 2);
                            break;
                        } else if (aboveBlockstate.is(Blocks.VINE)) {
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