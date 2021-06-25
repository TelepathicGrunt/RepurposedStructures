package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class StructureTargetChanceConfig implements FeatureConfig {
    public static final Codec<StructureTargetChanceConfig> CODEC = RecordCodecBuilder.<StructureTargetChanceConfig>create((configInstance) -> configInstance.group(
            Codec.floatRange(0, 1).fieldOf("chance").forGetter(config -> config.chance)
    ).apply(configInstance, StructureTargetChanceConfig::new));

    public final float chance;

    public StructureTargetChanceConfig(float chance)
    {
        this.chance = chance;
    }
}
