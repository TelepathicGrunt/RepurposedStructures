package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class StructureTargetChanceConfig implements IFeatureConfig {
    public static final Codec<StructureTargetChanceConfig> CODEC = RecordCodecBuilder.<StructureTargetChanceConfig>create((configInstance) -> configInstance.group(
            Registry.STRUCTURE_FEATURE.fieldOf("target_structure").forGetter(config -> config.targetStructure),
            Codec.floatRange(0, 1).fieldOf("chance").forGetter(config -> config.chance)
    ).apply(configInstance, StructureTargetChanceConfig::new));

    public final Structure<?> targetStructure;
    public final float chance;

    public StructureTargetChanceConfig(Structure<?> targetStructure, float chance)
    {
        this.targetStructure = targetStructure;
        this.chance = chance;
    }
}
