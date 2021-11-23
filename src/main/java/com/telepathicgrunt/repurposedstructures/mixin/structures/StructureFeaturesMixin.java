package com.telepathicgrunt.repurposedstructures.mixin.structures;

import com.google.common.collect.ImmutableSet;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;
import java.util.function.BiConsumer;

@Mixin(StructureFeatures.class)
public abstract class StructureFeaturesMixin {

    @Shadow
    private static void register(BiConsumer<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> biConsumer, ConfiguredStructureFeature<?, ?> configuredStructureFeature, ResourceKey<Biome> resourceKey) {}

    @Shadow
    private static void register(BiConsumer<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> biConsumer, ConfiguredStructureFeature<?, ?> configuredStructureFeature, Set<ResourceKey<Biome>> set) {
    }

    @Inject(
        method = "registerStructures(Ljava/util/function/BiConsumer;)V",
        at = @At(value = "HEAD")
    )
    private static void repurposedstructures_addStructuresToBiomes(BiConsumer<ConfiguredStructureFeature<?, ?>, ResourceKey<Biome>> biConsumer, CallbackInfo ci) {
        if(RepurposedStructures.initialized) {

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

            register(biConsumer, RSConfiguredStructures.BASTION_UNDERGROUND, dryOverworldBiomes);

            register(biConsumer, RSConfiguredStructures.CITY_NETHER, netherBiomes);

            register(biConsumer, RSConfiguredStructures.FORTRESS_JUNGLE, jungleBiomes);

            register(biConsumer, RSConfiguredStructures.IGLOO_GRASSY, forestPlainsMeadowBiomes);
            register(biConsumer, RSConfiguredStructures.IGLOO_STONE, giantTaigaBiomes);

            register(biConsumer, RSConfiguredStructures.MANSION_BIRCH, birchForestBiomes);
            register(biConsumer, RSConfiguredStructures.MANSION_DESERT, Biomes.DESERT);
            register(biConsumer, RSConfiguredStructures.MANSION_JUNGLE, jungleBiomes);
            register(biConsumer, RSConfiguredStructures.MANSION_OAK, forestNotDarkBiomes);
            register(biConsumer, RSConfiguredStructures.MANSION_SAVANNA, savannaBiomes);
            register(biConsumer, RSConfiguredStructures.MANSION_SNOWY, ImmutableSet.<ResourceKey<Biome>>builder().addAll(snowyBiomes).add(Biomes.ICE_SPIKES).build());
            register(biConsumer, RSConfiguredStructures.MANSION_TAIGA, Biomes.TAIGA);

            register(biConsumer, RSConfiguredStructures.MINESHAFT_BIRCH, birchForestBiomes);
            register(biConsumer, RSConfiguredStructures.MINESHAFT_DARK_FOREST, Biomes.DARK_FOREST);
            register(biConsumer, RSConfiguredStructures.MINESHAFT_DESERT, Biomes.DESERT);
            register(biConsumer, RSConfiguredStructures.MINESHAFT_ICY, ImmutableSet.<ResourceKey<Biome>>builder().addAll(icyBiomes).add(Biomes.SNOWY_SLOPES).build());
            register(biConsumer, RSConfiguredStructures.MINESHAFT_JUNGLE, jungleBiomes);
            register(biConsumer, RSConfiguredStructures.MINESHAFT_OCEAN, oceanBiomes);
            register(biConsumer, RSConfiguredStructures.MINESHAFT_SAVANNA, savannaBiomes);
            register(biConsumer, RSConfiguredStructures.MINESHAFT_STONE, windsweptAndWarmPeaksBiomes);
            register(biConsumer, RSConfiguredStructures.MINESHAFT_SWAMP, Biomes.SWAMP);
            register(biConsumer, RSConfiguredStructures.MINESHAFT_TAIGA, smallTaigaBiomes);
            register(biConsumer, RSConfiguredStructures.MINESHAFT_CRIMSON, Biomes.CRIMSON_FOREST);
            register(biConsumer, RSConfiguredStructures.MINESHAFT_NETHER, netherBiomes);
            register(biConsumer, RSConfiguredStructures.MINESHAFT_WARPED, Biomes.WARPED_FOREST);
            register(biConsumer, RSConfiguredStructures.MINESHAFT_END, endDenseBiomes);

            register(biConsumer, RSConfiguredStructures.OUTPOST_BADLANDS, badlandsBiomes);
            register(biConsumer, RSConfiguredStructures.OUTPOST_BIRCH, birchForestBiomes);
            register(biConsumer, RSConfiguredStructures.OUTPOST_DESERT, Biomes.DESERT);
            register(biConsumer, RSConfiguredStructures.OUTPOST_GIANT_TREE_TAIGA, giantTaigaBiomes);
            register(biConsumer, RSConfiguredStructures.OUTPOST_ICY, icyBiomes);
            register(biConsumer, RSConfiguredStructures.OUTPOST_JUNGLE, jungleBiomes);
            register(biConsumer, RSConfiguredStructures.OUTPOST_OAK, forestNotDarkBiomes);
            register(biConsumer, RSConfiguredStructures.OUTPOST_SNOWY, snowyBiomes);
            register(biConsumer, RSConfiguredStructures.OUTPOST_TAIGA, Biomes.TAIGA);
            register(biConsumer, RSConfiguredStructures.OUTPOST_NETHER_BRICK, netherNotCrimsonOrWarpedBiomes);
            register(biConsumer, RSConfiguredStructures.OUTPOST_CRIMSON, Biomes.CRIMSON_FOREST);
            register(biConsumer, RSConfiguredStructures.OUTPOST_WARPED, Biomes.WARPED_FOREST);
            register(biConsumer, RSConfiguredStructures.OUTPOST_END, endDenseBiomes);

            register(biConsumer, RSConfiguredStructures.PYRAMID_BADLANDS, badlandsBiomes);
            register(biConsumer, RSConfiguredStructures.PYRAMID_FLOWER_FOREST, Biomes.FLOWER_FOREST);
            register(biConsumer, RSConfiguredStructures.PYRAMID_GIANT_TREE_TAIGA, giantTaigaBiomes);
            register(biConsumer, RSConfiguredStructures.PYRAMID_JUNGLE, jungleBiomes);
            register(biConsumer, RSConfiguredStructures.PYRAMID_ICY, icyBiomes);
            register(biConsumer, RSConfiguredStructures.PYRAMID_MUSHROOM, Biomes.MUSHROOM_FIELDS);
            register(biConsumer, RSConfiguredStructures.PYRAMID_OCEAN, ImmutableSet.<ResourceKey<Biome>>builder().addAll(deepOceanBiomes).add(Biomes.WARM_OCEAN).build());
            register(biConsumer, RSConfiguredStructures.PYRAMID_SNOWY, snowyBiomes);
            register(biConsumer, RSConfiguredStructures.PYRAMID_NETHER, netherBiomes);
            register(biConsumer, RSConfiguredStructures.PYRAMID_END, endDenseBiomes);

            register(biConsumer, RSConfiguredStructures.RUINED_PORTAL_END, endNotCenterBiomes);

            register(biConsumer, RSConfiguredStructures.RUINS_LAND_HOT, Biomes.DESERT);
            register(biConsumer, RSConfiguredStructures.RUINS_LAND_WARM, warmNotSavannaBiomes);
            register(biConsumer, RSConfiguredStructures.RUINS_NETHER, netherBiomes);

            register(biConsumer, RSConfiguredStructures.SHIPWRECK_CRIMSON, Biomes.CRIMSON_FOREST);
            register(biConsumer, RSConfiguredStructures.SHIPWRECK_NETHER_BRICKS, netherNotCrimsonOrWarpedBiomes);
            register(biConsumer, RSConfiguredStructures.SHIPWRECK_WARPED, Biomes.WARPED_FOREST);
            register(biConsumer, RSConfiguredStructures.SHIPWRECK_END, endDenseBiomes);

            register(biConsumer, RSConfiguredStructures.STRONGHOLD_NETHER, netherBiomes);
            register(biConsumer, RSConfiguredStructures.STRONGHOLD_END, endDenseBiomes);

            register(biConsumer, RSConfiguredStructures.NETHER_BASALT_TEMPLE, Biomes.BASALT_DELTAS);
            register(biConsumer, RSConfiguredStructures.NETHER_CRIMSON_TEMPLE, Biomes.CRIMSON_FOREST);
            register(biConsumer, RSConfiguredStructures.NETHER_SOUL_TEMPLE, Biomes.SOUL_SAND_VALLEY);
            register(biConsumer, RSConfiguredStructures.NETHER_WASTELAND_TEMPLE, Biomes.NETHER_WASTES);
            register(biConsumer, RSConfiguredStructures.NETHER_WARPED_TEMPLE, Biomes.WARPED_FOREST);

            register(biConsumer, RSConfiguredStructures.VILLAGE_BADLANDS, badlandsBiomes);
            register(biConsumer, RSConfiguredStructures.VILLAGE_BIRCH, birchForestBiomes);
            register(biConsumer, RSConfiguredStructures.VILLAGE_DARK_FOREST, Biomes.DARK_FOREST);
            register(biConsumer, RSConfiguredStructures.VILLAGE_GIANT_TAIGA, giantTaigaBiomes);
            register(biConsumer, RSConfiguredStructures.VILLAGE_JUNGLE, jungleBiomes);
            register(biConsumer, RSConfiguredStructures.VILLAGE_MOUNTAINS, allMountainsBiomes);
            register(biConsumer, RSConfiguredStructures.VILLAGE_MUSHROOM, Biomes.MUSHROOM_FIELDS);
            register(biConsumer, RSConfiguredStructures.VILLAGE_OAK, forestNotDarkBiomes);
            register(biConsumer, RSConfiguredStructures.VILLAGE_SWAMP, Biomes.SWAMP);
            register(biConsumer, RSConfiguredStructures.VILLAGE_CRIMSON, Biomes.CRIMSON_FOREST);
            register(biConsumer, RSConfiguredStructures.VILLAGE_WARPED, Biomes.WARPED_FOREST);

            register(biConsumer, RSConfiguredStructures.WITCH_HUTS_BIRCH, birchForestBiomes);
            register(biConsumer, RSConfiguredStructures.WITCH_HUTS_DARK_FOREST, Biomes.DARK_FOREST);
            register(biConsumer, RSConfiguredStructures.WITCH_HUTS_GIANT_TREE_TAIGA, giantTaigaBiomes);
            register(biConsumer, RSConfiguredStructures.WITCH_HUTS_OAK, Biomes.FOREST);
            register(biConsumer, RSConfiguredStructures.WITCH_HUTS_TAIGA, smallTaigaBiomes);
        }
    }
}