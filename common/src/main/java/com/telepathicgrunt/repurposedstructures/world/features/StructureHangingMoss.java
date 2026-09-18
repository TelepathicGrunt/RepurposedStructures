package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HangingMossBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;


public record StructureHangingMoss(
        int attempts,
        int length,
        int xzRange,
        int heightRange
) implements Feature {

    public static final MapCodec<StructureHangingMoss> CODEC = RecordCodecBuilder.<StructureHangingMoss>mapCodec((instance) -> instance.group(
                    Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(feature -> feature.attempts),
                    Codec.intRange(1, 200).fieldOf("length").forGetter(feature -> feature.length),
                    Codec.intRange(1, 200).fieldOf("xz_range").forGetter(feature -> feature.xzRange),
                    Codec.intRange(1, 200).fieldOf("height_range").orElse(5).forGetter(feature -> feature.heightRange)
            ).apply(instance, StructureHangingMoss::new))
            .validate((feature) -> feature.heightRange <= 0 ?
                    DataResult.error(() -> "height must be greater than 0") : DataResult.success(feature));

    @Override
    public MapCodec<StructureHangingMoss> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState hangingMoss = Blocks.PALE_HANGING_MOSS.defaultBlockState();

        for (int i = 0; i < attempts; i++) {
            mutable.set(origin).move(
                    random.nextInt((xzRange * 2) + 1) - xzRange,
                    random.nextInt(heightRange) - 1,
                    random.nextInt((xzRange * 2) + 1) - xzRange
            );

            if (level.isEmptyBlock(mutable) && hangingMoss.canSurvive(level, mutable)) {
                mutable.move(Direction.UP);
                if (level.getBlockState(mutable).is(hangingMoss.getBlock())) {
                    continue;
                }
                mutable.move(Direction.DOWN);

                // Biased towards max length if greater than 3
                int maxLength = length > 3 ? length - random.nextInt(random.nextInt(length) + 1) : random.nextInt(length);
                for (int currentLength = 0; currentLength <= maxLength; currentLength++) {
                    if (currentLength == maxLength || !level.getBlockState(mutable.below()).isAir()) {
                        level.setBlock(mutable, hangingMoss.setValue(HangingMossBlock.TIP, true), 3);
                        break;
                    }
                    level.setBlock(mutable, hangingMoss.setValue(HangingMossBlock.TIP, false), 3);
                    mutable.move(Direction.DOWN);
                }
            }
        }

        return true;
    }
}