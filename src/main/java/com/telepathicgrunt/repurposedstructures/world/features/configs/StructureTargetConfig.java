package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class StructureTargetConfig implements IFeatureConfig {
    public static final Codec<StructureTargetConfig> CODEC = RecordCodecBuilder.<StructureTargetConfig>create((configInstance) -> configInstance.group(
            Registry.STRUCTURE_FEATURE.fieldOf("target_structure").forGetter(config -> config.targetStructure),
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(config -> config.attempts)
    ).apply(configInstance, StructureTargetConfig::new));

    public final Structure<?> targetStructure;
    public final int attempts;

    public StructureTargetConfig(Structure<?> targetStructure, int attempts)
    {
        this.targetStructure = targetStructure;
        this.attempts = attempts;
    }
}
