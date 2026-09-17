package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.codec.RegistryCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.List;
import java.util.stream.Stream;

public class ConfigurableCoralClaw extends ConfigurableCoral implements Feature {

    public static final MapCodec<ConfigurableCoralClaw> CODEC = RecordCodecBuilder.mapCodec((configurableCoralClawInstance) -> configurableCoralClawInstance.group(
            RegistryCodecs.holderSet(Registries.BLOCK).fieldOf("main_blocks").forGetter(configurableCoralClaw -> configurableCoralClaw.mainBlocks),
            RegistryCodecs.holderSet(Registries.BLOCK).fieldOf("wall_blocks").forGetter(configurableCoralClaw -> configurableCoralClaw.wallBlocks),
            RegistryCodecs.holderSet(Registries.BLOCK).fieldOf("floor_blocks").forGetter(configurableCoralClaw -> configurableCoralClaw.floorBlocks)
    ).apply(configurableCoralClawInstance, ConfigurableCoralClaw::new));

    public final HolderSet<Block> mainBlocks;
    public final HolderSet<Block> wallBlocks;
    public final HolderSet<Block> floorBlocks;

    public ConfigurableCoralClaw(HolderSet<Block> mainBlocks, HolderSet<Block> wallBlocks, HolderSet<Block> floorBlocks) {
        this.mainBlocks = mainBlocks;
        this.wallBlocks = wallBlocks;
        this.floorBlocks = floorBlocks;
    }

    @Override
    public MapCodec<ConfigurableCoralClaw> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel worldGenLevel, ChunkGenerator chunkGenerator, RandomSource randomSource, BlockPos blockPos) {
        return this.placeStart(worldGenLevel, randomSource, blockPos, mainBlocks, wallBlocks, floorBlocks);
    }

    @Override
    protected boolean placeFeature(LevelAccessor levelAccessor, RandomSource randomSource, BlockPos blockPos, BlockState blockState, HolderSet<Block> mainBlocks, HolderSet<Block> wallBlocks, HolderSet<Block> floorBlocks) {
        if (!this.placeCoralBlock(levelAccessor, randomSource, blockPos, blockState, mainBlocks, wallBlocks, floorBlocks)) {
            return false;
        }
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource);
        int i = randomSource.nextInt(2) + 2;
        List<Direction> list = Util.toShuffledList(Stream.of(direction, direction.getClockWise(), direction.getCounterClockWise()), randomSource);
        List<Direction> list2 = list.subList(0, i);
        block0: for (Direction direction2 : list2) {
            int l;
            int k;
            Direction direction3;
            BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
            int j = randomSource.nextInt(2) + 1;
            mutableBlockPos.move(direction2);
            if (direction2 == direction) {
                direction3 = direction;
                k = randomSource.nextInt(3) + 2;
            } else {
                mutableBlockPos.move(Direction.UP);
                Direction[] directions = new Direction[]{direction2, Direction.UP};
                direction3 = Util.getRandom(directions, randomSource);
                k = randomSource.nextInt(3) + 3;
            }
            for (l = 0; l < j && this.placeCoralBlock(levelAccessor, randomSource, mutableBlockPos, blockState, mainBlocks, wallBlocks, floorBlocks); ++l) {
                mutableBlockPos.move(direction3);
            }
            mutableBlockPos.move(direction3.getOpposite());
            mutableBlockPos.move(Direction.UP);
            for (l = 0; l < k; ++l) {
                mutableBlockPos.move(direction);
                if (!this.placeCoralBlock(levelAccessor, randomSource, mutableBlockPos, blockState, mainBlocks, wallBlocks, floorBlocks)) continue block0;
                if (!(randomSource.nextFloat() < 0.25f)) continue;
                mutableBlockPos.move(Direction.UP);
            }
        }
        return true;
    }
}

