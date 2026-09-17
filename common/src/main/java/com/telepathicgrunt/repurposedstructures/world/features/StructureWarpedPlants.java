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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;


public record StructureWarpedPlants(
        int attempts,
        int length,
        int xzRange,
        int heightRange
) implements Feature {

    public static final MapCodec<StructureWarpedPlants> CODEC = RecordCodecBuilder.<StructureWarpedPlants>mapCodec((structureWarpedPlantsInstance) -> structureWarpedPlantsInstance.group(
                    Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(structureWarpedPlants -> structureWarpedPlants.attempts),
                    Codec.intRange(1, 200).fieldOf("length").forGetter(structureWarpedPlants -> structureWarpedPlants.length),
                    Codec.intRange(1, 200).fieldOf("xz_range").forGetter(structureWarpedPlants -> structureWarpedPlants.xzRange),
                    Codec.intRange(1, 200).fieldOf("height_range").orElse(5).forGetter(structureWarpedPlants -> structureWarpedPlants.heightRange)
            ).apply(structureWarpedPlantsInstance, StructureWarpedPlants::new))
            .validate((structureWarpedPlants) -> structureWarpedPlants.heightRange <= 0 ?
                    DataResult.error(() -> "height must be greater than 0") : DataResult.success(structureWarpedPlants));

    @Override
    public MapCodec<StructureWarpedPlants> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState netherSprouts = Blocks.NETHER_SPROUTS.defaultBlockState();
        BlockState twistingFungus = Blocks.WARPED_FUNGUS.defaultBlockState();
        BlockState twistingRoots = Blocks.WARPED_ROOTS.defaultBlockState();
        BlockState twistingVines = Blocks.TWISTING_VINES.defaultBlockState();
        BlockState twistingVinesPlant = Blocks.TWISTING_VINES_PLANT.defaultBlockState();

        for(int i = 0; i < attempts; i++) {
            mutable.set(origin).move(
                    random.nextInt(7) - 3,
                    -1,
                    random.nextInt(7) - 3
            );

            if(level.getBlockState(mutable).isAir()) {
                if(random.nextFloat() < 0.5f && netherSprouts.canSurvive(level, mutable)) {

                    level.setBlock(mutable, netherSprouts, 3);
                }
                else if(random.nextFloat() < 0.4f && twistingRoots.canSurvive(level, mutable)) {

                    level.setBlock(mutable, twistingRoots, 3);
                }
                else if(random.nextFloat() < 0.3f && twistingFungus.canSurvive(level, mutable)) {

                    level.setBlock(mutable, twistingFungus, 3);
                }
                else if(twistingVines.canSurvive(level, mutable)) {

                    // Biased towards max length if greater than 3
                    int maxLength = length > 3 ? length - random.nextInt(random.nextInt(length) + 1) : random.nextInt(length);
                    for(int currentLength = 0; currentLength <= maxLength; currentLength++) {
                        if(currentLength == maxLength || !level.getBlockState(mutable.above()).isAir()) {
                            level.setBlock(mutable, twistingVines, 3);
                            break;
                        }
                        level.setBlock(mutable, twistingVinesPlant, 3);
                        mutable.move(Direction.UP);
                    }
                }
            }
        }

        return true;
    }
}