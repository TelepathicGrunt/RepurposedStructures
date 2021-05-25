package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.features.configs.*;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

import java.util.ArrayList;
import java.util.List;


public class RSConfiguredFeatures {
    private static final Identifier EMPTY_ID = new Identifier("minecraft:empty");

    // Dungeons

    // Need this field so we can test to make sure we do not add the dungeons to biomes that
    // we already added an RS dungeon to. (Due to BiomeModification API running separately for each feature)
    public static List<ConfiguredFeature<?, ?>> RS_DUNGEONS = new ArrayList<>();

    private static final NbtDungeonConfig BADLANDS_DUNGEON_CONFIG = new NbtDungeonConfig("badlands", EMPTY_ID);
    public static ConfiguredFeature<?, ?> BADLANDS_DUNGEONS = RSFeatures.BADLANDS_DUNGEONS.configure(BADLANDS_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.badlandsDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.badlandsDungeonMaxHeight))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.badlandsDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig DARK_FOREST_DUNGEON_CONFIG = new NbtDungeonConfig("dark_forest", new Identifier(RepurposedStructures.MODID, "dungeons/dark_forest_post_process"));
    public static ConfiguredFeature<?, ?> DARK_FOREST_DUNGEONS = RSFeatures.DARK_FOREST_DUNGEONS.configure(DARK_FOREST_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.darkForestDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.darkForestDungeonMaxHeight))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.darkForestDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig DESERT_DUNGEON_CONFIG = new NbtDungeonConfig("desert", EMPTY_ID);
    public static ConfiguredFeature<?, ?> DESERT_DUNGEONS = RSFeatures.DESERT_DUNGEONS.configure(DESERT_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.desertDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.desertDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.desertDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig END_DUNGEON_CONFIG = new NbtDungeonConfig("end", EMPTY_ID, 20, Blocks.SHULKER_BOX.getDefaultState());
    public static ConfiguredFeature<?, ?> END_DUNGEONS = RSFeatures.END_DUNGEONS.configure(END_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.endDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.endDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.endDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig NETHER_DUNGEON_CONFIG = new NbtDungeonConfig("nether", EMPTY_ID);
    public static ConfiguredFeature<?, ?> NETHER_DUNGEONS = RSFeatures.NETHER_DUNGEONS.configure(NETHER_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.netherDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.netherDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.netherDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig SNOW_DUNGEON_CONFIG = new NbtDungeonConfig("snow", EMPTY_ID);
    public static ConfiguredFeature<?, ?> SNOW_DUNGEONS = RSFeatures.SNOW_DUNGEONS.configure(SNOW_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.snowDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.snowDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.snowDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig ICY_DUNGEON_CONFIG = new NbtDungeonConfig("icy", EMPTY_ID);
    public static ConfiguredFeature<?, ?> ICY_DUNGEONS = RSFeatures.ICY_DUNGEONS.configure(ICY_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.icyDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.icyDungeonMaxHeight))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.icyDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig SWAMP_DUNGEON_CONFIG = new NbtDungeonConfig("swamp", EMPTY_ID);
    public static ConfiguredFeature<?, ?> SWAMP_DUNGEONS = RSFeatures.SWAMP_DUNGEONS.configure(SWAMP_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.swampDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.swampDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.swampDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig MUSHROOM_HIGH_DUNGEON_CONFIG = new NbtDungeonConfig("mushroom", "mushroom_high", new Identifier(RepurposedStructures.MODID, "dungeons/mushroom_high"), EMPTY_ID);
    public static ConfiguredFeature<?, ?> MUSHROOM_HIGH_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.configure(MUSHROOM_HIGH_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    Math.max(63, RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight), 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(Math.round(Math.max(0,
                    ((RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight - 62F) /
                    (RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight - RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight)) *
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk
            ))))));

    private static final NbtDungeonConfig MUSHROOM_LOW_DUNGEON_CONFIG = new NbtDungeonConfig("mushroom", "mushroom_low", new Identifier(RepurposedStructures.MODID, "dungeons/mushroom_low"), EMPTY_ID);
    public static ConfiguredFeature<?, ?> MUSHROOM_LOW_DUNGEONS = RSFeatures.MUSHROOM_DUNGEONS.configure(MUSHROOM_LOW_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight, 0,
                    Math.min(62, RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight)))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(Math.round(Math.max(0,
                    ((62F - RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight) /
                    (RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.mushroomDungeonMaxHeight - RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.mushroomDungeonMinHeight)) *
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.mushroomDungeonAttemptsPerChunk
            ))))));

    private static final NbtDungeonConfig JUNGLE_DUNGEON_CONFIG = new NbtDungeonConfig("jungle", new Identifier(RepurposedStructures.MODID, "dungeons/jungle_post_process"));
    public static ConfiguredFeature<?, ?> JUNGLE_DUNGEONS = RSFeatures.JUNGLE_DUNGEONS.configure(JUNGLE_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.jungleDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.jungleDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.jungleDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig NEUTRAL_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_neutral", new Identifier(RepurposedStructures.MODID, "dungeons/ocean_neutral"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_NEUTRAL_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(NEUTRAL_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))
            .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig COLD_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_cold", new Identifier(RepurposedStructures.MODID, "dungeons/ocean_cold"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_COLD_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(COLD_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig FROZEN_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_frozen", new Identifier(RepurposedStructures.MODID, "dungeons/ocean_frozen"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_FROZEN_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(FROZEN_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig LUKEWARM_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_lukewarm", new Identifier(RepurposedStructures.MODID, "dungeons/ocean_lukewarm"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_LUKEWARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(LUKEWARM_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));

    private static final NbtDungeonConfig WARM_DUNGEON_CONFIG = new NbtDungeonConfig("ocean", "ocean_warm", new Identifier(RepurposedStructures.MODID, "dungeons/ocean_warm"), EMPTY_ID, 55, true, -2);
    public static ConfiguredFeature<?, ?> OCEAN_WARM_DUNGEONS = RSFeatures.OCEAN_DUNGEONS.configure(WARM_DUNGEON_CONFIG)
            .decorate(RSPlacements.RS_DUNGEON_PLACEMENT.configure(new RangeDecoratorConfig(
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.minHeight.oceanDungeonMinHeight, 0,
                    RepurposedStructures.RSAllConfig.RSDungeonsConfig.maxHeight.oceanDungeonMaxHeight))
                    .decorate(RSPlacements.RS_UNLIMITED_COUNT.configure(new CountConfig(RepurposedStructures.RSAllConfig.RSDungeonsConfig.attemptsPerChunk.oceanDungeonAttemptsPerChunk))));


    // Wells
    public static ConfiguredFeature<?, ?> BADLANDS_WELL = RSFeatures.BADLANDS_WELL.configure(FeatureConfig.DEFAULT)
                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.badlandsWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> NETHER_WELL = RSFeatures.NETHER_WELL.configure(FeatureConfig.DEFAULT)
                    .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(30, 0, 91)))
                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.netherWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> SNOW_WELL = RSFeatures.SNOW_WELL.configure(FeatureConfig.DEFAULT)
                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.snowWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> MOSSY_STONE_WELL = RSFeatures.MOSSY_STONE_WELL.configure(FeatureConfig.DEFAULT)
                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.mossyStoneWellRarityPerChunk);

    public static ConfiguredFeature<?, ?> FOREST_WELL = RSFeatures.FOREST_WELL.configure(FeatureConfig.DEFAULT)
                    .decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
                    .applyChance(RepurposedStructures.RSAllConfig.RSWellsConfig.rarityPerChunk.forestWellRarityPerChunk);

    public static List<ConfiguredFeature<?, ?>> RS_WELLS = new ArrayList<>();

    // Misc

    public static ConfiguredFeature<?, ?> LILY_OF_THE_VALLEY_FEATURE = Feature.FLOWER.configure(
            (new RandomPatchFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.LILY_OF_THE_VALLEY.getDefaultState()),
                    SimpleBlockPlacer.INSTANCE))
            .tries(64)
            .build());

    public static ConfiguredFeature<?, ?> CRIMSON_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configure(HugeFungusFeatureConfig.CRIMSON_FUNGUS_NOT_PLANTED_CONFIG);
    public static ConfiguredFeature<?, ?> WARPED_FUNGI_NOT_PLANTED = Feature.HUGE_FUNGUS.configure(HugeFungusFeatureConfig.WARPED_FUNGUS_NOT_PLANTED_CONFIG);
    public static ConfiguredFeature<?, ?> COBBLESTONE_PATCH = Feature.BLOCK_PILE.configure(new BlockPileFeatureConfig(new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState())));
    public static ConfiguredFeature<?, ?> CHORUS_PLANT = Feature.CHORUS_PLANT.configure(FeatureConfig.DEFAULT);

    public static ConfiguredFeature<?, ?> WITHER_SKELETON_WITH_BOW = RSFeatures.WITHER_SKELETON_WITH_BOW.configure(FeatureConfig.DEFAULT);
    public static ConfiguredFeature<?, ?> SHULKER_MOB = RSFeatures.SHULKER_MOB.configure(FeatureConfig.DEFAULT);
    public static ConfiguredFeature<?, ?> DROWNED_WITH_ARMOR = RSFeatures.DROWNED_WITH_ARMOR.configure(FeatureConfig.DEFAULT);

    public static ConfiguredFeature<?, ?> POST_PROCESS_CONNECTING_BLOCKS = RSFeatures.POST_PROCESS_CONNECTING_BLOCKS.configure(FeatureConfig.DEFAULT);
    public static ConfiguredFeature<?, ?> JUNGLE_FORTRESS_STRUCTURE_BREAKAGE = RSFeatures.STRUCTURE_BREAKAGE.configure(new StructureTargetChanceConfig(RSStructures.JUNGLE_FORTRESS, 0.366f));
    public static ConfiguredFeature<?, ?> JUNGLE_FORTRESS_STRUCTURE_VINE_BREAKAGE = RSFeatures.STRUCTURE_VINE_BREAKAGE.configure(new StructureTargetAndLengthConfig(RSStructures.JUNGLE_FORTRESS, 15, 4));
    public static ConfiguredFeature<?, ?> JUNGLE_FORTRESS_STRUCTURE_VINES = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(RSStructures.JUNGLE_FORTRESS, 10, 3, 3));
    public static ConfiguredFeature<?, ?> NETHER_STRONGHOLD_CHAINS = RSFeatures.STRUCTURE_CHAINS.configure(new StructureTargetConfig(RSStructures.NETHER_STRONGHOLD, 7));
    public static ConfiguredFeature<?, ?> END_MINESHAFT_CHORUS = RSFeatures.STRUCTURE_CHORUS.configure(new StructureTargetConfig(RSStructures.END_MINESHAFT, 4));
    public static ConfiguredFeature<?, ?> END_MINESHAFT_DENSE_CHORUS = RSFeatures.STRUCTURE_CHORUS.configure(new StructureTargetConfig(RSStructures.END_MINESHAFT, 60));
    public static ConfiguredFeature<?, ?> CRIMSON_MINESHAFT_PLANTS = RSFeatures.STRUCTURE_CRIMSON_PLANTS.configure(new StructureTargetAndLengthConfig(RSStructures.CRIMSON_MINESHAFT, 40, 3));
    public static ConfiguredFeature<?, ?> CRIMSON_MINESHAFT_DENSE_PLANTS = RSFeatures.STRUCTURE_CRIMSON_PLANTS.configure(new StructureTargetAndLengthConfig(RSStructures.CRIMSON_MINESHAFT, 100, 3));
    public static ConfiguredFeature<?, ?> CRIMSON_MINESHAFT_PLANTS_LONG = RSFeatures.STRUCTURE_CRIMSON_PLANTS.configure(new StructureTargetAndLengthConfig(RSStructures.CRIMSON_MINESHAFT, 85, 7));
    public static ConfiguredFeature<?, ?> NETHER_MINESHAFT_FIRE = RSFeatures.STRUCTURE_FIRE.configure(new StructureTargetConfig(RSStructures.NETHER_MINESHAFT, 3));
    public static ConfiguredFeature<?, ?> NETHER_MINESHAFT_DENSE_FIRE = RSFeatures.STRUCTURE_FIRE.configure(new StructureTargetConfig(RSStructures.NETHER_MINESHAFT, 40));
    public static ConfiguredFeature<?, ?> NETHER_MINESHAFT_NETHERWART = RSFeatures.STRUCTURE_NETHERWART.configure(new StructureTargetConfig(RSStructures.NETHER_MINESHAFT, 40));
    public static ConfiguredFeature<?, ?> OCEAN_MINESHAFT_PLANTS = RSFeatures.STRUCTURE_SEAGRASS.configure(new StructureTargetConfig(RSStructures.OCEAN_MINESHAFT, 40));
    public static ConfiguredFeature<?, ?> OCEAN_MINESHAFT_DENSE_PLANTS = RSFeatures.STRUCTURE_SEAGRASS.configure(new StructureTargetConfig(RSStructures.OCEAN_MINESHAFT, 80));
    public static ConfiguredFeature<?, ?> DARK_FOREST_MINESHAFT_VINES = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(RSStructures.DARK_FOREST_MINESHAFT, 20, 2, 3));
    public static ConfiguredFeature<?, ?> DARK_FOREST_MINESHAFT_VINES_LONG = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(RSStructures.DARK_FOREST_MINESHAFT, 23, 8, 1));
    public static ConfiguredFeature<?, ?> SWAMP_MINESHAFT_VINES = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(RSStructures.SWAMP_MINESHAFT, 10, 1, 3));
    public static ConfiguredFeature<?, ?> SWAMP_MINESHAFT_VINES_LONG = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(RSStructures.SWAMP_MINESHAFT, 20, 8, 1));
    public static ConfiguredFeature<?, ?> JUNGLE_MINESHAFT_LEAVES_AND_VINES = RSFeatures.STRUCTURE_VINES_AND_LEAVES.configure(new StructureTargetAndLengthConfig(RSStructures.JUNGLE_MINESHAFT, 30, 3));
    public static ConfiguredFeature<?, ?> JUNGLE_MINESHAFT_VINES_LONG = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(RSStructures.JUNGLE_MINESHAFT, 25, 8, 1));
    public static ConfiguredFeature<?, ?> WARPED_MINESHAFT_PLANTS = RSFeatures.STRUCTURE_WARPED_PLANTS.configure(new StructureTargetAndLengthConfig(RSStructures.WARPED_MINESHAFT, 25, 3));
    public static ConfiguredFeature<?, ?> WARPED_MINESHAFT_DENSE_PLANTS = RSFeatures.STRUCTURE_WARPED_PLANTS.configure(new StructureTargetAndLengthConfig(RSStructures.WARPED_MINESHAFT, 80, 3));
    public static ConfiguredFeature<?, ?> WARPED_MINESHAFT_PLANTS_LONG = RSFeatures.STRUCTURE_WARPED_PLANTS.configure(new StructureTargetAndLengthConfig(RSStructures.WARPED_MINESHAFT, 60, 7));
    public static ConfiguredFeature<?, ?> JUNGLE_PYRAMID_STRUCTURE_VINES = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(RSStructures.PYRAMID_JUNGLE, 17, 4, 3));
    public static ConfiguredFeature<?, ?> JUNGLE_PYRAMID_STRUCTURE_VINES_NARROW = RSFeatures.STRUCTURE_VINES.configure(new StructureTargetLengthRangeConfig(RSStructures.PYRAMID_JUNGLE, 17, 2, 1));

    public static void registerConfiguredFeatures() {
        Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_badlands"), BADLANDS_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_dark_forest"), DARK_FOREST_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_desert"), DESERT_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_end"), END_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_nether"), NETHER_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_snow"), SNOW_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_icy"), ICY_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_swamp"), SWAMP_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_mushroom_high"), MUSHROOM_HIGH_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_mushroom_low"), MUSHROOM_LOW_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_jungle"), JUNGLE_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_neutral_ocean"), OCEAN_NEUTRAL_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_lukewarm_ocean"), OCEAN_LUKEWARM_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_frozen_ocean"), OCEAN_FROZEN_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_cold_ocean"), OCEAN_COLD_DUNGEONS));
        RS_DUNGEONS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dungeons_warm_ocean"), OCEAN_WARM_DUNGEONS));

        RS_WELLS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_badlands"), BADLANDS_WELL));
        RS_WELLS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_nether"), NETHER_WELL));
        RS_WELLS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_snow"), SNOW_WELL));
        RS_WELLS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_mossy_stone"), MOSSY_STONE_WELL));
        RS_WELLS.add(Registry.register(registry, new Identifier(RepurposedStructures.MODID, "well_forest"), FOREST_WELL));

        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "lily_of_the_valley"), LILY_OF_THE_VALLEY_FEATURE);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "crimson_fungi_not_planted"), CRIMSON_FUNGI_NOT_PLANTED);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "warped_fungi_not_planted"), WARPED_FUNGI_NOT_PLANTED);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "cobblestone_patch"), COBBLESTONE_PATCH);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "chorus_plant"), CHORUS_PLANT);

        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "wither_skeleton_with_bow"), WITHER_SKELETON_WITH_BOW);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "shulker_mob"), SHULKER_MOB);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "drowned_with_armor"), DROWNED_WITH_ARMOR);

        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "post_process_connecting_blocks"), POST_PROCESS_CONNECTING_BLOCKS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_fortress_structure_breakage"), JUNGLE_FORTRESS_STRUCTURE_BREAKAGE);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_fortress_structure_vine_breakage"), JUNGLE_FORTRESS_STRUCTURE_VINE_BREAKAGE);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_fortress_structure_vines"), JUNGLE_FORTRESS_STRUCTURE_VINES);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "nether_stronghold_chains"), NETHER_STRONGHOLD_CHAINS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "end_mineshaft_chorus"), END_MINESHAFT_CHORUS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "end_mineshaft_dense_chorus"), END_MINESHAFT_DENSE_CHORUS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "crimson_mineshaft_plants"), CRIMSON_MINESHAFT_PLANTS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "crimson_mineshaft_dense_plants"), CRIMSON_MINESHAFT_DENSE_PLANTS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "crimson_mineshaft_plants_long"), CRIMSON_MINESHAFT_PLANTS_LONG);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "nether_mineshaft_fire"), NETHER_MINESHAFT_FIRE);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "nether_mineshaft_dense_fire"), NETHER_MINESHAFT_DENSE_FIRE);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "nether_mineshaft_netherwart"), NETHER_MINESHAFT_NETHERWART);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "ocean_mineshaft_plants"), OCEAN_MINESHAFT_PLANTS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "ocean_mineshaft_dense_plants"), OCEAN_MINESHAFT_DENSE_PLANTS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dark_forest_mineshaft_vines"), DARK_FOREST_MINESHAFT_VINES);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "dark_forest_mineshaft_vines_long"), DARK_FOREST_MINESHAFT_VINES_LONG);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "swamp_mineshaft_vines"), SWAMP_MINESHAFT_VINES);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "swamp_mineshaft_vines_long"), SWAMP_MINESHAFT_VINES_LONG);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_mineshaft_leaves_and_vines"), JUNGLE_MINESHAFT_LEAVES_AND_VINES);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_mineshaft_vines_long"), JUNGLE_MINESHAFT_VINES_LONG);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "warped_mineshaft_plants"), WARPED_MINESHAFT_PLANTS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "warped_mineshaft_dense_plants"), WARPED_MINESHAFT_DENSE_PLANTS);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "warped_mineshaft_plants_long"), WARPED_MINESHAFT_PLANTS_LONG);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_pyramid_structure_vines"), JUNGLE_PYRAMID_STRUCTURE_VINES);
        Registry.register(registry, new Identifier(RepurposedStructures.MODID, "jungle_pyramid_structure_vines_narrow"), JUNGLE_PYRAMID_STRUCTURE_VINES_NARROW);
    }
}
