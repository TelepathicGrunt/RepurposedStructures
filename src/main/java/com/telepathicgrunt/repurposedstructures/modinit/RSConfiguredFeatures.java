package com.telepathicgrunt.repurposedstructures.modinit;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.configs.RSDungeonsConfig;
import com.telepathicgrunt.repurposedstructures.configs.RSWellsConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtFeatureConfig;
import com.telepathicgrunt.repurposedstructures.world.placements.SnapToLowerNonAirPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.UnlimitedCountPlacement;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.ArrayList;
import java.util.List;


public final class RSConfiguredFeatures {
    private RSConfiguredFeatures() {}

    private static final ResourceLocation EMPTY_ID = new ResourceLocation("minecraft:empty");

    // Dungeons

    // Need this field for dimension/biome blacklisting
    // Need this field so we can test to make sure we do not add the dungeons to biomes that
    // we already added an RS dungeon to. (Due to BiomeModification API running separately for each feature)
    public static List<PlacedFeature> RS_DUNGEONS = new ArrayList<>();

    private static final NbtDungeonConfig BADLANDS_DUNGEON_CONFIG = new NbtDungeonConfig("badlands", EMPTY_ID);
    public static ConfiguredFeature<?, ?> BADLANDS_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), BADLANDS_DUNGEON_CONFIG);
    public static PlacedFeature BADLANDS_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(BADLANDS_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.badlandsDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.badlandsDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.badlandsDungeonMaxHeight.get())),
            BiomeFilter.biome()));

    private static final NbtDungeonConfig DARK_FOREST_DUNGEON_CONFIG = new NbtDungeonConfig("dark_forest", new ResourceLocation(RepurposedStructures.MODID, "dungeons/dark_forest_post_process"));
    public static ConfiguredFeature<?, ?> DARK_FOREST_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), DARK_FOREST_DUNGEON_CONFIG);
    public static PlacedFeature DARK_FOREST_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(DARK_FOREST_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.darkForestDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.darkForestDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.darkForestDungeonMaxHeight.get())),
            BiomeFilter.biome()));

    private static final NbtDungeonConfig DESERT_DUNGEON_CONFIG = new NbtDungeonConfig("desert", EMPTY_ID);
    public static ConfiguredFeature<?, ?> DESERT_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), DESERT_DUNGEON_CONFIG);
    public static PlacedFeature DESERT_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(DESERT_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.desertDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.desertDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.desertDungeonMaxHeight.get())),
            BiomeFilter.biome()));

    private static final NbtDungeonConfig DEEP_DUNGEON_CONFIG = new NbtDungeonConfig("deep", "deep", EMPTY_ID, 11, false, 0);
    public static ConfiguredFeature<?, ?> DEEP_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), DEEP_DUNGEON_CONFIG);
    public static PlacedFeature DEEP_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(DEEP_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.deepDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.deepDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.deepDungeonMaxHeight.get())),
            BiomeFilter.biome()));

    private static final NbtDungeonConfig END_DUNGEON_CONFIG = new NbtDungeonConfig("end", "shulker_boxes", EMPTY_ID, 20, Blocks.SHULKER_BOX.defaultBlockState());
    public static ConfiguredFeature<?, ?> END_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), END_DUNGEON_CONFIG);
    public static PlacedFeature END_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(END_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.endDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.endDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.endDungeonMaxHeight.get())),
            BiomeFilter.biome()));

    private static final NbtDungeonConfig NETHER_DUNGEON_CONFIG = new NbtDungeonConfig("nether", EMPTY_ID);
    public static ConfiguredFeature<?, ?> NETHER_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), NETHER_DUNGEON_CONFIG);
    public static PlacedFeature NETHER_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(NETHER_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.netherDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.netherDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.netherDungeonMaxHeight.get())),
            BiomeFilter.biome()));

    private static final NbtDungeonConfig SNOW_DUNGEON_CONFIG = new NbtDungeonConfig("snow", EMPTY_ID);
    public static ConfiguredFeature<?, ?> SNOW_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), SNOW_DUNGEON_CONFIG);
    public static PlacedFeature SNOW_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(SNOW_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.snowDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.snowDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.snowDungeonMaxHeight.get())),
            BiomeFilter.biome()));

    private static final NbtDungeonConfig ICY_DUNGEON_CONFIG = new NbtDungeonConfig("icy", EMPTY_ID);
    public static ConfiguredFeature<?, ?> ICY_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), ICY_DUNGEON_CONFIG);
    public static PlacedFeature ICY_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(ICY_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.icyDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.icyDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.icyDungeonMaxHeight.get())),
            BiomeFilter.biome()));

    private static final NbtDungeonConfig SWAMP_DUNGEON_CONFIG = new NbtDungeonConfig("swamp", EMPTY_ID);
    public static ConfiguredFeature<?, ?> SWAMP_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), SWAMP_DUNGEON_CONFIG);
    public static PlacedFeature SWAMP_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(SWAMP_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.swampDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.swampDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.swampDungeonMaxHeight.get())),
            BiomeFilter.biome()));

    private static final NbtDungeonConfig MUSHROOM_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom"), "mushroom", "mushroom", new ResourceLocation(RepurposedStructures.MODID, "dungeons/mushroom"), EMPTY_ID);
    public static ConfiguredFeature<?, ?> MUSHROOM_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), MUSHROOM_DUNGEON_CONFIG);
    public static PlacedFeature MUSHROOM_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(MUSHROOM_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.mushroomDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.mushroomDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.mushroomDungeonMaxHeight.get())),
            BiomeFilter.biome()));


    private static final NbtDungeonConfig JUNGLE_DUNGEON_CONFIG = new NbtDungeonConfig("jungle", new ResourceLocation(RepurposedStructures.MODID, "dungeons/jungle_post_process"));
    public static ConfiguredFeature<?, ?> JUNGLE_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), JUNGLE_DUNGEON_CONFIG);
    public static PlacedFeature JUNGLE_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(JUNGLE_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.jungleDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.jungleDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.jungleDungeonMaxHeight.get())),
            BiomeFilter.biome()));

    private static final NbtDungeonConfig NEUTRAL_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_neutral_ocean"), "ocean", "ocean_neutral", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_neutral"), EMPTY_ID, 55, true, -1);
    public static ConfiguredFeature<?, ?> OCEAN_NEUTRAL_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), NEUTRAL_DUNGEON_CONFIG);
    public static PlacedFeature OCEAN_NEUTRAL_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(OCEAN_NEUTRAL_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.oceanDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.oceanDungeonMaxHeight.get())),
            BiomeFilter.biome()));

    private static final NbtDungeonConfig COLD_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_cold_ocean"), "ocean", "ocean_cold", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_cold"), EMPTY_ID, 55, true, -1);
    public static ConfiguredFeature<?, ?> OCEAN_COLD_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), COLD_DUNGEON_CONFIG);
    public static PlacedFeature OCEAN_COLD_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(OCEAN_COLD_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.oceanDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.oceanDungeonMaxHeight.get())),
            BiomeFilter.biome()));

    private static final NbtDungeonConfig FROZEN_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_frozen_ocean"), "ocean", "ocean_frozen", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_frozen"), EMPTY_ID, 55, true, -1);
    public static ConfiguredFeature<?, ?> OCEAN_FROZEN_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), FROZEN_DUNGEON_CONFIG);
    public static PlacedFeature OCEAN_FROZEN_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(OCEAN_FROZEN_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.oceanDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.oceanDungeonMaxHeight.get())),
            BiomeFilter.biome()));

    private static final NbtDungeonConfig LUKEWARM_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_lukewarm_ocean"), "ocean", "ocean_lukewarm", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_lukewarm"), EMPTY_ID, 55, true, -1);
    public static ConfiguredFeature<?, ?> OCEAN_LUKEWARM_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), LUKEWARM_DUNGEON_CONFIG);
    public static PlacedFeature OCEAN_LUKEWARM_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(OCEAN_LUKEWARM_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.oceanDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.oceanDungeonMaxHeight.get())),
            BiomeFilter.biome()));

    private static final NbtDungeonConfig WARM_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_warm_ocean"), "ocean", "ocean_warm", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_warm"), EMPTY_ID, 55, true, -1);
    public static ConfiguredFeature<?, ?> OCEAN_WARM_DUNGEONS = new ConfiguredFeature<>(RSFeatures.NBT_DUNGEONS.get(), WARM_DUNGEON_CONFIG);
    public static PlacedFeature OCEAN_WARM_DUNGEONS_PLACED = new PlacedFeature(Holder.direct(OCEAN_WARM_DUNGEONS), List.of(
            UnlimitedCountPlacement.of(RSDungeonsConfig.oceanDungeonAttemptsPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RSDungeonsConfig.oceanDungeonMinHeight.get()),
                    VerticalAnchor.absolute(RSDungeonsConfig.oceanDungeonMaxHeight.get())),
            BiomeFilter.biome()));


    // Wells

    // Need this field for dimension/biome blacklisting
    public static List<PlacedFeature> RS_WELLS = new ArrayList<>();

    public static ConfiguredFeature<?, ?> BADLANDS_WELL = new ConfiguredFeature<>(RSFeatures.NBT_FEATURE.get(),
            new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_badlands"),
                    false,
                    -2,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/badlands"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/badlands")));
    public static PlacedFeature BADLANDS_WELL_PLACED = new PlacedFeature(Holder.direct(BADLANDS_WELL), List.of(
            RarityFilter.onAverageOnceEvery(RSWellsConfig.wellBadlandsRarityPerChunk.get()),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome()));

    public static ConfiguredFeature<?, ?> NETHER_WELL = new ConfiguredFeature<>(RSFeatures.NBT_FEATURE.get(),
            new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_nether"),
                    false,
                    -1,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/nether"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/nether")));
    public static PlacedFeature NETHER_WELL_PLACED = new PlacedFeature(Holder.direct(NETHER_WELL), List.of(
            RarityFilter.onAverageOnceEvery(RSWellsConfig.wellNetherRarityPerChunk.get()),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.aboveBottom(30),
                    VerticalAnchor.belowTop(35)),
            SnapToLowerNonAirPlacement.snapToLowerNonAir(),
            BiomeFilter.biome()));

    public static ConfiguredFeature<?, ?> SNOW_WELL = new ConfiguredFeature<>(RSFeatures.NBT_FEATURE.get(),
            new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_snow"),
                    false,
                    -2,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/snow"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/snow")));
    public static PlacedFeature SNOW_WELL_PLACED = new PlacedFeature(Holder.direct(SNOW_WELL), List.of(
            RarityFilter.onAverageOnceEvery(RSWellsConfig.wellSnowRarityPerChunk.get()),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome()));

    public static ConfiguredFeature<?, ?> MOSSY_STONE_WELL = new ConfiguredFeature<>(RSFeatures.NBT_FEATURE.get(),
            new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_mossy_stone"),
                    true,
                    -2,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/mossy"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/mossy")));
    public static PlacedFeature MOSSY_STONE_WELL_PLACED = new PlacedFeature(Holder.direct(MOSSY_STONE_WELL), List.of(
            RarityFilter.onAverageOnceEvery(RSWellsConfig.wellMossyStoneRarityPerChunk.get()),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome()));

    public static ConfiguredFeature<?, ?> FOREST_WELL = new ConfiguredFeature<>(RSFeatures.NBT_FEATURE.get(),
            new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_forest"),
                    true,
                    -6,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/forest"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/forest")));
    public static PlacedFeature FOREST_WELL_PLACED = new PlacedFeature(Holder.direct(FOREST_WELL), List.of(
            RarityFilter.onAverageOnceEvery(RSWellsConfig.wellForestRarityPerChunk.get()),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome()));

    public static ConfiguredFeature<?, ?> MUSHROOM_WELL = new ConfiguredFeature<>(RSFeatures.NBT_FEATURE.get(),
            new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_mushroom"),
                    false,
                    -2,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/mushroom"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/mushroom")));
    public static PlacedFeature MUSHROOM_WELL_PLACED = new PlacedFeature(Holder.direct(MUSHROOM_WELL), List.of(
            RarityFilter.onAverageOnceEvery(RSWellsConfig.wellMushroomRarityPerChunk.get()),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome()));

    public static void registerPlacedFeatures() {
        Registry<PlacedFeature> registry = BuiltinRegistries.PLACED_FEATURE;

        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_badlands"), BADLANDS_DUNGEONS_PLACED));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_dark_forest"), DARK_FOREST_DUNGEONS_PLACED));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_desert"), DESERT_DUNGEONS_PLACED));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_deep"), DEEP_DUNGEONS_PLACED));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_end"), END_DUNGEONS_PLACED));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_nether"), NETHER_DUNGEONS_PLACED));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_snow"), SNOW_DUNGEONS_PLACED));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_icy"), ICY_DUNGEONS_PLACED));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_swamp"), SWAMP_DUNGEONS_PLACED));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom"), MUSHROOM_DUNGEONS_PLACED));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_jungle"), JUNGLE_DUNGEONS_PLACED));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_neutral_ocean"), OCEAN_NEUTRAL_DUNGEONS_PLACED));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_lukewarm_ocean"), OCEAN_LUKEWARM_DUNGEONS_PLACED));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_frozen_ocean"), OCEAN_FROZEN_DUNGEONS_PLACED));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_cold_ocean"), OCEAN_COLD_DUNGEONS_PLACED));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_warm_ocean"), OCEAN_WARM_DUNGEONS_PLACED));

        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_badlands"), BADLANDS_WELL_PLACED));
        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_nether"), NETHER_WELL_PLACED));
        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_snow"), SNOW_WELL_PLACED));
        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_mossy_stone"), MOSSY_STONE_WELL_PLACED));
        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_forest"), FOREST_WELL_PLACED));
        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_mushroom"), MUSHROOM_WELL_PLACED));
    }

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_badlands"), BADLANDS_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_dark_forest"), DARK_FOREST_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_desert"), DESERT_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_deep"), DEEP_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_end"), END_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_nether"), NETHER_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_snow"), SNOW_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_icy"), ICY_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_swamp"), SWAMP_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom"), MUSHROOM_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_jungle"), JUNGLE_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_neutral_ocean"), OCEAN_NEUTRAL_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_lukewarm_ocean"), OCEAN_LUKEWARM_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_frozen_ocean"), OCEAN_FROZEN_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_cold_ocean"), OCEAN_COLD_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_warm_ocean"), OCEAN_WARM_DUNGEONS);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_badlands"), BADLANDS_WELL);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_nether"), NETHER_WELL);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_snow"), SNOW_WELL);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_mossy_stone"), MOSSY_STONE_WELL);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_forest"), FOREST_WELL);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_mushroom"), MUSHROOM_WELL);
    }
}
