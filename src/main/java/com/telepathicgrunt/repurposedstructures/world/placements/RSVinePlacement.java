package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RSVinePlacement extends Placement<CountRangeConfig> {
    public RSVinePlacement(Codec<CountRangeConfig> config) {
        super(config);
    }


    public Stream<BlockPos> getPositions(IWorld world, ChunkGenerator generator, Random random, CountRangeConfig config, BlockPos pos) {
        return IntStream.range(0, config.count).mapToObj((i) -> {
            int x = random.nextInt(16) + pos.getX();
            int z = random.nextInt(16) + pos.getZ();
            int y = world.getHeight(Heightmap.Type.MOTION_BLOCKING, x, z);
            return y <= 0 ? null : new BlockPos(x, y + config.topOffset - random.nextInt(config.maximum), z);
        }).filter(Objects::nonNull);
    }
}