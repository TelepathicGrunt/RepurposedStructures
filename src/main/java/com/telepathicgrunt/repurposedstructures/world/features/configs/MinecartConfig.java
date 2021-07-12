package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.FeatureConfig;

public class MinecartConfig implements FeatureConfig {

    public static final Codec<MinecartConfig> CODEC = RecordCodecBuilder.create((configInstance) -> configInstance.group(
            Identifier.CODEC.fieldOf("minecart_nbt_file").forGetter(minecartConfig -> minecartConfig.nbtPath),
            Codec.BOOL.fieldOf("is_water_based").orElse(false).forGetter(minecartConfig -> minecartConfig.waterBased)
            ).apply(configInstance, MinecartConfig::new));

    public final Identifier nbtPath;
    public final boolean waterBased;

    public MinecartConfig(Identifier nbtPath) {
        this(nbtPath, false);
    }

    public MinecartConfig(Identifier nbtPath, boolean waterBased) {
        this.nbtPath = nbtPath;
        this.waterBased = waterBased;
    }
}
