package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class StructureTargetAndLengthConfig implements FeatureConfiguration {
    public static final Codec<StructureTargetAndLengthConfig> CODEC = RecordCodecBuilder.create((configInstance) -> configInstance.group(
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
