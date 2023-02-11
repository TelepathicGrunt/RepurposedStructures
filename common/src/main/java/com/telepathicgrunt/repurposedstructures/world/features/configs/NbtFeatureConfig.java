package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;

public class NbtFeatureConfig implements FeatureConfiguration {
    public static final Codec<NbtFeatureConfig> CODEC = RecordCodecBuilder.create((configInstance) -> configInstance.group(
            Codec.BOOL.fieldOf("allow_liquid").orElse(false).forGetter(nbtFeatureConfig -> nbtFeatureConfig.allowInWater),
            Codec.INT.fieldOf("height_offset").orElse(0).forGetter(nbtFeatureConfig -> nbtFeatureConfig.heightOffset),
            Codec.mapPair(ResourceLocation.CODEC.fieldOf("resourcelocation"), Codec.intRange(1, Integer.MAX_VALUE).fieldOf("weight")).codec().listOf().fieldOf("nbt_entries").forGetter(nbtFeatureConfig -> nbtFeatureConfig.nbtResourcelocationsAndWeights),
            ResourceLocation.CODEC.fieldOf("processors").orElse(null).forGetter(nbtFeatureConfig -> nbtFeatureConfig.processor)
            ).apply(configInstance, NbtFeatureConfig::new));

    public final boolean allowInWater;
    public final int heightOffset;
    public final List<Pair<ResourceLocation, Integer>> nbtResourcelocationsAndWeights;
    public final ResourceLocation processor;

    public NbtFeatureConfig(boolean allowInWater, int heightOffset, List<Pair<ResourceLocation, Integer>> nbtResourcelocationsAndWeights, ResourceLocation processor) {
        this.allowInWater = allowInWater;
        this.heightOffset = heightOffset;
        this.nbtResourcelocationsAndWeights = nbtResourcelocationsAndWeights;
        this.processor = processor;
    }
}
