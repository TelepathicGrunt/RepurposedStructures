package com.telepathicgrunt.repurposedstructures.world.placements.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.placement.IPlacementConfig;

public class SingleIntConfig implements IPlacementConfig {
    public static final Codec<SingleIntConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.INT.fieldOf("offset").stable().forGetter((singleIntConfig) -> singleIntConfig.yOffset))
            .apply(instance, instance.stable(SingleIntConfig::new)));

    public final int yOffset;

    public SingleIntConfig(int yOffset) {
        this.yOffset = yOffset;
    }
}