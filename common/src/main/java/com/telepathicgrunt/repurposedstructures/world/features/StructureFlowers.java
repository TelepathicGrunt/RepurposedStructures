package com.telepathicgrunt.repurposedstructures.world.features;

import com.google.common.collect.ImmutableList;
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

import java.util.List;


public record StructureFlowers(int attempts, int range) implements Feature {

    public static final MapCodec<StructureFlowers> CODEC = RecordCodecBuilder.mapCodec((structureFlowersInstance) -> structureFlowersInstance.group(
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(structureFlowers -> structureFlowers.attempts),
            Codec.intRange(1, 200).fieldOf("range").forGetter(structureFlowers -> structureFlowers.range)
    ).apply(structureFlowersInstance, StructureFlowers::new));

    private static final List<BlockState> FLOWERS = ImmutableList.of(
            Blocks.LILY_OF_THE_VALLEY.defaultBlockState(),
            Blocks.POPPY.defaultBlockState(),
            Blocks.DANDELION.defaultBlockState(),
            Blocks.CORNFLOWER.defaultBlockState(),
            Blocks.ORANGE_TULIP.defaultBlockState(),
            Blocks.PINK_TULIP.defaultBlockState(),
            Blocks.RED_TULIP.defaultBlockState(),
            Blocks.WHITE_TULIP.defaultBlockState(),
            Blocks.ROSE_BUSH.defaultBlockState(),
            Blocks.LILAC.defaultBlockState(),
            Blocks.PEONY.defaultBlockState()
    );

    @Override
    public MapCodec<StructureFlowers> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for(int i = 0; i < attempts; i++) {
            mutable.set(origin).move(
                    random.nextInt((range * 2) + 1) - range,
                    random.nextInt(3) - 1,
                    random.nextInt((range * 2) + 1) - range
            );

            if(level.getBlockState(mutable).isAir()) {

                BlockState chosenFlower = FLOWERS.get(random.nextInt(FLOWERS.size()));

                if(chosenFlower.canSurvive(level, mutable)) {

                    if(chosenFlower.getBlock() instanceof DoublePlantBlock && level.getBlockState(mutable.above()).isAir()) {
                        level.setBlock(mutable, chosenFlower.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER), 3);
                        level.setBlock(mutable.move(Direction.UP), chosenFlower.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 3);
                    }
                    else{
                        level.setBlock(mutable, chosenFlower, 3);
                    }
                }
            }
        }

        return true;
    }
}