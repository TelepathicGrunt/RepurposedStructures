package com.telepathicgrunt.repurposedstructures.world.structures.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RSGenericNetherConfig extends RSGenericConfig {

    public static final Codec<RSGenericNetherConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
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
            ResourceLocation.CODEC.listOf().fieldOf("pools_that_ignore_boundaries").orElse(new ArrayList<>()).xmap(HashSet::new, ArrayList::new).forGetter(config -> config.poolsThatIgnoreBoundaries),
            Codec.BOOL.fieldOf("search_for_highest_land").orElse(false).forGetter(config -> config.highestLandSearch),
            Codec.INT.fieldOf("ledge_spot_offset").orElse(0).forGetter(config -> config.ledgeSpotOffset),
            Codec.INT.fieldOf("liquid_spot_offset").orElse(0).forGetter(config -> config.liquidSpotOffset)
    ).apply(instance, RSGenericNetherConfig::new));

    public final boolean highestLandSearch;
    public final int ledgeSpotOffset;
    public final int liquidSpotOffset;

    public RSGenericNetherConfig(Holder<StructureTemplatePool> startPool, int size, int minYAllowed,
                                 int setFixedYSpawn, int centerYOffset, boolean doNotUseHeightmap,
                                 boolean cannotSpawnInWater, int terrainHeightCheckRadius, int allowedTerrainHeightRange,
                                 int biomeRadius, int structureAvoidRadius,
                                 List<ResourceKey<StructureSet>> structureSetToAvoid,
                                 HashSet<ResourceLocation> poolsThatIgnoreBoundaries, boolean highestLandSearch, int ledgeSpotOffset, int liquidSpotOffset) {

        super(startPool, size, minYAllowed, setFixedYSpawn, centerYOffset, doNotUseHeightmap, cannotSpawnInWater, terrainHeightCheckRadius, allowedTerrainHeightRange, biomeRadius, structureAvoidRadius, structureSetToAvoid, poolsThatIgnoreBoundaries);
        this.highestLandSearch = highestLandSearch;
        this.ledgeSpotOffset = ledgeSpotOffset;
        this.liquidSpotOffset = liquidSpotOffset;
    }
}
