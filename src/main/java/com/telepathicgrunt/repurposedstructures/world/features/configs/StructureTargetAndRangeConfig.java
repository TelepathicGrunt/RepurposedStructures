package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class StructureTargetAndRangeConfig implements IFeatureConfig {
    public static final Codec<StructureTargetAndRangeConfig> CODEC = RecordCodecBuilder.<StructureTargetAndRangeConfig>create((configInstance) -> configInstance.group(
            Registry.STRUCTURE_FEATURE.fieldOf("target_structure").forGetter(config -> config.targetStructure),
            Codec.intRange(1, 1000000).fieldOf("attempts").forGetter(config -> config.attempts),
            Codec.intRange(1, 200).fieldOf("range").forGetter(config -> config.range)
    ).apply(configInstance, StructureTargetAndRangeConfig::new));

    public final Structure<?> targetStructure;
    public final int attempts;
    public final int range;

    public StructureTargetAndRangeConfig(Structure<?> targetStructure, int attempts, int range)
    {
        this.targetStructure = targetStructure;
        this.attempts = attempts;
        this.range = range;
    }
}
