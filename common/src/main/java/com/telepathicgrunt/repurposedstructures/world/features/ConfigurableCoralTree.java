package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.codec.RegistryCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.List;

public class ConfigurableCoralTree extends ConfigurableCoral implements Feature  {

    public static final MapCodec<ConfigurableCoralTree> CODEC = RecordCodecBuilder.mapCodec((configurableCoralTreeInstance) -> configurableCoralTreeInstance.group(
            RegistryCodecs.holderSet(Registries.BLOCK).fieldOf("main_blocks").forGetter(configurableCoralTree -> configurableCoralTree.mainBlocks),
            RegistryCodecs.holderSet(Registries.BLOCK).fieldOf("wall_blocks").forGetter(configurableCoralTree -> configurableCoralTree.wallBlocks),
            RegistryCodecs.holderSet(Registries.BLOCK).fieldOf("floor_blocks").forGetter(configurableCoralTree -> configurableCoralTree.floorBlocks)
    ).apply(configurableCoralTreeInstance, ConfigurableCoralTree::new));

    public final HolderSet<Block> mainBlocks;
    public final HolderSet<Block> wallBlocks;
    public final HolderSet<Block> floorBlocks;

    public ConfigurableCoralTree(HolderSet<Block> mainBlocks, HolderSet<Block> wallBlocks, HolderSet<Block> floorBlocks) {
        this.mainBlocks = mainBlocks;
        this.wallBlocks = wallBlocks;
        this.floorBlocks = floorBlocks;
    }

    @Override
    public MapCodec<ConfigurableCoralTree> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel worldGenLevel, ChunkGenerator chunkGenerator, RandomSource randomSource, BlockPos blockPos) {
        return this.placeStart(worldGenLevel, randomSource, blockPos, mainBlocks, wallBlocks, floorBlocks);
    }

    @Override
    protected boolean placeFeature(LevelAccessor levelAccessor, RandomSource randomSource, BlockPos blockPos, BlockState blockState, HolderSet<Block> mainBlocks, HolderSet<Block> wallBlocks, HolderSet<Block> floorBlocks) {

        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
        int i = randomSource.nextInt(3) + 1;
        for (int j = 0; j < i; ++j) {
            if (!this.placeCoralBlock(levelAccessor, randomSource, mutableBlockPos, blockState, mainBlocks, wallBlocks, floorBlocks)) {
                return true;
            }
            mutableBlockPos.move(Direction.UP);
        }
        BlockPos blockPos2 = mutableBlockPos.immutable();
        int k = randomSource.nextInt(3) + 2;
        List<Direction> list = Direction.Plane.HORIZONTAL.shuffledCopy(randomSource);
        List<Direction> list2 = list.subList(0, k);
        for (Direction direction : list2) {
            mutableBlockPos.set(blockPos2);
            mutableBlockPos.move(direction);
            int l = randomSource.nextInt(5) + 2;
            int m = 0;
            for (int n = 0; n < l && this.placeCoralBlock(levelAccessor, randomSource, mutableBlockPos, blockState, mainBlocks, wallBlocks, floorBlocks); ++n) {
                mutableBlockPos.move(Direction.UP);
                if (n != 0 && (++m < 2 || !(randomSource.nextFloat() < 0.25f))) continue;
                mutableBlockPos.move(direction);
                m = 0;
            }
        }
        return true;
    }
}
