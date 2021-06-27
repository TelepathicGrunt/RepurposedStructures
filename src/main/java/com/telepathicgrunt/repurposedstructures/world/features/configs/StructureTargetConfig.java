package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;

public class StructureTargetConfig implements FeatureConfig {
    public static final Codec<StructureTargetConfig> CODEC = RecordCodecBuilder.<StructureTargetConfig>create((configInstance) -> configInstance.group(
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(config -> config.attempts)
    ).apply(configInstance, StructureTargetConfig::new));

    public final int attempts;

    public StructureTargetConfig(int attempts)
    {
        this.attempts = attempts;
    }
}
