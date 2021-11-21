package com.telepathicgrunt.repurposedstructures.modinit;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.configs.GenericMobConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.MinecartConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.MineshaftSupportConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtDungeonConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.NbtFeatureConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndLengthConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetAndRangeConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetChanceConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetConfig;
import com.telepathicgrunt.repurposedstructures.world.features.configs.StructureTargetLengthRangeConfig;
import com.telepathicgrunt.repurposedstructures.world.placements.MinusEightPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.SnapToLowerNonAirPlacement;
import com.telepathicgrunt.repurposedstructures.world.placements.UnlimitedCountPlacement;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.HugeFungusConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.BlockPileConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
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
    public static List<ConfiguredFeature<?, ?>> RS_DUNGEONS_WITHOUT_MUSHROOM = new ArrayList<>();

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

    private static final NbtDungeonConfig NEUTRAL_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_neutral_ocean"), "ocean", "ocean_neutral", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_neutral"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_NEUTRAL_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configured(NEUTRAL_DUNGEON_CONFIG);
    public static PlacedFeature OCEAN_NEUTRAL_DUNGEONS_PLACED = OCEAN_NEUTRAL_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig COLD_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_cold_ocean"), "ocean", "ocean_cold", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_cold"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_COLD_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configured(COLD_DUNGEON_CONFIG);
    public static PlacedFeature OCEAN_COLD_DUNGEONS_PLACED = OCEAN_COLD_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig FROZEN_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_frozen_ocean"), "ocean", "ocean_frozen", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_frozen"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_FROZEN_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configured(FROZEN_DUNGEON_CONFIG);
    public static PlacedFeature OCEAN_FROZEN_DUNGEONS_PLACED = OCEAN_FROZEN_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig LUKEWARM_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_lukewarm_ocean"), "ocean", "ocean_lukewarm", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_lukewarm"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_LUKEWARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configured(LUKEWARM_DUNGEON_CONFIG);
    public static PlacedFeature OCEAN_LUKEWARM_DUNGEONS_PLACED = OCEAN_LUKEWARM_DUNGEONS.placed(
            UnlimitedCountPlacement.of(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight)),
            BiomeFilter.biome());

    private static final NbtDungeonConfig WARM_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_warm_ocean"), "ocean", "ocean_warm", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_warm"), EMPTY_ID, 55, true, -2);
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
            RarityFilter.onAverageOnceEvery(RepurposedStructures.RSAllConfig.RSWellsConfig.forestWellRarityPerChunk),
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


    // Misc

    public static PlacedFeature LILY_OF_THE_VALLEY_FEATURE = Feature.FLOWER.configured((FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.LILY_OF_THE_VALLEY.defaultBlockState()))), List.of(), 64))).placed();

    public static PlacedFeature CRIMSON_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configured(new HugeFungusConfiguration(Blocks.CRIMSON_NYLIUM.defaultBlockState(), Blocks.CRIMSON_STEM.defaultBlockState(), Blocks.NETHER_WART_BLOCK.defaultBlockState(), Blocks.SHROOMLIGHT.defaultBlockState(), false)).placed();
    public static PlacedFeature WARPED_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configured(new HugeFungusConfiguration(Blocks.WARPED_NYLIUM.defaultBlockState(), Blocks.WARPED_STEM.defaultBlockState(), Blocks.WARPED_WART_BLOCK.defaultBlockState(), Blocks.SHROOMLIGHT.defaultBlockState(), false)).placed();
    public static PlacedFeature COBBLESTONE_PATCH = Feature.BLOCK_PILE.configured(new BlockPileConfiguration(BlockStateProvider.simple(Blocks.COBBLESTONE.defaultBlockState()))).placed();
    public static PlacedFeature CHORUS_PLANT = Feature.CHORUS_PLANT.configured(FeatureConfiguration.NONE).placed();

    public static PlacedFeature WITHER_SKELETON_WITH_BOW = RSFeatures.WITHER_SKELETON_WITH_BOW.configured(FeatureConfiguration.NONE).placed();
    public static PlacedFeature SHULKER_MOB = RSFeatures.SHULKER_MOB.configured(FeatureConfiguration.NONE).placed();
    public static PlacedFeature DROWNED_WITH_ARMOR = RSFeatures.DROWNED_WITH_ARMOR.configured(FeatureConfiguration.NONE).placed();
    public static PlacedFeature SKELETON_BOW = RSFeatures.SKELETON.configured(new GenericMobConfig(null, null, null, null, null, 0.25f, 14)).placed();
    public static PlacedFeature SKELETON_BOW_DEADLY = RSFeatures.SKELETON.configured(new GenericMobConfig(null, Items.LEATHER_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.LEATHER_LEGGINGS, null, 0.23f, 18)).placed();
    public static PlacedFeature SKELETON_BOW_DEADLIEST = RSFeatures.SKELETON.configured(new GenericMobConfig(null, Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, null, 0.21f, 24)).placed();
    public static PlacedFeature SKELETON_SWORD = RSFeatures.SKELETON.configured(new GenericMobConfig(Items.STONE_SWORD, null, null, null, null, 0.25f, 14)).placed();
    public static PlacedFeature SKELETON_SWORD_DEADLY = RSFeatures.SKELETON.configured(new GenericMobConfig(Items.STONE_SWORD, Items.LEATHER_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.LEATHER_LEGGINGS, null, 0.29f, 18)).placed();
    public static PlacedFeature SKELETON_SWORD_DEADLIEST = RSFeatures.SKELETON.configured(new GenericMobConfig(Items.STONE_SWORD, Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, null, 0.33f, 24)).placed();
    public static PlacedFeature SKELETON_HORSEMAN_SWORD = RSFeatures.SKELETON_HORSEMAN.configured(new GenericMobConfig(Items.IRON_SWORD, Items.IRON_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.IRON_BOOTS, 0.29f, 28)).placed();
    public static PlacedFeature SKELETON_HORSEMAN_BOW = RSFeatures.SKELETON_HORSEMAN.configured(new GenericMobConfig(Items.BOW, Items.IRON_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.IRON_BOOTS, 0.25f, 24)).placed();

    public static PlacedFeature POST_PROCESS_CONNECTING_BLOCKS = RSFeatures.POST_PROCESS_CONNECTING_BLOCKS.configured(FeatureConfiguration.NONE).placed();
    public static PlacedFeature JUNGLE_FORTRESS_STRUCTURE_BREAKAGE = RSFeatures.STRUCTURE_BREAKAGE.configured(new StructureTargetChanceConfig(0.366f)).placed();
    public static PlacedFeature JUNGLE_FORTRESS_STRUCTURE_VINE_BREAKAGE = RSFeatures.STRUCTURE_VINE_BREAKAGE.configured(new StructureTargetAndLengthConfig(15, 4)).placed();
    public static PlacedFeature JUNGLE_FORTRESS_STRUCTURE_VINES = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(10, 3, 3)).placed();
    public static PlacedFeature NETHER_STRONGHOLD_CHAINS = RSFeatures.STRUCTURE_CHAINS.configured(new StructureTargetConfig(7)).placed();
    public static PlacedFeature END_STRONGHOLD_CHAINS = RSFeatures.STRUCTURE_END_ROD_CHAINS.configured(new StructureTargetConfig(4)).placed();
    public static PlacedFeature END_MINESHAFT_CHORUS = RSFeatures.STRUCTURE_CHORUS.configured(new StructureTargetConfig(4)).placed();
    public static PlacedFeature END_MINESHAFT_DENSE_CHORUS = RSFeatures.STRUCTURE_CHORUS.configured(new StructureTargetConfig(60)).placed();
    public static PlacedFeature CRIMSON_MINESHAFT_PLANTS = RSFeatures.STRUCTURE_CRIMSON_PLANTS.configured(new StructureTargetAndLengthConfig(40, 3)).placed();
    public static PlacedFeature CRIMSON_MINESHAFT_DENSE_PLANTS = RSFeatures.STRUCTURE_CRIMSON_PLANTS.configured(new StructureTargetAndLengthConfig(100, 3)).placed();
    public static PlacedFeature CRIMSON_MINESHAFT_PLANTS_LONG = RSFeatures.STRUCTURE_CRIMSON_PLANTS.configured(new StructureTargetAndLengthConfig(85, 7)).placed();
    public static PlacedFeature NETHER_MINESHAFT_FIRE = RSFeatures.STRUCTURE_FIRE.configured(new StructureTargetConfig(3)).placed();
    public static PlacedFeature NETHER_MINESHAFT_DENSE_FIRE = RSFeatures.STRUCTURE_FIRE.configured(new StructureTargetConfig(40)).placed();
    public static PlacedFeature NETHER_MINESHAFT_NETHERWART = RSFeatures.STRUCTURE_NETHERWART.configured(new StructureTargetConfig(40)).placed();
    public static PlacedFeature OCEAN_MINESHAFT_PLANTS = RSFeatures.STRUCTURE_SEAGRASS.configured(new StructureTargetConfig(40)).placed();
    public static PlacedFeature OCEAN_MINESHAFT_DENSE_PLANTS = RSFeatures.STRUCTURE_SEAGRASS.configured(new StructureTargetConfig(80)).placed();
    public static PlacedFeature DARK_FOREST_MINESHAFT_VINES = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(20, 2, 3)).placed();
    public static PlacedFeature DARK_FOREST_MINESHAFT_VINES_LONG = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(23, 8, 1)).placed();
    public static PlacedFeature SWAMP_MINESHAFT_VINES = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(10, 1, 3)).placed();
    public static PlacedFeature SWAMP_MINESHAFT_VINES_LONG = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(20, 8, 1)).placed();
    public static PlacedFeature JUNGLE_MINESHAFT_LEAVES_AND_VINES = RSFeatures.STRUCTURE_VINES_AND_LEAVES.configured(new StructureTargetAndLengthConfig(30, 3)).placed();
    public static PlacedFeature JUNGLE_MINESHAFT_VINES_LONG = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(25, 8, 1)).placed();
    public static PlacedFeature WARPED_MINESHAFT_PLANTS = RSFeatures.STRUCTURE_WARPED_PLANTS.configured(new StructureTargetAndLengthConfig(25, 3)).placed();
    public static PlacedFeature WARPED_MINESHAFT_DENSE_PLANTS = RSFeatures.STRUCTURE_WARPED_PLANTS.configured(new StructureTargetAndLengthConfig(80, 3)).placed();
    public static PlacedFeature WARPED_MINESHAFT_PLANTS_LONG = RSFeatures.STRUCTURE_WARPED_PLANTS.configured(new StructureTargetAndLengthConfig(60, 7)).placed();
    public static PlacedFeature JUNGLE_PYRAMID_STRUCTURE_VINES = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(17, 4, 3)).placed();
    public static PlacedFeature JUNGLE_PYRAMID_STRUCTURE_VINES_NARROW = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(17, 2, 1)).placed();
    public static PlacedFeature OCEAN_PYRAMID_STRUCTURE_PLANTS = RSFeatures.STRUCTURE_SEAGRASS.configured(new StructureTargetConfig(12)).placed();
    public static PlacedFeature FLOWER_FOREST_PYRAMID_STRUCTURE_GRASS = RSFeatures.STRUCTURE_GRASS.configured(new StructureTargetAndRangeConfig(24, 3)).placed();
    public static PlacedFeature FLOWER_FOREST_PYRAMID_STRUCTURE_FLOWERS = RSFeatures.STRUCTURE_FLOWERS.configured(new StructureTargetAndRangeConfig(8, 3)).placed();
    public static PlacedFeature SWAMP_VILLAGE_STRUCTURE_VINES = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(10, 3, 4, 10)).placed();

    public static PlacedFeature WARM_LAND_RUINS_STRUCTURE_GRASS = Feature.RANDOM_PATCH
            .configured(FeatureUtils.simpleRandomPatchConfiguration(4, Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.TALL_GRASS))).onlyWhenEmpty())).placed(
                CountPlacement.of(2),
                MinusEightPlacement.subtractedEight(),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

    public static PlacedFeature HOT_LAND_RUINS_STRUCTURE_DEAD_BUSH = Feature.RANDOM_PATCH
            .configured(FeatureUtils.simpleRandomPatchConfiguration(4, Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.DEAD_BUSH))).onlyWhenEmpty())).placed(
                CountPlacement.of(8),
                MinusEightPlacement.subtractedEight(),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

    public static PlacedFeature BIRCH_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/birch/minecart"))).placed();
    public static PlacedFeature CRIMSON_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/crimson/minecart"))).placed();
    public static PlacedFeature DARK_FOREST_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/dark_forest/minecart"))).placed();
    public static PlacedFeature DESERT_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/desert/minecart"))).placed();
    public static PlacedFeature ICY_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/icy/minecart"))).placed();
    public static PlacedFeature JUNGLE_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/jungle/minecart"))).placed();
    public static PlacedFeature NETHER_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/nether/minecart"))).placed();
    public static PlacedFeature OCEAN_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/ocean/minecart"), true)).placed();
    public static PlacedFeature SAVANNA_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/savanna/minecart"))).placed();
    public static PlacedFeature STONE_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/stone/minecart"))).placed();
    public static PlacedFeature SWAMP_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/swamp/minecart"))).placed();
    public static PlacedFeature TAIGA_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/taiga/minecart"))).placed();
    public static PlacedFeature WARPED_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/warped/minecart"))).placed();

    public static PlacedFeature BIRCH_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.BIRCH_LOG.defaultBlockState(), Blocks.BIRCH_FENCE.defaultBlockState(), Blocks.BIRCH_PLANKS, false)).placed();
    public static PlacedFeature CRIMSON_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.CRIMSON_HYPHAE.defaultBlockState(), Blocks.CRIMSON_FENCE.defaultBlockState(), Blocks.CRIMSON_NYLIUM, false)).placed();
    public static PlacedFeature DARK_FOREST_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.DARK_OAK_LOG.defaultBlockState(), Blocks.DARK_OAK_FENCE.defaultBlockState(), Blocks.DARK_OAK_PLANKS, false)).placed();
    public static PlacedFeature DESERT_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.SMOOTH_SANDSTONE.defaultBlockState(), Blocks.SANDSTONE_WALL.defaultBlockState(), Blocks.SMOOTH_SANDSTONE, false)).placed();
    public static PlacedFeature ICY_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.PACKED_ICE.defaultBlockState(), Blocks.DIORITE_WALL.defaultBlockState(), Blocks.ICE, false)).placed();
    public static PlacedFeature JUNGLE_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.JUNGLE_LOG.defaultBlockState(), Blocks.JUNGLE_FENCE.defaultBlockState(), Blocks.JUNGLE_PLANKS, false)).placed();
    public static PlacedFeature NETHER_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.CRACKED_NETHER_BRICKS.defaultBlockState(), Blocks.NETHER_BRICK_FENCE.defaultBlockState(), Blocks.NETHER_BRICKS, false)).placed();
    public static PlacedFeature OCEAN_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.DARK_PRISMARINE.defaultBlockState(), Blocks.PRISMARINE_WALL.defaultBlockState(), Blocks.PRISMARINE_BRICKS, true)).placed();
    public static PlacedFeature SAVANNA_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.ACACIA_LOG.defaultBlockState(), Blocks.ACACIA_FENCE.defaultBlockState(), Blocks.ACACIA_PLANKS, false)).placed();
    public static PlacedFeature STONE_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.ANDESITE.defaultBlockState(), Blocks.COBBLESTONE_WALL.defaultBlockState(), Blocks.ANDESITE, false)).placed();
    public static PlacedFeature SWAMP_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), Blocks.MOSSY_STONE_BRICK_WALL.defaultBlockState(), Blocks.MOSS_BLOCK, false)).placed();
    public static PlacedFeature TAIGA_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_FENCE.defaultBlockState(), Blocks.SPRUCE_PLANKS, false)).placed();
    public static PlacedFeature WARPED_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.WARPED_HYPHAE.defaultBlockState(), Blocks.WARPED_FENCE.defaultBlockState(), Blocks.WARPED_NYLIUM, false)).placed();

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

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "lily_of_the_valley"), LILY_OF_THE_VALLEY_FEATURE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_fungi_not_planted"), CRIMSON_FUNGI_NOT_PLANTED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_fungi_not_planted"), WARPED_FUNGI_NOT_PLANTED);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "cobblestone_patch"), COBBLESTONE_PATCH);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "chorus_plant"), CHORUS_PLANT);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "wither_skeleton_with_bow"), WITHER_SKELETON_WITH_BOW);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "shulker_mob"), SHULKER_MOB);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "drowned_with_armor"), DROWNED_WITH_ARMOR);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_bow"), SKELETON_BOW);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_bow_deadly"), SKELETON_BOW_DEADLY);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_bow_deadliest"), SKELETON_BOW_DEADLIEST);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_sword"), SKELETON_SWORD);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_sword_deadly"), SKELETON_SWORD_DEADLY);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_sword_deadliest"), SKELETON_SWORD_DEADLIEST);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_horseman_sword"), SKELETON_HORSEMAN_SWORD);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "skeleton_horseman_bow"), SKELETON_HORSEMAN_BOW);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "post_process_connecting_blocks"), POST_PROCESS_CONNECTING_BLOCKS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_fortress_structure_breakage"), JUNGLE_FORTRESS_STRUCTURE_BREAKAGE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_fortress_structure_vine_breakage"), JUNGLE_FORTRESS_STRUCTURE_VINE_BREAKAGE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_fortress_structure_vines"), JUNGLE_FORTRESS_STRUCTURE_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_stronghold_chains"), NETHER_STRONGHOLD_CHAINS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "end_stronghold_chains"), END_STRONGHOLD_CHAINS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "end_mineshaft_chorus"), END_MINESHAFT_CHORUS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "end_mineshaft_dense_chorus"), END_MINESHAFT_DENSE_CHORUS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_mineshaft_plants"), CRIMSON_MINESHAFT_PLANTS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_mineshaft_dense_plants"), CRIMSON_MINESHAFT_DENSE_PLANTS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_mineshaft_plants_long"), CRIMSON_MINESHAFT_PLANTS_LONG);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_mineshaft_fire"), NETHER_MINESHAFT_FIRE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_mineshaft_dense_fire"), NETHER_MINESHAFT_DENSE_FIRE);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_mineshaft_netherwart"), NETHER_MINESHAFT_NETHERWART);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "ocean_mineshaft_plants"), OCEAN_MINESHAFT_PLANTS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "ocean_mineshaft_dense_plants"), OCEAN_MINESHAFT_DENSE_PLANTS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dark_forest_mineshaft_vines"), DARK_FOREST_MINESHAFT_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dark_forest_mineshaft_vines_long"), DARK_FOREST_MINESHAFT_VINES_LONG);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "swamp_mineshaft_vines"), SWAMP_MINESHAFT_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "swamp_mineshaft_vines_long"), SWAMP_MINESHAFT_VINES_LONG);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_mineshaft_leaves_and_vines"), JUNGLE_MINESHAFT_LEAVES_AND_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_mineshaft_vines_long"), JUNGLE_MINESHAFT_VINES_LONG);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_mineshaft_plants"), WARPED_MINESHAFT_PLANTS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_mineshaft_dense_plants"), WARPED_MINESHAFT_DENSE_PLANTS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_mineshaft_plants_long"), WARPED_MINESHAFT_PLANTS_LONG);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_pyramid_structure_vines"), JUNGLE_PYRAMID_STRUCTURE_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_pyramid_structure_vines_narrow"), JUNGLE_PYRAMID_STRUCTURE_VINES_NARROW);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "ocean_pyramid_structure_plants"), OCEAN_PYRAMID_STRUCTURE_PLANTS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "flower_forest_pyramid_structure_grass"), FLOWER_FOREST_PYRAMID_STRUCTURE_GRASS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "flower_forest_pyramid_structure_flowers"), FLOWER_FOREST_PYRAMID_STRUCTURE_FLOWERS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "swamp_village_structure_vines"), SWAMP_VILLAGE_STRUCTURE_VINES);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warm_land_ruins_structure_grass"), WARM_LAND_RUINS_STRUCTURE_GRASS);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "hot_land_ruins_structure_dead_bush"), HOT_LAND_RUINS_STRUCTURE_DEAD_BUSH);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "birch_mineshaft_minecart"), BIRCH_MINESHAFT_MINECART);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_mineshaft_minecart"), CRIMSON_MINESHAFT_MINECART);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dark_forest_mineshaft_minecart"), DARK_FOREST_MINESHAFT_MINECART);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "desert_mineshaft_minecart"), DESERT_MINESHAFT_MINECART);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "icy_mineshaft_minecart"), ICY_MINESHAFT_MINECART);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_mineshaft_minecart"), JUNGLE_MINESHAFT_MINECART);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_mineshaft_minecart"), NETHER_MINESHAFT_MINECART);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "ocean_mineshaft_minecart"), OCEAN_MINESHAFT_MINECART);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "savanna_mineshaft_minecart"), SAVANNA_MINESHAFT_MINECART);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "stone_mineshaft_minecart"), STONE_MINESHAFT_MINECART);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "swamp_mineshaft_minecart"), SWAMP_MINESHAFT_MINECART);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "taiga_mineshaft_minecart"), TAIGA_MINESHAFT_MINECART);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_mineshaft_minecart"), WARPED_MINESHAFT_MINECART);

        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "birch_mineshaft_support"), BIRCH_MINESHAFT_SUPPORT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "crimson_mineshaft_support"), CRIMSON_MINESHAFT_SUPPORT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dark_forest_mineshaft_support"), DARK_FOREST_MINESHAFT_SUPPORT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "desert_mineshaft_support"), DESERT_MINESHAFT_SUPPORT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "icy_mineshaft_support"), ICY_MINESHAFT_SUPPORT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "jungle_mineshaft_support"), JUNGLE_MINESHAFT_SUPPORT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "nether_mineshaft_support"), NETHER_MINESHAFT_SUPPORT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "ocean_mineshaft_support"), OCEAN_MINESHAFT_SUPPORT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "savanna_mineshaft_support"), SAVANNA_MINESHAFT_SUPPORT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "stone_mineshaft_support"), STONE_MINESHAFT_SUPPORT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "swamp_mineshaft_support"), SWAMP_MINESHAFT_SUPPORT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "taiga_mineshaft_support"), TAIGA_MINESHAFT_SUPPORT);
        Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "warped_mineshaft_support"), WARPED_MINESHAFT_SUPPORT);
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
