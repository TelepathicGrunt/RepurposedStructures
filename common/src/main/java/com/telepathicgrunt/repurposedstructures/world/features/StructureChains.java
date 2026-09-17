package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;


public record StructureChains(int attempts) implements Feature {

    public static final MapCodec<StructureChains> CODEC = RecordCodecBuilder.mapCodec((structureChainsInstance) -> structureChainsInstance.group(
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(structureChains -> structureChains.attempts)
    ).apply(structureChainsInstance, StructureChains::new));

    @Override
    public MapCodec<StructureChains> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        WorldGenLevel world = level;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for (int i = 0; i < attempts; i++) {
            mutable.set(origin).move(
                    random.nextInt(11) - 5,
                    random.nextInt(3) - 1,
                    random.nextInt(11) - 5
            );

            if (!world.getBlockState(mutable).isAir()) {
                continue;
            }

            // generates chains from given position down 1-8 blocks if path is clear and the given position is valid
            int length = 0;
            BlockState aboveBlockstate;
            boolean exitEarly = false;

            for (; mutable.getY() > world.getMinY() + 3 && length < random.nextInt(random.nextInt(random.nextInt(8) + 1) + 1) + 1; mutable.move(Direction.DOWN)) {
                if (world.isEmptyBlock(mutable)) {
                    aboveBlockstate = world.getBlockState(mutable.above());

                    if (aboveBlockstate.is(Blocks.IRON_CHAIN) || aboveBlockstate.isFaceSturdy(world, mutable.above(), Direction.DOWN)) {
                        world.setBlock(mutable, Blocks.IRON_CHAIN.defaultBlockState(), 2);
                        length++;
                    }
                } else {
                    exitEarly = true;
                }
            }

            if (exitEarly) continue;

            //attaches lantern at end at a rare chance
            if (mutable.getY() != world.getMinY() + 3 && random.nextFloat() < 0.075f && world.isEmptyBlock(mutable)) {
                if (world.getBiome(mutable).is(BiomeTags.IS_NETHER)) {
                    world.setBlock(mutable, Blocks.SOUL_LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, true), 2);
                } else {
                    world.setBlock(mutable, Blocks.LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, true), 2);
                }
            }
        }

        return true;
    }
}