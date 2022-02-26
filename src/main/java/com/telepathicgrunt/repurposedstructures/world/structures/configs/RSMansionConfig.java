package com.telepathicgrunt.repurposedstructures.world.structures.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.ArrayList;
import java.util.List;

public class RSMansionConfig implements FeatureConfiguration {

    public static final Codec<RSMansionConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("type").forGetter(config -> config.mansionType),
            BlockState.CODEC.fieldOf("foundation_block").forGetter(config -> config.foundationBlock),
            Codec.BOOL.fieldOf("pillar_only_to_land").orElse(true).forGetter(config -> config.pillarOnlyToLand),
            Codec.intRange(0, 100).fieldOf("valid_biome_radius_check").orElse(0).forGetter(config -> config.biomeRadius),
            Codec.intRange(0, 100).fieldOf("structure_set_avoid_radius_check").orElse(0).forGetter(config -> config.structureAvoidRadius),
            ResourceKey.codec(Registry.STRUCTURE_SET_REGISTRY).listOf().fieldOf("structure_set_to_avoid").orElse(new ArrayList<>()).forGetter(config -> config.structureSetToAvoid)
    ).apply(instance, RSMansionConfig::new));

    public final String mansionType;
    public final BlockState foundationBlock;
    public final boolean pillarOnlyToLand;
    public final int biomeRadius;
    public final int structureAvoidRadius;
    public final List<ResourceKey<StructureSet>> structureSetToAvoid;

    public RSMansionConfig(String mansionType, BlockState foundationBlock, boolean pillarOnlyToLand, int biomeRadius, int structureAvoidRadius, List<ResourceKey<StructureSet>> structureSetToAvoid) {

        this.mansionType = mansionType;
        this.foundationBlock = foundationBlock;
        this.pillarOnlyToLand = pillarOnlyToLand;
        this.biomeRadius = biomeRadius;
        this.structureAvoidRadius = structureAvoidRadius;
        this.structureSetToAvoid = structureSetToAvoid;
    }
}
