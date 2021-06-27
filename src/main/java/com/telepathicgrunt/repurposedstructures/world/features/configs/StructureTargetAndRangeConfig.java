package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;

public class StructureTargetAndRangeConfig implements FeatureConfig {
    public static final Codec<StructureTargetAndRangeConfig> CODEC = RecordCodecBuilder.<StructureTargetAndRangeConfig>create((configInstance) -> configInstance.group(
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(config -> config.attempts),
            Codec.intRange(1, 200).fieldOf("range").forGetter(config -> config.range)
    ).apply(configInstance, StructureTargetAndRangeConfig::new));

    public final int attempts;
    public final int range;

    public StructureTargetAndRangeConfig(int attempts, int range)
    {
        this.attempts = attempts;
        this.range = range;
    }
}
