package com.telepathicgrunt.repurposedstructures.world.structures.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.ArrayList;
import java.util.List;

public class RSShipwreckEndConfig implements FeatureConfiguration {

    public static final Codec<RSShipwreckEndConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(config -> config.startPool),
            Codec.intRange(0, 30).fieldOf("size").forGetter(config -> config.size),
            Codec.INT.fieldOf("min_y_allowed").orElse(0).forGetter(config -> config.minYAllowed),
            Codec.intRange(0, 100).fieldOf("valid_biome_radius_check").orElse(0).forGetter(config -> config.biomeRadius),
            Codec.intRange(0, 100).fieldOf("structure_set_avoid_radius_check").orElse(0).forGetter(config -> config.structureAvoidRadius),
            ResourceKey.codec(Registry.STRUCTURE_SET_REGISTRY).listOf().fieldOf("structure_set_to_avoid").orElse(new ArrayList<>()).forGetter(config -> config.structureSetToAvoid),
            Codec.INT.fieldOf("distance_from_origin").orElse(0).forGetter(config -> config.distanceFromOrigin)
    ).apply(instance, RSShipwreckEndConfig::new));

    public final Holder<StructureTemplatePool> startPool;
    public final int size;
    public final int minYAllowed;
    public final int biomeRadius;
    public final int structureAvoidRadius;
    public final List<ResourceKey<StructureSet>> structureSetToAvoid;
    public final int distanceFromOrigin;

    public RSShipwreckEndConfig(Holder<StructureTemplatePool> startPool, int size,
                                int minYAllowed, int biomeRadius, int structureAvoidRadius,
                                List<ResourceKey<StructureSet>> structureSetToAvoid,
                                int distanceFromOrigin) {

        this.startPool = startPool;
        this.size = size;
        this.minYAllowed = minYAllowed;
        this.biomeRadius = biomeRadius;
        this.structureAvoidRadius = structureAvoidRadius;
        this.structureSetToAvoid = structureSetToAvoid;
        this.distanceFromOrigin = distanceFromOrigin;
    }
}
