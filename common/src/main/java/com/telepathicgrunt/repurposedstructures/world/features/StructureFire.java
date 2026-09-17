package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public record StructureFire(int attempts) implements Feature {

    public static final MapCodec<StructureFire> CODEC = RecordCodecBuilder.mapCodec((structureFireInstance) -> structureFireInstance.group(
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(structureFire -> structureFire.attempts)
    ).apply(structureFireInstance, StructureFire::new));


    private static final Map<ResourceKey<Level>, TagKey<Block>> INFINITE_FIRE_BLOCKS = new HashMap<>() {{
        put(Level.OVERWORLD, BlockTags.INFINIBURN_OVERWORLD);
        put(Level.NETHER, BlockTags.INFINIBURN_NETHER);
        put(Level.END, BlockTags.INFINIBURN_END);
    }};

    private static final Set<Block> REPLACEABLE_BLOCKS = Set.of(
      Blocks.NETHER_BRICKS,
      Blocks.RED_NETHER_BRICKS,
      Blocks.CRIMSON_NYLIUM,
      Blocks.WARPED_NYLIUM
    );

    @Override
    public MapCodec<StructureFire> codec() {
        return CODEC;
    }

    @Override
    public boolean place(final WorldGenLevel level, final ChunkGenerator chunkGenerator, final RandomSource random, final BlockPos origin) {

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState fire = Blocks.FIRE.defaultBlockState();
        BlockState soulFire = Blocks.SOUL_FIRE.defaultBlockState();
        TagKey<Block> infiniteBurningBlocksTagKey = INFINITE_FIRE_BLOCKS.getOrDefault(level.getLevel().dimension(), BlockTags.INFINIBURN_OVERWORLD);

        for(int i = 0; i < attempts; i++) {
            mutable.set(origin).move(
                    random.nextInt(7) - 3,
                    -1,
                    random.nextInt(7) - 3
            );

            BlockState belowBlock = level.getBlockState(mutable.below());
            boolean belowIsSoul = belowBlock.getBlock() == Blocks.SOUL_SOIL || belowBlock.getBlock() == Blocks.SOUL_SAND;
            if(level.getBlockState(mutable).isAir() && (REPLACEABLE_BLOCKS.contains(belowBlock.getBlock()) || belowBlock.is(infiniteBurningBlocksTagKey) || belowIsSoul)) {

                if(REPLACEABLE_BLOCKS.contains(belowBlock.getBlock())) {
                    level.setBlock(mutable.below(), Blocks.NETHERRACK.defaultBlockState(), 3);
                }

                if (belowIsSoul) {
                    level.setBlock(mutable, soulFire, 3);
                }
                else {
                    level.setBlock(mutable, fire, 3);
                }
            }
        }

        return true;
    }
}