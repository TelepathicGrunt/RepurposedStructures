package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;


public record StructureGrass(int attempts, int range) implements Feature {

    public static final MapCodec<StructureGrass> CODEC = RecordCodecBuilder.mapCodec((structureGrassInstance) -> structureGrassInstance.group(
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(structureGrass -> structureGrass.attempts),
            Codec.intRange(1, 200).fieldOf("range").forGetter(structureGrass -> structureGrass.range)
    ).apply(structureGrassInstance, StructureGrass::new));

    @Override
    public MapCodec<StructureGrass> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState grass = Blocks.SHORT_GRASS.defaultBlockState();
        BlockState tallGrass = Blocks.TALL_GRASS.defaultBlockState();

        for (int i = 0; i < attempts; i++) {
            mutable.set(origin).move(
                    random.nextInt((range * 2) + 1) - range,
                    random.nextInt(3) - 1,
                    random.nextInt((range * 2) + 1) - range
            );

            if (level.getBlockState(mutable).isAir()) {
                if ((random.nextFloat() < 0.45f || !level.getBlockState(mutable.above()).isAir()) && grass.canSurvive(level, mutable)) {

                    level.setBlock(mutable, grass, 3);
                } else if (tallGrass.canSurvive(level, mutable)) {

                    level.setBlock(mutable, tallGrass.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER), 3);
                    level.setBlock(mutable.move(Direction.UP), tallGrass.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 3);
                }
            }
        }

        return true;
    }
}