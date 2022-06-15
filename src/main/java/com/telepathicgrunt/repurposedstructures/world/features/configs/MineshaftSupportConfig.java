package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.ArrayList;
import java.util.HashSet;

public class MineshaftSupportConfig implements FeatureConfiguration {

    public static final Codec<MineshaftSupportConfig> CODEC = RecordCodecBuilder.create((configInstance) -> configInstance.group(
            Registry.BLOCK.byNameCodec().listOf().fieldOf("arch_blocks").xmap(HashSet::new, ArrayList::new).forGetter(mineshaftSupportConfig -> mineshaftSupportConfig.archBlocks),
            BlockState.CODEC.fieldOf("pillar_state").forGetter(mineshaftSupportConfig -> mineshaftSupportConfig.pillarState),
            BlockState.CODEC.fieldOf("fence_state").forGetter(mineshaftSupportConfig -> mineshaftSupportConfig.fenceState),
            Registry.BLOCK.byNameCodec().fieldOf("target_floor_block").forGetter(mineshaftSupportConfig -> mineshaftSupportConfig.targetFloorState),
            Codec.BOOL.fieldOf("is_water_based").orElse(false).forGetter(mineshaftSupportConfig -> mineshaftSupportConfig.waterBased),
            Codec.BOOL.fieldOf("arch_only").orElse(false).forGetter(mineshaftSupportConfig -> mineshaftSupportConfig.archOnly)
            ).apply(configInstance, MineshaftSupportConfig::new));

    public final HashSet<Block> archBlocks;
    public final BlockState pillarState;
    public final BlockState fenceState;
    public final Block targetFloorState;
    public final boolean waterBased;
    public final boolean archOnly;

    public MineshaftSupportConfig(HashSet<Block> archBlocks, BlockState pillarState, BlockState fenceState, Block targetFloorState, boolean waterBased, boolean archOnly) {
        this.archBlocks = archBlocks;
        this.pillarState = pillarState;
        this.fenceState = fenceState;
        this.targetFloorState = targetFloorState;
        this.waterBased = waterBased;
        this.archOnly = archOnly;
    }
}
