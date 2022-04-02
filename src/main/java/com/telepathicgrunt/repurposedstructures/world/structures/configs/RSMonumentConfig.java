package com.telepathicgrunt.repurposedstructures.world.structures.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class RSMonumentConfig implements FeatureConfiguration {

    public static final Codec<RSMonumentConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("type").forGetter(config -> config.monumentType),
            Codec.intRange(0, 100).fieldOf("valid_biome_radius_check").orElse(0).forGetter(config -> config.biomeRadius),
            Codec.intRange(0, 100).fieldOf("structure_set_avoid_radius_check").orElse(0).forGetter(config -> config.structureAvoidRadius),
            ResourceKey.codec(Registry.STRUCTURE_SET_REGISTRY).listOf().fieldOf("structure_set_to_avoid").orElse(new ArrayList<>()).forGetter(config -> config.structureSetToAvoid),
            Codec.INT.optionalFieldOf("fixed_y_spawn").forGetter(config -> config.fixedYSpawn),
            Codec.FLOAT.optionalFieldOf("center_terrain_height_weight").forGetter(config -> config.centerTerrainHeightWeight)
    ).apply(instance, RSMonumentConfig::new));

    public final String monumentType;
    public final int biomeRadius;
    public final int structureAvoidRadius;
    public final Optional<Integer> fixedYSpawn;
    public final Optional<Float> centerTerrainHeightWeight;
    public final List<ResourceKey<StructureSet>> structureSetToAvoid;

    public RSMonumentConfig(String monumentType,
                            int biomeRadius,
                            int structureAvoidRadius,
                            List<ResourceKey<StructureSet>> structureSetToAvoid,
                            Optional<Integer> fixedYSpawn,
                            Optional<Float> centerTerrainHeightWeight)
    {
        this.monumentType = monumentType.toLowerCase(Locale.ROOT);
        this.biomeRadius = biomeRadius;
        this.structureAvoidRadius = structureAvoidRadius;
        this.structureSetToAvoid = structureSetToAvoid;
        this.fixedYSpawn = fixedYSpawn;
        this.centerTerrainHeightWeight = centerTerrainHeightWeight;
    }
}
