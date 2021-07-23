package com.telepathicgrunt.repurposedstructures.world.structures.configs;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class NetherShipwreckConfig implements FeatureConfiguration {

    public static final Codec<NetherShipwreckConfig> CODEC = Codec.BOOL.fieldOf("is_flying").orElse(false)
            .xmap(NetherShipwreckConfig::new, (p_236635_0_) -> p_236635_0_.isFlying).codec();

    public final boolean isFlying;

    public NetherShipwreckConfig(boolean isFlying) {
        this.isFlying = isFlying;
    }
}
