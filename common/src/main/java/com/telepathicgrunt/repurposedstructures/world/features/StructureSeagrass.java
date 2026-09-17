package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;


public record StructureSeagrass(int attempts) implements Feature {

    public static final MapCodec<StructureSeagrass> CODEC = RecordCodecBuilder.mapCodec((structureSeagrassInstance) -> structureSeagrassInstance.group(
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(structureSeagrass -> structureSeagrass.attempts)
    ).apply(structureSeagrassInstance, StructureSeagrass::new));

    @Override
    public MapCodec<StructureSeagrass> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState tallSeagrass = Blocks.TALL_SEAGRASS.defaultBlockState();
        BlockState seagrass = Blocks.SEAGRASS.defaultBlockState();

        for(int i = 0; i < attempts; i++) {
            mutable.set(origin).move(
                    random.nextInt(7) - 3,
                    -1,
                    random.nextInt(7) - 3
            );

            boolean isWater = level.getBlockState(mutable).is(Blocks.WATER);
            if(!isWater) continue;

            boolean isWaterAbove = level.getBlockState(mutable.above()).is(Blocks.WATER);
            if(isWaterAbove && random.nextFloat() < 0.33f && tallSeagrass.canSurvive(level, mutable)) {
                

                level.setBlock(mutable, tallSeagrass.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.LOWER), 3);
                level.setBlock(mutable.move(Direction.UP), tallSeagrass.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER), 3);
            }
            else if(seagrass.canSurvive(level, mutable)) {
                

                level.setBlock(mutable, Blocks.SEAGRASS.defaultBlockState(), 3);
            }
        }

        return true;
    }
}