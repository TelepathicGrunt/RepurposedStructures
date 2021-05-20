package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class StructureTargetAndLengthConfig implements FeatureConfig {
    public static final Codec<StructureTargetAndLengthConfig> CODEC = RecordCodecBuilder.<StructureTargetAndLengthConfig>create((configInstance) -> configInstance.group(
            Registry.STRUCTURE_FEATURE.fieldOf("target_structure").forGetter(config -> config.targetStructure),
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(config -> config.attempts),
            Codec.intRange(1, 200).fieldOf("length").forGetter(config -> config.length)
    ).apply(configInstance, StructureTargetAndLengthConfig::new));

    public final StructureFeature<?> targetStructure;
    public final int attempts;
    public final int length;

    public StructureTargetAndLengthConfig(StructureFeature<?> targetStructure, int attempts, int length)
    {
        this.targetStructure = targetStructure;
        this.attempts = attempts;
        this.length = length;
    }
}
