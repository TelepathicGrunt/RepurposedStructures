package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

import java.util.Random;
import java.util.stream.Stream;

public class RSVinePlacement extends Placement<TopSolidRangeConfig> {
    public RSVinePlacement(Codec<TopSolidRangeConfig> config) {
        super(config);
    }

    public Stream<BlockPos> getPositions(WorldDecoratingHelper world, Random random, TopSolidRangeConfig config, BlockPos pos) {
        int x = random.nextInt(16) + pos.getX();
        int z = random.nextInt(16) + pos.getZ();
        int y = world.getTopY(Heightmap.Type.MOTION_BLOCKING, x, z);
        return y <= 0 ? Stream.empty() : Stream.of(new BlockPos(x, y + config.topOffset - random.nextInt(config.maximum), z));
    }
}