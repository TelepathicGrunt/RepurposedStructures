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
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.HugeFungusConfiguration;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.BlockPileConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.HeightmapConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneDecoratorConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;


public class RSConfiguredFeatures {
    private static final ResourceLocation EMPTY_ID = new ResourceLocation("minecraft:empty");

    // Dungeons

    // Need this field for dimension/biome blacklisting
    // Need this field so we can test to make sure we do not add the dungeons to biomes that
    // we already added an RS dungeon to. (Due to BiomeModification API running separately for each feature)
    public static List<ConfiguredFeature<?, ?>> RS_DUNGEONS = new ArrayList<>();

    private static final NbtDungeonConfig BADLANDS_DUNGEON_CONFIG = new NbtDungeonConfig("badlands", EMPTY_ID);
    public static ConfiguredFeature<?, ?> BADLANDS_DUNGEONS = RSFeatures.BADLANDS_DUNGEONS.configured(BADLANDS_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.badlandsDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.badlandsDungeonMaxHeight))))
                    .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.badlandsDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig DARK_FOREST_DUNGEON_CONFIG = new NbtDungeonConfig("dark_forest", new ResourceLocation(RepurposedStructures.MODID, "dungeons/dark_forest_post_process"));
    public static ConfiguredFeature<?, ?> DARK_FOREST_DUNGEONS = RSFeatures.DARK_FOREST_DUNGEONS.configured(DARK_FOREST_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.darkForestDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.darkForestDungeonMaxHeight))))
                    .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.darkForestDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig DESERT_DUNGEON_CONFIG = new NbtDungeonConfig("desert", EMPTY_ID);
    public static ConfiguredFeature<?, ?> DESERT_DUNGEONS = RSFeatures.DESERT_DUNGEONS.configured(DESERT_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.desertDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.desertDungeonMaxHeight))))
            .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.desertDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig END_DUNGEON_CONFIG = new NbtDungeonConfig("end", "shulker_boxes", EMPTY_ID, 20, Blocks.SHULKER_BOX.defaultBlockState());
    public static ConfiguredFeature<?, ?> END_DUNGEONS = RSFeatures.END_DUNGEONS.configured(END_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.endDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.endDungeonMaxHeight))))
            .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.endDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig NETHER_DUNGEON_CONFIG = new NbtDungeonConfig("nether", EMPTY_ID);
    public static ConfiguredFeature<?, ?> NETHER_DUNGEONS = RSFeatures.NETHER_DUNGEONS.configured(NETHER_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.netherDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.netherDungeonMaxHeight))))
            .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.netherDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig SNOW_DUNGEON_CONFIG = new NbtDungeonConfig("snow", EMPTY_ID);
    public static ConfiguredFeature<?, ?> SNOW_DUNGEONS = RSFeatures.SNOW_DUNGEONS.configured(SNOW_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.snowDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.snowDungeonMaxHeight))))
            .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.snowDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig ICY_DUNGEON_CONFIG = new NbtDungeonConfig("icy", EMPTY_ID);
    public static ConfiguredFeature<?, ?> ICY_DUNGEONS = RSFeatures.ICY_DUNGEONS.configured(ICY_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.icyDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.icyDungeonMaxHeight))))
                    .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.icyDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig SWAMP_DUNGEON_CONFIG = new NbtDungeonConfig("swamp", EMPTY_ID);
    public static ConfiguredFeature<?, ?> SWAMP_DUNGEONS = RSFeatures.SWAMP_DUNGEONS.configured(SWAMP_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.swampDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.swampDungeonMaxHeight))))
            .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.swampDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig MUSHROOM_HIGH_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom_high"), "mushroom", "mushroom_high", new ResourceLocation(RepurposedStructures.MODID, "dungeons/mushroom_high"), EMPTY_ID);
    public static ConfiguredFeature<?, ?> MUSHROOM_HIGH_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.configured(MUSHROOM_HIGH_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(Math.max(63, RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight)),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight))))
            .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(Math.round(Math.max(0,
                    ((RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight - 62F) /
                    (RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight - RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight)) *
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk
            ))))));

    private static final NbtDungeonConfig MUSHROOM_LOW_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom_low"), "mushroom", "mushroom_low", new ResourceLocation(RepurposedStructures.MODID, "dungeons/mushroom_low"), EMPTY_ID);
    public static ConfiguredFeature<?, ?> MUSHROOM_LOW_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.configured(MUSHROOM_LOW_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight),
                    VerticalAnchor.absolute(Math.min(62, RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight)))))
            .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(Math.round(Math.max(0,
                    ((62F - RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight) /
                    (RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight - RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight)) *
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk
            ))))));

    private static final NbtDungeonConfig JUNGLE_DUNGEON_CONFIG = new NbtDungeonConfig("jungle", new ResourceLocation(RepurposedStructures.MODID, "dungeons/jungle_post_process"));
    public static ConfiguredFeature<?, ?> JUNGLE_DUNGEONS = RSFeatures.JUNGLE_DUNGEONS.configured(JUNGLE_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.jungleDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.jungleDungeonMaxHeight))))
            .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.jungleDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig NEUTRAL_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_neutral_ocean"), "ocean", "ocean_neutral", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_neutral"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_NEUTRAL_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configured(NEUTRAL_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))))
            .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig COLD_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_cold_ocean"), "ocean", "ocean_cold", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_cold"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_COLD_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configured(COLD_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))))
                    .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig FROZEN_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_frozen_ocean"), "ocean", "ocean_frozen", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_frozen"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_FROZEN_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configured(FROZEN_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))))
                    .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig LUKEWARM_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_lukewarm_ocean"), "ocean", "ocean_lukewarm", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_lukewarm"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_LUKEWARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configured(LUKEWARM_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))))
                    .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig WARM_DUNGEON_CONFIG = new NbtDungeonConfig(new ResourceLocation(RepurposedStructures.MODID, "dungeons_warm_ocean"), "ocean", "ocean_warm", new ResourceLocation(RepurposedStructures.MODID, "dungeons/ocean_warm"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_WARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configured(WARM_DUNGEON_CONFIG)
            .decorated(RSPlacements.DUNGEON_PLACEMENT.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight),
                    VerticalAnchor.absolute(RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))))
                    .decorated(RSPlacements.UNLIMITED_COUNT.configured(new CountConfiguration(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));


    // Wells

    // Need this field for dimension/biome blacklisting
    public static List<ConfiguredFeature<?, ?>> RS_WELLS = new ArrayList<>();

    public static ConfiguredFeature<?, ?> BADLANDS_WELL = RSFeatures.BADLANDS_WELL
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_badlands"),
                    false,
                    -2,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/badlands"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/badlands")))
            .decorated(FeatureDecorator.HEIGHTMAP.configured(new HeightmapConfiguration(Heightmap.Types.WORLD_SURFACE_WG)).squared())
            .rarity(RepurposedStructures.RSAllConfig.RSWellsConfig.badlandsWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> NETHER_WELL = RSFeatures.NETHER_WELL
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_nether"),
                    false,
                    -1,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/nether"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/nether")))
            .decorated(RSPlacements.SNAP_TO_LOWER_NON_AIR_PLACEMENT.configured(NoneDecoratorConfiguration.INSTANCE))
            .decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(UniformHeight.of(
                    VerticalAnchor.aboveBottom(30),
                    VerticalAnchor.belowTop(35)))))
            .rarity(RepurposedStructures.RSAllConfig.RSWellsConfig.netherWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> SNOW_WELL = RSFeatures.SNOW_WELL
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_snow"),
                    false,
                    -2,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/snow"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/snow")))
            .decorated(FeatureDecorator.HEIGHTMAP.configured(new HeightmapConfiguration(Heightmap.Types.WORLD_SURFACE_WG)).squared())
            .rarity(RepurposedStructures.RSAllConfig.RSWellsConfig.snowWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> MOSSY_STONE_WELL = RSFeatures.MOSSY_STONE_WELL
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_mossy_stone"),
                    true,
                    -2,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/mossy"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/mossy")))
            .decorated(FeatureDecorator.HEIGHTMAP.configured(new HeightmapConfiguration(Heightmap.Types.OCEAN_FLOOR_WG)).squared())
            .rarity(RepurposedStructures.RSAllConfig.RSWellsConfig.mossyStoneWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> FOREST_WELL = RSFeatures.FOREST_WELL
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_forest"),
                    true,
                    -6,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/forest"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/forest")))
            .decorated(FeatureDecorator.HEIGHTMAP.configured(new HeightmapConfiguration(Heightmap.Types.WORLD_SURFACE_WG)).squared())
            .rarity(RepurposedStructures.RSAllConfig.RSWellsConfig.forestWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> MUSHROOM_WELL = RSFeatures.MUSHROOM_WELL
            .configured(new NbtFeatureConfig(
                    new ResourceLocation(RepurposedStructures.MODID, "well_mushroom"),
                    false,
                    -2,
                    ImmutableList.of(Pair.of(new ResourceLocation(RepurposedStructures.MODID, "wells/mushroom"), 1)),
                    new ResourceLocation(RepurposedStructures.MODID, "wells/mushroom")))
            .decorated(FeatureDecorator.HEIGHTMAP.configured(new HeightmapConfiguration(Heightmap.Types.WORLD_SURFACE_WG)).squared())
            .rarity(RepurposedStructures.RSAllConfig.RSWellsConfig.mushroomWellRarityPerChunk);


    // Misc

    public static ConfiguredFeature<?, ?> LILY_OF_THE_VALLEY_FEATURE = Feature.FLOWER.configured(
            (new RandomPatchConfiguration.GrassConfigurationBuilder(
                    new SimpleStateProvider(Blocks.LILY_OF_THE_VALLEY.defaultBlockState()),
                    SimpleBlockPlacer.INSTANCE))
            .tries(64)
            .build());

    public static ConfiguredFeature<?, ?> CRIMSON_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configured(HugeFungusConfiguration.HUGE_CRIMSON_FUNGI_NOT_PLANTED_CONFIG);
    public static ConfiguredFeature<?, ?> WARPED_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configured(HugeFungusConfiguration.HUGE_WARPED_FUNGI_NOT_PLANTED_CONFIG);
    public static ConfiguredFeature<?, ?> COBBLESTONE_PATCH = Feature.BLOCK_PILE.configured(new BlockPileConfiguration(new SimpleStateProvider(Blocks.COBBLESTONE.defaultBlockState())));
    public static ConfiguredFeature<?, ?> CHORUS_PLANT = Feature.CHORUS_PLANT.configured(FeatureConfiguration.NONE);

    public static ConfiguredFeature<?, ?> WITHER_SKELETON_WITH_BOW = RSFeatures.WITHER_SKELETON_WITH_BOW.configured(FeatureConfiguration.NONE);
    public static ConfiguredFeature<?, ?> SHULKER_MOB = RSFeatures.SHULKER_MOB.configured(FeatureConfiguration.NONE);
    public static ConfiguredFeature<?, ?> DROWNED_WITH_ARMOR = RSFeatures.DROWNED_WITH_ARMOR.configured(FeatureConfiguration.NONE);
    public static ConfiguredFeature<?, ?> SKELETON_BOW = RSFeatures.SKELETON.configured(new GenericMobConfig(null, null, null, null, null, 0.25f, 14));
    public static ConfiguredFeature<?, ?> SKELETON_BOW_DEADLY = RSFeatures.SKELETON.configured(new GenericMobConfig(null, Items.LEATHER_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.LEATHER_LEGGINGS, null, 0.23f, 18));
    public static ConfiguredFeature<?, ?> SKELETON_BOW_DEADLIEST = RSFeatures.SKELETON.configured(new GenericMobConfig(null, Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, null, 0.21f, 24));
    public static ConfiguredFeature<?, ?> SKELETON_SWORD = RSFeatures.SKELETON.configured(new GenericMobConfig(Items.STONE_SWORD, null, null, null, null, 0.25f, 14));
    public static ConfiguredFeature<?, ?> SKELETON_SWORD_DEADLY = RSFeatures.SKELETON.configured(new GenericMobConfig(Items.STONE_SWORD, Items.LEATHER_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.LEATHER_LEGGINGS, null, 0.29f, 18));
    public static ConfiguredFeature<?, ?> SKELETON_SWORD_DEADLIEST = RSFeatures.SKELETON.configured(new GenericMobConfig(Items.STONE_SWORD, Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, null, 0.33f, 24));
    public static ConfiguredFeature<?, ?> SKELETON_HORSEMAN_SWORD = RSFeatures.SKELETON_HORSEMAN.configured(new GenericMobConfig(Items.IRON_SWORD, Items.IRON_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.IRON_BOOTS, 0.29f, 28));
    public static ConfiguredFeature<?, ?> SKELETON_HORSEMAN_BOW = RSFeatures.SKELETON_HORSEMAN.configured(new GenericMobConfig(Items.BOW, Items.IRON_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.IRON_BOOTS, 0.25f, 24));

    public static ConfiguredFeature<?, ?> POST_PROCESS_CONNECTING_BLOCKS = RSFeatures.POST_PROCESS_CONNECTING_BLOCKS.configured(FeatureConfiguration.NONE);
    public static ConfiguredFeature<?, ?> JUNGLE_FORTRESS_STRUCTURE_BREAKAGE = RSFeatures.STRUCTURE_BREAKAGE.configured(new StructureTargetChanceConfig(0.366f));
    public static ConfiguredFeature<?, ?> JUNGLE_FORTRESS_STRUCTURE_VINE_BREAKAGE = RSFeatures.STRUCTURE_VINE_BREAKAGE.configured(new StructureTargetAndLengthConfig(15, 4));
    public static ConfiguredFeature<?, ?> JUNGLE_FORTRESS_STRUCTURE_VINES = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(10, 3, 3));
    public static ConfiguredFeature<?, ?> NETHER_STRONGHOLD_CHAINS = RSFeatures.STRUCTURE_CHAINS.configured(new StructureTargetConfig(7));
    public static ConfiguredFeature<?, ?> END_MINESHAFT_CHORUS = RSFeatures.STRUCTURE_CHORUS.configured(new StructureTargetConfig(4));
    public static ConfiguredFeature<?, ?> END_MINESHAFT_DENSE_CHORUS = RSFeatures.STRUCTURE_CHORUS.configured(new StructureTargetConfig(60));
    public static ConfiguredFeature<?, ?> CRIMSON_MINESHAFT_PLANTS = RSFeatures.STRUCTURE_CRIMSON_PLANTS.configured(new StructureTargetAndLengthConfig(40, 3));
    public static ConfiguredFeature<?, ?> CRIMSON_MINESHAFT_DENSE_PLANTS = RSFeatures.STRUCTURE_CRIMSON_PLANTS.configured(new StructureTargetAndLengthConfig(100, 3));
    public static ConfiguredFeature<?, ?> CRIMSON_MINESHAFT_PLANTS_LONG = RSFeatures.STRUCTURE_CRIMSON_PLANTS.configured(new StructureTargetAndLengthConfig(85, 7));
    public static ConfiguredFeature<?, ?> NETHER_MINESHAFT_FIRE = RSFeatures.STRUCTURE_FIRE.configured(new StructureTargetConfig(3));
    public static ConfiguredFeature<?, ?> NETHER_MINESHAFT_DENSE_FIRE = RSFeatures.STRUCTURE_FIRE.configured(new StructureTargetConfig(40));
    public static ConfiguredFeature<?, ?> NETHER_MINESHAFT_NETHERWART = RSFeatures.STRUCTURE_NETHERWART.configured(new StructureTargetConfig(40));
    public static ConfiguredFeature<?, ?> OCEAN_MINESHAFT_PLANTS = RSFeatures.STRUCTURE_SEAGRASS.configured(new StructureTargetConfig(40));
    public static ConfiguredFeature<?, ?> OCEAN_MINESHAFT_DENSE_PLANTS = RSFeatures.STRUCTURE_SEAGRASS.configured(new StructureTargetConfig(80));
    public static ConfiguredFeature<?, ?> DARK_FOREST_MINESHAFT_VINES = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(20, 2, 3));
    public static ConfiguredFeature<?, ?> DARK_FOREST_MINESHAFT_VINES_LONG = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(23, 8, 1));
    public static ConfiguredFeature<?, ?> SWAMP_MINESHAFT_VINES = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(10, 1, 3));
    public static ConfiguredFeature<?, ?> SWAMP_MINESHAFT_VINES_LONG = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(20, 8, 1));
    public static ConfiguredFeature<?, ?> JUNGLE_MINESHAFT_LEAVES_AND_VINES = RSFeatures.STRUCTURE_VINES_AND_LEAVES.configured(new StructureTargetAndLengthConfig(30, 3));
    public static ConfiguredFeature<?, ?> JUNGLE_MINESHAFT_VINES_LONG = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(25, 8, 1));
    public static ConfiguredFeature<?, ?> WARPED_MINESHAFT_PLANTS = RSFeatures.STRUCTURE_WARPED_PLANTS.configured(new StructureTargetAndLengthConfig(25, 3));
    public static ConfiguredFeature<?, ?> WARPED_MINESHAFT_DENSE_PLANTS = RSFeatures.STRUCTURE_WARPED_PLANTS.configured(new StructureTargetAndLengthConfig(80, 3));
    public static ConfiguredFeature<?, ?> WARPED_MINESHAFT_PLANTS_LONG = RSFeatures.STRUCTURE_WARPED_PLANTS.configured(new StructureTargetAndLengthConfig(60, 7));
    public static ConfiguredFeature<?, ?> JUNGLE_PYRAMID_STRUCTURE_VINES = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(17, 4, 3));
    public static ConfiguredFeature<?, ?> JUNGLE_PYRAMID_STRUCTURE_VINES_NARROW = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(17, 2, 1));
    public static ConfiguredFeature<?, ?> OCEAN_PYRAMID_STRUCTURE_PLANTS = RSFeatures.STRUCTURE_SEAGRASS.configured(new StructureTargetConfig(12));
    public static ConfiguredFeature<?, ?> FLOWER_FOREST_PYRAMID_STRUCTURE_GRASS = RSFeatures.STRUCTURE_GRASS.configured(new StructureTargetAndRangeConfig(24, 3));
    public static ConfiguredFeature<?, ?> FLOWER_FOREST_PYRAMID_STRUCTURE_FLOWERS = RSFeatures.STRUCTURE_FLOWERS.configured(new StructureTargetAndRangeConfig(8, 3));
    public static ConfiguredFeature<?, ?> SWAMP_VILLAGE_STRUCTURE_VINES = RSFeatures.STRUCTURE_VINES.configured(new StructureTargetLengthRangeConfig(10, 3, 4, 10));

    public static ConfiguredFeature<?, ?> WARM_LAND_RUINS_STRUCTURE_GRASS = Feature.RANDOM_PATCH
            .configured(Features.Configs.TALL_GRASS_CONFIG)
            .decorated(FeatureDecorator.HEIGHTMAP.configured(new HeightmapConfiguration(Heightmap.Types.OCEAN_FLOOR_WG)).squared())
                .decorated(RSPlacements.MINUS_EIGHT_PLACEMENT.configured(NoneDecoratorConfiguration.NONE))
                .count(2);

    public static ConfiguredFeature<?, ?> HOT_LAND_RUINS_STRUCTURE_DEAD_BUSH = Feature.RANDOM_PATCH
            .configured(Features.Configs.DEAD_BUSH_CONFIG)
            .decorated(FeatureDecorator.HEIGHTMAP.configured(new HeightmapConfiguration(Heightmap.Types.OCEAN_FLOOR_WG)).squared())
                .decorated(RSPlacements.MINUS_EIGHT_PLACEMENT.configured(NoneDecoratorConfiguration.NONE))
                .count(8);

    public static ConfiguredFeature<?, ?> BIRCH_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/birch/minecart")));
    public static ConfiguredFeature<?, ?> CRIMSON_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/crimson/minecart")));
    public static ConfiguredFeature<?, ?> DARK_FOREST_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/dark_forest/minecart")));
    public static ConfiguredFeature<?, ?> DESERT_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/desert/minecart")));
    public static ConfiguredFeature<?, ?> ICY_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/icy/minecart")));
    public static ConfiguredFeature<?, ?> JUNGLE_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/jungle/minecart")));
    public static ConfiguredFeature<?, ?> NETHER_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/nether/minecart")));
    public static ConfiguredFeature<?, ?> OCEAN_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/ocean/minecart"), true));
    public static ConfiguredFeature<?, ?> SAVANNA_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/savanna/minecart")));
    public static ConfiguredFeature<?, ?> STONE_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/stone/minecart")));
    public static ConfiguredFeature<?, ?> SWAMP_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/swamp/minecart")));
    public static ConfiguredFeature<?, ?> TAIGA_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/taiga/minecart")));
    public static ConfiguredFeature<?, ?> WARPED_MINESHAFT_MINECART = RSFeatures.MINESHAFT_MINECARTS.configured(new MinecartConfig(new ResourceLocation(RepurposedStructures.MODID, "mineshafts/warped/minecart")));

    public static ConfiguredFeature<?, ?> BIRCH_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.BIRCH_LOG.defaultBlockState(), Blocks.BIRCH_FENCE.defaultBlockState(), Blocks.BIRCH_PLANKS, false));
    public static ConfiguredFeature<?, ?> CRIMSON_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.CRIMSON_HYPHAE.defaultBlockState(), Blocks.CRIMSON_FENCE.defaultBlockState(), Blocks.CRIMSON_NYLIUM, false));
    public static ConfiguredFeature<?, ?> DARK_FOREST_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.DARK_OAK_LOG.defaultBlockState(), Blocks.DARK_OAK_FENCE.defaultBlockState(), Blocks.DARK_OAK_PLANKS, false));
    public static ConfiguredFeature<?, ?> DESERT_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.SMOOTH_SANDSTONE.defaultBlockState(), Blocks.SANDSTONE_WALL.defaultBlockState(), Blocks.SMOOTH_SANDSTONE, false));
    public static ConfiguredFeature<?, ?> ICY_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.PACKED_ICE.defaultBlockState(), Blocks.DIORITE_WALL.defaultBlockState(), Blocks.ICE, false));
    public static ConfiguredFeature<?, ?> JUNGLE_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.JUNGLE_LOG.defaultBlockState(), Blocks.JUNGLE_FENCE.defaultBlockState(), Blocks.JUNGLE_PLANKS, false));
    public static ConfiguredFeature<?, ?> NETHER_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.CRACKED_NETHER_BRICKS.defaultBlockState(), Blocks.NETHER_BRICK_FENCE.defaultBlockState(), Blocks.NETHER_BRICKS, false));
    public static ConfiguredFeature<?, ?> OCEAN_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.DARK_PRISMARINE.defaultBlockState(), Blocks.PRISMARINE_WALL.defaultBlockState(), Blocks.PRISMARINE_BRICKS, true));
    public static ConfiguredFeature<?, ?> SAVANNA_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.ACACIA_LOG.defaultBlockState(), Blocks.ACACIA_FENCE.defaultBlockState(), Blocks.ACACIA_PLANKS, false));
    public static ConfiguredFeature<?, ?> STONE_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.ANDESITE.defaultBlockState(), Blocks.COBBLESTONE_WALL.defaultBlockState(), Blocks.ANDESITE, false));
    public static ConfiguredFeature<?, ?> SWAMP_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), Blocks.MOSSY_STONE_BRICK_WALL.defaultBlockState(), Blocks.MOSS_BLOCK, false));
    public static ConfiguredFeature<?, ?> TAIGA_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_FENCE.defaultBlockState(), Blocks.SPRUCE_PLANKS, false));
    public static ConfiguredFeature<?, ?> WARPED_MINESHAFT_SUPPORT = RSFeatures.MINESHAFT_SUPPORTS.configured(new MineshaftSupportConfig(Blocks.WARPED_HYPHAE.defaultBlockState(), Blocks.WARPED_FENCE.defaultBlockState(), Blocks.WARPED_NYLIUM, false));

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_badlands"), BADLANDS_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_dark_forest"), DARK_FOREST_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_desert"), DESERT_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_end"), END_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_nether"), NETHER_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_snow"), SNOW_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_icy"), ICY_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_swamp"), SWAMP_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom_high"), MUSHROOM_HIGH_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_mushroom_low"), MUSHROOM_LOW_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_jungle"), JUNGLE_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_neutral_ocean"), OCEAN_NEUTRAL_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_lukewarm_ocean"), OCEAN_LUKEWARM_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_frozen_ocean"), OCEAN_FROZEN_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_cold_ocean"), OCEAN_COLD_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "dungeons_warm_ocean"), OCEAN_WARM_DUNGEONS));

        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_badlands"), BADLANDS_WELL));
        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_nether"), NETHER_WELL));
        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_snow"), SNOW_WELL));
        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_mossy_stone"), MOSSY_STONE_WELL));
        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_forest"), FOREST_WELL));
        RS_WELLS.add(Registry.register(registry, new ResourceLocation(RepurposedStructures.MODID, "well_mushroom"), MUSHROOM_WELL));

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
}
