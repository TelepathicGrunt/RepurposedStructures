package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.modinit.RSPlacements;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.levelgen.placement.RepeatingPlacement;

import java.util.Random;

public class UnlimitedCountPlacement extends RepeatingPlacement {
    public static final Codec<UnlimitedCountPlacement> CODEC = IntProvider.NON_NEGATIVE_CODEC.fieldOf("count").xmap(UnlimitedCountPlacement::new, countPlacement -> countPlacement.count).codec();
    private final IntProvider count;

    private UnlimitedCountPlacement(IntProvider intProvider) {
        this.count = intProvider;
    }

    public static UnlimitedCountPlacement of(IntProvider intProvider) {
        return new UnlimitedCountPlacement(intProvider);
    }

    public static UnlimitedCountPlacement of(int i) {
        return of(ConstantInt.of(i));
    }

    @Override
    protected int count(RandomSource random, BlockPos blockPos) {
        return this.count.sample(random);
    }

    @Override
    public PlacementModifierType<?> type() {
        return RSPlacements.UNLIMITED_COUNT;
    }
}
