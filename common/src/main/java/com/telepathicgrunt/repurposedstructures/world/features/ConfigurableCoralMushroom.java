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

public class ConfigurableCoralMushroom extends ConfigurableCoral implements Feature {

    public static final MapCodec<ConfigurableCoralMushroom> CODEC = RecordCodecBuilder.mapCodec((configurableCoralMushroomInstance) -> configurableCoralMushroomInstance.group(
            RegistryCodecs.holderSet(Registries.BLOCK).fieldOf("main_blocks").forGetter(configurableCoralMushroom -> configurableCoralMushroom.mainBlocks),
            RegistryCodecs.holderSet(Registries.BLOCK).fieldOf("wall_blocks").forGetter(configurableCoralMushroom -> configurableCoralMushroom.wallBlocks),
            RegistryCodecs.holderSet(Registries.BLOCK).fieldOf("floor_blocks").forGetter(configurableCoralMushroom -> configurableCoralMushroom.floorBlocks)
    ).apply(configurableCoralMushroomInstance, ConfigurableCoralMushroom::new));

    public final HolderSet<Block> mainBlocks;
    public final HolderSet<Block> wallBlocks;
    public final HolderSet<Block> floorBlocks;

    public ConfigurableCoralMushroom(HolderSet<Block> mainBlocks, HolderSet<Block> wallBlocks, HolderSet<Block> floorBlocks) {
        this.mainBlocks = mainBlocks;
        this.wallBlocks = wallBlocks;
        this.floorBlocks = floorBlocks;
    }

    @Override
    public MapCodec<ConfigurableCoralMushroom> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel worldGenLevel, ChunkGenerator chunkGenerator, RandomSource randomSource, BlockPos blockPos) {
        return this.placeStart(worldGenLevel, randomSource, blockPos, mainBlocks, wallBlocks, floorBlocks);
    }

    @Override
    protected boolean placeFeature(LevelAccessor levelAccessor, RandomSource randomSource, BlockPos blockPos, BlockState blockState, HolderSet<Block> mainBlocks, HolderSet<Block> wallBlocks, HolderSet<Block> floorBlocks) {

        int i = randomSource.nextInt(3) + 3;
        int j = randomSource.nextInt(3) + 3;
        int k = randomSource.nextInt(3) + 3;
        int l = randomSource.nextInt(3) + 1;
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
        for (int m = 0; m <= j; ++m) {
            for (int n = 0; n <= i; ++n) {
                for (int o = 0; o <= k; ++o) {
                    mutableBlockPos.set(m + blockPos.getX(), n + blockPos.getY(), o + blockPos.getZ());
                    mutableBlockPos.move(Direction.DOWN, l);
                    if ((m != 0 && m != j || n != 0 && n != i) && (o != 0 && o != k || n != 0 && n != i) && (m != 0 && m != j || o != 0 && o != k) && (m == 0 || m == j || n == 0 || n == i || o == 0 || o == k) && !(randomSource.nextFloat() < 0.1f)) {
                        this.placeCoralBlock(levelAccessor, randomSource, mutableBlockPos, blockState, mainBlocks, wallBlocks, floorBlocks);
                    }
                }
            }
        }
        return true;
    }
}

