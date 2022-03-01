package com.telepathicgrunt.repurposedstructures.world.structures.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class RSAdvancedConfig implements FeatureConfiguration {

    public static final Codec<RSAdvancedConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(config -> config.startPool),
            Codec.intRange(0, 30).fieldOf("size").forGetter(config -> config.size),
            Codec.INT.fieldOf("max_y").orElse(Integer.MAX_VALUE).forGetter(config -> config.maxY),
            Codec.INT.fieldOf("min_y").orElse(Integer.MIN_VALUE).forGetter(config -> config.minY),
            Codec.BOOL.fieldOf("do_not_remove_out_of_bounds_pieces").orElse(false).forGetter(config -> config.clipOutOfBoundsPieces),
            Codec.INT.optionalFieldOf("vertical_distance_from_start_piece").forGetter(config -> config.verticalRange),
            Codec.intRange(0, 100).fieldOf("valid_biome_radius_check").orElse(0).forGetter(config -> config.biomeRadius),
            Codec.intRange(0, 100).fieldOf("structure_set_avoid_radius_check").orElse(0).forGetter(config -> config.structureAvoidRadius),
            ResourceKey.codec(Registry.STRUCTURE_SET_REGISTRY).listOf().fieldOf("structure_set_to_avoid").orElse(new ArrayList<>()).forGetter(config -> config.structureSetToAvoid),
            ResourceLocation.CODEC.listOf().fieldOf("pools_that_ignore_boundaries").orElse(new ArrayList<>()).xmap(HashSet::new, ArrayList::new).forGetter(config -> config.poolsThatIgnoreBoundaries)
    ).apply(instance, RSAdvancedConfig::new));

    public final Holder<StructureTemplatePool> startPool;
    public final int size;
    public final int maxY;
    public final int minY;
    public final boolean clipOutOfBoundsPieces;
    public final Optional<Integer> verticalRange;
    public final int biomeRadius;
    public final int structureAvoidRadius;
    public final List<ResourceKey<StructureSet>> structureSetToAvoid;
    public final HashSet<ResourceLocation> poolsThatIgnoreBoundaries;


    public RSAdvancedConfig(Holder<StructureTemplatePool> startPool, int size,
                            int maxY, int minY, boolean clipOutOfBoundsPieces,
                            Optional<Integer> verticalRange, int biomeRadius, int structureAvoidRadius,
                            List<ResourceKey<StructureSet>> structureSetToAvoid,
                            HashSet<ResourceLocation> poolsThatIgnoreBoundaries) {

        this.startPool = startPool;
        this.size = size;
        this.maxY = maxY;
        this.minY = minY;
        this.clipOutOfBoundsPieces = clipOutOfBoundsPieces;
        this.verticalRange = verticalRange;
        this.biomeRadius = biomeRadius;
        this.structureAvoidRadius = structureAvoidRadius;
        this.structureSetToAvoid = structureSetToAvoid;
        this.poolsThatIgnoreBoundaries = poolsThatIgnoreBoundaries;

        if(maxY < minY) {
            throw new RuntimeException(RepurposedStructures.MODID + ": max y should never be below min y for RS Structures : " + startPool.toString());
        }
    }
}
