package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;

import java.util.Set;

public final class TemporaryBiomeInjection {
    private TemporaryBiomeInjection() {}

    public static void addStructureToBiomes(ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> structureToMultiMap) {

        ImmutableSet<ResourceKey<Biome>> badlandsBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.BADLANDS)
                .add(Biomes.ERODED_BADLANDS)
                .add(Biomes.WOODED_BADLANDS)
                .build();

        ImmutableSet<ResourceKey<Biome>> jungleBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.JUNGLE)
                .add(Biomes.BAMBOO_JUNGLE)
                .add(Biomes.SPARSE_JUNGLE)
                .build();

        ImmutableSet<ResourceKey<Biome>> windsweptBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.WINDSWEPT_FOREST)
                .add(Biomes.WINDSWEPT_HILLS)
                .add(Biomes.WINDSWEPT_GRAVELLY_HILLS)
                .build();

        ImmutableSet<ResourceKey<Biome>> windsweptAndWarmPeaksBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .addAll(windsweptBiomes)
                .add(Biomes.STONY_PEAKS)
                .add(Biomes.JAGGED_PEAKS)
                .build();

        ImmutableSet<ResourceKey<Biome>> allMountainsBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .addAll(windsweptAndWarmPeaksBiomes)
                .add(Biomes.FROZEN_PEAKS)
                .add(Biomes.SNOWY_SLOPES)
                .build();

        ImmutableSet<ResourceKey<Biome>> birchForestBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.BIRCH_FOREST)
                .add(Biomes.OLD_GROWTH_BIRCH_FOREST)
                .build();

        ImmutableSet<ResourceKey<Biome>> forestNotDarkBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.FOREST)
                .add(Biomes.FLOWER_FOREST)
                .build();

        ImmutableSet<ResourceKey<Biome>> plainsBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.PLAINS)
                .add(Biomes.SUNFLOWER_PLAINS)
                .build();

        ImmutableSet<ResourceKey<Biome>> giantTaigaBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.OLD_GROWTH_SPRUCE_TAIGA)
                .add(Biomes.OLD_GROWTH_PINE_TAIGA)
                .build();

        ImmutableSet<ResourceKey<Biome>> savannaBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.SAVANNA)
                .add(Biomes.SAVANNA_PLATEAU)
                .add(Biomes.WINDSWEPT_SAVANNA)
                .build();

        ImmutableSet<ResourceKey<Biome>> smallTaigaBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.GROVE)
                .add(Biomes.TAIGA)
                .add(Biomes.SNOWY_TAIGA)
                .build();

        ImmutableSet<ResourceKey<Biome>> snowyBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.GROVE)
                .add(Biomes.SNOWY_TAIGA)
                .add(Biomes.SNOWY_PLAINS)
                .add(Biomes.SNOWY_SLOPES)
                .add(Biomes.SNOWY_BEACH)
                .build();

        ImmutableSet<ResourceKey<Biome>> icyBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.ICE_SPIKES)
                .add(Biomes.FROZEN_PEAKS)
                .build();

        ImmutableSet<ResourceKey<Biome>> caveBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.DRIPSTONE_CAVES)
                .add(Biomes.LUSH_CAVES)
                .build();

        ImmutableSet<ResourceKey<Biome>> netherNotCrimsonOrWarpedBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.BASALT_DELTAS)
                .add(Biomes.NETHER_WASTES)
                .add(Biomes.SOUL_SAND_VALLEY)
                .build();

        ImmutableSet<ResourceKey<Biome>> netherBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.BASALT_DELTAS)
                .add(Biomes.CRIMSON_FOREST)
                .add(Biomes.NETHER_WASTES)
                .add(Biomes.SOUL_SAND_VALLEY)
                .add(Biomes.WARPED_FOREST)
                .build();

        ImmutableSet<ResourceKey<Biome>> endDenseBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.END_MIDLANDS)
                .add(Biomes.END_HIGHLANDS)
                .build();

        ImmutableSet<ResourceKey<Biome>> endNotCenterBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .addAll(endDenseBiomes)
                .add(Biomes.END_BARRENS)
                .add(Biomes.SMALL_END_ISLANDS)
                .build();

        ImmutableSet<ResourceKey<Biome>> deepOceanBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.DEEP_OCEAN)
                .add(Biomes.DEEP_COLD_OCEAN)
                .add(Biomes.DEEP_FROZEN_OCEAN)
                .add(Biomes.DEEP_LUKEWARM_OCEAN)
                .build();

        ImmutableSet<ResourceKey<Biome>> oceanBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .add(Biomes.OCEAN)
                .add(Biomes.DEEP_OCEAN)
                .add(Biomes.COLD_OCEAN)
                .add(Biomes.DEEP_COLD_OCEAN)
                .add(Biomes.FROZEN_OCEAN)
                .add(Biomes.DEEP_FROZEN_OCEAN)
                .add(Biomes.LUKEWARM_OCEAN)
                .add(Biomes.DEEP_LUKEWARM_OCEAN)
                .add(Biomes.WARM_OCEAN)
                .build();

        ImmutableSet<ResourceKey<Biome>> forestPlainsMeadowBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .addAll(birchForestBiomes)
                .addAll(forestNotDarkBiomes)
                .addAll(plainsBiomes)
                .add(Biomes.MEADOW)
                .add(Biomes.DARK_FOREST)
                .build();

        ImmutableSet<ResourceKey<Biome>> warmNotSavannaBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .addAll(birchForestBiomes)
                .addAll(forestNotDarkBiomes)
                .addAll(plainsBiomes)
                .addAll(giantTaigaBiomes)
                .add(Biomes.TAIGA)
                .add(Biomes.DARK_FOREST)
                .add(Biomes.SWAMP)
                .build();

        ImmutableSet<ResourceKey<Biome>> dryOverworldBiomes = ImmutableSet.<ResourceKey<Biome>>builder()
                .addAll(badlandsBiomes)
                .addAll(jungleBiomes)
                .addAll(birchForestBiomes)
                .addAll(giantTaigaBiomes)
                .addAll(plainsBiomes)
                .addAll(forestNotDarkBiomes)
                .addAll(savannaBiomes)
                .addAll(smallTaigaBiomes)
                .addAll(allMountainsBiomes)
                .addAll(caveBiomes)
                .add(Biomes.SNOWY_PLAINS)
                .add(Biomes.DARK_FOREST)
                .add(Biomes.DESERT)
                .add(Biomes.ICE_SPIKES)
                .add(Biomes.MEADOW)
                .add(Biomes.SWAMP)
                .build();

        register(structureToMultiMap, RSConfiguredStructures.BASTION_UNDERGROUND, dryOverworldBiomes);

        register(structureToMultiMap, RSConfiguredStructures.CITY_NETHER, netherBiomes);

        register(structureToMultiMap, RSConfiguredStructures.FORTRESS_JUNGLE, jungleBiomes);

        register(structureToMultiMap, RSConfiguredStructures.IGLOO_GRASSY, forestPlainsMeadowBiomes);
        register(structureToMultiMap, RSConfiguredStructures.IGLOO_STONE, giantTaigaBiomes);

        register(structureToMultiMap, RSConfiguredStructures.MANSION_BIRCH, birchForestBiomes);
        register(structureToMultiMap, RSConfiguredStructures.MANSION_DESERT, Biomes.DESERT);
        register(structureToMultiMap, RSConfiguredStructures.MANSION_JUNGLE, jungleBiomes);
        register(structureToMultiMap, RSConfiguredStructures.MANSION_OAK, forestNotDarkBiomes);
        register(structureToMultiMap, RSConfiguredStructures.MANSION_SAVANNA, savannaBiomes);
        register(structureToMultiMap, RSConfiguredStructures.MANSION_SNOWY, ImmutableSet.<ResourceKey<Biome>>builder().addAll(snowyBiomes).add(Biomes.ICE_SPIKES).build());
        register(structureToMultiMap, RSConfiguredStructures.MANSION_TAIGA, Biomes.TAIGA);

        register(structureToMultiMap, RSConfiguredStructures.MINESHAFT_BIRCH, birchForestBiomes);
        register(structureToMultiMap, RSConfiguredStructures.MINESHAFT_DARK_FOREST, Biomes.DARK_FOREST);
        register(structureToMultiMap, RSConfiguredStructures.MINESHAFT_DESERT, Biomes.DESERT);
        register(structureToMultiMap, RSConfiguredStructures.MINESHAFT_ICY, ImmutableSet.<ResourceKey<Biome>>builder().addAll(icyBiomes).add(Biomes.SNOWY_SLOPES).build());
        register(structureToMultiMap, RSConfiguredStructures.MINESHAFT_JUNGLE, jungleBiomes);
        register(structureToMultiMap, RSConfiguredStructures.MINESHAFT_OCEAN, oceanBiomes);
        register(structureToMultiMap, RSConfiguredStructures.MINESHAFT_SAVANNA, savannaBiomes);
        register(structureToMultiMap, RSConfiguredStructures.MINESHAFT_STONE, windsweptAndWarmPeaksBiomes);
        register(structureToMultiMap, RSConfiguredStructures.MINESHAFT_SWAMP, Biomes.SWAMP);
        register(structureToMultiMap, RSConfiguredStructures.MINESHAFT_TAIGA, smallTaigaBiomes);
        register(structureToMultiMap, RSConfiguredStructures.MINESHAFT_CRIMSON, Biomes.CRIMSON_FOREST);
        register(structureToMultiMap, RSConfiguredStructures.MINESHAFT_NETHER, netherBiomes);
        register(structureToMultiMap, RSConfiguredStructures.MINESHAFT_WARPED, Biomes.WARPED_FOREST);
        register(structureToMultiMap, RSConfiguredStructures.MINESHAFT_END, endDenseBiomes);

        register(structureToMultiMap, RSConfiguredStructures.OUTPOST_BADLANDS, badlandsBiomes);
        register(structureToMultiMap, RSConfiguredStructures.OUTPOST_BIRCH, birchForestBiomes);
        register(structureToMultiMap, RSConfiguredStructures.OUTPOST_DESERT, Biomes.DESERT);
        register(structureToMultiMap, RSConfiguredStructures.OUTPOST_GIANT_TREE_TAIGA, giantTaigaBiomes);
        register(structureToMultiMap, RSConfiguredStructures.OUTPOST_ICY, icyBiomes);
        register(structureToMultiMap, RSConfiguredStructures.OUTPOST_JUNGLE, jungleBiomes);
        register(structureToMultiMap, RSConfiguredStructures.OUTPOST_OAK, forestNotDarkBiomes);
        register(structureToMultiMap, RSConfiguredStructures.OUTPOST_SNOWY, snowyBiomes);
        register(structureToMultiMap, RSConfiguredStructures.OUTPOST_TAIGA, Biomes.TAIGA);
        register(structureToMultiMap, RSConfiguredStructures.OUTPOST_NETHER_BRICK, netherNotCrimsonOrWarpedBiomes);
        register(structureToMultiMap, RSConfiguredStructures.OUTPOST_CRIMSON, Biomes.CRIMSON_FOREST);
        register(structureToMultiMap, RSConfiguredStructures.OUTPOST_WARPED, Biomes.WARPED_FOREST);
        register(structureToMultiMap, RSConfiguredStructures.OUTPOST_END, endDenseBiomes);

        register(structureToMultiMap, RSConfiguredStructures.PYRAMID_BADLANDS, badlandsBiomes);
        register(structureToMultiMap, RSConfiguredStructures.PYRAMID_FLOWER_FOREST, Biomes.FLOWER_FOREST);
        register(structureToMultiMap, RSConfiguredStructures.PYRAMID_GIANT_TREE_TAIGA, giantTaigaBiomes);
        register(structureToMultiMap, RSConfiguredStructures.PYRAMID_JUNGLE, jungleBiomes);
        register(structureToMultiMap, RSConfiguredStructures.PYRAMID_ICY, icyBiomes);
        register(structureToMultiMap, RSConfiguredStructures.PYRAMID_MUSHROOM, Biomes.MUSHROOM_FIELDS);
        register(structureToMultiMap, RSConfiguredStructures.PYRAMID_OCEAN, ImmutableSet.<ResourceKey<Biome>>builder().addAll(deepOceanBiomes).add(Biomes.WARM_OCEAN).build());
        register(structureToMultiMap, RSConfiguredStructures.PYRAMID_SNOWY, snowyBiomes);
        register(structureToMultiMap, RSConfiguredStructures.PYRAMID_NETHER, netherBiomes);
        register(structureToMultiMap, RSConfiguredStructures.PYRAMID_END, endDenseBiomes);

        register(structureToMultiMap, RSConfiguredStructures.RUINED_PORTAL_END, endNotCenterBiomes);

        register(structureToMultiMap, RSConfiguredStructures.RUINS_LAND_HOT, Biomes.DESERT);
        register(structureToMultiMap, RSConfiguredStructures.RUINS_LAND_WARM, warmNotSavannaBiomes);
        register(structureToMultiMap, RSConfiguredStructures.RUINS_NETHER, netherBiomes);

        register(structureToMultiMap, RSConfiguredStructures.SHIPWRECK_CRIMSON, Biomes.CRIMSON_FOREST);
        register(structureToMultiMap, RSConfiguredStructures.SHIPWRECK_NETHER_BRICKS, netherNotCrimsonOrWarpedBiomes);
        register(structureToMultiMap, RSConfiguredStructures.SHIPWRECK_WARPED, Biomes.WARPED_FOREST);
        register(structureToMultiMap, RSConfiguredStructures.SHIPWRECK_END, endDenseBiomes);

        register(structureToMultiMap, RSConfiguredStructures.STRONGHOLD_NETHER, netherBiomes);
        register(structureToMultiMap, RSConfiguredStructures.STRONGHOLD_END, endDenseBiomes);

        register(structureToMultiMap, RSConfiguredStructures.NETHER_BASALT_TEMPLE, Biomes.BASALT_DELTAS);
        register(structureToMultiMap, RSConfiguredStructures.NETHER_CRIMSON_TEMPLE, Biomes.CRIMSON_FOREST);
        register(structureToMultiMap, RSConfiguredStructures.NETHER_SOUL_TEMPLE, Biomes.SOUL_SAND_VALLEY);
        register(structureToMultiMap, RSConfiguredStructures.NETHER_WASTELAND_TEMPLE, Biomes.NETHER_WASTES);
        register(structureToMultiMap, RSConfiguredStructures.NETHER_WARPED_TEMPLE, Biomes.WARPED_FOREST);

        register(structureToMultiMap, RSConfiguredStructures.VILLAGE_BADLANDS, badlandsBiomes);
        register(structureToMultiMap, RSConfiguredStructures.VILLAGE_BIRCH, birchForestBiomes);
        register(structureToMultiMap, RSConfiguredStructures.VILLAGE_DARK_FOREST, Biomes.DARK_FOREST);
        register(structureToMultiMap, RSConfiguredStructures.VILLAGE_GIANT_TAIGA, giantTaigaBiomes);
        register(structureToMultiMap, RSConfiguredStructures.VILLAGE_JUNGLE, jungleBiomes);
        register(structureToMultiMap, RSConfiguredStructures.VILLAGE_MOUNTAINS, allMountainsBiomes);
        register(structureToMultiMap, RSConfiguredStructures.VILLAGE_MUSHROOM, Biomes.MUSHROOM_FIELDS);
        register(structureToMultiMap, RSConfiguredStructures.VILLAGE_OAK, forestNotDarkBiomes);
        register(structureToMultiMap, RSConfiguredStructures.VILLAGE_SWAMP, Biomes.SWAMP);
        register(structureToMultiMap, RSConfiguredStructures.VILLAGE_CRIMSON, Biomes.CRIMSON_FOREST);
        register(structureToMultiMap, RSConfiguredStructures.VILLAGE_WARPED, Biomes.WARPED_FOREST);

        register(structureToMultiMap, RSConfiguredStructures.WITCH_HUTS_BIRCH, birchForestBiomes);
        register(structureToMultiMap, RSConfiguredStructures.WITCH_HUTS_DARK_FOREST, Biomes.DARK_FOREST);
        register(structureToMultiMap, RSConfiguredStructures.WITCH_HUTS_GIANT_TREE_TAIGA, giantTaigaBiomes);
        register(structureToMultiMap, RSConfiguredStructures.WITCH_HUTS_OAK, Biomes.FOREST);
        register(structureToMultiMap, RSConfiguredStructures.WITCH_HUTS_TAIGA, smallTaigaBiomes);
    }

    private static void register(ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> multimapBuilder, ConfiguredStructureFeature<?, ?> configuredStructureFeature, ResourceKey<Biome> biomeKey) {

        // Create the multimap of Configured Structures to biomes we will need.
        ImmutableMultimap.Builder<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> tempConfiguredStructureBiomeMultiMap = ImmutableMultimap.builder();
        // Add all biomekey entries that this Configured Structure can spawn in.
        tempConfiguredStructureBiomeMultiMap.put(configuredStructureFeature, biomeKey);
        // Add the structure to associate with this new multimap of Configured Structures to biomes to spawn in.
        multimapBuilder.put(configuredStructureFeature.feature, tempConfiguredStructureBiomeMultiMap.build());
    }

    private static void register(ImmutableMap.Builder<StructureFeature<?>, ImmutableMultimap<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>>> multimapBuilder, ConfiguredStructureFeature<?, ?> configuredStructureFeature, Set<ResourceKey<Biome>> biomeKeySet) {
        // Create the multimap of Configured Structures to biomes we will need.
        ImmutableMultimap.Builder<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> tempConfiguredStructureBiomeMultiMap = ImmutableMultimap.builder();
        // Add all biomekey entries that this Configured Structure can spawn in.
        biomeKeySet.forEach(biomeKey -> tempConfiguredStructureBiomeMultiMap.put(configuredStructureFeature, biomeKey));
        // Add the structure to associate with this new multimap of Configured Structures to biomes to spawn in.
        multimapBuilder.put(configuredStructureFeature.feature, tempConfiguredStructureBiomeMultiMap.build());
    }
}
