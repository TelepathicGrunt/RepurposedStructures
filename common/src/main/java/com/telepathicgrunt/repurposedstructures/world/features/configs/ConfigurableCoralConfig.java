package com.telepathicgrunt.repurposedstructures.world.features.configs;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;

public class ConfigurableCoralConfig implements FeatureConfiguration {

    public static final Codec<ConfigurableCoralConfig> CODEC = RecordCodecBuilder.create((configInstance) -> configInstance.group(
            RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("main_blocks").forGetter(config -> config.mainBlocks),
            RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("wall_blocks").forGetter(config -> config.wallBlocks),
            RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("floor_blocks").forGetter(config -> config.floorBlocks)
            ).apply(configInstance, ConfigurableCoralConfig::new));

    public final HolderSet<Block> mainBlocks;
    public final HolderSet<Block> wallBlocks;
    public final HolderSet<Block> floorBlocks;

    public ConfigurableCoralConfig(HolderSet<Block> mainBlocks, HolderSet<Block> wallBlocks, HolderSet<Block> floorBlocks) {
        this.mainBlocks = mainBlocks;
        this.wallBlocks = wallBlocks;
        this.floorBlocks = floorBlocks;
    }
}
