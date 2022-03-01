package com.telepathicgrunt.repurposedstructures.world.structures.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

public class RSGenericConfig implements FeatureConfiguration {

    public static final Codec<RSGenericConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(config -> config.startPool),
            Codec.intRange(0, 30).fieldOf("size").forGetter(config -> config.size),
            Codec.INT.fieldOf("min_y_allowed").orElse(Integer.MIN_VALUE).forGetter(config -> config.minYAllowed),
            Codec.INT.fieldOf("set_fixed_y_spawn").orElse(0).forGetter(config -> config.setFixedYSpawn),
            Codec.INT.fieldOf("center_y_offset").orElse(0).forGetter(config -> config.centerYOffset),
            Codec.BOOL.fieldOf("do_not_use_heightmap").orElse(false).forGetter(config -> config.doNotUseHeightmap),
            Codec.BOOL.fieldOf("cannot_spawn_in_liquid").orElse(false).forGetter(config -> config.cannotSpawnInLiquid),
            Codec.intRange(0, 100).fieldOf("terrain_height_radius_check").orElse(0).forGetter(config -> config.terrainHeightCheckRadius),
            Codec.intRange(-1, 1000).fieldOf("allowed_terrain_height_range").orElse(-1).forGetter(config -> config.allowedTerrainHeightRange),
            Codec.intRange(0, 100).fieldOf("valid_biome_radius_check").orElse(0).forGetter(config -> config.biomeRadius),
            Codec.intRange(0, 100).fieldOf("structure_set_avoid_radius_check").orElse(0).forGetter(config -> config.structureAvoidRadius),
            ResourceKey.codec(Registry.STRUCTURE_SET_REGISTRY).listOf().fieldOf("structure_set_to_avoid").orElse(new ArrayList<>()).forGetter(config -> config.structureSetToAvoid),
            ResourceLocation.CODEC.listOf().fieldOf("pools_that_ignore_boundaries").orElse(new ArrayList<>()).xmap(HashSet::new, ArrayList::new).forGetter(config -> config.poolsThatIgnoreBoundaries)
    ).apply(instance, RSGenericConfig::new));

    public final Holder<StructureTemplatePool> startPool;
    public final int size;
    public final int minYAllowed;
    public final int setFixedYSpawn;
    public final int centerYOffset;
    public final boolean doNotUseHeightmap;
    public final boolean cannotSpawnInLiquid;
    public final int terrainHeightCheckRadius;
    public final int allowedTerrainHeightRange;
    public final int biomeRadius;
    public final int structureAvoidRadius;
    public final List<ResourceKey<StructureSet>> structureSetToAvoid;
    public final HashSet<ResourceLocation> poolsThatIgnoreBoundaries;

    public RSGenericConfig(Holder<StructureTemplatePool> startPool, int size, int minYAllowed,
                           int setFixedYSpawn, int centerYOffset, boolean doNotUseHeightmap,
                           boolean cannotSpawnInLiquid, int terrainHeightCheckRadius, int allowedTerrainHeightRange,
                           int biomeRadius, int structureAvoidRadius,
                           List<ResourceKey<StructureSet>> structureSetToAvoid,
                           HashSet<ResourceLocation> poolsThatIgnoreBoundaries) {

        this.startPool = startPool;
        this.size = size;
        this.minYAllowed = minYAllowed;
        this.setFixedYSpawn = setFixedYSpawn;
        this.doNotUseHeightmap = doNotUseHeightmap;
        this.cannotSpawnInLiquid = cannotSpawnInLiquid;
        this.centerYOffset = centerYOffset;
        this.terrainHeightCheckRadius = terrainHeightCheckRadius;
        this.allowedTerrainHeightRange = allowedTerrainHeightRange;
        this.biomeRadius = biomeRadius;
        this.structureAvoidRadius = structureAvoidRadius;
        this.structureSetToAvoid = structureSetToAvoid;
        this.poolsThatIgnoreBoundaries = poolsThatIgnoreBoundaries;
    }
}
