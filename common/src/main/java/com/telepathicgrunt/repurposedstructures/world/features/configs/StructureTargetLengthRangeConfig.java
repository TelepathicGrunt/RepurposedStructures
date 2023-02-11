package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.function.Function;

public class StructureTargetLengthRangeConfig implements FeatureConfiguration {
    public static final Codec<StructureTargetLengthRangeConfig> CODEC = RecordCodecBuilder.<StructureTargetLengthRangeConfig>create((configInstance) -> configInstance.group(
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(config -> config.attempts),
            Codec.intRange(1, 200).fieldOf("length").forGetter(config -> config.length),
            Codec.intRange(1, 200).fieldOf("xz_range").forGetter(config -> config.xzRange),
            Codec.intRange(1, 200).fieldOf("height_range").orElse(5).forGetter(config -> config.heightRange)
    ).apply(configInstance, StructureTargetLengthRangeConfig::new))
            .comapFlatMap((config) -> config.heightRange <= 0 ?
                    DataResult.error("height must be greater than 0") : DataResult.success(config), Function.identity());

    public final int attempts;
    public final int length;
    public final int xzRange;
    public final int heightRange;

    public StructureTargetLengthRangeConfig(int attempts, int length, int xzRange) {
        this.attempts = attempts;
        this.length = length;
        this.xzRange = xzRange;
        this.heightRange = 5;
    }

    public StructureTargetLengthRangeConfig(int attempts, int length, int xzRange, int heightRange) {
        this.attempts = attempts;
        this.length = length;
        this.xzRange = xzRange;
        this.heightRange = heightRange;
    }
}
