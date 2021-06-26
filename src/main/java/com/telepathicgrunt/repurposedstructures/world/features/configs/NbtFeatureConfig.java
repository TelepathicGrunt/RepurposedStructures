package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class NbtFeatureConfig implements FeatureConfig {
    public static final Codec<NbtFeatureConfig> CODEC = RecordCodecBuilder.create((configInstance) -> configInstance.group(
            Identifier.CODEC.fieldOf("configured_feature_name").forGetter(nbtFeatureConfig -> nbtFeatureConfig.cfID),
            Codec.BOOL.fieldOf("allow_liquid").orElse(false).forGetter(nbtFeatureConfig -> nbtFeatureConfig.allowInWater),
            Codec.INT.fieldOf("height_offset").orElse(0).forGetter(nbtFeatureConfig -> nbtFeatureConfig.heightOffset),
            Codec.mapPair(Identifier.CODEC.fieldOf("resourcelocation"), Codec.intRange(1, Integer.MAX_VALUE).fieldOf("weight")).codec().listOf().fieldOf("nbt_entries").forGetter(nbtFeatureConfig -> nbtFeatureConfig.nbtResourcelocationsAndWeights),
            Identifier.CODEC.fieldOf("processors").orElse(null).forGetter(nbtFeatureConfig -> nbtFeatureConfig.processor)
            ).apply(configInstance, NbtFeatureConfig::new));

    public final Identifier cfID;
    public final boolean allowInWater;
    public final int heightOffset;
    public final List<Pair<Identifier, Integer>> nbtResourcelocationsAndWeights;
    public final Identifier processor;

    public NbtFeatureConfig(Identifier cfID, boolean allowInWater, int heightOffset, List<Pair<Identifier, Integer>> nbtResourcelocationsAndWeights, Identifier processor) {
        this.cfID = cfID;
        this.allowInWater = allowInWater;
        this.heightOffset = heightOffset;
        this.nbtResourcelocationsAndWeights = nbtResourcelocationsAndWeights;
        this.processor = processor;
    }
}
