package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class StructureTargetChanceConfig implements FeatureConfiguration {
    public static final Codec<StructureTargetChanceConfig> CODEC = RecordCodecBuilder.create((configInstance) -> configInstance.group(
            Codec.floatRange(0, 1).fieldOf("chance").forGetter(config -> config.chance)
    ).apply(configInstance, StructureTargetChanceConfig::new));

    public final float chance;

    public StructureTargetChanceConfig(float chance)
    {
        this.chance = chance;
    }
}
