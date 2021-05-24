package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class StructureTargetConfig implements FeatureConfig {
    public static final Codec<StructureTargetConfig> CODEC = RecordCodecBuilder.<StructureTargetConfig>create((configInstance) -> configInstance.group(
            Registry.STRUCTURE_FEATURE.fieldOf("target_structure").forGetter(config -> config.targetStructure),
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(config -> config.attempts)
    ).apply(configInstance, StructureTargetConfig::new));

    public final StructureFeature<?> targetStructure;
    public final int attempts;

    public StructureTargetConfig(StructureFeature<?> targetStructure, int attempts)
    {
        this.targetStructure = targetStructure;
        this.attempts = attempts;
    }
}
