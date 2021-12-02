package com.telepathicgrunt.repurposedstructures.modinit;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtFeatureConfig;
import com.telepathicgrunt.repurposedstructures.world.placements.SnapToLowerNonAirPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.UnlimitedCountPlacement;
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
    public static List<ConfiguredFeature<?, ?>> RS_DUNGEONS = new ArrayList<>();

    private static final NbtDungeonConfig BADLANDS_DUNGEON_CONFIG = new NbtDungeonConfig("badlands", EMPTY_ID);
    public static ConfiguredFeature<?, ?> BADLANDS_DUNGEONS = RSFeatures.BADLANDS_DUNGEONS.configured(BADLANDS_DUNGEON_CONFIG);
    public static PlacedFeature BADLANDS_DUNGEONS_PLACED = BADLANDS_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.badlandsDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.badlandsDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.badlandsDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig DARK_FOREST_DUNGEON_CONFIG = new NbtDungeonConfig("dark_forest", new ResourceLocation(RepurposedStructures.MODID, "dungeons/dark_forest_post_process"));
    public static ConfiguredFeature<?, ?> DARK_FOREST_DUNGEONS = RSFeatures.DARK_FOREST_DUNGEONS.configured(DARK_FOREST_DUNGEON_CONFIG);
    public static PlacedFeature DARK_FOREST_DUNGEONS_PLACED = DARK_FOREST_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.darkForestDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.darkForestDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.darkForestDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig DESERT_DUNGEON_CONFIG = new NbtDungeonConfig("desert", EMPTY_ID);
    public static ConfiguredFeature<?, ?> DESERT_DUNGEONS = RSFeatures.DESERT_DUNGEONS.configured(DESERT_DUNGEON_CONFIG);
    public static PlacedFeature DESERT_DUNGEONS_PLACED = DESERT_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.desertDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.desertDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.desertDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig END_DUNGEON_CONFIG = new NbtDungeonConfig("end", "shulker_boxes", EMPTY_ID, 20, Blocks.SHULKER_BOX.defaultBlockState());
    public static ConfiguredFeature<?, ?> END_DUNGEONS = RSFeatures.END_DUNGEONS.configured(END_DUNGEON_CONFIG);
    public static PlacedFeature END_DUNGEONS_PLACED = END_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.endDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.endDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.endDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig NETHER_DUNGEON_CONFIG = new NbtDungeonConfig("nether", EMPTY_ID);
    public static ConfiguredFeature<?, ?> NETHER_DUNGEONS = RSFeatures.NETHER_DUNGEONS.configured(NETHER_DUNGEON_CONFIG);
    public static PlacedFeature NETHER_DUNGEONS_PLACED = NETHER_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.netherDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.netherDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.netherDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig SNOW_DUNGEON_CONFIG = new NbtDungeonConfig("snow", EMPTY_ID);
    public static ConfiguredFeature<?, ?> SNOW_DUNGEONS = RSFeatures.SNOW_DUNGEONS.configured(SNOW_DUNGEON_CONFIG);
    public static PlacedFeature SNOW_DUNGEONS_PLACED = SNOW_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.snowDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.snowDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.snowDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig ICY_DUNGEON_CONFIG = new NbtDungeonConfig("icy", EMPTY_ID);
    public static ConfiguredFeature<?, ?> ICY_DUNGEONS = RSFeatures.ICY_DUNGEONS.configured(ICY_DUNGEON_CONFIG);
    public static PlacedFeature ICY_DUNGEONS_PLACED = ICY_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.icyDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.icyDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.icyDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig SWAMP_DUNGEON_CONFIG = new NbtDungeonConfig("swamp", EMPTY_ID);
    public static ConfiguredFeature<?, ?> SWAMP_DUNGEONS = RSFeatures.SWAMP_DUNGEONS.configured(SWAMP_DUNGEON_CONFIG);
    public static PlacedFeature SWAMP_DUNGEONS_PLACED = SWAMP_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.swampDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.swampDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.swampDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig MUSHROOM_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom"), "mushroom", "mushroom", new ResourceLocation(RepurposedStructures.MODID, "dungeons/mushroom"), EMPTY_ID);
    public static ConfiguredFeature<?, ?> MUSHROOM_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.configured(MUSHROOM_DUNGEON_CONFIG);
    public static PlacedFeature MUSHROOM_DUNGEONS_PLACED = MUSHROOM_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight)),
            BiomeFilter.biome());


    private static final NbtDungeonConfig JUNGLE_DUNGEON_CONFIG = new NbtDungeonConfig("jungle", new ResourceLocation(RepurposedStructures.MODID, "dungeons/jungle_post_process"));
    public static ConfiguredFeature<?, ?> JUNGLE_DUNGEONS = RSFeatures.JUNGLE_DUNGEONS.configured(JUNGLE_DUNGEON_CONFIG);
    public static PlacedFeature JUNGLE_DUNGEONS_PLACED = JUNGLE_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.jungleDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.jungleDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.jungleDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig NEUTRAL_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_neutral_ocean"), "ocean", "ocean_neutral", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_neutral"), EMPTY_ID, 55, true, -1);
    public static ConfiguredFeature<?, ?> OCEAN_NEUTRAL_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configured(NEUTRAL_DUNGEON_CONFIG);
    public static PlacedFeature OCEAN_NEUTRAL_DUNGEONS_PLACED = OCEAN_NEUTRAL_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig COLD_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_cold_ocean"), "ocean", "ocean_cold", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_cold"), EMPTY_ID, 55, true, -1);
    public static ConfiguredFeature<?, ?> OCEAN_COLD_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configured(COLD_DUNGEON_CONFIG);
    public static PlacedFeature OCEAN_COLD_DUNGEONS_PLACED = OCEAN_COLD_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig FROZEN_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_frozen_ocean"), "ocean", "ocean_frozen", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_frozen"), EMPTY_ID, 55, true, -1);
    public static ConfiguredFeature<?, ?> OCEAN_FROZEN_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configured(FROZEN_DUNGEON_CONFIG);
    public static PlacedFeature OCEAN_FROZEN_DUNGEONS_PLACED = OCEAN_FROZEN_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig LUKEWARM_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_lukewarm_ocean"), "ocean", "ocean_lukewarm", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_lukewarm"), EMPTY_ID, 55, true, -1);
    public static ConfiguredFeature<?, ?> OCEAN_LUKEWARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configured(LUKEWARM_DUNGEON_CONFIG);
    public static PlacedFeature OCEAN_LUKEWARM_DUNGEONS_PLACED = OCEAN_LUKEWARM_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig WARM_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_warm_ocean"), "ocean", "ocean_warm", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_warm"), EMPTY_ID, 55, true, -1);
    public static ConfiguredFeature<?, ?> OCEAN_WARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configured(WARM_DUNGEON_CONFIG);
    public static PlacedFeature OCEAN_WARM_DUNGEONS_PLACED = OCEAN_WARM_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight)),
            BiomeFilter.biome());


    // Wells

    // Need this field for dimension/biome blacklisting
    public static List<ConfiguredFeature<?, ?>> RS_WELLS = new ArrayList<>();

    public static ConfiguredFeature<?, ?> BADLANDS_WELL = RSFeatures.BADLANDS_WELL
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_badlands"),
                    false,
                    -2,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/badlands"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/badlands")));
    public static PlacedFeature BADLANDS_WELL_PLACED = BADLANDS_WELL.placed(
            RarityFilter.onAverageOnceEvery(RepurposedStructures.RSAllConfig.RSWellsConfig.badlandsWellRarityPerChunk),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome());

    public static ConfiguredFeature<?, ?> NETHER_WELL = RSFeatures.NETHER_WELL
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_nether"),
                    false,
                    -1,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/nether"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/nether")));
    public static PlacedFeature NETHER_WELL_PLACED = NETHER_WELL.placed(
            RarityFilter.onAverageOnceEvery(RepurposedStructures.RSAllConfig.RSWellsConfig.netherWellRarityPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.aboveBottom(30),
                    VerticalAnchor.belowTop(35)),
            SnapToLowerNonAirPlacement.snapToLowerNonAir(),
            BiomeFilter.biome());

    public static ConfiguredFeature<?, ?> SNOW_WELL = RSFeatures.SNOW_WELL
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_snow"),
                    false,
                    -2,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/snow"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/snow")));
    public static PlacedFeature SNOW_WELL_PLACED = SNOW_WELL.placed(
            RarityFilter.onAverageOnceEvery(RepurposedStructures.RSAllConfig.RSWellsConfig.snowWellRarityPerChunk),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome());

    public static ConfiguredFeature<?, ?> MOSSY_STONE_WELL = RSFeatures.MOSSY_STONE_WELL
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_mossy_stone"),
                    true,
                    -2,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/mossy"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/mossy")));
    public static PlacedFeature MOSSY_STONE_WELL_PLACED = MOSSY_STONE_WELL.placed(
            RarityFilter.onAverageOnceEvery(RepurposedStructures.RSAllConfig.RSWellsConfig.mossyStoneWellRarityPerChunk),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome());

    public static ConfiguredFeature<?, ?> FOREST_WELL = RSFeatures.FOREST_WELL
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_forest"),
                    true,
                    -6,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/forest"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/forest")));
    public static PlacedFeature FOREST_WELL_PLACED = FOREST_WELL.placed(
            RarityFilter.onAverageOnceEvery(RepurposedStructures.RSAllConfig.RSWellsConfig.forestWellRarityPerChunk),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome());

    public static ConfiguredFeature<?, ?> MUSHROOM_WELL = RSFeatures.MUSHROOM_WELL
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_mushroom"),
                    false,
                    -2,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/mushroom"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/mushroom")));
    public static PlacedFeature MUSHROOM_WELL_PLACED = MUSHROOM_WELL.placed(
            RarityFilter.onAverageOnceEvery(RepurposedStructures.RSAllConfig.RSWellsConfig.mushroomWellRarityPerChunk),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
            BiomeFilter.biome());

    public static void registerPlacedFeatures() {
        Registry<PlacedFeature> registry = BuiltinRegistries.PLACED_FEATURE;

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_badlands"), BADLANDS_DUNGEONS_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_dark_forest"), DARK_FOREST_DUNGEONS_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_desert"), DESERT_DUNGEONS_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_end"), END_DUNGEONS_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_nether"), NETHER_DUNGEONS_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_snow"), SNOW_DUNGEONS_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_icy"), ICY_DUNGEONS_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_swamp"), SWAMP_DUNGEONS_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom"), MUSHROOM_DUNGEONS_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_jungle"), JUNGLE_DUNGEONS_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_neutral_ocean"), OCEAN_NEUTRAL_DUNGEONS_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_lukewarm_ocean"), OCEAN_LUKEWARM_DUNGEONS_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_frozen_ocean"), OCEAN_FROZEN_DUNGEONS_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_cold_ocean"), OCEAN_COLD_DUNGEONS_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_warm_ocean"), OCEAN_WARM_DUNGEONS_PLACED);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_badlands"), BADLANDS_WELL_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_nether"), NETHER_WELL_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_snow"), SNOW_WELL_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_mossy_stone"), MOSSY_STONE_WELL_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_forest"), FOREST_WELL_PLACED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_mushroom"), MUSHROOM_WELL_PLACED);
    }

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_badlands"), BADLANDS_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_dark_forest"), DARK_FOREST_DUNGEONS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_desert"), DESERT_DUNGEONS);
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
