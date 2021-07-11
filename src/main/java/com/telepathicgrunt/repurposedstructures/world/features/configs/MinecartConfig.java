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
            Identifier.CODEC.fieldOf("minecart_nbt_file").forGetter(mineshaftSupportConfig -> mineshaftSupportConfig.nbtPath)
            ).apply(configInstance, MinecartConfig::new));

    public final Identifier nbtPath;

    public MinecartConfig(Identifier nbtPath) {
        this.nbtPath = nbtPath;
    }
}
