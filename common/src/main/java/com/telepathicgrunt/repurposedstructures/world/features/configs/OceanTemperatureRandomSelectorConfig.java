package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class OceanTemperatureRandomSelectorConfig implements FeatureConfiguration {

    public static final Codec<OceanTemperatureRandomSelectorConfig> CODEC = RecordCodecBuilder.create((configInstance) -> configInstance.group(
            ExtraCodecs.nonEmptyHolderSet(PlacedFeature.LIST_CODEC).fieldOf("warm_features").forGetter(config -> config.warmFeatures),
            ExtraCodecs.nonEmptyHolderSet(PlacedFeature.LIST_CODEC).fieldOf("cold_features").forGetter(config -> config.coldFeatures)
            ).apply(configInstance, OceanTemperatureRandomSelectorConfig::new));

    public final HolderSet<PlacedFeature> warmFeatures;
    public final HolderSet<PlacedFeature> coldFeatures;

    public OceanTemperatureRandomSelectorConfig(HolderSet<PlacedFeature> warmFeatures, HolderSet<PlacedFeature> coldFeatures) {
        this.warmFeatures = warmFeatures;
        this.coldFeatures = coldFeatures;
    }
}
