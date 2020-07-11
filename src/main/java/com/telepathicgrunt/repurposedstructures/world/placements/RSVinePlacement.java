package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;

import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RSVinePlacement extends Decorator<RangeDecoratorConfig> {
    public RSVinePlacement(Codec<RangeDecoratorConfig> config) {
        super(config);
    }


    public Stream<BlockPos> getPositions(WorldAccess world, ChunkGenerator generator, Random random, RangeDecoratorConfig config, BlockPos pos) {
        return IntStream.range(0, config.count).mapToObj((i) -> {
            int x = random.nextInt(16) + pos.getX();
            int z = random.nextInt(16) + pos.getZ();
            int y = world.getTopY(Heightmap.Type.MOTION_BLOCKING, x, z);
            return y <= 0 ? null : new BlockPos(x, y + config.topOffset - random.nextInt(config.maximum), z);
        }).filter(Objects::nonNull);
    }
}