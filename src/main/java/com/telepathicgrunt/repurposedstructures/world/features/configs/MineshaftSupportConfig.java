package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.FeatureConfig;

public class MineshaftSupportConfig implements FeatureConfig {

    public static final Codec<MineshaftSupportConfig> CODEC = RecordCodecBuilder.create((configInstance) -> configInstance.group(
            BlockState.CODEC.fieldOf("pillar_state").forGetter(mineshaftSupportConfig -> mineshaftSupportConfig.pillarState),
            BlockState.CODEC.fieldOf("fence_state").forGetter(mineshaftSupportConfig -> mineshaftSupportConfig.fenceState),
            Registry.BLOCK.fieldOf("target_floor_block").forGetter(mineshaftSupportConfig -> mineshaftSupportConfig.targetFloorState),
            Codec.BOOL.fieldOf("is_water_based").orElse(false).forGetter(mineshaftSupportConfig -> mineshaftSupportConfig.waterBased)
            ).apply(configInstance, MineshaftSupportConfig::new));

    public final BlockState pillarState;
    public final BlockState fenceState;
    public final Block targetFloorState;
    public final boolean waterBased;

    public MineshaftSupportConfig(BlockState pillarState, BlockState fenceState, Block targetFloorState, boolean waterBased) {
        this.pillarState = pillarState;
        this.fenceState = fenceState;
        this.targetFloorState = targetFloorState;
        this.waterBased = waterBased;
    }
}
