package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;


public record StructureCrimsonPlants(int attempts, int length) implements Feature {

    public static final MapCodec<StructureCrimsonPlants> CODEC = RecordCodecBuilder.mapCodec((structureCrimsonPlantsInstance) -> structureCrimsonPlantsInstance.group(
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(structureCrimsonPlants -> structureCrimsonPlants.attempts),
            Codec.intRange(1, 200).fieldOf("length").forGetter(structureCrimsonPlants -> structureCrimsonPlants.length)
    ).apply(structureCrimsonPlantsInstance, StructureCrimsonPlants::new));

    @Override
    public MapCodec<StructureCrimsonPlants> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState crimsonFungus = Blocks.CRIMSON_FUNGUS.defaultBlockState();
        BlockState crimsonRoots = Blocks.CRIMSON_ROOTS.defaultBlockState();
        BlockState weepingVines = Blocks.WEEPING_VINES.defaultBlockState();
        BlockState weepingVinesPlant = Blocks.WEEPING_VINES_PLANT.defaultBlockState();

        for (int i = 0; i < attempts; i++) {
            mutable.set(origin).move(
                    random.nextInt(7) - 3,
                    random.nextInt(4) - 1,
                    random.nextInt(7) - 3
            );

            if (level.getBlockState(mutable).isAir()) {
                if (random.nextFloat() < 0.8f && crimsonRoots.canSurvive(level, mutable)) {

                    level.setBlock(mutable, crimsonRoots, 3);
                } else if (crimsonFungus.canSurvive(level, mutable)) {

                    level.setBlock(mutable, crimsonFungus, 3);
                } else if (weepingVines.canSurvive(level, mutable)) {

                    // Biased towards max length if greater than 3
                    int maxLength = length > 3 ? length - random.nextInt(random.nextInt(length) + 1) : random.nextInt(length);
                    for (int currentLength = 0; currentLength <= maxLength; currentLength++) {
                        if (currentLength == maxLength || !level.getBlockState(mutable.below()).isAir()) {
                            level.setBlock(mutable, weepingVines, 3);
                            break;
                        }
                        level.setBlock(mutable, weepingVinesPlant, 3);
                        mutable.move(Direction.DOWN);
                    }
                }
            }
        }

        return true;
    }
}