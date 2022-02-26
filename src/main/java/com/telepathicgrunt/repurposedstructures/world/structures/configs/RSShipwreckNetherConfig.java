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

public class RSShipwreckNetherConfig implements FeatureConfiguration {

    public static final Codec<RSShipwreckNetherConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(config -> config.startPool),
            Codec.intRange(0, 30).fieldOf("size").forGetter(config -> config.size),
            Codec.INT.fieldOf("sea_level_offset").orElse(0).forGetter(config -> config.sealevelOffset),
            Codec.intRange(0, 100).fieldOf("valid_biome_radius_check").orElse(0).forGetter(config -> config.biomeRadius),
            Codec.intRange(0, 100).fieldOf("structure_set_avoid_radius_check").orElse(0).forGetter(config -> config.structureAvoidRadius),
            ResourceKey.codec(Registry.STRUCTURE_SET_REGISTRY).listOf().fieldOf("structure_set_to_avoid").orElse(new ArrayList<>()).forGetter(config -> config.structureSetToAvoid)
    ).apply(instance, RSShipwreckNetherConfig::new));

    public final Holder<StructureTemplatePool> startPool;
    public final int size;
    public final int sealevelOffset;
    public final int biomeRadius;
    public final int structureAvoidRadius;
    public final List<ResourceKey<StructureSet>> structureSetToAvoid;

    public RSShipwreckNetherConfig(Holder<StructureTemplatePool> startPool, int size,
                                   int sealevelOffset, int biomeRadius, int structureAvoidRadius,
                                   List<ResourceKey<StructureSet>> structureSetToAvoid) {

        this.startPool = startPool;
        this.size = size;
        this.sealevelOffset = sealevelOffset;
        this.biomeRadius = biomeRadius;
        this.structureAvoidRadius = structureAvoidRadius;
        this.structureSetToAvoid = structureSetToAvoid;
    }
}
