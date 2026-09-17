package com.telepathicgrunt.repurposedstructures.world.placements;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.levelgen.placement.RepeatingPlacement;

public record UnlimitedCountPlacement(IntProvider count) implements RepeatingPlacement {
    public static final MapCodec<UnlimitedCountPlacement> CODEC = IntProviders.NON_NEGATIVE_CODEC.fieldOf("count").xmap(UnlimitedCountPlacement::new, countPlacement -> countPlacement.count);

    public static UnlimitedCountPlacement of(IntProvider intProvider) {
        return new UnlimitedCountPlacement(intProvider);
    }

    public static UnlimitedCountPlacement of(int i) {
        return of(ConstantInt.of(i));
    }

    @Override
    public int count(RandomSource random, BlockPos blockPos) {
        return this.count.sample(random);
    }

    @Override
    public MapCodec<UnlimitedCountPlacement> codec() {
        return CODEC;
    }
}
