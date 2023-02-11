package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class StructureRangeConfig implements FeatureConfiguration {
    public static final Codec<StructureRangeConfig> CODEC = RecordCodecBuilder.create((configInstance) -> configInstance.group(
            Codec.intRange(1, 200).fieldOf("range").forGetter(config -> config.range)
    ).apply(configInstance, StructureRangeConfig::new));

    public final int range;

    public StructureRangeConfig(int range) {
        this.range = range;
    }
}
