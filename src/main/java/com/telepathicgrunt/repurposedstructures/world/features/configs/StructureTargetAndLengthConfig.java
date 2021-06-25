package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class StructureTargetAndLengthConfig implements FeatureConfig {
    public static final Codec<StructureTargetAndLengthConfig> CODEC = RecordCodecBuilder.<StructureTargetAndLengthConfig>create((configInstance) -> configInstance.group(
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(config -> config.attempts),
            Codec.intRange(1, 200).fieldOf("length").forGetter(config -> config.length)
    ).apply(configInstance, StructureTargetAndLengthConfig::new));

    public final int attempts;
    public final int length;

    public StructureTargetAndLengthConfig(int attempts, int length)
    {
        this.attempts = attempts;
        this.length = length;
    }
}
