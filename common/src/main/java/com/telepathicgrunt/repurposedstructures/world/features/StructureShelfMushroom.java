package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.modinit.RSTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ShelfBlock;
import net.minecraft.world.level.block.ShelfMushroomBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;


public record StructureShelfMushroom(
        int attempts,
        int xzRange,
        int heightRange
) implements Feature {

    public static final MapCodec<StructureShelfMushroom> CODEC = RecordCodecBuilder.<StructureShelfMushroom>mapCodec((structureVineInstance) -> structureVineInstance.group(
                    Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(structureVine -> structureVine.attempts),
                    Codec.intRange(1, 200).fieldOf("xz_range").forGetter(structureVine -> structureVine.xzRange),
                    Codec.intRange(1, 200).fieldOf("height_range").orElse(5).forGetter(structureVine -> structureVine.heightRange)
            ).apply(structureVineInstance, StructureShelfMushroom::new))
            .validate((structureVine) -> structureVine.heightRange <= 0 ?
                    DataResult.error(() -> "height must be greater than 0") : DataResult.success(structureVine));

    @Override
    public MapCodec<StructureShelfMushroom> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int i = 0; i < attempts; i++) {
            mutable.set(origin).move(
                    random.nextInt((xzRange * 2) + 1) - xzRange,
                    random.nextInt((heightRange * 2) + 1) - heightRange,
                    random.nextInt((xzRange * 2) + 1) - xzRange
            );

            if (!level.isEmptyBlock(mutable)) {
                continue;
            }

            BlockPos.MutableBlockPos mushroomMutablePos = new BlockPos.MutableBlockPos().set(mutable);
            ChunkPos currentChunkPos = ChunkPos.containing(mushroomMutablePos);
            BlockState currentBlockstate;

            if (level.isEmptyBlock(mushroomMutablePos)) {
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    mutable.set(mushroomMutablePos).move(direction);
                    ChunkPos newChunkPos = ChunkPos.containing(mutable);
                    // Prevent floating mushrooms at chunk borders
                    if (newChunkPos.x() != currentChunkPos.x() || newChunkPos.z() != currentChunkPos.z()) continue;

                    currentBlockstate = Blocks.SHELF_MUSHROOM.defaultBlockState()
                            .setValue(ShelfMushroomBlock.AGE, random.nextInt(2))
                            .setValue(ShelfMushroomBlock.FACING, direction.getOpposite());
                    BlockState sideState = level.getBlockState(mutable);
                    if (currentBlockstate.canSurvive(level, mushroomMutablePos) && sideState.is(RSTags.SHELF_MUSHROOM_BASE_ATTACHMENT)) {
                        level.setBlock(mushroomMutablePos, currentBlockstate, Block.UPDATE_CLIENTS);
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